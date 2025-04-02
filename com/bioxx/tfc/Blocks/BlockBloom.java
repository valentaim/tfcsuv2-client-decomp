package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.TileEntities.TEBloom;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;



public class BlockBloom
  extends BlockTerraContainer
{
  public BlockBloom() {
    super(Material.field_151573_f);
  }



  public boolean func_149662_c() {
    return true;
  }



  public int func_149692_a(int j) {
    return j;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:devices/Iron Bloom");
  }



  public TileEntity func_149915_a(World w, int meta) {
    return (TileEntity)new TEBloom();
  }



  public Item func_149650_a(int i, Random rand, int j) {
    return null;
  }



  public void func_149725_f(World world, int i, int j, int k, int meta) {
    TEBloom te = (TEBloom)world.func_147438_o(i, j, k);
    EntityItem ei = new EntityItem(world, i, j, k, new ItemStack(TFCItems.rawBloom, 1, te.size));

    int[] pos = getBloomery(world, i, j, k);
    ei.field_70159_w = 0.0D; ei.field_70181_x = 0.0D; ei.field_70179_y = 0.0D;
    ei.func_70107_b(i + 0.5D + pos[0], j + 0.5D, k + 0.5D + pos[1]);
    ei.field_145804_b = 0;
    world.func_72838_d((Entity)ei);
  }


  public int[] getBloomery(World world, int x, int y, int z) {
    if (world.func_147439_a(x + 1, y, z) == TFCBlocks.bloomery)
      return new int[] { 2, 0 };
    if (world.func_147439_a(x - 1, y, z) == TFCBlocks.bloomery)
      return new int[] { -2, 0 };
    if (world.func_147439_a(x, y, z + 1) == TFCBlocks.bloomery)
      return new int[] { 0, 2 };
    if (world.func_147439_a(x, y, z - 1) == TFCBlocks.bloomery) {
      return new int[] { 0, -2 };
    }
    return new int[] { 0, 0 };
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockBloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */