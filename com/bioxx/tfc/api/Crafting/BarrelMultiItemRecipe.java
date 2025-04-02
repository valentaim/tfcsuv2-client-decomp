package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.Food;
import java.util.Stack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;



public class BarrelMultiItemRecipe
  extends BarrelRecipe
{
  public boolean keepstacksize = true;

  public BarrelMultiItemRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid) {
    super(inputItem, inputFluid, outIS, outputFluid);
  }


  public BarrelMultiItemRecipe setKeepStackSize(boolean b) {
    this.keepstacksize = b;
    return this;
  }



  public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
    ItemStack out = this.recipeOutIS.func_77946_l();
    if (inIS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

      int w = (int)Math.floor(Food.getWeight(inIS));
      if (w * this.recipeOutFluid.amount <= inFS.amount)
      {
        Food.setWeight(out, w * Food.getWeight(this.recipeOutIS));
      }
    }
    else if (inIS != null) {

      if (this.keepstacksize) {
        out.field_77994_a = inIS.field_77994_a;
      } else {
        out.field_77994_a *= inIS.field_77994_a;
      }
    }
    Stack<ItemStack> result = new Stack<>();
    result.push(out);

    return result;
  }



  public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
    FluidStack fs = this.recipeOutFluid.copy();

    if (inIS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

      int w = (int)Math.floor(Food.getWeight(inIS));
      if (w * this.recipeOutFluid.amount <= inFS.amount)
      {
        fs.amount = w * this.recipeOutFluid.amount;
      }
    }
    else if (inIS != null) {

      fs.amount *= inIS.field_77994_a;
    }
    return fs;
  }


  public boolean isKeepstacksize() {
    return this.keepstacksize;
  }



  public Boolean matches(ItemStack inIS, FluidStack inFS) {
    if (inIS != null && inFS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

      float w = Food.getWeight(inIS);
      if (inFS.isFluidEqual(this.recipeFluid) && w * this.recipeOutFluid.amount <= inFS.amount)
      {
        return Boolean.valueOf(OreDictionary.itemMatches(this.recipeIS, inIS, false));
      }

      return Boolean.valueOf(false);
    }
    return super.matches(inIS, inFS);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelMultiItemRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */