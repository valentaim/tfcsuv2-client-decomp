/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class QuernRecipe
/*    */ {
/*    */   private ItemStack inItemStack;
/*    */   private ItemStack outItemStack;
/*    */   
/*    */   public QuernRecipe(ItemStack inIS, ItemStack outIS) {
/* 12 */     this.inItemStack = inIS;
/* 13 */     this.outItemStack = outIS;
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean isInItem(ItemStack is) {
/* 18 */     return Boolean.valueOf((is.func_77973_b() == this.inItemStack.func_77973_b() && is.func_77960_j() == this.inItemStack.func_77960_j() && is.field_77994_a >= this.inItemStack.field_77994_a));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getInItem() {
/* 23 */     return this.inItemStack;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult() {
/* 28 */     return this.outItemStack;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\QuernRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */