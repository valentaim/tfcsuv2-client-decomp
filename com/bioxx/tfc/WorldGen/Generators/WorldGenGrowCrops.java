package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;



public class WorldGenGrowCrops
  implements IWorldGenerator
{
  private final int cropBlockId;

  public WorldGenGrowCrops(int par1) {
    this.cropBlockId = par1;
  }


  public void generate(World world, Random rand, int x, int z, int numToGen) {
    int i = x, j = 150, k = z;



    for (int c = 0; c < numToGen; c++) {

      i = x - 8 + rand.nextInt(16);
      k = z - 8 + rand.nextInt(16);
      j = world.func_72825_h(i, k);
      CropIndex crop = CropManager.getInstance().getCropFromId(this.cropBlockId);

      if (crop != null) {

        float temp = TFC_Climate.getHeightAdjustedTempSpecificDay(world, TFC_Time.getTotalDays(), i, j, k);
        int month = TFC_Time.getSeasonAdjustedMonth(k);

        if (temp > crop.minAliveTemp && month > 0 && month <= 6) {

          Block b = world.func_147439_a(i, j, k);
          if (TFCBlocks.crops.func_149718_j(world, i, j, k) && (b.isAir((IBlockAccess)world, i, j, k) || b == TFCBlocks.tallGrass))
          {
            if (world.func_147465_d(i, j, k, TFCBlocks.crops, 0, 2)) {

              TECrop te = (TECrop)world.func_147438_o(i, j, k);
              if (te != null) {

                te.cropId = this.cropBlockId;
                float gt = Math.max((crop.growthTime / TFC_Time.daysInMonth), 0.01F);
                float mg = Math.min(month / gt, 1.0F) * (0.75F + rand.nextFloat() * 0.25F);
                float growth = Math.min(crop.numGrowthStages * mg, crop.numGrowthStages);
                te.growth = growth;
              }
            }
          }
        }
      }
    }
  }

  public void generate(Random par2Random, int x, int z, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenGrowCrops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */