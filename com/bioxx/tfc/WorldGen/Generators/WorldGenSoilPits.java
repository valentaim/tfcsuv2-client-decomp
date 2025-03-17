/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenSoilPits
/*     */   implements IWorldGenerator
/*     */ {
/*  18 */   private static WorldGenBerryBush cranberryGen = new WorldGenBerryBush(false, 6, 15, 1, 6, TFCBlocks.peatGrass);
/*  19 */   private static WorldGenBerryBush cloudberryGen = new WorldGenBerryBush(false, 10, 12, 1, 6, TFCBlocks.peatGrass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  29 */     chunkX *= 16;
/*  30 */     chunkZ *= 16;
/*     */     
/*  32 */     int x = chunkX + random.nextInt(16) + 8;
/*  33 */     int z = chunkZ + random.nextInt(16) + 8;
/*  34 */     generateClay(world, random, x, world.func_72825_h(x, z), z);
/*     */     
/*  36 */     x = chunkX + random.nextInt(16) + 8;
/*  37 */     z = chunkZ + random.nextInt(16) + 8;
/*  38 */     if (generatePeat(world, random, x, world.func_72825_h(x, z), z))
/*     */     {
/*  40 */       if (random.nextInt(5) == 0)
/*     */       {
/*  42 */         if (!cloudberryGen.func_76484_a(world, random, x, world.func_72825_h(x, z) + 1, z)) {
/*  43 */           cranberryGen.func_76484_a(world, random, x, world.func_72825_h(x, z) + 1, z);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean generatePeat(World world, Random random, int xCoord, int yCoord, int zCoord) {
/*  50 */     int var6 = random.nextInt(16) + 8;
/*  51 */     byte var7 = 2;
/*  52 */     boolean flag = false;
/*     */     
/*  54 */     if (random.nextInt(50) == 0 && yCoord <= 144)
/*     */     {
/*  56 */       for (int x = xCoord - var6; x <= xCoord + var6; x++) {
/*     */         
/*  58 */         for (int z = zCoord - var6; z <= zCoord + var6; z++) {
/*     */           
/*  60 */           int var10 = x - xCoord;
/*  61 */           int var11 = z - zCoord;
/*  62 */           if (var10 * var10 + var11 * var11 <= var6 * var6)
/*     */           {
/*  64 */             for (int y = yCoord - var7; y <= yCoord + var7; y++) {
/*     */               
/*  66 */               Block block = world.func_147439_a(x, y, z);
/*  67 */               if (TFC_Climate.isSwamp(world, x, y, z))
/*     */               {
/*  69 */                 if (TFC_Core.isDirt(block) || TFC_Core.isClay(block) || TFC_Core.isPeat(block)) {
/*     */                   
/*  71 */                   world.func_147465_d(x, y, z, TFCBlocks.peat, 0, 2);
/*  72 */                   flag = true;
/*     */                 }
/*  74 */                 else if (TFC_Core.isGrass(block)) {
/*     */                   
/*  76 */                   world.func_147465_d(x, y, z, TFCBlocks.peatGrass, 0, 2);
/*  77 */                   flag = true;
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*  85 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateClay(World world, Random rand, int i, int j, int k) {
/*  90 */     int radius = rand.nextInt(14) + 2;
/*  91 */     byte depth = (byte)(rand.nextInt(3) + 1);
/*  92 */     boolean flag = false;
/*  93 */     if (rand.nextInt(30) == 0 && j <= 150)
/*     */     {
/*  95 */       for (int xCoord = i - radius; xCoord <= i + radius; xCoord++) {
/*     */         
/*  97 */         for (int zCoord = k - radius; zCoord <= k + radius; zCoord++) {
/*     */           
/*  99 */           int x = xCoord - i;
/* 100 */           int z = zCoord - k;
/* 101 */           if (x * x + z * z <= radius * radius && TFC_Climate.getRainfall(world, xCoord, 144, zCoord) >= 500.0F) {
/*     */             
/* 103 */             flag = false;
/* 104 */             for (int yCoord = j - depth; yCoord <= j + depth; yCoord++) {
/*     */               
/* 106 */               Block block = world.func_147439_a(xCoord, yCoord, zCoord);
/* 107 */               if (TFC_Climate.getCacheManager(world) != null) {
/*     */                 
/* 109 */                 DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 0);
/* 110 */                 if (block == TFCBlocks.dirt || block == TFCBlocks.dirt2) {
/*     */                   
/* 112 */                   world.func_147465_d(xCoord, yCoord, zCoord, TFC_Core.getTypeForClay(block), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
/* 113 */                   flag = true;
/*     */                 }
/* 115 */                 else if (block == TFCBlocks.grass || block == TFCBlocks.grass2) {
/*     */                   
/* 117 */                   world.func_147465_d(xCoord, yCoord, zCoord, TFC_Core.getTypeForClayGrass(block), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
/* 118 */                   flag = true;
/*     */                 } 
/*     */               } 
/*     */             } 
/* 122 */             if (flag && rand.nextInt(15) == 0) {
/*     */               
/* 124 */               int y = world.func_72825_h(xCoord, zCoord);
/* 125 */               if (world.func_147437_c(xCoord, y, zCoord) && TFC_Core.isSoil(world.func_147439_a(xCoord, y - 1, zCoord)))
/* 126 */                 world.func_147465_d(xCoord, y, zCoord, TFCBlocks.flora, 0, 2); 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/* 132 */     return flag;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenSoilPits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */