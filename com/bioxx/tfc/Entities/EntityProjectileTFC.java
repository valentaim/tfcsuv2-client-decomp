/*     */ package com.bioxx.tfc.Entities;
/*     */ 
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.material.Material;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.entity.projectile.EntityArrow;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.network.Packet;
/*     */ import net.minecraft.network.play.server.S2BPacketChangeGameState;
/*     */ import net.minecraft.util.AxisAlignedBB;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.util.Vec3;
/*     */ import net.minecraft.world.IBlockAccess;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ 
/*     */ public class EntityProjectileTFC
/*     */   extends EntityArrow
/*     */   implements ICausesDamage
/*     */ {
/*     */   public short damageTaken;
/*  38 */   public Item pickupItem = TFCItems.arrow; public float damageBuff; public float duraBuff; private int field_145791_d; private int field_145792_e; private int field_145789_f;
/*     */   private Block field_145790_g;
/*     */   private int inData;
/*  41 */   public String blacksmith = null; private boolean inGround; private int ticksInGround; private int ticksInAir; private double damage;
/*     */   private int knockbackStrength;
/*     */   
/*     */   public EntityProjectileTFC(World par1World) {
/*  45 */     super(par1World);
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
/* 160 */     this.field_145791_d = -1;
/* 161 */     this.field_145792_e = -1;
/* 162 */     this.field_145789_f = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 168 */     this.damage = 2.0D; } public EntityProjectileTFC(World world, double i, double j, double k) { super(world, i, j, k); this.field_145791_d = -1; this.field_145792_e = -1; this.field_145789_f = -1; this.damage = 2.0D; } public EntityProjectileTFC(World world, EntityLivingBase shooter, EntityLivingBase victim, float force, float forceVariation) { super(world, shooter, victim, force, forceVariation); this.field_145791_d = -1; this.field_145792_e = -1; this.field_145789_f = -1; this.damage = 2.0D; } public EntityProjectileTFC(World world, EntityLivingBase par2EntityLivingBase, float force) { super(world, par2EntityLivingBase, force); this.field_145791_d = -1; this.field_145792_e = -1; this.field_145789_f = -1; this.damage = 2.0D; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageTaken(short d) {
/*     */     this.damageTaken = d;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate0() {
/* 180 */     if (this.field_70127_C == 0.0F && this.field_70126_B == 0.0F) {
/*     */       
/* 182 */       float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 183 */       this.field_70126_B = this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
/* 184 */       this.field_70127_C = this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f) * 180.0D / Math.PI);
/*     */     } 
/*     */     
/* 187 */     Block block = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/*     */     
/* 189 */     if (block.func_149688_o() != Material.field_151579_a) {
/*     */       
/* 191 */       block.func_149719_a((IBlockAccess)this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
/* 192 */       AxisAlignedBB axisalignedbb = block.func_149668_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f);
/*     */       
/* 194 */       if (axisalignedbb != null && axisalignedbb.func_72318_a(Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v)))
/*     */       {
/* 196 */         this.inGround = true;
/*     */       }
/*     */     } 
/*     */     
/* 200 */     if (this.field_70249_b > 0)
/*     */     {
/* 202 */       this.field_70249_b--;
/*     */     }
/*     */     
/* 205 */     if (this.inGround) {
/*     */       
/* 207 */       int j = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/*     */       
/* 209 */       if (block == this.field_145790_g && j == this.inData)
/*     */       {
/* 211 */         this.ticksInGround++;
/*     */         
/* 213 */         if (this.ticksInGround == 1200)
/*     */         {
/* 215 */           func_70106_y();
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 220 */         this.inGround = false;
/* 221 */         this.field_70159_w *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 222 */         this.field_70181_x *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 223 */         this.field_70179_y *= (this.field_70146_Z.nextFloat() * 0.2F);
/* 224 */         this.ticksInGround = 0;
/* 225 */         this.ticksInAir = 0;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 230 */       this.ticksInAir++;
/* 231 */       Vec3 vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 232 */       Vec3 vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/* 233 */       MovingObjectPosition movingobjectposition = this.field_70170_p.func_147447_a(vec31, vec3, false, true, false);
/* 234 */       vec31 = Vec3.func_72443_a(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 235 */       vec3 = Vec3.func_72443_a(this.field_70165_t + this.field_70159_w, this.field_70163_u + this.field_70181_x, this.field_70161_v + this.field_70179_y);
/*     */       
/* 237 */       if (movingobjectposition != null)
/*     */       {
/* 239 */         vec3 = Vec3.func_72443_a(movingobjectposition.field_72307_f.field_72450_a, movingobjectposition.field_72307_f.field_72448_b, movingobjectposition.field_72307_f.field_72449_c);
/*     */       }
/*     */       
/* 242 */       Entity entity = null;
/* 243 */       List<Entity> list = this.field_70170_p.func_72839_b((Entity)this, this.field_70121_D.func_72321_a(this.field_70159_w, this.field_70181_x, this.field_70179_y).func_72314_b(1.0D, 1.0D, 1.0D));
/* 244 */       double d0 = 0.0D;
/*     */       
/*     */       int i;
/*     */       
/* 248 */       for (i = 0; i < list.size(); i++) {
/*     */         
/* 250 */         Entity entity1 = list.get(i);
/* 251 */         if (!(entity1 instanceof cuchaz.ships.EntityShip) && 
/* 252 */           entity1.func_70067_L() && (entity1 != this.field_70250_c || this.ticksInAir >= 5)) {
/*     */           
/* 254 */           float f = 0.3F;
/* 255 */           AxisAlignedBB axisalignedbb1 = entity1.field_70121_D.func_72314_b(f, f, f);
/* 256 */           MovingObjectPosition movingobjectposition1 = axisalignedbb1.func_72327_a(vec31, vec3);
/*     */           
/* 258 */           if (movingobjectposition1 != null) {
/*     */             
/* 260 */             double d1 = vec31.func_72438_d(movingobjectposition1.field_72307_f);
/*     */             
/* 262 */             if (d1 < d0 || d0 == 0.0D) {
/*     */               
/* 264 */               entity = entity1;
/* 265 */               d0 = d1;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 271 */       if (entity != null)
/*     */       {
/* 273 */         movingobjectposition = new MovingObjectPosition(entity);
/*     */       }
/*     */       
/* 276 */       if (movingobjectposition != null && movingobjectposition.field_72308_g != null && movingobjectposition.field_72308_g instanceof EntityPlayer) {
/*     */         
/* 278 */         EntityPlayer entityplayer = (EntityPlayer)movingobjectposition.field_72308_g;
/*     */         
/* 280 */         if (entityplayer.field_71075_bZ.field_75102_a || (this.field_70250_c instanceof EntityPlayer && !((EntityPlayer)this.field_70250_c).func_96122_a(entityplayer)))
/*     */         {
/* 282 */           movingobjectposition = null;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 289 */       if (movingobjectposition != null)
/*     */       {
/* 291 */         if (movingobjectposition.field_72308_g != null) {
/*     */           
/* 293 */           float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 294 */           int k = MathHelper.func_76143_f(f * this.damage);
/*     */           
/* 296 */           if (func_70241_g())
/*     */           {
/* 298 */             k += this.field_70146_Z.nextInt(k / 2 + 2);
/*     */           }
/*     */           
/* 301 */           DamageSource damagesource = null;
/*     */           
/* 303 */           if (this.field_70250_c == null) {
/*     */             
/* 305 */             damagesource = DamageSource.func_76353_a(this, (Entity)this);
/*     */           }
/*     */           else {
/*     */             
/* 309 */             damagesource = DamageSource.func_76353_a(this, this.field_70250_c);
/*     */           } 
/*     */           
/* 312 */           if (func_70027_ad() && !(movingobjectposition.field_72308_g instanceof net.minecraft.entity.monster.EntityEnderman))
/*     */           {
/* 314 */             movingobjectposition.field_72308_g.func_70015_d(5);
/*     */           }
/*     */           
/* 317 */           if (movingobjectposition.field_72308_g.func_70097_a(damagesource, k))
/*     */           {
/* 319 */             if (movingobjectposition.field_72308_g instanceof EntityLivingBase) {
/*     */               
/* 321 */               EntityLivingBase entitylivingbase = (EntityLivingBase)movingobjectposition.field_72308_g;
/*     */               
/* 323 */               if (!this.field_70170_p.field_72995_K)
/*     */               {
/* 325 */                 entitylivingbase.func_85034_r(entitylivingbase.func_85035_bI() + 1);
/*     */               }
/*     */               
/* 328 */               if (this.knockbackStrength > 0) {
/*     */                 
/* 330 */                 float f4 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/*     */                 
/* 332 */                 if (f4 > 0.0F)
/*     */                 {
/* 334 */                   movingobjectposition.field_72308_g.func_70024_g(this.field_70159_w * this.knockbackStrength * 0.6000000238418579D / f4, 0.1D, this.field_70179_y * this.knockbackStrength * 0.6000000238418579D / f4);
/*     */                 }
/*     */               } 
/*     */               
/* 338 */               if (this.field_70250_c != null && this.field_70250_c instanceof EntityLivingBase) {
/*     */                 
/* 340 */                 EnchantmentHelper.func_151384_a(entitylivingbase, this.field_70250_c);
/* 341 */                 EnchantmentHelper.func_151385_b((EntityLivingBase)this.field_70250_c, (Entity)entitylivingbase);
/*     */               } 
/*     */               
/* 344 */               if (this.field_70250_c != null && movingobjectposition.field_72308_g != this.field_70250_c && movingobjectposition.field_72308_g instanceof EntityPlayer && this.field_70250_c instanceof EntityPlayerMP)
/*     */               {
/* 346 */                 ((EntityPlayerMP)this.field_70250_c).field_71135_a.func_147359_a((Packet)new S2BPacketChangeGameState(6, 0.0F));
/*     */               }
/*     */             } 
/*     */             
/* 350 */             func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/*     */             
/* 352 */             if (!(movingobjectposition.field_72308_g instanceof net.minecraft.entity.monster.EntityEnderman))
/*     */             {
/* 354 */               func_70106_y();
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 359 */             this.field_70159_w *= -0.10000000149011612D;
/* 360 */             this.field_70181_x *= -0.10000000149011612D;
/* 361 */             this.field_70179_y *= -0.10000000149011612D;
/* 362 */             this.field_70177_z += 180.0F;
/* 363 */             this.field_70126_B += 180.0F;
/* 364 */             this.ticksInAir = 0;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 369 */           this.field_145791_d = movingobjectposition.field_72311_b;
/* 370 */           this.field_145792_e = movingobjectposition.field_72312_c;
/* 371 */           this.field_145789_f = movingobjectposition.field_72309_d;
/* 372 */           this.field_145790_g = this.field_70170_p.func_147439_a(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/* 373 */           this.inData = this.field_70170_p.func_72805_g(this.field_145791_d, this.field_145792_e, this.field_145789_f);
/* 374 */           this.field_70159_w = (float)(movingobjectposition.field_72307_f.field_72450_a - this.field_70165_t);
/* 375 */           this.field_70181_x = (float)(movingobjectposition.field_72307_f.field_72448_b - this.field_70163_u);
/* 376 */           this.field_70179_y = (float)(movingobjectposition.field_72307_f.field_72449_c - this.field_70161_v);
/* 377 */           float f = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70181_x * this.field_70181_x + this.field_70179_y * this.field_70179_y);
/* 378 */           this.field_70165_t -= this.field_70159_w / f * 0.05000000074505806D;
/* 379 */           this.field_70163_u -= this.field_70181_x / f * 0.05000000074505806D;
/* 380 */           this.field_70161_v -= this.field_70179_y / f * 0.05000000074505806D;
/* 381 */           func_85030_a("random.bowhit", 1.0F, 1.2F / (this.field_70146_Z.nextFloat() * 0.2F + 0.9F));
/* 382 */           this.inGround = true;
/* 383 */           this.field_70249_b = 7;
/* 384 */           func_70243_d(false);
/*     */           
/* 386 */           if (this.field_145790_g.func_149688_o() != Material.field_151579_a)
/*     */           {
/* 388 */             this.field_145790_g.func_149670_a(this.field_70170_p, this.field_145791_d, this.field_145792_e, this.field_145789_f, (Entity)this);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 393 */       if (func_70241_g())
/*     */       {
/* 395 */         for (i = 0; i < 4; i++)
/*     */         {
/* 397 */           this.field_70170_p.func_72869_a("crit", this.field_70165_t + this.field_70159_w * i / 4.0D, this.field_70163_u + this.field_70181_x * i / 4.0D, this.field_70161_v + this.field_70179_y * i / 4.0D, -this.field_70159_w, -this.field_70181_x + 0.2D, -this.field_70179_y);
/*     */         }
/*     */       }
/*     */       
/* 401 */       this.field_70165_t += this.field_70159_w;
/* 402 */       this.field_70163_u += this.field_70181_x;
/* 403 */       this.field_70161_v += this.field_70179_y;
/* 404 */       float f2 = MathHelper.func_76133_a(this.field_70159_w * this.field_70159_w + this.field_70179_y * this.field_70179_y);
/* 405 */       this.field_70177_z = (float)(Math.atan2(this.field_70159_w, this.field_70179_y) * 180.0D / Math.PI);
/*     */       
/* 407 */       for (this.field_70125_A = (float)(Math.atan2(this.field_70181_x, f2) * 180.0D / Math.PI); this.field_70125_A - this.field_70127_C < -180.0F; this.field_70127_C -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 412 */       while (this.field_70125_A - this.field_70127_C >= 180.0F)
/*     */       {
/* 414 */         this.field_70127_C += 360.0F;
/*     */       }
/*     */       
/* 417 */       while (this.field_70177_z - this.field_70126_B < -180.0F)
/*     */       {
/* 419 */         this.field_70126_B -= 360.0F;
/*     */       }
/*     */       
/* 422 */       while (this.field_70177_z - this.field_70126_B >= 180.0F)
/*     */       {
/* 424 */         this.field_70126_B += 360.0F;
/*     */       }
/*     */       
/* 427 */       this.field_70125_A = this.field_70127_C + (this.field_70125_A - this.field_70127_C) * 0.2F;
/* 428 */       this.field_70177_z = this.field_70126_B + (this.field_70177_z - this.field_70126_B) * 0.2F;
/* 429 */       float f3 = 0.99F;
/* 430 */       float f1 = 0.05F;
/*     */       
/* 432 */       if (func_70090_H()) {
/*     */         
/* 434 */         for (int l = 0; l < 4; l++) {
/*     */           
/* 436 */           float f4 = 0.25F;
/* 437 */           this.field_70170_p.func_72869_a("bubble", this.field_70165_t - this.field_70159_w * f4, this.field_70163_u - this.field_70181_x * f4, this.field_70161_v - this.field_70179_y * f4, this.field_70159_w, this.field_70181_x, this.field_70179_y);
/*     */         } 
/*     */         
/* 440 */         f3 = 0.8F;
/*     */       } 
/*     */       
/* 443 */       if (func_70026_G())
/*     */       {
/* 445 */         func_70066_B();
/*     */       }
/*     */       
/* 448 */       this.field_70159_w *= f3;
/* 449 */       this.field_70181_x *= f3;
/* 450 */       this.field_70179_y *= f3;
/* 451 */       this.field_70181_x -= f1;
/* 452 */       func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
/* 453 */       func_145775_I();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPickupItem(Item item) {
/*     */     this.pickupItem = item;
/*     */   }
/*     */   
/*     */   public void func_70100_b_(EntityPlayer player) {
/*     */     if (!this.field_70170_p.field_72995_K) {
/*     */       NBTTagCompound nbt = new NBTTagCompound();
/*     */       func_70109_d(nbt);
/*     */       boolean inground = (nbt.func_74764_b("inGround") && nbt.func_74771_c("inGround") == 1);
/*     */       if (inground) {
/*     */         ItemStack is = new ItemStack(this.pickupItem, 1, this.damageTaken);
/*     */         if (this.duraBuff != 0.0F)
/*     */           AnvilManager.setDurabilityBuff(is, this.duraBuff); 
/*     */         if (this.damageBuff != 0.0F)
/*     */           AnvilManager.setDamageBuff(is, this.damageBuff); 
/*     */         if (this.blacksmith != null) {
/*     */           nbt = AnvilManager.getCraftTag(is);
/*     */           nbt.func_74778_a("blacksmith", this.blacksmith);
/*     */           AnvilManager.setCraftTag(is, nbt);
/*     */         } 
/*     */         EntityItem ei = new EntityItem(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, is);
/*     */         if (this.field_70251_a == 1) {
/*     */           EntityItemPickupEvent event = new EntityItemPickupEvent(player, ei);
/*     */           if (MinecraftForge.EVENT_BUS.post((Event)event))
/*     */             return; 
/*     */         } 
/*     */         ItemStack itemstack = ei.func_92059_d();
/*     */         boolean flag = (this.field_70251_a == 1 || (this.field_70251_a == 2 && player.field_71075_bZ.field_75098_d));
/*     */         if (itemstack.field_77994_a <= 0) {
/*     */           flag = true;
/*     */         } else if (this.field_70251_a == 1 && !player.field_71071_by.func_70441_a(itemstack)) {
/*     */           flag = false;
/*     */         } 
/*     */         if (flag) {
/*     */           func_85030_a("random.pop", 0.2F, ((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*     */           player.func_71001_a((Entity)this, 1);
/*     */           func_70106_y();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70071_h_() {
/*     */     if (this.field_70170_p.field_72995_K) {
/*     */       onUpdate0();
/*     */     } else {
/*     */       super.func_70071_h_();
/*     */     } 
/*     */     if (!this.field_70170_p.field_72995_K && this.field_70128_L) {
/*     */       int maxDamage = (this.pickupItem instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) ? this.pickupItem.func_77612_l() : 1;
/*     */       if (this.field_70173_aa < 1200 && maxDamage > this.damageTaken)
/*     */         this.field_70128_L = false; 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void func_70037_a(NBTTagCompound nbt) {
/*     */     super.func_70037_a(nbt);
/*     */     this.damageTaken = nbt.func_74765_d("damageTaken");
/*     */   }
/*     */   
/*     */   public void func_70014_b(NBTTagCompound nbt) {
/*     */     super.func_70014_b(nbt);
/*     */     nbt.func_74777_a("damageTaken", this.damageTaken);
/*     */   }
/*     */   
/*     */   public EnumDamageType getDamageType() {
/*     */     return EnumDamageType.PIERCING;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Entities\EntityProjectileTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */