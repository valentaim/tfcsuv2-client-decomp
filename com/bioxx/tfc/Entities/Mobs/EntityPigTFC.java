/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ import net.minecraft.entity.ai.EntityAIPanic;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.effect.EntityLightningBolt;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntityPig;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntityPigTFC
/*     */   extends EntityPig
/*     */   implements IAnimal
/*     */ {
/*     */   private static final float GESTATION_PERIOD = 3.7F;
/*     */   private static final float DIMORPHISM = 0.271F;
/*     */   private static final int DEGREE_OF_DIVERSION = 2;
/*     */   private static final int FAMILIARITY_CAP = 35;
/*  55 */   protected final EntityAIEatGrass aiEatGrass = new EntityAIEatGrass((EntityLiving)this);
/*     */   
/*     */   private long animalID;
/*     */   private int sex;
/*     */   private int hunger;
/*     */   private boolean pregnant;
/*     */   private int pregnancyRequiredTime;
/*     */   private long timeOfConception;
/*     */   private float mateSizeMod;
/*     */   private float mateStrengthMod;
/*     */   private float mateAggroMod;
/*     */   private float mateObedMod;
/*     */   private float sizeMod;
/*     */   private float strengthMod;
/*  69 */   private float aggressionMod = 1.0F;
/*  70 */   private float obedienceMod = 1.0F;
/*     */   
/*     */   private boolean inLove;
/*     */   private int familiarity;
/*     */   private long lastFamiliarityUpdate;
/*     */   private boolean familiarizedToday;
/*     */   
/*     */   public EntityPigTFC(World par1World) {
/*  78 */     super(par1World);
/*  79 */     func_70105_a(0.9F, 0.9F);
/*  80 */     func_70661_as().func_75491_a(true);
/*  81 */     this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  82 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanic((EntityCreature)this, 0.3799999952316284D));
/*  83 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/*  84 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/*  85 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/*  86 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/*  87 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/*  88 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/*  89 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/*  90 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 0.2800000011920929D));
/*  91 */     this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.75D));
/*  92 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*  93 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPigTFC.class, 6.0F));
/*     */ 
/*     */     
/*  96 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*     */     
/*  98 */     this.hunger = 168000;
/*  99 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/* 100 */     this.pregnant = false;
/* 101 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 3.7F * (float)TFC_Time.ticksInMonth);
/* 102 */     this.timeOfConception = 0L;
/* 103 */     this.mateSizeMod = 0.0F;
/* 104 */     this.sex = this.field_70146_Z.nextInt(2);
/* 105 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.271F * this.sex)));
/* 106 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/* 107 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/* 108 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 114 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPigTFC(World par1World, IAnimal mother, List<Float> data) {
/* 120 */     this(par1World);
/* 121 */     float fatherSize = 1.0F;
/* 122 */     float fatherStr = 1.0F;
/* 123 */     float fatherAggro = 1.0F;
/* 124 */     float fatherObed = 1.0F;
/* 125 */     for (int i = 0; i < data.size(); i++) {
/* 126 */       switch (i) { case 0:
/* 127 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/* 128 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/* 129 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/* 130 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*     */           break; }
/*     */     
/*     */     } 
/* 134 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/* 135 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/* 136 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/* 137 */     float invSizeRatio = 0.578369F;
/* 138 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/* 139 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/* 140 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/* 141 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*     */     
/* 143 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*     */ 
/*     */     
/* 146 */     setAge(TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 153 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 154 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 155 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 157 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/* 158 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/* 159 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/* 160 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/* 161 */     if ((!isMountainous && temp > 20.0F && rain > 100.0F && rain <= 500.0F) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F) || (temp >= 23.0F && temp < 44.0F && rain > 1500.0F) || TFC_Climate.isSwamp(this.field_70170_p, i, 150, k)) {
/* 162 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
/*     */     }
/*     */     
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 171 */     super.func_110147_ax();
/* 172 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFamiliarize() {
/* 178 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 184 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityPigTFC);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 190 */     boolean flag = false;
/* 191 */     switch (interaction) { case MOUNT:
/* 192 */         flag = (this.familiarity > 15); break;
/* 193 */       case BREED: flag = (this.familiarity > 10); break;
/* 194 */       case NAME: flag = (this.familiarity > 40);
/*     */         break; }
/*     */     
/* 197 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/* 198 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 200 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityPig func_90011_a(EntityAgeable entityageable) {
/* 206 */     return (EntityPig)createChildTFC(entityageable);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/* 212 */     ArrayList<Float> data = new ArrayList<>();
/* 213 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/* 214 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/* 215 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/* 216 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/* 217 */     return (EntityAgeable)new EntityPigTFC(this.field_70170_p, this, data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 223 */     float ageMod = TFC_Core.getPercentGrown(this);
/*     */     
/* 225 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * this.sizeMod)))), 0.0F);
/* 226 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(4) + 2) * ageMod));
/*     */     
/* 228 */     float foodWeight = ageMod * this.sizeMod * 2400.0F;
/* 229 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.porkchopRaw, foodWeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70615_aA() {
/* 235 */     this.hunger += 24000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 241 */     super.func_70088_a();
/* 242 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/* 243 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/* 244 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/* 245 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/* 246 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70069_a(float par1) {
/* 255 */     super.func_70069_a(par1);
/*     */     
/* 257 */     if (par1 > 5.0F && this.field_70153_n instanceof EntityPlayer) {
/* 258 */       ((EntityPlayer)this.field_70153_n).func_71029_a((StatBase)AchievementList.field_76021_u);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void familiarize(EntityPlayer ep) {
/* 264 */     ItemStack stack = ep.func_70694_bm();
/* 265 */     if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {
/*     */       
/* 267 */       if (!ep.field_71075_bZ.field_75098_d) {
/*     */         
/* 269 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/*     */       else {
/*     */         
/* 273 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/* 275 */       this.hunger += 24000;
/* 276 */       this.familiarizedToday = true;
/* 277 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/* 278 */       func_70642_aH();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAggressionMod() {
/* 285 */     return this.aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getAnimalID() {
/* 290 */     return this.animalID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 296 */     return Helper.stringToInt("pig");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getAttackedVec() {
/* 302 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirthDay() {
/* 308 */     return this.field_70180_af.func_75679_c(15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item func_146068_u() {
/* 317 */     return func_70027_ad() ? Items.field_151157_am : Items.field_151147_al;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDueDay() {
/* 323 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLiving getEntity() {
/* 329 */     return (EntityLiving)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFamiliarity() {
/* 334 */     return this.familiarity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFamiliarizedToday() {
/* 340 */     return this.familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getFearSource() {
/* 346 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimal.GenderEnum getGender() {
/* 352 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHunger() {
/* 358 */     return this.hunger;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInLove() {
/* 364 */     return this.inLove;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastFamiliarityUpdate() {
/* 369 */     return this.lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDaysToAdult() {
/* 375 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 15.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getObedienceMod() {
/* 381 */     return this.obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPregnancyRequiredTime() {
/* 386 */     return this.pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70901_n() {
/* 395 */     return ((this.field_70180_af.func_75683_a(16) & 0x1) != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSex() {
/* 400 */     return this.sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeMod() {
/* 406 */     return this.sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrengthMod() {
/* 412 */     return this.strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTimeOfConception() {
/* 417 */     return this.timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFamiliarityUpdate() {
/* 422 */     int totalDays = TFC_Time.getTotalDays();
/* 423 */     if (this.lastFamiliarityUpdate < totalDays) {
/* 424 */       if (this.familiarizedToday && this.familiarity < 100) {
/* 425 */         this.lastFamiliarityUpdate = totalDays;
/* 426 */         this.familiarizedToday = false;
/* 427 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/* 428 */         if (isAdult() && this.familiarity <= 35) {
/*     */           
/* 430 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*     */         }
/* 432 */         else if (!isAdult()) {
/* 433 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/* 434 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/* 435 */           if (this.familiarity > 70) {
/* 436 */             this.obedienceMod *= 1.01F;
/*     */           }
/*     */         }
/*     */       
/* 440 */       } else if (this.familiarity < 30) {
/* 441 */         this.familiarity = (int)(this.familiarity - 2L * (totalDays - this.lastFamiliarityUpdate));
/* 442 */         this.lastFamiliarityUpdate = totalDays;
/*     */       } 
/*     */     }
/* 445 */     if (this.familiarity > 100) this.familiarity = 100; 
/* 446 */     if (this.familiarity < 0) this.familiarity = 0;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player) {
/* 455 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 457 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*     */         
/* 459 */         familiarize(player);
/* 460 */         return true;
/*     */       } 
/* 462 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
/* 463 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*     */       }
/*     */     } 
/* 466 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*     */     
/* 468 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || 
/* 469 */       !canFamiliarize())) {
/*     */       
/* 471 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 473 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/* 475 */       this.hunger += 24000;
/* 476 */       func_146082_f(player);
/* 477 */       return true;
/*     */     } 
/* 479 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/* 480 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/* 481 */         itemstack.field_77994_a--;
/*     */       }
/* 483 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 487 */     if (super.func_70085_c(player))
/*     */     {
/* 489 */       return true;
/*     */     }
/* 491 */     if (func_70901_n() && !this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == player)) {
/*     */       
/* 493 */       player.func_70078_a((Entity)this);
/* 494 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 498 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 506 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70650_aV() {
/* 515 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack par1ItemStack) {
/* 521 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreedingItemTFC(ItemStack item) {
/* 526 */     return (!this.pregnant && isFood(item));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70631_g_() {
/* 532 */     return !isAdult();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack item) {
/* 537 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/* 538 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPregnant() {
/* 544 */     return this.pregnant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mate(IAnimal otherAnimal) {
/* 550 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*     */       
/* 552 */       otherAnimal.mate(this);
/*     */       return;
/*     */     } 
/* 555 */     this.timeOfConception = TFC_Time.getTotalTicks();
/* 556 */     this.pregnant = true;
/* 557 */     func_70875_t();
/* 558 */     otherAnimal.setInLove(false);
/* 559 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/* 560 */     this.mateObedMod = otherAnimal.getObedienceMod();
/* 561 */     this.mateSizeMod = otherAnimal.getSizeMod();
/* 562 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 569 */     if (this.hunger > 168000)
/* 570 */       this.hunger = 168000; 
/* 571 */     if (this.hunger > 0) {
/* 572 */       this.hunger--;
/*     */     }
/* 574 */     if (func_70880_s()) {
/*     */       
/* 576 */       func_70875_t();
/* 577 */       setInLove(true);
/*     */     } 
/*     */     
/* 580 */     handleFamiliarityUpdate();
/*     */     
/* 582 */     syncData();
/* 583 */     if (isAdult()) {
/* 584 */       func_70873_a(0);
/*     */     } else {
/* 586 */       func_70873_a(-1);
/*     */     } 
/* 588 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*     */     {
/* 590 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*     */         
/* 592 */         for (int i = 0; i < 8 + this.field_70146_Z.nextInt(5); i++) {
/*     */           
/* 594 */           EntityPigTFC baby = (EntityPigTFC)createChildTFC((EntityAgeable)this);
/* 595 */           baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/* 596 */           baby.field_70759_as = baby.field_70177_z;
/* 597 */           baby.field_70761_aq = baby.field_70177_z;
/* 598 */           this.field_70170_p.func_72838_d((Entity)baby);
/* 599 */           baby.setAge(TFC_Time.getTotalDays());
/*     */         } 
/* 601 */         this.pregnant = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 608 */     TFC_Core.preventEntityDataUpdate = true;
/* 609 */     super.func_70636_d();
/* 610 */     TFC_Core.preventEntityDataUpdate = false;
/*     */     
/* 612 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/* 613 */       func_70691_i(1.0F);
/*     */     }
/* 615 */     else if (this.hunger < 144000 && func_70880_s()) {
/* 616 */       setInLove(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70077_a(EntityLightningBolt par1EntityLightningBolt) {
/* 627 */     func_70081_e(5);
/* 628 */     func_70015_d(8);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 634 */     super.func_70037_a(nbt);
/* 635 */     this.animalID = nbt.func_74763_f("Animal ID");
/* 636 */     this.sex = nbt.func_74762_e("Sex");
/* 637 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*     */     
/* 639 */     this.familiarity = nbt.func_74762_e("Familiarity");
/* 640 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/* 641 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*     */     
/* 643 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/* 644 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/* 645 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*     */     
/* 647 */     this.hunger = nbt.func_74762_e("Hunger");
/* 648 */     this.pregnant = nbt.func_74767_n("Pregnant");
/* 649 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/* 650 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/* 651 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/* 652 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/* 653 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/* 654 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/* 655 */     func_70900_e(nbt.func_74767_n("Saddle"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAge(int par1) {
/* 662 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressionMod(float aggressionMod) {
/* 669 */     this.aggressionMod = aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnimalID(long animalID) {
/* 674 */     this.animalID = animalID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttackedVec(Vec3 attackedVec) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBirthDay(int day) {
/* 686 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamiliarity(int familiarity) {
/* 692 */     this.familiarity = familiarity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 697 */     this.familiarizedToday = familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFearSource(Entity fearSource) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70873_a(int par1) {
/* 709 */     if (!TFC_Core.preventEntityDataUpdate) {
/* 710 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHunger(int h) {
/* 716 */     this.hunger = h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(boolean b) {
/* 722 */     this.inLove = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 727 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObedienceMod(float obedienceMod) {
/* 733 */     this.obedienceMod = obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 738 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnant(boolean pregnant) {
/* 743 */     this.pregnant = pregnant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70900_e(boolean par1) {
/* 752 */     if (par1) {
/* 753 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)1));
/*     */     } else {
/* 755 */       this.field_70180_af.func_75692_b(16, Byte.valueOf((byte)0));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setSex(int sex) {
/* 760 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeMod(float sizeMod) {
/* 766 */     this.sizeMod = sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrengthMod(float strengthMod) {
/* 772 */     this.strengthMod = strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeOfConception(long timeOfConception) {
/* 777 */     this.timeOfConception = timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncData() {
/* 782 */     if (this.field_70180_af != null)
/*     */     {
/* 784 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 786 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 792 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 798 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 799 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/* 800 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/* 801 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*     */       }
/*     */       else {
/*     */         
/* 805 */         this.sex = this.field_70180_af.func_75679_c(13);
/*     */         
/* 807 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 808 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/* 809 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/* 810 */         byte[] values = buf.array();
/*     */         
/* 812 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 813 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 814 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 815 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*     */         
/* 817 */         this.familiarity = values[4];
/* 818 */         this.familiarizedToday = (values[5] == 1);
/* 819 */         this.pregnant = (values[6] == 1);
/*     */ 
/*     */         
/*     */         try {
/* 823 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/* 824 */         } catch (NumberFormatException numberFormatException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 831 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 833 */       func_94058_c(name);
/* 834 */       return true;
/*     */     } 
/* 836 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 837 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 843 */     super.func_70014_b(nbt);
/* 844 */     nbt.func_74768_a("Sex", this.sex);
/* 845 */     nbt.func_74772_a("Animal ID", this.animalID);
/* 846 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*     */     
/* 848 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 849 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 850 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*     */     
/* 852 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 853 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 854 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*     */     
/* 856 */     nbt.func_74768_a("Hunger", this.hunger);
/* 857 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 858 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 859 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 860 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 861 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 862 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 863 */     nbt.func_74768_a("Age", getBirthDay());
/* 864 */     nbt.func_74757_a("Saddle", func_70901_n());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/* 870 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityPigTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */