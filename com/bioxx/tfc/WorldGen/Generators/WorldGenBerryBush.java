package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Blocks.Flora.BlockBerryBush;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.TileEntities.TEBerryBush;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;


public class WorldGenBerryBush
  extends WorldGenerator
{
  private int meta;
  private int clusterSize;
  private int bushHeight;
  private int spawnRadius;
  private Block underBlock = Blocks.field_150350_a;


  public WorldGenBerryBush(boolean flag, int m, int cluster, int height, int radius) {
    super(flag);
    this.meta = m;
    this.clusterSize = cluster;
    this.bushHeight = height;
    this.spawnRadius = radius;
  }


  public WorldGenBerryBush(boolean flag, int m, int cluster, int height, int radius, Block under) {
    this(flag, m, cluster, height, radius);
    this.underBlock = under;
  }



  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    float temp = TFC_Climate.getBioTemperatureHeight(world, i, j, k);
    float rain = TFC_Climate.getRainfall(world, i, j, k);
    float evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(i, k)).floatdata1;

    FloraIndex index = FloraManager.getInstance().findMatchingIndex(((BlockBerryBush)TFCBlocks.berryBush).getType(this.meta));
    if (world.func_147437_c(i, j, k) && j < 250 && temp > index.minBioTemp && temp < index.maxBioTemp && rain >= index.minRain && rain <= index.maxRain && evt >= index.minEVT && evt <= index.maxEVT) {


      int cluster = this.clusterSize + random.nextInt(this.clusterSize) - this.clusterSize / 2;
      short count = 0; short realCount;
      for (realCount = 0; count < cluster && realCount < this.spawnRadius * this.spawnRadius; realCount = (short)(realCount + 1)) {

        int x = random.nextInt(this.spawnRadius * 2);
        int z = random.nextInt(this.spawnRadius * 2);
        if (createBush(world, random, i - this.spawnRadius + x, world.func_72976_f(i - this.spawnRadius + x, k - this.spawnRadius + z), k - this.spawnRadius + z, index)) {
          count = (short)(count + 1);
        }
      }
    }
    return true;
  }



  public boolean createBush(World world, Random random, int i, int j, int k, FloraIndex fi) {
    Block id = world.func_147439_a(i, j - 1, k);




    if ((world.func_72937_j(i, j, k) || world.func_72957_l(i, j, k) > 8) && ((TFC_Core.isSoil(id) && this.underBlock == Blocks.field_150350_a) || id == this.underBlock || (TFC_Core.isGrass(this.underBlock) && id == TFC_Core.getTypeForSoil(this.underBlock)))) {

      for (int h = 0; h < this.bushHeight && random.nextBoolean(); h++) {

        world.func_147465_d(i, j + h, k, TFCBlocks.berryBush, this.meta, 2);
        if (TFC_Time.getSeasonAdjustedMonth(k) > fi.harvestStart && TFC_Time.getSeasonAdjustedMonth(k) < fi.harvestFinish + fi.fruitHangTime) {



          TileEntity te = world.func_147438_o(i, j + h, k);
          if (te instanceof TEBerryBush) ((TEBerryBush)te).hasFruit = true;
        }
      }  return true;
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenBerryBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */