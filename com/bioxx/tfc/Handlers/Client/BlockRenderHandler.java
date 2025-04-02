package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Render.Blocks.RenderAnvil;
import com.bioxx.tfc.Render.Blocks.RenderCrop;
import com.bioxx.tfc.Render.Blocks.RenderDetailed;
import com.bioxx.tfc.Render.Blocks.RenderGrass;
import com.bioxx.tfc.Render.TFC_CoreRender;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class BlockRenderHandler
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.sulfurRenderId)
    {
      return TFC_CoreRender.renderSulfur(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.moltenRenderId)
    {
      return TFC_CoreRender.renderMolten(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.grassRenderId)
    {
      return RenderGrass.render(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.clayGrassRenderId)
    {
      return RenderGrass.renderClay(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.peatGrassRenderId)
    {
      return RenderGrass.renderPeat(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.looseRockRenderId)
    {
      return TFC_CoreRender.renderLooseRock(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.snowRenderId)
    {
      return TFC_CoreRender.renderSnow(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.firepitRenderId)
    {
      return TFC_CoreRender.renderFirepit(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.forgeRenderId)
    {
      return TFC_CoreRender.renderForge(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.anvilRenderId)
    {
      return RenderAnvil.renderAnvil(block, i, j, k, renderer);
    }








    if (modelId == TFCBlocks.sluiceRenderId)
    {
      return TFC_CoreRender.renderSluice(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.woodFruitRenderId)
    {
      return TFC_CoreRender.renderWoodTrunk(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.leavesFruitRenderId)
    {
      return TFC_CoreRender.renderFruitLeaves(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.stairRenderId)
    {
      return TFC_CoreRender.renderBlockStairs(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.slabRenderId)
    {
      return TFC_CoreRender.renderBlockSlab(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.cropRenderId)
    {
      return RenderCrop.render(block, i, j, k, renderer);
    }








    if (modelId == TFCBlocks.detailedRenderId)
    {
      return RenderDetailed.renderBlockDetailed(block, i, j, k, renderer);
    }
    if (modelId == TFCBlocks.waterPlantRenderId)
    {
      return TFC_CoreRender.renderSeaPlant(block, i, j, k, renderer);
    }
    return false;
  }



  public boolean shouldRender3DInInventory(int i) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    IIcon[] icons = new IIcon[6];

    if (modelID == TFCBlocks.peatGrassRenderId) {

      for (int i = 0; i < 6; i++)
        icons[i] = TFCBlocks.peat.func_149733_h(i);
      renderInvBlock(block, renderer, icons);
    }
    else if (modelID == TFCBlocks.grassRenderId) {

      for (int i = 0; i < 6; i++) {

        if (block == TFCBlocks.dirt) {
          icons[i] = TFCBlocks.dirt.func_149733_h(i);
        } else {
          icons[i] = TFCBlocks.dirt2.func_149733_h(i);
        }
      }  renderInvBlock(block, renderer, icons);
    }
    else if (modelID == TFCBlocks.clayGrassRenderId) {

      for (int i = 0; i < 6; i++) {

        if (block == TFCBlocks.clay) {
          icons[i] = TFCBlocks.clay.func_149733_h(i);
        } else {
          icons[i] = TFCBlocks.clay2.func_149733_h(i);
        }
      }  renderInvBlock(block, renderer, icons);
    }
  }


  private void renderInvBlock(Block block, RenderBlocks renderer, IIcon[] icons) {
    Tessellator tessellator = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, icons[0]);
    tessellator.func_78381_a();
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, icons[1]);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(1));
    tessellator.func_78381_a();
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, icons[2]);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(2));
    tessellator.func_78381_a();
    tessellator.func_78382_b();
    tessellator.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, icons[3]);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(3));
    tessellator.func_78381_a();
    tessellator.func_78382_b();
    tessellator.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, icons[4]);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(4));
    tessellator.func_78381_a();
    tessellator.func_78382_b();
    tessellator.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, icons[5]);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149733_h(5));
    tessellator.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\BlockRenderHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */