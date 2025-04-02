package com.bioxx.tfc.WorldGen.Generators.Trees.New;

import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class LOTRWorldGenPine
  extends WorldGenAbstractTree
{
  private Block woodBlock;
  private int woodMeta;
  private Block leafBlock;
  private int leafMeta;
  private int minHeight;
  private int maxHeight;

  public LOTRWorldGenPine(boolean flag, int id, int minh, int maxh) {
    super(flag);
    this.woodBlock = TFCBlocks.logNatural;
    this.woodMeta = id;
    this.leafBlock = TFCBlocks.leaves;
    this.leafMeta = id;
    this.minHeight = minh;
    this.maxHeight = maxh;
  }

  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    int height = MathHelper.func_76136_a(random, this.minHeight, this.maxHeight);
    boolean flag = true;


    if (j >= 1 && height + 1 <= 256) {
      for (int below = j; below <= j + height + 1; below++) {
        byte isSoil = 1;
        if (below == j) {
          isSoil = 0;
        }

        if (below >= j + height - 1) {
          isSoil = 2;
        }

        for (int m = i - isSoil; m <= i + isSoil && flag; m++) {
          for (int n = k - isSoil; n <= k + isSoil && flag; n++) {
            if (below >= 0 && below < 256) {
              if (!isReplaceable(world, m, below, n)) {
                flag = false;
              }
            } else {
              flag = false;
            }
          }
        }
      }
    } else {
      flag = false;
    }

    Block var19 = world.func_147439_a(i, j - 1, k);
    boolean var20 = var19.canSustainPlant((IBlockAccess)world, i, j - 1, k, ForgeDirection.UP, (IPlantable)Blocks.field_150345_g);
    if (!var20) {
      flag = false;
    }

    if (!flag) {
      return false;
    }
    var19.onPlantGrow(world, i, j - 1, k, i, j, k);
    func_150516_a(world, i, j + height, k, this.leafBlock, this.leafMeta);
    generateLeafLayer(world, random, i, j + height - 1, k, 1);
    int leafHeight = j + height - 3;
    int minLeafHeight = j + (int)(height * 0.5F);


    while (leafHeight > minLeafHeight) {
      int m = random.nextInt(3);
      if (m == 0) {
        generateLeafLayer(world, random, i, leafHeight, k, 1);
        leafHeight -= 2; continue;
      }  if (m == 1) {
        leafHeight--;
        generateLeafLayer(world, random, i, leafHeight + 1, k, 1);
        generateLeafLayer(world, random, i, leafHeight, k, 2);
        generateLeafLayer(world, random, i, leafHeight - 1, k, 1);
        leafHeight -= 3; continue;
      }  if (m == 2) {
        leafHeight--;
        generateLeafLayer(world, random, i, leafHeight + 1, k, 2);
        generateLeafLayer(world, random, i, leafHeight, k, 3);
        generateLeafLayer(world, random, i, leafHeight - 1, k, 2);
        leafHeight -= 3;
      }
    }

    generateLeafLayer(world, random, i, leafHeight, k, 1);
    int lastDir = -1;

    for (int j1 = j; j1 < j + height; j1++) {
      func_150516_a(world, i, j1, k, this.woodBlock, this.woodMeta);
      if (j1 >= j + 3 && j1 < minLeafHeight && random.nextInt(3) == 0) {
        int dir = random.nextInt(4);
        if (dir != lastDir) {
          lastDir = dir;
          byte length = 1;

          for (int l = 1; l <= length; l++) {
            int i1 = i + Direction.field_71583_a[dir] * l;
            int k1 = k + Direction.field_71581_b[dir] * l;
            if (!isReplaceable(world, i1, j1, k1)) {
              break;
            }

            if (dir != 0 && dir != 2) {
              func_150516_a(world, i1, j1, k1, this.woodBlock, this.woodMeta);
            } else {
              func_150516_a(world, i1, j1, k1, this.woodBlock, this.woodMeta);
            }
          }
        }
      }
    }

    return true;
  }


  private void generateLeafLayer(World world, Random random, int i, int j, int k, int range) {
    for (int i1 = i - range; i1 <= i + range; i1++) {
      for (int k1 = k - range; k1 <= k + range; k1++) {
        int i2 = Math.abs(i1 - i);
        int k2 = Math.abs(k1 - k);
        if (i2 + k2 <= range) {
          Block block = world.func_147439_a(i1, j, k1);
          if (block.isReplaceable((IBlockAccess)world, i1, j, k1) || block.isLeaves((IBlockAccess)world, i1, j, k1))
            func_150516_a(world, i1, j, k1, this.leafBlock, this.leafMeta);
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\New\LOTRWorldGenPine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */