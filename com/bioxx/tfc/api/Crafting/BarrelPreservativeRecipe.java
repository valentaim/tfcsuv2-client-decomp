package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;





public class BarrelPreservativeRecipe
{
  private boolean requiresBrined;
  private boolean requiresPickled;
  private boolean requiresSalted;
  private boolean requiresDried;
  private boolean requiresSmoked;
  private boolean requiresInfused;
  private boolean requiresSealed;
  private boolean allowGrains = true;
  private boolean allowProteins = true;
  private boolean allowVegetables = true;
  private boolean allowFruit = true;
  private boolean allowDairy = true;
  private FluidStack liquidPerOz;
  private float environmentalDecayFactor = -1.0F;
  private float baseDecayModifier = -1.0F;





  private String preservingString;






  public boolean checkForPreservation(TEBarrel barrel, FluidStack fluid, ItemStack itemStack, boolean sealed) {
    if (itemStack == null || fluid == null)
    {
      return false;
    }
    if (!(itemStack.func_77973_b() instanceof IFood))
    {
      return false;
    }
    if (fluid.getFluid() != this.liquidPerOz.getFluid())
    {
      return false;
    }
    IFood iFood = (IFood)itemStack.func_77973_b();
    if (!this.allowGrains && iFood.getFoodGroup() == EnumFoodGroup.Grain)
    {
      return false;
    }
    if (!this.allowProteins && iFood.getFoodGroup() == EnumFoodGroup.Protein)
    {
      return false;
    }
    if (!this.allowFruit && iFood.getFoodGroup() == EnumFoodGroup.Fruit)
    {
      return false;
    }
    if (!this.allowVegetables && iFood.getFoodGroup() == EnumFoodGroup.Vegetable)
    {
      return false;
    }
    if (!this.allowDairy && iFood.getFoodGroup() == EnumFoodGroup.Dairy)
    {
      return false;
    }
    if (this.requiresBrined && !Food.isBrined(itemStack))
    {
      return false;
    }
    if (this.requiresPickled && !Food.isPickled(itemStack))
    {
      return false;
    }
    if (this.requiresSalted && !Food.isSalted(itemStack))
    {
      return false;
    }
    if (this.requiresDried && !Food.isDried(itemStack))
    {
      return false;
    }
    if (this.requiresSmoked && !Food.isSmoked(itemStack))
    {
      return false;
    }
    if (this.requiresInfused && !Food.isInfused(itemStack))
    {
      return false;
    }
    if (this.requiresSealed && !sealed)
    {
      return false;
    }
    float w = Food.getWeight(itemStack);
    return (this.liquidPerOz.amount * w <= fluid.amount);
  }

  public BarrelPreservativeRecipe(FluidStack liquidPerOz, String unlocalizedPreservingLabel) {
    this.liquidPerOz = liquidPerOz;
    this.preservingString = unlocalizedPreservingLabel;
  }


  public BarrelPreservativeRecipe setRequiresBrined(boolean b) {
    this.requiresBrined = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresPickled(boolean b) {
    this.requiresPickled = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresSalted(boolean b) {
    this.requiresSalted = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresDried(boolean b) {
    this.requiresDried = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresSmoked(boolean b) {
    this.requiresSmoked = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresInfused(boolean b) {
    this.requiresInfused = b;
    return this;
  }


  public BarrelPreservativeRecipe setRequiresSealed(boolean b) {
    this.requiresSealed = b;
    return this;
  }

  public BarrelPreservativeRecipe setAllowProtien(boolean b) {
    this.allowProteins = b;
    return this;
  }

  public BarrelPreservativeRecipe setAllowGrains(boolean b) {
    this.allowGrains = b;
    return this;
  }

  public BarrelPreservativeRecipe setAllowFruit(boolean b) {
    this.allowFruit = b;
    return this;
  }

  public BarrelPreservativeRecipe setAllowVegetable(boolean b) {
    this.allowVegetables = b;
    return this;
  }

  public BarrelPreservativeRecipe setAllowDairy(boolean b) {
    this.allowDairy = b;
    return this;
  }

  public BarrelPreservativeRecipe setEnvironmentalDecayFactor(float rate) {
    this.environmentalDecayFactor = rate;
    return this;
  }

  public BarrelPreservativeRecipe setBaseDecayModifier(float rate) {
    this.baseDecayModifier = rate;
    return this;
  }


  public float getEnvironmentalDecayFactor() {
    return this.environmentalDecayFactor;
  }

  public float getBaseDecayModifier() {
    return this.baseDecayModifier;
  }

  public String getPreservingString() {
    return this.preservingString;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelPreservativeRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */