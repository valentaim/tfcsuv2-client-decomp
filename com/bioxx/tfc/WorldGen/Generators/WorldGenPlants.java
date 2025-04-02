package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomFruitTree;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;



public class WorldGenPlants
  implements IWorldGenerator
{
  private static WorldGenFungi plantFungiGen = new WorldGenFungi();

  private static WorldGenCustomFruitTree appleTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 0);
  private static WorldGenCustomFruitTree bananaTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 1);
  private static WorldGenCustomFruitTree orangeTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 2);
  private static WorldGenCustomFruitTree grappleTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 3);
  private static WorldGenCustomFruitTree lemonTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 4);
  private static WorldGenCustomFruitTree oliveTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 5);
  private static WorldGenCustomFruitTree cherryTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 6);
  private static WorldGenCustomFruitTree peachTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 7);

  private static WorldGenCustomFruitTree plumTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves2, 8);


  private static WorldGenBerryBush wintergreenGen = new WorldGenBerryBush(false, 0, 12, 1, 5);
  private static WorldGenBerryBush blueberryGen = new WorldGenBerryBush(false, 1, 6, 1, 4);
  private static WorldGenBerryBush raspberryGen = new WorldGenBerryBush(false, 2, 5, 2, 4);
  private static WorldGenBerryBush strawberryGen = new WorldGenBerryBush(false, 3, 8, 1, 4);
  private static WorldGenBerryBush blackberryGen = new WorldGenBerryBush(false, 4, 5, 2, 4);
  private static WorldGenBerryBush bunchberryGen = new WorldGenBerryBush(false, 5, 8, 1, 4);
  private static WorldGenBerryBush cranberryGen = new WorldGenBerryBush(false, 6, 15, 1, 6);
  private static WorldGenBerryBush snowberryGen = new WorldGenBerryBush(false, 7, 6, 1, 4);
  private static WorldGenBerryBush elderberryGen = new WorldGenBerryBush(false, 8, 5, 2, 4);
  private static WorldGenBerryBush gooseberryGen = new WorldGenBerryBush(false, 9, 8, 1, 4);
  private static WorldGenBerryBush cloudberryGen = new WorldGenBerryBush(false, 10, 12, 1, 6, TFCBlocks.peatGrass);








  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;





    int grassPerChunk = 0;
    int flowerChunkRarity = 30;
    int mushroomsPerChunk = 0;


    float rain = TFC_Climate.getRainfall(world, chunkX, 144, chunkZ);





    if (rain >= 125.0F) {

      grassPerChunk += 12;
      mushroomsPerChunk++;
    }
    if (rain >= 250.0F) {

      grassPerChunk += 18;
      flowerChunkRarity -= 2;
      mushroomsPerChunk++;
    }
    if (rain >= 500.0F) {

      grassPerChunk += 24;
      flowerChunkRarity -= 3;
      mushroomsPerChunk++;
    }
    if (rain >= 1000.0F) {

      flowerChunkRarity -= 5;
      mushroomsPerChunk++;
    }
    if (rain >= 2000.0F) {

      flowerChunkRarity -= 5;
      mushroomsPerChunk++;
    }
    float bioTemperature = TFC_Climate.getBioTemperatureHeight(world, chunkX, 144, chunkZ);
    if (bioTemperature < 10.0F)
    {
      grassPerChunk /= 2;
    }
    if (bioTemperature < 5.0F)
    {
      grassPerChunk /= 2;
    }
    if (bioTemperature < 0.0F)
    {
      grassPerChunk = 0;
    }

    WorldGenFlowers.generate(world, random, chunkX, chunkZ, flowerChunkRarity);

    genBushes(random, chunkX, chunkZ, world); int i;
    for (i = 0; i < grassPerChunk; i++) {

      int xCoord = chunkX + random.nextInt(16) + 8;
      int zCoord = chunkZ + random.nextInt(16) + 8;
      int yCoord = world.func_72825_h(xCoord, zCoord);
      bioTemperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
      if (world.func_147437_c(xCoord, yCoord, zCoord) && TFCBlocks.tallGrass
        .func_149718_j(world, xCoord, yCoord, zCoord) &&
        !TFC_Core.isDryGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
      {
        world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(20) == 0) ? 1 : 0, 2);
      }

      if (bioTemperature >= 0.0F)
      {
        if (world.func_147437_c(xCoord, yCoord, zCoord) && TFCBlocks.tallGrass
          .func_149718_j(world, xCoord, yCoord, zCoord) &&
          TFC_Core.isDryGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
        {
          world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.tallGrass, (world.field_73012_v.nextInt(60) == 0) ? 1 : 2, 2);
        }
      }
    }

    for (i = 0; i < mushroomsPerChunk; i++) {

      if (random.nextInt(4) == 0) {

        int xCoord = chunkX + random.nextInt(16) + 8;
        int zCoord = chunkZ + random.nextInt(16) + 8;
        int yCoord = world.func_72825_h(xCoord, zCoord);
        plantFungiGen.genWithMeta(world, random, xCoord, yCoord, zCoord, 0);
      }

      if (random.nextInt(8) == 0) {

        int xCoord = chunkX + random.nextInt(16) + 8;
        int zCoord = chunkZ + random.nextInt(16) + 8;
        int yCoord = world.func_72825_h(xCoord, zCoord);
        plantFungiGen.genWithMeta(world, random, xCoord, yCoord, zCoord, 1);
      }
    }

    if (random.nextInt(70) == 0 && rain >= 500.0F) {

      int xCoord = chunkX + random.nextInt(16) + 8;
      int zCoord = chunkZ + random.nextInt(16) + 8;
      int yCoord = world.func_72825_h(xCoord, zCoord);
      bioTemperature = TFC_Climate.getBioTemperatureHeight(world, xCoord, yCoord, zCoord);
      switch ((new Random(world.func_72905_C() + (((chunkX >> 4) - (chunkZ >> 4)) * (chunkZ >> 4)))).nextInt(9)) {


        default:
          if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            appleTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 1:
          if (bioTemperature > 15.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            bananaTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 2:
          if (bioTemperature > 12.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            orangeTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 3:
          if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            grappleTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 4:
          if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            lemonTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 5:
          if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            oliveTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 6:
          if (bioTemperature > 8.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
            cherryTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          }
          return;

        case 7:
          if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord)))
            peachTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 8:
          break;
      }
      if (bioTemperature > 10.0F && world.func_147437_c(xCoord, yCoord, zCoord) && TFC_Core.isGrass(world.func_147439_a(xCoord, yCoord - 1, zCoord))) {
        plumTree.func_76484_a(world, random, xCoord, yCoord, zCoord);
      }
    }
  }














  private void genBushes(Random random, int chunkX, int chunkZ, World world) {
    if (random.nextInt(12) == 0) {

      int xCoord = chunkX + random.nextInt(16) + 8;
      int zCoord = chunkZ + random.nextInt(16) + 8;
      int yCoord = world.func_72825_h(xCoord, zCoord);
      switch (random.nextInt(11)) {

        default:
          wintergreenGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 1:
          blueberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 2:
          raspberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 3:
          strawberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 4:
          blackberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 5:
          bunchberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 6:
          cranberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 7:
          snowberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 8:
          elderberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
          return;
        case 9:
          gooseberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord); return;
        case 10:
          break;
      }  cloudberryGen.func_76484_a(world, random, xCoord, yCoord, zCoord);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenPlants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */