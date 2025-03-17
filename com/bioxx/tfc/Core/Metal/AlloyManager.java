/*    */ package com.bioxx.tfc.Core.Metal;
/*    */ 
/*    */ import com.bioxx.tfc.api.Metal;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class AlloyManager
/*    */ {
/* 11 */   public static final AlloyManager INSTANCE = new AlloyManager();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 18 */   public List<Alloy> alloys = new ArrayList<>();
/*    */ 
/*    */ 
/*    */   
/*    */   public void addAlloy(Alloy a) {
/* 23 */     this.alloys.add(a);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean matches(List<AlloyMetal> ingred) {
/* 28 */     Iterator<Alloy> iter = this.alloys.iterator();
/* 29 */     Alloy match = null;
/* 30 */     while (iter.hasNext() && match == null) {
/*    */       
/* 32 */       match = iter.next();
/* 33 */       match = match.matches(ingred);
/*    */     } 
/* 35 */     return (match != null);
/*    */   }
/*    */ 
/*    */   
/*    */   public Metal matchesAlloy(List<AlloyMetal> ingred, Alloy.EnumTier furnaceTier) {
/* 40 */     Iterator<Alloy> iter = this.alloys.iterator();
/* 41 */     Alloy match = null;
/* 42 */     while (iter.hasNext()) {
/*    */       
/* 44 */       match = iter.next();
/* 45 */       if (furnaceTier.tier >= match.furnaceTier.tier) {
/* 46 */         match = match.matches(ingred);
/*    */       } else {
/* 48 */         match = null;
/*    */       } 
/* 50 */       if (match != null)
/* 51 */         return match.outputType; 
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Metal\AlloyManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */