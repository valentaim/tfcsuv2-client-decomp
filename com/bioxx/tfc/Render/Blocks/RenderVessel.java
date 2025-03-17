/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderVessel
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   private static final float MIN = 0.2F;
/*     */   private static final float MAX = 0.8F;
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/*  22 */     TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
/*  23 */     renderer.field_147837_f = true;
/*     */     
/*  25 */     if ((te.rotation & Byte.MIN_VALUE) == 0) {
/*     */       
/*  27 */       if (te.getSealed()) {
/*     */         
/*  29 */         renderer.func_147782_a(0.17499999701976776D, 0.550000011920929D, 0.17499999701976776D, 0.824999988079071D, 0.6499999761581421D, 0.824999988079071D);
/*  30 */         renderer.func_147784_q(block, x, y, z);
/*  31 */         renderer.func_147782_a(0.4375D, 0.6499999761581421D, 0.4375D, 0.5625D, 0.699999988079071D, 0.5625D);
/*  32 */         renderer.func_147784_q(block, x, y, z);
/*  33 */         renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 0.05000000074505806D, 0.75D);
/*  34 */         renderer.func_147784_q(block, x, y, z);
/*     */       }
/*     */       else {
/*     */         
/*  38 */         renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 0.05000000074505806D, 0.75D);
/*  39 */         renderer.func_147784_q(block, x, y, z);
/*     */         
/*  41 */         if (te.fluid != null && renderer.field_147840_d == null) {
/*     */           
/*  43 */           int color = te.fluid.getFluid().getColor(te.fluid);
/*  44 */           float f = (color >> 16 & 0xFF) / 255.0F;
/*  45 */           float f1 = (color >> 8 & 0xFF) / 255.0F;
/*  46 */           float f2 = (color & 0xFF) / 255.0F;
/*  47 */           float h = 0.5F * te.fluid.amount / te.getMaxLiquid();
/*  48 */           renderer.func_147782_a(0.25D, 0.05D, 0.25D, 0.75D, (0.05F + h), 0.75D);
/*  49 */           IIcon still = te.fluid.getFluid().getStillIcon();
/*  50 */           renderer.func_147757_a(still);
/*  51 */           renderer.func_147736_d(block, x, y, z, f, f1, f2);
/*  52 */           renderer.func_147771_a();
/*     */         } 
/*     */       } 
/*  55 */       renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.25D, 0.25D, 0.6000000238418579D, 0.75D);
/*  56 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/*  58 */       renderer.func_147782_a(0.75D, 0.0D, 0.25D, 0.800000011920929D, 0.6000000238418579D, 0.75D);
/*  59 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/*  61 */       renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.6000000238418579D, 0.25D);
/*  62 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/*  64 */       renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.75D, 0.800000011920929D, 0.6000000238418579D, 0.800000011920929D);
/*  65 */       renderer.func_147784_q(block, x, y, z);
/*     */       
/*  67 */       renderer.func_147782_a(0.25D, 0.05D, 0.25D, 0.75D, 0.10000000149011612D, 0.75D);
/*  68 */       renderer.func_147784_q(block, x, y, z);
/*     */     }
/*     */     else {
/*     */       
/*  72 */       if ((te.rotation & 0x3) == 0) {
/*     */         
/*  74 */         renderer.func_147782_a(0.20000000298023224D, 0.20000000298023224D, 0.25D, 0.949999988079071D, 0.25D, 0.75D);
/*  75 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*  77 */       if ((te.rotation & 0x3) == 1) {
/*     */         
/*  79 */         renderer.func_147782_a(0.25D, 0.20000000298023224D, 0.20000000298023224D, 0.75D, 0.25D, 0.949999988079071D);
/*  80 */         renderer.func_147784_q(block, x, y, z);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  85 */     renderer.field_147837_f = false;
/*     */     
/*  87 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void rotate(RenderBlocks renderer, int i) {
/*  92 */     renderer.field_147875_q = i;
/*  93 */     renderer.field_147873_r = i;
/*  94 */     renderer.field_147869_t = i;
/*  95 */     renderer.field_147871_s = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
/* 101 */     renderer.func_147782_a(0.17499999701976776D, 0.550000011920929D, 0.17499999701976776D, 0.824999988079071D, 0.6499999761581421D, 0.824999988079071D);
/* 102 */     renderInvBlock(block, meta, renderer);
/* 103 */     renderer.func_147782_a(0.4375D, 0.6499999761581421D, 0.4375D, 0.5625D, 0.699999988079071D, 0.5625D);
/* 104 */     renderInvBlock(block, meta, renderer);
/* 105 */     renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.6000000238418579D, 0.800000011920929D);
/* 106 */     renderInvBlock(block, meta, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 113 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 119 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 124 */     Tessellator var14 = Tessellator.field_78398_a;
/* 125 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 126 */     var14.func_78382_b();
/* 127 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 128 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 129 */     var14.func_78381_a();
/* 130 */     var14.func_78382_b();
/* 131 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 132 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 133 */     var14.func_78381_a();
/* 134 */     var14.func_78382_b();
/* 135 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 136 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 137 */     var14.func_78381_a();
/* 138 */     var14.func_78382_b();
/* 139 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 140 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 141 */     var14.func_78381_a();
/* 142 */     var14.func_78382_b();
/* 143 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 144 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 145 */     var14.func_78381_a();
/* 146 */     var14.func_78382_b();
/* 147 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 148 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 149 */     var14.func_78381_a();
/* 150 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */