/*    */ package com.bioxx.tfc.Core.Player;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class PlayerManagerTFC
/*    */ {
/*    */   public List<PlayerInfo> players;
/* 11 */   private static final PlayerManagerTFC INSTANCE = new PlayerManagerTFC();
/*    */ 
/*    */   
/*    */   public static final PlayerManagerTFC getInstance() {
/* 15 */     return INSTANCE;
/*    */   }
/*    */ 
/*    */   
/*    */   private PlayerManagerTFC() {
/* 20 */     this.players = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo getPlayerInfoFromPlayer(EntityPlayer player) {
/* 25 */     for (PlayerInfo pi : this.players) {
/*    */       
/* 27 */       if (pi.playerName.equals(player.func_70005_c_()) && pi.playerUUID.equals(player.func_110124_au()))
/* 28 */         return pi; 
/*    */     } 
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo getPlayerInfoFromName(String name) {
/* 35 */     for (PlayerInfo pi : this.players) {
/*    */       
/* 37 */       if (pi.playerName.equals(name))
/* 38 */         return pi; 
/*    */     } 
/* 40 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo getPlayerInfoFromUUID(String uuid) {
/* 45 */     for (PlayerInfo pi : this.players) {
/*    */       
/* 47 */       if (pi.playerUUID.toString().equals(uuid))
/* 48 */         return pi; 
/*    */     } 
/* 50 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerInfo getClientPlayer() {
/* 55 */     if (!this.players.isEmpty())
/* 56 */       return this.players.get(0); 
/* 57 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\Player\PlayerManagerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */