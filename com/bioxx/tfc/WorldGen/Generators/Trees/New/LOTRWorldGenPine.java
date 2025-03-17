/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class LOTRWorldGenPine
/*     */   extends WorldGenAbstractTree
/*     */ {
/*     */   private Block woodBlock;
/*     */   private int woodMeta;
/*     */   private Block leafBlock;
/*     */   private int leafMeta;
/*     */   private int minHeight;
/*     */   private int maxHeight;
/*     */   
/*     */   public LOTRWorldGenPine(boolean flag, int id, int minh, int maxh) {
/*  26 */     super(flag);
/*  27 */     this.woodBlock = TFCBlocks.logNatural;
/*  28 */     this.woodMeta = id;
/*  29 */     this.leafBlock = TFCBlocks.leaves;
/*  30 */     this.leafMeta = id;
/*  31 */     this.minHeight = minh;
/*  32 */     this.maxHeight = maxh;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int i, int j, int k) {
/*  36 */     int height = MathHelper.func_76136_a(random, this.minHeight, this.maxHeight);
/*  37 */     boolean flag = true;
/*     */ 
/*     */     
/*  40 */     if (j >= 1 && height + 1 <= 256) {
/*  41 */       for (int below = j; below <= j + height + 1; below++) {
/*  42 */         byte isSoil = 1;
/*  43 */         if (below == j) {
/*  44 */           isSoil = 0;
/*     */         }
/*     */         
/*  47 */         if (below >= j + height - 1) {
/*  48 */           isSoil = 2;
/*     */         }
/*     */         
/*  51 */         for (int m = i - isSoil; m <= i + isSoil && flag; m++) {
/*  52 */           for (int n = k - isSoil; n <= k + isSoil && flag; n++) {
/*  53 */             if (below >= 0 && below < 256) {
/*  54 */               if (!isReplaceable(world, m, below, n)) {
/*  55 */                 flag = false;
/*     */               }
/*     */             } else {
/*  58 */               flag = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*  64 */       flag = false;
/*     */     } 
/*     */     
/*  67 */     Block var19 = world.func_147439_a(i, j - 1, k);
/*  68 */     boolean var20 = var19.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.field_150345_g);
/*  69 */     if (!var20) {
/*  70 */       flag = false;
/*     */     }
/*     */     
/*  73 */     if (!flag) {
/*  74 */       return false;
/*     */     }
/*  76 */     var19.onPlantGrow(world, i, j - 1, k, i, j, k);
/*  77 */     func_150516_a(world, i, j + height, k, this.leafBlock, this.leafMeta);
/*  78 */     generateLeafLayer(world, random, i, j + height - 1, k, 1);
/*  79 */     int leafHeight = j + height - 3;
/*  80 */     int minLeafHeight = j + (int)(height * 0.5F);
/*     */ 
/*     */     
/*  83 */     while (leafHeight > minLeafHeight) {
/*  84 */       int m = random.nextInt(3);
/*  85 */       if (m == 0) {
/*  86 */         generateLeafLayer(world, random, i, leafHeight, k, 1);
/*  87 */         leafHeight -= 2; continue;
/*  88 */       }  if (m == 1) {
/*  89 */         leafHeight--;
/*  90 */         generateLeafLayer(world, random, i, leafHeight + 1, k, 1);
/*  91 */         generateLeafLayer(world, random, i, leafHeight, k, 2);
/*  92 */         generateLeafLayer(world, random, i, leafHeight - 1, k, 1);
/*  93 */         leafHeight -= 3; continue;
/*  94 */       }  if (m == 2) {
/*  95 */         leafHeight--;
/*  96 */         generateLeafLayer(world, random, i, leafHeight + 1, k, 2);
/*  97 */         generateLeafLayer(world, random, i, leafHeight, k, 3);
/*  98 */         generateLeafLayer(world, random, i, leafHeight - 1, k, 2);
/*  99 */         leafHeight -= 3;
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     generateLeafLayer(world, random, i, leafHeight, k, 1);
/* 104 */     int lastDir = -1;
/*     */     
/* 106 */     for (int j1 = j; j1 < j + height; j1++) {
/* 107 */       func_150516_a(world, i, j1, k, this.woodBlock, this.woodMeta);
/* 108 */       if (j1 >= j + 3 && j1 < minLeafHeight && random.nextInt(3) == 0) {
/* 109 */         int dir = random.nextInt(4);
/* 110 */         if (dir != lastDir) {
/* 111 */           lastDir = dir;
/* 112 */           byte length = 1;
/*     */           
/* 114 */           for (int l = 1; l <= length; l++) {
/* 115 */             int i1 = i + Direction.field_71583_a[dir] * l;
/* 116 */             int k1 = k + Direction.field_71581_b[dir] * l;
/* 117 */             if (!isReplaceable(world, i1, j1, k1)) {
/*     */               break;
/*     */             }
/*     */             
/* 121 */             if (dir != 0 && dir != 2) {
/* 122 */               func_150516_a(world, i1, j1, k1, this.woodBlock, this.woodMeta);
/*     */             } else {
/* 124 */               func_150516_a(world, i1, j1, k1, this.woodBlock, this.woodMeta);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void generateLeafLayer(World world, Random random, int i, int j, int k, int range) {
/* 136 */     for (int i1 = i - range; i1 <= i + range; i1++) {
/* 137 */       for (int k1 = k - range; k1 <= k + range; k1++) {
/* 138 */         int i2 = Math.abs(i1 - i);
/* 139 */         int k2 = Math.abs(k1 - k);
/* 140 */         if (i2 + k2 <= range) {
/* 141 */           Block block = world.func_147439_a(i1, j, k1);
/* 142 */           if (block.isReplaceable((IBlockAccess)world, i1, j, k1) || block.isLeaves((IBlockAccess)world, i1, j, k1))
/* 143 */             func_150516_a(world, i1, j, k1, this.leafBlock, this.leafMeta); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\LOTRWorldGenPine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */