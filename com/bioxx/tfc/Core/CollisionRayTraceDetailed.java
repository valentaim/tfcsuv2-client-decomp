package com.bioxx.tfc.Core;

import com.bioxx.tfc.Blocks.BlockDetailed;
import com.bioxx.tfc.TileEntities.TEDetailed;
import java.util.BitSet;
import java.util.List;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;




public class CollisionRayTraceDetailed
{
  public static List<Object[]> rayTraceSubBlocks(BlockDetailed construct, Vec3 player, Vec3 view, int i, int j, int k, List<Object[]> returns, BitSet data, TEDetailed te) {
    int d = 8;

    float div = 1.0F / d;

    for (int subX = 0; subX < 8; subX++) {

      for (int subZ = 0; subZ < 8; subZ++) {

        for (int subY = 0; subY < 8; subY++) {

          if (te.data.get((subX * 8 + subZ) * 8 + subY)) {

            float minX = subX * div;
            float maxX = minX + div;
            float minY = subY * div;
            float maxY = minY + div;
            float minZ = subZ * div;
            float maxZ = minZ + div;

            Object[] ret = construct.rayTraceBound(AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ), i, j, k, player, view);
            if (ret != null)
              returns.add(new Object[] { ret[0], ret[1], ret[2], Integer.valueOf(subX), Integer.valueOf(subY), Integer.valueOf(subZ) });
          }
        }
      }
    }
    return returns;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\CollisionRayTraceDetailed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */