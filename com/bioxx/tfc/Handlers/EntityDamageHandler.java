/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Entities.EntityJavelin;
/*     */ import com.bioxx.tfc.Items.ItemTFCArmor;
/*     */ import com.bioxx.tfc.Items.Tools.ItemWeapon;
/*     */ import com.bioxx.tfc.api.Crafting.AnvilManager;
/*     */ import com.bioxx.tfc.api.Enums.EnumDamageType;
/*     */ import com.bioxx.tfc.api.Events.EntityArmorCalcEvent;
/*     */ import com.bioxx.tfc.api.Interfaces.ICausesDamage;
/*     */ import com.bioxx.tfc.api.Interfaces.IInnateArmor;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import fof.tfcsu.Enchantment.EnchantmentWeapon;
/*     */ import java.lang.reflect.Field;
/*     */ import java.util.Random;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.IEntityMultiPart;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.boss.EntityDragonPart;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.AchievementList;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraft.stats.StatList;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.EntityDamageSourceIndirect;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.living.LivingHurtEvent;
/*     */ import net.minecraftforge.event.entity.player.AttackEntityEvent;
/*     */ import net.smart.moving.IEntityPlayerMP;
/*     */ import net.smart.moving.playerapi.SmartMoving;
/*     */ import terramisc.items.tools.ItemCustomArmor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDamageHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onEntityHurt(LivingHurtEvent event) {
/*  51 */     EntityLivingBase entity = event.entityLiving;
/*  52 */     if (entity instanceof EntityPlayer) {
/*  53 */       float curMaxHealth = (float)((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*  54 */       float newMaxHealth = FoodStatsTFC.getMaxHealth((EntityPlayer)entity);
/*  55 */       float h = ((EntityPlayer)entity).func_110143_aJ();
/*  56 */       if (newMaxHealth != curMaxHealth) ((EntityPlayer)entity).func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth); 
/*  57 */       if (newMaxHealth < h) ((EntityPlayer)entity).func_70606_j(newMaxHealth);
/*     */     
/*     */     } 
/*  60 */     if (event.source == DamageSource.field_76370_b) { event.ammount = 50.0F; }
/*  61 */     else if (event.source == DamageSource.field_76379_h)
/*  62 */     { float healthMod = TFC_Core.getEntityMaxHealth(entity) / 1000.0F;
/*  63 */       event.ammount *= 80.0F * healthMod;
/*     */       
/*  65 */       if (event.entityLiving instanceof net.minecraft.entity.passive.EntityAnimal) event.ammount /= 10.0F;  }
/*  66 */     else if (event.source == DamageSource.field_76369_e) { event.ammount = 50.0F; }
/*  67 */     else if (event.source != DamageSource.field_76367_g)
/*  68 */     { if (event.source == DamageSource.field_76371_c) { event.ammount = 100.0F; }
/*  69 */       else if (event.source == DamageSource.field_76368_d) { event.ammount = 100.0F; }
/*  70 */       else if (event.source == DamageSource.field_82729_p) { event.ammount = 100.0F; }
/*  71 */       else if (event.source.func_94541_c())
/*  72 */       { event.ammount *= 30.0F;
/*     */         
/*  74 */         if (entity instanceof EntityPlayer) {
/*  75 */           IEntityPlayerMP sm_player = SmartMoving.getServerPlayerBase((EntityPlayer)event.entity);
/*  76 */           if ((sm_player.getMoving()).isCrawling) {
/*  77 */             event.ammount /= 2.0F;
/*     */           }
/*     */         }
/*     */          }
/*     */       
/*  82 */       else if (event.source == DamageSource.field_76376_m && entity.func_110143_aJ() > 25.0F) { event.ammount = 25.0F; }
/*  83 */       else if ("player".equals(event.source.field_76373_n) || "mob".equals(event.source.field_76373_n) || "arrow".equals(event.source.field_76373_n))
/*     */       
/*  85 */       { if (event.source.func_76346_g() != null && event.source.func_76346_g().getClass().getSimpleName().equals("NPCEntity")) event.ammount = getWeaponDamage(event.source.func_76346_g());
/*     */         
/*  87 */         double attacker_height = -1.0D;
/*  88 */         double target_height = -1.0D;
/*     */         
/*  90 */         if (!"arrow".equals(event.source.field_76373_n) && "mob".equals(event.source.field_76373_n) && event.entity instanceof EntityPlayer) {
/*  91 */           attacker_height = ((EntityLiving)event.source.func_76346_g()).field_70121_D.field_72337_e - ((EntityLiving)event.source.func_76346_g()).field_70121_D.field_72338_b;
/*  92 */           target_height = event.entityLiving.field_70121_D.field_72337_e - event.entityLiving.field_70121_D.field_72338_b;
/*     */         } 
/*  94 */         event.ammount = applyArmorCalculations(entity, event.source, event.ammount, attacker_height, target_height);
/*  95 */         if ("arrow".equals(event.source.field_76373_n)) {
/*  96 */           Entity e = ((EntityDamageSourceIndirect)event.source).func_76364_f();
/*  97 */           if (e instanceof EntityJavelin) {
/*  98 */             ((EntityJavelin)e).setDamageTaken((short)(((EntityJavelin)e).damageTaken + 10));
/*  99 */             if (((EntityJavelin)e).damageTaken >= ((EntityJavelin)e).pickupItem.func_77612_l()) e.func_70106_y();
/*     */           
/*     */           } 
/*     */         }  }
/*     */        }
/*     */   
/*     */   }
/*     */   
/*     */   public float getWeaponDamage(Entity e) {
/* 108 */     ItemStack is = ((EntityPlayer)e).field_71071_by.func_70448_g();
/* 109 */     float out = 0.0F; try {
/*     */       float base_damage;
/* 111 */       Field f = ItemWeapon.class.getDeclaredField("weaponBaseDamage");
/* 112 */       f.setAccessible(true);
/* 113 */       ItemWeapon iw = (ItemWeapon)is.func_77973_b();
/* 114 */       float damage = ((Float)f.get(iw)).floatValue();
/*     */       
/* 116 */       int level = EnchantmentWeapon.getEnchantmentLevel(is);
/* 117 */       if (level != 0) { base_damage = damage / (1 + level / 100); }
/* 118 */       else { base_damage = damage; }
/* 119 */        float end_damage = base_damage * (100 + level * 10) / 100.0F;
/* 120 */       out = (float)Math.floor((end_damage + end_damage * AnvilManager.getDamageBuff(is)));
/* 121 */     } catch (Exception exception) {}
/* 122 */     return (out < 50.0F) ? 50.0F : out;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int applyArmorCalculations(EntityLivingBase entity, DamageSource source, float originalDamage, double ah, double th) {
/* 127 */     ItemStack[] armor = entity.func_70035_c();
/* 128 */     int pierceRating = 0;
/* 129 */     int slashRating = 0;
/* 130 */     int crushRating = 0;
/*     */     
/* 132 */     EntityArmorCalcEvent eventPre = new EntityArmorCalcEvent(entity, originalDamage, EntityArmorCalcEvent.EventType.PRE);
/* 133 */     MinecraftForge.EVENT_BUS.post((Event)eventPre);
/* 134 */     float damage = eventPre.incomingDamage;
/*     */     
/* 136 */     if (!source.func_76363_c() && armor != null) {
/*     */       
/* 138 */       int location = getRandomSlot(entity.func_70681_au());
/* 139 */       if (ah != -1.0D && th != -1.0D) if (ah >= th) { location = getRandomSlotUpper(entity.func_70681_au()); }
/* 140 */         else if (th * 2.0D / 3.0D > ah) { location = getRandomSlotLower(entity.func_70681_au()); }
/*     */       
/*     */       
/* 143 */       if (armor[location] != null && (armor[location].func_77973_b() instanceof ItemTFCArmor || armor[location].func_77973_b() instanceof ItemCustomArmor)) {
/* 144 */         if (armor[location].func_77973_b() instanceof ItemTFCArmor) {
/* 145 */           pierceRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getPiercingAR();
/* 146 */           slashRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getSlashingAR();
/* 147 */           crushRating = ((ItemTFCArmor)armor[location].func_77973_b()).armorTypeTFC.getCrushingAR();
/* 148 */         } else if (armor[location].func_77973_b() instanceof ItemCustomArmor) {
/* 149 */           pierceRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getPiercingAR();
/* 150 */           slashRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getSlashingAR();
/* 151 */           crushRating = ((ItemCustomArmor)armor[location].func_77973_b()).ArmorType.getCrushingAR();
/*     */         } 
/* 153 */         if (entity instanceof IInnateArmor) {
/* 154 */           pierceRating += ((IInnateArmor)entity).getPierceArmor();
/* 155 */           slashRating += ((IInnateArmor)entity).getSlashArmor();
/* 156 */           crushRating += ((IInnateArmor)entity).getCrushArmor();
/*     */         } 
/*     */ 
/*     */         
/* 160 */         float pierceMult = getDamageReduction(pierceRating);
/* 161 */         float slashMult = getDamageReduction(slashRating);
/* 162 */         float crushMult = getDamageReduction(crushRating);
/*     */ 
/*     */         
/* 165 */         damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);
/*     */ 
/*     */         
/* 168 */         if (damageItem(armor[location], (int)(processArmorDamage(armor[location], damage) / 2.5F), entity)) armor[location] = null; 
/* 169 */       } else if (armor[location] == null || (armor[location] != null && !(armor[location].func_77973_b() instanceof ItemTFCArmor))) {
/* 170 */         if (entity instanceof IInnateArmor) {
/* 171 */           pierceRating += ((IInnateArmor)entity).getPierceArmor();
/* 172 */           slashRating += ((IInnateArmor)entity).getSlashArmor();
/* 173 */           crushRating += ((IInnateArmor)entity).getCrushArmor();
/*     */         } 
/*     */         
/* 176 */         float pierceMult = getDamageReduction(pierceRating);
/* 177 */         float slashMult = getDamageReduction(slashRating);
/* 178 */         float crushMult = getDamageReduction(crushRating);
/*     */         
/* 180 */         damage = processDamageSource(source, damage, pierceMult, slashMult, crushMult);
/*     */ 
/*     */ 
/*     */         
/* 184 */         if (location == 3) { damage *= 1.75F; }
/* 185 */         else if (location == 0) { entity.func_70690_d(new PotionEffect(Potion.field_76421_d.func_76396_c(), 100, 1)); }
/*     */       
/*     */       } 
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
/* 199 */       EntityArmorCalcEvent eventPost = new EntityArmorCalcEvent(entity, damage, EntityArmorCalcEvent.EventType.POST);
/* 200 */       MinecraftForge.EVENT_BUS.post((Event)eventPost);
/*     */ 
/*     */       
/* 203 */       float hasHealth = entity.func_110143_aJ();
/* 204 */       entity.func_70606_j(entity.func_110143_aJ() - eventPost.incomingDamage);
/* 205 */       entity.func_110142_aN().func_94547_a(source, hasHealth, eventPost.incomingDamage);
/*     */     } 
/* 207 */     return 0;
/*     */   }
/*     */   
/*     */   private float processDamageSource(DamageSource source, float damage, float pierceMult, float slashMult, float crushMult) {
/* 211 */     EnumDamageType damageType = getDamageType(source);
/*     */     
/* 213 */     switch (damageType) {
/*     */       case PIERCING:
/* 215 */         damage *= pierceMult;
/*     */         break;
/*     */       case SLASHING:
/* 218 */         damage *= slashMult;
/*     */         break;
/*     */       case CRUSHING:
/* 221 */         damage *= crushMult;
/*     */         break;
/*     */       case GENERIC:
/* 224 */         damage = (float)(damage * (((crushMult + slashMult + pierceMult) / 3.0F) - 0.25D)); break;
/*     */     } 
/* 226 */     return Math.max(0.0F, damage);
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumDamageType getDamageType(DamageSource source) {
/* 231 */     if (source.func_76364_f() instanceof EntityPlayer) {
/* 232 */       EntityPlayer player = (EntityPlayer)source.func_76364_f();
/* 233 */       if (player.func_71045_bC() != null && player
/* 234 */         .func_71045_bC().func_77973_b() instanceof ICausesDamage) return ((ICausesDamage)player.func_71045_bC().func_77973_b()).getDamageType();
/*     */     
/*     */     } 
/* 237 */     if (source.func_76364_f() instanceof EntityLiving) {
/* 238 */       EntityLiving el = (EntityLiving)source.func_76364_f();
/* 239 */       if (el.func_70694_bm() != null && el.func_70694_bm().func_77973_b() instanceof ICausesDamage) return ((ICausesDamage)el.func_70694_bm().func_77973_b()).getDamageType();
/*     */     
/*     */     } 
/* 242 */     if (source.func_76364_f() instanceof ICausesDamage) return ((ICausesDamage)source.func_76364_f()).getDamageType();
/*     */     
/* 244 */     return EnumDamageType.GENERIC;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getRandomSlot(Random rand) {
/* 250 */     int chance = rand.nextInt(100);
/* 251 */     if (chance < 30) return 3; 
/* 252 */     if (chance < 45) return 0; 
/* 253 */     if (chance < 75) return 2; 
/* 254 */     return 1;
/*     */   }
/*     */   
/*     */   private int getRandomSlotUpper(Random rand) {
/* 258 */     int chance = rand.nextInt(100);
/* 259 */     if (chance < 10) return 0; 
/* 260 */     if (chance < 30) return 1; 
/* 261 */     if (chance < 60) return 2; 
/* 262 */     return 3;
/*     */   }
/*     */   
/*     */   private int getRandomSlotLower(Random rand) {
/* 266 */     int chance = rand.nextInt(100);
/* 267 */     if (chance < 10) return 3; 
/* 268 */     if (chance < 30) return 2; 
/* 269 */     if (chance < 60) return 1; 
/* 270 */     return 0;
/*     */   }
/*     */   
/*     */   private float processArmorDamage(ItemStack armor, float baseDamage) {
/* 274 */     if (armor.func_77942_o()) {
/* 275 */       NBTTagCompound nbt = armor.func_77978_p();
/* 276 */       if (nbt.func_74764_b("armorReductionBuff")) {
/* 277 */         float reductBuff = nbt.func_74771_c("armorReductionBuff") / 100.0F;
/* 278 */         return baseDamage - baseDamage * reductBuff;
/*     */       } 
/*     */     } 
/* 281 */     return baseDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getDamageReduction(int armorRating) {
/* 291 */     if (armorRating == -1000) armorRating = -999; 
/* 292 */     return 1000.0F / (1000.0F + armorRating);
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onAttackEntity(AttackEntityEvent event) {
/* 297 */     if (event.entityLiving.field_70170_p.field_72995_K)
/*     */       return; 
/* 299 */     EntityLivingBase attacker = event.entityLiving;
/* 300 */     EntityPlayer player = event.entityPlayer;
/* 301 */     Entity target = event.target;
/* 302 */     ItemStack stack = attacker.func_71124_b(0);
/* 303 */     if (stack != null && stack.func_77973_b().onLeftClickEntity(stack, player, target))
/*     */       return; 
/* 305 */     if (target.func_70075_an() && 
/* 306 */       !target.func_85031_j(target)) {
/* 307 */       float damageAmount = 50.0F;
/* 308 */       if (stack != null) {
/* 309 */         damageAmount = (float)player.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
/*     */         
/* 311 */         if (damageAmount == 1.0F) {
/* 312 */           damageAmount = 50.0F;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 318 */       if (player.func_70644_a(Potion.field_76420_g)) damageAmount += (3 << player.func_70660_b(Potion.field_76420_g).func_76458_c());
/*     */       
/* 320 */       if (player.func_70644_a(Potion.field_76437_t)) damageAmount -= (2 << player.func_70660_b(Potion.field_76437_t).func_76458_c());
/*     */       
/* 322 */       int knockback = 0;
/* 323 */       float enchantmentDamage = 0.0F;
/*     */       
/* 325 */       if (target instanceof EntityLiving) {
/* 326 */         enchantmentDamage = EnchantmentHelper.func_77512_a((EntityLivingBase)player, (EntityLivingBase)target);
/* 327 */         knockback += EnchantmentHelper.func_77507_b((EntityLivingBase)player, (EntityLivingBase)target);
/*     */       } 
/*     */       
/* 330 */       if (player.func_70051_ag()) knockback++;
/*     */       
/* 332 */       if (damageAmount > 0.0F || enchantmentDamage > 0.0F) {
/* 333 */         boolean criticalHit = (player.field_70143_R > 0.0F && !player.field_70122_E && !player.func_70617_f_() && !player.func_70090_H() && !player.func_70644_a(Potion.field_76440_q) && player.field_70154_o == null && target instanceof EntityLiving);
/*     */ 
/*     */         
/* 336 */         if (criticalHit && damageAmount > 0.0F) damageAmount += event.entity.field_70170_p.field_73012_v.nextInt((int)(damageAmount / 2.0F + 2.0F));
/*     */         
/* 338 */         damageAmount += enchantmentDamage;
/* 339 */         boolean onFire = false;
/* 340 */         int fireAspect = EnchantmentHelper.func_90036_a((EntityLivingBase)player);
/*     */         
/* 342 */         if (target instanceof EntityLiving && fireAspect > 0 && !target.func_70027_ad()) {
/* 343 */           onFire = true;
/* 344 */           target.func_70015_d(1);
/*     */         } 
/*     */         
/* 347 */         boolean entityAttacked = target.func_70097_a(DamageSource.func_76365_a(player), damageAmount);
/*     */         
/* 349 */         if (entityAttacked) {
/* 350 */           if (knockback > 0) {
/* 351 */             target.func_70024_g((-MathHelper.func_76126_a(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (
/* 352 */                 MathHelper.func_76134_b(player.field_70177_z * 3.1415927F / 180.0F) * knockback * 0.5F));
/* 353 */             player.field_70159_w *= 0.6D;
/* 354 */             player.field_70179_y *= 0.6D;
/* 355 */             player.func_70031_b(false);
/*     */           } 
/*     */           
/* 358 */           if (criticalHit) player.func_71009_b(target);
/*     */           
/* 360 */           if (enchantmentDamage > 0.0F) player.func_71047_c(target);
/*     */           
/* 362 */           if (damageAmount >= 18.0F) player.func_71029_a((StatBase)AchievementList.field_75999_E);
/*     */           
/* 364 */           player.func_130011_c(target);
/*     */           
/* 366 */           if (target instanceof EntityLiving) target.func_70097_a(DamageSource.func_92087_a((Entity)attacker), damageAmount);
/*     */         
/*     */         } 
/* 369 */         ItemStack itemstack = player.func_71045_bC();
/* 370 */         Object object = target;
/*     */         
/* 372 */         if (target instanceof EntityDragonPart) {
/* 373 */           IEntityMultiPart ientitymultipart = ((EntityDragonPart)target).field_70259_a;
/* 374 */           if (ientitymultipart instanceof EntityLiving) object = ientitymultipart;
/*     */         
/*     */         } 
/* 377 */         if (itemstack != null && object instanceof EntityLiving) {
/* 378 */           itemstack.func_77961_a((EntityLivingBase)object, player);
/* 379 */           if (itemstack.field_77994_a <= 0) player.func_71028_bD();
/*     */         
/*     */         } 
/* 382 */         if (target instanceof EntityLivingBase) {
/* 383 */           player.func_71064_a(StatList.field_75951_w, Math.round(damageAmount * 10.0F));
/* 384 */           if (fireAspect > 0 && entityAttacked) { target.func_70015_d(fireAspect * 4); }
/* 385 */           else if (onFire) { target.func_70066_B(); }
/*     */         
/*     */         } 
/* 388 */         player.func_71020_j(0.3F);
/*     */       } 
/*     */     } 
/*     */     
/* 392 */     event.setCanceled(true);
/*     */   }
/*     */   
/*     */   public boolean damageItem(ItemStack item, int p_77972_1_, EntityLivingBase p_77972_2_) {
/* 396 */     if ((!(p_77972_2_ instanceof EntityPlayer) || !((EntityPlayer)p_77972_2_).field_71075_bZ.field_75098_d) && 
/* 397 */       item.func_77984_f() && 
/* 398 */       item.func_96631_a(p_77972_1_, p_77972_2_.func_70681_au())) {
/* 399 */       p_77972_2_.func_70669_a(item);
/* 400 */       item.field_77994_a--;
/*     */       
/* 402 */       if (p_77972_2_ instanceof EntityPlayer) {
/* 403 */         EntityPlayer entityplayer = (EntityPlayer)p_77972_2_;
/* 404 */         entityplayer.func_71064_a(StatList.field_75930_F[Item.func_150891_b(item.func_77973_b())], 1);
/*     */         
/* 406 */         if (item.field_77994_a == 0)
/* 407 */           if (item.func_77973_b() instanceof net.minecraft.item.ItemBow) { entityplayer.func_71028_bD(); }
/* 408 */           else if (item.func_77973_b() instanceof net.minecraft.item.ItemArmor) { return true; }
/*     */            
/*     */       } 
/* 411 */       if (item.field_77994_a < 0) item.field_77994_a = 0; 
/* 412 */       item.func_77964_b(0);
/*     */     } 
/*     */ 
/*     */     
/* 416 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EntityDamageHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */