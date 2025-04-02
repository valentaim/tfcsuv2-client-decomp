package com.bioxx.tfc.api.Enums;

public enum EnumWeight
{
  LIGHT("Light", 4),
  
  MEDIUM("Medium", 2),
  
  HEAVY("Heavy", 1);
  public final int multiplier;
  
  static {
    WEIGHTS = new EnumWeight[] { LIGHT, MEDIUM, HEAVY };
  }
  private final String name; private static final EnumWeight[] WEIGHTS;
  
  EnumWeight(String s, int i) {
    this.name = s;
    this.multiplier = i;
  }

  
  public String getName() {
    return this.name;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumWeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */