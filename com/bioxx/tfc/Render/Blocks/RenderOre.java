package com.bioxx.tfc.Render.Blocks;

import com.bioxx.tfc.TileEntities.TEOre;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;






public class RenderOre
  implements ISimpleBlockRenderingHandler
{
  public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderer) {}

  public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
    boolean breaking = (renderer.field_147840_d != null);

    if (breaking) {
      renderer.func_147784_q(block, x, y, z);
    }
    else {

      renderer.field_147840_d = getRockTexture((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
      renderer.func_147784_q(block, x, y, z);
      renderer.func_147771_a();


      renderer.func_147784_q(block, x, y, z);
    }

    return true;
  }


  public static IIcon getRockTexture(World worldObj, int xCoord, int yCoord, int zCoord) {
    TEOre te = (TEOre)worldObj.func_147438_o(xCoord, yCoord, zCoord);
    if (te != null && te.baseBlockID > 0)
    {
      return Block.func_149729_e(te.baseBlockID).func_149691_a(5, te.baseBlockMeta);
    }
    return null;
  }



  public boolean shouldRender3DInInventory(int modelId) {
    return false;
  }



  public int getRenderId() {
    return 0;
  }


  public static void renderInvBlock(Block block, int meta, RenderBlocks renderer) {
    Tessellator var14 = Tessellator.field_78398_a;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Blocks\RenderOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */