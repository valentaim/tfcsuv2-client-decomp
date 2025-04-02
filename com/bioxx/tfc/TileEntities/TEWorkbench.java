package com.bioxx.tfc.TileEntities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;



public class TEWorkbench
  extends TileEntity
  implements IInventory
{
  public ItemStack[] craftingMatrix = new ItemStack[9];




  public ItemStack func_70298_a(int i, int j) {
    if (this.craftingMatrix[i] != null) {

      if ((this.craftingMatrix[i]).field_77994_a <= j) {

        ItemStack itemstack = this.craftingMatrix[i];
        this.craftingMatrix[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = this.craftingMatrix[i].func_77979_a(j);
      if ((this.craftingMatrix[i]).field_77994_a == 0)
        this.craftingMatrix[i] = null;
      return itemstack1;
    }

    return null;
  }



  public int func_70297_j_() {
    return 64;
  }



  public int func_70302_i_() {
    return this.craftingMatrix.length;
  }



  public ItemStack func_70301_a(int i) {
    return this.craftingMatrix[i];
  }



  public ItemStack func_70304_b(int var1) {
    return null;
  }



  public boolean func_70300_a(EntityPlayer entityplayer) {
    return true;
  }



  public void func_70299_a(int i, ItemStack itemstack) {
    this.craftingMatrix[i] = itemstack;
    if (itemstack != null && itemstack.field_77994_a > func_70297_j_()) {
      itemstack.field_77994_a = func_70297_j_();
    }
  }



  public void func_145845_h() {}



  public boolean func_94041_b(int i, ItemStack itemstack) {
    return false;
  }



  public String func_145825_b() {
    return "Workbench";
  }



  public boolean func_145818_k_() {
    return false;
  }








  public void func_70295_k_() {}








  public void func_70305_f() {}







  public Packet func_145844_m() {
    NBTTagCompound nbt = new NBTTagCompound();
    func_145841_b(nbt);
    return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    func_145839_a(pkt.func_148857_g());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */