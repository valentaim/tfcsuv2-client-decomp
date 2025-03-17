/*     */ package com.bioxx.tfc.Chunkdata;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ 
/*     */ public class ChunkData
/*     */ {
/*     */   public int chunkX;
/*     */   public int chunkZ;
/*     */   public long lastVisited;
/*     */   public long previousVisit;
/*     */   public int spawnProtection;
/*  18 */   public int protectionBuffer = (TFCOptions.protectionBuffer >= 0) ? (TFCOptions.protectionBuffer * -1) : -24;
/*     */   
/*     */   public int[] heightmap;
/*     */   
/*     */   public DataLayer[] rainfallMap;
/*     */   public int sluicedAmount;
/*  24 */   public float fishPop = -1.0F;
/*     */   
/*     */   public static final float FISH_POP_MAX = 60.0F;
/*     */   
/*     */   public int lastSpringGen;
/*     */   
/*     */   public int cropInfestation;
/*     */   public boolean isUnloaded;
/*     */   private final Chunk chunk;
/*     */   
/*     */   public ChunkData(Chunk chunk) {
/*  35 */     this.chunk = chunk;
/*     */     
/*  37 */     this.heightmap = new int[256];
/*  38 */     this.rainfallMap = new DataLayer[256];
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkData(Chunk chunk, NBTTagCompound tag) {
/*  43 */     this.chunk = chunk;
/*     */     
/*  45 */     this.chunkX = tag.func_74762_e("chunkX");
/*  46 */     this.chunkZ = tag.func_74762_e("chunkZ");
/*  47 */     this.lastVisited = tag.func_74763_f("lastVisited");
/*  48 */     this.spawnProtection = tag.func_74762_e("spawnProtection");
/*     */     
/*  50 */     updateSpawnProtection();
/*     */     
/*  52 */     this.heightmap = tag.func_74759_k("heightmap");
/*  53 */     if (this.heightmap.length == 0)
/*  54 */       this.heightmap = new int[256]; 
/*  55 */     this.sluicedAmount = tag.func_74762_e("sluicedAmount");
/*     */     
/*  57 */     this.lastSpringGen = tag.func_74762_e("lastSpringGen");
/*  58 */     this.cropInfestation = tag.func_74762_e("cropInfestation");
/*     */     
/*  60 */     this.fishPop = Math.min(tag.func_74760_g("fishPopulation"), 60.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound getTag() {
/*  65 */     NBTTagCompound tag = new NBTTagCompound();
/*     */     
/*  67 */     tag.func_74768_a("chunkX", this.chunkX);
/*  68 */     tag.func_74768_a("chunkZ", this.chunkZ);
/*     */     
/*  70 */     updateSpawnProtection();
/*     */     
/*  72 */     tag.func_74768_a("spawnProtection", this.spawnProtection);
/*  73 */     tag.func_74772_a("lastVisited", this.lastVisited);
/*  74 */     tag.func_74783_a("heightmap", this.heightmap);
/*  75 */     tag.func_74768_a("lastSpringGen", this.lastSpringGen);
/*  76 */     tag.func_74768_a("sluicedAmount", this.sluicedAmount);
/*  77 */     tag.func_74768_a("cropInfestation", this.cropInfestation);
/*  78 */     tag.func_74776_a("fishPopulation", Math.max(this.fishPop, -1.0F));
/*  79 */     return tag;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkData createNew(World world, int x, int z) {
/*  84 */     this.chunkX = x;
/*  85 */     this.chunkZ = z;
/*  86 */     this.lastVisited = 0L;
/*  87 */     this.spawnProtection = this.protectionBuffer;
/*  88 */     this.lastSpringGen = TFC_Time.getYear();
/*  89 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnProtectionWithUpdate() {
/*  94 */     updateSpawnProtection();
/*     */     
/*  96 */     if (this.spawnProtection > 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths) {
/*  97 */       this.spawnProtection = 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths;
/*     */     }
/*  99 */     return this.spawnProtection;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnProtectionWithUpdate(int amount) {
/* 104 */     updateSpawnProtection();
/*     */     
/* 106 */     this.spawnProtection += amount;
/*     */     
/* 108 */     if (this.spawnProtection > 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths) {
/* 109 */       this.spawnProtection = 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths;
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateSpawnProtection() {
/* 114 */     long now = TFC_Time.getTotalTicks();
/*     */     
/* 116 */     if (this.lastVisited < now) {
/*     */       
/* 118 */       long visit = (now - this.lastVisited) / 1000L;
/* 119 */       this.spawnProtection = (int)(this.spawnProtection - visit);
/* 120 */       this.lastVisited += visit * 1000L;
/*     */       
/* 122 */       if (this.spawnProtection < this.protectionBuffer) {
/* 123 */         this.spawnProtection = this.protectionBuffer;
/*     */       }
/* 125 */       this.chunk.func_76630_e();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void infest() {
/* 131 */     Math.min(this.cropInfestation++, 10);
/*     */   }
/*     */ 
/*     */   
/*     */   public void uninfest() {
/* 136 */     Math.max(this.cropInfestation--, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getRainfall(int x, int z) {
/* 146 */     if (this.rainfallMap[x * z] != null) {
/* 147 */       return (this.rainfallMap[x * z]).floatdata1;
/*     */     }
/* 149 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Chunkdata\ChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */