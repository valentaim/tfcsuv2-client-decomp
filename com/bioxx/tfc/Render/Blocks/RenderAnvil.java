package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockAnvil;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.Crafting.AnvilReq;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;


public class RenderAnvil
  implements ISimpleBlockRenderingHandler
{
  public static boolean renderAnvil(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;

    int meta = blockAccess.func_72805_g(i, j, k);
    int direction = BlockAnvil.getDirectionFromMetadata(meta);
    boolean breaking = false;
    if (renderblocks.field_147840_d != null) {
      breaking = true;
    }
    TEAnvil te = (TEAnvil)blockAccess.func_147438_o(i, j, k);
    if (te.anvilTier != AnvilReq.STONE.Tier) {

      if (direction == 0)
      {

        renderblocks.func_147782_a(0.30000001192092896D, 0.4000000059604645D, 0.10000000149011612D, 0.699999988079071D, 0.6000000238418579D, 0.8999999761581421D);
        renderblocks.func_147784_q(block, i, j, k);


        renderblocks.func_147782_a(0.3499999940395355D, 0.0D, 0.15000000596046448D, 0.6499999761581421D, 0.4000000059604645D, 0.8500000238418579D);
        renderblocks.func_147784_q(block, i, j, k);


        renderblocks.func_147782_a(0.25D, 0.0D, 0.10000000149011612D, 0.75D, 0.20000000298023224D, 0.8999999761581421D);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147782_a(0.20000000298023224D, 0.0D, 0.0D, 0.800000011920929D, 0.10000000149011612D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);


      }
      else if (direction == 1)
      {

        renderblocks.func_147782_a(0.10000000149011612D, 0.4000000059604645D, 0.30000001192092896D, 0.8999999761581421D, 0.6000000238418579D, 0.699999988079071D);
        renderblocks.func_147784_q(block, i, j, k);


        renderblocks.func_147782_a(0.15000000596046448D, 0.0D, 0.3499999940395355D, 0.8500000238418579D, 0.4000000059604645D, 0.6499999761581421D);
        renderblocks.func_147784_q(block, i, j, k);


        renderblocks.func_147782_a(0.10000000149011612D, 0.0D, 0.25D, 0.8999999761581421D, 0.20000000298023224D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147782_a(0.0D, 0.0D, 0.20000000298023224D, 1.0D, 0.10000000149011612D, 0.800000011920929D);
        renderblocks.func_147784_q(block, i, j, k);

      }

    }
    else {

      Block b = Block.func_149729_e(te.stonePair[0]);
      if (b != null) {

        if (!breaking)
        {
          renderblocks.field_147840_d = b.func_149691_a(0, te.stonePair[1]);
        }
        renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.8999999761581421D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);
        renderblocks.func_147771_a();
      }
    }
    return true;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.anvilRenderId) {

      renderer.func_147782_a(0.30000001192092896D, 0.4000000059604645D, 0.10000000149011612D, 0.699999988079071D, 0.6000000238418579D, 0.8999999761581421D);
      renderInvBlock(block, metadata, renderer);


      renderer.func_147782_a(0.3499999940395355D, 0.0D, 0.15000000596046448D, 0.6499999761581421D, 0.4000000059604645D, 0.8500000238418579D);
      renderInvBlock(block, metadata, renderer);


      renderer.func_147782_a(0.25D, 0.0D, 0.10000000149011612D, 0.75D, 0.20000000298023224D, 0.8999999761581421D);
      renderInvBlock(block, metadata, renderer);
      renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.0D, 0.800000011920929D, 0.10000000149011612D, 1.0D);
      renderInvBlock(block, metadata, renderer);
    }
  }



  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.anvilRenderId)
      return renderAnvil(block, x, y, z, renderer);
    return false;
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    int meta = m;
    if (meta >= 8) {
      meta -= 8;
    }
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */