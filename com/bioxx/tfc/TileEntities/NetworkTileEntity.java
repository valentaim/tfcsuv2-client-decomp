package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.DataBlockPacket;
import com.bioxx.tfc.TerraFirmaCraft;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public abstract class NetworkTileEntity
  extends TileEntity
{
  public boolean shouldSendInitData = true;
  public EntityPlayer entityplayer;
  protected int broadcastRange = 256;


  public abstract void handleInitPacket(NBTTagCompound paramNBTTagCompound);

  public void handleDataPacket(NBTTagCompound nbt) {}

  public void createDataNBT(NBTTagCompound nbt) {}

  public abstract void createInitNBT(NBTTagCompound paramNBTTagCompound);

  public DataBlockPacket createDataPacket() {
    return createDataPacket(createDataNBT());
  }


  public DataBlockPacket createDataPacket(NBTTagCompound nbt) {
    return new DataBlockPacket(this.field_145851_c, this.field_145848_d, this.field_145849_e, nbt);
  }


  private NBTTagCompound createDataNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    createDataNBT(nbt);
    return nbt;
  }


  private NBTTagCompound createInitNBT() {
    NBTTagCompound nbt = new NBTTagCompound();
    createInitNBT(nbt);
    return nbt;
  }



  @SideOnly(Side.CLIENT)
  public double func_145833_n() {
    return 1024.0D;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB getRenderBoundingBox() {
    return AxisAlignedBB.func_72330_a(this.field_145851_c, this.field_145848_d, this.field_145849_e, (this.field_145851_c + 1), (this.field_145848_d + 1), (this.field_145849_e + 1));
  }



  public Packet func_145844_m() {
    if (this.shouldSendInitData)
      return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, createInitNBT());
    return null;
  }



  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    handleInitPacket(pkt.func_148857_g());
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void broadcastPacketInRange() {
    int dim = this.field_145850_b.field_73011_w.field_76574_g;
    if (this.field_145850_b.field_72995_K) {
      TerraFirmaCraft.PACKET_PIPELINE.sendToServer((AbstractPacket)createDataPacket());
    } else {
      TerraFirmaCraft.PACKET_PIPELINE.sendToAllAround((AbstractPacket)createDataPacket(), new NetworkRegistry.TargetPoint(dim, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.broadcastRange));
    }
  }


  public void broadcastPacketInRange(AbstractPacket packet) {
    if (this.field_145850_b.field_72995_K) {
      TerraFirmaCraft.PACKET_PIPELINE.sendToServer(packet);
    } else {
      TerraFirmaCraft.PACKET_PIPELINE.sendToAllAround(packet, new NetworkRegistry.TargetPoint(this.field_145850_b.field_73011_w.field_76574_g, this.field_145851_c, this.field_145848_d, this.field_145849_e, this.broadcastRange));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\NetworkTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */