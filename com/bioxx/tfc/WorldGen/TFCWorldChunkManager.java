package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.gen.layer.IntCache;








public class TFCWorldChunkManager
  extends WorldChunkManager
{
  protected World worldObj;
  protected GenLayerTFC field_76944_d;
  protected GenLayerTFC field_76945_e;
  protected BiomeCache field_76942_f;
  protected List<BiomeGenBase> field_76943_g;
  public long seed;

  public TFCWorldChunkManager() {
    this.field_76942_f = new BiomeCache(this);
    this.field_76943_g = new ArrayList<>();

    this.field_76943_g.add(BiomeGenBase.field_76767_f);
    this.field_76943_g.add(TFCBiome.PLAINS);
    this.field_76943_g.add(TFCBiome.ROLLING_HILLS);
    this.field_76943_g.add(TFCBiome.SWAMPLAND);
    this.field_76943_g.add(TFCBiome.MOUNTAINS);
    this.field_76943_g.add(TFCBiome.HIGH_PLAINS);
  }


  public TFCWorldChunkManager(World world) {
    this(world.func_72905_C(), world.func_72912_H().func_76067_t());
    this.worldObj = world;
  }


  public TFCWorldChunkManager(long genSeed, WorldType worldtype) {
    this(); GenLayerTFC[] var4;
    this.seed = genSeed;



    if (worldtype == TFCWorldType.flatWorldType) {
      var4 = GenLayerTFC.initialize(genSeed, TFCWorldType.flatWorldType);
    } else {
      var4 = GenLayerTFC.initialize(genSeed, TFCWorldType.defaultWorldType);
    }
    this.field_76944_d = var4[0];
    this.field_76945_e = var4[1];
  }






  public List<BiomeGenBase> func_76932_a() {
    return this.field_76943_g;
  }






  public float[] func_76936_a(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
    if (TFC_Climate.getCacheManager(this.worldObj) != null)
      return TFC_Climate.getCacheManager(this.worldObj).getRainfall(par1ArrayOfFloat, par2, par3, par4, par5);
    return par1ArrayOfFloat;
  }






  public TFCBiome[] getBiomesForGeneration(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
    IntCache.func_76446_a();

    TFCBiome[] biome = (TFCBiome[])par1;
    if (biome == null || biome.length < par4 * par5) {
      biome = new TFCBiome[par4 * par5];
    }
    int[] var6 = this.field_76944_d.func_75904_a(par2, par3, par4, par5);
    for (int var7 = 0; var7 < par4 * par5; var7++) {

      int index = Math.max(var6[var7], 0);
      biome[var7] = TFCBiome.getBiome(index);
    }

    return biome;
  }







  public BiomeGenBase[] func_76933_b(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
    return func_76931_a(par1, par2, par3, par4, par5, true);
  }






  public BiomeGenBase[] func_76931_a(BiomeGenBase[] biome, int par2, int par3, int par4, int par5, boolean par6) {
    TFCBiome[] arrayOfTFCBiome;
    IntCache.func_76446_a();

    if (biome == null || biome.length < par4 * par5) {
      arrayOfTFCBiome = new TFCBiome[par4 * par5];
    }
    if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xF) == 0 && (par3 & 0xF) == 0) {

      BiomeGenBase[] var9 = this.field_76942_f.func_76839_e(par2, par3);
      System.arraycopy(var9, 0, arrayOfTFCBiome, 0, par4 * par5);
      return (BiomeGenBase[])arrayOfTFCBiome;
    }


    int[] var7 = this.field_76945_e.func_75904_a(par2, par3, par4, par5);
    for (int zCoord = 0; zCoord < par4; zCoord++) {

      for (int xCoord = 0; xCoord < par5; xCoord++) {

        int id = (var7[zCoord * par4 + xCoord] != -1) ? var7[zCoord * par4 + xCoord] : 0;
        arrayOfTFCBiome[zCoord * par4 + xCoord] = TFCBiome.getBiome(id);
      }
    }
    return (BiomeGenBase[])arrayOfTFCBiome;
  }








  public boolean func_76940_a(int par1, int par2, int par3, List par4List) {
    IntCache.func_76446_a();
    int var5 = par1 - par3 >> 2;
    int var6 = par2 - par3 >> 2;
    int var7 = par1 + par3 >> 2;
    int var8 = par2 + par3 >> 2;
    int var9 = var7 - var5 + 1;
    int var10 = var8 - var6 + 1;
    int[] var11 = this.field_76944_d.func_75904_a(var5, var6, var9, var10);

    for (int var12 = 0; var12 < var9 * var10; var12++) {

      TFCBiome var13 = TFCBiome.getBiomeGenArray()[var11[var12]];
      if (!par4List.contains(var13))
        return false;
    }
    return true;
  }








  public ChunkPosition func_150795_a(int xCoord, int zCoord, int radius, List biomeList, Random rand) {
    IntCache.func_76446_a();
    int l = xCoord - radius >> 2;
    int i1 = zCoord - radius >> 2;
    int j1 = xCoord + radius >> 2;
    int k1 = zCoord + radius >> 2;
    int l1 = j1 - l + 1;
    int i2 = k1 - i1 + 1;
    int[] aint = this.field_76944_d.func_75904_a(l, i1, l1, i2);
    ChunkPosition chunkposition = null;
    int j2 = 0;

    for (int k2 = 0; k2 < l1 * i2; k2++) {

      int l2 = l + k2 % l1 << 2;
      int i3 = i1 + k2 / l1 << 2;
      TFCBiome biomegenbase = TFCBiome.getBiome(aint[k2]);

      if (biomeList.contains(biomegenbase) && (chunkposition == null || rand.nextInt(j2 + 1) == 0)) {

        chunkposition = new ChunkPosition(l2, 0, i3);
        j2++;
      }
    }

    return chunkposition;
  }



  @SideOnly(Side.CLIENT)
  public float func_76939_a(float t, int y) {
    int x = (int)Math.floor((Minecraft.func_71410_x()).field_71439_g.field_70165_t);
    int z = (int)Math.floor((Minecraft.func_71410_x()).field_71439_g.field_70161_v);
    return TFC_Climate.getHeightAdjustedTemp((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
  }



  public void func_76938_b() {
    this.field_76942_f.func_76838_a();
    WorldCacheManager wcm = TFC_Climate.getCacheManager(this.worldObj);
    if (wcm != null) wcm.cleanupCache();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCWorldChunkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */