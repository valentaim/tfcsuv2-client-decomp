/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIFindNest;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import fof.tfcsu.utils.ExpBonus;
/*     */ import java.nio.ByteBuffer;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityAgeable;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIEatGrass;
/*     */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityChicken;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityChickenTFC
/*     */   extends EntityChicken
/*     */   implements IAnimal
/*     */ {
/*     */   private static final float DIMORPHISM = 0.0606F;
/*     */   private static final int DEGREE_OF_DIVERSION = 2;
/*     */   protected static final int FAMILIARITY_CAP = 45;
/*     */   private static final int EGG_TIME = 24000;
/*  57 */   private final EntityAIEatGrass aiEatGrass = new EntityAIEatGrass((EntityLiving)this);
/*     */   private int sex;
/*     */   private int hunger;
/*     */   private float sizeMod;
/*     */   private float strengthMod;
/*  62 */   private float aggressionMod = 1.0F;
/*     */   
/*  64 */   private float obedienceMod = 1.0F;
/*     */   
/*     */   private boolean inLove;
/*     */   
/*     */   private long nextEgg;
/*     */   
/*     */   private int familiarity;
/*     */   
/*     */   private long lastFamiliarityUpdate;
/*     */   
/*     */   private boolean familiarizedToday;
/*     */   
/*     */   public EntityChickenTFC(World par1World) {
/*  77 */     super(par1World);
/*  78 */     func_70105_a(0.3F, 0.7F);
/*  79 */     this.field_70887_j = 9999;
/*  80 */     this.nextEgg = TFC_Time.getTotalTicks() + 24000L;
/*  81 */     this.hunger = 168000;
/*  82 */     this.sex = this.field_70146_Z.nextInt(2);
/*     */     
/*  84 */     this.field_70714_bg.field_75782_a.clear();
/*  85 */     this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  86 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 1.4D));
/*  87 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.1D));
/*  88 */     this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  89 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityChickenTFC.class, 6.0F));
/*  90 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
/*  91 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  92 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*  93 */     addAI();
/*     */     
/*  95 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0606F * this.sex)));
/*  96 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  97 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  98 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 104 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityChickenTFC(World world, double posX, double posY, double posZ, NBTTagCompound genes) {
/* 112 */     this(world);
/* 113 */     this.field_70165_t = posX;
/* 114 */     this.field_70163_u = posY;
/* 115 */     this.field_70161_v = posZ;
/* 116 */     float motherSize = genes.func_74760_g("m_size");
/* 117 */     float fatherSize = genes.func_74760_g("f_size");
/* 118 */     this.sizeMod = ((this.field_70146_Z.nextInt(3) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) / 10.0F + 1.0F) * (1.0F - 0.1F * this.sex) * (float)Math.sqrt(((motherSize + fatherSize) / 1.9F));
/*     */ 
/*     */     
/* 121 */     setAge(TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 128 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 129 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 130 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 132 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/* 133 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/* 134 */     if (temp >= 23.0F && temp < 44.0F && rain > 1500.0F)
/* 135 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/* 136 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addAI() {
/* 141 */     if (this.sex == 0)
/*     */     {
/* 143 */       this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
/*     */     }
/* 145 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/* 146 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/* 147 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/* 148 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/* 149 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/* 150 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/* 151 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFindNest((EntityAnimal)this, 1.2000000476837158D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 157 */     super.func_110147_ax();
/* 158 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(50.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFamiliarize() {
/* 164 */     return (!isAdult() || (isAdult() && this.familiarity <= 45));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 170 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal
/* 171 */       .getAnimalTypeID() == getAnimalTypeID());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 176 */     boolean flag = false;
/* 177 */     switch (interaction) {
/*     */       case NAME:
/* 179 */         flag = (this.familiarity > 50);
/*     */         break;
/*     */     } 
/*     */     
/* 183 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/* 184 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 186 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityChicken func_90011_a(EntityAgeable entityAgeable) {
/* 192 */     return (EntityChicken)createChildTFC(entityAgeable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable entityageable) {
/* 199 */     if (entityageable instanceof IAnimal) {
/*     */       
/* 201 */       IAnimal animal = (IAnimal)entityageable;
/* 202 */       NBTTagCompound nbt = new NBTTagCompound();
/* 203 */       nbt.func_74776_a("m_size", animal.getSizeMod());
/* 204 */       nbt.func_74776_a("f_size", animal.getSizeMod());
/* 205 */       return (EntityAgeable)new EntityChickenTFC(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, nbt);
/*     */     } 
/*     */     
/* 208 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 217 */     float ageMod = TFC_Core.getPercentGrown(this);
/* 218 */     func_145779_a(Items.field_151008_G, (int)(ageMod * this.sizeMod * (5 + this.field_70146_Z.nextInt(10))));
/*     */     
/* 220 */     if (isAdult()) {
/*     */       
/* 222 */       float foodWeight = ageMod * this.sizeMod * 40.0F;
/* 223 */       TFC_Core.animalDropMeat((Entity)this, TFCItems.chickenRaw, foodWeight);
/* 224 */       func_145779_a(Items.field_151103_aS, this.field_70146_Z.nextInt(2) + 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 231 */     super.func_70088_a();
/* 232 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/* 233 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*     */     
/* 235 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/* 236 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void familiarize(EntityPlayer ep) {
/* 241 */     ItemStack stack = ep.func_70694_bm();
/* 242 */     if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {
/*     */       
/* 244 */       if (!ep.field_71075_bZ.field_75098_d) {
/*     */         
/* 246 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/*     */       else {
/*     */         
/* 250 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/* 252 */       this.hunger += 24000;
/* 253 */       this.familiarizedToday = true;
/* 254 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/* 255 */       func_70642_aH();
/* 256 */       ExpBonus.FEED.give(ep);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAggressionMod() {
/* 263 */     return this.aggressionMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 269 */     return Helper.stringToInt("chicken");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getAttackedVec() {
/* 275 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirthDay() {
/* 281 */     return this.field_70180_af.func_75679_c(15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item func_146068_u() {
/* 290 */     return Items.field_151008_G;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDueDay() {
/* 296 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getEggs() {
/* 301 */     if (TFC_Time.getTotalTicks() >= this.nextEgg) {
/*     */       
/* 303 */       this.nextEgg = TFC_Time.getTotalTicks() + 24000L;
/* 304 */       return new ItemStack(TFCItems.egg, 1);
/*     */     } 
/* 306 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLiving getEntity() {
/* 312 */     return (EntityLiving)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFamiliarity() {
/* 317 */     return this.familiarity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFamiliarizedToday() {
/* 323 */     return this.familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getFearSource() {
/* 329 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimal.GenderEnum getGender() {
/* 335 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHunger() {
/* 341 */     return this.hunger;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInLove() {
/* 347 */     return this.inLove;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastFamiliarityUpdate() {
/* 352 */     return this.lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getNextEgg() {
/* 357 */     return this.nextEgg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDaysToAdult() {
/* 363 */     return (int)((TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth) * 4.14D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getObedienceMod() {
/* 369 */     return this.obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSex() {
/* 374 */     return this.sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeMod() {
/* 380 */     return this.sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrengthMod() {
/* 386 */     return this.strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFamiliarityUpdate() {
/* 391 */     int totalDays = TFC_Time.getTotalDays();
/* 392 */     if (this.lastFamiliarityUpdate < totalDays) {
/* 393 */       if (this.familiarizedToday && this.familiarity < 100) {
/* 394 */         this.lastFamiliarityUpdate = totalDays;
/* 395 */         this.familiarizedToday = false;
/* 396 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/* 397 */         if (isAdult() && this.familiarity <= 45) {
/*     */           
/* 399 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*     */         }
/* 401 */         else if (!isAdult()) {
/* 402 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/* 403 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/* 404 */           if (this.familiarity > 70) {
/* 405 */             this.obedienceMod *= 1.01F;
/*     */           }
/*     */         }
/*     */       
/* 409 */       } else if (this.familiarity < 30) {
/* 410 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/* 411 */         this.lastFamiliarityUpdate = totalDays;
/*     */       } 
/*     */     }
/* 414 */     if (this.familiarity > 100) this.familiarity = 100; 
/* 415 */     if (this.familiarity < 0) this.familiarity = 0;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player) {
/* 424 */     ItemStack itemstack = player.func_70694_bm();
/*     */     
/* 426 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 428 */       if (isAdult() && player.func_70093_af() && !isFood(itemstack) && func_70097_a(DamageSource.field_76377_j, 5.0F)) {
/*     */         
/* 430 */         player.field_71071_by.func_70441_a(new ItemStack(Items.field_151008_G, 1));
/* 431 */         this.familiarity -= 4;
/* 432 */         return true;
/*     */       } 
/*     */       
/* 435 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*     */         
/* 437 */         familiarize(player);
/* 438 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 442 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/* 443 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/* 444 */         itemstack.field_77994_a--;
/*     */       }
/* 446 */       return true;
/*     */     } 
/* 448 */     return super.func_70085_c(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 454 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70650_aV() {
/* 463 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack par1ItemStack) {
/* 473 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70631_g_() {
/* 479 */     return !isAdult();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack item) {
/* 484 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/* 485 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPregnant() {
/* 491 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mate(IAnimal otherAnimal) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 507 */     if (this.hunger > 168000)
/*     */     {
/* 509 */       this.hunger = 168000;
/*     */     }
/* 511 */     if (this.hunger > 0)
/*     */     {
/* 513 */       this.hunger--;
/*     */     }
/*     */     
/* 516 */     syncData();
/* 517 */     if (isAdult()) {
/*     */       
/* 519 */       func_70873_a(0);
/*     */     }
/*     */     else {
/*     */       
/* 523 */       func_70873_a(-1);
/*     */     } 
/*     */     
/* 526 */     roosterCrow();
/* 527 */     handleFamiliarityUpdate();
/*     */ 
/*     */     
/* 530 */     this.field_70887_j = 9999;
/*     */ 
/*     */ 
/*     */     
/* 534 */     TFC_Core.preventEntityDataUpdate = true;
/* 535 */     if (getGender() == IAnimal.GenderEnum.MALE)
/*     */     {
/* 537 */       this.nextEgg = 10000L;
/*     */     }
/*     */     
/* 540 */     super.func_70636_d();
/* 541 */     TFC_Core.preventEntityDataUpdate = false;
/*     */     
/* 543 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*     */       
/* 545 */       func_70691_i(1.0F);
/*     */     }
/* 547 */     else if (this.hunger < 144000 && func_70880_s()) {
/* 548 */       setInLove(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 558 */     super.func_70037_a(nbt);
/* 559 */     this.sex = nbt.func_74762_e("Sex");
/* 560 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*     */     
/* 562 */     this.familiarity = nbt.func_74762_e("Familiarity");
/* 563 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/* 564 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*     */     
/* 566 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/* 567 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/* 568 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*     */     
/* 570 */     this.hunger = nbt.func_74762_e("Hunger");
/* 571 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/* 572 */     this.nextEgg = nbt.func_74763_f("nextEgg");
/*     */   }
/*     */ 
/*     */   
/*     */   public void roosterCrow() {
/* 577 */     if ((TFC_Time.getTotalTicks() - 15L) % 24000L == 0L && getGender() == IAnimal.GenderEnum.MALE && isAdult() && this.field_70170_p.func_72937_j((int)this.field_70165_t, (int)this.field_70163_u, (int)this.field_70161_v)) {
/* 578 */       func_85030_a("terrafirmacraft:mob.rooster.cry", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + 0.75F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAge(int par1) {
/* 585 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressionMod(float aggressionMod) {
/* 591 */     this.aggressionMod = aggressionMod;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttackedVec(Vec3 attackedVec) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBirthDay(int day) {
/* 602 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamiliarity(int familiarity) {
/* 608 */     this.familiarity = familiarity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 613 */     this.familiarizedToday = familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFearSource(Entity fearSource) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70873_a(int par1) {
/* 624 */     if (!TFC_Core.preventEntityDataUpdate) {
/* 625 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHunger(int h) {
/* 631 */     this.hunger = h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(boolean b) {
/* 637 */     this.inLove = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 642 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNextEgg(long nextEgg) {
/* 647 */     this.nextEgg = nextEgg;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObedienceMod(float obedienceMod) {
/* 653 */     this.obedienceMod = obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSex(int sex) {
/* 658 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeMod(float sizeMod) {
/* 664 */     this.sizeMod = sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrengthMod(float strengthMod) {
/* 670 */     this.strengthMod = strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncData() {
/* 675 */     if (this.field_70180_af != null)
/*     */     {
/* 677 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 679 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 685 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), 0, 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 691 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 692 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/* 693 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/*     */       }
/*     */       else {
/*     */         
/* 697 */         this.sex = this.field_70180_af.func_75679_c(13);
/*     */         
/* 699 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 700 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/* 701 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/* 702 */         byte[] values = buf.array();
/*     */         
/* 704 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 705 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 706 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 707 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*     */         
/* 709 */         this.familiarity = values[4];
/* 710 */         this.familiarizedToday = (values[5] == 1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 717 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 719 */       func_94058_c(name);
/* 720 */       return true;
/*     */     } 
/* 722 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 723 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 732 */     super.func_70014_b(nbt);
/* 733 */     nbt.func_74768_a("Sex", this.sex);
/* 734 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*     */     
/* 736 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 737 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 738 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*     */     
/* 740 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 741 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 742 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*     */     
/* 744 */     nbt.func_74768_a("Hunger", this.hunger);
/* 745 */     nbt.func_74768_a("Age", getBirthDay());
/* 746 */     nbt.func_74772_a("nextEgg", this.nextEgg);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/* 752 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityChickenTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */