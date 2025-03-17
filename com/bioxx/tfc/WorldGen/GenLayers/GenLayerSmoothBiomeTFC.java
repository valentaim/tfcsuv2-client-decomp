/*    */ package com.bioxx.tfc.WorldGen.GenLayers;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import net.minecraft.world.gen.layer.GenLayer;
/*    */ 
/*    */ 
/*    */ public class GenLayerSmoothBiomeTFC
/*    */   extends GenLayerTFC
/*    */ {
/*    */   public GenLayerSmoothBiomeTFC(long par1, GenLayer par3GenLayer) {
/* 11 */     super(par1);
/* 12 */     this.field_75909_a = (GenLayerTFC)par3GenLayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] func_75904_a(int par1, int par2, int par3, int par4) {
/* 22 */     int var5 = par1 - 1;
/* 23 */     int var6 = par2 - 1;
/* 24 */     int var7 = par3 + 2;
/* 25 */     int var8 = par4 + 2;
/* 26 */     int[] var9 = null;
/*    */     
/*    */     try {
/* 29 */       var9 = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
/*    */     }
/* 31 */     catch (Exception e2) {
/*    */       
/*    */       try {
/* 34 */         var9 = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
/*    */       }
/* 36 */       catch (Exception e1) {
/*    */         
/*    */         try {
/* 39 */           var9 = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
/*    */         }
/* 41 */         catch (Exception e) {
/*    */           
/* 43 */           throw new RuntimeException("[Thermos] GenLayerSmoothBiomeTFC.parent.getInts(): " + e.getMessage());
/*    */         } 
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 49 */     validateIntArray(var9, var7, var8);
/* 50 */     int[] var10 = new int[par3 * par4];
/*    */     
/* 52 */     for (int var11 = 0; var11 < par4; var11++) {
/*    */       
/* 54 */       for (int var12 = 0; var12 < par3; var12++) {
/*    */         
/* 56 */         int var13 = var9[var12 + 0 + (var11 + 1) * var7];
/* 57 */         int var14 = var9[var12 + 2 + (var11 + 1) * var7];
/* 58 */         int var15 = var9[var12 + 1 + (var11 + 0) * var7];
/* 59 */         int var16 = var9[var12 + 1 + (var11 + 2) * var7];
/* 60 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/*    */         
/* 62 */         if (var13 == var14 && var15 == var16) {
/*    */           
/* 64 */           func_75903_a((var12 + par1), (var11 + par2));
/* 65 */           if (func_75902_a(2) == 0) {
/* 66 */             var17 = var13;
/*    */           } else {
/* 68 */             var17 = var15;
/*    */           } 
/*    */         } else {
/*    */           
/* 72 */           if (var13 == var14)
/* 73 */             var17 = var13; 
/* 74 */           if (var15 == var16)
/* 75 */             var17 = var15; 
/*    */         } 
/* 77 */         if (var17 < 0)
/*    */           try {
/* 79 */             throw new Exception();
/* 80 */           } catch (Exception e) {
/*    */             
/* 82 */             TerraFirmaCraft.LOG.catching(e);
/*    */           }  
/* 84 */         var10[var12 + var11 * par3] = var17;
/* 85 */         validateInt(var10, var12 + var11 * par3);
/*    */       } 
/*    */     } 
/* 88 */     return var10;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\GenLayerSmoothBiomeTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */