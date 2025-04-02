package com.bioxx.tfc.Entities.AI;

import com.bioxx.tfc.api.Entities.IAnimal;
import java.util.Iterator;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;




public class EntityAIMateTFC
  extends EntityAIBase
{
  private IAnimal theAnimal;
  private World theWorld;
  private IAnimal targetMate;
  private int matingCounter;
  private float speed;

  public EntityAIMateTFC(IAnimal par1EntityAnimal, World world, float par2) {
    this.matingCounter = 0;
    this.theAnimal = par1EntityAnimal;
    this.theWorld = world;
    this.speed = par2;
    func_75248_a(3);
  }







  public boolean func_75250_a() {
    if (!this.theAnimal.getInLove() || !this.theAnimal.isAdult())
    {
      return false;
    }


    this.targetMate = getLocalMate();
    if (this.targetMate != null)
    {
      if (this.targetMate.getGender() == this.theAnimal.getGender() || this.theAnimal
        .isPregnant() || this.targetMate.isPregnant())
      {
        return false;
      }
    }
    return (this.targetMate != null);
  }








  public boolean func_75253_b() {
    return (this.targetMate.getEntity().func_70089_S() && this.targetMate.getInLove() && this.matingCounter < 60 && this.theAnimal.getInLove() && ((this.targetMate
      .getGender() == IAnimal.GenderEnum.FEMALE && this.theAnimal.getGender() == IAnimal.GenderEnum.MALE) || (this.targetMate
      .getGender() == IAnimal.GenderEnum.MALE && this.theAnimal.getGender() == IAnimal.GenderEnum.FEMALE)));
  }







  public void func_75251_c() {
    this.targetMate = null;
    this.matingCounter = 0;
  }







  public void func_75246_d() {
    this.theAnimal.getEntity().func_70671_ap().func_75651_a((Entity)this.targetMate.getEntity(), 10.0F, this.theAnimal.getEntity().func_70646_bf());
    this.theAnimal.getEntity().func_70661_as().func_75497_a((Entity)this.targetMate.getEntity(), this.speed);
    this.matingCounter++;

    if (this.matingCounter >= 60 && this.theAnimal.getEntity().func_70068_e((Entity)this.targetMate.getEntity()) < 9.0D) {
      this.theAnimal.mate(this.targetMate);
    }
  }


  private IAnimal getLocalMate() {
    float f = 8.0F;
    List list = this.theWorld.func_72872_a(this.theAnimal.getClass(), (this.theAnimal.getEntity()).field_70121_D.func_72314_b(f, f, f));

    for (Iterator<Entity> iterator = list.iterator(); iterator.hasNext(); ) {

      Entity entity = iterator.next();
      if (entity instanceof IAnimal) {

        IAnimal entityanimal = (IAnimal)entity;
        if (this.theAnimal.canMateWith(entityanimal)) {
          return entityanimal;
        }
      }
    }
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAIMateTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */