package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerDeepOcean
  extends GenLayerTFC
{
  public GenLayerDeepOcean(long seed, GenLayerTFC parent) {
    super(seed);
    this.field_75909_a = parent;
  }







  public int[] func_75904_a(int parX, int parZ, int parXSize, int parZSize) {
    int xSize = parXSize + 2;
    int zSize = parZSize + 2;

    int[] parentIDs = this.field_75909_a.func_75904_a(parX - 1, parZ - 1, xSize, zSize);
    validateIntArray(parentIDs, xSize, zSize);
    int[] outCache = IntCache.func_76445_a(parXSize * parZSize);

    for (int z = 0; z < parZSize; z++) {

      for (int x = 0; x < parXSize; x++) {

        int northID = parentIDs[x + 1 + z * xSize];
        int rightID = parentIDs[x + 2 + (z + 1) * xSize];
        int leftID = parentIDs[x + (z + 1) * xSize];
        int southID = parentIDs[x + 1 + (z + 2) * xSize];
        int thisID = parentIDs[x + 1 + (z + 1) * xSize];
        int oceanCount = 0;
        int outIndex = x + z * parXSize;

        if (northID == 0)
        {
          oceanCount++;
        }

        if (rightID == 0)
        {
          oceanCount++;
        }

        if (leftID == 0)
        {
          oceanCount++;
        }

        if (southID == 0)
        {
          oceanCount++;
        }

        if (thisID == 0 && oceanCount > 3) {

          outCache[outIndex] = TFCBiome.DEEP_OCEAN.field_76756_M;
        }
        else {

          outCache[outIndex] = thisID;
        }
      }
    }

    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\Biome\GenLayerDeepOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */