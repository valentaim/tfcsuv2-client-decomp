package com.bioxx.tfc.Entities.Mobs;

import net.minecraft.entity.IEntityLivingData;

public class EntityHorseGroupData
  implements IEntityLivingData
{
  public int horseType;
  public int horseVariant;
  
  public EntityHorseGroupData(int par1, int par2) {
    this.horseType = par1;
    this.horseVariant = par2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityHorseGroupData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */