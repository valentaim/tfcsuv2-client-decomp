package com.bioxx.tfc.WorldGen.GenLayers.Biome;

import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRemoveOcean
  extends GenLayerTFC
{
  private final int chance;
  private final boolean checkType;

  public GenLayerRemoveOcean(long par1, GenLayerTFC parent, int chance, boolean ct) {
    super(par1);
    this.field_75909_a = parent;
    this.chance = chance;
    this.checkType = ct;
  }







  public int[] func_75904_a(int par1, int par2, int par3, int par4) {
    int i1 = par1 - 1;
    int j1 = par2 - 1;
    int k1 = par3 + 2;
    int l1 = par4 + 2;
    int[] biomes = this.field_75909_a.func_75904_a(i1, j1, k1, l1);
    int[] out = IntCache.func_76445_a(par3 * par4);

    for (int i2 = 0; i2 < par4; i2++) {

      for (int j2 = 0; j2 < par3; j2++) {

        int k2 = biomes[j2 + 1 + (i2 + 1 - 1) * (par3 + 2)];
        int l2 = biomes[j2 + 1 + 1 + (i2 + 1) * (par3 + 2)];
        int i3 = biomes[j2 + 1 - 1 + (i2 + 1) * (par3 + 2)];
        int j3 = biomes[j2 + 1 + (i2 + 1 + 1) * (par3 + 2)];
        int biome = biomes[j2 + 1 + (i2 + 1) * k1];
        out[j2 + i2 * par3] = biome;
        func_75903_a((j2 + par1), (i2 + par2));

        if (this.checkType && biome == 0 && k2 == 0 && l2 == 0 && i3 == 0 && j3 == 0 && func_75902_a(this.chance) == 0) {

          out[j2 + i2 * par3] = 1;
        }
        else if (!this.checkType && biome == 0 && k2 != 0 && l2 != 0 && i3 != 0 && j3 != 0) {

          out[j2 + i2 * par3] = 1;
        }
      }
    }

    return out;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\Biome\GenLayerRemoveOcean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */