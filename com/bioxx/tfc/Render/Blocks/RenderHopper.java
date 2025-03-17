/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.Devices.BlockHopper;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.EntityRenderer;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderHopper
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/*  20 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/*  28 */     renderer.func_147771_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  34 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  35 */     tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  36 */     int l = block.func_149720_d(renderer.field_147845_a, x, y, z);
/*  37 */     float f = (l >> 16 & 0xFF) / 255.0F;
/*  38 */     float f1 = (l >> 8 & 0xFF) / 255.0F;
/*  39 */     float f2 = (l & 0xFF) / 255.0F;
/*     */     
/*  41 */     if (EntityRenderer.field_78517_a) {
/*     */       
/*  43 */       float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
/*  44 */       float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
/*  45 */       float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
/*  46 */       f = f3;
/*  47 */       f1 = f4;
/*  48 */       f2 = f5;
/*     */     } 
/*     */     
/*  51 */     tessellator.func_78386_a(f, f1, f2);
/*  52 */     return renderBlockHopperMetadata(block, x, y, z, renderer.field_147845_a.func_72805_g(x, y, z), false, renderer);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderBlockHopperMetadata(Block block, int x, int y, int z, int meta, boolean unknownBool, RenderBlocks renderer) {
/*  57 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  58 */     int i1 = BlockHopper.getDirectionFromMetadata(meta);
/*  59 */     double d0 = 0.625D;
/*  60 */     renderer.func_147782_a(0.0D, d0, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */     
/*  62 */     if (unknownBool) {
/*     */       
/*  64 */       tessellator.func_78382_b();
/*  65 */       tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/*  66 */       renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 0, meta));
/*  67 */       tessellator.func_78381_a();
/*  68 */       tessellator.func_78382_b();
/*  69 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/*  70 */       renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 1, meta));
/*  71 */       tessellator.func_78381_a();
/*  72 */       tessellator.func_78382_b();
/*  73 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/*  74 */       renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 2, meta));
/*  75 */       tessellator.func_78381_a();
/*  76 */       tessellator.func_78382_b();
/*  77 */       tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/*  78 */       renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 3, meta));
/*  79 */       tessellator.func_78381_a();
/*  80 */       tessellator.func_78382_b();
/*  81 */       tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/*  82 */       renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 4, meta));
/*  83 */       tessellator.func_78381_a();
/*  84 */       tessellator.func_78382_b();
/*  85 */       tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/*  86 */       renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 5, meta));
/*  87 */       tessellator.func_78381_a();
/*     */     }
/*     */     else {
/*     */       
/*  91 */       renderer.func_147784_q(block, x, y, z);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  96 */     if (!unknownBool) {
/*     */       
/*  98 */       tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
/*  99 */       int j1 = block.func_149720_d(renderer.field_147845_a, x, y, z);
/* 100 */       float f = (j1 >> 16 & 0xFF) / 255.0F;
/* 101 */       float f3 = (j1 >> 8 & 0xFF) / 255.0F;
/* 102 */       float f2 = (j1 & 0xFF) / 255.0F;
/*     */       
/* 104 */       if (EntityRenderer.field_78517_a) {
/*     */         
/* 106 */         float f6 = (f * 30.0F + f3 * 59.0F + f2 * 11.0F) / 100.0F;
/* 107 */         float f4 = (f * 30.0F + f3 * 70.0F) / 100.0F;
/* 108 */         float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
/* 109 */         f = f6;
/* 110 */         f3 = f4;
/* 111 */         f2 = f5;
/*     */       } 
/*     */       
/* 114 */       tessellator.func_78386_a(f, f3, f2);
/*     */     } 
/*     */     
/* 117 */     IIcon iicon = BlockHopper.getHopperIcon("hopper_outside");
/* 118 */     IIcon iicon1 = BlockHopper.getHopperIcon("hopper_inside");
/* 119 */     float f1 = 0.125F;
/*     */     
/* 121 */     if (unknownBool) {
/*     */       
/* 123 */       tessellator.func_78382_b();
/* 124 */       tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/* 125 */       renderer.func_147764_f(block, (-1.0F + f1), 0.0D, 0.0D, iicon);
/* 126 */       tessellator.func_78381_a();
/* 127 */       tessellator.func_78382_b();
/* 128 */       tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 129 */       renderer.func_147798_e(block, (1.0F - f1), 0.0D, 0.0D, iicon);
/* 130 */       tessellator.func_78381_a();
/* 131 */       tessellator.func_78382_b();
/* 132 */       tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 133 */       renderer.func_147734_d(block, 0.0D, 0.0D, (-1.0F + f1), iicon);
/* 134 */       tessellator.func_78381_a();
/* 135 */       tessellator.func_78382_b();
/* 136 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 137 */       renderer.func_147761_c(block, 0.0D, 0.0D, (1.0F - f1), iicon);
/* 138 */       tessellator.func_78381_a();
/* 139 */       tessellator.func_78382_b();
/* 140 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 141 */       renderer.func_147806_b(block, 0.0D, -1.0D + d0, 0.0D, iicon1);
/* 142 */       tessellator.func_78381_a();
/*     */     }
/*     */     else {
/*     */       
/* 146 */       renderer.func_147764_f(block, (x - 1.0F + f1), y, z, iicon);
/* 147 */       renderer.func_147798_e(block, (x + 1.0F - f1), y, z, iicon);
/* 148 */       renderer.func_147734_d(block, x, y, (z - 1.0F + f1), iicon);
/* 149 */       renderer.func_147761_c(block, x, y, (z + 1.0F - f1), iicon);
/* 150 */       renderer.func_147806_b(block, x, (y - 1.0F) + d0, z, iicon1);
/*     */     } 
/*     */     
/* 153 */     renderer.func_147757_a(iicon);
/* 154 */     double d3 = 0.25D;
/* 155 */     double d4 = 0.25D;
/* 156 */     renderer.func_147782_a(d3, d4, d3, 1.0D - d3, d0 - 0.002D, 1.0D - d3);
/*     */     
/* 158 */     if (unknownBool) {
/*     */       
/* 160 */       tessellator.func_78382_b();
/* 161 */       tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
/* 162 */       renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, iicon);
/* 163 */       tessellator.func_78381_a();
/* 164 */       tessellator.func_78382_b();
/* 165 */       tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 166 */       renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, iicon);
/* 167 */       tessellator.func_78381_a();
/* 168 */       tessellator.func_78382_b();
/* 169 */       tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
/* 170 */       renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, iicon);
/* 171 */       tessellator.func_78381_a();
/* 172 */       tessellator.func_78382_b();
/* 173 */       tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
/* 174 */       renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, iicon);
/* 175 */       tessellator.func_78381_a();
/* 176 */       tessellator.func_78382_b();
/* 177 */       tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
/* 178 */       renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, iicon);
/* 179 */       tessellator.func_78381_a();
/* 180 */       tessellator.func_78382_b();
/* 181 */       tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
/* 182 */       renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, iicon);
/* 183 */       tessellator.func_78381_a();
/*     */     }
/*     */     else {
/*     */       
/* 187 */       renderer.func_147784_q(block, x, y, z);
/*     */     } 
/*     */     
/* 190 */     if (!unknownBool) {
/*     */       
/* 192 */       double d1 = 0.375D;
/* 193 */       double d2 = 0.25D;
/* 194 */       renderer.func_147757_a(iicon);
/*     */       
/* 196 */       if (i1 == 0) {
/*     */         
/* 198 */         renderer.func_147782_a(d1, 0.0D, d1, 1.0D - d1, 0.25D, 1.0D - d1);
/* 199 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */       
/* 202 */       if (i1 == 2) {
/*     */         
/* 204 */         renderer.func_147782_a(d1, d4, 0.0D, 1.0D - d1, d4 + d2, d3);
/* 205 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */       
/* 208 */       if (i1 == 3) {
/*     */         
/* 210 */         renderer.func_147782_a(d1, d4, 1.0D - d3, 1.0D - d1, d4 + d2, 1.0D);
/* 211 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */       
/* 214 */       if (i1 == 4) {
/*     */         
/* 216 */         renderer.func_147782_a(0.0D, d4, d1, d3, d4 + d2, 1.0D - d1);
/* 217 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */       
/* 220 */       if (i1 == 5) {
/*     */         
/* 222 */         renderer.func_147782_a(1.0D - d3, d4, d1, 1.0D, d4 + d2, 1.0D - d1);
/* 223 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     renderer.func_147771_a();
/* 228 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 234 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, RenderBlocks renderer) {
/* 239 */     Tessellator tess = Tessellator.field_78398_a;
/* 240 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 241 */     tess.func_78382_b();
/* 242 */     tess.func_78375_b(0.0F, -1.0F, 0.0F);
/* 243 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
/* 244 */     tess.func_78381_a();
/* 245 */     tess.func_78382_b();
/* 246 */     tess.func_78375_b(0.0F, 1.0F, 0.0F);
/* 247 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
/* 248 */     tess.func_78381_a();
/* 249 */     tess.func_78382_b();
/* 250 */     tess.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 251 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
/* 252 */     tess.func_78381_a();
/* 253 */     tess.func_78382_b();
/* 254 */     tess.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 255 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 3));
/* 256 */     tess.func_78381_a();
/* 257 */     tess.func_78382_b();
/* 258 */     tess.func_78375_b(0.0F, 0.0F, -1.0F);
/* 259 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
/* 260 */     tess.func_78381_a();
/* 261 */     tess.func_78382_b();
/* 262 */     tess.func_78375_b(0.0F, 0.0F, 1.0F);
/* 263 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
/* 264 */     tess.func_78381_a();
/* 265 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */