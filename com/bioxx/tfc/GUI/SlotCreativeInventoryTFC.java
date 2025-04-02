package com.bioxx.tfc.GUI;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;



@SideOnly(Side.CLIENT)
class SlotCreativeInventoryTFC
  extends Slot
{
  private final Slot theSlot;

  public SlotCreativeInventoryTFC(GuiContainerCreativeTFC par1GuiContainerCreative, Slot par2Slot, int par3) {
    super(par2Slot.field_75224_c, par3, 0, 0);

    this.theSlot = par2Slot;
  }



  public void func_82870_a(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack) {
    this.theSlot.func_82870_a(par1EntityPlayer, par2ItemStack);
  }






  public boolean func_75214_a(ItemStack par1ItemStack) {
    return this.theSlot.func_75214_a(par1ItemStack);
  }






  public ItemStack func_75211_c() {
    return this.theSlot.func_75211_c();
  }






  public boolean func_75216_d() {
    return this.theSlot.func_75216_d();
  }






  public void func_75215_d(ItemStack par1ItemStack) {
    this.theSlot.func_75215_d(par1ItemStack);
  }






  public void func_75218_e() {
    this.theSlot.func_75218_e();
  }







  public int func_75219_a() {
    return this.theSlot.func_75219_a();
  }






  public IIcon func_75212_b() {
    return this.theSlot.func_75212_b();
  }







  public ItemStack func_75209_a(int par1) {
    return this.theSlot.func_75209_a(par1);
  }






  public boolean func_75217_a(IInventory par1IInventory, int par2) {
    return this.theSlot.func_75217_a(par1IInventory, par2);
  }


  public static Slot getSlot(SlotCreativeInventoryTFC par0SlotCreativeInventory) {
    return par0SlotCreativeInventory.theSlot;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\GUI\SlotCreativeInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */