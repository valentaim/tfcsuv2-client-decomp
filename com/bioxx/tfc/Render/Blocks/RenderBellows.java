package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.Blocks.Devices.BlockBellows;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;




public class RenderBellows
  implements ISimpleBlockRenderingHandler
{
  public boolean shouldRender3DInInventory(int modelId) {
    return true;
  }




  public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
    renderer.field_147840_d = BlockBellows.bellowsFront;
    renderer.func_147782_a(0.0D, 0.0D, 0.8999999761581421D, 1.0D, 1.0D, 1.0D);
    renderInvBlock(block, renderer);

    renderer.func_147771_a();
    renderer.func_147782_a(0.10000000149011612D, 0.10000000149011612D, 0.05000000074505806D, 0.8999999761581421D, 0.8999999761581421D, 0.949999988079071D);
    renderInvBlock(block, renderer);

    renderer.field_147840_d = BlockBellows.bellowsBack;
    renderer.func_147782_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.10000000149011612D);
    renderInvBlock(block, renderer);

    renderer.func_147771_a();
  }




  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 2));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 1));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 3));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(0, 0));
    var14.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */