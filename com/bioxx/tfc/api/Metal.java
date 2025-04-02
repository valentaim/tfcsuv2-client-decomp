package com.bioxx.tfc.api;

import net.minecraft.item.Item;


public class Metal
{
  public String name;
  public Item meltedItem;
  public Item ingot;
  public boolean canUse = true;

  public Metal(String name) {
    this.name = name;
  }


  public Metal(String name, Item m, Item i) {
    this(name);
    this.meltedItem = m;
    this.ingot = i;
  }


  public Metal(String name, Item m, Item i, boolean use) {
    this(name);
    this.meltedItem = m;
    this.ingot = i;
    this.canUse = use;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Metal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */