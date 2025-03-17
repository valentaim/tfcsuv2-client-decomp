/*    */ package com.bioxx.tfc.Core;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.BlockDetailed;
/*    */ import com.bioxx.tfc.TileEntities.TEDetailed;
/*    */ import java.util.BitSet;
/*    */ import java.util.List;
/*    */ import net.minecraft.util.AxisAlignedBB;
/*    */ import net.minecraft.util.Vec3;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CollisionRayTraceDetailed
/*    */ {
/*    */   public static List<Object[]> rayTraceSubBlocks(BlockDetailed construct, Vec3 player, Vec3 view, int i, int j, int k, List<Object[]> returns, BitSet data, TEDetailed te) {
/* 16 */     int d = 8;
/*    */     
/* 18 */     float div = 1.0F / d;
/*    */     
/* 20 */     for (int subX = 0; subX < 8; subX++) {
/*    */       
/* 22 */       for (int subZ = 0; subZ < 8; subZ++) {
/*    */         
/* 24 */         for (int subY = 0; subY < 8; subY++) {
/*    */           
/* 26 */           if (te.data.get((subX * 8 + subZ) * 8 + subY)) {
/*    */             
/* 28 */             float minX = subX * div;
/* 29 */             float maxX = minX + div;
/* 30 */             float minY = subY * div;
/* 31 */             float maxY = minY + div;
/* 32 */             float minZ = subZ * div;
/* 33 */             float maxZ = minZ + div;
/*    */             
/* 35 */             Object[] ret = construct.rayTraceBound(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ), i, j, k, player, view);
/* 36 */             if (ret != null)
/* 37 */               returns.add(new Object[] { ret[0], ret[1], ret[2], Integer.valueOf(subX), Integer.valueOf(subY), Integer.valueOf(subZ) }); 
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/* 42 */     return returns;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\CollisionRayTraceDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */