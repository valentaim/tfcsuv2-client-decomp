package com.bioxx.tfc.api.Crafting;

import java.util.Stack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class BarrelLiquidToLiquidRecipe
  extends BarrelRecipe
{
  public FluidStack inputfluid;

  public BarrelLiquidToLiquidRecipe(FluidStack fluidInBarrel, FluidStack inputfluid, FluidStack outputFluid) {
    super(null, fluidInBarrel, null, outputFluid);
    this.inputfluid = inputfluid;
  }



  public Boolean matches(ItemStack item, FluidStack fluid) {
    FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(item);
    if (this.recipeFluid != null && this.recipeFluid.isFluidEqual(fluid) && itemLiquid != null && itemLiquid.isFluidEqual(this.inputfluid)) {


      if (10000 - fluid.amount < itemLiquid.amount) {
        return Boolean.valueOf(false);
      }
      return Boolean.valueOf(true);
    }
    return Boolean.valueOf(false);
  }



  public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
    Stack<ItemStack> result = new Stack<>();
    if (inIS != null) {
      result.push(inIS.func_77973_b().getContainerItem(inIS));
    } else {
      result.push(null);
    }
    return result;
  }



  public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
    if (this.recipeOutFluid != null) {

      FluidStack fs = this.recipeOutFluid.copy();
      FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(inIS);
      if (!this.removesLiquid) {

        inFS.amount += itemLiquid.amount;
      }
      else {

        fs.amount = fs.amount * inFS.amount / this.recipeFluid.amount;
      }
      return fs;
    }
    return null;
  }


  public FluidStack getInputfluid() {
    return this.inputfluid;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelLiquidToLiquidRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */