/*     */ package com.bioxx.tfc.WorldGen;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.world.ChunkPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.biome.BiomeCache;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.biome.WorldChunkManager;
/*     */ import net.minecraft.world.gen.layer.IntCache;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TFCWorldChunkManager
/*     */   extends WorldChunkManager
/*     */ {
/*     */   protected World worldObj;
/*     */   protected GenLayerTFC field_76944_d;
/*     */   protected GenLayerTFC field_76945_e;
/*     */   protected BiomeCache field_76942_f;
/*     */   protected List<BiomeGenBase> field_76943_g;
/*     */   public long seed;
/*     */   
/*     */   public TFCWorldChunkManager() {
/*  37 */     this.field_76942_f = new BiomeCache(this);
/*  38 */     this.field_76943_g = new ArrayList<>();
/*     */     
/*  40 */     this.field_76943_g.add(BiomeGenBase.field_76767_f);
/*  41 */     this.field_76943_g.add(TFCBiome.PLAINS);
/*  42 */     this.field_76943_g.add(TFCBiome.ROLLING_HILLS);
/*  43 */     this.field_76943_g.add(TFCBiome.SWAMPLAND);
/*  44 */     this.field_76943_g.add(TFCBiome.MOUNTAINS);
/*  45 */     this.field_76943_g.add(TFCBiome.HIGH_PLAINS);
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCWorldChunkManager(World world) {
/*  50 */     this(world.func_72905_C(), world.func_72912_H().func_76067_t());
/*  51 */     this.worldObj = world;
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCWorldChunkManager(long genSeed, WorldType worldtype) {
/*  56 */     this(); GenLayerTFC[] var4;
/*  57 */     this.seed = genSeed;
/*     */ 
/*     */ 
/*     */     
/*  61 */     if (worldtype == TFCWorldType.flatWorldType) {
/*  62 */       var4 = GenLayerTFC.initialize(genSeed, TFCWorldType.flatWorldType);
/*     */     } else {
/*  64 */       var4 = GenLayerTFC.initialize(genSeed, TFCWorldType.defaultWorldType);
/*     */     } 
/*  66 */     this.field_76944_d = var4[0];
/*  67 */     this.field_76945_e = var4[1];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BiomeGenBase> func_76932_a() {
/*  76 */     return this.field_76943_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] func_76936_a(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
/*  85 */     if (TFC_Climate.getCacheManager(this.worldObj) != null)
/*  86 */       return TFC_Climate.getCacheManager(this.worldObj).getRainfall(par1ArrayOfFloat, par2, par3, par4, par5); 
/*  87 */     return par1ArrayOfFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome[] getBiomesForGeneration(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
/*  96 */     IntCache.func_76446_a();
/*     */     
/*  98 */     TFCBiome[] biome = (TFCBiome[])par1;
/*  99 */     if (biome == null || biome.length < par4 * par5) {
/* 100 */       biome = new TFCBiome[par4 * par5];
/*     */     }
/* 102 */     int[] var6 = this.field_76944_d.func_75904_a(par2, par3, par4, par5);
/* 103 */     for (int var7 = 0; var7 < par4 * par5; var7++) {
/*     */       
/* 105 */       int index = Math.max(var6[var7], 0);
/* 106 */       biome[var7] = TFCBiome.getBiome(index);
/*     */     } 
/*     */     
/* 109 */     return biome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] func_76933_b(BiomeGenBase[] par1, int par2, int par3, int par4, int par5) {
/* 119 */     return func_76931_a(par1, par2, par3, par4, par5, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] func_76931_a(BiomeGenBase[] biome, int par2, int par3, int par4, int par5, boolean par6) {
/*     */     TFCBiome[] arrayOfTFCBiome;
/* 129 */     IntCache.func_76446_a();
/*     */     
/* 131 */     if (biome == null || biome.length < par4 * par5) {
/* 132 */       arrayOfTFCBiome = new TFCBiome[par4 * par5];
/*     */     }
/* 134 */     if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xF) == 0 && (par3 & 0xF) == 0) {
/*     */       
/* 136 */       BiomeGenBase[] var9 = this.field_76942_f.func_76839_e(par2, par3);
/* 137 */       System.arraycopy(var9, 0, arrayOfTFCBiome, 0, par4 * par5);
/* 138 */       return (BiomeGenBase[])arrayOfTFCBiome;
/*     */     } 
/*     */ 
/*     */     
/* 142 */     int[] var7 = this.field_76945_e.func_75904_a(par2, par3, par4, par5);
/* 143 */     for (int zCoord = 0; zCoord < par4; zCoord++) {
/*     */       
/* 145 */       for (int xCoord = 0; xCoord < par5; xCoord++) {
/*     */         
/* 147 */         int id = (var7[zCoord * par4 + xCoord] != -1) ? var7[zCoord * par4 + xCoord] : 0;
/* 148 */         arrayOfTFCBiome[zCoord * par4 + xCoord] = TFCBiome.getBiome(id);
/*     */       } 
/*     */     } 
/* 151 */     return (BiomeGenBase[])arrayOfTFCBiome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_76940_a(int par1, int par2, int par3, List par4List) {
/* 162 */     IntCache.func_76446_a();
/* 163 */     int var5 = par1 - par3 >> 2;
/* 164 */     int var6 = par2 - par3 >> 2;
/* 165 */     int var7 = par1 + par3 >> 2;
/* 166 */     int var8 = par2 + par3 >> 2;
/* 167 */     int var9 = var7 - var5 + 1;
/* 168 */     int var10 = var8 - var6 + 1;
/* 169 */     int[] var11 = this.field_76944_d.func_75904_a(var5, var6, var9, var10);
/*     */     
/* 171 */     for (int var12 = 0; var12 < var9 * var10; var12++) {
/*     */       
/* 173 */       TFCBiome var13 = TFCBiome.getBiomeGenArray()[var11[var12]];
/* 174 */       if (!par4List.contains(var13))
/* 175 */         return false; 
/*     */     } 
/* 177 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition func_150795_a(int xCoord, int zCoord, int radius, List biomeList, Random rand) {
/* 188 */     IntCache.func_76446_a();
/* 189 */     int l = xCoord - radius >> 2;
/* 190 */     int i1 = zCoord - radius >> 2;
/* 191 */     int j1 = xCoord + radius >> 2;
/* 192 */     int k1 = zCoord + radius >> 2;
/* 193 */     int l1 = j1 - l + 1;
/* 194 */     int i2 = k1 - i1 + 1;
/* 195 */     int[] aint = this.field_76944_d.func_75904_a(l, i1, l1, i2);
/* 196 */     ChunkPosition chunkposition = null;
/* 197 */     int j2 = 0;
/*     */     
/* 199 */     for (int k2 = 0; k2 < l1 * i2; k2++) {
/*     */       
/* 201 */       int l2 = l + k2 % l1 << 2;
/* 202 */       int i3 = i1 + k2 / l1 << 2;
/* 203 */       TFCBiome biomegenbase = TFCBiome.getBiome(aint[k2]);
/*     */       
/* 205 */       if (biomeList.contains(biomegenbase) && (chunkposition == null || rand.nextInt(j2 + 1) == 0)) {
/*     */         
/* 207 */         chunkposition = new ChunkPosition(l2, 0, i3);
/* 208 */         j2++;
/*     */       } 
/*     */     } 
/*     */     
/* 212 */     return chunkposition;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float func_76939_a(float t, int y) {
/* 219 */     int x = (int)Math.floor((Minecraft.func_71410_x()).field_71439_g.field_70165_t);
/* 220 */     int z = (int)Math.floor((Minecraft.func_71410_x()).field_71439_g.field_70161_v);
/* 221 */     return TFC_Climate.getHeightAdjustedTemp((World)(Minecraft.func_71410_x()).field_71441_e, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76938_b() {
/* 227 */     this.field_76942_f.func_76838_a();
/* 228 */     WorldCacheManager wcm = TFC_Climate.getCacheManager(this.worldObj);
/* 229 */     if (wcm != null) wcm.cleanupCache(); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCWorldChunkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */