/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ public class ItemRenamePacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private String name;
/*    */   
/*    */   public ItemRenamePacket() {}
/*    */   
/*    */   public ItemRenamePacket(String s) {
/* 16 */     this.name = s;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 22 */     ByteBufUtils.writeUTF8String(buffer, this.name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 28 */     this.name = ByteBufUtils.readUTF8String(buffer);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {
/* 34 */     (player.field_71071_by.func_70448_g()).field_77990_d.func_74778_a("ItemName", this.name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 40 */     (player.field_71071_by.func_70448_g()).field_77990_d.func_74778_a("ItemName", this.name);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\ItemRenamePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */