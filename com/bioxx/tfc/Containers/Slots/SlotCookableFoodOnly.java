package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ICookableFood;
import com.bioxx.tfc.api.Interfaces.ISize;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;


public class SlotCookableFoodOnly
  extends SlotSize
{
  private List<EnumFoodGroup> excpetionsFG = new ArrayList<>();
  private List<EnumFoodGroup> inclusionsFG = new ArrayList<>();

  public SlotCookableFoodOnly(IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
    setSize(EnumSize.SMALL);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    if (itemstack.func_77973_b() instanceof ICookableFood) {

      EnumFoodGroup efg = ((ICookableFood)itemstack.func_77973_b()).getFoodGroup();
      if (efg == null)
        return false;
      boolean except = this.excpetionsFG.contains(efg);
      boolean include = (this.inclusionsFG.contains(efg) || this.inclusionsFG.isEmpty());
      if (except || !include)
        return false;
      if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize)
        return super.func_75214_a(itemstack);
    }
    return false;
  }


  public SlotCookableFoodOnly addFGException(EnumFoodGroup... ex) {
    for (int i = 0; i < ex.length; i++)
      this.excpetionsFG.add(ex[i]);
    return this;
  }


  public SlotCookableFoodOnly addFGInclusion(EnumFoodGroup... ex) {
    for (int i = 0; i < ex.length; i++)
      this.inclusionsFG.add(ex[i]);
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotCookableFoodOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */