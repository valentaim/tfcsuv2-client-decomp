/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.TileEntities.TEFoodPrep;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CreateMealPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private byte flag;
/*    */   private int x;
/*    */   private int y;
/*    */   private int z;
/*    */   
/*    */   public CreateMealPacket() {}
/*    */   
/*    */   public CreateMealPacket(int f, TEFoodPrep te) {
/* 22 */     this.flag = (byte)f;
/* 23 */     this.x = te.field_145851_c;
/* 24 */     this.y = te.field_145848_d;
/* 25 */     this.z = te.field_145849_e;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 31 */     buffer.writeByte(this.flag);
/* 32 */     if (this.flag == 0) {
/*    */       
/* 34 */       buffer.writeInt(this.x);
/* 35 */       buffer.writeInt(this.y);
/* 36 */       buffer.writeInt(this.z);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 43 */     this.flag = buffer.readByte();
/* 44 */     if (this.flag == 0) {
/*    */       
/* 46 */       this.x = buffer.readInt();
/* 47 */       this.y = buffer.readInt();
/* 48 */       this.z = buffer.readInt();
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {
/* 61 */     if (this.flag == 0) {
/*    */       
/* 63 */       TEFoodPrep te = (TEFoodPrep)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
/* 64 */       TFC_Core.getSkillStats(player).increaseSkill("skill.cooking", 1);
/* 65 */       te.actionCreate(player);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\CreateMealPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */