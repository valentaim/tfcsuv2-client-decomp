package com.bioxx.tfc.WorldGen.GenLayers.River;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import net.minecraft.world.gen.layer.GenLayer;


public class GenLayerRiverMixTFC
  extends GenLayerTFC
{
  private GenLayer biomePatternGeneratorChain;
  private GenLayer riverPatternGeneratorChain;
  private int[] layerBiomes;
  private int[] layerRivers;
  private int[] layerOut;
  private int xn;
  private int xp;
  private int zn;
  private int zp;

  public GenLayerRiverMixTFC(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
    super(par1);
    this.biomePatternGeneratorChain = par3GenLayer;
    this.riverPatternGeneratorChain = par4GenLayer;
  }







  public int[] func_75904_a(int x, int z, int xSize, int zSize) {
    this.layerBiomes = this.biomePatternGeneratorChain.func_75904_a(x, z, xSize, zSize);
    this.layerRivers = this.riverPatternGeneratorChain.func_75904_a(x, z, xSize, zSize);
    this.layerOut = new int[xSize * zSize];

    for (int zElement = 0; zElement < zSize; zElement++) {

      for (int xElement = 0; xElement < xSize; xElement++) {

        int index = xElement + zElement * xSize;

        if (index < this.layerBiomes.length && index < this.layerRivers.length) {
          int b = this.layerBiomes[index];
          int r = this.layerRivers[index];

          this.xn = index - 1;
          this.xp = index + 1;
          this.zn = index - zSize;
          this.zp = index + zSize;

          if (TFC_Core.isOceanicBiome(b) || TFC_Core.isMountainBiome(b)) {
            this.layerOut[index] = b;
          } else if (r > 0) {

            this.layerOut[index] = r;


            if (TFC_Core.isBeachBiome(b)) {

              this.layerOut[index] = TFCBiome.OCEAN.field_76756_M;
              if (inBounds(this.xn, this.layerOut) && this.layerOut[this.xn] == TFCBiome.RIVER.field_76756_M)
              {
                this.layerOut[this.xn] = TFCBiome.OCEAN.field_76756_M;
              }
              if (inBounds(this.zn, this.layerOut) && this.layerOut[this.zn] == TFCBiome.RIVER.field_76756_M)
              {
                this.layerOut[this.zn] = TFCBiome.OCEAN.field_76756_M;
              }
              if (inBounds(this.zp, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.zp]) && this.layerRivers[this.zp] == 0)
              {
                this.layerOut[index] = b;
              }
              if (inBounds(this.zn, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.zn]) && this.layerRivers[this.zn] == 0)
              {
                this.layerOut[index] = b;
              }
              if (inBounds(this.xn, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.xn]) && this.layerRivers[this.xn] == 0)
              {
                this.layerOut[index] = b;
              }
              if (inBounds(this.xp, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.xp]) && this.layerRivers[this.xp] == 0)
              {
                this.layerOut[index] = b;
              }
            }
          } else {

            this.layerOut[index] = b;
          }

          removeRiver(index, TFCBiome.LAKE.field_76756_M);
          removeRiver(index, TFCBiome.MOUNTAINS_EDGE.field_76756_M);

          validateInt(this.layerOut, index);
        }
      }
    }
    return (int[])this.layerOut.clone();
  }


  public void removeRiver(int index, int biomeToReplaceWith) {
    if (this.layerOut[index] == TFCBiome.RIVER.field_76756_M) {

      if (this.xn >= 0 && this.layerBiomes[this.xn] == biomeToReplaceWith)
      {
        this.layerOut[index] = biomeToReplaceWith;
      }
      if (this.zn >= 0 && this.layerBiomes[this.zn] == biomeToReplaceWith)
      {
        this.layerOut[index] = biomeToReplaceWith;
      }
      if (this.xp < this.layerBiomes.length && this.layerBiomes[this.xp] == biomeToReplaceWith)
      {
        this.layerOut[index] = biomeToReplaceWith;
      }
      if (this.zp < this.layerBiomes.length && this.layerBiomes[this.zp] == biomeToReplaceWith)
      {
        this.layerOut[index] = biomeToReplaceWith;
      }
    }
  }


  public boolean inBounds(int index, int[] array) {
    return (index < array.length && index >= 0);
  }







  public void func_75905_a(long par1) {
    this.biomePatternGeneratorChain.func_75905_a(par1);
    this.riverPatternGeneratorChain.func_75905_a(par1);
    super.func_75905_a(par1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\River\GenLayerRiverMixTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */