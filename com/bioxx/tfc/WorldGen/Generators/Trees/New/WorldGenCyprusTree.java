/*     */ package com.bioxx.tfc.WorldGen.Generators.Trees.New;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenCyprusTree
/*     */   extends WorldGenerator
/*     */ {
/*     */   World wo;
/*     */   Random rand;
/*     */   int rootRand;
/*     */   int rootAlt;
/*     */   private Block cyprusWoodBlock;
/*     */   private Block cyprusLeafBlock;
/*     */   private int woodMeta;
/*     */   private int leafMeta;
/*     */   private int heightmin;
/*     */   private int heightmax;
/*     */   private int meta;
/*     */   
/*     */   public WorldGenCyprusTree(boolean flag, int id) {
/*  31 */     super(flag);
/*  32 */     this.meta = id;
/*  33 */     this.rootRand = 0;
/*  34 */     this.rootAlt = 0;
/*  35 */     this.woodMeta = this.meta;
/*  36 */     this.leafMeta = this.meta;
/*     */   }
/*     */   
/*     */   public void setConfigOptions(Block wood, Block leaf, int height1, int height2) {
/*  40 */     this.cyprusWoodBlock = wood;
/*  41 */     this.cyprusLeafBlock = leaf;
/*  42 */     this.heightmin = height1;
/*  43 */     this.heightmax = height2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_76484_a(World world, Random random, int i, int j, int k) {
/*  49 */     this.wo = world;
/*  50 */     this.rand = random;
/*     */     
/*  52 */     int l = random.nextInt(this.heightmax - this.heightmin) + this.heightmin;
/*  53 */     if (j < 1) return false; 
/*  54 */     if (j + l + 1 > 256) {
/*  55 */       return false;
/*     */     }
/*  57 */     if (!valid(i, j, k)) return false;
/*     */ 
/*     */ 
/*     */     
/*  61 */     double pitch = 1.2566370614359172D;
/*  62 */     double dir = this.rand.nextFloat(), spin = 3.8830085198369844D;
/*     */     
/*  64 */     double rootSlope = -1.0471975511965976D;
/*  65 */     j += 4;
/*  66 */     growRoot(i - 1, j, k, 0.5625D, rootSlope);
/*  67 */     growRoot(i - 1, j, k + 1, 0.4375D, rootSlope);
/*  68 */     growRoot(i, j, k + 2, 0.3125D, rootSlope);
/*  69 */     growRoot(i + 1, j, k + 2, 0.1875D, rootSlope);
/*  70 */     growRoot(i + 2, j, k + 1, 0.0625D, rootSlope);
/*  71 */     growRoot(i + 2, j, k, 0.9375D, rootSlope);
/*  72 */     growRoot(i + 1, j, k - 1, 0.8125D, rootSlope);
/*  73 */     growRoot(i, j, k - 1, 0.6875D, rootSlope);
/*  74 */     j -= 4; int n;
/*  75 */     for (n = 0; n < l; n++) {
/*  76 */       if (n < l - 2) {
/*  77 */         func_150516_a(world, i, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/*  78 */         func_150516_a(world, i + 1, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/*  79 */         func_150516_a(world, i + 1, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/*  80 */         func_150516_a(world, i, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/*     */       } else {
/*  82 */         func_150516_a(world, i, j + n, k, this.cyprusLeafBlock, this.leafMeta);
/*  83 */         func_150516_a(world, i + 1, j + n, k, this.cyprusLeafBlock, this.leafMeta);
/*  84 */         func_150516_a(world, i + 1, j + n, k + 1, this.cyprusLeafBlock, this.leafMeta);
/*  85 */         func_150516_a(world, i, j + n, k + 1, this.cyprusLeafBlock, this.leafMeta);
/*     */       } 
/*  87 */       if (n <= 8) {
/*  88 */         func_150516_a(world, i, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/*  89 */         func_150516_a(world, i + 1, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/*  90 */         func_150516_a(world, i + 2, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/*  91 */         func_150516_a(world, i + 2, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/*  92 */         func_150516_a(world, i + 1, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/*  93 */         func_150516_a(world, i, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/*  94 */         func_150516_a(world, i - 1, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/*  95 */         func_150516_a(world, i - 1, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/*     */       } 
/*  97 */       if (n <= 3) {
/*  98 */         func_150516_a(world, i - 1, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/*  99 */         func_150516_a(world, i - 1, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/* 100 */         func_150516_a(world, i + 2, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/* 101 */         func_150516_a(world, i + 2, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/*     */       } 
/* 103 */       if (n <= 1) {
/* 104 */         func_150516_a(world, i, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
/* 105 */         func_150516_a(world, i + 1, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
/* 106 */         func_150516_a(world, i, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
/* 107 */         func_150516_a(world, i + 1, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
/* 108 */         func_150516_a(world, i + 3, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/* 109 */         func_150516_a(world, i + 3, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/* 110 */         func_150516_a(world, i - 2, j + n, k, this.cyprusWoodBlock, this.woodMeta);
/* 111 */         func_150516_a(world, i - 2, j + n, k + 1, this.cyprusWoodBlock, this.woodMeta);
/*     */       } 
/* 113 */       if (n == 0) {
/* 114 */         func_150516_a(world, i - 2, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/* 115 */         func_150516_a(world, i - 1, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
/* 116 */         func_150516_a(world, i - 2, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/* 117 */         func_150516_a(world, i - 1, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
/* 118 */         func_150516_a(world, i + 3, j + n, k + 2, this.cyprusWoodBlock, this.woodMeta);
/* 119 */         func_150516_a(world, i + 2, j + n, k + 3, this.cyprusWoodBlock, this.woodMeta);
/* 120 */         func_150516_a(world, i + 3, j + n, k - 1, this.cyprusWoodBlock, this.woodMeta);
/* 121 */         func_150516_a(world, i + 2, j + n, k - 2, this.cyprusWoodBlock, this.woodMeta);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 132 */     for (n = 5; n < l - 2; n++) {
/* 133 */       if (this.rand.nextInt(l + 10) < n)
/* 134 */         growBranch(i, j + n, k, (l - n) * 0.618D, dir, pitch, 0.0D, 0.0D, 0); 
/* 135 */       dir += spin;
/*     */     } 
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
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void growBranch(int i, int j, int k, double len, double dir, double pitch, double pbias, double pbias2, int size) {
/* 186 */     double dx = 0.0D;
/* 187 */     double dy = 0.0D;
/* 188 */     double dz = 0.0D;
/*     */ 
/*     */     
/* 191 */     double spin = 0.0D;
/* 192 */     double heave = 0.0D;
/*     */     
/* 194 */     double blen = len * 0.75D;
/* 195 */     plotWood(i, j, k, size);
/* 196 */     while (len > 1.0D) {
/* 197 */       dy += Math.sin(pitch);
/* 198 */       double dd = Math.cos(pitch);
/* 199 */       dx += Math.cos(dir) * dd;
/* 200 */       dz += Math.sin(dir) * dd;
/* 201 */       boolean step = false;
/* 202 */       if (dx >= 1.0D) {
/* 203 */         i++; dx--; step = true;
/* 204 */       } else if (dx <= -1.0D) {
/* 205 */         i--; dx++; step = true;
/*     */       } 
/* 207 */       if (dy >= 1.0D) {
/* 208 */         j++; dy--; step = true;
/* 209 */       } else if (dy <= -1.0D) {
/* 210 */         j--; dy++; step = true;
/*     */       } 
/* 212 */       if (dz >= 1.0D) {
/* 213 */         k++; dz--; step = true;
/* 214 */       } else if (dz <= -1.0D) {
/* 215 */         k--; dz++; step = true;
/*     */       } 
/* 217 */       if (step == true) {
/* 218 */         Block id = this.wo.func_147439_a(i, j, k);
/* 219 */         if (id == Blocks.field_150350_a || id == this.cyprusWoodBlock || id == this.cyprusLeafBlock) {
/* 220 */           plotWood(i, j, k, size);
/*     */         } else {
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/* 226 */       if (len > 2.0D && len < blen) {
/* 227 */         int id1; if (size == 0) { id1 = this.rand.nextInt(8); }
/* 228 */         else { id1 = 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 235 */         if (id1 == 1) growBranch(i, j, k, len, dir + 0.7853981633974483D, 0.0D, 0.0D, 0.0D, 1); 
/* 236 */         if (id1 == 2) growBranch(i, j, k, len, dir - 0.7853981633974483D, 0.0D, 0.0D, 0.0D, 1);
/*     */       
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 245 */       spin += this.rand.nextFloat() * 0.1D - 0.05D;
/* 246 */       heave += this.rand.nextFloat() * 0.1D - 0.05D;
/* 247 */       dir += spin;
/* 248 */       if (pitch > pbias) { heave -= 0.01D; }
/* 249 */       else { heave += 0.01D; }
/* 250 */        if (heave > 0.2D) heave = 0.2D; 
/* 251 */       if (heave < -0.2D) heave = -0.2D; 
/* 252 */       pitch += heave;
/*     */       
/* 254 */       pitch = (pitch - pbias2) * 0.8D + pbias2;
/* 255 */       len--;
/*     */     } 
/* 257 */     treeLeaf(i, j, k, 3);
/*     */   }
/*     */   
/*     */   private void plotWood(int i, int j, int k, int size) {
/* 261 */     func_150516_a(this.wo, i, j, k, this.cyprusWoodBlock, this.woodMeta);
/*     */   }
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
/*     */   void treeLeaf(int i, int j, int k, int r) {
/* 294 */     if (r <= 0)
/* 295 */       return;  int rr = r * r + 1;
/* 296 */     for (int ii = -r; ii <= r; ii++) {
/* 297 */       for (int jj = 0; jj <= 1; jj++) {
/* 298 */         for (int kk = -r; kk <= r; kk++) {
/* 299 */           if (ii * ii + jj * jj + kk * kk <= rr && 
/* 300 */             this.wo.func_147439_a(i + ii, j + jj, k + kk) == Blocks.field_150350_a) {
/* 301 */             func_150516_a(this.wo, i + ii, j + jj, k + kk, this.cyprusLeafBlock, this.leafMeta);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMedium(int i, int j, int k) {
/* 312 */     Block[] canGrowOpen = { Blocks.field_150350_a, Blocks.field_150345_g, (Block)Blocks.field_150358_i, Blocks.field_150355_j, (Block)Blocks.field_150356_k, Blocks.field_150353_l, Blocks.field_150364_r, Blocks.field_150363_s, (Block)Blocks.field_150362_t, (Block)Blocks.field_150361_u };
/* 313 */     Block[] canGrowSolid = { (Block)Blocks.field_150349_c, Blocks.field_150346_d, (Block)Blocks.field_150354_m, Blocks.field_150351_n };
/* 314 */     Block qq = this.wo.func_147439_a(i, j, k);
/*     */     
/* 316 */     int medium = 0; int m;
/* 317 */     for (m = 0; m < canGrowOpen.length; m++) {
/* 318 */       if (qq == canGrowOpen[m]) {
/* 319 */         medium = 1;
/*     */         break;
/*     */       } 
/*     */     } 
/* 323 */     if (medium == 0) {
/* 324 */       for (m = 0; m < canGrowSolid.length; m++) {
/* 325 */         if (qq == canGrowSolid[m]) {
/* 326 */           medium = 2;
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     }
/* 331 */     return medium;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void growRoot(int l, int m, int n, double theta, double phi) {
/*     */     double x, z;
/* 338 */     if (this.rootAlt == 1) {
/* 339 */       this.rootRand = this.rand.nextInt(2);
/* 340 */       m -= this.rootRand;
/* 341 */       this.rootAlt = 2;
/* 342 */     } else if (this.rootAlt == 2) {
/* 343 */       if (this.rootRand == 0)
/* 344 */         m--; 
/* 345 */       this.rootAlt = 0;
/* 346 */     } else if (this.rootAlt == 10) {
/* 347 */       m -= this.rand.nextInt(2);
/*     */     } 
/* 349 */     m++;
/* 350 */     phi -= this.rand.nextFloat() * 0.05D;
/* 351 */     theta += this.rand.nextFloat() * 0.1D - 0.05D;
/* 352 */     double direction = 6.283185307179586D * theta;
/* 353 */     double curl = (this.rand.nextFloat() * 0.4F - 0.2F);
/* 354 */     double pitch = 6.283185307179586D * phi;
/* 355 */     int length = 20 + this.rand.nextInt(4);
/*     */     
/* 357 */     if (l > 0) { x = l + 0.5D; }
/* 358 */     else { x = l - 0.5D; }
/*     */     
/* 360 */     double y = m + 0.5D;
/* 361 */     if (n > 0) { z = n + 0.5D; }
/* 362 */     else { z = n - 0.5D; }
/*     */     
/* 364 */     int i = (int)x, j = (int)y, k = (int)z;
/*     */     
/* 366 */     int med = getMedium(i, j, k);
/* 367 */     int cnt = 0;
/* 368 */     while (length > 0.0D) {
/* 369 */       boolean dug; length--;
/*     */       
/* 371 */       curl = curl + (this.rand.nextFloat() * 0.06F) - 0.029999999329447746D;
/* 372 */       if (pitch < 0.0D) { dug = false; }
/* 373 */       else { dug = true; }
/* 374 */        if (med == 1) {
/* 375 */         pitch = (pitch + 1.5707963267948966D) * 0.7D - 1.5707963267948966D;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */         
/* 384 */         pitch = (pitch - 1.5707963267948966D) * 0.7D + 1.5707963267948966D;
/*     */       } 
/*     */       
/* 387 */       double hoz = Math.cos(pitch);
/* 388 */       double x2 = x + Math.cos(direction) * hoz;
/* 389 */       double y2 = y + Math.sin(pitch);
/* 390 */       double z2 = z + Math.sin(direction) * hoz;
/* 391 */       int i2 = (int)x2, j2 = (int)y2, k2 = (int)z2;
/* 392 */       if (i2 != i || j2 != j || k2 != k) {
/* 393 */         func_150516_a(this.wo, i, j, k, this.cyprusWoodBlock, this.woodMeta);
/* 394 */         if (dug) {
/* 395 */           if (this.wo.func_147439_a(i - 1, j, k) == Blocks.field_150350_a)
/* 396 */             return;  if (this.wo.func_147439_a(i + 1, j, k) == Blocks.field_150350_a)
/* 397 */             return;  if (this.wo.func_147439_a(i, j, k - 1) == Blocks.field_150350_a)
/* 398 */             return;  if (this.wo.func_147439_a(i, j, k + 1) == Blocks.field_150350_a)
/*     */             return; 
/* 400 */         }  cnt++;
/* 401 */         if (cnt < 4 && (
/* 402 */           j2 != j - 1 || i2 != i || k2 != k)) {
/* 403 */           func_150516_a(this.wo, i, j - 1, k, this.cyprusWoodBlock, this.woodMeta);
/*     */         }
/* 405 */         med = getMedium(i2, j2, k2);
/* 406 */         if (med != 0) {
/* 407 */           x = x2; y = y2; z = z2; i = i2; j = j2; k = k2; continue;
/*     */         } 
/* 409 */         med = getMedium(i, j - 1, k);
/* 410 */         if (med != 0) {
/* 411 */           y--; j--; pitch = -1.5707963267948966D; continue;
/*     */         } 
/* 413 */         x2 = x + Math.cos(direction);
/* 414 */         z2 = z + Math.sin(direction);
/* 415 */         i2 = (int)x2; k2 = (int)z2;
/* 416 */         med = getMedium(i2, j, k2);
/* 417 */         if (med != 0) {
/* 418 */           x = x2; z = z2; i = i2; k = k2; pitch = 0.0D; continue;
/*     */         } 
/* 420 */         int dir = (int)(direction * 8.0D / Math.PI);
/* 421 */         if (dir < 0) { dir = 15 - (15 - dir) % 16; }
/* 422 */         else { dir %= 16; }
/* 423 */          int pol = dir % 2;
/* 424 */         int di = i2 - i, dk = k2 - k;
/* 425 */         int[] tdir = { 0, 0, 0, 0 };
/* 426 */         if (di == 0 && dk == 0)
/* 427 */           if (dir < 1) { di = 1; dk = 0; }
/* 428 */           else if (dir < 3) { di = 1; dk = 1; }
/* 429 */           else if (dir < 5) { di = 0; dk = 1; }
/* 430 */           else if (dir < 7) { di = -1; dk = 1; }
/* 431 */           else if (dir < 9) { di = -1; dk = 0; }
/* 432 */           else if (dir < 11) { di = -1; dk = -1; }
/* 433 */           else if (dir < 13) { di = 0; dk = -1; }
/* 434 */           else if (dir < 15) { di = 1; dk = -1; }
/* 435 */           else { di = 1; dk = 0; }
/*     */            
/* 437 */         if (dk == 0) {
/* 438 */           if (di > 0) {
/* 439 */             if (pol == 1) {
/* 440 */               tdir[0] = 2;
/* 441 */               tdir[1] = 14;
/* 442 */               tdir[2] = 4;
/* 443 */               tdir[3] = 12;
/*     */             } else {
/* 445 */               tdir[0] = 14;
/* 446 */               tdir[1] = 2;
/* 447 */               tdir[2] = 12;
/* 448 */               tdir[3] = 4;
/*     */             }
/*     */           
/* 451 */           } else if (pol == 1) {
/* 452 */             tdir[0] = 6;
/* 453 */             tdir[1] = 10;
/* 454 */             tdir[2] = 4;
/* 455 */             tdir[3] = 12;
/*     */           } else {
/* 457 */             tdir[0] = 10;
/* 458 */             tdir[1] = 6;
/* 459 */             tdir[2] = 12;
/* 460 */             tdir[3] = 4;
/*     */           }
/*     */         
/* 463 */         } else if (di == 0) {
/* 464 */           if (dk > 0) {
/* 465 */             if (pol == 1) {
/* 466 */               tdir[0] = 2;
/* 467 */               tdir[1] = 6;
/* 468 */               tdir[2] = 0;
/* 469 */               tdir[3] = 8;
/*     */             } else {
/* 471 */               tdir[0] = 6;
/* 472 */               tdir[1] = 2;
/* 473 */               tdir[2] = 8;
/* 474 */               tdir[3] = 0;
/*     */             }
/*     */           
/* 477 */           } else if (pol == 1) {
/* 478 */             tdir[0] = 10;
/* 479 */             tdir[1] = 14;
/* 480 */             tdir[2] = 8;
/* 481 */             tdir[3] = 0;
/*     */           } else {
/* 483 */             tdir[0] = 14;
/* 484 */             tdir[1] = 10;
/* 485 */             tdir[2] = 0;
/* 486 */             tdir[3] = 8;
/*     */           }
/*     */         
/* 489 */         } else if (dk > 0) {
/* 490 */           if (di > 0) {
/* 491 */             if (pol == 1) {
/* 492 */               tdir[0] = 0;
/* 493 */               tdir[1] = 4;
/* 494 */               tdir[2] = 14;
/* 495 */               tdir[3] = 6;
/*     */             } else {
/* 497 */               tdir[0] = 4;
/* 498 */               tdir[1] = 0;
/* 499 */               tdir[2] = 6;
/* 500 */               tdir[3] = 14;
/*     */             }
/*     */           
/* 503 */           } else if (pol == 1) {
/* 504 */             tdir[0] = 4;
/* 505 */             tdir[1] = 8;
/* 506 */             tdir[2] = 2;
/* 507 */             tdir[3] = 10;
/*     */           } else {
/* 509 */             tdir[0] = 8;
/* 510 */             tdir[1] = 4;
/* 511 */             tdir[2] = 10;
/* 512 */             tdir[3] = 2;
/*     */           }
/*     */         
/*     */         }
/* 516 */         else if (di > 0) {
/* 517 */           if (pol == 1) {
/* 518 */             tdir[0] = 12;
/* 519 */             tdir[1] = 0;
/* 520 */             tdir[2] = 10;
/* 521 */             tdir[3] = 2;
/*     */           } else {
/* 523 */             tdir[0] = 0;
/* 524 */             tdir[1] = 12;
/* 525 */             tdir[2] = 2;
/* 526 */             tdir[3] = 10;
/*     */           }
/*     */         
/* 529 */         } else if (pol == 1) {
/* 530 */           tdir[0] = 8;
/* 531 */           tdir[1] = 12;
/* 532 */           tdir[2] = 6;
/* 533 */           tdir[3] = 14;
/*     */         } else {
/* 535 */           tdir[0] = 12;
/* 536 */           tdir[1] = 8;
/* 537 */           tdir[2] = 14;
/* 538 */           tdir[3] = 6;
/*     */         } 
/*     */ 
/*     */         
/* 542 */         for (int q = 0; q < 4; q++) {
/* 543 */           if (tdir[q] == 0) {
/* 544 */             di = 1; dk = 0;
/* 545 */           } else if (tdir[q] == 2) {
/* 546 */             di = 1; dk = 1;
/* 547 */           } else if (tdir[q] == 4) {
/* 548 */             di = 0; dk = 1;
/* 549 */           } else if (tdir[q] == 6) {
/* 550 */             di = -1; dk = 1;
/* 551 */           } else if (tdir[q] == 8) {
/* 552 */             di = -1; dk = 0;
/* 553 */           } else if (tdir[q] == 10) {
/* 554 */             di = -1; dk = -1;
/* 555 */           } else if (tdir[q] == 12) {
/* 556 */             di = 0; dk = -1;
/*     */           } else {
/* 558 */             di = 1; dk = -1;
/*     */           } 
/* 560 */           i2 = i + di; k2 = k + dk;
/* 561 */           med = getMedium(i2, j, k2);
/* 562 */           if (med != 0) {
/* 563 */             i = i2; k = k2; x = i + 0.5D; z = k + 0.5D;
/* 564 */             pitch = 0.0D;
/* 565 */             direction = tdir[q] * 2.0D * Math.PI / 16.0D;
/*     */             break;
/*     */           } 
/*     */         } 
/* 569 */         if (med == 0) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean valid(int rx, int ry, int rz) {
/* 581 */     if (ry >= this.wo.func_72800_K() - this.heightmax) return false; 
/* 582 */     for (int x = -2; x < 2; x++) {
/* 583 */       for (int z = -2; z < 2; z++)
/* 584 */       { if (!TFC_Core.isSoil(this.wo.func_147439_a(rx + x, ry - 1, rz + z))) return false;  } 
/* 585 */     }  return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\WorldGenCyprusTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */