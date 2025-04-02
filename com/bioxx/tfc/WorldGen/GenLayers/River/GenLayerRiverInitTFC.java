package com.bioxx.tfc.WorldGen.GenLayers.River;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;


public class GenLayerRiverInitTFC
  extends GenLayerTFC
{
  public GenLayerRiverInitTFC(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }






  public int[] func_75904_a(int xCoord, int zCoord, int xSize, int zSize) {
    int[] parentCache = this.field_75909_a.func_75904_a(xCoord, zCoord, xSize, zSize);
    int[] outCache = new int[xSize * zSize];

    for (int z = 0; z < zSize; z++) {

      for (int x = 0; x < xSize; x++) {

        func_75903_a((x + xCoord), (z + zCoord));
        int index = x + z * xSize;




        int id = parentCache[index];
        outCache[index] = (!TFC_Core.isOceanicBiome(id) && !TFC_Core.isMountainBiome(id)) ? 1 : 0;
      }
    }
    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\River\GenLayerRiverInitTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */