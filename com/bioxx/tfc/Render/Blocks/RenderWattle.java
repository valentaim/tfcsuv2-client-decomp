package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.BlockWattle;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderWattle
  implements ISimpleBlockRenderingHandler
{
  public static boolean renderBlockWall(BlockWattle wattleBlock, int x, int y, int z, RenderBlocks renderblocks) {
    double wallWidth = 0.1D;
    boolean flag0 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x - 1, y, z);
    boolean flag1 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x + 1, y, z);
    boolean flag2 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y, z - 1);
    boolean flag3 = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y, z + 1);
    boolean flag4 = (flag2 && flag3 && !flag0 && !flag1);
    boolean flag5 = (!flag2 && !flag3 && flag0 && flag1);
    boolean flag6 = renderblocks.field_147845_a.func_147437_c(x, y + 1, z);

    
    boolean flagUp = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z);
    
    boolean flag0Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x - 1, y + 1, z);
    boolean flag1Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x + 1, y + 1, z);
    boolean flag2Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z - 1);
    boolean flag3Up = wattleBlock.canConnectWallTo(renderblocks.field_147845_a, x, y + 1, z + 1);
    boolean flag4Up = (flag2Up && flag3Up);
    boolean flag5Up = (flag0Up && flag1Up);
    
    if (((flag4 && flag4Up) || (flag5 && flag5Up)) && flagUp) {
      
      if (flag4)
      {
        renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.0D, 1.0D);
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      }
      else
      {
        renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.0D, 0.5D + wallWidth * 0.5D);
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      }
    
    } else if ((flag4 || flag5) && flag6) {
      
      if (flag4)
      {
        renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.1D, 1.0D);
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      }
      else
      {
        renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.1D, 0.5D + wallWidth * 0.5D);
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      }
    
    } else {
      
      renderblocks.func_147782_a(0.5D - wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.55D, 0.5D + wallWidth * 0.55D, flagUp ? 1.0D : 1.25D, 0.5D + wallWidth * 0.55D);
      renderblocks.func_147784_q((Block)wattleBlock, x, y, z);


      
      if (flag0) {
        
        if (flagUp && flag0Up) {
          renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 0.5D - wallWidth * 0.55D, 1.0D, 0.5D + wallWidth * 0.5D);
        } else {
          
          renderblocks.func_147782_a(0.0D, 0.0D, 0.5D - wallWidth * 0.5D, 0.5D - wallWidth * 0.55D, 1.1D, 0.5D + wallWidth * 0.5D);
        } 
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      } 
      
      if (flag1) {
        
        if (flagUp && flag1Up) {
          renderblocks.func_147782_a(0.5D + wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.0D, 0.5D + wallWidth * 0.5D);
        }
        else {
          
          renderblocks.func_147782_a(0.5D + wallWidth * 0.55D, 0.0D, 0.5D - wallWidth * 0.5D, 1.0D, 1.1D, 0.5D + wallWidth * 0.5D);
        } 
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      } 
      
      if (flag2) {
        
        if (flagUp && flag2Up) {
          renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.0D, 0.5D - wallWidth * 0.55D);
        } else {
          
          renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.0D, 0.5D + wallWidth * 0.5D, 1.1D, 0.5D - wallWidth * 0.55D);
        } 
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      } 
      
      if (flag3) {
        
        if (flagUp && flag3Up) {
          renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.5D + wallWidth * 0.55D, 0.5D + wallWidth * 0.5D, 1.0D, 1.0D);
        } else {
          
          renderblocks.func_147782_a(0.5D - wallWidth * 0.5D, 0.0D, 0.5D + wallWidth * 0.55D, 0.5D + wallWidth * 0.5D, 1.1D, 1.0D);
        } 
        renderblocks.func_147784_q((Block)wattleBlock, x, y, z);
      } 
    } 
    wattleBlock.func_149719_a(renderblocks.field_147845_a, x, y, z);
    return true;
  }


  
  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    float f = 0.4F;
    float f1 = 0.6F;
    
    renderer.func_147782_a(f, 0.0D, f, f1, 1.25D, f1);
    renderInvBlock(block, metadata, renderer);
    
    f = 0.45F;
    f1 = 0.55F;
    float f2 = 0.0F;
    float f3 = 1.1F;

    
    float f6 = 0.0F;
    float f7 = 1.5F;
    
    renderer.func_147782_a(f - 0.001D, f2, f6, f1 - 0.001D, f3, 5.0D * f7 / 8.0D);
    renderInvBlock(block, metadata, renderer);
  }





  
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }


  
  public int getRenderId() {
    return 0;
  }


  
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    if (modelId == TFCBlocks.wattleRenderId && block instanceof BlockWattle) {
      return renderBlockWall((BlockWattle)block, x, y, z, renderer);
    }
    return false;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderWattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */