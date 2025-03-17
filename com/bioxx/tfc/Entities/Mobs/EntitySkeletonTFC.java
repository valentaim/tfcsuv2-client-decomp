/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*     */ import com.bioxx.tfc.api.Interfaces.IProjectile;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.EnumCreatureAttribute;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.IRangedAttackMob;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.EntityAIArrowAttack;
/*     */ import net.minecraft.entity.ai.EntityAIAttackOnCollide;
/*     */ import net.minecraft.entity.ai.EntityAIBase;
/*     */ import net.minecraft.entity.ai.EntityAIFleeSun;
/*     */ import net.minecraft.entity.ai.EntityAIHurtByTarget;
/*     */ import net.minecraft.entity.ai.EntityAILookIdle;
/*     */ import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
/*     */ import net.minecraft.entity.ai.EntityAIRestrictSun;
/*     */ import net.minecraft.entity.ai.EntityAISwimming;
/*     */ import net.minecraft.entity.ai.EntityAIWander;
/*     */ import net.minecraft.entity.ai.EntityAIWatchClosest;
/*     */ import net.minecraft.entity.monster.EntityMob;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class EntitySkeletonTFC
/*     */   extends EntityMob
/*     */   implements IRangedAttackMob, ICausesDamage, IInnateArmor {
/*  49 */   private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 120, 15.0F);
/*  50 */   private final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide((EntityCreature)this, EntityPlayer.class, 1.2D, false);
/*  51 */   private static final float[] ARMOR_PROBABILITY = new float[] { 0.0F, 0.5F, 0.1F, 0.15F };
/*     */   
/*     */   private static ItemStack heldItem;
/*     */   
/*     */   public EntitySkeletonTFC(World par1World) {
/*  56 */     super(par1World);
/*  57 */     heldItem = new ItemStack(TFCItems.bow);
/*  58 */     this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
/*  59 */     this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIRestrictSun((EntityCreature)this));
/*  60 */     this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIFleeSun((EntityCreature)this, 1.0D));
/*  61 */     this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0D));
/*  62 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0F));
/*  63 */     this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
/*  64 */     this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
/*  65 */     this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
/*     */     
/*  67 */     if (par1World != null && !par1World.field_72995_K) {
/*  68 */       setCombatTask();
/*     */     }
/*     */   }
/*     */   
/*     */   public void setCombatTask() {
/*  73 */     this.field_70714_bg.func_85156_a((EntityAIBase)this.aiAttackOnCollide);
/*  74 */     this.field_70714_bg.func_85156_a((EntityAIBase)this.aiArrowAttack);
/*  75 */     ItemStack itemstack = func_70694_bm();
/*     */     
/*  77 */     if (itemstack != null && (itemstack.func_77973_b() == TFCItems.bow || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin)) {
/*     */       
/*  79 */       this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiArrowAttack);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */       
/*  87 */       this.field_70714_bg.func_75776_a(4, (EntityAIBase)this.aiAttackOnCollide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70650_aV() {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70639_aQ() {
/* 106 */     return "mob.skeleton.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70621_aR() {
/* 115 */     return "mob.skeleton.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String func_70673_aS() {
/* 124 */     return "mob.skeleton.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_145780_a(int par1, int par2, int par3, Block par4) {
/* 133 */     func_85030_a("mob.skeleton.step", 0.15F, 1.0F);
/* 134 */     super.func_145780_a(par1, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 140 */     super.func_70037_a(par1NBTTagCompound);
/* 141 */     if (par1NBTTagCompound.func_74764_b("SkeletonType")) {
/*     */       
/* 143 */       byte b0 = par1NBTTagCompound.func_74771_c("SkeletonType");
/* 144 */       setSkeletonType(b0);
/*     */     } 
/* 146 */     setCombatTask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 155 */     super.func_70014_b(par1NBTTagCompound);
/* 156 */     par1NBTTagCompound.func_74774_a("SkeletonType", (byte)getSkeletonType());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/* 161 */     super.func_110147_ax();
/* 162 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1500.0D);
/* 163 */     func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {
/* 169 */     super.func_70088_a();
/* 170 */     this.field_70180_af.func_75682_a(13, Byte.valueOf((byte)this.field_70146_Z.nextInt(2)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70030_z() {
/* 176 */     super.func_70030_z();
/* 177 */     if (func_70027_ad()) {
/* 178 */       func_70097_a(DamageSource.field_76370_b, 50.0F);
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
/*     */   public EnumCreatureAttribute func_70668_bt() {
/* 193 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70636_d() {
/* 199 */     if (this.field_70170_p.func_72935_r() && !this.field_70170_p.field_72995_K) {
/*     */       
/* 201 */       float f = func_70013_c(1.0F);
/*     */       
/* 203 */       if (f > 0.5F && this.field_70146_Z.nextFloat() * 30.0F < (f - 0.4F) * 2.0F && this.field_70170_p.func_72937_j(MathHelper.func_76128_c(this.field_70165_t), MathHelper.func_76128_c(this.field_70163_u), MathHelper.func_76128_c(this.field_70161_v))) {
/*     */         
/* 205 */         boolean flag = true;
/* 206 */         ItemStack itemstack = func_71124_b(4);
/* 207 */         if (itemstack != null) {
/*     */           
/* 209 */           if (itemstack.func_77984_f()) {
/*     */             
/* 211 */             itemstack.func_77964_b(itemstack.func_77952_i() + this.field_70146_Z.nextInt(2));
/* 212 */             if (itemstack.func_77952_i() >= itemstack.func_77958_k()) {
/*     */               
/* 214 */               func_70669_a(itemstack);
/* 215 */               func_70062_b(4, (ItemStack)null);
/*     */             } 
/*     */           } 
/* 218 */           flag = false;
/*     */         } 
/*     */         
/* 221 */         if (flag) {
/* 222 */           func_70015_d(8);
/*     */         }
/*     */       } 
/*     */     } 
/* 226 */     if (this.field_70170_p.field_72995_K && getSkeletonType() == 1) {
/* 227 */       func_70105_a(0.72F, 2.34F);
/*     */     }
/* 229 */     super.func_70636_d();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70098_U() {
/* 238 */     super.func_70098_U();
/* 239 */     if (this.field_70154_o instanceof EntityCreature) {
/*     */       
/* 241 */       EntityCreature entitycreature = (EntityCreature)this.field_70154_o;
/* 242 */       this.field_70761_aq = entitycreature.field_70761_aq;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70645_a(DamageSource par1DamageSource) {
/* 252 */     super.func_70645_a(par1DamageSource);
/* 253 */     if (par1DamageSource.func_76364_f() instanceof net.minecraft.entity.projectile.EntityArrow && par1DamageSource.func_76346_g() instanceof EntityPlayer) {
/*     */       
/* 255 */       EntityPlayer entityplayer = (EntityPlayer)par1DamageSource.func_76346_g();
/* 256 */       double d0 = entityplayer.field_70165_t - this.field_70165_t;
/* 257 */       double d1 = entityplayer.field_70161_v - this.field_70161_v;
/*     */       
/* 259 */       if (d0 * d0 + d1 * d1 >= 2500.0D) {
/* 260 */         entityplayer.func_71029_a((StatBase)AchievementList.field_76020_v);
/*     */       }
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
/*     */   protected void func_70628_a(boolean par1, int par2) {
/* 273 */     if (getSkeletonType() == 1) {
/*     */       
/* 275 */       if (func_70694_bm() != null && func_70694_bm().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin && this.field_70146_Z.nextFloat() < 0.03F) {
/* 276 */         func_145779_a(func_70694_bm().func_77973_b(), 1);
/*     */       
/*     */       }
/*     */     }
/* 280 */     else if (func_70694_bm() != null && func_70694_bm().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomBow) {
/*     */       
/* 282 */       int i = this.field_70146_Z.nextInt(3 + par2);
/* 283 */       func_145779_a(TFCItems.arrow, i);
/*     */     } 
/*     */ 
/*     */     
/* 287 */     int amnt = this.field_70146_Z.nextInt(3 + par2);
/* 288 */     func_145779_a(Items.field_151103_aS, amnt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70600_l(int par1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82160_b(boolean forceDrop, int dropChance) {
/* 299 */     for (int j = 0; j < (func_70035_c()).length; j++) {
/*     */       
/* 301 */       ItemStack itemstack = func_71124_b(j);
/* 302 */       boolean flag1 = (this.field_82174_bp[j] > 1.0F);
/*     */       
/* 304 */       if (itemstack != null && (forceDrop || flag1) && this.field_70146_Z.nextFloat() - dropChance * 0.01F < this.field_82174_bp[j]) {
/*     */         
/* 306 */         if (!flag1 && itemstack.func_77984_f()) {
/*     */           
/* 308 */           int k = Math.max(itemstack.func_77958_k() - 25, 1);
/* 309 */           int l = itemstack.func_77958_k() - this.field_70146_Z.nextInt(this.field_70146_Z.nextInt(k) + 1);
/*     */           
/* 311 */           if (l > k)
/*     */           {
/* 313 */             l = k;
/*     */           }
/*     */           
/* 316 */           if (l < 1)
/*     */           {
/* 318 */             l = 1;
/*     */           }
/*     */           
/* 321 */           itemstack.func_77964_b(l);
/*     */         } 
/*     */         
/* 324 */         func_70099_a(itemstack, 0.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82164_bB() {
/* 332 */     superAddRandomArmor();
/* 333 */     if (getSkeletonType() == 0) {
/*     */       
/* 335 */       func_70062_b(0, new ItemStack(TFCItems.bow));
/*     */     }
/* 337 */     else if (getSkeletonType() == 1) {
/*     */       
/* 339 */       int i = this.field_70146_Z.nextInt(2);
/* 340 */       if (this.field_70146_Z.nextFloat() < 0.095F)
/* 341 */         i++; 
/* 342 */       if (this.field_70146_Z.nextFloat() < 0.095F)
/* 343 */         i++; 
/* 344 */       if (this.field_70146_Z.nextFloat() < 0.095F) {
/* 345 */         i++;
/*     */       }
/* 347 */       if (i == 0) {
/* 348 */         func_70062_b(0, new ItemStack(TFCItems.sedStoneJavelin));
/* 349 */       } else if (i == 1) {
/* 350 */         func_70062_b(0, new ItemStack(TFCItems.igExStoneJavelin));
/* 351 */       } else if (i == 2) {
/* 352 */         func_70062_b(0, new ItemStack(TFCItems.copperJavelin));
/* 353 */       } else if (i == 3) {
/* 354 */         func_70062_b(0, new ItemStack(TFCItems.bronzeJavelin));
/* 355 */       } else if (i == 4) {
/* 356 */         func_70062_b(0, new ItemStack(TFCItems.wroughtIronJavelin));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82162_bC() {}
/*     */ 
/*     */   
/*     */   public static Item getArmorItemForSlot(int par0, int par1) {
/* 367 */     switch (par0) {
/*     */       
/*     */       case 4:
/* 370 */         if (par1 == 0)
/* 371 */           return TFCItems.leatherHelmet; 
/* 372 */         if (par1 == 1)
/* 373 */           return TFCItems.copperHelmet; 
/* 374 */         if (par1 == 2)
/* 375 */           return TFCItems.bronzeHelmet; 
/* 376 */         if (par1 == 3)
/* 377 */           return TFCItems.wroughtIronHelmet; 
/* 378 */         if (par1 == 4) {
/* 379 */           return TFCItems.steelHelmet;
/*     */         }
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
/* 421 */         return null;case 3: if (par1 == 0) return TFCItems.leatherChestplate;  if (par1 == 1) return TFCItems.copperChestplate;  if (par1 == 2) return TFCItems.bronzeChestplate;  if (par1 == 3) return TFCItems.wroughtIronChestplate;  if (par1 == 4) return TFCItems.steelChestplate;  return null;case 2: if (par1 == 0) return TFCItems.leatherLeggings;  if (par1 == 1) return TFCItems.copperGreaves;  if (par1 == 2) return TFCItems.bronzeGreaves;  if (par1 == 3) return TFCItems.wroughtIronGreaves;  if (par1 == 4) return TFCItems.steelGreaves;  return null;case 1: if (par1 == 0) return TFCItems.leatherBoots;  if (par1 == 1) return TFCItems.copperBoots;  if (par1 == 2) return TFCItems.bronzeBoots;  if (par1 == 3) return TFCItems.wroughtIronBoots;  if (par1 == 4) return TFCItems.steelBoots;  return null;
/*     */     } 
/*     */     return null;
/*     */   }
/*     */   private void superAddRandomArmor() {
/* 426 */     if (this.field_70146_Z.nextFloat() < ARMOR_PROBABILITY[this.field_70170_p.field_73013_u.func_151525_a()]) {
/*     */       
/* 428 */       int i = this.field_70146_Z.nextInt(2);
/* 429 */       float f = (this.field_70170_p.field_73013_u == EnumDifficulty.HARD) ? 0.1F : 0.25F;
/*     */       
/* 431 */       if (this.field_70146_Z.nextFloat() < 0.095F)
/* 432 */         i++; 
/* 433 */       if (this.field_70146_Z.nextFloat() < 0.095F)
/* 434 */         i++; 
/* 435 */       if (this.field_70146_Z.nextFloat() < 0.095F) {
/* 436 */         i++;
/*     */       }
/* 438 */       for (int j = 3; j >= 0; j--) {
/*     */         
/* 440 */         ItemStack itemstack = func_71124_b(j);
/* 441 */         if (j < 3 && this.field_70146_Z.nextFloat() < f) {
/*     */           break;
/*     */         }
/* 444 */         if (itemstack == null) {
/*     */           
/* 446 */           Item item = getArmorItemForSlot(j + 1, i);
/* 447 */           if (item != null)
/* 448 */             func_70062_b(j + 1, new ItemStack(item)); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public ItemStack func_70694_bm() {
/* 455 */     return heldItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
/* 462 */     par1EntityLivingData = super.func_110161_a(par1EntityLivingData);
/*     */     
/* 464 */     int skelType = this.field_70146_Z.nextInt(2);
/* 465 */     setSkeletonType(skelType);
/* 466 */     func_82164_bB();
/* 467 */     func_82162_bC();
/* 468 */     setCombatTask();
/* 469 */     func_98053_h((this.field_70146_Z.nextFloat() < 0.55F * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)));
/*     */     
/* 471 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_82196_d(EntityLivingBase par1EntityLiving, float par2) {
/*     */     EntityJavelin entityJavelin;
/* 477 */     EntityProjectileTFC projectile = null;
/* 478 */     if (getSkeletonType() == 0) {
/*     */       
/* 480 */       projectile = new EntityProjectileTFC(this.field_70170_p, (EntityLivingBase)this, par1EntityLiving, 1.6F, 12.0F);
/* 481 */       projectile.func_70239_b(65.0D);
/*     */     }
/* 483 */     else if (getSkeletonType() == 1) {
/*     */       
/* 485 */       ItemStack is = func_70694_bm();
/* 486 */       if (is != null && is.func_77973_b() instanceof IProjectile) {
/*     */         
/* 488 */         entityJavelin = new EntityJavelin(this.field_70170_p, (EntityLivingBase)this, par1EntityLiving, 1.6F, 12.0F);
/* 489 */         double dam = ((IProjectile)is.func_77973_b()).getRangedDamage(is);
/* 490 */         entityJavelin.func_70239_b(dam);
/*     */       } 
/*     */     } 
/*     */     
/* 494 */     if (entityJavelin != null) {
/*     */       
/* 496 */       int var3 = EnchantmentHelper.func_77506_a(Enchantment.field_77345_t.field_77352_x, func_70694_bm());
/* 497 */       int var4 = EnchantmentHelper.func_77506_a(Enchantment.field_77344_u.field_77352_x, func_70694_bm());
/* 498 */       entityJavelin.func_70239_b(entityJavelin.func_70242_d() * 1.0D + this.field_70146_Z.nextGaussian() * 0.25D + (this.field_70170_p.field_73013_u.func_151525_a() * 0.11F));
/*     */       
/* 500 */       if (var3 > 0)
/* 501 */         entityJavelin.func_70239_b(entityJavelin.func_70242_d() + var3 * 0.5D); 
/* 502 */       if (var4 > 0)
/* 503 */         entityJavelin.func_70240_a(var4); 
/* 504 */       if (EnchantmentHelper.func_77506_a(Enchantment.field_77343_v.field_77352_x, func_70694_bm()) > 0) {
/* 505 */         entityJavelin.func_70015_d(100);
/*     */       }
/* 507 */       this.field_70170_p.func_72956_a((Entity)this, "random.bow", 1.0F, 1.0F / (func_70681_au().nextFloat() * 0.4F + 0.8F));
/* 508 */       this.field_70170_p.func_72838_d((Entity)entityJavelin);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkeletonType() {
/* 517 */     return this.field_70180_af.func_75683_a(13);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSkeletonType(int par1) {
/* 522 */     this.field_70180_af.func_75692_b(13, Byte.valueOf((byte)par1));
/* 523 */     func_70105_a(0.6F, 1.8F);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumDamageType getDamageType() {
/* 529 */     return EnumDamageType.PIERCING;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getCrushArmor() {
/* 534 */     return -335;
/*     */   }
/*     */   
/*     */   public int getSlashArmor() {
/* 538 */     return 1000;
/*     */   }
/*     */   
/*     */   public int getPierceArmor() {
/* 542 */     return 500000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/* 548 */     int x = MathHelper.func_76128_c(this.field_70165_t);
/* 549 */     int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/* 550 */     int z = MathHelper.func_76128_c(this.field_70161_v);
/* 551 */     Block b = this.field_70170_p.func_147439_a(x, y, z);
/*     */     
/* 553 */     if (b == TFCBlocks.leaves || b == TFCBlocks.leaves2 || b == TFCBlocks.thatch) {
/* 554 */       return false;
/*     */     }
/* 556 */     return super.func_70601_bi();
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntitySkeletonTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */