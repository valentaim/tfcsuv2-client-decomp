package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenCustomCedarTrees
  extends WorldGenerator
{
  private final int treeId;

  public WorldGenCustomCedarTrees(boolean flag, int id) {
    super(flag);
    this.treeId = id;
  }


  public boolean func_76484_a(World world, Random random, int xCoord, int yCoord, int zCoord) {
    int treeHeight = random.nextInt(6) + 3;
    if (yCoord < 1 || yCoord + treeHeight + 1 > world.func_72800_K()) {
      return false;
    }
    boolean flag = true;
    for (int y = yCoord; y <= yCoord + 1 + treeHeight; y++) {

      byte byte0 = 1;
      if (y == yCoord)
        byte0 = 0;
      if (y >= yCoord + 1 + treeHeight - 2) {
        byte0 = 2;
      }
      for (int x = xCoord - byte0; x <= xCoord + byte0 && flag; x++) {

        for (int z = zCoord - byte0; z <= zCoord + byte0 && flag; z++) {

          if (y >= 0 && y < world.func_72800_K()) {

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
    Block var3 = world.func_147439_a(xCoord, yCoord - 1, zCoord);
    if (!TFC_Core.isSoil(var3) || yCoord >= world.func_72800_K() - treeHeight - 1) {
      return false;
    }

    for (int treeHeightOffset = yCoord + 1; treeHeightOffset <= yCoord + treeHeight; treeHeightOffset++) {

      int treeDiameter = treeHeightOffset - yCoord + treeHeight;
      int treeRadius = 1 - treeDiameter / 2;
      for (int xPos = xCoord - 1; xPos <= xCoord + 1; xPos++) {

        int l3 = xPos - xCoord;
        for (int zPos = zCoord - 1; zPos <= zCoord + 1; zPos++) {

          int j4 = zPos - zCoord;
          if ((Math.abs(l3) != treeRadius || Math.abs(j4) != treeRadius || (random.nextInt(2) != 0 && treeDiameter != 0)) && world
            .func_147439_a(xPos, treeHeightOffset, zPos).canBeReplacedByLeaves((IBlockAccess)world, xPos, treeHeightOffset, zPos))
          {
            func_150516_a(world, xPos, treeHeightOffset, zPos, TFCBlocks.leaves, this.treeId);
          }
        }
      }
    }

    for (int l1 = 0; l1 < treeHeight; l1++)
    {
      func_150516_a(world, xCoord, yCoord + l1, zCoord, TFCBlocks.logNatural, this.treeId);
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomCedarTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */