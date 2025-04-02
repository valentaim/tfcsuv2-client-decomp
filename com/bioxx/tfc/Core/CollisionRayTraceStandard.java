package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;





public class CollisionRayTraceStandard
{
  public static MovingObjectPosition collisionRayTrace(ICustomCollision b, World world, int x, int y, int z, Vec3 player, Vec3 view) {
    player = player.func_72441_c(-x, -y, -z);
    view = view.func_72441_c(-x, -y, -z);

    List<Object[]> returns = new ArrayList();


    returns = rayTraceSubBlocks(b, world, player, view, x, y, z, returns);

    if (!returns.isEmpty()) {

      Object[] min = null;
      double distMin = 0.0D;
      for (Object[] ret : returns) {

        double dist = ((Double)ret[2]).doubleValue();
        if (min == null || dist < distMin) {

          distMin = dist;
          min = ret;
        }
      }
      if (min != null) {

        AxisAlignedBB aabb = (AxisAlignedBB)((Object[])min[3])[0];
        ((Block)b).func_149676_a((float)aabb.field_72340_a, (float)aabb.field_72338_b, (float)aabb.field_72339_c, (float)aabb.field_72336_d, (float)aabb.field_72337_e, (float)aabb.field_72334_f);
        rayTraceBound(aabb, x, y, z, player, view);
        MovingObjectPosition mop = new MovingObjectPosition(x, y, z, ((Byte)min[1]).byteValue(), ((Vec3)min[0]).func_72441_c(x, y, z));
        mop.hitInfo = min[3];
        return mop;
      }
    }
    ((Block)b).func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);

    return null;
  }


  public static Object[] rayTraceBound(AxisAlignedBB bound, int i, int j, int k, Vec3 player, Vec3 view) {
    Vec3 minX = player.func_72429_b(view, bound.field_72340_a);
    Vec3 maxX = player.func_72429_b(view, bound.field_72336_d);
    Vec3 minY = player.func_72435_c(view, bound.field_72338_b);
    Vec3 maxY = player.func_72435_c(view, bound.field_72337_e);
    Vec3 minZ = player.func_72434_d(view, bound.field_72339_c);
    Vec3 maxZ = player.func_72434_d(view, bound.field_72334_f);
    if (!isVecInsideYZBounds(bound, minX))
    {
      minX = null;
    }
    if (!isVecInsideYZBounds(bound, maxX))
    {
      maxX = null;
    }
    if (!isVecInsideXZBounds(bound, minY))
    {
      minY = null;
    }
    if (!isVecInsideXZBounds(bound, maxY))
    {
      maxY = null;
    }
    if (!isVecInsideXYBounds(bound, minZ))
    {
      minZ = null;
    }
    if (!isVecInsideXYBounds(bound, maxZ))
    {
      maxZ = null;
    }
    Vec3 tracedBound = null;
    if (minX != null && (tracedBound == null || player.func_72438_d(minX) < player.func_72438_d(tracedBound)))
    {
      tracedBound = minX;
    }
    if (maxX != null && (tracedBound == null || player.func_72438_d(maxX) < player.func_72438_d(tracedBound)))
    {
      tracedBound = maxX;
    }
    if (minY != null && (tracedBound == null || player.func_72438_d(minY) < player.func_72438_d(tracedBound)))
    {
      tracedBound = minY;
    }
    if (maxY != null && (tracedBound == null || player.func_72438_d(maxY) < player.func_72438_d(tracedBound)))
    {
      tracedBound = maxY;
    }
    if (minZ != null && (tracedBound == null || player.func_72438_d(minZ) < player.func_72438_d(tracedBound)))
    {
      tracedBound = minZ;
    }
    if (maxZ != null && (tracedBound == null || player.func_72438_d(maxZ) < player.func_72438_d(tracedBound)))
    {
      tracedBound = maxZ;
    }
    if (tracedBound == null) return null;
    byte side = -1;
    if (tracedBound == minX)
    {
      side = 4;
    }
    if (tracedBound == maxX)
    {
      side = 5;
    }
    if (tracedBound == minY)
    {
      side = 0;
    }
    if (tracedBound == maxY)
    {
      side = 1;
    }
    if (tracedBound == minZ)
    {
      side = 2;
    }
    if (tracedBound == maxZ)
    {
      side = 3;
    }
    return new Object[] { tracedBound, Byte.valueOf(side), Double.valueOf(player.func_72438_d(tracedBound)), bound };
  }


  public static List<Object[]> rayTraceSubBlocks(ICustomCollision b, World world, Vec3 player, Vec3 view, int i, int j, int k, List<Object[]> returns) {
    List<Object[]> bblist = new ArrayList();
    b.addCollisionBoxesToList(world, i, j, k, bblist);
    for (Object[] o : bblist) {

      AxisAlignedBB aabb = (AxisAlignedBB)o[0];
      Object[] ret = rayTraceBound(aabb, i, j, k, player, view);

      if (ret != null)
      {
        returns.add(new Object[] { ret[0], ret[1], ret[2], o });
      }
    }

    return returns;
  }


  private static boolean isVecInsideYZBounds(AxisAlignedBB bound, Vec3 vec3) {
    if (vec3 == null)
    {
      return false;
    }


    return (vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
  }



  private static boolean isVecInsideXZBounds(AxisAlignedBB bound, Vec3 vec3) {
    if (vec3 == null)
    {
      return false;
    }


    return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72449_c >= bound.field_72339_c && vec3.field_72449_c <= bound.field_72334_f);
  }



  private static boolean isVecInsideXYBounds(AxisAlignedBB bound, Vec3 vec3) {
    if (vec3 == null)
    {
      return false;
    }


    return (vec3.field_72450_a >= bound.field_72340_a && vec3.field_72450_a <= bound.field_72336_d && vec3.field_72448_b >= bound.field_72338_b && vec3.field_72448_b <= bound.field_72337_e);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\CollisionRayTraceStandard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */