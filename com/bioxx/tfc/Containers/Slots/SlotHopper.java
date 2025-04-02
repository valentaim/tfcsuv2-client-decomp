package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumSize;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotHopper
  extends Slot
{
  private EnumSize size = EnumSize.LARGE;

  private List<Item> exceptions;


  public SlotHopper(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
    this.exceptions = new ArrayList<>();
  }



















  public boolean func_75214_a(ItemStack itemstack) {
    return TFC_Core.isItemHopperValid(itemstack);
  }



  public SlotHopper setSize(EnumSize s) {
    return this;
  }


  public SlotHopper addItemException(List<Item> ex) {
    this.exceptions.addAll(ex);
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */