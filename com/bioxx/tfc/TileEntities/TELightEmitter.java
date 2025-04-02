package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Time;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TELightEmitter
  extends TileEntity
{
  public int hourPlaced = -1000;






  public void create() {
    this.hourPlaced = (int)TFC_Time.getTotalHours();
  }



  public boolean canUpdate() {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.hourPlaced = nbt.func_74762_e("hourPlaced");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("hourPlaced", this.hourPlaced);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELightEmitter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */