package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class AnvilManager
{
  private static final AnvilManager INSTANCE = new AnvilManager();

  public static final AnvilManager getInstance() {
    return INSTANCE;
  }


  public static World world;
  private List<AnvilRecipe> recipes;
  private List<AnvilRecipe> recipesWeld;
  private Map<String, PlanRecipe> plans;

  private AnvilManager() {
    this.recipes = new ArrayList<>();
    this.recipesWeld = new ArrayList<>();
    this.plans = new HashMap<>();
  }


  public void addRecipe(AnvilRecipe recipe) {
    this.recipes.add(recipe);
  }


  public void addWeldRecipe(AnvilRecipe recipe) {
    recipe.flux = true;
    this.recipesWeld.add(recipe);
  }


  public void clearRecipes() {
    this.recipes.clear();
    this.recipesWeld.clear();
    this.plans.clear();
  }






  public void addPlan(String s, PlanRecipe r) {
    s = s.toLowerCase();
    if (!this.plans.containsKey(s)) {
      this.plans.put(s, r);
    }
  }

  public PlanRecipe getPlan(String s) {
    return this.plans.get(s);
  }


  public AnvilRecipe findMatchingRecipe(AnvilRecipe recipe) {
    for (int k = 0; k < this.recipes.size(); k++) {

      AnvilRecipe irecipe = this.recipes.get(k);
      if (irecipe != null && irecipe.matches(recipe)) {
        return irecipe;
      }
    }
    return null;
  }


  public AnvilRecipe findMatchingWeldRecipe(AnvilRecipe recipe) {
    for (int k = 0; k < this.recipesWeld.size(); k++) {

      AnvilRecipe irecipe = this.recipesWeld.get(k);
      if (irecipe != null && irecipe.matches(recipe)) {
        return irecipe;
      }
    }
    return null;
  }


  public Object[] findCompleteRecipe(AnvilRecipe recipe, int[] rules) {
    for (int k = 0; k < this.recipes.size(); k++) {

      AnvilRecipe irecipe = this.recipes.get(k);
      if (irecipe != null && irecipe.isComplete(INSTANCE, recipe, rules)) {
        return new Object[] { irecipe, irecipe.getCraftingResult(recipe.input1) };
      }
    }
    return null;
  }


  public ItemStack findCompleteWeldRecipe(AnvilRecipe recipe) {
    for (int k = 0; k < this.recipesWeld.size(); k++) {

      AnvilRecipe irecipe = this.recipesWeld.get(k);
      if (irecipe != null && irecipe.matches(recipe)) {
        return irecipe.getCraftingResult(recipe.input1);
      }
    }
    return null;
  }


  public List<AnvilRecipe> getRecipeList() {
    return this.recipes;
  }


  public List<AnvilRecipe> getWeldRecipeList() {
    return this.recipesWeld;
  }


  public Map<String, PlanRecipe> getPlans() {
    return this.plans;
  }


  public static NBTTagCompound getCraftTag(ItemStack is) {
    if (is.func_77942_o() && is.func_77978_p().func_74764_b("craftingTag"))
    {
      return (NBTTagCompound)is.func_77978_p().func_74781_a("craftingTag");
    }

    return new NBTTagCompound();
  }


  public static void setCraftTag(ItemStack is, NBTTagCompound nbt) {
    if (!is.func_77942_o())
      is.func_77982_d(new NBTTagCompound());
    is.func_77978_p().func_74782_a("craftingTag", (NBTBase)nbt);
  }


  public static float getDurabilityBuff(ItemStack is) {
    NBTTagCompound nbt = getCraftTag(is);
    return nbt.func_74760_g("adv_durabuff");
  }


  public static void setDurabilityBuff(ItemStack is, float value) {
    NBTTagCompound nbt = getCraftTag(is);
    nbt.func_74776_a("adv_durabuff", value);
    setCraftTag(is, nbt);
  }


  public static float getDamageBuff(ItemStack is) {
    NBTTagCompound nbt = getCraftTag(is);
    return nbt.func_74760_g("adv_damagebuff");
  }


  public static void setDamageBuff(ItemStack is, float value) {
    NBTTagCompound nbt = getCraftTag(is);
    nbt.func_74776_a("adv_damagebuff", value);
    setCraftTag(is, nbt);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\AnvilManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */