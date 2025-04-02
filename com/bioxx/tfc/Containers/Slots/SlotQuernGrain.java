package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Crafting.QuernManager;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotQuernGrain
  extends Slot
{
  public SlotQuernGrain(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    return QuernManager.getInstance().isValidItem(itemstack).booleanValue();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotQuernGrain.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */