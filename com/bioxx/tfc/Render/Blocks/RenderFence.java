/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence;
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
/*     */ public class RenderFence
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
/*  23 */     BlockTFCFence par1BlockFence = (BlockTFCFence)block;
/*  24 */     boolean flag = false;
/*  25 */     float f = 0.375F;
/*  26 */     float f1 = 0.625F;
/*  27 */     renderblocks.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);
/*  28 */     renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*  29 */     flag = true;
/*  30 */     boolean flag1 = false;
/*  31 */     boolean flag2 = false;
/*     */     
/*  33 */     if (par1BlockFence.func_149826_e(renderblocks.field_147845_a, x - 1, y, z) || par1BlockFence.func_149826_e(renderblocks.field_147845_a, x + 1, y, z)) {
/*  34 */       flag1 = true;
/*     */     }
/*  36 */     if (par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z - 1) || par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z + 1)) {
/*  37 */       flag2 = true;
/*     */     }
/*  39 */     boolean flag3 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x - 1, y, z);
/*  40 */     boolean flag4 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x + 1, y, z);
/*  41 */     boolean flag5 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z - 1);
/*  42 */     boolean flag6 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z + 1);
/*     */     
/*  44 */     if (!flag1 && !flag2) {
/*  45 */       flag1 = true;
/*     */     }
/*  47 */     f = 0.4375F;
/*  48 */     f1 = 0.5625F;
/*  49 */     float f2 = 0.75F;
/*  50 */     float f3 = 0.9375F;
/*  51 */     float f4 = flag3 ? 0.0F : f;
/*  52 */     float f5 = flag4 ? 1.0F : f1;
/*  53 */     float f6 = flag5 ? 0.0F : f;
/*  54 */     float f7 = flag6 ? 1.0F : f1;
/*     */     
/*  56 */     if (flag1) {
/*     */       
/*  58 */       renderblocks.func_147782_a(f4, f2 + 0.001D, f + 0.001D, f5, f3 + 0.001D, f1 + 0.001D);
/*  59 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  61 */       renderblocks.func_147782_a(f5, f2, f1 - 0.001D, f4, f3, f - 0.001D);
/*  62 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  64 */       flag = true;
/*     */     } 
/*     */     
/*  67 */     if (flag2) {
/*     */       
/*  69 */       renderblocks.func_147782_a(f - 0.001D, f2 + 0.001D, f6, f1 - 0.001D, f3 + 0.001D, f7);
/*  70 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  72 */       renderblocks.func_147782_a(f1 + 0.001D, f2, f7, f + 0.001D, f3, f6);
/*  73 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*  74 */       flag = true;
/*     */     } 
/*     */     
/*  77 */     f2 = 0.375F;
/*  78 */     f3 = 0.5625F;
/*     */     
/*  80 */     if (flag1) {
/*     */       
/*  82 */       renderblocks.func_147782_a(f4, f2 + 0.001D, f + 0.001D, f5, f3 + 0.001D, f1 + 0.001D);
/*  83 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  85 */       renderblocks.func_147782_a(f5, f2, f1 - 0.001D, f4, f3, f - 0.001D);
/*  86 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  88 */       flag = true;
/*     */     } 
/*     */     
/*  91 */     if (flag2) {
/*     */       
/*  93 */       renderblocks.func_147782_a(f - 0.001D, f2 + 0.001D, f6, f1 - 0.001D, f3 + 0.001D, f7);
/*  94 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*     */       
/*  96 */       renderblocks.func_147782_a(f1 + 0.001D, f2, f7, f + 0.001D, f3, f6);
/*  97 */       renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
/*  98 */       flag = true;
/*     */     } 
/*     */     
/* 101 */     par1BlockFence.func_149719_a(renderblocks.field_147845_a, x, y, z);
/* 102 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 108 */     float f = 0.375F;
/* 109 */     float f1 = 0.625F;
/* 110 */     renderer.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);
/*     */     
/* 112 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 114 */     renderer.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);
/*     */     
/* 116 */     renderInvBlock2(block, metadata, renderer);
/*     */     
/* 118 */     f = 0.4375F;
/* 119 */     f1 = 0.5625F;
/* 120 */     float f2 = 0.75F;
/* 121 */     float f3 = 0.9375F;
/*     */ 
/*     */     
/* 124 */     float f6 = 0.0F;
/* 125 */     float f7 = 1.0F;
/*     */     
/* 127 */     renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, f7 / 2.0D);
/* 128 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 130 */     renderer.func_147782_a(f1 + 0.001D, f2, f7 / 2.0D, f + 0.001D, f3, f6);
/* 131 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 133 */     f2 = 0.375F;
/* 134 */     f3 = 0.5625F;
/*     */     
/* 136 */     renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, f7 / 2.0D);
/* 137 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 139 */     renderer.func_147782_a(f1 + 0.001D, f2, f7 / 2.0D, f + 0.001D, f3, f6);
/* 140 */     renderInvBlock(block, metadata, renderer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 145 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 151 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 156 */     Tessellator var14 = Tessellator.field_78398_a;
/* 157 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 158 */     var14.func_78382_b();
/* 159 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 160 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 161 */     var14.func_78381_a();
/* 162 */     var14.func_78382_b();
/* 163 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 164 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 165 */     var14.func_78381_a();
/* 166 */     var14.func_78382_b();
/* 167 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 168 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 169 */     var14.func_78381_a();
/* 170 */     var14.func_78382_b();
/* 171 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 172 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 173 */     var14.func_78381_a();
/* 174 */     var14.func_78382_b();
/* 175 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 176 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 177 */     var14.func_78381_a();
/* 178 */     var14.func_78382_b();
/* 179 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 180 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 181 */     var14.func_78381_a();
/* 182 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock2(Block block, int m, RenderBlocks renderer) {
/* 187 */     Tessellator var14 = Tessellator.field_78398_a;
/* 188 */     GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
/* 189 */     var14.func_78382_b();
/* 190 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 191 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 192 */     var14.func_78381_a();
/* 193 */     var14.func_78382_b();
/* 194 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 195 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 196 */     var14.func_78381_a();
/* 197 */     var14.func_78382_b();
/* 198 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 199 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 200 */     var14.func_78381_a();
/* 201 */     var14.func_78382_b();
/* 202 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 203 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 204 */     var14.func_78381_a();
/* 205 */     var14.func_78382_b();
/* 206 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 207 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 208 */     var14.func_78381_a();
/* 209 */     var14.func_78382_b();
/* 210 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 211 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 212 */     var14.func_78381_a();
/* 213 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */