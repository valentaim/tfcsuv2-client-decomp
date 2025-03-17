/*    */ package com.bioxx.tfc.api.Enums;
/*    */ 
/*    */ public enum EnumDamageType
/*    */ {
/*  5 */   GENERIC(-1),
/*  6 */   PIERCING(0),
/*  7 */   SLASHING(1),
/*  8 */   CRUSHING(2);
/*    */   
/*    */   public int damageID;
/*    */ 
/*    */   
/*    */   EnumDamageType(int id) {
/* 14 */     this.damageID = id;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     switch (this) {
/*    */       case PIERCING:
/* 22 */         return "gui.DamageType.Piercing";
/* 23 */       case SLASHING: return "gui.DamageType.Slashing";
/* 24 */       case CRUSHING: return "gui.DamageType.Crushing";
/* 25 */     }  return "gui.DamageType.Error";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumDamageType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */