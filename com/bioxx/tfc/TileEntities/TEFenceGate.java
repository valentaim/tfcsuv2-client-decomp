package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;


public class TEFenceGate
  extends NetworkTileEntity
{
  private boolean open;
  private byte direction;

  public boolean canUpdate() {
    return false;
  }


  public void setOpen(boolean value) {
    this.open = value;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void setDirection(byte value) {
    this.direction = value;
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public boolean getOpen() {
    return this.open;
  }


  public byte getDirection() {
    return this.direction;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.open = nbt.func_74767_n("open");
    this.direction = nbt.func_74771_c("dir");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74757_a("open", this.open);
    nbt.func_74774_a("dir", this.direction);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.open = nbt.func_74767_n("open");
    this.direction = nbt.func_74771_c("dir");
  }




  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("open", this.open);
    nbt.func_74774_a("dir", this.direction);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */