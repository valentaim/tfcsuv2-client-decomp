/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenGrowCrops;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenWaterPlants;
/*     */ import com.bioxx.tfc.WorldGen.WorldCacheManager;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.storage.WorldInfo;
/*     */ import net.minecraftforge.event.world.ChunkDataEvent;
/*     */ import net.minecraftforge.event.world.ChunkEvent;
/*     */ import net.minecraftforge.event.world.WorldEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkEventHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onLoad(ChunkEvent.Load event) {
/*  35 */     if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null && event.getChunk() != null) {
/*     */       
/*  37 */       ChunkData cd = TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*  38 */       if (cd == null)
/*     */         return; 
/*  40 */       BiomeGenBase biome = event.world.func_72807_a((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*  41 */       int month = TFC_Time.getSeasonAdjustedMonth((event.getChunk()).field_76647_h << 4);
/*  42 */       if (TFC_Time.getYear() > cd.lastSpringGen && month > 1 && month < 6) {
/*     */         
/*  44 */         int chunkX = (event.getChunk()).field_76635_g;
/*  45 */         int chunkZ = (event.getChunk()).field_76647_h;
/*  46 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  48 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  49 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/*  51 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */         
/*  53 */         Random rand = new Random(event.world.func_72905_C() + (((chunkX >> 3) - (chunkZ >> 3)) * (chunkZ >> 3)));
/*     */ 
/*     */         
/*  56 */         if (TFC_Core.isWaterBiome(biome)) {
/*  57 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  58 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*  59 */           if (rand.nextInt(50) == 0) {
/*  60 */             int waterPlantsPerChunk = 10;
/*     */             
/*  62 */             for (int var2 = 0; var2 < waterPlantsPerChunk; var2++) {
/*  63 */               int xCoord = (chunkX << 4) + rand.nextInt(16) + 8;
/*  64 */               int zCoord = (chunkZ << 4) + rand.nextInt(16) + 8;
/*  65 */               int yCoord = event.world.func_72874_g(xCoord, zCoord) - 1;
/*  66 */               if (TFC_Climate.getBioTemperatureHeight(event.world, xCoord, yCoord, zCoord) >= 7.0F) {
/*  67 */                 (new WorldGenWaterPlants(TFCBlocks.waterPlant)).func_76484_a(event.world, rand, xCoord, yCoord, zCoord);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  73 */         int cropid = rand.nextInt(CropManager.getInstance().getTotalCrops());
/*  74 */         CropIndex crop = CropManager.getInstance().getCropFromId(cropid);
/*  75 */         if (event.world.field_73012_v.nextInt(25) == 0 && crop != null)
/*     */         {
/*  77 */           int num = 1 + event.world.field_73012_v.nextInt(5);
/*  78 */           WorldGenGrowCrops cropGen = new WorldGenGrowCrops(cropid);
/*  79 */           int x = (chunkX << 4) + event.world.field_73012_v.nextInt(16) + 8;
/*  80 */           int z = (chunkZ << 4) + event.world.field_73012_v.nextInt(16) + 8;
/*  81 */           cropGen.generate(event.world, event.world.field_73012_v, x, z, num);
/*     */         }
/*     */       
/*  84 */       } else if (TFC_Time.getYear() > cd.lastSpringGen && month >= 6) {
/*     */ 
/*     */         
/*  87 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  89 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  90 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/*  92 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */       }
/*  94 */       else if (TFC_Time.getYear() > cd.lastSpringGen + 1) {
/*     */         
/*  96 */         if (TFC_Core.isWaterBiome(biome)) {
/*     */           
/*  98 */           cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
/*  99 */           cd.fishPop = Math.min(cd.fishPop, 60.0F);
/*     */         } 
/* 101 */         cd.lastSpringGen = TFC_Time.getYear();
/*     */       }
/*     */     
/* 104 */     } else if (TFC_Core.getCDM(event.world) != null && TFC_Climate.getCacheManager(event.world) != null) {
/*     */       
/* 106 */       Chunk chunk = event.getChunk();
/* 107 */       ChunkData data = (new ChunkData(chunk)).createNew(event.world, chunk.field_76635_g, chunk.field_76647_h);
/* 108 */       data.rainfallMap = TFC_Climate.getCacheManager(event.world).loadRainfallLayerGeneratorData(data.rainfallMap, (event.getChunk()).field_76635_g * 16, (event.getChunk()).field_76647_h * 16, 16, 16);
/* 109 */       TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUnload(ChunkEvent.Unload event) {
/* 116 */     if (TFC_Core.getCDM(event.world) != null && TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h) != null) {
/* 117 */       (TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h)).isUnloaded = true;
/*     */     }
/*     */     
/* 120 */     if (event.world.field_72995_K) TFC_Core.getCDM(event.world).removeData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
/*     */   
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onUnloadWorld(WorldEvent.Unload event) {
/* 126 */     TFC_Climate.removeCacheManager(event.world);
/* 127 */     TFC_Core.removeCDM(event.world);
/* 128 */     if (event.world.field_73011_w.field_76574_g == 0) {
/* 129 */       AnvilManager.getInstance().clearRecipes();
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLoadWorld(WorldEvent.Load event) {
/* 135 */     if (event.world.field_73011_w.field_76574_g == 0 && event.world.func_82737_E() < 100L)
/* 136 */       createSpawn(event.world); 
/* 137 */     if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0 && AnvilManager.getInstance().getRecipeList().size() == 0)
/*     */     {
/* 139 */       TFC_Core.setupWorld(event.world);
/*     */     }
/* 141 */     TFC_Climate.worldPair.put(event.world, new WorldCacheManager(event.world));
/* 142 */     TFC_Core.addCDM(event.world);
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onDataLoad(ChunkDataEvent.Load event) {
/* 148 */     if (!event.world.field_72995_K) {
/*     */       
/* 150 */       NBTTagCompound eventTag = event.getData();
/*     */       
/* 152 */       Chunk chunk = event.getChunk();
/* 153 */       if (eventTag.func_74764_b("ChunkData")) {
/*     */         
/* 155 */         NBTTagCompound spawnProtectionTag = eventTag.func_74775_l("ChunkData");
/* 156 */         ChunkData data = new ChunkData(chunk, spawnProtectionTag);
/* 157 */         if (TFC_Core.getCDM(event.world) != null) {
/* 158 */           TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 164 */         NBTTagCompound levelTag = eventTag.func_74775_l("Level");
/* 165 */         ChunkData data = (new ChunkData(chunk)).createNew(event.world, levelTag.func_74762_e("xPos"), levelTag.func_74762_e("zPos"));
/* 166 */         if (TFC_Core.getCDM(event.world) != null) {
/* 167 */           TFC_Core.getCDM(event.world).addData(chunk, data);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onDataSave(ChunkDataEvent.Save event) {
/* 175 */     if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null) {
/*     */       
/* 177 */       NBTTagCompound levelTag = event.getData().func_74775_l("Level");
/* 178 */       int x = levelTag.func_74762_e("xPos");
/* 179 */       int z = levelTag.func_74762_e("zPos");
/*     */       
/* 181 */       ChunkData data = TFC_Core.getCDM(event.world).getData(x, z);
/*     */       
/* 183 */       if (data != null) {
/*     */         
/* 185 */         NBTTagCompound spawnProtectionTag = data.getTag();
/*     */ 
/*     */         
/* 188 */         event.getData().func_74782_a("ChunkData", (NBTBase)spawnProtectionTag);
/* 189 */         if (data.isUnloaded) TFC_Core.getCDM(event.world).removeData(x, z);
/*     */       
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ChunkCoordinates createSpawn(World world) {
/* 197 */     List biomeList = world.func_72959_q().func_76932_a();
/* 198 */     long seed = world.func_72912_H().func_76063_b();
/* 199 */     Random rand = new Random(seed);
/*     */     
/* 201 */     ChunkPosition chunkCoord = null;
/* 202 */     int xOffset = 0;
/* 203 */     int xCoord = 0;
/*     */     
/* 205 */     int zCoord = 10000;
/* 206 */     int startingZ = 5000 + rand.nextInt(10000);
/*     */     
/* 208 */     while (chunkCoord == null) {
/*     */       
/* 210 */       chunkCoord = world.func_72959_q().func_150795_a(xOffset, -startingZ, 64, biomeList, rand);
/* 211 */       if (chunkCoord != null) {
/*     */         
/* 213 */         xCoord = chunkCoord.field_151329_a;
/* 214 */         zCoord = chunkCoord.field_151328_c;
/*     */         
/*     */         continue;
/*     */       } 
/* 218 */       xOffset += 64;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 223 */     int var9 = 0;
/* 224 */     while (!world.field_73011_w.func_76566_a(xCoord, zCoord)) {
/*     */       
/* 226 */       xCoord += rand.nextInt(16) - rand.nextInt(16);
/* 227 */       zCoord += rand.nextInt(16) - rand.nextInt(16);
/* 228 */       var9++;
/* 229 */       if (var9 == 1000) {
/*     */         break;
/*     */       }
/*     */     } 
/* 233 */     WorldInfo info = world.func_72912_H();
/* 234 */     info.func_76081_a(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
/* 235 */     if (!info.func_76066_a().func_74764_b("superseed"))
/* 236 */       info.func_76066_a().func_74772_a("superseed", System.currentTimeMillis()); 
/* 237 */     return new ChunkCoordinates(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\ChunkEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */