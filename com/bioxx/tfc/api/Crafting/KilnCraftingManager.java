package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;


public class KilnCraftingManager
{
  private static final KilnCraftingManager INSTANCE = new KilnCraftingManager();

  public static final KilnCraftingManager getInstance() {
    return INSTANCE;
  }


  private List<KilnRecipe> recipes;

  private KilnCraftingManager() {
    this.recipes = new ArrayList<>();
  }


  public void addRecipe(KilnRecipe recipe) {
    this.recipes.add(recipe);
  }


  public KilnRecipe findMatchingRecipe(KilnRecipe recipe) {
    for (int k = 0; k < this.recipes.size(); k++) {

      KilnRecipe irecipe = this.recipes.get(k);
      if (irecipe.matches(recipe))
      {
        return irecipe;
      }
    }

    return null;
  }


  public ItemStack findCompleteRecipe(KilnRecipe recipe) {
    for (int k = 0; k < this.recipes.size(); k++) {

      KilnRecipe irecipe = this.recipes.get(k);
      if (irecipe.isComplete(recipe)) {

        ItemStack out = irecipe.getCraftingResult();
        if (irecipe.getInheritsTag())
          out.func_77982_d(recipe.input1.func_77978_p());
        return out;
      }
    }

    return recipe.input1;
  }


  public List<KilnRecipe> getRecipeList() {
    return this.recipes;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\KilnCraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */