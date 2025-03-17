/*    */ package com.bioxx.tfc.WorldGen.Generators;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.common.IWorldGenerator;
/*    */ import java.util.Random;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ 
/*    */ 
/*    */ public class WorldGenFissureCluster
/*    */   implements IWorldGenerator
/*    */ {
/*    */   private Random rand;
/* 14 */   private int waterRarity = 225;
/*    */   
/* 16 */   private WorldGenFissure fissureGenWater = new WorldGenFissure(TFCBlocks.freshWater);
/* 17 */   private WorldGenFissure fissureGenLava = new WorldGenFissure(TFCBlocks.lava);
/* 18 */   private WorldGenFissure fissureGenAir = new WorldGenFissure(null);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/* 24 */     this.rand = random;
/* 25 */     chunkX *= 16;
/* 26 */     chunkZ *= 16;
/*    */     
/* 28 */     int startX = chunkX + random.nextInt(16) + 8;
/* 29 */     int startZ = chunkZ + random.nextInt(16) + 8;
/*    */     
/* 31 */     if (this.rand.nextInt(this.waterRarity) == 0) {
/*    */       
/* 33 */       int num = 3 + this.rand.nextInt(10);
/* 34 */       for (int i = 0; i < num; i++) {
/*    */         
/* 36 */         int x = startX - 30 + random.nextInt(60);
/* 37 */         int z = startZ - 30 + random.nextInt(60);
/* 38 */         int y = world.func_72825_h(x, z) - 1;
/* 39 */         if (this.rand.nextInt(10) == 0) {
/* 40 */           this.fissureGenAir.generate(world, this.rand, x, y, z);
/*    */         } else {
/* 42 */           this.fissureGenWater.generate(world, this.rand, x, y, z);
/*    */         } 
/*    */       } 
/* 45 */     } else if (this.rand.nextInt(400) == 0) {
/*    */       
/* 47 */       int num = 3 + this.rand.nextInt(10);
/* 48 */       for (int i = 0; i < num; i++) {
/*    */         
/* 50 */         int x = startX - 30 + random.nextInt(60);
/* 51 */         int z = startZ - 30 + random.nextInt(60);
/* 52 */         int y = world.func_72825_h(x, z) - 1;
/*    */         
/* 54 */         if (this.rand.nextInt(10) == 0) {
/* 55 */           this.fissureGenAir.generate(world, this.rand, x, y, z);
/*    */         } else {
/* 57 */           this.fissureGenLava.generate(world, this.rand, x, y, z);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenFissureCluster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */