/*     */ package com.bioxx.tfc.Handlers.Network;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import cpw.mods.fml.common.FMLCommonHandler;
/*     */ import cpw.mods.fml.common.network.FMLEmbeddedChannel;
/*     */ import cpw.mods.fml.common.network.FMLOutboundHandler;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.network.internal.FMLProxyPacket;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelHandler;
/*     */ import io.netty.channel.ChannelHandler.Sharable;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import io.netty.handler.codec.MessageToMessageCodec;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.EnumMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.network.INetHandler;
/*     */ import net.minecraft.network.NetHandlerPlayServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Sharable
/*     */ public class PacketPipeline
/*     */   extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
/*     */ {
/*     */   private EnumMap<Side, FMLEmbeddedChannel> channels;
/*  41 */   private List<Class<? extends AbstractPacket>> packets = new LinkedList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isPostInitialised;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean registerPacket(Class<? extends AbstractPacket> clazz) {
/*  57 */     if (this.packets.size() > 256) {
/*     */       
/*  59 */       TerraFirmaCraft.LOG.error("Error Registering Packet, Too Big: " + clazz.getName());
/*  60 */       return false;
/*     */     } 
/*     */     
/*  63 */     if (this.packets.contains(clazz)) {
/*     */       
/*  65 */       TerraFirmaCraft.LOG.error("Error Registering Packet, Already Exists: " + clazz.getName());
/*  66 */       return false;
/*     */     } 
/*     */     
/*  69 */     if (this.isPostInitialised) {
/*     */       
/*  71 */       TerraFirmaCraft.LOG.error("Error Registering Packet, Initialization Already Completed: " + clazz.getName());
/*  72 */       return false;
/*     */     } 
/*     */     
/*  75 */     this.packets.add(clazz);
/*  76 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
/*  83 */     if (!this.packets.contains(msg.getClass()))
/*     */     {
/*  85 */       throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
/*     */     }
/*     */     
/*  88 */     Class<? extends AbstractPacket> clazz = (Class)msg.getClass();
/*  89 */     ByteBuf buffer = Unpooled.buffer();
/*  90 */     byte discriminator = (byte)this.packets.indexOf(clazz);
/*  91 */     buffer.writeByte(discriminator);
/*  92 */     msg.encodeInto(ctx, buffer);
/*  93 */     FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
/*  94 */     out.add(proxyPacket);
/*     */   }
/*     */   
/*     */   protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
/*     */     EntityPlayer player;
/*     */     EntityPlayerMP entityPlayerMP;
/*     */     INetHandler netHandler;
/* 101 */     ByteBuf payload = msg.payload();
/* 102 */     byte discriminator = payload.readByte();
/* 103 */     Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
/* 104 */     if (clazz == null)
/*     */     {
/* 106 */       throw new NullPointerException("No packet registered for discriminator: " + discriminator);
/*     */     }
/*     */     
/* 109 */     AbstractPacket pkt = clazz.newInstance();
/* 110 */     pkt.decodeInto(ctx, payload.slice());
/*     */ 
/*     */     
/* 113 */     switch (FMLCommonHandler.instance().getEffectiveSide()) {
/*     */       
/*     */       case CLIENT:
/* 116 */         player = getClientPlayer();
/* 117 */         pkt.handleClientSide(player);
/*     */         break;
/*     */       
/*     */       case SERVER:
/* 121 */         netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
/* 122 */         entityPlayerMP = ((NetHandlerPlayServer)netHandler).field_147369_b;
/* 123 */         pkt.handleServerSide((EntityPlayer)entityPlayerMP);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initalise() {
/* 133 */     this.channels = NetworkRegistry.INSTANCE.newChannel("TerraFirmaCraft", new ChannelHandler[] { (ChannelHandler)this });
/* 134 */     registerPackets();
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerPackets() {
/* 139 */     registerPacket((Class)KeyPressPacket.class);
/* 140 */     registerPacket((Class)InitClientWorldPacket.class);
/* 141 */     registerPacket((Class)DataBlockPacket.class);
/* 142 */     registerPacket((Class)ItemRenamePacket.class);
/* 143 */     registerPacket((Class)KnappingUpdatePacket.class);
/* 144 */     registerPacket((Class)PlayerUpdatePacket.class);
/* 145 */     registerPacket((Class)CreateMealPacket.class);
/* 146 */     registerPacket((Class)TestPacket.class);
/* 147 */     registerPacket((Class)ItemNBTPacket.class);
/* 148 */     registerPacket((Class)DebugModePacket.class);
/* 149 */     registerPacket((Class)ConfigSyncPacket.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void postInitialise() {
/* 157 */     if (this.isPostInitialised) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 162 */     this.isPostInitialised = true;
/* 163 */     Collections.sort(this.packets, new Comparator<Class<? extends AbstractPacket>>()
/*     */         {
/*     */ 
/*     */           
/*     */           public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
/*     */           {
/* 169 */             int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
/* 170 */             if (com == 0)
/*     */             {
/* 172 */               com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
/*     */             }
/*     */             
/* 175 */             return com;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   @SideOnly(Side.CLIENT)
/*     */   private EntityPlayer getClientPlayer() {
/* 183 */     return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToAll(AbstractPacket message) {
/* 197 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
/* 198 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendTo(AbstractPacket message, EntityPlayerMP player) {
/* 214 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
/* 215 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
/* 216 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point) {
/* 234 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
/* 235 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
/* 236 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToDimension(AbstractPacket message, int dimensionId) {
/* 252 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
/* 253 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(Integer.valueOf(dimensionId));
/* 254 */     ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToServer(AbstractPacket message) {
/* 268 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
/* 269 */     ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).writeAndFlush(message);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\PacketPipeline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */