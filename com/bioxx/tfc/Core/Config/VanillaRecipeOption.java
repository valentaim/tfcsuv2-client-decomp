/*    */ package com.bioxx.tfc.Core.Config;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCCrafting;
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.crafting.CraftingManager;
/*    */ import net.minecraft.item.crafting.IRecipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class VanillaRecipeOption
/*    */   extends SyncingOption
/*    */ {
/*    */   public final ImmutableList<IRecipe> recipes;
/*    */   
/*    */   public VanillaRecipeOption(String name, ItemStack... toBeRemoved) throws NoSuchFieldException, IllegalAccessException {
/* 22 */     super(name, TFCCrafting.class, TFC_ConfigFiles.getCraftingConfig(), "Enable Vanilla Recipes");
/* 23 */     if (toBeRemoved.length == 0) throw new IllegalArgumentException("No items for removal " + name); 
/* 24 */     ImmutableList.Builder<IRecipe> builder = new ImmutableList.Builder();
/*    */     
/* 26 */     for (Object recipe : CraftingManager.func_77594_a().func_77592_b()) {
/*    */       
/* 28 */       if (recipe == null)
/* 29 */         continue;  for (ItemStack out : toBeRemoved) {
/*    */         
/* 31 */         if (ItemStack.func_77989_b(out, ((IRecipe)recipe).func_77571_b())) {
/*    */           
/* 33 */           builder.add(recipe);
/*    */           break;
/*    */         } 
/*    */       } 
/*    */     } 
/* 38 */     this.recipes = builder.build();
/*    */     
/* 40 */     CraftingManager.func_77594_a().func_77592_b().removeAll((Collection<?>)this.recipes);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ImmutableList<IRecipe> getRecipes() {
/* 46 */     return this.recipes;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 52 */     return this.name + "[default:" + this.defaultValue + " current:" + isAplied() + " config:" + inConfig() + " #ofRecipes:" + this.recipes.size() + "]";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Config\VanillaRecipeOption.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */