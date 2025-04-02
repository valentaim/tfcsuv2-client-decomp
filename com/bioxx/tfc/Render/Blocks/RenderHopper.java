package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockHopper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderHopper
  implements ISimpleBlockRenderingHandler
{
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }





  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.func_147771_a();
  }



  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
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
    return renderBlockHopperMetadata(block, x, y, z, renderer.field_147845_a.func_72805_g(x, y, z), false, renderer);
  }


  public boolean renderBlockHopperMetadata(Block block, int x, int y, int z, int meta, boolean unknownBool, RenderBlocks renderer) {
    Tessellator tessellator = Tessellator.field_78398_a;
    int i1 = BlockHopper.getDirectionFromMetadata(meta);
    double d0 = 0.625D;
    renderer.func_147782_a(0.0D, d0, 0.0D, 1.0D, 1.0D, 1.0D);

    if (unknownBool) {

      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 0, meta));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 1, meta));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 2, meta));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 3, meta));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 4, meta));
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, renderer.func_147787_a(block, 5, meta));
      tessellator.func_78381_a();
    }
    else {

      renderer.func_147784_q(block, x, y, z);
    }



    if (!unknownBool) {

      tessellator.func_78380_c(block.func_149677_c(renderer.field_147845_a, x, y, z));
      int j1 = block.func_149720_d(renderer.field_147845_a, x, y, z);
      float f = (j1 >> 16 & 0xFF) / 255.0F;
      float f3 = (j1 >> 8 & 0xFF) / 255.0F;
      float f2 = (j1 & 0xFF) / 255.0F;

      if (EntityRenderer.field_78517_a) {

        float f6 = (f * 30.0F + f3 * 59.0F + f2 * 11.0F) / 100.0F;
        float f4 = (f * 30.0F + f3 * 70.0F) / 100.0F;
        float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
        f = f6;
        f3 = f4;
        f2 = f5;
      }

      tessellator.func_78386_a(f, f3, f2);
    }

    IIcon iicon = BlockHopper.getHopperIcon("hopper_outside");
    IIcon iicon1 = BlockHopper.getHopperIcon("hopper_inside");
    float f1 = 0.125F;

    if (unknownBool) {

      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f(block, (-1.0F + f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e(block, (1.0F - f1), 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d(block, 0.0D, 0.0D, (-1.0F + f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c(block, 0.0D, 0.0D, (1.0F - f1), iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b(block, 0.0D, -1.0D + d0, 0.0D, iicon1);
      tessellator.func_78381_a();
    }
    else {

      renderer.func_147764_f(block, (x - 1.0F + f1), y, z, iicon);
      renderer.func_147798_e(block, (x + 1.0F - f1), y, z, iicon);
      renderer.func_147734_d(block, x, y, (z - 1.0F + f1), iicon);
      renderer.func_147761_c(block, x, y, (z + 1.0F - f1), iicon);
      renderer.func_147806_b(block, x, (y - 1.0F) + d0, z, iicon1);
    }

    renderer.func_147757_a(iicon);
    double d3 = 0.25D;
    double d4 = 0.25D;
    renderer.func_147782_a(d3, d4, d3, 1.0D - d3, d0 - 0.002D, 1.0D - d3);

    if (unknownBool) {

      tessellator.func_78382_b();
      tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
      renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
      renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
      renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
      renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
      renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, iicon);
      tessellator.func_78381_a();
    }
    else {

      renderer.func_147784_q(block, x, y, z);
    }

    if (!unknownBool) {

      double d1 = 0.375D;
      double d2 = 0.25D;
      renderer.func_147757_a(iicon);

      if (i1 == 0) {

        renderer.func_147782_a(d1, 0.0D, d1, 1.0D - d1, 0.25D, 1.0D - d1);
        renderer.func_147784_q(block, x, y, z);
      }

      if (i1 == 2) {

        renderer.func_147782_a(d1, d4, 0.0D, 1.0D - d1, d4 + d2, d3);
        renderer.func_147784_q(block, x, y, z);
      }

      if (i1 == 3) {

        renderer.func_147782_a(d1, d4, 1.0D - d3, 1.0D - d1, d4 + d2, 1.0D);
        renderer.func_147784_q(block, x, y, z);
      }

      if (i1 == 4) {

        renderer.func_147782_a(0.0D, d4, d1, d3, d4 + d2, 1.0D - d1);
        renderer.func_147784_q(block, x, y, z);
      }

      if (i1 == 5) {

        renderer.func_147782_a(1.0D - d3, d4, d1, 1.0D, d4 + d2, 1.0D - d1);
        renderer.func_147784_q(block, x, y, z);
      }
    }

    renderer.func_147771_a();
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, RenderBlocks renderer) {
    Tessellator tess = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    tess.func_78382_b();
    tess.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 3));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
    tess.func_78381_a();
    tess.func_78382_b();
    tess.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
    tess.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */