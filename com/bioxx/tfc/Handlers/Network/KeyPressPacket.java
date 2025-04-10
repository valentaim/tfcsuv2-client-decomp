/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class KeyPressPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private int type;
/*    */   private static long keyTimer;
/*    */   
/*    */   public KeyPressPacket() {}
/*    */   
/*    */   public KeyPressPacket(byte t) {
/* 19 */     this.type = t;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 25 */     buffer.writeInt(this.type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 31 */     this.type = buffer.readInt();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 42 */     if (keyTimer + 1L < TFC_Time.getTotalTicks()) {
/*    */       
/* 44 */       keyTimer = TFC_Time.getTotalTicks();
/*    */       
/* 46 */       PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player).setChiselMode((byte)this.type);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\KeyPressPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */