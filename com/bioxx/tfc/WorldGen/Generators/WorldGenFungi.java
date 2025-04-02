package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;







public class WorldGenFungi
  extends WorldGenerator
{
  private int meta;

  public boolean genWithMeta(World world, Random rnd, int x, int y, int z, int m) {
    this.meta = m;
    return func_76484_a(world, rnd, x, y, z);
  }



  public boolean func_76484_a(World world, Random rnd, int x, int y, int z) {
    for (int l = 0; l < 2; l++) {

      int i = x + rnd.nextInt(8) - rnd.nextInt(8);
      int j = y + rnd.nextInt(4) - rnd.nextInt(4);
      int k = z + rnd.nextInt(8) - rnd.nextInt(8);

      if (world.func_147437_c(i, j, k) && TFCBlocks.fungi.func_149718_j(world, i, j, k))
      {
        world.func_147465_d(i, j, k, TFCBlocks.fungi, this.meta, 2);
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */