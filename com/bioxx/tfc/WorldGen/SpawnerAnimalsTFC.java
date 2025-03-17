/*     */ package com.bioxx.tfc.WorldGen;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EnumCreatureType;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.WeightedRandom;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SpawnerAnimalsTFC
/*     */ {
/*     */   public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType par0EnumCreatureType, World par1World, int par2, int par3, int par4) {
/*  28 */     if (par0EnumCreatureType.func_75600_c() == Material.field_151586_h)
/*     */     {
/*  30 */       return (par1World.func_147439_a(par2, par3, par4).func_149688_o().func_76224_d() && par1World
/*  31 */         .func_147439_a(par2, par3 - 1, par4).func_149688_o().func_76224_d() && 
/*  32 */         !par1World.func_147439_a(par2, par3 + 1, par4).func_149721_r());
/*     */     }
/*  34 */     if (!World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4))
/*     */     {
/*  36 */       return false;
/*     */     }
/*     */ 
/*     */     
/*  40 */     Block b = par1World.func_147439_a(par2, par3 - 1, par4);
/*  41 */     boolean spawnBlock = (b != null && b.canCreatureSpawn(par0EnumCreatureType, (IBlockAccess)par1World, par2, par3 - 1, par4));
/*  42 */     return (spawnBlock && b != Blocks.field_150357_h && 
/*  43 */       !par1World.func_147439_a(par2, par3, par4).func_149721_r() && 
/*  44 */       !par1World.func_147439_a(par2, par3, par4).func_149688_o().func_76224_d() && 
/*  45 */       !par1World.func_147439_a(par2, par3 + 1, par4).func_149721_r());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void performWorldGenSpawning(World world, TFCBiome biome, int par2, int par3, int par4, int par5, Random par6Random) {
/*  55 */     List<BiomeGenBase.SpawnListEntry> list = TFCChunkProviderGenerate.getCreatureSpawnsByChunk(world, biome, par2, par3);
/*  56 */     if (!list.isEmpty())
/*     */     {
/*  58 */       while (par6Random.nextFloat() < biome.func_76741_f()) {
/*     */         
/*  60 */         BiomeGenBase.SpawnListEntry spawnlistentry = (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(world.field_73012_v, list);
/*  61 */         IEntityLivingData entitylivingdata = null;
/*  62 */         int i1 = spawnlistentry.field_76301_c + par6Random.nextInt(1 + spawnlistentry.field_76299_d - spawnlistentry.field_76301_c);
/*  63 */         int j1 = par2 + par6Random.nextInt(par4);
/*  64 */         int k1 = par3 + par6Random.nextInt(par5);
/*  65 */         int l1 = j1;
/*  66 */         int i2 = k1;
/*     */         
/*  68 */         for (int j2 = 0; j2 < i1; j2++) {
/*     */           
/*  70 */           boolean flag = false;
/*  71 */           for (int k2 = 0; !flag && k2 < 4; k2++) {
/*     */             
/*  73 */             int l2 = world.func_72825_h(j1, k1);
/*  74 */             if (canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world, j1, l2, k1)) {
/*     */               EntityLiving entityliving;
/*     */ 
/*     */               
/*     */               try {
/*  79 */                 entityliving = spawnlistentry.field_76300_b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*     */               }
/*  81 */               catch (Exception exception) {
/*     */                 
/*  83 */                 TerraFirmaCraft.LOG.catching(exception);
/*     */               } 
/*     */               
/*  86 */               if (entityliving instanceof com.bioxx.tfc.Entities.Mobs.EntityFishTFC && 
/*  87 */                 entityliving.func_70681_au().nextInt(60) > TFC_Core.getCDM(world).getFishPop(j1 >> 4, k1 >> 4)) {
/*     */                 return;
/*     */               }
/*     */ 
/*     */               
/*  92 */               float f = j1 + 0.5F;
/*  93 */               float f1 = l2;
/*  94 */               float f2 = k1 + 0.5F;
/*  95 */               entityliving.func_70012_b(f, f1, f2, par6Random.nextFloat() * 360.0F, 0.0F);
/*  96 */               world.func_72838_d((Entity)entityliving);
/*  97 */               entitylivingdata = entityliving.func_110161_a(entitylivingdata);
/*  98 */               flag = true;
/*     */             } 
/*     */             
/* 101 */             j1 += par6Random.nextInt(5) - par6Random.nextInt(5);
/* 102 */             k1 += par6Random.nextInt(5) - par6Random.nextInt(5);
/* 103 */             for (; j1 < par2 || j1 >= par2 + par4 || k1 < par3 || k1 >= par3 + par4; 
/* 104 */               k1 = i2 + par6Random.nextInt(5) - par6Random.nextInt(5))
/*     */             {
/* 106 */               j1 = l1 + par6Random.nextInt(5) - par6Random.nextInt(5);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\SpawnerAnimalsTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */