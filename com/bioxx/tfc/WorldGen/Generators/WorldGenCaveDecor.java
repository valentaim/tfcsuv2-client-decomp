/*    */ package com.bioxx.tfc.WorldGen.Generators;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.common.IWorldGenerator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenCaveDecor
/*    */   implements IWorldGenerator
/*    */ {
/*    */   public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/* 22 */     chunkX *= 16;
/* 23 */     chunkZ *= 16;
/* 24 */     for (int xCoord = 0; xCoord < 16; xCoord++) {
/*    */       
/* 26 */       for (int zCoord = 0; zCoord < 16; zCoord++) {
/*    */         
/* 28 */         for (int y = 127; y >= 0; y--) {
/*    */           
/* 30 */           int x = chunkX + xCoord;
/* 31 */           int z = chunkZ + zCoord;
/* 32 */           if (rand.nextInt(35) == 0 && createStalactite(world, rand, x, y, z)) {
/*    */             
/* 34 */             if (!createStalagmite(world, rand, x, y - 5, z)) {
/* 35 */               createStalagmite(world, rand, x, y - 6, z);
/*    */             }
/* 37 */           } else if (rand.nextInt(35) == 0) {
/*    */             
/* 39 */             createStalagmite(world, rand, x, y, z);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean createStalactite(World world, Random rand, int x, int y, int z) {
/* 48 */     if (!world.field_72995_K && y > 8 && world.func_147437_c(x, y, z) && TFC_Core.isRawStone(world, x, y + 1, z))
/*    */     {
/* 50 */       if (world.func_147437_c(x, y - 1, z) && world.func_147437_c(x, y - 2, z) && world.func_147437_c(x, y - 3, z)) {
/*    */         
/* 52 */         world.func_147465_d(x, y, z, TFCBlocks.stoneStalac, 0, 2);
/* 53 */         world.func_147465_d(x, y - 1, z, TFCBlocks.stoneStalac, 0, 2);
/* 54 */         world.func_147465_d(x, y - 2, z, TFCBlocks.stoneStalac, 0, 2);
/* 55 */         return true;
/*    */       } 
/*    */     }
/* 58 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean createStalagmite(World world, Random rand, int x, int y, int z) {
/* 63 */     if (!world.field_72995_K && y < 128 && world.func_147437_c(x, y, z) && TFC_Core.isRawStone(world, x, y - 1, z) && !world.func_72937_j(x, y, z))
/*    */     {
/* 65 */       if (world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z) && world.func_147437_c(x, y + 3, z)) {
/*    */         
/* 67 */         world.func_147465_d(x, y, z, TFCBlocks.stoneStalac, 8, 2);
/* 68 */         world.func_147465_d(x, y + 1, z, TFCBlocks.stoneStalac, 8, 2);
/* 69 */         world.func_147465_d(x, y + 2, z, TFCBlocks.stoneStalac, 8, 2);
/* 70 */         return true;
/*    */       } 
/*    */     }
/* 73 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCaveDecor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */