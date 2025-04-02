package com.bioxx.tfc.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;




public class BlockSmoke
  extends BlockTerra
{
  public BlockSmoke() {
    super((new Material(MapColor.field_151666_j)).func_76231_i());
    func_149647_a(null);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    func_149675_a(true);
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:Smoke");
  }



  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess access, int x, int y, int z) {
    return 6710886;
  }



  public boolean func_149646_a(IBlockAccess access, int x, int y, int z, int side) {
    return (access.func_147439_a(x, y, z) != this);
  }



  @SideOnly(Side.CLIENT)
  public int func_149701_w() {
    return 0;
  }



  public boolean func_149662_c() {
    return false;
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    verify(world, x, y, z);
  }















  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public boolean func_149703_v() {
    return false;
  }



  public void func_149726_b(World world, int x, int y, int z) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      boolean isOdd = ((meta & 0x1) > 0);
      if (meta < 15)
      {
        if ((isSurrounded(world, x, y + 1, z) || world.field_73012_v.nextInt(5) != 0 || meta < 8) && addSmoke(world, x, y + 1, z, meta)) {

          if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x, y + 1, z + 1, meta);
          if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x, y + 1, z - 1, meta);
          if (!isOdd) {

            if (world.field_73012_v.nextBoolean() && addSmoke(world, x + 1, y + 1, z, meta))
            {
              if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x + 1, y + 1, z + 1, meta);
              if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x + 1, y + 1, z - 1, meta);

            }

          }
          else if (world.field_73012_v.nextBoolean() && addSmoke(world, x - 1, y + 1, z, meta)) {

            if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x - 1, y + 1, z + 1, meta);
            if (world.field_73012_v.nextInt((16 - meta) / 2) != 0) addSmoke(world, x - 1, y + 1, z - 1, meta);

          }
        }
      }
    }
  }



  private boolean isSurrounded(World world, int x, int y, int z) {
    return (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH) && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH) && world
      .isSideSolid(x - 1, y, z, ForgeDirection.EAST) && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST));
  }



  public void func_149695_a(World world, int x, int y, int z, Block neighborType) {
    verify(world, x, y, z);
  }

  private void verify(World world, int x, int y, int z) {
    if (!world.field_72995_K) {

      int thisMeta = world.func_72805_g(x, y, z);
      if (thisMeta == 0)
        return;
      boolean hasBase = false;
      if (hasBase(world, x, y - 1, z, thisMeta - 1) || hasBase(world, x, y - 1, z - 1, thisMeta - 1) ||
        hasBase(world, x, y - 1, z + 1, thisMeta - 1) || hasBase(world, x - 1, y - 1, z, thisMeta - 1) ||
        hasBase(world, x - 1, y - 1, z - 1, thisMeta - 1) || hasBase(world, x - 1, y - 1, z + 1, thisMeta - 1) ||
        hasBase(world, x + 1, y - 1, z, thisMeta - 1) || hasBase(world, x + 1, y - 1, z - 1, thisMeta - 1) ||
        hasBase(world, x + 1, y - 1, z + 1, thisMeta - 1))
      {
        hasBase = true;
      }

      if (!hasBase) {
        world.func_147468_f(x, y, z);
      }
    }
  }

  private boolean hasBase(World world, int x, int y, int z, int meta) {
    return (world.func_72899_e(x, y, z) && world.func_147439_a(x, y, z) == this && world.func_72805_g(x, y, z) == meta);
  }


  private boolean addSmoke(World world, int x, int y, int z, int meta) {
    if (world.func_147437_c(x, y, z))
    {
      return world.func_147465_d(x, y, z, this, meta + 1, 2);
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSmoke.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */