/*    */ package com.bioxx.tfc;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.init.Blocks;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderFoodPrep
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int a, int b, int c, Block block, int modelId, RenderBlocks renderer) {
/* 22 */     Block ublock = world.func_147439_a(a, b - 1, c);
/* 23 */     IIcon icon = null;
/* 24 */     for (int i = 0; i < 4 && icon == null; i++)
/* 25 */       icon = ublock.func_149673_e(world, a, b - 1, c, i); 
/* 26 */     if (icon == null) icon = Blocks.field_150348_b.func_149733_h(0); 
/* 27 */     renderer.func_147757_a(icon);
/* 28 */     renderer.func_147784_q(block, a, b, c);
/* 29 */     renderer.func_147771_a();
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 37 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 43 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\RenderFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */