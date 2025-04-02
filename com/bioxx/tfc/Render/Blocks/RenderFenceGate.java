package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEFenceGate;
import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;









public class RenderFenceGate
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int par2, int par3, int par4, Block block, int modelId, RenderBlocks renderer) {
    Block par1BlockFenceGate = ((IMultipleBlock)block).getBlockTypeForRender();
    boolean flag = true;
    int l = ((TEFenceGate)renderer.field_147845_a.func_147438_o(par2, par3, par4)).getDirection();
    boolean flag1 = ((TEFenceGate)renderer.field_147845_a.func_147438_o(par2, par3, par4)).getOpen();
    int i1 = BlockDirectional.func_149895_l(l);
    float f = 0.375F;
    float f1 = 0.5625F;
    float f2 = 0.75F;
    float f3 = 0.9375F;
    float f4 = 0.3125F;
    float f5 = 1.0F;

    if (((i1 == 2 || i1 == 0) && renderer.field_147845_a
      .func_147439_a(par2 - 1, par3, par4) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall && renderer.field_147845_a
      .func_147439_a(par2 + 1, par3, par4) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall) || ((i1 == 3 || i1 == 1) && renderer.field_147845_a

      .func_147439_a(par2, par3, par4 - 1) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall && renderer.field_147845_a
      .func_147439_a(par2, par3, par4 + 1) instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall)) {

      f -= 0.1875F;
      f1 -= 0.1875F;
      f2 -= 0.1875F;
      f3 -= 0.1875F;
      f4 -= 0.1875F;
      f5 -= 0.1875F;
    }






    if (i1 != 3 && i1 != 1) {

      float f6 = 0.0F;
      float f8 = 0.125F;
      float f7 = 0.4375F;
      float f9 = 0.5625F;
      renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f6 = 0.875F;
      f8 = 1.0F;
      renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
    }
    else {

      renderer.field_147867_u = 1;
      float f6 = 0.4375F;
      float f8 = 0.5625F;
      float f7 = 0.0F;
      float f9 = 0.125F;
      renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f7 = 0.875F;
      f9 = 1.0F;
      renderer.func_147782_a(f6, f4, f7, f8, f5, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      renderer.field_147867_u = 0;
    }

    if (flag1) {

      if (i1 == 2 || i1 == 0) {
        renderer.field_147867_u = 1;
      }




      if (i1 == 3)
      {
        float f6 = 0.0F;
        float f8 = 0.125F;
        float f7 = 0.875F;
        float f9 = 1.0F;



        renderer.func_147782_a(0.8125D, f, 0.0D, 0.9375D, f3, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.8125D, f, 0.875D, 0.9375D, f3, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.5625D, f, 0.0D, 0.8125D, f1, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.5625D, f, 0.875D, 0.8125D, f1, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.5625D, f2, 0.0D, 0.8125D, f3, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.5625D, f2, 0.875D, 0.8125D, f3, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      }
      else if (i1 == 1)
      {
        float f6 = 0.0F;
        float f8 = 0.125F;
        float f7 = 0.875F;
        float f9 = 1.0F;



        renderer.func_147782_a(0.0625D, f, 0.0D, 0.1875D, f3, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.0625D, f, 0.875D, 0.1875D, f3, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.1875D, f, 0.0D, 0.4375D, f1, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.1875D, f, 0.875D, 0.4375D, f1, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.1875D, f2, 0.0D, 0.4375D, f3, 0.125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.1875D, f2, 0.875D, 0.4375D, f3, 1.0D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      }
      else if (i1 == 0)
      {
        float f6 = 0.0F;
        float f8 = 0.125F;
        float f7 = 0.875F;
        float f9 = 1.0F;



        renderer.func_147782_a(0.0D, f, 0.8125D, 0.125D, f3, 0.9375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f, 0.8125D, 1.0D, f3, 0.9375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.0D, f, 0.5625D, 0.125D, f1, 0.8125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f, 0.5625D, 1.0D, f1, 0.8125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.0D, f2, 0.5625D, 0.125D, f3, 0.8125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f2, 0.5625D, 1.0D, f3, 0.8125D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      }
      else if (i1 == 2)
      {
        float f6 = 0.0F;
        float f8 = 0.125F;
        float f7 = 0.875F;
        float f9 = 1.0F;



        renderer.func_147782_a(0.0D, f, 0.0625D, 0.125D, f3, 0.1875D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f, 0.0625D, 1.0D, f3, 0.1875D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.0D, f, 0.1875D, 0.125D, f1, 0.4375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f, 0.1875D, 1.0D, f1, 0.4375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.0D, f2, 0.1875D, 0.125D, f3, 0.4375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
        renderer.func_147782_a(0.875D, f2, 0.1875D, 1.0D, f3, 0.4375D);
        renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      }

    } else if (i1 != 3 && i1 != 1) {

      float f6 = 0.375F;
      float f8 = 0.5F;
      float f7 = 0.4375F;
      float f9 = 0.5625F;
      renderer.func_147782_a(f6, f, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f6 = 0.5F;
      f8 = 0.625F;
      renderer.func_147782_a(f6, f, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f6 = 0.625F;
      f8 = 0.875F;
      renderer.func_147782_a(f6, f, f7, f8, f1, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f6 = 0.125F;
      f8 = 0.375F;
      renderer.func_147782_a(f6, f, f7, f8, f1, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
    }
    else {

      renderer.field_147867_u = 1;
      float f6 = 0.4375F;
      float f8 = 0.5625F;
      float f7 = 0.375F;
      float f9 = 0.5F;
      renderer.func_147782_a(f6, f, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f7 = 0.5F;
      f9 = 0.625F;
      renderer.func_147782_a(f6, f, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f7 = 0.625F;
      f9 = 0.875F;
      renderer.func_147782_a(f6, f, f7, f8, f1, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      f7 = 0.125F;
      f9 = 0.375F;
      renderer.func_147782_a(f6, f, f7, f8, f1, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
      renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
      renderer.func_147784_q(par1BlockFenceGate, par2, par3, par4);
    }

    renderer.field_147867_u = 0;
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    return flag;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    int l = 1;

    int i1 = BlockDirectional.func_149895_l(l);

    float f = 0.375F;
    float f1 = 0.5625F;
    float f2 = 0.75F;
    float f3 = 0.9375F;
    float f4 = 0.3125F;
    float f5 = 1.0F;





    if (i1 != 3 && i1 != 1) {

      float f10 = 0.0F;
      float f12 = 0.125F;
      float f11 = 0.4375F;
      float f13 = 0.5625F;
      renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
      renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
      f10 = 0.875F;
      f12 = 1.0F;
      renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
      renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    }
    else {

      renderer.field_147867_u = 1;
      float f10 = 0.4375F;
      float f12 = 0.5625F;
      float f11 = 0.0F;
      float f13 = 0.125F;
      renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
      renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
      f11 = 0.875F;
      f13 = 1.0F;
      renderer.func_147782_a(f10, f4, f11, f12, f5, f13);
      renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
      renderer.field_147867_u = 0;
    }

    renderer.field_147867_u = 1;
    float f6 = 0.4375F;
    float f8 = 0.5625F;
    float f7 = 0.375F;
    float f9 = 0.5F;
    renderer.func_147782_a(f6, f, f7, f8, f3, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    f7 = 0.5F;
    f9 = 0.625F;
    renderer.func_147782_a(f6, f, f7, f8, f3, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    f7 = 0.625F;
    f9 = 0.875F;
    renderer.func_147782_a(f6, f, f7, f8, f1, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    f7 = 0.125F;
    f9 = 0.375F;
    renderer.func_147782_a(f6, f, f7, f8, f1, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);
    renderer.func_147782_a(f6, f2, f7, f8, f3, f9);
    renderInvBlock2(((IMultipleBlock)block).getBlockTypeForRender(), metadata, renderer);

    renderer.field_147867_u = 0;
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
    var14.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }


  public static void renderInvBlock2(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
    var14.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */