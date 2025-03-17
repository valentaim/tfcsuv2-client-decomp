/*     */ package com.bioxx.tfc.Food;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFCTabs;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.Render.Item.FoodItemRenderer;
/*     */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.FoodRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.ICookableFood;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.Interfaces.IMergeableFood;
/*     */ import com.bioxx.tfc.api.Interfaces.ISize;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.IIcon;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
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
/*     */ public class ItemFoodTFC
/*     */   extends ItemTerra
/*     */   implements ISize, ICookableFood, IMergeableFood
/*     */ {
/*     */   private EnumFoodGroup foodgroup;
/*     */   public int foodID;
/*  58 */   public float decayRate = 1.0F;
/*     */   
/*     */   public boolean edible = true;
/*     */   
/*     */   public boolean canBeUsedRaw = true;
/*     */   
/*     */   protected int tasteSweet;
/*     */   protected int tasteSour;
/*     */   protected int tasteSalty;
/*     */   protected int tasteBitter;
/*     */   protected int tasteUmami;
/*     */   protected boolean canBeSmoked;
/*     */   protected float smokeAbsorb;
/*     */   public IIcon cookedIcon;
/*     */   protected boolean hasCookedIcon;
/*     */   
/*     */   public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um) {
/*  75 */     func_77637_a(TFCTabs.TFC_FOODS);
/*  76 */     setFolder("food/");
/*  77 */     this.foodgroup = fg;
/*  78 */     TFCItems.foodList.add(this);
/*  79 */     func_77656_e(100);
/*  80 */     this.field_77787_bX = false;
/*  81 */     this.smokeAbsorb = 0.5F;
/*  82 */     this.tasteSweet = sw;
/*  83 */     this.tasteSour = so;
/*  84 */     this.tasteSalty = sa;
/*  85 */     this.tasteBitter = bi;
/*  86 */     this.tasteUmami = um;
/*  87 */     this.foodID = FoodRegistry.getInstance().registerFood(fg, (Item)this);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um, boolean edible) {
/*  92 */     this(fg, sw, so, sa, bi, um);
/*  93 */     this.edible = edible;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFoodTFC(EnumFoodGroup fg, int sw, int so, int sa, int bi, int um, boolean edible, boolean usable) {
/*  98 */     this(fg, sw, so, sa, bi, um, edible);
/*  99 */     this.canBeUsedRaw = usable;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFoodTFC setDecayRate(float f) {
/* 104 */     this.decayRate = f;
/* 105 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFoodTFC setCanSmoke() {
/* 110 */     this.canBeSmoked = true;
/* 111 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemFoodTFC setHasCookedIcon() {
/* 116 */     this.hasCookedIcon = true;
/* 117 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/* 123 */     super.func_94581_a(registerer);
/* 124 */     if (this.hasCookedIcon) {
/*     */       
/* 126 */       String name = func_77658_a();
/* 127 */       this.cookedIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + name.replace("item.", "") + " Cooked");
/*     */     } 
/* 129 */     MinecraftForgeClient.registerItemRenderer((Item)this, (IItemRenderer)new FoodItemRenderer());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IIcon getIcon(ItemStack stack, int pass) {
/* 135 */     if (Food.isCooked(stack) && this.cookedIcon != null)
/* 136 */       return this.cookedIcon; 
/* 137 */     return this.field_77791_bV;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_77623_v() {
/* 144 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRenderPasses(int metadata) {
/* 154 */     return 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDecayRate(ItemStack is) {
/* 172 */     float mult = 1.0F;
/* 173 */     if (Food.isCooked(is)) {
/*     */       
/* 175 */       mult *= 0.75F;
/* 176 */       if (Food.isPickled(is) || Food.isSalted(is))
/* 177 */         mult *= 0.75F; 
/* 178 */       if (Food.isSmoked(is)) {
/* 179 */         mult *= 1.0F - 0.25F * getSmokeAbsorbMultiplier();
/*     */       }
/*     */     } else {
/*     */       
/* 183 */       if (Food.isPickled(is) || Food.isSalted(is))
/* 184 */         mult *= 0.5F; 
/* 185 */       if (Food.isSmoked(is))
/* 186 */         mult *= 1.0F - 0.25F * getSmokeAbsorbMultiplier(); 
/* 187 */       if (Food.isDried(is))
/* 188 */         mult *= 0.25F; 
/*     */     } 
/* 190 */     return this.decayRate * TFC_Time.getYearRatio(96.0F) * mult;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 196 */     list.add(createTag(new ItemStack((Item)this, 1), 160.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_77653_i(ItemStack is) {
/* 202 */     StringBuilder name = new StringBuilder();
/* 203 */     if (is.func_77942_o()) {
/*     */       
/* 205 */       if (Food.isPickled(is)) {
/* 206 */         name.append(TFC_Core.translate("word.pickled")).append(' ');
/* 207 */       } else if (Food.isBrined(is) && !Food.isDried(is)) {
/* 208 */         name.append(TFC_Core.translate("word.brined")).append(' ');
/*     */       } 
/* 210 */       if (Food.isSalted(is))
/* 211 */         name.append(TFC_Core.translate("word.salted")).append(' '); 
/* 212 */       if (Food.isCooked(is)) {
/* 213 */         name.append(TFC_Core.translate("word.cooked")).append(' ');
/* 214 */       } else if (Food.isSmoked(is)) {
/* 215 */         name.append(TFC_Core.translate("word.smoked")).append(' ');
/*     */       } 
/* 217 */       if (Food.isDried(is) && !Food.isCooked(is))
/* 218 */         name.append(TFC_Core.translate("word.dried")).append(' '); 
/* 219 */       if (Food.isInfused(is)) {
/* 220 */         name.append(TFC_Core.translate(Food.getInfusion(is) + ".name")).append(' ');
/*     */       }
/*     */     } 
/* 223 */     return name.append(TFC_Core.translate(func_77667_c(is) + ".name")).append(getCookedLevelString(is)).toString();
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getCookedLevelString(ItemStack is) {
/* 228 */     String s = "";
/* 229 */     if (Food.isCooked(is)) {
/*     */       
/* 231 */       s = s + " (";
/* 232 */       int cookedLevel = ((int)Food.getCooked(is) - 600) / 120;
/* 233 */       switch (cookedLevel) {
/*     */         case 0:
/* 235 */           s = s + TFC_Core.translate("food.cooked.0"); break;
/* 236 */         case 1: s = s + TFC_Core.translate("food.cooked.1"); break;
/* 237 */         case 2: s = s + TFC_Core.translate("food.cooked.2"); break;
/* 238 */         case 3: s = s + TFC_Core.translate("food.cooked.3"); break;
/* 239 */         default: s = s + TFC_Core.translate("food.cooked.4"); break;
/*     */       } 
/* 241 */       s = s + ")";
/*     */     } 
/* 243 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void addFoodHeatInformation(ItemStack is, List<String> arraylist) {
/* 248 */     if (TFC_ItemHeat.hasTemp(is)) {
/*     */       
/* 250 */       float meltTemp = TFC_ItemHeat.isCookable(is);
/* 251 */       if (meltTemp != -1.0F) {
/* 252 */         arraylist.add(TFC_ItemHeat.getHeatColorFood(TFC_ItemHeat.getTemp(is), meltTemp));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/* 259 */     ItemTerra.addSizeInformation(is, arraylist);
/* 260 */     arraylist.add(getFoodGroupName(getFoodGroup()));
/*     */     
/* 262 */     if (is.func_77942_o()) {
/*     */       
/* 264 */       addFoodHeatInformation(is, arraylist);
/* 265 */       addFoodInformation(is, player, arraylist);
/*     */     }
/*     */     else {
/*     */       
/* 269 */       arraylist.add(TFC_Core.translate("gui.badnbt"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFoodInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 278 */     float ounces = Helper.roundNumber(Food.getWeight(is), 100.0F);
/* 279 */     if (ounces > 0.0F && ounces <= 160.0F) {
/* 280 */       arraylist.add(TFC_Core.translate("gui.food.amount") + " " + ounces + " oz / " + 160.0F + " oz");
/*     */     }
/* 282 */     float decay = Food.getDecay(is);
/* 283 */     if (decay > 0.0F)
/* 284 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + " " + Helper.roundNumber(decay / ounces * 100.0F, 10.0F) + "%"); 
/* 285 */     if (TFCOptions.enableDebugMode) {
/*     */       
/* 287 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + ": " + decay);
/* 288 */       arraylist.add(EnumChatFormatting.DARK_GRAY + "Decay Rate: " + Helper.roundNumber(getDecayRate(is), 100.0F));
/*     */     } 
/*     */     
/* 291 */     if (TFC_Core.showCtrlInformation()) {
/* 292 */       addTasteInformation(is, player, arraylist);
/*     */     } else {
/* 294 */       arraylist.add(TFC_Core.translate("gui.showtaste"));
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void addTasteInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 299 */     IFood food = (IFood)is.func_77973_b();
/* 300 */     int sweet = food.getTasteSweet(is);
/* 301 */     int sour = food.getTasteSour(is);
/* 302 */     int salty = food.getTasteSalty(is);
/* 303 */     int bitter = food.getTasteBitter(is);
/* 304 */     int savory = food.getTasteSavory(is);
/* 305 */     SkillStats.SkillRank rank = TFC_Core.getSkillStats(player).getSkillRank("skill.cooking");
/* 306 */     if (Food.hasMealSkill(is)) {
/* 307 */       rank = SkillStats.SkillRank.values()[Food.getMealSkill(is)];
/*     */     }
/* 309 */     int[] prefs = TFC_Core.getPlayerFoodStats(player).getPrefTaste();
/*     */     
/* 311 */     String sSweet = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.sweet") + ": " + EnumChatFormatting.WHITE;
/* 312 */     String sSour = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.sour") + ": " + EnumChatFormatting.WHITE;
/* 313 */     String sSalty = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.salty") + ": " + EnumChatFormatting.WHITE;
/* 314 */     String sBitter = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.bitter") + ": " + EnumChatFormatting.WHITE;
/* 315 */     String sSavory = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.taste.savory") + ": " + EnumChatFormatting.WHITE;
/*     */     
/* 317 */     if (rank == SkillStats.SkillRank.Novice) {
/*     */       
/* 319 */       sSweet = sSweet + ((sweet > prefs[0]) ? TFC_Core.translate("gui.taste.novice.sweet1") : TFC_Core.translate("gui.taste.novice.sweet0"));
/* 320 */       sSour = sSour + ((sour > prefs[1]) ? TFC_Core.translate("gui.taste.novice.sour1") : TFC_Core.translate("gui.taste.novice.sour0"));
/* 321 */       sSalty = sSalty + ((salty > prefs[2]) ? TFC_Core.translate("gui.taste.novice.salty1") : TFC_Core.translate("gui.taste.novice.salty0"));
/* 322 */       sBitter = sBitter + ((bitter > prefs[3]) ? TFC_Core.translate("gui.taste.novice.bitter1") : TFC_Core.translate("gui.taste.novice.bitter0"));
/* 323 */       sSavory = sSavory + ((savory > prefs[4]) ? TFC_Core.translate("gui.taste.novice.savory1") : TFC_Core.translate("gui.taste.novice.savory0"));
/*     */     }
/* 325 */     else if (rank == SkillStats.SkillRank.Adept) {
/*     */       
/* 327 */       sSweet = sSweet + createBasicString(sweet, prefs[0], "sweet");
/* 328 */       sSour = sSour + createBasicString(sour, prefs[1], "sour");
/* 329 */       sSalty = sSalty + createBasicString(salty, prefs[2], "salty");
/* 330 */       sBitter = sBitter + createBasicString(bitter, prefs[3], "bitter");
/* 331 */       sSavory = sSavory + createBasicString(savory, prefs[4], "savory");
/*     */     }
/* 333 */     else if (rank == SkillStats.SkillRank.Expert) {
/*     */       
/* 335 */       sSweet = sSweet + createExpertString(sweet, prefs[0], "sweet");
/* 336 */       sSour = sSour + createExpertString(sour, prefs[1], "sour");
/* 337 */       sSalty = sSalty + createExpertString(salty, prefs[2], "salty");
/* 338 */       sBitter = sBitter + createExpertString(bitter, prefs[3], "bitter");
/* 339 */       sSavory = sSavory + createExpertString(savory, prefs[4], "savory");
/*     */     }
/* 341 */     else if (rank == SkillStats.SkillRank.Master) {
/*     */       
/* 343 */       sSweet = sSweet + createBasicString(sweet, prefs[0], "sweet") + " (" + (sweet - prefs[0]) + ")";
/* 344 */       sSour = sSour + createBasicString(sour, prefs[1], "sour") + " (" + (sour - prefs[1]) + ")";
/* 345 */       sSalty = sSalty + createBasicString(salty, prefs[2], "salty") + " (" + (salty - prefs[2]) + ")";
/* 346 */       sBitter = sBitter + createBasicString(bitter, prefs[3], "bitter") + " (" + (bitter - prefs[3]) + ")";
/* 347 */       sSavory = sSavory + createBasicString(savory, prefs[4], "savory") + " (" + (savory - prefs[4]) + ")";
/*     */     } 
/*     */     
/* 350 */     arraylist.add(sSweet);
/* 351 */     arraylist.add(sSour);
/* 352 */     arraylist.add(sSalty);
/* 353 */     arraylist.add(sBitter);
/* 354 */     arraylist.add(sSavory);
/*     */   }
/*     */ 
/*     */   
/*     */   private static String createExpertString(int val, int pref, String name) {
/* 359 */     int abs = Math.abs(val - pref);
/*     */     
/* 361 */     String out = createBasicString(val, pref, name);
/*     */     
/* 363 */     if (abs <= 5) {
/* 364 */       out = out + " (+-5)";
/* 365 */     } else if (abs <= 10) {
/* 366 */       out = out + " (+-10)";
/* 367 */     } else if (abs <= 15) {
/* 368 */       out = out + " (+-15)";
/* 369 */     } else if (abs <= 20) {
/* 370 */       out = out + " (+-20)";
/*     */     } 
/* 372 */     return out;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String createBasicString(int val, int pref, String name) {
/* 377 */     int dif = val - pref;
/*     */     
/* 379 */     if (dif >= -5 && dif <= 5)
/* 380 */       return TFC_Core.translate("gui.taste.4") + " " + TFC_Core.translate("gui.taste." + name); 
/* 381 */     if (dif >= -10 && dif < -5)
/* 382 */       return TFC_Core.translate("gui.taste.3") + " " + TFC_Core.translate("gui.taste." + name); 
/* 383 */     if (dif >= -15 && dif < -10)
/* 384 */       return TFC_Core.translate("gui.taste.2") + " " + TFC_Core.translate("gui.taste." + name); 
/* 385 */     if (dif >= -20 && dif < -15)
/* 386 */       return TFC_Core.translate("gui.taste.1") + " " + TFC_Core.translate("gui.taste." + name); 
/* 387 */     if (dif < -20)
/* 388 */       return TFC_Core.translate("gui.taste.0") + " " + TFC_Core.translate("gui.taste." + name); 
/* 389 */     if (dif > 5 && dif <= 10)
/* 390 */       return TFC_Core.translate("gui.taste.5") + " " + TFC_Core.translate("gui.taste." + name); 
/* 391 */     if (dif > 10 && dif <= 15)
/* 392 */       return TFC_Core.translate("gui.taste.6") + " " + TFC_Core.translate("gui.taste." + name); 
/* 393 */     if (dif > 15 && dif <= 20)
/* 394 */       return TFC_Core.translate("gui.taste.7") + " " + TFC_Core.translate("gui.taste." + name); 
/* 395 */     if (dif > 20) {
/* 396 */       return TFC_Core.translate("gui.taste.8") + " " + TFC_Core.translate("gui.taste." + name);
/*     */     }
/* 398 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFoodGroupName(EnumFoodGroup fg) {
/* 403 */     if (fg == EnumFoodGroup.Dairy)
/* 404 */       return EnumChatFormatting.WHITE + TFC_Core.translate("gui.food.dairy"); 
/* 405 */     if (fg == EnumFoodGroup.Fruit)
/* 406 */       return EnumChatFormatting.DARK_PURPLE + TFC_Core.translate("gui.food.fruit"); 
/* 407 */     if (fg == EnumFoodGroup.Vegetable)
/* 408 */       return EnumChatFormatting.DARK_GREEN + TFC_Core.translate("gui.food.vegetable"); 
/* 409 */     if (fg == EnumFoodGroup.Protein)
/* 410 */       return EnumChatFormatting.DARK_RED + TFC_Core.translate("gui.food.protein"); 
/* 411 */     if (fg == EnumFoodGroup.Grain) {
/* 412 */       return EnumChatFormatting.YELLOW + TFC_Core.translate("gui.food.grain");
/*     */     }
/* 414 */     return "N/A";
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getFoodGroupColor(EnumFoodGroup fg) {
/* 419 */     if (fg == EnumFoodGroup.Dairy)
/* 420 */       return EnumChatFormatting.WHITE.toString(); 
/* 421 */     if (fg == EnumFoodGroup.Fruit)
/* 422 */       return EnumChatFormatting.DARK_PURPLE.toString(); 
/* 423 */     if (fg == EnumFoodGroup.Vegetable)
/* 424 */       return EnumChatFormatting.DARK_GREEN.toString(); 
/* 425 */     if (fg == EnumFoodGroup.Protein)
/* 426 */       return EnumChatFormatting.DARK_RED.toString(); 
/* 427 */     if (fg == EnumFoodGroup.Grain) {
/* 428 */       return EnumChatFormatting.YELLOW.toString();
/*     */     }
/* 430 */     return "N/A";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/* 436 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/* 437 */     if (foodstats.needFood() && isEdible(is)) {
/* 438 */       player.func_71008_a(is, 32);
/*     */     }
/* 440 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
/* 446 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/* 447 */     if (!world.field_72995_K && isEdible(is))
/*     */     {
/* 449 */       if (is.func_77942_o()) {
/*     */ 
/*     */         
/* 452 */         float weight = Food.getWeight(is);
/* 453 */         float decay = Math.max(Food.getDecay(is), 0.0F);
/*     */         
/* 455 */         float eatAmount = Math.min(weight - decay, 5.0F);
/* 456 */         float stomachDiff = foodstats.stomachLevel + eatAmount - foodstats.getMaxStomach(foodstats.player);
/* 457 */         if (stomachDiff > 0.0F) {
/* 458 */           eatAmount -= stomachDiff;
/*     */         }
/* 460 */         float tasteFactor = foodstats.getTasteFactor(is);
/* 461 */         foodstats.addNutrition(((IFood)is.func_77973_b()).getFoodGroup(), eatAmount * tasteFactor);
/* 462 */         foodstats.stomachLevel += eatAmount * tasteFactor;
/* 463 */         if (FoodStatsTFC.reduceFood(is, eatAmount)) {
/* 464 */           is.field_77994_a = 0;
/*     */         }
/*     */       } else {
/*     */         
/* 468 */         foodstats.addNutrition(((IFood)is.func_77973_b()).getFoodGroup(), 1.0F);
/*     */ 
/*     */         
/* 471 */         String error = TFC_Core.translate("error.error") + " " + is.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");
/*     */ 
/*     */         
/* 474 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentText(error));
/*     */       } 
/*     */     }
/*     */     
/* 478 */     world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/* 479 */     TFC_Core.setPlayerFoodStats(player, foodstats);
/* 480 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack onConsumedByEntity(ItemStack is, World world, EntityLivingBase entity) {
/* 485 */     if (entity instanceof com.bioxx.tfc.api.Entities.IAnimal) {
/*     */       
/* 487 */       if (!world.field_72995_K) {
/*     */         
/* 489 */         float weight = Food.getWeight(is);
/* 490 */         float decay = Math.max(Food.getDecay(is), 0.0F);
/* 491 */         float eatAmount = Math.min(weight - decay, 5.0F);
/* 492 */         if (FoodStatsTFC.reduceFood(is, eatAmount))
/* 493 */           is.field_77994_a = 0; 
/*     */       } 
/* 495 */       world.func_72956_a((Entity)entity, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     } 
/* 497 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHot(ItemStack is) {
/* 502 */     return (TFC_ItemHeat.getTemp(is) > TFC_ItemHeat.isCookable(is) * 0.8D);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack createTag(ItemStack is) {
/* 507 */     return createTag(is, 999.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack createTag(ItemStack is, float weight) {
/* 512 */     if (!is.func_77942_o()) {
/* 513 */       is.func_77982_d(new NBTTagCompound());
/*     */     }
/* 515 */     Food.setWeight(is, weight);
/* 516 */     Food.setDecay(is, -24.0F);
/* 517 */     Food.setDecayTimer(is, (int)TFC_Time.getTotalHours() + 1);
/*     */     
/* 519 */     return is;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack createTag(ItemStack is, float weight, float decay) {
/* 524 */     is = createTag(is, weight);
/* 525 */     Food.setDecay(is, decay);
/* 526 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack is) {
/* 532 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack is) {
/* 538 */     return EnumAction.eat;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDisplayDamage(ItemStack is) {
/* 544 */     float decay = Food.getDecay(is);
/* 545 */     float weight = Food.getWeight(is);
/* 546 */     int percent = (int)(decay / weight * 100.0F);
/* 547 */     percent = (percent > 0) ? ((percent < 100) ? percent : 100) : 0;
/* 548 */     return percent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDamaged(ItemStack is) {
/* 557 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxDamage(ItemStack is) {
/* 563 */     return 100;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 569 */     float weight = Food.getWeight(is);
/* 570 */     if (weight <= 20.0F)
/* 571 */       return EnumSize.TINY; 
/* 572 */     if (weight <= 40.0F)
/* 573 */       return EnumSize.VERYSMALL; 
/* 574 */     if (weight <= 80.0F) {
/* 575 */       return EnumSize.SMALL;
/*     */     }
/* 577 */     return EnumSize.MEDIUM;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 583 */     float weight = Food.getWeight(is);
/* 584 */     if (weight < 80.0F)
/* 585 */       return EnumWeight.LIGHT; 
/* 586 */     if (weight < 160.0F) {
/* 587 */       return EnumWeight.MEDIUM;
/*     */     }
/* 589 */     return EnumWeight.HEAVY;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canStack() {
/* 594 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFoodGroup getFoodGroup() {
/* 600 */     return this.foodgroup;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFoodID() {
/* 606 */     return this.foodID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onDecayed(ItemStack is, World world, int x, int y, int z) {
/* 612 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEdible(ItemStack is) {
/* 618 */     return (this.edible || Food.isCooked(is));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsable(ItemStack is) {
/* 624 */     return (this.canBeUsedRaw || Food.isCooked(is));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSweet(ItemStack is) {
/* 629 */     int base = this.tasteSweet;
/* 630 */     if (is != null && is.func_77942_o()) {
/*     */       
/* 632 */       if (is.func_77978_p().func_74764_b("tasteSweet"))
/* 633 */         base = is.func_77978_p().func_74762_e("tasteSweet"); 
/* 634 */       base += Food.getCookedProfile(is)[0];
/* 635 */       base = (int)(base + Food.getFuelProfile(is)[0] * getSmokeAbsorbMultiplier());
/*     */     } 
/* 637 */     return Math.max(base + Food.getSweetMod(is), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSour(ItemStack is) {
/* 642 */     int base = this.tasteSour;
/* 643 */     if (is != null) {
/*     */       
/* 645 */       if (is.func_77978_p().func_74764_b("tasteSour"))
/* 646 */         base = is.func_77978_p().func_74762_e("tasteSour"); 
/* 647 */       base += Food.getCookedProfile(is)[1];
/* 648 */       base = (int)(base + Food.getFuelProfile(is)[1] * getSmokeAbsorbMultiplier());
/*     */     } 
/* 650 */     if (Food.isBrined(is))
/* 651 */       base += 5; 
/* 652 */     if (Food.isPickled(is))
/* 653 */       base += 30; 
/* 654 */     return Math.max(base + Food.getSourMod(is), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSalty(ItemStack is) {
/* 659 */     int base = this.tasteSalty;
/* 660 */     if (is != null) {
/*     */       
/* 662 */       if (is.func_77978_p().func_74764_b("tasteSalty"))
/* 663 */         base = is.func_77978_p().func_74762_e("tasteSalty"); 
/* 664 */       base += Food.getCookedProfile(is)[2];
/* 665 */       base = (int)(base + Food.getFuelProfile(is)[2] * getSmokeAbsorbMultiplier());
/*     */     } 
/* 667 */     if (Food.isSalted(is))
/* 668 */       base += 40; 
/* 669 */     if (Food.isBrined(is)) {
/* 670 */       base += 10;
/*     */     }
/* 672 */     return Math.max(base + Food.getSaltyMod(is), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteBitter(ItemStack is) {
/* 677 */     int base = this.tasteBitter;
/* 678 */     if (is != null) {
/*     */       
/* 680 */       if (is.func_77978_p().func_74764_b("tasteBitter"))
/* 681 */         base = is.func_77978_p().func_74762_e("tasteBitter"); 
/* 682 */       base += Food.getCookedProfile(is)[3];
/* 683 */       base = (int)(base + Food.getFuelProfile(is)[3] * getSmokeAbsorbMultiplier());
/*     */     } 
/* 685 */     return Math.max(base + Food.getBitterMod(is), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSavory(ItemStack is) {
/* 690 */     int base = this.tasteUmami;
/* 691 */     if (is != null) {
/*     */       
/* 693 */       if (is.func_77978_p().func_74764_b("tasteUmami"))
/* 694 */         base = is.func_77978_p().func_74762_e("tasteUmami"); 
/* 695 */       base += Food.getCookedProfile(is)[4];
/* 696 */       base = (int)(base + Food.getFuelProfile(is)[4] * getSmokeAbsorbMultiplier());
/*     */     } 
/* 698 */     return Math.max(base + Food.getSavoryMod(is), 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFoodMaxWeight(ItemStack is) {
/* 703 */     return 160.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderDecay() {
/* 708 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderWeight() {
/* 713 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSmoke() {
/* 718 */     return this.canBeSmoked;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSmokeAbsorbMultiplier() {
/* 724 */     return this.smokeAbsorb;
/*     */   }
/*     */   
/*     */   public ItemFoodTFC setSmokeAbsorbMultiplier(float s) {
/* 728 */     this.smokeAbsorb = s;
/* 729 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFoodWeight(ItemStack is) {
/* 735 */     return Food.getWeight(is);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemFoodTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */