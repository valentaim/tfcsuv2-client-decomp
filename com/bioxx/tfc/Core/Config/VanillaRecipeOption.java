package com.bioxx.tfc.Core.Config;

import com.bioxx.tfc.api.TFCCrafting;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;







public class VanillaRecipeOption
  extends SyncingOption
{
  public final ImmutableList<IRecipe> recipes;

  public VanillaRecipeOption(String name, ItemStack... toBeRemoved) throws NoSuchFieldException, IllegalAccessException {
    super(name, TFCCrafting.class, TFC_ConfigFiles.getCraftingConfig(), "Enable Vanilla Recipes");
    if (toBeRemoved.length == 0) throw new IllegalArgumentException("No items for removal " + name);
    ImmutableList.Builder<IRecipe> builder = new ImmutableList.Builder();

    for (Object recipe : CraftingManager.func_77594_a().func_77592_b()) {

      if (recipe == null)
        continue;  for (ItemStack out : toBeRemoved) {

        if (ItemStack.func_77989_b(out, ((IRecipe)recipe).func_77571_b())) {

          builder.add(recipe);
          break;
        }
      }
    }
    this.recipes = builder.build();

    CraftingManager.func_77594_a().func_77592_b().removeAll((Collection<?>)this.recipes);
  }



  public ImmutableList<IRecipe> getRecipes() {
    return this.recipes;
  }



  public String toString() {
    return this.name + "[default:" + this.defaultValue + " current:" + isAplied() + " config:" + inConfig() + " #ofRecipes:" + this.recipes.size() + "]";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\VanillaRecipeOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */