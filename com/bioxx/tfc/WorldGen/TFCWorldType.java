package com.bioxx.tfc.WorldGen;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;






public class TFCWorldType
  extends WorldType
{
  public static TFCWorldType defaultWorldType;
  public static TFCWorldType flatWorldType;
  private static final TFCBiome[] BIOMES_UNKNOWN = new TFCBiome[] { TFCBiome.OCEAN };


  private static final TFCBiome[] BIOMES_FLAT = new TFCBiome[] { TFCBiome.HELL };


  private static final TFCBiome[] BIOMES_DEFAULT = new TFCBiome[] { TFCBiome.OCEAN, TFCBiome.HIGH_HILLS, TFCBiome.PLAINS, TFCBiome.HIGH_PLAINS, TFCBiome.SWAMPLAND, TFCBiome.ROLLING_HILLS, TFCBiome.MOUNTAINS };










  public TFCWorldType(int i, String par2Str) {
    super(i, par2Str);
  }


  public TFCWorldType(String par2Str) {
    super(par2Str);
  }


  public TFCBiome[] getBiomesForWorldType() {
    if (this == defaultWorldType)
      return (TFCBiome[])BIOMES_DEFAULT.clone();
    if (this == flatWorldType) {
      return (TFCBiome[])BIOMES_FLAT.clone();
    }
    return (TFCBiome[])BIOMES_UNKNOWN.clone();
  }




  public WorldChunkManager getChunkManager(World world) {
    if (this == flatWorldType)
    {


      return new TFCWorldChunkManagerHell(TFCBiome.HELL, 0.5F, 0.5F, world);
    }


    return new TFCWorldChunkManager(world);
  }




  public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
    return (IChunkProvider)new TFCChunkProviderGenerate(world, world.func_72905_C(), world.func_72912_H().func_76089_r());
  }



  public int getMinimumSpawnHeight(World world) {
    return 145;
  }



  public double getHorizon(World world) {
    return 144.0D;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCWorldType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */