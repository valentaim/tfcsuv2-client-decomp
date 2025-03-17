/*     */ package com.bioxx.tfc.api.Crafting;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.Anvil_Skills;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnvilRecipe
/*     */ {
/*     */   public ItemStack result;
/*  18 */   public String plan = "";
/*     */   public ItemStack input1;
/*     */   public ItemStack input2;
/*     */   public boolean flux;
/*     */   public int craftingValue;
/*     */   public int anvilreq;
/*     */   public boolean inheritsDamage;
/*  25 */   public int craftingXP = 1;
/*  26 */   public List<String> skillsList = new ArrayList<>();
/*  27 */   public static int craftingBoundDefault = 50;
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack in2, String p, boolean flux, AnvilReq req, ItemStack result) {
/*  31 */     this(in, in2, p.toLowerCase(), 0, flux, req.Tier, result);
/*  32 */     this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((in != null) ? Item.func_150891_b(in.func_77973_b()) : 0L) + ((result != null) ? Item.func_150891_b(result.func_77973_b()) : 0L))).nextInt(craftingBoundDefault);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack in2, String p, AnvilReq req, ItemStack result) {
/*  37 */     this(in, in2, p.toLowerCase(), 0, false, req.Tier, result);
/*  38 */     this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((in != null) ? Item.func_150891_b(in.func_77973_b()) : 0L) + ((result != null) ? Item.func_150891_b(result.func_77973_b()) : 0L))).nextInt(craftingBoundDefault);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe setCraftingBound(int max) {
/*  43 */     this.craftingValue = 70 + (new Random(TFC_Core.getSuperSeed(AnvilManager.world) + ((this.input1 != null) ? Item.func_150891_b(this.input1.func_77973_b()) : 0L) + ((this.result != null) ? Item.func_150891_b(this.result.func_77973_b()) : 0L))).nextInt(max);
/*  44 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack in2, String p, int cv, boolean flux, int req, ItemStack result) {
/*  49 */     this.input1 = in;
/*  50 */     this.input2 = in2;
/*  51 */     this.flux = flux;
/*  52 */     this.craftingValue = cv;
/*  53 */     this.anvilreq = req;
/*  54 */     this.result = result;
/*  55 */     this.inheritsDamage = false;
/*  56 */     this.plan = p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, AnvilReq req) {
/*  62 */     this(in, p, flux, req.Tier);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, int req) {
/*  67 */     this.input1 = in;
/*  68 */     this.input2 = p;
/*  69 */     this.flux = flux;
/*  70 */     this.anvilreq = req;
/*  71 */     this.inheritsDamage = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack p, String s, boolean flux, int req) {
/*  76 */     this(in, p, flux, req);
/*  77 */     this.plan = s;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack p, boolean flux, AnvilReq req, ItemStack res) {
/*  82 */     this(in, p, req, res);
/*  83 */     this.flux = flux;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe(ItemStack in, ItemStack p, AnvilReq req, ItemStack res) {
/*  88 */     this.input1 = in;
/*  89 */     this.input2 = p;
/*  90 */     this.anvilreq = req.Tier;
/*  91 */     this.result = res;
/*  92 */     this.inheritsDamage = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe clearRecipeSkills() {
/*  97 */     this.skillsList.clear();
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe setCraftingXP(int xp) {
/* 103 */     this.craftingXP = xp;
/* 104 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe setInheritsDamage() {
/* 109 */     this.inheritsDamage = true;
/* 110 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public AnvilRecipe addRecipeSkill(String s) {
/* 115 */     this.skillsList.add(s);
/* 116 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matches(AnvilRecipe recipe) {
/* 124 */     if (areItemStacksEqual(this.input1, recipe.input1) && 
/* 125 */       areItemStacksEqual(this.input2, recipe.input2) && this.plan
/* 126 */       .equals(recipe.plan) && 
/* 127 */       AnvilReq.matches(this.anvilreq, recipe.anvilreq))
/*     */     {
/* 129 */       return (!this.flux || recipe.flux);
/*     */     }
/* 131 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete(AnvilManager am, AnvilRecipe recipe, int[] rules) {
/* 136 */     PlanRecipe pr = am.getPlan(recipe.plan);
/* 137 */     if (areItemStacksEqual(this.input1, recipe.input1) && 
/* 138 */       areItemStacksEqual(this.input2, recipe.input2) && this.plan
/* 139 */       .equals(recipe.plan) && pr.rules[0]
/* 140 */       .matches(rules, 0) && pr.rules[1].matches(rules, 1) && pr.rules[2].matches(rules, 2) && this.craftingValue == recipe.craftingValue && 
/* 141 */       AnvilReq.matches(this.anvilreq, recipe.anvilreq)) {
/* 142 */       if (this.flux && recipe.flux)
/* 143 */         return true; 
/* 144 */       if (!this.flux)
/* 145 */         return true; 
/* 146 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComplete(AnvilRecipe recipe) {
/* 151 */     if (recipe.input1 == this.input1 && recipe.input2 == this.input2 && this.craftingValue == recipe.craftingValue && this.plan
/* 152 */       .equals(recipe.plan) && AnvilReq.matches(this.anvilreq, recipe.anvilreq)) {
/* 153 */       if (this.flux && recipe.flux)
/* 154 */         return true; 
/* 155 */       if (!this.flux)
/* 156 */         return true; 
/* 157 */     }  return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean areItemStacksEqual(ItemStack is1, ItemStack is2) {
/* 162 */     if (is1 != null && is2 != null) {
/*     */       
/* 164 */       if (is1.func_77973_b() != is2.func_77973_b()) {
/* 165 */         return false;
/*     */       }
/* 167 */       if (is1.func_77960_j() != 32767 && is1.func_77960_j() != is2.func_77960_j()) {
/* 168 */         return false;
/*     */       }
/* 170 */     } else if ((is1 == null && is2 != null) || (is1 != null && is2 == null)) {
/* 171 */       return false;
/*     */     } 
/* 173 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult() {
/* 181 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getCraftingResult(ItemStack input) {
/* 189 */     ItemStack is = this.result.func_77946_l();
/* 190 */     if (this.inheritsDamage)
/* 191 */       is.func_77964_b(input.func_77960_j()); 
/* 192 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCraftingValue() {
/* 199 */     return this.craftingValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getSkillMult(EntityPlayer p) {
/* 204 */     float skill = 0.0F;
/* 205 */     float total = 0.0F;
/* 206 */     SkillStats ss = TFC_Core.getSkillStats(p);
/* 207 */     for (String s : this.skillsList) {
/*     */       
/* 209 */       total++;
/* 210 */       Anvil_Skills.Anvil_Skill as = ss.adv_skills.getSkill(s);
/* 211 */       if (as != null && this.anvilreq > 0) { skill += (float)as.math_bonus((byte)(this.anvilreq - 1)); continue; }
/* 212 */        skill += TFC_Core.getSkillStats(p).getSkillMultiplier(s);
/*     */     } 
/* 214 */     if (total > 0.0F) return skill / total;
/*     */     
/* 216 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPlan() {
/* 221 */     return this.plan;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getInput1() {
/* 226 */     return this.input1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getInput2() {
/* 231 */     return this.input2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFlux() {
/* 236 */     return this.flux;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAnvilreq() {
/* 241 */     return this.anvilreq;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInheritsDamage() {
/* 246 */     return this.inheritsDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCraftingXP() {
/* 251 */     return this.craftingXP;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<String> getSkillsList() {
/* 256 */     return this.skillsList;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\AnvilRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */