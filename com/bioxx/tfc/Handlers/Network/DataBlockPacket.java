package com.bioxx.tfc.Handlers.Network;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.NetworkTileEntity;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;


public class DataBlockPacket
  extends AbstractPacket
{
  private int x;
  private int y;
  private int z;
  private NBTTagCompound nbtData;

  public DataBlockPacket() {}

  public DataBlockPacket(int x, int y, int z, NBTTagCompound data) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.nbtData = data;
  }



  public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    PacketBuffer pb = new PacketBuffer(buffer);
    pb.writeInt(this.x);
    pb.writeShort(this.y);
    pb.writeInt(this.z);

    try {
      pb.func_150786_a(this.nbtData);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }



  public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
    PacketBuffer pb = new PacketBuffer(buffer);
    this.x = pb.readInt();
    this.y = pb.readShort();
    this.z = pb.readInt();

    try {
      this.nbtData = pb.func_150793_b();
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }




  public void handleClientSide(EntityPlayer player) {
    if (player.field_70170_p.func_147438_o(this.x, this.y, this.z) instanceof NetworkTileEntity) {

      NetworkTileEntity te = (NetworkTileEntity)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
      if (te != null) {

        te.entityplayer = player;
        te.handleDataPacket(this.nbtData);
      }
    }
  }



  public void handleServerSide(EntityPlayer player) {
    NetworkTileEntity te = (NetworkTileEntity)player.field_70170_p.func_147438_o(this.x, this.y, this.z);
    if (te != null) {

      te.entityplayer = player;
      te.handleDataPacket(this.nbtData);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Network\DataBlockPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */