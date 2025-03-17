/*     */ package com.bioxx.tfc.WorldGen;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.ChunkCoordinates;
/*     */ import net.minecraft.world.WorldProvider;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ import net.minecraft.world.chunk.IChunkProvider;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TFCProvider
/*     */   extends WorldProvider
/*     */ {
/*     */   private int moonPhase;
/*     */   private int moonPhaseLastCalculated;
/*     */   
/*     */   protected void func_76572_b() {
/*  33 */     TFC_Climate.worldPair.put(this.field_76579_a, new WorldCacheManager(this.field_76579_a));
/*  34 */     TFC_Core.addCDM(this.field_76579_a);
/*     */     
/*  36 */     super.func_76572_b();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChunkProvider func_76555_c() {
/*  42 */     return (IChunkProvider)new TFCChunkProviderGenerate(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_76566_a(int x, int z) {
/*  48 */     int y = this.field_76579_a.func_72825_h(x, z) - 1;
/*  49 */     if (y < 144 || y > 169) return false; 
/*  50 */     Block b = this.field_76579_a.func_147439_a(x, y, z);
/*  51 */     return (TFC_Core.isSand(b) || TFC_Core.isGrass(b));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public int func_76559_b(long par1) {
/*  60 */     if (TFC_Time.getDayFromTotalHours(TFC_Time.getTotalHours()) != this.moonPhaseLastCalculated) {
/*     */       
/*  62 */       int daysPassed = (int)(par1 / 24000L);
/*  63 */       int dayOfMonth = daysPassed % TFC_Time.daysInMonth;
/*  64 */       float dayToLunarDayMultiplier = 8.0F / TFC_Time.daysInMonth;
/*     */ 
/*     */       
/*  67 */       int lunarDay = Math.round(dayOfMonth * dayToLunarDayMultiplier);
/*     */       
/*  69 */       this.moonPhase = lunarDay % 8;
/*     */       
/*  71 */       this.moonPhaseLastCalculated = TFC_Time.getDayFromTotalHours(TFC_Time.getTotalHours());
/*     */     } 
/*     */     
/*  74 */     return this.moonPhase;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float func_76571_f() {
/*  80 */     return 256.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isNextToShoreOrIce(int x, int y, int z) {
/*  91 */     if (this.field_76579_a.func_72904_c(x + 1, y, z, x + 1, y, z) && (
/*  92 */       this.field_76579_a.func_147439_a(x + 1, y, z) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x + 1, y, z))))
/*  93 */       return true; 
/*  94 */     if (this.field_76579_a.func_72904_c(x - 1, y, z, x - 1, y, z) && (
/*  95 */       this.field_76579_a.func_147439_a(x - 1, y, z) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x - 1, y, z))))
/*  96 */       return true; 
/*  97 */     if (this.field_76579_a.func_72904_c(x, y, z + 1, x, y, z + 1) && (
/*  98 */       this.field_76579_a.func_147439_a(x, y, z + 1) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x, y, z + 1))))
/*  99 */       return true; 
/* 100 */     if (this.field_76579_a.func_72904_c(x, y, z - 1, x, y, z - 1) && (
/* 101 */       this.field_76579_a.func_147439_a(x, y, z - 1) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x, y, z - 1))))
/* 102 */       return true; 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
/* 109 */     Block id = this.field_76579_a.func_147439_a(x, y, z);
/* 110 */     int meta = this.field_76579_a.func_72805_g(x, y, z);
/* 111 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_76579_a, x, y, z);
/* 112 */     BiomeGenBase biome = this.field_76579_a.func_72807_a(x, z);
/*     */     
/* 114 */     if (temp <= 0.0F && biome != TFCBiome.DEEP_OCEAN) {
/*     */       
/* 116 */       if (this.field_76579_a.func_147437_c(x, y + 1, z) && TFC_Core.isWater(id) && this.field_76579_a.field_73012_v.nextInt(4) == 0 && isNextToShoreOrIce(x, y, z))
/*     */       {
/* 118 */         Material mat = this.field_76579_a.func_147439_a(x, y, z).func_149688_o();
/* 119 */         boolean salty = TFC_Core.isSaltWaterIncludeIce(id, meta, mat);
/*     */         
/* 121 */         if (temp <= -2.0F) {
/* 122 */           salty = false;
/*     */         }
/* 124 */         if ((mat == Material.field_151586_h || mat == Material.field_151588_w) && !salty)
/*     */         {
/* 126 */           if (id == TFCBlocks.freshWaterStationary && meta == 0) {
/*     */             
/* 128 */             this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.ice, 1, 2);
/*     */           }
/* 130 */           else if (id == TFCBlocks.saltWaterStationary && meta == 0) {
/*     */             
/* 132 */             this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.ice, 0, 2);
/*     */           } 
/*     */         }
/* 135 */         return false;
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 140 */     else if (id == TFCBlocks.ice) {
/*     */       
/* 142 */       int chance = (int)Math.floor(Math.max(1.0F, 6.0F - temp));
/* 143 */       if (id == TFCBlocks.ice && this.field_76579_a.field_73012_v.nextInt(chance) == 0)
/*     */       {
/* 145 */         if (this.field_76579_a.func_147439_a(x, y + 1, z) == Blocks.field_150433_aE) {
/*     */           
/* 147 */           int m = this.field_76579_a.func_72805_g(x, y + 1, z);
/* 148 */           if (m > 0)
/*     */           {
/* 150 */             this.field_76579_a.func_72921_c(x, y + 1, z, m - 1, 2);
/*     */           }
/*     */           else
/*     */           {
/* 154 */             this.field_76579_a.func_147468_f(x, y + 1, z);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 159 */           int flag = 2;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 164 */           if ((meta & 0x1) == 0) {
/*     */             
/* 166 */             this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.saltWaterStationary, 0, flag);
/*     */           }
/* 168 */           else if ((meta & 0x1) == 1) {
/*     */             
/* 170 */             this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.freshWaterStationary, 0, flag);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 176 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDoRainSnowIce(Chunk chunk) {
/* 182 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
/* 188 */     if (TFC_Climate.getHeightAdjustedTemp(this.field_76579_a, x, y, z) > 0.0F)
/* 189 */       return false; 
/* 190 */     Material material = this.field_76579_a.func_147439_a(x, y, z).func_149688_o();
/* 191 */     if (material == Material.field_151597_y) {
/* 192 */       return false;
/*     */     }
/* 194 */     return (TFCBlocks.snow.func_149742_c(this.field_76579_a, x, y, z) && material.func_76222_j());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_80007_l() {
/* 207 */     return "DEFAULT";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates func_76554_h() {
/* 216 */     return getSpawnPoint();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */