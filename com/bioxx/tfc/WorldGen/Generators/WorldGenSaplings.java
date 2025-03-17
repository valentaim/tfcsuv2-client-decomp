/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.Enums.EnumTree;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import fof.tfcsu.tfcsu;
/*     */ import java.util.Random;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenSaplings
/*     */ {
/*     */   public void generate(World world, Random random, int xCoord, int yCoord, int zCoord) {
/*  18 */     if (tfcsu.instance.getPluginAccess().isTownBlock(xCoord, zCoord))
/*     */       return; 
/*  20 */     boolean hasSpaceToGrow = true;
/*     */     
/*  22 */     int treeType0 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 0);
/*  23 */     int treeType1 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 1);
/*  24 */     int treeType2 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 2);
/*     */     
/*  26 */     for (int y = -3; y < 6 && hasSpaceToGrow; y++) {
/*     */       
/*  28 */       for (int x = -7; x < 8 && hasSpaceToGrow; x++) {
/*     */         
/*  30 */         for (int z = -7; z < 8 && hasSpaceToGrow; z++) {
/*     */           
/*  32 */           if (world.func_147439_a(xCoord + x, yCoord + y, zCoord + z) == TFCBlocks.logNatural || world
/*  33 */             .func_147439_a(xCoord + x, yCoord + y, zCoord + z) == TFCBlocks.sapling)
/*     */           {
/*  35 */             hasSpaceToGrow = false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     if (hasSpaceToGrow) {
/*     */       
/*  43 */       float rainfall = TFC_Climate.getRainfall(world, xCoord, yCoord, zCoord);
/*  44 */       float temperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
/*  45 */       float evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(xCoord, zCoord)).floatdata1;
/*     */       
/*  47 */       if (treeType0 < 0 || treeType0 > 15)
/*  48 */         treeType0 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 2); 
/*  49 */       if (treeType1 < 0 || treeType1 > 15)
/*  50 */         treeType1 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 0); 
/*  51 */       if (treeType2 < 0 || treeType2 > 15) {
/*  52 */         treeType2 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 1);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  58 */       if (getNearWater(world, xCoord, yCoord, zCoord)) {
/*     */         
/*  60 */         rainfall *= 2.0F;
/*  61 */         evt /= 2.0F;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  67 */       if (zCoord > 14500 || zCoord < -14500) {
/*  68 */         treeType2 = 8;
/*     */       }
/*     */       
/*  71 */       boolean canSpawnTemp0 = false;
/*  72 */       boolean canSpawnTemp1 = false;
/*  73 */       boolean canSpawnTemp2 = false;
/*  74 */       boolean canSpawnRain0 = false;
/*  75 */       boolean canSpawnRain1 = false;
/*  76 */       boolean canSpawnRain2 = false;
/*  77 */       boolean canSpawnEVT0 = false;
/*  78 */       boolean canSpawnEVT1 = false;
/*  79 */       boolean canSpawnEVT2 = false;
/*     */       
/*  81 */       if (treeType0 != -1) {
/*     */         
/*  83 */         float tree0EVTMin = (EnumTree.values()[treeType0]).minEVT;
/*  84 */         float tree0EVTMax = (EnumTree.values()[treeType0]).maxEVT;
/*  85 */         float tree0RainMin = (EnumTree.values()[treeType0]).minRain;
/*  86 */         float tree0RainMax = (EnumTree.values()[treeType0]).maxRain;
/*  87 */         float tree0TempMin = (EnumTree.values()[treeType0]).minTemp;
/*  88 */         float tree0TempMax = (EnumTree.values()[treeType0]).maxTemp;
/*     */         
/*  90 */         canSpawnTemp0 = (temperature >= tree0TempMin && temperature <= tree0TempMax);
/*  91 */         canSpawnRain0 = (rainfall >= tree0RainMin && rainfall <= tree0RainMax);
/*  92 */         canSpawnEVT0 = (evt >= tree0EVTMin && evt <= tree0EVTMax);
/*     */       } 
/*     */       
/*  95 */       if (treeType1 != -1) {
/*     */         
/*  97 */         float tree1EVTMin = (EnumTree.values()[treeType1]).minEVT;
/*  98 */         float tree1EVTMax = (EnumTree.values()[treeType1]).maxEVT;
/*  99 */         float tree1RainMin = (EnumTree.values()[treeType1]).minRain;
/* 100 */         float tree1RainMax = (EnumTree.values()[treeType1]).maxRain;
/* 101 */         float tree1TempMin = (EnumTree.values()[treeType1]).minTemp;
/* 102 */         float tree1TempMax = (EnumTree.values()[treeType1]).maxTemp;
/*     */         
/* 104 */         canSpawnTemp1 = (temperature >= tree1TempMin && temperature <= tree1TempMax);
/* 105 */         canSpawnRain0 = (rainfall >= tree1RainMin && rainfall <= tree1RainMax);
/* 106 */         canSpawnEVT0 = (evt >= tree1EVTMin && evt <= tree1EVTMax);
/*     */       } 
/*     */       
/* 109 */       if (treeType2 != -1) {
/*     */         
/* 111 */         float tree2EVTMin = (EnumTree.values()[treeType2]).minEVT;
/* 112 */         float tree2EVTMax = (EnumTree.values()[treeType2]).maxEVT;
/* 113 */         float tree2RainMin = (EnumTree.values()[treeType2]).minRain;
/* 114 */         float tree2RainMax = (EnumTree.values()[treeType2]).maxRain;
/* 115 */         float tree2TempMin = (EnumTree.values()[treeType2]).minTemp;
/* 116 */         float tree2TempMax = (EnumTree.values()[treeType2]).maxTemp;
/*     */         
/* 118 */         canSpawnTemp2 = (temperature >= tree2TempMin && temperature <= tree2TempMax);
/* 119 */         canSpawnRain0 = (rainfall >= tree2RainMin && rainfall <= tree2RainMax);
/* 120 */         canSpawnEVT0 = (evt >= tree2EVTMin && evt <= tree2EVTMax);
/*     */       } 
/*     */ 
/*     */       
/* 124 */       if (canSpawnTemp0 || canSpawnTemp1 || canSpawnTemp2) {
/*     */ 
/*     */         
/* 127 */         if (!canSpawnEVT0 && !canSpawnEVT1 && !canSpawnEVT2)
/*     */         {
/*     */           
/* 130 */           if (random.nextInt(10) > 0) {
/*     */             return;
/*     */           }
/*     */         }
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 139 */       int randomNumber = random.nextInt(100);
/* 140 */       if (randomNumber < 40) {
/*     */         
/* 142 */         if (canSpawnTemp0 && canSpawnRain0) {
/* 143 */           world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType0, 2);
/*     */         }
/* 145 */       } else if (randomNumber < 70) {
/*     */         
/* 147 */         if (canSpawnTemp1 && canSpawnRain1) {
/* 148 */           world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType1, 2);
/*     */         }
/* 150 */       } else if (randomNumber < 100) {
/*     */         
/* 152 */         if (canSpawnTemp2 && canSpawnRain2) {
/* 153 */           world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType2, 2);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean getNearWater(World world, int x, int y, int z) {
/* 160 */     for (int x1 = -4; x1 < 5; x1++) {
/*     */       
/* 162 */       for (int z1 = -4; z1 < 5; z1++) {
/*     */         
/* 164 */         for (int y1 = -2; y1 < 1; y1++) {
/*     */           
/* 166 */           if (world.func_72899_e(x + x1, y + y1, z + z1) && TFC_Core.isWater(world.func_147439_a(x + x1, y + y1, z + z1)))
/* 167 */             return true; 
/*     */         } 
/*     */       } 
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenSaplings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */