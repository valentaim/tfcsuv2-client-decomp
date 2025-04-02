package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEWaterPlant;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;




public class WorldGenWaterPlants
  extends WorldGenerator
{
  private final Block plantBlock;

  public WorldGenWaterPlants(Block par1) {
    this.plantBlock = par1;
  }



  public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
    if (TFC_Core.isWater(world.func_147439_a(x, y, z)) && world.func_147437_c(x, y + 1, z)) {


      int depthCounter = 1;

      boolean isTooDeep = false;
      boolean isFreshWater = TFC_Core.isFreshWater(world.func_147439_a(x, y, z));
      int maxDepth = !isFreshWater ? 10 : 4;


      while (y > 0 && TFC_Core.isWater(world.func_147439_a(x, --y, z)) && !isTooDeep) {

        depthCounter++;
        if (depthCounter > maxDepth)
        {

          isTooDeep = true;
        }
      }
      if (!isTooDeep && depthCounter > 0) {

        int meta = world.func_72805_g(x, y, z);
        Block oldBlock = world.func_147439_a(x, y, z);
        if (TFC_Core.isSoilOrGravel(oldBlock) || TFC_Core.isSand(oldBlock)) {

          world.func_147465_d(x, y, z, this.plantBlock, meta, 2);
          TileEntity te = world.func_147438_o(x, y, z);
          if (te instanceof TEWaterPlant) {
            ((TEWaterPlant)te).setBlock(oldBlock);
          }
        }
      }
    }
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenWaterPlants.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */