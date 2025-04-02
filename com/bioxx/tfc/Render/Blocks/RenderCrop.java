package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.BlockCrop;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.TileEntities.TEFarmland;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderCrop
{
  public static boolean render(Block block, int x, int y, int z, RenderBlocks renderblocks) {
    IBlockAccess blockaccess = renderblocks.field_147845_a;
    TECrop cropTE = (TECrop)blockaccess.func_147438_o(x, y, z);

    if (cropTE != null) {
      CropManager.getInstance().getCropFromId(cropTE.cropId);
    } else {
      return false;
    }
    Tessellator var9 = Tessellator.field_78398_a;
    var9.func_78380_c(block.func_149677_c(blockaccess, x, y, z));
    switch (cropTE.cropId) {


      case 0:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.5D, 1.0D);
        break;


      case 1:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 1.0D, 2.0D);
        break;


      case 2:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.9D, 2.0D);
        break;


      case 3:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.5D, 1.0D);
        break;


      case 4:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.5D, 1.0D);
        break;


      case 5:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.5D, 1.0D);
        break;


      case 6:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.5D, 1.0D);
        break;


      case 7:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 8:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 9:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 10:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 11:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 12:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 13:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 14:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 15:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 16:
        drawCrossedSquares(block, x, y, z, renderblocks, 0.45D, 1.0D);
        break;


      case 17:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 0.8D, 2.0D);
        break;


      case 18:
        renderBlockCropsImpl(block, x, y, z, renderblocks, 1.0D, 2.0D);
        break;


      default:
        renderblocks.func_147796_n(block, x, y, z);
        break;
    }

    TileEntity te = blockaccess.func_147438_o(x, y - 1, z);
    TEFarmland tef = null;
    if (te instanceof TEFarmland)
      tef = (TEFarmland)te;
    if (tef != null && tef.isInfested) {


      Tessellator tessellator = Tessellator.field_78398_a;


      tessellator.func_78374_a((x + 0), y + 0.001D, (z + 1), ((BlockCrop)block).iconInfest.func_94209_e(), ((BlockCrop)block).iconInfest.func_94210_h());
      tessellator.func_78374_a((x + 1), y + 0.001D, (z + 1), ((BlockCrop)block).iconInfest.func_94212_f(), ((BlockCrop)block).iconInfest.func_94210_h());
      tessellator.func_78374_a((x + 1), y + 0.001D, (z + 0), ((BlockCrop)block).iconInfest.func_94212_f(), ((BlockCrop)block).iconInfest.func_94206_g());
      tessellator.func_78374_a((x + 0), y + 0.001D, (z + 0), ((BlockCrop)block).iconInfest.func_94209_e(), ((BlockCrop)block).iconInfest.func_94206_g());
    }


    return true;
  }


  private static void renderBlockCropsImpl(Block block, double i, double j, double k, RenderBlocks renderblocks, double width, double height) {
    Tessellator tess = Tessellator.field_78398_a;
    GL11.glColor3f(1.0F, 1.0F, 1.0F);
    int brightness = block.func_149677_c(renderblocks.field_147845_a, (int)i, (int)j, (int)k);
    tess.func_78380_c(brightness);
    tess.func_78386_a(1.0F, 1.0F, 1.0F);

    IIcon icon = block.func_149673_e(renderblocks.field_147845_a, (int)i, (int)j, (int)k, renderblocks.field_147845_a.func_72805_g((int)i, (int)j, (int)k));
    if (renderblocks.func_147744_b()) {
      icon = renderblocks.field_147840_d;
    }
    if (icon != null) {

      if (((int)i & 0x1) > 0)
      {
        k += 1.0E-4D;
      }
      if (((int)k & 0x1) > 0)
      {
        i += 1.0E-4D;
      }

      double minU = icon.func_94209_e();
      double maxU = icon.func_94212_f();
      double minV = icon.func_94206_g();
      double maxV = icon.func_94210_h();
      double minX = i + 0.25D;
      double maxX = i + 0.75D;
      double minZ = k + 0.5D - width;
      double maxZ = k + 0.5D + width;
      double y = j;

      tess.func_78374_a(minX, y + height, minZ, minU, minV);
      tess.func_78374_a(minX, y, minZ, minU, maxV);
      tess.func_78374_a(minX, y, maxZ, maxU, maxV);
      tess.func_78374_a(minX, y + height, maxZ, maxU, minV);
      tess.func_78374_a(minX, y + height, maxZ, minU, minV);
      tess.func_78374_a(minX, y, maxZ, minU, maxV);
      tess.func_78374_a(minX, y, minZ, maxU, maxV);
      tess.func_78374_a(minX, y + height, minZ, maxU, minV);
      tess.func_78374_a(maxX, y + height, maxZ, minU, minV);
      tess.func_78374_a(maxX, y, maxZ, minU, maxV);
      tess.func_78374_a(maxX, y, minZ, maxU, maxV);
      tess.func_78374_a(maxX, y + height, minZ, maxU, minV);
      tess.func_78374_a(maxX, y + height, minZ, minU, minV);
      tess.func_78374_a(maxX, y, minZ, minU, maxV);
      tess.func_78374_a(maxX, y, maxZ, maxU, maxV);
      tess.func_78374_a(maxX, y + height, maxZ, maxU, minV);
      minX = i + 0.5D - width;
      maxX = i + 0.5D + width;
      minZ = k + 0.5D - 0.25D;
      maxZ = k + 0.5D + 0.25D;
      tess.func_78374_a(minX, y + height, minZ, minU, minV);
      tess.func_78374_a(minX, y, minZ, minU, maxV);
      tess.func_78374_a(maxX, y, minZ, maxU, maxV);
      tess.func_78374_a(maxX, y + height, minZ, maxU, minV);
      tess.func_78374_a(maxX, y + height, minZ, minU, minV);
      tess.func_78374_a(maxX, y, minZ, minU, maxV);
      tess.func_78374_a(minX, y, minZ, maxU, maxV);
      tess.func_78374_a(minX, y + height, minZ, maxU, minV);
      tess.func_78374_a(maxX, y + height, maxZ, minU, minV);
      tess.func_78374_a(maxX, y, maxZ, minU, maxV);
      tess.func_78374_a(minX, y, maxZ, maxU, maxV);
      tess.func_78374_a(minX, y + height, maxZ, maxU, minV);
      tess.func_78374_a(minX, y + height, maxZ, minU, minV);
      tess.func_78374_a(minX, y, maxZ, minU, maxV);
      tess.func_78374_a(maxX, y, maxZ, maxU, maxV);
      tess.func_78374_a(maxX, y + height, maxZ, maxU, minV);
    }
  }


  private static void drawCrossedSquares(Block block, double x, double y, double z, RenderBlocks renderblocks, double width, double height) {
    Tessellator tess = Tessellator.field_78398_a;
    GL11.glColor3f(1.0F, 1.0F, 1.0F);

    int brightness = block.func_149677_c(renderblocks.field_147845_a, (int)x, (int)y, (int)z);
    tess.func_78380_c(brightness);
    tess.func_78386_a(1.0F, 1.0F, 1.0F);

    IIcon icon = block.func_149673_e(renderblocks.field_147845_a, (int)x, (int)y, (int)z, renderblocks.field_147845_a.func_72805_g((int)x, (int)y, (int)z));
    if (renderblocks.func_147744_b()) {
      icon = renderblocks.field_147840_d;
    }
    double minU = icon.func_94209_e();
    double maxU = icon.func_94212_f();
    double minV = icon.func_94206_g();
    double maxV = icon.func_94210_h();

    double minX = x + 0.5D - width;
    double maxX = x + 0.5D + width;
    double minZ = z + 0.5D - width;
    double maxZ = z + 0.5D + width;

    tess.func_78374_a(minX, y + height, minZ, minU, minV);
    tess.func_78374_a(minX, y + 0.0D, minZ, minU, maxV);
    tess.func_78374_a(maxX, y + 0.0D, maxZ, maxU, maxV);
    tess.func_78374_a(maxX, y + height, maxZ, maxU, minV);

    tess.func_78374_a(maxX, y + height, maxZ, minU, minV);
    tess.func_78374_a(maxX, y + 0.0D, maxZ, minU, maxV);
    tess.func_78374_a(minX, y + 0.0D, minZ, maxU, maxV);
    tess.func_78374_a(minX, y + height, minZ, maxU, minV);

    tess.func_78374_a(minX, y + height, maxZ, minU, minV);
    tess.func_78374_a(minX, y + 0.0D, maxZ, minU, maxV);
    tess.func_78374_a(maxX, y + 0.0D, minZ, maxU, maxV);
    tess.func_78374_a(maxX, y + height, minZ, maxU, minV);

    tess.func_78374_a(maxX, y + height, minZ, minU, minV);
    tess.func_78374_a(maxX, y + 0.0D, minZ, minU, maxV);
    tess.func_78374_a(minX, y + 0.0D, maxZ, maxU, maxV);
    tess.func_78374_a(minX, y + height, maxZ, maxU, minV);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderCrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */