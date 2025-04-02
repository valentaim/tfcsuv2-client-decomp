package com.bioxx.tfc.Render.Blocks;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFlowerPot;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;




public class RenderFlowerPot
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    renderer.func_147784_q(block, x, y, z);
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78380_c(block.func_149677_c(world, x, y, z));
    int colorMult = block.func_149720_d(world, x, y, z);
    IIcon iicon = renderer.func_147777_a(block, 0);
    float r = (colorMult >> 16 & 0xFF) / 255.0F;
    float g = (colorMult >> 8 & 0xFF) / 255.0F;
    float b = (colorMult & 0xFF) / 255.0F;


    if (EntityRenderer.field_78517_a) {

      float f1 = (r * 30.0F + g * 59.0F + b * 11.0F) / 100.0F;
      float g2 = (r * 30.0F + g * 70.0F) / 100.0F;
      float b2 = (r * 30.0F + b * 70.0F) / 100.0F;
      r = f1;
      g = g2;
      b = b2;
    }

    tessellator.func_78386_a(r, g, b);
    float r2 = 0.1865F;
    renderer.func_147764_f(block, (x - 0.5F + r2), y, z, iicon);
    renderer.func_147798_e(block, (x + 0.5F - r2), y, z, iicon);
    renderer.func_147734_d(block, x, y, (z - 0.5F + r2), iicon);
    renderer.func_147761_c(block, x, y, (z + 0.5F - r2), iicon);
    renderer.func_147806_b(block, x, (y - 0.5F + r2 + 0.1875F), z, renderer.func_147745_b(Blocks.field_150346_d));
    TileEntity tileentity = world.func_147438_o(x, y, z);

    if (tileentity instanceof TileEntityFlowerPot) {

      Item item = ((TileEntityFlowerPot)tileentity).func_145965_a();
      int meta = ((TileEntityFlowerPot)tileentity).func_145966_b();

      if (item instanceof net.minecraft.item.ItemBlock) {

        Block plantedBlock = Block.func_149634_a(item);
        int renderType = plantedBlock.func_149645_b();
        float transX = 0.0F;
        float transY = 4.0F;
        float transZ = 0.0F;
        tessellator.func_78372_c(transX / 16.0F, transY / 16.0F, transZ / 16.0F);
        colorMult = plantedBlock.func_149720_d(world, x, y, z);

        if (colorMult != 16777215) {

          r = (colorMult >> 16 & 0xFF) / 255.0F;
          g = (colorMult >> 8 & 0xFF) / 255.0F;
          b = (colorMult & 0xFF) / 255.0F;
          tessellator.func_78386_a(r, g, b);
        }

        if (renderType == 1) {

          renderer.func_147765_a(renderer.func_147787_a(plantedBlock, 0, meta), x, y, z, 0.75F);
        }
        else if (renderType == 13) {

          renderer.field_147837_f = true;
          float f9 = 0.125F;
          renderer.func_147782_a((0.5F - f9), 0.0D, (0.5F - f9), (0.5F + f9), 0.25D, (0.5F + f9));
          renderer.func_147784_q(plantedBlock, x, y, z);
          renderer.func_147782_a((0.5F - f9), 0.25D, (0.5F - f9), (0.5F + f9), 0.5D, (0.5F + f9));
          renderer.func_147784_q(plantedBlock, x, y, z);
          renderer.func_147782_a((0.5F - f9), 0.5D, (0.5F - f9), (0.5F + f9), 0.75D, (0.5F + f9));
          renderer.func_147784_q(plantedBlock, x, y, z);
          renderer.field_147837_f = false;
          renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
        }

        tessellator.func_78372_c(-transX / 16.0F, -transY / 16.0F, -transZ / 16.0F);
      }
    }

    return true;
  }




  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}



  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderFlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */