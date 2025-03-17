/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Render.EntityRendererTFC;
/*     */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.FoodRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ 
/*     */ 
/*     */ public class FoodStatsTFC
/*     */ {
/*  26 */   private boolean updateStats = !TFCOptions.enableDebugMode;
/*     */ 
/*     */   
/*  29 */   public float stomachLevel = 24.0F;
/*  30 */   private float stomachMax = 24.0F;
/*  31 */   private float prevFoodLevel = 24.0F;
/*     */   
/*  33 */   private ResourceLocation drunkBlur = new ResourceLocation("shaders/post/blur.json");
/*  34 */   private ResourceLocation wastedBlur = new ResourceLocation("shaders/post/blur.json");
/*     */   
/*  36 */   public float nutrFruit = 1.0F;
/*  37 */   public float nutrVeg = 1.0F;
/*  38 */   public float nutrGrain = 1.0F;
/*  39 */   public float nutrDairy = 1.0F;
/*  40 */   public float nutrProtein = 1.0F;
/*     */ 
/*     */   
/*     */   private boolean sendUpdate = true;
/*     */ 
/*     */   
/*     */   public long soberTime;
/*     */   
/*     */   private float satisfaction;
/*     */   
/*     */   private float foodExhaustionLevel;
/*     */   
/*     */   public long foodTimer;
/*     */   
/*     */   public long foodHealTimer;
/*     */   
/*  56 */   public float waterLevel = 48000.0F;
/*     */   
/*     */   public long waterTimer;
/*     */   public EntityPlayer player;
/*  60 */   private long nameSeed = Long.MIN_VALUE;
/*     */   
/*     */   private boolean satFruit;
/*     */   private boolean satVeg;
/*     */   private boolean satGrain;
/*     */   private boolean satProtein;
/*     */   private boolean satDairy;
/*     */   
/*     */   public FoodStatsTFC(EntityPlayer player) {
/*  69 */     this.player = player;
/*  70 */     this.waterTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
/*  71 */     this.foodTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
/*  72 */     this.foodHealTimer = Math.max(TFC_Time.getTotalTicks(), TFC_Time.startTime);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate(EntityPlayer player) {
/*  80 */     if (!player.field_70170_p.field_72995_K) {
/*     */       
/*  82 */       BodyTempStats bodyTemp = TFC_Core.getBodyTempStats(player);
/*  83 */       float temp = TFC_Climate.getHeightAdjustedTemp(player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*     */       
/*  85 */       float tempWaterMod = temp;
/*  86 */       if (tempWaterMod >= 25.0F) {
/*  87 */         tempWaterMod = (tempWaterMod - 25.0F) * 0.2F;
/*     */       } else {
/*  89 */         tempWaterMod = 0.0F;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  94 */       if (this.foodTimer < TFC_Time.startTime) {
/*     */         
/*  96 */         this.foodTimer = TFC_Time.startTime;
/*  97 */         this.foodHealTimer = TFC_Time.startTime;
/*  98 */         this.waterTimer = TFC_Time.startTime;
/*     */       } 
/*     */       
/* 101 */       if (TFC_Time.getTotalTicks() - this.foodTimer >= 1000L && !player.field_71075_bZ.field_75098_d && this.updateStats) {
/*     */         
/* 103 */         this.foodTimer += 1000L;
/* 104 */         float drainMult = 1.0F;
/* 105 */         if (player.func_70608_bn())
/*     */         {
/* 107 */           drainMult = 0.5F;
/*     */         }
/*     */         
/* 110 */         if (player.func_70051_ag())
/* 111 */           this.waterLevel -= 5.0F + tempWaterMod; 
/* 112 */         if (!player.field_71075_bZ.field_75098_d && this.updateStats) {
/* 113 */           this.waterLevel -= bodyTemp.getExtraWater() * drainMult;
/*     */         }
/*     */         
/* 116 */         float hunger = (1.0F + this.foodExhaustionLevel + bodyTemp.getExtraFood()) * drainMult;
/* 117 */         if (this.satisfaction >= hunger) {
/*     */           
/* 119 */           this.satisfaction -= hunger;
/* 120 */           hunger = 0.0F;
/* 121 */           this.foodExhaustionLevel = 0.0F;
/*     */         }
/*     */         else {
/*     */           
/* 125 */           hunger -= this.satisfaction;
/* 126 */           this.satisfaction = 0.0F;
/* 127 */           this.foodExhaustionLevel = 0.0F;
/*     */         } 
/* 129 */         this.stomachLevel = Math.max(this.stomachLevel - hunger, 0.0F);
/*     */         
/* 131 */         if (this.satisfaction == 0.0F) {
/*     */           
/* 133 */           this.satProtein = false; this.satFruit = false; this.satVeg = false; this.satDairy = false; this.satGrain = false;
/*     */         } 
/*     */         
/* 136 */         if (this.stomachLevel <= 0.0F) {
/*     */           
/* 138 */           reduceNutrition(0.0024F);
/*     */         }
/* 140 */         else if (this.satisfaction <= 0.0F) {
/*     */           
/* 142 */           reduceNutrition(8.0E-4F);
/*     */         }
/*     */         else {
/*     */           
/* 146 */           if (this.satProtein)
/* 147 */             addNutrition(EnumFoodGroup.Protein, this.satisfaction * (1.0F - this.nutrProtein) / 100.0F, false); 
/* 148 */           if (this.satGrain)
/* 149 */             addNutrition(EnumFoodGroup.Grain, this.satisfaction * (1.0F - this.nutrGrain) / 100.0F, false); 
/* 150 */           if (this.satVeg)
/* 151 */             addNutrition(EnumFoodGroup.Vegetable, this.satisfaction * (1.0F - this.nutrVeg) / 100.0F, false); 
/* 152 */           if (this.satFruit)
/* 153 */             addNutrition(EnumFoodGroup.Fruit, this.satisfaction * (1.0F - this.nutrFruit) / 100.0F, false); 
/* 154 */           if (this.satDairy)
/* 155 */             addNutrition(EnumFoodGroup.Dairy, this.satisfaction * (1.0F - this.nutrDairy) / 100.0F, false); 
/*     */         } 
/* 157 */         this.sendUpdate = true;
/*     */       } 
/*     */ 
/*     */       
/* 161 */       if (TFC_Time.getTotalTicks() - this.foodHealTimer >= 500L) {
/*     */         
/* 163 */         this.foodHealTimer += 500L;
/*     */         
/* 165 */         if (this.stomachLevel >= getMaxStomach(player) / 4.0F && player.func_70996_bM())
/*     */         {
/*     */           
/* 168 */           player.func_70691_i((int)(player.func_110138_aP() * 0.01F));
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 178 */       if (!player.field_71075_bZ.field_75098_d && this.updateStats)
/*     */       {
/* 180 */         for (; this.waterTimer < TFC_Time.getTotalTicks(); this.waterTimer++) {
/*     */ 
/*     */           
/* 183 */           this.waterLevel -= 1.0F + tempWaterMod / 2.0F;
/* 184 */           if (this.waterLevel < 0.0F)
/* 185 */             this.waterLevel = 0.0F; 
/* 186 */           if (!TFC_Core.isPlayerInDebugMode(player) && this.waterLevel == 0.0F && temp > 35.0F) {
/* 187 */             player.func_70097_a((new DamageSource("heatStroke")).func_76348_h().func_151518_m(), 2.0F);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clientUpdate() {
/* 195 */     if ((Minecraft.func_71410_x()).field_71460_t instanceof EntityRendererTFC) {
/*     */       
/* 197 */       EntityRendererTFC erTFC = (EntityRendererTFC)(Minecraft.func_71410_x()).field_71460_t;
/* 198 */       if ((erTFC.getCurrentShaderLocation() == null || !erTFC.getCurrentShaderLocation().equals(this.wastedBlur)) && this.soberTime > TFC_Time.getTotalTicks() + 8000L) {
/*     */         
/* 200 */         erTFC.setManualShader(this.wastedBlur);
/*     */       }
/* 202 */       else if ((erTFC.getCurrentShaderLocation() == null || !erTFC.getCurrentShaderLocation().equals(this.drunkBlur)) && this.soberTime > TFC_Time.getTotalTicks() + 4000L && this.soberTime <= TFC_Time.getTotalTicks() + 8000L) {
/*     */         
/* 204 */         erTFC.setManualShader(this.drunkBlur);
/*     */       }
/* 206 */       else if (erTFC.getManualShaderBeingUsed() && this.soberTime <= TFC_Time.getTotalTicks() + 4000L) {
/*     */         
/* 208 */         erTFC.deactivateManualShader();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void reduceNutrition(float amount) {
/* 215 */     this.nutrFruit = Math.max(this.nutrFruit - amount + this.foodExhaustionLevel, 0.0F);
/* 216 */     this.nutrVeg = Math.max(this.nutrVeg - amount + this.foodExhaustionLevel, 0.0F);
/* 217 */     this.nutrGrain = Math.max(this.nutrGrain - amount + this.foodExhaustionLevel, 0.0F);
/* 218 */     this.nutrProtein = Math.max(this.nutrProtein - amount + this.foodExhaustionLevel, 0.0F);
/* 219 */     this.nutrDairy = Math.max(this.nutrDairy - amount + this.foodExhaustionLevel, 0.0F);
/*     */     
/* 221 */     this.sendUpdate = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxWater(EntityPlayer player) {
/* 226 */     return 48000 + 200 * player.field_71068_ca;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getMaxStomach(EntityPlayer player) {
/* 231 */     return this.stomachMax;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFoodLevel() {
/* 239 */     return this.stomachLevel;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public float getPrevFoodLevel() {
/* 245 */     return this.prevFoodLevel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean needFood() {
/* 253 */     return (this.stomachLevel < getMaxStomach(this.player) && (getMaxStomach(this.player) - this.stomachLevel) > 0.1D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean needDrink() {
/* 258 */     return (this.waterLevel < (getMaxWater(this.player) - 500));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readNBT(NBTTagCompound par1NBTTagCompound) {
/* 266 */     if (par1NBTTagCompound.func_74764_b("foodCompound")) {
/*     */       
/* 268 */       NBTTagCompound foodCompound = par1NBTTagCompound.func_74775_l("foodCompound");
/* 269 */       this.waterLevel = foodCompound.func_74760_g("waterLevel");
/* 270 */       this.stomachLevel = foodCompound.func_74760_g("foodLevel");
/* 271 */       this.foodTimer = foodCompound.func_74763_f("foodTickTimer");
/* 272 */       this.foodHealTimer = foodCompound.func_74763_f("foodHealTimer");
/* 273 */       this.waterTimer = foodCompound.func_74763_f("waterTimer");
/* 274 */       this.soberTime = foodCompound.func_74763_f("soberTime");
/* 275 */       this.satisfaction = foodCompound.func_74760_g("foodSaturationLevel");
/* 276 */       this.foodExhaustionLevel = foodCompound.func_74760_g("foodExhaustionLevel");
/* 277 */       this.nutrFruit = foodCompound.func_74760_g("nutrFruit");
/* 278 */       this.nutrVeg = foodCompound.func_74760_g("nutrVeg");
/* 279 */       this.nutrGrain = foodCompound.func_74760_g("nutrGrain");
/* 280 */       this.nutrProtein = foodCompound.func_74760_g("nutrProtein");
/* 281 */       this.nutrDairy = foodCompound.func_74760_g("nutrDairy");
/* 282 */       this.sendUpdate = foodCompound.func_74767_n("shouldSendUpdate");
/* 283 */       this.satFruit = foodCompound.func_74767_n("satFruit");
/* 284 */       this.satVeg = foodCompound.func_74767_n("satVeg");
/* 285 */       this.satGrain = foodCompound.func_74767_n("satGrain");
/* 286 */       this.satProtein = foodCompound.func_74767_n("satProtein");
/* 287 */       this.satDairy = foodCompound.func_74767_n("satDairy");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeNBT(NBTTagCompound nbt) {
/* 296 */     NBTTagCompound foodNBT = new NBTTagCompound();
/* 297 */     foodNBT.func_74776_a("waterLevel", this.waterLevel);
/* 298 */     foodNBT.func_74776_a("foodLevel", this.stomachLevel);
/* 299 */     foodNBT.func_74772_a("foodTickTimer", this.foodTimer);
/* 300 */     foodNBT.func_74772_a("foodHealTimer", this.foodHealTimer);
/* 301 */     foodNBT.func_74772_a("waterTimer", this.waterTimer);
/* 302 */     foodNBT.func_74772_a("soberTime", this.soberTime);
/* 303 */     foodNBT.func_74776_a("foodSaturationLevel", this.satisfaction);
/* 304 */     foodNBT.func_74776_a("foodExhaustionLevel", this.foodExhaustionLevel);
/* 305 */     foodNBT.func_74776_a("nutrFruit", this.nutrFruit);
/* 306 */     foodNBT.func_74776_a("nutrVeg", this.nutrVeg);
/* 307 */     foodNBT.func_74776_a("nutrGrain", this.nutrGrain);
/* 308 */     foodNBT.func_74776_a("nutrProtein", this.nutrProtein);
/* 309 */     foodNBT.func_74776_a("nutrDairy", this.nutrDairy);
/* 310 */     foodNBT.func_74757_a("shouldSendUpdate", this.sendUpdate);
/* 311 */     foodNBT.func_74757_a("satFruit", this.satFruit);
/* 312 */     foodNBT.func_74757_a("satVeg", this.satVeg);
/* 313 */     foodNBT.func_74757_a("satGrain", this.satGrain);
/* 314 */     foodNBT.func_74757_a("satProtein", this.satProtein);
/* 315 */     foodNBT.func_74757_a("satDairy", this.satDairy);
/* 316 */     nbt.func_74782_a("foodCompound", (NBTBase)foodNBT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addFoodExhaustion(float par1) {
/* 321 */     this.foodExhaustionLevel = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSatisfaction() {
/* 331 */     return this.satisfaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFoodLevel(float par1) {
/* 336 */     if (par1 != this.stomachLevel)
/* 337 */       this.sendUpdate = true; 
/* 338 */     this.stomachLevel = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSatisfaction(float par1, int[] fg) {
/* 344 */     this.satisfaction = Math.min(par1, 10.0F);
/* 345 */     for (int i = 0; i < fg.length; i++) {
/*     */       
/* 347 */       if (fg[i] != -1) {
/*     */         
/* 349 */         EnumFoodGroup efg = FoodRegistry.getInstance().getFoodGroup(fg[i]);
/* 350 */         switch (efg) {
/*     */           case Protein:
/* 352 */             this.satProtein = true; break;
/* 353 */           case Grain: this.satGrain = true; break;
/* 354 */           case Fruit: this.satFruit = true; break;
/* 355 */           case Vegetable: this.satVeg = true; break;
/* 356 */           case Dairy: this.satDairy = true;
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public long getPlayerFoodSeed() {
/* 366 */     if (this.nameSeed == Long.MIN_VALUE) {
/*     */       
/* 368 */       long seed = 0L;
/* 369 */       byte[] nameBytes = this.player.func_70005_c_().getBytes();
/* 370 */       for (byte b : nameBytes)
/* 371 */         seed += b; 
/* 372 */       this.nameSeed = seed + this.player.field_70170_p.func_72905_C();
/*     */     } 
/* 374 */     return this.nameSeed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getPrefTaste() {
/* 382 */     Random r = new Random(getPlayerFoodSeed());
/* 383 */     return new int[] { 20 + r
/* 384 */         .nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70), 20 + r.nextInt(70) };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTasteFactor(ItemStack food) {
/* 390 */     float tasteFactor = 0.85F;
/* 391 */     int[] tastePref = getPrefTaste();
/*     */     
/* 393 */     tasteFactor += getTasteDistanceFactor(tastePref[0], ((IFood)food.func_77973_b()).getTasteSweet(food));
/* 394 */     tasteFactor += getTasteDistanceFactor(tastePref[1], ((IFood)food.func_77973_b()).getTasteSour(food));
/* 395 */     tasteFactor += getTasteDistanceFactor(tastePref[2], ((IFood)food.func_77973_b()).getTasteSalty(food));
/* 396 */     tasteFactor += getTasteDistanceFactor(tastePref[3], ((IFood)food.func_77973_b()).getTasteBitter(food));
/* 397 */     tasteFactor += getTasteDistanceFactor(tastePref[4], ((IFood)food.func_77973_b()).getTasteSavory(food));
/*     */     
/* 399 */     return tasteFactor;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getTasteDistanceFactor(int pref, int val) {
/* 404 */     int abs = Math.abs(pref - val);
/* 405 */     if (abs < 11)
/* 406 */       return (10 - abs) * 0.01F; 
/* 407 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getNutritionHealthModifier() {
/* 412 */     float nMod = 0.0F;
/* 413 */     nMod += 0.2F * this.nutrFruit;
/* 414 */     nMod += 0.2F * this.nutrVeg;
/* 415 */     nMod += 0.2F * this.nutrGrain;
/* 416 */     nMod += 0.2F * this.nutrProtein;
/* 417 */     nMod += 0.2F * this.nutrDairy;
/* 418 */     return Math.max(nMod, 0.05F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getMaxHealth(EntityPlayer player) {
/* 423 */     return 
/* 424 */       (int)(Math.min(1000 + player.field_71068_ca * TFCOptions.healthGainRate, TFCOptions.healthGainCap) * TFC_Core.getPlayerFoodStats(player).getNutritionHealthModifier());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean reduceFood(ItemStack is, float amount) {
/* 433 */     if (is.func_77942_o()) {
/*     */       
/* 435 */       float weight = Food.getWeight(is);
/* 436 */       float decay = Food.getDecay(is);
/* 437 */       if (decay >= 0.0F && weight - decay - amount <= 0.0F)
/* 438 */         return true; 
/* 439 */       if (decay <= 0.0F && weight - amount <= 0.0F) {
/* 440 */         return true;
/*     */       }
/*     */       
/* 443 */       Food.setWeight(is, weight - amount);
/*     */     } 
/*     */     
/* 446 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNutrition(EnumFoodGroup fg, float foodAmt) {
/* 451 */     addNutrition(fg, foodAmt, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addNutrition(EnumFoodGroup fg, float foodAmt, boolean shouldDoMath) {
/* 456 */     float amount = foodAmt;
/* 457 */     if (shouldDoMath)
/* 458 */       amount = foodAmt / 5.0F / 50.0F; 
/* 459 */     switch (fg) {
/*     */       
/*     */       case Dairy:
/* 462 */         this.nutrDairy = Math.min(this.nutrDairy + amount, 1.0F);
/*     */         break;
/*     */       case Fruit:
/* 465 */         this.nutrFruit = Math.min(this.nutrFruit + amount, 1.0F);
/*     */         break;
/*     */       case Grain:
/* 468 */         this.nutrGrain = Math.min(this.nutrGrain + amount, 1.0F);
/*     */         break;
/*     */       case Protein:
/* 471 */         this.nutrProtein = Math.min(this.nutrProtein + amount, 1.0F);
/*     */         break;
/*     */       case Vegetable:
/* 474 */         this.nutrVeg = Math.min(this.nutrVeg + amount, 1.0F);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean shouldSendUpdate() {
/* 483 */     return this.sendUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void restoreWater(EntityPlayer player, int w) {
/* 488 */     this.waterLevel = Math.min(this.waterLevel + w, getMaxWater(player));
/* 489 */     this.sendUpdate = true;
/* 490 */     writeNBT(player.getEntityData());
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTimers() {
/* 495 */     this.waterTimer = TFC_Time.getTotalTicks();
/* 496 */     this.foodTimer = TFC_Time.getTotalTicks();
/* 497 */     this.foodHealTimer = TFC_Time.getTotalTicks();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void consumeAlcohol() {
/* 503 */     if (this.soberTime <= TFC_Time.getTotalTicks()) {
/* 504 */       this.soberTime = TFC_Time.getTotalTicks() + this.player.field_70170_p.field_73012_v.nextInt(1000) + 400L;
/*     */     } else {
/* 506 */       this.soberTime += (this.player.field_70170_p.field_73012_v.nextInt(1000) + 400);
/* 507 */     }  this.sendUpdate = true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\FoodStatsTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */