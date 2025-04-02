package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.Metal.MetalPair;
import com.bioxx.tfc.Core.Metal.MetalRegistry;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TECrucible
  extends NetworkTileEntity
  implements IInventory {
  public Map<String, MetalPair> metals = new HashMap<>();

  public Alloy currentAlloy;
  public int temperature;
  public ItemStack[] storage;
  public byte inputTick;
  public byte outputTick;
  public byte tempTick;
  private int cookDelay;
  public static final int MAX_UNITS = 3000;

  public TECrucible() {
    this.storage = new ItemStack[2];
    this.broadcastRange = 5;
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);

    nbt.func_74768_a("temp", this.temperature);

    NBTTagList nbttaglist = new NBTTagList();
    Iterator<MetalPair> iter = this.metals.values().iterator();
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74768_a("ID", Item.func_150891_b(m.type.ingot));
        nbttagcompound1.func_74776_a("AmountF", m.amount);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Metals", (NBTBase)nbttaglist);

    nbttaglist = new NBTTagList();
    for (int i = 0; i < this.storage.length; i++) {

      if (this.storage[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.storage[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbt.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    readFromItemNBT(nbt);
  }


  public void readFromItemNBT(NBTTagCompound nbt) {
    this.temperature = nbt.func_74762_e("temp");
    NBTTagList nbttaglist = nbt.func_150295_c("Metals", 10);
    int i;
    for (i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      int id = nbttagcompound1.func_74762_e("ID");
      float amount = nbttagcompound1.func_74765_d("Amount");

      float amountF = amount + nbttagcompound1.func_74760_g("AmountF");
      addMetal(MetalRegistry.instance.getMetalFromItem(Item.func_150899_d(id)), amountF);
    }

    nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    for (i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      this.inputTick = (byte)(this.inputTick + 1);
      this.outputTick = (byte)(this.outputTick + 1);
      this.tempTick = (byte)(this.tempTick + 1);

      if (this.cookDelay > 0) {
        this.cookDelay--;
      }

      if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) == TFCBlocks.forge) {

        TEForge te = (TEForge)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (te.fireTemp >= 1.0F && TFCOptions.enableDebugMode) {
          this.temperature = 2000;
        } else if (te.fireTemp > this.temperature) {
          this.temperature++;
        }
      }  if (this.tempTick > 22) {

        this.tempTick = 0;
        if (this.temperature > TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
          this.temperature--;
        }
      }
      ItemStack stackToSmelt = this.storage[0];
      if (stackToSmelt != null) {

        Item itemToSmelt = stackToSmelt.func_77973_b();
        int newDamage = stackToSmelt.func_77960_j() + 1;
        int maxDamage = stackToSmelt.func_77958_k() - 1;

        if (itemToSmelt instanceof com.bioxx.tfc.Items.ItemMeltedMetal && TFC_ItemHeat.getIsLiquid(this.storage[0]).booleanValue()) {

          if (this.inputTick > 10)
          {
            Metal inputMetal = MetalRegistry.instance.getMetalFromItem(itemToSmelt);

            if (this.currentAlloy != null && this.currentAlloy.outputType != null && itemToSmelt == this.currentAlloy.outputType.meltedItem) {

              addMetal(inputMetal, 1.0F);
              if (newDamage >= maxDamage)
              {
                this.storage[0] = new ItemStack(TFCItems.ceramicMold, 1, 1);
              }
              else
              {
                stackToSmelt.func_77964_b(newDamage);
              }

            } else {

              addMetal(inputMetal, 1.0F);
              if (newDamage >= maxDamage) {

                this.storage[0] = new ItemStack(TFCItems.ceramicMold, 1, 1);
              }
              else {

                stackToSmelt.func_77964_b(newDamage);
              }
            }
            this.inputTick = 0;
            updateGui((byte)0);
          }

        } else if (itemToSmelt instanceof ISmeltable && ((ISmeltable)itemToSmelt)
          .isSmeltable(stackToSmelt) &&
          !TFC_Core.isOreIron(stackToSmelt) && this.temperature >=
          TFC_ItemHeat.isCookable(stackToSmelt) && this.cookDelay == 0) {

          Metal mType = ((ISmeltable)itemToSmelt).getMetalType(stackToSmelt);
          if (addMetal(mType, ((ISmeltable)itemToSmelt).getMetalReturnAmount(stackToSmelt))) {

            this.temperature = (int)(this.temperature * 0.9F);
            this.cookDelay = 40;
            if (stackToSmelt.field_77994_a <= 1) {
              this.storage[0] = null;
            } else {
              (this.storage[0]).field_77994_a--;
            }  updateGui((byte)0);
          }
        }
      }

      if (this.currentAlloy != null && this.storage[1] != null && this.currentAlloy.outputType != null && this.outputTick >= 2 && this.temperature >=



        TFC_ItemHeat.isCookable(this.currentAlloy.outputType)) {

        if (this.storage[1].func_77973_b() == TFCItems.ceramicMold) {

          this.storage[1] = new ItemStack(this.currentAlloy.outputType.meltedItem, 1, 99);
          TFC_ItemHeat.setTemp(this.storage[1], this.temperature);

          drainOutput(1.0F);
          updateGui((byte)1);
        }
        else if (this.storage[1].func_77973_b() == this.currentAlloy.outputType.meltedItem && this.storage[1].func_77960_j() > 0) {

          this.storage[1].func_77964_b(this.storage[1].func_77960_j() - 1);
          float inTemp = TFC_ItemHeat.getTemp(this.storage[1]);
          float temp = (this.temperature - inTemp) / 2.0F;
          TFC_ItemHeat.setTemp(this.storage[1], inTemp + temp);

          drainOutput(1.0F);
          (this.storage[1]).field_77994_a = 1;
          updateGui((byte)1);
        }
        this.outputTick = 0;
      }

      if (this.currentAlloy != null && getTotalMetal() < 1.0F) {

        this.metals = new HashMap<>();
        updateCurrentAlloy();
        updateGui((byte)2);
        this.currentAlloy = null;
      }

      if (this.storage[1] != null && (this.storage[1]).field_77994_a <= 0)
        (this.storage[1]).field_77994_a = 1;
      if (this.inputTick > 10)
        this.inputTick = 0;
      if (this.outputTick >= 2) {
        this.outputTick = 0;
      }
    }
  }

  public boolean drainOutput(float amount) {
    if (this.metals != null && this.metals.values().size() > 0) {

      for (MetalPair am : this.metals.values()) {

        float percent = this.currentAlloy.getPercentForMetal(am.type) / 100.0F;
        am.amount -= amount * percent;
      }
      updateCurrentAlloy();
    }
    return true;
  }


  public boolean addMetal(Metal m, float amt) {
    if (getTotalMetal() + amt <= 3000.0F && m.name != null && !"Unknown".equals(m.name)) {

      if (this.metals.containsKey(m.name)) {
        ((MetalPair)this.metals.get(m.name)).amount += amt;
      } else {
        this.metals.put(m.name, new MetalPair(m, amt));
      }
      updateCurrentAlloy();
      return true;
    }
    return false;
  }


  public float getTotalMetal() {
    Iterator<MetalPair> iter = this.metals.values().iterator();
    float totalAmount = 0.0F;
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null)
        totalAmount += m.amount;
    }
    return totalAmount;
  }


  private void updateCurrentAlloy() {
    List<AlloyMetal> a = new ArrayList<>();
    Iterator<MetalPair> iter = this.metals.values().iterator();
    float totalAmount = getTotalMetal();
    iter = this.metals.values().iterator();
    while (iter.hasNext()) {

      MetalPair m = iter.next();
      if (m != null) {
        a.add(new AlloyMetal(m.type, m.amount / totalAmount * 100.0F));
      }
    }
    Metal match = AlloyManager.INSTANCE.matchesAlloy(a, Alloy.EnumTier.TierV);
    if (match != null) {

      this.currentAlloy = new Alloy(match, totalAmount);
      this.currentAlloy.alloyIngred = a;
    }
    else {

      this.currentAlloy = new Alloy(Global.UNKNOWN, totalAmount);
      this.currentAlloy.alloyIngred = a;
    }
  }



  public int func_70302_i_() {
    return 2;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70298_a(int i, int j) {
    if (this.storage[i] != null) {

      if ((this.storage[i]).field_77994_a <= j) {

        ItemStack itemstack = this.storage[i];
        this.storage[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.storage[i].func_77979_a(j);
      if ((this.storage[i]).field_77994_a == 0)
        this.storage[i] = null;
      return itemstack1;
    }


    return null;
  }




  public ItemStack func_70304_b(int i) {
    return this.storage[i];
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
  }



  public String func_145825_b() {
    return "Crucible";
  }



  public boolean func_145818_k_() {
    return false;
  }



  public int func_70297_j_() {
    return 64;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return true;
  }




  public void func_70295_k_() {}




  public void func_70305_f() {}



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return true;
  }


  public int getOutCountScaled(int length) {
    if (this.currentAlloy != null) {
      return (int)this.currentAlloy.outputAmount * length / 3000;
    }
    return 0;
  }


  public int getTemperatureScaled(int s) {
    return this.temperature * s / 2500;
  }


  public void updateGui(byte action) {
    if (!this.field_145850_b.field_72995_K) {

      NBTTagCompound nbt = new NBTTagCompound();
      nbt.func_74774_a("action", action);
      if (this.currentAlloy != null) {
        if (action == 0) {
          this.currentAlloy.toNBT(nbt);
        }
        else if (action == 1 && this.currentAlloy != null) {
          nbt.func_74776_a("outputAmount", this.currentAlloy.outputAmount);
        }
      }
      broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
    }
  }



  public void handleInitPacket(NBTTagCompound nbt) {}



  public void handleDataPacket(NBTTagCompound nbt) {
    byte action = nbt.func_74771_c("action");
    if (action == 0) {
      this.currentAlloy = (new Alloy()).fromNBT(nbt);
    } else if (action == 1 && this.currentAlloy != null) {

      this.currentAlloy.outputAmount = nbt.func_74760_g("outputAmount");
    }
    else if (action == 2) {
      this.currentAlloy = null;
    }
  }

  public void createInitNBT(NBTTagCompound nbt) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TECrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */