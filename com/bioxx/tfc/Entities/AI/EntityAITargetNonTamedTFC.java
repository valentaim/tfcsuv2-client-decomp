package com.bioxx.tfc.Entities.AI;

import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;




public class EntityAITargetNonTamedTFC
  extends EntityAITargetNonTamed
{
  private EntityTameable entityTameable;
  private Class targetClass;

  public EntityAITargetNonTamedTFC(EntityTameable entity, Class targetClass, int targetChance, boolean shouldCheckSight) {
    super(entity, targetClass, targetChance, shouldCheckSight);
    this.targetClass = targetClass;
    this.entityTameable = entity;
  }






  public boolean func_75250_a() {
    if (this.entityTameable instanceof IAnimal) {


      IAnimal animal = (IAnimal)this.entityTameable;
      int familiarity = animal.getFamiliarity();
      if (this.targetClass == EntityPlayer.class && animal.checkFamiliarity(IAnimal.InteractionEnum.TOLERATEPLAYER, null))
      {
        return false;
      }
      if (familiarity > 0 && this.field_75299_d.func_70681_au().nextInt(familiarity) != 0) {
        return false;
      }
    }
    return super.func_75250_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAITargetNonTamedTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */