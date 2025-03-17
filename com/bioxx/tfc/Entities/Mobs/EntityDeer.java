/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*     */ import com.bioxx.tfc.Entities.AI.EntityAIPanicTFC;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.api.Entities.IAnimal;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import fof.tfcsu.Entity.EntityBear3D;
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
/*     */ import net.minecraft.entity.ai.EntityAIEatGrass;
/*     */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
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
/*     */ 
/*     */ 
/*     */ public class EntityDeer
/*     */   extends EntityAnimal
/*     */   implements IAnimal
/*     */ {
/*     */   private static final float GESTATION_PERIOD = 7.0F;
/*     */   private static final float DIMORPHISM = 0.1728F;
/*     */   private static final int DEGREE_OF_DIVERSION = 1;
/*     */   private static final int FAMILIARITY_CAP = 70;
/*  58 */   protected final EntityAIEatGrass aiEatGrass = new EntityAIEatGrass((EntityLiving)this);
/*     */   
/*     */   private boolean running;
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
/*  73 */   private float aggressionMod = 1.0F;
/*  74 */   private float obedienceMod = 1.0F;
/*     */   
/*     */   private boolean inLove;
/*     */   
/*     */   private Vec3 attackedVec;
/*     */   
/*     */   private Entity fearSource;
/*     */   private boolean wasRoped;
/*     */   private int familiarity;
/*     */   private long lastFamiliarityUpdate;
/*     */   private boolean familiarizedToday;
/*     */   
/*     */   public EntityDeer(World par1World) {
/*  87 */     super(par1World);
/*  88 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/*  89 */     this.hunger = 168000;
/*  90 */     this.pregnant = false;
/*  91 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 7.0F * (float)TFC_Time.ticksInMonth);
/*  92 */     this.timeOfConception = 0L;
/*  93 */     this.mateSizeMod = 0.0F;
/*  94 */     this.sex = this.field_70146_Z.nextInt(2);
/*  95 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.1728F * this.sex)));
/*  96 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  97 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  98 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*  99 */     this.running = false;
/*     */     
/* 101 */     func_70105_a(0.9F, 1.3F);
/* 102 */     func_70661_as().func_75491_a(true);
/* 103 */     this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/* 104 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanicTFC((EntityCreature)this, 0.6800000071525574D, true));
/* 105 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/* 106 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityPlayer.class, 12.0F, 0.5D, 0.699999988079071D));
/* 107 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
/* 108 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityBear3D.class, 16.0F, 0.25D, 0.30000001192092896D));
/* 109 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent(this, 0.25D));
/* 110 */     this.field_70714_bg.func_75776_a(5, (EntityAIBase)this.aiEatGrass);
/* 111 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.5D));
/* 112 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/* 113 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 119 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDeer(World par1World, IAnimal mother, List<Float> data) {
/* 124 */     this(par1World);
/* 125 */     float fatherSize = 1.0F;
/* 126 */     float fatherStr = 1.0F;
/* 127 */     float fatherAggro = 1.0F;
/* 128 */     float fatherObed = 1.0F;
/* 129 */     for (int i = 0; i < data.size(); i++) {
/* 130 */       switch (i) { case 0:
/* 131 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/* 132 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/* 133 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/* 134 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*     */           break; }
/*     */     
/*     */     } 
/* 138 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/* 139 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/* 140 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/* 141 */     float invSizeRatio = 0.5472855F;
/* 142 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/* 143 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/* 144 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/* 145 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*     */     
/* 147 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*     */ 
/*     */     
/* 150 */     setAge(TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 157 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 158 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 159 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 161 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/* 162 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/* 163 */     if ((temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 100.0F))
/* 164 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/* 165 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 171 */     super.func_110147_ax();
/* 172 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
/* 178 */     Entity entity = par1DamageSource.func_76346_g();
/* 179 */     if (entity != null) {
/*     */       
/* 181 */       setAttackedVec(Vec3.func_72443_a(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v));
/* 182 */       setFearSource(entity);
/*     */     } 
/* 184 */     return super.func_70097_a(par1DamageSource, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/* 190 */     return !this.wasRoped;
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
/*     */   public boolean canFamiliarize() {
/* 202 */     return (!isAdult() || (isAdult() && this.familiarity <= 70));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 208 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityDeer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 214 */     boolean flag = false;
/* 215 */     switch (interaction) { case BREED:
/* 216 */         flag = (this.familiarity > 20); break;
/* 217 */       case NAME: flag = (this.familiarity > 60); break;
/* 218 */       case TOLERATEPLAYER: flag = (this.familiarity > 40);
/*     */         break; }
/*     */     
/* 221 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/* 222 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 224 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable func_90011_a(EntityAgeable entityageable) {
/* 230 */     return createChildTFC(entityageable);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/* 236 */     ArrayList<Float> data = new ArrayList<>();
/* 237 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/* 238 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/* 239 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/* 240 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/* 241 */     return (EntityAgeable)new EntityDeer(this.field_70170_p, this, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 250 */     float ageMod = TFC_Core.getPercentGrown(this);
/* 251 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)((ageMod * this.sizeMod) * 1.84D)))), 0.0F);
/* 252 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(4) + 2) * ageMod));
/* 253 */     float foodWeight = ageMod * this.sizeMod * 2400.0F;
/*     */     
/* 255 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.venisonRaw, foodWeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70615_aA() {
/* 261 */     this.hunger += 24000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 267 */     super.func_70088_a();
/* 268 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/* 269 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*     */     
/* 271 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/* 272 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/* 273 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void familiarize(EntityPlayer ep) {
/* 278 */     ItemStack stack = ep.func_70694_bm();
/* 279 */     if (stack != null && stack.func_77973_b() != null && stack.func_77973_b().equals(TFCItems.powder) && stack.func_77960_j() == 9 && !this.familiarizedToday && 
/* 280 */       canFamiliarize()) {
/*     */       
/* 282 */       if (!ep.field_71075_bZ.field_75098_d) {
/*     */         
/* 284 */         stack.field_77994_a--;
/* 285 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, stack);
/*     */       } 
/* 287 */       this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/* 288 */       this.hunger += 24000;
/* 289 */       this.familiarizedToday = true;
/* 290 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/* 291 */       func_70642_aH();
/* 292 */       ExpBonus.FEED.give(ep);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAggressionMod() {
/* 299 */     return this.aggressionMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 305 */     return Helper.stringToInt("deer");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Vec3 getAttackedVec() {
/* 311 */     return this.attackedVec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirthDay() {
/* 317 */     return this.field_70180_af.func_75679_c(15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70673_aS() {
/* 326 */     return "terrafirmacraft:mob.deer.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item func_146068_u() {
/* 335 */     return Items.field_151116_aA;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDueDay() {
/* 341 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLiving getEntity() {
/* 346 */     return (EntityLiving)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getFamiliarity() {
/* 351 */     return this.familiarity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFamiliarizedToday() {
/* 357 */     return this.familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getFearSource() {
/* 363 */     return this.fearSource;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimal.GenderEnum getGender() {
/* 369 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHunger() {
/* 375 */     return this.hunger;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70621_aR() {
/* 384 */     return "terrafirmacraft:mob.deer.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInLove() {
/* 390 */     return this.inLove;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastFamiliarityUpdate() {
/* 395 */     return this.lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70639_aQ() {
/* 404 */     if (getGender() == IAnimal.GenderEnum.MALE && isAdult() && this.field_70170_p.field_73012_v.nextInt(100) < 5)
/* 405 */       return "terrafirmacraft:mob.deer.cry"; 
/* 406 */     return "terrafirmacraft:mob.deer.say";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDaysToAdult() {
/* 412 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 24.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getObedienceMod() {
/* 418 */     return this.obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPregnancyRequiredTime() {
/* 423 */     return this.pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRunning() {
/* 428 */     return this.running;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSex() {
/* 433 */     return this.sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeMod() {
/* 439 */     return this.sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrengthMod() {
/* 445 */     return this.strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTimeOfConception() {
/* 450 */     return this.timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleFamiliarityUpdate() {
/* 455 */     int totalDays = TFC_Time.getTotalDays();
/* 456 */     if (this.lastFamiliarityUpdate < totalDays) {
/* 457 */       if (this.familiarizedToday && this.familiarity < 100) {
/* 458 */         this.lastFamiliarityUpdate = totalDays;
/* 459 */         this.familiarizedToday = false;
/* 460 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/* 461 */         if (isAdult() && this.familiarity <= 70) {
/*     */           
/* 463 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*     */         }
/* 465 */         else if (!isAdult()) {
/* 466 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/* 467 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/* 468 */           if (this.familiarity > 70) {
/* 469 */             this.obedienceMod *= 1.01F;
/*     */           }
/*     */         }
/*     */       
/* 473 */       } else if (this.familiarity < 30) {
/* 474 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/* 475 */         this.lastFamiliarityUpdate = totalDays;
/*     */       } 
/*     */     }
/* 478 */     if (this.familiarity > 100) this.familiarity = 100; 
/* 479 */     if (this.familiarity < 0) this.familiarity = 0;
/*     */   
/*     */   }
/*     */   
/*     */   public boolean hasBeenRoped() {
/* 484 */     return this.wasRoped;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player) {
/* 493 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 495 */       if (player.func_70093_af() && canFamiliarize()) {
/*     */         
/* 497 */         familiarize(player);
/* 498 */         return true;
/*     */       } 
/* 500 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
/*     */       {
/* 502 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*     */       }
/*     */     } 
/* 505 */     ItemStack itemstack = player.func_70694_bm();
/* 506 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/* 507 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/* 508 */         itemstack.field_77994_a--;
/*     */       }
/* 510 */       return true;
/*     */     } 
/* 512 */     return super.func_70085_c(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 518 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70650_aV() {
/* 527 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70631_g_() {
/* 533 */     return !isAdult();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack item) {
/* 539 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPregnant() {
/* 545 */     return this.pregnant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mate(IAnimal otherAnimal) {
/* 551 */     if (this.sex == 0) {
/*     */       
/* 553 */       otherAnimal.mate(this);
/*     */       return;
/*     */     } 
/* 556 */     this.timeOfConception = TFC_Time.getTotalTicks();
/* 557 */     this.pregnant = true;
/* 558 */     func_70875_t();
/* 559 */     otherAnimal.setInLove(false);
/* 560 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/* 561 */     this.mateObedMod = otherAnimal.getObedienceMod();
/* 562 */     this.mateSizeMod = otherAnimal.getSizeMod();
/* 563 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 573 */     if (func_70880_s()) {
/*     */       
/* 575 */       func_70875_t();
/* 576 */       setInLove(true);
/*     */     } 
/*     */     
/* 579 */     if (func_110167_bD() && !this.wasRoped) this.wasRoped = true;
/*     */     
/* 581 */     syncData();
/* 582 */     if (isAdult()) {
/*     */       
/* 584 */       func_70873_a(0);
/*     */     }
/*     */     else {
/*     */       
/* 588 */       func_70873_a(-1);
/*     */     } 
/*     */     
/* 591 */     handleFamiliarityUpdate();
/*     */     
/* 593 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*     */     {
/* 595 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*     */         
/* 597 */         EntityDeer baby = (EntityDeer)createChildTFC((EntityAgeable)this);
/* 598 */         baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/* 599 */         baby.field_70759_as = baby.field_70177_z;
/* 600 */         baby.field_70761_aq = baby.field_70177_z;
/* 601 */         this.field_70170_p.func_72838_d((Entity)baby);
/* 602 */         baby.setAge(TFC_Time.getTotalDays());
/* 603 */         this.pregnant = false;
/*     */       } 
/*     */     }
/*     */     
/* 607 */     if (this.attackedVec != null) {
/*     */ 
/*     */       
/* 610 */       Vec3 positionVec = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 611 */       if (getFearSource() != null && func_70068_e(getFearSource()) > 144.0D) {
/*     */         
/* 613 */         setFearSource((Entity)null);
/*     */       }
/* 615 */       else if (positionVec.func_72438_d(getAttackedVec()) > 16.0D) {
/*     */         
/* 617 */         setAttackedVec((Vec3)null);
/*     */       } 
/*     */     } 
/*     */     
/* 621 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*     */       
/* 623 */       func_70691_i(1.0F);
/*     */     }
/* 625 */     else if (this.hunger < 144000 && func_70880_s()) {
/* 626 */       setInLove(false);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 632 */     TFC_Core.preventEntityDataUpdate = true;
/* 633 */     super.func_70636_d();
/* 634 */     TFC_Core.preventEntityDataUpdate = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 643 */     super.func_70037_a(nbt);
/* 644 */     this.animalID = nbt.func_74763_f("Animal ID");
/* 645 */     this.sex = nbt.func_74762_e("Sex");
/* 646 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*     */     
/* 648 */     this.familiarity = nbt.func_74762_e("Familiarity");
/* 649 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/* 650 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*     */     
/* 652 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/* 653 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/* 654 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*     */     
/* 656 */     this.wasRoped = nbt.func_74767_n("wasRoped");
/*     */     
/* 658 */     this.hunger = nbt.func_74762_e("Hunger");
/* 659 */     this.pregnant = nbt.func_74767_n("Pregnant");
/* 660 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/* 661 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/* 662 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/* 663 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/* 664 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/* 665 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAge(int par1) {
/* 671 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressionMod(float aggressionMod) {
/* 677 */     this.aggressionMod = aggressionMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttackedVec(Vec3 attackedVec) {
/* 683 */     this.attackedVec = attackedVec;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBirthDay(int day) {
/* 689 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamiliarity(int familiarity) {
/* 695 */     this.familiarity = familiarity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 700 */     this.familiarizedToday = familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFearSource(Entity fearSource) {
/* 706 */     this.fearSource = fearSource;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70873_a(int par1) {
/* 712 */     if (!TFC_Core.preventEntityDataUpdate)
/*     */     {
/* 714 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHunger(int h) {
/* 721 */     this.hunger = h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(boolean b) {
/* 727 */     this.inLove = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 732 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObedienceMod(float obedienceMod) {
/* 738 */     this.obedienceMod = obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 743 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnant(boolean pregnant) {
/* 748 */     this.pregnant = pregnant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRunning(boolean r) {
/* 753 */     this.running = r;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSex(int sex) {
/* 758 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeMod(float sizeMod) {
/* 764 */     this.sizeMod = sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrengthMod(float strengthMod) {
/* 770 */     this.strengthMod = strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeOfConception(long timeOfConception) {
/* 775 */     this.timeOfConception = timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWasRoped(boolean wasRoped) {
/* 780 */     this.wasRoped = wasRoped;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncData() {
/* 785 */     if (this.field_70180_af != null)
/*     */     {
/* 787 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 789 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 795 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 801 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 802 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/* 803 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/* 804 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*     */       }
/*     */       else {
/*     */         
/* 808 */         this.sex = this.field_70180_af.func_75679_c(13);
/*     */         
/* 810 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 811 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/* 812 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/* 813 */         byte[] values = buf.array();
/*     */         
/* 815 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 816 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 817 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 818 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*     */         
/* 820 */         this.familiarity = values[4];
/* 821 */         this.familiarizedToday = (values[5] == 1);
/* 822 */         this.pregnant = (values[6] == 1);
/*     */ 
/*     */         
/*     */         try {
/* 826 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/* 827 */         } catch (NumberFormatException numberFormatException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 834 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 836 */       func_94058_c(name);
/* 837 */       return true;
/*     */     } 
/* 839 */     func_85030_a("terrafirmacraft:mob.deer.cry", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 840 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 849 */     super.func_70014_b(nbt);
/* 850 */     nbt.func_74768_a("Sex", this.sex);
/* 851 */     nbt.func_74772_a("Animal ID", this.animalID);
/* 852 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*     */     
/* 854 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 855 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 856 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*     */     
/* 858 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 859 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 860 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*     */     
/* 862 */     nbt.func_74757_a("wasRoped", this.wasRoped);
/*     */     
/* 864 */     nbt.func_74768_a("Hunger", this.hunger);
/* 865 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 866 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 867 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 868 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 869 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 870 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 871 */     nbt.func_74768_a("Age", getBirthDay());
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */