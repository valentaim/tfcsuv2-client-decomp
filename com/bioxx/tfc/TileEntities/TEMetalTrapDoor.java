package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;







public class TEMetalTrapDoor
  extends NetworkTileEntity
{
  public ItemStack sheetStack;
  public byte data;

  public boolean canUpdate() {
    return false;
  }


  public int getSide() {
    return this.data & 0x7;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.sheetStack = ItemStack.func_77949_a(nbt.func_74775_l("sheetType"));
    this.data = nbt.func_74771_c("data");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74774_a("data", this.data);
    NBTTagCompound st = new NBTTagCompound();
    if (this.sheetStack != null) {

      this.sheetStack.func_77955_b(st);
      nbt.func_74782_a("sheetType", (NBTBase)st);
    }
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.data = nbt.func_74771_c("data");
    this.sheetStack = new ItemStack(TFCBlocks.metalTrapDoor, 1, nbt.func_74762_e("metalID"));
  }



  public void handleDataPacket(NBTTagCompound nbt) {}



  public void createDataNBT(NBTTagCompound nbt) {}


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74774_a("data", this.data);
    nbt.func_74768_a("metalID", (this.sheetStack != null) ? this.sheetStack.func_77960_j() : 0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEMetalTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */