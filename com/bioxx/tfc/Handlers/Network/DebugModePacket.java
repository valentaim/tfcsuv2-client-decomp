package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.api.TFCOptions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;


public class DebugModePacket
  extends AbstractPacket
{
  private boolean debugMode;

  public DebugModePacket() {}

  public DebugModePacket(EntityPlayer p) {
    this.debugMode = TFCOptions.enableDebugMode;
  }



  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeBoolean(this.debugMode);
  }



  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    this.debugMode = buffer.readBoolean();
  }



  public void handleClientSide(EntityPlayer player) {
    TFCOptions.enableDebugMode = this.debugMode;
  }

  public void handleServerSide(EntityPlayer player) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\DebugModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */