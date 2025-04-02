package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import net.minecraft.item.ItemStack;


public class ItemFoodMeat
  extends ItemFoodTFC
{
  public ItemFoodMeat(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um, boolean edible, boolean usable) {
    super(fg, sw, so, sa, bi, um, edible, usable);
  }



  protected String getCookedLevelString(ItemStack is) {
    String s = "";
    if (Food.isCooked(is)) {

      s = s + " (";
      int cookedLevel = ((int)Food.getCooked(is) - 600) / 120;
      switch (cookedLevel) {
        case 0:
          s = s + TFC_Core.translate("food.cooked.rare"); break;
        case 1: s = s + TFC_Core.translate("food.cooked.medrare"); break;
        case 2: s = s + TFC_Core.translate("food.cooked.med"); break;
        case 3: s = s + TFC_Core.translate("food.cooked.medwell"); break;
        default: s = s + TFC_Core.translate("food.cooked.well"); break;
      }
      s = s + ")";
    }
    return s;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemFoodMeat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */