package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.Food;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;




public class BarrelBriningRecipe
  extends BarrelRecipe
{
  public BarrelBriningRecipe(FluidStack inputFluid, FluidStack outputFluid) {
    super(null, inputFluid, null, outputFluid, 4);
  }



  public Boolean matches(ItemStack item, FluidStack fluid) {
    if (item != null && item.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && !Food.isBrined(item)) {

      float w = Food.getWeight(item);
      if (fluid.isFluidEqual(this.recipeFluid) && w <= 1.0F * (fluid.amount / this.recipeFluid.amount))
      {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelBriningRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */