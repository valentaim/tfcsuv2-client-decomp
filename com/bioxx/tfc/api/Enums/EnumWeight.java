/*    */ package com.bioxx.tfc.api.Enums;
/*    */ 
/*    */ public enum EnumWeight
/*    */ {
/*  5 */   LIGHT("Light", 4),
/*    */   
/*  7 */   MEDIUM("Medium", 2),
/*    */   
/*  9 */   HEAVY("Heavy", 1);
/*    */   public final int multiplier;
/*    */   
/*    */   static {
/* 13 */     WEIGHTS = new EnumWeight[] { LIGHT, MEDIUM, HEAVY };
/*    */   }
/*    */   private final String name; private static final EnumWeight[] WEIGHTS;
/*    */   
/*    */   EnumWeight(String s, int i) {
/* 18 */     this.name = s;
/* 19 */     this.multiplier = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 24 */     return this.name;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumWeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */