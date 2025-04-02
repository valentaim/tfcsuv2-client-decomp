package com.bioxx.tfc.Food;

import com.bioxx.tfc.TileEntities.TECrop;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class CropIndexJute
  extends CropIndex
{
  public CropIndexJute(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, Item seed) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
  }

  public CropIndexJute(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }

  public CropIndexJute(int id, String name, int type, int growth, int stages, float minGTemp, float minATemp, float nutrientUsageMultiplier, Item seed, int[] nutriRestore) {
    super(id, name, type, growth, stages, minGTemp, minATemp, seed);
    this.nutrientExtraRestore = (int[])nutriRestore.clone();
    this.nutrientUsageMult = nutrientUsageMultiplier;
  }



  public ItemStack getOutput1(TECrop crop) {
    if (this.output1 != null && crop.growth >= 5.0F)
    {
      return new ItemStack(this.output1, (int)this.output1Avg);
    }
    return null;
  }


  public ItemStack getOutput2(TECrop crop) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\CropIndexJute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */