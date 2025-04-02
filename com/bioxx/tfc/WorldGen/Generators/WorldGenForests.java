package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenAcaciaKoaTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomShortTrees;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Enums.EnumTree;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;






public class WorldGenForests
  implements IWorldGenerator
{
  private WorldGenerator gen0;
  private WorldGenerator gen1;
  private WorldGenerator gen2;
  private int treeType0;
  private int treeType1;
  private int treeType2;
  private float evt;
  private float rainfall;
  private float temperature = 20.0F;




  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;

    if (world.func_72807_a(chunkX, chunkZ) instanceof TFCBiome) {

      TFCBiome biome = (TFCBiome)world.func_72807_a(chunkX, chunkZ);
      if (biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN) {
        return;
      }
      this.rainfall = TFC_Climate.getRainfall(world, chunkX, 0, chunkZ);
      this.evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(chunkX + 8, chunkZ + 8)).floatdata1;
      this.treeType0 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 0);
      this.treeType1 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 1);
      this.treeType2 = TFC_Climate.getTreeLayer(world, chunkX, 144, chunkZ, 2);

      this.gen0 = TFCBiome.getTreeGen(this.treeType0, Boolean.valueOf(random.nextBoolean()));
      this.gen1 = TFCBiome.getTreeGen(this.treeType1, Boolean.valueOf(random.nextBoolean()));
      this.gen2 = TFCBiome.getTreeGen(this.treeType2, Boolean.valueOf(random.nextBoolean()));




      if (!generateJungle(random, chunkX, chunkZ, world)) {
        generateForest(random, chunkX, chunkZ, world);
      }
    }
  }

  private void generateForest(Random random, int chunkX, int chunkZ, World world) {
    int xCoord = chunkX;
    int yCoord = 145;
    int zCoord = chunkZ;

    int numTreesBase = 8;
    if (random.nextInt(10) == 0)
    {
      numTreesBase -= 6;
    }

    int numTrees = 1;
    for (int var2 = 0; var2 < numTrees; var2++) {

      xCoord = chunkX + random.nextInt(16);
      zCoord = chunkZ + random.nextInt(16);
      yCoord = world.func_72976_f(xCoord, zCoord);

      numTrees = (int)(numTreesBase + this.rainfall / 1000.0F * 2.0F);
      if (numTrees > 30) {
        numTrees = 30;
      }
      this.temperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, world.func_72976_f(xCoord, zCoord), zCoord);
      int spawnParam0 = canTreeSpawn(this.treeType0);
      int spawnParam1 = canTreeSpawn(this.treeType1);
      int spawnParam2 = canTreeSpawn(this.treeType2);

      if (getNearWater(world, xCoord, yCoord, zCoord)) {

        this.rainfall *= 2.0F;
        this.evt /= 2.0F;
      }

      try {
        if (zCoord > 14500 || zCoord < -14500) {
          this.gen2 = TFCBiome.getTreeGen(8, Boolean.valueOf(random.nextBoolean()));
        }

        if ((spawnParam0 & 0x1) > 0 || (spawnParam1 & 0x1) > 0 || (spawnParam2 & 0x1) > 0) {


          if (spawnParam0 > 0 && (spawnParam0 & 0x2) == 0 && spawnParam1 > 0 && (spawnParam1 & 0x2) == 0 && spawnParam2 > 0 && (spawnParam2 & 0x2) == 0)
          {

            if (random.nextInt(8) == 0) {
              numTrees = 1;
            } else {
              return;
            }
          }
        } else {
          return;
        }


        int randomNumber = random.nextInt(100);
        if (randomNumber < 50 && this.gen0 != null && (spawnParam0 == 5 || spawnParam0 == 7))
        {
          this.gen0.func_76484_a(world, random, xCoord, yCoord, zCoord);
        }
        else if (randomNumber < 80 && this.gen1 != null && (spawnParam1 == 5 || spawnParam1 == 7))
        {
          this.gen1.func_76484_a(world, random, xCoord, yCoord, zCoord);
        }
        else if (randomNumber < 100 && this.gen2 != null && (spawnParam2 == 5 || spawnParam2 == 7))
        {
          this.gen2.func_76484_a(world, random, xCoord, yCoord, zCoord);
        }

      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
    }
  }




  private int canTreeSpawn(int tree) {
    float treeEVTMin = (tree != -1) ? (EnumTree.values()[tree]).minEVT : 0.0F;
    float treeEVTMax = (tree != -1) ? (EnumTree.values()[tree]).maxEVT : 0.0F;

    float treeRainMin = (tree != -1) ? (EnumTree.values()[tree]).minRain : 0.0F;
    float treeRainMax = (tree != -1) ? (EnumTree.values()[tree]).maxRain : 0.0F;

    float treeTempMin = (tree != -1) ? (EnumTree.values()[tree]).minTemp : 0.0F;
    float treeTempMax = (tree != -1) ? (EnumTree.values()[tree]).maxTemp : 0.0F;

    int out = 0;

    if (this.temperature >= treeTempMin && this.temperature <= treeTempMax)
      out++;
    if (this.evt >= treeEVTMin && this.evt <= treeEVTMax)
      out += 2;
    if (this.rainfall >= treeRainMin && this.rainfall <= treeRainMax) {
      out += 4;
    }
    return out;
  }


  public boolean generateJungle(Random random, int chunkX, int chunkZ, World world) {
    boolean completed = false;
    int xCoord = chunkX;
    int yCoord = 145;
    int zCoord = chunkZ;





    int numTrees = 50;
    for (int var2 = 0; var2 < numTrees; var2++) {

      xCoord = chunkX + 8 + random.nextInt(16);
      zCoord = chunkZ + 8 + random.nextInt(16);
      yCoord = world.func_72976_f(xCoord, zCoord);


      float temperatureAvg = TFC_Climate.getBioTemperature(world, xCoord, zCoord);


      try {
        if (this.evt >= EnumTree.KAPOK.minEVT && this.evt <= EnumTree.KAPOK.maxEVT && this.rainfall >= EnumTree.KAPOK.minRain && this.rainfall <= EnumTree.KAPOK.maxRain && temperatureAvg >= EnumTree.KAPOK.minTemp && temperatureAvg <= EnumTree.KAPOK.maxTemp) {
          WorldGenerator gen0;





          if (random.nextInt(10) == 0) {
            gen0 = TFCBiome.getTreeGen(15, Boolean.valueOf(false));
          } else if (random.nextInt(2) == 0) {
            WorldGenCustomShortTrees worldGenCustomShortTrees = new WorldGenCustomShortTrees(false, 15);
          } else {
            gen0 = new WorldGenJungleShrub(15);
          }

          if (random.nextBoolean()) {
            gen0.func_76487_a(1.0D, 1.0D, 1.0D);
            gen0.func_76484_a(world, random, xCoord, yCoord, zCoord);
            completed = true;
          }
        }

        if (this.evt >= EnumTree.KOA.minEVT && this.evt <= EnumTree.KOA.maxEVT && this.rainfall >= EnumTree.KOA.minRain && this.rainfall <= EnumTree.KOA.maxRain && temperatureAvg >= EnumTree.KOA.minTemp && temperatureAvg <= EnumTree.KOA.maxTemp)
        {




          if (random.nextBoolean()) {
            WorldGenAcaciaKoaTrees worldGenAcaciaKoaTrees = new WorldGenAcaciaKoaTrees(false, 0);

            worldGenAcaciaKoaTrees.func_76487_a(1.0D, 1.0D, 1.0D);
            worldGenAcaciaKoaTrees.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }

        }
      } catch (IndexOutOfBoundsException indexOutOfBoundsException) {}
    }



    if (completed) {

      WorldGenCustomVines vineGen = new WorldGenCustomVines();








      for (int l = 0; l < 50; l++) {

        int i1 = chunkX + random.nextInt(16) + 8;
        short short1 = 256;
        int j1 = chunkZ + random.nextInt(16) + 8;
        vineGen.generate2(world, random, i1, short1, j1);
      }
    }
    return completed;
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenForests.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */