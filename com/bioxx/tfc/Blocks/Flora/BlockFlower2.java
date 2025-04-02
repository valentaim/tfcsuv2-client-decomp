package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Core.TFC_Climate;
import net.minecraft.world.World;










public class BlockFlower2
  extends BlockFlower
{
  public boolean canGrowConditions(World world, int x, int y, int z, int flowerMeta) {
    float rain = TFC_Climate.getRainfall(world, x, 144, z);
    float bioTemperature = TFC_Climate.getBioTemperatureHeight(world, x, y, z);
    return (bioTemperature > 5.0F && rain > 250.0F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFlower2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */