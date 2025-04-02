package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.world.World;



public class TFC_Time
{
  public static final String[] SEASONS = new String[] {
      TFC_Core.translate("gui.Calendar.EarlySpring"),
      TFC_Core.translate("gui.Calendar.Spring"), TFC_Core.translate("gui.Calendar.LateSpring"),
      TFC_Core.translate("gui.Calendar.EarlySummer"), TFC_Core.translate("gui.Calendar.Summer"),
      TFC_Core.translate("gui.Calendar.LateSummer"), TFC_Core.translate("gui.Calendar.EarlyAutumn"),
      TFC_Core.translate("gui.Calendar.Autumn"), TFC_Core.translate("gui.Calendar.LateAutumn"),
      TFC_Core.translate("gui.Calendar.EarlyWinter"), TFC_Core.translate("gui.Calendar.Winter"),
      TFC_Core.translate("gui.Calendar.LateWinter") };
  public static final String[] MONTHS = new String[] {
      TFC_Core.translate("gui.Calendar.March"),
      TFC_Core.translate("gui.Calendar.April"), TFC_Core.translate("gui.Calendar.May"),
      TFC_Core.translate("gui.Calendar.June"), TFC_Core.translate("gui.Calendar.July"),
      TFC_Core.translate("gui.Calendar.August"), TFC_Core.translate("gui.Calendar.September"),
      TFC_Core.translate("gui.Calendar.October"), TFC_Core.translate("gui.Calendar.November"),
      TFC_Core.translate("gui.Calendar.December"), TFC_Core.translate("gui.Calendar.January"),
      TFC_Core.translate("gui.Calendar.February") };
  public static final String[] DAYS = new String[] {
      TFC_Core.translate("gui.Calendar.Sunday"),
      TFC_Core.translate("gui.Calendar.Monday"), TFC_Core.translate("gui.Calendar.Tuesday"),
      TFC_Core.translate("gui.Calendar.Wednesday"), TFC_Core.translate("gui.Calendar.Thursday"),
      TFC_Core.translate("gui.Calendar.Friday"), TFC_Core.translate("gui.Calendar.Saturday")
    };
  public static int currentDay;
  public static int lastMonth = 11;

  public static int currentMonth;

  public static int currentYear;

  private static long time;

  public static final int JANUARY = 10;

  public static final int FEBRUARY = 11;

  public static final int MARCH = 0;

  public static final int APRIL = 1;
  public static final int MAY = 2;
  public static final int JUNE = 3;
  public static final int JULY = 4;
  public static final int AUGUST = 5;
  public static final int SEPTEMBER = 6;
  public static final int OCTOBER = 7;
  public static final int NOVEMBER = 8;
  public static final int DECEMBER = 9;
  public static final long HOUR_LENGTH = 1000L;
  public static final int DAY_LENGTH = 24000;
  public static final int HOURS_IN_DAY = 24;
  public static float timeRatio360 = TFCOptions.yearLength / 360.0F;




  public static float timeRatio96 = TFCOptions.yearLength / 96.0F;

  public static int daysInYear = TFCOptions.yearLength;
  public static int daysInMonth = daysInYear / 12;
  public static long ticksInYear = (daysInYear * 24000);
  public static long ticksInMonth = (daysInMonth * 24000);
  public static long startTime = ticksInMonth * 3L;


  public static void setYearLength(int length) {
    daysInYear = length;
    daysInMonth = daysInYear / 12;
    ticksInYear = (daysInYear * 24000);
    ticksInMonth = (daysInMonth * 24000);
    startTime = ticksInMonth * 3L;
  }


  public static void updateTime(World world) {
    time = world.func_72912_H().func_76073_f();

    if (time < startTime) {

      world.func_72912_H().func_76068_b(startTime);
      world.func_72912_H().func_82572_b(startTime);
    }

    int m = getMonth();
    int m1 = m - 1;

    if (m1 < 0) {
      m1 = 11;
    }
    lastMonth = m1;
    currentDay = getDayOfYear();
    currentMonth = m;
    currentYear = getYear();
  }


  public static String getDateStringFromHours(int tHours) {
    int tDays = tHours / 24;

    int day = tDays % daysInMonth;
    int tMonths = tDays / daysInMonth;

    int month = tMonths % 12;
    int year = tMonths / 12;


    if (tHours < 0) {
      day += daysInMonth - 1;
      month += 11;
      year--;
    }


    if (month >= 10) {
      year++;
    }

    int d = day + 1;
    String m = MONTHS[month];
    int y = 1000 + year;

    String date = d + " " + m + ", " + y;

    return date;
  }


  public static int getHoursInMonth() {
    return 24 * daysInMonth;
  }


  public static String getSeason() {
    return SEASONS[getMonth()];
  }


  public static long getTotalTicks() {
    return time;
  }


  public static int getDayOfWeek() {
    long day = (getTotalDays() + 1);
    long days = day / 7L;
    return (int)(day - days * 7L);
  }


  public static int getDayOfWeek(int tDays) {
    long day = (tDays + 1);
    long days = day / 7L;
    return (int)(day - days * 7L);
  }


  public static int getDayOfMonth() {
    long month = getTotalMonths();
    long days = daysInMonth * month;
    long days2 = getTotalDays() - days;
    return 1 + (int)days2;
  }


  public static int getDayOfMonth(int tDays) {
    int months = tDays / daysInMonth;
    int rem = tDays - months * daysInMonth;
    return 1 + rem;
  }


  public static int getDayOfYear() {
    long year = getYear();
    long years = ticksInYear * year;
    long years2 = time - years;
    return (int)(years2 / 24000L);
  }


  public static int getDayOfYearFromTick(long tick) {
    long years = tick / ticksInYear;
    long years2 = tick - ticksInYear * years;
    return (int)(years2 / 24000L);
  }


  public static int getDayOfYearFromDays(long days) {
    long years = days / daysInYear;
    return (int)(days - daysInYear * years);
  }






  public static int getMonth() {
    long totalmonths = getTotalMonths();
    long totalmonths2 = totalmonths / 12L;
    return (int)(totalmonths - totalmonths2 * 12L);
  }






  public static int getSeasonAdjustedMonth(int z) {
    if (z > 0)
      return (getMonth() + 6) % 12;
    return getMonth();
  }


  public static int getYear() {
    long totalmonths = getTotalMonths();
    return (int)(totalmonths / 12L);
  }


  public static int getTotalDays() {
    return (int)Math.floor(((float)time / 24000.0F));
  }


  public static long getTotalHours() {
    return time / 1000L;
  }


  public static long getTotalMonths() {
    return (getTotalDays() / daysInMonth);
  }


  public static long getTotalYears() {
    return getTotalMonths() / 12L;
  }


  public static int getHour() {
    int th = (int)getTotalHours();
    return getHourOfDayFromTotalHours(th);
  }


  public static int getHourOfDayFromTotalHours(int th) {
    return (th + 6) % 24;
  }


  public static int getDayFromTotalHours(int th) {
    return th / 24;
  }


  public static int getDayFromTotalHours(long th) {
    return (int)(th / 24L);
  }


  public static boolean isSpring(int z) {
    int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
    return (day >= 20 && day <= 111);
  }


  public static boolean isSummer(int z) {
    int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
    return (day >= 112 && day <= 202);
  }


  public static boolean isFall(int z) {
    int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
    return (day >= 203 && day <= 293);
  }


  public static boolean isWinter(int z) {
    int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
    return (day >= 294 || day < 20);
  }


  public static int getMonthFromDayOfYear(int day) {
    if (day < 0)
      day = daysInYear + day;
    return day / daysInMonth;
  }








  public static int getSeasonFromDayOfYear(int day, int z) {
    if (day < 0)
      day = daysInYear + day;
    return (day / daysInMonth + ((z > 0) ? 6 : 0)) % 12;
  }


  public static int getDayOfMonthFromDayOfYear(int day) {
    if (day < 0)
      day = daysInYear + day;
    return day - (int)Math.floor((day / daysInMonth)) * daysInMonth;
  }


  public static int getPrevMonth() {
    return lastMonth;
  }


  public static int getPrevMonth(int month) {
    if (month == 0)
      return 11;
    return month - 1;
  }


  public static float getYearRatio(float expectedDays) {
    return expectedDays / daysInYear;
  }


  public static int getMonthsSinceDay(int totalDay) {
    int days = getTotalDays() - totalDay;
    return days / daysInMonth;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */