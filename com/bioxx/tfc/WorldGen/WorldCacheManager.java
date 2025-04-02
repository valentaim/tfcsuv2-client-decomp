package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.WorldGen.Data.DataCache;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Drainage.GenDrainageLayer;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.EVT.GenEVTLayer;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH.GenPHLayer;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rain.GenRainLayerTFC;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rock.GenRockLayer;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Stability.GenStabilityLayer;
import com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Tree.GenTreeLayer;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.IntCache;




















public class WorldCacheManager
{
  protected GenLayerTFC[] rocksIndexLayer;
  protected DataCache[] rockCache;
  protected GenLayerTFC[] treesIndexLayer;
  protected DataCache[] treeCache;
  protected GenLayerTFC evtIndexLayer;
  protected DataCache evtCache;
  protected GenLayerTFC rainfallIndexLayer;
  protected DataCache rainfallCache;
  protected GenLayerTFC stabilityIndexLayer;
  protected DataCache stabilityCache;
  protected GenLayerTFC phIndexLayer;
  protected DataCache phCache;
  protected GenLayerTFC drainageIndexLayer;
  protected DataCache drainageCache;
  protected Map<String, Float> worldTempCache;
  public long seed;
  public static final DataLayer[] ROCK_LAYER_1 = new DataLayer[] { DataLayer.SHALE, DataLayer.CLAYSTONE, DataLayer.ROCKSALT, DataLayer.LIMESTONE, DataLayer.CONGLOMERATE, DataLayer.DOLOMITE, DataLayer.CHERT, DataLayer.CHALK, DataLayer.RHYOLITE, DataLayer.BASALT, DataLayer.ANDESITE, DataLayer.DACITE, DataLayer.QUARTZITE, DataLayer.SLATE, DataLayer.PHYLLITE, DataLayer.SCHIST, DataLayer.GNEISS, DataLayer.MARBLE, DataLayer.GRANITE, DataLayer.DIORITE, DataLayer.GABBRO };





  public static final DataLayer[] ROCK_LAYER_2 = new DataLayer[] { DataLayer.RHYOLITE, DataLayer.BASALT, DataLayer.ANDESITE, DataLayer.DACITE, DataLayer.QUARTZITE, DataLayer.SLATE, DataLayer.PHYLLITE, DataLayer.SCHIST, DataLayer.GNEISS, DataLayer.MARBLE, DataLayer.GRANITE, DataLayer.DIORITE, DataLayer.GABBRO };




  public static final DataLayer[] ROCK_LAYER_3 = new DataLayer[] { DataLayer.RHYOLITE, DataLayer.BASALT, DataLayer.ANDESITE, DataLayer.DACITE, DataLayer.GRANITE, DataLayer.DIORITE, DataLayer.GABBRO };



  public static final DataLayer[] TREE_ARRAY = new DataLayer[] { DataLayer.ASH, DataLayer.ASPEN, DataLayer.BIRCH, DataLayer.CHESTNUT, DataLayer.DOUGLASFIR, DataLayer.HICKORY, DataLayer.MAPLE, DataLayer.OAK, DataLayer.PINE, DataLayer.REDWOOD, DataLayer.PINE, DataLayer.SPRUCE, DataLayer.SYCAMORE, DataLayer.WHITECEDAR, DataLayer.WHITEELM, DataLayer.WILLOW, DataLayer.NO_TREE };




  private WorldCacheManager() {
    this.rockCache = new DataCache[3];
    this.treeCache = new DataCache[3];
    this.evtCache = new DataCache(this, 0);
    this.rainfallCache = new DataCache(this, 0);
    this.rockCache[0] = new DataCache(this, 0);
    this.rockCache[1] = new DataCache(this, 1);
    this.rockCache[2] = new DataCache(this, 2);
    this.treeCache[0] = new DataCache(this, 0);
    this.treeCache[1] = new DataCache(this, 1);
    this.treeCache[2] = new DataCache(this, 2);
    this.stabilityCache = new DataCache(this, 0);
    this.phCache = new DataCache(this, 0);
    this.drainageCache = new DataCache(this, 0);
    this.worldTempCache = new LinkedHashMap<>();
  }


  public WorldCacheManager(World world) {
    this(world.func_72905_C(), world.func_72912_H().func_76067_t());
  }



  private WorldCacheManager(long genSeed, WorldType worldtype) {
    this();
    this.seed = genSeed;


    this.rocksIndexLayer = new GenLayerTFC[3];
    this.rocksIndexLayer[0] = GenRockLayer.initialize(genSeed + 1L, ROCK_LAYER_1);
    this.rocksIndexLayer[1] = GenRockLayer.initialize(genSeed + 2L, ROCK_LAYER_2);
    this.rocksIndexLayer[2] = GenRockLayer.initialize(genSeed + 3L, ROCK_LAYER_3);



    this.treesIndexLayer = new GenLayerTFC[3];

    this.treesIndexLayer[0] = GenTreeLayer.initialize(genSeed + 4L, TREE_ARRAY);
    this.treesIndexLayer[1] = GenTreeLayer.initialize(genSeed + 5L, TREE_ARRAY);
    this.treesIndexLayer[2] = GenTreeLayer.initialize(genSeed + 6L, TREE_ARRAY);


    this.evtIndexLayer = GenEVTLayer.initialize(genSeed + 7L, worldtype);


    this.rainfallIndexLayer = GenRainLayerTFC.initialize(genSeed + 8L, worldtype);


    this.stabilityIndexLayer = GenStabilityLayer.initialize(genSeed + 9L, worldtype);


    this.phIndexLayer = GenPHLayer.initialize(genSeed + 10L, worldtype);


    this.drainageIndexLayer = GenDrainageLayer.initialize(genSeed + 11L, worldtype);

    this.worldTempCache = new LinkedHashMap<>();
  }


  public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
    IntCache.func_76446_a();

    if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5) {
      par1ArrayOfFloat = new float[par4 * par5];
    }
    int[] var6 = this.rainfallIndexLayer.func_75904_a(par2, par3, par4, par5);

    for (int var7 = 0; var7 < par4 * par5; var7++) {

      float var8 = var6[var7];
      var8 = 1.0F - (8000.0F - var8) / 8000.0F;
      if (var8 > 1.0F)
        var8 = 1.0F;
      par1ArrayOfFloat[var7] = var8;
    }

    return par1ArrayOfFloat;
  }


  public void cleanupCache() {
    this.rockCache[0].cleanupCache();
    this.rockCache[1].cleanupCache();
    this.rockCache[2].cleanupCache();
    this.treeCache[0].cleanupCache();
    this.treeCache[1].cleanupCache();
    this.treeCache[2].cleanupCache();
    this.evtCache.cleanupCache();
    this.rainfallCache.cleanupCache();
    this.stabilityCache.cleanupCache();
    this.phCache.cleanupCache();
    this.drainageCache.cleanupCache();
    while (this.worldTempCache.size() > 51000)
    {
      trimTempCache();
    }
  }


  public String getTempKey(int x, int z, int totalHours) {
    return x + ',' + z + ',' + totalHours;
  }


  public float getTemp(int x, int z, int totalHours) {
    String key = getTempKey(x, z, totalHours);
    if (this.worldTempCache != null && this.worldTempCache.containsKey(key))
    {
      return ((Float)this.worldTempCache.get(key)).floatValue();
    }
    return Float.MIN_VALUE;
  }


  public void addTemp(int x, int z, int totalHours, float temp) {
    String key = getTempKey(x, z, totalHours);
    if (this.worldTempCache != null)
      this.worldTempCache.put(key, Float.valueOf(temp));
    trimTempCache();
  }


  private void trimTempCache() {
    if (this.worldTempCache.size() > 50000) {

      Iterator<String> iter = this.worldTempCache.keySet().iterator();
      if (iter.hasNext()) {
        this.worldTempCache.remove(iter.next());
      }
    }
  }

  public DataLayer getDataLayerAt(DataCache cache, GenLayerTFC indexLayers, int par1, int par2, int index) {
    return cache.getDataLayerAt(indexLayers, par1, par2);
  }







  public DataLayer[] getDataLayerAt(DataCache[] cache, DataLayer[] layers, GenLayerTFC[] indexLayers, int x, int y, int width, int height, boolean par6, int layer) {
    if (layers == null || layers.length < width * height) {
      layers = new DataLayer[width * height];
    }
    if (par6 && width == 16 && height == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {

      DataLayer[] var9 = cache[layer].getCachedData(indexLayers[layer], x, y);
      System.arraycopy(var9, 0, layers, 0, width * height);
      return layers;
    }


    IntCache.func_76446_a();
    int[] var7 = indexLayers[layer].func_75904_a(x, y, width, height);
    for (int var8 = 0; var8 < width * height; var8++)
    {
      layers[var8] = DataLayer.layers[var7[var8]];
    }
    return layers;
  }



  public DataLayer[] getDataLayerAt(DataCache cache, DataLayer[] layers, GenLayerTFC indexLayers, int x, int y, int width, int height, boolean par6, int layer) {
    if (layers == null || layers.length < width * height) {
      layers = new DataLayer[width * height];
    }
    if (par6 && width == 16 && height == 16 && (x & 0xF) == 0 && (y & 0xF) == 0) {

      DataLayer[] var9 = cache.getCachedData(indexLayers, x, y);
      System.arraycopy(var9, 0, layers, 0, width * height);
      return layers;
    }


    IntCache.func_76446_a();
    int[] var7 = indexLayers.func_75904_a(x, y, width, height);
    for (int var8 = 0; var8 < width * height; var8++)
    {
      layers[var8] = DataLayer.layers[var7[var8]];
    }
    return layers;
  }



  public DataLayer getRockLayerAt(int x, int y, int index) {
    return this.rockCache[index].getDataLayerAt(this.rocksIndexLayer[index], x, y);
  }






  public DataLayer[] loadRockLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height, int layer) {
    return getDataLayerAt((DataCache[])this.rockCache.clone(), layers, this.rocksIndexLayer, x, y, width, height, true, layer);
  }


  public DataLayer getTreeLayerAt(int x, int z, int index) {
    return this.treeCache[index].getDataLayerAt(this.treesIndexLayer[index], x, z);
  }






  public DataLayer[] loadTreeLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height, int layer) {
    return getDataLayerAt((DataCache[])this.treeCache.clone(), layers, this.treesIndexLayer, x, y, width, height, true, 0);
  }


  public DataLayer getEVTLayerAt(int x, int z) {
    return this.evtCache.getDataLayerAt(this.evtIndexLayer, x, z);
  }






  public DataLayer[] loadEVTLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height) {
    return getDataLayerAt(this.evtCache, layers, this.evtIndexLayer, x, y, width, height, true, 0);
  }


  public DataLayer getRainfallLayerAt(int x, int z) {
    return this.rainfallCache.getDataLayerAt(this.rainfallIndexLayer, x, z);
  }





  public DataLayer[] loadRainfallLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height) {
    return getDataLayerAt(this.rainfallCache, layers, this.rainfallIndexLayer, x, y, width, height, true, 0);
  }


  public DataLayer getStabilityLayerAt(int x, int z) {
    return this.stabilityCache.getDataLayerAt(this.stabilityIndexLayer, x, z);
  }


  public DataLayer[] loadStabilityLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height) {
    return getDataLayerAt(this.stabilityCache, layers, this.stabilityIndexLayer, x, y, width, height, true, 0);
  }


  public DataLayer getPHLayerAt(int x, int z) {
    DataLayer dl = this.phCache.getDataLayerAt(this.phIndexLayer, x, z);
    return (dl != null) ? dl : DataLayer.PH_NEUTRAL;
  }


  public DataLayer[] loadPHLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height) {
    return getDataLayerAt(this.phCache, layers, this.phIndexLayer, x, y, width, height, true, 0);
  }


  public DataLayer getDrainageLayerAt(int x, int z) {
    return this.drainageCache.getDataLayerAt(this.drainageIndexLayer, x, z);
  }


  public DataLayer[] loadDrainageLayerGeneratorData(DataLayer[] layers, int x, int y, int width, int height) {
    return getDataLayerAt(this.drainageCache, layers, this.drainageIndexLayer, x, y, width, height, true, 0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\WorldCacheManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */