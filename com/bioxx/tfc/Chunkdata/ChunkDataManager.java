/*     */ package com.bioxx.tfc.Chunkdata;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.util.LongHashMap;
/*     */ import net.minecraft.world.ChunkCoordIntPair;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkDataManager
/*     */ {
/*  16 */   private LongHashMap chunkmap = new LongHashMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkDataManager(World world) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeData(int x, int z) {
/*  25 */     this.chunkmap.func_76159_d(ChunkCoordIntPair.func_77272_a(x, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addData(long key, ChunkData cd) {
/*  30 */     this.chunkmap.func_76163_a(key, cd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addData(Chunk chunk, ChunkData cd) {
/*  35 */     this.chunkmap.func_76163_a(ChunkCoordIntPair.func_77272_a(chunk.field_76635_g, chunk.field_76647_h), cd);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addData(int x, int z, ChunkData cd) {
/*  40 */     this.chunkmap.func_76163_a(ChunkCoordIntPair.func_77272_a(x, z), cd);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkData getData(int x, int z) {
/*  45 */     return (ChunkData)this.chunkmap.func_76164_a(ChunkCoordIntPair.func_77272_a(x, z));
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkData getData(long key) {
/*  50 */     return (ChunkData)this.chunkmap.func_76164_a(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasData(long key) {
/*  55 */     return this.chunkmap.func_76161_b(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addProtection(int x, int z, int amount) {
/*  60 */     ChunkData d = getData(x, z);
/*  61 */     if (d != null) {
/*     */       
/*  63 */       if (d.spawnProtection < 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths)
/*  64 */         d.setSpawnProtectionWithUpdate(amount); 
/*  65 */       return true;
/*     */     } 
/*  67 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFishPop(int x, int z, float fishPop) {
/*  72 */     ChunkData d = getData(x, z);
/*  73 */     if (d != null && fishPop >= 0.0F)
/*     */     {
/*  75 */       d.fishPop = fishPop;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFishPop(int x, int z) {
/*  81 */     ChunkData d = getData(x, z);
/*  82 */     if (d != null)
/*     */     {
/*  84 */       return (int)d.fishPop;
/*     */     }
/*  86 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean catchFish(int x, int z) {
/*  91 */     ChunkData d = getData(x, z);
/*  92 */     if (d != null)
/*     */     {
/*  94 */       if (d.fishPop > 0.0F) {
/*  95 */         d.fishPop--;
/*  96 */         return true;
/*     */       } 
/*     */     }
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setLastVisted(int x, int z) {
/* 104 */     ChunkData d = getData(x, z);
/* 105 */     if (d != null) {
/*     */       
/* 107 */       d.lastVisited = TFC_Time.getTotalTicks();
/* 108 */       return true;
/*     */     } 
/* 110 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Chunkdata\ChunkDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */