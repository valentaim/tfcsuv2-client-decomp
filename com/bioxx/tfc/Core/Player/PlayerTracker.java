/*     */ package com.bioxx.tfc.Core.Player;
/*     */ 
/*     */ import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.ConfigSyncPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.InitClientWorldPacket;
/*     */ import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*     */ import cpw.mods.fml.common.gameevent.PlayerEvent;
/*     */ import cpw.mods.fml.common.network.FMLNetworkEvent;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayerTracker
/*     */ {
/*     */   @SubscribeEvent
/*     */   public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
/*  29 */     (PlayerManagerTFC.getInstance()).players.add(new PlayerInfo(event.player
/*  30 */           .func_70005_c_(), event.player
/*  31 */           .func_110124_au()));
/*  32 */     TFC_ConfigFiles.reloadAll();
/*  33 */     InitClientWorldPacket initClientWorldPacket = new InitClientWorldPacket(event.player);
/*  34 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)initClientWorldPacket, (EntityPlayerMP)event.player);
/*  35 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)new ConfigSyncPacket(), (EntityPlayerMP)event.player);
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
/*     */   @SubscribeEvent
/*     */   public void onClientConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
/*  49 */     TerraFirmaCraft.proxy.onClientLogin();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onClientDisconnect(FMLNetworkEvent.ServerDisconnectionFromClientEvent event) {}
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
/*  60 */     float foodLevel = event.player.field_70170_p.field_73012_v.nextFloat() * 12.0F + 12.0F;
/*  61 */     FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(event.player);
/*  62 */     foodstats.setFoodLevel(foodLevel);
/*  63 */     TFC_Core.setPlayerFoodStats(event.player, foodstats);
/*  64 */     event.player.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(1000.0D);
/*  65 */     event.player.func_70606_j(1000.0F * (0.25F + event.player.field_70170_p.field_73012_v.nextFloat() * 0.25F));
/*     */     
/*  67 */     PlayerInfo pi = PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(event.player);
/*  68 */     if (pi.tempSkills != null) {
/*  69 */       TFC_Core.setSkillStats(event.player, pi.tempSkills);
/*     */     }
/*     */     
/*  72 */     if (pi.tempEquipment != null && event.player.field_70170_p.func_82736_K().func_82766_b("keepInventory")) {
/*     */       
/*  74 */       InventoryPlayerTFC invPlayer = (InventoryPlayerTFC)event.player.field_71071_by;
/*  75 */       invPlayer.extraEquipInventory = (ItemStack[])pi.tempEquipment.clone();
/*  76 */       pi.tempEquipment = null;
/*     */     } 
/*     */ 
/*     */     
/*  80 */     PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(event.player, 3);
/*  81 */     TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)event.player);
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
/*     */   @SubscribeEvent
/*     */   public void notifyPickup(PlayerEvent.ItemPickupEvent event) {}
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
/*     */   @SubscribeEvent
/*     */   public void onPlayerTossEvent(ItemTossEvent event) {
/* 112 */     if (event.entityItem == null)
/* 113 */       event.setCanceled(true); 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\PlayerTracker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */