package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;



public class RenderFlora
{
  public static boolean render(Block block, int x, int y, int z, RenderBlocks renderer) {
    Block blockDirectlyAbove = renderer.field_147845_a.func_147439_a(x, y + 1, z);
    boolean hasAirTwoAbove = renderer.field_147845_a.func_147439_a(x, y + 2, z).isAir(renderer.field_147845_a, x, y + 2, z);
    if (TFC_Core.isWater(blockDirectlyAbove))
    {
      if (TFC_Core.isFreshWater(blockDirectlyAbove)) {

        if (hasAirTwoAbove)
        {
          renderCatTails(block, x, y + 1, z, renderer);
        }
        else
        {
          renderShortWaterPlant(block, x, y + 1, z, renderer, 1);
        }

      } else if (TFC_Core.isSaltWater(blockDirectlyAbove)) {
        renderShortWaterPlant(block, x, y + 1, z, renderer, 0);
      }
    }
    return true;
  }


  public static boolean renderShortWaterPlant(Block block, int x, int y, int z, RenderBlocks renderer, int plantType) {
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
    int l = block.func_149720_d(renderer.field_147845_a, x, y, z);
    float f = (l >> 16 & 0xFF) / 255.0F;
    float f1 = (l >> 8 & 0xFF) / 255.0F;
    float f2 = (l & 0xFF) / 255.0F;

    if (EntityRenderer.field_78517_a) {

      float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
      float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
      float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
      f = f3;
      f1 = f4;
      f2 = f5;
    }

    tessellator.func_78386_a(f, f1, f2);
    double d1 = x;
    double d2 = y;
    double d0 = z;


    if (block == Blocks.field_150329_H) {

      long i1 = (x * 3129871) ^ z * 116129781L ^ y;
      i1 = i1 * i1 * 42317861L + i1 * 11L;
      d1 += (((float)(i1 >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
      d2 += (((float)(i1 >> 20L & 0xFL) / 15.0F) - 1.0D) * 0.2D;
      d0 += (((float)(i1 >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.5D;
    }
    else if (block == Blocks.field_150328_O || block == Blocks.field_150327_N) {

      long i1 = (x * 3129871) ^ z * 116129781L ^ y;
      i1 = i1 * i1 * 42317861L + i1 * 11L;
      d1 += (((float)(i1 >> 16L & 0xFL) / 15.0F) - 0.5D) * 0.3D;
      d0 += (((float)(i1 >> 24L & 0xFL) / 15.0F) - 0.5D) * 0.3D;
    }

    IIcon iicon = block.func_149691_a(0, plantType);
    renderer.func_147765_a(iicon, d1, d2, d0, 1.0F);
    return true;
  }



  public static void renderCatTails(Block block, int x, int y, int z, RenderBlocks renderer) {
    IIcon icon = block.func_149691_a(0, 2);
    renderer.func_147765_a(icon, x, y, z, 2.0F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFlora.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */