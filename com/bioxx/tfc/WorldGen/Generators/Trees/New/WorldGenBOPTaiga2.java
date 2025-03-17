/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ import net.minecraftforge.common.IPlantable;
/*     */ import net.minecraftforge.common.util.ForgeDirection;
/*     */ 
/*     */ public class WorldGenBOPTaiga2
/*     */   extends WorldGenAbstractTree {
/*     */   private final int minTreeHeight;
/*     */   private final int randomTreeHeight;
/*     */   private final Block wood;
/*     */   private final Block leaves;
/*     */   private final int metaWood;
/*     */   private final int metaLeaves;
/*     */   private final int altNo;
/*     */   
/*     */   public WorldGenBOPTaiga2(int id, Block wood, Block leaves, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight, int altNo, int metaFruit) {
/*  23 */     super(doBlockNotify);
/*  24 */     this.wood = wood;
/*  25 */     this.leaves = leaves;
/*  26 */     this.metaWood = id;
/*  27 */     this.metaLeaves = id;
/*  28 */     this.minTreeHeight = minTreeHeight;
/*  29 */     this.randomTreeHeight = randomTreeHeight;
/*     */     
/*  31 */     this.altNo = altNo;
/*     */   }
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int x, int y, int z) {
/*  35 */     int i1, j1, k1, l = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
/*     */ 
/*     */ 
/*     */     
/*  39 */     switch (this.altNo) {
/*     */       case 1:
/*  41 */         i1 = 2 + random.nextInt(4);
/*  42 */         j1 = l - i1;
/*  43 */         k1 = 2 + random.nextInt(4);
/*     */         break;
/*     */       case 2:
/*  46 */         i1 = 4 + random.nextInt(4);
/*  47 */         j1 = l - i1;
/*  48 */         k1 = 2;
/*     */         break;
/*     */       case 3:
/*  51 */         i1 = 1 - random.nextInt(4);
/*  52 */         j1 = l - i1;
/*  53 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */       case 4:
/*  56 */         i1 = 8 + random.nextInt(4);
/*  57 */         j1 = l - i1;
/*  58 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */       case 5:
/*  61 */         i1 = 2;
/*  62 */         j1 = l - i1;
/*  63 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */       case 6:
/*  66 */         i1 = 2 + random.nextInt(4);
/*  67 */         j1 = l - i1;
/*  68 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */       case 7:
/*  71 */         i1 = 2;
/*  72 */         j1 = l - i1;
/*  73 */         k1 = 3;
/*     */         break;
/*     */       case 8:
/*  76 */         i1 = 2 + random.nextInt(3);
/*  77 */         j1 = l - i1;
/*  78 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */       default:
/*  81 */         i1 = 1 + random.nextInt(2);
/*  82 */         j1 = l - i1;
/*  83 */         k1 = 2 + random.nextInt(2);
/*     */         break;
/*     */     } 
/*  86 */     boolean flag = true;
/*  87 */     if (y >= 1 && y + l + 1 <= 256) {
/*     */ 
/*     */ 
/*     */       
/*  91 */       for (int block1 = y; block1 <= y + 1 + l && flag; block1++) {
/*  92 */         int l3; boolean bool = true;
/*  93 */         if (block1 - y < i1) {
/*  94 */           l3 = 0;
/*     */         } else {
/*  96 */           l3 = k1;
/*     */         } 
/*     */         
/*  99 */         for (int i2 = x - l3; i2 <= x + l3 && flag; i2++) {
/* 100 */           for (int b0 = z - l3; b0 <= z + l3 && flag; b0++) {
/* 101 */             if (block1 >= 0 && block1 < 256) {
/* 102 */               Block k2 = world.func_147439_a(i2, block1, b0);
/* 103 */               if (!k2.isAir((IBlockAccess)world, i2, block1, b0) && !k2.isLeaves((IBlockAccess)world, i2, block1, b0)) {
/* 104 */                 flag = false;
/*     */               }
/*     */             } else {
/* 107 */               flag = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 113 */       if (!flag) {
/* 114 */         return false;
/*     */       }
/* 116 */       Block var24 = world.func_147439_a(x, y - 1, z);
/* 117 */       boolean isSoil = var24.canSustainPlant((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP, (IPlantable)Blocks.field_150345_g);
/* 118 */       if (isSoil && y < 256 - l - 1) {
/* 119 */         var24.onPlantGrow(world, x, y - 1, z, x, y, z);
/* 120 */         int l3 = random.nextInt(2);
/* 121 */         int i2 = 1;
/* 122 */         byte var25 = 0;
/*     */ 
/*     */ 
/*     */         
/*     */         int i4;
/*     */ 
/*     */ 
/*     */         
/* 130 */         for (i4 = 0; i4 <= j1; i4++) {
/* 131 */           int i = y + l - i4;
/*     */           
/* 133 */           for (int fr = x - l3; fr <= x + l3; fr++) {
/* 134 */             int fl = fr - x;
/*     */             
/* 136 */             for (int f1 = z - l3; f1 <= z + l3; f1++) {
/* 137 */               int f2 = f1 - z;
/* 138 */               if ((Math.abs(fl) != l3 || Math.abs(f2) != l3 || l3 <= 0) && world.func_147439_a(fr, i, f1).canBeReplacedByLeaves((IBlockAccess)world, fr, i, f1)) {
/* 139 */                 func_150516_a(world, fr, i, f1, this.leaves, this.metaLeaves);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 144 */           if (l3 >= i2) {
/* 145 */             l3 = var25;
/* 146 */             var25 = 1;
/* 147 */             i2++;
/* 148 */             if (i2 > k1) {
/* 149 */               i2 = k1;
/*     */             }
/*     */           } else {
/* 152 */             l3++;
/*     */           } 
/*     */         } 
/*     */         
/* 156 */         i4 = random.nextInt(3);
/*     */         
/* 158 */         for (int var26 = 0; var26 < l - i4; var26++) {
/* 159 */           Block var27 = world.func_147439_a(x, y + var26, z);
/* 160 */           if (var27.isAir((IBlockAccess)world, x, y + var26, z) || var27.isLeaves((IBlockAccess)world, x, y + var26, z)) {
/* 161 */             func_150516_a(world, x, y + var26, z, this.wood, this.metaWood);
/*     */           }
/*     */         } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 179 */         return true;
/*     */       } 
/* 181 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 185 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\WorldGenBOPTaiga2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */