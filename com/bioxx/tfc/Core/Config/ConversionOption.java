package com.bioxx.tfc.Core.Config;

import com.bioxx.tfc.api.TFCCrafting;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.crafting.IRecipe;








public class ConversionOption
  extends SyncingOption
{
  public final ImmutableList<IRecipe> recipes;

  public ConversionOption(String name, IRecipe... shapelessRecipes) throws NoSuchFieldException, IllegalAccessException {
    super(name, TFCCrafting.class, TFC_ConfigFiles.getCraftingConfig(), "Conversion");
    if (shapelessRecipes.length == 0) throw new IllegalArgumentException("No recipes for conversion " + name);
    this.recipes = ImmutableList.copyOf((Object[])shapelessRecipes);
  }



  public ImmutableList<IRecipe> getRecipes() {
    return this.recipes;
  }



  public String toString() {
    return this.name + "[default:" + this.defaultValue + " current:" + isAplied() + " config:" + inConfig() + " #ofRecipes:" + this.recipes.size() + "]";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\ConversionOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */