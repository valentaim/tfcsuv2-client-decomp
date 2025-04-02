package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;


public class CraftingManagerTFC
{
  private static final CraftingManagerTFC INSTANCE = new CraftingManagerTFC();

  public static final CraftingManagerTFC getInstance() {
    return INSTANCE;
  }


  private List<IRecipe> recipes;

  private CraftingManagerTFC() {
    this.recipes = new ArrayList<>();

    Collections.sort(this.recipes, new RecipeSorterTFC(this));
  }



  public ShapedRecipesTFC addRecipe(ItemStack itemstack, Object[] aobj) {
    String s = "";
    int i = 0;
    int j = 0;
    int k = 0;
    if (aobj[i] instanceof String[]) {

      String[] as = (String[])aobj[i++];
      for (int l = 0; l < as.length; l++)
      {
        String s2 = as[l];
        k++;
        j = s2.length();
        s = s + s2;
      }

    } else {

      while (aobj[i] instanceof String) {

        String s1 = (String)aobj[i++];
        k++;
        j = s1.length();
        s = s + s1;
      }
    }
    HashMap<Character, ItemStack> hashmap = new HashMap<>();
    for (; i < aobj.length; i += 2) {

      Character character = (Character)aobj[i];
      ItemStack itemstack1 = null;
      if (aobj[i + 1] instanceof Item) {

        itemstack1 = new ItemStack((Item)aobj[i + 1]);
      }
      else if (aobj[i + 1] instanceof Block) {

        itemstack1 = new ItemStack((Block)aobj[i + 1], 1, -1);
      }
      else if (aobj[i + 1] instanceof ItemStack) {

        itemstack1 = (ItemStack)aobj[i + 1];
      }
      hashmap.put(character, itemstack1);
    }

    ItemStack[] aitemstack = new ItemStack[j * k];
    for (int i1 = 0; i1 < j * k; i1++) {

      char c = s.charAt(i1);
      if (hashmap.containsKey(Character.valueOf(c))) {

        aitemstack[i1] = ((ItemStack)hashmap.get(Character.valueOf(c))).func_77946_l();
      }
      else {

        aitemstack[i1] = null;
      }
    }

    ShapedRecipesTFC shapedRecipesTFC = new ShapedRecipesTFC(j, k, aitemstack, itemstack);
    this.recipes.add(shapedRecipesTFC);
    return shapedRecipesTFC;
  }


  public ShapelessRecipesTFC addShapelessRecipe(ItemStack itemstack, Object[] aobj) {
    ArrayList<ItemStack> arraylist = new ArrayList<>();
    Object[] aobj1 = aobj;
    int i = aobj1.length;
    for (int j = 0; j < i; j++) {

      Object obj = aobj1[j];
      if (obj instanceof ItemStack) {

        arraylist.add(((ItemStack)obj).func_77946_l());

      }
      else if (obj instanceof Item) {

        arraylist.add(new ItemStack((Item)obj));

      }
      else if (obj instanceof Block) {

        arraylist.add(new ItemStack((Block)obj));
      }
      else {

        throw new RuntimeException("Invalid shapeless recipy!");
      }
    }
    ShapelessRecipesTFC recipesTFC = new ShapelessRecipesTFC(itemstack, arraylist);
    this.recipes.add(recipesTFC);
    return recipesTFC;
  }




































  public ItemStack findMatchingRecipe(InventoryCrafting inventorycrafting, World world) {
    for (int k = 0; k < this.recipes.size(); k++) {

      IRecipe irecipe = this.recipes.get(k);
      if (irecipe.func_77569_a(inventorycrafting, world))
      {
        return irecipe.func_77572_b(inventorycrafting);
      }
    }

    return null;
  }


  public List<IRecipe> getRecipeList() {
    return this.recipes;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\CraftingManagerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */