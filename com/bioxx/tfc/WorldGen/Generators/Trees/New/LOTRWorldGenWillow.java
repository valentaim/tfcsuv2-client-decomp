/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ public class LOTRWorldGenWillow
/*     */   extends WorldGenAbstractTree {
/*     */   private Block woodBlock;
/*     */   private int woodMeta;
/*     */   private Block leafBlock;
/*     */   private int leafMeta;
/*     */   private int minHeight;
/*     */   private int maxHeight;
/*     */   private boolean needsWater;
/*     */   
/*     */   public LOTRWorldGenWillow(boolean flag, int id) {
/*  27 */     super(flag);
/*  28 */     this.woodBlock = TFCBlocks.logNatural;
/*  29 */     this.woodMeta = id;
/*  30 */     this.leafBlock = TFCBlocks.leaves;
/*  31 */     this.leafMeta = id;
/*  32 */     this.minHeight = 8;
/*  33 */     this.maxHeight = 13;
/*  34 */     this.needsWater = false;
/*     */   }
/*     */   
/*     */   public LOTRWorldGenWillow setNeedsWater() {
/*  38 */     this.needsWater = true;
/*  39 */     return this;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int i, int j, int k) {
/*  43 */     int height = MathHelper.func_76136_a(random, this.minHeight, this.maxHeight);
/*  44 */     boolean flag = true;
/*     */     
/*  46 */     if (j >= 1 && height + 1 <= 256) {
/*  47 */       for (int below = j; below <= j + height + 1; below++) {
/*  48 */         byte isSoil = 1;
/*  49 */         if (below == j) {
/*  50 */           isSoil = 0;
/*     */         }
/*     */         
/*  53 */         if (below >= j + height - 1) {
/*  54 */           isSoil = 2;
/*     */         }
/*     */         
/*  57 */         for (int vineGrows = i - isSoil; vineGrows <= i + isSoil && flag; vineGrows++) {
/*  58 */           for (int m = k - isSoil; m <= k + isSoil && flag; m++) {
/*  59 */             if (below >= 0 && below < 256) {
/*  60 */               if (!isReplaceable(world, vineGrows, below, m)) {
/*  61 */                 flag = false;
/*     */               }
/*     */             } else {
/*  64 */               flag = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*  70 */       return false;
/*     */     } 
/*     */     
/*  73 */     Block var21 = world.func_147439_a(i, j - 1, k);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     if (!TFC_Core.isSoil(world.func_147439_a(i, j - 1, k)) || j >= world.func_72800_K() - height) {
/*  80 */       return false;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     if (this.needsWater) {
/*  89 */       boolean var23 = false;
/*  90 */       byte var24 = 4;
/*     */       
/*  92 */       for (int m = 0; m < var24; m++) {
/*  93 */         int coords = i + MathHelper.func_76136_a(random, -12, 12);
/*  94 */         int i2 = j + MathHelper.func_76136_a(random, -8, 4);
/*  95 */         int k2 = k + MathHelper.func_76136_a(random, -12, 12);
/*  96 */         if (world.func_147439_a(coords, i2, k2).func_149688_o() == Material.field_151586_h) {
/*  97 */           var23 = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 102 */       if (!var23) {
/* 103 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 110 */     var21.onPlantGrow(world, i, j - 1, k, i, j, k);
/* 111 */     ArrayList<ChunkCoordinates> var25 = new ArrayList<>();
/* 112 */     int angle = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     while (angle < 360) {
/* 119 */       angle += 30 + random.nextInt(30);
/* 120 */       float var26 = (float)Math.toRadians(angle);
/* 121 */       float var27 = MathHelper.func_76126_a(var26);
/* 122 */       float var28 = MathHelper.func_76134_b(var26);
/* 123 */       int k2 = j + height - 3 - random.nextInt(3);
/* 124 */       int rootX = 2 + random.nextInt(4);
/* 125 */       int rootY = i;
/* 126 */       int rootZ = k2;
/* 127 */       int roots = k;
/*     */       
/* 129 */       for (int l = 0; l < rootX; l++) {
/* 130 */         if (l > 0 && (l % 4 == 0 || random.nextInt(3) == 0)) {
/* 131 */           rootZ++;
/*     */         }
/*     */         
/* 134 */         if (random.nextFloat() < Math.abs(var28)) {
/* 135 */           rootY = (int)(rootY + Math.signum(var28));
/*     */         }
/*     */         
/* 138 */         if (random.nextFloat() < Math.abs(var27)) {
/* 139 */           roots = (int)(roots + Math.signum(var27));
/*     */         }
/*     */         
/* 142 */         func_150516_a(world, rootY, rootZ, roots, this.woodBlock, this.woodMeta);
/*     */       } 
/*     */       
/* 145 */       spawnLeafCluster(world, random, rootY, rootZ, roots);
/* 146 */       var25.add(new ChunkCoordinates(rootY, rootZ, roots));
/*     */     } 
/*     */     int i1;
/* 149 */     for (i1 = 0; i1 < height; i1++) {
/* 150 */       func_150516_a(world, i, j + i1, k, this.woodBlock, this.woodMeta);
/* 151 */       if (i1 == height - 1) {
/* 152 */         spawnLeafCluster(world, random, i, j + i1, k);
/* 153 */         var25.add(new ChunkCoordinates(i, j + i1, k));
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     for (i1 = i - 1; i1 <= i + 1; i1++) {
/* 158 */       for (int coords = k - 1; coords <= k + 1; coords++) {
/* 159 */         int i2 = i1 - i;
/* 160 */         int k2 = coords - k;
/* 161 */         if (Math.abs(i2) != Math.abs(k2)) {
/* 162 */           int rootX = i1;
/* 163 */           int rootY = j + 1 + random.nextInt(2);
/* 164 */           int rootZ = coords;
/* 165 */           int roots = 0;
/*     */           
/* 167 */           while (world.func_147439_a(rootX, rootY, coords).isReplaceable((IBlockAccess)world, rootX, rootY, rootZ)) {
/* 168 */             func_150516_a(world, rootX, rootY, rootZ, this.woodBlock, this.woodMeta);
/* 169 */             world.func_147439_a(rootX, rootY - 1, rootZ).onPlantGrow(world, rootX, rootY - 1, rootZ, rootX, rootY, rootZ);
/* 170 */             rootY--;
/* 171 */             roots++;
/* 172 */             if (roots > 4 + random.nextInt(3)) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 180 */     Iterator<ChunkCoordinates> var29 = var25.iterator();
/*     */     
/* 182 */     while (var29.hasNext()) {
/* 183 */       ChunkCoordinates var30 = var29.next();
/* 184 */       spawnVineCluster(world, random, var30.field_71574_a, var30.field_71572_b, var30.field_71573_c);
/*     */     } 
/*     */     
/* 187 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void spawnLeafCluster(World world, Random random, int i, int j, int k) {
/* 193 */     byte leafRange = 3;
/* 194 */     int leafRangeSq = leafRange * leafRange;
/* 195 */     int leafRangeSqLess = (int)((leafRange - 0.5D) * (leafRange - 0.5D));
/*     */     
/* 197 */     for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
/* 198 */       for (int j1 = j - leafRange; j1 <= j + leafRange; j1++) {
/* 199 */         for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
/* 200 */           int i2 = i1 - i;
/* 201 */           int j2 = j1 - j;
/* 202 */           int k2 = k1 - k;
/* 203 */           int dist = i2 * i2 + j2 * j2 + k2 * k2;
/* 204 */           int taxicab = Math.abs(i2) + Math.abs(j2) + Math.abs(k2);
/* 205 */           if ((dist < leafRangeSqLess || (dist < leafRangeSq && random.nextInt(3) == 0)) && taxicab <= 4) {
/* 206 */             Block block = world.func_147439_a(i1, j1, k1);
/* 207 */             if (block.isReplaceable((IBlockAccess)world, i1, j1, k1) || block.isLeaves((IBlockAccess)world, i1, j1, k1)) {
/* 208 */               func_150516_a(world, i1, j1, k1, this.leafBlock, this.leafMeta);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void spawnVineCluster(World world, Random random, int i, int j, int k) {
/* 218 */     byte leafRange = 3;
/* 219 */     int leafRangeSq = leafRange * leafRange;
/*     */     
/* 221 */     for (int i1 = i - leafRange; i1 <= i + leafRange; i1++) {
/* 222 */       for (int j1 = j - leafRange; j1 <= j + leafRange; j1++) {
/* 223 */         for (int k1 = k - leafRange; k1 <= k + leafRange; k1++) {
/* 224 */           int i2 = i1 - i;
/* 225 */           int j2 = j1 - j;
/* 226 */           int k2 = k1 - k;
/* 227 */           int dist = i2 * i2 + j2 * j2 + k2 * k2;
/* 228 */           if (dist < leafRangeSq) {
/* 229 */             Block block = world.func_147439_a(i1, j1, k1);
/* 230 */             int meta = world.func_72805_g(i1, j1, k1);
/* 231 */             if (block == this.leafBlock && meta == this.leafMeta) {
/* 232 */               byte vineChance = 2;
/* 233 */               if (random.nextInt(vineChance) == 0 && world.func_147439_a(i1 - 1, j1, k1).isAir((IBlockAccess)world, i1 - 1, j1, k1)) {
/* 234 */                 growVines(world, random, i1 - 1, j1, k1, 8);
/*     */               }
/*     */               
/* 237 */               if (random.nextInt(vineChance) == 0 && world.func_147439_a(i1 + 1, j1, k1).isAir((IBlockAccess)world, i1 + 1, j1, k1)) {
/* 238 */                 growVines(world, random, i1 + 1, j1, k1, 2);
/*     */               }
/*     */               
/* 241 */               if (random.nextInt(vineChance) == 0 && world.func_147439_a(i1, j1, k1 - 1).isAir((IBlockAccess)world, i1, j1, k1 - 1)) {
/* 242 */                 growVines(world, random, i1, j1, k1 - 1, 1);
/*     */               }
/*     */               
/* 245 */               if (random.nextInt(vineChance) == 0 && world.func_147439_a(i1, j1, k1 + 1).isAir((IBlockAccess)world, i1, j1, k1 + 1)) {
/* 246 */                 growVines(world, random, i1, j1, k1 + 1, 4);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void growVines(World world, Random random, int i, int j, int k, int meta) {
/* 257 */     func_150516_a(world, i, j, k, TFCBlocks.vine, meta);
/* 258 */     int vines = 0;
/*     */     
/*     */     while (true) {
/* 261 */       j--;
/* 262 */       if (!world.func_147439_a(i, j, k).isAir((IBlockAccess)world, i, j, k) || vines >= 2 + random.nextInt(4)) {
/*     */         return;
/*     */       }
/*     */       
/* 266 */       func_150516_a(world, i, j, k, TFCBlocks.vine, meta);
/* 267 */       vines++;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\LOTRWorldGenWillow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */