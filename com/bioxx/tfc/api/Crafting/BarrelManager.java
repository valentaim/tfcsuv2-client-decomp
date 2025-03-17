/*    */ package com.bioxx.tfc.api.Crafting;
/*    */ 
/*    */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.fluids.FluidStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BarrelManager
/*    */ {
/* 13 */   private static final BarrelManager INSTANCE = new BarrelManager();
/*    */   
/*    */   public static final BarrelManager getInstance() {
/* 16 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   private List<BarrelRecipe> recipes;
/*    */   private List<BarrelPreservativeRecipe> preservativeRecipes;
/*    */   
/*    */   private BarrelManager() {
/* 24 */     this.recipes = new ArrayList<>();
/* 25 */     this.preservativeRecipes = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public void addRecipe(BarrelRecipe recipe) {
/* 30 */     this.recipes.add(recipe);
/*    */   }
/*    */   
/*    */   public void addPreservative(BarrelPreservativeRecipe recipe) {
/* 34 */     this.preservativeRecipes.add(recipe);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrelRecipe findMatchingRecipe(ItemStack item, FluidStack fluid, boolean sealed, int techLevel) {
/* 39 */     for (BarrelRecipe recipe : this.recipes) {
/*    */       
/* 41 */       BarrelRecipe br = recipe;
/* 42 */       if (fluid != null && br.matches(item, fluid).booleanValue() && 
/* 43 */         br.sealedRecipe == sealed && br.minTechLevel <= techLevel)
/* 44 */         return br; 
/*    */     } 
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrelPreservativeRecipe findMatchingPreservativeRepice(TEBarrel barrel, ItemStack item, FluidStack fluid, boolean sealed) {
/* 51 */     for (BarrelPreservativeRecipe recipe : this.preservativeRecipes) {
/* 52 */       if (recipe.checkForPreservation(barrel, fluid, item, sealed))
/* 53 */         return recipe; 
/*    */     } 
/* 55 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BarrelRecipe> getRecipes() {
/* 60 */     return this.recipes;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BarrelPreservativeRecipe> getPreservatives() {
/* 65 */     return this.preservativeRecipes;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */