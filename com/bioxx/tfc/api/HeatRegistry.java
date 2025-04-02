package com.bioxx.tfc.api;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;



public class HeatRegistry
{
  private static final HeatRegistry INSTANCE = new HeatRegistry();

  public static final HeatRegistry getInstance() {
    return INSTANCE;
  }


  private List<HeatIndex> heatList;

  private HeatRegistry() {
    this.heatList = new ArrayList<>();
  }


  public void addIndex(HeatIndex index) {
    this.heatList.add(index);
  }


  public List<HeatIndex> getHeatList() {
    return this.heatList;
  }


  public HeatIndex findMatchingIndex(ItemStack input) {
    for (int k = 0; k < this.heatList.size(); k++) {

      HeatIndex tempIndex = this.heatList.get(k);
      if (tempIndex.matches(input))
      {
        return tempIndex;
      }
    }

    return null;
  }


  public Boolean getIsLiquid(ItemStack is) {
    HeatIndex hi = INSTANCE.findMatchingIndex(is);
    if (hi != null && is.func_77942_o()) {

      float temp = 0.0F;
      if (is.func_77978_p().func_74764_b("temperature")) {
        temp = is.func_77978_p().func_74760_g("temperature");
      }
      return Boolean.valueOf((temp >= hi.meltTemp));
    }
    return Boolean.valueOf(false);
  }



  public float getMeltingPoint(ItemStack is) {
    HeatIndex hi = findMatchingIndex(is);
    if (hi != null)
    {
      return hi.meltTemp;
    }
    return -1.0F;
  }




  public Boolean isTemperatureWeldable(ItemStack is) {
    if (TFC_ItemHeat.hasTemp(is)) {

      HeatIndex index = INSTANCE.findMatchingIndex(is);
      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        return Boolean.valueOf((temp < index.meltTemp && temp > index.meltTemp * 0.8D));
      }
    }
    return Boolean.valueOf(false);
  }


  public Boolean isTemperatureWorkable(ItemStack is) {
    if (TFC_ItemHeat.hasTemp(is)) {

      HeatIndex index = INSTANCE.findMatchingIndex(is);
      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        return Boolean.valueOf((temp < index.meltTemp && temp > index.meltTemp * 0.6D));
      }
    }
    return Boolean.valueOf(false);
  }


  public Boolean isTemperatureDanger(ItemStack is) {
    if (TFC_ItemHeat.hasTemp(is)) {

      HeatIndex index = INSTANCE.findMatchingIndex(is);
      if (index != null) {

        float temp = TFC_ItemHeat.getTemp(is);
        return Boolean.valueOf((temp < index.meltTemp && temp > index.meltTemp * 0.9D));
      }
    }
    return Boolean.valueOf(false);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\HeatRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */