package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;



public class BarrelVinegarRecipe
  extends BarrelRecipe
{
  public BarrelVinegarRecipe(FluidStack inputFluid, FluidStack outputFluid) {
    super(null, inputFluid, null, outputFluid);
    setMinTechLevel(0);
  }



  public Boolean matches(ItemStack item, FluidStack fluid) {
    if (item != null && item.func_77973_b() instanceof IFood)
    {
      if (fluid.isFluidEqual(this.recipeFluid) && ((IFood)item.func_77973_b()).getFoodGroup() == EnumFoodGroup.Fruit && Food.getWeight(item) >= 1.0F * (fluid.amount / 100))
      {
        return Boolean.valueOf(true);
      }
    }
    return Boolean.valueOf(false);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelVinegarRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */