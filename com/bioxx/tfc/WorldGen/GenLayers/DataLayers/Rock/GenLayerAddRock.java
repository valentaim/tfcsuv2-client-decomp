package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rock;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;


public class GenLayerAddRock
  extends GenLayerTFC
{
  public GenLayerAddRock(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int par1, int par2, int xMax, int zMax) {
    int var5 = par1 - 1;
    int var6 = par2 - 1;
    int var7 = xMax + 2;
    int var8 = zMax + 2;
    int[] var9 = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
    int[] outCache = new int[xMax * zMax];

    for (int z = 0; z < zMax; z++) {

      for (int x = 0; x < xMax; x++) {

        int var13 = var9[x + 0 + (z + 0) * var7];
        int var14 = var9[x + 2 + (z + 0) * var7];
        int var15 = var9[x + 0 + (z + 2) * var7];
        int var16 = var9[x + 2 + (z + 2) * var7];
        int thisID = var9[x + 1 + (z + 1) * var7];
        func_75903_a((x + par1), (z + par2));


        int var18 = 1;
        int outID = thisID;

        if (func_75902_a(var18++) == 0) {
          outID = var13;
        }
        if (func_75902_a(var18++) == 0) {
          outID = var14;
        }
        if (func_75902_a(var18++) == 0) {
          outID = var15;
        }
        if (func_75902_a(var18++) == 0) {
          outID = var16;
        }
        if (func_75902_a(2) == 0) {
          outCache[x + z * xMax] = outID;
        } else {
          outCache[x + z * xMax] = thisID;
        }
      }
    }
    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Rock\GenLayerAddRock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */