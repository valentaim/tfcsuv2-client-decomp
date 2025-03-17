/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.Devices.BlockLeatherRack;
/*    */ import com.bioxx.tfc.TileEntities.TELeatherRack;
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderLeatherRack
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 18 */     TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
/* 19 */     BlockLeatherRack blk = (BlockLeatherRack)block;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     boolean breaking = (renderer.field_147840_d != null);
/*    */     
/* 27 */     for (int k = 0; k < 4; k++) {
/*    */       
/* 29 */       for (int i = 0; i < 4; i++) {
/*    */         
/* 31 */         if ((te.workedArea >> k * 4 + i & 0x1) != 0 && !breaking)
/* 32 */           renderer.field_147840_d = blk.scrapedTex; 
/* 33 */         renderer.func_147782_a(0.25D * i, 0.0D, 0.25D * k, 0.25D * i + 0.25D, 0.001D, 0.25D * k + 0.25D);
/* 34 */         renderer.func_147784_q(block, x, y, z);
/* 35 */         if (!breaking)
/* 36 */           renderer.func_147771_a(); 
/*    */       } 
/*    */     } 
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 57 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
/* 62 */     Tessellator tess = Tessellator.field_78398_a;
/* 63 */     tess.func_78382_b();
/* 64 */     tess.func_78375_b(0.0F, -1.0F, 0.0F);
/* 65 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 66 */     tess.func_78381_a();
/* 67 */     tess.func_78382_b();
/* 68 */     tess.func_78375_b(0.0F, 1.0F, 0.0F);
/* 69 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 70 */     tess.func_78381_a();
/* 71 */     tess.func_78382_b();
/* 72 */     tess.func_78375_b(0.0F, 0.0F, -1.0F);
/* 73 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 74 */     tess.func_78381_a();
/* 75 */     tess.func_78382_b();
/* 76 */     tess.func_78375_b(0.0F, 0.0F, 1.0F);
/* 77 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 78 */     tess.func_78381_a();
/* 79 */     tess.func_78382_b();
/* 80 */     tess.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 81 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 82 */     tess.func_78381_a();
/* 83 */     tess.func_78382_b();
/* 84 */     tess.func_78375_b(1.0F, 0.0F, 0.0F);
/* 85 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 86 */     tess.func_78381_a();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderLeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */