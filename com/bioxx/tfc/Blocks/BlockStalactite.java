package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;




public class BlockStalactite
  extends BlockTerra
{
  private Random r;

  public BlockStalactite() {
    func_149672_a(field_149769_e);
  }



  public void func_149734_b(World world, int i, int j, int k, Random random) {
    if (isStalactite(world.func_72805_g(i, j, k)) && random.nextInt(80) == 0) {

      AxisAlignedBB aabb = func_149668_a(world, i, j, k);

      double xRand = random.nextFloat() * (aabb.field_72336_d - aabb.field_72340_a) + aabb.field_72340_a;
      double zRand = random.nextFloat() * (aabb.field_72334_f - aabb.field_72339_c) + aabb.field_72339_c;

      world.func_72869_a("dripWater", xRand + 0.2D, aabb.field_72338_b + 0.9D, zRand + 0.2D, 0.0D, 0.0D, 0.0D);
    }
  }



  public void func_149719_a(IBlockAccess access, int i, int j, int k) {
    boolean isStalac = isStalactite(access.func_72805_g(i, j, k));
    boolean isStalag = isStalagmite(access.func_72805_g(i, j, k));

    float f = 0.125F;
    this.r = new Random((i + i * k));
    if (isStalac) {

      float height = TFC_Core.isRawStone(access.func_147439_a(i, j + 1, k)) ? 1.0F : (TFC_Core.isRawStone(access.func_147439_a(i, j + 2, k)) ? 2.0F : 3.0F);
      f = 0.1F + this.r.nextFloat() * 0.025F;
      float width = height * f;
      if (height == 3.0F)
      { height = 0.5F + this.r.nextFloat() * 0.5F; }
      else { height = 1.0F; }
       func_149676_a(width, 1.0F - height, width, 1.0F - width, 1.0F, 1.0F - width);
    }
    else if (isStalag) {

      float height = TFC_Core.isRawStone(access.func_147439_a(i, j - 1, k)) ? 1.0F : (TFC_Core.isRawStone(access.func_147439_a(i, j - 2, k)) ? 2.0F : 3.0F);
      f = 0.1F + this.r.nextFloat() * 0.025F;
      float width = height * f;
      if (height == 3.0F)
      { height = 0.5F + this.r.nextFloat() * 0.5F; }
      else { height = 1.0F; }
       func_149676_a(width, 0.0F, width, 1.0F - width, height, 1.0F - width);
    }
  }



  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    boolean isStalac = isStalactite(world.func_72805_g(i, j, k));
    boolean isStalag = isStalagmite(world.func_72805_g(i, j, k));

    float f = 0.125F;
    this.r = new Random((i + i * k));
    if (isStalac) {

      float height = TFC_Core.isRawStone(world.func_147439_a(i, j + 1, k)) ? 1.0F : (TFC_Core.isRawStone(world.func_147439_a(i, j + 2, k)) ? 2.0F : 3.0F);
      f = 0.1F + this.r.nextFloat() * 0.025F;
      float width = height * f;
      if (height == 3.0F)
      { height = 0.5F + this.r.nextFloat() * 0.5F; }
      else { height = 1.0F; }

      return AxisAlignedBB.func_72330_a((i + width), (j - height), (k + width), ((i + 1) - width), (j + 1), ((k + 1) - width));
    }
    if (isStalag) {

      float height = TFC_Core.isRawStone(world.func_147439_a(i, j - 1, k)) ? 1.0F : (TFC_Core.isRawStone(world.func_147439_a(i, j - 2, k)) ? 2.0F : 3.0F);
      f = 0.1F + this.r.nextFloat() * 0.025F;
      float width = height * f;
      if (height == 3.0F)
      { height = 0.5F + this.r.nextFloat() * 0.5F; }
      else { height = 1.0F; }
       return AxisAlignedBB.func_72330_a((i + width), j, (k + width), ((i + 1) - width), (j + height), ((k + 1) - width));
    }

    return AxisAlignedBB.func_72330_a(i + this.field_149759_B, j + this.field_149760_C, k + this.field_149754_D, i + this.field_149755_E, j + this.field_149756_F, k + this.field_149757_G);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
    boolean isStalac = isStalactite(access.func_72805_g(i, j, k));
    boolean isStalag = isStalagmite(access.func_72805_g(i, j, k));
    if (isStalac) {

      if (TFC_Core.isRawStone(access.func_147439_a(i, j + 1, k)))
        return access.func_147439_a(i, j + 1, k).func_149691_a(0, access.func_72805_g(i, j + 1, k));
      if (TFC_Core.isRawStone(access.func_147439_a(i, j + 2, k)))
        return access.func_147439_a(i, j + 2, k).func_149691_a(0, access.func_72805_g(i, j + 2, k));
      if (TFC_Core.isRawStone(access.func_147439_a(i, j + 3, k))) {
        return access.func_147439_a(i, j + 3, k).func_149691_a(0, access.func_72805_g(i, j + 3, k));
      }
    } else if (isStalag) {

      if (TFC_Core.isRawStone(access.func_147439_a(i, j - 1, k)))
        return access.func_147439_a(i, j - 1, k).func_149691_a(0, access.func_72805_g(i, j - 1, k));
      if (TFC_Core.isRawStone(access.func_147439_a(i, j - 2, k)))
        return access.func_147439_a(i, j - 2, k).func_149691_a(0, access.func_72805_g(i, j - 2, k));
      if (TFC_Core.isRawStone(access.func_147439_a(i, j - 3, k)))
        return access.func_147439_a(i, j - 3, k).func_149691_a(0, access.func_72805_g(i, j - 3, k));
    }
    return TFC_Textures.invisibleTexture;
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    return TFC_Textures.invisibleTexture;
  }



  public void func_149695_a(World world, int i, int j, int k, Block b) {
    if (!world.field_72995_K)
    {
      if (!func_149718_j(world, i, j, k)) {

        world.func_147468_f(i, j, k);
        return;
      }
    }
  }



  public boolean func_149718_j(World world, int i, int j, int k) {
    boolean isStalac = isStalactite(world.func_72805_g(i, j, k));
    boolean isStalag = isStalagmite(world.func_72805_g(i, j, k));
    int h = 0;
    if (isStalac) {

      if (TFC_Core.isRawStone(world.func_147439_a(i, j + 1, k))) {
        h = 1;
      } else if (TFC_Core.isRawStone(world.func_147439_a(i, j + 2, k))) {
        h = 2;
      } else if (TFC_Core.isRawStone(world.func_147439_a(i, j + 3, k))) {
        h = 3;
      }  for (int y = h; y > 0; y--)
      {
        if (world.func_147437_c(i, j + y, k))
        {
          return false;
        }
      }

    } else if (isStalag) {

      if (TFC_Core.isRawStone(world.func_147439_a(i, j - 1, k))) {
        h = 1;
      } else if (TFC_Core.isRawStone(world.func_147439_a(i, j - 2, k))) {
        h = 2;
      } else if (TFC_Core.isRawStone(world.func_147439_a(i, j - 3, k))) {
        h = 3;
      }  for (int y = h; y > 0; y--) {

        if (world.func_147437_c(i, j - y, k))
        {
          return false;
        }
      }
    }
    return true;
  }



  public boolean func_149705_a(World world, int x, int y, int z, int i, ItemStack is) {
    return false;
  }



  public boolean isAir(IBlockAccess world, int x, int y, int z) {
    return false;
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }





  public boolean isStalagmite(int meta) {
    return ((meta & 0x8) > 0);
  }





  public boolean isStalactite(int meta) {
    return ((meta & 0x8) == 0);
  }



  public boolean func_149659_a(Explosion ex) {
    return false;
  }



  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }




  public void func_149651_a(IIconRegister registerer) {}



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockStalactite.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */