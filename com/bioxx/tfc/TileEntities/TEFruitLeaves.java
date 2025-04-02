package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;

public class TEFruitLeaves
  extends NetworkTileEntity {
  public int dayHarvested = -1000;
  public int dayFruited = -1000;

  public boolean hasFruit;

  public TEFruitLeaves() {
    this.shouldSendInitData = true;
  }



  public boolean canUpdate() {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.dayHarvested = nbt.func_74762_e("dayHarvested");
    this.dayFruited = nbt.func_74762_e("dayFruited");
    this.hasFruit = nbt.func_74767_n("hasFruit");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("dayHarvested", this.dayHarvested);
    nbt.func_74768_a("dayFruited", this.dayFruited);
    nbt.func_74757_a("hasFruit", this.hasFruit);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.hasFruit = nbt.func_74767_n("hasFruit");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void handleDataPacket(NBTTagCompound nbt) {
    this.hasFruit = nbt.func_74767_n("hasFruit");
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void createDataNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("hasFruit", this.hasFruit);
  }


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("hasFruit", this.hasFruit);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFruitLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */