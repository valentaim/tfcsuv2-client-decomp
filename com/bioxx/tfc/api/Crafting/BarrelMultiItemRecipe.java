/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import com.bioxx.tfc.api.Food;
/*    */ import java.util.Stack;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarrelMultiItemRecipe
/*    */   extends BarrelRecipe
/*    */ {
/*    */   public boolean keepstacksize = true;
/*    */   
/*    */   public BarrelMultiItemRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid) {
/* 17 */     super(inputItem, inputFluid, outIS, outputFluid);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrelMultiItemRecipe setKeepStackSize(boolean b) {
/* 22 */     this.keepstacksize = b;
/* 23 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 29 */     ItemStack out = this.recipeOutIS.func_77946_l();
/* 30 */     if (inIS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*    */       
/* 32 */       int w = (int)Math.floor(Food.getWeight(inIS));
/* 33 */       if (w * this.recipeOutFluid.amount <= inFS.amount)
/*    */       {
/* 35 */         Food.setWeight(out, w * Food.getWeight(this.recipeOutIS));
/*    */       }
/*    */     }
/* 38 */     else if (inIS != null) {
/*    */       
/* 40 */       if (this.keepstacksize) {
/* 41 */         out.field_77994_a = inIS.field_77994_a;
/*    */       } else {
/* 43 */         out.field_77994_a *= inIS.field_77994_a;
/*    */       } 
/*    */     } 
/* 46 */     Stack<ItemStack> result = new Stack<>();
/* 47 */     result.push(out);
/*    */     
/* 49 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 55 */     FluidStack fs = this.recipeOutFluid.copy();
/*    */     
/* 57 */     if (inIS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*    */       
/* 59 */       int w = (int)Math.floor(Food.getWeight(inIS));
/* 60 */       if (w * this.recipeOutFluid.amount <= inFS.amount)
/*    */       {
/* 62 */         fs.amount = w * this.recipeOutFluid.amount;
/*    */       }
/*    */     }
/* 65 */     else if (inIS != null) {
/*    */       
/* 67 */       fs.amount *= inIS.field_77994_a;
/*    */     } 
/* 69 */     return fs;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isKeepstacksize() {
/* 74 */     return this.keepstacksize;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Boolean matches(ItemStack inIS, FluidStack inFS) {
/* 80 */     if (inIS != null && inFS != null && inIS.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*    */       
/* 82 */       float w = Food.getWeight(inIS);
/* 83 */       if (inFS.isFluidEqual(this.recipeFluid) && w * this.recipeOutFluid.amount <= inFS.amount)
/*    */       {
/* 85 */         return Boolean.valueOf(OreDictionary.itemMatches(this.recipeIS, inIS, false));
/*    */       }
/*    */       
/* 88 */       return Boolean.valueOf(false);
/*    */     } 
/* 90 */     return super.matches(inIS, inFS);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelMultiItemRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */