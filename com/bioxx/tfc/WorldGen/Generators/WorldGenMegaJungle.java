package com.bioxx.tfc.WorldGen.Generators;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;

public class WorldGenMegaJungle
  extends WorldGenHugeTrees
{
  public WorldGenMegaJungle(boolean doBlockNotify, int baseHeight, int extraHeight, int woodMeta, int leafMeta) {
    super(doBlockNotify, baseHeight, extraHeight, woodMeta, leafMeta);
  }



  public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
    int l = func_150533_a(rand);

    if (!func_150537_a(world, rand, x, y, z, l))
    {
      return false;
    }


    callGenLeaves(world, x, z, y + l, 2, rand);
    int i1;
    for (i1 = y + l - 2 - rand.nextInt(4); i1 > y + l / 2; i1 -= 2 + rand.nextInt(4)) {

      float f = rand.nextFloat() * 3.1415927F * 2.0F;
      int j1 = x + (int)(0.5F + MathHelper.func_76134_b(f) * 4.0F);
      int k1 = z + (int)(0.5F + MathHelper.func_76126_a(f) * 4.0F);

      int l1;
      for (l1 = 0; l1 < 5; l1++) {

        j1 = x + (int)(1.5F + MathHelper.func_76134_b(f) * l1);
        k1 = z + (int)(1.5F + MathHelper.func_76126_a(f) * l1);
        func_150516_a(world, j1, i1 - 3 + l1 / 2, k1, Blocks.field_150364_r, this.field_76520_b);
      }

      l1 = 1 + rand.nextInt(2);
      int i2 = i1;

      for (int j2 = i1 - l1; j2 <= i2; j2++) {

        int k2 = j2 - i2;
        func_150534_b(world, j1, j2, k1, 1 - k2, rand);
      }
    }

    for (int l2 = 0; l2 < l; l2++) {

      Block block = world.func_147439_a(x, y + l2, z);

      if (block.isAir((IBlockAccess)world, x, y + l2, z) || block.isLeaves((IBlockAccess)world, x, y + l2, z)) {

        func_150516_a(world, x, y + l2, z, Blocks.field_150364_r, this.field_76520_b);

        if (l2 > 0) {

          if (rand.nextInt(3) > 0 && world.func_147437_c(x - 1, y + l2, z))
          {
            func_150516_a(world, x - 1, y + l2, z, Blocks.field_150395_bd, 8);
          }

          if (rand.nextInt(3) > 0 && world.func_147437_c(x, y + l2, z - 1))
          {
            func_150516_a(world, x, y + l2, z - 1, Blocks.field_150395_bd, 1);
          }
        }
      }

      if (l2 < l - 1) {

        block = world.func_147439_a(x + 1, y + l2, z);

        if (block.isAir((IBlockAccess)world, x + 1, y + l2, z) || block.isLeaves((IBlockAccess)world, x + 1, y + l2, z)) {

          func_150516_a(world, x + 1, y + l2, z, Blocks.field_150364_r, this.field_76520_b);

          if (l2 > 0) {

            if (rand.nextInt(3) > 0 && world.func_147437_c(x + 2, y + l2, z))
            {
              func_150516_a(world, x + 2, y + l2, z, Blocks.field_150395_bd, 2);
            }

            if (rand.nextInt(3) > 0 && world.func_147437_c(x + 1, y + l2, z - 1))
            {
              func_150516_a(world, x + 1, y + l2, z - 1, Blocks.field_150395_bd, 1);
            }
          }
        }

        block = world.func_147439_a(x + 1, y + l2, z + 1);

        if (block.isAir((IBlockAccess)world, x + 1, y + l2, z + 1) || block.isLeaves((IBlockAccess)world, x + 1, y + l2, z + 1)) {

          func_150516_a(world, x + 1, y + l2, z + 1, Blocks.field_150364_r, this.field_76520_b);

          if (l2 > 0) {

            if (rand.nextInt(3) > 0 && world.func_147437_c(x + 2, y + l2, z + 1))
            {
              func_150516_a(world, x + 2, y + l2, z + 1, Blocks.field_150395_bd, 2);
            }

            if (rand.nextInt(3) > 0 && world.func_147437_c(x + 1, y + l2, z + 2))
            {
              func_150516_a(world, x + 1, y + l2, z + 2, Blocks.field_150395_bd, 4);
            }
          }
        }

        block = world.func_147439_a(x, y + l2, z + 1);

        if (block.isAir((IBlockAccess)world, x, y + l2, z + 1) || block.isLeaves((IBlockAccess)world, x, y + l2, z + 1)) {

          func_150516_a(world, x, y + l2, z + 1, Blocks.field_150364_r, this.field_76520_b);

          if (l2 > 0) {

            if (rand.nextInt(3) > 0 && world.func_147437_c(x - 1, y + l2, z + 1))
            {
              func_150516_a(world, x - 1, y + l2, z + 1, Blocks.field_150395_bd, 8);
            }

            if (rand.nextInt(3) > 0 && world.func_147437_c(x, y + l2, z + 2))
            {
              func_150516_a(world, x, y + l2, z + 2, Blocks.field_150395_bd, 4);
            }
          }
        }
      }
    }

    return true;
  }



  private void callGenLeaves(World world, int x, int y, int z, int offset, Random rand) {
    byte b = 2;

    for (int i = z - b; i <= z; i++) {

      int j = i - z;
      func_150535_a(world, x, i, y, offset + 1 - j, rand);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenMegaJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */