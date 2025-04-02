package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.BlockMetalSheet;
import com.bioxx.tfc.Blocks.BlockMetalTrapDoor;
import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;


public class RenderMetalTrapDoor
  implements ISimpleBlockRenderingHandler
{
  public static boolean render(Block block, int i, int j, int k, RenderBlocks renderer) {
    IBlockAccess access = renderer.field_147845_a;
    TEMetalTrapDoor te = (TEMetalTrapDoor)access.func_147438_o(i, j, k);
    int side = te.data & 0x7;
    int hinge = te.data >> 4;
    float f = 0.0625F;



    float fx = 0.0F;
    float fy = 0.0F;
    float fz = 0.0F;
    float fx2 = 1.0F;
    float fy2 = 1.0F;
    float fz2 = 1.0F;
    renderer.field_147837_f = true;
    if (BlockMetalTrapDoor.isTrapdoorOpen(access.func_72805_g(i, j, k))) {

      if (hinge == 0) {

        switch (side) {


          case 0:
            fx2 = f;
            fy2 = 1.0F - f;
            break;


          case 1:
            fy = f;
            fx2 = f;
            break;


          case 2:
            fx2 = f;
            fz2 = 1.0F - f;
            break;


          case 3:
            fz = f;
            fx2 = f;
            break;


          case 4:
            fy2 = f;
            fx2 = 1.0F - f;
            break;


          case 5:
            fy2 = f;
            fx = f;
            break;

          default:
            fx2 = f;
            break;
        }

      } else if (hinge == 1) {

        switch (side) {


          case 0:
            fz2 = f;
            fy2 = 1.0F - f;
            break;


          case 1:
            fy = f;
            fz2 = f;
            break;


          case 2:
            fy2 = f;
            fz2 = 1.0F - f;
            break;


          case 3:
            fy2 = f;
            fz = f;
            break;


          case 4:
            fx2 = 1.0F - f;
            fz2 = f;
            break;


          case 5:
            fx = f;
            fz2 = f;
            break;

          default:
            fz2 = f;
            break;
        }

      } else if (hinge == 2) {

        switch (side) {


          case 0:
            fx = 1.0F - f;
            fy2 = 1.0F - f;
            break;


          case 1:
            fx = 1.0F - f;
            fy = f;
            break;


          case 2:
            fx = 1.0F - f;
            fz2 = 1.0F - f;
            break;


          case 3:
            fx = 1.0F - f;
            fz = f;
            break;


          case 4:
            fx2 = 1.0F - f;
            fy = 1.0F - f;
            break;


          case 5:
            fy = 1.0F - f;
            fx = f;
            break;

          default:
            fx = 1.0F - f;
            break;
        }


      } else if (hinge == 3) {

        switch (side) {


          case 0:
            fy2 = 1.0F - f;
            fz = 1.0F - f;
            break;


          case 1:
            fy = f;
            fz = 1.0F - f;
            break;


          case 2:
            fy = 1.0F - f;
            fz2 = 1.0F - f;
            break;


          case 3:
            fy = 1.0F - f;
            fz = f;
            break;


          case 4:
            fx2 = 1.0F - f;
            fz = 1.0F - f;
            break;


          case 5:
            fx = f;
            fz = 1.0F - f;
            break;

          default:
            fz = 1.0F - f;
            break;
        }
      }
      renderer.func_147782_a((fx + 1.0E-4F), (fy + 1.0E-4F), (fz + 1.0E-4F), (fx2 - 1.0E-4F), (fy2 - 1.0E-4F), (fz2 - 1.0E-4F));
      renderer.func_147784_q(block, i, j, k);
    }
    else {

      if (side == 0) {

        fy = 1.0F - f;
        switch (hinge) {

          case 0:
            fx = f;
            break;
          case 1:
            fz = f;
            break;
          case 2:
            fx2 = 1.0F - f;
            break;
          case 3:
            fz2 = 1.0F - f;
            break;
        }

      } else if (side == 1) {

        fy2 = f;
        switch (hinge) {

          case 0:
            fx = f;
            break;
          case 1:
            fz = f;
            break;
          case 2:
            fx2 = 1.0F - f;
            break;
          case 3:
            fz2 = 1.0F - f;
            break;
        }

      } else if (side == 2) {

        fz = 1.0F - f;
        switch (hinge) {

          case 0:
            fx = f;
            break;
          case 1:
            fy = f;
            break;
          case 2:
            fx2 = 1.0F - f;
            break;
          case 3:
            fy2 = 1.0F - f;
            break;
        }

      } else if (side == 3) {

        fz2 = f;
        switch (hinge) {

          case 0:
            fx = f;
            break;
          case 1:
            fy = f;
            break;
          case 2:
            fx2 = 1.0F - f;
            break;
          case 3:
            fy2 = 1.0F - f;
            break;
        }

      } else if (side == 4) {

        fx = 1.0F - f;
        switch (hinge) {

          case 0:
            fy = f;
            break;
          case 1:
            fz = f;
            break;
          case 2:
            fy2 = 1.0F - f;
            break;
          case 3:
            fz2 = 1.0F - f;
            break;
        }

      } else if (side == 5) {

        fx2 = f;
        switch (hinge) {

          case 0:
            fy = f;
            break;
          case 1:
            fz = f;
            break;
          case 2:
            fy2 = 1.0F - f;
            break;
          case 3:
            fz2 = 1.0F - f;
            break;
        }
      }
      renderer.func_147782_a(fx, fy, fz, fx2, fy2, fz2);
      renderer.func_147784_q(block, i, j, k);
    }

    int hingeID = (te.sheetStack != null) ? Math.min(((BlockMetalSheet)TFCBlocks.metalSheet).icons.length - 1, te.sheetStack.func_77960_j() >> 5) : 0;

    boolean breaking = (renderer.field_147840_d != null);

    if (!breaking) {
      renderer.func_147757_a(((BlockMetalSheet)TFCBlocks.metalSheet).icons[hingeID]);
    }
    drawHinges(block, i, j, k, renderer, side, hinge);

    if (!breaking) {
      renderer.func_147771_a();
    }
    renderer.field_147837_f = false;
    return true;
  }



  private static void drawHinges(Block block, int i, int j, int k, RenderBlocks renderer, int side, int hinge) {
    float f = 0.0625F;
    float f3 = f / 2.0F;

    float hingeMin = 0.0F;
    float hingeMin2 = f + f3;
    float hingeMax = 1.0F - f - f3;
    float hingeMax2 = 1.0F;

    if (hinge == 0) {

      switch (side) {


        case 0:
          renderer.func_147782_a(hingeMin, hingeMax, 0.1D, hingeMin2, hingeMax2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, hingeMax, 0.6D, hingeMin2, hingeMax2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 1:
          renderer.func_147782_a(hingeMin, hingeMin, 0.1D, hingeMin2, hingeMin2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, hingeMin, 0.6D, hingeMin2, hingeMin2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 2:
          renderer.func_147782_a(hingeMin, 0.1D, hingeMax, hingeMin2, 0.4D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, 0.6D, hingeMax, hingeMin2, 0.9D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 3:
          renderer.func_147782_a(hingeMin, 0.1D, hingeMin, hingeMin2, 0.4D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, 0.6D, hingeMin, hingeMin2, 0.9D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 4:
          renderer.func_147782_a(hingeMax, hingeMin, 0.1D, hingeMax2, hingeMin2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, hingeMin, 0.6D, hingeMax2, hingeMin2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 5:
          renderer.func_147782_a(hingeMin, hingeMin, 0.1D, hingeMin2, hingeMin2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, hingeMin, 0.6D, hingeMin2, hingeMin2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;
      }


    } else if (hinge == 1) {

      switch (side) {


        case 0:
          renderer.func_147782_a(0.1D, hingeMax, hingeMin, 0.4D, hingeMax2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMax, hingeMin, 0.9D, hingeMax2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 1:
          renderer.func_147782_a(0.1D, hingeMin, hingeMin, 0.4D, hingeMin2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMin, hingeMin, 0.9D, hingeMin2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 2:
          renderer.func_147782_a(0.1D, hingeMin, hingeMax, 0.4D, hingeMin2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMin, hingeMax, 0.9D, hingeMin2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 3:
          renderer.func_147782_a(0.1D, hingeMin, hingeMin, 0.4D, hingeMin2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMin, hingeMin, 0.9D, hingeMin2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 4:
          renderer.func_147782_a(hingeMax, 0.1D, hingeMin, hingeMax2, 0.4D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, 0.6D, hingeMin, hingeMax2, 0.9D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 5:
          renderer.func_147782_a(hingeMin, 0.1D, hingeMin, hingeMin2, 0.4D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, 0.6D, hingeMin, hingeMin2, 0.9D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;
      }


    } else if (hinge == 2) {

      switch (side) {


        case 0:
          renderer.func_147782_a(hingeMax, hingeMax, 0.1D, hingeMax2, hingeMax2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, hingeMax, 0.6D, hingeMax2, hingeMax2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 1:
          renderer.func_147782_a(hingeMax, hingeMin, 0.1D, hingeMax2, hingeMin2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, hingeMin, 0.6D, hingeMax2, hingeMin2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 2:
          renderer.func_147782_a(hingeMax, 0.1D, hingeMax, hingeMax2, 0.4D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, 0.6D, hingeMax, hingeMax2, 0.9D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 3:
          renderer.func_147782_a(hingeMax, 0.1D, hingeMin, hingeMax2, 0.4D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, 0.6D, hingeMin, hingeMax2, 0.9D, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 4:
          renderer.func_147782_a(hingeMax, hingeMax, 0.1D, hingeMax2, hingeMax2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, hingeMax, 0.6D, hingeMax2, hingeMax2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 5:
          renderer.func_147782_a(hingeMin, hingeMax, 0.1D, hingeMin2, hingeMax2, 0.4D);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, hingeMax, 0.6D, hingeMin2, hingeMax2, 0.9D);
          renderer.func_147784_q(block, i, j, k);
          break;
      }


    } else if (hinge == 3) {

      switch (side) {


        case 0:
          renderer.func_147782_a(0.1D, hingeMax, hingeMax, 0.4D, hingeMax2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMax, hingeMax, 0.9D, hingeMax2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 1:
          renderer.func_147782_a(0.1D, hingeMin, hingeMax, 0.4D, hingeMin2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMin, hingeMax, 0.9D, hingeMin2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 2:
          renderer.func_147782_a(0.1D, hingeMax, hingeMax, 0.4D, hingeMax2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMax, hingeMax, 0.9D, hingeMax2, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 3:
          renderer.func_147782_a(0.1D, hingeMax, hingeMin, 0.4D, hingeMax2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(0.6D, hingeMax, hingeMin, 0.9D, hingeMax2, hingeMin2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 4:
          renderer.func_147782_a(hingeMax, 0.1D, hingeMax, hingeMax2, 0.4D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMax, 0.6D, hingeMax, hingeMax2, 0.9D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;


        case 5:
          renderer.func_147782_a(hingeMin, 0.1D, hingeMax, hingeMin2, 0.4D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          renderer.func_147782_a(hingeMin, 0.6D, hingeMax, hingeMin2, 0.9D, hingeMax2);
          renderer.func_147784_q(block, i, j, k);
          break;
      }
    }
  }




  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    renderer.func_147782_a(0.125D, 0.4000000059604645D, 0.0D, 1.0D, 0.4749999940395355D, 1.0D);
    renderInvBlock(block, metadata & 0xFF, renderer);
    renderer.func_147782_a(0.0D, 0.4000000059604645D, 0.10000000149011612D, 0.125D, 0.5249999761581421D, 0.4000000059604645D);
    int index = Math.min(((BlockMetalSheet)TFCBlocks.metalSheet).icons.length - 1, metadata >> 5);
    renderInvBlock(block, ((BlockMetalSheet)TFCBlocks.metalSheet).icons[index], renderer);
    renderer.func_147782_a(0.0D, 0.4000000059604645D, 0.6000000238418579D, 0.125D, 0.5249999761581421D, 0.8999999761581421D);
    renderInvBlock(block, ((BlockMetalSheet)TFCBlocks.metalSheet).icons[index], renderer);
  }



  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return render(block, x, y, z, renderer);
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
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


  public static void renderInvBlock(Block block, IIcon ico, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, ico);
    var14.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderMetalTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */