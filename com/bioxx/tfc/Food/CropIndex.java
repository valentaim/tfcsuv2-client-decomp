package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TECrop;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.Helper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class CropIndex
{
  public int growthTime;
  public String cropName;
  public int cycleType;
  public int cropId;
  public int numGrowthStages;
  public float minGrowthTemp;
  public float minAliveTemp;
  public float nutrientUsageMult;
  public int[] nutrientExtraRestore;
  public boolean dormantInFrost;
  public int maxLifespan = -1;

  public int chanceForOutput1 = 100;

  public Item output1;
  public float output1Avg;
  public int chanceForOutput2 = 100;

  public Item output2;
  public float output2Avg;
  public boolean needsSunlight = true;
  public float waterUsageMult = 1.0F;

  public Item seedItem;

  public CropIndex(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, Item seed) {
    this.growthTime = growth;
    this.cycleType = type;
    this.cropName = name.toLowerCase();
    this.cropId = id;
    this.numGrowthStages = stages;
    this.minGrowthTemp = minGTemp;
    this.minAliveTemp = minATemp;
    this.nutrientExtraRestore = new int[] { 0, 0, 0 };
    this.nutrientUsageMult = 1.0F;
    this.dormantInFrost = false;
    this.seedItem = seed;
  }

  public CropIndex(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed) {
    this(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }

  public CropIndex(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed, int[] nutriRestore) {
    this(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientExtraRestore = (int[])nutriRestore.clone();
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }


  public CropIndex setOutput1(Item o, float oAvg) {
    this.output1 = o;
    this.output1Avg = oAvg;
    return this;
  }

  public CropIndex setOutput2(Item o, float oAvg) {
    this.output2 = o;
    this.output2Avg = oAvg;
    return this;
  }

  public CropIndex setOutput1Chance(Item o, float oAvg, int chance) {
    this.output1 = o;
    this.output1Avg = oAvg;
    this.chanceForOutput1 = chance;
    return this;
  }

  public CropIndex setOutput2Chance(Item o, float oAvg, int chance) {
    this.output2 = o;
    this.output2Avg = oAvg;
    this.chanceForOutput2 = chance;
    return this;
  }

  public ItemStack getOutput1(TECrop crop) {
    if (this.output1 != null && crop.growth >= this.numGrowthStages) {

      ItemStack is = new ItemStack(this.output1);
      Random r = new Random();
      if (r.nextInt(100) < this.chanceForOutput1) {

        ItemFoodTFC.createTag(is, getWeight(this.output1Avg, r));
        addFlavorProfile(crop, is);
        return is;
      }
    }
    return null;
  }

  public ItemStack getOutput2(TECrop crop) {
    if (this.output2 != null && crop.growth >= this.numGrowthStages) {

      ItemStack is = new ItemStack(this.output2);
      Random r = new Random();
      if (r.nextInt(100) < this.chanceForOutput2) {

        ItemFoodTFC.createTag(is, getWeight(this.output2Avg, r));
        addFlavorProfile(crop, is);
        return is;
      }
    }
    return null;
  }


  private Random getGrowthRand(TECrop te) {
    Block farmBlock = te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e);

    if (!TFC_Core.isSoil(farmBlock)) {


      int soilType1 = (farmBlock == TFCBlocks.tilledSoil) ? te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e) : (te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e) + 16);

      int soilType2 = (farmBlock == TFCBlocks.dirt) ? (te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d - 2, te.field_145849_e) * 2) : ((farmBlock == TFCBlocks.dirt2) ? ((te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d - 2, te.field_145849_e) + 16) * 2) : 0);

      int ph = (TFC_Climate.getCacheManager(te.func_145831_w()).getPHLayerAt(te.field_145851_c, te.field_145849_e)).data1 * 100;
      int drainage = 0;

      for (int y = 2; y < 8; y++) {

        if (TFC_Core.isGravel(te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - y, te.field_145849_e)))
        {
          drainage++;
        }
      }
      drainage *= 1000;

      return new Random((soilType1 + soilType2 + ph + drainage));
    }
    return null;
  }


  private void addFlavorProfile(TECrop te, ItemStack outFood) {
    Random r = getGrowthRand(te);
    if (r != null)
    {
      Food.adjustFlavor(outFood, r);
    }
  }


  public static float getWeight(float average, Random r) {
    float weight = average + average * (10.0F * r.nextFloat() - 5.0F) / 100.0F;
    return Helper.roundNumber(weight, 10.0F);
  }


  public CropIndex setNeedsSunlight(boolean b) {
    this.needsSunlight = b;
    return this;
  }


  public CropIndex setWaterUsage(float m) {
    this.waterUsageMult = m;
    return this;
  }


  public CropIndex setGoesDormant(boolean b) {
    this.dormantInFrost = b;
    return this;
  }


  public ItemStack getSeed() {
    return new ItemStack(this.seedItem, 1);
  }


  public int getCycleType() {
    return this.cycleType;
  }

  public void onCropGrow(float stage) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\CropIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */