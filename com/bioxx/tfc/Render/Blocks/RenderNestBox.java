/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
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
/*     */ public class RenderNestBox
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  24 */     renderer.field_147837_f = true;
/*  25 */     renderer.func_147782_a(0.15000000596046448D, 0.10000000149011612D, 0.15000000596046448D, 0.8500000238418579D, 0.10000000149011612D, 0.8500000238418579D);
/*  26 */     renderer.func_147784_q(TFCBlocks.planks, i, j, k);
/*     */     
/*  28 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.15000000596046448D, 0.15000000596046448D, 0.4000000059604645D, 0.8500000238418579D);
/*  29 */     rotate(renderer, 1);
/*  30 */     renderer.func_147784_q(TFCBlocks.planks, i, j, k);
/*  31 */     rotate(renderer, 0);
/*  32 */     renderer.func_147784_q(block, i, j, k);
/*     */     
/*  34 */     renderer.func_147782_a(0.8500000238418579D, 0.0D, 0.15000000596046448D, 0.8999999761581421D, 0.4000000059604645D, 0.8500000238418579D);
/*  35 */     rotate(renderer, 1);
/*  36 */     renderer.func_147784_q(TFCBlocks.planks, i, j, k);
/*  37 */     rotate(renderer, 0);
/*  38 */     renderer.func_147784_q(block, i, j, k);
/*     */     
/*  40 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.8999999761581421D, 0.4000000059604645D, 0.15000000596046448D);
/*  41 */     rotate(renderer, 1);
/*  42 */     renderer.func_147784_q(TFCBlocks.planks, i, j, k);
/*  43 */     rotate(renderer, 0);
/*  44 */     renderer.func_147784_q(block, i, j, k);
/*     */     
/*  46 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.8500000238418579D, 0.8999999761581421D, 0.4000000059604645D, 0.8999999761581421D);
/*  47 */     rotate(renderer, 1);
/*  48 */     renderer.func_147784_q(TFCBlocks.planks, i, j, k);
/*  49 */     rotate(renderer, 0);
/*  50 */     renderer.func_147784_q(block, i, j, k);
/*  51 */     renderer.field_147837_f = false;
/*  52 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  57 */     renderer.field_147875_q = i;
/*  58 */     renderer.field_147873_r = i;
/*  59 */     renderer.field_147869_t = i;
/*  60 */     renderer.field_147871_s = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  66 */     renderer.func_147782_a(0.15000000596046448D, 0.20000000298023224D, 0.15000000596046448D, 0.8500000238418579D, 0.10000000149011612D, 0.8500000238418579D);
/*  67 */     rotate(renderer, 1);
/*  68 */     renderInvBlock(TFCBlocks.planks, metadata, renderer);
/*     */     
/*  70 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.15000000596046448D, 0.15000000596046448D, 0.4000000059604645D, 0.8500000238418579D);
/*  71 */     rotate(renderer, 1);
/*  72 */     renderInvBlock(TFCBlocks.planks, metadata, renderer);
/*  73 */     rotate(renderer, 0);
/*  74 */     renderInvBlock(block, metadata, renderer);
/*     */     
/*  76 */     renderer.func_147782_a(0.8500000238418579D, 0.0D, 0.15000000596046448D, 0.8999999761581421D, 0.4000000059604645D, 0.8500000238418579D);
/*  77 */     rotate(renderer, 1);
/*  78 */     renderInvBlock(TFCBlocks.planks, metadata, renderer);
/*  79 */     rotate(renderer, 0);
/*  80 */     renderInvBlock(block, metadata, renderer);
/*     */     
/*  82 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.8999999761581421D, 0.4000000059604645D, 0.15000000596046448D);
/*  83 */     rotate(renderer, 1);
/*  84 */     renderInvBlock(TFCBlocks.planks, metadata, renderer);
/*  85 */     rotate(renderer, 0);
/*  86 */     renderInvBlock(block, metadata, renderer);
/*     */     
/*  88 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.8500000238418579D, 0.8999999761581421D, 0.4000000059604645D, 0.8999999761581421D);
/*  89 */     rotate(renderer, 1);
/*  90 */     renderInvBlock(TFCBlocks.planks, metadata, renderer);
/*  91 */     rotate(renderer, 0);
/*  92 */     renderInvBlock(block, metadata, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 104 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 109 */     Tessellator var14 = Tessellator.field_78398_a;
/* 110 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 111 */     var14.func_78382_b();
/* 112 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 113 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 114 */     var14.func_78381_a();
/* 115 */     var14.func_78382_b();
/* 116 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 117 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 118 */     var14.func_78381_a();
/* 119 */     var14.func_78382_b();
/* 120 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 121 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 122 */     var14.func_78381_a();
/* 123 */     var14.func_78382_b();
/* 124 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 125 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 126 */     var14.func_78381_a();
/* 127 */     var14.func_78382_b();
/* 128 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 129 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 130 */     var14.func_78381_a();
/* 131 */     var14.func_78382_b();
/* 132 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 133 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 134 */     var14.func_78381_a();
/* 135 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderNestBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */