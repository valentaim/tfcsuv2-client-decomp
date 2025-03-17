/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TELoom;
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
/*     */ public class RenderLoom
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   private static final float MIN_X = 0.0F;
/*     */   private static final float MAX_X = 1.0F;
/*     */   private static final float MIN_Y = 0.0F;
/*     */   private static final float MAX_Y = 1.0F;
/*     */   private static final float MIN_Z = 0.0F;
/*     */   private static final float MAX_Z = 1.0F;
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*     */     Block materialBlock;
/*  26 */     TELoom te = (TELoom)world.func_147438_o(x, y, z);
/*     */     
/*  28 */     if (te.loomType < 16) {
/*     */       
/*  30 */       materialBlock = TFCBlocks.woodSupportH;
/*     */     }
/*     */     else {
/*     */       
/*  34 */       materialBlock = TFCBlocks.woodSupportH2;
/*     */     } 
/*  36 */     renderer.field_147837_f = true;
/*  37 */     GL11.glPushMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  42 */     setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.0F, 0.75F, 0.19999999F, 1.0F, 0.85F);
/*  43 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */     
/*  45 */     setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.0F, 0.75F, 0.9F, 1.0F, 0.85F);
/*  46 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */ 
/*     */ 
/*     */     
/*  50 */     setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.25F, 0.5F, 0.19999999F, 0.3F, 0.75F);
/*  51 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */     
/*  53 */     setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.05F, 0.5F, 0.19999999F, 0.100000024F, 0.75F);
/*  54 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */ 
/*     */     
/*  57 */     setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.25F, 0.5F, 0.9F, 0.3F, 0.75F);
/*  58 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */     
/*  60 */     setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.05F, 0.5F, 0.9F, 0.100000024F, 0.75F);
/*  61 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */ 
/*     */     
/*  64 */     setRotatedRenderBounds(renderer, te.rotation, 0.19999999F, 0.8F, 0.75F, 0.8F, 0.9F, 0.85F);
/*  65 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */     
/*  67 */     setRotatedRenderBounds(renderer, te.rotation, 0.19999999F, 0.0F, 0.75F, 0.8F, 0.1F, 0.85F);
/*  68 */     renderer.func_147784_q(materialBlock, x, y, z);
/*     */     
/*  70 */     rotate(renderer, 0);
/*  71 */     renderer.field_147837_f = false;
/*  72 */     GL11.glPopMatrix();
/*  73 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  78 */     renderer.field_147875_q = i;
/*  79 */     renderer.field_147873_r = i;
/*  80 */     renderer.field_147869_t = i;
/*  81 */     renderer.field_147871_s = i;
/*     */   }
/*     */   
/*     */   private void setRotatedRenderBounds(RenderBlocks renderer, byte rotation, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
/*  85 */     switch (rotation) { case 0:
/*  86 */         renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ); break;
/*  87 */       case 1: renderer.func_147782_a((1.0F - maxZ), minY, minX, (1.0F - minZ), maxY, maxX); break;
/*  88 */       case 2: renderer.func_147782_a(minX, minY, (1.0F - maxZ), maxX, maxY, (1.0F - minZ)); break;
/*  89 */       case 3: renderer.func_147782_a(minZ, minY, minX, maxZ, maxY, maxX);
/*     */         break; }
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
/*     */     Block materialBlock;
/*  99 */     if (meta < 16) {
/*     */       
/* 101 */       materialBlock = TFCBlocks.woodSupportH;
/*     */     }
/*     */     else {
/*     */       
/* 105 */       materialBlock = TFCBlocks.woodSupportH2;
/*     */     } 
/*     */     
/* 108 */     GL11.glPushMatrix();
/* 109 */     GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
/*     */     
/* 111 */     renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.75D, 0.19999998807907104D, 1.0D, 0.8500000238418579D);
/* 112 */     rotate(renderer, 1);
/* 113 */     renderInvBlock(materialBlock, meta, renderer);
/*     */     
/* 115 */     renderer.func_147782_a(0.800000011920929D, 0.0D, 0.75D, 0.8999999761581421D, 1.0D, 0.8500000238418579D);
/* 116 */     rotate(renderer, 1);
/* 117 */     renderInvBlock(materialBlock, meta, renderer);
/*     */ 
/*     */ 
/*     */     
/* 121 */     renderer.func_147782_a(0.10000000149011612D, 0.3499999940395355D, 0.6000000238418579D, 0.19999998807907104D, 0.3999999761581421D, 0.75D);
/* 122 */     rotate(renderer, 1);
/* 123 */     renderInvBlock(materialBlock, meta, renderer);
/*     */     
/* 125 */     renderer.func_147782_a(0.10000000149011612D, 0.15000000596046448D, 0.6000000238418579D, 0.19999998807907104D, 0.19999998807907104D, 0.75D);
/* 126 */     rotate(renderer, 1);
/* 127 */     renderInvBlock(materialBlock, meta, renderer);
/*     */ 
/*     */     
/* 130 */     renderer.func_147782_a(0.800000011920929D, 0.3499999940395355D, 0.6000000238418579D, 0.8999999761581421D, 0.3999999761581421D, 0.75D);
/* 131 */     rotate(renderer, 1);
/* 132 */     renderInvBlock(materialBlock, meta, renderer);
/*     */     
/* 134 */     renderer.func_147782_a(0.800000011920929D, 0.15000000596046448D, 0.6000000238418579D, 0.8999999761581421D, 0.19999998807907104D, 0.75D);
/* 135 */     rotate(renderer, 1);
/* 136 */     renderInvBlock(materialBlock, meta, renderer);
/*     */ 
/*     */     
/* 139 */     renderer.func_147782_a(0.19999998807907104D, 0.800000011920929D, 0.75D, 0.800000011920929D, 0.8999999761581421D, 0.8500000238418579D);
/* 140 */     rotate(renderer, 1);
/* 141 */     renderInvBlock(materialBlock, meta, renderer);
/*     */     
/* 143 */     renderer.func_147782_a(0.19999998807907104D, 0.0D, 0.75D, 0.800000011920929D, 0.10000000149011612D, 0.8500000238418579D);
/* 144 */     rotate(renderer, 1);
/* 145 */     renderInvBlock(materialBlock, meta, renderer);
/*     */     
/* 147 */     rotate(renderer, 0);
/* 148 */     GL11.glPopMatrix();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 160 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 165 */     Tessellator var14 = Tessellator.field_78398_a;
/* 166 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 167 */     var14.func_78382_b();
/* 168 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 169 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 170 */     var14.func_78381_a();
/* 171 */     var14.func_78382_b();
/* 172 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 173 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 174 */     var14.func_78381_a();
/* 175 */     var14.func_78382_b();
/* 176 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 177 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 178 */     var14.func_78381_a();
/* 179 */     var14.func_78382_b();
/* 180 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 181 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 182 */     var14.func_78381_a();
/* 183 */     var14.func_78382_b();
/* 184 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 185 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 186 */     var14.func_78381_a();
/* 187 */     var14.func_78382_b();
/* 188 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 189 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 190 */     var14.func_78381_a();
/* 191 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlockHoop(Block block, int m, RenderBlocks renderer) {
/* 196 */     Tessellator var14 = Tessellator.field_78398_a;
/* 197 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 198 */     var14.func_78382_b();
/* 199 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 200 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(10, m));
/* 201 */     var14.func_78381_a();
/* 202 */     var14.func_78382_b();
/* 203 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 204 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(11, m));
/* 205 */     var14.func_78381_a();
/* 206 */     var14.func_78382_b();
/* 207 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 208 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(12, m));
/* 209 */     var14.func_78381_a();
/* 210 */     var14.func_78382_b();
/* 211 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 212 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(13, m));
/* 213 */     var14.func_78381_a();
/* 214 */     var14.func_78382_b();
/* 215 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 216 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(14, m));
/* 217 */     var14.func_78381_a();
/* 218 */     var14.func_78382_b();
/* 219 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 220 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(15, m));
/* 221 */     var14.func_78381_a();
/* 222 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */