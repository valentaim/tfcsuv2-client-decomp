/*    */ package com.bioxx.tfc.api;
/*    */ 
/*    */ import net.minecraft.item.Item;
/*    */ 
/*    */ 
/*    */ public class Metal
/*    */ {
/*    */   public String name;
/*    */   public Item meltedItem;
/*    */   public Item ingot;
/*    */   public boolean canUse = true;
/*    */   
/*    */   public Metal(String name) {
/* 14 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public Metal(String name, Item m, Item i) {
/* 19 */     this(name);
/* 20 */     this.meltedItem = m;
/* 21 */     this.ingot = i;
/*    */   }
/*    */ 
/*    */   
/*    */   public Metal(String name, Item m, Item i, boolean use) {
/* 26 */     this(name);
/* 27 */     this.meltedItem = m;
/* 28 */     this.ingot = i;
/* 29 */     this.canUse = use;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Metal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */