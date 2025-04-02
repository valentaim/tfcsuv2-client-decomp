package com.bioxx.tfc.api;


public class HeatRaw
{
  public float specificHeat;
  public float meltTemp;

  public HeatRaw(double sh, double melt) {
    this.specificHeat = (float)sh;
    this.meltTemp = (float)melt;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\HeatRaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */