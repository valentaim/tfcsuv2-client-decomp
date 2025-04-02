package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.WorldGen.TFCWorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerBiomeTFC
  extends GenLayerTFC
{
  private TFCBiome[] allowedBiomes = new TFCBiome[] { TFCBiome.OCEAN, TFCBiome.HIGH_HILLS, TFCBiome.PLAINS, TFCBiome.HIGH_PLAINS, TFCBiome.SWAMPLAND, TFCBiome.ROLLING_HILLS, TFCBiome.MOUNTAINS };










  public GenLayerBiomeTFC(long par1, GenLayer par3GenLayer, TFCWorldType par4) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int par1, int par2, int par3, int par4) {
    int[] var5 = this.field_75909_a.func_75904_a(par1, par2, par3, par4);
    validateIntArray(var5, par3, par4);
    int[] var6 = IntCache.func_76445_a(par3 * par4);

    for (int var7 = 0; var7 < par4; var7++) {

      for (int var8 = 0; var8 < par3; var8++) {

        func_75903_a((var8 + par1), (var7 + par2));
        int id = var5[var8 + var7 * par3];
        if (TFC_Core.isOceanicBiome(id)) {
          var6[var8 + var7 * par3] = id;
        } else {
          var6[var8 + var7 * par3] = (this.allowedBiomes[func_75902_a(this.allowedBiomes.length)]).field_76756_M;
        }
        validateInt(var6, var8 + var7 * par3);
      }
    }
    return var6;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\Biome\GenLayerBiomeTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */