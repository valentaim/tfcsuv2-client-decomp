package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TEOre
  extends NetworkTileEntity {
  public int baseBlockID = -1000;
  public int baseBlockMeta = -1000;

  public byte extraData;

  public TEOre() {
    this.shouldSendInitData = true;
  }



  public boolean canUpdate() {
    return false;
  }


  public void setVisible() {
    if ((this.extraData & 0x8) == 0) {
      this.extraData = (byte)(this.extraData + 8);
    }
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.baseBlockID = nbt.func_74762_e("baseBlockID");
    this.baseBlockMeta = nbt.func_74762_e("baseBlockMeta");
    this.extraData = nbt.func_74771_c("extraData");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("baseBlockID", this.baseBlockID);
    nbt.func_74768_a("baseBlockMeta", this.baseBlockMeta);
    nbt.func_74774_a("extraData", this.extraData);
  }



  public Packet func_145844_m() {
    if ((this.extraData & 0x8) != 0 || this.field_145848_d > 100) {

      NBTTagCompound nbt = new NBTTagCompound();
      createInitNBT(nbt);
      return (Packet)new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 1, nbt);
    }
    return null;
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    this.baseBlockID = nbt.func_74762_e("id");
    this.baseBlockMeta = nbt.func_74762_e("meta");
    this.extraData = nbt.func_74771_c("extraData");
  }





  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74768_a("id", this.baseBlockID);
    nbt.func_74768_a("meta", this.baseBlockMeta);
    nbt.func_74774_a("extraData", this.extraData);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEOre.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */