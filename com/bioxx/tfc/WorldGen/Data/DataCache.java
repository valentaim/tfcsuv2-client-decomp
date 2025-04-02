package com.bioxx.tfc.WorldGen.Data;

import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.WorldCacheManager;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;







public class DataCache
{
  private final WorldCacheManager chunkManager;
  private long lastCleanupTime;
  private Long2ObjectMap<DataCacheBlockTFC> cacheMap = (Long2ObjectMap<DataCacheBlockTFC>)new Long2ObjectOpenHashMap(4096);

  private List<DataCacheBlockTFC> cache = new ArrayList<>();

  private int index;

  public DataCache(WorldCacheManager worldLayerManager) {
    this.chunkManager = worldLayerManager;
  }


  public DataCache(WorldCacheManager par1, int ind) {
    this.chunkManager = par1;
    this.index = ind;
  }


  public DataCacheBlockTFC getDataCacheBlock(GenLayerTFC indexLayers, int x, int y) {
    x >>= 4;
    y >>= 4;
    long var3 = x & 0xFFFFFFFFL | (y & 0xFFFFFFFFL) << 32L;

    DataCacheBlockTFC var5 = (DataCacheBlockTFC)this.cacheMap.get(var3);
    if (var5 == null) {

      var5 = new DataCacheBlockTFC(this, indexLayers, x, y, this.index);

      this.cacheMap.put(var3, var5);
      this.cache.add(var5);
    }
    var5.lastAccessTime = MinecraftServer.func_130071_aq();
    return var5;
  }


  public DataLayer getDataLayerAt(GenLayerTFC indexLayers, int par1, int par2) {
    return getDataCacheBlock(indexLayers, par1, par2).getDataLayerAt(par1, par2);
  }


  public void cleanupCache() {
    long var1 = MinecraftServer.func_130071_aq();
    long var3 = var1 - this.lastCleanupTime;
    if (var3 > 7500L || var3 < 0L) {

      this.lastCleanupTime = var1;
      for (int var5 = 0; var5 < this.cache.size(); var5++) {

        DataCacheBlockTFC var6 = this.cache.get(var5);
        if (var6 != null) {

          long var7 = var1 - var6.lastAccessTime;
          if (var7 > 30000L || var7 < 0L) {

            this.cache.remove(var5--);
            long var9 = var6.xPosition & 0xFFFFFFFFL | (var6.zPosition & 0xFFFFFFFFL) << 32L;
            this.cacheMap.remove(var9);
          }
        }
      }
    }
  }


  public DataLayer[] getCachedData(GenLayerTFC indexLayers, int par1, int par2) {
    return (getDataCacheBlock(indexLayers, par1, par2)).data;
  }





  public static WorldCacheManager getChunkManager(DataCache cache) {
    return cache.chunkManager;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Data\DataCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */