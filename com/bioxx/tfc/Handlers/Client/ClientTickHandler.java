/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.TickEvent;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClientTickHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onClientPlayerTick(TickEvent.PlayerTickEvent event) {
/* 23 */     if (event.phase == TickEvent.Phase.END) {
/*    */ 
/*    */       
/* 26 */       EntityPlayer player = event.player;
/* 27 */       World world = player.field_70170_p;
/*    */ 
/*    */       
/* 30 */       TFC_Time.updateTime(world);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\ClientTickHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */