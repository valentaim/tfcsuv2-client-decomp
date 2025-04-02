package com.bioxx.tfc.api.Enums;

public enum EnumItemReach
{
  SHORT("Short", 0.75D),

  MEDIUM("Medium", 1.0D),

  FAR("Far", 1.25D);
  public final double multiplier;

  static {
    DISTANCES = new EnumItemReach[] { SHORT, MEDIUM, FAR };
  }
  private final String name; private static final EnumItemReach[] DISTANCES;

  EnumItemReach(String s, double i) {
    this.name = s;
    this.multiplier = i;
  }


  public String getName() {
    return this.name;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumItemReach.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */