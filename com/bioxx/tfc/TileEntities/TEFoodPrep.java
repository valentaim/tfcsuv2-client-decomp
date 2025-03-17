/*     */ package com.bioxx.tfc.TileEntities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.CreateMealPacket;
/*     */ import com.bioxx.tfc.Items.ItemTerra;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IFood;
/*     */ import com.bioxx.tfc.api.Interfaces.IItemFoodBlock;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTBase;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.nbt.NBTTagList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ 
/*     */ public class TEFoodPrep
/*     */   extends NetworkTileEntity
/*     */   implements IInventory {
/*  29 */   public ItemStack[] storage = new ItemStack[11];
/*     */   public int lastTab;
/*  31 */   private final float[] sandwichWeights = new float[] { 2.0F, 3.0F, 2.0F, 2.0F, 1.0F };
/*  32 */   private final float[] saladWeights = new float[] { 10.0F, 4.0F, 4.0F, 2.0F };
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_145845_h() {
/*  37 */     TFC_Core.handleItemTicking(this, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFoodIdFromItemStack(ItemStack is) {
/*  42 */     if (is != null) {
/*     */       
/*  44 */       if (is.func_77973_b() instanceof IFood)
/*  45 */         return ((IFood)is.func_77973_b()).getFoodID(); 
/*  46 */       if (is.func_77973_b() instanceof IItemFoodBlock)
/*  47 */         return ((IItemFoodBlock)is.func_77973_b()).getFoodId(is); 
/*     */     } 
/*  49 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public AxisAlignedBB getRenderBoundingBox() {
/*  56 */     return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHealAmountFromItemStack(ItemStack is) {
/*  61 */     if (is != null) {
/*     */       
/*  63 */       if (is.func_77973_b() instanceof IFood)
/*  64 */         return ((IFood)is.func_77973_b()).getFoodID(); 
/*  65 */       if (is.func_77973_b() instanceof IItemFoodBlock)
/*  66 */         return ((IItemFoodBlock)is.func_77973_b()).getHealAmount(is); 
/*     */     } 
/*  68 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionCreate(EntityPlayer player) {
/*  73 */     if (!this.field_145850_b.field_72995_K) {
/*     */       
/*  75 */       if (this.lastTab == 0) {
/*  76 */         createSandwich(player);
/*  77 */       } else if (this.lastTab == 1) {
/*  78 */         createSalad(player);
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  84 */       CreateMealPacket createMealPacket = new CreateMealPacket(0, this);
/*  85 */       broadcastPacketInRange((AbstractPacket)createMealPacket);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void createSandwich(EntityPlayer player) {
/*  91 */     if (validateSandwich()) {
/*     */       
/*  93 */       ItemStack is = new ItemStack(TFCItems.sandwich, 1);
/*     */       
/*  95 */       float w = 0.0F;
/*  96 */       for (int i = 0; i < 5; i++) {
/*     */         
/*  98 */         ItemStack f = func_70301_a(i);
/*  99 */         if (f != null && Food.getWeight(f) >= this.sandwichWeights[i]) {
/* 100 */           w += this.sandwichWeights[i];
/*     */         }
/*     */       } 
/* 103 */       ItemFoodTFC.createTag(is, w);
/* 104 */       Food.setDecayRate(is, 2.0F);
/*     */       
/* 106 */       int[] foodGroups = { -1, -1, -1, -1, -1 };
/* 107 */       if (func_70301_a(0) != null) foodGroups[0] = ((IFood)func_70301_a(0).func_77973_b()).getFoodID(); 
/* 108 */       if (func_70301_a(1) != null) foodGroups[1] = ((IFood)func_70301_a(1).func_77973_b()).getFoodID(); 
/* 109 */       if (func_70301_a(2) != null) foodGroups[2] = ((IFood)func_70301_a(2).func_77973_b()).getFoodID(); 
/* 110 */       if (func_70301_a(3) != null) foodGroups[3] = ((IFood)func_70301_a(3).func_77973_b()).getFoodID(); 
/* 111 */       if (func_70301_a(4) != null) foodGroups[4] = ((IFood)func_70301_a(4).func_77973_b()).getFoodID();
/*     */       
/* 113 */       Food.setFoodGroups(is, foodGroups);
/* 114 */       setSandwichIcon(is);
/*     */       
/* 116 */       combineTastes(is.func_77978_p(), this.sandwichWeights, new ItemStack[] { func_70301_a(0), func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });
/*     */       
/* 118 */       Food.setMealSkill(is, TFC_Core.getSkillStats(player).getSkillRank("skill.cooking").ordinal());
/* 119 */       func_70299_a(6, is);
/*     */       
/* 121 */       consumeFoodWeight(this.sandwichWeights, new ItemStack[] { func_70301_a(0), func_70301_a(1), func_70301_a(2), 
/* 122 */             func_70301_a(3), func_70301_a(4) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void setSandwichIcon(ItemStack is) {
/* 128 */     if (func_70301_a(0).func_77973_b() == TFCItems.wheatBread) {
/* 129 */       is.func_77964_b(0);
/* 130 */     } else if (func_70301_a(0).func_77973_b() == TFCItems.oatBread) {
/* 131 */       is.func_77964_b(1);
/* 132 */     } else if (func_70301_a(0).func_77973_b() == TFCItems.barleyBread) {
/* 133 */       is.func_77964_b(2);
/* 134 */     } else if (func_70301_a(0).func_77973_b() == TFCItems.ryeBread) {
/* 135 */       is.func_77964_b(3);
/* 136 */     } else if (func_70301_a(0).func_77973_b() == TFCItems.cornBread) {
/* 137 */       is.func_77964_b(4);
/* 138 */     } else if (func_70301_a(0).func_77973_b() == TFCItems.riceBread) {
/* 139 */       is.func_77964_b(5);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createSalad(EntityPlayer player) {
/* 144 */     if (validateSalad()) {
/*     */       
/* 146 */       ItemStack is = new ItemStack(TFCItems.salad, 1);
/*     */       
/* 148 */       float w = 0.0F;
/* 149 */       for (int i = 0; i < 4; i++) {
/*     */         
/* 151 */         ItemStack f = func_70301_a(i + 1);
/* 152 */         if (f != null && Food.getWeight(f) >= this.saladWeights[i]) {
/* 153 */           w += this.saladWeights[i];
/*     */         }
/*     */       } 
/* 156 */       ItemFoodTFC.createTag(is, w);
/* 157 */       Food.setDecayRate(is, 2.0F);
/*     */       
/* 159 */       int[] foodGroups = { -1, -1, -1, -1 };
/* 160 */       if (func_70301_a(1) != null) foodGroups[0] = ((IFood)func_70301_a(1).func_77973_b()).getFoodID(); 
/* 161 */       if (func_70301_a(2) != null) foodGroups[1] = ((IFood)func_70301_a(2).func_77973_b()).getFoodID(); 
/* 162 */       if (func_70301_a(3) != null) foodGroups[2] = ((IFood)func_70301_a(3).func_77973_b()).getFoodID(); 
/* 163 */       if (func_70301_a(4) != null) foodGroups[3] = ((IFood)func_70301_a(4).func_77973_b()).getFoodID();
/*     */       
/* 165 */       Food.setFoodGroups(is, foodGroups);
/*     */       
/* 167 */       is.func_77964_b((new Random(getIconSeed())).nextInt(((ItemTerra)TFCItems.salad).metaIcons.length));
/*     */       
/* 169 */       combineTastes(is.func_77978_p(), this.saladWeights, new ItemStack[] { func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });
/*     */       
/* 171 */       Food.setMealSkill(is, TFC_Core.getSkillStats(player).getSkillRank("skill.cooking").ordinal());
/* 172 */       func_70299_a(6, is);
/*     */       
/* 174 */       consumeFoodWeight(this.saladWeights, new ItemStack[] { func_70301_a(1), func_70301_a(2), func_70301_a(3), func_70301_a(4) });
/*     */       
/* 176 */       (TFC_Core.getItemInInventory(TFCItems.potteryBowl, this)).field_77994_a--;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validateSandwich() {
/* 182 */     if (this.lastTab == 0) {
/*     */       
/* 184 */       if (this.storage[0] == null || this.storage[6] != null) {
/* 185 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 192 */       if (!validateIngreds(new ItemStack[] { this.storage[1], this.storage[2], this.storage[3], this.storage[4] })) {
/* 193 */         return false;
/*     */       }
/* 195 */       float weight = 0.0F;
/* 196 */       for (int i = 0; i < 5; i++) {
/*     */         
/* 198 */         ItemStack f = func_70301_a(i);
/* 199 */         if (f != null && f.func_77973_b() instanceof IFood && Food.getWeight(f) - Food.getDecay(f) >= this.sandwichWeights[i])
/*     */         {
/* 201 */           weight += this.sandwichWeights[i];
/*     */         }
/*     */       } 
/*     */       
/* 205 */       if (weight < 7.0F)
/* 206 */         return false; 
/*     */     } 
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validateSalad() {
/* 213 */     if (this.lastTab == 1) {
/*     */       
/* 215 */       if (this.storage[6] != null) {
/* 216 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 225 */       if (!validateIngreds(new ItemStack[] { this.storage[1], this.storage[2], this.storage[3], this.storage[4] })) {
/* 226 */         return false;
/*     */       }
/* 228 */       float weight = 0.0F;
/* 229 */       for (int i = 0; i < 4; i++) {
/*     */         
/* 231 */         ItemStack f = func_70301_a(i + 1);
/* 232 */         if (f != null && Food.getWeight(f) - Food.getDecay(f) >= this.saladWeights[i])
/*     */         {
/* 234 */           weight += this.saladWeights[i];
/*     */         }
/*     */       } 
/*     */       
/* 238 */       if (weight < 14.0F) {
/* 239 */         return false;
/*     */       }
/* 241 */       ItemStack bowlStack = TFC_Core.getItemInInventory(TFCItems.potteryBowl, this);
/* 242 */       if (bowlStack == null || bowlStack.func_77960_j() != 1)
/*     */       {
/* 244 */         return false;
/*     */       }
/*     */     } 
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validateIngreds(ItemStack... is) {
/* 252 */     for (int i = 0; i < is.length; i++) {
/*     */       
/* 254 */       if (is[i] != null && !((IFood)is[i].func_77973_b()).isUsable(is[i]))
/* 255 */         return false; 
/* 256 */       for (int j = 0; j < is.length; j++) {
/*     */ 
/*     */         
/* 259 */         if (j != i && !compareIngred(is[i], is[j]))
/* 260 */           return false; 
/*     */       } 
/*     */     } 
/* 263 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean compareIngred(ItemStack is1, ItemStack is2) {
/* 268 */     return (is1 == null || is2 == null || is1.func_77973_b() != is2.func_77973_b());
/*     */   }
/*     */ 
/*     */   
/*     */   private void combineTastes(NBTTagCompound nbt, float[] weights, ItemStack... isArray) {
/* 273 */     int tasteSweet = 0;
/* 274 */     int tasteSour = 0;
/* 275 */     int tasteSalty = 0;
/* 276 */     int tasteBitter = 0;
/* 277 */     int tasteUmami = 0;
/*     */     
/* 279 */     for (int i = 0; i < isArray.length; i++) {
/*     */       
/* 281 */       float weightMult = 1.0F;
/* 282 */       if (isArray[i] != null) {
/*     */         
/* 284 */         tasteSweet = (int)(tasteSweet + ((IFood)isArray[i].func_77973_b()).getTasteSweet(isArray[i]) * weightMult);
/* 285 */         tasteSour = (int)(tasteSour + ((IFood)isArray[i].func_77973_b()).getTasteSour(isArray[i]) * weightMult);
/* 286 */         tasteSalty = (int)(tasteSalty + ((IFood)isArray[i].func_77973_b()).getTasteSalty(isArray[i]) * weightMult);
/* 287 */         tasteBitter = (int)(tasteBitter + ((IFood)isArray[i].func_77973_b()).getTasteBitter(isArray[i]) * weightMult);
/* 288 */         tasteUmami = (int)(tasteUmami + ((IFood)isArray[i].func_77973_b()).getTasteSavory(isArray[i]) * weightMult);
/*     */       } 
/*     */     } 
/* 291 */     nbt.func_74768_a("tasteSweet", tasteSweet);
/* 292 */     nbt.func_74768_a("tasteSour", tasteSour);
/* 293 */     nbt.func_74768_a("tasteSalty", tasteSalty);
/* 294 */     nbt.func_74768_a("tasteBitter", tasteBitter);
/* 295 */     nbt.func_74768_a("tasteUmami", tasteUmami);
/*     */   }
/*     */ 
/*     */   
/*     */   public void openGui(EntityPlayer player) {
/* 300 */     if (this.lastTab == 0) {
/* 301 */       player.openGui(TerraFirmaCraft.instance, 44, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 302 */     } else if (this.lastTab == 1) {
/* 303 */       player.openGui(TerraFirmaCraft.instance, 45, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long getIconSeed() {
/* 321 */     int seed = 0;
/* 322 */     for (int i = 1; i < 5; i++) {
/*     */       
/* 324 */       ItemStack is = func_70301_a(i);
/* 325 */       if (is != null)
/* 326 */         seed += ((ItemFoodTFC)is.func_77973_b()).getFoodID(); 
/*     */     } 
/* 328 */     return seed + this.field_145850_b.func_72905_C();
/*     */   }
/*     */ 
/*     */   
/*     */   public void consumeFoodWeight(float[] weights, ItemStack... isArray) {
/* 333 */     for (int i = 0; i < isArray.length; i++) {
/*     */       
/* 335 */       ItemStack is = isArray[i];
/* 336 */       if (is != null) {
/*     */         
/* 338 */         float oldW = Food.getWeight(is);
/* 339 */         Food.setWeight(is, oldW - weights[i]);
/* 340 */         float newW = Food.getWeight(is);
/* 341 */         if (newW <= 0.0F || newW <= Food.getDecay(is)) {
/* 342 */           is.field_77994_a = 0;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145839_a(NBTTagCompound nbt) {
/* 350 */     super.func_145839_a(nbt);
/* 351 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 352 */     this.storage = new ItemStack[func_70302_i_()];
/* 353 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 355 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 356 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 357 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 358 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_145841_b(NBTTagCompound nbt) {
/* 365 */     super.func_145841_b(nbt);
/* 366 */     NBTTagList nbttaglist = new NBTTagList();
/* 367 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 369 */       if (this.storage[i] != null) {
/*     */         
/* 371 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 372 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 373 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 374 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 377 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70298_a(int i, int j) {
/* 383 */     if (this.storage[i] != null) {
/*     */       
/* 385 */       if ((this.storage[i]).field_77994_a <= j) {
/*     */         
/* 387 */         ItemStack itemstack = this.storage[i];
/* 388 */         this.storage[i] = null;
/* 389 */         return itemstack;
/*     */       } 
/* 391 */       ItemStack itemstack1 = this.storage[i].func_77979_a(j);
/* 392 */       if ((this.storage[i]).field_77994_a == 0)
/* 393 */         this.storage[i] = null; 
/* 394 */       return itemstack1;
/*     */     } 
/*     */     
/* 397 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ejectContents() {
/* 403 */     float f3 = 0.05F;
/*     */     
/* 405 */     Random rand = new Random();
/* 406 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 407 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 408 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 410 */     for (int i = 0; i < func_70302_i_(); i++) {
/*     */       
/* 412 */       if (this.storage[i] != null) {
/*     */         
/* 414 */         EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[i]);
/* 415 */         entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 416 */         entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.2F);
/* 417 */         entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 418 */         this.field_145850_b.func_72838_d((Entity)entityitem);
/* 419 */         this.storage[i] = null;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ejectItem(int index) {
/* 426 */     float f3 = 0.05F;
/*     */     
/* 428 */     Random rand = new Random();
/* 429 */     float f = rand.nextFloat() * 0.8F + 0.1F;
/* 430 */     float f1 = rand.nextFloat() * 2.0F + 0.4F;
/* 431 */     float f2 = rand.nextFloat() * 0.8F + 0.1F;
/*     */     
/* 433 */     if (this.storage[index] != null) {
/*     */       
/* 435 */       EntityItem entityitem = new EntityItem(this.field_145850_b, (this.field_145851_c + f), (this.field_145848_d + f1), (this.field_145849_e + f2), this.storage[index]);
/* 436 */       entityitem.field_70159_w = ((float)rand.nextGaussian() * f3);
/* 437 */       entityitem.field_70181_x = ((float)rand.nextGaussian() * f3 + 0.05F);
/* 438 */       entityitem.field_70179_y = ((float)rand.nextGaussian() * f3);
/* 439 */       this.field_145850_b.func_72838_d((Entity)entityitem);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70302_i_() {
/* 446 */     return this.storage.length;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack func_70301_a(int i) {
/* 452 */     return this.storage[i];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70299_a(int i, ItemStack itemstack) {
/* 458 */     if (!TFC_Core.areItemsEqual(this.storage[i], itemstack))
/* 459 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e); 
/* 460 */     this.storage[i] = itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_145825_b() {
/* 466 */     return "FoodPrep";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70297_j_() {
/* 472 */     return 64;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70300_a(EntityPlayer var1) {
/* 478 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70295_k_() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70305_f() {
/* 489 */     if (this.field_145850_b.field_72995_K)
/*     */     {
/* 491 */       this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
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
/*     */   public ItemStack func_70304_b(int var1) {
/* 503 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_145818_k_() {
/* 509 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_94041_b(int i, ItemStack itemstack) {
/* 515 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionSwitchTab(int tab, EntityPlayer player) {
/* 520 */     NBTTagCompound nbt = new NBTTagCompound();
/* 521 */     nbt.func_74774_a("tab", (byte)tab);
/* 522 */     nbt.func_74778_a("player", player.func_70005_c_());
/* 523 */     broadcastPacketInRange((AbstractPacket)createDataPacket(nbt));
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleInitPacket(NBTTagCompound nbt) {
/* 528 */     NBTTagList nbttaglist = nbt.func_150295_c("Items", 10);
/* 529 */     this.storage = new ItemStack[func_70302_i_()];
/* 530 */     for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*     */       
/* 532 */       NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
/* 533 */       byte byte0 = nbttagcompound1.func_74771_c("Slot");
/* 534 */       if (byte0 >= 0 && byte0 < this.storage.length) {
/* 535 */         this.storage[byte0] = ItemStack.func_77949_a(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void createInitNBT(NBTTagCompound nbt) {
/* 541 */     NBTTagList nbttaglist = new NBTTagList();
/* 542 */     for (int i = 0; i < this.storage.length; i++) {
/*     */       
/* 544 */       if (this.storage[i] != null) {
/*     */         
/* 546 */         NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 547 */         nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 548 */         this.storage[i].func_77955_b(nbttagcompound1);
/* 549 */         nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*     */       } 
/*     */     } 
/* 552 */     nbt.func_74782_a("Items", (NBTBase)nbttaglist);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDataPacket(NBTTagCompound nbt) {
/* 558 */     if (!this.field_145850_b.field_72995_K)
/*     */     {
/* 560 */       if (nbt.func_74764_b("tab")) {
/*     */         
/* 562 */         int tab = nbt.func_74771_c("tab");
/* 563 */         EntityPlayer player = this.field_145850_b.func_72924_a(nbt.func_74779_i("player"));
/* 564 */         if (player != null && tab == 0) {
/* 565 */           player.openGui(TerraFirmaCraft.instance, 44, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/* 566 */         } else if (player != null && tab == 1) {
/* 567 */           player.openGui(TerraFirmaCraft.instance, 45, this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */