/*     */ package com.bioxx.tfc.api.Crafting;
/*     */ 
/*     */ import java.util.Stack;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraftforge.fluids.FluidStack;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ public class BarrelRecipe
/*     */ {
/*     */   public ItemStack recipeIS;
/*     */   public FluidStack recipeFluid;
/*     */   public ItemStack recipeOutIS;
/*     */   public FluidStack recipeOutFluid;
/*  17 */   public int sealTime = 8;
/*     */   public boolean removesLiquid = true;
/*     */   public boolean sealedRecipe = true;
/*  20 */   public int minTechLevel = 1;
/*     */   
/*     */   public boolean allowAnyStack = true;
/*     */   
/*     */   public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid) {
/*  25 */     this.recipeIS = inputItem;
/*  26 */     this.recipeFluid = inputFluid;
/*  27 */     this.recipeOutIS = outIS;
/*  28 */     this.recipeOutFluid = outputFluid;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelRecipe(ItemStack inputItem, FluidStack inputFluid, ItemStack outIS, FluidStack outputFluid, int seal) {
/*  33 */     this(inputItem, inputFluid, outIS, outputFluid);
/*  34 */     this.sealTime = seal;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelRecipe setRemovesLiquid(boolean b) {
/*  39 */     this.removesLiquid = b;
/*  40 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelRecipe setAllowAnyStack(boolean b) {
/*  45 */     this.allowAnyStack = b;
/*  46 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelRecipe setMinTechLevel(int t) {
/*  51 */     this.minTechLevel = t;
/*  52 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public BarrelRecipe setSealedRecipe(boolean b) {
/*  57 */     this.sealedRecipe = b;
/*  58 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean matches(ItemStack item, FluidStack fluid) {
/*  63 */     boolean iStack = this.removesLiquid ? true : ((this.recipeIS != null && item != null && fluid != null && this.recipeFluid != null && item.field_77994_a >= (int)Math.ceil((fluid.amount / this.recipeFluid.amount))));
/*  64 */     boolean fStack = !this.removesLiquid ? true : ((this.recipeFluid != null && item != null && fluid != null && this.recipeOutFluid != null && fluid.amount >= item.field_77994_a * this.recipeOutFluid.amount));
/*     */     
/*  66 */     boolean anyStack = (!this.removesLiquid && !this.sealedRecipe && this.recipeOutIS == null && this.allowAnyStack);
/*  67 */     boolean itemsEqual = ((item == null && this.recipeIS == null) || OreDictionary.itemMatches(this.recipeIS, item, false));
/*     */     
/*  69 */     return Boolean.valueOf((((this.recipeIS != null && itemsEqual && (iStack || anyStack)) || this.recipeIS == null) && ((this.recipeFluid != null && this.recipeFluid
/*  70 */         .isFluidEqual(fluid) && (fStack || anyStack)) || this.recipeFluid == null)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Boolean isInFluid(FluidStack item) {
/*  75 */     return Boolean.valueOf(this.recipeFluid.isFluidEqual(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getInItem() {
/*  80 */     return this.recipeIS;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack getInFluid() {
/*  85 */     return this.recipeFluid;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getRecipeOutIS() {
/*  90 */     return this.recipeOutIS;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack getRecipeOutFluid() {
/*  95 */     return this.recipeOutFluid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSealTime() {
/* 100 */     return this.sealTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRemovesLiquid() {
/* 105 */     return this.removesLiquid;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinTechLevel() {
/* 110 */     return this.minTechLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAllowAnyStack() {
/* 115 */     return this.allowAnyStack;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getRecipeName() {
/* 120 */     String s = "";
/* 121 */     if (this.recipeOutIS != null) {
/*     */       
/* 123 */       if (this.recipeOutIS.field_77994_a > 1)
/* 124 */         s = s + this.recipeOutIS.field_77994_a + "x "; 
/* 125 */       s = s + this.recipeOutIS.func_82833_r();
/*     */     } 
/* 127 */     if (this.recipeOutFluid != null && !this.recipeFluid.isFluidEqual(this.recipeOutFluid))
/* 128 */       s = this.recipeOutFluid.getFluid().getLocalizedName(this.recipeOutFluid); 
/* 129 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSealedRecipe() {
/* 134 */     return this.sealedRecipe;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getnumberOfRuns(ItemStack inIS, FluidStack inFS) {
/* 139 */     int runs = 0;
/* 140 */     int div = 0;
/* 141 */     if (inIS != null && this.recipeIS != null) {
/*     */       
/* 143 */       runs = inIS.field_77994_a / this.recipeIS.field_77994_a;
/* 144 */       div = inFS.amount / (getInFluid()).amount;
/*     */     } 
/* 146 */     return Math.min(runs, div);
/*     */   }
/*     */ 
/*     */   
/*     */   public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 151 */     Stack<ItemStack> stackList = new Stack<>();
/* 152 */     ItemStack outStack = null;
/*     */     
/* 154 */     if (this.recipeOutIS != null) {
/*     */       
/* 156 */       stackList.clear();
/* 157 */       outStack = this.recipeOutIS.func_77946_l();
/* 158 */       int outputCount = outStack.field_77994_a * getnumberOfRuns(inIS, inFS);
/* 159 */       int maxStackSize = outStack.func_77976_d();
/* 160 */       Item item = outStack.func_77973_b();
/* 161 */       int damage = outStack.func_77960_j();
/*     */       
/* 163 */       int remainder = outputCount % maxStackSize;
/* 164 */       if (remainder > 0) {
/*     */         
/* 166 */         stackList.push(new ItemStack(item, remainder, damage));
/* 167 */         outputCount -= remainder;
/*     */       } 
/*     */       
/* 170 */       while (outputCount >= maxStackSize) {
/*     */         
/* 172 */         stackList.push(new ItemStack(item, maxStackSize, damage));
/* 173 */         outputCount -= maxStackSize;
/*     */       } 
/* 175 */       return stackList;
/*     */     } 
/*     */     
/* 178 */     if (!this.removesLiquid && inIS != null && inFS != null) {
/*     */       
/* 180 */       stackList.clear();
/* 181 */       outStack = inIS.func_77946_l();
/* 182 */       outStack.field_77994_a -= inFS.amount / this.recipeOutFluid.amount;
/* 183 */       stackList.push(outStack);
/*     */     } 
/* 185 */     if (outStack == null) {
/*     */       
/* 187 */       stackList.clear();
/* 188 */       stackList.push(outStack);
/*     */     } 
/* 190 */     return stackList;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
/* 195 */     if (this.recipeOutFluid != null) {
/*     */       
/* 197 */       FluidStack fs = null;
/*     */       
/* 199 */       if (this.recipeOutFluid.tag != null) {
/*     */         
/* 201 */         fs = new FluidStack(this.recipeOutFluid.getFluid(), this.recipeOutFluid.amount, (NBTTagCompound)this.recipeOutFluid.tag.func_74737_b());
/*     */       }
/*     */       else {
/*     */         
/* 205 */         fs = new FluidStack(this.recipeOutFluid.getFluid(), this.recipeOutFluid.amount);
/*     */       } 
/*     */       
/* 208 */       if (!this.removesLiquid && inFS != null) {
/*     */         
/* 210 */         fs.amount = inFS.amount;
/*     */       }
/* 212 */       else if (inIS != null) {
/*     */         
/* 214 */         fs.amount *= inIS.field_77994_a;
/*     */       } 
/* 216 */       return fs;
/*     */     } 
/* 218 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\BarrelRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */