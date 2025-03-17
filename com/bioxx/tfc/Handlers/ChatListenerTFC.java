/*    */ package com.bioxx.tfc.Handlers;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import java.util.Random;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.util.ChatComponentTranslation;
/*    */ import net.minecraftforge.event.ServerChatEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatListenerTFC
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onServerChatEvent(ServerChatEvent event) {
/* 19 */     String msg = event.message;
/* 20 */     long soberTime = (TFC_Core.getPlayerFoodStats((EntityPlayer)event.player)).soberTime;
/* 21 */     if (soberTime > TFC_Time.getTotalTicks()) {
/*    */ 
/*    */       
/* 24 */       String s = "аеиоуыврсфлзкбнмАЕИОУЫВРСФЛЗКБНМ";
/* 25 */       Random rand = new Random();
/* 26 */       soberTime -= TFC_Time.getTotalTicks();
/* 27 */       for (int i = 0; i < event.message.length() - 1; i++) {
/*    */         
/* 29 */         String start = event.message.substring(0, i);
/* 30 */         String s2 = event.message.substring(i, i + 1);
/* 31 */         String end = event.message.substring(i + 1);
/*    */         
/* 33 */         if (event.message.charAt(0) != '/') {
/*    */           
/* 35 */           int chance = Math.max(1, 11 - (int)(soberTime / 1000L));
/* 36 */           if (s.indexOf(s2) != -1 && rand.nextInt(chance) == 0) {
/*    */             
/* 38 */             int n = rand.nextInt(2);
/* 39 */             int m = 0;
/* 40 */             msg = start + s2;
/* 41 */             for (int j = 0; j < n; j++)
/*    */             {
/* 43 */               msg = msg + (end.substring(0, 1).toUpperCase().equals(end.substring(0, 1)) ? s2 : s2.toLowerCase());
/*    */             }
/* 45 */             if (("С".equals(s2) || "с".equals(s2)) && !"С".equals(end.substring(0, 1)) && !"с".equals(end.substring(0, 1))) {
/*    */ 
/*    */               
/* 48 */               msg = msg + (s2.toUpperCase().equals(s2) ? (end.substring(0, 1).toUpperCase().equals(end.substring(0, 1)) ? "Х" : "х".toLowerCase()) : "х");
/*    */               
/* 50 */               m++;
/*    */             } 
/* 52 */             msg = msg + end;
/* 53 */             i += m;
/*    */           } 
/*    */         } 
/*    */       } 
/*    */ 
/*    */ 
/*    */       
/* 60 */       event.component = new ChatComponentTranslation("chat.type.text", new Object[] { event.username, msg });
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\ChatListenerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */