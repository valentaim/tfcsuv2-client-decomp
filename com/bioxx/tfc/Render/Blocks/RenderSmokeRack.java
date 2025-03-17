/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TESmokeRack;
/*     */ import com.bioxx.tfc.api.Food;
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
/*     */ public class RenderSmokeRack
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  19 */     TESmokeRack te = (TESmokeRack)world.func_147438_o(x, y, z);
/*  20 */     renderer.field_147837_f = true;
/*  21 */     renderer.func_147784_q(block, x, y, z);
/*  22 */     if ((world.func_72805_g(x, y, z) & 0x1) == 0) {
/*     */       
/*  24 */       if (te.func_70301_a(0) != null) {
/*     */         
/*  26 */         double mid = 0.25D;
/*  27 */         float r = 1.0F, g = 1.0F, b = 1.0F;
/*  28 */         if (Food.isSmoked(te.func_70301_a(0))) {
/*  29 */           r = 0.1F; g = 0.1F; b = 0.1F;
/*  30 */         }  renderer.func_147782_a(0.43D, 0.43D, mid - 0.07D, 0.57D, 0.57D, mid + 0.07D);
/*  31 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*  32 */         renderer.func_147782_a(0.491D, 0.2D, mid - 0.009D, 0.509D, 0.5D, mid + 0.009D);
/*  33 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*     */       } 
/*  35 */       if (te.func_70301_a(1) != null)
/*     */       {
/*  37 */         double mid = 0.75D;
/*  38 */         float r = 1.0F, g = 1.0F, b = 1.0F;
/*  39 */         if (Food.isSmoked(te.func_70301_a(1))) {
/*  40 */           r = 0.1F; g = 0.1F; b = 0.1F;
/*  41 */         }  renderer.func_147782_a(0.43D, 0.43D, mid - 0.07D, 0.57D, 0.57D, mid + 0.07D);
/*  42 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*  43 */         renderer.func_147782_a(0.491D, 0.2D, mid - 0.009D, 0.509D, 0.5D, mid + 0.009D);
/*  44 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  49 */       if (te.func_70301_a(0) != null) {
/*     */         
/*  51 */         double mid = 0.25D;
/*  52 */         float r = 1.0F, g = 1.0F, b = 1.0F;
/*  53 */         if (Food.isSmoked(te.func_70301_a(0))) {
/*  54 */           r = 0.1F; g = 0.1F; b = 0.1F;
/*  55 */         }  renderer.func_147782_a(mid - 0.07D, 0.43D, 0.43D, mid + 0.07D, 0.57D, 0.57D);
/*  56 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*  57 */         renderer.func_147782_a(mid - 0.009D, 0.2D, 0.491D, mid + 0.009D, 0.5D, 0.509D);
/*  58 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*     */       } 
/*  60 */       if (te.func_70301_a(1) != null) {
/*     */         
/*  62 */         double mid = 0.75D;
/*  63 */         float r = 1.0F, g = 1.0F, b = 1.0F;
/*  64 */         if (Food.isSmoked(te.func_70301_a(1))) {
/*  65 */           r = 0.1F; g = 0.1F; b = 0.1F;
/*  66 */         }  renderer.func_147782_a(mid - 0.07D, 0.43D, 0.43D, mid + 0.07D, 0.57D, 0.57D);
/*  67 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*  68 */         renderer.func_147782_a(mid - 0.009D, 0.2D, 0.491D, mid + 0.009D, 0.5D, 0.509D);
/*  69 */         renderer.func_147736_d(block, x, y, z, r, g, b);
/*     */       } 
/*     */     } 
/*  72 */     renderer.field_147837_f = false;
/*     */     
/*  74 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  79 */     renderer.field_147875_q = i;
/*  80 */     renderer.field_147873_r = i;
/*  81 */     renderer.field_147869_t = i;
/*  82 */     renderer.field_147871_s = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/*  94 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 100 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 105 */     Tessellator var14 = Tessellator.field_78398_a;
/* 106 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 107 */     var14.func_78382_b();
/* 108 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 109 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 110 */     var14.func_78381_a();
/* 111 */     var14.func_78382_b();
/* 112 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 113 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 114 */     var14.func_78381_a();
/* 115 */     var14.func_78382_b();
/* 116 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 117 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 118 */     var14.func_78381_a();
/* 119 */     var14.func_78382_b();
/* 120 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 121 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 122 */     var14.func_78381_a();
/* 123 */     var14.func_78382_b();
/* 124 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 125 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 126 */     var14.func_78381_a();
/* 127 */     var14.func_78382_b();
/* 128 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 129 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 130 */     var14.func_78381_a();
/* 131 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */