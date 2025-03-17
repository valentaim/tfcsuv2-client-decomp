/*     */ package com.bioxx.tfc.Core;
/*     */ 
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TFC_Time
/*     */ {
/*  10 */   public static final String[] SEASONS = new String[] { 
/*  11 */       TFC_Core.translate("gui.Calendar.EarlySpring"), 
/*  12 */       TFC_Core.translate("gui.Calendar.Spring"), TFC_Core.translate("gui.Calendar.LateSpring"), 
/*  13 */       TFC_Core.translate("gui.Calendar.EarlySummer"), TFC_Core.translate("gui.Calendar.Summer"), 
/*  14 */       TFC_Core.translate("gui.Calendar.LateSummer"), TFC_Core.translate("gui.Calendar.EarlyAutumn"), 
/*  15 */       TFC_Core.translate("gui.Calendar.Autumn"), TFC_Core.translate("gui.Calendar.LateAutumn"), 
/*  16 */       TFC_Core.translate("gui.Calendar.EarlyWinter"), TFC_Core.translate("gui.Calendar.Winter"), 
/*  17 */       TFC_Core.translate("gui.Calendar.LateWinter") };
/*  18 */   public static final String[] MONTHS = new String[] { 
/*  19 */       TFC_Core.translate("gui.Calendar.March"), 
/*  20 */       TFC_Core.translate("gui.Calendar.April"), TFC_Core.translate("gui.Calendar.May"), 
/*  21 */       TFC_Core.translate("gui.Calendar.June"), TFC_Core.translate("gui.Calendar.July"), 
/*  22 */       TFC_Core.translate("gui.Calendar.August"), TFC_Core.translate("gui.Calendar.September"), 
/*  23 */       TFC_Core.translate("gui.Calendar.October"), TFC_Core.translate("gui.Calendar.November"), 
/*  24 */       TFC_Core.translate("gui.Calendar.December"), TFC_Core.translate("gui.Calendar.January"), 
/*  25 */       TFC_Core.translate("gui.Calendar.February") };
/*  26 */   public static final String[] DAYS = new String[] {
/*  27 */       TFC_Core.translate("gui.Calendar.Sunday"), 
/*  28 */       TFC_Core.translate("gui.Calendar.Monday"), TFC_Core.translate("gui.Calendar.Tuesday"), 
/*  29 */       TFC_Core.translate("gui.Calendar.Wednesday"), TFC_Core.translate("gui.Calendar.Thursday"), 
/*  30 */       TFC_Core.translate("gui.Calendar.Friday"), TFC_Core.translate("gui.Calendar.Saturday")
/*     */     };
/*     */   public static int currentDay;
/*  33 */   public static int lastMonth = 11;
/*     */   
/*     */   public static int currentMonth;
/*     */   
/*     */   public static int currentYear;
/*     */   
/*     */   private static long time;
/*     */   
/*     */   public static final int JANUARY = 10;
/*     */   
/*     */   public static final int FEBRUARY = 11;
/*     */   
/*     */   public static final int MARCH = 0;
/*     */   
/*     */   public static final int APRIL = 1;
/*     */   public static final int MAY = 2;
/*     */   public static final int JUNE = 3;
/*     */   public static final int JULY = 4;
/*     */   public static final int AUGUST = 5;
/*     */   public static final int SEPTEMBER = 6;
/*     */   public static final int OCTOBER = 7;
/*     */   public static final int NOVEMBER = 8;
/*     */   public static final int DECEMBER = 9;
/*     */   public static final long HOUR_LENGTH = 1000L;
/*     */   public static final int DAY_LENGTH = 24000;
/*     */   public static final int HOURS_IN_DAY = 24;
/*  59 */   public static float timeRatio360 = TFCOptions.yearLength / 360.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   public static float timeRatio96 = TFCOptions.yearLength / 96.0F;
/*     */   
/*  66 */   public static int daysInYear = TFCOptions.yearLength;
/*  67 */   public static int daysInMonth = daysInYear / 12;
/*  68 */   public static long ticksInYear = (daysInYear * 24000);
/*  69 */   public static long ticksInMonth = (daysInMonth * 24000);
/*  70 */   public static long startTime = ticksInMonth * 3L;
/*     */ 
/*     */   
/*     */   public static void setYearLength(int length) {
/*  74 */     daysInYear = length;
/*  75 */     daysInMonth = daysInYear / 12;
/*  76 */     ticksInYear = (daysInYear * 24000);
/*  77 */     ticksInMonth = (daysInMonth * 24000);
/*  78 */     startTime = ticksInMonth * 3L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void updateTime(World world) {
/*  83 */     time = world.func_72912_H().func_76073_f();
/*     */     
/*  85 */     if (time < startTime) {
/*     */       
/*  87 */       world.func_72912_H().func_76068_b(startTime);
/*  88 */       world.func_72912_H().func_82572_b(startTime);
/*     */     } 
/*     */     
/*  91 */     int m = getMonth();
/*  92 */     int m1 = m - 1;
/*     */     
/*  94 */     if (m1 < 0) {
/*  95 */       m1 = 11;
/*     */     }
/*  97 */     lastMonth = m1;
/*  98 */     currentDay = getDayOfYear();
/*  99 */     currentMonth = m;
/* 100 */     currentYear = getYear();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDateStringFromHours(int tHours) {
/* 105 */     int tDays = tHours / 24;
/*     */     
/* 107 */     int day = tDays % daysInMonth;
/* 108 */     int tMonths = tDays / daysInMonth;
/*     */     
/* 110 */     int month = tMonths % 12;
/* 111 */     int year = tMonths / 12;
/*     */ 
/*     */     
/* 114 */     if (tHours < 0) {
/* 115 */       day += daysInMonth - 1;
/* 116 */       month += 11;
/* 117 */       year--;
/*     */     } 
/*     */ 
/*     */     
/* 121 */     if (month >= 10) {
/* 122 */       year++;
/*     */     }
/*     */     
/* 125 */     int d = day + 1;
/* 126 */     String m = MONTHS[month];
/* 127 */     int y = 1000 + year;
/*     */     
/* 129 */     String date = d + " " + m + ", " + y;
/*     */     
/* 131 */     return date;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getHoursInMonth() {
/* 136 */     return 24 * daysInMonth;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getSeason() {
/* 141 */     return SEASONS[getMonth()];
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getTotalTicks() {
/* 146 */     return time;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfWeek() {
/* 151 */     long day = (getTotalDays() + 1);
/* 152 */     long days = day / 7L;
/* 153 */     return (int)(day - days * 7L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfWeek(int tDays) {
/* 158 */     long day = (tDays + 1);
/* 159 */     long days = day / 7L;
/* 160 */     return (int)(day - days * 7L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfMonth() {
/* 165 */     long month = getTotalMonths();
/* 166 */     long days = daysInMonth * month;
/* 167 */     long days2 = getTotalDays() - days;
/* 168 */     return 1 + (int)days2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfMonth(int tDays) {
/* 173 */     int months = tDays / daysInMonth;
/* 174 */     int rem = tDays - months * daysInMonth;
/* 175 */     return 1 + rem;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfYear() {
/* 180 */     long year = getYear();
/* 181 */     long years = ticksInYear * year;
/* 182 */     long years2 = time - years;
/* 183 */     return (int)(years2 / 24000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfYearFromTick(long tick) {
/* 188 */     long years = tick / ticksInYear;
/* 189 */     long years2 = tick - ticksInYear * years;
/* 190 */     return (int)(years2 / 24000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfYearFromDays(long days) {
/* 195 */     long years = days / daysInYear;
/* 196 */     return (int)(days - daysInYear * years);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getMonth() {
/* 205 */     long totalmonths = getTotalMonths();
/* 206 */     long totalmonths2 = totalmonths / 12L;
/* 207 */     return (int)(totalmonths - totalmonths2 * 12L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSeasonAdjustedMonth(int z) {
/* 216 */     if (z > 0)
/* 217 */       return (getMonth() + 6) % 12; 
/* 218 */     return getMonth();
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getYear() {
/* 223 */     long totalmonths = getTotalMonths();
/* 224 */     return (int)(totalmonths / 12L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getTotalDays() {
/* 229 */     return (int)Math.floor(((float)time / 24000.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getTotalHours() {
/* 234 */     return time / 1000L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getTotalMonths() {
/* 239 */     return (getTotalDays() / daysInMonth);
/*     */   }
/*     */ 
/*     */   
/*     */   public static long getTotalYears() {
/* 244 */     return getTotalMonths() / 12L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getHour() {
/* 249 */     int th = (int)getTotalHours();
/* 250 */     return getHourOfDayFromTotalHours(th);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getHourOfDayFromTotalHours(int th) {
/* 255 */     return (th + 6) % 24;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayFromTotalHours(int th) {
/* 260 */     return th / 24;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayFromTotalHours(long th) {
/* 265 */     return (int)(th / 24L);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSpring(int z) {
/* 270 */     int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
/* 271 */     return (day >= 20 && day <= 111);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSummer(int z) {
/* 276 */     int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
/* 277 */     return (day >= 112 && day <= 202);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isFall(int z) {
/* 282 */     int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
/* 283 */     return (day >= 203 && day <= 293);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isWinter(int z) {
/* 288 */     int day = (getDayOfYear() + ((z > 0) ? (daysInYear / 2) : 0)) % daysInYear;
/* 289 */     return (day >= 294 || day < 20);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMonthFromDayOfYear(int day) {
/* 294 */     if (day < 0)
/* 295 */       day = daysInYear + day; 
/* 296 */     return day / daysInMonth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getSeasonFromDayOfYear(int day, int z) {
/* 307 */     if (day < 0)
/* 308 */       day = daysInYear + day; 
/* 309 */     return (day / daysInMonth + ((z > 0) ? 6 : 0)) % 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDayOfMonthFromDayOfYear(int day) {
/* 314 */     if (day < 0)
/* 315 */       day = daysInYear + day; 
/* 316 */     return day - (int)Math.floor((day / daysInMonth)) * daysInMonth;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPrevMonth() {
/* 321 */     return lastMonth;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getPrevMonth(int month) {
/* 326 */     if (month == 0)
/* 327 */       return 11; 
/* 328 */     return month - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getYearRatio(float expectedDays) {
/* 333 */     return expectedDays / daysInYear;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMonthsSinceDay(int totalDay) {
/* 338 */     int days = getTotalDays() - totalDay;
/* 339 */     return days / daysInMonth;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFC_Time.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */