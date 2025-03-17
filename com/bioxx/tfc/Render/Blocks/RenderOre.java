/*    */ package com.bioxx.tfc.Render.Blocks;
/*    */ 
/*    */ import com.bioxx.tfc.TileEntities.TEOre;
/*    */ import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.renderer.RenderBlocks;
/*    */ import net.minecraft.client.renderer.Tessellator;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.IBlockAccess;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RenderOre
/*    */   implements ISimpleBlockRenderingHandler
/*    */ {
/*    */   public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}
/*    */   
/*    */   public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
/* 24 */     boolean breaking = (renderer.field_147840_d != null);
/*    */     
/* 26 */     if (breaking) {
/* 27 */       renderer.func_147784_q(block, x, y, z);
/*    */     }
/*    */     else {
/*    */       
/* 31 */       renderer.field_147840_d = getRockTexture((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
/* 32 */       renderer.func_147784_q(block, x, y, z);
/* 33 */       renderer.func_147771_a();
/*    */ 
/*    */       
/* 36 */       renderer.func_147784_q(block, x, y, z);
/*    */     } 
/*    */     
/* 39 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static IIcon getRockTexture(World worldObj, int xCoord, int yCoord, int zCoord) {
/* 44 */     TEOre te = (TEOre)worldObj.func_147438_o(xCoord, yCoord, zCoord);
/* 45 */     if (te != null && te.baseBlockID > 0)
/*    */     {
/* 47 */       return Block.func_149729_e(te.baseBlockID).func_149691_a(5, te.baseBlockMeta);
/*    */     }
/* 49 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldRender3DInInventory(int modelId) {
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRenderId() {
/* 61 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
/* 66 */     Tessellator var14 = Tessellator.field_78398_a;
/* 67 */     var14.func_78382_b();
/* 68 */     var14.func_78375_b(0.0F, -1.0F, 0.0F);
/* 69 */     renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
/* 70 */     var14.func_78381_a();
/* 71 */     var14.func_78382_b();
/* 72 */     var14.func_78375_b(0.0F, 1.0F, 0.0F);
/* 73 */     renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
/* 74 */     var14.func_78381_a();
/* 75 */     var14.func_78382_b();
/* 76 */     var14.func_78375_b(0.0F, 0.0F, -1.0F);
/* 77 */     renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
/* 78 */     var14.func_78381_a();
/* 79 */     var14.func_78382_b();
/* 80 */     var14.func_78375_b(0.0F, 0.0F, 1.0F);
/* 81 */     renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
/* 82 */     var14.func_78381_a();
/* 83 */     var14.func_78382_b();
/* 84 */     var14.func_78375_b(-1.0F, 0.0F, 0.0F);
/* 85 */     renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
/* 86 */     var14.func_78381_a();
/* 87 */     var14.func_78382_b();
/* 88 */     var14.func_78375_b(1.0F, 0.0F, 0.0F);
/* 89 */     renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
/* 90 */     var14.func_78381_a();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */