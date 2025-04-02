package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.TileEntities.TEFruitTreeWood;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;



public class WorldGenCustomFruitTree
  extends WorldGenerator
{
  private final Block leavesBlock;
  private final int metaId;

  public WorldGenCustomFruitTree(boolean flag, Block block, int meta) {
    super(flag);
    this.leavesBlock = block;
    this.metaId = meta;
  }



  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    float temp = TFC_Climate.getBioTemperatureHeight(world, i, j, k);
    float rain = TFC_Climate.getRainfall(world, i, j, k);
    if (world.func_147437_c(i, j, k) && j < 250 && temp > 10.0F && temp < 25.0F && rain >= 500.0F)
      gen(world, random, i, j, k);
    return true;
  }


  public void gen(World world, Random random, int i, int j, int k) {
    world.func_147465_d(i, j, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
    ((TEFruitTreeWood)world.func_147438_o(i, j, k)).setTrunk(true);
    ((TEFruitTreeWood)world.func_147438_o(i, j, k)).setHeight(0);
    ((TEFruitTreeWood)world.func_147438_o(i, j, k)).initBirth();

    if (world.func_147437_c(i, j + 1, k)) {

      world.func_147465_d(i, j + 1, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
      ((TEFruitTreeWood)world.func_147438_o(i, j + 1, k)).setTrunk(true);
      ((TEFruitTreeWood)world.func_147438_o(i, j + 1, k)).setHeight(1);
      ((TEFruitTreeWood)world.func_147438_o(i, j + 1, k)).initBirth();

      if (world.func_147437_c(i, j + 2, k)) {

        world.func_147465_d(i, j + 2, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
        ((TEFruitTreeWood)world.func_147438_o(i, j + 2, k)).setTrunk(true);
        ((TEFruitTreeWood)world.func_147438_o(i, j + 2, k)).setHeight(2);
        ((TEFruitTreeWood)world.func_147438_o(i, j + 2, k)).initBirth();
        surroundWithLeaves(world, i, j + 2, k);

        if (world.func_147437_c(i + 1, j + 2, k) || world.func_147439_a(i + 1, j + 2, k) == this.leavesBlock)
        {
          world.func_147465_d(i + 1, j + 2, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
        }
        if (world.func_147437_c(i - 1, j + 2, k) || world.func_147439_a(i - 1, j + 2, k - 1) == this.leavesBlock)
        {
          world.func_147465_d(i - 1, j + 2, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
        }
        if (world.func_147437_c(i, j + 2, k + 1) || world.func_147439_a(i, j + 2, k + 1) == this.leavesBlock)
        {
          world.func_147465_d(i, j + 2, k + 1, TFCBlocks.fruitTreeWood, this.metaId, 2);
        }
        if (world.func_147437_c(i, j + 2, k - 1) || world.func_147439_a(i, j + 2, k - 1) == this.leavesBlock)
        {
          world.func_147465_d(i, j + 2, k - 1, TFCBlocks.fruitTreeWood, this.metaId, 2);
        }

        if (world.func_147437_c(i, j + 3, k) || world.func_147439_a(i, j + 3, k) == this.leavesBlock) {

          world.func_147465_d(i, j + 3, k, TFCBlocks.fruitTreeWood, this.metaId, 2);
          ((TEFruitTreeWood)world.func_147438_o(i, j + 3, k)).setTrunk(true);
          ((TEFruitTreeWood)world.func_147438_o(i, j + 3, k)).setHeight(3);
          ((TEFruitTreeWood)world.func_147438_o(i, j + 3, k)).initBirth();
          if (world.func_147437_c(i, j + 4, k)) {
            world.func_147465_d(i, j + 4, k, this.leavesBlock, this.metaId & 0x7, 2);
          }
        }
      }
    }
  }

  public void surroundWithLeaves(World world, int i, int j, int k) {
    for (int y = 1; y >= 0; y--) {

      for (int x = 1; x >= -1; x--) {

        for (int z = 1; z >= -1; z--) {

          if (world.func_147437_c(i + x, j + y, k + z))
            world.func_147465_d(i + x, j + y, k + z, this.leavesBlock, this.metaId & 0x7, 2);
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomFruitTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */