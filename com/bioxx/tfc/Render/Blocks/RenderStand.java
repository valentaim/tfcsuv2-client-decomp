package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;









public class RenderStand
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int i, int j, int k, Block block, int modelId, RenderBlocks renderer) {
    return true;
  }

  
  public void rotate(RenderBlocks renderer, int i) {
    renderer.field_147875_q = i;
    renderer.field_147873_r = i;
    renderer.field_147869_t = i;
    renderer.field_147871_s = i;
  }


  
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    Block blockToRender = (block == TFCBlocks.armorStand) ? TFCBlocks.planks : TFCBlocks.planks2;
    Block woodblock = (block == TFCBlocks.armorStand) ? TFCBlocks.woodSupportH : TFCBlocks.woodSupportH2;
    
    float yScale = 0.7F;
    float blockScale = 0.5F;

    
    renderer.func_147782_a((0.44F * blockScale), (1.45F * yScale * blockScale), (0.2F * blockScale), (0.56F * blockScale), (1.55F * yScale * blockScale), (0.8F * blockScale));
    renderInvBlock(woodblock, metadata, renderer);
    
    renderer.func_147782_a((0.45F * blockScale), (0.201F * yScale * blockScale), (0.35F * blockScale), (0.55F * blockScale), (1.45F * yScale * blockScale), (0.45F * blockScale));
    renderInvBlock(woodblock, metadata, renderer);
    
    renderer.func_147782_a((0.45F * blockScale), (0.201F * yScale * blockScale), (0.55F * blockScale), (0.55F * blockScale), (1.45F * yScale * blockScale), (0.65F * blockScale));
    renderInvBlock(woodblock, metadata, renderer);

    
    renderer.func_147782_a(0.20000000298023224D, 0.0D, 0.20000000298023224D, 0.800000011920929D, 0.2D * yScale, 0.800000011920929D);
    renderInvBlock(blockToRender, metadata, renderer, false);

    
    renderer.func_147782_a((0.45F * blockScale), (1.45F * yScale * blockScale), (0.45F * blockScale), (0.55F * blockScale), (1.9F * yScale * blockScale), (0.55F * blockScale));
    renderInvBlock(woodblock, metadata, renderer);
  }


  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }


  
  public int getRenderId() {
    return 0;
  }

  
  public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
    renderInvBlock(block, m, renderer, true);
  }

  
  public static void renderInvBlock(Block block, int m, RenderBlocks renderer, boolean b) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    if (b) GL11.glScalef(2.0F, 2.0F, 2.0F); 
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
    if (b) GL11.glScalef(0.5F, 0.5F, 0.5F); 
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */