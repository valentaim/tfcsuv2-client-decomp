/*    */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.EVT;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.DataLayer;
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*    */ import net.minecraft.world.gen.layer.GenLayer;
/*    */ 
/*    */ 
/*    */ public class GenLayerEVTMix
/*    */   extends GenLayerTFC
/*    */ {
/*    */   public GenLayerEVTMix(long par1, GenLayer par3GenLayer) {
/* 12 */     super(par1);
/* 13 */     this.field_75909_a = (GenLayerTFC)par3GenLayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] func_75904_a(int x, int z, int xSize, int zSize) {
/* 23 */     int[] var5 = this.field_75909_a.func_75904_a(x - 1, z - 1, xSize + 2, zSize + 2);
/* 24 */     int[] outCache = new int[xSize * zSize];
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     for (int var7 = 0; var7 < zSize; var7++) {
/*    */       
/* 34 */       for (int var8 = 0; var8 < xSize; var8++) {
/*    */         
/* 36 */         func_75903_a((var8 + x), (var7 + z));
/* 37 */         int thisID = var5[var8 + 1 + (var7 + 1) * (xSize + 2)];
/* 38 */         int id0 = var5[var8 + 1 + (var7 + 1 - 1) * (xSize + 2)];
/* 39 */         int id1 = var5[var8 + 1 + 1 + (var7 + 1) * (xSize + 2)];
/* 40 */         int id2 = var5[var8 + 1 - 1 + (var7 + 1) * (xSize + 2)];
/* 41 */         int id3 = var5[var8 + 1 + (var7 + 1 + 1) * (xSize + 2)];
/* 42 */         int index = var8 + var7 * xSize;
/*    */         
/* 44 */         if ((id0 >= thisID + 2 || id1 >= thisID + 2 || id2 >= thisID + 2 || id3 >= thisID + 2) && 
/* 45 */           thisID + 1 < DataLayer.EVT_16.layerID)
/* 46 */           thisID++; 
/* 47 */         if ((id0 <= thisID - 2 || id1 <= thisID - 2 || id2 <= thisID - 2 || id3 <= thisID - 2) && 
/* 48 */           thisID - 1 > DataLayer.EVT_0_125.layerID) {
/* 49 */           thisID--;
/*    */         }
/* 51 */         outCache[index] = thisID;
/*    */       } 
/*    */     } 
/* 54 */     return outCache;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\EVT\GenLayerEVTMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */