package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPineTall extends WorldGenerator {
  private final Block blockLeaf;
  private final Block blockWood;
  private final int metaLeaf;
  private final int metaWood;

  public WorldGenPineTall(int id) {
    this.metaLeaf = id;
    this.blockLeaf = TFCBlocks.leaves;
    this.metaWood = id;
    this.blockWood = TFCBlocks.logNatural;
  }



  public boolean func_76484_a(World world, Random par2Random, int xCoord, int yCoord, int zCoord) {
    int treeHeight = par2Random.nextInt(5) + 7;
    int var7 = treeHeight - par2Random.nextInt(2) - 3;
    int var8 = treeHeight - var7;
    int var9 = 1 + par2Random.nextInt(var8 + 1);
    boolean isValid = true;

    if (yCoord >= 1 && yCoord + treeHeight + 1 <= 256) {








      for (int y = yCoord; y <= yCoord + 1 + treeHeight && isValid; y++) {
        int var18;
        if (y - yCoord < var7) {
          var18 = 0;
        } else {
          var18 = var9;
        }
        for (int x = xCoord - var18; x <= xCoord + var18 && isValid; x++) {

          for (int z = zCoord - var18; z <= zCoord + var18 && isValid; z++) {

            if (y >= 0 && y < 256) {

              Block block1 = world.func_147439_a(x, y, z);
              if (!block1.isAir((IBlockAccess)world, x, y, z) && !block1.isLeaves((IBlockAccess)world, x, y, z) && !block1.isReplaceable((IBlockAccess)world, x, y, z)) {
                isValid = false;
              }
            } else {

              isValid = false;
            }
          }
        }
      }

      if (!isValid)
      {
        return false;
      }


      Block block = world.func_147439_a(xCoord, yCoord - 1, zCoord);

      if (TFC_Core.isSoil(block) && yCoord < world.func_72940_L() - treeHeight - 1) {

        Block soil = TFC_Core.getTypeForSoil(block);
        int soilMeta = world.func_72805_g(xCoord, yCoord - 1, zCoord);

        func_150516_a(world, xCoord, yCoord - 1, zCoord, soil, soilMeta);
        int var18 = 0;
        int x;
        for (x = yCoord + treeHeight; x >= yCoord + var7; x--) {

          for (int z = xCoord - var18; z <= xCoord + var18; z++) {

            int var15 = z - xCoord;

            for (int var16 = zCoord - var18; var16 <= zCoord + var18; var16++) {

              int var17 = var16 - zCoord;
              block = world.func_147439_a(z, x, var16);
              if ((Math.abs(var15) != var18 || Math.abs(var17) != var18 || var18 <= 0) && (block == null || block
                .canBeReplacedByLeaves((IBlockAccess)world, z, x, var16)))
              {
                func_150516_a(world, z, x, var16, this.blockLeaf, this.metaLeaf);
              }
            }
          }

          if (var18 >= 1 && x == yCoord + var7 + 1) {
            var18--;
          } else if (var18 < var9) {
            var18++;
          }
        }
        for (x = 0; x < treeHeight - 1; x++)
        {
          func_150516_a(world, xCoord, yCoord + x, zCoord, this.blockWood, this.metaWood);
        }
        return true;
      }


      return false;
    }




    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenPineTall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */