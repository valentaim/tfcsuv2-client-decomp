package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;


public class RenderSupportBeam
  implements ISimpleBlockRenderingHandler
{
  public static boolean renderWoodSupportBeamH(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;

    Boolean hasVerticalBeam = Boolean.valueOf(false);
    Boolean hasHorizontalBeamX = Boolean.valueOf(false);
    Boolean hasHorizontalBeamZ = Boolean.valueOf(false);


    if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j - 1, k))) {

      renderblocks.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
      renderblocks.func_147784_q(block, i, j, k);
      hasVerticalBeam = Boolean.valueOf(true);
    }


    if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i - 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i - 1, j, k))) {

      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k)))
      {
        if (hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
          renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else if (!hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        hasHorizontalBeamX = Boolean.valueOf(true);
      }
      else
      {
        if (hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else {

          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        hasHorizontalBeamX = Boolean.valueOf(true);
      }

    } else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k))) {

      if (hasVerticalBeam.booleanValue()) {

        renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      hasHorizontalBeamX = Boolean.valueOf(true);
    }

    if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k - 1))) {

      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1)))
      {
        if (hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
          renderblocks.func_147784_q(block, i, j, k);
          renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else if (!hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        hasHorizontalBeamZ = Boolean.valueOf(true);
      }
      else
      {
        if (hasVerticalBeam.booleanValue()) {

          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else {

          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        hasHorizontalBeamZ = Boolean.valueOf(true);
      }

    } else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1))) {

      if (hasVerticalBeam.booleanValue()) {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      hasHorizontalBeamZ = Boolean.valueOf(true);
    }

    float minX = -1.0F;
    float minY = -1.0F;
    float minZ = -1.0F;

    float maxX = -1.0F;
    float maxY = -1.0F;
    float maxZ = -1.0F;

    if (hasHorizontalBeamX.booleanValue()) {

      minX = 0.0F;
      maxX = 1.0F;
      minZ = 0.25F;
      maxZ = 0.75F;
    }
    if (hasHorizontalBeamZ.booleanValue()) {

      if (maxX == -1.0F) {

        minX = 0.25F;
        maxX = 0.75F;
      }

      minZ = 0.0F;
      maxZ = 1.0F;
    }

    if (hasVerticalBeam.booleanValue()) {

      minY = 0.0F;
      maxY = 1.0F;
      if (maxX == -1.0F) {

        minX = 0.25F;
        maxX = 0.75F;
      }
      if (maxZ == -1.0F)
      {
        minZ = 0.25F;
        maxZ = 0.75F;
      }

    } else {

      minY = 0.5F;
      maxY = 1.0F;
    }

    renderblocks.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);

    return true;
  }


  public static boolean renderWoodSupportBeamV(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;

    Boolean hasVerticalBeam = Boolean.valueOf(false);


















    if ((blockAccess.func_147439_a(i, j - 1, k).func_149662_c() || TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j - 1, k))) && TFCBlocks.isBlockVSupport(block)) {

      renderblocks.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
      renderblocks.func_147784_q(block, i, j, k);
      hasVerticalBeam = Boolean.valueOf(true);
    }


    if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i - 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i - 1, j, k))) {

      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k)))
      {
        if (hasVerticalBeam.booleanValue())
        {
          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
          renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else if (!hasVerticalBeam.booleanValue())
        {
          renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
          renderblocks.func_147784_q(block, i, j, k);

        }


      }
      else if (hasVerticalBeam.booleanValue())
      {
        renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.25D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else
      {
        renderblocks.func_147782_a(0.0D, 0.5D, 0.25D, 0.75D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);

      }

    }
    else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i + 1, j, k)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i + 1, j, k))) {

      if (hasVerticalBeam.booleanValue()) {

        renderblocks.func_147782_a(0.75D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 1.0D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);
      }
    }


    if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k - 1))) {

      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1)))
      {
        if (hasVerticalBeam.booleanValue())
        {
          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
          renderblocks.func_147784_q(block, i, j, k);
          renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);
        }
        else if (!hasVerticalBeam.booleanValue())
        {
          renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 1.0D);
          renderblocks.func_147784_q(block, i, j, k);

        }


      }
      else if (hasVerticalBeam.booleanValue())
      {
        renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.25D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else
      {
        renderblocks.func_147782_a(0.25D, 0.5D, 0.0D, 0.75D, 1.0D, 0.75D);
        renderblocks.func_147784_q(block, i, j, k);

      }

    }
    else if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(i, j, k + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(i, j, k + 1))) {

      if (hasVerticalBeam.booleanValue()) {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.75D, 0.75D, 1.0D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);
      }
      else {

        renderblocks.func_147782_a(0.25D, 0.5D, 0.25D, 0.75D, 1.0D, 1.0D);
        renderblocks.func_147784_q(block, i, j, k);
      }
    }


    float minX = 0.25F;
    float minY = 0.0F;
    float minZ = 0.25F;

    float maxX = 0.75F;
    float maxY = 1.0F;
    float maxZ = 0.75F;


    renderblocks.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);

    return true;
  }


  public static boolean isSurroundedByWater(IBlockAccess access, int i, int j, int k) {
    if (access.func_147439_a(i, j + 1, k).func_149688_o() == Material.field_151586_h) {
      return true;
    }
    return (access.func_147439_a(i + 1, j, k).func_149688_o() == Material.field_151586_h || access.func_147439_a(i - 1, j, k).func_149688_o() == Material.field_151586_h || access
      .func_147439_a(i, j, k + 1).func_149688_o() == Material.field_151586_h || access.func_147439_a(i, j, k - 1).func_149688_o() == Material.field_151586_h);
  }




  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.func_147757_a(block.func_149691_a(0, metadata));
    if (modelID == TFCBlocks.woodSupportRenderIdH) {

      renderer.func_147782_a(0.25D, 0.25D, 0.0D, 0.75D, 0.75D, 1.0D);
      renderInvBlock(block, renderer);
    }
    else if (modelID == TFCBlocks.woodSupportRenderIdV) {

      renderer.func_147782_a(0.25D, 0.0D, 0.25D, 0.75D, 1.0D, 0.75D);
      renderInvBlock(block, renderer);
    }
    renderer.func_147771_a();
  }




  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.woodSupportRenderIdH)
    {
      return renderWoodSupportBeamH(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.woodSupportRenderIdV)
    {
      return renderWoodSupportBeamV(block, i, j, k, renderer);
    }
    return false;
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, 0));
    var14.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSupportBeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */