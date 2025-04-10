/*    */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*    */ import net.minecraft.world.gen.layer.GenLayer;
/*    */ 
/*    */ 
/*    */ public class GenLayerPHMix
/*    */   extends GenLayerTFC
/*    */ {
/*    */   public GenLayerPHMix(long par1, GenLayer par3GenLayer) {
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
/*    */   public int[] func_75904_a(int x, int z, int xSize, int zSize) {
/* 22 */     int[] var5 = this.field_75909_a.func_75904_a(x - 1, z - 1, xSize + 2, zSize + 2);
/* 23 */     int[] outCache = new int[xSize * zSize];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 31 */     for (int var7 = 0; var7 < zSize; var7++) {
/*    */       
/* 33 */       for (int var8 = 0; var8 < xSize; var8++) {
/*    */         
/* 35 */         func_75903_a((var8 + x), (var7 + z));
/* 36 */         int thisID = var5[var8 + 1 + (var7 + 1) * (xSize + 2)];
/* 37 */         int id0 = var5[var8 + 1 + (var7 + 1 - 1) * (xSize + 2)];
/* 38 */         int id1 = var5[var8 + 1 + 1 + (var7 + 1) * (xSize + 2)];
/* 39 */         int id2 = var5[var8 + 1 - 1 + (var7 + 1) * (xSize + 2)];
/* 40 */         int id3 = var5[var8 + 1 + (var7 + 1 + 1) * (xSize + 2)];
/* 41 */         int index = var8 + var7 * xSize;
/*    */         
/* 43 */         if ((id0 >= thisID + 2 || id1 >= thisID + 2 || id2 >= thisID + 2 || id3 >= thisID + 2) && 
/* 44 */           thisID + 1 < GenPHLayer.MAX)
/* 45 */           thisID++; 
/* 46 */         if ((id0 <= thisID - 2 || id1 <= thisID - 2 || id2 <= thisID - 2 || id3 <= thisID - 2) && 
/* 47 */           thisID - 1 > GenPHLayer.MIN) {
/* 48 */           thisID--;
/*    */         }
/* 50 */         outCache[index] = thisID;
/*    */       } 
/*    */     } 
/* 53 */     return outCache;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\PH\GenLayerPHMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */