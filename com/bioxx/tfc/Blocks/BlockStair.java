package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.CollisionRayTraceStandard;
import com.bioxx.tfc.TileEntities.TEPartial;
import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;



public class BlockStair
  extends BlockPartial
  implements ICustomCollision
{
  public BlockStair(Material m) {
    super(m);
  }



  public int func_149645_b() {
    return TFCBlocks.stairRenderId;
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return super.func_149668_a(world, x, y, z);
  }



  public boolean func_149659_a(Explosion ex) {
    return false;
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public AxisAlignedBB func_149633_g(World world, int i, int j, int k) {
    return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 1), (k + 1));
  }



  public void func_149719_a(IBlockAccess bAccess, int i, int j, int k) {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {}



  public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);
    long rvmeta = te.extraData;



    switch (side) {

      case DOWN:
        return ((rvmeta & 0xF0L) == 0L);
      case UP:
        return ((rvmeta & 0xFL) == 0L);
      case NORTH:
        return ((rvmeta & 0x66L) == 0L);
      case SOUTH:
        return ((rvmeta & 0x99L) == 0L);
      case EAST:
        return ((rvmeta & 0xAAL) == 0L);
      case WEST:
        return ((rvmeta & 0x55L) == 0L);
    }
    return false;
  }





  public void addCollisionBoxesToList(World world, int x, int y, int z, List<Object[]> list) {
    TEPartial te = (TEPartial)world.func_147438_o(x, y, z);

    if (te == null)
      return;  long rvmeta = te.extraData;

    if ((rvmeta & 0x1L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.5D, 0.5D, 0.5D, 1.0D, 1.0D) });
    }
    if ((rvmeta & 0x2L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.5D, 0.0D, 1.0D, 1.0D, 0.5D) });
    }
    if ((rvmeta & 0x4L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.5D, 0.0D, 0.5D, 1.0D, 0.5D) });
    }
    if ((rvmeta & 0x8L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.5D, 0.5D, 1.0D, 1.0D, 1.0D) });
    }
    if ((rvmeta & 0x10L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.5D, 0.5D, 0.5D, 1.0D) });
    }
    if ((rvmeta & 0x20L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, 0.0D, 1.0D, 0.5D, 0.5D) });
    }
    if ((rvmeta & 0x40L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.5D, 0.5D, 0.5D) });
    }
    if ((rvmeta & 0x80L) == 0L)
    {
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, 0.5D, 1.0D, 0.5D, 1.0D) });
    }
  }




  public void func_149743_a(World world, int i, int j, int k, AxisAlignedBB aabb, List<AxisAlignedBB> list, Entity entity) {
    ArrayList<Object[]> l = new ArrayList();
    addCollisionBoxesToList(world, i, j, k, l);
    for (Object[] o : l) {

      AxisAlignedBB a = ((AxisAlignedBB)o[0]).func_72325_c(i, j, k);
      if (a != null && aabb.func_72326_a(a)) {
        list.add(a);
      }
    }
  }


  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
    return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockStair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */