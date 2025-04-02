package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;




public class WorldGenCustomReed
  extends WorldGenerator
{
  public boolean func_76484_a(World par1World, Random par2Random, int par3, int par4, int par5) {
    for (int var6 = 0; var6 < 20; var6++) {

      int var7 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
      int var8 = par4;
      int var9 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);

      if (par1World.func_147437_c(var7, par4, var9) && (par1World
        .func_147439_a(var7 - 1, par4 - 1, var9).func_149688_o() == Material.field_151586_h || par1World
        .func_147439_a(var7 + 1, par4 - 1, var9).func_149688_o() == Material.field_151586_h || par1World
        .func_147439_a(var7, par4 - 1, var9 - 1).func_149688_o() == Material.field_151586_h || par1World
        .func_147439_a(var7, par4 - 1, var9 + 1).func_149688_o() == Material.field_151586_h)) {

        int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);
        for (int var11 = 0; var11 < var10; var11++) {


          if (TFCBlocks.reeds.func_149718_j(par1World, var7, var8 + var11, var9))
            par1World.func_147449_b(var7, var8 + var11, var9, TFCBlocks.reeds);
        }
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenCustomReed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */