package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;


public class QuernManager
{
  private static final QuernManager INSTANCE = new QuernManager();

  public static final QuernManager getInstance() {
    return INSTANCE;
  }


  private List<QuernRecipe> recipes;
  private List<ItemStack> validItems;

  private QuernManager() {
    this.recipes = new ArrayList<>();
    this.validItems = new ArrayList<>();
  }


  public void addRecipe(QuernRecipe recipe) {
    this.recipes.add(recipe);
    this.validItems.add(recipe.getInItem());
  }


  public Boolean isValidItem(ItemStack is) {
    for (ItemStack vi : this.validItems) {

      ItemStack vis = vi;
      if (vis.func_77973_b() == is.func_77973_b() && vis.func_77960_j() == is.func_77960_j())
        return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }


  public QuernRecipe findMatchingRecipe(ItemStack is) {
    for (QuernRecipe recipe : this.recipes) {

      QuernRecipe qr = recipe;
      if (qr.isInItem(is).booleanValue())
        return qr;
    }
    return null;
  }


  public List<QuernRecipe> getRecipes() {
    return this.recipes;
  }


  public List<ItemStack> getValidItems() {
    return this.validItems;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\QuernManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */