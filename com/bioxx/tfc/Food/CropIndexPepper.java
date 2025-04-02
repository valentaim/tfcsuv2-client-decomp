package com.bioxx.tfc.Food;

import com.bioxx.tfc.TileEntities.TECrop;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class CropIndexPepper
  extends CropIndex
{
  public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, Item seed) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
  }

  public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }

  public CropIndexPepper(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed, int[] nutriRestore) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientExtraRestore = (int[])nutriRestore.clone();
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }



  public ItemStack getOutput1(TECrop crop) {
    if (this.output1 != null && crop.growth >= 5.0F && crop.growth < 6.0F) {

      ItemStack is = new ItemStack(this.output1);
      Random r = new Random();
      if (r.nextInt(100) < this.chanceForOutput1) {

        ItemFoodTFC.createTag(is, getWeight(this.output1Avg, r));
        return is;
      }
    }
    return null;
  }


  public ItemStack getOutput2(TECrop crop) {
    if (this.output2 != null && crop.growth >= 6.0F) {

      ItemStack is = new ItemStack(this.output2);
      Random r = new Random();
      if (r.nextInt(100) < this.chanceForOutput2) {

        ItemFoodTFC.createTag(is, getWeight(this.output2Avg, r));
        return is;
      }
    }
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\CropIndexPepper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */