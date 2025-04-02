package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.DataLayer;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;






public class WorldGenCustomSand
  extends WorldGenerator
{
  private Block sandBlock;
  private int radius;

  public WorldGenCustomSand(int par1, Block par2) {
    this.sandBlock = par2;
    this.radius = par1;
  }



  public boolean func_76484_a(World world, Random par2Random, int x, int y, int z) {
    if (world.func_147439_a(x, y, z).func_149688_o() != Material.field_151586_h)
    {
      return false;
    }


    DataLayer dl = TFC_Climate.getRockLayer(world, x, y, z, 0);
    this.sandBlock = TFC_Core.getTypeForSand(dl.data1);
    int var6 = par2Random.nextInt(this.radius - 2) + 2;
    byte var7 = 2;

    for (int var8 = x - var6; var8 <= x + var6; var8++) {

      for (int var9 = z - var6; var9 <= z + var6; var9++) {

        int var10 = var8 - x;
        int var11 = var9 - z;
        if (var10 * var10 + var11 * var11 <= var6 * var6)
        {
          for (int var12 = y - var7; var12 <= y + var7; var12++) {

            Block var13 = world.func_147439_a(var8, var12, var9);
            boolean notCorrectSoil = (!TFC_Core.isSoil(var13) && !TFC_Core.isSand(var13));
            if (!notCorrectSoil)
              world.func_147465_d(var8, var12, var9, this.sandBlock, dl.data2, 2);
          }
        }
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCustomSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */