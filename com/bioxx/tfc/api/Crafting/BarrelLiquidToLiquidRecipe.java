/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import java.util.Stack;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidContainerRegistry;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ public class BarrelLiquidToLiquidRecipe
/*    */   extends BarrelRecipe
/*    */ {
/*    */   public FluidStack inputfluid;
/*    */   
/*    */   public BarrelLiquidToLiquidRecipe(FluidStack fluidInBarrel, FluidStack inputfluid, FluidStack outputFluid) {
/* 14 */     super(null, fluidInBarrel, null, outputFluid);
/* 15 */     this.inputfluid = inputfluid;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean matches(ItemStack item, FluidStack fluid) {
/* 21 */     FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(item);
/* 22 */     if (this.recipeFluid != null && this.recipeFluid.isFluidEqual(fluid) && itemLiquid != null && itemLiquid.isFluidEqual(this.inputfluid)) {
/*    */ 
/*    */       
/* 25 */       if (10000 - fluid.amount < itemLiquid.amount) {
/* 26 */         return Boolean.valueOf(false);
/*    */       }
/* 28 */       return Boolean.valueOf(true);
/*    */     } 
/* 30 */     return Boolean.valueOf(false);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 36 */     Stack<ItemStack> result = new Stack<>();
/* 37 */     if (inIS != null) {
/* 38 */       result.push(inIS.func_77973_b().getContainerItem(inIS));
/*    */     } else {
/* 40 */       result.push(null);
/*    */     } 
/* 42 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 48 */     if (this.recipeOutFluid != null) {
/*    */       
/* 50 */       FluidStack fs = this.recipeOutFluid.copy();
/* 51 */       FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(inIS);
/* 52 */       if (!this.removesLiquid) {
/*    */         
/* 54 */         inFS.amount += itemLiquid.amount;
/*    */       }
/*    */       else {
/*    */         
/* 58 */         fs.amount = fs.amount * inFS.amount / this.recipeFluid.amount;
/*    */       } 
/* 60 */       return fs;
/*    */     } 
/* 62 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public FluidStack getInputfluid() {
/* 67 */     return this.inputfluid;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelLiquidToLiquidRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */