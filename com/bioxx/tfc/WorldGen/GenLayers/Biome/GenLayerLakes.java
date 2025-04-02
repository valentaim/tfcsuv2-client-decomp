package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;


public class GenLayerLakes
  extends GenLayerTFC
{
  public GenLayerLakes(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }







  public int[] func_75904_a(int par1, int par2, int par3, int par4) {
    int[] var5 = this.field_75909_a.func_75904_a(par1 - 1, par2 - 1, par3 + 2, par4 + 2);
    int[] var6 = IntCache.func_76445_a(par3 * par4);





    for (int var7 = 0; var7 < par4; var7++) {

      for (int var8 = 0; var8 < par3; var8++) {

        func_75903_a((var8 + par1), (var7 + par2));
        int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];


        int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
        int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
        int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
        int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];

        if (isOceanic(var9)) {

          if (!isOceanic(var10) && !isOceanic(var11) && !isOceanic(var12) && !isOceanic(var13)) {
            var6[var8 + var7 * par3] = TFCBiome.LAKE.field_76756_M;
          } else {
            var6[var8 + var7 * par3] = var9;
          }
        } else {

          var6[var8 + var7 * par3] = var9;
        }
      }
    }
    return var6;
  }


  private boolean isOceanic(int id) {
    return (id == TFCBiome.OCEAN.field_76756_M || id == TFCBiome.DEEP_OCEAN.field_76756_M);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\Biome\GenLayerLakes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */