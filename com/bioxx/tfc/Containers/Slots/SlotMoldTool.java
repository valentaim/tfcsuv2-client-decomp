package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;



public class SlotMoldTool
  extends Slot
{
  public SlotMoldTool(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }


  
  public boolean func_75214_a(ItemStack itemstack) {
    return (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal && TFC_ItemHeat.getIsLiquid(itemstack).booleanValue());
  }


  
  public int func_75219_a() {
    return 1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotMoldTool.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */