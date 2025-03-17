/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockWall;
/*     */ import net.minecraft.client.renderer.RenderBlocks;
/*     */ import net.minecraft.client.renderer.Tessellator;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import org.lwjgl.opengl.GL11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RenderWall
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean renderBlockWall(BlockWall wallBlock, int x, int y, int z, RenderBlocks renderblocks) {
/*  19 */     boolean flag0 = wallBlock.func_150091_e(renderblocks.field_147845_a, x - 1, y, z);
/*  20 */     boolean flag1 = wallBlock.func_150091_e(renderblocks.field_147845_a, x + 1, y, z);
/*  21 */     boolean flag2 = wallBlock.func_150091_e(renderblocks.field_147845_a, x, y, z - 1);
/*  22 */     boolean flag3 = wallBlock.func_150091_e(renderblocks.field_147845_a, x, y, z + 1);
/*  23 */     boolean flag4 = (flag2 && flag3 && !flag0 && !flag1);
/*  24 */     boolean flag5 = (!flag2 && !flag3 && flag0 && flag1);
/*  25 */     boolean flag6 = renderblocks.field_147845_a.func_147437_c(x, y + 1, z);
/*     */ 
/*     */     
/*  28 */     boolean flagUp = wallBlock.func_150091_e(renderblocks.field_147845_a, x, y + 1, z);
/*     */     
/*  30 */     boolean flag0Up = wallBlock.func_150091_e(renderblocks.field_147845_a, x - 1, y + 1, z);
/*  31 */     boolean flag1Up = wallBlock.func_150091_e(renderblocks.field_147845_a, x + 1, y + 1, z);
/*  32 */     boolean flag2Up = wallBlock.func_150091_e(renderblocks.field_147845_a, x, y + 1, z - 1);
/*  33 */     boolean flag3Up = wallBlock.func_150091_e(renderblocks.field_147845_a, x, y + 1, z + 1);
/*  34 */     boolean flag4Up = (flag2Up && flag3Up);
/*  35 */     boolean flag5Up = (flag0Up && flag1Up);
/*     */     
/*  37 */     if (((flag4 && flag4Up) || (flag5 && flag5Up)) && flagUp) {
/*     */       
/*  39 */       if (flag4)
/*     */       {
/*  41 */         renderblocks.func_147782_a(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 1.0D);
/*  42 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       }
/*     */       else
/*     */       {
/*  46 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
/*  47 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       }
/*     */     
/*  50 */     } else if ((flag4 || flag5) && flag6) {
/*     */       
/*  52 */       if (flag4)
/*     */       {
/*  54 */         renderblocks.func_147782_a(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 1.0D);
/*  55 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       }
/*     */       else
/*     */       {
/*  59 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
/*  60 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  65 */       renderblocks.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
/*  66 */       renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */ 
/*     */ 
/*     */       
/*  70 */       if (flag0) {
/*     */         
/*  72 */         if (flagUp && flag0Up) {
/*  73 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.3125D, 0.25D, 1.0D, 0.6875D);
/*     */         } else {
/*     */           
/*  76 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.3125D, 0.25D, 0.8125D, 0.6875D);
/*     */         } 
/*  78 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       } 
/*     */       
/*  81 */       if (flag1) {
/*     */         
/*  83 */         if (flagUp && flag1Up) {
/*  84 */           renderblocks.func_147782_a(0.75D, 0.0D, 0.3125D, 1.0D, 1.0D, 0.6875D);
/*     */         }
/*     */         else {
/*     */           
/*  88 */           renderblocks.func_147782_a(0.75D, 0.0D, 0.3125D, 1.0D, 0.8125D, 0.6875D);
/*     */         } 
/*  90 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       } 
/*     */       
/*  93 */       if (flag2) {
/*     */         
/*  95 */         if (flagUp && flag2Up) {
/*  96 */           renderblocks.func_147782_a(0.3125D, 0.0D, 0.0D, 0.6875D, 1.0D, 0.25D);
/*     */         } else {
/*     */           
/*  99 */           renderblocks.func_147782_a(0.3125D, 0.0D, 0.0D, 0.6875D, 0.8125D, 0.25D);
/*     */         } 
/* 101 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       } 
/*     */       
/* 104 */       if (flag3) {
/*     */         
/* 106 */         if (flagUp && flag3Up) {
/* 107 */           renderblocks.func_147782_a(0.3125D, 0.0D, 0.75D, 0.6875D, 1.0D, 1.0D);
/*     */         } else {
/*     */           
/* 110 */           renderblocks.func_147782_a(0.3125D, 0.0D, 0.75D, 0.6875D, 0.8125D, 1.0D);
/*     */         } 
/* 112 */         renderblocks.func_147784_q((Block)wallBlock, x, y, z);
/*     */       } 
/*     */     } 
/*     */     
/* 116 */     wallBlock.func_149719_a(renderblocks.field_147845_a, x, y, z);
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 123 */     float f = 0.3F;
/* 124 */     float f1 = 0.7F;
/*     */     
/* 126 */     renderer.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);
/* 127 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 129 */     f = 0.325F;
/* 130 */     f1 = 0.675F;
/* 131 */     float f2 = 0.0F;
/* 132 */     float f3 = 0.8F;
/*     */ 
/*     */     
/* 135 */     float f6 = 0.0F;
/* 136 */     float f7 = 1.5F;
/*     */     
/* 138 */     renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, 5.0D * f7 / 8.0D);
/* 139 */     renderInvBlock(block, metadata, renderer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldRender3DInInventory(int modelId) {
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderId() {
/* 154 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 160 */     if (modelId == TFCBlocks.wallRenderId && block instanceof BlockWall) {
/* 161 */       return renderBlockWall((BlockWall)block, x, y, z, renderer);
/*     */     }
/* 163 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
/* 168 */     Tessellator var14 = Tessellator.field_78398_a;
/* 169 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 170 */     var14.func_78382_b();
/* 171 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 172 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
/* 173 */     var14.func_78381_a();
/* 174 */     var14.func_78382_b();
/* 175 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 176 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
/* 177 */     var14.func_78381_a();
/* 178 */     var14.func_78382_b();
/* 179 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 180 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
/* 181 */     var14.func_78381_a();
/* 182 */     var14.func_78382_b();
/* 183 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 184 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
/* 185 */     var14.func_78381_a();
/* 186 */     var14.func_78382_b();
/* 187 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 188 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
/* 189 */     var14.func_78381_a();
/* 190 */     var14.func_78382_b();
/* 191 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 192 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
/* 193 */     var14.func_78381_a();
/* 194 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */