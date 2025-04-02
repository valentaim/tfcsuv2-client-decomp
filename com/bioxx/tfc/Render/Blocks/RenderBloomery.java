package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockEarlyBloomery;
import com.bioxx.tfc.TileEntities.TEBloomery;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;



public class RenderBloomery
  implements ISimpleBlockRenderingHandler
{
  public static boolean render(Block block, int i, int j, int k, RenderBlocks renderblocks) {
    IBlockAccess blockAccess = renderblocks.field_147845_a;
    
    int meta = blockAccess.func_72805_g(i, j, k);
    int dir = meta & 0x3;
    TEBloomery te = (TEBloomery)blockAccess.func_147438_o(i, j, k);
    if (te != null && 
      te.isFlipped)
      dir = BlockEarlyBloomery.flipDir(dir); 
    float f = 0.125F;



    
    if (!BlockEarlyBloomery.isOpen(meta)) {
      
      switch (dir) {
        
        case 0:
          renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, f); break;
        case 1:
          renderblocks.func_147782_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D); break;
        case 2:
          renderblocks.func_147782_a(0.0D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D); break;
        case 3:
          renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, f, 1.0D, 1.0D); break;
      } 
      renderblocks.func_147784_q(block, i, j, k);

    
    }
    else if (dir == 0) {

      
      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, f);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.9375D, 0.0D, 1.0D, 1.0D, f);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.0625D, 0.0D, 0.0625D, 0.9375D, f);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.9375D, 0.0625D, 0.0D, 1.0D, 0.9375D, f);
      renderblocks.func_147784_q(block, i, j, k);
      
      renderblocks.func_147782_a(0.0D, 0.0625D, 0.0625D, f, 0.9375D, 0.5D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.0625D, 1.0D, 0.9375D, 0.5D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    else if (dir == 1) {

      
      renderblocks.func_147782_a((1.0F - f), 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a((1.0F - f), 0.9375D, 0.0D, 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.0D, 1.0D, 0.9375D, 0.0625D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.9375D, 1.0D, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      
      renderblocks.func_147782_a(0.5D, 0.0625D, (1.0F - f), 0.9375D, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.5D, 0.0625D, 0.0D, 0.9375D, 0.9375D, f);
      renderblocks.func_147784_q(block, i, j, k);
    }
    else if (dir == 2) {

      
      renderblocks.func_147782_a(0.0D, 0.0D, (1.0F - f), 1.0D, 0.0625D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.9375D, (1.0F - f), 1.0D, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.0625D, (1.0F - f), 0.0625D, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.9375D, 0.0625D, (1.0F - f), 1.0D, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      
      renderblocks.func_147782_a(0.0D, 0.0625D, 0.5D, f, 0.9375D, 0.9375D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a((1.0F - f), 0.0625D, 0.5D, 1.0D, 0.9375D, 0.9375D);
      renderblocks.func_147784_q(block, i, j, k);
    }
    else if (dir == 3) {

      
      renderblocks.func_147782_a(0.0D, 0.0D, 0.0D, f, 0.0625D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.9375D, 0.0D, f, 1.0D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.0625D, 0.0D, f, 0.9375D, 0.0625D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0D, 0.0625D, 0.9375D, f, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      
      renderblocks.func_147782_a(0.0625D, 0.0625D, (1.0F - f), 0.5D, 0.9375D, 1.0D);
      renderblocks.func_147784_q(block, i, j, k);
      renderblocks.func_147782_a(0.0625D, 0.0625D, 0.0D, 0.5D, 0.9375D, f);
      renderblocks.func_147784_q(block, i, j, k);
    } 
    
    return true;
  }


  
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.bloomeryRenderId) {
      
      renderer.func_147782_a(0.5D, 0.0D, 0.0D, 0.699999988079071D, 1.0D, 1.0D);
      renderInvBlock(block, metadata, renderer);
    } 
  }


  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.bloomeryRenderId)
      return render(block, x, y, z, renderer); 
    return false;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */