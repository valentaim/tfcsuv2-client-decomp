package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;



public class RenderGrass
{
  public static boolean render(Block block, int x, int y, int z, RenderBlocks renderer) {
    float red = 1.0F;
    float green = 1.0F;
    float blue = 1.0F;

    if (block == TFCBlocks.grass || block == TFCBlocks.dryGrass) {
      renderer.func_147751_a(TFCBlocks.dirt, x, y, z, red, blue, green);
    } else if (block == TFCBlocks.grass2 || block == TFCBlocks.dryGrass2) {
      renderer.func_147751_a(TFCBlocks.dirt2, x, y, z, red, blue, green);
    }
    renderer.func_147784_q(block, x, y, z);

    return true;
  }


  public static boolean renderClay(Block block, int x, int y, int z, RenderBlocks renderer) {
    float red = 1.0F;
    float green = 1.0F;
    float blue = 1.0F;

    if (block == TFCBlocks.clayGrass) {
      renderer.func_147751_a(TFCBlocks.clay, x, y, z, red, blue, green);
    } else if (block == TFCBlocks.clayGrass2) {
      renderer.func_147751_a(TFCBlocks.clay2, x, y, z, red, blue, green);
    }
    renderer.func_147784_q(block, x, y, z);

    return true;
  }


  public static boolean renderPeat(Block block, int x, int y, int z, RenderBlocks renderer) {
    float red = 1.0F;
    float green = 1.0F;
    float blue = 1.0F;

    renderer.func_147751_a(TFCBlocks.peat, x, y, z, red, blue, green);

    renderer.func_147784_q(block, x, y, z);

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */