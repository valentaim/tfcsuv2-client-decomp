/*    */ package com.bioxx.tfc.WorldGen.Generators;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Climate;
/*    */ import com.bioxx.tfc.Core.Util.CaseInsensitiveHashMap;
/*    */ import com.bioxx.tfc.WorldGen.DataLayer;
/*    */ import com.bioxx.tfc.api.Enums.EnumOreGen;
/*    */ import cpw.mods.fml.common.IWorldGenerator;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.chunk.IChunkProvider;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenOre
/*    */   implements IWorldGenerator
/*    */ {
/*    */   private int chunkX;
/*    */   private int chunkZ;
/*    */   private World worldObj;
/*    */   private Random random;
/* 27 */   public static Map<String, OreSpawnData> oreList = (Map<String, OreSpawnData>)new CaseInsensitiveHashMap();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/* 36 */     chunkX *= 16;
/* 37 */     chunkZ *= 16;
/* 38 */     this.chunkX = chunkX;
/* 39 */     this.chunkZ = chunkZ;
/* 40 */     this.worldObj = world;
/* 41 */     this.random = rand;
/*    */     
/* 43 */     for (Map.Entry<String, OreSpawnData> entry : oreList.entrySet()) {
/*    */       
/* 45 */       String name = entry.getKey();
/* 46 */       OreSpawnData osd = entry.getValue();
/* 47 */       ore(osd.type, osd.block, osd.meta, osd.base, osd.rarity, osd.min, osd.max, osd.rnd, osd.SphereXSize, osd.SphereYSize, osd.SphereZSize, osd.VeinWidth, osd.VeinBaseHeight, osd.VeinDownFactor, osd.AreaNumber, osd.AreaMaxDistance, osd.CellSize, name);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void ore(EnumOreGen type, Block block, int meta, Map<Block, List<Integer>> baseRocks, int rarity, int min, int max, int rnd, int SphereXSize, int SphereYSize, int SphereZSize, int VeinWidth, int VeinBaseHeight, int VeinDownFactor, int AreaNumber, int AreaMaxDistance, int CellSize, String name) {
/* 56 */     createOre(type, block, meta, baseRocks, rarity, rnd, this.worldObj, this.random, this.chunkX, this.chunkZ, min, max, SphereXSize, SphereYSize, SphereZSize, VeinWidth, VeinBaseHeight, VeinDownFactor, AreaNumber, AreaMaxDistance, CellSize, name);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static void createOre(EnumOreGen type, Block block, int j, Map<Block, List<Integer>> layers, int rarity, int rnd, World world, Random rand, int chunkX, int chunkZ, int min, int max, int SphereXSize, int SphereYSize, int SphereZSize, int VeinWidth, int VeinBaseHeight, int VeinDownFactor, int AreaNumber, int AreaMaxDistance, int CellSize, String name) {
/* 64 */     if (world.func_72959_q() instanceof com.bioxx.tfc.WorldGen.TFCWorldChunkManager)
/*    */     {
/* 66 */       for (Block b : layers.keySet()) {
/*    */         
/* 68 */         for (Iterator<Integer> iterator = ((List)layers.get(b)).iterator(); iterator.hasNext(); ) { int metadata = ((Integer)iterator.next()).intValue();
/*    */           
/* 70 */           DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 0);
/* 71 */           DataLayer rockLayer2 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 1);
/* 72 */           DataLayer rockLayer3 = TFC_Climate.getCacheManager(world).getRockLayerAt(chunkX, chunkZ, 2);
/* 73 */           if ((rockLayer1.block == b && (rockLayer1.data2 == metadata || metadata == -1)) || (rockLayer2.block == b && (rockLayer2.data2 == metadata || metadata == -1)) || (rockLayer3.block == b && (rockLayer3.data2 == metadata || metadata == -1)))
/*    */           {
/*    */ 
/*    */ 
/*    */             
/* 78 */             (new WorldGenMinableTFCNew(type, block, j, b, metadata, rarity, rnd, SphereXSize, SphereYSize, SphereZSize, VeinWidth, VeinBaseHeight, VeinDownFactor, AreaNumber, AreaMaxDistance, CellSize, name))
/*    */               
/* 80 */               .generate(world, rand, chunkX, chunkZ, min, max);
/*    */           } }
/*    */       
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */