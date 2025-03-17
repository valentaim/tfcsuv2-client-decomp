/*     */ package com.bioxx.tfc.Render.Blocks;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockWattle;
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
/*     */ public class RenderWattle
/*     */   implements ISimpleBlockRenderingHandler
/*     */ {
/*     */   public static boolean renderBlockWall(BlockWattle wattleBlock, int x, int y, int z, RenderBlocks renderblocks) {
/*  19 */     double wallWidth = 0.1D;
/*  20 */     boolean flag0 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x - 1, y, z);
/*  21 */     boolean flag1 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x + 1, y, z);
/*  22 */     boolean flag2 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y, z - 1);
/*  23 */     boolean flag3 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y, z + 1);
/*  24 */     boolean flag4 = (flag2 && flag3 && !flag0 && !flag1);
/*  25 */     boolean flag5 = (!flag2 && !flag3 && flag0 && flag1);
/*  26 */     boolean flag6 = renderblocks.field_147845_a.func_147437_c(x, y + 1, z);
/*     */ 
/*     */     
/*  29 */     boolean flagUp = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z);
/*     */     
/*  31 */     boolean flag0Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x - 1, y + 1, z);
/*  32 */     boolean flag1Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x + 1, y + 1, z);
/*  33 */     boolean flag2Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z - 1);
/*  34 */     boolean flag3Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z + 1);
/*  35 */     boolean flag4Up = (flag2Up && flag3Up);
/*  36 */     boolean flag5Up = (flag0Up && flag1Up);
/*     */     
/*  38 */     if (((flag4 && flag4Up) || (flag5 && flag5Up)) && flagUp) {
/*     */       
/*  40 */       if (flag4)
/*     */       {
/*  42 */         renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.0D, 1.0D);
/*  43 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       }
/*     */       else
/*     */       {
/*  47 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.0D, 0.5D + wallWidth * 0.5D);
/*  48 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       }
/*     */     
/*  51 */     } else if ((flag4 || flag5) && flag6) {
/*     */       
/*  53 */       if (flag4)
/*     */       {
/*  55 */         renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.1D, 1.0D);
/*  56 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       }
/*     */       else
/*     */       {
/*  60 */         renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.1D, 0.5D + wallWidth * 0.5D);
/*  61 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  66 */       renderblocks.func_147782_a(0.5D - wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.55D, 0.5D + wallWidth * 0.55D, flagUp ? 1.0D : 1.25D, 0.5D + wallWidth * 0.55D);
/*  67 */       renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */ 
/*     */ 
/*     */       
/*  71 */       if (flag0) {
/*     */         
/*  73 */         if (flagUp && flag0Up) {
/*  74 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 0.5D - wallWidth * 0.55D, 1.0D, 0.5D + wallWidth * 0.5D);
/*     */         } else {
/*     */           
/*  77 */           renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 0.5D - wallWidth * 0.55D, 1.1D, 0.5D + wallWidth * 0.5D);
/*     */         } 
/*  79 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       } 
/*     */       
/*  82 */       if (flag1) {
/*     */         
/*  84 */         if (flagUp && flag1Up) {
/*  85 */           renderblocks.func_147782_a(0.5D + wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.0D, 0.5D + wallWidth * 0.5D);
/*     */         }
/*     */         else {
/*     */           
/*  89 */           renderblocks.func_147782_a(0.5D + wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.1D, 0.5D + wallWidth * 0.5D);
/*     */         } 
/*  91 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       } 
/*     */       
/*  94 */       if (flag2) {
/*     */         
/*  96 */         if (flagUp && flag2Up) {
/*  97 */           renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.0D, 0.5D - wallWidth * 0.55D);
/*     */         } else {
/*     */           
/* 100 */           renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.1D, 0.5D - wallWidth * 0.55D);
/*     */         } 
/* 102 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       } 
/*     */       
/* 105 */       if (flag3) {
/*     */         
/* 107 */         if (flagUp && flag3Up) {
/* 108 */           renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.5D + wallWidth * 0.55D, 0.5D + wallWidth * 0.5D, 1.0D, 1.0D);
/*     */         } else {
/*     */           
/* 111 */           renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.5D + wallWidth * 0.55D, 0.5D + wallWidth * 0.5D, 1.1D, 1.0D);
/*     */         } 
/* 113 */         renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
/*     */       } 
/*     */     } 
/* 116 */     wattleBlock.func_149719_a(renderblocks.field_147845_a, x, y, z);
/* 117 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 123 */     float f = 0.4F;
/* 124 */     float f1 = 0.6F;
/*     */     
/* 126 */     renderer.func_147782_a(f, 0.0D, f, f1, 1.25D, f1);
/* 127 */     renderInvBlock(block, metadata, renderer);
/*     */     
/* 129 */     f = 0.45F;
/* 130 */     f1 = 0.55F;
/* 131 */     float f2 = 0.0F;
/* 132 */     float f3 = 1.1F;
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
/* 160 */     if (modelId == TFCBlocks.wattleRenderId && block instanceof BlockWattle) {
/* 161 */       return renderBlockWall((BlockWattle)block, x, y, z, renderer);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderWattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */