package com.bioxx.tfc.Core;


public class ColorizerFoliageTFC
{
  private static int[] foliageBuffer = new int[65536];


  public static void setFoliageBiomeColorizer(int[] par0ArrayOfInteger) {
    foliageBuffer = (int[])par0ArrayOfInteger.clone();
  }





  public static int getFoliageColor(double par0, double par2) {
    par2 *= par0;
    int var4 = (int)((1.0D - par0) * 255.0D);
    int var5 = (int)((1.0D - par2) * 255.0D);
    return foliageBuffer[var5 << 8 | var4];
  }





  public static int getFoliageColorPine() {
    return 6396257;
  }





  public static int getFoliageColorBirch() {
    return 8431445;
  }


  public static int getFoliageColorBasic() {
    return 4764952;
  }


  public static int getFoliageDead() {
    return 9004839;
  }


  public static int getFoliageOrange() {
    return 15765504;
  }


  public static int getFoliageYellow() {
    return 16763904;
  }


  public static int getFoliageRed() {
    return 9312280;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\ColorizerFoliageTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */