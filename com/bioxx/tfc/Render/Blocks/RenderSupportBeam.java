/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ public class RenderSupportBeam
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean renderWoodSupportBeamH(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/*  16 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */     
/*  18 */     Boolean hasVerticalBeam = Boolean.valueOf(false);
/*  19 */     Boolean hasHorizontalBeamX = Boolean.valueOf(false);
/*  20 */     Boolean hasHorizontalBeamZ = Boolean.valueOf(false);
/*     */ 
/*     */     
/*  23 */     if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j - 1, k))) {
/*     */       
/*  25 */       renderblocks.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/*  26 */       renderblocks.func_147784_q(block, i, j, k);
/*  27 */       hasVerticalBeam = Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/*  31 */     if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i - 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i - 1, j, k))) {
/*     */       
/*  33 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k)))
/*     */       {
/*  35 */         if (hasVerticalBeam.booleanValue()) {
/*     */           
/*  37 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
/*  38 */           renderblocks.func_147784_q(block, i, j, k);
/*  39 */           renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/*  40 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*  42 */         else if (!hasVerticalBeam.booleanValue()) {
/*     */           
/*  44 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/*  45 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*  47 */         hasHorizontalBeamX = Boolean.valueOf(true);
/*     */       }
/*     */       else
/*     */       {
/*  51 */         if (hasVerticalBeam.booleanValue()) {
/*     */           
/*  53 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
/*  54 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */         else {
/*     */           
/*  58 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
/*  59 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*  61 */         hasHorizontalBeamX = Boolean.valueOf(true);
/*     */       }
/*     */     
/*  64 */     } else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k))) {
/*     */       
/*  66 */       if (hasVerticalBeam.booleanValue()) {
/*     */         
/*  68 */         renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/*  69 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else {
/*     */         
/*  73 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/*  74 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       } 
/*  76 */       hasHorizontalBeamX = Boolean.valueOf(true);
/*     */     } 
/*     */     
/*  79 */     if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k - 1))) {
/*     */       
/*  81 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1)))
/*     */       {
/*  83 */         if (hasVerticalBeam.booleanValue()) {
/*     */           
/*  85 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
/*  86 */           renderblocks.func_147784_q(block, i, j, k);
/*  87 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
/*  88 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*  90 */         else if (!hasVerticalBeam.booleanValue()) {
/*     */           
/*  92 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 1.0D);
/*  93 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/*  95 */         hasHorizontalBeamZ = Boolean.valueOf(true);
/*     */       }
/*     */       else
/*     */       {
/*  99 */         if (hasVerticalBeam.booleanValue()) {
/*     */           
/* 101 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
/* 102 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/*     */         else {
/*     */           
/* 106 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.75D);
/* 107 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         } 
/* 109 */         hasHorizontalBeamZ = Boolean.valueOf(true);
/*     */       }
/*     */     
/* 112 */     } else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1))) {
/*     */       
/* 114 */       if (hasVerticalBeam.booleanValue()) {
/*     */         
/* 116 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
/* 117 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else {
/*     */         
/* 121 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 1.0D);
/* 122 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       } 
/* 124 */       hasHorizontalBeamZ = Boolean.valueOf(true);
/*     */     } 
/*     */     
/* 127 */     float minX = -1.0F;
/* 128 */     float minY = -1.0F;
/* 129 */     float minZ = -1.0F;
/*     */     
/* 131 */     float maxX = -1.0F;
/* 132 */     float maxY = -1.0F;
/* 133 */     float maxZ = -1.0F;
/*     */     
/* 135 */     if (hasHorizontalBeamX.booleanValue()) {
/*     */       
/* 137 */       minX = 0.0F;
/* 138 */       maxX = 1.0F;
/* 139 */       minZ = 0.25F;
/* 140 */       maxZ = 0.75F;
/*     */     } 
/* 142 */     if (hasHorizontalBeamZ.booleanValue()) {
/*     */       
/* 144 */       if (maxX == -1.0F) {
/*     */         
/* 146 */         minX = 0.25F;
/* 147 */         maxX = 0.75F;
/*     */       } 
/*     */       
/* 150 */       minZ = 0.0F;
/* 151 */       maxZ = 1.0F;
/*     */     } 
/*     */     
/* 154 */     if (hasVerticalBeam.booleanValue()) {
/*     */       
/* 156 */       minY = 0.0F;
/* 157 */       maxY = 1.0F;
/* 158 */       if (maxX == -1.0F) {
/*     */         
/* 160 */         minX = 0.25F;
/* 161 */         maxX = 0.75F;
/*     */       } 
/* 163 */       if (maxZ == -1.0F)
/*     */       {
/* 165 */         minZ = 0.25F;
/* 166 */         maxZ = 0.75F;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 171 */       minY = 0.5F;
/* 172 */       maxY = 1.0F;
/*     */     } 
/*     */     
/* 175 */     renderblocks.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
/*     */     
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean renderWoodSupportBeamV(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/* 182 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */     
/* 184 */     Boolean hasVerticalBeam = Boolean.valueOf(false);
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
/* 203 */     if ((blockAccess.func_147439_a(i, j - 1, k).func_149662_c() || TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j - 1, k))) && TFCBlocks.isBlockVSupport(block)) {
/*     */       
/* 205 */       renderblocks.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 206 */       renderblocks.func_147784_q(block, i, j, k);
/* 207 */       hasVerticalBeam = Boolean.valueOf(true);
/*     */     } 
/*     */ 
/*     */     
/* 211 */     if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i - 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i - 1, j, k))) {
/*     */       
/* 213 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k)))
/*     */       {
/* 215 */         if (hasVerticalBeam.booleanValue())
/*     */         {
/* 217 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
/* 218 */           renderblocks.func_147784_q(block, i, j, k);
/* 219 */           renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/* 220 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/* 222 */         else if (!hasVerticalBeam.booleanValue())
/*     */         {
/* 224 */           renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/* 225 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 231 */       else if (hasVerticalBeam.booleanValue())
/*     */       {
/* 233 */         renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
/* 234 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else
/*     */       {
/* 238 */         renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 239 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 244 */     else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k))) {
/*     */       
/* 246 */       if (hasVerticalBeam.booleanValue()) {
/*     */         
/* 248 */         renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/* 249 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else {
/*     */         
/* 253 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
/* 254 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 259 */     if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k - 1))) {
/*     */       
/* 261 */       if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1)))
/*     */       {
/* 263 */         if (hasVerticalBeam.booleanValue())
/*     */         {
/* 265 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
/* 266 */           renderblocks.func_147784_q(block, i, j, k);
/* 267 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
/* 268 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         }
/* 270 */         else if (!hasVerticalBeam.booleanValue())
/*     */         {
/* 272 */           renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 1.0D);
/* 273 */           renderblocks.func_147784_q(block, i, j, k);
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 279 */       else if (hasVerticalBeam.booleanValue())
/*     */       {
/* 281 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
/* 282 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else
/*     */       {
/* 286 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.75D);
/* 287 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 292 */     else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1))) {
/*     */       
/* 294 */       if (hasVerticalBeam.booleanValue()) {
/*     */         
/* 296 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
/* 297 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       }
/*     */       else {
/*     */         
/* 301 */         renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 1.0D);
/* 302 */         renderblocks.func_147784_q(block, i, j, k);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 307 */     float minX = 0.25F;
/* 308 */     float minY = 0.0F;
/* 309 */     float minZ = 0.25F;
/*     */     
/* 311 */     float maxX = 0.75F;
/* 312 */     float maxY = 1.0F;
/* 313 */     float maxZ = 0.75F;
/*     */ 
/*     */     
/* 316 */     renderblocks.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
/*     */     
/* 318 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSurroundedByWater(IBlockAccess access, int i, int j, int k) {
/* 323 */     if (access.func_147439_a(i, j + 1, k).func_149688_o() == Material.field_151586_h) {
/* 324 */       return true;
/*     */     }
/* 326 */     return (access.func_147439_a(i + 1, j, k).func_149688_o() == Material.field_151586_h || access.func_147439_a(i - 1, j, k).func_149688_o() == Material.field_151586_h || access
/* 327 */       .func_147439_a(i, j, k + 1).func_149688_o() == Material.field_151586_h || access.func_147439_a(i, j, k - 1).func_149688_o() == Material.field_151586_h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 334 */     renderer.func_147757_a(block.func_149691_a(0, metadata));
/* 335 */     if (modelID == TFCBlocks.woodSupportRenderIdH) {
/*     */       
/* 337 */       renderer.func_147782_a(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
/* 338 */       renderInvBlock(block, renderer);
/*     */     }
/* 340 */     else if (modelID == TFCBlocks.woodSupportRenderIdV) {
/*     */       
/* 342 */       renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/* 343 */       renderInvBlock(block, renderer);
/*     */     } 
/* 345 */     renderer.func_147771_a();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/* 352 */     if (modelId == TFCBlocks.woodSupportRenderIdH)
/*     */     {
/* 354 */       return renderWoodSupportBeamH(block, i, j, k, renderer);
/*     */     }
/* 356 */     if (modelId == TFCBlocks.woodSupportRenderIdV)
/*     */     {
/* 358 */       return renderWoodSupportBeamV(block, i, j, k, renderer);
/*     */     }
/* 360 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 366 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 372 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, RenderBlocks renderer) {
/* 377 */     Tessellator var14 = Tessellator.field_78398_a;
/* 378 */     var14.func_78382_b();
/* 379 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 380 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
/* 381 */     var14.func_78381_a();
/* 382 */     var14.func_78382_b();
/* 383 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 384 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 0));
/* 385 */     var14.func_78381_a();
/* 386 */     var14.func_78382_b();
/* 387 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 388 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
/* 389 */     var14.func_78381_a();
/* 390 */     var14.func_78382_b();
/* 391 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 392 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
/* 393 */     var14.func_78381_a();
/* 394 */     var14.func_78382_b();
/* 395 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 396 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
/* 397 */     var14.func_78381_a();
/* 398 */     var14.func_78382_b();
/* 399 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 400 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
/* 401 */     var14.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSupportBeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */