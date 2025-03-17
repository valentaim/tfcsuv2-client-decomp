/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderTorch
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 19 */     int meta = world.func_72805_g(x, y, z);
/* 20 */     Tessellator tessellator = Tessellator.field_78398_a;
/* 21 */     tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
/* 22 */     tessellator.func_78386_a(1.0F, 1.0F, 1.0F);
/* 23 */     double d0 = 0.4000000059604645D;
/* 24 */     double d1 = 0.5D - d0;
/* 25 */     double d2 = 0.20000000298023224D;
/*    */     
/* 27 */     if ((meta & 0x7) == 1) {
/*    */       
/* 29 */       renderer.func_147747_a(block, x - d1, y + d2, z, -d0, 0.0D, meta);
/*    */     }
/* 31 */     else if ((meta & 0x7) == 2) {
/*    */       
/* 33 */       renderer.func_147747_a(block, x + d1, y + d2, z, d0, 0.0D, meta);
/*    */     }
/* 35 */     else if ((meta & 0x7) == 3) {
/*    */       
/* 37 */       renderer.func_147747_a(block, x, y + d2, z - d1, 0.0D, -d0, meta);
/*    */     }
/* 39 */     else if ((meta & 0x7) == 4) {
/*    */       
/* 41 */       renderer.func_147747_a(block, x, y + d2, z + d1, 0.0D, d0, meta);
/*    */     }
/*    */     else {
/*    */       
/* 45 */       renderer.func_147747_a(block, x, y, z, 0.0D, 0.0D, meta);
/*    */     } 
/*    */     
/* 48 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 65 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */