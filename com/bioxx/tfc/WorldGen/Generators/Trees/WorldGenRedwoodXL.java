package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;



public class WorldGenRedwoodXL
  extends WorldGenerator
{
  private final Block blockLeaf;
  private final Block blockWood;
  private final int metaLeaf;
  private final int metaWood;

  public WorldGenRedwoodXL(boolean doNotify) {
    super(doNotify);
    this.blockLeaf = TFCBlocks.leaves;
    this.metaLeaf = 9;
    this.blockWood = TFCBlocks.logNatural;
    this.metaWood = 9;
  }



  public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
    int height = rand.nextInt(20) + 22;

    if (y < 1 || y + height + 1 > 256) {
      return false;
    }
    if (!TFC_Core.isSoil(world.func_147439_a(x, y - 1, z)) ||
      !TFC_Core.isSoil(world.func_147439_a(x - 1, y - 1, z)) ||
      !TFC_Core.isSoil(world.func_147439_a(x, y - 1, z - 1)) ||
      !TFC_Core.isSoil(world.func_147439_a(x - 1, y - 1, z - 1)) || y >= 180)
    {

      return false;
    }

    int l = 4 + rand.nextInt(6);
    int j = 5 + rand.nextInt(12);
    for (int y1 = y; y1 <= y + 1 + height; y1++) {

      int k1 = 1;

      if (y1 - y < j) {
        k1 = 0;
      } else {
        k1 = l;
      }
      for (int x1 = x - k1; x1 <= x + k1; x1++) {

        for (int z1 = z - k1; z1 <= z + k1; z1++) {

          if (y1 >= 0 && y1 < 256) {

            Block id1 = world.func_147439_a(x1, y1, z1);
            if (id1 != null && id1.isLeaves((IBlockAccess)world, x1, y1, z1)) {
              return false;
            }
          } else {

            return false;
          }
        }
      }
    }

    Block block = world.func_147439_a(x, y - 1, z);
    Block soil = null;
    int soilMeta = 0;

    if (TFC_Core.isGrass(block)) {

      soil = TFC_Core.getTypeForSoil(block);
      soilMeta = world.func_72805_g(x, y - 1, z);
      world.func_147465_d(x, y - 1, z, soil, soilMeta, 2);
    }

    block = world.func_147439_a(x - 1, y - 1, z);
    if (TFC_Core.isGrass(block)) {

      soil = TFC_Core.getTypeForSoil(block);
      soilMeta = world.func_72805_g(x - 1, y - 1, z);
      world.func_147465_d(x - 1, y - 1, z, soil, soilMeta, 2);
    }

    block = world.func_147439_a(x, y - 1, z - 1);
    if (TFC_Core.isGrass(block)) {

      soil = TFC_Core.getTypeForSoil(block);
      soilMeta = world.func_72805_g(x, y - 1, z - 1);
      world.func_147465_d(x, y - 1, z - 1, soil, soilMeta, 2);
    }

    block = world.func_147439_a(x - 1, y - 1, z - 1);
    if (TFC_Core.isGrass(block)) {

      soil = TFC_Core.getTypeForSoil(block);
      soilMeta = world.func_72805_g(x - 1, y - 1, z - 1);
      world.func_147465_d(x - 1, y - 1, z - 1, soil, soilMeta, 2);
    }

    int l1 = rand.nextInt(2);
    int j2 = 1;
    boolean flag1 = false;

    for (int i = 0; i < height - 3; i++) {

      func_150516_a(world, x, y + i, z, this.blockWood, this.metaWood);
      func_150516_a(world, x - 1, y + i, z, this.blockWood, this.metaWood);
      func_150516_a(world, x, y + i, z - 1, this.blockWood, this.metaWood);
      func_150516_a(world, x - 1, y + i, z - 1, this.blockWood, this.metaWood);
    }

    int k = height - j;
    for (int i3 = 0; i3 <= k; i3++) {

      int m = y + height - i3;
      for (int x1 = x - l1; x1 <= x + l1; x1++) {

        int k4 = x1 - x;
        for (int z1 = z - l1; z1 <= z + l1; z1++) {

          int i5 = z1 - z;
          if (Math.abs(k4) != l1 || Math.abs(i5) != l1 || l1 <= 0) {


            setLeaf(world, x1, m, z1);
            setLeaf(world, x1 - 1, m, z1);
            setLeaf(world, x1, m, z1 - 1);
            setLeaf(world, x1 - 1, m, z1 - 1);
          }
        }
      }

      if (l1 >= j2) {

        l1 = flag1 ? 1 : 0;
        flag1 = true;
        if (++j2 > l) {
          j2 = l;
        }
      } else {

        l1++;
      }
    }
    return true;
  }


  private void setLeaf(World world, int x, int y, int z) {
    Block b = world.func_147439_a(x, y, z);
    if (b.canBeReplacedByLeaves((IBlockAccess)world, x, y, z))
      func_150516_a(world, x, y, z, this.blockLeaf, this.metaLeaf);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenRedwoodXL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */