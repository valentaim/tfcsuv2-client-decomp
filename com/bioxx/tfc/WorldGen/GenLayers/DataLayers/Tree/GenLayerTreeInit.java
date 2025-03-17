/*    */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Tree;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.DataLayer;
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*    */ 
/*    */ public class GenLayerTreeInit
/*    */   extends GenLayerTFC {
/*    */   private DataLayer[] layerTrees;
/*    */   
/*    */   public GenLayerTreeInit(long par1, DataLayer[] trees) {
/* 11 */     super(par1);
/* 12 */     this.layerTrees = (DataLayer[])trees.clone();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] func_75904_a(int par1, int par2, int maxX, int maxZ) {
/* 18 */     int[] cache = new int[maxX * maxZ];
/*    */     
/* 20 */     for (int z = 0; z < maxZ; z++) {
/*    */       
/* 22 */       for (int x = 0; x < maxX; x++) {
/*    */         
/* 24 */         func_75903_a((par1 + x), (par2 + z));
/* 25 */         cache[x + z * maxX] = (this.layerTrees[func_75902_a(this.layerTrees.length)]).layerID;
/*    */       } 
/*    */     } 
/*    */     
/* 29 */     return cache;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Tree\GenLayerTreeInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */