/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import com.bioxx.tfc.Blocks.BlockDetailed;
/*    */ import com.bioxx.tfc.Core.Player.PlayerInfo;
/*    */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*    */ import com.bioxx.tfc.Handlers.Network.AbstractPacket;
/*    */ import com.bioxx.tfc.Handlers.Network.KeyPressPacket;
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import cpw.mods.fml.client.FMLClientHandler;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import cpw.mods.fml.common.gameevent.InputEvent;
/*    */ import net.minecraft.client.entity.EntityClientPlayerMP;
/*    */ import net.minecraft.client.settings.KeyBinding;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeyBindingHandler
/*    */ {
/* 24 */   public static KeyBinding keyToolMode = new KeyBinding("key.ToolMode", 50, "TerraFirmaCraft");
/* 25 */   public static KeyBinding keyLockTool = new KeyBinding("key.LockTool", 38, "TerraFirmaCraft");
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onKeyInput(InputEvent.KeyInputEvent event) {
/* 30 */     PlayerInfo pi = PlayerManagerTFC.getInstance().getClientPlayer();
/* 31 */     EntityClientPlayerMP player = (FMLClientHandler.instance().getClient()).field_71439_g;
/*    */     
/* 33 */     if ((FMLClientHandler.instance().getClient()).field_71415_G && 
/* 34 */       (FMLClientHandler.instance().getClient()).field_71439_g.func_71045_bC() != null && 
/* 35 */       (FMLClientHandler.instance().getClient()).field_71462_r == null)
/*    */     {
/* 37 */       if (keyToolMode.func_151468_f()) {
/*    */         
/* 39 */         if (player.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemChisel)
/*    */         {
/* 41 */           pi.switchChiselMode();
/*    */ 
/*    */           
/* 44 */           KeyPressPacket keyPressPacket = new KeyPressPacket(pi.chiselMode);
/* 45 */           TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)keyPressPacket);
/*    */         }
/* 47 */         else if (player.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomHoe)
/*    */         {
/* 49 */           pi.switchHoeMode((EntityPlayer)player);
/*    */         }
/*    */       
/* 52 */       } else if (keyLockTool.func_151468_f() && pi != null) {
/*    */         
/* 54 */         if (pi.lockX == -9999999) {
/*    */           
/* 56 */           pi.lockX = BlockDetailed.lockX;
/* 57 */           pi.lockY = BlockDetailed.lockY;
/* 58 */           pi.lockZ = BlockDetailed.lockZ;
/*    */         }
/*    */         else {
/*    */           
/* 62 */           pi.lockX = -9999999;
/* 63 */           pi.lockY = -9999999;
/* 64 */           pi.lockZ = -9999999;
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\KeyBindingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */