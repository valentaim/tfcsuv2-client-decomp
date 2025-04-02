package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;


public class ShapedRecipesTFC
  implements IRecipe
{
  private int recipeWidth;
  private int recipeHeight;
  private ItemStack[] recipeItems;
  private ItemStack recipeOutput;

  public ShapedRecipesTFC(int i, int j, ItemStack[] aitemstack, ItemStack itemstack) {
    this.recipeWidth = i;
    this.recipeHeight = j;
    this.recipeItems = (ItemStack[])aitemstack.clone();
    this.recipeOutput = itemstack;
  }


  private boolean compare(InventoryCrafting inventorycrafting, int i, int j, boolean flag) {
    for (int k = 0; k < 5; k++) {

      for (int l = 0; l < 5; l++) {

        int i1 = k - i;
        int j1 = l - j;
        ItemStack recipeIS = null;
        if (i1 >= 0 && j1 >= 0 && i1 < this.recipeWidth && j1 < this.recipeHeight)
        {
          if (flag) {

            recipeIS = this.recipeItems[this.recipeWidth - i1 - 1 + j1 * this.recipeWidth];
          }
          else {

            recipeIS = this.recipeItems[i1 + j1 * this.recipeWidth];
          }
        }
        ItemStack inputIS = inventorycrafting.func_70463_b(k, l);
        if (inputIS != null || recipeIS != null) {



          if (inputIS == null || recipeIS == null)
          {
            return false;
          }


          if (recipeIS.func_77973_b() != inputIS.func_77973_b())
          {
            return false;
          }
          if (recipeIS.func_77960_j() != 32767 && recipeIS.func_77960_j() != inputIS.func_77960_j())
          {
            return false;
          }

          if (!tempMatch(recipeIS, inputIS))
          {
            return false;
          }
        }
      }
    }
    return true;
  }



  public ItemStack func_77572_b(InventoryCrafting inventorycrafting) {
    return new ItemStack(this.recipeOutput.func_77973_b(), this.recipeOutput.field_77994_a, this.recipeOutput.func_77960_j());
  }



  public ItemStack func_77571_b() {
    return this.recipeOutput;
  }



  public int func_77570_a() {
    return this.recipeWidth * this.recipeHeight;
  }


  public int getRecipeWidth() {
    return this.recipeWidth;
  }


  public int getRecipeHeight() {
    return this.recipeHeight;
  }


  public ItemStack[] getRecipeItems() {
    return (ItemStack[])this.recipeItems.clone();
  }



  public boolean func_77569_a(InventoryCrafting inventorycrafting, World world) {
    for (int i = 0; i <= 5 - this.recipeWidth; i++) {

      for (int j = 0; j <= 5 - this.recipeHeight; j++) {

        if (compare(inventorycrafting, i, j, true))
        {
          return true;
        }
        if (compare(inventorycrafting, i, j, false))
        {
          return true;
        }
      }
    }

    return false;
  }


  private boolean tempMatch(ItemStack recipeIS, ItemStack inputIS) {
    NBTTagCompound rnbt = recipeIS.func_77978_p();
    NBTTagCompound inbt = inputIS.func_77978_p();

    if (rnbt != null && rnbt.func_74764_b("noTemp"))
    {

      return (inbt == null || !TFC_ItemHeat.hasTemp(inputIS));
    }

    if (rnbt != null && TFC_ItemHeat.hasTemp(recipeIS)) {

      if (inbt != null && TFC_ItemHeat.hasTemp(inputIS))
      {
        return HeatRegistry.getInstance().getIsLiquid(inputIS).booleanValue();
      }


      return false;
    }


    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\ShapedRecipesTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */