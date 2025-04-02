package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Drainage;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;


public class GenLayerDrainageMix
  extends GenLayerTFC
{
  public GenLayerDrainageMix(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int x, int z, int xSize, int zSize) {
    int[] var5 = this.field_75909_a.func_75904_a(x - 1, z - 1, xSize + 2, zSize + 2);
    int[] outCache = new int[xSize * zSize];







    for (int var7 = 0; var7 < zSize; var7++) {

      for (int var8 = 0; var8 < xSize; var8++) {

        func_75903_a((var8 + x), (var7 + z));
        int thisID = var5[var8 + 1 + (var7 + 1) * (xSize + 2)];
        int id0 = var5[var8 + 1 + (var7 + 1 - 1) * (xSize + 2)];
        int id1 = var5[var8 + 1 + 1 + (var7 + 1) * (xSize + 2)];
        int id2 = var5[var8 + 1 - 1 + (var7 + 1) * (xSize + 2)];
        int id3 = var5[var8 + 1 + (var7 + 1 + 1) * (xSize + 2)];
        int index = var8 + var7 * xSize;

        if ((id0 >= thisID + 2 || id1 >= thisID + 2 || id2 >= thisID + 2 || id3 >= thisID + 2) &&
          thisID + 1 < GenDrainageLayer.MAX)
          thisID++;
        if ((id0 <= thisID - 2 || id1 <= thisID - 2 || id2 <= thisID - 2 || id3 <= thisID - 2) &&
          thisID - 1 > GenDrainageLayer.MIN) {
          thisID--;
        }
        outCache[index] = thisID;
      }
    }
    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Drainage\GenLayerDrainageMix.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */