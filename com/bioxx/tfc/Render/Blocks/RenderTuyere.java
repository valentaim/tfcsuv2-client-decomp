/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ public class RenderTuyere
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
/* 14 */     if ((world.func_72805_g(i, j, k) & 0x8) > 0) {
/* 15 */       renderblocks.func_147782_a(0.0D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
/*    */     } else {
/* 17 */       renderblocks.func_147782_a(0.4D, 0.4D, 0.0D, 0.6D, 0.6D, 1.0D);
/*    */     } 
/* 19 */     renderblocks.func_147784_q(block, i, j, k);
/*    */     
/* 21 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 27 */     renderer.func_147782_a(0.0D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
/* 28 */     renderInvBlock(block, renderer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 34 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 40 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void renderInvBlock(Block block, RenderBlocks renderer) {
/* 45 */     Tessellator var14 = Tessellator.field_78398_a;
/* 46 */     var14.func_78382_b();
/* 47 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 48 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
/* 49 */     var14.func_78381_a();
/* 50 */     var14.func_78382_b();
/* 51 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 52 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 1));
/* 53 */     var14.func_78381_a();
/* 54 */     var14.func_78382_b();
/* 55 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 56 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
/* 57 */     var14.func_78381_a();
/* 58 */     var14.func_78382_b();
/* 59 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 60 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
/* 61 */     var14.func_78381_a();
/* 62 */     var14.func_78382_b();
/* 63 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 64 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
/* 65 */     var14.func_78381_a();
/* 66 */     var14.func_78382_b();
/* 67 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 68 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
/* 69 */     var14.func_78381_a();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderTuyere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */