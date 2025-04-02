package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;

public class TEBerryBush
  extends NetworkTileEntity {
  public int dayHarvested = -1000;
  public int dayFruited = -1000;



  public boolean hasFruit;




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
  }




  public void handleDataPacket(NBTTagCompound nbt) {}




  public void createDataNBT(NBTTagCompound nbt) {}




  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74757_a("hasFruit", this.hasFruit);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBerryBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */