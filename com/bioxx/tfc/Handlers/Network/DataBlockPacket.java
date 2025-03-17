/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import com.bioxx.tfc.TileEntities.NetworkTileEntity;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ 
/*    */ 
/*    */ public class DataBlockPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   private NBTTagCompound nbtData;
/*    */   
/*    */   public DataBlockPacket() {}
/*    */   
/*    */   public DataBlockPacket(int x, int y, int z, NBTTagCompound data) {
/* 23 */     this.x = x;
/* 24 */     this.y = y;
/* 25 */     this.z = z;
/* 26 */     this.nbtData = data;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 32 */     PacketBuffer pb = new PacketBuffer(buffer);
/* 33 */     pb.writeInt(this.x);
/* 34 */     pb.writeShort(this.y);
/* 35 */     pb.writeInt(this.z);
/*    */     
/*    */     try {
/* 38 */       pb.func_150786_a(this.nbtData);
/*    */     }
/* 40 */     catch (Exception e) {
/*    */       
/* 42 */       TerraFirmaCraft.LOG.catching(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 49 */     PacketBuffer pb = new PacketBuffer(buffer);
/* 50 */     this.x = pb.readInt();
/* 51 */     this.y = pb.readShort();
/* 52 */     this.z = pb.readInt();
/*    */     
/*    */     try {
/* 55 */       this.nbtData = pb.func_150793_b();
/*    */     }
/* 57 */     catch (Exception e) {
/*    */       
/* 59 */       TerraFirmaCraft.LOG.catching(e);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {
/* 67 */     if (player.field_70170_p.func_147438_o(this.x, this.y, this.z) instanceof NetworkTileEntity) {
/*    */       
/* 69 */       NetworkTileEntity te = (NetworkTileEntity)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
/* 70 */       if (te != null) {
/*    */         
/* 72 */         te.entityplayer = player;
/* 73 */         te.handleDataPacket(this.nbtData);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 81 */     NetworkTileEntity te = (NetworkTileEntity)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
/* 82 */     if (te != null) {
/*    */       
/* 84 */       te.entityplayer = player;
/* 85 */       te.handleDataPacket(this.nbtData);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\DataBlockPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */