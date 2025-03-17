/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEFenceGate;
/*     */ import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockDirectional;
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
/*     */ public class RenderFenceGate
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int par2, int par3, int par4, Block block, int modelId, RenderBlocks renderer) {
/*  25 */     Block par1BlockFenceGate = ((IMultipleBlock)block).getBlockTypeForRender();
/*  26 */     boolean flag = true;
/*  27 */     int l = ((TEFenceGate)renderer.field_147845_a.func_147438_o(par2, par3, par4)).getDirection();
/*  28 */     boolean flag1 = ((TEFenceGate)renderer.field_147845_a.func_147438_o(par2, par3, par4)).getOpen();
/*  29 */     int i1 = BlockDirectional.func_149895_l(l);
/*  30 */     float f = 0.375F;
/*  31 */     float f1 = 0.5625F;
/*  32 */     float f2 = 0.75F;
/*  33 */     float f3 = 0.9375F;
/*  34 */     float f4 = 0.3125F;
/*  35 */     float f5 = 1.0F;
/*     */     
/*  37 */     if (((i1 == 2 || i1 == 0) && renderer.field_147845_a
/*  38 */       .func_147439_a(par2 - 1, par3, par4) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall && renderer.field_147845_a
/*  39 */       .func_147439_a(par2 + 1, par3, par4) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall) || ((i1 == 3 || i1 == 1) && renderer.field_147845_a
/*     */       
/*  41 */       .func_147439_a(par2, par3, par4 - 1) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall && renderer.field_147845_a
/*  42 */       .func_147439_a(par2, par3, par4 + 1) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall)) {
/*     */       
/*  44 */       f -= 0.1875F;
/*  45 */       f1 -= 0.1875F;
/*  46 */       f2 -= 0.1875F;
/*  47 */       f3 -= 0.1875F;
/*  48 */       f4 -= 0.1875F;
/*  49 */       f5 -= 0.1875F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  57 */     if (i1 != 3 && i1 != 1) {
/*     */       
/*  59 */       float f6 = 0.0F;
/*  60 */       float f8 = 0.125F;
/*  61 */       float f7 = 0.4375F;
/*  62 */       float f9 = 0.5625F;
/*  63 */       renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
/*  64 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*  65 */       f6 = 0.875F;
/*  66 */       f8 = 1.0F;
/*  67 */       renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
/*  68 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */     }
/*     */     else {
/*     */       
/*  72 */       renderer.field_147867_u = 1;
/*  73 */       float f6 = 0.4375F;
/*  74 */       float f8 = 0.5625F;
/*  75 */       float f7 = 0.0F;
/*  76 */       float f9 = 0.125F;
/*  77 */       renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
/*  78 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*  79 */       f7 = 0.875F;
/*  80 */       f9 = 1.0F;
/*  81 */       renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
/*  82 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*  83 */       renderer.field_147867_u = 0;
/*     */     } 
/*     */     
/*  86 */     if (flag1) {
/*     */       
/*  88 */       if (i1 == 2 || i1 == 0) {
/*  89 */         renderer.field_147867_u = 1;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  95 */       if (i1 == 3)
/*     */       {
/*  97 */         float f6 = 0.0F;
/*  98 */         float f8 = 0.125F;
/*  99 */         float f7 = 0.875F;
/* 100 */         float f9 = 1.0F;
/*     */ 
/*     */ 
/*     */         
/* 104 */         renderer.func_147782_a(0.8125D, f, 0.0D, 0.9375D, f3, 0.125D);
/* 105 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 106 */         renderer.func_147782_a(0.8125D, f, 0.875D, 0.9375D, f3, 1.0D);
/* 107 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 108 */         renderer.func_147782_a(0.5625D, f, 0.0D, 0.8125D, f1, 0.125D);
/* 109 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 110 */         renderer.func_147782_a(0.5625D, f, 0.875D, 0.8125D, f1, 1.0D);
/* 111 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 112 */         renderer.func_147782_a(0.5625D, f2, 0.0D, 0.8125D, f3, 0.125D);
/* 113 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 114 */         renderer.func_147782_a(0.5625D, f2, 0.875D, 0.8125D, f3, 1.0D);
/* 115 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */       }
/* 117 */       else if (i1 == 1)
/*     */       {
/* 119 */         float f6 = 0.0F;
/* 120 */         float f8 = 0.125F;
/* 121 */         float f7 = 0.875F;
/* 122 */         float f9 = 1.0F;
/*     */ 
/*     */ 
/*     */         
/* 126 */         renderer.func_147782_a(0.0625D, f, 0.0D, 0.1875D, f3, 0.125D);
/* 127 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 128 */         renderer.func_147782_a(0.0625D, f, 0.875D, 0.1875D, f3, 1.0D);
/* 129 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 130 */         renderer.func_147782_a(0.1875D, f, 0.0D, 0.4375D, f1, 0.125D);
/* 131 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 132 */         renderer.func_147782_a(0.1875D, f, 0.875D, 0.4375D, f1, 1.0D);
/* 133 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 134 */         renderer.func_147782_a(0.1875D, f2, 0.0D, 0.4375D, f3, 0.125D);
/* 135 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 136 */         renderer.func_147782_a(0.1875D, f2, 0.875D, 0.4375D, f3, 1.0D);
/* 137 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */       }
/* 139 */       else if (i1 == 0)
/*     */       {
/* 141 */         float f6 = 0.0F;
/* 142 */         float f8 = 0.125F;
/* 143 */         float f7 = 0.875F;
/* 144 */         float f9 = 1.0F;
/*     */ 
/*     */ 
/*     */         
/* 148 */         renderer.func_147782_a(0.0D, f, 0.8125D, 0.125D, f3, 0.9375D);
/* 149 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 150 */         renderer.func_147782_a(0.875D, f, 0.8125D, 1.0D, f3, 0.9375D);
/* 151 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 152 */         renderer.func_147782_a(0.0D, f, 0.5625D, 0.125D, f1, 0.8125D);
/* 153 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 154 */         renderer.func_147782_a(0.875D, f, 0.5625D, 1.0D, f1, 0.8125D);
/* 155 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 156 */         renderer.func_147782_a(0.0D, f2, 0.5625D, 0.125D, f3, 0.8125D);
/* 157 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 158 */         renderer.func_147782_a(0.875D, f2, 0.5625D, 1.0D, f3, 0.8125D);
/* 159 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */       }
/* 161 */       else if (i1 == 2)
/*     */       {
/* 163 */         float f6 = 0.0F;
/* 164 */         float f8 = 0.125F;
/* 165 */         float f7 = 0.875F;
/* 166 */         float f9 = 1.0F;
/*     */ 
/*     */ 
/*     */         
/* 170 */         renderer.func_147782_a(0.0D, f, 0.0625D, 0.125D, f3, 0.1875D);
/* 171 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 172 */         renderer.func_147782_a(0.875D, f, 0.0625D, 1.0D, f3, 0.1875D);
/* 173 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 174 */         renderer.func_147782_a(0.0D, f, 0.1875D, 0.125D, f1, 0.4375D);
/* 175 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 176 */         renderer.func_147782_a(0.875D, f, 0.1875D, 1.0D, f1, 0.4375D);
/* 177 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 178 */         renderer.func_147782_a(0.0D, f2, 0.1875D, 0.125D, f3, 0.4375D);
/* 179 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 180 */         renderer.func_147782_a(0.875D, f2, 0.1875D, 1.0D, f3, 0.4375D);
/* 181 */         renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */       }
/*     */     
/* 184 */     } else if (i1 != 3 && i1 != 1) {
/*     */       
/* 186 */       float f6 = 0.375F;
/* 187 */       float f8 = 0.5F;
/* 188 */       float f7 = 0.4375F;
/* 189 */       float f9 = 0.5625F;
/* 190 */       renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 191 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 192 */       f6 = 0.5F;
/* 193 */       f8 = 0.625F;
/* 194 */       renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 195 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 196 */       f6 = 0.625F;
/* 197 */       f8 = 0.875F;
/* 198 */       renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 199 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 200 */       renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 201 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 202 */       f6 = 0.125F;
/* 203 */       f8 = 0.375F;
/* 204 */       renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 205 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 206 */       renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 207 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */     }
/*     */     else {
/*     */       
/* 211 */       renderer.field_147867_u = 1;
/* 212 */       float f6 = 0.4375F;
/* 213 */       float f8 = 0.5625F;
/* 214 */       float f7 = 0.375F;
/* 215 */       float f9 = 0.5F;
/* 216 */       renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 217 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 218 */       f7 = 0.5F;
/* 219 */       f9 = 0.625F;
/* 220 */       renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 221 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 222 */       f7 = 0.625F;
/* 223 */       f9 = 0.875F;
/* 224 */       renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 225 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 226 */       renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 227 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 228 */       f7 = 0.125F;
/* 229 */       f9 = 0.375F;
/* 230 */       renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 231 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/* 232 */       renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 233 */       renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
/*     */     } 
/*     */     
/* 236 */     renderer.field_147867_u = 0;
/* 237 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/* 238 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 244 */     int l = 1;
/*     */     
/* 246 */     int i1 = BlockDirectional.func_149895_l(l);
/*     */     
/* 248 */     float f = 0.375F;
/* 249 */     float f1 = 0.5625F;
/* 250 */     float f2 = 0.75F;
/* 251 */     float f3 = 0.9375F;
/* 252 */     float f4 = 0.3125F;
/* 253 */     float f5 = 1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     if (i1 != 3 && i1 != 1) {
/*     */       
/* 261 */       float f10 = 0.0F;
/* 262 */       float f12 = 0.125F;
/* 263 */       float f11 = 0.4375F;
/* 264 */       float f13 = 0.5625F;
/* 265 */       renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
/* 266 */       renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 267 */       f10 = 0.875F;
/* 268 */       f12 = 1.0F;
/* 269 */       renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
/* 270 */       renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/*     */     }
/*     */     else {
/*     */       
/* 274 */       renderer.field_147867_u = 1;
/* 275 */       float f10 = 0.4375F;
/* 276 */       float f12 = 0.5625F;
/* 277 */       float f11 = 0.0F;
/* 278 */       float f13 = 0.125F;
/* 279 */       renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
/* 280 */       renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 281 */       f11 = 0.875F;
/* 282 */       f13 = 1.0F;
/* 283 */       renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
/* 284 */       renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 285 */       renderer.field_147867_u = 0;
/*     */     } 
/*     */     
/* 288 */     renderer.field_147867_u = 1;
/* 289 */     float f6 = 0.4375F;
/* 290 */     float f8 = 0.5625F;
/* 291 */     float f7 = 0.375F;
/* 292 */     float f9 = 0.5F;
/* 293 */     renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 294 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 295 */     f7 = 0.5F;
/* 296 */     f9 = 0.625F;
/* 297 */     renderer.func_147782_a(f6, f, f7, f8, f3, f9);
/* 298 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 299 */     f7 = 0.625F;
/* 300 */     f9 = 0.875F;
/* 301 */     renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 302 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 303 */     renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 304 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 305 */     f7 = 0.125F;
/* 306 */     f9 = 0.375F;
/* 307 */     renderer.func_147782_a(f6, f, f7, f8, f1, f9);
/* 308 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/* 309 */     renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
/* 310 */     renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
/*     */     
/* 312 */     renderer.field_147867_u = 0;
/* 313 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 319 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 325 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 330 */     Tessellator var14 = Tessellator.field_78398_a;
/* 331 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 332 */     var14.func_78382_b();
/* 333 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 334 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 335 */     var14.func_78381_a();
/* 336 */     var14.func_78382_b();
/* 337 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 338 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 339 */     var14.func_78381_a();
/* 340 */     var14.func_78382_b();
/* 341 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 342 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 343 */     var14.func_78381_a();
/* 344 */     var14.func_78382_b();
/* 345 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 346 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 347 */     var14.func_78381_a();
/* 348 */     var14.func_78382_b();
/* 349 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 350 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 351 */     var14.func_78381_a();
/* 352 */     var14.func_78382_b();
/* 353 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 354 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 355 */     var14.func_78381_a();
/* 356 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock2(Block block, int m, RenderBlocks renderer) {
/* 361 */     Tessellator var14 = Tessellator.field_78398_a;
/* 362 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 363 */     var14.func_78382_b();
/* 364 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 365 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 366 */     var14.func_78381_a();
/* 367 */     var14.func_78382_b();
/* 368 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 369 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 370 */     var14.func_78381_a();
/* 371 */     var14.func_78382_b();
/* 372 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 373 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 374 */     var14.func_78381_a();
/* 375 */     var14.func_78382_b();
/* 376 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 377 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 378 */     var14.func_78381_a();
/* 379 */     var14.func_78382_b();
/* 380 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 381 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 382 */     var14.func_78381_a();
/* 383 */     var14.func_78382_b();
/* 384 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 385 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 386 */     var14.func_78381_a();
/* 387 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */