package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenCustomTallTrees
  extends WorldGenerator
{
  private final int treeId;

  public WorldGenCustomTallTrees(boolean flag, int id) {
    super(flag);
    this.treeId = id;
  }



  public boolean func_76484_a(World world, Random random, int xCoord, int yCoord, int zCoord) {
    int height = random.nextInt(5) + 6;
    if (yCoord < 1 || yCoord + height + 1 > world.func_72800_K())
    {
      return false;
    }

    boolean flag = true; int y;
    for (y = yCoord; y <= yCoord + 1 + height; y++) {

      byte byte0 = 1;
      if (y == yCoord)
      {
        byte0 = 0;
      }
      if (y >= yCoord + 1 + height - 2)
      {
        byte0 = 2;
      }
      for (int x = xCoord - byte0; x <= xCoord + byte0 && flag; x++) {

        for (int z = zCoord - byte0; z <= zCoord + byte0 && flag; z++) {

          if (y >= 0 && y + height < world.func_72800_K()) {

            Block j3 = world.func_147439_a(x, y, z);
            if (!j3.isAir((IBlockAccess)world, x, y, z) && !j3.canBeReplacedByLeaves((IBlockAccess)world, x, y, z)) {
              flag = false;
            }
          } else {

            flag = false;
          }
        }
      }
    }

    if (!flag) {
      return false;
    }
    if (!TFC_Core.isSoil(world.func_147439_a(xCoord, yCoord - 1, zCoord)) || yCoord >= world.func_72800_K() - height - 1) {
      return false;
    }
    for (y = yCoord - 3 + height; y <= yCoord + height; y++) {

      int j2 = y - yCoord + height;
      int i3 = 1 - j2 / 2;
      for (int x = xCoord - i3; x <= xCoord + i3; x++) {

        int l3 = x - xCoord;
        for (int z = zCoord - i3; z <= zCoord + i3; z++) {

          int j4 = z - zCoord;
          if ((Math.abs(l3) != i3 || Math.abs(j4) != i3 || (random.nextInt(2) != 0 && j2 != 0)) && world.func_147437_c(x, y, z))
          {
            func_150516_a(world, x, y, z, TFCBlocks.leaves, this.treeId);
          }
        }
      }
    }

    for (int l1 = 0; l1 < height; l1++)
    {
      func_150516_a(world, xCoord, yCoord + l1, zCoord, TFCBlocks.logNatural, this.treeId);
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomTallTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */