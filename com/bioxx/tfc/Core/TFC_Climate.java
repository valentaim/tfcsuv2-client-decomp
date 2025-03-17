/*     */ package com.bioxx.tfc.Core;
/*     */ 
/*     */ import com.bioxx.tfc.Chunkdata.ChunkData;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.WorldGen.WorldCacheManager;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import fof.tfcsu.utils.Utils;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TFC_Climate
/*     */ {
/*  19 */   public static Map<World, WorldCacheManager> worldPair = new HashMap<>();
/*     */   
/*  21 */   private static final float[] Y_FACTOR_CACHE = new float[441];
/*  22 */   private static final float[] Z_FACTOR_CACHE = new float[30001];
/*  23 */   private static final float[][] MONTH_TEMP_CACHE = new float[12][30001];
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
/*     */   public static void initCache() {
/*  43 */     for (int y = 0; y < Y_FACTOR_CACHE.length; y++) {
/*     */       float factor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  54 */       if (y < 110) {
/*     */         
/*  56 */         factor = (y * y) / 677.966F;
/*     */       } else {
/*     */         
/*  59 */         factor = 0.16225F * y;
/*     */       } 
/*  61 */       Y_FACTOR_CACHE[y] = factor;
/*     */     } 
/*     */     
/*  64 */     for (int zCoord = 0; zCoord < getMaxZPos() + 1; zCoord++) {
/*     */       
/*  66 */       float factor = 0.0F;
/*  67 */       float z = zCoord;
/*     */       
/*  69 */       factor = (getMaxZPos() - z) / getMaxZPos();
/*     */       
/*  71 */       Z_FACTOR_CACHE[zCoord] = factor;
/*     */       
/*  73 */       for (int month = 0; month < 12; month++) {
/*     */         
/*  75 */         float MAXTEMP = 35.0F;
/*     */         
/*  77 */         double angle = factor * 1.5707963267948966D;
/*  78 */         double latitudeFactor = Math.cos(angle);
/*     */         
/*  80 */         switch (month) {
/*     */ 
/*     */           
/*     */           case 10:
/*  84 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 13.5D * latitudeFactor - latitudeFactor * 55.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 9:
/*     */           case 11:
/*  90 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 12.5D * latitudeFactor - latitudeFactor * 53.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 0:
/*     */           case 8:
/*  96 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 10.0D * latitudeFactor - latitudeFactor * 46.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 1:
/*     */           case 7:
/* 102 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 7.5D * latitudeFactor - latitudeFactor * 40.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 2:
/*     */           case 6:
/* 108 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 5.0D * latitudeFactor - latitudeFactor * 33.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 3:
/*     */           case 5:
/* 114 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 2.5D * latitudeFactor - latitudeFactor * 27.0D);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 119 */             MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 1.5D * latitudeFactor - latitudeFactor * 27.0D);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static float getZFactor(int zCoord) {
/* 129 */     if (zCoord < 0) {
/* 130 */       zCoord = -zCoord;
/*     */     }
/* 132 */     if (zCoord > getMaxZPos()) {
/* 133 */       zCoord = getMaxZPos();
/*     */     }
/* 135 */     return Z_FACTOR_CACHE[zCoord];
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getTemp(World world, int x, int z) {
/* 140 */     return getTemp0(world, TFC_Time.currentDay, TFC_Time.getHour(), x, z, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getTemp(World world, int day, int hour, int x, int z) {
/* 145 */     return getTemp0(world, day, hour, x, z, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getBioTemp(World world, int day, int x, int z) {
/* 150 */     return getTemp0(world, day, 0, x, z, true);
/*     */   }
/*     */ 
/*     */   
/*     */   private static float getTemp0(World world, int day, int hour, int x, int z, boolean bio) {
/* 155 */     if (getCacheManager(world) != null) {
/*     */       
/* 157 */       float hourMod, dailyTemp, zMod = getZFactor(z);
/* 158 */       float zTemp = zMod * getMaxTemperature() - 20.0F + (zMod - 0.5F) * 10.0F;
/*     */       
/* 160 */       float rain = getRainfall(world, x, 144, z);
/* 161 */       float rainMod = (1.0F - rain / 4000.0F) * zMod;
/*     */       
/* 163 */       int month = TFC_Time.getSeasonFromDayOfYear(day, z);
/* 164 */       int lastMonth = TFC_Time.getSeasonFromDayOfYear(day - TFC_Time.daysInMonth, z);
/*     */       
/* 166 */       float monthTemp = getMonthTemp(month, z);
/* 167 */       float lastMonthTemp = getMonthTemp(lastMonth, z);
/*     */       
/* 169 */       int dayOfMonth = TFC_Time.getDayOfMonthFromDayOfYear(day);
/*     */ 
/*     */ 
/*     */       
/* 173 */       if (bio) {
/* 174 */         hourMod = 0.2F;
/* 175 */         dailyTemp = 0.0F;
/*     */       } else {
/* 177 */         int h = (hour - 6) % 24;
/* 178 */         if (h < 0) {
/* 179 */           h += 24;
/*     */         }
/*     */         
/* 182 */         if (h < 12) {
/* 183 */           hourMod = h / 11.0F * 0.3F;
/*     */         } else {
/* 185 */           hourMod = 0.3F - (h - 12.0F) / 11.0F * 0.3F;
/*     */         } 
/* 187 */         dailyTemp = WeatherManager.getInstance().getDailyTemp(day);
/*     */       } 
/*     */       
/* 190 */       float monthDelta = (monthTemp - lastMonthTemp) * dayOfMonth / TFC_Time.daysInMonth;
/* 191 */       float temp = lastMonthTemp + monthDelta;
/*     */       
/* 193 */       temp += dailyTemp + hourMod * (zTemp + dailyTemp);
/*     */       
/* 195 */       if (temp >= 12.0F) {
/* 196 */         temp += 8.0F * rainMod * zMod;
/*     */       } else {
/* 198 */         temp -= 8.0F * rainMod * zMod;
/*     */       } 
/* 200 */       return temp;
/*     */     } 
/* 202 */     return -10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getMonthTemp(int season, int z) {
/* 207 */     if (z < 0)
/* 208 */       z = -z; 
/* 209 */     if (z > getMaxZPos())
/* 210 */       z = getMaxZPos(); 
/* 211 */     return MONTH_TEMP_CACHE[season][z];
/*     */   }
/*     */ 
/*     */   
/*     */   protected static float getTempSpecificDay(World world, int day, int x, int z) {
/* 216 */     return getTemp(world, day, 12, x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getHeightAdjustedTemp(World world, int x, int y, int z) {
/* 221 */     float out, temp = getTemp(world, x, z);
/* 222 */     temp += getTemp(world, x + 1, z);
/* 223 */     temp += getTemp(world, x - 1, z);
/* 224 */     temp += getTemp(world, x, z + 1);
/* 225 */     temp += getTemp(world, x, z - 1);
/* 226 */     temp /= 5.0F;
/* 227 */     temp = adjustHeightToTemp(y, temp);
/* 228 */     float light = 1.0F;
/*     */     
/* 230 */     if (world.func_72863_F() != null)
/*     */     {
/*     */ 
/*     */       
/* 234 */       if (world.func_72937_j(x, y, z)) {
/*     */         
/* 236 */         light = 0.0F;
/*     */       }
/*     */       else {
/*     */         
/* 240 */         float bl = world.func_72957_l(x, y, z);
/* 241 */         light = 0.25F * (1.0F - bl / 15.0F);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     if (temp > 0.0F) {
/*     */       
/* 253 */       out = temp - temp * light;
/*     */     } else {
/*     */       
/* 256 */       out = temp;
/*     */     } 
/* 258 */     return Utils.adjustGreenHouseTemp(world, out, x, y, z);
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
/*     */ 
/*     */   
/*     */   public static float adjustHeightToTemp(int y, float temp) {
/* 273 */     if (y > 144) {
/*     */       
/* 275 */       int i = y - 144;
/* 276 */       if (i >= Y_FACTOR_CACHE.length) {
/* 277 */         i = Y_FACTOR_CACHE.length - 1;
/*     */       }
/* 279 */       temp -= Y_FACTOR_CACHE[i];
/*     */     } 
/* 281 */     return temp;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getHeightAdjustedTempSpecificDay(World world, int day, int x, int y, int z) {
/* 286 */     float temp = getTempSpecificDay(world, day, x, z);
/* 287 */     temp = adjustHeightToTemp(y, temp);
/* 288 */     return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getHeightAdjustedTempSpecificDay(World world, int day, int hour, int x, int y, int z) {
/* 294 */     float temp = getTemp(world, day, hour, x, z);
/* 295 */     temp = adjustHeightToTemp(y, temp);
/* 296 */     return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getHeightAdjustedBioTemp(World world, int day, int x, int y, int z) {
/* 302 */     float temp = getBioTemp(world, day, x, z);
/* 303 */     temp = adjustHeightToTemp(y, temp);
/* 304 */     return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getMaxTemperature() {
/* 310 */     return 52.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getBioTemperatureHeight(World world, int x, int y, int z) {
/* 315 */     float temp = 0.0F;
/* 316 */     for (int i = 0; i < 12; i++) {
/*     */       
/* 318 */       float t = getHeightAdjustedBioTemp(world, i * TFC_Time.daysInMonth, x, y, z);
/*     */ 
/*     */       
/* 321 */       temp += t;
/*     */     } 
/* 323 */     return temp / 12.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getBioTemperature(World world, int x, int z) {
/* 328 */     float temp = 0.0F;
/* 329 */     for (int i = 0; i < 24; i++) {
/*     */       
/* 331 */       float t = getBioTemp(world, i * TFC_Time.daysInMonth / 2, x, z);
/*     */ 
/*     */       
/* 334 */       temp += t;
/*     */     } 
/* 336 */     return temp / 24.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getGrassColor(World world, int x, int y, int z) {
/* 345 */     float temp = (getTemp(world, x, z) + getMaxTemperature()) / getMaxTemperature() * 2.0F;
/*     */     
/* 347 */     float rain = getRainfall(world, x, y, z) / 8000.0F;
/*     */     
/* 349 */     double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
/* 350 */     double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);
/*     */     
/* 352 */     return ColorizerGrassTFC.getGrassColor(var1, var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getFoliageColor(World world, int x, int y, int z) {
/* 361 */     float temperature = getHeightAdjustedTempSpecificDay(world, TFC_Time.getDayOfYear(), x, y, z);
/* 362 */     float rainfall = getRainfall(world, x, y, z);
/* 363 */     if (temperature > 5.0F && rainfall > 100.0F) {
/*     */       
/* 365 */       float temp = (temperature + 35.0F) / (getMaxTemperature() + 35.0F);
/* 366 */       float rain = rainfall / 8000.0F;
/*     */       
/* 368 */       double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
/* 369 */       double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);
/* 370 */       return ColorizerFoliageTFC.getFoliageColor(var1, var3);
/*     */     } 
/*     */ 
/*     */     
/* 374 */     return ColorizerFoliageTFC.getFoliageDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public static int getFoliageColorEvergreen(World world, int x, int y, int z) {
/* 385 */     float rainfall = getRainfall(world, x, y, z);
/* 386 */     if (rainfall > 100.0F) {
/*     */       
/* 388 */       float temp = (getTemp(world, x, z) + 35.0F) / (getMaxTemperature() + 35.0F);
/* 389 */       float rain = rainfall / 8000.0F;
/*     */       
/* 391 */       double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
/* 392 */       double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);
/* 393 */       return ColorizerFoliageTFC.getFoliageColor(var1, var3);
/*     */     } 
/*     */ 
/*     */     
/* 397 */     return ColorizerFoliageTFC.getFoliageDead();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getRainfall(World world, int x, int y, int z) {
/* 407 */     if (world.field_72995_K && TFC_Core.getCDM(world) != null) {
/*     */       
/* 409 */       ChunkData cd = TFC_Core.getCDM(world).getData(x >> 4, z >> 4);
/* 410 */       if (cd != null) {
/* 411 */         return cd.getRainfall(x & 0xF, z & 0xF);
/*     */       }
/*     */     } 
/*     */     try {
/* 415 */       if (getCacheManager(world) != null) {
/*     */         
/* 417 */         DataLayer dl = getCacheManager(world).getRainfallLayerAt(x, z);
/* 418 */         return (dl != null) ? dl.floatdata1 : DataLayer.RAIN_500.floatdata1;
/*     */       } 
/*     */     } catch (Exception e) {
/* 421 */       System.out.println("Got rainfal error");
/*     */     } 
/* 423 */     return DataLayer.RAIN_500.floatdata1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTreeLayer(World world, int x, int y, int z, int index) {
/* 428 */     return (getCacheManager(world).getTreeLayerAt(x, z, index)).data1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DataLayer getRockLayer(World world, int x, int y, int z, int index) {
/* 433 */     return getCacheManager(world).getRockLayerAt(x, z, index);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxZPos() {
/* 438 */     return 30000;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSwamp(World world, int x, int y, int z) {
/* 443 */     float rain = getRainfall(world, x, y, z);
/* 444 */     float evt = (getCacheManager(world).getEVTLayerAt(x, z)).floatdata1;
/* 445 */     return (rain >= 1000.0F && evt <= 0.25D && (world.func_72807_a(x, z)).field_76749_E < 0.15D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getStability(World world, int x, int z) {
/* 450 */     if (getCacheManager(world) != null) {
/* 451 */       return (getCacheManager(world).getStabilityLayerAt(x, z)).data1;
/*     */     }
/* 453 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static WorldCacheManager getCacheManager(World world) {
/* 458 */     return worldPair.get(world);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void removeCacheManager(World world) {
/* 463 */     if (worldPair.containsKey(world))
/* 464 */       worldPair.remove(world); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Climate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */