package com.bioxx.tfc.Entities.Mobs;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.world.World;



public class EntitySilverfishTFC
  extends EntitySilverfish
{
  public EntitySilverfishTFC(World par1World) {
    super(par1World);
  }



  protected void func_110147_ax() {
    super.func_110147_ax();
    func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(50.0D);
    func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
  }






  public EnumCreatureAttribute func_70668_bt() {
    return EnumCreatureAttribute.ARTHROPOD;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySilverfishTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */