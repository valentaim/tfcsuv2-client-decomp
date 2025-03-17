/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderSmoke
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  21 */     renderer.field_147863_w = false;
/*  22 */     Tessellator tessellator = Tessellator.field_78398_a;
/*  23 */     boolean flag = false;
/*  24 */     float f3 = 0.5F;
/*  25 */     float f4 = 1.0F;
/*  26 */     float f5 = 0.8F;
/*  27 */     float f6 = 0.6F;
/*  28 */     float f7 = f4 * 1.0F;
/*  29 */     float f8 = f4 * 1.0F;
/*  30 */     float f9 = f4 * 1.0F;
/*  31 */     float f10 = f3;
/*  32 */     float f11 = f5;
/*  33 */     float f12 = f6;
/*  34 */     float f13 = f3;
/*  35 */     float f14 = f5;
/*  36 */     float f15 = f6;
/*  37 */     float f16 = f3;
/*  38 */     float f17 = f5;
/*  39 */     float f18 = f6;
/*  40 */     float alpha = 0.8F;
/*     */ 
/*     */ 
/*     */     
/*  44 */     int l = block.func_149677_c(world, x, y, z);
/*     */     
/*  46 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y - 1, z, 0)) {
/*     */       
/*  48 */       tessellator.func_78380_c((renderer.field_147855_j > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y - 1, z));
/*  49 */       tessellator.func_78369_a(f10, f13, f16, alpha);
/*  50 */       renderer.func_147768_a(block, x, y, z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 0));
/*  51 */       flag = true;
/*     */     } 
/*     */     
/*  54 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y + 1, z, 1)) {
/*     */       
/*  56 */       tessellator.func_78380_c((renderer.field_147857_k < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y + 1, z));
/*  57 */       tessellator.func_78369_a(f7, f8, f9, alpha);
/*  58 */       renderer.func_147806_b(block, x, y, z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 1));
/*  59 */       flag = true;
/*     */     } 
/*     */ 
/*     */     
/*  63 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z - 1, 2)) {
/*     */       
/*  65 */       tessellator.func_78380_c((renderer.field_147851_l > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y, z - 1));
/*  66 */       tessellator.func_78369_a(f11, f14, f17, alpha);
/*  67 */       IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 2);
/*  68 */       renderer.func_147761_c(block, x, y, z, iicon);
/*  69 */       flag = true;
/*     */     } 
/*     */     
/*  72 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z + 1, 3)) {
/*     */       
/*  74 */       tessellator.func_78380_c((renderer.field_147853_m < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y, z + 1));
/*  75 */       tessellator.func_78369_a(f11, f14, f17, alpha);
/*  76 */       IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 3);
/*  77 */       renderer.func_147734_d(block, x, y, z, iicon);
/*  78 */       flag = true;
/*     */     } 
/*     */     
/*  81 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x - 1, y, z, 4)) {
/*     */       
/*  83 */       tessellator.func_78380_c((renderer.field_147859_h > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x - 1, y, z));
/*  84 */       tessellator.func_78369_a(f12, f15, f18, alpha);
/*  85 */       IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 4);
/*  86 */       renderer.func_147798_e(block, x, y, z, iicon);
/*  87 */       flag = true;
/*     */     } 
/*     */     
/*  90 */     if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x + 1, y, z, 5)) {
/*     */       
/*  92 */       tessellator.func_78380_c((renderer.field_147861_i < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x + 1, y, z));
/*  93 */       tessellator.func_78369_a(f12, f15, f18, alpha);
/*  94 */       IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 5);
/*  95 */       renderer.func_147764_f(block, x, y, z, iicon);
/*  96 */       flag = true;
/*     */     } 
/*  98 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 110 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 116 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */