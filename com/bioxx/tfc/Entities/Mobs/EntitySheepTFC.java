/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.AI.AIEatGrass;
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
/*     */ import net.minecraft.entity.ai.EntityAITempt;
/*     */ import net.minecraft.entity.passive.EntityAnimal;
/*     */ import net.minecraft.entity.passive.EntitySheep;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.inventory.Container;
/*     */ import net.minecraft.inventory.InventoryCrafting;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.crafting.CraftingManager;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.IShearable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntitySheepTFC
/*     */   extends EntitySheep
/*     */   implements IShearable, IAnimal
/*     */ {
/*  49 */   public static final float[][] FLEECE_COLOR_TABLE = new float[][] { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
/*     */   
/*     */   private static final float GESTATION_PERIOD = 5.0F;
/*     */   
/*     */   byte ticks;
/*  54 */   long lasttick = 0L;
/*     */   
/*     */   private static final float DIMORPHISM = 0.1633F;
/*     */   
/*     */   private static final int DEGREE_OF_DIVERSION = 2;
/*     */   
/*     */   private static final int FAMILIARITY_CAP = 35;
/*     */ 
/*     */   
/*  63 */   private final InventoryCrafting colorCrafting = new InventoryCrafting(new Container()
/*     */       {
/*     */         
/*     */         public boolean func_75145_c(EntityPlayer p_75145_1_)
/*     */         {
/*  68 */           return false;
/*     */         }
/*     */       },  2, 1);
/*     */ 
/*     */ 
/*     */   
/*     */   private int sheepTimer;
/*     */ 
/*     */   
/*  77 */   protected final AIEatGrass aiEatGrass = new AIEatGrass(this);
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
/*     */   private int mateColor;
/*     */   private float sizeMod;
/*     */   private float strengthMod;
/*  92 */   private float aggressionMod = 1.0F;
/*  93 */   private float obedienceMod = 1.0F;
/*     */   
/*     */   private boolean inLove;
/*     */   private EntityPlayer shearer;
/*     */   private int familiarity;
/*     */   private long lastFamiliarityUpdate;
/*     */   private boolean familiarizedToday;
/*     */   
/*     */   public EntitySheepTFC(World par1World) {
/* 102 */     super(par1World);
/* 103 */     func_70105_a(0.9F, 1.3F);
/* 104 */     func_70661_as().func_75491_a(true);
/* 105 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/* 106 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/* 107 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/* 108 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/* 109 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/* 110 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/* 111 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/*     */ 
/*     */     
/* 114 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*     */     
/* 116 */     this.colorCrafting.func_70299_a(0, new ItemStack(Items.field_151100_aR, 1, 0));
/* 117 */     this.colorCrafting.func_70299_a(1, new ItemStack(Items.field_151100_aR, 1, 0));
/*     */     
/* 119 */     this.hunger = 168000;
/* 120 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/* 121 */     this.pregnant = false;
/* 122 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 5.0F * (float)TFC_Time.ticksInMonth);
/* 123 */     this.timeOfConception = 0L;
/* 124 */     this.mateSizeMod = 0.0F;
/* 125 */     this.sex = this.field_70146_Z.nextInt(2);
/* 126 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.1633F * this.sex)));
/* 127 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/* 128 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/* 129 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySheepTFC(World par1World, IAnimal mother, List<Float> data) {
/* 142 */     this(par1World);
/* 143 */     float fatherSize = 1.0F;
/* 144 */     float fatherStr = 1.0F;
/* 145 */     float fatherAggro = 1.0F;
/* 146 */     float fatherObed = 1.0F;
/* 147 */     for (int i = 0; i < data.size(); i++) {
/* 148 */       switch (i) { case 0:
/* 149 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/* 150 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/* 151 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/* 152 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*     */           break; }
/*     */     
/*     */     } 
/* 156 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/* 157 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/* 158 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*     */     
/* 160 */     float invSizeRatio = 0.54445475F;
/* 161 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/* 162 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/* 163 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/* 164 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*     */     
/* 166 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*     */ 
/*     */     
/* 169 */     setAge(TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 176 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 177 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 178 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/* 179 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/* 180 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/* 181 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/* 182 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/* 183 */     if ((isMountainous && temp < 25.0F && temp > -10.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F))
/* 184 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/* 185 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 191 */     super.func_110147_ax();
/* 192 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(400.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canFamiliarize() {
/* 198 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(IAnimal animal) {
/* 204 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntitySheepTFC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/* 211 */     boolean flag = false;
/* 212 */     switch (interaction) {
/*     */       
/*     */       case BREED:
/* 215 */         flag = (this.familiarity > 20);
/*     */         break;
/*     */       case SHEAR:
/* 218 */         flag = (this.familiarity > 10);
/*     */         break;
/*     */       case NAME:
/* 221 */         flag = (this.familiarity > 40);
/*     */         break;
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if (!flag && player != null && !player.field_70170_p.field_72995_K)
/*     */     {
/* 228 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*     */     }
/* 230 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public int combineColors(EntityAnimal parent, int mateColor) {
/* 235 */     int babyColor, parent1Color = 15 - ((EntitySheep)parent).func_70896_n();
/* 236 */     int parent2Color = 15 - mateColor;
/* 237 */     this.colorCrafting.func_70301_a(0).func_77964_b(parent1Color);
/* 238 */     this.colorCrafting.func_70301_a(1).func_77964_b(parent2Color);
/* 239 */     ItemStack itemstack = CraftingManager.func_77594_a().func_82787_a(this.colorCrafting, ((EntitySheep)parent).field_70170_p);
/*     */ 
/*     */     
/* 242 */     if (itemstack != null && itemstack.func_77973_b() == Items.field_151100_aR) {
/*     */       
/* 244 */       babyColor = itemstack.func_77960_j();
/*     */     }
/*     */     else {
/*     */       
/* 248 */       babyColor = this.field_70170_p.field_73012_v.nextBoolean() ? parent1Color : parent2Color;
/*     */     } 
/*     */     
/* 251 */     return babyColor;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntitySheep func_90011_a(EntityAgeable entityageable) {
/* 257 */     return (EntitySheep)createChildTFC(entityageable);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/* 263 */     ArrayList<Float> data = new ArrayList<>();
/* 264 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/* 265 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/* 266 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/* 267 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/* 268 */     EntitySheepTFC baby = new EntitySheepTFC(this.field_70170_p, this, data);
/* 269 */     int colorMeta = combineColors((EntityAnimal)this, ((EntitySheepTFC)eAgeable).mateColor);
/* 270 */     baby.func_70891_b(15 - colorMeta);
/* 271 */     return (EntityAgeable)baby;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 280 */     float ageMod = TFC_Core.getPercentGrown(this);
/*     */     
/* 282 */     if (!func_70892_o()) {
/* 283 */       func_70099_a(new ItemStack(TFCItems.sheepSkin, 1, Math.max(0, Math.min(2, (int)(ageMod * this.sizeMod)))), 0.0F);
/*     */     } else {
/* 285 */       func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * this.sizeMod)))), 0.0F);
/*     */     } 
/* 287 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(5) + 2) * ageMod));
/*     */     
/* 289 */     float foodWeight = ageMod * this.sizeMod * 640.0F;
/* 290 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.muttonRaw, foodWeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70615_aA() {
/* 296 */     func_70893_e(false);
/* 297 */     this.hunger += 24000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 303 */     super.func_70088_a();
/* 304 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/* 305 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*     */     
/* 307 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/* 308 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/* 309 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void familiarize(EntityPlayer ep) {
/* 314 */     ItemStack stack = ep.func_70694_bm();
/* 315 */     if (stack != null && !this.familiarizedToday && isFood(stack) && canFamiliarize()) {
/*     */       
/* 317 */       if (!ep.field_71075_bZ.field_75098_d) {
/*     */         
/* 319 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/*     */       else {
/*     */         
/* 323 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*     */       } 
/* 325 */       this.hunger += 24000;
/* 326 */       this.familiarizedToday = true;
/* 327 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/* 328 */       func_70642_aH();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getAggressionMod() {
/* 335 */     return this.aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getAnimalID() {
/* 340 */     return this.animalID;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAnimalTypeID() {
/* 346 */     return Helper.stringToInt("sheep");
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3 getAttackedVec() {
/* 351 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getBirthDay() {
/* 357 */     return this.field_70180_af.func_75679_c(15);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Item func_146068_u() {
/* 366 */     return TFCItems.wool;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDueDay() {
/* 372 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityLiving getEntity() {
/* 378 */     return (EntityLiving)this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFamiliarity() {
/* 384 */     return this.familiarity;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getFamiliarizedToday() {
/* 390 */     return this.familiarizedToday;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getFearSource() {
/* 396 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IAnimal.GenderEnum getGender() {
/* 402 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHunger() {
/* 408 */     return this.hunger;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getInLove() {
/* 414 */     return this.inLove;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLastFamiliarityUpdate() {
/* 419 */     return this.lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumberOfDaysToAdult() {
/* 425 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 12.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getObedienceMod() {
/* 431 */     return this.obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPregnancyRequiredTime() {
/* 436 */     return this.pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSex() {
/* 441 */     return this.sex;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityPlayer getShearer() {
/* 446 */     return this.shearer;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSheepTimer() {
/* 451 */     return this.sheepTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSizeMod() {
/* 457 */     return this.sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStrengthMod() {
/* 463 */     return this.strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTimeOfConception() {
/* 468 */     return this.timeOfConception;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleFamiliarityUpdate() {
/* 474 */     int totalDays = TFC_Time.getTotalDays();
/* 475 */     if (this.lastFamiliarityUpdate < totalDays)
/*     */     {
/* 477 */       if (this.familiarizedToday && this.familiarity < 100) {
/*     */         
/* 479 */         this.lastFamiliarityUpdate = totalDays;
/* 480 */         this.familiarizedToday = false;
/* 481 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/* 482 */         if (isAdult() && this.familiarity <= 35)
/*     */         {
/* 484 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*     */         }
/* 486 */         else if (!isAdult())
/*     */         {
/* 488 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/* 489 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/* 490 */           if (this.familiarity > 70)
/*     */           {
/* 492 */             this.obedienceMod *= 1.01F;
/*     */           }
/*     */         }
/*     */       
/* 496 */       } else if (this.familiarity < 30) {
/*     */         
/* 498 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/* 499 */         this.lastFamiliarityUpdate = totalDays;
/*     */       } 
/*     */     }
/* 502 */     if (this.familiarity > 100)
/* 503 */       this.familiarity = 100; 
/* 504 */     if (this.familiarity < 0) {
/* 505 */       this.familiarity = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70085_c(EntityPlayer player) {
/* 514 */     if (!this.field_70170_p.field_72995_K) {
/*     */       
/* 516 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*     */         
/* 518 */         familiarize(player);
/* 519 */         return true;
/*     */       } 
/*     */       
/* 522 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
/* 523 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*     */       }
/* 525 */       this.shearer = player;
/*     */ 
/*     */       
/* 528 */       if (player.func_70694_bm() != null && player.func_70694_bm().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife && !func_70892_o() && checkFamiliarity(IAnimal.InteractionEnum.SHEAR, player) && isAdult())
/*     */       
/* 530 */       { if (System.currentTimeMillis() > this.lasttick + 500L)
/* 531 */         { this.ticks = (byte)(this.ticks + 1);
/* 532 */           this.lasttick = System.currentTimeMillis(); }
/* 533 */         else { this.ticks = 0; }
/* 534 */          if (this.ticks < 10) return false; 
/* 535 */         if (!this.familiarizedToday && this.familiarity <= 35) {
/*     */           
/* 537 */           this.familiarizedToday = true;
/* 538 */           func_70671_ap().func_75651_a((Entity)player, 0.0F, 0.0F);
/* 539 */           func_70642_aH();
/*     */         } 
/* 541 */         func_70893_e(true);
/* 542 */         func_70099_a(new ItemStack(TFCItems.wool, 1), 0.0F);
/* 543 */         if (!player.field_71075_bZ.field_75098_d)
/* 544 */           player.func_70694_bm().func_77972_a(1, (EntityLivingBase)player);  }
/* 545 */       else { this.ticks = 0; }
/*     */     
/*     */     } 
/* 548 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*     */     
/* 550 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || 
/* 551 */       !canFamiliarize())) {
/*     */       
/* 553 */       if (!player.field_71075_bZ.field_75098_d)
/*     */       {
/* 555 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*     */       }
/*     */       
/* 558 */       this.hunger += 24000;
/* 559 */       func_146082_f(player);
/* 560 */       return true;
/*     */     } 
/* 562 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/* 563 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/* 564 */         itemstack.field_77994_a--;
/*     */       }
/* 566 */       return true;
/*     */     } 
/*     */ 
/*     */     
/* 570 */     return super.func_70085_c(player);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAdult() {
/* 577 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70877_b(ItemStack par1ItemStack) {
/* 583 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBreedingItemTFC(ItemStack item) {
/* 588 */     return (!this.pregnant && isFood(item));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70631_g_() {
/* 594 */     return !isAdult();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFood(ItemStack item) {
/* 600 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/* 601 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPregnant() {
/* 607 */     return this.pregnant;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
/* 613 */     return (!func_70892_o() && isAdult() && this.shearer != null && checkFamiliarity(IAnimal.InteractionEnum.SHEAR, this.shearer));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mate(IAnimal otherAnimal) {
/* 619 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*     */       
/* 621 */       otherAnimal.mate(this);
/*     */       return;
/*     */     } 
/* 624 */     this.timeOfConception = TFC_Time.getTotalTicks();
/* 625 */     this.pregnant = true;
/* 626 */     func_70875_t();
/* 627 */     otherAnimal.setInLove(false);
/* 628 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/* 629 */     this.mateObedMod = otherAnimal.getObedienceMod();
/* 630 */     this.mateSizeMod = otherAnimal.getSizeMod();
/* 631 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/* 632 */     this.mateColor = ((EntitySheepTFC)otherAnimal).func_70896_n();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 642 */     if (this.field_70170_p.field_72995_K) {
/* 643 */       this.sheepTimer = Math.max(0, this.sheepTimer - 1);
/*     */     }
/*     */     
/* 646 */     if (this.hunger > 168000)
/* 647 */       this.hunger = 168000; 
/* 648 */     if (this.hunger > 0) {
/* 649 */       this.hunger--;
/*     */     }
/* 651 */     if (func_70880_s()) {
/*     */       
/* 653 */       func_70875_t();
/* 654 */       setInLove(true);
/*     */     } 
/*     */     
/* 657 */     handleFamiliarityUpdate();
/*     */     
/* 659 */     syncData();
/* 660 */     if (isAdult()) {
/* 661 */       func_70873_a(0);
/*     */     } else {
/* 663 */       func_70873_a(-1);
/*     */     } 
/* 665 */     if (!this.field_70170_p.field_72995_K && isPregnant() && 
/* 666 */       TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*     */       
/* 668 */       int i = this.field_70146_Z.nextInt(3) + 1;
/* 669 */       for (int x = 0; x < i; x++) {
/*     */         
/* 671 */         EntitySheepTFC baby = (EntitySheepTFC)createChildTFC((EntityAgeable)this);
/* 672 */         baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/* 673 */         baby.field_70759_as = baby.field_70177_z;
/* 674 */         baby.field_70761_aq = baby.field_70177_z;
/* 675 */         this.field_70170_p.func_72838_d((Entity)baby);
/* 676 */         baby.setAge(TFC_Time.getTotalDays());
/*     */       } 
/* 678 */       this.pregnant = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 684 */     TFC_Core.preventEntityDataUpdate = true;
/* 685 */     super.func_70636_d();
/* 686 */     TFC_Core.preventEntityDataUpdate = false;
/*     */     
/* 688 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*     */       
/* 690 */       func_70691_i(1.0F);
/*     */     }
/* 692 */     else if (this.hunger < 144000 && func_70880_s()) {
/*     */       
/* 694 */       setInLove(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
/* 701 */     ArrayList<ItemStack> ret = new ArrayList<>();
/* 702 */     func_70893_e(true);
/* 703 */     ret.add(new ItemStack(TFCItems.wool, 2));
/* 704 */     if (!this.familiarizedToday && this.familiarity <= 35) {
/*     */       
/* 706 */       this.familiarizedToday = true;
/* 707 */       func_70642_aH();
/*     */     } 
/* 709 */     this.field_70170_p.func_72956_a((Entity)this, "mob.sheep.shear", 1.0F, 1.0F);
/* 710 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/* 719 */     super.func_70037_a(nbt);
/* 720 */     this.animalID = nbt.func_74763_f("Animal ID");
/* 721 */     this.sex = nbt.func_74762_e("Sex");
/* 722 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*     */     
/* 724 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/* 725 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/* 726 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*     */     
/* 728 */     this.familiarity = nbt.func_74762_e("Familiarity");
/* 729 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/* 730 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*     */     
/* 732 */     this.hunger = nbt.func_74762_e("Hunger");
/* 733 */     this.pregnant = nbt.func_74767_n("Pregnant");
/* 734 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/* 735 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/* 736 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/* 737 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/* 738 */     this.mateColor = nbt.func_74762_e("MateColor");
/* 739 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/* 740 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/* 741 */     setAge(nbt.func_74762_e("Age"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAge(int par1) {
/* 747 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAggressionMod(float aggressionMod) {
/* 753 */     this.aggressionMod = aggressionMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAnimalID(long animalID) {
/* 758 */     this.animalID = animalID;
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
/* 770 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFamiliarity(int familiarity) {
/* 776 */     this.familiarity = familiarity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 781 */     this.familiarizedToday = familiarizedToday;
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
/* 793 */     if (!TFC_Core.preventEntityDataUpdate) {
/* 794 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHunger(int h) {
/* 800 */     this.hunger = h;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInLove(boolean b) {
/* 806 */     this.inLove = b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 811 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setObedienceMod(float obedienceMod) {
/* 817 */     this.obedienceMod = obedienceMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 822 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPregnant(boolean pregnant) {
/* 827 */     this.pregnant = pregnant;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSex(int sex) {
/* 832 */     this.sex = sex;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShearer(EntityPlayer shearer) {
/* 837 */     this.shearer = shearer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSheepTimer(int sheepTimer) {
/* 842 */     this.sheepTimer = sheepTimer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSizeMod(float sizeMod) {
/* 848 */     this.sizeMod = sizeMod;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStrengthMod(float strengthMod) {
/* 854 */     this.strengthMod = strengthMod;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTimeOfConception(long timeOfConception) {
/* 859 */     this.timeOfConception = timeOfConception;
/*     */   }
/*     */ 
/*     */   
/*     */   public void syncData() {
/* 864 */     if (this.field_70180_af != null)
/*     */     {
/* 866 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 868 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*     */         
/* 870 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*     */ 
/*     */         
/* 873 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 874 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/* 875 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/* 876 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*     */       }
/*     */       else {
/*     */         
/* 880 */         this.sex = this.field_70180_af.func_75679_c(13);
/*     */         
/* 882 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 883 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/* 884 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/* 885 */         byte[] values = buf.array();
/*     */         
/* 887 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 888 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 889 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 890 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*     */         
/* 892 */         this.familiarity = values[4];
/* 893 */         this.familiarizedToday = (values[5] == 1);
/* 894 */         this.pregnant = (values[6] == 1);
/*     */ 
/*     */         
/*     */         try {
/* 898 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/* 899 */         } catch (NumberFormatException numberFormatException) {}
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trySetName(String name, EntityPlayer player) {
/* 908 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*     */       
/* 910 */       func_94058_c(name);
/* 911 */       return true;
/*     */     } 
/* 913 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 914 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70619_bc() {
/* 920 */     this.sheepTimer = this.aiEatGrass.getEatGrassTick();
/* 921 */     super.func_70619_bc();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/* 930 */     super.func_70014_b(nbt);
/* 931 */     nbt.func_74768_a("Sex", this.sex);
/* 932 */     nbt.func_74772_a("Animal ID", this.animalID);
/* 933 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*     */     
/* 935 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 936 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 937 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*     */     
/* 939 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 940 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 941 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*     */     
/* 943 */     nbt.func_74768_a("Hunger", this.hunger);
/* 944 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 945 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 946 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 947 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 948 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 949 */     nbt.func_74768_a("MateColor", this.mateColor);
/* 950 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 951 */     nbt.func_74768_a("Age", getBirthDay());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean func_70692_ba() {
/* 957 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySheepTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */