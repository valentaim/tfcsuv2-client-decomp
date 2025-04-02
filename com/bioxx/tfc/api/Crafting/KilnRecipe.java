package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;


public class KilnRecipe
{
  public ItemStack result;
  public ItemStack input1;
  public int kilnLevel;
  public boolean inheritsTag = true;

  public KilnRecipe(ItemStack in, int kl, ItemStack res) {
    this.input1 = in;
    this.result = res;
    this.kilnLevel = kl;
  }


  public KilnRecipe(ItemStack in, int kl) {
    this.input1 = in;
    this.kilnLevel = kl;
  }





  public boolean matches(KilnRecipe recipe) {
    return (areItemStacksEqual(this.input1, recipe.input1) && recipe.kilnLevel == this.kilnLevel);
  }


  public boolean isComplete(KilnRecipe recipe) {
    return (areItemStacksEqual(this.input1, recipe.input1) && recipe.kilnLevel == this.kilnLevel);
  }


  public boolean getInheritsTag() {
    return this.inheritsTag;
  }


  private boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
    if (is1 != null && is2 != null) {

      if (is1.func_77973_b() != is2.func_77973_b()) {
        return false;
      }
      if (is1.func_77960_j() != -1 && is1.func_77960_j() != is2.func_77960_j()) {
        return false;
      }
    } else if ((is1 == null && is2 != null) || (is1 != null && is2 == null)) {
      return false;
    }
    return true;
  }





  public ItemStack getCraftingResult() {
    return this.result;
  }


  public int getKilnLevel() {
    return this.kilnLevel;
  }


  public ItemStack getInput1() {
    return this.input1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\KilnRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */