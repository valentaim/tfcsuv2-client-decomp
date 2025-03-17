/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class KilnCraftingManager
/*    */ {
/* 10 */   private static final KilnCraftingManager INSTANCE = new KilnCraftingManager();
/*    */   
/*    */   public static final KilnCraftingManager getInstance() {
/* 13 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   private List<KilnRecipe> recipes;
/*    */   
/*    */   private KilnCraftingManager() {
/* 20 */     this.recipes = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRecipe(KilnRecipe recipe) {
/* 25 */     this.recipes.add(recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public KilnRecipe findMatchingRecipe(KilnRecipe recipe) {
/* 30 */     for (int k = 0; k < this.recipes.size(); k++) {
/*    */       
/* 32 */       KilnRecipe irecipe = this.recipes.get(k);
/* 33 */       if (irecipe.matches(recipe))
/*    */       {
/* 35 */         return irecipe;
/*    */       }
/*    */     } 
/*    */     
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack findCompleteRecipe(KilnRecipe recipe) {
/* 44 */     for (int k = 0; k < this.recipes.size(); k++) {
/*    */       
/* 46 */       KilnRecipe irecipe = this.recipes.get(k);
/* 47 */       if (irecipe.isComplete(recipe)) {
/*    */         
/* 49 */         ItemStack out = irecipe.getCraftingResult();
/* 50 */         if (irecipe.getInheritsTag())
/* 51 */           out.func_77982_d(recipe.input1.func_77978_p()); 
/* 52 */         return out;
/*    */       } 
/*    */     } 
/*    */     
/* 56 */     return recipe.input1;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<KilnRecipe> getRecipeList() {
/* 61 */     return this.recipes;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\KilnCraftingManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */