package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.DataLayer;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;







public class WorldGenLargeRock
  implements IWorldGenerator
{
  private int xWidth;
  private int xWidth2;
  private int zWidth;
  private int zWidth2;
  private static final int HEIGHT = 3;

  public boolean generate(World world, Random rand, int i, int j, int k) {
    int yOffset = 0;
    boolean isFlatEnough = false;

    for (; yOffset > -2 && !isFlatEnough; yOffset--) {

      if (world.func_147439_a(i, j + yOffset, k).func_149721_r())
      {
        if (world.func_147439_a(i + 1, j + yOffset, k).func_149721_r() && world.func_147439_a(i - 1, j + yOffset, k).func_149721_r() && world
          .func_147439_a(i - 1, j + yOffset, k).func_149721_r() && world.func_147439_a(i, j + yOffset, k + 1).func_149721_r()) {
          isFlatEnough = true;
        }
      }
    }
    if (j <= 155) {


      int i2 = i + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
      int j2 = j + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
      int k2 = k + (rand.nextInt(2) + 1) * (rand.nextBoolean() ? 1 : -1);
      genFromPoint(world, rand, i, j, k, yOffset);
      genFromPoint(world, rand, i2, j2, k2, yOffset);
    }
    return true;
  }


  public void genFromPoint(World world, Random rand, int i, int j, int k, int yOffset) {
    DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(i, k, 0);
    Vec3 center = Vec3.func_72443_a(i, (j + yOffset), k);
    this.xWidth = 3;
    this.xWidth2 = 3;
    this.zWidth = 3;
    this.zWidth2 = 3;

    for (int xCoord = i - this.xWidth; xCoord <= i + this.xWidth2; xCoord++) {

      for (int zCoord = k - this.zWidth; zCoord <= k + this.zWidth2; zCoord++) {

        for (int yCoord = j + yOffset - 3; yCoord <= j + yOffset + 3; yCoord++) {

          Vec3 point = Vec3.func_72443_a(xCoord, yCoord, zCoord);
          double distance = center.func_72436_e(point);
          boolean canPlaceX = true;
          boolean canPlaceZ = true;
          if (xCoord < i && distance > (this.xWidth * 4))
            canPlaceX = false;
          if (xCoord > i && distance > (this.xWidth2 * 4))
            canPlaceX = false;
          if (zCoord < i && distance > (this.zWidth * 4))
            canPlaceZ = false;
          if (zCoord > i && distance > (this.zWidth2 * 4))
            canPlaceZ = false;
          if (rand.nextInt(10) + 1 != 0 && canPlaceX && canPlaceZ) {
            world.func_147465_d(xCoord, yCoord, zCoord, rockLayer1.block, rockLayer1.data2, 2);
          }
        }
      }
    }
  }



  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;
    int xCoord = 0;
    int zCoord = 0;

    for (int count = 0; count < 1; count++) {

      xCoord = chunkX + random.nextInt(16) + 8;
      zCoord = chunkZ + random.nextInt(16) + 8;
      int yCoord = world.func_72976_f(xCoord, zCoord) - 1;
      this.xWidth = 2 + random.nextInt(6);
      this.xWidth2 = 2 + random.nextInt(6);
      this.zWidth = 2 + random.nextInt(6);
      this.zWidth2 = 2 + random.nextInt(6);
      if (random.nextInt(20) == 0 && TFC_Core.isSoil(world.func_147439_a(xCoord, yCoord, zCoord)))
        generate(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenLargeRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */