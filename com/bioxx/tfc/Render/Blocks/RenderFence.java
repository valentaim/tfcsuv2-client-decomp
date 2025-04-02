package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Vanilla.BlockTFCFence;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;









public class RenderFence
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderblocks) {
    BlockTFCFence par1BlockFence = (BlockTFCFence)block;
    boolean flag = false;
    float f = 0.375F;
    float f1 = 0.625F;
    renderblocks.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);
    renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
    flag = true;
    boolean flag1 = false;
    boolean flag2 = false;

    if (par1BlockFence.func_149826_e(renderblocks.field_147845_a, x - 1, y, z) || par1BlockFence.func_149826_e(renderblocks.field_147845_a, x + 1, y, z)) {
      flag1 = true;
    }
    if (par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z - 1) || par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z + 1)) {
      flag2 = true;
    }
    boolean flag3 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x - 1, y, z);
    boolean flag4 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x + 1, y, z);
    boolean flag5 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z - 1);
    boolean flag6 = par1BlockFence.func_149826_e(renderblocks.field_147845_a, x, y, z + 1);

    if (!flag1 && !flag2) {
      flag1 = true;
    }
    f = 0.4375F;
    f1 = 0.5625F;
    float f2 = 0.75F;
    float f3 = 0.9375F;
    float f4 = flag3 ? 0.0F : f;
    float f5 = flag4 ? 1.0F : f1;
    float f6 = flag5 ? 0.0F : f;
    float f7 = flag6 ? 1.0F : f1;

    if (flag1) {

      renderblocks.func_147782_a(f4, f2 + 0.001D, f + 0.001D, f5, f3 + 0.001D, f1 + 0.001D);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      renderblocks.func_147782_a(f5, f2, f1 - 0.001D, f4, f3, f - 0.001D);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      flag = true;
    }

    if (flag2) {

      renderblocks.func_147782_a(f - 0.001D, f2 + 0.001D, f6, f1 - 0.001D, f3 + 0.001D, f7);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      renderblocks.func_147782_a(f1 + 0.001D, f2, f7, f + 0.001D, f3, f6);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
      flag = true;
    }

    f2 = 0.375F;
    f3 = 0.5625F;

    if (flag1) {

      renderblocks.func_147782_a(f4, f2 + 0.001D, f + 0.001D, f5, f3 + 0.001D, f1 + 0.001D);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      renderblocks.func_147782_a(f5, f2, f1 - 0.001D, f4, f3, f - 0.001D);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      flag = true;
    }

    if (flag2) {

      renderblocks.func_147782_a(f - 0.001D, f2 + 0.001D, f6, f1 - 0.001D, f3 + 0.001D, f7);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);

      renderblocks.func_147782_a(f1 + 0.001D, f2, f7, f + 0.001D, f3, f6);
      renderblocks.func_147784_q((Block)par1BlockFence, x, y, z);
      flag = true;
    }

    par1BlockFence.func_149719_a(renderblocks.field_147845_a, x, y, z);
    return flag;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    float f = 0.375F;
    float f1 = 0.625F;
    renderer.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);

    renderInvBlock(block, metadata, renderer);

    renderer.func_147782_a(f, 0.0D, f, f1, 1.0D, f1);

    renderInvBlock2(block, metadata, renderer);

    f = 0.4375F;
    f1 = 0.5625F;
    float f2 = 0.75F;
    float f3 = 0.9375F;


    float f6 = 0.0F;
    float f7 = 1.0F;

    renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, f7 / 2.0D);
    renderInvBlock(block, metadata, renderer);

    renderer.func_147782_a(f1 + 0.001D, f2, f7 / 2.0D, f + 0.001D, f3, f6);
    renderInvBlock(block, metadata, renderer);

    f2 = 0.375F;
    f3 = 0.5625F;

    renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, f7 / 2.0D);
    renderInvBlock(block, metadata, renderer);

    renderer.func_147782_a(f1 + 0.001D, f2, f7 / 2.0D, f + 0.001D, f3, f6);
    renderInvBlock(block, metadata, renderer);
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
    GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */