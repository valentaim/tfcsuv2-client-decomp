package com.bioxx.tfc.api.Enums;

public enum EnumFuelMaterial
{
  ASH("ASH", 696, 1250, new int[] { -15, 12, 0, 0, 0 }),
  ASPEN("ASPEN", 611, 1000, new int[] { 0, -11, 0, 12, 5 }),
  BIRCH("BIRCH", 652, 1750, new int[] { -4, -4, 0, 3, 0 }),
  CHESTNUT("CHESTNUT", 651, 1500, new int[] { 6, -5, 0, -6, -5 }),
  DOUGLASFIR("DOUGLASFIR", 707, 1500, new int[] { -14, 14, 0, 18, -9 }),
  HICKORY("HICKORY", 762, 2000, new int[] { -5, -6, 0, -8, 13 }),
  MAPLE("MAPLE", 745, 2000, new int[] { 8, -4, 0, -5, 5 }),
  OAK("OAK", 728, 2250, new int[] { 0, -8, 0, -8, 12 }),
  PINE("PINE", 627, 1250, new int[] { -19, 23, 0, 21, -19 }),
  REDWOOD("REDWOOD", 612, 1750, new int[] { -13, 9, 0, 12, 6 }),
  SPRUCE("SPRUCE", 608, 1500, new int[] { -17, 16, 0, -9, -13 }),
  SYCAMORE("SYCAMORE", 653, 1750, new int[] { 8, 15, 0, 17, -6 }),
  WHITECEDAR("WHITECEDAR", 625, 1500, new int[] { -5, 9, 0, 16, -3 }),
  WHITEELM("WHITEELM", 647, 1750, new int[] { 0, 0, 0, 0, 0 }),
  WILLOW("WILLOW", 603, 1000, new int[] { -4, -6, 0, 9, -2 }),
  KAPOK("KAPOK", 645, 1000, new int[] { 7, 0, 0, -7, 0 }),
  PEAT("PEAT", 680, 2500, new int[] { -10, 0, 0, 10, 0 }),
  ACACIA("ACACIA", 650, 1000, new int[] { 6, 6, 0, 9, -6 }),
  CHARCOAL("CHARCOAL", 1350, 1800, new int[] { -10, 8, 0, 4, 15 }),
  COAL("COAL", 1400, 2200, new int[] { -18, 13, 0, 20, 12 });

  public final int burnTimeMax;

  public final int burnTempMax;
  public final int[] tasteProfile;

  EnumFuelMaterial(String s, int i, int j, int[] taste) {
    this.burnTempMax = i;
    this.burnTimeMax = j;
    this.tasteProfile = (int[])taste.clone();
  }


  public static int[] getFuelProfile(int ordinal) {
    if (ordinal < 0 || ordinal > (values()).length)
      return new int[] { 0, 0, 0, 0, 0 };
    return (values()[ordinal]).tasteProfile;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumFuelMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */