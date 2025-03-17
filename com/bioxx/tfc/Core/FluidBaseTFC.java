/*    */ package com.bioxx.tfc.Core;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraftforge.fluids.Fluid;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ public class FluidBaseTFC extends Fluid {
/*    */   private int color;
/*    */   
/*    */   public FluidBaseTFC(String fluidName) {
/* 12 */     super(fluidName);
/*    */ 
/*    */     
/* 15 */     this.color = 16777215;
/*    */   }
/*    */   
/*    */   public FluidBaseTFC setBaseColor(int c) {
/* 19 */     this.color = c;
/* 20 */     return this;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColor(FluidStack fs) {
/* 26 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getColor() {
/* 32 */     return this.color;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon getStillIcon() {
/* 38 */     if (this.stillIcon == null)
/* 39 */       return TFCBlocks.hotWater.func_149691_a(0, 0); 
/* 40 */     return this.stillIcon;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IIcon getFlowingIcon() {
/* 46 */     if (this.flowingIcon == null)
/* 47 */       return TFCBlocks.hotWater.func_149691_a(2, 0); 
/* 48 */     return this.flowingIcon;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\FluidBaseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */