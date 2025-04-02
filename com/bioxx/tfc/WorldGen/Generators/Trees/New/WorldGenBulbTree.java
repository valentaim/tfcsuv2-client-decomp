package com.bioxx.tfc.WorldGen.Generators.Trees.New;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenBulbTree
  extends WorldGenAbstractTree {
  private final int minTreeHeight;
  private final int randomTreeHeight;
  private final boolean vinesGrow;
  private final Block wood;
  private final Block leaves;
  private final int metaWood;
  private final int metaLeaves;

  public WorldGenBulbTree(int id, Block wood, Block leaves, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight, boolean vinesGrow) {
    super(doBlockNotify);
    this.wood = wood;
    this.leaves = leaves;
    this.metaWood = id;
    this.metaLeaves = id;
    this.minTreeHeight = minTreeHeight;
    this.randomTreeHeight = randomTreeHeight;
    this.vinesGrow = vinesGrow;
  }

  public boolean func_76484_a(World world, Random random, int x, int y, int z) {
    int l = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
    if (!TFC_Core.isSoil(world.func_147439_a(x, y - 1, z)) || y >= world.func_72800_K() - l - 1) return false;
    if (world.func_147439_a(x, y, z) != Blocks.field_150350_a) return false;







    if (y < 256 - l - 1) {
      byte b0 = 3;
      byte b1 = 0;


      int k1;


      for (k1 = y - b0 + l; k1 <= y + l; k1++) {
        int i3 = k1 - y + l;
        int l1 = b1 + 1 - i3 / 3;

        for (int i2 = x - l1; i2 <= x + l1; i2++) {
          int j2 = i2 - x;

          for (int k2 = z - l1; k2 <= z + l1; k2++) {
            int l2 = k2 - z;
            if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || (random.nextInt(2) != 0 && i3 != 0)) {
              Block block1 = world.func_147439_a(i2, k1, k2);
              if (block1.isAir((IBlockAccess)world, i2, k1, k2) || block1.isLeaves((IBlockAccess)world, i2, k1, k2)) {
                func_150516_a(world, i2, k1, k2, this.leaves, this.metaLeaves);
              }
            }
          }
        }
      }

      for (k1 = 0; k1 < l; k1++) {
        Block block = world.func_147439_a(x, y + k1, z);
        if (block.isAir((IBlockAccess)world, x, y + k1, z) || block.isLeaves((IBlockAccess)world, x, y + k1, z)) {
          func_150516_a(world, x, y + k1, z, this.wood, this.metaWood);
          func_150516_a(world, x - 1, y + l - 4, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 4, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 4, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 4, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 5, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 5, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 5, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 5, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 5, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 5, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 5, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 5, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 6, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 6, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 6, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 7, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 7, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 7, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 7, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 7, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 7, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 7, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 7, z + 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x - 1, y + l - 8, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x + 1, y + l - 8, z, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 8, z - 1, this.leaves, this.metaLeaves);
          func_150516_a(world, x, y + l - 8, z + 1, this.leaves, this.metaLeaves);
          if (this.vinesGrow && k1 > 0) {
            if (random.nextInt(3) > 0 && world.func_147437_c(x - 1, y + k1, z)) {
              func_150516_a(world, x - 1, y + k1, z, TFCBlocks.vine, 8);
            }

            if (random.nextInt(3) > 0 && world.func_147437_c(x + 1, y + k1, z)) {
              func_150516_a(world, x + 1, y + k1, z, TFCBlocks.vine, 2);
            }

            if (random.nextInt(3) > 0 && world.func_147437_c(x, y + k1, z - 1)) {
              func_150516_a(world, x, y + k1, z - 1, TFCBlocks.vine, 1);
            }

            if (random.nextInt(3) > 0 && world.func_147437_c(x, y + k1, z + 1)) {
              func_150516_a(world, x, y + k1, z + 1, TFCBlocks.vine, 4);
            }
          }
        }
      }

      if (this.vinesGrow) {
        for (k1 = y - 3 + l; k1 <= y + l; k1++) {
          int i3 = k1 - y + l;
          int l1 = 2 - i3 / 2;

          for (int i2 = x - l1; i2 <= x + l1; i2++) {
            for (int j2 = z - l1; j2 <= z + l1; j2++) {
              if (world.func_147439_a(i2, k1, j2).isLeaves((IBlockAccess)world, i2, k1, j2)) {
                if (random.nextInt(4) == 0 && world.func_147439_a(i2 - 1, k1, j2).isAir((IBlockAccess)world, i2 - 1, k1, j2)) {
                  growVines(world, i2 - 1, k1, j2, 8);
                }

                if (random.nextInt(4) == 0 && world.func_147439_a(i2 + 1, k1, j2).isAir((IBlockAccess)world, i2 + 1, k1, j2)) {
                  growVines(world, i2 + 1, k1, j2, 2);
                }

                if (random.nextInt(4) == 0 && world.func_147439_a(i2, k1, j2 - 1).isAir((IBlockAccess)world, i2, k1, j2 - 1)) {
                  growVines(world, i2, k1, j2 - 1, 1);
                }

                if (random.nextInt(4) == 0 && world.func_147439_a(i2, k1, j2 + 1).isAir((IBlockAccess)world, i2, k1, j2 + 1)) {
                  growVines(world, i2, k1, j2 + 1, 4);
                }
              }
            }
          }
        }

        if (random.nextInt(5) == 0 && l > 5) {
          for (k1 = 0; k1 < 2; k1++) {
            for (int i3 = 0; i3 < 4; i3++) {
              if (random.nextInt(4 - k1) == 0) {
                int l1 = random.nextInt(3);
                func_150516_a(world, x + Direction.field_71583_a[Direction.field_71580_e[i3]], y + l - 5 + k1, z + Direction.field_71581_b[Direction.field_71580_e[i3]], Blocks.field_150375_by, l1 << 2 | i3);
              }
            }
          }
        }
      }

      return true;
    }
    return false;
  }



  private void growVines(World world, int x, int y, int z, int flag) {
    func_150516_a(world, x, y, z, TFCBlocks.vine, flag);
    int i1 = 4;

    while (true) {
      y--;
      if (world.func_147439_a(x, y, z).isAir((IBlockAccess)world, x, y, z) || i1 <= 0) {
        return;
      }

      func_150516_a(world, x, y, z, TFCBlocks.vine, flag);
      i1--;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\WorldGenBulbTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */