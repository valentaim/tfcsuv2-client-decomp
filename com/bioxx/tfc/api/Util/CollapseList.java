/*    */ package com.bioxx.tfc.api.Util;
/*    */ 
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class CollapseList<E>
/*    */   extends LinkedList<CollapseData>
/*    */ {
/*    */   public boolean add(List<ByteCoord> checkedmap, CollapseData e) {
/* 11 */     if (peekFirst() != null) {
/*    */       
/* 13 */       CollapseData first = peekFirst();
/* 14 */       if (first.coords.equals(e.coords) || checkedmap.contains(e.coords)) {
/* 15 */         return false;
/*    */       }
/* 17 */       addLast(e);
/*    */     } 
/* 19 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\CollapseList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */