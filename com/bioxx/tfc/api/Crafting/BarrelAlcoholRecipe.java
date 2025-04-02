package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCItems;
import java.util.Stack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;





public class BarrelAlcoholRecipe
  extends BarrelRecipe
{
  public BarrelAlcoholRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid) {
    super(inputItem, inputFluid, outIS, outputFluid);
    this.sealTime = 72;
  }



  public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
    Stack<ItemStack> result = new Stack<>();
    result.push(this.recipeOutIS);
    return result;
  }



  public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
    float amt = inFS.amount / 10000.0F;
    FluidStack out = this.recipeOutFluid.copy();
    if (out.tag == null)
      out.tag = new NBTTagCompound();
    float weight = Food.getWeight(inIS);
    out.tag.func_74776_a("potency", weight / Food.getWeight(this.recipeIS) / amt);
    return this.recipeOutFluid;
  }



  public Boolean matches(ItemStack itemstack, FluidStack inFluid) {
    if (this.recipeIS.func_77942_o()) {

      if (itemstack == null || !itemstack.func_77942_o())
      {
        return Boolean.valueOf(false);
      }
      if (this.recipeIS.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {

        if (!(itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC))
        {
          return Boolean.valueOf(false);
        }
        float recipeWeight = Food.getWeight(this.recipeIS);
        float itemstackWeight = Food.getWeight(itemstack);
        float percent = itemstackWeight / recipeWeight * inFluid.amount / this.recipeFluid.amount;
        if (percent < ((itemstack.func_77973_b() == TFCItems.sugar) ? 0.25D : 1.0D))
          return Boolean.valueOf(false);
      }
    }
    return Boolean.valueOf((OreDictionary.itemMatches(this.recipeIS, itemstack, false) && inFluid.isFluidEqual(this.recipeFluid)));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelAlcoholRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */