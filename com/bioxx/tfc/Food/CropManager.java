package com.bioxx.tfc.Food;

import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.List;



public class CropManager
{
  public List<CropIndex> crops;
  protected static final CropManager INSTANCE = new CropManager();


  public static final CropManager getInstance() {
    return INSTANCE;
  }


  public CropManager() {
    this.crops = new ArrayList<>();
  }


  public void addIndex(CropIndex index) {
    this.crops.add(index);
  }


  public int getTotalCrops() {
    return this.crops.size();
  }


  public CropIndex getCropFromName(String n) {
    for (CropIndex pi : this.crops) {
      if (pi.cropName.equalsIgnoreCase(n))
        return pi;
    }  return null;
  }


  public CropIndex getCropFromId(int n) {
    for (CropIndex pi : this.crops) {
      if (pi.cropId == n)
        return pi;
    }  return null;
  }


  static {
    INSTANCE.addIndex((new CropIndex(0, "wheat", 0, 32, 7, 4.0F, 0.0F, 0.9F, TFCItems.seedsWheat)).setOutput1(TFCItems.wheatWhole, 14.0F));

    INSTANCE.addIndex((new CropIndex(1, "maize", 0, 36, 5, 8.0F, 0.0F, 0.8F, TFCItems.seedsMaize)).setOutput1(TFCItems.maizeEar, 32.0F));

    INSTANCE.addIndex((new CropIndex(2, "tomato", 0, 23, 7, 8.0F, 0.0F, 1.2F, TFCItems.seedsTomato)).setWaterUsage(1.2F).setOutput1(TFCItems.tomato, 16.0F));

    INSTANCE.addIndex((new CropIndex(3, "barley", 0, 33, 7, 4.0F, 0.0F, 0.85F, TFCItems.seedsBarley)).setOutput1(TFCItems.barleyWhole, 14.0F));

    INSTANCE.addIndex((new CropIndex(4, "rye", 0, 32, 7, 4.0F, 0.0F, 0.9F, TFCItems.seedsRye)).setOutput1(TFCItems.ryeWhole, 14.0F));

    INSTANCE.addIndex((new CropIndex(5, "oat", 0, 32, 7, 4.0F, 0.0F, 0.9F, TFCItems.seedsOat)).setWaterUsage(1.4F).setOutput1(TFCItems.oatWhole, 14.0F));

    INSTANCE.addIndex((new CropIndex(6, "rice", 1, 32, 7, 4.0F, 0.0F, 0.9F, TFCItems.seedsRice)).setWaterUsage(1.1F).setOutput1(TFCItems.riceWhole, 14.0F));

    INSTANCE.addIndex((new CropIndex(7, "potato", 2, 32, 6, 4.0F, 0.0F, 0.9F, TFCItems.seedsPotato)).setOutput1(TFCItems.potato, 55.0F));

    INSTANCE.addIndex((new CropIndex(8, "onion", 1, 16, 6, 8.0F, 0.0F, 1.2F, TFCItems.seedsOnion)).setOutput1(TFCItems.onion, 36.0F).setGoesDormant(true));

    INSTANCE.addIndex((new CropIndex(9, "cabbage", 1, 29, 5, 10.0F, 0.0F, 0.9F, TFCItems.seedsCabbage)).setWaterUsage(0.9F).setOutput1(TFCItems.cabbage, 32.0F).setGoesDormant(true));

    INSTANCE.addIndex((new CropIndex(10, "garlic", 2, 25, 4, 8.0F, 0.0F, 0.5F, TFCItems.seedsGarlic)).setOutput1(TFCItems.garlic, 20.0F).setGoesDormant(true));

    INSTANCE.addIndex((new CropIndex(11, "carrot", 2, 23, 4, 8.0F, 0.0F, 0.75F, TFCItems.seedsCarrot)).setOutput1(TFCItems.carrot, 30.0F).setGoesDormant(true));

    INSTANCE.addIndex((new CropIndexPepper(12, "yellowbellpepper", 2, 18, 6, 12.0F, 4.0F, 1.2F, TFCItems.seedsYellowBellPepper)).setOutput1(TFCItems.greenBellPepper, 6.0F).setOutput2(TFCItems.yellowBellPepper, 6.0F));
    INSTANCE.addIndex((new CropIndexPepper(13, "redbellpepper", 2, 18, 6, 12.0F, 4.0F, 1.2F, TFCItems.seedsRedBellPepper)).setOutput1(TFCItems.greenBellPepper, 6.0F).setOutput2(TFCItems.redBellPepper, 6.0F));

    INSTANCE.addIndex((new CropIndex(14, "soybean", 1, 25, 6, 8.0F, 0.0F, 1.0F, TFCItems.seedsSoybean, new int[] { 10, 0, 10 })).setOutput1(TFCItems.soybean, 16.0F));

    INSTANCE.addIndex((new CropIndex(15, "greenbean", 1, 24, 6, 8.0F, 0.0F, 1.0F, TFCItems.seedsGreenbean, new int[] { 10, 0, 10 })).setOutput1(TFCItems.greenbeans, 16.0F));

    INSTANCE.addIndex((new CropIndex(16, "squash", 2, 33, 6, 8.0F, 0.0F, 0.9F, TFCItems.seedsSquash)).setOutput1(TFCItems.squash, 16.0F));

    INSTANCE.addIndex((new CropIndexJute(17, "jute", 1, 28, 5, 10.0F, 5.0F, 1.0F, TFCItems.seedsJute)).setOutput1(TFCItems.jute, 2.0F));

    INSTANCE.addIndex((new CropIndex(18, "sugarcane", 1, 96, 7, 18.0F, 12.0F, 0.25F, TFCItems.seedsSugarcane)).setOutput1(TFCItems.sugarcane, 8.0F));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\CropManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */