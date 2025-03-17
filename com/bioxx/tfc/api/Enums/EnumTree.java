/*    */ package com.bioxx.tfc.api.Enums;
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumTree
/*    */ {
/*  7 */   OAK("OAK", 500.0F, 1200.0F, 5.0F, 25.0F, 0.25F, 2.0F, false),
/*    */   
/*  9 */   ASPEN("ASPEN", 300.0F, 1600.0F, -5.0F, 18.0F, 0.25F, 1.0F, false),
/*    */   
/* 11 */   BIRCH("BIRCH", 200.0F, 500.0F, -10.0F, 12.0F, 0.0F, 1.0F, false),
/*    */   
/* 13 */   CHESTNUT("CHESTNUT", 250.0F, 16000.0F, 3.0F, 24.0F, 0.0F, 1.0F, false),
/*    */   
/* 15 */   DOUGLASFIR("DOUGLASFIR", 750.0F, 16000.0F, 1.0F, 14.0F, 0.0F, 1.0F, true),
/*    */   
/* 17 */   HICKORY("HICKORY", 250.0F, 16000.0F, 4.0F, 28.0F, 0.0F, 1.0F, false),
/*    */   
/* 19 */   MAPLE("MAPLE", 250.0F, 16000.0F, 3.0F, 20.0F, 0.0F, 1.0F, false),
/*    */   
/* 21 */   ASH("ASH", 250.0F, 16000.0F, 4.0F, 24.0F, 0.5F, 2.0F, false),
/*    */   
/* 23 */   PINE("PINE", 250.0F, 16000.0F, -15.0F, 24.0F, 0.5F, 2.0F, true),
/*    */   
/* 25 */   REDWOOD("REDWOOD", 4000.0F, 16000.0F, 10.0F, 16.0F, 0.0F, 0.5F, true),
/*    */   
/* 27 */   SPRUCE("SPRUCE", 250.0F, 16000.0F, -5.0F, 24.0F, 0.0F, 1.0F, true),
/*    */   
/* 29 */   SYCAMORE("SYCAMORE", 400.0F, 16000.0F, 6.0F, 30.0F, 0.0F, 1.0F, false),
/*    */   
/* 31 */   WHITECEDAR("WHITECEDAR", 250.0F, 16000.0F, -5.0F, 24.0F, 0.0F, 2.0F, true),
/*    */   
/* 33 */   WHITEELM("WHITEELM", 400.0F, 16000.0F, 4.0F, 30.0F, 0.0F, 1.0F, false),
/*    */   
/* 35 */   WILLOW("WILLOW", 4000.0F, 16000.0F, 10.0F, 30.0F, 0.0F, 0.5F, false),
/*    */   
/* 37 */   KAPOK("KAPOK", 2000.0F, 16000.0F, 30.0F, 50.0F, 0.0F, 1.0F, false),
/*    */ 
/*    */ 
/*    */   
/* 41 */   KOA("KOA", 500.0F, 16000.0F, 28.0F, 50.0F, 2.0F, 2.0F, false),
/*    */   
/* 43 */   UTACACIA("UTACACIA", 75.0F, 1000.0F, 20.0F, 50.0F, 0.0F, 1.0F, false);
/*    */   public final float minRain;
/*    */   public final float maxRain;
/*    */   public final float minTemp;
/*    */   public final float maxTemp;
/*    */   public final float minEVT;
/*    */   public final float maxEVT;
/*    */   public final boolean isEvergreen;
/*    */   private static final EnumTree[] MATERIALS;
/*    */   
/*    */   static {
/* 54 */     MATERIALS = new EnumTree[] { OAK, ASPEN, BIRCH, CHESTNUT, DOUGLASFIR, HICKORY, KOA, ASH, MAPLE, PINE, REDWOOD, SPRUCE, SYCAMORE, UTACACIA, WHITECEDAR, WHITEELM, WILLOW, KAPOK };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   EnumTree(String s, float i, float j, float mintemp, float maxtemp, float minevt, float maxevt, boolean e) {
/* 60 */     this.minRain = i;
/* 61 */     this.maxRain = j;
/* 62 */     this.minTemp = mintemp;
/* 63 */     this.maxTemp = maxtemp;
/* 64 */     this.minEVT = minevt;
/* 65 */     this.maxEVT = maxevt;
/* 66 */     this.isEvergreen = e;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Enums\EnumTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */