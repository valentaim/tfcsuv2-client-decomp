package com.bioxx.tfc.Entities.AI;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.passive.EntityTameable;

public class EntityAISitTFC
  extends EntityAISit {
  private EntityTameable theEntity;
  private boolean isSitting;
  
  public EntityAISitTFC(EntityTameable theEntity) {
    super(theEntity);
    this.theEntity = theEntity;
    func_75248_a(5);
  }






  
  public boolean func_75250_a() {
    if (!this.theEntity.func_70909_n() && !this.theEntity.func_70906_o())
    {
      return false;
    }
    if (this.theEntity.func_70090_H())
    {
      return false;
    }
    if (!this.theEntity.field_70122_E)
    {
      return false;
    }

    
    EntityLivingBase entitylivingbase = this.theEntity.func_70902_q();
    return (entitylivingbase == null) ? true : ((this.theEntity.func_70068_e((Entity)entitylivingbase) < 144.0D && entitylivingbase.func_70643_av() != null) ? ((this.isSitting && !this.theEntity.func_70909_n())) : this.isSitting);
  }






  
  public void func_75249_e() {
    super.func_75249_e();
    this.theEntity.func_70661_as().func_75499_g();
    this.theEntity.func_70904_g(true);
  }





  
  public void func_75251_c() {
    super.func_75251_c();
    this.theEntity.func_70904_g(false);
  }





  
  public void func_75270_a(boolean sitting) {
    super.func_75270_a(sitting);
    this.isSitting = sitting;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAISitTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */