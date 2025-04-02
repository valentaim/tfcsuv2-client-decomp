package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;









public class WorldGenCaveDecor
  implements IWorldGenerator
{
  public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;
    for (int xCoord = 0; xCoord < 16; xCoord++) {

      for (int zCoord = 0; zCoord < 16; zCoord++) {

        for (int y = 127; y >= 0; y--) {

          int x = chunkX + xCoord;
          int z = chunkZ + zCoord;
          if (rand.nextInt(35) == 0 && createStalactite(world, rand, x, y, z)) {

            if (!createStalagmite(world, rand, x, y - 5, z)) {
              createStalagmite(world, rand, x, y - 6, z);
            }
          } else if (rand.nextInt(35) == 0) {

            createStalagmite(world, rand, x, y, z);
          }
        }
      }
    }
  }


  private boolean createStalactite(World world, Random rand, int x, int y, int z) {
    if (!world.field_72995_K && y > 8 && world.func_147437_c(x, y, z) && TFC_Core.isRawStone(world, x, y + 1, z))
    {
      if (world.func_147437_c(x, y - 1, z) && world.func_147437_c(x, y - 2, z) && world.func_147437_c(x, y - 3, z)) {

        world.func_147465_d(x, y, z, TFCBlocks.stoneStalac, 0, 2);
        world.func_147465_d(x, y - 1, z, TFCBlocks.stoneStalac, 0, 2);
        world.func_147465_d(x, y - 2, z, TFCBlocks.stoneStalac, 0, 2);
        return true;
      }
    }
    return false;
  }


  private boolean createStalagmite(World world, Random rand, int x, int y, int z) {
    if (!world.field_72995_K && y < 128 && world.func_147437_c(x, y, z) && TFC_Core.isRawStone(world, x, y - 1, z) && !world.func_72937_j(x, y, z))
    {
      if (world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z) && world.func_147437_c(x, y + 3, z)) {

        world.func_147465_d(x, y, z, TFCBlocks.stoneStalac, 8, 2);
        world.func_147465_d(x, y + 1, z, TFCBlocks.stoneStalac, 8, 2);
        world.func_147465_d(x, y + 2, z, TFCBlocks.stoneStalac, 8, 2);
        return true;
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCaveDecor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */