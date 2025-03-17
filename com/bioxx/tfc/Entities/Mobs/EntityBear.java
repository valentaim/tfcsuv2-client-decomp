/*      */ package com.bioxx.tfc.Entities.Mobs;
/*      */ 
/*      */ import com.bioxx.tfc.Core.TFC_Climate;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAITargetNonTamedTFC;
/*      */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*      */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*      */ import com.bioxx.tfc.api.Entities.IAnimal;
/*      */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*      */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*      */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*      */ import com.bioxx.tfc.api.TFCItems;
/*      */ import com.bioxx.tfc.api.TFCOptions;
/*      */ import com.bioxx.tfc.api.Util.Helper;
/*      */ import fof.tfcsu.Entity.EntityBoar;
/*      */ import fof.tfcsu.Entity.Sheep.EntityAnimaniaSheep;
/*      */ import fof.tfcsu.Register.tfcsuItems;
/*      */ import fof.tfcsu.utils.ExpBonus;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityCreature;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAIFollowParent;
/*      */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*      */ import net.minecraft.entity.ai.EntityAILeapAtTarget;
/*      */ import net.minecraft.entity.ai.EntityAILookIdle;
/*      */ import net.minecraft.entity.ai.EntityAISwimming;
/*      */ import net.minecraft.entity.ai.EntityAITempt;
/*      */ import net.minecraft.entity.ai.EntityAIWander;
/*      */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*      */ import net.minecraft.entity.passive.EntityAnimal;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.util.ChatComponentTranslation;
/*      */ import net.minecraft.util.DamageSource;
/*      */ import net.minecraft.util.IChatComponent;
/*      */ import net.minecraft.util.MathHelper;
/*      */ import net.minecraft.util.Vec3;
/*      */ import net.minecraft.world.EnumDifficulty;
/*      */ import net.minecraft.world.World;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityBear
/*      */   extends EntityTameable
/*      */   implements ICausesDamage, IAnimal, IInnateArmor
/*      */ {
/*      */   private static final float GESTATION_PERIOD = 7.0F;
/*      */   private static final float DIMORPHISM = 0.2182F;
/*      */   private static final int DEGREE_OF_DIVERSION = 4;
/*      */   private static final int FAMILIARITY_CAP = 80;
/*   68 */   private final Random rand = new Random();
/*      */   
/*      */   private float moveSpeed;
/*      */   
/*      */   private boolean isWet;
/*      */   private int sex;
/*      */   private int hunger;
/*      */   private boolean pregnant;
/*      */   private int pregnancyRequiredTime;
/*      */   private long timeOfConception;
/*      */   private float mateSizeMod;
/*      */   private float mateStrengthMod;
/*      */   private float mateAggroMod;
/*      */   private float mateObedMod;
/*      */   private float sizeMod;
/*      */   private float strengthMod;
/*   84 */   private float aggressionMod = 1.0F;
/*   85 */   private float obedienceMod = 1.0F;
/*      */   
/*      */   private boolean inLove;
/*      */   
/*      */   protected EntityAIAttackOnCollide attackAI;
/*      */   
/*      */   protected EntityAILeapAtTarget leapAI;
/*      */   
/*      */   protected EntityAITargetNonTamedTFC targetSheep;
/*      */   protected EntityAITargetNonTamedTFC targetDeer;
/*      */   protected EntityAITargetNonTamedTFC targetPig;
/*      */   protected EntityAITargetNonTamedTFC targetHorse;
/*      */   protected EntityAITargetNonTamedTFC targetPlayer;
/*      */   protected EntityAIHurtByTarget hurtAI;
/*      */   protected boolean isPeacefulAI;
/*      */   private boolean wasRoped;
/*      */   private int familiarity;
/*      */   private long lastFamiliarityUpdate;
/*      */   private boolean familiarizedToday;
/*      */   
/*      */   public EntityBear(World par1World) {
/*  106 */     super(par1World);
/*  107 */     func_70105_a(1.2F, 1.2F);
/*  108 */     this.moveSpeed = 0.4F;
/*  109 */     func_70661_as().func_75491_a(true);
/*  110 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  111 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 0.5F));
/*  112 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAITempt((EntityCreature)this, 0.24000000953674316D, TFCItems.fishRaw, false));
/*  113 */     this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIFollowParent((EntityAnimal)this, 0.2800000011920929D));
/*      */     
/*  115 */     this.sizeMod = (float)Math.sqrt((((this.rand.nextInt(this.rand.nextInt(50) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.2182F * this.sex)));
/*      */     
/*  117 */     this.strengthMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  118 */     this.aggressionMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  119 */     this.obedienceMod = (float)Math.sqrt(((this.rand.nextInt(this.rand.nextInt(40) + 1) * (this.rand.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*      */ 
/*      */ 
/*      */     
/*  123 */     this.sex = this.rand.nextInt(2);
/*      */     
/*  125 */     this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, this.moveSpeed));
/*  126 */     this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  127 */     this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  128 */     this.attackAI = new EntityAIAttackOnCollide((EntityCreature)this, (this.moveSpeed * 1.5F), true);
/*  129 */     this.leapAI = new EntityAILeapAtTarget((EntityLiving)this, 0.4F);
/*      */ 
/*      */ 
/*      */     
/*  133 */     this.targetSheep = new EntityAITargetNonTamedTFC(this, EntityAnimaniaSheep.class, 200, false);
/*  134 */     this.targetDeer = new EntityAITargetNonTamedTFC(this, EntityDeer.class, 200, false);
/*      */     
/*  136 */     this.targetPig = new EntityAITargetNonTamedTFC(this, EntityBoar.class, 200, false);
/*  137 */     this.targetHorse = new EntityAITargetNonTamedTFC(this, EntityHorseTFC.class, 200, false);
/*  138 */     this.targetPlayer = new EntityAITargetNonTamedTFC(this, EntityPlayer.class, 20, false);
/*  139 */     this.hurtAI = new EntityAIHurtByTarget((EntityCreature)this, true);
/*      */     
/*  141 */     if (par1World.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */       
/*  143 */       this.isPeacefulAI = false;
/*  144 */       this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
/*  145 */       this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
/*  146 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
/*  147 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
/*  148 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
/*  149 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
/*  150 */       this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
/*  151 */       this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
/*      */     } else {
/*      */       
/*  154 */       this.isPeacefulAI = true;
/*      */     } 
/*      */     
/*  157 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 7.0F * (float)TFC_Time.ticksInMonth);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  163 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityBear(World par1World, IAnimal mother, List<Float> data) {
/*  169 */     this(par1World);
/*  170 */     float fatherSize = 1.0F;
/*  171 */     float fatherStr = 1.0F;
/*  172 */     float fatherAggro = 1.0F;
/*  173 */     float fatherObed = 1.0F;
/*  174 */     for (int i = 0; i < data.size(); i++) {
/*  175 */       switch (i) { case 0:
/*  176 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/*  177 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/*  178 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/*  179 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*      */           break; }
/*      */     
/*      */     } 
/*  183 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/*  184 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/*  185 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*  186 */     float invSizeRatio = 0.5612302F;
/*  187 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/*  188 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/*  189 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/*  190 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*      */     
/*  192 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*      */ 
/*      */     
/*  195 */     setAge(TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70601_bi() {
/*  202 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  203 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  204 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*      */     
/*  206 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  207 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  208 */     float evt = 0.0F;
/*  209 */     if (TFC_Climate.getCacheManager(this.field_70170_p) != null && TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k) != null)
/*  210 */       evt = (TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k)).floatdata1; 
/*  211 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/*  212 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/*  213 */     if ((isMountainous && temp < 25.0F && temp > -10.0F && rain > 250.0F && evt < 0.75D) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F))
/*  214 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/*  215 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_110147_ax() {
/*  222 */     super.func_110147_ax();
/*  223 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(3000.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70652_k(Entity par1Entity) {
/*  229 */     int dam = (int)(275.0F * getStrengthMod() * getAggressionMod() * (getSizeMod() / 2.0F + 0.5F));
/*  230 */     return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), dam);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_70692_ba() {
/*  239 */     return !this.wasRoped;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canFamiliarize() {
/*  246 */     return (!isAdult() || (isAdult() && this.familiarity <= 80));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(IAnimal animal) {
/*  263 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityBear);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean func_70041_e_() {
/*  274 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/*  280 */     boolean flag = false;
/*  281 */     switch (interaction) { case MOUNT:
/*  282 */         flag = (this.familiarity > 15); break;
/*  283 */       case BREED: flag = (this.familiarity > 20); break;
/*  284 */       case NAME: flag = (this.familiarity > 70); break;
/*  285 */       case TOLERATEPLAYER: flag = (this.familiarity > 75);
/*      */         break; }
/*      */     
/*  288 */     if (!flag && player != null && !player.field_70170_p.field_72995_K) {
/*  289 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*      */     }
/*  291 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable func_90011_a(EntityAgeable entityageable) {
/*  297 */     return createChildTFC(entityageable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/*  303 */     ArrayList<Float> data = new ArrayList<>();
/*  304 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/*  305 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/*  306 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/*  307 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/*  308 */     return (EntityAgeable)new EntityBear(this.field_70170_p, this, data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2) {
/*  314 */     float ageMod = TFC_Core.getPercentGrown(this);
/*      */     
/*  316 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)(ageMod * 3.0F - 1.0F)))), 0.0F);
/*  317 */     func_145779_a(Items.field_151103_aS, (int)((this.rand.nextInt(6) + 2) * ageMod));
/*  318 */     float foodWeight = ageMod * getSizeMod() * 1500.0F;
/*  319 */     TFC_Core.animalDropMeat((Entity)this, tfcsuItems.bearRaw, foodWeight);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  326 */     super.func_70088_a();
/*  327 */     this.field_70180_af.func_75682_a(18, Float.valueOf(func_110143_aJ()));
/*  328 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/*  329 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*  330 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/*  331 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/*  332 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*      */   }
/*      */ 
/*      */   
/*      */   public void familiarize(EntityPlayer ep) {
/*  337 */     ItemStack stack = ep.func_70694_bm();
/*  338 */     if (stack != null && isFood(stack) && !this.familiarizedToday && canFamiliarize()) {
/*      */       
/*  340 */       if (!ep.field_71075_bZ.field_75098_d) {
/*      */         
/*  342 */         ep.field_71071_by.func_70299_a(ep.field_71071_by.field_70461_c, ((ItemFoodTFC)stack.func_77973_b()).onConsumedByEntity(ep.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */       }
/*      */       else {
/*      */         
/*  346 */         this.field_70170_p.func_72956_a((Entity)this, "random.burp", 0.5F, this.field_70170_p.field_73012_v.nextFloat() * 0.1F + 0.9F);
/*      */       } 
/*  348 */       this.hunger += 24000;
/*  349 */       this.familiarizedToday = true;
/*  350 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  351 */       func_70642_aH();
/*  352 */       ExpBonus.FEED.give(ep);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getAggressionMod() {
/*  359 */     return this.aggressionMod;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnimalTypeID() {
/*  366 */     return Helper.stringToInt("bear");
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Vec3 getAttackedVec() {
/*  372 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getBirthDay() {
/*  378 */     return this.field_70180_af.func_75679_c(15);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCrushArmor() {
/*  384 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumDamageType getDamageType() {
/*  390 */     return EnumDamageType.SLASHING;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70673_aS() {
/*  399 */     if (!func_70631_g_()) {
/*  400 */       return "terrafirmacraft:mob.bear.death";
/*      */     }
/*  402 */     return "terrafirmacraft:mob.bear.cub.cry";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Item func_146068_u() {
/*  412 */     return Item.func_150899_d(0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getDueDay() {
/*  418 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityLiving getEntity() {
/*  424 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float func_70047_e() {
/*  430 */     return this.field_70131_O * 0.8F;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getFamiliarity() {
/*  435 */     return this.familiarity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFamiliarizedToday() {
/*  441 */     return this.familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getFearSource() {
/*  447 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAnimal.GenderEnum getGender() {
/*  453 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHunger() {
/*  459 */     return this.hunger;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70621_aR() {
/*  468 */     if (!func_70631_g_()) {
/*  469 */       return "terrafirmacraft:mob.bear.hurt";
/*      */     }
/*  471 */     return "terrafirmacraft:mob.bear.cub.cry";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInLove() {
/*  477 */     return this.inLove;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastFamiliarityUpdate() {
/*  482 */     return this.lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String func_70639_aQ() {
/*  491 */     if (isAdult() && this.field_70170_p.field_73012_v.nextInt(100) < 5)
/*  492 */       return "terrafirmacraft:mob.bear.cry"; 
/*  493 */     if (func_70631_g_() && this.field_70170_p.field_73012_v.nextInt(100) < 5) {
/*  494 */       return "terrafirmacraft:mob.bear.cub.cry";
/*      */     }
/*  496 */     return func_70631_g_() ? null : "terrafirmacraft:mob.bear.say";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int func_70641_bl() {
/*  505 */     return 2;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getMoveSpeed() {
/*  510 */     return this.moveSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysToAdult() {
/*  516 */     return TFC_Time.daysInMonth * 60;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getObedienceMod() {
/*  522 */     return this.obedienceMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPierceArmor() {
/*  528 */     return -335;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPregnancyRequiredTime() {
/*  533 */     return this.pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSex() {
/*  538 */     return this.sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSizeMod() {
/*  544 */     return this.sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSlashArmor() {
/*  550 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float func_70599_aP() {
/*  559 */     return 0.4F;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrengthMod() {
/*  565 */     return this.strengthMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeOfConception() {
/*  570 */     return this.timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void handleFamiliarityUpdate() {
/*  575 */     int totalDays = TFC_Time.getTotalDays();
/*  576 */     if (this.lastFamiliarityUpdate < totalDays) {
/*  577 */       if (this.familiarizedToday && this.familiarity < 100) {
/*  578 */         this.lastFamiliarityUpdate = totalDays;
/*  579 */         this.familiarizedToday = false;
/*  580 */         float familiarityChange = 3.0F * this.obedienceMod / this.aggressionMod;
/*  581 */         if (isAdult() && this.familiarity <= 80) {
/*      */           
/*  583 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*      */         }
/*  585 */         else if (!isAdult()) {
/*  586 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/*  587 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/*  588 */           if (this.familiarity > 70) {
/*  589 */             this.obedienceMod *= 1.01F;
/*      */           }
/*      */         }
/*      */       
/*  593 */       } else if (this.familiarity < 30) {
/*  594 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/*  595 */         this.lastFamiliarityUpdate = totalDays;
/*      */       } 
/*      */     }
/*  598 */     if (this.familiarity > 100) this.familiarity = 100; 
/*  599 */     if (this.familiarity < 0) this.familiarity = 0;
/*      */   
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70103_a(byte par1) {
/*  605 */     if (par1 == 8) {
/*      */       
/*  607 */       this.isWet = true;
/*      */     }
/*      */     else {
/*      */       
/*  611 */       super.func_70103_a(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBreedingItemTFC(ItemStack item) {
/*  617 */     return (!this.pregnant && isFood(item));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player) {
/*  623 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  625 */       if (player.func_70093_af() && !this.familiarizedToday && canFamiliarize()) {
/*      */         
/*  627 */         familiarize(player);
/*  628 */         return true;
/*      */       } 
/*  630 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
/*  631 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant)
/*      */       {
/*  633 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*      */       }
/*      */     } 
/*  636 */     ItemStack itemstack = player.func_70694_bm();
/*  637 */     if (itemstack != null && isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s() && (this.familiarizedToday || !canFamiliarize())) {
/*      */       
/*  639 */       if (!player.field_71075_bZ.field_75098_d) {
/*  640 */         player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */       }
/*  642 */       this.hunger += 24000;
/*  643 */       func_146082_f(player);
/*  644 */       return true;
/*  645 */     }  if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/*      */       
/*  647 */       if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player))
/*      */       {
/*  649 */         itemstack.field_77994_a--;
/*      */       }
/*  651 */       return true;
/*      */     } 
/*  653 */     return super.func_70085_c(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAdult() {
/*  659 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70650_aV() {
/*  668 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70631_g_() {
/*  674 */     return !isAdult();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isFood(ItemStack item) {
/*  679 */     return (item != null && item.func_77973_b().equals(TFCItems.fishRaw));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPregnant() {
/*  685 */     return this.pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isWasRoped() {
/*  690 */     return this.wasRoped;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mate(IAnimal otherAnimal) {
/*  696 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*      */       
/*  698 */       otherAnimal.mate(this);
/*      */       return;
/*      */     } 
/*  701 */     this.timeOfConception = TFC_Time.getTotalTicks();
/*  702 */     this.pregnant = true;
/*  703 */     func_70875_t();
/*  704 */     otherAnimal.setInLove(false);
/*  705 */     this.mateSizeMod = otherAnimal.getSizeMod();
/*  706 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*  707 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/*  708 */     this.mateObedMod = otherAnimal.getObedienceMod();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70636_d() {
/*  718 */     TFC_Core.preventEntityDataUpdate = true;
/*  719 */     super.func_70636_d();
/*  720 */     TFC_Core.preventEntityDataUpdate = false;
/*      */ 
/*      */     
/*  723 */     if (this.hunger > 168000) this.hunger = 168000; 
/*  724 */     if (this.hunger > 0) this.hunger--;
/*      */     
/*  726 */     if (func_70880_s()) {
/*      */       
/*  728 */       func_70875_t();
/*  729 */       setInLove(true);
/*      */     } 
/*      */     
/*  732 */     handleFamiliarityUpdate();
/*  733 */     syncData();
/*  734 */     if (isAdult()) { func_70873_a(0); }
/*  735 */     else { func_70873_a(-1); }
/*      */     
/*  737 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  739 */       if (!this.isWet && !func_70781_l() && this.field_70122_E) {
/*      */         
/*  741 */         this.isWet = true;
/*  742 */         this.field_70170_p.func_72960_a((Entity)this, (byte)8);
/*      */       } 
/*      */       
/*  745 */       if (isPregnant())
/*      */       {
/*  747 */         if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*      */           
/*  749 */           int i = this.rand.nextInt(3) + 1;
/*  750 */           for (int x = 0; x < i; x++) {
/*      */             
/*  752 */             EntityBear baby = (EntityBear)createChildTFC((EntityAgeable)this);
/*  753 */             baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/*  754 */             baby.field_70759_as = baby.field_70177_z;
/*  755 */             baby.field_70761_aq = baby.field_70177_z;
/*  756 */             this.field_70170_p.func_72838_d((Entity)baby);
/*  757 */             baby.setAge(TFC_Time.getTotalDays());
/*      */           } 
/*      */           
/*  760 */           this.pregnant = false;
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  765 */     if (func_110167_bD() && !this.wasRoped) {
/*  766 */       this.wasRoped = true;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70071_h_() {
/*  776 */     super.func_70071_h_();
/*  777 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  779 */       if (!this.isPeacefulAI && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
/*      */         
/*  781 */         this.isPeacefulAI = true;
/*  782 */         this.field_70714_bg.func_85156_a((EntityAIBase)this.attackAI);
/*  783 */         this.field_70714_bg.func_85156_a((EntityAIBase)this.leapAI);
/*  784 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetSheep);
/*  785 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetDeer);
/*  786 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPig);
/*  787 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetHorse);
/*  788 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPlayer);
/*  789 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.hurtAI);
/*      */       }
/*  791 */       else if (this.isPeacefulAI && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */         
/*  793 */         this.isPeacefulAI = false;
/*  794 */         this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.attackAI);
/*  795 */         this.field_70714_bg.func_75776_a(3, (EntityAIBase)this.leapAI);
/*  796 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetSheep);
/*  797 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetDeer);
/*  798 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPig);
/*  799 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetHorse);
/*  800 */         this.field_70715_bh.func_75776_a(4, (EntityAIBase)this.targetPlayer);
/*  801 */         this.field_70715_bh.func_75776_a(3, (EntityAIBase)this.hurtAI);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70037_a(NBTTagCompound nbt) {
/*  812 */     super.func_70037_a(nbt);
/*  813 */     this.sex = nbt.func_74762_e("Sex");
/*  814 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*      */     
/*  816 */     this.familiarity = nbt.func_74762_e("Familiarity");
/*  817 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/*  818 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*      */     
/*  820 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/*  821 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/*  822 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*      */     
/*  824 */     this.wasRoped = nbt.func_74767_n("wasRoped");
/*      */     
/*  826 */     this.hunger = nbt.func_74762_e("Hunger");
/*  827 */     this.pregnant = nbt.func_74767_n("Pregnant");
/*  828 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/*  829 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/*  830 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/*  831 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/*  832 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/*  833 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAge(int par1) {
/*  840 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAggressionMod(float aggression) {
/*  846 */     this.aggressionMod = aggression;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAttackedVec(Vec3 attackedVec) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBirthDay(int day) {
/*  857 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFamiliarity(int f) {
/*  863 */     this.familiarity = f;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFamiliarizedToday(boolean familiarizedToday) {
/*  868 */     this.familiarizedToday = familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFearSource(Entity fearSource) {}
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70873_a(int par1) {
/*  879 */     if (!TFC_Core.preventEntityDataUpdate) {
/*  880 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHunger(int h) {
/*  886 */     this.hunger = h;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInLove(boolean b) {
/*  892 */     this.inLove = b;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/*  897 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setMoveSpeed(float moveSpeed) {
/*  902 */     this.moveSpeed = moveSpeed;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObedienceMod(float obedience) {
/*  908 */     this.obedienceMod = obedience;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/*  913 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnant(boolean pregnant) {
/*  918 */     this.pregnant = pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSex(int sex) {
/*  923 */     this.sex = sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSizeMod(float size) {
/*  929 */     this.sizeMod = size;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrengthMod(float strength) {
/*  935 */     this.strengthMod = strength;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeOfConception(long timeOfConception) {
/*  940 */     this.timeOfConception = timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWasRoped(boolean wasRoped) {
/*  945 */     this.wasRoped = wasRoped;
/*      */   }
/*      */ 
/*      */   
/*      */   public void syncData() {
/*  950 */     if (this.field_70180_af != null)
/*      */     {
/*  952 */       if (!this.field_70170_p.field_72995_K) {
/*      */         
/*  954 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  960 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), 0 };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  966 */         ByteBuffer buf = ByteBuffer.wrap(values);
/*  967 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/*  968 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/*  969 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*      */       }
/*      */       else {
/*      */         
/*  973 */         this.sex = this.field_70180_af.func_75679_c(13);
/*      */         
/*  975 */         ByteBuffer buf = ByteBuffer.allocate(8);
/*  976 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/*  977 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/*  978 */         byte[] values = buf.array();
/*      */         
/*  980 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/*  981 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/*  982 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/*  983 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*      */         
/*  985 */         this.familiarity = values[4];
/*  986 */         this.familiarizedToday = (values[5] == 1);
/*  987 */         this.pregnant = (values[6] == 1);
/*      */ 
/*      */         
/*      */         try {
/*  991 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/*  992 */         } catch (NumberFormatException numberFormatException) {}
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean trySetName(String name, EntityPlayer player) {
/*  999 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*      */       
/* 1001 */       func_94058_c(name);
/* 1002 */       return true;
/*      */     } 
/* 1004 */     func_85030_a(func_70631_g_() ? "terrafirmacraft:mob.bear.cub.cry" : "terrafirmacraft:mob.bear.cry", 6.0F, this.rand.nextFloat() / 2.0F + 0.75F);
/* 1005 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70629_bd() {
/* 1014 */     this.field_70180_af.func_75692_b(18, Float.valueOf(func_110143_aJ()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbt) {
/* 1023 */     super.func_70014_b(nbt);
/* 1024 */     nbt.func_74768_a("Sex", this.sex);
/* 1025 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*      */     
/* 1027 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 1028 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 1029 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/*      */     
/* 1031 */     nbt.func_74776_a("Strength Modifier", this.strengthMod);
/* 1032 */     nbt.func_74776_a("Aggression Modifier", this.aggressionMod);
/* 1033 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*      */     
/* 1035 */     nbt.func_74757_a("wasRoped", this.wasRoped);
/*      */     
/* 1037 */     nbt.func_74768_a("Hunger", this.hunger);
/* 1038 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 1039 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 1040 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 1041 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 1042 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 1043 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 1044 */     nbt.func_74768_a("Age", getBirthDay());
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */