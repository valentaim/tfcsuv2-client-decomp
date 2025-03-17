/*     */ package com.bioxx.tfc.Entities;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.projectile.EntityFishHook;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSource;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.World;
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
/*     */ public class EntityFishHookTFC
/*     */   extends EntityFishHook
/*     */ {
/*     */   private int xTile;
/*     */   private int yTile;
/*     */   private int zTile;
/*     */   private Block inTile;
/*     */   private boolean inGround;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private int ticksCatchable;
/*     */   private int fishPosRotationIncrements;
/*     */   private double fishX;
/*     */   private double fishY;
/*     */   private double fishZ;
/*     */   private double fishYaw;
/*     */   private double fishPitch;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityX;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityY;
/*     */   @SideOnly(Side.CLIENT)
/*     */   private double velocityZ;
/*  62 */   private double maxDistance = -1.0D;
/*     */   
/*     */   private boolean canCatchFish;
/*     */   
/*     */   public double pullX;
/*     */   
/*     */   public double pullY;
/*     */   
/*     */   public double pullZ;
/*     */   private int lineTension;
/*     */   private static final int MAX_LINE_TENSION = 800;
/*     */   private int reelCounter;
/*     */   private int lastCheckTick;
/*     */   private boolean lineTensionSnap;
/*     */   
/*     */   public EntityFishHookTFC(World par1World) {
/*  78 */     super(par1World);
/*  79 */     this.xTile = -1;
/*  80 */     this.yTile = -1;
/*  81 */     this.zTile = -1;
/*  82 */     func_70105_a(0.25F, 0.25F);
/*  83 */     this.field_70158_ak = true;
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public EntityFishHookTFC(World par1World, double par2, double par4, double par6, EntityPlayer par8EntityPlayer) {
/*  89 */     this(par1World);
/*  90 */     func_70107_b(par2, par4, par6);
/*  91 */     this.field_70158_ak = true;
/*  92 */     this.field_146042_b = par8EntityPlayer;
/*  93 */     par8EntityPlayer.field_71104_cf = this;
/*     */   }
/*     */   
/*     */   public double getMaxDistance() {
/*  97 */     return this.maxDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHookTFC(World par1World, EntityPlayer par2EntityPlayer) {
/* 102 */     super(par1World);
/* 103 */     this.xTile = -1;
/* 104 */     this.yTile = -1;
/* 105 */     this.zTile = -1;
/* 106 */     this.field_70158_ak = true;
/* 107 */     this.field_146042_b = par2EntityPlayer;
/* 108 */     this.field_146042_b.field_71104_cf = this;
/* 109 */     func_70105_a(0.25F, 0.25F);
/* 110 */     func_70012_b(par2EntityPlayer.field_70165_t, par2EntityPlayer.field_70163_u + 1.62D - par2EntityPlayer.field_70129_M, par2EntityPlayer.field_70161_v, par2EntityPlayer.field_70177_z, par2EntityPlayer.field_70125_A);
/* 111 */     this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/* 112 */     this.field_70163_u -= 0.10000000149011612D;
/* 113 */     this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/* 114 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 115 */     this.field_70129_M = 0.0F;
/* 116 */     float f = 0.4F;
/* 117 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 118 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 119 */     this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 120 */     func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, 1.5F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHookTFC(World par1World, EntityPlayer par2EntityPlayer, int ticks) {
/* 125 */     super(par1World);
/* 126 */     this.xTile = -1;
/* 127 */     this.yTile = -1;
/* 128 */     this.zTile = -1;
/* 129 */     this.field_70158_ak = true;
/* 130 */     this.field_146042_b = par2EntityPlayer;
/* 131 */     this.field_146042_b.field_71104_cf = this;
/* 132 */     func_70105_a(0.25F, 0.25F);
/* 133 */     func_70012_b(par2EntityPlayer.field_70165_t, par2EntityPlayer.field_70163_u + 1.62D - par2EntityPlayer.field_70129_M, par2EntityPlayer.field_70161_v, par2EntityPlayer.field_70177_z, par2EntityPlayer.field_70125_A);
/* 134 */     this.field_70165_t -= (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/* 135 */     this.field_70163_u -= 0.10000000149011612D;
/* 136 */     this.field_70161_v -= (MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * 0.16F);
/* 137 */     func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 138 */     this.field_70129_M = 0.0F;
/* 139 */     float f = 0.4F;
/* 140 */     float tickRatio = Math.min(ticks, 60) / 20.0F;
/* 141 */     this.field_70159_w = (-MathHelper.func_76126_a(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 142 */     this.field_70179_y = (MathHelper.func_76134_b(this.field_70177_z / 180.0F * 3.1415927F) * MathHelper.func_76134_b(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 143 */     this.field_70181_x = (-MathHelper.func_76126_a(this.field_70125_A / 180.0F * 3.1415927F) * f);
/* 144 */     func_146035_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, tickRatio, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_70088_a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean func_70112_a(double par1) {
/* 159 */     double d1 = this.field_70121_D.func_72320_b() * 4.0D;
/* 160 */     d1 *= 64.0D;
/* 161 */     return (par1 < d1 * d1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_146035_c(double par1, double par3, double par5, float par7, float par8) {
/* 167 */     float f2 = MathHelper.func_76133_a(par1 * par1 + par3 * par3 + par5 * par5);
/* 168 */     par1 /= f2;
/* 169 */     par3 /= f2;
/* 170 */     par5 /= f2;
/* 171 */     par1 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/* 172 */     par3 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/* 173 */     par5 += this.field_70146_Z.nextGaussian() * 0.007499999832361937D * par8;
/* 174 */     par1 *= par7;
/* 175 */     par3 *= par7;
/* 176 */     par5 *= par7;
/* 177 */     this.field_70159_w = par1;
/* 178 */     this.field_70181_x = par3;
/* 179 */     this.field_70179_y = par5;
/* 180 */     float f3 = MathHelper.func_76133_a(par1 * par1 + par5 * par5);
/* 181 */     this.field_70126_B = this.field_70177_z = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/* 182 */     this.field_70127_C = this.field_70125_A = (float)(Math.atan2(par3, f3) * 180.0D / Math.PI);
/* 183 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70056_a(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 195 */     this.fishX = par1;
/* 196 */     this.fishY = par3;
/* 197 */     this.fishZ = par5;
/* 198 */     this.fishYaw = par7;
/* 199 */     this.fishPitch = par8;
/* 200 */     this.fishPosRotationIncrements = par9;
/* 201 */     this.field_70159_w = this.velocityX;
/* 202 */     this.field_70181_x = this.velocityY;
/* 203 */     this.field_70179_y = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void func_70016_h(double par1, double par3, double par5) {
/* 214 */     this.velocityX = this.field_70159_w = par1;
/* 215 */     this.velocityY = this.field_70181_x = par3;
/* 216 */     this.velocityZ = this.field_70179_y = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70071_h_() {
/* 226 */     func_70030_z();
/*     */     
/* 228 */     if (func_70032_d((Entity)this.field_146042_b) < 1.0F) {
/* 229 */       func_70106_y();
/* 230 */       if (this.field_146042_b.func_70694_bm() != null) {
/* 231 */         ItemStack itemstack = this.field_146042_b.func_70694_bm();
/* 232 */         if (itemstack.field_77990_d == null) {
/* 233 */           itemstack.field_77990_d = new NBTTagCompound();
/*     */         }
/* 235 */         itemstack.field_77990_d.func_74772_a("tickReeledIn", TFC_Time.getTotalTicks());
/*     */       } 
/*     */     } 
/*     */     
/* 239 */     if (this.fishPosRotationIncrements > 0) {
/*     */       
/* 241 */       double d0 = this.field_70165_t + (this.fishX - this.field_70165_t) / this.fishPosRotationIncrements;
/* 242 */       double d1 = this.field_70163_u + (this.fishY - this.field_70163_u) / this.fishPosRotationIncrements;
/* 243 */       double d2 = this.field_70161_v + (this.fishZ - this.field_70161_v) / this.fishPosRotationIncrements;
/* 244 */       double d3 = MathHelper.func_76138_g(this.fishYaw - this.field_70177_z);
/* 245 */       this.field_70177_z = (float)(this.field_70177_z + d3 / this.fishPosRotationIncrements);
/* 246 */       this.field_70125_A = (float)(this.field_70125_A + (this.fishPitch - this.field_70125_A) / this.fishPosRotationIncrements);
/* 247 */       this.fishPosRotationIncrements--;
/* 248 */       func_70107_b(d0, d1, d2);
/* 249 */       func_70101_b(this.field_70177_z, this.field_70125_A);
/*     */     }
/*     */     else {
/*     */       
/* 253 */       if (!this.field_70170_p.field_72995_K) {
/*     */         
/* 255 */         ItemStack itemstack = this.field_146042_b.func_71045_bC();
/*     */         
/* 257 */         if (this.field_146042_b.field_70128_L || !this.field_146042_b.func_70089_S() || itemstack == null || !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomFishingRod) || func_70068_e((Entity)this.field_146042_b) > 2500.0D) {
/*     */           
/* 259 */           func_70106_y();
/* 260 */           this.field_146042_b.field_71104_cf = null;
/*     */           
/*     */           return;
/*     */         } 
/* 264 */         if (this.field_146043_c != null) {
/*     */           
/* 266 */           if (!this.field_146043_c.field_70128_L) {
/*     */             
/* 268 */             this.field_70165_t = this.field_146043_c.field_70165_t;
/* 269 */             this.field_70163_u = this.field_146043_c.field_70121_D.field_72338_b + this.field_146043_c.field_70131_O * 0.8D;
/* 270 */             this.field_70161_v = this.field_146043_c.field_70161_v;
/*     */             return;
/*     */           } 
/* 273 */           this.field_146043_c = null;
/*     */         } 
/*     */       } 
/*     */       
/* 277 */       if (this.field_146044_a > 0)
/*     */       {
/* 279 */         this.field_146044_a--;
/*     */       }
/*     */       
/* 282 */       if (this.inGround) {
/*     */         
/* 284 */         if (this.field_70170_p.func_147439_a(this.xTile, this.yTile, this.zTile) == this.inTile) {
/*     */           
/* 286 */           this.ticksInGround++;
/* 287 */           if (this.ticksInGround == 1200) {
/* 288 */             func_70106_y();
/*     */           }
/*     */           return;
/*     */         } 
/* 292 */         this.inGround = false;
/* 293 */         this.field_70159_w *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 294 */         this.field_70181_x *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 295 */         this.field_70179_y *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 296 */         this.ticksInGround = 0;
/* 297 */         this.ticksInAir = 0;
/*     */       }
/*     */       else {
/*     */         
/* 301 */         this.ticksInAir++;
/*     */       } 
/*     */       
/* 304 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 305 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 306 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_72933_a(vec3, vec31);
/* 307 */       vec3 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 308 */       vec31 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 310 */       if (movingobjectposition != null) {
/* 311 */         vec31 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/* 313 */       Entity entity = null;
/* 314 */       List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/* 315 */       double d4 = 0.0D;
/*     */ 
/*     */       
/* 318 */       for (int j = 0; j < list.size(); j++) {
/*     */         
/* 320 */         Entity entity1 = list.get(j);
/* 321 */         if (entity1.func_70067_L() && (entity1 != this.field_146042_b || this.ticksInAir >= 5)) {
/*     */           
/* 323 */           float f = 0.3F;
/* 324 */           AxisAlignedBB axisalignedbb = entity1.field_70121_D.func_72314_b(f, f, f);
/* 325 */           MovingObjectPosition movingobjectposition1 = axisalignedbb.func_72327_a(vec3, vec31);
/* 326 */           if (movingobjectposition1 != null) {
/*     */             
/* 328 */             double d5 = vec3.func_72438_d(movingobjectposition1.field_72307_f);
/* 329 */             if (d5 < d4 || d4 == 0.0D) {
/*     */               
/* 331 */               entity = entity1;
/* 332 */               d4 = d5;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 338 */       if (entity != null) {
/* 339 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/* 341 */       if (movingobjectposition != null)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 348 */         if (movingobjectposition.field_72308_g == null)
/*     */         {
/* 350 */           this.inGround = true;
/*     */         }
/*     */       }
/*     */       
/* 354 */       if (!this.inGround) {
/*     */         
/* 356 */         func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
/* 357 */         float f1 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 358 */         this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
/*     */         
/* 360 */         for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f1) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
/*     */ 
/*     */         
/* 363 */         while (this.field_70125_A - this.field_70127_C >= 180.0F) {
/* 364 */           this.field_70127_C += 360.0F;
/*     */         }
/* 366 */         while (this.field_70177_z - this.field_70126_B < -180.0F) {
/* 367 */           this.field_70126_B -= 360.0F;
/*     */         }
/* 369 */         while (this.field_70177_z - this.field_70126_B >= 180.0F) {
/* 370 */           this.field_70126_B += 360.0F;
/*     */         }
/* 372 */         this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
/* 373 */         this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
/* 374 */         float f2 = 0.92F;
/*     */         
/* 376 */         if (this.field_70122_E || this.field_70123_F) {
/* 377 */           f2 = 0.5F;
/*     */         }
/* 379 */         byte b0 = 5;
/* 380 */         double d6 = 0.0D;
/*     */         
/* 382 */         for (int k = 0; k < b0; k++) {
/*     */           
/* 384 */           double d7 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (k + 0) / b0 - 0.125D + 0.125D;
/* 385 */           double d8 = this.field_70121_D.field_72338_b + (this.field_70121_D.field_72337_e - this.field_70121_D.field_72338_b) * (k + 1) / b0 - 0.125D + 0.125D;
/* 386 */           AxisAlignedBB axisalignedbb1 = AxisAlignedBB.func_72330_a(this.field_70121_D.field_72340_a, d7, this.field_70121_D.field_72339_c, this.field_70121_D.field_72336_d, d8, this.field_70121_D.field_72334_f);
/*     */           
/* 388 */           if (this.field_70170_p.func_72830_b(axisalignedbb1, Material.field_151586_h)) {
/* 389 */             d6 += 1.0D / b0;
/*     */           }
/*     */         } 
/* 392 */         if (this.ticksCatchable > 0) {
/* 393 */           this.field_70181_x -= (this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat() * this.field_70146_Z.nextFloat()) * 0.2D;
/*     */         }
/* 395 */         double d5 = d6 * 2.0D - 1.0D;
/* 396 */         this.field_70181_x += 0.03999999910593033D * d5;
/*     */         
/* 398 */         if (d6 > 0.0D) {
/*     */           
/* 400 */           if (this.maxDistance == -1.0D) {
/* 401 */             this.maxDistance = func_70032_d((Entity)this.field_146042_b);
/* 402 */             this.canCatchFish = true;
/*     */           } 
/* 404 */           if (this.canCatchFish && !this.field_70170_p.field_72995_K) {
/* 405 */             attemptToCatch();
/*     */           }
/* 407 */           f2 = (float)(f2 * 0.9D);
/* 408 */           this.field_70181_x *= 0.8D;
/*     */         } 
/*     */         
/* 411 */         this.field_70159_w *= f2;
/* 412 */         this.field_70181_x *= f2;
/* 413 */         this.field_70179_y *= f2;
/*     */         
/* 415 */         double distance = func_70032_d((Entity)this.field_146042_b);
/* 416 */         if (distance > this.maxDistance && this.maxDistance != -1.0D) {
/* 417 */           Vec3 distVec = Vec3.func_72443_a(this.field_70165_t - this.field_146042_b.field_70165_t, this.field_70163_u - this.field_146042_b.field_70163_u, this.field_70161_v - this.field_146042_b.field_70161_v);
/* 418 */           double distRatio = this.maxDistance / distance;
/* 419 */           this.field_70165_t = this.field_146042_b.field_70165_t + distVec.field_72450_a * distRatio;
/* 420 */           this.field_70163_u = this.field_146042_b.field_70163_u + distVec.field_72448_b * distRatio;
/* 421 */           this.field_70161_v = this.field_146042_b.field_70161_v + distVec.field_72449_c * distRatio;
/*     */         } 
/* 423 */         func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
/* 442 */     par1NBTTagCompound.func_74777_a("xTile", (short)this.xTile);
/* 443 */     par1NBTTagCompound.func_74777_a("yTile", (short)this.yTile);
/* 444 */     par1NBTTagCompound.func_74777_a("zTile", (short)this.zTile);
/* 445 */     par1NBTTagCompound.func_74774_a("inTile", (byte)Block.func_149682_b(this.inTile));
/* 446 */     par1NBTTagCompound.func_74774_a("shake", (byte)this.field_146044_a);
/* 447 */     par1NBTTagCompound.func_74774_a("inGround", (byte)(this.inGround ? 1 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
/* 456 */     this.xTile = par1NBTTagCompound.func_74765_d("xTile");
/* 457 */     this.yTile = par1NBTTagCompound.func_74765_d("yTile");
/* 458 */     this.zTile = par1NBTTagCompound.func_74765_d("zTile");
/* 459 */     this.inTile = Block.func_149729_e(par1NBTTagCompound.func_74771_c("inTile") & 0xFF);
/* 460 */     this.field_146044_a = par1NBTTagCompound.func_74771_c("shake") & 0xFF;
/* 461 */     this.inGround = (par1NBTTagCompound.func_74771_c("inGround") == 1);
/*     */   }
/*     */   
/*     */   public Vec3 applyEntityForce(Vec3 entityForce, double x, double y, double z) {
/* 465 */     Vec3 pullVec = Vec3.func_72443_a(this.pullX, this.pullY, this.pullZ);
/*     */     
/* 467 */     double force = pullVec.func_72438_d(entityForce);
/* 468 */     Vec3 netForceVec = entityForce.func_72441_c(this.pullX, this.pullY, this.pullZ);
/* 469 */     double forceRatio = force * 30.0D / netForceVec.func_72433_c();
/*     */     
/* 471 */     if (TFC_Time.getTotalTicks() % 40L == 0L) {
/* 472 */       force += 0.0D;
/*     */     }
/* 474 */     this.lineTension = (int)(this.lineTension + ((forceRatio - 31.0D > 1.0D) ? Math.sqrt(forceRatio - 31.0D) : (forceRatio - 31.0D)));
/*     */     
/* 476 */     this.lineTension = Math.max(this.lineTension, 0);
/*     */     
/* 478 */     ItemStack is = this.field_146042_b.func_70694_bm();
/* 479 */     if (is != null && is.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomFishingRod) {
/* 480 */       if (!is.func_77942_o()) {
/* 481 */         is.func_77982_d(new NBTTagCompound());
/*     */       }
/* 483 */       if (this.reelCounter > 2) {
/* 484 */         is.field_77990_d.func_74768_a("tension", (int)(forceRatio - 29.0D + Math.pow(entityForce.func_72433_c() / 0.2D, 3.0D) * 2.0D) * 100);
/*     */       } else {
/*     */         
/* 487 */         this.reelCounter++;
/*     */       } 
/*     */     } 
/* 490 */     if (forceRatio != 30.0D) {
/* 491 */       this.reelCounter = 0;
/*     */     }
/* 493 */     if (this.lineTension >= 400)
/*     */     {
/* 495 */       this.maxDistance += pullVec.func_72433_c() * 0.3D;
/*     */     }
/* 497 */     if (this.lineTension > 640.0D && !this.lineTensionSnap) {
/* 498 */       this.lineTensionSnap = true;
/* 499 */       TFC_Core.sendInfoMessage(this.field_146042_b, (IChatComponent)new ChatComponentTranslation("fishingRod.lineTension", new Object[0]));
/*     */     }
/* 501 */     else if (this.lineTension < 640.0D) {
/* 502 */       this.lineTensionSnap = false;
/*     */     } 
/* 504 */     if (this.lineTension >= 800) {
/* 505 */       this.field_146042_b.func_71045_bC().func_77972_a(20, (EntityLivingBase)this.field_146042_b);
/* 506 */       this.field_70154_o.field_70153_n = null;
/* 507 */       this.field_70154_o = null;
/*     */       
/* 509 */       TFC_Core.sendInfoMessage(this.field_146042_b, (IChatComponent)new ChatComponentTranslation("fishingRod.lineSnap", new Object[0]));
/* 510 */       func_70106_y();
/*     */     } 
/* 512 */     this.pullX = 0.0D;
/* 513 */     this.pullY = 0.0D;
/* 514 */     this.pullZ = 0.0D;
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
/* 526 */     return Vec3.func_72443_a(netForceVec.field_72450_a, (this.field_70170_p.func_147437_c((int)x, (int)y + 1, (int)z) && netForceVec.field_72448_b > 0.0D) ? 0.0D : netForceVec.field_72448_b, netForceVec.field_72449_c);
/*     */   }
/*     */   
/*     */   public Vec3 getNormalDirectionOfPlayer(double x, double y, double z) {
/* 530 */     Vec3 dirVec = Vec3.func_72443_a(this.field_146042_b.field_70165_t - x, this.field_146042_b.field_70163_u - y, this.field_146042_b.field_70161_v - z);
/* 531 */     return dirVec.func_72432_b();
/*     */   }
/*     */   
/*     */   public Vec3 getTooFarAdjustedVec(Vec3 motionVec, double x, double y, double z) {
/* 535 */     Vec3 playerMotion = Vec3.func_72443_a(this.field_146042_b.field_70159_w, this.field_146042_b.field_70181_x, this.field_146042_b.field_70179_y);
/* 536 */     double subractedRatio = Math.max(1.0D - this.maxDistance / this.field_146042_b.func_70011_f(x + playerMotion.field_72450_a, y + playerMotion.field_72448_b, z + playerMotion.field_72449_c), 0.0D);
/* 537 */     return Vec3.func_72443_a((this.field_146042_b.field_70165_t + playerMotion.field_72450_a - motionVec.field_72450_a + x) * subractedRatio, (this.field_146042_b.field_70163_u + playerMotion.field_72448_b - motionVec.field_72448_b + y) * subractedRatio, (this.field_146042_b.field_70161_v + playerMotion.field_72449_c - motionVec.field_72449_c + z) * subractedRatio);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attemptToCatch() {
/* 544 */     int fishPopulation = getAverageFishPopFromChunks();
/* 545 */     if (this.lastCheckTick == 0) {
/* 546 */       int maxValue = 72;
/* 547 */       int minValue = 0;
/* 548 */       int hour = TFC_Time.getHour();
/* 549 */       if ((hour >= 3 && hour <= 9) || (hour >= 17 && hour < 22))
/*     */       {
/* 551 */         minValue = 1;
/*     */       }
/* 553 */       if (this.field_70146_Z.nextInt(maxValue - fishPopulation) <= minValue) {
/* 554 */         func_146034_e();
/*     */       }
/* 556 */       this.lastCheckTick = 20;
/*     */     } else {
/*     */       
/* 559 */       this.lastCheckTick--;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isTooFarFromPlayer(double x, double y, double z) {
/* 564 */     return (this.field_146042_b.func_70011_f(x, y, z) > this.maxDistance);
/*     */   }
/*     */   
/*     */   public void reelInBobber(Entity entity, ItemStack itemstack) {
/* 568 */     double distance = func_70032_d(entity);
/* 569 */     if (distance < this.maxDistance) {
/* 570 */       this.maxDistance -= 0.2D;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 575 */     if (distance > 1.5D) {
/*     */       
/* 577 */       this.pullX = (entity.field_70165_t - this.field_70165_t) / distance;
/* 578 */       this.pullY = (entity.field_70163_u - this.field_70163_u) / distance;
/* 579 */       this.pullZ = (entity.field_70161_v - this.field_70161_v) / distance;
/*     */       
/* 581 */       if (this.field_70154_o == null) {
/* 582 */         this.field_70159_w += this.pullX * 0.2D;
/* 583 */         this.field_70181_x += this.pullY * 0.2D;
/* 584 */         this.field_70179_y += this.pullZ * 0.2D;
/*     */       } 
/*     */     } else {
/*     */       
/* 588 */       setDeadKill();
/* 589 */       if (itemstack.field_77990_d == null) {
/* 590 */         itemstack.field_77990_d = new NBTTagCompound();
/*     */       }
/* 592 */       itemstack.field_77990_d.func_74772_a("tickReeledIn", TFC_Time.getTotalTicks());
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getAverageFishPopFromChunks() {
/* 597 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 599 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 603 */     EntityPlayer player = this.field_146042_b;
/* 604 */     int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
/* 605 */     int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
/*     */     
/* 607 */     int chunksVisited = 0;
/* 608 */     int totalFish = TFC_Core.getCDM(this.field_70170_p).getFishPop(lastChunkX, lastChunkZ);
/* 609 */     if (totalFish > 0) {
/* 610 */       chunksVisited++;
/*     */     } else {
/*     */       
/* 613 */       return 0;
/*     */     } 
/*     */     
/* 616 */     int maxChunksVisitable = 20;
/* 617 */     for (int radius = 1; radius < 5 && chunksVisited < maxChunksVisitable; radius++) {
/* 618 */       for (int i = -radius; i <= radius; i++) {
/*     */         int k;
/* 620 */         for (k = -radius; k <= radius; k += (Math.abs(i) == radius) ? 1 : (radius * 2)) {
/*     */           
/* 622 */           int tempFish = TFC_Core.getCDM(this.field_70170_p).getFishPop(lastChunkX + i, lastChunkZ + k);
/* 623 */           if (tempFish > 0) {
/* 624 */             chunksVisited++;
/* 625 */             totalFish += tempFish;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 630 */     return totalFish / chunksVisited;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_146034_e() {
/* 637 */     if (this.field_70170_p.field_72995_K)
/*     */     {
/* 639 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 643 */     EntityPlayer player = this.field_146042_b;
/* 644 */     EntityFishTFC fish = new EntityFishTFC(this.field_70170_p);
/* 645 */     fish.func_70107_b(this.field_70165_t, this.field_70163_u - 0.3D, this.field_70161_v);
/* 646 */     this.field_70170_p.func_72838_d((Entity)fish);
/* 647 */     TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentTranslation("fishingRod.bite", new Object[0]));
/* 648 */     func_70078_a((Entity)fish);
/*     */     
/* 650 */     this.canCatchFish = false;
/*     */     
/* 652 */     int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
/* 653 */     int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
/* 654 */     int maxChunksVisitable = 20;
/* 655 */     TFC_Core.getCDM(this.field_70170_p).catchFish(lastChunkX, lastChunkZ);
/* 656 */     int chunksVisited = 1;
/* 657 */     for (int radius = 1; radius < 5 && chunksVisited < maxChunksVisitable; radius++) {
/* 658 */       for (int i = -radius; i <= radius; i++) {
/*     */         int k;
/* 660 */         for (k = -radius; k <= radius; k += (Math.abs(i) == radius) ? 1 : (radius * 2)) {
/*     */           
/* 662 */           if (TFC_Core.getCDM(this.field_70170_p).catchFish(lastChunkX + i, lastChunkZ + k)) {
/* 663 */             chunksVisited++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 668 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDeadKill() {
/* 673 */     if (this.field_70154_o != null && this.field_70154_o instanceof EntityLiving) {
/* 674 */       ((EntityLiving)this.field_70154_o).func_70606_j(1.0F);
/* 675 */       ((EntityLiving)this.field_70154_o).func_70097_a((DamageSource)new EntityDamageSource("fishing", (Entity)this.field_146042_b), 1.0F);
/* 676 */       this.field_146042_b.func_71064_a(StatList.field_75933_B, 1);
/*     */     } 
/* 678 */     this.field_70154_o = null;
/* 679 */     func_70106_y();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_70106_y() {
/* 688 */     if (this.field_70154_o != null) {
/* 689 */       EntityLiving e = (EntityLiving)this.field_70154_o;
/* 690 */       e.func_70106_y();
/*     */     } 
/* 692 */     super.func_70106_y();
/* 693 */     this.lineTension = 0;
/* 694 */     this.maxDistance = -1.0D;
/* 695 */     if (this.field_146042_b != null)
/* 696 */       this.field_146042_b.field_71104_cf = null; 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityFishHookTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */