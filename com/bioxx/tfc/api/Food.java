/*     */ package com.bioxx.tfc.api;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.Random;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Food
/*     */ {
/*     */   public static final String DECAY_TAG = "foodDecay";
/*     */   public static final String DECAY_TIMER_TAG = "decayTimer";
/*     */   public static final String DECAY_RATE_TAG = "decayRate";
/*     */   public static final String WEIGHT_TAG = "foodWeight";
/*     */   public static final String PROCESSING_TAG = "Processing Tag";
/*     */   public static final String BRINED_TAG = "Brined";
/*     */   public static final String PICKLED_TAG = "Pickled";
/*     */   public static final String SALTED_TAG = "Salted";
/*     */   public static final String COOKED_TAG = "Cooked";
/*     */   public static final String COOKED_PROFILE_TAG = "CookedProfile";
/*     */   public static final String FUEL_PROFILE_TAG = "FuelProfile";
/*     */   public static final String DRIED_TAG = "Dried";
/*     */   public static final String SMOKE_COUNTER_TAG = "SmokeCounter";
/*     */   public static final String SWEET_MOD_TAG = "tasteSweetMod";
/*     */   public static final String SOUR_MOD_TAG = "tasteSourMod";
/*     */   public static final String SALTY_MOD_TAG = "tasteSaltyMod";
/*     */   public static final String BITTER_MOD_TAG = "tasteBitterMod";
/*     */   public static final String UMAMI_MOD_TAG = "tasteUmamiMod";
/*     */   public static final String MEAL_SKILL_TAG = "mealSkill";
/*     */   public static final String INFUSION_TAG = "Infusion";
/*     */   public static final String FOOD_GROUP_TAG = "FG";
/*     */   public static final int DRYHOURS = 4;
/*     */   public static final int SMOKEHOURS = 12;
/*     */   
/*     */   private static NBTTagCompound getProcTag(ItemStack is) {
/*  40 */     if (is.func_77942_o() && is.func_77978_p().func_74764_b("Processing Tag"))
/*     */     {
/*  42 */       return (NBTTagCompound)is.func_77978_p().func_74781_a("Processing Tag");
/*     */     }
/*     */     
/*  45 */     return new NBTTagCompound();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void setProcTag(ItemStack is, NBTTagCompound nbt) {
/*  50 */     if (!is.func_77942_o())
/*  51 */       is.func_77982_d(new NBTTagCompound()); 
/*  52 */     is.func_77978_p().func_74782_a("Processing Tag", (NBTBase)nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   private static NBTTagCompound getNBT(ItemStack is) {
/*  57 */     if (is.func_77942_o())
/*     */     {
/*  59 */       return is.func_77978_p();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     return new NBTTagCompound();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean areEqual(ItemStack is1, ItemStack is2) {
/*  72 */     return (isBrined(is1) == isBrined(is2) && 
/*  73 */       isPickled(is1) == isPickled(is2) && 
/*  74 */       isCooked(is1) == isCooked(is2) && 
/*  75 */       isDried(is1) == isDried(is2) && 
/*  76 */       isSalted(is1) == isSalted(is2) && ((
/*  77 */       isInfused(is1) && isInfused(is2) && getInfusion(is1).equals(getInfusion(is2))) || (
/*  78 */       !isInfused(is1) && !isInfused(is2))) && ((
/*  79 */       isSmoked(is1) && isSmoked(is2) && isSameSmoked(is1, is2)) || (
/*  80 */       !isSmoked(is1) && !isSmoked(is2))));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isBrined(ItemStack is) {
/*  85 */     NBTTagCompound nbt = getProcTag(is);
/*  86 */     return (nbt.func_74764_b("Brined") && nbt.func_74767_n("Brined"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setBrined(ItemStack is, boolean value) {
/*  91 */     NBTTagCompound nbt = getProcTag(is);
/*  92 */     nbt.func_74757_a("Brined", value);
/*  93 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isPickled(ItemStack is) {
/*  98 */     NBTTagCompound nbt = getProcTag(is);
/*  99 */     return (nbt.func_74764_b("Pickled") && nbt.func_74767_n("Pickled"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setPickled(ItemStack is, boolean value) {
/* 104 */     NBTTagCompound nbt = getProcTag(is);
/* 105 */     nbt.func_74757_a("Pickled", value);
/* 106 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSalted(ItemStack is) {
/* 111 */     NBTTagCompound nbt = getProcTag(is);
/* 112 */     return (nbt.func_74764_b("Salted") && nbt.func_74767_n("Salted"));
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSalted(ItemStack is, boolean value) {
/* 117 */     NBTTagCompound nbt = getProcTag(is);
/* 118 */     nbt.func_74757_a("Salted", value);
/* 119 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isCooked(ItemStack is) {
/* 124 */     NBTTagCompound nbt = getProcTag(is);
/* 125 */     return (nbt.func_74764_b("Cooked") && nbt.func_74760_g("Cooked") > 600.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getCooked(ItemStack is) {
/* 130 */     NBTTagCompound nbt = getProcTag(is);
/* 131 */     if (nbt.func_74764_b("Cooked")) {
/* 132 */       return nbt.func_74760_g("Cooked");
/*     */     }
/* 134 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCooked(ItemStack is, float value) {
/* 139 */     NBTTagCompound nbt = getProcTag(is);
/* 140 */     nbt.func_74776_a("Cooked", value);
/* 141 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getCookedProfile(ItemStack is) {
/* 146 */     NBTTagCompound nbt = getProcTag(is);
/* 147 */     if (nbt.func_74764_b("CookedProfile")) {
/* 148 */       return nbt.func_74759_k("CookedProfile");
/*     */     }
/* 150 */     return new int[] { 0, 0, 0, 0, 0 };
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setCookedProfile(ItemStack is, int[] value) {
/* 155 */     NBTTagCompound nbt = getProcTag(is);
/* 156 */     nbt.func_74783_a("CookedProfile", value);
/* 157 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getFuelProfile(ItemStack is) {
/* 162 */     NBTTagCompound nbt = getProcTag(is);
/* 163 */     if (nbt.func_74764_b("FuelProfile")) {
/* 164 */       return nbt.func_74759_k("FuelProfile");
/*     */     }
/* 166 */     return new int[] { 0, 0, 0, 0, 0 };
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setFuelProfile(ItemStack is, int[] value) {
/* 171 */     NBTTagCompound nbt = getProcTag(is);
/* 172 */     nbt.func_74783_a("FuelProfile", value);
/* 173 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSmoked(ItemStack is) {
/* 178 */     NBTTagCompound nbt = getProcTag(is);
/* 179 */     return (nbt.func_74764_b("FuelProfile") && !isSameSmoked(getFuelProfile(is), new int[] { 0, 0, 0, 0, 0 }));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isSameSmoked(ItemStack is1, ItemStack is2) {
/* 185 */     int[] f1 = getFuelProfile(is1);
/* 186 */     int[] f2 = getFuelProfile(is2);
/* 187 */     return (f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] && f1[3] == f2[3] && f1[4] == f2[4]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isSameSmoked(int[] f1, int[] f2) {
/* 192 */     return (f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] && f1[3] == f2[3] && f1[4] == f2[4]);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDecay(ItemStack is, float value) {
/* 197 */     NBTTagCompound nbt = getNBT(is);
/* 198 */     nbt.func_74776_a("foodDecay", Helper.roundNumber(value, 10000.0F));
/* 199 */     if (value > getWeight(is)) {
/* 200 */       is.field_77994_a = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static float getDecay(ItemStack is) {
/* 205 */     NBTTagCompound nbt = getNBT(is);
/* 206 */     if (nbt.func_74764_b("foodDecay")) {
/* 207 */       return nbt.func_74760_g("foodDecay");
/*     */     }
/* 209 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDecayTimer(ItemStack is, int value) {
/* 214 */     NBTTagCompound nbt = getNBT(is);
/* 215 */     nbt.func_74768_a("decayTimer", value);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getDecayTimer(ItemStack is) {
/* 220 */     NBTTagCompound nbt = getNBT(is);
/* 221 */     if (nbt.func_74764_b("decayTimer")) {
/* 222 */       return nbt.func_74762_e("decayTimer");
/*     */     }
/* 224 */     return (int)TFC_Time.getTotalHours();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setWeight(ItemStack is, float value) {
/* 229 */     NBTTagCompound nbt = getNBT(is);
/* 230 */     nbt.func_74776_a("foodWeight", Helper.roundNumber(value, 100.0F));
/* 231 */     if (getDecay(is) > value || value <= 0.0F) {
/* 232 */       is.field_77994_a = 0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static float getWeight(ItemStack is) {
/* 237 */     NBTTagCompound nbt = getNBT(is);
/* 238 */     if (nbt.func_74764_b("foodWeight")) {
/* 239 */       return nbt.func_74760_g("foodWeight");
/*     */     }
/* 241 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isDried(ItemStack is) {
/* 246 */     NBTTagCompound nbt = getProcTag(is);
/* 247 */     return (nbt.func_74764_b("Dried") && nbt.func_74765_d("Dried") >= 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public static short getDried(ItemStack is) {
/* 252 */     NBTTagCompound nbt = getProcTag(is);
/* 253 */     if (nbt.func_74764_b("Dried")) {
/* 254 */       return nbt.func_74765_d("Dried");
/*     */     }
/* 256 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDried(ItemStack is, int value) {
/* 261 */     NBTTagCompound nbt = getProcTag(is);
/* 262 */     nbt.func_74777_a("Dried", (short)value);
/* 263 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static short getSmokeCounter(ItemStack is) {
/* 268 */     NBTTagCompound nbt = getProcTag(is);
/* 269 */     if (nbt.func_74764_b("SmokeCounter")) {
/* 270 */       return nbt.func_74765_d("SmokeCounter");
/*     */     }
/* 272 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSmokeCounter(ItemStack is, int value) {
/* 277 */     NBTTagCompound nbt = getProcTag(is);
/* 278 */     nbt.func_74777_a("SmokeCounter", (short)value);
/* 279 */     setProcTag(is, nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getCookedColorMultiplier(ItemStack is) {
/* 284 */     float cookedLevel = getCooked(is);
/* 285 */     int r = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
/* 286 */     int b = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
/* 287 */     int g = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
/* 288 */     return (r << 16) + (b << 8) + g;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSweetMod(ItemStack is, int val) {
/* 293 */     NBTTagCompound nbt = getNBT(is);
/* 294 */     nbt.func_74768_a("tasteSweetMod", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSweetMod(ItemStack is) {
/* 299 */     NBTTagCompound nbt = getNBT(is);
/* 300 */     if (nbt.func_74764_b("tasteSweetMod")) {
/* 301 */       return nbt.func_74762_e("tasteSweetMod");
/*     */     }
/* 303 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSourMod(ItemStack is, int val) {
/* 308 */     NBTTagCompound nbt = getNBT(is);
/* 309 */     nbt.func_74768_a("tasteSourMod", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSourMod(ItemStack is) {
/* 314 */     NBTTagCompound nbt = getNBT(is);
/* 315 */     if (nbt.func_74764_b("tasteSourMod")) {
/* 316 */       return nbt.func_74762_e("tasteSourMod");
/*     */     }
/* 318 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSaltyMod(ItemStack is, int val) {
/* 323 */     NBTTagCompound nbt = getNBT(is);
/* 324 */     nbt.func_74768_a("tasteSaltyMod", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSaltyMod(ItemStack is) {
/* 329 */     NBTTagCompound nbt = getNBT(is);
/* 330 */     if (nbt.func_74764_b("tasteSaltyMod")) {
/* 331 */       return nbt.func_74762_e("tasteSaltyMod");
/*     */     }
/* 333 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setBitterMod(ItemStack is, int val) {
/* 338 */     NBTTagCompound nbt = getNBT(is);
/* 339 */     nbt.func_74768_a("tasteBitterMod", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getBitterMod(ItemStack is) {
/* 344 */     NBTTagCompound nbt = getNBT(is);
/* 345 */     if (nbt.func_74764_b("tasteBitterMod")) {
/* 346 */       return nbt.func_74762_e("tasteBitterMod");
/*     */     }
/* 348 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setSavoryMod(ItemStack is, int val) {
/* 353 */     NBTTagCompound nbt = getNBT(is);
/* 354 */     nbt.func_74768_a("tasteUmamiMod", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSavoryMod(ItemStack is) {
/* 359 */     NBTTagCompound nbt = getNBT(is);
/* 360 */     if (nbt.func_74764_b("tasteUmamiMod")) {
/* 361 */       return nbt.func_74762_e("tasteUmamiMod");
/*     */     }
/* 363 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void adjustFlavor(ItemStack is, Random r) {
/* 368 */     setSweetMod(is, r.nextInt(17) - 8);
/* 369 */     setSourMod(is, r.nextInt(17) - 8);
/* 370 */     setSaltyMod(is, r.nextInt(17) - 8);
/* 371 */     setBitterMod(is, r.nextInt(17) - 8);
/* 372 */     setSavoryMod(is, r.nextInt(17) - 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setMealSkill(ItemStack is, int val) {
/* 377 */     NBTTagCompound nbt = getNBT(is);
/* 378 */     nbt.func_74768_a("mealSkill", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMealSkill(ItemStack is) {
/* 383 */     NBTTagCompound nbt = getNBT(is);
/* 384 */     if (nbt.func_74764_b("mealSkill")) {
/* 385 */       return nbt.func_74762_e("mealSkill");
/*     */     }
/* 387 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasMealSkill(ItemStack is) {
/* 392 */     NBTTagCompound nbt = getNBT(is);
/* 393 */     return nbt.func_74764_b("mealSkill");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getFoodTasteProfile(ItemStack is) {
/* 398 */     int[] profile = new int[5];
/* 399 */     if (is != null && is.func_77973_b() instanceof IFood) {
/*     */       
/* 401 */       profile[0] = ((IFood)is.func_77973_b()).getTasteSweet(is);
/* 402 */       profile[1] = ((IFood)is.func_77973_b()).getTasteSour(is);
/* 403 */       profile[2] = ((IFood)is.func_77973_b()).getTasteSalty(is);
/* 404 */       profile[3] = ((IFood)is.func_77973_b()).getTasteBitter(is);
/* 405 */       profile[4] = ((IFood)is.func_77973_b()).getTasteSavory(is);
/*     */     } 
/* 407 */     return profile;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInfused(ItemStack is) {
/* 412 */     NBTTagCompound nbt = getNBT(is);
/* 413 */     return nbt.func_74764_b("Infusion");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getInfusion(ItemStack is) {
/* 418 */     NBTTagCompound nbt = getNBT(is);
/* 419 */     if (nbt.func_74764_b("Infusion")) {
/* 420 */       return nbt.func_74779_i("Infusion");
/*     */     }
/* 422 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setInfusion(ItemStack is, String val) {
/* 427 */     NBTTagCompound nbt = getNBT(is);
/* 428 */     nbt.func_74778_a("Infusion", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setFoodGroups(ItemStack is, int[] val) {
/* 433 */     NBTTagCompound nbt = getNBT(is);
/* 434 */     nbt.func_74783_a("FG", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int[] getFoodGroups(ItemStack is) {
/* 439 */     NBTTagCompound nbt = getNBT(is);
/* 440 */     if (nbt.func_74764_b("FG")) {
/* 441 */       return nbt.func_74759_k("FG");
/*     */     }
/* 443 */     return new int[] { -1, -1, -1, -1 };
/*     */   }
/*     */ 
/*     */   
/*     */   public static void setDecayRate(ItemStack is, float val) {
/* 448 */     NBTTagCompound nbt = getNBT(is);
/* 449 */     nbt.func_74776_a("decayRate", val);
/*     */   }
/*     */ 
/*     */   
/*     */   public static float getDecayRate(ItemStack is) {
/* 454 */     NBTTagCompound nbt = getNBT(is);
/* 455 */     if (nbt.func_74764_b("decayRate")) {
/* 456 */       return nbt.func_74760_g("decayRate");
/*     */     }
/* 458 */     return 1.0F;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Food.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */