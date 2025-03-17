/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
/*     */ import com.bioxx.tfc.TileEntities.TEBloomery;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderBloomery
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean render(Block block, int i, int j, int k, RenderBlocks renderblocks) {
/*  18 */     IBlockAccess blockAccess = renderblocks.field_147845_a;
/*     */     
/*  20 */     int meta = blockAccess.func_72805_g(i, j, k);
/*  21 */     int dir = meta & 0x3;
/*  22 */     TEBloomery te = (TEBloomery)blockAccess.func_147438_o(i, j, k);
/*  23 */     if (te != null && 
/*  24 */       te.isFlipped)
/*  25 */       dir = BlockEarlyBloomery.flipDir(dir); 
/*  26 */     float f = 0.125F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     if (!BlockEarlyBloomery.isOpen(meta)) {
/*     */       
/*  33 */       switch (dir) {
/*     */         
/*     */         case 0:
/*  36 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, f); break;
/*     */         case 1:
/*  38 */           renderblocks.func_147782_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); break;
/*     */         case 2:
/*  40 */           renderblocks.func_147782_a(0.0D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D); break;
/*     */         case 3:
/*  42 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, f, 1.0D, 1.0D); break;
/*     */       } 
/*  44 */       renderblocks.func_147784_q(block, i, j, k);
/*     */ 
/*     */     
/*     */     }
/*  48 */     else if (dir == 0) {
/*     */ 
/*     */       
/*  51 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, f);
/*  52 */       renderblocks.func_147784_q(block, i, j, k);
/*  53 */       renderblocks.func_147782_a(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, f);
/*  54 */       renderblocks.func_147784_q(block, i, j, k);
/*  55 */       renderblocks.func_147782_a(0.0D, 0.0625D, 0.0D, 0.0625D, 0.9375D, f);
/*  56 */       renderblocks.func_147784_q(block, i, j, k);
/*  57 */       renderblocks.func_147782_a(0.9375D, 0.0625D, 0.0D, 1.0D, 0.9375D, f);
/*  58 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*  60 */       renderblocks.func_147782_a(0.0D, 0.0625D, 0.0625D, f, 0.9375D, 0.5D);
/*  61 */       renderblocks.func_147784_q(block, i, j, k);
/*  62 */       renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.0625D, 1.0D, 0.9375D, 0.5D);
/*  63 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     }
/*  65 */     else if (dir == 1) {
/*     */ 
/*     */       
/*  68 */       renderblocks.func_147782_a((1.0F - f), 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
/*  69 */       renderblocks.func_147784_q(block, i, j, k);
/*  70 */       renderblocks.func_147782_a((1.0F - f), 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
/*  71 */       renderblocks.func_147784_q(block, i, j, k);
/*  72 */       renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.0D, 1.0D, 0.9375D, 0.0625D);
/*  73 */       renderblocks.func_147784_q(block, i, j, k);
/*  74 */       renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.9375D, 1.0D, 0.9375D, 1.0D);
/*  75 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*  77 */       renderblocks.func_147782_a(0.5D, 0.0625D, (1.0F - f), 0.9375D, 0.9375D, 1.0D);
/*  78 */       renderblocks.func_147784_q(block, i, j, k);
/*  79 */       renderblocks.func_147782_a(0.5D, 0.0625D, 0.0D, 0.9375D, 0.9375D, f);
/*  80 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     }
/*  82 */     else if (dir == 2) {
/*     */ 
/*     */       
/*  85 */       renderblocks.func_147782_a(0.0D, 0.0D, (1.0F - f), 1.0D, 0.0625D, 1.0D);
/*  86 */       renderblocks.func_147784_q(block, i, j, k);
/*  87 */       renderblocks.func_147782_a(0.0D, 0.9375D, (1.0F - f), 1.0D, 1.0D, 1.0D);
/*  88 */       renderblocks.func_147784_q(block, i, j, k);
/*  89 */       renderblocks.func_147782_a(0.0D, 0.0625D, (1.0F - f), 0.0625D, 0.9375D, 1.0D);
/*  90 */       renderblocks.func_147784_q(block, i, j, k);
/*  91 */       renderblocks.func_147782_a(0.9375D, 0.0625D, (1.0F - f), 1.0D, 0.9375D, 1.0D);
/*  92 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/*  94 */       renderblocks.func_147782_a(0.0D, 0.0625D, 0.5D, f, 0.9375D, 0.9375D);
/*  95 */       renderblocks.func_147784_q(block, i, j, k);
/*  96 */       renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.5D, 1.0D, 0.9375D, 0.9375D);
/*  97 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     }
/*  99 */     else if (dir == 3) {
/*     */ 
/*     */       
/* 102 */       renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, f, 0.0625D, 1.0D);
/* 103 */       renderblocks.func_147784_q(block, i, j, k);
/* 104 */       renderblocks.func_147782_a(0.0D, 0.9375D, 0.0D, f, 1.0D, 1.0D);
/* 105 */       renderblocks.func_147784_q(block, i, j, k);
/* 106 */       renderblocks.func_147782_a(0.0D, 0.0625D, 0.0D, f, 0.9375D, 0.0625D);
/* 107 */       renderblocks.func_147784_q(block, i, j, k);
/* 108 */       renderblocks.func_147782_a(0.0D, 0.0625D, 0.9375D, f, 0.9375D, 1.0D);
/* 109 */       renderblocks.func_147784_q(block, i, j, k);
/*     */       
/* 111 */       renderblocks.func_147782_a(0.0625D, 0.0625D, (1.0F - f), 0.5D, 0.9375D, 1.0D);
/* 112 */       renderblocks.func_147784_q(block, i, j, k);
/* 113 */       renderblocks.func_147782_a(0.0625D, 0.0625D, 0.0D, 0.5D, 0.9375D, f);
/* 114 */       renderblocks.func_147784_q(block, i, j, k);
/*     */     } 
/*     */     
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
/* 123 */     if (modelId == TFCBlocks.bloomeryRenderId) {
/*     */       
/* 125 */       renderer.func_147782_a(0.5D, 0.0D, 0.0D, 0.699999988079071D, 1.0D, 1.0D);
/* 126 */       renderInvBlock(block, metadata, renderer);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 133 */     if (modelId == TFCBlocks.bloomeryRenderId)
/* 134 */       return render(block, x, y, z, renderer); 
/* 135 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 147 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 152 */     Tessellator var14 = Tessellator.field_78398_a;
/* 153 */     int meta = m;
/* 154 */     if (meta >= 8)
/* 155 */       meta -= 8; 
/* 156 */     var14.func_78382_b();
/* 157 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 158 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 159 */     var14.func_78381_a();
/* 160 */     var14.func_78382_b();
/* 161 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 162 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 163 */     var14.func_78381_a();
/* 164 */     var14.func_78382_b();
/* 165 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 166 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 167 */     var14.func_78381_a();
/* 168 */     var14.func_78382_b();
/* 169 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 170 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 171 */     var14.func_78381_a();
/* 172 */     var14.func_78382_b();
/* 173 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 174 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 175 */     var14.func_78381_a();
/* 176 */     var14.func_78382_b();
/* 177 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 178 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 179 */     var14.func_78381_a();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */