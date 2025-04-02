package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Render.RenderBlocksLightCache;
import com.bioxx.tfc.TileEntities.TEDetailed;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;



public class RenderDetailed
{
  private static RenderBlocksLightCache renderer;

  public static boolean renderBlockDetailed(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    TEDetailed te = (TEDetailed)renderblocks.field_147845_a.func_147438_o(i, j, k);

    if (renderer == null) {
      renderer = new RenderBlocksLightCache(renderblocks);
    } else {
      renderer.update(renderblocks);
    }

    renderer.disableRender();
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
    renderer.func_147784_q(block, i, j, k);
    renderer.enableRender();

    if (te.typeID <= 0) {
      return false;
    }
    int type = te.typeID;
    int meta = te.metaID;

    IIcon myTexture = (renderblocks.field_147840_d == null) ? Block.func_149729_e(te.typeID).func_149691_a(0, te.metaID) : renderblocks.field_147840_d;

    for (int subX = 0; subX < 2; subX++) {

      for (int subZ = 0; subZ < 2; subZ++) {

        for (int subY = 0; subY < 2; subY++) {

          if (!te.isQuadSolid(subX, subY, subZ)) {

            renderMiniBlock(block, i, j, k, subX, subY, subZ, te, type, meta, myTexture);
          }
          else {

            float minX = 0.5F * subX;
            float maxX = minX + 0.5F;
            float minY = 0.5F * subY;
            float maxY = minY + 0.5F;
            float minZ = 0.5F * subZ;
            float maxZ = minZ + 0.5F;

            renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
            renderer.renderCachedBlock(block, i, j, k, myTexture);
          }
        }
      }
    }
    renderer.func_147771_a();
    return true;
  }


  private static void renderMiniBlock(Block block, int i, int j, int k, int x, int y, int z, TEDetailed te, int type, int meta, IIcon myTexture) {
    for (int subX = x * 4; subX < 4 + x * 4; subX++) {

      for (int subZ = z * 4; subZ < 4 + z * 4; subZ++) {

        for (int subY = y * 4; subY < 4 + y * 4; subY++) {

          boolean subExists = isOpaque(te, subX, subY, subZ);
          if (subExists) {






            boolean isVisible = (isTransparent(te, subX - 1, subY, subZ) || isTransparent(te, subX + 1, subY, subZ) || isTransparent(te, subX, subY - 1, subZ) || isTransparent(te, subX, subY + 1, subZ) || isTransparent(te, subX, subY, subZ - 1) || isTransparent(te, subX, subY, subZ + 1));

            if (isVisible) {

              float minX = 0.125F * subX;
              float maxX = minX + 0.125F;
              float minY = 0.125F * subY;
              float maxY = minY + 0.125F;
              float minZ = 0.125F * subZ;
              float maxZ = minZ + 0.125F;

              renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
              renderer.renderCachedBlock(block, i, j, k, myTexture);
            }
          }
        }
      }
    }
  }


  public static boolean isOpaque(TEDetailed te, int x, int y, int z) {
    return te.data.get((x * 8 + z) * 8 + y);
  }


  public static boolean isTransparent(TEDetailed te, int x, int y, int z) {
    if (x < 0 || x >= 8 || y < 0 || y >= 8 || z < 0 || z >= 8) {
      return true;
    }
    return !te.data.get((x * 8 + z) * 8 + y);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */