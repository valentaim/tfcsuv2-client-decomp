package com.bioxx.tfc.Containers.Slots;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFoodBowl
  extends Slot
{
  public SlotFoodBowl(IInventory par1iInventory, int par2, int par3, int par4) {
    super(par1iInventory, par2, par3, par4);
  }



  public boolean func_75214_a(ItemStack par1ItemStack) {
    return (par1ItemStack.func_77973_b() == Items.field_151054_z);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotFoodBowl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */