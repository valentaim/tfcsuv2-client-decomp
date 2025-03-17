/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
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
/*     */ public class RenderCrucible
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  17 */     float s0 = 0.0F;
/*  18 */     float s1 = 0.0625F;
/*     */     
/*  20 */     float s3 = 0.1875F;
/*     */     
/*  22 */     float s13 = 0.8125F;
/*     */     
/*  24 */     float s15 = 0.9375F;
/*     */     
/*  26 */     renderer.func_147782_a(s1, s0, s1, s15, s1, s15);
/*  27 */     renderer.func_147784_q(block, i, j, k);
/*  28 */     renderer.func_147782_a(s1, s1, s3, s3, s15, s13);
/*  29 */     renderer.func_147784_q(block, i, j, k);
/*  30 */     renderer.func_147782_a(s13, s1, s3, s15, s15, s13);
/*  31 */     renderer.func_147784_q(block, i, j, k);
/*  32 */     renderer.func_147782_a(s1, s1, s1, s15, s15, s3);
/*  33 */     renderer.func_147784_q(block, i, j, k);
/*  34 */     renderer.func_147782_a(s1, s1, s13, s15, s15, s15);
/*  35 */     renderer.func_147784_q(block, i, j, k);
/*  36 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  42 */     float s0 = 0.0F;
/*  43 */     float s1 = 0.0625F;
/*     */     
/*  45 */     float s3 = 0.1875F;
/*  46 */     float s13 = 0.8125F;
/*     */     
/*  48 */     float s15 = 0.9375F;
/*     */     
/*  50 */     renderer.func_147782_a(s1, s0, s1, s15, s1, s15);
/*  51 */     renderInvBlock(block, metadata, renderer);
/*  52 */     renderer.func_147782_a(s1, s1, s3, s3, s15, s13);
/*  53 */     renderInvBlock(block, metadata, renderer);
/*  54 */     renderer.func_147782_a(s13, s1, s3, s15, s15, s13);
/*  55 */     renderInvBlock(block, metadata, renderer);
/*  56 */     renderer.func_147782_a(s1, s1, s1, s15, s15, s3);
/*  57 */     renderInvBlock(block, metadata, renderer);
/*  58 */     renderer.func_147782_a(s1, s1, s13, s15, s15, s15);
/*  59 */     renderInvBlock(block, metadata, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/*  65 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/*  71 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/*  76 */     Tessellator var14 = Tessellator.field_78398_a;
/*  77 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  78 */     var14.func_78382_b();
/*  79 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/*  80 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/*  81 */     var14.func_78381_a();
/*  82 */     var14.func_78382_b();
/*  83 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/*  84 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/*  85 */     var14.func_78381_a();
/*  86 */     var14.func_78382_b();
/*  87 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  88 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/*  89 */     var14.func_78381_a();
/*  90 */     var14.func_78382_b();
/*  91 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/*  92 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/*  93 */     var14.func_78381_a();
/*  94 */     var14.func_78382_b();
/*  95 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  96 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/*  97 */     var14.func_78381_a();
/*  98 */     var14.func_78382_b();
/*  99 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 100 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 101 */     var14.func_78381_a();
/* 102 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */