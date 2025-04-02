package com.bioxx.tfc.Render.Blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;








public class RenderSmoke
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    renderer.field_147863_w = false;
    Tessellator tessellator = Tessellator.field_78398_a;
    boolean flag = false;
    float f3 = 0.5F;
    float f4 = 1.0F;
    float f5 = 0.8F;
    float f6 = 0.6F;
    float f7 = f4 * 1.0F;
    float f8 = f4 * 1.0F;
    float f9 = f4 * 1.0F;
    float f10 = f3;
    float f11 = f5;
    float f12 = f6;
    float f13 = f3;
    float f14 = f5;
    float f15 = f6;
    float f16 = f3;
    float f17 = f5;
    float f18 = f6;
    float alpha = 0.8F;



    int l = block.func_149677_c(world, x, y, z);

    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y - 1, z, 0)) {

      tessellator.func_78380_c((renderer.field_147855_j > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y - 1, z));
      tessellator.func_78369_a(f10, f13, f16, alpha);
      renderer.func_147768_a(block, x, y, z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 0));
      flag = true;
    }

    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y + 1, z, 1)) {

      tessellator.func_78380_c((renderer.field_147857_k < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y + 1, z));
      tessellator.func_78369_a(f7, f8, f9, alpha);
      renderer.func_147806_b(block, x, y, z, renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 1));
      flag = true;
    }


    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z - 1, 2)) {

      tessellator.func_78380_c((renderer.field_147851_l > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y, z - 1));
      tessellator.func_78369_a(f11, f14, f17, alpha);
      IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 2);
      renderer.func_147761_c(block, x, y, z, iicon);
      flag = true;
    }

    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x, y, z + 1, 3)) {

      tessellator.func_78380_c((renderer.field_147853_m < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x, y, z + 1));
      tessellator.func_78369_a(f11, f14, f17, alpha);
      IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 3);
      renderer.func_147734_d(block, x, y, z, iicon);
      flag = true;
    }

    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x - 1, y, z, 4)) {

      tessellator.func_78380_c((renderer.field_147859_h > 0.0D) ? l : block.func_149677_c(renderer.field_147845_a, x - 1, y, z));
      tessellator.func_78369_a(f12, f15, f18, alpha);
      IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 4);
      renderer.func_147798_e(block, x, y, z, iicon);
      flag = true;
    }

    if (renderer.field_147837_f || block.func_149646_a(renderer.field_147845_a, x + 1, y, z, 5)) {

      tessellator.func_78380_c((renderer.field_147861_i < 1.0D) ? l : block.func_149677_c(renderer.field_147845_a, x + 1, y, z));
      tessellator.func_78369_a(f12, f15, f18, alpha);
      IIcon iicon = renderer.func_147793_a(block, renderer.field_147845_a, x, y, z, 5);
      renderer.func_147764_f(block, x, y, z, iicon);
      flag = true;
    }
    return flag;
  }




  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}




  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */