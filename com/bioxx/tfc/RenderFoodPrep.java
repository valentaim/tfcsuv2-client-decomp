package com.bioxx.tfc;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;







public class RenderFoodPrep
  implements ISimpleBlockRenderingHandler
{
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

  public boolean renderWorldBlock(IBlockAccess world, int a, int b, int c, Block block, int modelId, RenderBlocks renderer) {
    Block ublock = world.func_147439_a(a, b - 1, c);
    IIcon icon = null;
    for (int i = 0; i < 4 && icon == null; i++)
      icon = ublock.func_149673_e(world, a, b - 1, c, i);
    if (icon == null) icon = Blocks.field_150348_b.func_149733_h(0);
    renderer.func_147757_a(icon);
    renderer.func_147784_q(block, a, b, c);
    renderer.func_147771_a();

    return true;
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\RenderFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */