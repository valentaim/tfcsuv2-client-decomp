/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.oredict.OreDictionary;
/*    */ 
/*    */ 
/*    */ public class LoomRecipe
/*    */ {
/*    */   public ItemStack inItemStack;
/*    */   public ItemStack outItemStack;
/*    */   public int inSize;
/*    */   
/*    */   public LoomRecipe(ItemStack inputItem, ItemStack outIS) {
/* 14 */     this.inItemStack = inputItem;
/* 15 */     this.outItemStack = outIS;
/* 16 */     this.inSize = inputItem.field_77994_a;
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean matches(ItemStack item) {
/* 21 */     boolean iStack = (this.inItemStack != null && item != null && item.field_77994_a == this.inItemStack.field_77994_a);
/*    */     
/* 23 */     boolean itemsEqual = OreDictionary.itemMatches(this.inItemStack, item, false);
/*    */     
/* 25 */     return Boolean.valueOf((iStack && itemsEqual));
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean resultMatches(ItemStack item) {
/* 30 */     boolean iStack = (this.outItemStack != null && item != null && item.field_77994_a == this.outItemStack.field_77994_a);
/*    */     
/* 32 */     boolean itemsEqual = OreDictionary.itemMatches(this.outItemStack, item, false);
/*    */     
/* 34 */     return Boolean.valueOf((iStack && itemsEqual));
/*    */   }
/*    */ 
/*    */   
/*    */   public Boolean partiallyMatches(ItemStack item) {
/* 39 */     boolean iStack = (this.inItemStack != null && item != null);
/*    */     
/* 41 */     boolean itemsEqual = OreDictionary.itemMatches(this.inItemStack, item, false);
/*    */     
/* 43 */     return Boolean.valueOf((iStack && itemsEqual));
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getInItem() {
/* 48 */     return this.inItemStack;
/*    */   }
/*    */   
/*    */   public int getReqSize() {
/* 52 */     return this.inSize;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRecipeName() {
/* 57 */     String s = "";
/* 58 */     if (this.outItemStack != null)
/* 59 */       s = this.outItemStack.func_82833_r(); 
/* 60 */     return s;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getResult(ItemStack inIS) {
/* 65 */     ItemStack is = null;
/* 66 */     if (this.outItemStack != null) {
/*    */       
/* 68 */       is = this.outItemStack.func_77946_l();
/* 69 */       return is;
/*    */     } 
/* 71 */     return is;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getOutItemStack() {
/* 76 */     return this.outItemStack;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\LoomRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */