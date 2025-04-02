package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEWoodConstruct;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;






public class RenderWoodConstruct
  implements ISimpleBlockRenderingHandler
{
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {}

  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    renderOld(i, j, k, block, renderer);
    return true;
  }


  private void renderOld(int i, int j, int k, Block block, RenderBlocks renderer) {
    TEWoodConstruct te = (TEWoodConstruct)renderer.field_147845_a.func_147438_o(i, j, k);


    int d = TEWoodConstruct.plankDetailLevel;
    int dd = TEWoodConstruct.plankDetailLevel * TEWoodConstruct.plankDetailLevel;
    int dd2 = dd * 2;

    float div = 1.0F / d;

    boolean breaking = false;
    if (renderer.field_147840_d != null) {
      breaking = true;
    }
    float minX = 0.0F;
    float maxX = 1.0F;
    float minY = 0.0F;
    float maxY = 1.0F;
    float minZ = 0.0F;
    float maxZ = 1.0F;
    boolean render = false; int index;
    for (index = 0; index < dd; ) {

      int in3 = index >> 3;
      if (te.solidCheck[in3]) {

        minX = 0.0F;
        maxX = 1.0F;
        minY = 0.0F;
        maxY = 1.0F;
        minZ = div * in3;
        maxZ = minZ + div;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]);
        index++;
        render = true;
      }
      else if (te.solidCheck[in3 + 24]) {

        minX = 0.0F;
        maxX = 1.0F;
        minY = div * ((index & 0x7) + in3);
        maxY = minY + div;
        minZ = 0.0F;
        maxZ = 1.0F;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]);
        index += 8;
        render = true;
      }
      else if (te.data.get(index)) {

        minX = 0.0F;
        maxX = 1.0F;
        minY = div * (index & 0x7);
        maxY = minY + div;
        minZ = div * in3;
        maxZ = minZ + div;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index]);
        index++;
        render = true;
      }
      else {

        index++;
        render = false;
      }

      if (render) {

        renderer.field_147867_u = 3;
        renderer.field_147865_v = 3;
        renderer.func_147782_a((minX + 3.0E-5F), (minY + 3.0E-5F), (minZ + 3.0E-5F), (maxX - 3.0E-5F), (maxY - 3.0E-5F), (maxZ - 3.0E-5F));
        renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
      }
    }

    renderer.field_147867_u = 0;
    renderer.field_147865_v = 0;
    for (index = 0; index < dd; ) {

      if (te.solidCheck[dd + index >> 3]) {

        minX = 0.0F;
        maxX = 1.0F;
        minY = 0.0F;
        maxY = 1.0F;
        minZ = div * (index >> 3);
        maxZ = minZ + div;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd]);
        index += 8;
        render = true;
      }
      else if (te.data.get(index + dd)) {

        minX = div * (index & 0x7);
        maxX = minX + div;
        minY = 0.0F;
        maxY = 1.0F;
        minZ = div * (index >> 3);
        maxZ = minZ + div;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd]);
        index++;
        render = true;
      }
      else {

        index++;
        render = false;
      }

      if (render) {

        renderer.field_147869_t = 1;
        renderer.field_147871_s = 1;
        renderer.field_147875_q = 1;
        renderer.field_147873_r = 1;
        renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ);
        renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
      }
    }



    renderer.field_147869_t = 0;
    renderer.field_147871_s = 0;
    renderer.field_147875_q = 0;
    renderer.field_147873_r = 0;

    for (index = 0; index < dd; ) {

      if (te.solidCheck[dd2 + index >> 3]) {

        minX = 0.0F;
        maxX = 1.0F;
        minY = div * (index >> 3);
        maxY = minY + div;
        minZ = 0.0F;
        maxZ = 1.0F;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd2]);
        index += 8;
        render = true;
      }
      else if (te.data.get(index + dd2)) {

        minX = div * (index & 0x7);
        maxX = minX + div;
        minY = div * (index >> 3);
        maxY = minY + div;
        minZ = 0.0F;
        maxZ = 1.0F;
        if (!breaking)
          renderer.field_147840_d = TFCBlocks.woodConstruct.func_149691_a(0, te.woodTypes[index + dd2]);
        index++;
        render = true;
      }
      else {

        index++;
        render = false;
      }

      if (render) {

        renderer.field_147867_u = 1;
        renderer.field_147865_v = 1;
        renderer.func_147782_a((minX + 1.0E-5F), (minY + 1.0E-5F), (minZ + 1.0E-5F), (maxX - 1.0E-5F), (maxY - 1.0E-5F), (maxZ - 1.0E-5F));
        renderer.func_147736_d(block, i, j, k, 1.0F, 1.0F, 1.0F);
      }
    }
    renderer.field_147867_u = 0;
    renderer.field_147865_v = 0;
    renderer.func_147771_a();
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderWoodConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */