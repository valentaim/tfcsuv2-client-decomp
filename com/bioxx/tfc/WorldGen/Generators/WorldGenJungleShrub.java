package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenJungleShrub
  extends WorldGenerator
{
  private final int meta;

  public WorldGenJungleShrub(int m) {
    this.meta = m;
  }





  public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
    do {
      Block block = world.func_147439_a(x, y, z);
      if (!block.isLeaves((IBlockAccess)world, x, y, z) && !block.isAir((IBlockAccess)world, x, y, z)) {
        break;
      }

      --y;
    } while (y > 0);

    Block block1 = world.func_147439_a(x, y, z);

    if (TFC_Core.isSoil(block1)) {

      y++;
      func_150516_a(world, x, y, z, TFCBlocks.logNatural, this.meta);

      for (int l = y; l <= y + 2; l++) {

        int i1 = l - y;
        int j1 = 2 - i1;

        for (int k1 = x - j1; k1 <= x + j1; k1++) {

          int l1 = k1 - x;

          for (int i2 = z - j1; i2 <= z + j1; i2++) {

            int j2 = i2 - z;

            if ((Math.abs(l1) != j1 || Math.abs(j2) != j1 || rand.nextInt(2) != 0) && world.func_147439_a(k1, l, i2).canBeReplacedByLeaves((IBlockAccess)world, k1, l, i2))
            {
              func_150516_a(world, k1, l, i2, TFCBlocks.leaves, this.meta);
            }
          }
        }
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenJungleShrub.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */