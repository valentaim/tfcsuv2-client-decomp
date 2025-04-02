package com.bioxx.tfc.api.Interfaces;

public interface IEquipable {
  EquipType getEquipType(ItemStack paramItemStack);

  void onEquippedRender();

  boolean getTooHeavyToCarry(ItemStack paramItemStack);

  public enum EquipType {
    BACK, NULL;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Interfaces\IEquipable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */