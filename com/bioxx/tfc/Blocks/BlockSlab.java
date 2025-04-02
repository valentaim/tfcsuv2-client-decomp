package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class BlockSlab
  extends BlockPartial
{
  public BlockSlab() {
    super(Material.field_151576_e);
  }



  public int func_149645_b() {
    return TFCBlocks.slabRenderId;
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}



  public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
    if (8 - getTopChiselLevel(te.extraData) + getBottomChiselLevel(te.extraData) < 3)
    {
      if (8 - getSouthChiselLevel(te.extraData) + getNorthChiselLevel(te.extraData) < 3 || 8 -
        getEastChiselLevel(te.extraData) + getWestChiselLevel(te.extraData) < 3)
      {
        return true;
      }
    }
    return false;
  }



  public float func_149712_f(World world, int x, int y, int z) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
    if (te != null)
      return Block.func_149729_e(te.typeID).func_149712_f(world, x, y, z);
    return this.field_149782_v;
  }


  public static int getTopChiselLevel(long data) {
    return (int)(data >> 16L & 0xFL);
  }


  public static int getBottomChiselLevel(long data) {
    return (int)(data >> 4L & 0xFL);
  }


  public static int getEastChiselLevel(long data) {
    return (int)(data >> 12L & 0xFL);
  }


  public static int getWestChiselLevel(long data) {
    return (int)(data & 0xFL);
  }


  public static int getNorthChiselLevel(long data) {
    return (int)(data >> 8L & 0xFL);
  }


  public static int getSouthChiselLevel(long data) {
    return (int)(data >> 20L & 0xFL);
  }







  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    TEPartial te = (TEPartial)world.func_147438_o(i, j, k);

    if (te != null) {

      short type = te.typeID;

      if (type <= 0) {
        return super.func_149668_a(world, i, j, k);
      }

      byte extraX = (byte)(int)(te.extraData & 0xFL);
      byte extraY = (byte)(int)(te.extraData >> 4L & 0xFL);
      byte extraZ = (byte)(int)(te.extraData >> 8L & 0xFL);
      byte extraX2 = (byte)(int)(te.extraData >> 12L & 0xFL);
      byte extraY2 = (byte)(int)(te.extraData >> 16L & 0xFL);
      byte extraZ2 = (byte)(int)(te.extraData >> 20L & 0xFL);

      float div = 0.125F;

      return AxisAlignedBB.func_72330_a((i + div * extraX), (j + div * extraY), (k + div * extraZ), (i + 1.0F - div * extraX2), (j + 1.0F - div * extraY2), (k + 1.0F - div * extraZ2));
    }
    return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 1), (k + 1));
  }







  public AxisAlignedBB func_149633_g(World world, int i, int j, int k) {
    return func_149668_a(world, i, j, k);
  }



  public void func_149719_a(IBlockAccess bAccess, int i, int j, int k) {
    TEPartial te = (TEPartial)bAccess.func_147438_o(i, j, k);

    long extraX = te.extraData & 0xFL;
    long extraY = te.extraData >> 4L & 0xFL;
    long extraZ = te.extraData >> 8L & 0xFL;
    long extraX2 = te.extraData >> 12L & 0xFL;
    long extraY2 = te.extraData >> 16L & 0xFL;
    long extraZ2 = te.extraData >> 20L & 0xFL;

    float div = 0.125F;

    func_149676_a(0.0F + div * (float)extraX, 0.0F + div * (float)extraY, 0.0F + div * (float)extraZ, 1.0F - div * (float)extraX2, 1.0F - div * (float)extraY2, 1.0F - div * (float)extraZ2);
  }




  public void onBlockDestroyedByExplosion(World world, int i, int j, int k) {}




  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {}



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    return true;
  }



  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    TEPartial te = null;

    if (world.func_147438_o(x, y, z) instanceof TEPartial) {
      te = (TEPartial)world.func_147438_o(x, y, z);
    }
    if (te == null) {
      return false;
    }
    long data = te.extraData;

    switch (side) {

      case DOWN:
        return (getBottomChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 &&
          getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0);
      case UP:
        return (getTopChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 &&
          getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0);
      case NORTH:
        return (getNorthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0 &&
          getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
      case SOUTH:
        return (getSouthChiselLevel(data) == 0 && getEastChiselLevel(data) == 0 && getWestChiselLevel(data) == 0 &&
          getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
      case EAST:
        return (getEastChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && getSouthChiselLevel(data) == 0 &&
          getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
      case WEST:
        return (getWestChiselLevel(data) == 0 && getNorthChiselLevel(data) == 0 && getSouthChiselLevel(data) == 0 &&
          getTopChiselLevel(data) == 0 && getBottomChiselLevel(data) == 0);
    }
    return false;
  }




  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSlab.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */