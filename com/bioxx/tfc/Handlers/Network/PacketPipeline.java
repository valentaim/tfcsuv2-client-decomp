package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.TerraFirmaCraft;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;









@Sharable
public class PacketPipeline
  extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
{
  private EnumMap<Side, FMLEmbeddedChannel> channels;
  private List<Class<? extends AbstractPacket>> packets = new LinkedList<>();






  private boolean isPostInitialised;







  public boolean registerPacket(Class<? extends AbstractPacket> clazz) {
    if (this.packets.size() > 256) {

      TerraFirmaCraft.LOG.error("Error Registering Packet, Too Big: " + clazz.getName());
      return false;
    }

    if (this.packets.contains(clazz)) {

      TerraFirmaCraft.LOG.error("Error Registering Packet, Already Exists: " + clazz.getName());
      return false;
    }

    if (this.isPostInitialised) {

      TerraFirmaCraft.LOG.error("Error Registering Packet, Initialization Already Completed: " + clazz.getName());
      return false;
    }

    this.packets.add(clazz);
    return true;
  }




  protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception {
    if (!this.packets.contains(msg.getClass()))
    {
      throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
    }

    Class<? extends AbstractPacket> clazz = (Class)msg.getClass();
    ByteBuf buffer = Unpooled.buffer();
    byte discriminator = (byte)this.packets.indexOf(clazz);
    buffer.writeByte(discriminator);
    msg.encodeInto(ctx, buffer);
    FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), (String)ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
    out.add(proxyPacket);
  }

  protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception {
    EntityPlayer player;
    EntityPlayerMP entityPlayerMP;
    INetHandler netHandler;
    ByteBuf payload = msg.payload();
    byte discriminator = payload.readByte();
    Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
    if (clazz == null)
    {
      throw new NullPointerException("No packet registered for discriminator: " + discriminator);
    }

    AbstractPacket pkt = clazz.newInstance();
    pkt.decodeInto(ctx, payload.slice());


    switch (FMLCommonHandler.instance().getEffectiveSide()) {

      case CLIENT:
        player = getClientPlayer();
        pkt.handleClientSide(player);
        break;

      case SERVER:
        netHandler = (INetHandler)ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
        entityPlayerMP = ((NetHandlerPlayServer)netHandler).field_147369_b;
        pkt.handleServerSide((EntityPlayer)entityPlayerMP);
        break;
    }
  }





  public void initalise() {
    this.channels = NetworkRegistry.INSTANCE.newChannel("TerraFirmaCraft", new ChannelHandler[] { (ChannelHandler)this });
    registerPackets();
  }


  public void registerPackets() {
    registerPacket((Class)KeyPressPacket.class);
    registerPacket((Class)InitClientWorldPacket.class);
    registerPacket((Class)DataBlockPacket.class);
    registerPacket((Class)ItemRenamePacket.class);
    registerPacket((Class)KnappingUpdatePacket.class);
    registerPacket((Class)PlayerUpdatePacket.class);
    registerPacket((Class)CreateMealPacket.class);
    registerPacket((Class)TestPacket.class);
    registerPacket((Class)ItemNBTPacket.class);
    registerPacket((Class)DebugModePacket.class);
    registerPacket((Class)ConfigSyncPacket.class);
  }





  public void postInitialise() {
    if (this.isPostInitialised) {
      return;
    }


    this.isPostInitialised = true;
    Collections.sort(this.packets, new Comparator<Class<? extends AbstractPacket>>()
        {


          public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
          {
            int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
            if (com == 0)
            {
              com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
            }

            return com;
          }
        });
  }


  @SideOnly(Side.CLIENT)
  private EntityPlayer getClientPlayer() {
    return (EntityPlayer)(Minecraft.func_71410_x()).field_71439_g;
  }











  public void sendToAll(AbstractPacket message) {
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
  }













  public void sendTo(AbstractPacket message, EntityPlayerMP player) {
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
  }















  public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point) {
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
  }













  public void sendToDimension(AbstractPacket message, int dimensionId) {
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(Integer.valueOf(dimensionId));
    ((FMLEmbeddedChannel)this.channels.get(Side.SERVER)).writeAndFlush(message);
  }











  public void sendToServer(AbstractPacket message) {
    ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
    ((FMLEmbeddedChannel)this.channels.get(Side.CLIENT)).writeAndFlush(message);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\PacketPipeline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */