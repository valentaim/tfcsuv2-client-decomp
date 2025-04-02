package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotForgeFuel
  extends Slot
{
  public SlotForgeFuel(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    return (itemstack.func_77973_b() == TFCItems.coal);
  }



  public int func_75219_a() {
    return 1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotForgeFuel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */