package com.bioxx.tfc.TileEntities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

public class TEWorldItem extends NetworkTileEntity implements IInventory {
  public ItemStack[] storage = new ItemStack[1];



  
  public EntityItem renderItem;



  
  public boolean canUpdate() {
    return false;
  }


  
  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
    this.storage = new ItemStack[1];
    for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
      
      NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
      byte byte0 = nbttagcompound1.func_74771_c("Slot");
      if (byte0 >= 0 && byte0 < this.storage.length) {
        this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
      }
    } 
  }

  
  @SideOnly(Side.CLIENT)
  public double func_145833_n() {
    return 1024.0D;
  }


  
  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), this.field_145848_d + 0.1D, (this.field_145849_e + 1));
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
  }


  
  public int func_70302_i_() {
    return 1;
  }


  
  public ItemStack func_70301_a(int i) {
    return this.storage[i];
  }


  
  public ItemStack func_70298_a(int i, int j) {
    (this.storage[i]).field_77994_a -= j;
    return this.storage[i];
  }


  
  public ItemStack func_70304_b(int i) {
    return null;
  }


  
  public void func_70299_a(int i, ItemStack itemstack) {
    this.storage[i] = itemstack;
  }


  
  public String func_145825_b() {
    return "worldItem";
  }


  
  public boolean func_145818_k_() {
    return false;
  }


  
  public int func_70297_j_() {
    return 1;
  }


  
  public boolean func_70300_a(EntityPlayer entityplayer) {
    return false;
  }



  
  public void func_70295_k_() {}



  
  public void func_70305_f() {}


  
  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }

  
  public void handleInitPacket(NBTTagCompound nbt) {
    if (nbt.func_74764_b("id")) {
      this.storage[0] = ItemStack.func_77949_a(nbt);
    }
  }



  
  public void handleDataPacket(NBTTagCompound nbt) {}



  
  public void createDataNBT(NBTTagCompound nbt) {}


  
  public void createInitNBT(NBTTagCompound nbt) {
    if (this.storage[0] != null)
      this.storage[0].func_77955_b(nbt); 
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWorldItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */