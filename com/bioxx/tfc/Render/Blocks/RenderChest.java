package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Render.TESRChestHelper;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;






public class RenderChest
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    return true;
  }



  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    TESRChestHelper.instance.renderChest(block, metadata, modelID);
    GL11.glEnable(32826);
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }



  public int getRenderId() {
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */