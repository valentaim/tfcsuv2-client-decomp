package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEToolRack;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;




public class RenderToolRack
  implements ISimpleBlockRenderingHandler
{
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderblocks) {
    renderblocks.field_147840_d = block.func_149691_a(0, metadata);

    renderblocks.func_147782_a(0.30000001192092896D, 0.3D, 0.949999988079071D, 1.7000000476837158D, 0.44999998807907104D, 1.100000023841858D);
    renderInvBlock(block, metadata, renderblocks);

    renderblocks.func_147782_a(0.30000001192092896D, 0.9D, 0.949999988079071D, 1.7000000476837158D, 1.0499999523162842D, 1.100000023841858D);
    renderInvBlock(block, metadata, renderblocks);

    renderblocks.func_147771_a();
  }



  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;
    TEToolRack te = (TEToolRack)blockAccess.func_147438_o(i, j, k);
    int dir = blockAccess.func_72805_g(i, j, k);
    boolean breaking = (renderblocks.field_147840_d != null);
    if (te != null) {

      if (!breaking) {
        renderblocks.field_147840_d = block.func_149691_a(0, te.woodType);
      }





      if (dir == 0) {

        renderRackDir0(block, i, j, k, renderblocks, 0.7F);
        renderRackDir0(block, i, j, k, renderblocks, 0.3F);
      }
      else if (dir == 1) {

        renderRackDir1(block, i, j, k, renderblocks, 0.7F);
        renderRackDir1(block, i, j, k, renderblocks, 0.3F);
      }
      else if (dir == 2) {

        renderRackDir2(block, i, j, k, renderblocks, 0.7F);
        renderRackDir2(block, i, j, k, renderblocks, 0.3F);
      }
      else if (dir == 3) {

        renderRackDir3(block, i, j, k, renderblocks, 0.7F);
        renderRackDir3(block, i, j, k, renderblocks, 0.3F);
      }
    }

    if (!breaking) {
      renderblocks.func_147771_a();
    }
    return true;
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  private static void renderRackDir0(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
    renderblocks.func_147782_a(0.0D, yOffset, 0.949999988079071D, 1.0D, (yOffset + 0.05F), 1.0D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.20000000298023224D, yOffset, 0.8999999761581421D, 0.2199999988079071D, (yOffset + 0.05F), 0.949999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.30000001192092896D, yOffset, 0.8999999761581421D, 0.3199999928474426D, (yOffset + 0.05F), 0.949999988079071D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.6800000071525574D, yOffset, 0.8999999761581421D, 0.699999988079071D, (yOffset + 0.05F), 0.949999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.7799999713897705D, yOffset, 0.8999999761581421D, 0.800000011920929D, (yOffset + 0.05F), 0.949999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
  }


  private static void renderRackDir1(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
    renderblocks.func_147782_a(0.0D, yOffset, 0.0D, 0.05000000074505806D, (yOffset + 0.05F), 1.0D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.20000000298023224D, 0.10000000149011612D, (yOffset + 0.05F), 0.2199999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.30000001192092896D, 0.10000000149011612D, (yOffset + 0.05F), 0.3199999928474426D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.6800000071525574D, 0.10000000149011612D, (yOffset + 0.05F), 0.699999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.05000000074505806D, yOffset, 0.7799999713897705D, 0.10000000149011612D, (yOffset + 0.05F), 0.800000011920929D);
    renderblocks.func_147784_q(block, i, j, k);
  }


  private static void renderRackDir2(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
    renderblocks.func_147782_a(0.0D, yOffset, 0.0D, 1.0D, (yOffset + 0.05F), 0.05000000074505806D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.20000000298023224D, yOffset, 0.05000000074505806D, 0.2199999988079071D, (yOffset + 0.05F), 0.10000000149011612D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.30000001192092896D, yOffset, 0.05000000074505806D, 0.3199999928474426D, (yOffset + 0.05F), 0.10000000149011612D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.6800000071525574D, yOffset, 0.05000000074505806D, 0.699999988079071D, (yOffset + 0.05F), 0.10000000149011612D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.7799999713897705D, yOffset, 0.05000000074505806D, 0.800000011920929D, (yOffset + 0.05F), 0.10000000149011612D);
    renderblocks.func_147784_q(block, i, j, k);
  }


  private static void renderRackDir3(Block block, int i, int j, int k, RenderBlocks renderblocks, float yOffset) {
    renderblocks.func_147782_a(0.949999988079071D, yOffset, 0.0D, 1.0D, (yOffset + 0.05F), 1.0D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.20000000298023224D, 0.949999988079071D, (yOffset + 0.05F), 0.2199999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.30000001192092896D, 0.949999988079071D, (yOffset + 0.05F), 0.3199999928474426D);
    renderblocks.func_147784_q(block, i, j, k);

    renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.6800000071525574D, 0.949999988079071D, (yOffset + 0.05F), 0.699999988079071D);
    renderblocks.func_147784_q(block, i, j, k);
    renderblocks.func_147782_a(0.8999999761581421D, yOffset, 0.7799999713897705D, 0.949999988079071D, (yOffset + 0.05F), 0.800000011920929D);
    renderblocks.func_147784_q(block, i, j, k);
  }


  public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
    var14.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */