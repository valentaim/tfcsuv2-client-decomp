package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumTree;
import com.bioxx.tfc.api.TFCBlocks;
import fof.tfcsu.tfcsu;
import java.util.Random;
import net.minecraft.world.World;





public class WorldGenSaplings
{
  public void generate(World world, Random random, int xCoord, int yCoord, int zCoord) {
    if (tfcsu.instance.getPluginAccess().isTownBlock(xCoord, zCoord))
      return;
    boolean hasSpaceToGrow = true;

    int treeType0 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 0);
    int treeType1 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 1);
    int treeType2 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 2);

    for (int y = -3; y < 6 && hasSpaceToGrow; y++) {

      for (int x = -7; x < 8 && hasSpaceToGrow; x++) {

        for (int z = -7; z < 8 && hasSpaceToGrow; z++) {

          if (world.func_147439_a(xCoord + x, yCoord + y, zCoord + z) == TFCBlocks.logNatural || world
            .func_147439_a(xCoord + x, yCoord + y, zCoord + z) == TFCBlocks.sapling)
          {
            hasSpaceToGrow = false;
          }
        }
      }
    }

    if (hasSpaceToGrow) {

      float rainfall = TFC_Climate.getRainfall(world, xCoord, yCoord, zCoord);
      float temperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
      float evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(xCoord, zCoord)).floatdata1;

      if (treeType0 < 0 || treeType0 > 15)
        treeType0 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 2);
      if (treeType1 < 0 || treeType1 > 15)
        treeType1 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 0);
      if (treeType2 < 0 || treeType2 > 15) {
        treeType2 = TFC_Climate.getTreeLayer(world, xCoord, yCoord, zCoord, 1);
      }




      if (getNearWater(world, xCoord, yCoord, zCoord)) {

        rainfall *= 2.0F;
        evt /= 2.0F;
      }




      if (zCoord > 14500 || zCoord < -14500) {
        treeType2 = 8;
      }

      boolean canSpawnTemp0 = false;
      boolean canSpawnTemp1 = false;
      boolean canSpawnTemp2 = false;
      boolean canSpawnRain0 = false;
      boolean canSpawnRain1 = false;
      boolean canSpawnRain2 = false;
      boolean canSpawnEVT0 = false;
      boolean canSpawnEVT1 = false;
      boolean canSpawnEVT2 = false;

      if (treeType0 != -1) {

        float tree0EVTMin = (EnumTree.values()[treeType0]).minEVT;
        float tree0EVTMax = (EnumTree.values()[treeType0]).maxEVT;
        float tree0RainMin = (EnumTree.values()[treeType0]).minRain;
        float tree0RainMax = (EnumTree.values()[treeType0]).maxRain;
        float tree0TempMin = (EnumTree.values()[treeType0]).minTemp;
        float tree0TempMax = (EnumTree.values()[treeType0]).maxTemp;

        canSpawnTemp0 = (temperature >= tree0TempMin && temperature <= tree0TempMax);
        canSpawnRain0 = (rainfall >= tree0RainMin && rainfall <= tree0RainMax);
        canSpawnEVT0 = (evt >= tree0EVTMin && evt <= tree0EVTMax);
      }

      if (treeType1 != -1) {

        float tree1EVTMin = (EnumTree.values()[treeType1]).minEVT;
        float tree1EVTMax = (EnumTree.values()[treeType1]).maxEVT;
        float tree1RainMin = (EnumTree.values()[treeType1]).minRain;
        float tree1RainMax = (EnumTree.values()[treeType1]).maxRain;
        float tree1TempMin = (EnumTree.values()[treeType1]).minTemp;
        float tree1TempMax = (EnumTree.values()[treeType1]).maxTemp;

        canSpawnTemp1 = (temperature >= tree1TempMin && temperature <= tree1TempMax);
        canSpawnRain0 = (rainfall >= tree1RainMin && rainfall <= tree1RainMax);
        canSpawnEVT0 = (evt >= tree1EVTMin && evt <= tree1EVTMax);
      }

      if (treeType2 != -1) {

        float tree2EVTMin = (EnumTree.values()[treeType2]).minEVT;
        float tree2EVTMax = (EnumTree.values()[treeType2]).maxEVT;
        float tree2RainMin = (EnumTree.values()[treeType2]).minRain;
        float tree2RainMax = (EnumTree.values()[treeType2]).maxRain;
        float tree2TempMin = (EnumTree.values()[treeType2]).minTemp;
        float tree2TempMax = (EnumTree.values()[treeType2]).maxTemp;

        canSpawnTemp2 = (temperature >= tree2TempMin && temperature <= tree2TempMax);
        canSpawnRain0 = (rainfall >= tree2RainMin && rainfall <= tree2RainMax);
        canSpawnEVT0 = (evt >= tree2EVTMin && evt <= tree2EVTMax);
      }


      if (canSpawnTemp0 || canSpawnTemp1 || canSpawnTemp2) {


        if (!canSpawnEVT0 && !canSpawnEVT1 && !canSpawnEVT2)
        {

          if (random.nextInt(10) > 0) {
            return;
          }
        }
      } else {
        return;
      }


      int randomNumber = random.nextInt(100);
      if (randomNumber < 40) {

        if (canSpawnTemp0 && canSpawnRain0) {
          world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType0, 2);
        }
      } else if (randomNumber < 70) {

        if (canSpawnTemp1 && canSpawnRain1) {
          world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType1, 2);
        }
      } else if (randomNumber < 100) {

        if (canSpawnTemp2 && canSpawnRain2) {
          world.func_147465_d(xCoord, yCoord + 1, zCoord, TFCBlocks.sapling, treeType2, 2);
        }
      }
    }
  }

  public boolean getNearWater(World world, int x, int y, int z) {
    for (int x1 = -4; x1 < 5; x1++) {

      for (int z1 = -4; z1 < 5; z1++) {

        for (int y1 = -2; y1 < 1; y1++) {

          if (world.func_72899_e(x + x1, y + y1, z + z1) && TFC_Core.isWater(world.func_147439_a(x + x1, y + y1, z + z1)))
            return true;
        }
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenSaplings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */