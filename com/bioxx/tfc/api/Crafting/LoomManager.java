package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;


public class LoomManager
{
  private static final LoomManager INSTANCE = new LoomManager();
  
  public static final LoomManager getInstance() {
    return INSTANCE;
  }

  
  private List<LoomRecipe> recipes;
  private Map<LoomRecipe, ResourceLocation> textures;
  
  private LoomManager() {
    this.recipes = new ArrayList<>();
    this.textures = new HashMap<>();
  }

  
  public void addRecipe(LoomRecipe recipe, ResourceLocation rl) {
    this.recipes.add(recipe);
    if (recipe != null) {
      this.textures.remove(recipe);
      this.textures.put(recipe, rl);
    } 
  }

  
  public LoomRecipe findMatchingRecipe(ItemStack item) {
    for (LoomRecipe recipe : this.recipes) {
      
      LoomRecipe lr = recipe;
      if (item != null && lr.matches(item).booleanValue())
        return lr; 
    } 
    return null;
  }

  
  public LoomRecipe findMatchingResult(ItemStack item) {
    for (LoomRecipe recipe : this.recipes) {
      
      LoomRecipe lr = recipe;
      if (item != null && lr.resultMatches(item).booleanValue())
        return lr; 
    } 
    return null;
  }
  
  public boolean hasPotentialRecipes(ItemStack item) {
    for (LoomRecipe recipe : this.recipes) {
      
      LoomRecipe lr = recipe;
      if (item != null && lr.partiallyMatches(item).booleanValue())
        return true; 
    } 
    return false;
  }
  
  public LoomRecipe findPotentialRecipes(ItemStack item) {
    for (LoomRecipe recipe : this.recipes) {
      
      LoomRecipe lr = recipe;
      if (item != null && lr.partiallyMatches(item).booleanValue())
        return lr; 
    } 
    return null;
  }
  
  public ResourceLocation findMatchingTexture(LoomRecipe recipe) {
    ResourceLocation rl = null;
    if (recipe != null) {
      rl = this.textures.remove(recipe);
      if (rl != null) {
        this.textures.put(recipe, rl);
        return rl;
      } 
    } 
    return null;
  }

  
  public List<LoomRecipe> getRecipes() {
    return this.recipes;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\LoomManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */