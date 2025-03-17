/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.TileEntities.TEPottery;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderPottery
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 22 */     TEPottery te = (TEPottery)world.func_147438_o(x, y, z);
/* 23 */     boolean breaking = (renderer.field_147840_d != null);
/*    */     
/* 25 */     if (te.straw > 0) {
/*    */       
/* 27 */       if (!breaking) {
/* 28 */         renderer.field_147840_d = TFCBlocks.thatch.func_149673_e(world, x, y, z, 0);
/*    */       }
/* 30 */       renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D * te.straw, 1.0D);
/* 31 */       renderer.func_147784_q(block, x, y, z);
/*    */     } 
/*    */     
/* 34 */     if (te.wood > 0) {
/*    */       
/* 36 */       if (!breaking) {
/* 37 */         renderer.field_147840_d = TFCBlocks.logPile.func_149691_a(0, 0);
/*    */       }
/* 39 */       int w = te.wood;
/* 40 */       if (te.wood > 4) {
/*    */         
/* 42 */         w = te.wood - 4;
/* 43 */         renderer.func_147782_a(0.0D, 0.75D, 0.0D, 0.25D * w, 1.0D, 1.0D);
/* 44 */         renderer.func_147784_q(block, x, y, z);
/* 45 */         w = 4;
/*    */       } 
/* 47 */       renderer.func_147782_a(0.0D, 0.5D, 0.0D, 0.25D * w, 0.75D, 1.0D);
/* 48 */       renderer.func_147784_q(block, x, y, z);
/*    */     } 
/*    */     
/* 51 */     if (!breaking) {
/* 52 */       renderer.func_147771_a();
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 66 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderPottery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */