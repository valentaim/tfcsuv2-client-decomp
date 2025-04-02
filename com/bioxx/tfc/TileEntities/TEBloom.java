package com.bioxx.tfc.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;




public class TEBloom
  extends TileEntity
{
  public int size = 0;



  public void setSize(int iron) {
    this.size = iron;
  }



  public void func_145841_b(NBTTagCompound nbttagcompound) {
    super.func_145841_b(nbttagcompound);
    nbttagcompound.func_74768_a("size", this.size);
  }



  public void func_145839_a(NBTTagCompound nbttagcompound) {
    super.func_145839_a(nbttagcompound);
    this.size = nbttagcompound.func_74762_e("size");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEBloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */