/*    */ package com.bioxx.tfc.Entities.AI;
/*    */ 
/*    */ import com.bioxx.tfc.api.Entities.IAnimal;
/*    */ import net.minecraft.entity.EntityCreature;
/*    */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityAIAttackTFC
/*    */   extends EntityAINearestAttackableTarget
/*    */ {
/*    */   public EntityAIAttackTFC(IAnimal par1EntityTameable, Class par2Class, int par3, boolean par4) {
/* 14 */     super((EntityCreature)par1EntityTameable, par2Class, par3, par4);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\AI\EntityAIAttackTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */