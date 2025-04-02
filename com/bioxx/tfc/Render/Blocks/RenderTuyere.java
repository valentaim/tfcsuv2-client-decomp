package com.bioxx.tfc.Render.Blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;


public class RenderTuyere
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
    if ((world.func_72805_g(i, j, k) & 0x8) > 0) {
      renderblocks.func_147782_a(0.0D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
    } else {
      renderblocks.func_147782_a(0.4D, 0.4D, 0.0D, 0.6D, 0.6D, 1.0D);
    }
    renderblocks.func_147784_q(block, i, j, k);

    return false;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.func_147782_a(0.0D, 0.4D, 0.4D, 1.0D, 0.6D, 0.6D);
    renderInvBlock(block, renderer);
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 1));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
    var14.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderTuyere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */