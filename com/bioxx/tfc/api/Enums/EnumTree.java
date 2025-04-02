package com.bioxx.tfc.api.Enums;



public enum EnumTree
{
  OAK("OAK", 500.0F, 1200.0F, 5.0F, 25.0F, 0.25F, 2.0F, false),

  ASPEN("ASPEN", 300.0F, 1600.0F, -5.0F, 18.0F, 0.25F, 1.0F, false),

  BIRCH("BIRCH", 200.0F, 500.0F, -10.0F, 12.0F, 0.0F, 1.0F, false),

  CHESTNUT("CHESTNUT", 250.0F, 16000.0F, 3.0F, 24.0F, 0.0F, 1.0F, false),

  DOUGLASFIR("DOUGLASFIR", 750.0F, 16000.0F, 1.0F, 14.0F, 0.0F, 1.0F, true),

  HICKORY("HICKORY", 250.0F, 16000.0F, 4.0F, 28.0F, 0.0F, 1.0F, false),

  MAPLE("MAPLE", 250.0F, 16000.0F, 3.0F, 20.0F, 0.0F, 1.0F, false),

  ASH("ASH", 250.0F, 16000.0F, 4.0F, 24.0F, 0.5F, 2.0F, false),

  PINE("PINE", 250.0F, 16000.0F, -15.0F, 24.0F, 0.5F, 2.0F, true),

  REDWOOD("REDWOOD", 4000.0F, 16000.0F, 10.0F, 16.0F, 0.0F, 0.5F, true),

  SPRUCE("SPRUCE", 250.0F, 16000.0F, -5.0F, 24.0F, 0.0F, 1.0F, true),

  SYCAMORE("SYCAMORE", 400.0F, 16000.0F, 6.0F, 30.0F, 0.0F, 1.0F, false),

  WHITECEDAR("WHITECEDAR", 250.0F, 16000.0F, -5.0F, 24.0F, 0.0F, 2.0F, true),

  WHITEELM("WHITEELM", 400.0F, 16000.0F, 4.0F, 30.0F, 0.0F, 1.0F, false),

  WILLOW("WILLOW", 4000.0F, 16000.0F, 10.0F, 30.0F, 0.0F, 0.5F, false),

  KAPOK("KAPOK", 2000.0F, 16000.0F, 30.0F, 50.0F, 0.0F, 1.0F, false),



  KOA("KOA", 500.0F, 16000.0F, 28.0F, 50.0F, 2.0F, 2.0F, false),

  UTACACIA("UTACACIA", 75.0F, 1000.0F, 20.0F, 50.0F, 0.0F, 1.0F, false);
  public final float minRain;
  public final float maxRain;
  public final float minTemp;
  public final float maxTemp;
  public final float minEVT;
  public final float maxEVT;
  public final boolean isEvergreen;
  private static final EnumTree[] MATERIALS;

  static {
    MATERIALS = new EnumTree[] { OAK, ASPEN, BIRCH, CHESTNUT, DOUGLASFIR, HICKORY, KOA, ASH, MAPLE, PINE, REDWOOD, SPRUCE, SYCAMORE, UTACACIA, WHITECEDAR, WHITEELM, WILLOW, KAPOK };
  }



  EnumTree(String s, float i, float j, float mintemp, float maxtemp, float minevt, float maxevt, boolean e) {
    this.minRain = i;
    this.maxRain = j;
    this.minTemp = mintemp;
    this.maxTemp = maxtemp;
    this.minEVT = minevt;
    this.maxEVT = maxevt;
    this.isEvergreen = e;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */