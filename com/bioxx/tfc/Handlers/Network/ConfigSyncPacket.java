/*    */ package com.bioxx.tfc.Handlers.Network;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Config.SyncingOption;
/*    */ import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
/*    */ import com.bioxx.tfc.TerraFirmaCraft;
/*    */ import cpw.mods.fml.common.network.ByteBufUtils;
/*    */ import io.netty.buffer.ByteBuf;
/*    */ import io.netty.channel.ChannelHandlerContext;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.entity.player.EntityPlayer;
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
/*    */ 
/*    */ 
/*    */ public class ConfigSyncPacket
/*    */   extends AbstractPacket
/*    */ {
/*    */   private Map<String, Boolean> map;
/*    */   
/*    */   public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 31 */     buffer.writeInt(TFC_ConfigFiles.SYNCING_OPTION_MAP.size());
/* 32 */     for (SyncingOption option : TFC_ConfigFiles.SYNCING_OPTION_MAP.values()) {
/*    */       
/* 34 */       ByteBufUtils.writeUTF8String(buffer, option.name);
/* 35 */       buffer.writeBoolean(option.inConfig());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
/* 42 */     int size = buffer.readInt();
/* 43 */     this.map = new HashMap<>(size);
/* 44 */     for (int i = 0; i < size; i++)
/*    */     {
/* 46 */       this.map.put(ByteBufUtils.readUTF8String(buffer), Boolean.valueOf(buffer.readBoolean()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void handleClientSide(EntityPlayer player) {
/* 53 */     if (this.map == null) throw new IllegalStateException("Packet was not decoded"); 
/* 54 */     TerraFirmaCraft.LOG.info("Applying server TFCCrafting settings");
/*    */     
/*    */     try {
/* 57 */       for (Map.Entry<String, Boolean> entry : this.map.entrySet())
/*    */       {
/* 59 */         ((SyncingOption)TFC_ConfigFiles.SYNCING_OPTION_MAP.get(entry.getKey())).apply(((Boolean)entry.getValue()).booleanValue());
/*    */       }
/*    */     }
/* 62 */     catch (IllegalAccessException e) {
/*    */       
/* 64 */       TerraFirmaCraft.LOG.fatal("Error loading TFCCrafting settings from server!", e);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void handleServerSide(EntityPlayer player) {}
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\ConfigSyncPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */