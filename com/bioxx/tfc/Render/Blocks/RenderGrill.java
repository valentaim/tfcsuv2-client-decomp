package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockGrill;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;









































public class RenderGrill
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    BlockGrill grill = (BlockGrill)block;
    int meta = world.func_72805_g(i, j, k);
    
    if (!grill.isGrillOpen(meta)) {
      renderer.func_147782_a(0.0D, -0.05000000074505806D, 0.0D, 1.0D, 0.0D, 1.0D);
    }
    renderer.func_147784_q(block, i, j, k);
    
    return true;
  }

  
  public void rotate(RenderBlocks renderer, int i) {
    renderer.field_147875_q = i;
    renderer.field_147873_r = i;
    renderer.field_147869_t = i;
    renderer.field_147871_s = i;
  }































  
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.func_147782_a(0.0D, 0.5D, 0.0D, 1.0D, 0.550000011920929D, 1.0D);
    renderInvBlock(block, metadata, renderer);
  }



  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }


  
  public int getRenderId() {
    return 0;
  }

  
  public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(1, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(2, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(3, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(4, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(5, m));
    var14.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */