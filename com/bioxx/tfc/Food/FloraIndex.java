package com.bioxx.tfc.Food;

import java.util.Random;
import net.minecraft.item.ItemStack;



public class FloraIndex
{
  public String type;
  public int bloomStart;
  public int bloomFinish;
  public int harvestStart;
  public int harvestFinish;
  public int fruitHangTime = 1;
  public float minTemp;
  public float maxTemp = 38.0F;
  public float minBioTemp = 10.0F;
  public float maxBioTemp = 30.0F;
  public float minRain = 125.0F;
  public float maxRain = 2000.0F;
  public float minEVT;
  public float maxEVT = 16.0F;




  public ItemStack output;





  public FloraIndex(String n, int b1, int b2, int h1, int h2, ItemStack o) {
    this.minEVT = 0.25F;
    this.type = n;
    this.bloomStart = b1;
    this.bloomFinish = b2;
    this.harvestStart = h1;
    this.harvestFinish = h2;
    this.output = o;
  }


  public FloraIndex(String n, int h1, int h2, ItemStack o) {
    this.minEVT = 0.25F;
    this.type = n;
    this.bloomStart = 0;
    this.bloomFinish = 0;
    this.harvestStart = h1;
    this.harvestFinish = h2;
    this.output = o;
  }


  public FloraIndex setHangTime(int time) {
    this.fruitHangTime = time;
    return this;
  }


  public FloraIndex setBioTemp(float min, float max) {
    this.minBioTemp = min;
    this.maxBioTemp = max;
    return this;
  }


  public FloraIndex setRain(float min, float max) {
    this.minRain = min;
    this.maxRain = max;
    return this;
  }


  public FloraIndex setEVT(float min, float max) {
    this.minEVT = min;
    this.maxEVT = max;
    return this;
  }


  public ItemStack getOutput(Random r, int i) {
    ItemStack is = this.output.func_77946_l();
    is.field_77994_a += r.nextInt(i);
    return is;
  }


  public ItemStack getOutput() {
    return this.output.func_77946_l();
  }


  public boolean inHarvest(int month) {
    return (month >= this.harvestStart && month <= this.harvestFinish);
  }


  public boolean inBloom(int month) {
    return (month >= this.bloomStart && month <= this.bloomFinish);
  }


  public FloraIndex setTemp(float min, float max) {
    this.minTemp = min;
    this.maxTemp = max;
    return this;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\FloraIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */