/*     */ package com.bioxx.tfc.Food;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.Render.Item.FoodItemRenderer;
/*     */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*     */ import com.bioxx.tfc.api.Enums.EnumSize;
/*     */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.FoodRegistry;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.TFC_ItemHeat;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.renderer.texture.IIconRegister;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.EnumAction;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.client.IItemRenderer;
/*     */ import net.minecraftforge.client.MinecraftForgeClient;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemMeal
/*     */   extends ItemTerra
/*     */   implements IFood
/*     */ {
/*     */   public ItemMeal() {
/*  43 */     this.field_77787_bX = true;
/*  44 */     this.metaNames = new String[] { "Meal0", "Meal1", "Meal2", "Meal3", "Meal4", "Meal5", "Meal6", "Meal7", "Meal8", "Meal9", "Meal10" };
/*  45 */     this.metaIcons = new net.minecraft.util.IIcon[11];
/*  46 */     setFolder("food/");
/*  47 */     this.stackable = false;
/*  48 */     func_77637_a(null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_94581_a(IIconRegister registerer) {
/*  54 */     super.func_94581_a(registerer);
/*  55 */     MinecraftForgeClient.registerItemRenderer((Item)this, (IItemRenderer)new FoodItemRenderer());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/*  61 */     list.add(createTag(new ItemStack((Item)this, 1)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static ItemStack createTag(ItemStack is) {
/*  67 */     if (!is.func_77942_o()) {
/*  68 */       is.func_77982_d(new NBTTagCompound());
/*     */     }
/*  70 */     Food.setWeight(is, 0.0F);
/*  71 */     Food.setDecay(is, 0.0F);
/*  72 */     Food.setDecayTimer(is, (int)TFC_Time.getTotalHours() + 1);
/*  73 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_77667_c(ItemStack itemstack) {
/*  79 */     return func_77658_a();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
/*  85 */     ItemTerra.addSizeInformation(is, arraylist);
/*  86 */     if (!TFC_Core.showShiftInformation())
/*     */     {
/*  88 */       arraylist.add("");
/*     */     }
/*     */     
/*  91 */     if (is.func_77942_o()) {
/*     */ 
/*     */       
/*  94 */       ItemFoodTFC.addFoodHeatInformation(is, arraylist);
/*  95 */       addFoodInformation(is, player, arraylist);
/*     */       
/*  97 */       if (TFC_Core.showShiftInformation())
/*     */       {
/*  99 */         addFGInformation(is, arraylist);
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 104 */       arraylist.add(TFC_Core.translate("gui.badnbt"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFoodInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
/* 113 */     float ounces = Helper.roundNumber(Food.getWeight(is), 100.0F);
/* 114 */     if (ounces > 0.0F)
/* 115 */       arraylist.add(TFC_Core.translate("gui.food.amount") + " " + ounces + " oz / " + 160.0F + " oz"); 
/* 116 */     float decay = Food.getDecay(is);
/* 117 */     if (decay > 0.0F)
/* 118 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + " " + Helper.roundNumber(decay / ounces * 100.0F, 10.0F) + "%"); 
/* 119 */     if (TFCOptions.enableDebugMode) {
/*     */       
/* 121 */       arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + ": " + decay);
/* 122 */       arraylist.add(EnumChatFormatting.DARK_GRAY + "Decay Rate: " + getDecayRate(is));
/*     */     } 
/*     */     
/* 125 */     if (TFC_Core.showCtrlInformation()) {
/* 126 */       ItemFoodTFC.addTasteInformation(is, player, arraylist);
/*     */     } else {
/* 128 */       arraylist.add(TFC_Core.translate("gui.showtaste"));
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void addFGInformation(ItemStack is, List<String> arraylist) {
/* 133 */     int[] fg = Food.getFoodGroups(is);
/* 134 */     for (int i = 0; i < fg.length; i++) {
/*     */       
/* 136 */       if (fg[i] != -1) {
/* 137 */         arraylist.add(localize(fg[i]));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected String localize(int id) {
/* 143 */     return ItemFoodTFC.getFoodGroupColor(FoodRegistry.getInstance().getFoodGroup(id)) + 
/* 144 */       TFC_Core.translate(FoodRegistry.getInstance().getFood(id).func_77658_a() + ".name");
/*     */   }
/*     */ 
/*     */   
/*     */   protected float[] getNutritionalWeights(int[] foodGroups) {
/* 149 */     float[] nw = new float[foodGroups.length];
/* 150 */     float[] fw = getFoodWeights();
/* 151 */     float totalWeight = 0.0F; int i;
/* 152 */     for (i = 0; i < foodGroups.length; i++) {
/*     */       
/* 154 */       if (foodGroups[i] != -1)
/*     */       {
/* 156 */         totalWeight += fw[i];
/*     */       }
/*     */     } 
/*     */     
/* 160 */     for (i = 0; i < foodGroups.length; i++)
/*     */     {
/* 162 */       nw[i] = fw[i] / totalWeight;
/*     */     }
/* 164 */     return nw;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float[] getFoodWeights() {
/* 169 */     return new float[] { 10.0F, 4.0F, 4.0F, 2.0F };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getEatAmount(FoodStatsTFC fs, float amount) {
/* 179 */     float eatAmount = Math.min(amount, 5.0F);
/* 180 */     float stomachDiff = fs.stomachLevel + eatAmount - fs.getMaxStomach(fs.player);
/* 181 */     if (stomachDiff > 0.0F)
/* 182 */       eatAmount -= stomachDiff; 
/* 183 */     return eatAmount;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getFillingBoost() {
/* 188 */     return 1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
/* 194 */     world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */     
/* 196 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/* 197 */     if (!world.field_72995_K)
/*     */     {
/*     */       
/* 200 */       if (is.func_77942_o()) {
/*     */         
/* 202 */         float weight = Food.getWeight(is);
/* 203 */         float decay = Math.max(Food.getDecay(is), 0.0F);
/* 204 */         float eatAmount = getEatAmount(foodstats, weight - decay);
/* 205 */         float tasteFactor = foodstats.getTasteFactor(is);
/*     */         
/* 207 */         int[] fg = Food.getFoodGroups(is);
/* 208 */         float[] nWeights = getNutritionalWeights(fg);
/* 209 */         for (int i = 0; i < fg.length; i++) {
/*     */           
/* 211 */           if (fg[i] != -1) {
/* 212 */             foodstats.addNutrition(FoodRegistry.getInstance().getFoodGroup(fg[i]), eatAmount * nWeights[i] * 2.5F);
/*     */           }
/*     */         } 
/*     */         
/* 216 */         foodstats.stomachLevel += eatAmount * getFillingBoost();
/* 217 */         foodstats.setSatisfaction(foodstats.getSatisfaction() + eatAmount / 3.0F * tasteFactor, fg);
/*     */ 
/*     */         
/* 220 */         if (FoodStatsTFC.reduceFood(is, eatAmount))
/*     */         {
/* 222 */           is.field_77994_a = 0;
/*     */         
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 228 */         String error = TFC_Core.translate("error.error") + " " + is.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");
/*     */ 
/*     */         
/* 231 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentText(error));
/*     */       } 
/*     */     }
/* 234 */     TFC_Core.setPlayerFoodStats(player, foodstats);
/* 235 */     return is;
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
/*     */   public static boolean isWarm(ItemStack is) {
/* 252 */     return (TFC_ItemHeat.getTemp(is) > TFC_ItemHeat.isCookable(is) * 0.1D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_77626_a(ItemStack par1ItemStack) {
/* 261 */     return 32;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumAction func_77661_b(ItemStack par1ItemStack) {
/* 270 */     return EnumAction.eat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
/* 279 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/*     */ 
/*     */     
/* 282 */     if (foodstats.needFood()) {
/* 283 */       player.func_71008_a(is, func_77626_a(is));
/*     */     }
/* 285 */     return is;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSize getSize(ItemStack is) {
/* 291 */     return EnumSize.SMALL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumWeight getWeight(ItemStack is) {
/* 297 */     return EnumWeight.MEDIUM;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumFoodGroup getFoodGroup() {
/* 303 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFoodID() {
/* 309 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDecayRate(ItemStack is) {
/* 315 */     return Food.getDecayRate(is);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onDecayed(ItemStack is, World world, int i, int j, int k) {
/* 321 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEdible(ItemStack is) {
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isUsable(ItemStack is) {
/* 333 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSweet(ItemStack is) {
/* 338 */     int base = 0;
/* 339 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSweet"))
/* 340 */       base = is.func_77978_p().func_74762_e("tasteSweet"); 
/* 341 */     return base + Food.getSweetMod(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSour(ItemStack is) {
/* 346 */     int base = 0;
/* 347 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSour"))
/* 348 */       base = is.func_77978_p().func_74762_e("tasteSour"); 
/* 349 */     return base + Food.getSourMod(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSalty(ItemStack is) {
/* 354 */     int base = 0;
/* 355 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSalty"))
/* 356 */       base = is.func_77978_p().func_74762_e("tasteSalty"); 
/* 357 */     return base + Food.getSaltyMod(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteBitter(ItemStack is) {
/* 362 */     int base = 0;
/* 363 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteBitter"))
/* 364 */       base = is.func_77978_p().func_74762_e("tasteBitter"); 
/* 365 */     return base + Food.getBitterMod(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTasteSavory(ItemStack is) {
/* 370 */     int base = 0;
/* 371 */     if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteUmami"))
/* 372 */       base = is.func_77978_p().func_74762_e("tasteUmami"); 
/* 373 */     return base + Food.getSavoryMod(is);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getFoodMaxWeight(ItemStack is) {
/* 378 */     return 20.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderDecay() {
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean renderWeight() {
/* 388 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFoodWeight(ItemStack is) {
/* 394 */     return Food.getWeight(is);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemMeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */