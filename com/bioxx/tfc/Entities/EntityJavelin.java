package com.bioxx.tfc.Entities;

import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Interfaces.ICausesDamage;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;

public class EntityJavelin
  extends EntityProjectileTFC
  implements ICausesDamage
{
  public EntityJavelin(World par1World) {
    super(par1World);
  }


  public EntityJavelin(World par1World, double i, double j, double k) {
    super(par1World, i, j, k);
  }


  public EntityJavelin(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation) {
    super(world, shooter, victim, force, forceVariation);
  }


  public EntityJavelin(World par1World, EntityLivingBase shooter, float force) {
    super(par1World, shooter, force);
  }



  public EnumDamageType getDamageType() {
    return EnumDamageType.PIERCING;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityJavelin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */