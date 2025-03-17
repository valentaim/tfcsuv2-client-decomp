/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomFruitTree;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenPlants
/*     */   implements IWorldGenerator
/*     */ {
/*  17 */   private static WorldGenFungi plantFungiGen = new WorldGenFungi();
/*     */   
/*  19 */   private static WorldGenCustomFruitTree appleTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 0);
/*  20 */   private static WorldGenCustomFruitTree bananaTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 1);
/*  21 */   private static WorldGenCustomFruitTree orangeTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 2);
/*  22 */   private static WorldGenCustomFruitTree grappleTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 3);
/*  23 */   private static WorldGenCustomFruitTree lemonTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 4);
/*  24 */   private static WorldGenCustomFruitTree oliveTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 5);
/*  25 */   private static WorldGenCustomFruitTree cherryTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 6);
/*  26 */   private static WorldGenCustomFruitTree peachTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 7);
/*     */   
/*  28 */   private static WorldGenCustomFruitTree plumTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves2, 8);
/*     */ 
/*     */   
/*  31 */   private static WorldGenBerryBush wintergreenGen = new WorldGenBerryBush(false, 0, 12, 1, 5);
/*  32 */   private static WorldGenBerryBush blueberryGen = new WorldGenBerryBush(false, 1, 6, 1, 4);
/*  33 */   private static WorldGenBerryBush raspberryGen = new WorldGenBerryBush(false, 2, 5, 2, 4);
/*  34 */   private static WorldGenBerryBush strawberryGen = new WorldGenBerryBush(false, 3, 8, 1, 4);
/*  35 */   private static WorldGenBerryBush blackberryGen = new WorldGenBerryBush(false, 4, 5, 2, 4);
/*  36 */   private static WorldGenBerryBush bunchberryGen = new WorldGenBerryBush(false, 5, 8, 1, 4);
/*  37 */   private static WorldGenBerryBush cranberryGen = new WorldGenBerryBush(false, 6, 15, 1, 6);
/*  38 */   private static WorldGenBerryBush snowberryGen = new WorldGenBerryBush(false, 7, 6, 1, 4);
/*  39 */   private static WorldGenBerryBush elderberryGen = new WorldGenBerryBush(false, 8, 5, 2, 4);
/*  40 */   private static WorldGenBerryBush gooseberryGen = new WorldGenBerryBush(false, 9, 8, 1, 4);
/*  41 */   private static WorldGenBerryBush cloudberryGen = new WorldGenBerryBush(false, 10, 12, 1, 6, TFCBlocks.peatGrass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  51 */     chunkX *= 16;
/*  52 */     chunkZ *= 16;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     int grassPerChunk = 0;
/*  59 */     int flowerChunkRarity = 30;
/*  60 */     int mushroomsPerChunk = 0;
/*     */ 
/*     */     
/*  63 */     float rain = TFC_Climate.getRainfall(world, chunkX, 144, chunkZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  69 */     if (rain >= 125.0F) {
/*     */       
/*  71 */       grassPerChunk += 12;
/*  72 */       mushroomsPerChunk++;
/*     */     } 
/*  74 */     if (rain >= 250.0F) {
/*     */       
/*  76 */       grassPerChunk += 18;
/*  77 */       flowerChunkRarity -= 2;
/*  78 */       mushroomsPerChunk++;
/*     */     } 
/*  80 */     if (rain >= 500.0F) {
/*     */       
/*  82 */       grassPerChunk += 24;
/*  83 */       flowerChunkRarity -= 3;
/*  84 */       mushroomsPerChunk++;
/*     */     } 
/*  86 */     if (rain >= 1000.0F) {
/*     */       
/*  88 */       flowerChunkRarity -= 5;
/*  89 */       mushroomsPerChunk++;
/*     */     } 
/*  91 */     if (rain >= 2000.0F) {
/*     */       
/*  93 */       flowerChunkRarity -= 5;
/*  94 */       mushroomsPerChunk++;
/*     */     } 
/*  96 */     float bioTemperature = TFC_Climate.getBioTemperatureHeight(world, chunkX, 144, chunkZ);
/*  97 */     if (bioTemperature < 10.0F)
/*     */     {
/*  99 */       grassPerChunk /= 2;
/*     */     }
/* 101 */     if (bioTemperature < 5.0F)
/*     */     {
/* 103 */       grassPerChunk /= 2;
/*     */     }
/* 105 */     if (bioTemperature < 0.0F)
/*     */     {
/* 107 */       grassPerChunk = 0;
/*     */     }
/*     */     
/* 110 */     WorldGenFlowers.generate(world, random, chunkX, chunkZ, flowerChunkRarity);
/*     */     
/* 112 */     genBushes(random, chunkX, chunkZ, world); int i;
/* 113 */     for (i = 0; i < grassPerChunk; i++) {
/*     */       
/* 115 */       int xCoord = chunkX + random.nextInt(16) + 8;
/* 116 */       int zCoord = chunkZ + random.nextInt(16) + 8;
/* 117 */       int yCoord = world.func_72825_h(xCoord, zCoord);
/* 118 */       bioTemperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
/* 119 */       if (world.func_147437_c(xCoord, yCoord, zCoord) && TFCBlocks.tallGrass
/* 120 */         .func_149718_j(world, xCoord, yCoord, zCoord) && 
/* 121 */         !TFC_Core.isDryGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
/*     */       {
/* 123 */         world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(20) == 0) ? 1 : 0, 2);
/*     */       }
/*     */       
/* 126 */       if (bioTemperature >= 0.0F)
/*     */       {
/* 128 */         if (world.func_147437_c(xCoord, yCoord, zCoord) && TFCBlocks.tallGrass
/* 129 */           .func_149718_j(world, xCoord, yCoord, zCoord) && 
/* 130 */           TFC_Core.isDryGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
/*     */         {
/* 132 */           world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(60) == 0) ? 1 : 2, 2);
/*     */         }
/*     */       }
/*     */     } 
/*     */     
/* 137 */     for (i = 0; i < mushroomsPerChunk; i++) {
/*     */       
/* 139 */       if (random.nextInt(4) == 0) {
/*     */         
/* 141 */         int xCoord = chunkX + random.nextInt(16) + 8;
/* 142 */         int zCoord = chunkZ + random.nextInt(16) + 8;
/* 143 */         int yCoord = world.func_72825_h(xCoord, zCoord);
/* 144 */         plantFungiGen.genWithMeta(world, random, xCoord, yCoord, zCoord, 0);
/*     */       } 
/*     */       
/* 147 */       if (random.nextInt(8) == 0) {
/*     */         
/* 149 */         int xCoord = chunkX + random.nextInt(16) + 8;
/* 150 */         int zCoord = chunkZ + random.nextInt(16) + 8;
/* 151 */         int yCoord = world.func_72825_h(xCoord, zCoord);
/* 152 */         plantFungiGen.genWithMeta(world, random, xCoord, yCoord, zCoord, 1);
/*     */       } 
/*     */     } 
/*     */     
/* 156 */     if (random.nextInt(70) == 0 && rain >= 500.0F) {
/*     */       
/* 158 */       int xCoord = chunkX + random.nextInt(16) + 8;
/* 159 */       int zCoord = chunkZ + random.nextInt(16) + 8;
/* 160 */       int yCoord = world.func_72825_h(xCoord, zCoord);
/* 161 */       bioTemperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
/* 162 */       switch ((new Random(world.func_72905_C() + (((chunkX >> 4) - (chunkZ >> 4)) * (chunkZ >> 4)))).nextInt(9)) {
/*     */ 
/*     */         
/*     */         default:
/* 166 */           if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 167 */             appleTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 1:
/* 172 */           if (bioTemperature > 15.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 173 */             bananaTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 2:
/* 178 */           if (bioTemperature > 12.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 179 */             orangeTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 3:
/* 184 */           if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 185 */             grappleTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 4:
/* 190 */           if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 191 */             lemonTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 5:
/* 196 */           if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 197 */             oliveTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 6:
/* 202 */           if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 203 */             cherryTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */           return;
/*     */         
/*     */         case 7:
/* 208 */           if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
/* 209 */             peachTree.func_76484_a(world, random, xCoord, yCoord, zCoord); 
/*     */           return;
/*     */         case 8:
/*     */           break;
/*     */       } 
/* 214 */       if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
/* 215 */         plumTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void genBushes(Random random, int chunkX, int chunkZ, World world) {
/* 234 */     if (random.nextInt(12) == 0) {
/*     */       
/* 236 */       int xCoord = chunkX + random.nextInt(16) + 8;
/* 237 */       int zCoord = chunkZ + random.nextInt(16) + 8;
/* 238 */       int yCoord = world.func_72825_h(xCoord, zCoord);
/* 239 */       switch (random.nextInt(11)) {
/*     */         
/*     */         default:
/* 242 */           wintergreenGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 1:
/* 245 */           blueberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 2:
/* 248 */           raspberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 3:
/* 251 */           strawberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 4:
/* 254 */           blackberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 5:
/* 257 */           bunchberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 6:
/* 260 */           cranberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 7:
/* 263 */           snowberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 8:
/* 266 */           elderberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           return;
/*     */         case 9:
/* 269 */           gooseberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord); return;
/*     */         case 10:
/*     */           break;
/* 272 */       }  cloudberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenPlants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */