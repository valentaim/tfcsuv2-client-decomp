package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.Core.Config.SyncingOption;
import com.bioxx.tfc.Core.Config.TFC_ConfigFiles;
import com.bioxx.tfc.TerraFirmaCraft;
import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;













public class ConfigSyncPacket
  extends AbstractPacket
{
  private Map<String, Boolean> map;

  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeInt(TFC_ConfigFiles.SYNCING_OPTION_MAP.size());
    for (SyncingOption option : TFC_ConfigFiles.SYNCING_OPTION_MAP.values()) {

      ByteBufUtils.writeUTF8String(buffer, option.name);
      buffer.writeBoolean(option.inConfig());
    }
  }



  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    int size = buffer.readInt();
    this.map = new HashMap<>(size);
    for (int i = 0; i < size; i++)
    {
      this.map.put(ByteBufUtils.readUTF8String(buffer), Boolean.valueOf(buffer.readBoolean()));
    }
  }



  public void handleClientSide(EntityPlayer player) {
    if (this.map == null) throw new IllegalStateException("Packet was not decoded");
    TerraFirmaCraft.LOG.info("Applying server TFCCrafting settings");

    try {
      for (Map.Entry<String, Boolean> entry : this.map.entrySet())
      {
        ((SyncingOption)TFC_ConfigFiles.SYNCING_OPTION_MAP.get(entry.getKey())).apply(((Boolean)entry.getValue()).booleanValue());
      }
    }
    catch (IllegalAccessException e) {

      TerraFirmaCraft.LOG.fatal("Error loading TFCCrafting settings from server!", e);
    }
  }

  public void handleServerSide(EntityPlayer player) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\ConfigSyncPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */