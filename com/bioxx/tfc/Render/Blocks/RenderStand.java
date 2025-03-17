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
/*     */ public class RenderStand
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  23 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  28 */     renderer.field_147875_q = i;
/*  29 */     renderer.field_147873_r = i;
/*  30 */     renderer.field_147869_t = i;
/*  31 */     renderer.field_147871_s = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  37 */     Block blockToRender = (block == TFCBlocks.armorStand) ? TFCBlocks.planks : TFCBlocks.planks2;
/*  38 */     Block woodblock = (block == TFCBlocks.armorStand) ? TFCBlocks.woodSupportH : TFCBlocks.woodSupportH2;
/*     */     
/*  40 */     float yScale = 0.7F;
/*  41 */     float blockScale = 0.5F;
/*     */ 
/*     */     
/*  44 */     renderer.func_147782_a((0.44F * blockScale), (1.45F * yScale * blockScale), (0.2F * blockScale), (0.56F * blockScale), (1.55F * yScale * blockScale), (0.8F * blockScale));
/*  45 */     renderInvBlock(woodblock, metadata, renderer);
/*     */     
/*  47 */     renderer.func_147782_a((0.45F * blockScale), (0.201F * yScale * blockScale), (0.35F * blockScale), (0.55F * blockScale), (1.45F * yScale * blockScale), (0.45F * blockScale));
/*  48 */     renderInvBlock(woodblock, metadata, renderer);
/*     */     
/*  50 */     renderer.func_147782_a((0.45F * blockScale), (0.201F * yScale * blockScale), (0.55F * blockScale), (0.55F * blockScale), (1.45F * yScale * blockScale), (0.65F * blockScale));
/*  51 */     renderInvBlock(woodblock, metadata, renderer);
/*     */ 
/*     */     
/*  54 */     renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.2D * yScale, 0.800000011920929D);
/*  55 */     renderInvBlock(blockToRender, metadata, renderer, false);
/*     */ 
/*     */     
/*  58 */     renderer.func_147782_a((0.45F * blockScale), (1.45F * yScale * blockScale), (0.45F * blockScale), (0.55F * blockScale), (1.9F * yScale * blockScale), (0.55F * blockScale));
/*  59 */     renderInvBlock(woodblock, metadata, renderer);
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
/*  76 */     renderInvBlock(block, m, renderer, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer, boolean b) {
/*  81 */     Tessellator var14 = Tessellator.field_78398_a;
/*  82 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/*  83 */     if (b) GL11.glScalef(2.0F, 2.0F, 2.0F); 
/*  84 */     var14.func_78382_b();
/*  85 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/*  86 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/*  87 */     var14.func_78381_a();
/*  88 */     var14.func_78382_b();
/*  89 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/*  90 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/*  91 */     var14.func_78381_a();
/*  92 */     var14.func_78382_b();
/*  93 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/*  94 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/*  95 */     var14.func_78381_a();
/*  96 */     var14.func_78382_b();
/*  97 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/*  98 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/*  99 */     var14.func_78381_a();
/* 100 */     var14.func_78382_b();
/* 101 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 102 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 103 */     var14.func_78381_a();
/* 104 */     var14.func_78382_b();
/* 105 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 106 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 107 */     var14.func_78381_a();
/* 108 */     if (b) GL11.glScalef(0.5F, 0.5F, 0.5F); 
/* 109 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */