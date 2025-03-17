/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.AI.AIEatGrass;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.Items.Tools.ItemCustomBucketMilk;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import fof.tfcsu.utils.ExpBonus;
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
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.passive.EntityCow;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityCowTFC
/*     */   extends EntityCow
/*     */   implements IAnimal
/*     */ {
/*     */   private static final float GESTATION_PERIOD = 9.0F;
/*     */   private static final float DIMORPHISM = 0.1822F;
/*     */   private static final int DEGREE_OF_DIVERSION = 1;
/*     */   private static final int FAMILIARITY_CAP = 35;
/*  50 */   protected final AIEatGrass aiEatGrass = new AIEatGrass(this);
/*     */   
/*     */   private long animalID;
/*     */   private int sex;
/*     */   private int hunger;
/*     */   private long hasMilkTime;
/*     */   private boolean canMilk;
/*     */   private boolean pregnant;
/*     */   private int pregnancyRequiredTime;
/*     */   private long timeOfConception;
/*     */   private float mateSizeMod;
/*     */   private float mateStrengthMod;
/*     */   private float mateAggroMod;
/*     */   private float mateObedMod;
/*     */   private float sizeMod;
/*     */   private float strengthMod;
/*  66 */   private float aggressionMod = 1.0F;
/*  67 */   private float obedienceMod = 1.0F;
/*     */   
/*     */   private boolean inLove;
/*     */   
/*     */   private int familiarity;
/*     */   private long lastFamiliarityUpdate;
/*     */   private boolean familiarizedToday;
/*     */   
/*     */   public EntityCowTFC(World par1World) {
/*  76 */     super(par1World);
/*  77 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/*  78 */     this.hunger = 168000;
/*  79 */     this.pregnant = false;
/*  80 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 9.0F * (float)TFC_Time.ticksInMonth);
/*  81 */     this.timeOfConception = 0L;
/*  82 */     this.mateSizeMod = 0.0F;
/*  83 */     this.sex = this.field_70146_Z.nextInt(2);
/*     */     
/*  85 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.1822F * this.sex)));
/*  86 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  87 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  88 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*  89 */     func_70105_a(0.9F, 1.3F);
/*  90 */     func_70661_as().func_75491_a(true);
/*  91 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/*  92 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/*  93 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/*  94 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/*  95 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/*  96 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/*  97 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/*  98 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
/*  99 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 105 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityCowTFC(World par1World, IAnimal mother, List<Float> data) {
/* 112 */     this(par1World);
/* 113 */     float fatherSize = 1.0F;
/* 114 */     float fatherStr = 1.0F;
/* 115 */     float fatherAggro = 1.0F;
/* 116 */     float fatherObed = 1.0F;
/* 117 */     for (int i = 0; i < data.size(); i++) {
/* 118 */       switch (i) { case 0:
/* 119 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/* 120 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/* 121 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/* 122 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*     */           break; }
/*     */     
/*     */     } 
/* 126 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/* 127 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/* 128 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/* 129 */     float invSizeRatio = 0.5501155F;
/* 130 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/* 131 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/* 132 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/* 133 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*     */     
/* 135 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*     */ 
/*     */     
/* 138 */     setAge(TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 145 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 146 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 147 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 148 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/* 149 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/* 150 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/* 151 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/* 152 */     if (!isMountainous && temp > 0.0F && temp < 30.0F && rain > 100.0F && rain <= 500.0F)
/* 153 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/* 154 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 160 */     super.func_110147_ax();
/* 161 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(500.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFamiliarize() {
/* 167 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 173 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityCowTFC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 180 */     boolean flag = false;
/* 181 */     switch (interaction) {
/*     */       
/*     */       case BREED:
/* 184 */         flag = (this.familiarity > 20);
/*     */         break;
/*     */       case MILK:
/* 187 */         flag = (this.familiarity > 15);
/*     */         break;
/*     */       case NAME:
/* 190 */         flag = (this.familiarity > 40);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 195 */     if (!flag && player != null && !player.field_70170_p.field_72995_K)
/*     */     {
/* 197 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 199 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityCow func_90011_a(EntityAgeable entityageable) {
/* 205 */     return (EntityCow)createChildTFC(entityageable);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/* 211 */     ArrayList<Float> data = new ArrayList<>();
/* 212 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/* 213 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/* 214 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/* 215 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/* 216 */     return (EntityAgeable)new EntityCowTFC(this.field_70170_p, this, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 225 */     float ageMod = TFC_Core.getPercentGrown(this);
/*     */     
/* 227 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
/* 228 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(6) + 3) * ageMod));
/*     */     
/* 230 */     float foodWeight = ageMod * this.sizeMod * 4000.0F;
/*     */     
/* 232 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.beefRaw, foodWeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70615_aA() {
/* 238 */     this.hunger += 24000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 244 */     super.func_70088_a();
/* 245 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/* 246 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*     */     
/* 248 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/* 249 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/* 250 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void familiarize(EntityPlayer ep) {
/* 256 */     ItemStack stack = ep.func_70694_bm();
/* 257 */     if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {
/*     */       
/* 259 */       if (!ep.field_71075_bZ.field_75098_d) {
/*     */         
/* 261 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/*     */       else {
/*     */         
/* 265 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/* 267 */       this.hunger += 24000;
/* 268 */       this.familiarizedToday = true;
/* 269 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/* 270 */       func_70642_aH();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAggressionMod() {
/* 277 */     return this.aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getAnimalID() {
/* 282 */     return this.animalID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 288 */     return Helper.stringToInt("cow");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getAttackedVec() {
/* 294 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirthDay() {
/* 300 */     return this.field_70180_af.func_75679_c(15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item func_146068_u() {
/* 309 */     return Items.field_151116_aA;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDueDay() {
/* 315 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLiving getEntity() {
/* 322 */     return (EntityLiving)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFamiliarity() {
/* 328 */     return this.familiarity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFamiliarizedToday() {
/* 334 */     return this.familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getFearSource() {
/* 340 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimal.GenderEnum getGender() {
/* 346 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*     */   }
/*     */ 
/*     */   
/*     */   public long getHasMilkTime() {
/* 351 */     return this.hasMilkTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHunger() {
/* 357 */     return this.hunger;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInLove() {
/* 363 */     return this.inLove;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastFamiliarityUpdate() {
/* 368 */     return this.lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDaysToAdult() {
/* 374 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 36.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getObedienceMod() {
/* 380 */     return this.obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPregnancyRequiredTime() {
/* 385 */     return this.pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSex() {
/* 390 */     return this.sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeMod() {
/* 396 */     return this.sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrengthMod() {
/* 402 */     return this.strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTimeOfConception() {
/* 407 */     return this.timeOfConception;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleFamiliarityUpdate() {
/* 413 */     int totalDays = TFC_Time.getTotalDays();
/* 414 */     if (this.lastFamiliarityUpdate < totalDays)
/*     */     {
/* 416 */       if (this.familiarizedToday && this.familiarity < 100) {
/*     */         
/* 418 */         this.lastFamiliarityUpdate = totalDays;
/* 419 */         this.familiarizedToday = false;
/* 420 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/* 421 */         if (isAdult() && this.familiarity <= 35)
/*     */         {
/* 423 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*     */         }
/* 425 */         else if (!isAdult())
/*     */         {
/* 427 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/* 428 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/* 429 */           if (this.familiarity > 70)
/*     */           {
/* 431 */             this.obedienceMod *= 1.01F;
/*     */           }
/*     */         }
/*     */       
/* 435 */       } else if (this.familiarity < 30) {
/*     */         
/* 437 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/* 438 */         this.lastFamiliarityUpdate = totalDays;
/*     */       } 
/*     */     }
/* 441 */     if (this.familiarity > 100)
/* 442 */       this.familiarity = 100; 
/* 443 */     if (this.familiarity < 0) {
/* 444 */       this.familiarity = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player) {
/* 453 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 455 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*     */         
/* 457 */         familiarize(player);
/* 458 */         return true;
/*     */       } 
/*     */       
/* 461 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
/*     */       {
/* 463 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*     */       }
/*     */       
/* 466 */       if (getGender() == IAnimal.GenderEnum.FEMALE && isAdult() && this.hasMilkTime < TFC_Time.getTotalTicks() && checkFamiliarity(IAnimal.InteractionEnum.MILK, player)) {
/*     */         
/* 468 */         ItemStack heldItem = player.field_71071_by.func_70448_g();
/* 469 */         if (heldItem != null && heldItem.func_77973_b() == TFCItems.woodenBucketEmpty) {
/*     */           
/* 471 */           if (!this.familiarizedToday && this.familiarity <= 35) {
/*     */             
/* 473 */             this.familiarizedToday = true;
/* 474 */             func_70671_ap().func_75651_a((Entity)player, 0.0F, 0.0F);
/* 475 */             func_70642_aH();
/*     */           } 
/*     */           
/* 478 */           ItemStack milkBucket = new ItemStack(TFCItems.woodenBucketMilk);
/* 479 */           ItemCustomBucketMilk.createTag(milkBucket, 20.0F);
/* 480 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, milkBucket);
/* 481 */           this.hasMilkTime = TFC_Time.getTotalTicks() + 24000L;
/* 482 */           ExpBonus.MILK.give(player);
/* 483 */           return true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 488 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/* 489 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || 
/* 490 */       !canFamiliarize())) {
/*     */       
/* 492 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 494 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/* 496 */       this.hunger += 24000;
/* 497 */       func_146082_f(player);
/* 498 */       return true;
/*     */     } 
/* 500 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/* 501 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/* 502 */         itemstack.field_77994_a--;
/*     */       }
/* 504 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 508 */     return super.func_70085_c(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 515 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
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
/*     */   
/*     */   public boolean isFood(ItemStack item) {
/* 538 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/* 539 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMilkable() {
/* 544 */     return this.canMilk;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPregnant() {
/* 550 */     return this.pregnant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mate(IAnimal otherAnimal) {
/* 556 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*     */       
/* 558 */       otherAnimal.mate(this);
/*     */       return;
/*     */     } 
/* 561 */     this.timeOfConception = TFC_Time.getTotalTicks();
/* 562 */     this.pregnant = true;
/* 563 */     func_70875_t();
/* 564 */     otherAnimal.setInLove(false);
/* 565 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/* 566 */     this.mateObedMod = otherAnimal.getObedienceMod();
/* 567 */     this.mateSizeMod = otherAnimal.getSizeMod();
/* 568 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 575 */     if (this.hunger > 168000)
/*     */     {
/* 577 */       this.hunger = 168000;
/*     */     }
/* 579 */     if (this.hunger > 0)
/*     */     {
/* 581 */       this.hunger--;
/*     */     }
/*     */     
/* 584 */     if (func_70880_s()) {
/*     */       
/* 586 */       func_70875_t();
/* 587 */       setInLove(true);
/*     */     } 
/*     */     
/* 590 */     handleFamiliarityUpdate();
/*     */     
/* 592 */     if (getGender() == IAnimal.GenderEnum.FEMALE && isAdult() && this.hasMilkTime < TFC_Time.getTotalTicks() && checkFamiliarity(IAnimal.InteractionEnum.MILK, (EntityPlayer)null)) {
/* 593 */       this.canMilk = true;
/*     */     } else {
/* 595 */       this.canMilk = false;
/*     */     } 
/* 597 */     syncData();
/* 598 */     if (isAdult()) {
/*     */       
/* 600 */       func_70873_a(0);
/*     */     }
/*     */     else {
/*     */       
/* 604 */       func_70873_a(-1);
/*     */     } 
/* 606 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*     */     {
/* 608 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*     */         
/* 610 */         EntityCowTFC baby = (EntityCowTFC)createChildTFC((EntityAgeable)this);
/* 611 */         baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/* 612 */         baby.field_70759_as = baby.field_70177_z;
/* 613 */         baby.field_70761_aq = baby.field_70177_z;
/* 614 */         this.field_70170_p.func_72838_d((Entity)baby);
/* 615 */         baby.setAge(TFC_Time.getTotalDays());
/* 616 */         this.pregnant = false;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 623 */     TFC_Core.preventEntityDataUpdate = true;
/* 624 */     super.func_70636_d();
/* 625 */     TFC_Core.preventEntityDataUpdate = false;
/*     */     
/* 627 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*     */       
/* 629 */       func_70691_i(1.0F);
/*     */     }
/* 631 */     else if (this.hunger < 144000 && func_70880_s()) {
/*     */       
/* 633 */       setInLove(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 640 */     super.func_70037_a(nbt);
/* 641 */     this.animalID = nbt.func_74763_f("Animal ID");
/* 642 */     this.sex = nbt.func_74762_e("Sex");
/* 643 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*     */     
/* 645 */     this.familiarity = nbt.func_74762_e("Familiarity");
/* 646 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/* 647 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*     */     
/* 649 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/* 650 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/* 651 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*     */     
/* 653 */     this.hunger = nbt.func_74762_e("Hunger");
/* 654 */     this.pregnant = nbt.func_74767_n("Pregnant");
/* 655 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/* 656 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/* 657 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/* 658 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/* 659 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/* 660 */     this.hasMilkTime = nbt.func_74763_f("HasMilkTime");
/* 661 */     this.canMilk = nbt.func_74767_n("CanMilk");
/* 662 */     setAge(nbt.func_74762_e("Age"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAge(int par1) {
/* 669 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressionMod(float aggressionMod) {
/* 676 */     this.aggressionMod = aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnimalID(long animalID) {
/* 681 */     this.animalID = animalID;
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
/* 693 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanMilk(boolean canMilk) {
/* 698 */     this.canMilk = canMilk;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamiliarity(int familiarity) {
/* 704 */     this.familiarity = familiarity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 709 */     this.familiarizedToday = familiarizedToday;
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
/* 721 */     if (!TFC_Core.preventEntityDataUpdate)
/*     */     {
/* 723 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHasMilkTime(long hasMilkTime) {
/* 729 */     this.hasMilkTime = hasMilkTime;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHunger(int h) {
/* 735 */     this.hunger = h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(boolean b) {
/* 741 */     this.inLove = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 746 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObedienceMod(float obedienceMod) {
/* 752 */     this.obedienceMod = obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 757 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnant(boolean pregnant) {
/* 762 */     this.pregnant = pregnant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSex(int sex) {
/* 767 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeMod(float sizeMod) {
/* 773 */     this.sizeMod = sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrengthMod(float strengthMod) {
/* 779 */     this.strengthMod = strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeOfConception(long timeOfConception) {
/* 784 */     this.timeOfConception = timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncData() {
/* 789 */     if (this.field_70180_af != null)
/*     */     {
/* 791 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 793 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*     */         
/* 795 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), (byte)(this.canMilk ? 1 : 0) };
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
/* 820 */         this.canMilk = (values[7] == 1);
/*     */ 
/*     */         
/*     */         try {
/* 824 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/* 825 */         } catch (NumberFormatException numberFormatException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 834 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 836 */       func_94058_c(name);
/* 837 */       func_94061_f(true);
/* 838 */       return true;
/*     */     } 
/* 840 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 841 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 847 */     super.func_70014_b(nbt);
/* 848 */     nbt.func_74768_a("Sex", this.sex);
/* 849 */     nbt.func_74772_a("Animal ID", this.animalID);
/* 850 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*     */     
/* 852 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 853 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 854 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*     */     
/* 856 */     NBTTagCompound nbt2 = nbt;
/* 857 */     nbt2.func_74776_a("Strength Modifier", this.strengthMod);
/* 858 */     nbt2.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 859 */     nbt2.func_74776_a("Obedience Modifier", this.obedienceMod);
/*     */     
/* 861 */     nbt.func_74768_a("Hunger", this.hunger);
/* 862 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 863 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 864 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 865 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 866 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 867 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 868 */     nbt.func_74768_a("Age", getBirthDay());
/* 869 */     nbt.func_74772_a("HasMilkTime", this.hasMilkTime);
/* 870 */     nbt.func_74757_a("CanMilk", this.canMilk);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/* 876 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityCowTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */