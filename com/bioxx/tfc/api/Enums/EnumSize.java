package com.bioxx.tfc.api.Enums;

public enum EnumSize
{
  TINY("Tiny", 64),

  VERYSMALL("Very Small", 32),

  SMALL("Small", 16),

  MEDIUM("Medium", 8),

  LARGE("Large", 4),

  VERYLARGE("Very Large", 2),

  HUGE("Huge", 1);
  public final int stackSize;

  static {
    SIZES = new EnumSize[] { TINY, VERYSMALL, SMALL, MEDIUM, LARGE, VERYLARGE, HUGE };
  }
  private final String name; private static final EnumSize[] SIZES;

  EnumSize(String s, int i) {
    this.name = s;
    this.stackSize = i;
  }


  public String getName() {
    return this.name;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumSize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */