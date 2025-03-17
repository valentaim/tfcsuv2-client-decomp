/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenAcaciaKoaTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomShortTrees;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Enums.EnumTree;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenForests
/*     */   implements IWorldGenerator
/*     */ {
/*     */   private WorldGenerator gen0;
/*     */   private WorldGenerator gen1;
/*     */   private WorldGenerator gen2;
/*     */   private int treeType0;
/*     */   private int treeType1;
/*     */   private int treeType2;
/*     */   private float evt;
/*     */   private float rainfall;
/*  31 */   private float temperature = 20.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  37 */     chunkX *= 16;
/*  38 */     chunkZ *= 16;
/*     */     
/*  40 */     if (world.func_72807_a(chunkX, chunkZ) instanceof TFCBiome) {
/*     */       
/*  42 */       TFCBiome biome = (TFCBiome)world.func_72807_a(chunkX, chunkZ);
/*  43 */       if (biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN) {
/*     */         return;
/*     */       }
/*  46 */       this.rainfall = TFC_Climate.getRainfall(world, chunkX, 0, chunkZ);
/*  47 */       this.evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(chunkX + 8, chunkZ + 8)).floatdata1;
/*  48 */       this.treeType0 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 0);
/*  49 */       this.treeType1 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 1);
/*  50 */       this.treeType2 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 2);
/*     */       
/*  52 */       this.gen0 = TFCBiome.getTreeGen(this.treeType0, Boolean.valueOf(random.nextBoolean()));
/*  53 */       this.gen1 = TFCBiome.getTreeGen(this.treeType1, Boolean.valueOf(random.nextBoolean()));
/*  54 */       this.gen2 = TFCBiome.getTreeGen(this.treeType2, Boolean.valueOf(random.nextBoolean()));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  59 */       if (!generateJungle(random, chunkX, chunkZ, world)) {
/*  60 */         generateForest(random, chunkX, chunkZ, world);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void generateForest(Random random, int chunkX, int chunkZ, World world) {
/*  66 */     int xCoord = chunkX;
/*  67 */     int yCoord = 145;
/*  68 */     int zCoord = chunkZ;
/*     */     
/*  70 */     int numTreesBase = 8;
/*  71 */     if (random.nextInt(10) == 0)
/*     */     {
/*  73 */       numTreesBase -= 6;
/*     */     }
/*     */     
/*  76 */     int numTrees = 1;
/*  77 */     for (int var2 = 0; var2 < numTrees; var2++) {
/*     */       
/*  79 */       xCoord = chunkX + random.nextInt(16);
/*  80 */       zCoord = chunkZ + random.nextInt(16);
/*  81 */       yCoord = world.func_72976_f(xCoord, zCoord);
/*     */       
/*  83 */       numTrees = (int)(numTreesBase + this.rainfall / 1000.0F * 2.0F);
/*  84 */       if (numTrees > 30) {
/*  85 */         numTrees = 30;
/*     */       }
/*  87 */       this.temperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, world.func_72976_f(xCoord, zCoord), zCoord);
/*  88 */       int spawnParam0 = canTreeSpawn(this.treeType0);
/*  89 */       int spawnParam1 = canTreeSpawn(this.treeType1);
/*  90 */       int spawnParam2 = canTreeSpawn(this.treeType2);
/*     */       
/*  92 */       if (getNearWater(world, xCoord, yCoord, zCoord)) {
/*     */         
/*  94 */         this.rainfall *= 2.0F;
/*  95 */         this.evt /= 2.0F;
/*     */       } 
/*     */       
/*     */       try {
/*  99 */         if (zCoord > 14500 || zCoord < -14500) {
/* 100 */           this.gen2 = TFCBiome.getTreeGen(8, Boolean.valueOf(random.nextBoolean()));
/*     */         }
/*     */         
/* 103 */         if ((spawnParam0 & 0x1) > 0 || (spawnParam1 & 0x1) > 0 || (spawnParam2 & 0x1) > 0) {
/*     */ 
/*     */           
/* 106 */           if (spawnParam0 > 0 && (spawnParam0 & 0x2) == 0 && spawnParam1 > 0 && (spawnParam1 & 0x2) == 0 && spawnParam2 > 0 && (spawnParam2 & 0x2) == 0)
/*     */           {
/*     */             
/* 109 */             if (random.nextInt(8) == 0) {
/* 110 */               numTrees = 1;
/*     */             } else {
/*     */               return;
/*     */             } 
/*     */           }
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */ 
/*     */         
/* 120 */         int randomNumber = random.nextInt(100);
/* 121 */         if (randomNumber < 50 && this.gen0 != null && (spawnParam0 == 5 || spawnParam0 == 7))
/*     */         {
/* 123 */           this.gen0.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */         }
/* 125 */         else if (randomNumber < 80 && this.gen1 != null && (spawnParam1 == 5 || spawnParam1 == 7))
/*     */         {
/* 127 */           this.gen1.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */         }
/* 129 */         else if (randomNumber < 100 && this.gen2 != null && (spawnParam2 == 5 || spawnParam2 == 7))
/*     */         {
/* 131 */           this.gen2.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */         }
/*     */       
/* 134 */       } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int canTreeSpawn(int tree) {
/* 142 */     float treeEVTMin = (tree != -1) ? (EnumTree.values()[tree]).minEVT : 0.0F;
/* 143 */     float treeEVTMax = (tree != -1) ? (EnumTree.values()[tree]).maxEVT : 0.0F;
/*     */     
/* 145 */     float treeRainMin = (tree != -1) ? (EnumTree.values()[tree]).minRain : 0.0F;
/* 146 */     float treeRainMax = (tree != -1) ? (EnumTree.values()[tree]).maxRain : 0.0F;
/*     */     
/* 148 */     float treeTempMin = (tree != -1) ? (EnumTree.values()[tree]).minTemp : 0.0F;
/* 149 */     float treeTempMax = (tree != -1) ? (EnumTree.values()[tree]).maxTemp : 0.0F;
/*     */     
/* 151 */     int out = 0;
/*     */     
/* 153 */     if (this.temperature >= treeTempMin && this.temperature <= treeTempMax)
/* 154 */       out++; 
/* 155 */     if (this.evt >= treeEVTMin && this.evt <= treeEVTMax)
/* 156 */       out += 2; 
/* 157 */     if (this.rainfall >= treeRainMin && this.rainfall <= treeRainMax) {
/* 158 */       out += 4;
/*     */     }
/* 160 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateJungle(Random random, int chunkX, int chunkZ, World world) {
/* 165 */     boolean completed = false;
/* 166 */     int xCoord = chunkX;
/* 167 */     int yCoord = 145;
/* 168 */     int zCoord = chunkZ;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     int numTrees = 50;
/* 175 */     for (int var2 = 0; var2 < numTrees; var2++) {
/*     */       
/* 177 */       xCoord = chunkX + 8 + random.nextInt(16);
/* 178 */       zCoord = chunkZ + 8 + random.nextInt(16);
/* 179 */       yCoord = world.func_72976_f(xCoord, zCoord);
/*     */ 
/*     */       
/* 182 */       float temperatureAvg = TFC_Climate.getBioTemperature(world, xCoord, zCoord);
/*     */ 
/*     */       
/*     */       try {
/* 186 */         if (this.evt >= EnumTree.KAPOK.minEVT && this.evt <= EnumTree.KAPOK.maxEVT && this.rainfall >= EnumTree.KAPOK.minRain && this.rainfall <= EnumTree.KAPOK.maxRain && temperatureAvg >= EnumTree.KAPOK.minTemp && temperatureAvg <= EnumTree.KAPOK.maxTemp) {
/*     */           WorldGenerator gen0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 193 */           if (random.nextInt(10) == 0) {
/* 194 */             gen0 = TFCBiome.getTreeGen(15, Boolean.valueOf(false));
/* 195 */           } else if (random.nextInt(2) == 0) {
/* 196 */             WorldGenCustomShortTrees worldGenCustomShortTrees = new WorldGenCustomShortTrees(false, 15);
/*     */           } else {
/* 198 */             gen0 = new WorldGenJungleShrub(15);
/*     */           } 
/*     */           
/* 201 */           if (random.nextBoolean()) {
/* 202 */             gen0.func_76487_a(1.0D, 1.0D, 1.0D);
/* 203 */             gen0.func_76484_a(world, random, xCoord, yCoord, zCoord);
/* 204 */             completed = true;
/*     */           } 
/*     */         } 
/*     */         
/* 208 */         if (this.evt >= EnumTree.KOA.minEVT && this.evt <= EnumTree.KOA.maxEVT && this.rainfall >= EnumTree.KOA.minRain && this.rainfall <= EnumTree.KOA.maxRain && temperatureAvg >= EnumTree.KOA.minTemp && temperatureAvg <= EnumTree.KOA.maxTemp)
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 214 */           if (random.nextBoolean()) {
/* 215 */             WorldGenAcaciaKoaTrees worldGenAcaciaKoaTrees = new WorldGenAcaciaKoaTrees(false, 0);
/*     */             
/* 217 */             worldGenAcaciaKoaTrees.func_76487_a(1.0D, 1.0D, 1.0D);
/* 218 */             worldGenAcaciaKoaTrees.func_76484_a(world, random, xCoord, yCoord, zCoord);
/*     */           }
/*     */         
/*     */         }
/* 222 */       } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 227 */     if (completed) {
/*     */       
/* 229 */       WorldGenCustomVines vineGen = new WorldGenCustomVines();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 238 */       for (int l = 0; l < 50; l++) {
/*     */         
/* 240 */         int i1 = chunkX + random.nextInt(16) + 8;
/* 241 */         short short1 = 256;
/* 242 */         int j1 = chunkZ + random.nextInt(16) + 8;
/* 243 */         vineGen.generate2(world, random, i1, short1, j1);
/*     */       } 
/*     */     } 
/* 246 */     return completed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getNearWater(World world, int x, int y, int z) {
/* 251 */     for (int x1 = -4; x1 < 5; x1++) {
/*     */       
/* 253 */       for (int z1 = -4; z1 < 5; z1++) {
/*     */         
/* 255 */         for (int y1 = -2; y1 < 1; y1++) {
/*     */           
/* 257 */           if (world.func_72899_e(x + x1, y + y1, z + z1) && TFC_Core.isWater(world.func_147439_a(x + x1, y + y1, z + z1)))
/* 258 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 262 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenForests.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */