package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TESmokeRack;
import com.bioxx.tfc.api.Food;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderSmokeRack
  implements ISimpleBlockRenderingHandler
{
  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    TESmokeRack te = (TESmokeRack)world.func_147438_o(x, y, z);
    renderer.field_147837_f = true;
    renderer.func_147784_q(block, x, y, z);
    if ((world.func_72805_g(x, y, z) & 0x1) == 0) {
      
      if (te.func_70301_a(0) != null) {
        
        double mid = 0.25D;
        float r = 1.0F, g = 1.0F, b = 1.0F;
        if (Food.isSmoked(te.func_70301_a(0))) {
          r = 0.1F; g = 0.1F; b = 0.1F;
        }  renderer.func_147782_a(0.43D, 0.43D, mid - 0.07D, 0.57D, 0.57D, mid + 0.07D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
        renderer.func_147782_a(0.491D, 0.2D, mid - 0.009D, 0.509D, 0.5D, mid + 0.009D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
      } 
      if (te.func_70301_a(1) != null)
      {
        double mid = 0.75D;
        float r = 1.0F, g = 1.0F, b = 1.0F;
        if (Food.isSmoked(te.func_70301_a(1))) {
          r = 0.1F; g = 0.1F; b = 0.1F;
        }  renderer.func_147782_a(0.43D, 0.43D, mid - 0.07D, 0.57D, 0.57D, mid + 0.07D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
        renderer.func_147782_a(0.491D, 0.2D, mid - 0.009D, 0.509D, 0.5D, mid + 0.009D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
      }
    
    } else {
      
      if (te.func_70301_a(0) != null) {
        
        double mid = 0.25D;
        float r = 1.0F, g = 1.0F, b = 1.0F;
        if (Food.isSmoked(te.func_70301_a(0))) {
          r = 0.1F; g = 0.1F; b = 0.1F;
        }  renderer.func_147782_a(mid - 0.07D, 0.43D, 0.43D, mid + 0.07D, 0.57D, 0.57D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
        renderer.func_147782_a(mid - 0.009D, 0.2D, 0.491D, mid + 0.009D, 0.5D, 0.509D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
      } 
      if (te.func_70301_a(1) != null) {
        
        double mid = 0.75D;
        float r = 1.0F, g = 1.0F, b = 1.0F;
        if (Food.isSmoked(te.func_70301_a(1))) {
          r = 0.1F; g = 0.1F; b = 0.1F;
        }  renderer.func_147782_a(mid - 0.07D, 0.43D, 0.43D, mid + 0.07D, 0.57D, 0.57D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
        renderer.func_147782_a(mid - 0.009D, 0.2D, 0.491D, mid + 0.009D, 0.5D, 0.509D);
        renderer.func_147736_d(block, x, y, z, r, g, b);
      } 
    } 
    renderer.field_147837_f = false;
    
    return true;
  }

  
  public void rotate(RenderBlocks renderer, int i) {
    renderer.field_147875_q = i;
    renderer.field_147873_r = i;
    renderer.field_147869_t = i;
    renderer.field_147871_s = i;
  }



  
  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {}



  
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderSmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */