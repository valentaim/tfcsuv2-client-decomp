package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
import com.bioxx.tfc.api.Events.ItemCookEvent;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.ICookableFood;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;








public class TEFirepit
  extends TEFireEntity
  implements IInventory
{
  public ItemStack[] fireItemStacks = new ItemStack[11];

  public boolean hasCookingPot = true;

  public int smokeTimer;


  public void func_70305_f() {}


  public void combineMetals(ItemStack inputItem, ItemStack destItem) {
    int d1 = 100 - inputItem.func_77960_j();
    int d2 = 100 - destItem.func_77960_j();

    destItem.func_77964_b(100 - d1 + d2);
  }


  public void cookItem() {
    HeatRegistry manager = HeatRegistry.getInstance();
    Random r = new Random();
    if (this.fireItemStacks[1] != null) {

      HeatIndex index = manager.findMatchingIndex(this.fireItemStacks[1]);
      if (index != null && TFC_ItemHeat.getTemp(this.fireItemStacks[1]) > index.meltTemp) {

        ItemStack output = index.getOutput(this.fireItemStacks[1], r);
        ItemCookEvent eventMelt = new ItemCookEvent(this.fireItemStacks[1], output, (TileEntity)this);
        MinecraftForge.EVENT_BUS.post((Event)eventMelt);
        output = eventMelt.result;
        int damage = 0;
        ItemStack mold = null;
        if (output != null) {

          damage = output.func_77960_j();
          if (output.func_77973_b() == this.fireItemStacks[1].func_77973_b()) {
            damage = this.fireItemStacks[1].func_77960_j();
          }

          if (this.fireItemStacks[1].func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {


            if (this.fireItemStacks[7] == null && this.fireItemStacks[8] == null) {

              this.fireItemStacks[7] = this.fireItemStacks[1].func_77946_l();
              this.fireItemStacks[1] = null;

              return;
            }
            if (this.fireItemStacks[7] != null && this.fireItemStacks[7].func_77973_b() != TFCItems.ceramicMold && (this.fireItemStacks[7]
              .func_77973_b() != this.fireItemStacks[1].func_77973_b() || this.fireItemStacks[7].func_77960_j() == 0))
            {
              if (this.fireItemStacks[8] == null) {

                this.fireItemStacks[8] = this.fireItemStacks[1].func_77946_l();
                this.fireItemStacks[1] = null;
                return;
              }
            }
            mold = new ItemStack(TFCItems.ceramicMold, 1);
            mold.field_77994_a = 1;
            mold.func_77964_b(1);
          }
        }

        float temp = TFC_ItemHeat.getTemp(this.fireItemStacks[1]);
        this.fireItemStacks[1] = index.getMorph();
        if (this.fireItemStacks[1] != null && manager.findMatchingIndex(this.fireItemStacks[1]) != null)
        {

          TFC_ItemHeat.setTemp(this.fireItemStacks[1], temp);
        }


        if (output != null && output.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {

          int leftover = 0;
          boolean addLeftover = false;
          int fromSide = 0;
          if (this.fireItemStacks[7] != null && output.func_77973_b() == this.fireItemStacks[7].func_77973_b() && this.fireItemStacks[7].func_77960_j() > 0) {

            int amt1 = 100 - damage;
            int amt2 = 100 - this.fireItemStacks[7].func_77960_j();
            int amt3 = amt1 + amt2;
            leftover = amt3 - 100;
            if (leftover > 0)
              addLeftover = true;
            int amt4 = 100 - amt3;
            if (amt4 < 0)
              amt4 = 0;
            this.fireItemStacks[7] = output.func_77946_l();
            this.fireItemStacks[7].func_77964_b(amt4);

            TFC_ItemHeat.setTemp(this.fireItemStacks[7], temp);

            if (this.fireItemStacks[1] == null && mold != null) {
              this.fireItemStacks[1] = mold;
            }
          } else if (this.fireItemStacks[8] != null && output.func_77973_b() == this.fireItemStacks[8].func_77973_b() && this.fireItemStacks[8].func_77960_j() > 0) {

            int amt1 = 100 - damage;
            int amt2 = 100 - this.fireItemStacks[8].func_77960_j();
            int amt3 = amt1 + amt2;
            leftover = amt3 - 100;
            if (leftover > 0)
              addLeftover = true;
            fromSide = 1;
            int amt4 = 100 - amt3;
            if (amt4 < 0)
              amt4 = 0;
            this.fireItemStacks[8] = output.func_77946_l();
            this.fireItemStacks[8].func_77964_b(amt4);

            TFC_ItemHeat.setTemp(this.fireItemStacks[8], temp);

            if (this.fireItemStacks[1] == null && mold != null) {
              this.fireItemStacks[1] = mold;
            }
          } else if (this.fireItemStacks[7] != null && this.fireItemStacks[7].func_77973_b() == TFCItems.ceramicMold) {

            this.fireItemStacks[7] = output.func_77946_l();
            this.fireItemStacks[7].func_77964_b(damage);

            TFC_ItemHeat.setTemp(this.fireItemStacks[7], temp);
          }
          else if (this.fireItemStacks[8] != null && this.fireItemStacks[8].func_77973_b() == TFCItems.ceramicMold) {

            this.fireItemStacks[8] = output.func_77946_l();
            this.fireItemStacks[8].func_77964_b(damage);

            TFC_ItemHeat.setTemp(this.fireItemStacks[8], temp);
          }

          if (addLeftover) {

            int dest = (fromSide == 1) ? 7 : 8;
            if (this.fireItemStacks[dest] != null && output.func_77973_b() == this.fireItemStacks[dest].func_77973_b() && this.fireItemStacks[dest].func_77960_j() > 0)
            {
              int amt1 = 100 - leftover;
              int amt2 = 100 - this.fireItemStacks[dest].func_77960_j();
              int amt3 = amt1 + amt2;
              int amt4 = 100 - amt3;
              if (amt4 < 0)
                amt4 = 0;
              this.fireItemStacks[dest] = output.func_77946_l();
              this.fireItemStacks[dest].func_77964_b(amt4);

              TFC_ItemHeat.setTemp(this.fireItemStacks[dest], temp);
            }
            else if (this.fireItemStacks[dest] != null && this.fireItemStacks[dest].func_77973_b() == TFCItems.ceramicMold)
            {
              this.fireItemStacks[dest] = output.func_77946_l();
              this.fireItemStacks[dest].func_77964_b(100 - leftover);
              TFC_ItemHeat.setTemp(this.fireItemStacks[dest], temp);
            }

          }
        } else if (output != null) {

          if (this.fireItemStacks[7] != null && this.fireItemStacks[7]
            .func_77973_b() == output.func_77973_b() && (this.fireItemStacks[7]).field_77994_a + output.field_77994_a <= this.fireItemStacks[7]
            .func_77976_d()) {

            (this.fireItemStacks[7]).field_77994_a += output.field_77994_a;
          }
          else if (this.fireItemStacks[8] != null && this.fireItemStacks[8]
            .func_77973_b() == output.func_77973_b() && (this.fireItemStacks[8]).field_77994_a + output.field_77994_a <= this.fireItemStacks[8]
            .func_77976_d()) {

            (this.fireItemStacks[8]).field_77994_a += output.field_77994_a;
          }
          else if (this.fireItemStacks[7] == null) {

            this.fireItemStacks[7] = output.func_77946_l();
          }
          else if (this.fireItemStacks[8] == null) {

            this.fireItemStacks[8] = output.func_77946_l();
          }
          else if (((this.fireItemStacks[7]).field_77994_a == this.fireItemStacks[7].func_77976_d() && (this.fireItemStacks[8]).field_77994_a == this.fireItemStacks[8]
            .func_77976_d()) || (this.fireItemStacks[7]
            .func_77973_b() != output.func_77973_b() && this.fireItemStacks[8].func_77973_b() != output.func_77973_b()) || ((this.fireItemStacks[7]).field_77994_a == this.fireItemStacks[7]
            .func_77976_d() && this.fireItemStacks[8].func_77973_b() != output.func_77973_b()) || (this.fireItemStacks[7]
            .func_77973_b() != output.func_77973_b() && (this.fireItemStacks[8]).field_77994_a == this.fireItemStacks[8].func_77976_d())) {

            this.fireItemStacks[1] = output.func_77946_l();
          }
        }
      }
    }
  }



  public ItemStack func_70298_a(int slot, int amount) {
    if (this.fireItemStacks[slot] != null) {

      if ((this.fireItemStacks[slot]).field_77994_a <= amount) {

        ItemStack itemstack = this.fireItemStacks[slot];
        this.fireItemStacks[slot] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.fireItemStacks[slot].func_77979_a(amount);
      if ((this.fireItemStacks[slot]).field_77994_a == 0)
        this.fireItemStacks[slot] = null;
      return itemstack1;
    }

    return null;
  }


  public void ejectContents() {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 0.8F + 0.3F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.fireItemStacks[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.fireItemStacks[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.fireItemStacks[i] = null;
      }
    }
  }



  public int func_70297_j_() {
    return 64;
  }



  public String func_145825_b() {
    return "Firepit";
  }


  public float getOutput1Temp() {
    return TFC_ItemHeat.getTemp(this.fireItemStacks[7]);
  }


  public float getOutput2Temp() {
    return TFC_ItemHeat.getTemp(this.fireItemStacks[8]);
  }



  public int func_70302_i_() {
    return this.fireItemStacks.length;
  }



  public ItemStack func_70301_a(int slot) {
    return this.fireItemStacks[slot];
  }



  public ItemStack func_70304_b(int slot) {
    return null;
  }


  public void handleFuelStack() {
    if (this.fireItemStacks[3] == null && this.fireItemStacks[0] != null) {

      this.fireItemStacks[3] = this.fireItemStacks[0];
      this.fireItemStacks[0] = null;
    }
    if (this.fireItemStacks[4] == null && this.fireItemStacks[3] != null) {

      this.fireItemStacks[4] = this.fireItemStacks[3];
      this.fireItemStacks[3] = null;
    }
    if (this.fireItemStacks[5] == null && this.fireItemStacks[4] != null) {

      this.fireItemStacks[5] = this.fireItemStacks[4];
      this.fireItemStacks[4] = null;
    }
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_70299_a(int slot, ItemStack is) {
    this.fireItemStacks[slot] = is;
    if (is != null && is.field_77994_a > func_70297_j_()) {
      is.field_77994_a = func_70297_j_();
    }
  }


  public void careForInventorySlot(ItemStack is) {
    if (is != null) {

      HeatRegistry manager = HeatRegistry.getInstance();
      HeatIndex index = manager.findMatchingIndex(is);

      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        if (this.fuelTimeLeft > 0 && is.func_77973_b() instanceof ICookableFood) {

          float inc = Food.getCooked(is) + Math.min(this.fireTemp / 700.0F, 2.0F);
          Food.setCooked(is, inc);
          temp = inc;
          if (Food.isCooked(is))
          {
            int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };

            Random r = new Random((((ICookableFood)is.func_77973_b()).getFoodID() + ((int)Food.getCooked(is) - 600) / 120));
            cookedTasteProfile[0] = r.nextInt(31) - 15;
            cookedTasteProfile[1] = r.nextInt(31) - 15;
            cookedTasteProfile[2] = r.nextInt(31) - 15;
            cookedTasteProfile[3] = r.nextInt(31) - 15;
            cookedTasteProfile[4] = r.nextInt(31) - 15;
            Food.setCookedProfile(is, cookedTasteProfile);
            Food.setFuelProfile(is, EnumFuelMaterial.getFuelProfile(this.fuelTasteProfile));
          }

        } else if (this.fireTemp > temp && index.hasOutput()) {

          temp += TFC_ItemHeat.getTempIncrease(is);
        } else {

          temp -= TFC_ItemHeat.getTempDecrease(is);
        }  TFC_ItemHeat.setTemp(is, temp);
      }
    }
  }




  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {


      List<EntityItem> list = this.field_145850_b.func_72872_a(EntityItem.class, AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), this.field_145848_d + 1.1D, (this.field_145849_e + 1)));

      if (list != null && !list.isEmpty() && this.fireItemStacks[0] == null)
      {

        for (Iterator<EntityItem> iterator = list.iterator(); iterator.hasNext(); ) {

          EntityItem entity = iterator.next();
          ItemStack is = entity.func_92059_d();
          Item item = is.func_77973_b();

          if (item == TFCItems.logs || item == Item.func_150898_a(TFCBlocks.peat)) {

            for (int c = 0; c < is.field_77994_a; c++) {

              if (this.fireItemStacks[0] == null) {





                func_70299_a(0, new ItemStack(item, 1, is.func_77960_j()));
                is.field_77994_a--;
                handleFuelStack();
              }
            }

            if (is.field_77994_a == 0) {
              entity.func_70106_y();
            }
          }
        }
      }

      careForInventorySlot(this.fireItemStacks[1]);
      careForInventorySlot(this.fireItemStacks[7]);
      careForInventorySlot(this.fireItemStacks[8]);

      smokeFoods();





      cookItem();


      handleFuelStack();

      if (this.fireTemp < 1.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 0) {

        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 0, 3);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
      else if (this.fireTemp >= 1.0F && this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 1) {

        this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, 3);
        this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }


      if (this.fuelTimeLeft > 0 && this.fireTemp >= 1.0F) {

        if (this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e) != 2)
        {
          this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, 2, 3);
          this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }

      } else if (this.fuelTimeLeft <= 0 && this.fireTemp >= 1.0F && this.fireItemStacks[5] != null &&
        !TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {

        if (this.fireItemStacks[5] != null) {

          EnumFuelMaterial m = TFC_Core.getFuelMaterial(this.fireItemStacks[5]);
          this.fuelTasteProfile = m.ordinal();
          this.fireItemStacks[5] = null;
          this.fuelTimeLeft = m.burnTimeMax;
          this.fuelBurnTemp = m.burnTempMax;
        }
      }


      float desiredTemp = handleTemp();

      handleTempFlux(desiredTemp);


      handleAirReduction();


      if (this.fireItemStacks[7] != null)
      {
        if ((this.fireItemStacks[7]).field_77994_a <= 0) {
          (this.fireItemStacks[7]).field_77994_a = 1;
        }
      }
      if (this.fireItemStacks[8] != null)
      {
        if ((this.fireItemStacks[8]).field_77994_a <= 0) {
          (this.fireItemStacks[8]).field_77994_a = 1;
        }
      }
      if (this.fuelTimeLeft <= 0) {
        TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
      }
    }
  }

  private void smokeFoods() {
    if (this.fuelTimeLeft > 0) {

      this.smokeTimer++;
      if (this.smokeTimer > 1000) {

        this.smokeTimer = 0;
        smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e);
        smokeBlock(this.field_145851_c + 1, this.field_145848_d + 1, this.field_145849_e);
        smokeBlock(this.field_145851_c - 1, this.field_145848_d + 1, this.field_145849_e);
        smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e + 1);
        smokeBlock(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e - 1);
        smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e);
        smokeBlock(this.field_145851_c + 1, this.field_145848_d + 2, this.field_145849_e);
        smokeBlock(this.field_145851_c - 1, this.field_145848_d + 2, this.field_145849_e);
        smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e + 1);
        smokeBlock(this.field_145851_c, this.field_145848_d + 2, this.field_145849_e - 1);
      }
    }
  }

  private void smokeBlock(int x, int y, int z) {
    if (this.field_145850_b.func_72899_e(x, y, z) && this.field_145850_b.func_147439_a(x, y, z) == TFCBlocks.smokeRack && this.field_145850_b
      .func_147438_o(x, y, z) instanceof TESmokeRack) {

      boolean broadcast = false;
      TESmokeRack te = (TESmokeRack)this.field_145850_b.func_147438_o(x, y, z);
      te.lastSmokedTime = (int)TFC_Time.getTotalHours();

      for (int i = 0; i < te.storage.length; i++) {

        ItemStack is = te.func_70301_a(i);
        if (is != null)
        {
          if (Food.getSmokeCounter(is) < 12) {


            Food.setSmokeCounter(is, Food.getSmokeCounter(is) + 1);
          }
          else {

            Food.setFuelProfile(is, EnumFuelMaterial.getFuelProfile(this.fuelTasteProfile));
            broadcast = true;
          }
        }
      }

      if (broadcast) {
        te.broadcastPacketInRange();
      }
    }
  }


  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
    this.fireItemStacks = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.fireItemStacks.length) {
        this.fireItemStacks[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.fireItemStacks.length; i++) {

      if (this.fireItemStacks[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.fireItemStacks[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    nbttagcompound.func_74782_a("Items", (NBTBase)nbttaglist);
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int slot, ItemStack is) {
    return false;
  }

  @SideOnly(Side.CLIENT)
  public void generateSmoke() {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */