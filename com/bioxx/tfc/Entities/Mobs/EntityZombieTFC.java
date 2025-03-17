/*     */ package com.bioxx.tfc.Entities.Mobs;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityLivingData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.monster.EntityZombie;
/*     */ import net.minecraft.entity.passive.EntityWolf;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.EnumDifficulty;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.ForgeHooks;
/*     */ import net.minecraftforge.event.ForgeEventFactory;
/*     */ import net.minecraftforge.event.entity.living.ZombieEvent;
/*     */ 
/*     */ 
/*     */ public class EntityZombieTFC
/*     */   extends EntityZombie
/*     */   implements ICausesDamage, IInnateArmor
/*     */ {
/*     */   public EntityZombieTFC(World par1World) {
/*  41 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_110147_ax() {
/*  47 */     super.func_110147_ax();
/*  48 */     func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(250.0D);
/*  49 */     func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(450.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70601_bi() {
/*  55 */     int x = MathHelper.func_76128_c(this.field_70165_t);
/*  56 */     int y = MathHelper.func_76128_c(this.field_70121_D.field_72338_b);
/*  57 */     int z = MathHelper.func_76128_c(this.field_70161_v);
/*  58 */     Block b = this.field_70170_p.func_147439_a(x, y, z);
/*     */     
/*  60 */     if (b == TFCBlocks.leaves || b == TFCBlocks.leaves2 || b == TFCBlocks.thatch) {
/*  61 */       return false;
/*     */     }
/*  63 */     return super.func_70601_bi();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_70658_aO() {
/*  72 */     int var1 = super.func_70658_aO() + 2;
/*  73 */     if (var1 > 20)
/*  74 */       var1 = 20; 
/*  75 */     return var1; } protected void func_70600_l(int par1) {
/*     */     Random r0;
/*     */     ItemStack is1;
/*     */     Random r1;
/*     */     ItemStack is2;
/*     */     Random r2;
/*  81 */     switch (this.field_70146_Z.nextInt(3)) {
/*     */       
/*     */       case 0:
/*  84 */         r0 = new Random();
/*  85 */         if (r0.nextInt(3) == 0)
/*  86 */           func_145779_a(TFCItems.wroughtIronIngot, 1); 
/*     */         break;
/*     */       case 1:
/*  89 */         is1 = new ItemStack(TFCItems.carrot);
/*  90 */         r1 = new Random();
/*  91 */         if (r1.nextInt(100) < 100) {
/*     */           
/*  93 */           float weight = CropIndex.getWeight(30.0F, r1);
/*  94 */           ItemFoodTFC.createTag(is1, weight, weight / 2.0F);
/*  95 */           func_70099_a(is1, 0.0F);
/*     */         } 
/*     */         break;
/*     */       case 2:
/*  99 */         is2 = new ItemStack(TFCItems.potato);
/* 100 */         r2 = new Random();
/* 101 */         if (r2.nextInt(100) < 100) {
/*     */           
/* 103 */           float weight = CropIndex.getWeight(55.0F, r2);
/* 104 */           ItemFoodTFC.createTag(is2, weight, weight / 2.0F);
/* 105 */           func_70099_a(is2, 0.0F);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82164_bB() {
/* 114 */     func_70062_b(0, null);
/* 115 */     func_70062_b(1, null);
/* 116 */     func_70062_b(2, null);
/* 117 */     func_70062_b(3, null);
/* 118 */     func_70062_b(4, null);
/*     */     
/* 120 */     if (this.field_70146_Z.nextFloat() < ((this.field_70170_p.field_73013_u == EnumDifficulty.HARD) ? 0.05F : 0.01F)) {
/*     */       
/* 122 */       int var1 = this.field_70146_Z.nextInt(3);
/* 123 */       if (var1 == 0) {
/* 124 */         func_70062_b(0, new ItemStack(TFCItems.bronzePick));
/*     */       } else {
/* 126 */         func_70062_b(0, new ItemStack(TFCItems.bronzeShovel));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_82162_bC() {}
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70103_a(byte par1) {
/* 140 */     if (par1 == 16) {
/* 141 */       this.field_70170_p.func_72908_a(this.field_70165_t + 0.5D, this.field_70163_u + 0.5D, this.field_70161_v + 0.5D, "mob.zombie.remedy", 1.0F + this.field_70146_Z.nextFloat(), this.field_70146_Z.nextFloat() * 0.7F + 0.3F);
/*     */     } else {
/* 143 */       super.func_70103_a(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumDamageType getDamageType() {
/* 149 */     return EnumDamageType.SLASHING;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_70097_a(DamageSource ds, float par2) {
/* 155 */     if (ForgeHooks.onLivingAttack((EntityLivingBase)this, ds, par2)) return false; 
/* 156 */     if (func_85032_ar())
/*     */     {
/* 158 */       return false;
/*     */     }
/* 160 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 162 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 166 */     this.field_70708_bq = 0;
/*     */     
/* 168 */     if (func_110143_aJ() <= 0.0F)
/*     */     {
/* 170 */       return false;
/*     */     }
/* 172 */     if (ds.func_76347_k() && func_70644_a(Potion.field_76426_n))
/*     */     {
/* 174 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 178 */     if ((ds == DamageSource.field_82728_o || ds == DamageSource.field_82729_p) && func_71124_b(4) != null) {
/*     */       
/* 180 */       func_71124_b(4).func_77972_a((int)(par2 * 4.0F + this.field_70146_Z.nextFloat() * par2 * 2.0F), (EntityLivingBase)this);
/* 181 */       par2 *= 0.75F;
/*     */     } 
/*     */     
/* 184 */     this.field_70721_aZ = 1.5F;
/* 185 */     boolean flag = true;
/*     */     
/* 187 */     if (this.field_70172_ad > this.field_70771_an / 2.0F) {
/*     */       
/* 189 */       if (par2 <= this.field_110153_bc)
/* 190 */         return false; 
/* 191 */       func_70665_d(ds, par2 - this.field_110153_bc);
/* 192 */       this.field_110153_bc = par2;
/* 193 */       flag = false;
/*     */     }
/*     */     else {
/*     */       
/* 197 */       this.field_110153_bc = par2;
/* 198 */       this.field_70735_aL = func_110143_aJ();
/* 199 */       this.field_70172_ad = this.field_70771_an;
/* 200 */       func_70665_d(ds, par2);
/* 201 */       this.field_70737_aN = this.field_70738_aO = 10;
/*     */     } 
/*     */     
/* 204 */     this.field_70739_aP = 0.0F;
/* 205 */     Entity entity = ds.func_76346_g();
/*     */     
/* 207 */     if (entity != null) {
/*     */       
/* 209 */       if (entity instanceof EntityLivingBase) {
/* 210 */         func_70604_c((EntityLivingBase)entity);
/*     */       }
/* 212 */       if (entity instanceof EntityPlayer) {
/*     */         
/* 214 */         this.field_70718_bc = 100;
/* 215 */         this.field_70717_bb = (EntityPlayer)entity;
/*     */       }
/* 217 */       else if (entity instanceof EntityWolf) {
/*     */         
/* 219 */         EntityWolf entitywolf = (EntityWolf)entity;
/*     */         
/* 221 */         if (entitywolf.func_70909_n()) {
/*     */           
/* 223 */           this.field_70718_bc = 100;
/* 224 */           this.field_70717_bb = null;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 229 */     if (flag) {
/*     */       
/* 231 */       this.field_70170_p.func_72960_a((Entity)this, (byte)2);
/*     */       
/* 233 */       if (ds != DamageSource.field_76369_e) {
/* 234 */         func_70018_K();
/*     */       }
/* 236 */       if (entity != null) {
/*     */         
/* 238 */         double d0 = entity.field_70165_t - this.field_70165_t;
/*     */         
/*     */         double d1;
/* 241 */         for (d1 = entity.field_70161_v - this.field_70161_v; d0 * d0 + d1 * d1 < 1.0E-4D; d1 = (Math.random() - Math.random()) * 0.01D)
/*     */         {
/* 243 */           d0 = (Math.random() - Math.random()) * 0.01D;
/*     */         }
/*     */         
/* 246 */         this.field_70739_aP = (float)(Math.atan2(d1, d0) * 180.0D / Math.PI) - this.field_70177_z;
/* 247 */         func_70653_a(entity, par2, d0, d1);
/*     */       }
/*     */       else {
/*     */         
/* 251 */         this.field_70739_aP = ((int)(Math.random() * 2.0D) * 180);
/*     */       } 
/*     */     } 
/*     */     
/* 255 */     if (func_110143_aJ() <= 0.0F) {
/*     */       
/* 257 */       if (flag)
/* 258 */         func_85030_a(func_70673_aS(), func_70599_aP(), func_70647_i()); 
/* 259 */       func_70645_a(ds);
/*     */     }
/* 261 */     else if (flag) {
/*     */       
/* 263 */       func_85030_a(func_70621_aR(), func_70599_aP(), func_70647_i());
/*     */     } 
/*     */     
/* 266 */     summonAid(ds);
/*     */     
/* 268 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void summonAid(DamageSource ds) {
/* 275 */     EntityLivingBase entitylivingbase = func_70638_az();
/*     */     
/* 277 */     if (entitylivingbase == null && func_70777_m() instanceof EntityLivingBase)
/*     */     {
/* 279 */       entitylivingbase = (EntityLivingBase)func_70777_m();
/*     */     }
/*     */     
/* 282 */     if (entitylivingbase == null && ds.func_76346_g() instanceof EntityLivingBase)
/*     */     {
/* 284 */       entitylivingbase = (EntityLivingBase)ds.func_76346_g();
/*     */     }
/*     */     
/* 287 */     int i = MathHelper.func_76128_c(this.field_70165_t);
/* 288 */     int j = MathHelper.func_76128_c(this.field_70163_u);
/* 289 */     int k = MathHelper.func_76128_c(this.field_70161_v);
/*     */     
/* 291 */     ZombieEvent.SummonAidEvent summonAid = ForgeEventFactory.fireZombieSummonAid(this, this.field_70170_p, i, j, k, entitylivingbase, func_110148_a(field_110186_bp).func_111126_e());
/*     */     
/* 293 */     if (summonAid.getResult() == Event.Result.DENY) {
/*     */       return;
/*     */     }
/*     */     
/* 297 */     if (summonAid.getResult() == Event.Result.ALLOW || (entitylivingbase != null && this.field_70170_p.field_73013_u == EnumDifficulty.HARD && this.field_70146_Z.nextFloat() < func_110148_a(field_110186_bp).func_111126_e())) {
/*     */       EntityZombie entityzombie;
/*     */       
/* 300 */       if (summonAid.customSummonedAid != null && summonAid.getResult() == Event.Result.ALLOW) {
/*     */         
/* 302 */         entityzombie = summonAid.customSummonedAid;
/*     */       }
/*     */       else {
/*     */         
/* 306 */         entityzombie = new EntityZombieTFC(this.field_70170_p);
/*     */       } 
/*     */       
/* 309 */       for (int l = 0; l < 50; l++) {
/*     */         
/* 311 */         int i1 = i + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
/* 312 */         int j1 = j + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
/* 313 */         int k1 = k + MathHelper.func_76136_a(this.field_70146_Z, 7, 40) * MathHelper.func_76136_a(this.field_70146_Z, -1, 1);
/*     */         
/* 315 */         if (World.func_147466_a((IBlockAccess)this.field_70170_p, i1, j1 - 1, k1) && this.field_70170_p.func_72957_l(i1, j1, k1) < 10 && TFC_Core.getCDM(this.field_70170_p).getData(i1 >> 4, k1 >> 4).getSpawnProtectionWithUpdate() <= 0) {
/*     */           
/* 317 */           entityzombie.func_70107_b(i1, j1, k1);
/*     */           
/* 319 */           if (this.field_70170_p.func_72855_b(entityzombie.field_70121_D) && this.field_70170_p.func_72945_a((Entity)entityzombie, entityzombie.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(entityzombie.field_70121_D)) {
/*     */             
/* 321 */             this.field_70170_p.func_72838_d((Entity)entityzombie);
/* 322 */             if (entitylivingbase != null) entityzombie.func_70624_b(entitylivingbase); 
/* 323 */             entityzombie.func_110161_a((IEntityLivingData)null);
/* 324 */             func_110148_a(field_110186_bp).func_111121_a(new AttributeModifier("Zombie reinforcement caller charge", -0.05000000074505806D, 0));
/* 325 */             entityzombie.func_110148_a(field_110186_bp).func_111121_a(new AttributeModifier("Zombie reinforcement callee charge", -0.05000000074505806D, 0));
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCrushArmor() {
/* 336 */     return 1000;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSlashArmor() {
/* 342 */     return -335;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPierceArmor() {
/* 348 */     return 0;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\Mobs\EntityZombieTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */