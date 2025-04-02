package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.TileEntities.TEBarrel;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;



public class BarrelManager
{
  private static final BarrelManager INSTANCE = new BarrelManager();

  public static final BarrelManager getInstance() {
    return INSTANCE;
  }


  private List<BarrelRecipe> recipes;
  private List<BarrelPreservativeRecipe> preservativeRecipes;

  private BarrelManager() {
    this.recipes = new ArrayList<>();
    this.preservativeRecipes = new ArrayList<>();
  }


  public void addRecipe(BarrelRecipe recipe) {
    this.recipes.add(recipe);
  }

  public void addPreservative(BarrelPreservativeRecipe recipe) {
    this.preservativeRecipes.add(recipe);
  }


  public BarrelRecipe findMatchingRecipe(ItemStack item, FluidStack fluid, boolean sealed, int techLevel) {
    for (BarrelRecipe recipe : this.recipes) {

      BarrelRecipe br = recipe;
      if (fluid != null && br.matches(item, fluid).booleanValue() &&
        br.sealedRecipe == sealed && br.minTechLevel <= techLevel)
        return br;
    }
    return null;
  }


  public BarrelPreservativeRecipe findMatchingPreservativeRepice(TEBarrel barrel, ItemStack item, FluidStack fluid, boolean sealed) {
    for (BarrelPreservativeRecipe recipe : this.preservativeRecipes) {
      if (recipe.checkForPreservation(barrel, fluid, item, sealed))
        return recipe;
    }
    return null;
  }


  public List<BarrelRecipe> getRecipes() {
    return this.recipes;
  }


  public List<BarrelPreservativeRecipe> getPreservatives() {
    return this.preservativeRecipes;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */