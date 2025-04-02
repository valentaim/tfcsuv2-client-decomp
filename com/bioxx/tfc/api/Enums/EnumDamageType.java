package com.bioxx.tfc.api.Enums;

public enum EnumDamageType
{
  GENERIC(-1),
  PIERCING(0),
  SLASHING(1),
  CRUSHING(2);

  public int damageID;


  EnumDamageType(int id) {
    this.damageID = id;
  }



  public String toString() {
    switch (this) {
      case PIERCING:
        return "gui.DamageType.Piercing";
      case SLASHING: return "gui.DamageType.Slashing";
      case CRUSHING: return "gui.DamageType.Crushing";
    }  return "gui.DamageType.Error";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumDamageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */