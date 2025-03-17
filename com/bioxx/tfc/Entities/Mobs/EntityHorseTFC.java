/*      */ package com.bioxx.tfc.Entities.Mobs;
/*      */ import com.bioxx.tfc.Core.TFC_Climate;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Entities.AI.AIEatGrass;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIAvoidEntityTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIPanicTFC;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.TerraFirmaCraft;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.TFCBlocks;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import fof.tfcsu.Entity.EntityBear3D;
/*      */ import fof.tfcsu.Weight.InventoryWeight;
/*      */ import fof.tfcsu.utils.ExpBonus;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import net.minecraft.command.IEntitySelector;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.IEntityLivingData;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAIRunAroundLikeCrazy;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAITempt;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.ai.attributes.IAttribute;
/*      */ import net.minecraft.entity.ai.attributes.RangedAttribute;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityHorse;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.entity.player.EntityPlayerMP;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.inventory.AnimalChest;
/*      */ import net.minecraft.inventory.IInvBasic;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTBase;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.nbt.NBTTagList;
/*      */ import net.minecraft.network.Packet;
/*      */ import net.minecraft.network.play.server.S1BPacketEntityAttach;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ public class EntityHorseTFC extends EntityHorse implements IInvBasic, IAnimal {
/*   63 */   private static final IEntitySelector HORSE_BREEDING_SELECTOR = new EntityHorseBredSelector();
/*   64 */   private static final IAttribute HORSE_JUMP_STRENGTH = (IAttribute)(new RangedAttribute("horse.jumpStrengthTFC", 0.7D, 0.0D, 2.0D)).func_111117_a("Jump StrengthTFC").func_111112_a(true);
/*      */   
/*      */   private static final float GESTATION_PERIOD = 11.17F;
/*      */   
/*      */   private static final float DIMORPHISM = 0.0813F;
/*      */   
/*      */   private static final int DEGREE_OF_DIVERSION = 2;
/*      */   
/*      */   private static final int FAMILIARITY_CAP = 35;
/*      */   
/*   74 */   protected final AIEatGrass aiEatGrass = new AIEatGrass(this);
/*      */   
/*      */   private long animalID;
/*      */   private int sex;
/*      */   private int hunger;
/*      */   private boolean pregnant;
/*      */   private int pregnancyRequiredTime;
/*      */   private long timeOfConception;
/*      */   private float sizeMod;
/*      */   private float strengthMod;
/*   84 */   private float aggressionMod = 1.0F;
/*   85 */   private float obedienceMod = 1.0F;
/*      */   
/*      */   private float mateSizeMod;
/*      */   private float mateStrengthMod;
/*      */   private float mateAggroMod;
/*      */   private float mateObedMod;
/*      */   private int mateType;
/*      */   private int mateVariant;
/*   93 */   private double mateMaxHealth = calcMaxHealth();
/*   94 */   private double mateJumpStrength = calcJumpStrength();
/*   95 */   private double mateMoveSpeed = calcMoveSpeed();
/*      */   
/*      */   private boolean inLove;
/*      */   
/*      */   private Vec3 attackedVec;
/*      */   private Entity fearSource;
/*      */   private int familiarity;
/*      */   private long lastFamiliarityUpdate;
/*      */   private boolean familiarizedToday;
/*      */   
/*      */   public EntityHorseTFC(World par1World) {
/*  106 */     super(par1World);
/*  107 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/*  108 */     this.hunger = 168000;
/*  109 */     this.pregnant = false;
/*  110 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 11.17F * (float)TFC_Time.ticksInMonth);
/*  111 */     this.timeOfConception = 0L;
/*  112 */     this.sex = this.field_70146_Z.nextInt(2);
/*  113 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(30) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0813F * this.sex)));
/*  114 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  115 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  116 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*  117 */     func_70105_a(1.4F, 1.6F);
/*  118 */     func_70661_as().func_75491_a(true);
/*  119 */     this.field_70714_bg.field_75782_a.clear();
/*  120 */     this.field_70714_bg.func_75776_a(0, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  121 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIRunAroundLikeCrazy(this, 1.2D));
/*  122 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 1.0D));
/*  123 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.7D));
/*  124 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 6.0F));
/*  125 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  126 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/*  127 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityWolfTFC.class, 8.0F, 0.5D, 0.699999988079071D));
/*  128 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIAvoidEntityTFC((EntityCreature)this, EntityBear3D.class, 16.0F, 0.25D, 0.30000001192092896D));
/*  129 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.wheatGrain, false));
/*  130 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.ryeGrain, false));
/*  131 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.riceGrain, false));
/*  132 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.barleyGrain, false));
/*  133 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.oatGrain, false));
/*  134 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 1.2000000476837158D, TFCItems.maizeEar, false));
/*  135 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)this.aiEatGrass);
/*  136 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAIPanicTFC((EntityCreature)this, 1.2D, true));
/*  137 */     updateChestSaddle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  143 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityHorseTFC(World par1World, IAnimal mother, List<Float> data, int type, int variant) {
/*  150 */     this(par1World);
/*  151 */     float fatherSize = 1.0F;
/*  152 */     float fatherStr = 1.0F;
/*  153 */     float fatherAggro = 1.0F;
/*  154 */     float fatherObed = 1.0F;
/*  155 */     for (int i = 0; i < data.size(); i++) {
/*  156 */       switch (i) { case 0:
/*  157 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/*  158 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/*  159 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/*  160 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*      */           break; }
/*      */     
/*      */     } 
/*  164 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/*  165 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/*  166 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*  167 */     float invSizeRatio = 0.52118623F;
/*  168 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/*  169 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/*  170 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/*  171 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*      */     
/*  173 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*      */ 
/*      */     
/*  176 */     setAge(TFC_Time.getTotalDays());
/*  177 */     func_110214_p(type);
/*  178 */     func_110235_q(variant);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70601_bi() {
/*  185 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  186 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  187 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*  188 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  189 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  190 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/*  191 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/*  192 */     if (!isMountainous && temp > 0.0F && temp < 30.0F && rain > 100.0F && rain <= 500.0F) {
/*      */       
/*  194 */       func_110262_ch();
/*  195 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this));
/*      */     } 
/*  197 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_110147_ax() {
/*  203 */     super.func_110147_ax();
/*  204 */     func_110140_aT().func_111150_b(HORSE_JUMP_STRENGTH);
/*  205 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((1250.0F * this.sizeMod * this.strengthMod));
/*  206 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.22499999403953552D * this.strengthMod * this.obedienceMod / (this.sizeMod * this.sizeMod));
/*  207 */     func_70606_j(func_110138_aP());
/*      */   }
/*      */ 
/*      */   
/*      */   private double calcJumpStrength() {
/*  212 */     return 0.4000000059604645D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D + this.field_70146_Z.nextDouble() * 0.2D;
/*      */   }
/*      */ 
/*      */   
/*      */   private float calcMaxHealth() {
/*  217 */     return 1000.0F + this.field_70146_Z.nextInt(101) + this.field_70146_Z.nextInt(151);
/*      */   }
/*      */ 
/*      */   
/*      */   private double calcMoveSpeed() {
/*  222 */     return (0.44999998807907104D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D + this.field_70146_Z.nextDouble() * 0.3D) * 0.25D;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canFamiliarize() {
/*  228 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70878_b(EntityAnimal animal) {
/*  237 */     if (animal == this)
/*      */     {
/*  239 */       return false;
/*      */     }
/*  241 */     if (animal.getClass() != getClass())
/*      */     {
/*  243 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  247 */     EntityHorseTFC entityhorse = (EntityHorseTFC)animal;
/*      */     
/*  249 */     if (getBreedable() && entityhorse.getBreedable()) {
/*      */       
/*  251 */       int i = func_110265_bP();
/*  252 */       int j = entityhorse.func_110265_bP();
/*  253 */       return (i == j || (i == 0 && j == 1) || (i == 1 && j == 0));
/*      */     } 
/*      */ 
/*      */     
/*  257 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(IAnimal animal) {
/*  265 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityHorseTFC);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/*  271 */     boolean flag = false;
/*  272 */     switch (interaction) { case MOUNT:
/*  273 */         flag = (this.familiarity > 15); break;
/*  274 */       case BREED: flag = (this.familiarity > 20); break;
/*  275 */       case NAME: flag = (this.familiarity > 40);
/*      */         break; }
/*      */     
/*  278 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/*  279 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*      */     }
/*  281 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110160_i(boolean par1, boolean par2) {
/*  287 */     Entity entity = func_110166_bE();
/*  288 */     if (entity instanceof EntityPlayer) {
/*      */ 
/*      */       
/*  291 */       if (entity.func_70093_af()) {
/*  292 */         super.func_110160_i(par1, true);
/*      */       }
/*      */     } else {
/*      */       
/*  296 */       super.func_110160_i(par1, true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable func_90011_a(EntityAgeable eAgeable) {
/*  303 */     return createChildTFC(eAgeable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/*  309 */     ArrayList<Float> data = new ArrayList<>();
/*  310 */     data.add(Float.valueOf(this.mateSizeMod));
/*  311 */     data.add(Float.valueOf(this.mateStrengthMod));
/*  312 */     data.add(Float.valueOf(this.mateAggroMod));
/*  313 */     data.add(Float.valueOf(this.mateObedMod));
/*      */     
/*  315 */     int momType = func_110265_bP();
/*  316 */     int dadType = this.mateType;
/*  317 */     int babyType = 0;
/*  318 */     int babyVariant = 0;
/*      */     
/*  320 */     if (momType == dadType) {
/*      */       
/*  322 */       babyType = momType;
/*      */     }
/*  324 */     else if ((momType == 0 && dadType == 1) || (momType == 1 && dadType == 0)) {
/*      */       
/*  326 */       babyType = 2;
/*      */     } 
/*      */     
/*  329 */     if (babyType == 0) {
/*      */       
/*  331 */       int l = this.field_70146_Z.nextInt(9);
/*      */       
/*  333 */       if (l < 4) { babyVariant = func_110202_bQ() & 0xFF; }
/*  334 */       else if (l < 8) { babyVariant = this.mateVariant & 0xFF; }
/*  335 */       else { babyVariant = this.field_70146_Z.nextInt(7); }
/*      */       
/*  337 */       int j1 = this.field_70146_Z.nextInt(5);
/*      */       
/*  339 */       if (j1 < 4) { babyVariant |= func_110202_bQ() & 0xFF00; }
/*  340 */       else if (j1 < 8) { babyVariant |= this.mateVariant & 0xFF00; }
/*  341 */       else { babyVariant |= this.field_70146_Z.nextInt(5) << 8 & 0xFF00; }
/*      */     
/*      */     } 
/*  344 */     EntityHorseTFC baby = new EntityHorseTFC(this.field_70170_p, this, data, babyType, babyVariant);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  350 */     double health = formula(func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b(), this.mateMaxHealth);
/*  351 */     baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a((health > 1262.0D) ? 1252.0D : health);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  357 */     double jumpstr = formula(func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b(), this.mateJumpStrength);
/*  358 */     baby.func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a((jumpstr > 1.0D) ? 1.0D : jumpstr);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  364 */     double speed = formula(func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b(), this.mateMoveSpeed);
/*  365 */     baby.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a((speed > 0.3374999970197678D) ? 0.3374999970197678D : speed);
/*      */ 
/*      */     
/*  368 */     baby.func_70606_j((float)baby.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
/*  369 */     return (EntityAgeable)baby;
/*      */   }
/*      */ 
/*      */   
/*      */   private double formula(double par1, double par2) {
/*  374 */     double kk = this.field_70170_p.field_73012_v.nextDouble() / 2.0D + 0.05D;
/*  375 */     return (par1 + par2) / 2.0D * (0.7D + kk);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110224_ci() {
/*  383 */     if (!this.field_70170_p.field_72995_K && func_110261_ca())
/*      */     {
/*  385 */       func_110207_m(false);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2) {
/*  392 */     float ageMod = TFC_Core.getPercentGrown(this);
/*      */     
/*  394 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
/*  395 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(8) + 3) * ageMod));
/*  396 */     if (func_110167_bD()) {
/*  397 */       func_70099_a(new ItemStack(TFCItems.rope), 0.0F);
/*      */     }
/*      */     
/*  400 */     float foodWeight = ageMod * this.sizeMod * 4000.0F / 2.0F;
/*      */     
/*  402 */     TFC_Core.animalDropMeat((Entity)this, TFCItems.horseMeatRaw, foodWeight);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  408 */     super.func_70088_a();
/*  409 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/*  410 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*      */     
/*  412 */     this.field_70180_af.func_75682_a(26, Integer.valueOf(0));
/*  413 */     this.field_70180_af.func_75682_a(24, Integer.valueOf(0));
/*  414 */     this.field_70180_af.func_75682_a(25, String.valueOf("0"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void familiarize(EntityPlayer ep) {
/*  420 */     ItemStack stack = ep.func_70694_bm();
/*  421 */     if ((this.field_70153_n == null || !this.field_70153_n.equals(ep)) && !this.familiarizedToday && stack != null && isFood(stack) && canFamiliarize()) {
/*      */       
/*  423 */       if (!ep.field_71075_bZ.field_75098_d) {
/*      */         
/*  425 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */       }
/*      */       else {
/*      */         
/*  429 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */       } 
/*  431 */       this.hunger += 24000;
/*  432 */       this.familiarizedToday = true;
/*  433 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  434 */       func_70642_aH();
/*  435 */       ExpBonus.FEED.give(ep);
/*      */     } 
/*  437 */     if (this.field_70153_n != null && this.field_70153_n.equals(ep) && isAdult()) {
/*      */       
/*  439 */       this.familiarizedToday = true;
/*  440 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  441 */       func_70642_aH();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAggressionMod() {
/*  448 */     return this.aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnimalTypeID() {
/*  454 */     return Helper.stringToInt("horse");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getAttackedVec() {
/*  460 */     return this.attackedVec;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBirthDay() {
/*  466 */     return this.field_70180_af.func_75679_c(15);
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean getBreedable() {
/*  471 */     return (this.field_70153_n == null && this.field_70154_o == null && func_110248_bS() && func_110228_bR() && 
/*  472 */       !func_110222_cv() && func_110143_aJ() >= func_110138_aP());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected EntityHorseTFC getClosestHorse(Entity entity, double range) {
/*  479 */     double maxDistance = Double.MAX_VALUE;
/*  480 */     EntityHorseTFC closestHorse = null;
/*  481 */     List<EntityHorseTFC> list = this.field_70170_p.func_94576_a(entity, entity.field_70121_D.func_72321_a(range, range, range), HORSE_BREEDING_SELECTOR);
/*  482 */     Iterator<EntityHorseTFC> iterator = list.iterator();
/*      */     
/*  484 */     while (iterator.hasNext()) {
/*      */       
/*  486 */       EntityHorseTFC nearbyHorse = iterator.next();
/*  487 */       double distance = nearbyHorse.func_70092_e(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
/*  488 */       if (distance < maxDistance) {
/*      */         
/*  490 */         closestHorse = nearbyHorse;
/*  491 */         maxDistance = distance;
/*      */       } 
/*      */     } 
/*      */     
/*  495 */     return closestHorse;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDueDay() {
/*  501 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLiving getEntity() {
/*  507 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFamiliarity() {
/*  512 */     return this.familiarity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFamiliarizedToday() {
/*  518 */     return this.familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getFearSource() {
/*  524 */     return this.fearSource;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAnimal.GenderEnum getGender() {
/*  530 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*      */   }
/*      */ 
/*      */   
/*      */   public AnimalChest getHorseChest() {
/*  535 */     return this.field_110296_bG;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHunger() {
/*  541 */     return this.hunger;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInLove() {
/*  547 */     return this.inLove;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastFamiliarityUpdate() {
/*  552 */     return this.lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_110167_bD() {
/*  559 */     if (super.func_110167_bD() && func_110166_bE() instanceof EntityPlayer && ((EntityPlayer)
/*  560 */       func_110166_bE()).field_71071_by.func_70448_g() == null && func_110174_bM() != -1.0F)
/*      */     {
/*  562 */       return false;
/*      */     }
/*  564 */     return super.func_110167_bD();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_110218_cm() {
/*  570 */     return (int)(500.0F * this.aggressionMod);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double func_70042_X() {
/*  579 */     float offset = this.sizeMod * this.field_70131_O * 0.75F;
/*  580 */     int type = func_110265_bP();
/*      */     
/*  582 */     if (type == 1) {
/*  583 */       offset *= 0.87F;
/*  584 */     } else if (type == 2) {
/*  585 */       offset *= 0.92F;
/*      */     } 
/*  587 */     return offset;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysToAdult() {
/*  593 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 30.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   private int getNumChestSlots() {
/*  598 */     int i = func_110265_bP();
/*  599 */     return (func_110261_ca() && (i == 1 || i == 2)) ? 17 : 2;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getObedienceMod() {
/*  605 */     return this.obedienceMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPregnancyRequiredTime() {
/*  610 */     return this.pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSex() {
/*  615 */     return this.sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSizeMod() {
/*  621 */     return this.sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrengthMod() {
/*  627 */     return this.strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeOfConception() {
/*  632 */     return this.timeOfConception;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFamiliarityUpdate() {
/*  638 */     int totalDays = TFC_Time.getTotalDays();
/*  639 */     if (this.lastFamiliarityUpdate < totalDays) {
/*  640 */       if (this.familiarizedToday && this.familiarity < 100) {
/*  641 */         this.lastFamiliarityUpdate = totalDays;
/*  642 */         this.familiarizedToday = false;
/*  643 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/*  644 */         if (isAdult() && this.familiarity <= 35) {
/*      */           
/*  646 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*      */         }
/*  648 */         else if (!isAdult()) {
/*  649 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/*  650 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/*  651 */           if (this.familiarity > 70) {
/*  652 */             this.obedienceMod *= 1.01F;
/*      */           }
/*      */         }
/*      */       
/*  656 */       } else if (this.familiarity < 30) {
/*  657 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/*  658 */         this.lastFamiliarityUpdate = totalDays;
/*      */       } 
/*      */     }
/*  661 */     if (this.familiarity > 100) this.familiarity = 100; 
/*  662 */     if (this.familiarity < 0) this.familiarity = 0;
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public int func_110198_t(int temper) {
/*  668 */     temper *= 5;
/*  669 */     int j = MathHelper.func_76125_a(func_110252_cg() + (int)(temper * this.obedienceMod * 1.0F / this.aggressionMod), 0, func_110218_cm());
/*  670 */     func_110238_s(j);
/*  671 */     return j;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player) {
/*  680 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*  681 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  683 */       if (player.func_70093_af() && !this.familiarizedToday && itemstack != null && canFamiliarize()) {
/*      */         
/*  685 */         familiarize(player);
/*  686 */         return true;
/*      */       } 
/*  688 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
/*  689 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
/*  690 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*      */       }
/*      */     } 
/*      */     
/*  694 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || 
/*  695 */       !canFamiliarize())) {
/*      */       
/*  697 */       if (!player.field_71075_bZ.field_75098_d)
/*  698 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this)); 
/*  699 */       this.hunger += 24000;
/*  700 */       setInLove(true);
/*  701 */       return true;
/*      */     } 
/*  703 */     if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/*  704 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player)) {
/*  705 */         itemstack.field_77994_a--;
/*      */       }
/*  707 */       return true;
/*      */     } 
/*  709 */     if (itemstack != null && itemstack.func_77973_b() == Items.field_151063_bx)
/*      */     {
/*  711 */       return super.func_70085_c(player);
/*      */     }
/*  713 */     if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && !func_110167_bD()) {
/*      */       
/*  715 */       func_110199_f(player);
/*  716 */       return true;
/*      */     } 
/*  718 */     if (func_110248_bS() && func_110228_bR() && player.func_70093_af() && func_110167_bD()) {
/*      */       
/*  720 */       func_110160_i(true, true);
/*  721 */       return true;
/*      */     } 
/*  723 */     if (func_110228_bR() && this.field_70153_n != null)
/*      */     {
/*  725 */       return super.func_70085_c(player);
/*      */     }
/*      */ 
/*      */     
/*  729 */     if (itemstack != null) {
/*      */       
/*  731 */       if (!func_110248_bS()) {
/*      */         
/*  733 */         if (itemstack.func_111282_a(player, (EntityLivingBase)this))
/*      */         {
/*  735 */           return true;
/*      */         }
/*      */         
/*  738 */         func_110231_cz();
/*      */       } 
/*      */       
/*  741 */       boolean flag = false;
/*      */       
/*  743 */       if (func_110229_cs() && !func_110261_ca() && itemstack.func_77973_b() == Item.func_150898_a(TFCBlocks.chest)) {
/*      */         
/*  745 */         func_110207_m(true);
/*  746 */         func_85030_a("mob.chickenplop", 1.0F, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2F + 1.0F);
/*  747 */         flag = true;
/*  748 */         updateChestSaddle();
/*      */       } 
/*      */       
/*  751 */       if (flag) {
/*      */         
/*  753 */         if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a == 0)
/*      */         {
/*  755 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */         }
/*      */         
/*  758 */         return true;
/*      */       } 
/*      */     } 
/*  761 */     if (func_110228_bR() && this.field_70153_n == null) {
/*      */       
/*  763 */       if (itemstack != null && itemstack.func_111282_a(player, (EntityLivingBase)this))
/*      */       {
/*  765 */         return true;
/*      */       }
/*      */ 
/*      */       
/*  769 */       if (func_110166_bE() instanceof EntityPlayer && func_110166_bE() == player)
/*      */       {
/*  771 */         mountHorse(player);
/*      */       }
/*  773 */       return true;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  778 */     return super.func_70085_c(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAdult() {
/*  784 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70877_b(ItemStack par1ItemStack) {
/*  790 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBreedingItemTFC(ItemStack item) {
/*  795 */     return (!this.pregnant && isFood(item));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70631_g_() {
/*  801 */     return !isAdult();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFood(ItemStack item) {
/*  806 */     return (item != null && (item.func_77973_b() == TFCItems.wheatGrain || item.func_77973_b() == TFCItems.oatGrain || item.func_77973_b() == TFCItems.riceGrain || item
/*  807 */       .func_77973_b() == TFCItems.barleyGrain || item.func_77973_b() == TFCItems.ryeGrain || item.func_77973_b() == TFCItems.maizeEar));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPregnant() {
/*  813 */     return this.pregnant;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mate(IAnimal otherAnimal) {
/*  819 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*      */       
/*  821 */       otherAnimal.mate(this);
/*  822 */       setInLove(false);
/*  823 */       func_70875_t();
/*      */       return;
/*      */     } 
/*  826 */     this.timeOfConception = TFC_Time.getTotalTicks();
/*  827 */     this.pregnant = true;
/*  828 */     func_70875_t();
/*  829 */     setInLove(false);
/*  830 */     otherAnimal.setInLove(false);
/*  831 */     this.mateSizeMod = otherAnimal.getSizeMod();
/*  832 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*  833 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/*  834 */     this.mateObedMod = otherAnimal.getObedienceMod();
/*  835 */     this.mateType = ((EntityHorse)otherAnimal).func_110265_bP();
/*  836 */     this.mateVariant = ((EntityHorse)otherAnimal).func_110202_bQ();
/*  837 */     this.mateMaxHealth = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
/*  838 */     this.mateJumpStrength = ((EntityLivingBase)otherAnimal).func_110148_a(HORSE_JUMP_STRENGTH).func_111125_b();
/*  839 */     this.mateMoveSpeed = ((EntityLivingBase)otherAnimal).func_110148_a(SharedMonsterAttributes.field_111263_d).func_111125_b();
/*      */   }
/*      */ 
/*      */   
/*      */   private void mountHorse(EntityPlayer player) {
/*  844 */     player.field_70177_z = this.field_70177_z;
/*  845 */     player.field_70125_A = this.field_70125_A;
/*  846 */     func_110227_p(false);
/*  847 */     func_110219_q(false);
/*      */     
/*  849 */     if (!this.field_70170_p.field_72995_K && checkFamiliarity(IAnimal.InteractionEnum.MOUNT, player)) {
/*  850 */       player.func_70078_a((Entity)this);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70636_d() {
/*  856 */     if (func_110215_cj() == 0.7D) func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a(super.func_110215_cj());
/*      */     
/*  858 */     if (this.hunger > 168000)
/*  859 */       this.hunger = 168000; 
/*  860 */     if (this.hunger > 0) {
/*  861 */       this.hunger--;
/*      */     }
/*  863 */     if (this.field_70153_n != null && this.field_70153_n instanceof EntityPlayer && this.field_70146_Z.nextInt(600) == 0 && !this.familiarizedToday && canFamiliarize())
/*      */     {
/*  865 */       familiarize((EntityPlayer)this.field_70153_n);
/*      */     }
/*      */     
/*  868 */     syncData();
/*  869 */     if (isAdult()) {
/*  870 */       func_70873_a(0);
/*      */     } else {
/*  872 */       func_70873_a(-1);
/*      */     } 
/*  874 */     handleFamiliarityUpdate();
/*  875 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*      */     {
/*  877 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*      */         
/*  879 */         EntityHorseTFC baby = (EntityHorseTFC)createChildTFC((EntityAgeable)this);
/*  880 */         baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/*  881 */         baby.field_70759_as = baby.field_70177_z;
/*  882 */         baby.field_70761_aq = baby.field_70177_z;
/*  883 */         this.field_70170_p.func_72838_d((Entity)baby);
/*  884 */         baby.setAge(TFC_Time.getTotalDays());
/*  885 */         this.pregnant = false;
/*      */       } 
/*      */     }
/*      */     
/*  889 */     if (this.field_70170_p.func_82737_E() % 20L == 0L && 
/*  890 */       isOverweight()) {
/*  891 */       func_110244_cA();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  899 */     TFC_Core.preventEntityDataUpdate = true;
/*  900 */     super.func_70636_d();
/*  901 */     TFC_Core.preventEntityDataUpdate = false;
/*      */     
/*  903 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*      */       
/*  905 */       func_70691_i(1.0F);
/*      */     }
/*  907 */     else if (this.hunger < 144000 && func_70880_s()) {
/*  908 */       setInLove(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IEntityLivingData func_110161_a(IEntityLivingData livingData) {
/*  915 */     IEntityLivingData data = super.func_110161_a(livingData);
/*  916 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1250.0D);
/*  917 */     func_110148_a(HORSE_JUMP_STRENGTH).func_111128_a(super.func_110215_cj());
/*  918 */     func_70691_i(1250.0F);
/*  919 */     return data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_110199_f(EntityPlayer player) {
/*  926 */     Entity temp = player.field_70154_o;
/*      */     
/*  928 */     if (temp == null) {
/*      */       
/*  930 */       player.field_70154_o = (Entity)this;
/*  931 */       if (player instanceof EntityPlayerMP) {
/*      */         
/*  933 */         EntityPlayerMP playerMP = (EntityPlayerMP)player;
/*  934 */         playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, (Entity)this));
/*  935 */         openHorseContainer(player);
/*  936 */         playerMP.field_71135_a.func_147359_a((Packet)new S1BPacketEntityAttach(0, (Entity)playerMP, temp));
/*      */       }
/*      */       else {
/*      */         
/*  940 */         openHorseContainer(player);
/*      */       } 
/*  942 */       player.field_70154_o = null;
/*      */     } 
/*      */     
/*  945 */     if (temp != null && temp == this)
/*      */     {
/*  947 */       openHorseContainer(player);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void openHorseContainer(EntityPlayer player) {
/*  953 */     if (!this.field_70170_p.field_72995_K && (this.field_70153_n == null || this.field_70153_n == player) && func_110248_bS())
/*      */     {
/*  955 */       player.openGui(TerraFirmaCraft.instance, 42, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70037_a(NBTTagCompound nbttc) {
/*  965 */     super.func_70037_a(nbttc);
/*  966 */     NBTTagCompound nbt = nbttc;
/*  967 */     this.animalID = nbt.func_74763_f("Animal ID");
/*  968 */     this.sex = nbt.func_74762_e("Sex");
/*  969 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*      */     
/*  971 */     this.familiarity = nbt.func_74762_e("Familiarity");
/*  972 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/*  973 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*      */     
/*  975 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/*  976 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/*  977 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*      */     
/*  979 */     this.hunger = nbt.func_74762_e("Hunger");
/*  980 */     this.pregnant = nbt.func_74767_n("Pregnant");
/*  981 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/*  982 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/*  983 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/*  984 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/*  985 */     this.mateType = nbt.func_74762_e("MateType");
/*  986 */     this.mateVariant = nbt.func_74762_e("MateVariant");
/*  987 */     this.mateMaxHealth = nbt.func_74769_h("MateHealth");
/*  988 */     this.mateJumpStrength = nbt.func_74769_h("MateJump");
/*  989 */     this.mateMoveSpeed = nbt.func_74769_h("MateSpeed");
/*  990 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/*  991 */     setAge(nbt.func_74762_e("Age"));
/*      */     
/*  993 */     if (func_110261_ca()) {
/*      */       
/*  995 */       NBTTagList nbttaglist = nbttc.func_150295_c("Items", 10);
/*  996 */       updateChestSaddle();
/*      */       
/*  998 */       for (int i = 0; i < nbttaglist.func_74745_c(); i++) {
/*      */         
/* 1000 */         NBTTagCompound nbt1 = nbttaglist.func_150305_b(i);
/* 1001 */         int j = nbt1.func_74771_c("Slot") & 0xFF;
/*      */         
/* 1003 */         if (j >= 2 && j < this.field_110296_bG.func_70302_i_())
/*      */         {
/* 1005 */           this.field_110296_bG.func_70299_a(j, ItemStack.func_77949_a(nbt1));
/*      */         }
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1012 */     if (nbttc.func_150297_b("ArmorItem", 10)) {
/*      */       
/* 1014 */       ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("ArmorItem"));
/*      */       
/* 1016 */       if (itemstack != null && EntityHorse.func_146085_a(itemstack.func_77973_b()))
/*      */       {
/* 1018 */         this.field_110296_bG.func_70299_a(1, itemstack);
/*      */       }
/*      */     } 
/*      */     
/* 1022 */     if (nbttc.func_150297_b("SaddleItem", 10)) {
/*      */       
/* 1024 */       ItemStack itemstack = ItemStack.func_77949_a(nbttc.func_74775_l("SaddleItem"));
/* 1025 */       if (itemstack != null && itemstack.func_77973_b() == Items.field_151141_av)
/*      */       {
/* 1027 */         this.field_110296_bG.func_70299_a(0, itemstack);
/*      */       }
/*      */     }
/* 1030 */     else if (nbttc.func_74767_n("Saddle")) {
/*      */       
/* 1032 */       this.field_110296_bG.func_70299_a(0, new ItemStack(Items.field_151141_av));
/*      */     } 
/*      */     
/* 1035 */     updateSaddle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAge(int par1) {
/* 1042 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAggressionMod(float aggressionMod) {
/* 1049 */     this.aggressionMod = aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackedVec(Vec3 attackedVec) {
/* 1055 */     this.attackedVec = attackedVec;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBirthDay(int day) {
/* 1061 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFamiliarity(int familiarity) {
/* 1067 */     this.familiarity = familiarity;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFamiliarizedToday(boolean familiarizedToday) {
/* 1072 */     this.familiarizedToday = familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFearSource(Entity fearSource) {
/* 1078 */     this.fearSource = fearSource;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70873_a(int par1) {
/* 1084 */     if (!TFC_Core.preventEntityDataUpdate)
/*      */     {
/* 1086 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHunger(int h) {
/* 1093 */     this.hunger = h;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInLove(boolean b) {
/* 1099 */     this.inLove = b;
/* 1100 */     if (b) {
/*      */       
/* 1102 */       this.field_70789_a = null;
/* 1103 */       this.field_70170_p.func_72960_a((Entity)this, (byte)18);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/* 1109 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObedienceMod(float obedienceMod) {
/* 1115 */     this.obedienceMod = obedienceMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/* 1120 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnant(boolean pregnant) {
/* 1125 */     this.pregnant = pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSex(int sex) {
/* 1130 */     this.sex = sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSizeMod(float sizeMod) {
/* 1136 */     this.sizeMod = sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrengthMod(float strengthMod) {
/* 1142 */     this.strengthMod = strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeOfConception(long timeOfConception) {
/* 1147 */     this.timeOfConception = timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void syncData() {
/* 1152 */     if (this.field_70180_af != null)
/*      */     {
/* 1154 */       if (!this.field_70170_p.field_72995_K) {
/*      */         
/* 1156 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1162 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1168 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 1169 */         this.field_70180_af.func_75692_b(26, Integer.valueOf(buf.getInt()));
/* 1170 */         this.field_70180_af.func_75692_b(24, Integer.valueOf(buf.getInt()));
/* 1171 */         this.field_70180_af.func_75692_b(25, String.valueOf(this.timeOfConception));
/*      */       }
/*      */       else {
/*      */         
/* 1175 */         this.sex = this.field_70180_af.func_75679_c(13);
/*      */         
/* 1177 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 1178 */         buf.putInt(this.field_70180_af.func_75679_c(26));
/* 1179 */         buf.putInt(this.field_70180_af.func_75679_c(24));
/* 1180 */         byte[] values = buf.array();
/*      */         
/* 1182 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 1183 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 1184 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 1185 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*      */         
/* 1187 */         this.familiarity = values[4];
/* 1188 */         this.familiarizedToday = (values[5] == 1);
/* 1189 */         this.pregnant = (values[6] == 1);
/*      */ 
/*      */         
/*      */         try {
/* 1193 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(25));
/* 1194 */         } catch (NumberFormatException numberFormatException) {}
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean trySetName(String name, EntityPlayer player) {
/* 1201 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*      */       
/* 1203 */       func_94058_c(name);
/* 1204 */       return true;
/*      */     } 
/* 1206 */     func_85030_a(func_70621_aR(), 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 1207 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateChestSaddle() {
/* 1212 */     AnimalChest animalchest = this.field_110296_bG;
/* 1213 */     this.field_110296_bG = new AnimalChest("HorseChest", getNumChestSlots());
/*      */     
/* 1215 */     if (animalchest != null) {
/*      */       
/* 1217 */       animalchest.func_110132_b(this);
/* 1218 */       int i = Math.min(animalchest.func_70302_i_(), this.field_110296_bG.func_70302_i_());
/* 1219 */       for (int j = 0; j < i; j++) {
/*      */         
/* 1221 */         ItemStack itemstack = animalchest.func_70301_a(j);
/* 1222 */         if (itemstack != null)
/* 1223 */           this.field_110296_bG.func_70299_a(j, itemstack.func_77946_l()); 
/*      */       } 
/* 1225 */       animalchest = null;
/*      */     } 
/*      */     
/* 1228 */     this.field_110296_bG.func_110134_a(this);
/* 1229 */     updateSaddle();
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateSaddle() {
/* 1234 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/* 1236 */       func_110251_o((this.field_110296_bG.func_70301_a(0) != null));
/* 1237 */       if (func_110265_bP() == 0) {
/* 1238 */         func_146086_d(this.field_110296_bG.func_70301_a(1));
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbttc) {
/* 1248 */     super.func_70014_b(nbttc);
/* 1249 */     nbttc.func_74768_a("Sex", this.sex);
/* 1250 */     nbttc.func_74772_a("Animal ID", this.animalID);
/* 1251 */     nbttc.func_74776_a("Size Modifier", this.sizeMod);
/*      */     
/* 1253 */     nbttc.func_74768_a("Familiarity", this.familiarity);
/* 1254 */     nbttc.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 1255 */     nbttc.func_74757_a("Familiarized Today", this.familiarizedToday);
/*      */     
/* 1257 */     NBTTagCompound nbt = nbttc;
/* 1258 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 1259 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 1260 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*      */     
/* 1262 */     nbttc.func_74768_a("Hunger", this.hunger);
/* 1263 */     nbttc.func_74757_a("Pregnant", this.pregnant);
/* 1264 */     nbttc.func_74776_a("MateSize", this.mateSizeMod);
/* 1265 */     nbttc.func_74776_a("MateStrength", this.mateStrengthMod);
/* 1266 */     nbttc.func_74776_a("MateAggro", this.mateAggroMod);
/* 1267 */     nbttc.func_74776_a("MateObed", this.mateObedMod);
/* 1268 */     nbttc.func_74768_a("MateType", this.mateType);
/* 1269 */     nbttc.func_74768_a("MateVariant", this.mateVariant);
/* 1270 */     nbttc.func_74780_a("MateHealth", this.mateMaxHealth);
/* 1271 */     nbttc.func_74780_a("MateJump", this.mateJumpStrength);
/* 1272 */     nbttc.func_74780_a("MateSpeed", this.mateMoveSpeed);
/* 1273 */     nbttc.func_74772_a("ConceptionTime", this.timeOfConception);
/* 1274 */     nbttc.func_74768_a("Age", getBirthDay());
/*      */     
/* 1276 */     if (func_110261_ca()) {
/*      */       
/* 1278 */       NBTTagList nbttaglist = new NBTTagList();
/*      */       
/* 1280 */       for (int i = 2; i < this.field_110296_bG.func_70302_i_(); i++) {
/*      */         
/* 1282 */         ItemStack itemstack = this.field_110296_bG.func_70301_a(i);
/*      */         
/* 1284 */         if (itemstack != null) {
/*      */           
/* 1286 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 1287 */           nbttagcompound1.func_74774_a("Slot", (byte)i);
/* 1288 */           itemstack.func_77955_b(nbttagcompound1);
/* 1289 */           nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
/*      */         } 
/*      */       } 
/*      */       
/* 1293 */       nbttc.func_74782_a("Items", (NBTBase)nbttaglist);
/*      */     } 
/*      */     
/* 1296 */     if (this.field_110296_bG.func_70301_a(1) != null)
/*      */     {
/* 1298 */       nbttc.func_74782_a("ArmorItem", (NBTBase)this.field_110296_bG.func_70301_a(1).func_77955_b(new NBTTagCompound()));
/*      */     }
/*      */     
/* 1301 */     if (this.field_110296_bG.func_70301_a(0) != null)
/*      */     {
/* 1303 */       nbttc.func_74782_a("SaddleItem", (NBTBase)this.field_110296_bG.func_70301_a(0).func_77955_b(new NBTTagCompound()));
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_70692_ba() {
/* 1310 */     return !func_110248_bS();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isOverweight() {
/* 1315 */     return (getWeight() > 80000);
/*      */   }
/*      */   
/*      */   public int getWeight() {
/* 1319 */     ItemStack[] inv = new ItemStack[getHorseChest().func_70302_i_()];
/* 1320 */     for (int i = 0; i < getHorseChest().func_70302_i_(); ) { inv[i] = getHorseChest().func_70301_a(i); i++; }
/* 1321 */      InventoryWeight iv = new InventoryWeight(inv);
/* 1322 */     return iv.getWeight();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public double func_110215_cj() {
/* 1328 */     return func_110148_a(HORSE_JUMP_STRENGTH).func_111126_e();
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */