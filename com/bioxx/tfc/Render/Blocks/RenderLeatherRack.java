package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockLeatherRack;
import com.bioxx.tfc.TileEntities.TELeatherRack;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;




public class RenderLeatherRack
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    TELeatherRack te = (TELeatherRack)world.func_147438_o(x, y, z);
    BlockLeatherRack blk = (BlockLeatherRack)block;





    boolean breaking = (renderer.field_147840_d != null);

    for (int k = 0; k < 4; k++) {

      for (int i = 0; i < 4; i++) {

        if ((te.workedArea >> k * 4 + i & 0x1) != 0 && !breaking)
          renderer.field_147840_d = blk.scrapedTex;
        renderer.func_147782_a(0.25D * i, 0.0D, 0.25D * k, 0.25D * i + 0.25D, 0.001D, 0.25D * k + 0.25D);
        renderer.func_147784_q(block, x, y, z);
        if (!breaking)
          renderer.func_147771_a();
      }
    }
    return true;
  }




  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}




  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
    Tessellator tess = Tessellator.field_78398_a;
    tess.func_78382_b();
    tess.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, meta));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, meta));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, meta));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, meta));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, meta));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, meta));
    tess.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderLeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */