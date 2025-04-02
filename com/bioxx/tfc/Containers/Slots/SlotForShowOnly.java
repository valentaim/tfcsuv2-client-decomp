package com.bioxx.tfc.Containers.Slots;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotForShowOnly
  extends Slot
{
  public SlotForShowOnly(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_82869_a(EntityPlayer par1EntityPlayer) {
    return false;
  }



  public boolean func_75214_a(ItemStack par1ItemStack) {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_111238_b() {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotForShowOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */