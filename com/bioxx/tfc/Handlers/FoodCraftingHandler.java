/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.api.Events.ItemCookEvent;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraftforge.oredict.OreDictionary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FoodCraftingHandler
/*     */ {
/*     */   public static boolean preCrafted;
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onFoodCook(ItemCookEvent event) {}
/*     */   
/*     */   private ItemStack getweightstack(ItemStack is, float weight) {
/*  38 */     ItemStack out = new ItemStack(is.func_77973_b());
/*  39 */     ItemFoodTFC.createTag(out, weight);
/*  40 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onFoodCrafting(PlayerEvent.ItemCraftedEvent e) {
/*  46 */     if (preCrafted) {
/*     */       
/*  48 */       preCrafted = false;
/*     */       
/*     */       return;
/*     */     } 
/*  52 */     ItemStack craftResult = e.crafting;
/*  53 */     IInventory craftingInv = e.craftMatrix;
/*     */     
/*  55 */     if (craftingInv != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  60 */       int num = 0;
/*  61 */       boolean butter = false;
/*  62 */       boolean salt = false;
/*  63 */       boolean knife = false;
/*  64 */       float butterweight = 0.0F;
/*  65 */       float saltweight = 0.0F;
/*  66 */       int saltpos = 0;
/*  67 */       int butterpos = 0; int i;
/*  68 */       for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/*  70 */         ItemStack itemstack = craftingInv.func_70301_a(i);
/*  71 */         if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*  72 */           if (itemstack.func_77977_a().toLowerCase().contains("butter")) { butter = true; butterweight = Food.getWeight(itemstack); butterpos = i; }
/*  73 */           else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { salt = true; saltweight = Food.getWeight(itemstack); saltpos = i; }
/*  74 */           else if (itemstack.func_77977_a().toLowerCase().contains("knife")) { knife = true; }
/*  75 */            num++;
/*     */         } 
/*     */       } 
/*  78 */       if (!knife && butter && salt && num == 2) {
/*     */         
/*  80 */         if (saltweight >= butterweight / 3.0F) {
/*  81 */           ItemStack salt_ = craftingInv.func_70301_a(saltpos);
/*  82 */           Food.setWeight(salt_, saltweight - butterweight / 3.0F);
/*  83 */           TFC_Core.giveItemToPlayer(salt_, e.player);
/*  84 */           if (butterweight + butterweight / 3.0F > 160.0F) TFC_Core.giveItemToPlayer(getweightstack(craftingInv.func_70301_a(butterpos), butterweight + butterweight / 3.0F - 160.0F), e.player);
/*     */         
/*     */         } else {
/*     */           
/*  88 */           ItemStack butter_ = craftingInv.func_70301_a(butterpos);
/*  89 */           Food.setWeight(butter_, Math.min(160.0F, butterweight - saltweight * 3.0F));
/*  90 */           TFC_Core.giveItemToPlayer(butter_, e.player);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*  95 */       for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/*  97 */         ItemStack itemstack = craftingInv.func_70301_a(i);
/*  98 */         if (itemstack != null && 
/*  99 */           itemstack.func_77977_a().toLowerCase().contains("knife")) knife = true; 
/*     */       } 
/* 101 */       if (knife && refiningMilk(craftResult, craftingInv, true)) {
/* 102 */         List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
/* 103 */         handleItem(e.player, craftingInv, knives);
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 108 */       if (refiningGrain(craftResult, craftingInv)) {
/*     */         
/* 110 */         List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
/* 111 */         handleItem(e.player, craftingInv, knives);
/*     */         
/* 113 */         for (int j = 0; j < craftingInv.func_70302_i_(); j++) {
/*     */           
/* 115 */           ItemStack inputStack = craftingInv.func_70301_a(j);
/* 116 */           if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */ 
/*     */             
/* 119 */             float foodWeight = Food.getWeight(inputStack);
/* 120 */             int strawCount = 0;
/*     */             
/* 122 */             for (int k = 0; k < foodWeight; k += 4) {
/* 123 */               strawCount++;
/*     */             }
/* 125 */             TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.straw, strawCount), e.player);
/*     */           } 
/*     */         } 
/* 128 */       } else if (makingDough(craftResult, craftingInv)) {
/*     */         
/* 130 */         for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */           
/* 132 */           ItemStack inputStack = craftingInv.func_70301_a(i);
/* 133 */           if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */ 
/*     */             
/* 136 */             float grainWeight = Food.getWeight(inputStack);
/* 137 */             float grainDecay = Food.getDecay(inputStack);
/* 138 */             if (grainDecay >= 0.0F)
/* 139 */               grainWeight -= grainDecay; 
/* 140 */             grainWeight -= Math.min(grainWeight, 80.0F);
/*     */             
/* 142 */             inputStack = ItemFoodTFC.createTag(inputStack, grainWeight, 0.0F);
/*     */             
/* 144 */             if (grainWeight > 0.0F) {
/*     */               
/* 146 */               inputStack.field_77994_a++;
/* 147 */               if (inputStack.field_77994_a > 2)
/* 148 */                 inputStack.field_77994_a = 2; 
/*     */             } 
/*     */           } 
/*     */         } 
/* 152 */       } else if (craftResult.func_77942_o() && craftResult.func_77978_p().func_74764_b("foodWeight")) {
/*     */         
/* 154 */         craftResult = processFoodInput(e.player, craftResult, craftingInv);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ItemStack processFoodInput(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
/* 169 */     float finalWeight = 0.0F;
/* 170 */     float finalDecay = 0.0F;
/* 171 */     int[] fuelTasteProfile = { 0, 0, 0, 0, 0 };
/* 172 */     int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
/* 173 */     float cookedTime = 0.0F;
/* 174 */     int foodCount = 0;
/* 175 */     int itemCount = 0;
/* 176 */     int foodSlot = 0;
/*     */     
/* 178 */     int milkcount = 0;
/* 179 */     int todelcount = 0; int i;
/* 180 */     for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */       
/* 182 */       ItemStack is = craftingInv.func_70301_a(i);
/* 183 */       if (is != null) {
/*     */         
/* 185 */         itemCount++;
/* 186 */         if (is.func_77973_b() instanceof ItemFoodTFC && is.func_77942_o() && is.func_77978_p().func_74764_b("foodWeight")) {
/*     */           
/* 188 */           foodSlot = i;
/* 189 */           if (foodCount == 0) {
/*     */             
/* 191 */             fuelTasteProfile = Food.getFuelProfile(is);
/* 192 */             cookedTasteProfile = Food.getCookedProfile(is);
/* 193 */             cookedTime = Food.getCooked(is);
/*     */           } 
/*     */           
/* 196 */           float inputWeight = Food.getWeight(is);
/* 197 */           float oldInputWeight = inputWeight;
/* 198 */           float inputDecayPercent = Food.getDecay(is) / oldInputWeight;
/* 199 */           float inputDecay = Food.getDecay(is);
/* 200 */           float weightChange = 0.0F;
/*     */ 
/*     */ 
/*     */           
/* 204 */           if (finalWeight < 160.0F && 
/* 205 */             Food.isSameSmoked(cookedTasteProfile, Food.getCookedProfile(is)) && 
/* 206 */             Food.isSameSmoked(fuelTasteProfile, Food.getFuelProfile(is)) && (
/* 207 */             (int)Food.getCooked(is) - 600) / 120 == ((int)cookedTime - 600) / 120) {
/*     */             
/* 209 */             weightChange = Math.min(160.0F - finalWeight, inputWeight);
/* 210 */             inputWeight -= weightChange;
/* 211 */             finalWeight += weightChange;
/*     */           } 
/*     */ 
/*     */           
/* 215 */           if (inputWeight != oldInputWeight) {
/*     */             
/* 217 */             if (inputWeight == 0.0F) {
/*     */               
/* 219 */               if (finalDecay < 0.0F) {
/*     */                 
/* 221 */                 if (inputDecay > finalDecay) {
/* 222 */                   finalDecay = inputDecay;
/*     */                 }
/*     */               } else {
/* 225 */                 finalDecay += inputDecay;
/*     */               } 
/*     */             } else {
/*     */               
/* 229 */               float decayChange = weightChange * inputDecayPercent;
/* 230 */               inputDecay -= decayChange;
/* 231 */               if (finalDecay < 0.0F) {
/*     */                 
/* 233 */                 if (decayChange > finalDecay) {
/* 234 */                   finalDecay = decayChange;
/*     */                 }
/*     */               } else {
/* 237 */                 finalDecay += decayChange;
/*     */               } 
/* 239 */             }  foodCount++;
/*     */           } 
/*     */           
/* 242 */           milkcount++;
/* 243 */           if (inputWeight > 0.0F) {
/*     */             
/* 245 */             Food.setWeight(is, inputWeight);
/* 246 */             Food.setDecay(is, inputDecay);
/* 247 */             is.field_77994_a++;
/* 248 */             if (is.field_77994_a > 2) is.field_77994_a = 2; 
/* 249 */             todelcount++;
/*     */           } 
/*     */         } 
/*     */       } 
/* 253 */     }  if (milkcount > 1 && (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream") || craftResult
/* 254 */       .func_77973_b().func_77658_a().toLowerCase().contains("yogurt"))) {
/* 255 */       int result = milkcount - todelcount - 1;
/* 256 */       if (result > 0) TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.potteryBowl, result, 1), player);
/*     */     
/*     */     } 
/* 259 */     if (craftResult.field_77994_a == 0) {
/* 260 */       craftResult.field_77994_a = 1;
/*     */     }
/* 262 */     if (itemCount == 1) {
/*     */       
/* 264 */       if (finalDecay > 0.0F)
/*     */       {
/* 266 */         for (i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*     */           
/* 268 */           ItemStack stack = player.field_71071_by.func_70301_a(i);
/*     */           
/* 270 */           if (stack != null && stack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
/*     */ 
/*     */             
/* 273 */             stack.func_77972_a(1, (EntityLivingBase)player);
/* 274 */             if (stack.func_77960_j() >= stack.func_77958_k()) {
/* 275 */               player.field_71071_by.func_70299_a(i, null);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } else {
/* 285 */       for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/* 287 */         ItemStack itemstack = craftingInv.func_70301_a(i);
/* 288 */         if (itemstack != null) {
/*     */           
/* 290 */           boolean fullInv = isInvFull(player);
/*     */           
/* 292 */           if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife && fullInv)
/*     */           {
/* 294 */             if (!preCrafted) {
/*     */ 
/*     */               
/* 297 */               itemstack.field_77994_a++;
/* 298 */               if (itemstack.field_77994_a > 2) {
/* 299 */                 itemstack.field_77994_a = 2;
/*     */               }
/*     */             } 
/*     */           }
/* 303 */           if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife && (!fullInv || !preCrafted))
/*     */           {
/* 305 */             if (finalDecay > 0.0F) {
/*     */               
/* 307 */               damageItem(player, craftingInv, i, itemstack.func_77973_b());
/*     */             }
/* 309 */             else if (finalDecay <= 0.0F) {
/*     */               
/* 311 */               if (finalWeight / 2.0F < 1.0F) {
/*     */ 
/*     */                 
/* 314 */                 itemstack.field_77994_a++;
/* 315 */                 if (itemstack.field_77994_a > 2) {
/* 316 */                   itemstack.field_77994_a = 2;
/*     */                 }
/*     */               } else {
/*     */                 
/* 320 */                 damageItem(player, craftingInv, i, itemstack.func_77973_b());
/*     */                 
/* 322 */                 if (!craftResult.func_77973_b().func_77658_a().toLowerCase().contains("yogurt") && 
/* 323 */                   !craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream")) {
/*     */ 
/*     */                   
/* 326 */                   Food.setWeight(craftingInv.func_70301_a(foodSlot), Helper.roundNumber(finalWeight / 2.0F, 100.0F));
/*     */                   
/* 328 */                   (craftingInv.func_70301_a(foodSlot)).field_77994_a++;
/* 329 */                   if ((craftingInv.func_70301_a(foodSlot)).field_77994_a > 2) (craftingInv.func_70301_a(foodSlot)).field_77994_a = 2; 
/*     */                 } 
/*     */               } 
/*     */             }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 336 */     return craftResult;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean isButter(IInventory craftingInv) {
/* 341 */     boolean butter = false;
/* 342 */     boolean salt = false;
/* 343 */     boolean knife = false;
/* 344 */     int num = 0;
/* 345 */     for (int i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */       
/* 347 */       ItemStack itemstack = craftingInv.func_70301_a(i);
/* 348 */       if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/* 349 */         if (itemstack.func_77977_a().toLowerCase().contains("butter")) { butter = true; }
/* 350 */         else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { salt = true; }
/* 351 */         else if (itemstack.func_77977_a().toLowerCase().contains("knife")) { knife = true; }
/* 352 */          num++;
/*     */       } 
/*     */     } 
/* 355 */     if (!knife && butter && salt && num == 2) return true; 
/* 356 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean haskmilk(IInventory inv) {
/* 361 */     for (int i = 0; i < inv.func_70302_i_(); i++) {
/*     */       
/* 363 */       ItemStack itemstack = inv.func_70301_a(i);
/* 364 */       if (itemstack != null && itemstack.func_77973_b() == TFCItems.woodenBucketMilk) return true; 
/*     */     } 
/* 366 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void updateOutput(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
/* 376 */     if (isButter(craftingInv)) {
/* 377 */       float weight = 0.0F;
/* 378 */       float saltweight = 0.0F;
/* 379 */       for (int j = 0; j < craftingInv.func_70302_i_() || (weight == 0.0F && saltweight == 0.0F); j++) {
/*     */         
/* 381 */         ItemStack itemstack = craftingInv.func_70301_a(j);
/* 382 */         if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood)
/* 383 */           if (itemstack.func_77977_a().toLowerCase().contains("butter")) { weight = Food.getWeight(itemstack); }
/* 384 */           else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { saltweight = Food.getWeight(itemstack); }
/*     */            
/* 386 */       }  if (saltweight >= weight / 3.0F) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 392 */         craftResult = ItemFoodTFC.createTag(craftResult, Math.min(160.0F, weight + weight / 3.0F), Helper.roundNumber(Food.getDecay(craftResult), 100.0F));
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 399 */         craftResult = ItemFoodTFC.createTag(craftResult, Math.min(160.0F, saltweight * 3.0F), Helper.roundNumber(Food.getDecay(craftResult), 100.0F));
/*     */       } 
/*     */       return;
/*     */     } 
/* 403 */     if (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("yogurt")) {
/*     */       
/* 405 */       if (haskmilk(craftingInv)) {
/* 406 */         ItemFoodTFC.createTag(craftResult, 40.0F); return;
/*     */       } 
/* 408 */     } else if (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream")) {
/*     */       
/* 410 */       if (haskmilk(craftingInv)) {
/* 411 */         ItemFoodTFC.createTag(craftResult, 80.0F); return;
/*     */       } 
/*     */     } 
/* 414 */     float finalWeight = 0.0F;
/* 415 */     float finalDecay = 0.0F;
/* 416 */     int sweetMod = -1;
/* 417 */     int sourMod = -1;
/* 418 */     int saltyMod = -1;
/* 419 */     int bitterMod = -1;
/* 420 */     int umamiMod = -1;
/* 421 */     int[] fuelTasteProfile = { 0, 0, 0, 0, 0 };
/* 422 */     int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
/* 423 */     float cookedTime = 0.0F;
/* 424 */     String infusion = null;
/* 425 */     boolean salted = true;
/* 426 */     boolean pickled = true;
/* 427 */     boolean brined = true;
/* 428 */     boolean dried = true;
/* 429 */     int driedAmt = 0;
/* 430 */     int foodCount = 0;
/* 431 */     int itemCount = 0;
/* 432 */     boolean hasknife = false; int i;
/* 433 */     for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */       
/* 435 */       if (craftingInv.func_70301_a(i) != null) {
/*     */         
/* 437 */         itemCount++;
/* 438 */         ItemStack is = craftingInv.func_70301_a(i);
/* 439 */         if (is.func_77973_b() instanceof ItemFoodTFC && is.func_77942_o() && is.func_77978_p().func_74764_b("foodWeight")) {
/*     */           
/* 441 */           if (foodCount == 0) {
/*     */             
/* 443 */             fuelTasteProfile = Food.getFuelProfile(is);
/* 444 */             cookedTasteProfile = Food.getCookedProfile(is);
/* 445 */             cookedTime = Food.getCooked(is);
/* 446 */             infusion = Food.getInfusion(is);
/* 447 */             driedAmt = Food.getDried(is);
/*     */           } 
/* 449 */           if (sweetMod == -1) {
/* 450 */             sweetMod = Food.getSweetMod(is);
/* 451 */           } else if (sweetMod != Food.getSweetMod(is)) {
/* 452 */             sweetMod = 0;
/*     */           } 
/* 454 */           if (sourMod == -1) {
/* 455 */             sourMod = Food.getSourMod(is);
/* 456 */           } else if (sourMod != Food.getSourMod(is)) {
/* 457 */             sourMod = 0;
/*     */           } 
/* 459 */           if (saltyMod == -1) {
/* 460 */             saltyMod = Food.getSaltyMod(is);
/* 461 */           } else if (saltyMod != Food.getSaltyMod(is)) {
/* 462 */             saltyMod = 0;
/*     */           } 
/* 464 */           if (bitterMod == -1) {
/* 465 */             bitterMod = Food.getBitterMod(is);
/* 466 */           } else if (bitterMod != Food.getBitterMod(is)) {
/* 467 */             bitterMod = 0;
/*     */           } 
/* 469 */           if (umamiMod == -1) {
/* 470 */             umamiMod = Food.getSavoryMod(is);
/* 471 */           } else if (umamiMod != Food.getSavoryMod(is)) {
/* 472 */             umamiMod = 0;
/*     */           } 
/* 474 */           float inputWeight = Food.getWeight(is);
/* 475 */           float oldInputWeight = inputWeight;
/* 476 */           float inputDecayPercent = Food.getDecay(is) / oldInputWeight;
/* 477 */           float inputDecay = Food.getDecay(is);
/* 478 */           float weightChange = 0.0F;
/*     */           
/* 480 */           salted = (salted && Food.isSalted(is));
/* 481 */           pickled = (pickled && Food.isPickled(is));
/* 482 */           brined = (brined && Food.isBrined(is));
/* 483 */           dried = (dried && Food.isDried(is));
/*     */ 
/*     */ 
/*     */           
/* 487 */           if (finalWeight < 160.0F && 
/* 488 */             Food.isSameSmoked(cookedTasteProfile, Food.getCookedProfile(is)) && 
/* 489 */             Food.isSameSmoked(fuelTasteProfile, Food.getFuelProfile(is)) && (
/* 490 */             (int)Food.getCooked(is) - 600) / 120 == ((int)cookedTime - 600) / 120) {
/*     */             
/* 492 */             weightChange = Math.min(160.0F - finalWeight, inputWeight);
/* 493 */             inputWeight -= weightChange;
/* 494 */             finalWeight += weightChange;
/*     */           } 
/*     */ 
/*     */           
/* 498 */           if (inputWeight != oldInputWeight) {
/*     */             
/* 500 */             if (inputWeight == 0.0F) {
/*     */               
/* 502 */               if (finalDecay < 0.0F) {
/*     */                 
/* 504 */                 if (inputDecay > finalDecay) {
/* 505 */                   finalDecay = inputDecay;
/*     */                 }
/*     */               } else {
/* 508 */                 finalDecay += inputDecay;
/*     */               } 
/*     */             } else {
/*     */               
/* 512 */               float decayChange = weightChange * inputDecayPercent;
/* 513 */               inputDecay -= decayChange;
/* 514 */               if (finalDecay < 0.0F) {
/*     */                 
/* 516 */                 if (decayChange > finalDecay) {
/* 517 */                   finalDecay = decayChange;
/*     */                 }
/*     */               } else {
/* 520 */                 finalDecay += decayChange;
/*     */               } 
/* 522 */             }  foodCount++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 527 */     if (itemCount == 1) {
/*     */       
/* 529 */       if (finalDecay > 0.0F)
/*     */       {
/* 531 */         for (i = 0; i < player.field_71071_by.func_70302_i_(); i++) {
/*     */           
/* 533 */           if (player.field_71071_by.func_70301_a(i) != null)
/*     */           {
/* 535 */             if (player.field_71071_by.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
/*     */               
/* 537 */               finalWeight -= finalDecay;
/* 538 */               finalDecay = 0.0F;
/*     */ 
/*     */               
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       }
/*     */     } else {
/* 547 */       for (i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/* 549 */         ItemStack inputStack = craftingInv.func_70301_a(i);
/* 550 */         if (inputStack != null)
/*     */         {
/*     */ 
/*     */           
/* 554 */           if (inputStack.func_77973_b() == TFCItems.powder && inputStack.func_77960_j() == 9) {
/*     */             
/* 556 */             salted = true;
/*     */           }
/* 558 */           else if (inputStack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {
/*     */             
/* 560 */             hasknife = true;
/* 561 */             if (finalDecay > 0.0F) {
/*     */               
/* 563 */               finalWeight -= finalDecay;
/* 564 */               finalDecay = 0.0F;
/*     */             }
/* 566 */             else if (finalDecay <= 0.0F) {
/*     */               
/* 568 */               if (!refiningGrain(craftResult, craftingInv) && finalWeight / 2.0F >= 1.0F)
/*     */               {
/* 570 */                 if (!refiningMilk(craftResult, craftingInv, false)) finalWeight /= 2.0F;
/*     */               
/*     */               }
/*     */             } 
/* 574 */           } else if (makingDough(craftResult, craftingInv) && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */             
/* 576 */             float grainWeight = Food.getWeight(inputStack);
/* 577 */             float grainDecay = Food.getDecay(inputStack);
/* 578 */             if (grainDecay >= 0.0F)
/* 579 */               grainWeight -= grainDecay; 
/* 580 */             float doughWeight = Math.min(grainWeight, 80.0F) * 2.0F;
/* 581 */             finalWeight = doughWeight;
/* 582 */             finalDecay = 0.0F;
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 587 */     craftResult = ItemFoodTFC.createTag(craftResult, Helper.roundNumber(finalWeight, 100.0F), Helper.roundNumber(finalDecay, 100.0F));
/* 588 */     if (sweetMod != 0)
/* 589 */       Food.setSweetMod(craftResult, sweetMod); 
/* 590 */     if (sourMod != 0)
/* 591 */       Food.setSourMod(craftResult, sourMod); 
/* 592 */     if (saltyMod != 0)
/* 593 */       Food.setSaltyMod(craftResult, saltyMod); 
/* 594 */     if (bitterMod != 0)
/* 595 */       Food.setBitterMod(craftResult, bitterMod); 
/* 596 */     if (umamiMod != 0) {
/* 597 */       Food.setSavoryMod(craftResult, umamiMod);
/*     */     }
/* 599 */     if (cookedTime > 0.0F) {
/* 600 */       Food.setCooked(craftResult, cookedTime);
/*     */     }
/* 602 */     for (int fuelTaste : fuelTasteProfile) {
/*     */       
/* 604 */       if (fuelTaste > 0) {
/*     */         
/* 606 */         Food.setFuelProfile(craftResult, fuelTasteProfile);
/*     */         break;
/*     */       } 
/*     */     } 
/* 610 */     for (int cookedTaste : cookedTasteProfile) {
/*     */       
/* 612 */       if (cookedTaste > 0) {
/*     */         
/* 614 */         Food.setCookedProfile(craftResult, cookedTasteProfile);
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 619 */     if (salted)
/* 620 */       Food.setSalted(craftResult, salted); 
/* 621 */     if (pickled)
/* 622 */       Food.setPickled(craftResult, pickled); 
/* 623 */     if (brined) {
/* 624 */       Food.setBrined(craftResult, brined);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 629 */     if (dried)
/* 630 */     { Food.setDried(craftResult, 4); }
/* 631 */     else if (hasknife || itemCount == 1)
/* 632 */     { if (driedAmt > 0) Food.setDried(craftResult, driedAmt);  }
/* 633 */     else if (Food.isDried(craftResult)) { ((NBTTagCompound)craftResult.func_77978_p().func_74781_a("Processing Tag")).func_82580_o("Dried"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 641 */     if (infusion != null) {
/* 642 */       Food.setInfusion(craftResult, infusion);
/*     */     }
/* 644 */     if (craftResult.field_77994_a == 0) {
/* 645 */       craftResult.field_77994_a = 1;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean refiningMilk(ItemStack craftResult, IInventory iinventory, boolean delete) {
/* 652 */     return ((craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream") && gridHasItem(iinventory, "icecream", delete)) || (craftResult
/* 653 */       .func_77973_b().func_77658_a().toLowerCase().contains("yogurt") && gridHasItem(iinventory, "yogurt", delete)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean refiningGrain(ItemStack craftResult, IInventory iinventory) {
/* 659 */     return ((craftResult.func_77973_b() == TFCItems.wheatGrain && gridHasItem(iinventory, TFCItems.wheatWhole)) || (craftResult
/* 660 */       .func_77973_b() == TFCItems.ryeGrain && gridHasItem(iinventory, TFCItems.ryeWhole)) || (craftResult
/* 661 */       .func_77973_b() == TFCItems.oatGrain && gridHasItem(iinventory, TFCItems.oatWhole)) || (craftResult
/* 662 */       .func_77973_b() == TFCItems.barleyGrain && gridHasItem(iinventory, TFCItems.barleyWhole)) || (craftResult
/* 663 */       .func_77973_b() == TFCItems.riceGrain && gridHasItem(iinventory, TFCItems.riceWhole)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean makingDough(ItemStack craftResult, IInventory iinventory) {
/* 668 */     return ((craftResult.func_77973_b() == TFCItems.wheatDough || craftResult.func_77973_b() == TFCItems.ryeDough || craftResult.func_77973_b() == TFCItems.oatDough || craftResult
/* 669 */       .func_77973_b() == TFCItems.barleyDough || craftResult.func_77973_b() == TFCItems.cornmealDough || craftResult
/* 670 */       .func_77973_b() == TFCItems.riceDough) && (
/* 671 */       gridHasItem(iinventory, TFCItems.woodenBucketWater) || gridHasItem(iinventory, TFCItems.redSteelBucketWater)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isInvFull(EntityPlayer player) {
/* 676 */     for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
/*     */       
/* 678 */       if (player.field_71071_by.field_70462_a[i] == null)
/* 679 */         return false; 
/*     */     } 
/* 681 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void preCraft(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
/* 690 */     if (isButter(craftingInv))
/* 691 */       return;  preCrafted = true;
/* 692 */     if (refiningGrain(craftResult, craftingInv)) {
/*     */       
/* 694 */       List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
/* 695 */       handleItem(player, craftingInv, knives);
/* 696 */       for (int i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/* 698 */         ItemStack inputStack = craftingInv.func_70301_a(i);
/* 699 */         if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */ 
/*     */           
/* 702 */           float foodWeight = Food.getWeight(inputStack);
/* 703 */           int strawCount = 0;
/*     */           
/* 705 */           for (int j = 0; j < foodWeight; j += 4) {
/* 706 */             strawCount++;
/*     */           }
/* 708 */           TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.straw, strawCount), player);
/*     */         } 
/*     */       } 
/* 711 */     } else if (makingDough(craftResult, craftingInv)) {
/*     */       
/* 713 */       for (int i = 0; i < craftingInv.func_70302_i_(); i++) {
/*     */         
/* 715 */         ItemStack inputStack = craftingInv.func_70301_a(i);
/* 716 */         if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */ 
/*     */           
/* 719 */           float grainWeight = Food.getWeight(inputStack);
/* 720 */           float grainDecay = Food.getDecay(inputStack);
/* 721 */           if (grainDecay >= 0.0F)
/* 722 */             grainWeight -= grainDecay; 
/* 723 */           float doughWeight = Math.min(grainWeight, 80.0F);
/* 724 */           grainWeight -= doughWeight;
/*     */           
/* 726 */           inputStack = ItemFoodTFC.createTag(inputStack, Helper.roundNumber(grainWeight, 100.0F), 0.0F);
/*     */           
/* 728 */           if (grainWeight > 0.0F)
/* 729 */             inputStack.field_77994_a++; 
/*     */         } 
/*     */       } 
/* 732 */     } else if (craftResult.func_77942_o() && craftResult.func_77978_p().func_74764_b("foodWeight")) {
/*     */       
/* 734 */       craftResult = processFoodInput(player, craftResult, craftingInv);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean gridHasItem(IInventory iinventory, Item id) {
/* 740 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 742 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 744 */         if (iinventory.func_70301_a(i).func_77973_b() == id)
/* 745 */           return true;  } 
/*     */     } 
/* 747 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean gridHasItem(IInventory iinventory, String id, boolean delete) {
/* 752 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 754 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 756 */         if (iinventory.func_70301_a(i).func_77973_b().func_77658_a().toLowerCase().contains(id)) {
/*     */           
/* 758 */           if (delete) iinventory.func_70299_a(i, null); 
/* 759 */           return true;
/*     */         }  } 
/*     */     } 
/* 762 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, Item[] items) {
/* 768 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 770 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 772 */         for (int j = 0; j < items.length; j++)
/* 773 */           damageItem(entityplayer, iinventory, i, items[j]); 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, List<ItemStack> items) {
/* 779 */     for (int i = 0; i < iinventory.func_70302_i_(); i++) {
/*     */       
/* 781 */       if (iinventory.func_70301_a(i) != null)
/*     */       {
/* 783 */         for (ItemStack is : items)
/* 784 */           damageItem(entityplayer, iinventory, i, is.func_77973_b()); 
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void damageItem(EntityPlayer entityplayer, IInventory iinventory, int i, Item item) {
/* 790 */     if (iinventory.func_70301_a(i).func_77973_b() == item) {
/*     */       
/* 792 */       int index = i;
/* 793 */       ItemStack is = iinventory.func_70301_a(index).func_77946_l();
/* 794 */       if (is != null) {
/*     */         
/* 796 */         is.func_77972_a(1, (EntityLivingBase)entityplayer);
/* 797 */         if (is.func_77960_j() != 0 || entityplayer.field_71075_bZ.field_75098_d) {
/*     */           
/* 799 */           iinventory.func_70299_a(index, is);
/* 800 */           (iinventory.func_70301_a(index)).field_77994_a++;
/* 801 */           if ((iinventory.func_70301_a(index)).field_77994_a > 2)
/* 802 */             (iinventory.func_70301_a(index)).field_77994_a = 2; 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\FoodCraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */