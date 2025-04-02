package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;




public class WorldGenCustomCactus
  extends WorldGenerator
{
  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    for (int var6 = 0; var6 < random.nextInt(4) + 1; var6++) {

      int xCoord = i + random.nextInt(8) - random.nextInt(8);
      int yCoord = j + random.nextInt(4) - random.nextInt(4);
      int zCoord = k + random.nextInt(8) - random.nextInt(8);

      if (world.func_147437_c(xCoord, yCoord, zCoord) && !TFC_Core.isBeachBiome((world.func_72807_a(xCoord, zCoord)).field_76756_M)) {

        int var10 = 1 + random.nextInt(random.nextInt(3) + 1);
        for (int var11 = 0; var11 < var10; var11++) {

          if (TFC_Core.isSand(world.func_147439_a(xCoord, yCoord - 1, zCoord)) || TFCBlocks.cactus.func_149718_j(world, xCoord, yCoord + var11, zCoord))
            world.func_147465_d(xCoord, yCoord + var11, zCoord, TFCBlocks.cactus, 0, 2);
        }
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCustomCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */