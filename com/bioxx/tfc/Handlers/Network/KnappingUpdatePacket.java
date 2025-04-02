package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
import com.bioxx.tfc.Core.Player.PlayerManagerTFC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public class KnappingUpdatePacket
  extends AbstractPacket
{
  private byte index;

  public KnappingUpdatePacket() {}

  public KnappingUpdatePacket(int i) {
    this.index = (byte)i;
  }



  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    buffer.writeByte(this.index);
  }



  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    this.index = buffer.readByte();
  }




  public void handleClientSide(EntityPlayer player) {}



  public void handleServerSide(EntityPlayer player) {
    if (player.field_71070_bA != null && player.field_71070_bA instanceof ContainerSpecialCrafting) {

      ((ContainerSpecialCrafting)player.field_71070_bA).craftMatrix.func_70299_a(this.index, (PlayerManagerTFC.getInstance().getPlayerInfoFromPlayer(player)).specialCraftingTypeAlternate);
      ((ContainerSpecialCrafting)player.field_71070_bA).func_75130_a((IInventory)((ContainerSpecialCrafting)player.field_71070_bA).craftMatrix);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\KnappingUpdatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */