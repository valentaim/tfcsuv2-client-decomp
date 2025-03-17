/*      */ package com.bioxx.tfc.Entities.Mobs;
/*      */ 
/*      */ import com.bioxx.tfc.Core.TFC_Climate;
/*      */ import com.bioxx.tfc.Core.TFC_Core;
/*      */ import com.bioxx.tfc.Core.TFC_Time;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAIMateTFC;
/*      */ import com.bioxx.tfc.Entities.AI.EntityAISitTFC;
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
/*      */ import cpw.mods.fml.relauncher.Side;
/*      */ import cpw.mods.fml.relauncher.SideOnly;
/*      */ import fof.tfcsu.Entity.EntityBoar;
/*      */ import fof.tfcsu.utils.ExpBonus;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import net.minecraft.block.BlockColored;
/*      */ import net.minecraft.entity.Entity;
/*      */ import net.minecraft.entity.EntityAgeable;
/*      */ import net.minecraft.entity.EntityLiving;
/*      */ import net.minecraft.entity.EntityLivingBase;
/*      */ import net.minecraft.entity.SharedMonsterAttributes;
/*      */ import net.minecraft.entity.ai.EntityAIBase;
/*      */ import net.minecraft.entity.ai.EntityAISit;
/*      */ import net.minecraft.entity.passive.EntityTameable;
/*      */ import net.minecraft.entity.passive.EntityWolf;
/*      */ import net.minecraft.entity.player.EntityPlayer;
/*      */ import net.minecraft.init.Items;
/*      */ import net.minecraft.item.Item;
/*      */ import net.minecraft.item.ItemStack;
/*      */ import net.minecraft.nbt.NBTTagCompound;
/*      */ import net.minecraft.pathfinding.PathEntity;
/*      */ import net.minecraft.util.AxisAlignedBB;
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
/*      */ public class EntityWolfTFC
/*      */   extends EntityWolf
/*      */   implements IAnimal, IInnateArmor, ICausesDamage
/*      */ {
/*      */   private static final float GESTATION_PERIOD = 2.25F;
/*      */   private static final float DIMORPHISM = 0.0786F;
/*      */   private static final int DEGREE_OF_DIVERSION = 1;
/*      */   private static final int FAMILIARITY_CAP = 35;
/*      */   private long animalID;
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
/*   74 */   private float aggressionMod = 1.0F;
/*   75 */   private float obedienceMod = 1.0F;
/*      */   
/*      */   private boolean inLove;
/*      */   
/*      */   private int familiarity;
/*      */   
/*      */   private long lastFamiliarityUpdate;
/*      */   
/*      */   private boolean familiarizedToday;
/*      */   private int happyTicks;
/*      */   private boolean wasRoped;
/*      */   private static final int BREED_RANGE = 50;
/*      */   protected EntityAITargetNonTamedTFC targetChicken;
/*      */   protected EntityAITargetNonTamedTFC targetPheasant;
/*      */   protected EntityAITargetNonTamedTFC targetPig;
/*      */   protected EntityAITargetNonTamedTFC targetDeer;
/*      */   protected EntityAITargetNonTamedTFC targetHorse;
/*      */   private boolean peacefulAI;
/*      */   
/*      */   public EntityWolfTFC(World par1World) {
/*   95 */     super(par1World);
/*   96 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMateTFC(this, this.field_70170_p, 1.0F));
/*   97 */     this.field_70715_bh.func_85156_a((EntityAIBase)this.field_70911_d);
/*   98 */     this.field_70911_d = (EntityAISit)new EntityAISitTFC((EntityTameable)this);
/*   99 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.field_70911_d);
/*      */ 
/*      */     
/*  102 */     this.targetChicken = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityChickenTFC.class, 200, false);
/*  103 */     this.targetPheasant = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityPheasantTFC.class, 200, false);
/*      */     
/*  105 */     this.targetPig = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityBoar.class, 200, false);
/*      */     
/*  107 */     this.targetDeer = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityDeer.class, 200, false);
/*  108 */     this.targetHorse = new EntityAITargetNonTamedTFC((EntityTameable)this, EntityHorseTFC.class, 200, false);
/*  109 */     if (this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */       
/*  111 */       this.peacefulAI = false;
/*  112 */       this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetChicken);
/*  113 */       this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPheasant);
/*  114 */       this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPig);
/*      */       
/*  116 */       this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetDeer);
/*  117 */       this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetHorse);
/*      */     } else {
/*      */       
/*  120 */       this.peacefulAI = true;
/*      */     } 
/*  122 */     this.hunger = 168000;
/*  123 */     this.animalID = TFC_Time.getTotalTicks() + func_145782_y();
/*  124 */     this.pregnant = false;
/*  125 */     this.pregnancyRequiredTime = (int)(TFCOptions.animalTimeMultiplier * 2.25F * (float)TFC_Time.ticksInMonth);
/*  126 */     this.timeOfConception = 0L;
/*  127 */     this.mateSizeMod = 1.0F;
/*  128 */     this.sex = this.field_70146_Z.nextInt(2);
/*  129 */     this.sizeMod = (float)Math.sqrt((((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(20) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F) * (1.0F - 0.0786F * this.sex)));
/*  130 */     this.strengthMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + this.sizeMod));
/*  131 */     this.aggressionMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F));
/*  132 */     this.obedienceMod = (float)Math.sqrt(((this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(10) + 1) * (this.field_70146_Z.nextBoolean() ? 1 : -1)) * 0.01F + 1.0F / this.aggressionMod));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  138 */     setAge(TFC_Time.getTotalDays() - getNumberOfDaysToAdult());
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityWolfTFC(World par1World, IAnimal mother, List<Float> data) {
/*  143 */     this(par1World);
/*  144 */     float fatherSize = 1.0F;
/*  145 */     float fatherStr = 1.0F;
/*  146 */     float fatherAggro = 1.0F;
/*  147 */     float fatherObed = 1.0F;
/*  148 */     for (int i = 0; i < data.size(); i++) {
/*  149 */       switch (i) { case 0:
/*  150 */           fatherSize = ((Float)data.get(i)).floatValue(); break;
/*  151 */         case 1: fatherStr = ((Float)data.get(i)).floatValue(); break;
/*  152 */         case 2: fatherAggro = ((Float)data.get(i)).floatValue(); break;
/*  153 */         case 3: fatherObed = ((Float)data.get(i)).floatValue();
/*      */           break; }
/*      */     
/*      */     } 
/*  157 */     this.field_70165_t = ((EntityLivingBase)mother).field_70165_t;
/*  158 */     this.field_70163_u = ((EntityLivingBase)mother).field_70163_u;
/*  159 */     this.field_70161_v = ((EntityLivingBase)mother).field_70161_v;
/*  160 */     float invSizeRatio = 0.5204539F;
/*  161 */     this.sizeMod = (float)Math.sqrt((this.sizeMod * this.sizeMod * (float)Math.sqrt(((mother.getSizeMod() + fatherSize) * invSizeRatio))));
/*  162 */     this.strengthMod = (float)Math.sqrt((this.strengthMod * this.strengthMod * (float)Math.sqrt(((mother.getStrengthMod() + fatherStr) * 0.5F))));
/*  163 */     this.aggressionMod = (float)Math.sqrt((this.aggressionMod * this.aggressionMod * (float)Math.sqrt(((mother.getAggressionMod() + fatherAggro) * 0.5F))));
/*  164 */     this.obedienceMod = (float)Math.sqrt((this.obedienceMod * this.obedienceMod * (float)Math.sqrt(((mother.getObedienceMod() + fatherObed) * 0.5F))));
/*      */     
/*  166 */     this.familiarity = (int)((mother.getFamiliarity() < 90) ? (mother.getFamiliarity() / 2) : (mother.getFamiliarity() * 0.9F));
/*      */ 
/*      */     
/*  169 */     setAge(TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70601_bi() {
/*  176 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/*  177 */     int j = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  178 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*      */     
/*  180 */     float temp = TFC_Climate.getHeightAdjustedTemp(this.field_70170_p, i, this.field_70170_p.func_72825_h(i, k), k);
/*  181 */     float rain = TFC_Climate.getRainfall(this.field_70170_p, i, 150, k);
/*  182 */     float evt = 0.0F;
/*  183 */     if (TFC_Climate.getCacheManager(this.field_70170_p) != null && TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k) != null)
/*  184 */       evt = (TFC_Climate.getCacheManager(this.field_70170_p).getEVTLayerAt(i, k)).floatdata1; 
/*  185 */     TFCBiome biome = (TFCBiome)this.field_70170_p.func_72959_q().func_76935_a(i, k);
/*  186 */     boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);
/*  187 */     if ((isMountainous && temp < 25.0F && temp > -10.0F && rain > 250.0F && evt < 0.75D) || (temp > 0.0F && temp < 21.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 250.0F) || (temp > -20.0F && temp <= 0.0F && rain > 100.0F))
/*  188 */       return (TFCBiome.isGrass(this.field_70170_p.func_147439_a(i, j - 1, k)) && this.field_70170_p.func_72883_k(i, j, k) > 8 && TFCBiome.getCanSpawnHere((EntityLiving)this)); 
/*  189 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_110147_ax() {
/*  194 */     super.func_110147_ax();
/*  195 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0D);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_70652_k(Entity par1Entity) {
/*  200 */     int damage = (int)(200.0F * getStrengthMod() * getAggressionMod() * (getSizeMod() / 2.0F + 0.5F));
/*      */     
/*  202 */     return par1Entity.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), damage);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean func_70692_ba() {
/*  207 */     if (!isAdult())
/*  208 */       return false; 
/*  209 */     if (func_70902_q() != null)
/*  210 */       return false; 
/*  211 */     if (this.wasRoped) {
/*  212 */       return false;
/*      */     }
/*  214 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canFamiliarize() {
/*  219 */     return (!isAdult() || (isAdult() && this.familiarity <= 35));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canMateWith(IAnimal animal) {
/*  224 */     return (animal.getGender() != getGender() && isAdult() && animal.isAdult() && animal instanceof EntityWolfTFC);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkFamiliarity(IAnimal.InteractionEnum interaction, EntityPlayer player) {
/*  231 */     boolean flag = false;
/*  232 */     switch (interaction) {
/*      */       
/*      */       case BREED:
/*  235 */         flag = (this.familiarity > 20);
/*      */         break;
/*      */       case NAME:
/*  238 */         flag = (this.familiarity > 40);
/*      */         break;
/*      */     } 
/*      */ 
/*      */     
/*  243 */     if (!flag && player != null && !player.field_70170_p.field_72995_K)
/*      */     {
/*  245 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.notFamiliar", new Object[0]));
/*      */     }
/*  247 */     return flag;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityWolf func_90011_a(EntityAgeable entityageable) {
/*  253 */     return (EntityWolf)createChildTFC(entityageable);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChildTFC(EntityAgeable eAgeable) {
/*  259 */     ArrayList<Float> data = new ArrayList<>();
/*  260 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateSize")));
/*  261 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateStrength")));
/*  262 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateAggro")));
/*  263 */     data.add(Float.valueOf(eAgeable.getEntityData().func_74760_g("MateObed")));
/*  264 */     return (EntityAgeable)new EntityWolfTFC(this.field_70170_p, this, data);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void func_70628_a(boolean par1, int par2) {
/*  270 */     float ageMod = TFC_Core.getPercentGrown(this);
/*  271 */     func_70099_a(new ItemStack(TFCItems.hide, 1, Math.max(0, Math.min(2, (int)((this.sizeMod * ageMod) * 0.9D)))), 0.0F);
/*  272 */     func_145779_a(Items.field_151103_aS, (int)((this.field_70146_Z.nextInt(3) + 1) * ageMod));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void func_70088_a() {
/*  277 */     super.func_70088_a();
/*  278 */     this.field_70180_af.func_75682_a(13, Integer.valueOf(0));
/*  279 */     this.field_70180_af.func_75682_a(15, Integer.valueOf(0));
/*      */     
/*  281 */     this.field_70180_af.func_75682_a(22, Integer.valueOf(0));
/*  282 */     this.field_70180_af.func_75682_a(23, Integer.valueOf(0));
/*  283 */     this.field_70180_af.func_75682_a(24, String.valueOf("0"));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void familiarize(EntityPlayer ep) {
/*  289 */     if (this.happyTicks == 0 && this.familiarity >= 5 && !this.familiarizedToday && canFamiliarize()) {
/*      */       
/*  291 */       this.familiarizedToday = true;
/*  292 */       func_70671_ap().func_75651_a((Entity)ep, 0.0F, 0.0F);
/*  293 */       func_70642_aH();
/*  294 */       this.happyTicks = 40;
/*      */     } 
/*  296 */     if (this.familiarity > 80 && func_70902_q() != null) {
/*      */       
/*  298 */       if (!func_70909_n()) ExpBonus.TAMED.give(ep); 
/*  299 */       func_70903_f(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public float getAggressionMod() {
/*  305 */     return this.aggressionMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getAnimalID() {
/*  310 */     return this.animalID;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getAnimalTypeID() {
/*  316 */     return Helper.stringToInt("wolf");
/*      */   }
/*      */ 
/*      */   
/*      */   public Vec3 getAttackedVec() {
/*  321 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getBirthDay() {
/*  326 */     return this.field_70180_af.func_75679_c(15);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCrushArmor() {
/*  331 */     return 250;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumDamageType getDamageType() {
/*  337 */     return EnumDamageType.SLASHING;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDueDay() {
/*  342 */     return TFC_Time.getDayFromTotalHours((this.timeOfConception + this.pregnancyRequiredTime) / 1000L);
/*      */   }
/*      */ 
/*      */   
/*      */   public EntityLiving getEntity() {
/*  347 */     return (EntityLiving)this;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getFamiliarity() {
/*  353 */     return this.familiarity;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFamiliarizedToday() {
/*  359 */     return this.familiarizedToday;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Entity getFearSource() {
/*  366 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public IAnimal.GenderEnum getGender() {
/*  372 */     return IAnimal.GenderEnum.GENDERS[this.field_70180_af.func_75679_c(13)];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHappyTicks() {
/*  378 */     return this.happyTicks;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getHunger() {
/*  384 */     return this.hunger;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getInLove() {
/*  390 */     return this.inLove;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getLastFamiliarityUpdate() {
/*  395 */     return this.lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfDaysToAdult() {
/*  401 */     return (int)(TFCOptions.animalTimeMultiplier * TFC_Time.daysInMonth * 9.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getObedienceMod() {
/*  407 */     return this.obedienceMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getPierceArmor() {
/*  413 */     return -335;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getPregnancyRequiredTime() {
/*  418 */     return this.pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getSex() {
/*  423 */     return this.sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getSizeMod() {
/*  429 */     return this.sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int getSlashArmor() {
/*  435 */     return 250;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getStrengthMod() {
/*  441 */     return this.strengthMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SideOnly(Side.CLIENT)
/*      */   public float func_70920_v() {
/*  448 */     float scale = func_110138_aP() / 20.0F;
/*  449 */     if (func_70919_bu())
/*  450 */       return 1.5393804F; 
/*  451 */     if (func_70902_q() != null) {
/*  452 */       return (0.55F - (func_110138_aP() - this.field_70180_af.func_111145_d(18)) / scale * 0.02F) * 3.1415927F;
/*      */     }
/*  454 */     return 0.62831855F;
/*      */   }
/*      */ 
/*      */   
/*      */   public long getTimeOfConception() {
/*  459 */     return this.timeOfConception;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleFamiliarityUpdate() {
/*  465 */     int totalDays = TFC_Time.getTotalDays();
/*  466 */     if (this.lastFamiliarityUpdate < totalDays)
/*      */     {
/*  468 */       if (this.familiarizedToday && this.familiarity < 100) {
/*      */         
/*  470 */         this.lastFamiliarityUpdate = totalDays;
/*  471 */         this.familiarizedToday = false;
/*  472 */         float familiarityChange = 6.0F * this.obedienceMod / this.aggressionMod;
/*  473 */         if (isAdult() && this.familiarity >= 5 && this.familiarity <= 35)
/*      */         {
/*  475 */           this.familiarity = (int)(this.familiarity + familiarityChange);
/*      */         }
/*  477 */         else if (!isAdult())
/*      */         {
/*  479 */           float ageMod = 2.0F / (1.0F + TFC_Core.getPercentGrown(this));
/*  480 */           this.familiarity = (int)(this.familiarity + ageMod * familiarityChange);
/*  481 */           if (this.familiarity > 70)
/*      */           {
/*  483 */             this.obedienceMod *= 1.01F;
/*      */           }
/*      */         }
/*      */       
/*  487 */       } else if (this.familiarity < 30) {
/*      */         
/*  489 */         this.familiarity = (int)(this.familiarity - 2L * (TFC_Time.getTotalDays() - this.lastFamiliarityUpdate));
/*  490 */         this.lastFamiliarityUpdate = totalDays;
/*      */       } 
/*      */     }
/*  493 */     if (this.familiarity > 100)
/*  494 */       this.familiarity = 100; 
/*  495 */     if (this.familiarity < ((func_70902_q() != null) ? 5 : 0)) {
/*  496 */       this.familiarity = (func_70902_q() != null) ? 5 : 0;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70085_c(EntityPlayer player) {
/*  505 */     if (!this.field_70170_p.field_72995_K) {
/*      */       
/*  507 */       if (player.func_70093_af() && func_70902_q() != null && canFamiliarize()) {
/*      */         
/*  509 */         familiarize(player);
/*  510 */         return true;
/*      */       } 
/*  512 */       if (player.func_70694_bm() != null) {
/*      */         
/*  514 */         ItemStack is = player.func_70694_bm();
/*  515 */         if (isFood(is)) {
/*      */           
/*  517 */           Item item = is.func_77973_b();
/*  518 */           if (item instanceof ItemFoodTFC && this.hunger <= 160000) {
/*      */             
/*  520 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)item).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*  521 */             this.hunger += 24000;
/*  522 */             return true;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  527 */       TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation((getGender() == IAnimal.GenderEnum.FEMALE) ? "entity.female" : "entity.male", new Object[0]));
/*  528 */       if (getGender() == IAnimal.GenderEnum.FEMALE && this.pregnant) {
/*  529 */         TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("entity.pregnant", new Object[0]));
/*      */       }
/*      */     } 
/*  532 */     ItemStack itemstack = player.field_71071_by.func_70448_g();
/*      */     
/*  534 */     if (itemstack != null) {
/*      */       
/*  536 */       if (isBreedingItemTFC(itemstack) && checkFamiliarity(IAnimal.InteractionEnum.BREED, player) && func_70874_b() == 0 && !func_70880_s()) {
/*      */         
/*  538 */         if (!player.field_71075_bZ.field_75098_d)
/*      */         {
/*  540 */           player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, ((ItemFoodTFC)itemstack.func_77973_b()).onConsumedByEntity(player.func_70694_bm(), this.field_70170_p, (EntityLivingBase)this));
/*      */         }
/*      */         
/*  543 */         func_146082_f(player);
/*  544 */         return true;
/*      */       } 
/*  546 */       if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemCustomNameTag && itemstack.func_77942_o() && itemstack.field_77990_d.func_74764_b("ItemName")) {
/*      */         
/*  548 */         if (trySetName(itemstack.field_77990_d.func_74779_i("ItemName"), player))
/*      */         {
/*  550 */           itemstack.field_77994_a--;
/*      */         }
/*  552 */         return true;
/*      */       } 
/*  554 */       if (itemstack.func_77973_b() == Items.field_151103_aS && !func_70919_bu()) {
/*      */         
/*  556 */         if (func_70902_q() == null) {
/*      */           
/*  558 */           if (!player.field_71075_bZ.field_75098_d)
/*      */           {
/*  560 */             itemstack.field_77994_a--;
/*      */           }
/*      */           
/*  563 */           if (itemstack.field_77994_a <= 0)
/*      */           {
/*  565 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */           }
/*      */           
/*  568 */           if (!this.field_70170_p.field_72995_K)
/*      */           {
/*  570 */             if (this.field_70146_Z.nextInt(3) == 0) {
/*      */               
/*  572 */               func_70903_f(true);
/*  573 */               func_70778_a((PathEntity)null);
/*  574 */               func_70624_b((EntityLivingBase)null);
/*  575 */               func_152115_b(player.func_110124_au().toString());
/*  576 */               func_70908_e(true);
/*  577 */               this.field_70170_p.func_72960_a((Entity)this, (byte)7);
/*      */             }
/*      */             else {
/*      */               
/*  581 */               func_70908_e(false);
/*  582 */               this.field_70170_p.func_72960_a((Entity)this, (byte)6);
/*      */             } 
/*      */           }
/*      */         } 
/*      */         
/*  587 */         return true;
/*      */       } 
/*      */       
/*  590 */       if (func_70909_n() && (itemstack.func_77973_b() == Items.field_151100_aR || itemstack.func_77973_b() == TFCItems.dye)) {
/*      */         
/*  592 */         int i = BlockColored.func_150032_b(itemstack.func_77960_j());
/*      */         
/*  594 */         if (i != func_82186_bH()) {
/*      */           
/*  596 */           func_82185_r(i);
/*      */           
/*  598 */           if (!player.field_71075_bZ.field_75098_d && --itemstack.field_77994_a <= 0)
/*      */           {
/*  600 */             player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, (ItemStack)null);
/*      */           }
/*      */         } 
/*      */         
/*  604 */         return true;
/*      */       } 
/*      */     } 
/*      */     
/*  608 */     return super.func_70085_c(player);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAdult() {
/*  614 */     return (getBirthDay() + getNumberOfDaysToAdult() <= TFC_Time.getTotalDays());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70877_b(ItemStack is) {
/*  620 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isBreedingItemTFC(ItemStack item) {
/*  625 */     return (!this.pregnant && isFood(item));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70631_g_() {
/*  631 */     return !isAdult();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFood(ItemStack item) {
/*  637 */     return (item != null && (item
/*  638 */       .func_77973_b() == TFCItems.beefRaw || item.func_77973_b() == TFCItems.chickenRaw || item.func_77973_b() == TFCItems.fishRaw || item
/*  639 */       .func_77973_b() == TFCItems.horseMeatRaw || item.func_77973_b() == TFCItems.muttonRaw || item.func_77973_b() == TFCItems.porkchopRaw || item
/*  640 */       .func_77973_b() == TFCItems.venisonRaw));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isPeacefulAI() {
/*  645 */     return this.peacefulAI;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isPregnant() {
/*  651 */     return this.pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasBeenRoped() {
/*  656 */     return this.wasRoped;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mate(IAnimal otherAnimal) {
/*  662 */     if (getGender() == IAnimal.GenderEnum.MALE) {
/*      */       
/*  664 */       otherAnimal.mate(this);
/*  665 */       setInLove(false);
/*  666 */       func_70875_t();
/*      */       return;
/*      */     } 
/*  669 */     this.timeOfConception = TFC_Time.getTotalTicks();
/*  670 */     this.pregnant = true;
/*  671 */     func_70875_t();
/*  672 */     setInLove(false);
/*  673 */     otherAnimal.setInLove(false);
/*  674 */     this.mateAggroMod = otherAnimal.getAggressionMod();
/*  675 */     this.mateObedMod = otherAnimal.getObedienceMod();
/*  676 */     this.mateSizeMod = otherAnimal.getSizeMod();
/*  677 */     this.mateStrengthMod = otherAnimal.getStrengthMod();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70636_d() {
/*  688 */     if (this.hunger > 168000)
/*  689 */       this.hunger = 168000; 
/*  690 */     if (this.hunger > 0) {
/*  691 */       this.hunger--;
/*      */     }
/*  693 */     if (func_110167_bD()) {
/*      */       
/*  695 */       Entity leashedTo = func_110166_bE();
/*      */       
/*  697 */       if (leashedTo instanceof net.minecraft.entity.EntityLeashKnot && this.familiarity >= 5 && !func_70919_bu()) {
/*      */         
/*  699 */         this.field_70911_d.func_75270_a(true);
/*  700 */         func_70904_g(true);
/*  701 */         this.field_70703_bu = false;
/*      */         
/*  703 */         func_70778_a((PathEntity)null);
/*  704 */         func_70784_b((Entity)null);
/*  705 */         func_70624_b((EntityLivingBase)null);
/*      */       
/*      */       }
/*  708 */       else if (leashedTo instanceof EntityPlayer || (leashedTo instanceof net.minecraft.entity.EntityLeashKnot && func_70919_bu())) {
/*      */         
/*  710 */         this.field_70911_d.func_75270_a(false);
/*  711 */         func_70904_g(false);
/*      */       } 
/*      */       
/*  714 */       if (!this.wasRoped) {
/*  715 */         this.wasRoped = true;
/*      */       }
/*      */     }
/*  718 */     else if (func_70919_bu() && func_70906_o()) {
/*      */       
/*  720 */       this.field_70911_d.func_75270_a(false);
/*  721 */       func_70904_g(false);
/*      */     } 
/*      */     
/*  724 */     if (func_70880_s()) {
/*  725 */       setInLove(true);
/*      */     }
/*  727 */     if (isAdult()) {
/*  728 */       func_70873_a(0);
/*      */     } else {
/*  730 */       func_70873_a(-1);
/*      */     } 
/*  732 */     handleFamiliarityUpdate();
/*      */     
/*  734 */     if (this.happyTicks > 0) {
/*  735 */       this.happyTicks--;
/*      */     }
/*  737 */     syncData();
/*      */     
/*  739 */     if (!this.field_70170_p.field_72995_K && isPregnant())
/*      */     {
/*  741 */       if (TFC_Time.getTotalTicks() >= this.timeOfConception + this.pregnancyRequiredTime) {
/*      */         
/*  743 */         List<?> around_wolfs = this.field_70170_p.func_72872_a(EntityWolfTFC.class, AxisAlignedBB.func_72330_a(this.field_70165_t - 50.0D, this.field_70163_u - 50.0D, this.field_70161_v - 50.0D, this.field_70165_t + 50.0D, this.field_70163_u + 50.0D, this.field_70161_v + 50.0D));
/*      */         
/*  745 */         if (around_wolfs.size() < 10) {
/*      */           
/*  747 */           int i = this.field_70146_Z.nextInt(2) + 1;
/*  748 */           for (int x = 0; x < i; x++) {
/*      */             
/*  750 */             ArrayList<Float> data = new ArrayList<>();
/*  751 */             data.add(Float.valueOf(this.mateSizeMod));
/*  752 */             EntityWolfTFC baby = (EntityWolfTFC)createChildTFC((EntityAgeable)this);
/*  753 */             baby.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0F, 0.0F);
/*  754 */             baby.field_70759_as = baby.field_70177_z;
/*  755 */             baby.field_70761_aq = baby.field_70177_z;
/*  756 */             baby.setAge(TFC_Time.getTotalDays());
/*  757 */             this.field_70170_p.func_72838_d((Entity)baby);
/*      */           } 
/*      */         } 
/*  760 */         this.pregnant = false;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  767 */     TFC_Core.preventEntityDataUpdate = true;
/*  768 */     super.func_70636_d();
/*  769 */     TFC_Core.preventEntityDataUpdate = false;
/*      */     
/*  771 */     if (this.hunger > 144000 && this.field_70146_Z.nextInt(100) == 0 && func_110143_aJ() < TFC_Core.getEntityMaxHealth((EntityLivingBase)this) && !this.field_70128_L) {
/*      */       
/*  773 */       func_70691_i(1.0F);
/*      */     }
/*  775 */     else if (this.hunger < 144000 && func_70880_s()) {
/*      */       
/*  777 */       setInLove(false);
/*      */     } 
/*      */ 
/*      */     
/*  781 */     if (func_110167_bD() && func_70919_bu() && func_110166_bE() == func_70902_q()) {
/*      */       
/*  783 */       func_70916_h(false);
/*  784 */       func_70778_a((PathEntity)null);
/*  785 */       func_70784_b((Entity)null);
/*  786 */       func_70624_b((EntityLivingBase)null);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70071_h_() {
/*  793 */     super.func_70071_h_();
/*  794 */     if (!this.field_70170_p.field_72995_K)
/*      */     {
/*  796 */       if (!this.peacefulAI && this.field_70170_p.field_73013_u == EnumDifficulty.PEACEFUL) {
/*      */         
/*  798 */         this.peacefulAI = true;
/*  799 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetChicken);
/*  800 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPheasant);
/*  801 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetPig);
/*      */         
/*  803 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetDeer);
/*  804 */         this.field_70715_bh.func_85156_a((EntityAIBase)this.targetHorse);
/*      */       }
/*  806 */       else if (this.peacefulAI && this.field_70170_p.field_73013_u != EnumDifficulty.PEACEFUL) {
/*      */         
/*  808 */         this.peacefulAI = false;
/*  809 */         this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetChicken);
/*  810 */         this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPheasant);
/*  811 */         this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetPig);
/*      */         
/*  813 */         this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetDeer);
/*  814 */         this.field_70715_bh.func_75776_a(7, (EntityAIBase)this.targetHorse);
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
/*  825 */     super.func_70037_a(nbt);
/*  826 */     func_70916_h(nbt.func_74767_n("Angry"));
/*  827 */     this.animalID = nbt.func_74763_f("Animal ID");
/*  828 */     this.sex = nbt.func_74762_e("Sex");
/*  829 */     this.sizeMod = nbt.func_74760_g("Size Modifier");
/*      */     
/*  831 */     this.familiarity = nbt.func_74762_e("Familiarity");
/*  832 */     this.lastFamiliarityUpdate = nbt.func_74763_f("lastFamUpdate");
/*  833 */     this.familiarizedToday = nbt.func_74767_n("Familiarized Today");
/*      */     
/*  835 */     this.strengthMod = nbt.func_74760_g("Strength Modifier");
/*  836 */     this.aggressionMod = nbt.func_74760_g("Aggression Modifier");
/*  837 */     this.obedienceMod = nbt.func_74760_g("Obedience Modifier");
/*      */     
/*  839 */     this.field_70180_af.func_75692_b(16, Byte.valueOf(nbt.func_74771_c("tamed")));
/*  840 */     this.happyTicks = nbt.func_74762_e("happy");
/*      */     
/*  842 */     this.wasRoped = nbt.func_74767_n("wasRoped");
/*      */     
/*  844 */     this.hunger = nbt.func_74762_e("Hunger");
/*  845 */     this.pregnant = nbt.func_74767_n("Pregnant");
/*  846 */     this.mateSizeMod = nbt.func_74760_g("MateSize");
/*  847 */     this.mateStrengthMod = nbt.func_74760_g("MateStrength");
/*  848 */     this.mateAggroMod = nbt.func_74760_g("MateAggro");
/*  849 */     this.mateObedMod = nbt.func_74760_g("MateObed");
/*  850 */     this.timeOfConception = nbt.func_74763_f("ConceptionTime");
/*  851 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(nbt.func_74762_e("Age")));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAge(int par1) {
/*  857 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(par1));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setAggressionMod(float aggressionMod) {
/*  863 */     this.aggressionMod = aggressionMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAnimalID(long animalID) {
/*  868 */     this.animalID = animalID;
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
/*  879 */     this.field_70180_af.func_75692_b(15, Integer.valueOf(day));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFamiliarity(int familiarity) {
/*  885 */     this.familiarity = familiarity;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setFamiliarizedToday(boolean familiarizedToday) {
/*  890 */     this.familiarizedToday = familiarizedToday;
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
/*  901 */     if (!TFC_Core.preventEntityDataUpdate) {
/*  902 */       this.field_70180_af.func_75692_b(12, Integer.valueOf(par1));
/*      */     }
/*      */   }
/*      */   
/*      */   public void setHappyTicks(int happyTicks) {
/*  907 */     this.happyTicks = happyTicks;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setHunger(int h) {
/*  913 */     this.hunger = h;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInLove(boolean b) {
/*  919 */     this.inLove = b;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLastFamiliarityUpdate(long lastFamiliarityUpdate) {
/*  924 */     this.lastFamiliarityUpdate = lastFamiliarityUpdate;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setObedienceMod(float obedienceMod) {
/*  930 */     this.obedienceMod = obedienceMod;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPeacefulAI(boolean isPeacefulAI) {
/*  935 */     this.peacefulAI = isPeacefulAI;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnancyRequiredTime(int pregnancyRequiredTime) {
/*  940 */     this.pregnancyRequiredTime = pregnancyRequiredTime;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPregnant(boolean pregnant) {
/*  945 */     this.pregnant = pregnant;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setSex(int sex) {
/*  950 */     this.sex = sex;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSizeMod(float sizeMod) {
/*  956 */     this.sizeMod = sizeMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStrengthMod(float strengthMod) {
/*  962 */     this.strengthMod = strengthMod;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70903_f(boolean par1) {
/*  968 */     if (this.familiarity > 80 && !func_70909_n()) {
/*      */       
/*  970 */       super.func_70903_f(par1);
/*      */       
/*  972 */       double healthRatio = func_110143_aJ() / func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b();
/*      */       
/*  974 */       func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2000.0D);
/*  975 */       float h = (float)(healthRatio * func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b());
/*  976 */       func_70606_j(h);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void setTimeOfConception(long timeOfConception) {
/*  982 */     this.timeOfConception = timeOfConception;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWasRoped(boolean wasRoped) {
/*  987 */     this.wasRoped = wasRoped;
/*      */   }
/*      */ 
/*      */   
/*      */   public void syncData() {
/*  992 */     if (this.field_70180_af != null)
/*      */     {
/*  994 */       if (!this.field_70170_p.field_72995_K) {
/*      */         
/*  996 */         this.field_70180_af.func_75692_b(13, Integer.valueOf(this.sex));
/*      */         
/*  998 */         byte[] values = { TFC_Core.getByteFromSmallFloat(this.sizeMod), TFC_Core.getByteFromSmallFloat(this.strengthMod), TFC_Core.getByteFromSmallFloat(this.aggressionMod), TFC_Core.getByteFromSmallFloat(this.obedienceMod), (byte)this.familiarity, (byte)(this.familiarizedToday ? 1 : 0), (byte)(this.pregnant ? 1 : 0), (byte)this.happyTicks };
/*      */ 
/*      */         
/* 1001 */         ByteBuffer buf = ByteBuffer.wrap(values);
/* 1002 */         this.field_70180_af.func_75692_b(22, Integer.valueOf(buf.getInt()));
/* 1003 */         this.field_70180_af.func_75692_b(23, Integer.valueOf(buf.getInt()));
/* 1004 */         this.field_70180_af.func_75692_b(24, String.valueOf(this.timeOfConception));
/*      */       }
/*      */       else {
/*      */         
/* 1008 */         this.sex = this.field_70180_af.func_75679_c(13);
/*      */         
/* 1010 */         ByteBuffer buf = ByteBuffer.allocate(8);
/* 1011 */         buf.putInt(this.field_70180_af.func_75679_c(22));
/* 1012 */         buf.putInt(this.field_70180_af.func_75679_c(23));
/* 1013 */         byte[] values = buf.array();
/*      */         
/* 1015 */         this.sizeMod = TFC_Core.getSmallFloatFromByte(values[0]);
/* 1016 */         this.strengthMod = TFC_Core.getSmallFloatFromByte(values[1]);
/* 1017 */         this.aggressionMod = TFC_Core.getSmallFloatFromByte(values[2]);
/* 1018 */         this.obedienceMod = TFC_Core.getSmallFloatFromByte(values[3]);
/*      */         
/* 1020 */         this.familiarity = values[4];
/* 1021 */         this.familiarizedToday = (values[5] == 1);
/* 1022 */         this.pregnant = (values[6] == 1);
/* 1023 */         this.happyTicks = values[7];
/*      */ 
/*      */         
/*      */         try {
/* 1027 */           this.timeOfConception = Long.parseLong(this.field_70180_af.func_75681_e(24));
/* 1028 */         } catch (NumberFormatException numberFormatException) {}
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean trySetName(String name, EntityPlayer player) {
/* 1037 */     if (checkFamiliarity(IAnimal.InteractionEnum.NAME, player)) {
/*      */       
/* 1039 */       func_94058_c(name);
/* 1040 */       return true;
/*      */     } 
/* 1042 */     func_85030_a("mob.wolf.growl", 6.0F, this.field_70146_Z.nextFloat() / 2.0F + (func_70631_g_() ? 1.25F : 0.75F));
/* 1043 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void func_70014_b(NBTTagCompound nbt) {
/* 1052 */     super.func_70014_b(nbt);
/* 1053 */     nbt.func_74757_a("Angry", func_70919_bu());
/* 1054 */     nbt.func_74768_a("Familiarity", this.familiarity);
/* 1055 */     nbt.func_74772_a("lastFamUpdate", this.lastFamiliarityUpdate);
/* 1056 */     nbt.func_74757_a("Familiarized Today", this.familiarizedToday);
/* 1057 */     nbt.func_74768_a("Sex", this.sex);
/* 1058 */     nbt.func_74772_a("Animal ID", this.animalID);
/* 1059 */     nbt.func_74776_a("Size Modifier", this.sizeMod);
/*      */     
/* 1061 */     nbt.func_74774_a("tamed", this.field_70180_af.func_75683_a(16));
/* 1062 */     nbt.func_74768_a("happy", this.happyTicks);
/*      */     
/* 1064 */     nbt.func_74757_a("wasRoped", this.wasRoped);
/*      */     
/* 1066 */     nbt.func_74776_a("Strength Modifier", getStrengthMod());
/* 1067 */     nbt.func_74776_a("Aggression Modifier", getAggressionMod());
/* 1068 */     nbt.func_74776_a("Obedience Modifier", this.obedienceMod);
/*      */     
/* 1070 */     nbt.func_74768_a("Hunger", this.hunger);
/* 1071 */     nbt.func_74757_a("Pregnant", this.pregnant);
/* 1072 */     nbt.func_74776_a("MateSize", this.mateSizeMod);
/* 1073 */     nbt.func_74776_a("MateStrength", this.mateStrengthMod);
/* 1074 */     nbt.func_74776_a("MateAggro", this.mateAggroMod);
/* 1075 */     nbt.func_74776_a("MateObed", this.mateObedMod);
/* 1076 */     nbt.func_74772_a("ConceptionTime", this.timeOfConception);
/* 1077 */     nbt.func_74768_a("Age", getBirthDay());
/*      */   }
/*      */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityWolfTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */