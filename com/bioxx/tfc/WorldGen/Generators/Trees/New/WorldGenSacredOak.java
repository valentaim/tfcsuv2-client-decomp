/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/*     */ 
/*     */ 
/*     */ public class WorldGenSacredOak
/*     */   extends WorldGenAbstractTree
/*     */ {
/*  17 */   static final byte[] otherCoordPairs = new byte[] { 2, 0, 0, 1, 2, 1 };
/*  18 */   Random rand = new Random();
/*     */   World worldObj;
/*  20 */   int[] basePos = new int[] { 0, 0, 0 };
/*     */   
/*     */   private final int heightLimit;
/*     */   int height;
/*  24 */   private final double heightAttenuation = 0.45D;
/*  25 */   private final double branchSlope = 0.45D;
/*     */   private final double scaleWidth;
/*  27 */   private final double leafDensity = 1.0D;
/*  28 */   private int trunkSize = 1;
/*     */   
/*     */   private final int leafDistanceLimit;
/*     */   
/*     */   int[][] leafNodes;
/*     */   private final int meta;
/*     */   
/*     */   public WorldGenSacredOak(boolean par1, int id, double scale, int leaf, int heightl, int hr) {
/*  36 */     super(par1);
/*  37 */     this.scaleWidth = scale;
/*  38 */     this.leafDistanceLimit = leaf;
/*  39 */     this.heightLimit = vary(new Random(), heightl, hr);
/*  40 */     if (this.heightLimit > 25) this.trunkSize = 2; 
/*  41 */     this.meta = id;
/*     */   }
/*     */   
/*     */   public int vary(Random rand, int min, int dist) {
/*  45 */     return rand.nextInt(dist) + min;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  50 */     if (!TFC_Core.isSoil(par1World.func_147439_a(par3, par4 - 1, par5))) return false; 
/*  51 */     if (!valid(par1World, par3, par4, par5)) par4--;
/*     */     
/*  53 */     this.worldObj = par1World;
/*  54 */     long var6 = par2Random.nextLong();
/*  55 */     this.rand.setSeed(var6);
/*  56 */     this.basePos[0] = par3;
/*  57 */     this.basePos[1] = par4;
/*  58 */     this.basePos[2] = par5;
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
/*  70 */     generateLeafNodeList();
/*     */     
/*  72 */     generateTrunk();
/*  73 */     generateLeafNodeBases();
/*     */ 
/*     */     
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void generateLeafNodeList() {
/*  82 */     getClass(); this.height = (int)(this.heightLimit * 0.45D);
/*  83 */     if (this.height >= this.heightLimit) {
/*  84 */       this.height = this.heightLimit - 1;
/*     */     }
/*     */     
/*  87 */     getClass(); int var1 = (int)(1.382D + Math.pow(1.0D * this.heightLimit / 13.0D, 2.0D));
/*  88 */     if (var1 < 1) {
/*  89 */       var1 = 1;
/*     */     }
/*     */     
/*  92 */     int[][] var2 = new int[var1 * this.heightLimit][4];
/*  93 */     int var3 = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
/*  94 */     int var4 = 1;
/*  95 */     int var5 = this.basePos[1] + this.height;
/*  96 */     int var6 = var3 - this.basePos[1];
/*  97 */     var2[0][0] = this.basePos[0];
/*  98 */     var2[0][1] = var3;
/*  99 */     var2[0][2] = this.basePos[2];
/* 100 */     var2[0][3] = var5;
/* 101 */     var3--;
/*     */ 
/*     */     
/* 104 */     while (var6 >= 0) {
/* 105 */       int var7 = 0;
/* 106 */       float var8 = layerSize(var6);
/* 107 */       if (var8 < 0.0F) {
/* 108 */         var3--;
/* 109 */         var6--; continue;
/*     */       } 
/* 111 */       for (double var9 = 0.5D; var7 < var1; var7++) {
/* 112 */         double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
/* 113 */         double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
/* 114 */         int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
/* 115 */         int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
/* 116 */         int[] var17 = { var15, var3, var16 };
/* 117 */         int[] var18 = { var15, var3 + this.leafDistanceLimit, var16 };
/* 118 */         if (checkBlockLine(var17, var18) == -1) {
/* 119 */           int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
/* 120 */           double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
/* 121 */           getClass(); double var22 = var20 * 0.45D;
/* 122 */           if (var17[1] - var22 > var5) {
/* 123 */             var19[1] = var5;
/*     */           } else {
/* 125 */             var19[1] = (int)(var17[1] - var22);
/*     */           } 
/*     */           
/* 128 */           if (checkBlockLine(var19, var17) == -1) {
/* 129 */             var2[var4][0] = var15;
/* 130 */             var2[var4][1] = var3;
/* 131 */             var2[var4][2] = var16;
/* 132 */             var2[var4][3] = var19[1];
/* 133 */             var4++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 138 */       var3--;
/* 139 */       var6--;
/*     */     } 
/*     */ 
/*     */     
/* 143 */     this.leafNodes = new int[var4][4];
/* 144 */     System.arraycopy(var2, 0, this.leafNodes, 0, var4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void genTreeLayer(int par1, int par2, int par3, float par4, byte par5, Block leaves) {
/* 150 */     int var7 = (int)(par4 + 0.618D);
/* 151 */     byte var8 = otherCoordPairs[par5];
/* 152 */     byte var9 = otherCoordPairs[par5 + 3];
/* 153 */     int[] var10 = { par1, par2, par3 };
/* 154 */     int[] var11 = { 0, 0, 0 };
/* 155 */     int var12 = -var7;
/* 156 */     int var13 = -var7;
/*     */ 
/*     */     
/* 159 */     for (var11[par5] = var10[par5]; var12 <= var7; var12++) {
/* 160 */       var11[var8] = var10[var8] + var12;
/* 161 */       var13 = -var7;
/*     */ 
/*     */ 
/*     */       
/* 165 */       while (var13 <= var7) {
/*     */ 
/*     */ 
/*     */         
/* 169 */         double var15 = Math.pow(Math.abs(var12) + 0.5D, 2.0D) + Math.pow(Math.abs(var13) + 0.5D, 2.0D);
/* 170 */         if (var15 > (par4 * par4)) {
/* 171 */           var13++; continue;
/*     */         } 
/* 173 */         var11[var9] = var10[var9] + var13;
/* 174 */         Block block = this.worldObj.func_147439_a(var11[0], var11[1], var11[2]);
/* 175 */         if (block != Blocks.field_150350_a && block != leaves) {
/* 176 */           var13++; continue;
/*     */         } 
/* 178 */         func_150516_a(this.worldObj, var11[0], var11[1], var11[2], leaves, this.meta);
/* 179 */         var13++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   float layerSize(int par1) {
/*     */     float var4;
/* 189 */     if (par1 < this.heightLimit * 0.3D) {
/* 190 */       return -1.618F;
/*     */     }
/* 192 */     float var2 = this.heightLimit / 2.0F;
/* 193 */     float var3 = this.heightLimit / 2.0F - par1;
/*     */     
/* 195 */     if (var3 == 0.0F) {
/* 196 */       var4 = var2;
/* 197 */     } else if (Math.abs(var3) >= var2) {
/* 198 */       var4 = 0.0F;
/*     */     } else {
/* 200 */       var4 = (float)Math.sqrt(Math.pow(Math.abs(var2), 2.0D) - Math.pow(Math.abs(var3), 2.0D));
/*     */     } 
/*     */     
/* 203 */     var4 *= 0.5F;
/* 204 */     return var4;
/*     */   }
/*     */ 
/*     */   
/*     */   float leafSize(int par1) {
/* 209 */     return (par1 >= 0 && par1 < this.leafDistanceLimit) ? ((par1 != 0 && par1 != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
/*     */   }
/*     */   
/*     */   void generateLeafNode(int par1, int par2, int par3) {
/* 213 */     int var4 = par2;
/*     */     
/* 215 */     for (int var5 = par2 + this.leafDistanceLimit; var4 < var5; var4++) {
/* 216 */       float var6 = leafSize(var4 - par2);
/* 217 */       genTreeLayer(par1, var4, par3, var6, (byte)1, TFCBlocks.leaves);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger, Block par3) {
/* 223 */     int[] var4 = { 0, 0, 0 };
/* 224 */     byte var5 = 0;
/*     */     
/*     */     byte var6;
/* 227 */     for (var6 = 0; var5 < 3; var5 = (byte)(var5 + 1)) {
/* 228 */       var4[var5] = par2ArrayOfInteger[var5] - par1ArrayOfInteger[var5];
/* 229 */       if (Math.abs(var4[var5]) > Math.abs(var4[var6])) {
/* 230 */         var6 = var5;
/*     */       }
/*     */     } 
/*     */     
/* 234 */     if (var4[var6] != 0) {
/* 235 */       byte var9, var7 = otherCoordPairs[var6];
/* 236 */       byte var8 = otherCoordPairs[var6 + 3];
/*     */       
/* 238 */       if (var4[var6] > 0) {
/* 239 */         var9 = 1;
/*     */       } else {
/* 241 */         var9 = -1;
/*     */       } 
/*     */       
/* 244 */       double var10 = var4[var7] / var4[var6];
/* 245 */       double var12 = var4[var8] / var4[var6];
/* 246 */       int[] var14 = { 0, 0, 0 };
/* 247 */       int var15 = 0;
/*     */       
/* 249 */       for (int var16 = var4[var6] + var9; var15 != var16; var15 += var9) {
/* 250 */         var14[var6] = MathHelper.func_76128_c((par1ArrayOfInteger[var6] + var15) + 0.5D);
/* 251 */         var14[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var15 * var10 + 0.5D);
/* 252 */         var14[var8] = MathHelper.func_76128_c(par1ArrayOfInteger[var8] + var15 * var12 + 0.5D);
/*     */         
/* 254 */         func_150516_a(this.worldObj, var14[0], var14[1], var14[2], par3, this.meta);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void generateLeaves() {
/* 261 */     int var1 = 0;
/*     */     
/* 263 */     for (int var2 = this.leafNodes.length; var1 < var2; var1++) {
/* 264 */       int var3 = this.leafNodes[var1][0];
/* 265 */       int var4 = this.leafNodes[var1][1];
/* 266 */       int var5 = this.leafNodes[var1][2];
/* 267 */       generateLeafNode(var3, var4, var5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean leafNodeNeedsBase(int par1) {
/* 273 */     return (par1 >= this.heightLimit * 0.2D);
/*     */   }
/*     */   
/*     */   void generateTrunk() {
/* 277 */     int var1 = this.basePos[0];
/* 278 */     int var2 = this.basePos[1];
/* 279 */     int var3 = this.basePos[1] + this.height;
/* 280 */     int var4 = this.basePos[2];
/* 281 */     int[] var5 = { var1, var2, var4 };
/* 282 */     int[] var6 = { var1, var3, var4 };
/* 283 */     if (this.trunkSize == 2) {
/* 284 */       for (int i = -2; i < 3; i++) {
/* 285 */         var5[0] = var1 + i;
/* 286 */         var6[0] = var1 + i;
/*     */         
/* 288 */         for (int j = -2; j < 3; j++) {
/* 289 */           if ((i != -2 || j != -2) && (i != -2 || j != 2) && (i != 2 || j != -2) && (i != 2 || j != 2)) {
/* 290 */             var5[2] = var4 + j;
/* 291 */             var6[2] = var4 + j;
/* 292 */             placeBlockLine(var5, var6, TFCBlocks.logNatural);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 298 */     if (this.trunkSize == 1) {
/* 299 */       for (int i = 0; i < 2; i++) {
/* 300 */         var5[0] = var1 + i;
/* 301 */         var6[0] = var1 + i;
/*     */         
/* 303 */         for (int j = 0; j < 2; j++) {
/* 304 */           if ((i != -2 || j != -2) && (i != -2 || j != 2) && (i != 2 || j != -2) && (i != 2 || j != 2)) {
/* 305 */             var5[2] = var4 + j;
/* 306 */             var6[2] = var4 + j;
/* 307 */             placeBlockLine(var5, var6, TFCBlocks.logNatural);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   void generateLeafNodeBases() {
/* 315 */     int var1 = 0;
/* 316 */     int var2 = this.leafNodes.length;
/*     */     
/* 318 */     for (int[] var3 = { this.basePos[0], this.basePos[1], this.basePos[2] }; var1 < var2; var1++) {
/* 319 */       int[] var4 = this.leafNodes[var1];
/* 320 */       int[] var5 = { var4[0], var4[1], var4[2] };
/* 321 */       var3[1] = var4[3];
/* 322 */       int var6 = var3[1] - this.basePos[1];
/* 323 */       Vec3 from = Vec3.func_72443_a(var3[0], var3[1], var3[2]);
/* 324 */       Vec3 to = Vec3.func_72443_a(var5[0], var5[1], var5[2]);
/* 325 */       if (from.func_72438_d(to) < this.height * 2.5D && 
/* 326 */         leafNodeNeedsBase(var6)) {
/* 327 */         placeBlockLine(var3, var5, TFCBlocks.logNatural);
/* 328 */         int var3l = this.leafNodes[var1][0];
/* 329 */         int var4l = this.leafNodes[var1][1];
/* 330 */         int var5l = this.leafNodes[var1][2];
/* 331 */         generateLeafNode(var3l, var4l, var5l);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
/*     */     byte var8;
/* 339 */     int[] var3 = { 0, 0, 0 };
/* 340 */     byte var4 = 0;
/*     */     
/*     */     byte var5;
/* 343 */     for (var5 = 0; var4 < 3; var4 = (byte)(var4 + 1)) {
/* 344 */       var3[var4] = par2ArrayOfInteger[var4] - par1ArrayOfInteger[var4];
/* 345 */       if (Math.abs(var3[var4]) > Math.abs(var3[var5])) {
/* 346 */         var5 = var4;
/*     */       }
/*     */     } 
/*     */     
/* 350 */     if (var3[var5] == 0) {
/* 351 */       return -1;
/*     */     }
/* 353 */     byte var6 = otherCoordPairs[var5];
/* 354 */     byte var7 = otherCoordPairs[var5 + 3];
/*     */     
/* 356 */     if (var3[var5] > 0) {
/* 357 */       var8 = 1;
/*     */     } else {
/* 359 */       var8 = -1;
/*     */     } 
/*     */     
/* 362 */     double var9 = var3[var6] / var3[var5];
/* 363 */     double var11 = var3[var7] / var3[var5];
/* 364 */     int[] var13 = { 0, 0, 0 };
/* 365 */     int var14 = 0;
/*     */     
/*     */     int var15;
/* 368 */     for (var15 = var3[var5] + var8; var14 != var15; var14 += var8) {
/* 369 */       var13[var5] = par1ArrayOfInteger[var5] + var14;
/* 370 */       var13[var6] = MathHelper.func_76128_c(par1ArrayOfInteger[var6] + var14 * var9);
/* 371 */       var13[var7] = MathHelper.func_76128_c(par1ArrayOfInteger[var7] + var14 * var11);
/* 372 */       Block block = this.worldObj.func_147439_a(var13[0], var13[1], var13[2]);
/* 373 */       if (block != Blocks.field_150350_a && block != TFCBlocks.leaves) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/* 378 */     return (var14 == var15) ? -1 : Math.abs(var14);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean valid(World wo, int rx, int ry, int rz) {
/* 384 */     if (ry >= wo.func_72800_K() - this.heightLimit) return false; 
/* 385 */     for (int x = -1; x < 1; x++) {
/* 386 */       for (int z = -1; z < 1; z++)
/* 387 */       { if (wo.func_147439_a(rx + x, ry - 1, rz + z) == Blocks.field_150350_a) return false;  } 
/* 388 */     }  return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\WorldGenSacredOak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */