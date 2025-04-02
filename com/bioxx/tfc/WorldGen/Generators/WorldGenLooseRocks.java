package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;







public class WorldGenLooseRocks
  implements IWorldGenerator
{
  public boolean generateRocks(World world, Random random, int i, int j, int k) {
    if ((world.func_147437_c(i, j + 1, k) || world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE || world.func_147439_a(i, j + 1, k) == TFCBlocks.tallGrass) && (world
      .func_147439_a(i, j, k).func_149688_o() == Material.field_151577_b || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151576_e) && world.func_147439_a(i, j, k).func_149662_c())
    {
      if (world.func_147465_d(i, j + 1, k, TFCBlocks.worldItem, 0, 2)) {

        TEWorldItem te = (TEWorldItem)world.func_147438_o(i, j + 1, k);
        ItemStack sample = getCoreSample(world, i, j, k);
        if (world.field_73012_v.nextInt(3) == 0 && sample != null) {

          te.storage[0] = sample;
        }
        else {

          DataLayer dl = TFC_Climate.getRockLayer(world, i, j, k, 0);

          te.storage[0] = new ItemStack(TFCItems.looseRock, 1, dl.data1);
        }
      }
    }
    return true;
  }


  private ItemStack getCoreSample(World world, int xCoord, int yCoord, int zCoord) {
    ArrayList<Item> coreSample = new ArrayList<>();
    ArrayList<ItemStack> coreSampleStacks = new ArrayList<>();
    for (int x = -15; x < 16; x++) {

      for (int z = -15; z < 16; z++) {

        for (int y = yCoord; y > yCoord - 35; y--) {

          if (world.func_72899_e(xCoord + x, y, zCoord + z) && world.func_147439_a(xCoord + x, y, zCoord + z) == TFCBlocks.ore) {

            int m = world.func_72805_g(xCoord + x, y, zCoord + z);
            if (!coreSample.contains(BlockOre.getDroppedItem(m)))
            {
              if (m != 14 && m != 15) {

                coreSample.add(BlockOre.getDroppedItem(m));
                coreSampleStacks.add(new ItemStack(BlockOre.getDroppedItem(m), 1, m));
              }
            }
          }
        }
      }
    }
    if (!coreSampleStacks.isEmpty())
      return coreSampleStacks.get(world.field_73012_v.nextInt(coreSampleStacks.size()));
    return null;
  }




  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;

    if (world.func_72807_a(chunkX, chunkZ) instanceof TFCBiome) {

      TFCBiome biome = (TFCBiome)world.func_72807_a(chunkX, chunkZ);
      if (biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN) {
        return;
      }
    }

    for (int itemCount = 0; itemCount < 8; itemCount++) {

      int xCoord = chunkX + random.nextInt(16) + 8;
      int zCoord = chunkZ + random.nextInt(16) + 8;
      generateRocks(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord);
    }


    for (int stickCount = 0; stickCount < 5; stickCount++) {

      int xCoord = chunkX + random.nextInt(16) + 8;
      int zCoord = chunkZ + random.nextInt(16) + 8;
      generateSticks(world, random, xCoord, world.func_72825_h(xCoord, zCoord) - 1, zCoord);
    }
  }


  public boolean generateSticks(World world, Random random, int i, int j, int k) {
    if ((world.func_147437_c(i, j + 1, k) || world.func_147439_a(i, j + 1, k) == Blocks.field_150433_aE || world.func_147439_a(i, j + 1, k) == TFCBlocks.tallGrass) && (world
      .func_147439_a(i, j, k).func_149688_o() == Material.field_151577_b || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151576_e || world
      .func_147439_a(i, j, k).func_149688_o() == Material.field_151595_p || world.func_147439_a(i, j, k).func_149688_o() == Material.field_151578_c) && world.func_147439_a(i, j, k).func_149662_c())
    {
      if (world.func_72807_a(i, k) instanceof TFCBiome) {

        TFCBiome biome = (TFCBiome)world.func_72807_a(i, k);
        if ((biome == TFCBiome.DEEP_OCEAN || biome == TFCBiome.BEACH || biome == TFCBiome.GRAVEL_BEACH || biome == TFCBiome.OCEAN || biome == TFCBiome.RIVER || isNearTree(world, i, j, k)) && world
          .func_147465_d(i, j + 1, k, TFCBlocks.worldItem, 0, 2)) {

          TEWorldItem te = (TEWorldItem)world.func_147438_o(i, j + 1, k);

          te.storage[0] = new ItemStack(TFCItems.stick, 1);
        }
      }
    }
    return true;
  }


  private boolean isNearTree(World world, int i, int j, int k) {
    if (world.func_147439_a(i, j + 3, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i + 5, j + 3, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i - 5, j + 3, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i, j + 3, k + 5).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i, j + 3, k - 5).func_149688_o() == Material.field_151584_j) {
      return true;
    }
    return (world.func_147439_a(i, j + 6, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i + 5, j + 6, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i - 5, j + 6, k).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i, j + 6, k + 5).func_149688_o() == Material.field_151584_j || world
      .func_147439_a(i, j + 6, k - 5).func_149688_o() == Material.field_151584_j);
  }

  public static boolean rocksNearby(World world, int i, int j, int k) {
    return (world.func_147439_a(i + 1, j + 1, k) != TFCBlocks.worldItem || world.func_147439_a(i + 1, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k + 1) != TFCBlocks.worldItem || world.func_147439_a(i - 1, j + 1, k - 1) != TFCBlocks.worldItem || world.func_147439_a(i, j + 1, k - 1) != TFCBlocks.worldItem || world.func_147439_a(i + 1, j + 1, k) != TFCBlocks.worldItem);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenLooseRocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */