package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumFuelMaterial;
import com.bioxx.tfc.api.Events.ItemCookEvent;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.ICookableFood;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.MinecraftForge;

public class TEGrill
  extends NetworkTileEntity implements IInventory {
  public ItemStack[] storage = new ItemStack[6];

  public byte data;


  public void func_145845_h() {
    TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);

    for (int i = 0; i < 6; i++) {

      careForInventorySlot(this.storage[i]);
      cookItem(i);
    }
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public boolean isOven() {
    int wallCount = 0;
    if (TFC_Core.isWestFaceSolid(this.field_145850_b, this.field_145851_c - 1, this.field_145848_d, this.field_145849_e))
      wallCount++;
    if (TFC_Core.isEastFaceSolid(this.field_145850_b, this.field_145851_c + 1, this.field_145848_d, this.field_145849_e))
      wallCount++;
    if (TFC_Core.isNorthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e + 1))
      wallCount++;
    if (TFC_Core.isSouthFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e - 1)) {
      wallCount++;
    }
    if (TFC_Core.isBottomFaceSolid(this.field_145850_b, this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)) {
      wallCount++;
    }
    if (this.field_145850_b.func_147439_a(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.metalTrapDoor) {

      TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c - 1, this.field_145848_d, this.field_145849_e);
      if (te.getSide() == 4) {
        wallCount++;
      }
    } else if (this.field_145850_b.func_147439_a(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e) == TFCBlocks.metalTrapDoor) {

      TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c + 1, this.field_145848_d, this.field_145849_e);
      if (te.getSide() == 5) {
        wallCount++;
      }
    } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1) == TFCBlocks.metalTrapDoor) {

      TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e - 1);
      if (te.getSide() == 2) {
        wallCount++;
      }
    } else if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1) == TFCBlocks.metalTrapDoor) {

      TEMetalTrapDoor te = (TEMetalTrapDoor)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e + 1);
      if (te.getSide() == 3) {
        wallCount++;
      }
    }
    return (wallCount >= 5);
  }



  public boolean isDoor(int x, int y, int z) {
    return false;
  }


  public void careForInventorySlot(ItemStack is) {
    TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
    if (is != null && te instanceof TEFireEntity) {

      HeatRegistry manager = HeatRegistry.getInstance();
      HeatIndex index = manager.findMatchingIndex(is);

      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        TEFireEntity fire = (TEFireEntity)te;
        if (fire.fuelTimeLeft > 0 && is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

          float inc = Food.getCooked(is) + Math.min(fire.fireTemp / 700.0F, 2.0F);
          Food.setCooked(is, inc);
          temp = inc;
        }
        else if (fire.fireTemp > temp) {

          temp += TFC_ItemHeat.getTempIncrease(is);
        }

        if (fire.fireTemp > temp) {
          temp += TFC_ItemHeat.getTempIncrease(is);
        } else {
          temp -= TFC_ItemHeat.getTempDecrease(is);
        }  TFC_ItemHeat.setTemp(is, temp);
      }
    }
  }


  public void cookItem(int i) {
    HeatRegistry manager = HeatRegistry.getInstance();
    Random r = new Random();
    if (this.storage[i] != null) {

      HeatIndex index = manager.findMatchingIndex(this.storage[i]);
      if (index != null && Food.isCooked(this.storage[i])) {


        int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
        r = new Random((((ICookableFood)this.storage[i].func_77973_b()).getFoodID() + ((int)Food.getCooked(this.storage[i]) - 600) / 120));
        cookedTasteProfile[0] = r.nextInt(31) - 15;
        cookedTasteProfile[1] = r.nextInt(31) - 15;
        cookedTasteProfile[2] = r.nextInt(31) - 15;
        cookedTasteProfile[3] = r.nextInt(31) - 15;
        cookedTasteProfile[4] = r.nextInt(31) - 15;
        Food.setCookedProfile(this.storage[i], cookedTasteProfile);
        TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
        if (te instanceof TEFireEntity) {

          TEFireEntity fire = (TEFireEntity)te;
          Food.setFuelProfile(this.storage[i], EnumFuelMaterial.getFuelProfile(fire.fuelTasteProfile));
        }
      }

      if (index != null && TFC_ItemHeat.getTemp(this.storage[i]) > index.meltTemp) {

        float temp = TFC_ItemHeat.getTemp(this.storage[i]);
        ItemStack output = index.getOutput(this.storage[i], r);

        ItemCookEvent eventMelt = new ItemCookEvent(this.storage[i], output, this);
        MinecraftForge.EVENT_BUS.post((Event)eventMelt);
        output = eventMelt.result;


        this.storage[i] = output;
        if (this.storage[i] != null && manager.findMatchingIndex(this.storage[i]) != null)
        {

          TFC_ItemHeat.setTemp(this.storage[i], temp);
        }
      }
    }
  }


  public int getSide() {
    return this.data & 0x7;
  }


  public boolean isEmpty() {
    for (ItemStack is : this.storage) {

      if (is != null) {
        return false;
      }
    }
    return true;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.data = nbt.func_74771_c("data");
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }


  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74774_a("data", this.data);
    NBTTagList nbttaglist = new NBTTagList();
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





  public void ejectContents() {
    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.storage[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.storage[i] = null;
      }
    }
  }




  public void ejectItem(int index) {
    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 2.0F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    if (this.storage[index] != null) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
      this.field_145850_b.func_72838_d((Entity)entityitem);
    }
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public String func_145825_b() {
    return "grill";
  }



  public int func_70297_j_() {
    return 64;
  }



  public boolean func_70300_a(EntityPlayer var1) {
    return false;
  }




  public void func_70295_k_() {}



  public void func_70305_f() {
    if (this.field_145850_b.field_72995_K)
    {
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_145818_k_() {
    return false;
  }



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.data = nbt.func_74771_c("data");
    this.storage = new ItemStack[func_70302_i_()];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    }
  }

  public void createInitNBT(NBTTagCompound nbt) {
    NBTTagList nbttaglist = new NBTTagList();
    nbt.func_74774_a("data", this.data);
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */