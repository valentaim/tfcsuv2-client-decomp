package com.bioxx.tfc.Food;

import net.minecraft.item.ItemStack;




public class ItemSoup
  extends ItemMeal
{
  public ItemSoup() {
    this.field_77787_bX = true;
    this.metaNames = new String[] { "Soup0", "Soup1", "Soup2", "Soup3" };
    this.metaIcons = new net.minecraft.util.IIcon[4];
    setFolder("food/");
  }

  
  public float getFoodMaxWeight(ItemStack is) {
    return 24.0F;
  }

  
  public boolean renderDecay() {
    return true;
  }

  
  public boolean renderWeight() {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemSoup.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */