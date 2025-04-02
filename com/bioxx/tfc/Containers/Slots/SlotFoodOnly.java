package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotFoodOnly
  extends SlotSize
{
  private List<EnumFoodGroup> excpetionsFG = new ArrayList<>();
  private List<EnumFoodGroup> inclusionsFG = new ArrayList<>();

  public SlotFoodOnly(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    Item item = itemstack.func_77973_b();
    if (item instanceof IFood && ((IFood)item).isUsable(itemstack)) {

      EnumFoodGroup efg = ((IFood)item).getFoodGroup();
      if (efg == null)
        return false;
      boolean except = this.excpetionsFG.contains(efg);
      boolean include = (this.inclusionsFG.contains(efg) || this.inclusionsFG.isEmpty());
      if (except || !include)
        return false;
      if (item instanceof ISize && (((ISize)item).getSize(itemstack)).stackSize >= this.size.stackSize)
        return super.func_75214_a(itemstack);
    }
    return false;
  }


  public SlotFoodOnly addFGException(EnumFoodGroup... ex) {
    for (int i = 0; i < ex.length; i++)
      this.excpetionsFG.add(ex[i]);
    return this;
  }


  public SlotFoodOnly addFGInclusion(EnumFoodGroup... ex) {
    for (int i = 0; i < ex.length; i++)
      this.inclusionsFG.add(ex[i]);
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotFoodOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */