/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockMetalSheet;
/*     */ import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
/*     */ import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ public class RenderMetalTrapDoor
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean render(Block block, int i, int j, int k, RenderBlocks renderer) {
/*  19 */     IBlockAccess access = renderer.field_147845_a;
/*  20 */     TEMetalTrapDoor te = (TEMetalTrapDoor)access.func_147438_o(i, j, k);
/*  21 */     int side = te.data & 0x7;
/*  22 */     int hinge = te.data >> 4;
/*  23 */     float f = 0.0625F;
/*     */ 
/*     */ 
/*     */     
/*  27 */     float fx = 0.0F;
/*  28 */     float fy = 0.0F;
/*  29 */     float fz = 0.0F;
/*  30 */     float fx2 = 1.0F;
/*  31 */     float fy2 = 1.0F;
/*  32 */     float fz2 = 1.0F;
/*  33 */     renderer.field_147837_f = true;
/*  34 */     if (BlockMetalTrapDoor.isTrapdoorOpen(access.func_72805_g(i, j, k))) {
/*     */       
/*  36 */       if (hinge == 0) {
/*     */         
/*  38 */         switch (side) {
/*     */ 
/*     */           
/*     */           case 0:
/*  42 */             fx2 = f;
/*  43 */             fy2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/*  48 */             fy = f;
/*  49 */             fx2 = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/*  54 */             fx2 = f;
/*  55 */             fz2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/*  60 */             fz = f;
/*  61 */             fx2 = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/*  66 */             fy2 = f;
/*  67 */             fx2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/*  72 */             fy2 = f;
/*  73 */             fx = f;
/*     */             break;
/*     */           
/*     */           default:
/*  77 */             fx2 = f;
/*     */             break;
/*     */         } 
/*     */       
/*  81 */       } else if (hinge == 1) {
/*     */         
/*  83 */         switch (side) {
/*     */ 
/*     */           
/*     */           case 0:
/*  87 */             fz2 = f;
/*  88 */             fy2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/*  93 */             fy = f;
/*  94 */             fz2 = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/*  99 */             fy2 = f;
/* 100 */             fz2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/* 105 */             fy2 = f;
/* 106 */             fz = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 111 */             fx2 = 1.0F - f;
/* 112 */             fz2 = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 117 */             fx = f;
/* 118 */             fz2 = f;
/*     */             break;
/*     */           
/*     */           default:
/* 122 */             fz2 = f;
/*     */             break;
/*     */         } 
/*     */       
/* 126 */       } else if (hinge == 2) {
/*     */         
/* 128 */         switch (side) {
/*     */ 
/*     */           
/*     */           case 0:
/* 132 */             fx = 1.0F - f;
/* 133 */             fy2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 138 */             fx = 1.0F - f;
/* 139 */             fy = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/* 144 */             fx = 1.0F - f;
/* 145 */             fz2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/* 150 */             fx = 1.0F - f;
/* 151 */             fz = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 156 */             fx2 = 1.0F - f;
/* 157 */             fy = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 162 */             fy = 1.0F - f;
/* 163 */             fx = f;
/*     */             break;
/*     */           
/*     */           default:
/* 167 */             fx = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/* 172 */       } else if (hinge == 3) {
/*     */         
/* 174 */         switch (side) {
/*     */ 
/*     */           
/*     */           case 0:
/* 178 */             fy2 = 1.0F - f;
/* 179 */             fz = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/* 184 */             fy = f;
/* 185 */             fz = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/* 190 */             fy = 1.0F - f;
/* 191 */             fz2 = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/* 196 */             fy = 1.0F - f;
/* 197 */             fz = f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 202 */             fx2 = 1.0F - f;
/* 203 */             fz = 1.0F - f;
/*     */             break;
/*     */ 
/*     */           
/*     */           case 5:
/* 208 */             fx = f;
/* 209 */             fz = 1.0F - f;
/*     */             break;
/*     */           
/*     */           default:
/* 213 */             fz = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       } 
/* 217 */       renderer.func_147782_a((fx + 1.0E-4F), (fy + 1.0E-4F), (fz + 1.0E-4F), (fx2 - 1.0E-4F), (fy2 - 1.0E-4F), (fz2 - 1.0E-4F));
/* 218 */       renderer.func_147784_q(block, i, j, k);
/*     */     }
/*     */     else {
/*     */       
/* 222 */       if (side == 0) {
/*     */         
/* 224 */         fy = 1.0F - f;
/* 225 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 228 */             fx = f;
/*     */             break;
/*     */           case 1:
/* 231 */             fz = f;
/*     */             break;
/*     */           case 2:
/* 234 */             fx2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 237 */             fz2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       
/* 241 */       } else if (side == 1) {
/*     */         
/* 243 */         fy2 = f;
/* 244 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 247 */             fx = f;
/*     */             break;
/*     */           case 1:
/* 250 */             fz = f;
/*     */             break;
/*     */           case 2:
/* 253 */             fx2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 256 */             fz2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       
/* 260 */       } else if (side == 2) {
/*     */         
/* 262 */         fz = 1.0F - f;
/* 263 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 266 */             fx = f;
/*     */             break;
/*     */           case 1:
/* 269 */             fy = f;
/*     */             break;
/*     */           case 2:
/* 272 */             fx2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 275 */             fy2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       
/* 279 */       } else if (side == 3) {
/*     */         
/* 281 */         fz2 = f;
/* 282 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 285 */             fx = f;
/*     */             break;
/*     */           case 1:
/* 288 */             fy = f;
/*     */             break;
/*     */           case 2:
/* 291 */             fx2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 294 */             fy2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       
/* 298 */       } else if (side == 4) {
/*     */         
/* 300 */         fx = 1.0F - f;
/* 301 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 304 */             fy = f;
/*     */             break;
/*     */           case 1:
/* 307 */             fz = f;
/*     */             break;
/*     */           case 2:
/* 310 */             fy2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 313 */             fz2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       
/* 317 */       } else if (side == 5) {
/*     */         
/* 319 */         fx2 = f;
/* 320 */         switch (hinge) {
/*     */           
/*     */           case 0:
/* 323 */             fy = f;
/*     */             break;
/*     */           case 1:
/* 326 */             fz = f;
/*     */             break;
/*     */           case 2:
/* 329 */             fy2 = 1.0F - f;
/*     */             break;
/*     */           case 3:
/* 332 */             fz2 = 1.0F - f;
/*     */             break;
/*     */         } 
/*     */       } 
/* 336 */       renderer.func_147782_a(fx, fy, fz, fx2, fy2, fz2);
/* 337 */       renderer.func_147784_q(block, i, j, k);
/*     */     } 
/*     */     
/* 340 */     int hingeID = (te.sheetStack != null) ? Math.min(((BlockMetalSheet)TFCBlocks.metalSheet).icons.length - 1, te.sheetStack.func_77960_j() >> 5) : 0;
/*     */     
/* 342 */     boolean breaking = (renderer.field_147840_d != null);
/*     */     
/* 344 */     if (!breaking) {
/* 345 */       renderer.func_147757_a(((BlockMetalSheet)TFCBlocks.metalSheet).icons[hingeID]);
/*     */     }
/* 347 */     drawHinges(block, i, j, k, renderer, side, hinge);
/*     */     
/* 349 */     if (!breaking) {
/* 350 */       renderer.func_147771_a();
/*     */     }
/* 352 */     renderer.field_147837_f = false;
/* 353 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void drawHinges(Block block, int i, int j, int k, RenderBlocks renderer, int side, int hinge) {
/* 359 */     float f = 0.0625F;
/* 360 */     float f3 = f / 2.0F;
/*     */     
/* 362 */     float hingeMin = 0.0F;
/* 363 */     float hingeMin2 = f + f3;
/* 364 */     float hingeMax = 1.0F - f - f3;
/* 365 */     float hingeMax2 = 1.0F;
/*     */     
/* 367 */     if (hinge == 0) {
/*     */       
/* 369 */       switch (side) {
/*     */ 
/*     */         
/*     */         case 0:
/* 373 */           renderer.func_147782_a(hingeMin, hingeMax, 0.1D, hingeMin2, hingeMax2, 0.4D);
/* 374 */           renderer.func_147784_q(block, i, j, k);
/* 375 */           renderer.func_147782_a(hingeMin, hingeMax, 0.6D, hingeMin2, hingeMax2, 0.9D);
/* 376 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 381 */           renderer.func_147782_a(hingeMin, hingeMin, 0.1D, hingeMin2, hingeMin2, 0.4D);
/* 382 */           renderer.func_147784_q(block, i, j, k);
/* 383 */           renderer.func_147782_a(hingeMin, hingeMin, 0.6D, hingeMin2, hingeMin2, 0.9D);
/* 384 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 389 */           renderer.func_147782_a(hingeMin, 0.1D, hingeMax, hingeMin2, 0.4D, hingeMax2);
/* 390 */           renderer.func_147784_q(block, i, j, k);
/* 391 */           renderer.func_147782_a(hingeMin, 0.6D, hingeMax, hingeMin2, 0.9D, hingeMax2);
/* 392 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 397 */           renderer.func_147782_a(hingeMin, 0.1D, hingeMin, hingeMin2, 0.4D, hingeMin2);
/* 398 */           renderer.func_147784_q(block, i, j, k);
/* 399 */           renderer.func_147782_a(hingeMin, 0.6D, hingeMin, hingeMin2, 0.9D, hingeMin2);
/* 400 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 405 */           renderer.func_147782_a(hingeMax, hingeMin, 0.1D, hingeMax2, hingeMin2, 0.4D);
/* 406 */           renderer.func_147784_q(block, i, j, k);
/* 407 */           renderer.func_147782_a(hingeMax, hingeMin, 0.6D, hingeMax2, hingeMin2, 0.9D);
/* 408 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 413 */           renderer.func_147782_a(hingeMin, hingeMin, 0.1D, hingeMin2, hingeMin2, 0.4D);
/* 414 */           renderer.func_147784_q(block, i, j, k);
/* 415 */           renderer.func_147782_a(hingeMin, hingeMin, 0.6D, hingeMin2, hingeMin2, 0.9D);
/* 416 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 421 */     } else if (hinge == 1) {
/*     */       
/* 423 */       switch (side) {
/*     */ 
/*     */         
/*     */         case 0:
/* 427 */           renderer.func_147782_a(0.1D, hingeMax, hingeMin, 0.4D, hingeMax2, hingeMin2);
/* 428 */           renderer.func_147784_q(block, i, j, k);
/* 429 */           renderer.func_147782_a(0.6D, hingeMax, hingeMin, 0.9D, hingeMax2, hingeMin2);
/* 430 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 435 */           renderer.func_147782_a(0.1D, hingeMin, hingeMin, 0.4D, hingeMin2, hingeMin2);
/* 436 */           renderer.func_147784_q(block, i, j, k);
/* 437 */           renderer.func_147782_a(0.6D, hingeMin, hingeMin, 0.9D, hingeMin2, hingeMin2);
/* 438 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 443 */           renderer.func_147782_a(0.1D, hingeMin, hingeMax, 0.4D, hingeMin2, hingeMax2);
/* 444 */           renderer.func_147784_q(block, i, j, k);
/* 445 */           renderer.func_147782_a(0.6D, hingeMin, hingeMax, 0.9D, hingeMin2, hingeMax2);
/* 446 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 451 */           renderer.func_147782_a(0.1D, hingeMin, hingeMin, 0.4D, hingeMin2, hingeMin2);
/* 452 */           renderer.func_147784_q(block, i, j, k);
/* 453 */           renderer.func_147782_a(0.6D, hingeMin, hingeMin, 0.9D, hingeMin2, hingeMin2);
/* 454 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 459 */           renderer.func_147782_a(hingeMax, 0.1D, hingeMin, hingeMax2, 0.4D, hingeMin2);
/* 460 */           renderer.func_147784_q(block, i, j, k);
/* 461 */           renderer.func_147782_a(hingeMax, 0.6D, hingeMin, hingeMax2, 0.9D, hingeMin2);
/* 462 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 467 */           renderer.func_147782_a(hingeMin, 0.1D, hingeMin, hingeMin2, 0.4D, hingeMin2);
/* 468 */           renderer.func_147784_q(block, i, j, k);
/* 469 */           renderer.func_147782_a(hingeMin, 0.6D, hingeMin, hingeMin2, 0.9D, hingeMin2);
/* 470 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 475 */     } else if (hinge == 2) {
/*     */       
/* 477 */       switch (side) {
/*     */ 
/*     */         
/*     */         case 0:
/* 481 */           renderer.func_147782_a(hingeMax, hingeMax, 0.1D, hingeMax2, hingeMax2, 0.4D);
/* 482 */           renderer.func_147784_q(block, i, j, k);
/* 483 */           renderer.func_147782_a(hingeMax, hingeMax, 0.6D, hingeMax2, hingeMax2, 0.9D);
/* 484 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 489 */           renderer.func_147782_a(hingeMax, hingeMin, 0.1D, hingeMax2, hingeMin2, 0.4D);
/* 490 */           renderer.func_147784_q(block, i, j, k);
/* 491 */           renderer.func_147782_a(hingeMax, hingeMin, 0.6D, hingeMax2, hingeMin2, 0.9D);
/* 492 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 497 */           renderer.func_147782_a(hingeMax, 0.1D, hingeMax, hingeMax2, 0.4D, hingeMax2);
/* 498 */           renderer.func_147784_q(block, i, j, k);
/* 499 */           renderer.func_147782_a(hingeMax, 0.6D, hingeMax, hingeMax2, 0.9D, hingeMax2);
/* 500 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 505 */           renderer.func_147782_a(hingeMax, 0.1D, hingeMin, hingeMax2, 0.4D, hingeMin2);
/* 506 */           renderer.func_147784_q(block, i, j, k);
/* 507 */           renderer.func_147782_a(hingeMax, 0.6D, hingeMin, hingeMax2, 0.9D, hingeMin2);
/* 508 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 513 */           renderer.func_147782_a(hingeMax, hingeMax, 0.1D, hingeMax2, hingeMax2, 0.4D);
/* 514 */           renderer.func_147784_q(block, i, j, k);
/* 515 */           renderer.func_147782_a(hingeMax, hingeMax, 0.6D, hingeMax2, hingeMax2, 0.9D);
/* 516 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 521 */           renderer.func_147782_a(hingeMin, hingeMax, 0.1D, hingeMin2, hingeMax2, 0.4D);
/* 522 */           renderer.func_147784_q(block, i, j, k);
/* 523 */           renderer.func_147782_a(hingeMin, hingeMax, 0.6D, hingeMin2, hingeMax2, 0.9D);
/* 524 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */       } 
/*     */ 
/*     */     
/* 529 */     } else if (hinge == 3) {
/*     */       
/* 531 */       switch (side) {
/*     */ 
/*     */         
/*     */         case 0:
/* 535 */           renderer.func_147782_a(0.1D, hingeMax, hingeMax, 0.4D, hingeMax2, hingeMax2);
/* 536 */           renderer.func_147784_q(block, i, j, k);
/* 537 */           renderer.func_147782_a(0.6D, hingeMax, hingeMax, 0.9D, hingeMax2, hingeMax2);
/* 538 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 1:
/* 543 */           renderer.func_147782_a(0.1D, hingeMin, hingeMax, 0.4D, hingeMin2, hingeMax2);
/* 544 */           renderer.func_147784_q(block, i, j, k);
/* 545 */           renderer.func_147782_a(0.6D, hingeMin, hingeMax, 0.9D, hingeMin2, hingeMax2);
/* 546 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 2:
/* 551 */           renderer.func_147782_a(0.1D, hingeMax, hingeMax, 0.4D, hingeMax2, hingeMax2);
/* 552 */           renderer.func_147784_q(block, i, j, k);
/* 553 */           renderer.func_147782_a(0.6D, hingeMax, hingeMax, 0.9D, hingeMax2, hingeMax2);
/* 554 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 3:
/* 559 */           renderer.func_147782_a(0.1D, hingeMax, hingeMin, 0.4D, hingeMax2, hingeMin2);
/* 560 */           renderer.func_147784_q(block, i, j, k);
/* 561 */           renderer.func_147782_a(0.6D, hingeMax, hingeMin, 0.9D, hingeMax2, hingeMin2);
/* 562 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 4:
/* 567 */           renderer.func_147782_a(hingeMax, 0.1D, hingeMax, hingeMax2, 0.4D, hingeMax2);
/* 568 */           renderer.func_147784_q(block, i, j, k);
/* 569 */           renderer.func_147782_a(hingeMax, 0.6D, hingeMax, hingeMax2, 0.9D, hingeMax2);
/* 570 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */ 
/*     */         
/*     */         case 5:
/* 575 */           renderer.func_147782_a(hingeMin, 0.1D, hingeMax, hingeMin2, 0.4D, hingeMax2);
/* 576 */           renderer.func_147784_q(block, i, j, k);
/* 577 */           renderer.func_147782_a(hingeMin, 0.6D, hingeMax, hingeMin2, 0.9D, hingeMax2);
/* 578 */           renderer.func_147784_q(block, i, j, k);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
/* 588 */     renderer.func_147782_a(0.125D, 0.4000000059604645D, 0.0D, 1.0D, 0.4749999940395355D, 1.0D);
/* 589 */     renderInvBlock(block, metadata & 0xFF, renderer);
/* 590 */     renderer.func_147782_a(0.0D, 0.4000000059604645D, 0.10000000149011612D, 0.125D, 0.5249999761581421D, 0.4000000059604645D);
/* 591 */     int index = Math.min(((BlockMetalSheet)TFCBlocks.metalSheet).icons.length - 1, metadata >> 5);
/* 592 */     renderInvBlock(block, ((BlockMetalSheet)TFCBlocks.metalSheet).icons[index], renderer);
/* 593 */     renderer.func_147782_a(0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.125D, 0.5249999761581421D, 0.8999999761581421D);
/* 594 */     renderInvBlock(block, ((BlockMetalSheet)TFCBlocks.metalSheet).icons[index], renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 600 */     return render(block, x, y, z, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 606 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 612 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
/* 617 */     Tessellator var14 = Tessellator.field_78398_a;
/* 618 */     var14.func_78382_b();
/* 619 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 620 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 621 */     var14.func_78381_a();
/* 622 */     var14.func_78382_b();
/* 623 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 624 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 625 */     var14.func_78381_a();
/* 626 */     var14.func_78382_b();
/* 627 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 628 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 629 */     var14.func_78381_a();
/* 630 */     var14.func_78382_b();
/* 631 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 632 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 633 */     var14.func_78381_a();
/* 634 */     var14.func_78382_b();
/* 635 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 636 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 637 */     var14.func_78381_a();
/* 638 */     var14.func_78382_b();
/* 639 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 640 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 641 */     var14.func_78381_a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, IIcon ico, RenderBlocks renderer) {
/* 646 */     Tessellator var14 = Tessellator.field_78398_a;
/* 647 */     var14.func_78382_b();
/* 648 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 649 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, ico);
/* 650 */     var14.func_78381_a();
/* 651 */     var14.func_78382_b();
/* 652 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 653 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, ico);
/* 654 */     var14.func_78381_a();
/* 655 */     var14.func_78382_b();
/* 656 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 657 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, ico);
/* 658 */     var14.func_78381_a();
/* 659 */     var14.func_78382_b();
/* 660 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 661 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, ico);
/* 662 */     var14.func_78381_a();
/* 663 */     var14.func_78382_b();
/* 664 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 665 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, ico);
/* 666 */     var14.func_78381_a();
/* 667 */     var14.func_78382_b();
/* 668 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 669 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, ico);
/* 670 */     var14.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderMetalTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */