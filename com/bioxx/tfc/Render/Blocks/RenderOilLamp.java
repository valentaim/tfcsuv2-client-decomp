package com.bioxx.tfc.Render.Blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;







public class RenderOilLamp
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    renderer.field_147837_f = true;

    renderer.func_147782_a(0.275D, 0.0D, 0.275D, 0.725D, 0.0625D, 0.725D);
    renderer.func_147784_q(block, x, y, z);
    renderer.func_147782_a(0.25D, 0.0625D, 0.25D, 0.75D, 0.375D, 0.75D);
    renderer.func_147784_q(block, x, y, z);
    renderer.func_147782_a(0.3125D, 0.375D, 0.3125D, 0.6875D, 0.4375D, 0.6875D);
    renderer.func_147784_q(block, x, y, z);
    renderer.func_147782_a(0.375D, 0.4375D, 0.375D, 0.625D, 0.5D, 0.625D);
    renderer.func_147784_q(block, x, y, z);
    renderer.func_147782_a(0.46875D, 0.5D, 0.46875D, 0.53125D, 0.5625D, 0.53125D);
    renderer.func_147784_q(Blocks.field_150433_aE, x, y, z);
    renderer.field_147837_f = false;
    return true;
  }



  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
    renderer.func_147782_a(0.275D, 0.0D, 0.275D, 0.725D, 0.0625D, 0.725D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.25D, 0.0625D, 0.25D, 0.75D, 0.375D, 0.75D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.3125D, 0.375D, 0.3125D, 0.6875D, 0.4375D, 0.6875D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.375D, 0.4375D, 0.375D, 0.625D, 0.5D, 0.625D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.46875D, 0.5D, 0.46875D, 0.53125D, 0.5625D, 0.53125D);
    renderInvBlock(Blocks.field_150433_aE, meta, renderer);
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */