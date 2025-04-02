package com.bioxx.tfc.api;

import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;



public class TFC_ItemHeat
{
  public static String getHeatColor(float temp, float meltTemp) {
    String phrase = "";
    if (temp < 80.0F) {

      phrase = TFC_Core.translate("gui.ItemHeat.Warming");
      if (temp > 16.0D)
        phrase = phrase + "★";
      if (temp > 32.0D)
        phrase = phrase + "★";
      if (temp > 48.0D)
        phrase = phrase + "★";
      if (temp > 64.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 80.0F && temp < 210.0F) {

      phrase = TFC_Core.translate("gui.ItemHeat.Hot");
      if (temp > 106.0D)
        phrase = phrase + "★";
      if (temp > 132.0D)
        phrase = phrase + "★";
      if (temp > 158.0D)
        phrase = phrase + "★";
      if (temp > 184.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 210.0F && temp < 480.0F) {

      phrase = TFC_Core.translate("gui.ItemHeat.VeryHot");
      if (temp > 264.0D)
        phrase = phrase + "★";
      if (temp > 318.0D)
        phrase = phrase + "★";
      if (temp > 372.0D)
        phrase = phrase + "★";
      if (temp > 426.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 480.0F && temp < 580.0F) {

      phrase = "§4" + TFC_Core.translate("gui.ItemHeat.FaintRed");
      if (temp > 500.0D)
        phrase = phrase + "★";
      if (temp > 520.0D)
        phrase = phrase + "★";
      if (temp > 540.0D)
        phrase = phrase + "★";
      if (temp > 560.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 580.0F && temp < 730.0F) {

      phrase = "§4" + TFC_Core.translate("gui.ItemHeat.DarkRed");
      if (temp > 610.0D)
        phrase = phrase + "★";
      if (temp > 640.0D)
        phrase = phrase + "★";
      if (temp > 670.0D)
        phrase = phrase + "★";
      if (temp > 700.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 730.0F && temp < 930.0F) {

      phrase = "§c" + TFC_Core.translate("gui.ItemHeat.BrightRed");
      if (temp > 770.0D)
        phrase = phrase + "★";
      if (temp > 810.0D)
        phrase = phrase + "★";
      if (temp > 850.0D)
        phrase = phrase + "★";
      if (temp > 890.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 930.0F && temp < 1100.0F) {

      phrase = "§6" + TFC_Core.translate("gui.ItemHeat.Orange");
      if (temp > 964.0D)
        phrase = phrase + "★";
      if (temp > 998.0D)
        phrase = phrase + "★";
      if (temp > 1032.0D)
        phrase = phrase + "★";
      if (temp > 1066.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 1100.0F && temp < 1300.0F) {

      phrase = "§e" + TFC_Core.translate("gui.ItemHeat.Yellow");
      if (temp > 1140.0D)
        phrase = phrase + "★";
      if (temp > 1180.0D)
        phrase = phrase + "★";
      if (temp > 1220.0D)
        phrase = phrase + "★";
      if (temp > 1260.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 1300.0F && temp < 1400.0F) {

      phrase = "§e" + TFC_Core.translate("gui.ItemHeat.YellowWhite");
      if (temp > 1320.0D)
        phrase = phrase + "★";
      if (temp > 1340.0D)
        phrase = phrase + "★";
      if (temp > 1360.0D)
        phrase = phrase + "★";
      if (temp > 1380.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 1400.0F && temp < 1500.0F) {

      phrase = "§f" + TFC_Core.translate("gui.ItemHeat.White");
      if (temp > 1420.0D)
        phrase = phrase + "★";
      if (temp > 1440.0D)
        phrase = phrase + "★";
      if (temp > 1460.0D)
        phrase = phrase + "★";
      if (temp > 1480.0D) {
        phrase = phrase + "★";
      }
    } else if (temp >= 1500.0F) {
      phrase = "§f" + TFC_Core.translate("gui.ItemHeat.BrilliantWhite");
    }
    if (temp > meltTemp) {
      phrase = phrase + "§f - " + TFC_Core.translate("gui.ItemHeat.Liquid");
    }
    return phrase;
  }


  public static String getHeatColorFood(float temp, float meltTemp) {
    if (temp < meltTemp) {

      if (temp < meltTemp * 0.1F)
        return TFC_Core.translate("gui.FoodHeat.Cold");
      if (temp >= meltTemp * 0.1F && temp < meltTemp * 0.4F)
        return "§4" + TFC_Core.translate("gui.FoodHeat.Warm");
      if (temp >= meltTemp * 0.4F && temp < meltTemp * 0.8F) {
        return "§4" + TFC_Core.translate("gui.ItemHeat.Hot");
      }
      return "§4" + TFC_Core.translate("gui.ItemHeat.VeryHot");
    }
    return TFC_Core.translate("gui.ClearSlot");
  }


  public static String getHeatColorTorch(float temp, float meltTemp) {
    if (temp < meltTemp) {

      if (temp > 0.0F && temp < meltTemp * 0.8F)
        return TFC_Core.translate("gui.Torch.CatchingFire");
      if (temp >= meltTemp * 0.8F)
        return "§4" + TFC_Core.translate("gui.Torch.Lit");
    }
    return TFC_Core.translate("gui.ClearSlot");
  }


  public static Boolean getIsLiquid(ItemStack is) {
    return Boolean.valueOf((getTemp(is) >= isCookable(is)));
  }


  public static float isCookable(ItemStack is) {
    HeatRegistry manager = HeatRegistry.getInstance();
    if (is != null && manager != null) {

      HeatIndex hi = manager.findMatchingIndex(is);
      if (hi != null) {
        return hi.meltTemp;
      }
      return -1.0F;
    }

    return -1.0F;
  }


  public static float getSpecificHeat(ItemStack is) {
    HeatRegistry manager = HeatRegistry.getInstance();
    if (is != null && manager != null) {

      HeatIndex hi = manager.findMatchingIndex(is);
      if (hi != null) {
        return hi.specificHeat;
      }
      return 1.0F;
    }

    return 1.0F;
  }


  public static float isCookable(Metal m) {
    HeatRegistry manager = HeatRegistry.getInstance();
    if (manager != null) {

      HeatIndex hi = manager.findMatchingIndex(new ItemStack(m.meltedItem));
      if (hi != null) {
        return hi.meltTemp;
      }
      return -1.0F;
    }

    return -1.0F;
  }


  public static float getTemp(ItemStack is) {
    if (hasTemp(is))
    {
      return is.func_77978_p().func_74760_g("temperature");
    }
    return 0.0F;
  }


  public static boolean hasTemp(ItemStack is) {
    if (is != null)
    {
      if (is.func_77942_o() && is.func_77978_p().func_74764_b("temperature"))
        return true;
    }
    return false;
  }


  public static float getTempIncrease(ItemStack is) {
    byte debugBump = 0;
    if (TFCOptions.enableDebugMode)
      debugBump = 2;
    return TFCOptions.tempIncreaseMultiplier * getSpecificHeat(is) + debugBump;
  }


  public static float getTempDecrease(ItemStack is) {
    if (TFCOptions.enableDebugMode)
      return 0.0F;
    return TFCOptions.tempDecreaseMultiplier * getSpecificHeat(is);
  }

  public static boolean handleItemHeat(ItemStack is) {
    boolean changed = false;
    if (is != null &&
      is.func_77942_o()) {
      NBTTagCompound comp = is.func_77978_p();
      if (hasTemp(is)) {
        float temp = getTemp(is);
        if (temp > 0.0F) {
          temp -= getTempDecrease(is);
          comp.func_74776_a("temperature", temp);
          changed = true;
        }
        if (temp <= 0.0F) { comp.func_82580_o("temperature"); changed = true; }
         if (comp.func_82582_d()) { is.field_77990_d = null; changed = true; }

      }
    }
    return changed;
  }


  public static Boolean setTemp(ItemStack is, float temp) {
    if (is != null) {

      if (is.func_77942_o()) {
        is.func_77978_p().func_74776_a("temperature", temp);
      } else if (isCookable(is) != -1.0F) {

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.func_74776_a("temperature", temp);
        is.func_77982_d(nbt);
      }
    } else {

      return Boolean.valueOf(false);
    }
    if (temp <= 0.0F) {
      removeTempTag(is);
    }
    return Boolean.valueOf(true);
  }


  public static void removeTempTag(ItemStack is) {
    if (is.func_77942_o() && is.func_77978_p().func_74764_b("temperature"))
    {
      is.func_77978_p().func_82580_o("temperature");
    }
    if (is.func_77942_o() && is.func_77978_p().func_82582_d())
      is.field_77990_d = null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\TFC_ItemHeat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */