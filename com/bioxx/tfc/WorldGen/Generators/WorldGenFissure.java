/*     */ package com.bioxx.tfc.WorldGen.Generators;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.Util.BlockMeta;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Enums.TFCDirection;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.Util.ByteCoord;
/*     */ import com.bioxx.tfc.api.Util.CollapseData;
/*     */ import com.bioxx.tfc.api.Util.CollapseList;
/*     */ import cpw.mods.fml.common.IWorldGenerator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ public class WorldGenFissure
/*     */   implements IWorldGenerator
/*     */ {
/*     */   private Random rand;
/*     */   private int poolDepth;
/*  30 */   private int creviceDepth = 1;
/*     */   private Block fillBlock;
/*  32 */   private int depth = 20;
/*  33 */   private int minTunnel = 1;
/*     */   
/*     */   public boolean checkStability = true;
/*     */   private boolean underground;
/*  37 */   private int rarity = 30;
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenFissure(Block b) {
/*  42 */     this.fillBlock = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenFissure(Block b, int minTunnel, boolean s, int rare) {
/*  47 */     this(b);
/*  48 */     this.minTunnel = minTunnel;
/*  49 */     this.checkStability = s;
/*  50 */     this.rarity = rare;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldGenFissure setSeed(int i) {
/*  56 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenFissure setUnderground(boolean i, int d) {
/*  61 */     this.underground = i;
/*  62 */     this.depth = d;
/*  63 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
/*  70 */     this.rand = random;
/*  71 */     chunkX *= 16;
/*  72 */     chunkZ *= 16;
/*     */     
/*  74 */     int startX = chunkX + random.nextInt(16) + 8;
/*  75 */     int startZ = chunkZ + random.nextInt(16) + 8;
/*  76 */     BiomeGenBase biome = world.func_72807_a(startX, startZ);
/*     */     
/*  78 */     if (this.rarity <= 0 || this.rand.nextInt(this.rarity) != 0 || biome == TFCBiome.BEACH || biome == TFCBiome.OCEAN || biome == TFCBiome.GRAVEL_BEACH || biome == TFCBiome.LAKE || biome == TFCBiome.RIVER || biome == TFCBiome.DEEP_OCEAN) {
/*     */       return;
/*     */     }
/*     */     
/*  82 */     int startY = world.func_72825_h(startX, startZ) - 1;
/*  83 */     if (this.underground) {
/*  84 */       startY = this.depth + this.rand.nextInt(60);
/*     */     }
/*  86 */     generate(world, this.rand, startX, startY, startZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public void generate(World world, Random rand, int x, int y, int z) {
/*  91 */     this.creviceDepth = 1;
/*  92 */     if (rand.nextInt(100) < 50)
/*  93 */       this.creviceDepth += 2 + rand.nextInt(8); 
/*  94 */     this.poolDepth = 1 + rand.nextInt(Math.max(this.creviceDepth - 1, 1));
/*     */     
/*  96 */     for (int d = 1; d <= this.poolDepth; d++) {
/*     */       
/*  98 */       if (!world.func_147439_a(x, y - d, z).func_149721_r()) {
/*     */         return;
/*     */       }
/*     */     } 
/* 102 */     Block block = world.func_147439_a(x, y, z);
/*     */     
/* 104 */     if (block != null && block.func_149688_o() == Material.field_151586_h)
/*     */       return; 
/* 106 */     if (this.underground) {
/* 107 */       y -= 20 + rand.nextInt(this.depth);
/*     */     }
/* 109 */     int stability = TFC_Climate.getStability(world, x, z);
/* 110 */     if (this.checkStability && stability == 0)
/*     */       return; 
/* 112 */     if (stability == 1 && this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151586_h)
/* 113 */       this.fillBlock = TFCBlocks.hotWater; 
/* 114 */     if (!TFC_Core.isGround(block)) {
/*     */       return;
/*     */     }
/* 117 */     DataLayer dl = TFC_Climate.getRockLayer(world, x, y, z, 2);
/* 118 */     BlockMeta rockLayer = (this.fillBlock != null) ? new BlockMeta(dl.block, dl.data2) : new BlockMeta(Blocks.field_150350_a, -1);
/* 119 */     if (rockLayer.block == null)
/*     */       return; 
/* 121 */     List<ByteCoord> map = getCollapseMap(world, x, y - this.creviceDepth, z);
/* 122 */     for (ByteCoord b : map) {
/*     */       
/* 124 */       world.func_147468_f(x + b.x, y + b.y, z + b.z);
/* 125 */       for (int i = 1; i <= this.poolDepth; i++) {
/* 126 */         fill(world, x + b.x, y + b.y - i, z + b.z, rockLayer.block, rockLayer.meta, (this.fillBlock != null) ? this.fillBlock : Blocks.field_150350_a);
/*     */       }
/* 128 */       int rx = 0;
/* 129 */       int rz = 0;
/* 130 */       for (int j = 0; j <= this.creviceDepth; j++) {
/*     */         
/* 132 */         carve(world, x + b.x, y + b.y + j, z + b.z, rockLayer.block, rockLayer.meta);
/* 133 */         if (rand.nextInt(3) == 0) {
/*     */           
/* 135 */           rx = -1 + rand.nextInt(3);
/* 136 */           rz = -1 + rand.nextInt(3);
/* 137 */           carve(world, x + b.x + rx, y + b.y + j, z + b.z + rz, rockLayer.block, rockLayer.meta);
/*     */         } 
/*     */       } 
/* 140 */       if (this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151587_i) {
/* 141 */         world.func_147465_d(x + b.x, y + b.y - this.poolDepth - 1, z + b.z, rockLayer.block, rockLayer.meta, 2);
/*     */       }
/*     */     } 
/* 144 */     boolean makeTunnel = (map.size() > 10);
/* 145 */     if (makeTunnel) {
/* 146 */       makeTunnel(rand, world, x, z, y, rockLayer);
/*     */     }
/*     */   }
/*     */   
/*     */   private void carve(World world, int x, int y, int z, Block block, int meta) {
/* 151 */     if (world.func_147439_a(x, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isGround(world.func_147439_a(x, y, z)))
/* 152 */       world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3); 
/* 153 */     if (world.func_147439_a(x - 1, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x - 1, y, z)))
/* 154 */       world.func_147465_d(x - 1, y, z, block, meta, 3); 
/* 155 */     if (world.func_147439_a(x + 1, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x + 1, y, z)))
/* 156 */       world.func_147465_d(x + 1, y, z, block, meta, 3); 
/* 157 */     if (world.func_147439_a(x, y, z - 1).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x, y, z - 1)))
/* 158 */       world.func_147465_d(x, y, z - 1, block, meta, 3); 
/* 159 */     if (world.func_147439_a(x, y, z + 1).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x, y, z + 1))) {
/* 160 */       world.func_147465_d(x, y, z + 1, block, meta, 3);
/*     */     }
/*     */   }
/*     */   
/*     */   private void fill(World world, int x, int y, int z, Block block, int meta, Block fill) {
/* 165 */     world.func_147465_d(x, y, z, fill, 0, 2);
/* 166 */     if (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151579_a)
/* 167 */       world.func_147465_d(x - 1, y, z, block, meta, 2); 
/* 168 */     if (world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151579_a)
/* 169 */       world.func_147465_d(x + 1, y, z, block, meta, 2); 
/* 170 */     if (world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151579_a)
/* 171 */       world.func_147465_d(x, y, z - 1, block, meta, 2); 
/* 172 */     if (world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151579_a)
/* 173 */       world.func_147465_d(x, y, z + 1, block, meta, 2); 
/* 174 */     if (world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151579_a) {
/* 175 */       world.func_147465_d(x, y - 1, z, block, meta, 2);
/*     */     }
/*     */   }
/*     */   
/*     */   private void makeTunnel(Random random, World world, int x, int z, int y, BlockMeta rockLayer) {
/* 180 */     int xCoord = x, yCoord = y - this.poolDepth - 1, zCoord = z;
/* 181 */     float downChance = 75.0F;
/* 182 */     while (yCoord > this.minTunnel) {
/*     */       
/* 184 */       if (world.func_147439_a(xCoord, yCoord, zCoord) == Blocks.field_150357_h)
/*     */         break; 
/* 186 */       if (random.nextFloat() < downChance / 100.0F) {
/*     */         
/* 188 */         yCoord--;
/*     */       }
/*     */       else {
/*     */         
/* 192 */         int dir = random.nextInt(3);
/* 193 */         switch (dir) {
/*     */           case 0:
/* 195 */             xCoord++; break;
/* 196 */           case 1: xCoord--; break;
/* 197 */           case 2: zCoord++; break;
/* 198 */           case 3: zCoord--;
/*     */             break;
/*     */         } 
/*     */       } 
/* 202 */       world.func_147465_d(xCoord, yCoord, zCoord, (this.fillBlock != null) ? this.fillBlock : Blocks.field_150350_a, 0, 2);
/*     */       
/* 204 */       if (this.fillBlock != null && world.func_147439_a(xCoord + 1, yCoord, zCoord).func_149688_o() != this.fillBlock.func_149688_o())
/* 205 */         world.func_147465_d(xCoord + 1, yCoord, zCoord, rockLayer.block, rockLayer.meta, 2); 
/* 206 */       if (this.fillBlock != null && world.func_147439_a(xCoord - 1, yCoord, zCoord).func_149688_o() != this.fillBlock.func_149688_o())
/* 207 */         world.func_147465_d(xCoord - 1, yCoord, zCoord, rockLayer.block, rockLayer.meta, 2); 
/* 208 */       if (this.fillBlock != null && world.func_147439_a(xCoord, yCoord, zCoord + 1).func_149688_o() != this.fillBlock.func_149688_o())
/* 209 */         world.func_147465_d(xCoord, yCoord, zCoord + 1, rockLayer.block, rockLayer.meta, 2); 
/* 210 */       if (this.fillBlock != null && world.func_147439_a(xCoord, yCoord, zCoord - 1).func_149688_o() != this.fillBlock.func_149688_o()) {
/* 211 */         world.func_147465_d(xCoord, yCoord, zCoord - 1, rockLayer.block, rockLayer.meta, 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ByteCoord> getCollapseMap(World world, int i, int j, int k) {
/* 218 */     ArrayList<ByteCoord> map = new ArrayList<>();
/* 219 */     ArrayList<ByteCoord> checkedmap = new ArrayList<>();
/* 220 */     CollapseList<CollapseData> checkQueue = new CollapseList();
/* 221 */     float baseCollapse = 55.0F;
/* 222 */     float incrementChance = 5.0F;
/* 223 */     float incrementChanceDiag = 2.5F;
/*     */     
/* 225 */     DataLayer dl = TFC_Climate.getRockLayer(world, i, j, k, TFC_Core.getRockLayerFromHeight(world, i, j, k));
/* 226 */     DataLayer dl2 = TFC_Climate.getRockLayer(world, i, j, k, 2);
/* 227 */     BlockMeta rockLayer = (this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151587_i) ? new BlockMeta(dl2.block, dl2.data2) : new BlockMeta(dl.block, dl.data2);
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
/* 238 */     checkQueue.add(new CollapseData(new ByteCoord(0, -1, 0), 100.0F, TFCDirection.NULL));
/*     */     
/* 240 */     while (checkQueue.peek() != null) {
/*     */       
/* 242 */       CollapseData block = (CollapseData)checkQueue.peek();
/* 243 */       int worldX = block.coords.x + i;
/* 244 */       int worldY = block.coords.y + j;
/* 245 */       int worldZ = block.coords.z + k;
/* 246 */       int localX = block.coords.x;
/* 247 */       int localY = block.coords.y;
/* 248 */       int localZ = block.coords.z;
/* 249 */       Block id = world.func_147439_a(worldX, worldY, worldZ);
/*     */ 
/*     */       
/* 252 */       if (!checkedmap.contains(block.coords) && TFC_Core.isGround(id) && world.field_73012_v
/* 253 */         .nextFloat() < block.collapseChance / 100.0F) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 258 */         map.add(block.coords);
/*     */         
/* 260 */         if (checkQueue.size() < 500) {
/* 261 */           switch (block.direction) {
/*     */ 
/*     */             
/*     */             case NORTH:
/* 265 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
/* 266 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
/* 267 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTH:
/* 272 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
/* 273 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
/* 274 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case EAST:
/* 279 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
/* 280 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
/* 281 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case WEST:
/* 286 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
/* 287 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
/* 288 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTHEAST:
/* 293 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.SOUTHEAST));
/* 294 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
/* 295 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case SOUTHWEST:
/* 300 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.SOUTHWEST));
/* 301 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
/* 302 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             case NORTHEAST:
/* 307 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.NORTHEAST));
/* 308 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
/* 309 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
/*     */               break;
/*     */ 
/*     */             
/*     */             case NORTHWEST:
/* 314 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.NORTHWEST));
/* 315 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
/* 316 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
/*     */               break;
/*     */ 
/*     */             
/*     */             default:
/* 321 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), 50.0F, TFCDirection.EAST));
/* 322 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), 50.0F, TFCDirection.WEST));
/* 323 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), 52.5F, TFCDirection.NORTHEAST));
/* 324 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), 52.5F, TFCDirection.SOUTHEAST));
/* 325 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), 52.5F, TFCDirection.NORTHWEST));
/* 326 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), 52.5F, TFCDirection.SOUTHWEST));
/* 327 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), 50.0F, TFCDirection.SOUTH));
/* 328 */               checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), 50.0F, TFCDirection.NORTH));
/*     */               break;
/*     */           } 
/*     */         
/*     */         }
/* 333 */       } else if (block.collapseChance < 100.0F) {
/*     */         
/* 335 */         for (int d = 0; d <= this.poolDepth; d++) {
/*     */           
/* 337 */           if (TFC_Core.isGround(world.func_147439_a(worldX, worldY - d, worldZ)))
/* 338 */             world.func_147465_d(worldX, worldY - d, worldZ, rockLayer.block, rockLayer.meta, 2); 
/*     */         } 
/*     */       } 
/* 341 */       checkedmap.add(block.coords);
/* 342 */       checkQueue.removeFirst();
/*     */     } 
/* 344 */     return map;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenFissure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */