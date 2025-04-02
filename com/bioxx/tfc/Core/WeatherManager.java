package com.bioxx.tfc.Core;

import java.util.Random;
import net.minecraft.world.World;


public class WeatherManager
{
  protected static final WeatherManager INSTANCE = new WeatherManager();
  private Random rand = new Random();
  private Random clientRand = new Random();

  public static final WeatherManager getInstance() {
    return INSTANCE;
  }







  public long seed;







  public float getDailyTemp() {
    this.rand.setSeed(this.seed + TFC_Time.getTotalDays());
    return ((this.rand.nextInt(200) - 100) / 10);
  }


  public float getDailyTemp(int day) {
    this.rand.setSeed(this.seed + day);
    return ((this.rand.nextInt(200) - 100) / 20);
  }


  public float getWeeklyTemp(int week) {
    this.rand.setSeed(this.seed + week);
    return ((this.rand.nextInt(200) - 100) / 10);
  }


  public static int getDayOfWeek(long day) {
    long days = day / 6L;
    return (int)(day - days * 6L);
  }


  public static boolean canSnow(World world, int x, int y, int z) {
    return (TFC_Climate.getHeightAdjustedTemp(world, x, y, z) <= 0.0F);
  }


  public float getLocalFog(World world, int x, int y, int z) {
    if (world.field_72995_K) {

      int hour = TFC_Time.getHour();
      if (hour >= 4 && hour < 9) {

        this.clientRand.setSeed(TFC_Time.getTotalDays());
        float rain = TFC_Climate.getRainfall(world, x, y, z);
        float strength = this.clientRand.nextFloat();
        if (rain >= 500.0F && this.clientRand.nextInt(3) == 0) {

          float mult = 1.0F;
          if (9 - hour < 2)
            mult = 0.5F;
          return strength * mult;
        }
      }
    }
    return 0.0F;
  }


  public float getSnowStrength() {
    int hour = TFC_Time.getHour();
    this.clientRand.setSeed((TFC_Time.getTotalDays() + hour));
    return this.clientRand.nextFloat();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\WeatherManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */