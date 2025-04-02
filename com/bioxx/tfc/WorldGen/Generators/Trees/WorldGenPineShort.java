package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPineShort
  extends WorldGenerator {
  private final Block blockLeaf;
  private final Block blockWood;
  private final int metaLeaf;
  private final int metaWood;

  public WorldGenPineShort(boolean par1, int id) {
    super(par1);
    this.metaLeaf = id;
    this.blockLeaf = TFCBlocks.leaves;
    this.metaWood = id;
    this.blockWood = TFCBlocks.logNatural;
  }



  public boolean func_76484_a(World world, Random par2Random, int xCoord, int yCoord, int zCoord) {
    int treeHeight = par2Random.nextInt(4) + 6;
    int var7 = 1 + par2Random.nextInt(2);
    int var8 = treeHeight - var7;
    int var9 = 2 + par2Random.nextInt(2);
    boolean isValid = true;

    if (yCoord >= 1 && yCoord + treeHeight + 1 <= 256) {





      for (int y = yCoord; y <= yCoord + 1 + treeHeight && isValid; y++) {
        int var21;
        if (y - yCoord < var7) {
          var21 = 0;
        } else {
          var21 = var9;
        }
        for (int x = xCoord - var21; x <= xCoord + var21 && isValid; x++) {

          for (int z = zCoord - var21; z <= zCoord + var21 && isValid; z++) {

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
        int var21 = par2Random.nextInt(2);
        int i = 1;
        byte var22 = 0;



        for (int var15 = 0; var15 <= var8; var15++) {

          int k = yCoord + treeHeight - var15;
          for (int x = xCoord - var21; x <= xCoord + var21; x++) {

            int var18 = x - xCoord;
            for (int z = zCoord - var21; z <= zCoord + var21; z++) {

              int var20 = z - zCoord;
              block = world.func_147439_a(x, k, z);
              if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && (block == null || block
                .canBeReplacedByLeaves((IBlockAccess)world, x, k, z)))
              {
                func_150516_a(world, x, k, z, this.blockLeaf, this.metaLeaf);
              }
            }
          }

          if (var21 >= i) {

            var21 = var22;
            var22 = 1;
            i++;
            if (i > var9) {
              i = var9;
            }
          } else {

            var21++;
          }
        }

        for (int j = 0; j < treeHeight - 1; j++)
        {
          func_150516_a(world, xCoord, yCoord + j, zCoord, this.blockWood, this.metaWood);
        }
        return true;
      }


      return false;
    }




    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenPineShort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */