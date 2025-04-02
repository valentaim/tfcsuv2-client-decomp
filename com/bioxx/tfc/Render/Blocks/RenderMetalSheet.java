package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEMetalSheet;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;



public class RenderMetalSheet
  implements ISimpleBlockRenderingHandler
{
  public static boolean render(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess access = renderblocks.field_147845_a;
    TEMetalSheet te = (TEMetalSheet)access.func_147438_o(i, j, k);
    
    double yMax = 1.0D;
    double yMin = 0.0D;
    double f0 = 0.0625D;
    double f1 = 0.9375D;
    
    if (te.bottomExists()) {
      
      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, f0, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      yMin = 0.0625D;
    } 
    if (te.topExists()) {
      
      renderblocks.func_147782_a(0.0D, f1, 0.0D, 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      yMax = 0.9375D;
    } 
    if (te.northExists()) {
      
      renderblocks.func_147782_a(0.0D, yMin, 0.0D, 1.0D, yMax, f0);
      renderblocks.func_147784_q(block, i, j, k);
    } 
    if (te.southExists()) {
      
      renderblocks.func_147782_a(0.0D, yMin, f1, 1.0D, yMax, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
    } 
    if (te.eastExists()) {
      
      renderblocks.func_147782_a(0.0D, yMin, 0.0D, f0, yMax, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
    } 
    if (te.westExists()) {
      
      renderblocks.func_147782_a(f1, yMin, 0.0D, 1.0D, yMax, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
    } 
    
    return true;
  }


  
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.20000000298023224D, 1.0D);
    renderInvBlock(block, metadata, renderer);
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

  
  public static void renderInvBlock(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    int meta = m;
    if (meta >= 8)
      meta -= 8; 
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderMetalSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */