package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;



public class WorldGenLiquidsTFC
  extends WorldGenerator
{
  private final Block liquidBlock;

  public WorldGenLiquidsTFC(Block block) {
    this.liquidBlock = block;
  }



  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    if (world.func_147439_a(i, j + 1, k) != TFCBlocks.stoneIgIn && world.func_147439_a(i, j + 1, k) != TFCBlocks.stoneSed && world
      .func_147439_a(i, j + 1, k) != TFCBlocks.stoneIgEx && world.func_147439_a(i, j + 1, k) != TFCBlocks.stoneMM)
    {
      return false;
    }
    if (world.func_147439_a(i, j - 1, k) != TFCBlocks.stoneIgIn && world.func_147439_a(i, j - 1, k) != TFCBlocks.stoneSed && world
      .func_147439_a(i, j - 1, k) != TFCBlocks.stoneIgEx && world.func_147439_a(i, j - 1, k) != TFCBlocks.stoneMM)
    {
      return false;
    }
    if (!world.func_147437_c(i, j, k) && world.func_147439_a(i, j, k) != TFCBlocks.stoneIgIn && world.func_147439_a(i, j, k) != TFCBlocks.stoneSed && world
      .func_147439_a(i, j, k) != TFCBlocks.stoneIgEx && world.func_147439_a(i, j, k) != TFCBlocks.stoneMM)
    {
      return false;
    }

    int l = 0;
    if (world.func_147439_a(i - 1, j, k) == TFCBlocks.stoneIgIn && world.func_147439_a(i - 1, j, k) == TFCBlocks.stoneSed && world
      .func_147439_a(i - 1, j, k) == TFCBlocks.stoneIgEx && world.func_147439_a(i - 1, j, k) == TFCBlocks.stoneMM)
    {
      l++;
    }
    if (world.func_147439_a(i + 1, j, k) == TFCBlocks.stoneIgIn && world.func_147439_a(i + 1, j, k) == TFCBlocks.stoneSed && world
      .func_147439_a(i + 1, j, k) == TFCBlocks.stoneIgEx && world.func_147439_a(i + 1, j, k) == TFCBlocks.stoneMM)
    {
      l++;
    }
    if (world.func_147439_a(i, j, k - 1) == TFCBlocks.stoneIgIn && world.func_147439_a(i, j, k - 1) == TFCBlocks.stoneSed && world
      .func_147439_a(i, j, k - 1) == TFCBlocks.stoneIgEx && world.func_147439_a(i, j, k - 1) == TFCBlocks.stoneMM)
    {
      l++;
    }
    if (world.func_147439_a(i, j, k + 1) == TFCBlocks.stoneIgIn && world.func_147439_a(i, j, k + 1) == TFCBlocks.stoneSed && world
      .func_147439_a(i, j, k + 1) == TFCBlocks.stoneIgEx && world.func_147439_a(i, j, k + 1) == TFCBlocks.stoneMM)
    {
      l++;
    }

    int i1 = 0;
    if (world.func_147437_c(i - 1, j, k))
      i1++;
    if (world.func_147437_c(i + 1, j, k))
      i1++;
    if (world.func_147437_c(i, j, k - 1))
      i1++;
    if (world.func_147437_c(i, j, k + 1)) {
      i1++;
    }
    if (l == 3 && i1 == 1) {

      world.func_147465_d(i, j, k, this.liquidBlock, 0, 2);
      world.field_72999_e = true;
      this.liquidBlock.func_149674_a(world, i, j, k, random);
      world.field_72999_e = false;
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenLiquidsTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */