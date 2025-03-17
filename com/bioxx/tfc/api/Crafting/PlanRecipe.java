/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.RuleEnum;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlanRecipe
/*    */ {
/*    */   public RuleEnum[] rules;
/*    */   public IIcon icon;
/*    */   
/*    */   public PlanRecipe(RuleEnum[] r, IIcon i) {
/* 14 */     this.rules = (RuleEnum[])r.clone();
/* 15 */     this.icon = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlanRecipe(RuleEnum[] r) {
/* 20 */     this.rules = (RuleEnum[])r.clone();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\PlanRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */