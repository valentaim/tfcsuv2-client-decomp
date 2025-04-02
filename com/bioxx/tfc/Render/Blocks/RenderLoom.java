package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;



public class RenderLoom
  implements ISimpleBlockRenderingHandler
{
  private static final float MIN_X = 0.0F;
  private static final float MAX_X = 1.0F;
  private static final float MIN_Y = 0.0F;
  private static final float MAX_Y = 1.0F;
  private static final float MIN_Z = 0.0F;
  private static final float MAX_Z = 1.0F;

  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    Block materialBlock;
    TELoom te = (TELoom)world.func_147438_o(x, y, z);

    if (te.loomType < 16) {

      materialBlock = TFCBlocks.woodSupportH;
    }
    else {

      materialBlock = TFCBlocks.woodSupportH2;
    }
    renderer.field_147837_f = true;
    GL11.glPushMatrix();




    setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.0F, 0.75F, 0.19999999F, 1.0F, 0.85F);
    renderer.func_147784_q(materialBlock, x, y, z);

    setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.0F, 0.75F, 0.9F, 1.0F, 0.85F);
    renderer.func_147784_q(materialBlock, x, y, z);



    setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.25F, 0.5F, 0.19999999F, 0.3F, 0.75F);
    renderer.func_147784_q(materialBlock, x, y, z);

    setRotatedRenderBounds(renderer, te.rotation, 0.1F, 0.05F, 0.5F, 0.19999999F, 0.100000024F, 0.75F);
    renderer.func_147784_q(materialBlock, x, y, z);


    setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.25F, 0.5F, 0.9F, 0.3F, 0.75F);
    renderer.func_147784_q(materialBlock, x, y, z);

    setRotatedRenderBounds(renderer, te.rotation, 0.8F, 0.05F, 0.5F, 0.9F, 0.100000024F, 0.75F);
    renderer.func_147784_q(materialBlock, x, y, z);


    setRotatedRenderBounds(renderer, te.rotation, 0.19999999F, 0.8F, 0.75F, 0.8F, 0.9F, 0.85F);
    renderer.func_147784_q(materialBlock, x, y, z);

    setRotatedRenderBounds(renderer, te.rotation, 0.19999999F, 0.0F, 0.75F, 0.8F, 0.1F, 0.85F);
    renderer.func_147784_q(materialBlock, x, y, z);

    rotate(renderer, 0);
    renderer.field_147837_f = false;
    GL11.glPopMatrix();
    return true;
  }


  public void rotate(RenderBlocks renderer, int i) {
    renderer.field_147875_q = i;
    renderer.field_147873_r = i;
    renderer.field_147869_t = i;
    renderer.field_147871_s = i;
  }

  private void setRotatedRenderBounds(RenderBlocks renderer, byte rotation, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
    switch (rotation) { case 0:
        renderer.func_147782_a(minX, minY, minZ, maxX, maxY, maxZ); break;
      case 1: renderer.func_147782_a((1.0F - maxZ), minY, minX, (1.0F - minZ), maxY, maxX); break;
      case 2: renderer.func_147782_a(minX, minY, (1.0F - maxZ), maxX, maxY, (1.0F - minZ)); break;
      case 3: renderer.func_147782_a(minZ, minY, minX, maxZ, maxY, maxX);
        break; }

  }




  public void renderInventoryBlock(Block block, int meta, int modelID, RenderBlocks renderer) {
    Block materialBlock;
    if (meta < 16) {

      materialBlock = TFCBlocks.woodSupportH;
    }
    else {

      materialBlock = TFCBlocks.woodSupportH2;
    }

    GL11.glPushMatrix();
    GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);

    renderer.func_147782_a(0.10000000149011612D, 0.0D, 0.75D, 0.19999998807907104D, 1.0D, 0.8500000238418579D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);

    renderer.func_147782_a(0.800000011920929D, 0.0D, 0.75D, 0.8999999761581421D, 1.0D, 0.8500000238418579D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);



    renderer.func_147782_a(0.10000000149011612D, 0.3499999940395355D, 0.6000000238418579D, 0.19999998807907104D, 0.3999999761581421D, 0.75D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);

    renderer.func_147782_a(0.10000000149011612D, 0.15000000596046448D, 0.6000000238418579D, 0.19999998807907104D, 0.19999998807907104D, 0.75D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);


    renderer.func_147782_a(0.800000011920929D, 0.3499999940395355D, 0.6000000238418579D, 0.8999999761581421D, 0.3999999761581421D, 0.75D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);

    renderer.func_147782_a(0.800000011920929D, 0.15000000596046448D, 0.6000000238418579D, 0.8999999761581421D, 0.19999998807907104D, 0.75D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);


    renderer.func_147782_a(0.19999998807907104D, 0.800000011920929D, 0.75D, 0.800000011920929D, 0.8999999761581421D, 0.8500000238418579D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);

    renderer.func_147782_a(0.19999998807907104D, 0.0D, 0.75D, 0.800000011920929D, 0.10000000149011612D, 0.8500000238418579D);
    rotate(renderer, 1);
    renderInvBlock(materialBlock, meta, renderer);

    rotate(renderer, 0);
    GL11.glPopMatrix();
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


  public static void renderInvBlockHoop(Block block, int m, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
    GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
    var14.func_78382_b();
    var14.func_78375_b(0.0F, -1.0F, 0.0F);
    renderer.func_147768_a(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(10, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 1.0F, 0.0F);
    renderer.func_147806_b(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(11, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, -1.0F);
    renderer.func_147798_e(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(12, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(0.0F, 0.0F, 1.0F);
    renderer.func_147764_f(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(13, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(-1.0F, 0.0F, 0.0F);
    renderer.func_147761_c(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(14, m));
    var14.func_78381_a();
    var14.func_78382_b();
    var14.func_78375_b(1.0F, 0.0F, 0.0F);
    renderer.func_147734_d(block, 0.0D, 0.0D, 0.0D, block.func_149691_a(15, m));
    var14.func_78381_a();
    GL11.glTranslatef(0.5F, 0.5F, 0.5F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */