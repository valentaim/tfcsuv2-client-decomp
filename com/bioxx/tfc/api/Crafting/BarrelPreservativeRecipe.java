/*     */ package com.bioxx.tfc.api.Crafting;
/*     */ 
/*     */ import com.bioxx.tfc.TileEntities.TEBarrel;
/*     */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarrelPreservativeRecipe
/*     */ {
/*     */   private boolean requiresBrined;
/*     */   private boolean requiresPickled;
/*     */   private boolean requiresSalted;
/*     */   private boolean requiresDried;
/*     */   private boolean requiresSmoked;
/*     */   private boolean requiresInfused;
/*     */   private boolean requiresSealed;
/*     */   private boolean allowGrains = true;
/*     */   private boolean allowProteins = true;
/*     */   private boolean allowVegetables = true;
/*     */   private boolean allowFruit = true;
/*     */   private boolean allowDairy = true;
/*     */   private FluidStack liquidPerOz;
/*  29 */   private float environmentalDecayFactor = -1.0F;
/*  30 */   private float baseDecayModifier = -1.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String preservingString;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkForPreservation(TEBarrel barrel, FluidStack fluid, ItemStack itemStack, boolean sealed) {
/*  44 */     if (itemStack == null || fluid == null)
/*     */     {
/*  46 */       return false;
/*     */     }
/*  48 */     if (!(itemStack.func_77973_b() instanceof IFood))
/*     */     {
/*  50 */       return false;
/*     */     }
/*  52 */     if (fluid.getFluid() != this.liquidPerOz.getFluid())
/*     */     {
/*  54 */       return false;
/*     */     }
/*  56 */     IFood iFood = (IFood)itemStack.func_77973_b();
/*  57 */     if (!this.allowGrains && iFood.getFoodGroup() == EnumFoodGroup.Grain)
/*     */     {
/*  59 */       return false;
/*     */     }
/*  61 */     if (!this.allowProteins && iFood.getFoodGroup() == EnumFoodGroup.Protein)
/*     */     {
/*  63 */       return false;
/*     */     }
/*  65 */     if (!this.allowFruit && iFood.getFoodGroup() == EnumFoodGroup.Fruit)
/*     */     {
/*  67 */       return false;
/*     */     }
/*  69 */     if (!this.allowVegetables && iFood.getFoodGroup() == EnumFoodGroup.Vegetable)
/*     */     {
/*  71 */       return false;
/*     */     }
/*  73 */     if (!this.allowDairy && iFood.getFoodGroup() == EnumFoodGroup.Dairy)
/*     */     {
/*  75 */       return false;
/*     */     }
/*  77 */     if (this.requiresBrined && !Food.isBrined(itemStack))
/*     */     {
/*  79 */       return false;
/*     */     }
/*  81 */     if (this.requiresPickled && !Food.isPickled(itemStack))
/*     */     {
/*  83 */       return false;
/*     */     }
/*  85 */     if (this.requiresSalted && !Food.isSalted(itemStack))
/*     */     {
/*  87 */       return false;
/*     */     }
/*  89 */     if (this.requiresDried && !Food.isDried(itemStack))
/*     */     {
/*  91 */       return false;
/*     */     }
/*  93 */     if (this.requiresSmoked && !Food.isSmoked(itemStack))
/*     */     {
/*  95 */       return false;
/*     */     }
/*  97 */     if (this.requiresInfused && !Food.isInfused(itemStack))
/*     */     {
/*  99 */       return false;
/*     */     }
/* 101 */     if (this.requiresSealed && !sealed)
/*     */     {
/* 103 */       return false;
/*     */     }
/* 105 */     float w = Food.getWeight(itemStack);
/* 106 */     return (this.liquidPerOz.amount * w <= fluid.amount);
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe(FluidStack liquidPerOz, String unlocalizedPreservingLabel) {
/* 110 */     this.liquidPerOz = liquidPerOz;
/* 111 */     this.preservingString = unlocalizedPreservingLabel;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresBrined(boolean b) {
/* 116 */     this.requiresBrined = b;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresPickled(boolean b) {
/* 122 */     this.requiresPickled = b;
/* 123 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresSalted(boolean b) {
/* 128 */     this.requiresSalted = b;
/* 129 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresDried(boolean b) {
/* 134 */     this.requiresDried = b;
/* 135 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresSmoked(boolean b) {
/* 140 */     this.requiresSmoked = b;
/* 141 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresInfused(boolean b) {
/* 146 */     this.requiresInfused = b;
/* 147 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelPreservativeRecipe setRequiresSealed(boolean b) {
/* 152 */     this.requiresSealed = b;
/* 153 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setAllowProtien(boolean b) {
/* 157 */     this.allowProteins = b;
/* 158 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setAllowGrains(boolean b) {
/* 162 */     this.allowGrains = b;
/* 163 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setAllowFruit(boolean b) {
/* 167 */     this.allowFruit = b;
/* 168 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setAllowVegetable(boolean b) {
/* 172 */     this.allowVegetables = b;
/* 173 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setAllowDairy(boolean b) {
/* 177 */     this.allowDairy = b;
/* 178 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setEnvironmentalDecayFactor(float rate) {
/* 182 */     this.environmentalDecayFactor = rate;
/* 183 */     return this;
/*     */   }
/*     */   
/*     */   public BarrelPreservativeRecipe setBaseDecayModifier(float rate) {
/* 187 */     this.baseDecayModifier = rate;
/* 188 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getEnvironmentalDecayFactor() {
/* 193 */     return this.environmentalDecayFactor;
/*     */   }
/*     */   
/*     */   public float getBaseDecayModifier() {
/* 197 */     return this.baseDecayModifier;
/*     */   }
/*     */   
/*     */   public String getPreservingString() {
/* 201 */     return this.preservingString;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelPreservativeRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */