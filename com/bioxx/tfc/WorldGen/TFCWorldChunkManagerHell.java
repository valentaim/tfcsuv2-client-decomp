package com.bioxx.tfc.WorldGen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;






public class TFCWorldChunkManagerHell
  extends TFCWorldChunkManager
{
  private TFCBiome biomeGenerator;
  private float rainfall;

  public TFCWorldChunkManagerHell(TFCBiome par1, float par2, float par3, World world) {
    this.biomeGenerator = par1;

    this.rainfall = par3;
    this.worldObj = world;
    this.field_76943_g = new ArrayList<>();
    this.field_76943_g.add(TFCBiome.HELL);
  }






  public List<BiomeGenBase> func_76932_a() {
    return this.field_76943_g;
  }






  public TFCBiome getBiomeGenAt(int par1, int par2) {
    return this.biomeGenerator;
  }





  public TFCBiome[] getBiomesForGeneration(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
    TFCBiome[] arrayOfTFCBiome;
    if (par1 == null || par1.length < par4 * par5)
      arrayOfTFCBiome = new TFCBiome[par4 * par5];
    Arrays.fill((Object[])arrayOfTFCBiome, 0, par4 * par5, this.biomeGenerator);
    return arrayOfTFCBiome;
  }






  public float[] func_76936_a(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
    if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
      par1ArrayOfFloat = new float[par4 * par5];
    Arrays.fill(par1ArrayOfFloat, 0, par4 * par5, this.rainfall);
    return par1ArrayOfFloat;
  }






  public TFCBiome[] loadBlockGeneratorData(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
    TFCBiome[] arrayOfTFCBiome;
    if (par1 == null || par1.length < par4 * par5)
      arrayOfTFCBiome = new TFCBiome[par4 * par5];
    Arrays.fill((Object[])arrayOfTFCBiome, 0, par4 * par5, this.biomeGenerator);
    return arrayOfTFCBiome;
  }







  public TFCBiome[] getBiomeGenAt(BiomeGenBase[] par1, int par2, int par3, int par4, int par5, boolean par6) {
    return loadBlockGeneratorData(par1, par2, par3, par4, par5);
  }








  public ChunkPosition func_150795_a(int par1, int par2, int par3, List par4List, Random par5Random) {
    return par4List.contains(this.biomeGenerator) ? new ChunkPosition(par1 - par3 + par5Random.nextInt(par3 * 2 + 1), 0, par2 - par3 + par5Random.nextInt(par3 * 2 + 1)) : null;
  }







  public boolean func_76940_a(int par1, int par2, int par3, List par4List) {
    return par4List.contains(this.biomeGenerator);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCWorldChunkManagerHell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */