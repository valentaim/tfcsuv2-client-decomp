package com.bioxx.tfc.TileEntities;

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
import net.minecraft.util.AxisAlignedBB;



public class TEToolRack
  extends NetworkTileEntity
  implements IInventory
{
  public ItemStack[] storage = new ItemStack[4];
  public byte woodType = 0;



  public void addContents(int index, ItemStack is) {
    if (this.storage[index] == null) {
      this.storage[index] = is;
    }
  }

  public void clearContents() {
    this.storage[0] = null;
    this.storage[1] = null;
    this.storage[2] = null;
    this.storage[3] = null;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }


  public boolean contentsMatch(int index, ItemStack is) {
    return (this.storage[index] != null && this.storage[index]
      .func_77973_b() == is.func_77973_b() && this.storage[index]
      .func_77960_j() == is.func_77960_j() && (this.storage[index]).field_77994_a < this.storage[index]
      .func_77976_d());
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
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 0.8F + 0.4F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    for (int i = 0; i < func_70302_i_(); i++) {

      if (this.storage[i] != null) {

        EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
        entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
        entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
        entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
        this.field_145850_b.func_72838_d((Entity)entityitem);
        this.storage[i] = null;
      }
    }
  }


  public void ejectItem(int index, int dir) {
    float f3 = 0.05F;

    Random rand = new Random();
    float f = rand.nextFloat() * 0.8F + 0.1F;
    float f1 = rand.nextFloat() * 0.2F + 0.1F;
    float f2 = rand.nextFloat() * 0.8F + 0.1F;

    if (this.storage[index] != null) {

      EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
      entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
      entityitem.field_70181_x = 0.0D;
      entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
      this.field_145850_b.func_72838_d((Entity)entityitem);
      this.storage[index] = null;
    }
  }



  public int func_70297_j_() {
    return 1;
  }



  public int func_70302_i_() {
    return this.storage.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }


  public void injectContents(int index, int count) {
    if (this.storage[index] != null) {
      this.storage[index] = new ItemStack(this.storage[index].func_77973_b(), (this.storage[index]).field_77994_a + count, this.storage[index].func_77960_j());
    }
  }


  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }








  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public String func_145825_b() {
    return "Tool Rack";
  }



  public boolean func_145818_k_() {
    return false;
  }




  public void func_70295_k_() {}




  public void func_70305_f() {}



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.woodType = nbttagcompound.func_74771_c("woodType");
    NBTTagList nbttaglist = nbttagcompound.func_150295_c("Items", 10);
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
    nbt.func_74774_a("woodType", this.woodType);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    func_145839_a(nbt);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    func_145839_a(nbt);
  }


  public void createDataNBT(NBTTagCompound nbt) {
    func_145841_b(nbt);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    func_145841_b(nbt);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */