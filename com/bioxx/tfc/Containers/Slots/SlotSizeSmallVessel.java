package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;








public class SlotSizeSmallVessel
  extends Slot
{
  private EnumSize size = EnumSize.SMALL;

  private List<Item> exceptions;

  public SlotSizeSmallVessel(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
    this.exceptions = new ArrayList<>();
  }



  public boolean func_75214_a(ItemStack itemstack) {
    if (itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IBag || itemstack
      .func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal || itemstack
      .func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotteryBase)
    {
      return false;
    }

    if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && (itemstack
      .func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood || itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IItemFoodBlock) && (
      !itemstack.func_77942_o() || !itemstack.func_77978_p().func_74764_b("foodWeight") || !itemstack.func_77978_p().func_74764_b("foodDecay"))) {
      return false;
    }
    boolean except = this.exceptions.contains(itemstack.func_77973_b());
    if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && !except)
      return true;
    if (!(itemstack.func_77973_b() instanceof ISize) && !except)
      return true;
    return false;
  }


  public SlotSizeSmallVessel addItemException(List<Item> ex) {
    this.exceptions = ex;
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotSizeSmallVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */