/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Terrain.BlockOre;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.TileEntities.TEWorldItem;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenLooseRocks
/*     */   implements IWorldGenerator
/*     */ {
/*     */   public boolean generateRocks(World world, Random random, int i, int j, int k) {
/*  30 */     if ((world.func_147437_c(i, j + 1, k) || world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE || world.func_147439_a(i, j + 1, k) == TFCBlocks.tallGrass) && (world
/*  31 */       .func_147439_a(i, j, k).func_149688_o() == Material.field_151577_b || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151576_e) && world.func_147439_a(i, j, k).func_149662_c())
/*     */     {
/*  33 */       if (world.func_147465_d(i, j + 1, k, TFCBlocks.worldItem, 0, 2)) {
/*     */         
/*  35 */         TEWorldItem te = (TEWorldItem)world.func_147438_o(i, j + 1, k);
/*  36 */         ItemStack sample = getCoreSample(world, i, j, k);
/*  37 */         if (world.field_73012_v.nextInt(3) == 0 && sample != null) {
/*     */           
/*  39 */           te.storage[0] = sample;
/*     */         }
/*     */         else {
/*     */           
/*  43 */           DataLayer dl = TFC_Climate.getRockLayer(world, i, j, k, 0);
/*     */           
/*  45 */           te.storage[0] = new ItemStack(TFCItems.looseRock, 1, dl.data1);
/*     */         } 
/*     */       } 
/*     */     }
/*  49 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private ItemStack getCoreSample(World world, int xCoord, int yCoord, int zCoord) {
/*  54 */     ArrayList<Item> coreSample = new ArrayList<>();
/*  55 */     ArrayList<ItemStack> coreSampleStacks = new ArrayList<>();
/*  56 */     for (int x = -15; x < 16; x++) {
/*     */       
/*  58 */       for (int z = -15; z < 16; z++) {
/*     */         
/*  60 */         for (int y = yCoord; y > yCoord - 35; y--) {
/*     */           
/*  62 */           if (world.func_72899_e(xCoord + x, y, zCoord + z) && world.func_147439_a(xCoord + x, y, zCoord + z) == TFCBlocks.ore) {
/*     */             
/*  64 */             int m = world.func_72805_g(xCoord + x, y, zCoord + z);
/*  65 */             if (!coreSample.contains(BlockOre.getDroppedItem(m)))
/*     */             {
/*  67 */               if (m != 14 && m != 15) {
/*     */                 
/*  69 */                 coreSample.add(BlockOre.getDroppedItem(m));
/*  70 */                 coreSampleStacks.add(new ItemStack(BlockOre.getDroppedItem(m), 1, m));
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  77 */     if (!coreSampleStacks.isEmpty())
/*  78 */       return coreSampleStacks.get(world.field_73012_v.nextInt(coreSampleStacks.size())); 
/*  79 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  86 */     chunkX *= 16;
/*  87 */     chunkZ *= 16;
/*     */     
/*  89 */     if (world.func_72807_a(chunkX, chunkZ) instanceof TFCBiome) {
/*     */       
/*  91 */       TFCBiome biome = (TFCBiome)world.func_72807_a(chunkX, chunkZ);
/*  92 */       if (biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN) {
/*     */         return;
/*     */       }
/*     */     } 
/*     */     
/*  97 */     for (int itemCount = 0; itemCount < 8; itemCount++) {
/*     */       
/*  99 */       int xCoord = chunkX + random.nextInt(16) + 8;
/* 100 */       int zCoord = chunkZ + random.nextInt(16) + 8;
/* 101 */       generateRocks(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord);
/*     */     } 
/*     */ 
/*     */     
/* 105 */     for (int stickCount = 0; stickCount < 5; stickCount++) {
/*     */       
/* 107 */       int xCoord = chunkX + random.nextInt(16) + 8;
/* 108 */       int zCoord = chunkZ + random.nextInt(16) + 8;
/* 109 */       generateSticks(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateSticks(World world, Random random, int i, int j, int k) {
/* 115 */     if ((world.func_147437_c(i, j + 1, k) || world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE || world.func_147439_a(i, j + 1, k) == TFCBlocks.tallGrass) && (world
/* 116 */       .func_147439_a(i, j, k).func_149688_o() == Material.field_151577_b || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151576_e || world
/* 117 */       .func_147439_a(i, j, k).func_149688_o() == Material.field_151595_p || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151578_c) && world.func_147439_a(i, j, k).func_149662_c())
/*     */     {
/* 119 */       if (world.func_72807_a(i, k) instanceof TFCBiome) {
/*     */         
/* 121 */         TFCBiome biome = (TFCBiome)world.func_72807_a(i, k);
/* 122 */         if ((biome == TFCBiome.DEEP_OCEAN || biome == TFCBiome.BEACH || biome == TFCBiome.GRAVEL_BEACH || biome == TFCBiome.OCEAN || biome == TFCBiome.RIVER || isNearTree(world, i, j, k)) && world
/* 123 */           .func_147465_d(i, j + 1, k, TFCBlocks.worldItem, 0, 2)) {
/*     */           
/* 125 */           TEWorldItem te = (TEWorldItem)world.func_147438_o(i, j + 1, k);
/*     */           
/* 127 */           te.storage[0] = new ItemStack(TFCItems.stick, 1);
/*     */         } 
/*     */       } 
/*     */     }
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isNearTree(World world, int i, int j, int k) {
/* 136 */     if (world.func_147439_a(i, j + 3, k).func_149688_o() == Material.field_151584_j || world
/* 137 */       .func_147439_a(i + 5, j + 3, k).func_149688_o() == Material.field_151584_j || world
/* 138 */       .func_147439_a(i - 5, j + 3, k).func_149688_o() == Material.field_151584_j || world
/* 139 */       .func_147439_a(i, j + 3, k + 5).func_149688_o() == Material.field_151584_j || world
/* 140 */       .func_147439_a(i, j + 3, k - 5).func_149688_o() == Material.field_151584_j) {
/* 141 */       return true;
/*     */     }
/* 143 */     return (world.func_147439_a(i, j + 6, k).func_149688_o() == Material.field_151584_j || world
/* 144 */       .func_147439_a(i + 5, j + 6, k).func_149688_o() == Material.field_151584_j || world
/* 145 */       .func_147439_a(i - 5, j + 6, k).func_149688_o() == Material.field_151584_j || world
/* 146 */       .func_147439_a(i, j + 6, k + 5).func_149688_o() == Material.field_151584_j || world
/* 147 */       .func_147439_a(i, j + 6, k - 5).func_149688_o() == Material.field_151584_j);
/*     */   }
/*     */   
/*     */   public static boolean rocksNearby(World world, int i, int j, int k) {
/* 151 */     return (world.func_147439_a(i + 1, j + 1, k) != TFCBlocks.worldItem || world.func_147439_a(i + 1, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k - 1) != TFCBlocks.worldItem || world.func_147439_a(i, j + 1, k - 1) != TFCBlocks.worldItem || world.func_147439_a(i + 1, j + 1, k) != TFCBlocks.worldItem);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenLooseRocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */