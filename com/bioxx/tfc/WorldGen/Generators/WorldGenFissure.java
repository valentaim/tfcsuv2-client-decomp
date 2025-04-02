package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.Util.BlockMeta;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.Enums.TFCDirection;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.ByteCoord;
import com.bioxx.tfc.api.Util.CollapseData;
import com.bioxx.tfc.api.Util.CollapseList;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;


public class WorldGenFissure
  implements IWorldGenerator
{
  private Random rand;
  private int poolDepth;
  private int creviceDepth = 1;
  private Block fillBlock;
  private int depth = 20;
  private int minTunnel = 1;

  public boolean checkStability = true;
  private boolean underground;
  private int rarity = 30;



  public WorldGenFissure(Block b) {
    this.fillBlock = b;
  }


  public WorldGenFissure(Block b, int minTunnel, boolean s, int rare) {
    this(b);
    this.minTunnel = minTunnel;
    this.checkStability = s;
    this.rarity = rare;
  }



  public WorldGenFissure setSeed(int i) {
    return this;
  }


  public WorldGenFissure setUnderground(boolean i, int d) {
    this.underground = i;
    this.depth = d;
    return this;
  }




  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    this.rand = random;
    chunkX *= 16;
    chunkZ *= 16;

    int startX = chunkX + random.nextInt(16) + 8;
    int startZ = chunkZ + random.nextInt(16) + 8;
    BiomeGenBase biome = world.func_72807_a(startX, startZ);

    if (this.rarity <= 0 || this.rand.nextInt(this.rarity) != 0 || biome == TFCBiome.BEACH || biome == TFCBiome.OCEAN || biome == TFCBiome.GRAVEL_BEACH || biome == TFCBiome.LAKE || biome == TFCBiome.RIVER || biome == TFCBiome.DEEP_OCEAN) {
      return;
    }

    int startY = world.func_72825_h(startX, startZ) - 1;
    if (this.underground) {
      startY = this.depth + this.rand.nextInt(60);
    }
    generate(world, this.rand, startX, startY, startZ);
  }


  public void generate(World world, Random rand, int x, int y, int z) {
    this.creviceDepth = 1;
    if (rand.nextInt(100) < 50)
      this.creviceDepth += 2 + rand.nextInt(8);
    this.poolDepth = 1 + rand.nextInt(Math.max(this.creviceDepth - 1, 1));

    for (int d = 1; d <= this.poolDepth; d++) {

      if (!world.func_147439_a(x, y - d, z).func_149721_r()) {
        return;
      }
    }
    Block block = world.func_147439_a(x, y, z);

    if (block != null && block.func_149688_o() == Material.field_151586_h)
      return;
    if (this.underground) {
      y -= 20 + rand.nextInt(this.depth);
    }
    int stability = TFC_Climate.getStability(world, x, z);
    if (this.checkStability && stability == 0)
      return;
    if (stability == 1 && this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151586_h)
      this.fillBlock = TFCBlocks.hotWater;
    if (!TFC_Core.isGround(block)) {
      return;
    }
    DataLayer dl = TFC_Climate.getRockLayer(world, x, y, z, 2);
    BlockMeta rockLayer = (this.fillBlock != null) ? new BlockMeta(dl.block, dl.data2) : new BlockMeta(Blocks.field_150350_a, -1);
    if (rockLayer.block == null)
      return;
    List<ByteCoord> map = getCollapseMap(world, x, y - this.creviceDepth, z);
    for (ByteCoord b : map) {

      world.func_147468_f(x + b.x, y + b.y, z + b.z);
      for (int i = 1; i <= this.poolDepth; i++) {
        fill(world, x + b.x, y + b.y - i, z + b.z, rockLayer.block, rockLayer.meta, (this.fillBlock != null) ? this.fillBlock : Blocks.field_150350_a);
      }
      int rx = 0;
      int rz = 0;
      for (int j = 0; j <= this.creviceDepth; j++) {

        carve(world, x + b.x, y + b.y + j, z + b.z, rockLayer.block, rockLayer.meta);
        if (rand.nextInt(3) == 0) {

          rx = -1 + rand.nextInt(3);
          rz = -1 + rand.nextInt(3);
          carve(world, x + b.x + rx, y + b.y + j, z + b.z + rz, rockLayer.block, rockLayer.meta);
        }
      }
      if (this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151587_i) {
        world.func_147465_d(x + b.x, y + b.y - this.poolDepth - 1, z + b.z, rockLayer.block, rockLayer.meta, 2);
      }
    }
    boolean makeTunnel = (map.size() > 10);
    if (makeTunnel) {
      makeTunnel(rand, world, x, z, y, rockLayer);
    }
  }

  private void carve(World world, int x, int y, int z, Block block, int meta) {
    if (world.func_147439_a(x, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isGround(world.func_147439_a(x, y, z)))
      world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 3);
    if (world.func_147439_a(x - 1, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x - 1, y, z)))
      world.func_147465_d(x - 1, y, z, block, meta, 3);
    if (world.func_147439_a(x + 1, y, z).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x + 1, y, z)))
      world.func_147465_d(x + 1, y, z, block, meta, 3);
    if (world.func_147439_a(x, y, z - 1).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x, y, z - 1)))
      world.func_147465_d(x, y, z - 1, block, meta, 3);
    if (world.func_147439_a(x, y, z + 1).func_149688_o() != Material.field_151579_a && TFC_Core.isRawStone(world.func_147439_a(x, y, z + 1))) {
      world.func_147465_d(x, y, z + 1, block, meta, 3);
    }
  }

  private void fill(World world, int x, int y, int z, Block block, int meta, Block fill) {
    world.func_147465_d(x, y, z, fill, 0, 2);
    if (world.func_147439_a(x - 1, y, z).func_149688_o() == Material.field_151579_a)
      world.func_147465_d(x - 1, y, z, block, meta, 2);
    if (world.func_147439_a(x + 1, y, z).func_149688_o() == Material.field_151579_a)
      world.func_147465_d(x + 1, y, z, block, meta, 2);
    if (world.func_147439_a(x, y, z - 1).func_149688_o() == Material.field_151579_a)
      world.func_147465_d(x, y, z - 1, block, meta, 2);
    if (world.func_147439_a(x, y, z + 1).func_149688_o() == Material.field_151579_a)
      world.func_147465_d(x, y, z + 1, block, meta, 2);
    if (world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151579_a) {
      world.func_147465_d(x, y - 1, z, block, meta, 2);
    }
  }

  private void makeTunnel(Random random, World world, int x, int z, int y, BlockMeta rockLayer) {
    int xCoord = x, yCoord = y - this.poolDepth - 1, zCoord = z;
    float downChance = 75.0F;
    while (yCoord > this.minTunnel) {

      if (world.func_147439_a(xCoord, yCoord, zCoord) == Blocks.field_150357_h)
        break;
      if (random.nextFloat() < downChance / 100.0F) {

        yCoord--;
      }
      else {

        int dir = random.nextInt(3);
        switch (dir) {
          case 0:
            xCoord++; break;
          case 1: xCoord--; break;
          case 2: zCoord++; break;
          case 3: zCoord--;
            break;
        }
      }
      world.func_147465_d(xCoord, yCoord, zCoord, (this.fillBlock != null) ? this.fillBlock : Blocks.field_150350_a, 0, 2);

      if (this.fillBlock != null && world.func_147439_a(xCoord + 1, yCoord, zCoord).func_149688_o() != this.fillBlock.func_149688_o())
        world.func_147465_d(xCoord + 1, yCoord, zCoord, rockLayer.block, rockLayer.meta, 2);
      if (this.fillBlock != null && world.func_147439_a(xCoord - 1, yCoord, zCoord).func_149688_o() != this.fillBlock.func_149688_o())
        world.func_147465_d(xCoord - 1, yCoord, zCoord, rockLayer.block, rockLayer.meta, 2);
      if (this.fillBlock != null && world.func_147439_a(xCoord, yCoord, zCoord + 1).func_149688_o() != this.fillBlock.func_149688_o())
        world.func_147465_d(xCoord, yCoord, zCoord + 1, rockLayer.block, rockLayer.meta, 2);
      if (this.fillBlock != null && world.func_147439_a(xCoord, yCoord, zCoord - 1).func_149688_o() != this.fillBlock.func_149688_o()) {
        world.func_147465_d(xCoord, yCoord, zCoord - 1, rockLayer.block, rockLayer.meta, 2);
      }
    }
  }


  public List<ByteCoord> getCollapseMap(World world, int i, int j, int k) {
    ArrayList<ByteCoord> map = new ArrayList<>();
    ArrayList<ByteCoord> checkedmap = new ArrayList<>();
    CollapseList<CollapseData> checkQueue = new CollapseList();
    float baseCollapse = 55.0F;
    float incrementChance = 5.0F;
    float incrementChanceDiag = 2.5F;

    DataLayer dl = TFC_Climate.getRockLayer(world, i, j, k, TFC_Core.getRockLayerFromHeight(world, i, j, k));
    DataLayer dl2 = TFC_Climate.getRockLayer(world, i, j, k, 2);
    BlockMeta rockLayer = (this.fillBlock != null && this.fillBlock.func_149688_o() == Material.field_151587_i) ? new BlockMeta(dl2.block, dl2.data2) : new BlockMeta(dl.block, dl.data2);










    checkQueue.add(new CollapseData(new ByteCoord(0, -1, 0), 100.0F, TFCDirection.NULL));

    while (checkQueue.peek() != null) {

      CollapseData block = (CollapseData)checkQueue.peek();
      int worldX = block.coords.x + i;
      int worldY = block.coords.y + j;
      int worldZ = block.coords.z + k;
      int localX = block.coords.x;
      int localY = block.coords.y;
      int localZ = block.coords.z;
      Block id = world.func_147439_a(worldX, worldY, worldZ);


      if (!checkedmap.contains(block.coords) && TFC_Core.isGround(id) && world.field_73012_v
        .nextFloat() < block.collapseChance / 100.0F) {




        map.add(block.coords);

        if (checkQueue.size() < 500) {
          switch (block.direction) {


            case NORTH:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
              break;


            case SOUTH:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
              break;


            case EAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
              break;


            case WEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
              break;


            case SOUTHEAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.SOUTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
              break;


            case SOUTHWEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), block.collapseChance - 2.5F, TFCDirection.SOUTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), block.collapseChance - 5.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
              break;


            case NORTHEAST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.NORTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
              break;


            case NORTHWEST:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), block.collapseChance - 2.5F, TFCDirection.NORTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), block.collapseChance - 5.0F, TFCDirection.NORTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), block.collapseChance - 5.0F, TFCDirection.WEST));
              break;


            default:
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 0), 50.0F, TFCDirection.EAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 0), 50.0F, TFCDirection.WEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ + 1), 52.5F, TFCDirection.NORTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 1, localY + 0, localZ - 1), 52.5F, TFCDirection.SOUTHEAST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ + 1), 52.5F, TFCDirection.NORTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX - 1, localY + 0, localZ - 1), 52.5F, TFCDirection.SOUTHWEST));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ + 1), 50.0F, TFCDirection.SOUTH));
              checkQueue.add(checkedmap, new CollapseData(new ByteCoord(localX + 0, localY + 0, localZ - 1), 50.0F, TFCDirection.NORTH));
              break;
          }

        }
      } else if (block.collapseChance < 100.0F) {

        for (int d = 0; d <= this.poolDepth; d++) {

          if (TFC_Core.isGround(world.func_147439_a(worldX, worldY - d, worldZ)))
            world.func_147465_d(worldX, worldY - d, worldZ, rockLayer.block, rockLayer.meta, 2);
        }
      }
      checkedmap.add(block.coords);
      checkQueue.removeFirst();
    }
    return map;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenFissure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */