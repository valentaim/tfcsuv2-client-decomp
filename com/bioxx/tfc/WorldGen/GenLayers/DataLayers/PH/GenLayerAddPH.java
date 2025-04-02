package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import net.minecraft.world.gen.layer.GenLayer;


public class GenLayerAddPH
  extends GenLayerTFC
{
  public GenLayerAddPH(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int xCoord, int zCoord, int xSize, int zSize) {
    int var5 = xCoord - 1;
    int var6 = zCoord - 1;
    int var7 = xSize + 2;
    int var8 = zSize + 2;
    int[] inCache = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
    int[] outCache = new int[xSize * zSize];

    for (int var11 = 0; var11 < zSize; var11++) {

      for (int var12 = 0; var12 < xSize; var12++) {

        int id0 = inCache[var12 + 0 + (var11 + 0) * var7];
        int id1 = inCache[var12 + 2 + (var11 + 0) * var7];
        int id2 = inCache[var12 + 0 + (var11 + 2) * var7];
        int id3 = inCache[var12 + 2 + (var11 + 2) * var7];
        int thisID = inCache[var12 + 1 + (var11 + 1) * var7];
        func_75903_a((var12 + xCoord), (var11 + zCoord));

        if (id0 > thisID || id1 > thisID || id2 > thisID || id3 > thisID) {

          int count = 1;
          int outID = thisID;

          if (id0 < GenPHLayer.MAX && func_75902_a(count++) == 0) {
            outID = id0 + 1;
          }
          if (id1 < GenPHLayer.MAX && func_75902_a(count++) == 0) {
            outID = id1 + 1;
          }
          if (id2 < GenPHLayer.MAX && func_75902_a(count++) == 0) {
            outID = id2 + 1;
          }
          if (id3 < GenPHLayer.MAX && func_75902_a(count++) == 0) {
            outID = id3 + 1;
          }
          if (func_75902_a(3) == 0 && outID <= GenPHLayer.MAX) {
            outCache[var12 + var11 * xSize] = outID;
          } else {
            outCache[var12 + var11 * xSize] = thisID;
          }
        } else if (id0 < thisID || id1 < thisID || id2 < thisID || id3 < thisID) {

          int count = 1;
          int outID = thisID;

          if (id0 > GenPHLayer.MIN && func_75902_a(count++) == 0) {
            outID = id0 - 1;
          }
          if (id1 > GenPHLayer.MIN && func_75902_a(count++) == 0) {
            outID = id1 - 1;
          }
          if (id2 > GenPHLayer.MIN && func_75902_a(count++) == 0) {
            outID = id2 - 1;
          }
          if (id3 > GenPHLayer.MIN && func_75902_a(count++) == 0) {
            outID = id3 - 1;
          }
          if (func_75902_a(3) == 0 && outID >= GenPHLayer.MIN) {
            outCache[var12 + var11 * xSize] = outID;
          } else {
            outCache[var12 + var11 * xSize] = thisID;
          }
        } else {

          outCache[var12 + var11 * xSize] = thisID;
        }
      }
    }
    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\PH\GenLayerAddPH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */