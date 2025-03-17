/*    */ package com.bioxx.tfc.WorldGen.Data;
/*    */ 
/*    */ import com.bioxx.tfc.WorldGen.DataLayer;
/*    */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*    */ import com.bioxx.tfc.WorldGen.WorldCacheManager;
/*    */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataCache
/*    */ {
/*    */   private final WorldCacheManager chunkManager;
/*    */   private long lastCleanupTime;
/* 22 */   private Long2ObjectMap<DataCacheBlockTFC> cacheMap = (Long2ObjectMap<DataCacheBlockTFC>)new Long2ObjectOpenHashMap(4096);
/*    */   
/* 24 */   private List<DataCacheBlockTFC> cache = new ArrayList<>();
/*    */   
/*    */   private int index;
/*    */   
/*    */   public DataCache(WorldCacheManager worldLayerManager) {
/* 29 */     this.chunkManager = worldLayerManager;
/*    */   }
/*    */ 
/*    */   
/*    */   public DataCache(WorldCacheManager par1, int ind) {
/* 34 */     this.chunkManager = par1;
/* 35 */     this.index = ind;
/*    */   }
/*    */ 
/*    */   
/*    */   public DataCacheBlockTFC getDataCacheBlock(GenLayerTFC indexLayers, int x, int y) {
/* 40 */     x >>= 4;
/* 41 */     y >>= 4;
/* 42 */     long var3 = x & 0xFFFFFFFFL | (y & 0xFFFFFFFFL) << 32L;
/*    */     
/* 44 */     DataCacheBlockTFC var5 = (DataCacheBlockTFC)this.cacheMap.get(var3);
/* 45 */     if (var5 == null) {
/*    */       
/* 47 */       var5 = new DataCacheBlockTFC(this, indexLayers, x, y, this.index);
/*    */       
/* 49 */       this.cacheMap.put(var3, var5);
/* 50 */       this.cache.add(var5);
/*    */     } 
/* 52 */     var5.lastAccessTime = MinecraftServer.func_130071_aq();
/* 53 */     return var5;
/*    */   }
/*    */ 
/*    */   
/*    */   public DataLayer getDataLayerAt(GenLayerTFC indexLayers, int par1, int par2) {
/* 58 */     return getDataCacheBlock(indexLayers, par1, par2).getDataLayerAt(par1, par2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void cleanupCache() {
/* 63 */     long var1 = MinecraftServer.func_130071_aq();
/* 64 */     long var3 = var1 - this.lastCleanupTime;
/* 65 */     if (var3 > 7500L || var3 < 0L) {
/*    */       
/* 67 */       this.lastCleanupTime = var1;
/* 68 */       for (int var5 = 0; var5 < this.cache.size(); var5++) {
/*    */         
/* 70 */         DataCacheBlockTFC var6 = this.cache.get(var5);
/* 71 */         if (var6 != null) {
/*    */           
/* 73 */           long var7 = var1 - var6.lastAccessTime;
/* 74 */           if (var7 > 30000L || var7 < 0L) {
/*    */             
/* 76 */             this.cache.remove(var5--);
/* 77 */             long var9 = var6.xPosition & 0xFFFFFFFFL | (var6.zPosition & 0xFFFFFFFFL) << 32L;
/* 78 */             this.cacheMap.remove(var9);
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public DataLayer[] getCachedData(GenLayerTFC indexLayers, int par1, int par2) {
/* 87 */     return (getDataCacheBlock(indexLayers, par1, par2)).data;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static WorldCacheManager getChunkManager(DataCache cache) {
/* 95 */     return cache.chunkManager;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Data\DataCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */