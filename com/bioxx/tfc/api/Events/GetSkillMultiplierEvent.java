/*    */ package com.bioxx.tfc.api.Events;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ @Cancelable
/*    */ public class GetSkillMultiplierEvent
/*    */   extends EntityEvent {
/*    */   public final String skillname;
/*    */   public float skillResult;
/*    */   
/*    */   public GetSkillMultiplierEvent(EntityPlayer entity, String skillName, float result) {
/* 15 */     super((Entity)entity);
/* 16 */     this.skillname = skillName;
/* 17 */     this.skillResult = result;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\GetSkillMultiplierEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */