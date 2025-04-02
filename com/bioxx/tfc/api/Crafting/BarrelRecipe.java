package com.bioxx.tfc.api.Crafting;

import java.util.Stack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;


public class BarrelRecipe
{
  public ItemStack recipeIS;
  public FluidStack recipeFluid;
  public ItemStack recipeOutIS;
  public FluidStack recipeOutFluid;
  public int sealTime = 8;
  public boolean removesLiquid = true;
  public boolean sealedRecipe = true;
  public int minTechLevel = 1;

  public boolean allowAnyStack = true;

  public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid) {
    this.recipeIS = inputItem;
    this.recipeFluid = inputFluid;
    this.recipeOutIS = outIS;
    this.recipeOutFluid = outputFluid;
  }


  public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int seal) {
    this(inputItem, inputFluid, outIS, outputFluid);
    this.sealTime = seal;
  }


  public BarrelRecipe setRemovesLiquid(boolean b) {
    this.removesLiquid = b;
    return this;
  }


  public BarrelRecipe setAllowAnyStack(boolean b) {
    this.allowAnyStack = b;
    return this;
  }


  public BarrelRecipe setMinTechLevel(int t) {
    this.minTechLevel = t;
    return this;
  }


  public BarrelRecipe setSealedRecipe(boolean b) {
    this.sealedRecipe = b;
    return this;
  }


  public Boolean matches(ItemStack item, FluidStack fluid) {
    boolean iStack = this.removesLiquid ? true : ((this.recipeIS != null && item != null && fluid != null && this.recipeFluid != null && item.field_77994_a >= (int)Math.ceil((fluid.amount / this.recipeFluid.amount))));
    boolean fStack = !this.removesLiquid ? true : ((this.recipeFluid != null && item != null && fluid != null && this.recipeOutFluid != null && fluid.amount >= item.field_77994_a * this.recipeOutFluid.amount));

    boolean anyStack = (!this.removesLiquid && !this.sealedRecipe && this.recipeOutIS == null && this.allowAnyStack);
    boolean itemsEqual = ((item == null && this.recipeIS == null) || OreDictionary.itemMatches(this.recipeIS, item, false));

    return Boolean.valueOf((((this.recipeIS != null && itemsEqual && (iStack || anyStack)) || this.recipeIS == null) && ((this.recipeFluid != null && this.recipeFluid
        .isFluidEqual(fluid) && (fStack || anyStack)) || this.recipeFluid == null)));
  }


  public Boolean isInFluid(FluidStack item) {
    return Boolean.valueOf(this.recipeFluid.isFluidEqual(item));
  }


  public ItemStack getInItem() {
    return this.recipeIS;
  }


  public FluidStack getInFluid() {
    return this.recipeFluid;
  }


  public ItemStack getRecipeOutIS() {
    return this.recipeOutIS;
  }


  public FluidStack getRecipeOutFluid() {
    return this.recipeOutFluid;
  }


  public int getSealTime() {
    return this.sealTime;
  }


  public boolean isRemovesLiquid() {
    return this.removesLiquid;
  }


  public int getMinTechLevel() {
    return this.minTechLevel;
  }


  public boolean isAllowAnyStack() {
    return this.allowAnyStack;
  }


  public String getRecipeName() {
    String s = "";
    if (this.recipeOutIS != null) {

      if (this.recipeOutIS.field_77994_a > 1)
        s = s + this.recipeOutIS.field_77994_a + "x ";
      s = s + this.recipeOutIS.func_82833_r();
    }
    if (this.recipeOutFluid != null && !this.recipeFluid.isFluidEqual(this.recipeOutFluid))
      s = this.recipeOutFluid.getFluid().getLocalizedName(this.recipeOutFluid);
    return s;
  }


  public boolean isSealedRecipe() {
    return this.sealedRecipe;
  }


  protected int getnumberOfRuns(ItemStack inIS, FluidStack inFS) {
    int runs = 0;
    int div = 0;
    if (inIS != null && this.recipeIS != null) {

      runs = inIS.field_77994_a / this.recipeIS.field_77994_a;
      div = inFS.amount / (getInFluid()).amount;
    }
    return Math.min(runs, div);
  }


  public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
    Stack<ItemStack> stackList = new Stack<>();
    ItemStack outStack = null;

    if (this.recipeOutIS != null) {

      stackList.clear();
      outStack = this.recipeOutIS.func_77946_l();
      int outputCount = outStack.field_77994_a * getnumberOfRuns(inIS, inFS);
      int maxStackSize = outStack.func_77976_d();
      Item item = outStack.func_77973_b();
      int damage = outStack.func_77960_j();

      int remainder = outputCount % maxStackSize;
      if (remainder > 0) {

        stackList.push(new ItemStack(item, remainder, damage));
        outputCount -= remainder;
      }

      while (outputCount >= maxStackSize) {

        stackList.push(new ItemStack(item, maxStackSize, damage));
        outputCount -= maxStackSize;
      }
      return stackList;
    }

    if (!this.removesLiquid && inIS != null && inFS != null) {

      stackList.clear();
      outStack = inIS.func_77946_l();
      outStack.field_77994_a -= inFS.amount / this.recipeOutFluid.amount;
      stackList.push(outStack);
    }
    if (outStack == null) {

      stackList.clear();
      stackList.push(outStack);
    }
    return stackList;
  }


  public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
    if (this.recipeOutFluid != null) {

      FluidStack fs = null;

      if (this.recipeOutFluid.tag != null) {

        fs = new FluidStack(this.recipeOutFluid.getFluid(), this.recipeOutFluid.amount, (NBTTagCompound)this.recipeOutFluid.tag.func_74737_b());
      }
      else {

        fs = new FluidStack(this.recipeOutFluid.getFluid(), this.recipeOutFluid.amount);
      }

      if (!this.removesLiquid && inFS != null) {

        fs.amount = inFS.amount;
      }
      else if (inIS != null) {

        fs.amount *= inIS.field_77994_a;
      }
      return fs;
    }
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */