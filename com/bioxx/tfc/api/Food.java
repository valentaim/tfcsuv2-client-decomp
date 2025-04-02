package com.bioxx.tfc.api;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Util.Helper;
import java.util.Random;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;



public class Food
{
  public static final String DECAY_TAG = "foodDecay";
  public static final String DECAY_TIMER_TAG = "decayTimer";
  public static final String DECAY_RATE_TAG = "decayRate";
  public static final String WEIGHT_TAG = "foodWeight";
  public static final String PROCESSING_TAG = "Processing Tag";
  public static final String BRINED_TAG = "Brined";
  public static final String PICKLED_TAG = "Pickled";
  public static final String SALTED_TAG = "Salted";
  public static final String COOKED_TAG = "Cooked";
  public static final String COOKED_PROFILE_TAG = "CookedProfile";
  public static final String FUEL_PROFILE_TAG = "FuelProfile";
  public static final String DRIED_TAG = "Dried";
  public static final String SMOKE_COUNTER_TAG = "SmokeCounter";
  public static final String SWEET_MOD_TAG = "tasteSweetMod";
  public static final String SOUR_MOD_TAG = "tasteSourMod";
  public static final String SALTY_MOD_TAG = "tasteSaltyMod";
  public static final String BITTER_MOD_TAG = "tasteBitterMod";
  public static final String UMAMI_MOD_TAG = "tasteUmamiMod";
  public static final String MEAL_SKILL_TAG = "mealSkill";
  public static final String INFUSION_TAG = "Infusion";
  public static final String FOOD_GROUP_TAG = "FG";
  public static final int DRYHOURS = 4;
  public static final int SMOKEHOURS = 12;

  private static NBTTagCompound getProcTag(ItemStack is) {
    if (is.func_77942_o() && is.func_77978_p().func_74764_b("Processing Tag"))
    {
      return (NBTTagCompound)is.func_77978_p().func_74781_a("Processing Tag");
    }

    return new NBTTagCompound();
  }


  private static void setProcTag(ItemStack is, NBTTagCompound nbt) {
    if (!is.func_77942_o())
      is.func_77982_d(new NBTTagCompound());
    is.func_77978_p().func_74782_a("Processing Tag", (NBTBase)nbt);
  }


  private static NBTTagCompound getNBT(ItemStack is) {
    if (is.func_77942_o())
    {
      return is.func_77978_p();
    }





    return new NBTTagCompound();
  }



  public static boolean areEqual(ItemStack is1, ItemStack is2) {
    return (isBrined(is1) == isBrined(is2) &&
      isPickled(is1) == isPickled(is2) &&
      isCooked(is1) == isCooked(is2) &&
      isDried(is1) == isDried(is2) &&
      isSalted(is1) == isSalted(is2) && ((
      isInfused(is1) && isInfused(is2) && getInfusion(is1).equals(getInfusion(is2))) || (
      !isInfused(is1) && !isInfused(is2))) && ((
      isSmoked(is1) && isSmoked(is2) && isSameSmoked(is1, is2)) || (
      !isSmoked(is1) && !isSmoked(is2))));
  }


  public static boolean isBrined(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("Brined") && nbt.func_74767_n("Brined"));
  }


  public static void setBrined(ItemStack is, boolean value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74757_a("Brined", value);
    setProcTag(is, nbt);
  }


  public static boolean isPickled(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("Pickled") && nbt.func_74767_n("Pickled"));
  }


  public static void setPickled(ItemStack is, boolean value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74757_a("Pickled", value);
    setProcTag(is, nbt);
  }


  public static boolean isSalted(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("Salted") && nbt.func_74767_n("Salted"));
  }


  public static void setSalted(ItemStack is, boolean value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74757_a("Salted", value);
    setProcTag(is, nbt);
  }


  public static boolean isCooked(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("Cooked") && nbt.func_74760_g("Cooked") > 600.0F);
  }


  public static float getCooked(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    if (nbt.func_74764_b("Cooked")) {
      return nbt.func_74760_g("Cooked");
    }
    return 0.0F;
  }


  public static void setCooked(ItemStack is, float value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74776_a("Cooked", value);
    setProcTag(is, nbt);
  }


  public static int[] getCookedProfile(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    if (nbt.func_74764_b("CookedProfile")) {
      return nbt.func_74759_k("CookedProfile");
    }
    return new int[] { 0, 0, 0, 0, 0 };
  }


  public static void setCookedProfile(ItemStack is, int[] value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74783_a("CookedProfile", value);
    setProcTag(is, nbt);
  }


  public static int[] getFuelProfile(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    if (nbt.func_74764_b("FuelProfile")) {
      return nbt.func_74759_k("FuelProfile");
    }
    return new int[] { 0, 0, 0, 0, 0 };
  }


  public static void setFuelProfile(ItemStack is, int[] value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74783_a("FuelProfile", value);
    setProcTag(is, nbt);
  }


  public static boolean isSmoked(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("FuelProfile") && !isSameSmoked(getFuelProfile(is), new int[] { 0, 0, 0, 0, 0 }));
  }



  public static boolean isSameSmoked(ItemStack is1, ItemStack is2) {
    int[] f1 = getFuelProfile(is1);
    int[] f2 = getFuelProfile(is2);
    return (f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] && f1[3] == f2[3] && f1[4] == f2[4]);
  }


  public static boolean isSameSmoked(int[] f1, int[] f2) {
    return (f1[0] == f2[0] && f1[1] == f2[1] && f1[2] == f2[2] && f1[3] == f2[3] && f1[4] == f2[4]);
  }


  public static void setDecay(ItemStack is, float value) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74776_a("foodDecay", Helper.roundNumber(value, 10000.0F));
    if (value > getWeight(is)) {
      is.field_77994_a = 0;
    }
  }

  public static float getDecay(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("foodDecay")) {
      return nbt.func_74760_g("foodDecay");
    }
    return 0.0F;
  }


  public static void setDecayTimer(ItemStack is, int value) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("decayTimer", value);
  }


  public static int getDecayTimer(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("decayTimer")) {
      return nbt.func_74762_e("decayTimer");
    }
    return (int)TFC_Time.getTotalHours();
  }


  public static void setWeight(ItemStack is, float value) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74776_a("foodWeight", Helper.roundNumber(value, 100.0F));
    if (getDecay(is) > value || value <= 0.0F) {
      is.field_77994_a = 0;
    }
  }

  public static float getWeight(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("foodWeight")) {
      return nbt.func_74760_g("foodWeight");
    }
    return 0.0F;
  }


  public static boolean isDried(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    return (nbt.func_74764_b("Dried") && nbt.func_74765_d("Dried") >= 4);
  }


  public static short getDried(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    if (nbt.func_74764_b("Dried")) {
      return nbt.func_74765_d("Dried");
    }
    return 0;
  }


  public static void setDried(ItemStack is, int value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74777_a("Dried", (short)value);
    setProcTag(is, nbt);
  }


  public static short getSmokeCounter(ItemStack is) {
    NBTTagCompound nbt = getProcTag(is);
    if (nbt.func_74764_b("SmokeCounter")) {
      return nbt.func_74765_d("SmokeCounter");
    }
    return 0;
  }


  public static void setSmokeCounter(ItemStack is, int value) {
    NBTTagCompound nbt = getProcTag(is);
    nbt.func_74777_a("SmokeCounter", (short)value);
    setProcTag(is, nbt);
  }


  public static int getCookedColorMultiplier(ItemStack is) {
    float cookedLevel = getCooked(is);
    int r = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
    int b = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
    int g = 255 - (int)(160.0F * Math.max(cookedLevel - 600.0F, 0.0F) / 600.0F);
    return (r << 16) + (b << 8) + g;
  }


  public static void setSweetMod(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("tasteSweetMod", val);
  }


  public static int getSweetMod(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("tasteSweetMod")) {
      return nbt.func_74762_e("tasteSweetMod");
    }
    return 0;
  }


  public static void setSourMod(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("tasteSourMod", val);
  }


  public static int getSourMod(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("tasteSourMod")) {
      return nbt.func_74762_e("tasteSourMod");
    }
    return 0;
  }


  public static void setSaltyMod(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("tasteSaltyMod", val);
  }


  public static int getSaltyMod(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("tasteSaltyMod")) {
      return nbt.func_74762_e("tasteSaltyMod");
    }
    return 0;
  }


  public static void setBitterMod(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("tasteBitterMod", val);
  }


  public static int getBitterMod(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("tasteBitterMod")) {
      return nbt.func_74762_e("tasteBitterMod");
    }
    return 0;
  }


  public static void setSavoryMod(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("tasteUmamiMod", val);
  }


  public static int getSavoryMod(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("tasteUmamiMod")) {
      return nbt.func_74762_e("tasteUmamiMod");
    }
    return 0;
  }


  public static void adjustFlavor(ItemStack is, Random r) {
    setSweetMod(is, r.nextInt(17) - 8);
    setSourMod(is, r.nextInt(17) - 8);
    setSaltyMod(is, r.nextInt(17) - 8);
    setBitterMod(is, r.nextInt(17) - 8);
    setSavoryMod(is, r.nextInt(17) - 8);
  }


  public static void setMealSkill(ItemStack is, int val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74768_a("mealSkill", val);
  }


  public static int getMealSkill(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("mealSkill")) {
      return nbt.func_74762_e("mealSkill");
    }
    return 0;
  }


  public static boolean hasMealSkill(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    return nbt.func_74764_b("mealSkill");
  }


  public static int[] getFoodTasteProfile(ItemStack is) {
    int[] profile = new int[5];
    if (is != null && is.func_77973_b() instanceof IFood) {

      profile[0] = ((IFood)is.func_77973_b()).getTasteSweet(is);
      profile[1] = ((IFood)is.func_77973_b()).getTasteSour(is);
      profile[2] = ((IFood)is.func_77973_b()).getTasteSalty(is);
      profile[3] = ((IFood)is.func_77973_b()).getTasteBitter(is);
      profile[4] = ((IFood)is.func_77973_b()).getTasteSavory(is);
    }
    return profile;
  }


  public static boolean isInfused(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    return nbt.func_74764_b("Infusion");
  }


  public static String getInfusion(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("Infusion")) {
      return nbt.func_74779_i("Infusion");
    }
    return null;
  }


  public static void setInfusion(ItemStack is, String val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74778_a("Infusion", val);
  }


  public static void setFoodGroups(ItemStack is, int[] val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74783_a("FG", val);
  }


  public static int[] getFoodGroups(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("FG")) {
      return nbt.func_74759_k("FG");
    }
    return new int[] { -1, -1, -1, -1 };
  }


  public static void setDecayRate(ItemStack is, float val) {
    NBTTagCompound nbt = getNBT(is);
    nbt.func_74776_a("decayRate", val);
  }


  public static float getDecayRate(ItemStack is) {
    NBTTagCompound nbt = getNBT(is);
    if (nbt.func_74764_b("decayRate")) {
      return nbt.func_74760_g("decayRate");
    }
    return 1.0F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Food.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */