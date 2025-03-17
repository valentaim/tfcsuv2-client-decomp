/*    */ package com.bioxx.tfc.Core.Metal;
/*    */ 
/*    */ import com.bioxx.tfc.api.Metal;
/*    */ 
/*    */ public class AlloyMetalCompare
/*    */   extends AlloyMetal
/*    */ {
/*    */   private float metalMin;
/*    */   private float metalMax;
/*    */   
/*    */   public AlloyMetalCompare(Metal e, float min) {
/* 12 */     super(e, min);
/*    */   }
/*    */ 
/*    */   
/*    */   public AlloyMetalCompare(Metal e, float min, float max) {
/* 17 */     super(e, min);
/* 18 */     this.metalType = e;
/* 19 */     this.metalMin = min;
/* 20 */     this.metalMax = max;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean compare(AlloyMetal b) {
/* 30 */     return (this.metalType == b.metalType && (b.metal == -1.0F || (
/* 31 */       Math.round(b.metal * 1000.0F) >= Math.round(this.metalMin * 1000.0F) && Math.round(b.metal * 1000.0F) <= Math.round(this.metalMax * 1000.0F))));
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMetalMin() {
/* 36 */     return this.metalMin;
/*    */   }
/*    */ 
/*    */   
/*    */   public float getMetalMax() {
/* 41 */     return this.metalMax;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\AlloyMetalCompare.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */