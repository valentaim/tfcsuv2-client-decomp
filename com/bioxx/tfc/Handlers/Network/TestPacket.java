/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class TestPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private String msg;
/*    */   
/*    */   public TestPacket() {}
/*    */   
/*    */   public TestPacket(String s) {
/* 18 */     this.msg = s;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 24 */     ByteBufUtils.writeUTF8String(buffer, this.msg);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 31 */     this.msg = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {
/* 37 */     TerraFirmaCraft.LOG.info("++++++++++++Client: " + this.msg);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 43 */     TerraFirmaCraft.LOG.info("++++++++++++Server: " + this.msg);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\TestPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */