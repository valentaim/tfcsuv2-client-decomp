/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.Devices.BlockBellows;
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import org.lwjgl.opengl.GL11;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderBellows
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 18 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
/* 25 */     renderer.field_147840_d = BlockBellows.bellowsFront;
/* 26 */     renderer.func_147782_a(0.0D, 0.0D, 0.8999999761581421D, 1.0D, 1.0D, 1.0D);
/* 27 */     renderInvBlock(block, renderer);
/*    */     
/* 29 */     renderer.func_147771_a();
/* 30 */     renderer.func_147782_a(0.10000000149011612D, 0.10000000149011612D, 0.05000000074505806D, 0.8999999761581421D, 0.8999999761581421D, 0.949999988079071D);
/* 31 */     renderInvBlock(block, renderer);
/*    */     
/* 33 */     renderer.field_147840_d = BlockBellows.bellowsBack;
/* 34 */     renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.10000000149011612D);
/* 35 */     renderInvBlock(block, renderer);
/*    */     
/* 37 */     renderer.func_147771_a();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 50 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void renderInvBlock(Block block, RenderBlocks renderer) {
/* 55 */     Tessellator var14 = Tessellator.field_78398_a;
/* 56 */     GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
/* 57 */     var14.func_78382_b();
/* 58 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 59 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
/* 60 */     var14.func_78381_a();
/* 61 */     var14.func_78382_b();
/* 62 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 63 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
/* 64 */     var14.func_78381_a();
/* 65 */     var14.func_78382_b();
/* 66 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 67 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
/* 68 */     var14.func_78381_a();
/* 69 */     var14.func_78382_b();
/* 70 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 71 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 3));
/* 72 */     var14.func_78381_a();
/* 73 */     var14.func_78382_b();
/* 74 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 75 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
/* 76 */     var14.func_78381_a();
/* 77 */     var14.func_78382_b();
/* 78 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 79 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
/* 80 */     var14.func_78381_a();
/* 81 */     GL11.glTranslatef(0.5F, 0.5F, 0.5F);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */