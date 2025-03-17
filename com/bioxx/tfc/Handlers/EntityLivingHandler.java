/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.Player.InventoryPlayerTFC;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*     */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*     */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*     */ import com.bioxx.tfc.Core.Player.SkillStats;
/*     */ import com.bioxx.tfc.Core.TFC_Achievements;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.Entities.EntityProjectileTFC;
/*     */ import com.bioxx.tfc.Food.ItemFoodTFC;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.Items.ItemQuiver;
/*     */ import com.bioxx.tfc.Items.Tools.ItemCustomBow;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.api.Food;
/*     */ import com.bioxx.tfc.api.Interfaces.IEquipable;
/*     */ import com.bioxx.tfc.api.TFCAttributes;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.util.ArrayList;
/*     */ import net.minecraft.client.entity.EntityPlayerSP;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.NBTTagCompound;
/*     */ import net.minecraft.potion.Potion;
/*     */ import net.minecraft.potion.PotionEffect;
/*     */ import net.minecraft.stats.StatBase;
/*     */ import net.minecraftforge.client.event.FOVUpdateEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDropsEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
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
/*     */ public class EntityLivingHandler
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onEntityLivingUpdate(LivingEvent.LivingUpdateEvent event) {
/*  63 */     if (event.entityLiving instanceof EntityPlayer) {
/*     */       
/*  65 */       EntityPlayer player = (EntityPlayer)event.entityLiving;
/*     */       
/*  67 */       float newMaxHealth = FoodStatsTFC.getMaxHealth(player);
/*  68 */       float oldMaxHealth = (float)player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111126_e();
/*  69 */       if (oldMaxHealth != newMaxHealth)
/*     */       {
/*  71 */         player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(newMaxHealth);
/*     */       }
/*     */       
/*  74 */       if (!player.field_70170_p.field_72995_K) {
/*     */ 
/*     */         
/*  77 */         TFC_Core.handleItemTicking(player.field_71071_by.field_70462_a, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  86 */         player.func_71024_bL().func_75122_a(20 - player.func_71024_bL().func_75116_a(), 0.0F);
/*     */         
/*  88 */         FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/*  89 */         foodstats.onUpdate(player);
/*  90 */         TFC_Core.setPlayerFoodStats(player, foodstats);
/*     */         
/*  92 */         if (foodstats.shouldSendUpdate()) {
/*     */           
/*  94 */           PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(player, 0);
/*  95 */           TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)player);
/*     */         } 
/*  97 */         if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.25F) {
/*     */           
/*  99 */           setThirsty(player, true);
/*     */         }
/* 101 */         else if (foodstats.waterLevel / foodstats.getMaxWater(player) <= 0.5F) {
/*     */           
/* 103 */           if (player.func_70051_ag()) {
/* 104 */             player.func_70031_b(false);
/*     */           }
/*     */         } else {
/*     */           
/* 108 */           setThirsty(player, false);
/*     */         } 
/* 110 */         if (foodstats.stomachLevel / foodstats.getMaxStomach(player) <= 0.25F) {
/*     */           
/* 112 */           player.func_70690_d(new PotionEffect(Potion.field_76419_f.field_76415_H, 20, 1));
/* 113 */           player.func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 20, 1));
/*     */         } 
/*     */ 
/*     */         
/* 117 */         boolean isOverburdened = false;
/* 118 */         if (!player.field_71075_bZ.field_75098_d)
/*     */         {
/* 120 */           for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {
/*     */             
/* 122 */             ItemStack is = player.field_71071_by.func_70301_a(i);
/* 123 */             if (is != null && is.func_77973_b() instanceof IEquipable) {
/*     */               
/* 125 */               isOverburdened = ((IEquipable)is.func_77973_b()).getTooHeavyToCarry(is);
/* 126 */               if (isOverburdened) {
/*     */                 break;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         }
/* 132 */         setOverburdened(player, isOverburdened);
/*     */ 
/*     */         
/* 135 */         NBTTagCompound nbt = player.getEntityData();
/* 136 */         long spawnProtectionTimer = nbt.func_74764_b("spawnProtectionTimer") ? nbt.func_74763_f("spawnProtectionTimer") : (TFC_Time.getTotalTicks() + 1000L);
/* 137 */         if (spawnProtectionTimer < TFC_Time.getTotalTicks())
/*     */         {
/*     */           
/* 140 */           for (int i = -2; i < 3; i++) {
/*     */             
/* 142 */             for (int k = -2; k < 3; k++) {
/*     */               
/* 144 */               int lastChunkX = (int)Math.floor(player.field_70165_t) >> 4;
/* 145 */               int lastChunkZ = (int)Math.floor(player.field_70161_v) >> 4;
/* 146 */               TFC_Core.getCDM(player.field_70170_p).addProtection(lastChunkX + i, lastChunkZ + k, TFCOptions.protectionGain);
/*     */             } 
/*     */           } 
/*     */           
/* 150 */           spawnProtectionTimer += 1000L;
/* 151 */           nbt.func_74772_a("spawnProtectionTimer", spawnProtectionTimer);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 156 */         PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
/* 157 */         FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
/* 158 */         foodstats.clientUpdate();
/*     */         
/* 160 */         if (pi != null && pi.playerUUID.equals(player.func_110124_au())) {
/*     */           
/* 162 */           foodstats.onUpdate(player);
/* 163 */           if (player.field_71071_by.func_70448_g() != null) {
/*     */             
/* 165 */             if (player.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) {
/*     */               
/* 167 */               pi.guishowFoodRestoreAmount = true;
/* 168 */               pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
/*     */             }
/* 170 */             else if (player.field_71071_by.func_70448_g().func_77973_b() instanceof ItemFoodTFC) {
/*     */               
/* 172 */               pi.guishowFoodRestoreAmount = true;
/* 173 */               pi.guiFoodRestoreAmount = Food.getWeight(player.field_71071_by.func_70448_g());
/*     */             } else {
/*     */               
/* 176 */               pi.guishowFoodRestoreAmount = false;
/*     */             } 
/*     */           } else {
/* 179 */             pi.guishowFoodRestoreAmount = false;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThirsty(EntityPlayer player, boolean b) {
/* 188 */     IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);
/*     */     
/* 190 */     if (iattributeinstance.func_111127_a(TFCAttributes.THIRSTY_UUID) != null)
/*     */     {
/* 192 */       iattributeinstance.func_111124_b(TFCAttributes.THIRSTY);
/*     */     }
/*     */     
/* 195 */     if (b)
/*     */     {
/* 197 */       iattributeinstance.func_111121_a(TFCAttributes.THIRSTY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOverburdened(EntityPlayer player, boolean b) {
/* 203 */     IAttributeInstance iattributeinstance = player.func_110148_a(SharedMonsterAttributes.field_111263_d);
/*     */     
/* 205 */     if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null)
/*     */     {
/* 207 */       iattributeinstance.func_111124_b(TFCAttributes.OVERBURDENED);
/*     */     }
/*     */     
/* 210 */     if (b)
/*     */     {
/* 212 */       iattributeinstance.func_111121_a(TFCAttributes.OVERBURDENED);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   @SideOnly(Side.CLIENT)
/*     */   public void handleFOV(FOVUpdateEvent event) {
/* 220 */     EntityPlayerSP entityPlayerSP = event.entity;
/*     */ 
/*     */     
/* 223 */     IAttributeInstance iattributeinstance = entityPlayerSP.func_110148_a(SharedMonsterAttributes.field_111263_d);
/* 224 */     if (iattributeinstance.func_111127_a(TFCAttributes.OVERBURDENED_UUID) != null) {
/*     */       
/* 226 */       event.newfov = 1.0F;
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 231 */     if (entityPlayerSP.func_71039_bw() && entityPlayerSP.func_71011_bu().func_77973_b() instanceof ItemCustomBow) {
/*     */       
/* 233 */       float fov = 1.0F;
/* 234 */       int duration = entityPlayerSP.func_71057_bx();
/* 235 */       float speed = ItemCustomBow.getUseSpeed((EntityPlayer)entityPlayerSP);
/* 236 */       float force = duration / speed;
/*     */       
/* 238 */       if (force > 1.0F) {
/*     */         
/* 240 */         force = 1.0F;
/*     */       }
/*     */       else {
/*     */         
/* 244 */         force *= force;
/*     */       } 
/*     */       
/* 247 */       fov *= 1.0F - force * 0.15F;
/* 248 */       event.newfov = fov;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void handleItemPickup(EntityItemPickupEvent event) {
/* 255 */     EntityPlayer player = event.entityPlayer;
/* 256 */     ItemStack item = event.item.func_92059_d();
/* 257 */     if (player.field_71071_by instanceof InventoryPlayerTFC) {
/*     */       
/* 259 */       ItemStack backItem = ((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory[0];
/*     */ 
/*     */       
/* 262 */       if (backItem == null && item.func_77973_b() instanceof IEquipable) {
/*     */         
/* 264 */         IEquipable equipment = (IEquipable)item.func_77973_b();
/* 265 */         if (equipment.getEquipType(item) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(item)))
/*     */         {
/* 267 */           PlayerInventory.putItemToExtraSlot(player, item.func_77946_l());
/*     */           
/* 269 */           item.field_77994_a = 0;
/* 270 */           event.item.func_92058_a(item);
/*     */         }
/*     */       
/*     */       }
/* 274 */       else if (backItem != null && backItem.func_77973_b() instanceof ItemQuiver) {
/*     */         
/* 276 */         ItemQuiver quiver = (ItemQuiver)backItem.func_77973_b();
/*     */ 
/*     */         
/* 279 */         if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemArrow) {
/*     */           
/* 281 */           ItemStack is = quiver.addItem(backItem, item);
/* 282 */           if (is != null) {
/* 283 */             event.item.func_92058_a(is);
/*     */           } else {
/*     */             
/* 286 */             is = item;
/* 287 */             is.field_77994_a = 0;
/* 288 */             event.item.func_92058_a(is);
/* 289 */             event.setResult(Event.Result.DENY);
/*     */           }
/*     */         
/* 292 */         } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/*     */ 
/*     */           
/* 295 */           boolean foundJav = false;
/* 296 */           for (int i = 0; i < 9; i++) {
/*     */             
/* 298 */             if (player.field_71071_by.func_70301_a(i) != null && player.field_71071_by.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin) {
/* 299 */               foundJav = true;
/*     */             }
/*     */           } 
/*     */           
/* 303 */           if (foundJav) {
/*     */             
/* 305 */             ItemStack is = quiver.addItem(backItem, item);
/* 306 */             if (is == null) {
/*     */               
/* 308 */               is = item;
/* 309 */               is.field_77994_a = 0;
/* 310 */               event.item.func_92058_a(is);
/* 311 */               event.setResult(Event.Result.DENY);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 318 */     if (item.func_77973_b() == TFCItems.looseRock) {
/* 319 */       player.func_71029_a((StatBase)TFC_Achievements.achLooseRock);
/* 320 */     } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOreSmall) {
/* 321 */       player.func_71029_a((StatBase)TFC_Achievements.achSmallOre);
/* 322 */     } else if (item.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBloom) {
/* 323 */       player.func_71029_a((StatBase)TFC_Achievements.achIronAge);
/* 324 */     } else if (item.func_77973_b().equals(TFCItems.gemDiamond)) {
/* 325 */       player.func_71029_a((StatBase)TFC_Achievements.achDiamond);
/* 326 */     } else if (item.func_77973_b().equals(TFCItems.onion) && TFCOptions.onionsAreGross) {
/* 327 */       player.func_71029_a((StatBase)TFC_Achievements.achRutabaga);
/* 328 */     } else if (item.func_77973_b().equals(TFCItems.oreChunk) && (item.func_77960_j() == 11 || item.func_77960_j() == 46 || item.func_77960_j() == 60)) {
/* 329 */       player.func_71029_a((StatBase)TFC_Achievements.achLimonite);
/*     */     } 
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onEntityDeath(LivingDeathEvent event) {
/* 335 */     EntityLivingBase entity = event.entityLiving;
/*     */     
/* 337 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 339 */       EntityPlayer player = (EntityPlayer)entity;
/*     */       
/* 341 */       if (player.getClass().getSimpleName().equals("NPCEntity"))
/*     */         return; 
/* 343 */       SkillStats skills = TFC_Core.getSkillStats(player);
/* 344 */       PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player);
/* 345 */       pi.tempSkills = skills;
/*     */ 
/*     */ 
/*     */       
/* 349 */       if (entity.field_70170_p.func_82736_K().func_82766_b("keepInventory") && player.field_71071_by instanceof InventoryPlayerTFC)
/*     */       {
/* 351 */         pi.tempEquipment = (ItemStack[])((InventoryPlayerTFC)player.field_71071_by).extraEquipInventory.clone();
/*     */       }
/*     */     } 
/*     */     
/* 355 */     if (event.entity.field_71093_bK == 1) {
/* 356 */       event.entity.func_71027_c(0);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onLivingDrop(LivingDropsEvent event) {
/* 362 */     boolean processed = false;
/* 363 */     if (!event.entity.field_70170_p.field_72995_K && event.recentlyHit && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie))
/*     */     {
/* 365 */       if (event.source.func_76364_f() instanceof EntityPlayer || event.source.func_76352_a()) {
/*     */         
/* 367 */         boolean foundFood = false;
/* 368 */         processed = true;
/* 369 */         ArrayList<EntityItem> drop = new ArrayList<>();
/* 370 */         EntityPlayer p = null;
/* 371 */         if (event.source.func_76364_f() instanceof EntityPlayer) {
/* 372 */           p = (EntityPlayer)event.source.func_76364_f();
/* 373 */         } else if (event.source.func_76364_f() instanceof EntityProjectileTFC) {
/*     */           
/* 375 */           EntityProjectileTFC proj = (EntityProjectileTFC)event.source.func_76364_f();
/* 376 */           if (proj.field_70250_c instanceof EntityPlayer)
/* 377 */             p = (EntityPlayer)proj.field_70250_c; 
/*     */         } 
/* 379 */         for (EntityItem ei : event.drops) {
/*     */           
/* 381 */           ItemStack is = ei.func_92059_d();
/* 382 */           if (is.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
/*     */             
/* 384 */             if (p == null)
/*     */               continue; 
/* 386 */             foundFood = true;
/*     */             
/* 388 */             int sweetMod = Food.getSweetMod(is);
/* 389 */             int sourMod = Food.getSourMod(is);
/* 390 */             int saltyMod = Food.getSaltyMod(is);
/* 391 */             int bitterMod = Food.getBitterMod(is);
/* 392 */             int umamiMod = Food.getSavoryMod(is);
/*     */             
/* 394 */             float oldWeight = Food.getWeight(is);
/* 395 */             Food.setWeight(is, 0.0F);
/*     */             
/* 397 */             float newWeight = oldWeight * 0.3F;
/* 398 */             if (is.func_77973_b() instanceof fof.tfcsu.Items.ItemFoodInsect) newWeight = oldWeight;
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 403 */             while (newWeight >= 0.1F) {
/*     */               
/* 405 */               float fw = Helper.roundNumber(Math.min(160.0F, newWeight), 10.0F);
/* 406 */               if (fw < 160.0F)
/* 407 */                 newWeight = 0.0F; 
/* 408 */               newWeight -= fw;
/*     */ 
/*     */               
/* 411 */               ItemStack result = ItemFoodTFC.createTag(new ItemStack(is.func_77973_b(), 1), fw);
/*     */               
/* 413 */               if (sweetMod != 0)
/* 414 */                 Food.setSweetMod(result, sweetMod); 
/* 415 */               if (sourMod != 0)
/* 416 */                 Food.setSourMod(result, sourMod); 
/* 417 */               if (saltyMod != 0)
/* 418 */                 Food.setSaltyMod(result, saltyMod); 
/* 419 */               if (bitterMod != 0)
/* 420 */                 Food.setBitterMod(result, bitterMod); 
/* 421 */               if (umamiMod != 0) {
/* 422 */                 Food.setSavoryMod(result, umamiMod);
/*     */               }
/* 424 */               drop.add(new EntityItem(event.entity.field_70170_p, event.entity.field_70165_t, event.entity.field_70163_u, event.entity.field_70161_v, result));
/*     */             } 
/*     */             
/*     */             continue;
/*     */           } 
/* 429 */           drop.add(ei);
/*     */         } 
/*     */         
/* 432 */         event.drops.clear();
/* 433 */         event.drops.addAll(drop);
/* 434 */         if (foundFood && p != null)
/*     */         {
/* 436 */           TFC_Core.getSkillStats(p).increaseSkill("skill.butchering", 1);
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 441 */     if (!processed && !(event.entity instanceof EntityPlayer) && !(event.entity instanceof net.minecraft.entity.monster.EntityZombie)) {
/*     */       
/* 443 */       ArrayList<EntityItem> drop = new ArrayList<>();
/* 444 */       for (EntityItem ei : event.drops) {
/*     */         
/* 446 */         if (!(ei.func_92059_d().func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood))
/*     */         {
/* 448 */           drop.add(ei);
/*     */         }
/*     */       } 
/* 451 */       event.drops.clear();
/* 452 */       event.drops.addAll(drop);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EntityLivingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */