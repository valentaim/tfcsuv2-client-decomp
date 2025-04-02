package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEBarrel;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderVessel
  implements ISimpleBlockRenderingHandler
{
  private static final float MIN = 0.2F;
  private static final float MAX = 0.8F;

  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    TEBarrel te = (TEBarrel)world.func_147438_o(x, y, z);
    renderer.field_147837_f = true;

    if ((te.rotation & Byte.MIN_VALUE) == 0) {

      if (te.getSealed()) {

        renderer.func_147782_a(0.17499999701976776D, 0.550000011920929D, 0.17499999701976776D, 0.824999988079071D, 0.6499999761581421D, 0.824999988079071D);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147782_a(0.4375D, 0.6499999761581421D, 0.4375D, 0.5625D, 0.699999988079071D, 0.5625D);
        renderer.func_147784_q(block, x, y, z);
        renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 0.05000000074505806D, 0.75D);
        renderer.func_147784_q(block, x, y, z);
      }
      else {

        renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 0.05000000074505806D, 0.75D);
        renderer.func_147784_q(block, x, y, z);

        if (te.fluid != null && renderer.field_147840_d == null) {

          int color = te.fluid.getFluid().getColor(te.fluid);
          float f = (color >> 16 & 0xFF) / 255.0F;
          float f1 = (color >> 8 & 0xFF) / 255.0F;
          float f2 = (color & 0xFF) / 255.0F;
          float h = 0.5F * te.fluid.amount / te.getMaxLiquid();
          renderer.func_147782_a(0.25D, 0.05D, 0.25D, 0.75D, (0.05F + h), 0.75D);
          IIcon still = te.fluid.getFluid().getStillIcon();
          renderer.func_147757_a(still);
          renderer.func_147736_d(block, x, y, z, f, f1, f2);
          renderer.func_147771_a();
        }
      }
      renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.25D, 0.25D, 0.6000000238418579D, 0.75D);
      renderer.func_147784_q(block, x, y, z);

      renderer.func_147782_a(0.75D, 0.0D, 0.25D, 0.800000011920929D, 0.6000000238418579D, 0.75D);
      renderer.func_147784_q(block, x, y, z);

      renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.6000000238418579D, 0.25D);
      renderer.func_147784_q(block, x, y, z);

      renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.75D, 0.800000011920929D, 0.6000000238418579D, 0.800000011920929D);
      renderer.func_147784_q(block, x, y, z);

      renderer.func_147782_a(0.25D, 0.05D, 0.25D, 0.75D, 0.10000000149011612D, 0.75D);
      renderer.func_147784_q(block, x, y, z);
    }
    else {

      if ((te.rotation & 0x3) == 0) {

        renderer.func_147782_a(0.20000000298023224D, 0.20000000298023224D, 0.25D, 0.949999988079071D, 0.25D, 0.75D);
        renderer.func_147784_q(block, x, y, z);
      }
      if ((te.rotation & 0x3) == 1) {

        renderer.func_147782_a(0.25D, 0.20000000298023224D, 0.20000000298023224D, 0.75D, 0.25D, 0.949999988079071D);
        renderer.func_147784_q(block, x, y, z);
      }
    }


    renderer.field_147837_f = false;

    return true;
  }


  public void rotate(RenderBlocks renderer, int i) {
    renderer.field_147875_q = i;
    renderer.field_147873_r = i;
    renderer.field_147869_t = i;
    renderer.field_147871_s = i;
  }



  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
    renderer.func_147782_a(0.17499999701976776D, 0.550000011920929D, 0.17499999701976776D, 0.824999988079071D, 0.6499999761581421D, 0.824999988079071D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.4375D, 0.6499999761581421D, 0.4375D, 0.5625D, 0.699999988079071D, 0.5625D);
    renderInvBlock(block, meta, renderer);
    renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.6000000238418579D, 0.800000011920929D);
    renderInvBlock(block, meta, renderer);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */