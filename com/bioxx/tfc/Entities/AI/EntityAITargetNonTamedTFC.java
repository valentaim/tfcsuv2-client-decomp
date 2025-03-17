/*    */ package com.bioxx.tfc.Entities.AI;
/*    */ 
/*    */ import com.bioxx.tfc.api.Entities.IAnimal;
/*    */ import net.minecraft.entity.ai.EntityAITargetNonTamed;
/*    */ import net.minecraft.entity.passive.EntityTameable;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAITargetNonTamedTFC
/*    */   extends EntityAITargetNonTamed
/*    */ {
/*    */   private EntityTameable entityTameable;
/*    */   private Class targetClass;
/*    */   
/*    */   public EntityAITargetNonTamedTFC(EntityTameable entity, Class targetClass, int targetChance, boolean shouldCheckSight) {
/* 18 */     super(entity, targetClass, targetChance, shouldCheckSight);
/* 19 */     this.targetClass = targetClass;
/* 20 */     this.entityTameable = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75250_a() {
/* 29 */     if (this.entityTameable instanceof IAnimal) {
/*    */ 
/*    */       
/* 32 */       IAnimal animal = (IAnimal)this.entityTameable;
/* 33 */       int familiarity = animal.getFamiliarity();
/* 34 */       if (this.targetClass == EntityPlayer.class && animal.checkFamiliarity(IAnimal.InteractionEnum.TOLERATEPLAYER, null))
/*    */       {
/* 36 */         return false;
/*    */       }
/* 38 */       if (familiarity > 0 && this.field_75299_d.func_70681_au().nextInt(familiarity) != 0) {
/* 39 */         return false;
/*    */       }
/*    */     } 
/* 42 */     return super.func_75250_a();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAITargetNonTamedTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */