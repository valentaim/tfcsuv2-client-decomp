package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;

public class GenLayerPHInit
  extends GenLayerTFC
{
  public GenLayerPHInit(long par1) {
    super(par1);
  }



  public int[] func_75904_a(int par1, int par2, int maxX, int maxZ) {
    int[] outCache = new int[maxX * maxZ];

    for (int z = 0; z < maxZ; z++) {

      for (int x = 0; x < maxX; x++) {

        func_75903_a((par1 + x), (par2 + z));
        int out = GenPHLayer.MIN + func_75902_a(4);
        outCache[x + z * maxX] = out;
      }
    }

    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\PH\GenLayerPHInit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */