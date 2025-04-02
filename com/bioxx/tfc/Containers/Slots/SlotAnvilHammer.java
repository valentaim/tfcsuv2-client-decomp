package com.bioxx.tfc.Containers.Slots;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;



public class SlotAnvilHammer
  extends Slot
{
  public SlotAnvilHammer(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    return itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotAnvilHammer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */