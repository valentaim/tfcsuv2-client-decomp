package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenCustomWillowTrees
  extends WorldGenerator
{
  private final int treeId;

  public WorldGenCustomWillowTrees(boolean flag, int id) {
    super(flag);
    this.treeId = id;
  }


  private void addBranch(int x, int y, int z, int x1, int z1, Random random, World world) {
    if (random.nextInt(3) == 0) {

      int xCoord = (random.nextInt(8) - 4) * z1;
      xCoord += (random.nextInt(2) + 3) * x1;
      int zCoord = (random.nextInt(8) - 4) * x1;
      zCoord += (random.nextInt(2) + 3) * z1;
      int yCoord = random.nextInt(3) + random.nextInt(1);
      int length = (int)Math.sqrt(Math.pow(xCoord, 2.0D) + Math.pow(yCoord, 2.0D) + Math.pow(zCoord, 2.0D));
      for (int a = 0; a < length; a++) {

        if (world.func_147437_c(xCoord * a / length + x, yCoord * a / length + y, zCoord * a / length + z))
          world.func_147465_d(xCoord * a / length + x, yCoord * a / length + y, zCoord * a / length + z, TFCBlocks.logNatural, this.treeId, 2);
      }
      createLeafGroup(xCoord + x, yCoord + y, zCoord + z, random, world);
    }
  }


  private void createLeafGroup(int x, int y, int z, Random random, World world) {
    for (int y1 = 0; y1 < 2; y1++) {

      for (int x1 = x - 2 + y1; x1 < x + 2 - y1; x1++) {

        for (int z1 = z - 2 + y1; z1 < z + 2 - y1; z1++) {

          if (world.func_147439_a(x1, y1 + y, z1).canBeReplacedByLeaves((IBlockAccess)world, x1, y1 + y, z1)) {

            world.func_147465_d(x1, y1 + y, z1, TFCBlocks.leaves, this.treeId, 2);
            for (int a = 0; a < random.nextInt(2) + 2; a++) {

              Block b = world.func_147439_a(x1, y1 - 1 - a + y, z1);
              if (b.canBeReplacedByLeaves((IBlockAccess)world, x1, y1 - 1 - a + y, z1)) {
                world.func_147465_d(x1, y1 - 1 - a + y, z1, TFCBlocks.leaves, this.treeId, 2);
              }
            }
          }
        }
      }
    }
  }


  public boolean func_76484_a(World world, Random random, int xCoord, int yCoord, int zCoord) {
    int height = random.nextInt(2) + 3;
    while (world.func_147439_a(xCoord, yCoord - 1, zCoord).func_149688_o() == Material.field_151586_h)
    {
      yCoord--;
    }
    if (yCoord < 1 || yCoord + height + 5 > world.func_72800_K()) {
      return false;
    }
    boolean flag = true;
    for (int i1 = yCoord; i1 <= yCoord + 1 + height; i1++) {

      byte byte0 = 1;
      if (i1 == yCoord)
        byte0 = 0;
      if (i1 >= yCoord + 1 + height - 2) {
        byte0 = 3;
      }
      for (int j2 = xCoord - byte0; j2 <= xCoord + byte0 && flag; j2++) {

        for (int j3 = zCoord - byte0; j3 <= zCoord + byte0 && flag; j3++) {

          if (i1 >= 0 && i1 < world.func_72800_K()) {

            Block i4 = world.func_147439_a(j2, i1, j3);
            if (!i4.isAir((IBlockAccess)world, j2, i1, j3) && i4 != TFCBlocks.leaves && i4 != TFCBlocks.leaves2)
            {
              if (i4 == TFCBlocks.freshWaterStationary) {

                if (i1 > yCoord) {
                  flag = false;
                }
              } else {

                flag = false;
              }
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

    int y = height + yCoord;
    int x = xCoord;
    int z = zCoord;

    for (int n = 0; n < 2; n++) {

      int x1 = 1 - n * (random.nextInt(1) - random.nextInt(1));
      int z1 = 0 - n * (random.nextInt(1) - random.nextInt(1));
      int x2 = (random.nextInt(6) - 3) * z1;
      x2 += (random.nextInt(1) + 2) * x1;
      int z2 = (random.nextInt(6) - 3) * x1;
      z2 += (random.nextInt(1) + 2) * z1;
      int y2 = random.nextInt(2) + 3;
      int length = (int)Math.sqrt(Math.pow(x2, 2.0D) + Math.pow(y2, 2.0D) + Math.pow(z2, 2.0D));
      for (int a = 0; a < length; a++) {

        if (world.func_147437_c(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z))
          world.func_147465_d(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z, TFCBlocks.logNatural, this.treeId, 2);
        addBranch(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z, -1, 0, random, world);
        addBranch(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z, 0, -1, random, world);
        addBranch(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z, 1, 0, random, world);
        addBranch(x2 * a / length + x, y2 * a / length + y, z2 * a / length + z, 0, 1, random, world);
      }
      createLeafGroup(x2 + x, y2 + y, z2 + z, random, world);
    }
    createLeafGroup(xCoord, yCoord + height + 1, zCoord, random, world);
    for (int l1 = 0; l1 < height; l1++)
    {
      world.func_147465_d(xCoord, yCoord + l1, zCoord, TFCBlocks.logNatural, this.treeId, 2);
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomWillowTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */