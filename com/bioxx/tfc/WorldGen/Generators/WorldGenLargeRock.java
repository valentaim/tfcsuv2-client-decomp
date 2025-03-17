/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenLargeRock
/*     */   implements IWorldGenerator
/*     */ {
/*     */   private int xWidth;
/*     */   private int xWidth2;
/*     */   private int zWidth;
/*     */   private int zWidth2;
/*     */   private static final int HEIGHT = 3;
/*     */   
/*     */   public boolean generate(World world, Random rand, int i, int j, int k) {
/*  28 */     int yOffset = 0;
/*  29 */     boolean isFlatEnough = false;
/*     */     
/*  31 */     for (; yOffset > -2 && !isFlatEnough; yOffset--) {
/*     */       
/*  33 */       if (world.func_147439_a(i, j + yOffset, k).func_149721_r())
/*     */       {
/*  35 */         if (world.func_147439_a(i + 1, j + yOffset, k).func_149721_r() && world.func_147439_a(i - 1, j + yOffset, k).func_149721_r() && world
/*  36 */           .func_147439_a(i - 1, j + yOffset, k).func_149721_r() && world.func_147439_a(i, j + yOffset, k + 1).func_149721_r()) {
/*  37 */           isFlatEnough = true;
/*     */         }
/*     */       }
/*     */     } 
/*  41 */     if (j <= 155) {
/*     */ 
/*     */       
/*  44 */       int i2 = i + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
/*  45 */       int j2 = j + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
/*  46 */       int k2 = k + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
/*  47 */       genFromPoint(world, rand, i, j, k, yOffset);
/*  48 */       genFromPoint(world, rand, i2, j2, k2, yOffset);
/*     */     } 
/*  50 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void genFromPoint(World world, Random rand, int i, int j, int k, int yOffset) {
/*  55 */     DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(i, k, 0);
/*  56 */     Vec3 center = Vec3.func_72443_a(i, (j + yOffset), k);
/*  57 */     this.xWidth = 3;
/*  58 */     this.xWidth2 = 3;
/*  59 */     this.zWidth = 3;
/*  60 */     this.zWidth2 = 3;
/*     */     
/*  62 */     for (int xCoord = i - this.xWidth; xCoord <= i + this.xWidth2; xCoord++) {
/*     */       
/*  64 */       for (int zCoord = k - this.zWidth; zCoord <= k + this.zWidth2; zCoord++) {
/*     */         
/*  66 */         for (int yCoord = j + yOffset - 3; yCoord <= j + yOffset + 3; yCoord++) {
/*     */           
/*  68 */           Vec3 point = Vec3.func_72443_a(xCoord, yCoord, zCoord);
/*  69 */           double distance = center.func_72436_e(point);
/*  70 */           boolean canPlaceX = true;
/*  71 */           boolean canPlaceZ = true;
/*  72 */           if (xCoord < i && distance > (this.xWidth * 4))
/*  73 */             canPlaceX = false; 
/*  74 */           if (xCoord > i && distance > (this.xWidth2 * 4))
/*  75 */             canPlaceX = false; 
/*  76 */           if (zCoord < i && distance > (this.zWidth * 4))
/*  77 */             canPlaceZ = false; 
/*  78 */           if (zCoord > i && distance > (this.zWidth2 * 4))
/*  79 */             canPlaceZ = false; 
/*  80 */           if (rand.nextInt(10) + 1 != 0 && canPlaceX && canPlaceZ) {
/*  81 */             world.func_147465_d(xCoord, yCoord, zCoord, rockLayer1.block, rockLayer1.data2, 2);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  91 */     chunkX *= 16;
/*  92 */     chunkZ *= 16;
/*  93 */     int xCoord = 0;
/*  94 */     int zCoord = 0;
/*     */     
/*  96 */     for (int count = 0; count < 1; count++) {
/*     */       
/*  98 */       xCoord = chunkX + random.nextInt(16) + 8;
/*  99 */       zCoord = chunkZ + random.nextInt(16) + 8;
/* 100 */       int yCoord = world.func_72976_f(xCoord, zCoord) - 1;
/* 101 */       this.xWidth = 2 + random.nextInt(6);
/* 102 */       this.xWidth2 = 2 + random.nextInt(6);
/* 103 */       this.zWidth = 2 + random.nextInt(6);
/* 104 */       this.zWidth2 = 2 + random.nextInt(6);
/* 105 */       if (random.nextInt(20) == 0 && TFC_Core.isSoil(world.func_147439_a(xCoord, yCoord, zCoord)))
/* 106 */         generate(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenLargeRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */