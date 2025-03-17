/*     */ package com.bioxx.tfc.Handlers;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Core.TFC_Time;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCItems;
/*     */ import com.bioxx.tfc.api.Util.Helper;
/*     */ import cpw.mods.fml.common.eventhandler.Event;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityLivingBase;
/*     */ import net.minecraft.entity.item.EntityItem;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.init.Items;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.MovingObjectPosition;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerInteractHandler
/*     */ {
/*  32 */   private Map<UUID, Long> lastDrink = new HashMap<>();
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerInteract(PlayerInteractEvent event) {
/*  37 */     if (event.entityPlayer.field_70170_p.field_72995_K) {
/*     */       return;
/*     */     }
/*  40 */     ItemStack itemInHand = event.entityPlayer.func_71045_bC();
/*     */     
/*  42 */     boolean validAction = (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR);
/*     */     
/*  44 */     if (validAction && event.getResult() != Event.Result.DENY && itemInHand == null)
/*     */     {
/*  46 */       handleDrinkingWater(event.entityPlayer);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void handleDrinkingWater(EntityPlayer entityPlayer) {
/*  52 */     Long lastCheck = this.lastDrink.get(entityPlayer.func_110124_au());
/*  53 */     if (lastCheck != null && lastCheck.longValue() + 20L > TFC_Time.getTotalTicks())
/*     */       return; 
/*  55 */     this.lastDrink.put(entityPlayer.func_110124_au(), Long.valueOf(TFC_Time.getTotalTicks()));
/*  56 */     World world = entityPlayer.field_70170_p;
/*  57 */     MovingObjectPosition mop = Helper.getMovingObjectPositionFromPlayer(world, (EntityLivingBase)entityPlayer, true);
/*  58 */     FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(entityPlayer);
/*  59 */     Boolean canDrink = Boolean.valueOf(((fs.getMaxWater(entityPlayer) - 500) > fs.waterLevel));
/*     */ 
/*     */     
/*  62 */     if (mop != null && canDrink.booleanValue() && mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK)
/*     */     {
/*  64 */       if (TFC_Core.isFreshWater(world.func_147439_a(mop.field_72311_b, mop.field_72312_c, mop.field_72309_d))) {
/*     */ 
/*     */         
/*  67 */         fs.restoreWater(entityPlayer, 2000);
/*     */ 
/*     */         
/*  70 */         world.func_72956_a((Entity)entityPlayer, "random.drink", 0.2F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);
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
/*     */   @SubscribeEvent
/*     */   public void onItemPickup(EntityItemPickupEvent event) {
/*  83 */     EntityItem item = event.item;
/*  84 */     ItemStack is = item.func_92059_d();
/*  85 */     EntityPlayer player = event.entityPlayer;
/*     */     
/*  87 */     if (is.func_77973_b() == Items.field_151055_y) {
/*     */       
/*  89 */       int count = is.field_77994_a;
/*  90 */       item.field_145804_b = 100;
/*  91 */       item.func_70106_y();
/*  92 */       item.func_82142_c(true);
/*  93 */       Random rand = player.field_70170_p.field_73012_v;
/*  94 */       player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/*  95 */       ItemStack tfcSticks = new ItemStack(TFCItems.stick, count);
/*  96 */       player.field_71071_by.func_70441_a(tfcSticks);
/*     */     }
/*  98 */     else if (is.func_77973_b() == Item.func_150898_a(Blocks.field_150344_f) && is.func_77960_j() == 0) {
/*     */       
/* 100 */       int count = is.field_77994_a;
/* 101 */       item.field_145804_b = 100;
/* 102 */       item.func_70106_y();
/* 103 */       item.func_82142_c(true);
/* 104 */       Random rand = player.field_70170_p.field_73012_v;
/* 105 */       player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 106 */       ItemStack tfcPlanks = new ItemStack(TFCBlocks.planks, count);
/* 107 */       player.field_71071_by.func_70441_a(tfcPlanks);
/*     */     }
/* 109 */     else if (is.func_77973_b() == Item.func_150898_a(Blocks.field_150428_aP)) {
/*     */       
/* 111 */       int count = is.field_77994_a;
/* 112 */       item.field_145804_b = 100;
/* 113 */       item.func_70106_y();
/* 114 */       item.func_82142_c(true);
/* 115 */       Random rand = player.field_70170_p.field_73012_v;
/* 116 */       player.field_70170_p.func_72956_a((Entity)player, "random.pop", 0.2F, ((rand.nextFloat() - rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 117 */       ItemStack jackOLanternTFC = new ItemStack(TFCBlocks.litPumpkin, count);
/* 118 */       player.field_71071_by.func_70441_a(jackOLanternTFC);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\PlayerInteractHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */