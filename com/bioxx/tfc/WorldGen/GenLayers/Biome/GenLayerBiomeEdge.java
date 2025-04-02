package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerBiomeEdge
  extends GenLayerTFC
{
  public GenLayerBiomeEdge(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int par1, int par2, int xSize, int zSize) {
    int[] inCache = this.field_75909_a.func_75904_a(par1 - 1, par2 - 1, xSize + 2, zSize + 2);
    validateIntArray(inCache, xSize + 2, zSize + 2);
    int[] outCache = IntCache.func_76445_a(xSize * zSize);





    for (int z = 0; z < zSize; z++) {

      for (int x = 0; x < xSize; x++) {

        func_75903_a((x + par1), (z + par2));
        int thisID = inCache[x + 1 + (z + 1) * (xSize + 2)];

        int var10 = inCache[x + 1 + (z + 1 - 1) * (xSize + 2)];
        int var11 = inCache[x + 1 + 1 + (z + 1) * (xSize + 2)];
        int var12 = inCache[x + 1 - 1 + (z + 1) * (xSize + 2)];
        int var13 = inCache[x + 1 + (z + 1 + 1) * (xSize + 2)];

        if (thisID == TFCBiome.HIGH_HILLS.field_76756_M) {

          if (var10 == TFCBiome.HIGH_HILLS.field_76756_M && var11 == TFCBiome.HIGH_HILLS.field_76756_M && var12 == TFCBiome.HIGH_HILLS.field_76756_M && var13 == TFCBiome.HIGH_HILLS.field_76756_M) {
            outCache[x + z * xSize] = thisID;
          } else {
            outCache[x + z * xSize] = TFCBiome.HIGH_HILLS_EDGE.field_76756_M;
          }
        } else if (thisID == TFCBiome.MOUNTAINS.field_76756_M) {

          if (var10 == TFCBiome.MOUNTAINS.field_76756_M && var11 == TFCBiome.MOUNTAINS.field_76756_M && var12 == TFCBiome.MOUNTAINS.field_76756_M && var13 == TFCBiome.MOUNTAINS.field_76756_M) {
            outCache[x + z * xSize] = thisID;
          } else {
            outCache[x + z * xSize] = TFCBiome.MOUNTAINS_EDGE.field_76756_M;
          }
        } else if (thisID == TFCBiome.SWAMPLAND.field_76756_M) {

          if (var10 == TFCBiome.SWAMPLAND.field_76756_M && var11 == TFCBiome.SWAMPLAND.field_76756_M && var12 == TFCBiome.SWAMPLAND.field_76756_M && var13 == TFCBiome.SWAMPLAND.field_76756_M) {
            outCache[x + z * xSize] = thisID;
          } else {
            outCache[x + z * xSize] = TFCBiome.PLAINS.field_76756_M;
          }
        } else if (thisID == TFCBiome.HIGH_PLAINS.field_76756_M) {

          if (var10 == TFCBiome.HIGH_PLAINS.field_76756_M && var11 == TFCBiome.HIGH_PLAINS.field_76756_M && var12 == TFCBiome.HIGH_PLAINS.field_76756_M && var13 == TFCBiome.HIGH_PLAINS.field_76756_M) {
            outCache[x + z * xSize] = thisID;
          } else {
            outCache[x + z * xSize] = TFCBiome.PLAINS.field_76756_M;
          }
        } else {

          outCache[x + z * xSize] = thisID;
        }

        validateInt(outCache, x + z * xSize);
      }
    }
    return outCache;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\Biome\GenLayerBiomeEdge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */