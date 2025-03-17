/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Blocks.BlockFarmland;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import growthcraft.rice.common.block.BlockPaddy;
/*     */ import growthcraft.rice.util.RiceBlockCheck;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.world.EnumSkyBlock;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.chunk.Chunk;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TECrop
/*     */   extends NetworkTileEntity
/*     */ {
/*  42 */   public float growth = 0.1F;
/*  43 */   private long plantedTime = TFC_Time.getTotalTicks();
/*  44 */   private long growthTimer = TFC_Time.getTotalTicks();
/*  45 */   private long finishedTime = 0L;
/*  46 */   private byte sunLevel = 1;
/*     */   public int cropId;
/*     */   public int tendingLevel;
/*     */   private int killLevel;
/*     */   private boolean finished;
/*     */   
/*     */   public void func_145845_h() {
/*  53 */     Random r = new Random();
/*  54 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  56 */       float timeMultiplier = 360.0F / TFC_Time.daysInYear;
/*  57 */       CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
/*     */       
/*  59 */       if (crop.cropId == 6 && 
/*  60 */         !this.finished && this.growth >= crop.numGrowthStages) {
/*     */         
/*  62 */         Block paddyBlock = this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  63 */         if (RiceBlockCheck.isPaddy(paddyBlock))
/*     */         {
/*  65 */           ((BlockPaddy)paddyBlock).drainPaddy(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*     */         }
/*  67 */         this.finished = true;
/*     */       } 
/*     */       
/*  70 */       if ((int)Math.min(this.growth / crop.numGrowthStages * 100.0F, 100.0F) >= 100 && 
/*  71 */         this.finishedTime == 0L) this.finishedTime = TFC_Time.getTotalMonths();
/*     */ 
/*     */       
/*  74 */       long time = TFC_Time.getTotalTicks();
/*     */       
/*  76 */       if (crop != null && this.growthTimer < time && this.sunLevel > 0) {
/*     */         
/*  78 */         this.sunLevel = (byte)(this.sunLevel - 1);
/*  79 */         if (crop.needsSunlight && hasSunlight(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e)) {
/*     */           
/*  81 */           this.sunLevel = (byte)(this.sunLevel + 1);
/*  82 */           if (this.sunLevel > 30) {
/*  83 */             this.sunLevel = 30;
/*     */           }
/*     */         } 
/*  86 */         TEFarmland tef = null;
/*  87 */         TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e);
/*  88 */         if (te instanceof TEFarmland) {
/*  89 */           tef = (TEFarmland)te;
/*     */         }
/*  91 */         float ambientTemp = TFC_Climate.getHeightAdjustedTempSpecificDay(this.field_145850_b, TFC_Time.getDayOfYearFromTick(this.growthTimer), this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*  92 */         float tempAdded = 0.0F;
/*  93 */         boolean isDormant = false;
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
/*     */         
/* 115 */         if (!crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
/* 116 */           tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp);
/* 117 */         } else if (crop.dormantInFrost && ambientTemp < crop.minGrowthTemp) {
/*     */           
/* 119 */           if (this.growth > 1.0F)
/* 120 */             tempAdded = -0.03F * (crop.minGrowthTemp - ambientTemp); 
/* 121 */           isDormant = true;
/*     */         }
/* 123 */         else if (ambientTemp < 28.0F) {
/* 124 */           tempAdded = ambientTemp * 3.5E-4F;
/* 125 */         } else if (ambientTemp < 37.0F) {
/* 126 */           tempAdded = (28.0F - ambientTemp - 28.0F) * 3.0E-4F;
/*     */         } 
/* 128 */         if (!crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
/*     */           
/* 130 */           int baseKillChance = 6;
/* 131 */           if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
/* 132 */             killCrop(crop);
/*     */           
/*     */           }
/* 135 */           else if (this.killLevel < baseKillChance - 1) {
/* 136 */             this.killLevel++;
/*     */           }
/*     */         
/* 139 */         } else if (crop.dormantInFrost && ambientTemp < crop.minAliveTemp) {
/*     */           
/* 141 */           if (this.growth > 1.0F) {
/*     */             
/* 143 */             int baseKillChance = 6;
/* 144 */             if (this.field_145850_b.field_73012_v.nextInt(baseKillChance - this.killLevel) == 0) {
/* 145 */               killCrop(crop);
/*     */             
/*     */             }
/* 148 */             else if (this.killLevel < baseKillChance - 1) {
/* 149 */               this.killLevel++;
/*     */             }
/*     */           
/*     */           } 
/*     */         } else {
/*     */           
/* 155 */           this.killLevel = 0;
/*     */         } 
/*     */         
/* 158 */         int nutriType = crop.cycleType;
/* 159 */         int nutri = (tef != null) ? tef.nutrients[nutriType] : 18000;
/* 160 */         int fert = (tef != null) ? tef.nutrients[3] : 0;
/* 161 */         int soilMax = (tef != null) ? tef.getSoilMax() : 18000;
/*     */         
/* 163 */         float waterBoost = BlockFarmland.isFreshWaterNearby(this.field_145850_b, this.field_145851_c, this.field_145848_d - 1, this.field_145849_e) ? 0.1F : 0.0F;
/*     */ 
/*     */         
/* 166 */         nutri = Math.min(nutri + fert, (int)(soilMax * 1.25F));
/*     */         
/* 168 */         float nutriMult = 0.2F + nutri / soilMax * 0.5F + waterBoost;
/*     */         
/* 170 */         if (tef != null && !isDormant) {
/*     */           
/* 172 */           if (tef.nutrients[nutriType] > 0) {
/* 173 */             tef.drainNutrients(nutriType, crop.nutrientUsageMult);
/*     */           }
/* 175 */           if (tef.nutrients[3] > 0) {
/* 176 */             tef.drainNutrients(3, crop.nutrientUsageMult);
/*     */           }
/*     */         } 
/* 179 */         float growthRate = Math.max(0.0F, (crop.numGrowthStages / crop.growthTime * TFCOptions.yearLength / 120.0F + tempAdded) * nutriMult * timeMultiplier * TFCOptions.cropGrowthMultiplier);
/* 180 */         if (tef != null && tef.isInfested)
/* 181 */           growthRate /= 2.0F; 
/* 182 */         int oldGrowth = (int)Math.floor(this.growth);
/*     */         
/* 184 */         if (!isDormant) {
/* 185 */           this.growth += growthRate;
/*     */         }
/* 187 */         if (oldGrowth < (int)Math.floor(this.growth))
/*     */         {
/*     */           
/* 190 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */ 
/*     */         
/* 194 */         if (this.finishedTime > 0L && TFC_Time.getTotalMonths() - this.finishedTime > 4L) killCrop(crop);
/*     */         
/* 196 */         if (((this.finished || TFCOptions.enableCropsDie || !TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) && crop.maxLifespan == -1 && this.growth > crop.numGrowthStages + crop.numGrowthStages / 2.0F) || this.growth < 0.0F)
/*     */         {
/*     */           
/* 199 */           killCrop(crop);
/*     */         }
/*     */         
/* 202 */         this.growthTimer += (r.nextInt(2) + 23) * 1000L;
/*     */       
/*     */       }
/* 205 */       else if (crop != null && crop.needsSunlight && this.sunLevel <= 0) {
/*     */         
/* 207 */         killCrop(crop);
/*     */       } 
/*     */ 
/*     */       
/* 211 */       if (TFC_Core.isExposedToRain(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) && TFC_Climate.getHeightAdjustedTemp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e) < 0.0F)
/*     */       {
/* 213 */         if ((crop != null && !crop.dormantInFrost) || this.growth > 1.0F)
/*     */         {
/* 215 */           killCrop(crop);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasSunlight(World world, int x, int y, int z) {
/* 223 */     Chunk chunk = world.func_72938_d(x, z);
/* 224 */     int skylight = chunk.func_76614_a(EnumSkyBlock.Sky, x & 0xF, y, z & 0xF);
/* 225 */     boolean sky = world.func_72937_j(x, y + 1, z);
/* 226 */     return (sky || skylight > 13);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEstimatedGrowth(CropIndex crop) {
/* 231 */     return crop.numGrowthStages / (float)(this.growthTimer - this.plantedTime / 24000L) * 1.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onHarvest(World world, EntityPlayer player, boolean isBreaking) {
/* 236 */     if (!world.field_72995_K) {
/*     */       
/* 238 */       CropIndex crop = CropManager.getInstance().getCropFromId(this.cropId);
/*     */       
/* 240 */       if (crop != null && this.growth >= (crop.numGrowthStages - 1)) {
/*     */         
/* 242 */         ItemStack is1 = crop.getOutput1(this);
/* 243 */         ItemStack is2 = crop.getOutput2(this);
/* 244 */         ItemStack seedStack = crop.getSeed();
/*     */         
/* 246 */         if (is1 != null) {
/* 247 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is1));
/*     */         }
/* 249 */         if (is2 != null) {
/* 250 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is2));
/*     */         }
/* 252 */         int skill = 20 - (int)(20.0F * TFC_Core.getSkillStats(player).getSkillMultiplier("skill.agriculture"));
/*     */         
/* 254 */         seedStack.field_77994_a = 1 + ((world.field_73012_v.nextInt(1 + skill) == 0) ? 1 : 0);
/* 255 */         if (isBreaking) {
/* 256 */           world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, seedStack));
/*     */         }
/* 258 */         TFC_Core.getSkillStats(player).increaseSkill("skill.agriculture", 1);
/*     */         
/* 260 */         if (TFC_Core.isSoil(world.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e))) {
/* 261 */           player.func_71064_a((StatBase)TFC_Achievements.achWildVegetable, 1);
/*     */         }
/* 263 */       } else if (crop != null) {
/*     */         
/* 265 */         ItemStack is = crop.getSeed();
/* 266 */         is.field_77994_a = 1;
/* 267 */         world.func_72838_d((Entity)new EntityItem(world, this.field_145851_c + 0.5D, this.field_145848_d + 0.5D, this.field_145849_e + 0.5D, is));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void killCrop(CropIndex crop) {
/* 274 */     ItemStack is = crop.getSeed();
/* 275 */     is.field_77994_a = 1;
/* 276 */     if (TFC_Core.isFarmland(this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d - 1, this.field_145849_e)) && TFCOptions.enableSeedDrops) {
/*     */       
/* 278 */       if (this.field_145850_b.func_147449_b(this.field_145851_c, this.field_145848_d, this.field_145849_e, TFCBlocks.worldItem)) {
/*     */         
/* 280 */         TEWorldItem te = (TEWorldItem)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 281 */         if (te != null) {
/* 282 */           te.storage[0] = is;
/* 283 */           this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 289 */       this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 299 */     super.func_145839_a(nbt);
/* 300 */     this.growth = nbt.func_74760_g("growth");
/* 301 */     this.cropId = nbt.func_74762_e("cropId");
/* 302 */     this.growthTimer = nbt.func_74763_f("growthTimer");
/* 303 */     this.plantedTime = nbt.func_74763_f("plantedTime");
/* 304 */     this.killLevel = nbt.func_74762_e("killLevel");
/* 305 */     this.sunLevel = nbt.func_74771_c("sunLevel");
/* 306 */     this.finished = nbt.func_74767_n("finished");
/* 307 */     this.finishedTime = nbt.func_74763_f("finishedTime");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 316 */     super.func_145841_b(nbt);
/* 317 */     nbt.func_74776_a("growth", this.growth);
/* 318 */     nbt.func_74768_a("cropId", this.cropId);
/* 319 */     nbt.func_74772_a("growthTimer", this.growthTimer);
/* 320 */     nbt.func_74772_a("plantedTime", this.plantedTime);
/* 321 */     nbt.func_74768_a("killLevel", this.killLevel);
/* 322 */     nbt.func_74774_a("sunLevel", this.sunLevel);
/* 323 */     nbt.func_74757_a("finished", this.finished);
/* 324 */     nbt.func_74772_a("finishedTime", this.finishedTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 329 */     this.growth = nbt.func_74760_g("growth");
/* 330 */     this.cropId = nbt.func_74762_e("cropId");
/* 331 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 337 */     this.growth = nbt.func_74760_g("growth");
/* 338 */     this.cropId = nbt.func_74762_e("cropId");
/* 339 */     this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createDataNBT(NBTTagCompound nbt) {
/* 344 */     nbt.func_74776_a("growth", this.growth);
/* 345 */     nbt.func_74768_a("cropId", this.cropId);
/*     */   }
/*     */ 
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 350 */     nbt.func_74776_a("growth", this.growth);
/* 351 */     nbt.func_74768_a("cropId", this.cropId);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TECrop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */