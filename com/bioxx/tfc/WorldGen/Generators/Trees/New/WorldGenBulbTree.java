/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ public class WorldGenBulbTree
/*     */   extends WorldGenAbstractTree {
/*     */   private final int minTreeHeight;
/*     */   private final int randomTreeHeight;
/*     */   private final boolean vinesGrow;
/*     */   private final Block wood;
/*     */   private final Block leaves;
/*     */   private final int metaWood;
/*     */   private final int metaLeaves;
/*     */   
/*     */   public WorldGenBulbTree(int id, Block wood, Block leaves, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight, boolean vinesGrow) {
/*  24 */     super(doBlockNotify);
/*  25 */     this.wood = wood;
/*  26 */     this.leaves = leaves;
/*  27 */     this.metaWood = id;
/*  28 */     this.metaLeaves = id;
/*  29 */     this.minTreeHeight = minTreeHeight;
/*  30 */     this.randomTreeHeight = randomTreeHeight;
/*  31 */     this.vinesGrow = vinesGrow;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int x, int y, int z) {
/*  35 */     int l = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
/*  36 */     if (!TFC_Core.isSoil(world.func_147439_a(x, y - 1, z)) || y >= world.func_72800_K() - l - 1) return false; 
/*  37 */     if (world.func_147439_a(x, y, z) != Blocks.field_150350_a) return false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (y < 256 - l - 1) {
/*  46 */       byte b0 = 3;
/*  47 */       byte b1 = 0;
/*     */ 
/*     */       
/*     */       int k1;
/*     */ 
/*     */       
/*  53 */       for (k1 = y - b0 + l; k1 <= y + l; k1++) {
/*  54 */         int i3 = k1 - y + l;
/*  55 */         int l1 = b1 + 1 - i3 / 3;
/*     */         
/*  57 */         for (int i2 = x - l1; i2 <= x + l1; i2++) {
/*  58 */           int j2 = i2 - x;
/*     */           
/*  60 */           for (int k2 = z - l1; k2 <= z + l1; k2++) {
/*  61 */             int l2 = k2 - z;
/*  62 */             if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || (random.nextInt(2) != 0 && i3 != 0)) {
/*  63 */               Block block1 = world.func_147439_a(i2, k1, k2);
/*  64 */               if (block1.isAir((IBlockAccess)world, i2, k1, k2) || block1.isLeaves((IBlockAccess)world, i2, k1, k2)) {
/*  65 */                 func_150516_a(world, i2, k1, k2, this.leaves, this.metaLeaves);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  72 */       for (k1 = 0; k1 < l; k1++) {
/*  73 */         Block block = world.func_147439_a(x, y + k1, z);
/*  74 */         if (block.isAir((IBlockAccess)world, x, y + k1, z) || block.isLeaves((IBlockAccess)world, x, y + k1, z)) {
/*  75 */           func_150516_a(world, x, y + k1, z, this.wood, this.metaWood);
/*  76 */           func_150516_a(world, x - 1, y + l - 4, z, this.leaves, this.metaLeaves);
/*  77 */           func_150516_a(world, x + 1, y + l - 4, z, this.leaves, this.metaLeaves);
/*  78 */           func_150516_a(world, x, y + l - 4, z - 1, this.leaves, this.metaLeaves);
/*  79 */           func_150516_a(world, x, y + l - 4, z + 1, this.leaves, this.metaLeaves);
/*  80 */           func_150516_a(world, x - 1, y + l - 5, z + 1, this.leaves, this.metaLeaves);
/*  81 */           func_150516_a(world, x - 1, y + l - 5, z - 1, this.leaves, this.metaLeaves);
/*  82 */           func_150516_a(world, x + 1, y + l - 5, z + 1, this.leaves, this.metaLeaves);
/*  83 */           func_150516_a(world, x + 1, y + l - 5, z - 1, this.leaves, this.metaLeaves);
/*  84 */           func_150516_a(world, x - 1, y + l - 5, z, this.leaves, this.metaLeaves);
/*  85 */           func_150516_a(world, x + 1, y + l - 5, z, this.leaves, this.metaLeaves);
/*  86 */           func_150516_a(world, x, y + l - 5, z - 1, this.leaves, this.metaLeaves);
/*  87 */           func_150516_a(world, x, y + l - 5, z + 1, this.leaves, this.metaLeaves);
/*  88 */           func_150516_a(world, x - 1, y + l - 6, z, this.leaves, this.metaLeaves);
/*  89 */           func_150516_a(world, x + 1, y + l - 6, z, this.leaves, this.metaLeaves);
/*  90 */           func_150516_a(world, x, y + l - 6, z - 1, this.leaves, this.metaLeaves);
/*  91 */           func_150516_a(world, x, y + l, z + 1, this.leaves, this.metaLeaves);
/*  92 */           func_150516_a(world, x - 1, y + l - 7, z + 1, this.leaves, this.metaLeaves);
/*  93 */           func_150516_a(world, x - 1, y + l - 7, z - 1, this.leaves, this.metaLeaves);
/*  94 */           func_150516_a(world, x + 1, y + l - 7, z + 1, this.leaves, this.metaLeaves);
/*  95 */           func_150516_a(world, x + 1, y + l - 7, z - 1, this.leaves, this.metaLeaves);
/*  96 */           func_150516_a(world, x - 1, y + l - 7, z, this.leaves, this.metaLeaves);
/*  97 */           func_150516_a(world, x + 1, y + l - 7, z, this.leaves, this.metaLeaves);
/*  98 */           func_150516_a(world, x, y + l - 7, z - 1, this.leaves, this.metaLeaves);
/*  99 */           func_150516_a(world, x, y + l - 7, z + 1, this.leaves, this.metaLeaves);
/* 100 */           func_150516_a(world, x - 1, y + l - 8, z, this.leaves, this.metaLeaves);
/* 101 */           func_150516_a(world, x + 1, y + l - 8, z, this.leaves, this.metaLeaves);
/* 102 */           func_150516_a(world, x, y + l - 8, z - 1, this.leaves, this.metaLeaves);
/* 103 */           func_150516_a(world, x, y + l - 8, z + 1, this.leaves, this.metaLeaves);
/* 104 */           if (this.vinesGrow && k1 > 0) {
/* 105 */             if (random.nextInt(3) > 0 && world.func_147437_c(x - 1, y + k1, z)) {
/* 106 */               func_150516_a(world, x - 1, y + k1, z, TFCBlocks.vine, 8);
/*     */             }
/*     */             
/* 109 */             if (random.nextInt(3) > 0 && world.func_147437_c(x + 1, y + k1, z)) {
/* 110 */               func_150516_a(world, x + 1, y + k1, z, TFCBlocks.vine, 2);
/*     */             }
/*     */             
/* 113 */             if (random.nextInt(3) > 0 && world.func_147437_c(x, y + k1, z - 1)) {
/* 114 */               func_150516_a(world, x, y + k1, z - 1, TFCBlocks.vine, 1);
/*     */             }
/*     */             
/* 117 */             if (random.nextInt(3) > 0 && world.func_147437_c(x, y + k1, z + 1)) {
/* 118 */               func_150516_a(world, x, y + k1, z + 1, TFCBlocks.vine, 4);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 124 */       if (this.vinesGrow) {
/* 125 */         for (k1 = y - 3 + l; k1 <= y + l; k1++) {
/* 126 */           int i3 = k1 - y + l;
/* 127 */           int l1 = 2 - i3 / 2;
/*     */           
/* 129 */           for (int i2 = x - l1; i2 <= x + l1; i2++) {
/* 130 */             for (int j2 = z - l1; j2 <= z + l1; j2++) {
/* 131 */               if (world.func_147439_a(i2, k1, j2).isLeaves((IBlockAccess)world, i2, k1, j2)) {
/* 132 */                 if (random.nextInt(4) == 0 && world.func_147439_a(i2 - 1, k1, j2).isAir((IBlockAccess)world, i2 - 1, k1, j2)) {
/* 133 */                   growVines(world, i2 - 1, k1, j2, 8);
/*     */                 }
/*     */                 
/* 136 */                 if (random.nextInt(4) == 0 && world.func_147439_a(i2 + 1, k1, j2).isAir((IBlockAccess)world, i2 + 1, k1, j2)) {
/* 137 */                   growVines(world, i2 + 1, k1, j2, 2);
/*     */                 }
/*     */                 
/* 140 */                 if (random.nextInt(4) == 0 && world.func_147439_a(i2, k1, j2 - 1).isAir((IBlockAccess)world, i2, k1, j2 - 1)) {
/* 141 */                   growVines(world, i2, k1, j2 - 1, 1);
/*     */                 }
/*     */                 
/* 144 */                 if (random.nextInt(4) == 0 && world.func_147439_a(i2, k1, j2 + 1).isAir((IBlockAccess)world, i2, k1, j2 + 1)) {
/* 145 */                   growVines(world, i2, k1, j2 + 1, 4);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 152 */         if (random.nextInt(5) == 0 && l > 5) {
/* 153 */           for (k1 = 0; k1 < 2; k1++) {
/* 154 */             for (int i3 = 0; i3 < 4; i3++) {
/* 155 */               if (random.nextInt(4 - k1) == 0) {
/* 156 */                 int l1 = random.nextInt(3);
/* 157 */                 func_150516_a(world, x + Direction.field_71583_a[Direction.field_71580_e[i3]], y + l - 5 + k1, z + Direction.field_71581_b[Direction.field_71580_e[i3]], Blocks.field_150375_by, l1 << 2 | i3);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 164 */       return true;
/*     */     } 
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void growVines(World world, int x, int y, int z, int flag) {
/* 172 */     func_150516_a(world, x, y, z, TFCBlocks.vine, flag);
/* 173 */     int i1 = 4;
/*     */     
/*     */     while (true) {
/* 176 */       y--;
/* 177 */       if (world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z) || i1 <= 0) {
/*     */         return;
/*     */       }
/*     */       
/* 181 */       func_150516_a(world, x, y, z, TFCBlocks.vine, flag);
/* 182 */       i1--;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\WorldGenBulbTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */