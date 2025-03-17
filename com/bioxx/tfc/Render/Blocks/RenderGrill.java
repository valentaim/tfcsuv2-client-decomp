/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockGrill;
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
/*     */ public class RenderGrill
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
/*  55 */     BlockGrill grill = (BlockGrill)block;
/*  56 */     int meta = world.func_72805_g(i, j, k);
/*     */     
/*  58 */     if (!grill.isGrillOpen(meta)) {
/*  59 */       renderer.func_147782_a(0.0D, -0.05000000074505806D, 0.0D, 1.0D, 0.0D, 1.0D);
/*     */     }
/*  61 */     renderer.func_147784_q(block, i, j, k);
/*     */     
/*  63 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  68 */     renderer.field_147875_q = i;
/*  69 */     renderer.field_147873_r = i;
/*  70 */     renderer.field_147869_t = i;
/*  71 */     renderer.field_147871_s = i;
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
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 106 */     renderer.func_147782_a(0.0D, 0.5D, 0.0D, 1.0D, 0.550000011920929D, 1.0D);
/* 107 */     renderInvBlock(block, metadata, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 120 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 125 */     Tessellator var14 = Tessellator.field_78398_a;
/* 126 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 127 */     var14.func_78382_b();
/* 128 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 129 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 130 */     var14.func_78381_a();
/* 131 */     var14.func_78382_b();
/* 132 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 133 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 134 */     var14.func_78381_a();
/* 135 */     var14.func_78382_b();
/* 136 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 137 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 138 */     var14.func_78381_a();
/* 139 */     var14.func_78382_b();
/* 140 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 141 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 142 */     var14.func_78381_a();
/* 143 */     var14.func_78382_b();
/* 144 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 145 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 146 */     var14.func_78381_a();
/* 147 */     var14.func_78382_b();
/* 148 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 149 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 150 */     var14.func_78381_a();
/* 151 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */