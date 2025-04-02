package com.bioxx.tfc.Entities.AI;

import com.bioxx.tfc.api.Entities.IAnimal;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.Vec3;



public class EntityAIAvoidEntityTFC
  extends EntityAIBase
{
  public final IEntitySelector field_98218_a = new EntityAIAvoidEntitySelectorTFC(this);
  
  private EntityCreature theEntity;
  
  private double farSpeed;
  
  private double nearSpeed;
  
  private Entity closestLivingEntity;
  
  private float distanceFromEntity;
  
  private PathEntity entityPathEntity;
  
  private PathNavigate entityPathNavigate;
  
  private Class targetEntityClass;

  
  public EntityAIAvoidEntityTFC(EntityCreature par1EntityCreature, Class par2Class, float par3, double par4, double par6) {
    this.theEntity = par1EntityCreature;
    this.targetEntityClass = par2Class;
    this.distanceFromEntity = par3;
    this.farSpeed = par4;
    this.nearSpeed = par6;
    this.entityPathNavigate = par1EntityCreature.func_70661_as();
    func_75248_a(1);
  }





  
  public boolean func_75250_a() {
    if (this.targetEntityClass == EntityPlayer.class) {
      
      if (this.theEntity instanceof IAnimal && ((IAnimal)this.theEntity).checkFamiliarity(IAnimal.InteractionEnum.TOLERATEPLAYER, null)) {
        return false;
      }
      if (this.theEntity instanceof EntityTameable && ((EntityTameable)this.theEntity).func_70909_n())
      {
        return false;
      }
    } 
    
    List<Entity> list = this.theEntity.field_70170_p.func_82733_a(this.targetEntityClass, this.theEntity.field_70121_D.func_72314_b(this.distanceFromEntity, 3.0D, this.distanceFromEntity), this.field_98218_a);
    
    if (list.isEmpty()) {
      return false;
    }
    this.closestLivingEntity = list.get(0);
    
    Vec3 vec3 = RandomPositionGenerator.func_75461_b(this.theEntity, 16, 7, Vec3.func_72443_a(this.closestLivingEntity.field_70165_t, this.closestLivingEntity.field_70163_u, this.closestLivingEntity.field_70161_v));
    if (vec3 == null)
      return false; 
    if (this.closestLivingEntity.func_70092_e(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c) < this.closestLivingEntity.func_70068_e((Entity)this.theEntity)) {
      return false;
    }
    
    this.entityPathEntity = this.entityPathNavigate.func_75488_a(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c);
    return (this.entityPathEntity == null) ? false : this.entityPathEntity.func_75880_b(vec3);
  }






  
  public boolean func_75253_b() {
    return !this.entityPathNavigate.func_75500_f();
  }





  
  public void func_75249_e() {
    this.entityPathNavigate.func_75484_a(this.entityPathEntity, this.farSpeed);
  }





  
  public void func_75251_c() {
    this.closestLivingEntity = null;
  }





  
  public void func_75246_d() {
    if (this.theEntity.func_70068_e(this.closestLivingEntity) < 49.0D) {
      this.theEntity.func_70661_as().func_75489_a(this.nearSpeed);
    } else {
      this.theEntity.func_70661_as().func_75489_a(this.farSpeed);
    } 
  }
  
  public static EntityCreature getEntity(EntityAIAvoidEntityTFC par0EntityAIAvoidEntity) {
    return par0EntityAIAvoidEntity.theEntity;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAIAvoidEntityTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */