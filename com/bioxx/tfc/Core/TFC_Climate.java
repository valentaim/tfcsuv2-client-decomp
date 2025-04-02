package com.bioxx.tfc.Core;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.WorldCacheManager;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fof.tfcsu.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.world.World;




public class TFC_Climate
{
  public static Map<World, WorldCacheManager> worldPair = new HashMap<>();

  private static final float[] Y_FACTOR_CACHE = new float[441];
  private static final float[] Z_FACTOR_CACHE = new float[30001];
  private static final float[][] MONTH_TEMP_CACHE = new float[12][30001];


















  public static void initCache() {
    for (int y = 0; y < Y_FACTOR_CACHE.length; y++) {
      float factor;









      if (y < 110) {

        factor = (y * y) / 677.966F;
      } else {

        factor = 0.16225F * y;
      }
      Y_FACTOR_CACHE[y] = factor;
    }

    for (int zCoord = 0; zCoord < getMaxZPos() + 1; zCoord++) {

      float factor = 0.0F;
      float z = zCoord;

      factor = (getMaxZPos() - z) / getMaxZPos();

      Z_FACTOR_CACHE[zCoord] = factor;

      for (int month = 0; month < 12; month++) {

        float MAXTEMP = 35.0F;

        double angle = factor * 1.5707963267948966D;
        double latitudeFactor = Math.cos(angle);

        switch (month) {


          case 10:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 13.5D * latitudeFactor - latitudeFactor * 55.0D);
            break;


          case 9:
          case 11:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 12.5D * latitudeFactor - latitudeFactor * 53.0D);
            break;


          case 0:
          case 8:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 10.0D * latitudeFactor - latitudeFactor * 46.0D);
            break;


          case 1:
          case 7:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 7.5D * latitudeFactor - latitudeFactor * 40.0D);
            break;


          case 2:
          case 6:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 5.0D * latitudeFactor - latitudeFactor * 33.0D);
            break;


          case 3:
          case 5:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 2.5D * latitudeFactor - latitudeFactor * 27.0D);
            break;


          case 4:
            MONTH_TEMP_CACHE[month][zCoord] = (float)(35.0D - 1.5D * latitudeFactor - latitudeFactor * 27.0D);
            break;
        }
      }
    }
  }



  protected static float getZFactor(int zCoord) {
    if (zCoord < 0) {
      zCoord = -zCoord;
    }
    if (zCoord > getMaxZPos()) {
      zCoord = getMaxZPos();
    }
    return Z_FACTOR_CACHE[zCoord];
  }


  protected static float getTemp(World world, int x, int z) {
    return getTemp0(world, TFC_Time.currentDay, TFC_Time.getHour(), x, z, false);
  }


  protected static float getTemp(World world, int day, int hour, int x, int z) {
    return getTemp0(world, day, hour, x, z, false);
  }


  protected static float getBioTemp(World world, int day, int x, int z) {
    return getTemp0(world, day, 0, x, z, true);
  }


  private static float getTemp0(World world, int day, int hour, int x, int z, boolean bio) {
    if (getCacheManager(world) != null) {

      float hourMod, dailyTemp, zMod = getZFactor(z);
      float zTemp = zMod * getMaxTemperature() - 20.0F + (zMod - 0.5F) * 10.0F;

      float rain = getRainfall(world, x, 144, z);
      float rainMod = (1.0F - rain / 4000.0F) * zMod;

      int month = TFC_Time.getSeasonFromDayOfYear(day, z);
      int lastMonth = TFC_Time.getSeasonFromDayOfYear(day - TFC_Time.daysInMonth, z);

      float monthTemp = getMonthTemp(month, z);
      float lastMonthTemp = getMonthTemp(lastMonth, z);

      int dayOfMonth = TFC_Time.getDayOfMonthFromDayOfYear(day);



      if (bio) {
        hourMod = 0.2F;
        dailyTemp = 0.0F;
      } else {
        int h = (hour - 6) % 24;
        if (h < 0) {
          h += 24;
        }

        if (h < 12) {
          hourMod = h / 11.0F * 0.3F;
        } else {
          hourMod = 0.3F - (h - 12.0F) / 11.0F * 0.3F;
        }
        dailyTemp = WeatherManager.getInstance().getDailyTemp(day);
      }

      float monthDelta = (monthTemp - lastMonthTemp) * dayOfMonth / TFC_Time.daysInMonth;
      float temp = lastMonthTemp + monthDelta;

      temp += dailyTemp + hourMod * (zTemp + dailyTemp);

      if (temp >= 12.0F) {
        temp += 8.0F * rainMod * zMod;
      } else {
        temp -= 8.0F * rainMod * zMod;
      }
      return temp;
    }
    return -10.0F;
  }


  protected static float getMonthTemp(int season, int z) {
    if (z < 0)
      z = -z;
    if (z > getMaxZPos())
      z = getMaxZPos();
    return MONTH_TEMP_CACHE[season][z];
  }


  protected static float getTempSpecificDay(World world, int day, int x, int z) {
    return getTemp(world, day, 12, x, z);
  }


  public static float getHeightAdjustedTemp(World world, int x, int y, int z) {
    float out, temp = getTemp(world, x, z);
    temp += getTemp(world, x + 1, z);
    temp += getTemp(world, x - 1, z);
    temp += getTemp(world, x, z + 1);
    temp += getTemp(world, x, z - 1);
    temp /= 5.0F;
    temp = adjustHeightToTemp(y, temp);
    float light = 1.0F;

    if (world.func_72863_F() != null)
    {


      if (world.func_72937_j(x, y, z)) {

        light = 0.0F;
      }
      else {

        float bl = world.func_72957_l(x, y, z);
        light = 0.25F * (1.0F - bl / 15.0F);
      }
    }







    if (temp > 0.0F) {

      out = temp - temp * light;
    } else {

      out = temp;
    }
    return Utils.adjustGreenHouseTemp(world, out, x, y, z);
  }












  public static float adjustHeightToTemp(int y, float temp) {
    if (y > 144) {

      int i = y - 144;
      if (i >= Y_FACTOR_CACHE.length) {
        i = Y_FACTOR_CACHE.length - 1;
      }
      temp -= Y_FACTOR_CACHE[i];
    }
    return temp;
  }


  public static float getHeightAdjustedTempSpecificDay(World world, int day, int x, int y, int z) {
    float temp = getTempSpecificDay(world, day, x, z);
    temp = adjustHeightToTemp(y, temp);
    return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
  }



  public static float getHeightAdjustedTempSpecificDay(World world, int day, int hour, int x, int y, int z) {
    float temp = getTemp(world, day, hour, x, z);
    temp = adjustHeightToTemp(y, temp);
    return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
  }



  public static float getHeightAdjustedBioTemp(World world, int day, int x, int y, int z) {
    float temp = getBioTemp(world, day, x, z);
    temp = adjustHeightToTemp(y, temp);
    return Utils.adjustGreenHouseTemp(world, temp, x, y, z);
  }



  public static float getMaxTemperature() {
    return 52.0F;
  }


  public static float getBioTemperatureHeight(World world, int x, int y, int z) {
    float temp = 0.0F;
    for (int i = 0; i < 12; i++) {

      float t = getHeightAdjustedBioTemp(world, i * TFC_Time.daysInMonth, x, y, z);


      temp += t;
    }
    return temp / 12.0F;
  }


  public static float getBioTemperature(World world, int x, int z) {
    float temp = 0.0F;
    for (int i = 0; i < 24; i++) {

      float t = getBioTemp(world, i * TFC_Time.daysInMonth / 2, x, z);


      temp += t;
    }
    return temp / 24.0F;
  }





  @SideOnly(Side.CLIENT)
  public static int getGrassColor(World world, int x, int y, int z) {
    float temp = (getTemp(world, x, z) + getMaxTemperature()) / getMaxTemperature() * 2.0F;

    float rain = getRainfall(world, x, y, z) / 8000.0F;

    double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
    double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);

    return ColorizerGrassTFC.getGrassColor(var1, var3);
  }





  @SideOnly(Side.CLIENT)
  public static int getFoliageColor(World world, int x, int y, int z) {
    float temperature = getHeightAdjustedTempSpecificDay(world, TFC_Time.getDayOfYear(), x, y, z);
    float rainfall = getRainfall(world, x, y, z);
    if (temperature > 5.0F && rainfall > 100.0F) {

      float temp = (temperature + 35.0F) / (getMaxTemperature() + 35.0F);
      float rain = rainfall / 8000.0F;

      double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
      double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);
      return ColorizerFoliageTFC.getFoliageColor(var1, var3);
    }


    return ColorizerFoliageTFC.getFoliageDead();
  }







  @SideOnly(Side.CLIENT)
  public static int getFoliageColorEvergreen(World world, int x, int y, int z) {
    float rainfall = getRainfall(world, x, y, z);
    if (rainfall > 100.0F) {

      float temp = (getTemp(world, x, z) + 35.0F) / (getMaxTemperature() + 35.0F);
      float rain = rainfall / 8000.0F;

      double var1 = Helper.clampFloat(temp, 0.0F, 1.0F);
      double var3 = Helper.clampFloat(rain, 0.0F, 1.0F);
      return ColorizerFoliageTFC.getFoliageColor(var1, var3);
    }


    return ColorizerFoliageTFC.getFoliageDead();
  }







  public static float getRainfall(World world, int x, int y, int z) {
    if (world.field_72995_K && TFC_Core.getCDM(world) != null) {

      ChunkData cd = TFC_Core.getCDM(world).getData(x >> 4, z >> 4);
      if (cd != null) {
        return cd.getRainfall(x & 0xF, z & 0xF);
      }
    }
    try {
      if (getCacheManager(world) != null) {

        DataLayer dl = getCacheManager(world).getRainfallLayerAt(x, z);
        return (dl != null) ? dl.floatdata1 : DataLayer.RAIN_500.floatdata1;
      }
    } catch (Exception e) {
      System.out.println("Got rainfal error");
    }
    return DataLayer.RAIN_500.floatdata1;
  }


  public static int getTreeLayer(World world, int x, int y, int z, int index) {
    return (getCacheManager(world).getTreeLayerAt(x, z, index)).data1;
  }


  public static DataLayer getRockLayer(World world, int x, int y, int z, int index) {
    return getCacheManager(world).getRockLayerAt(x, z, index);
  }


  public static int getMaxZPos() {
    return 30000;
  }


  public static boolean isSwamp(World world, int x, int y, int z) {
    float rain = getRainfall(world, x, y, z);
    float evt = (getCacheManager(world).getEVTLayerAt(x, z)).floatdata1;
    return (rain >= 1000.0F && evt <= 0.25D && (world.func_72807_a(x, z)).field_76749_E < 0.15D);
  }


  public static int getStability(World world, int x, int z) {
    if (getCacheManager(world) != null) {
      return (getCacheManager(world).getStabilityLayerAt(x, z)).data1;
    }
    return 0;
  }


  public static WorldCacheManager getCacheManager(World world) {
    return worldPair.get(world);
  }


  public static void removeCacheManager(World world) {
    if (worldPair.containsKey(world))
      worldPair.remove(world);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Climate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */