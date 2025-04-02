package com.bioxx.tfc.TileEntities;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;





public class TEMetalSheet
  extends NetworkTileEntity
{
  public ItemStack sheetStack;
  private byte sides;
  public int metalID;

  public void clearSides() {
    this.sides = 0;
  }


  public boolean topExists() {
    return ((this.sides & 0x1) > 0);
  }


  public boolean bottomExists() {
    return ((this.sides & 0x2) > 0);
  }


  public boolean northExists() {
    return ((this.sides & 0x4) > 0);
  }


  public boolean southExists() {
    return ((this.sides & 0x8) > 0);
  }


  public boolean eastExists() {
    return ((this.sides & 0x10) > 0);
  }


  public boolean westExists() {
    return ((this.sides & 0x20) > 0);
  }


  public void toggleBySide(int side, boolean setOn) {
    switch (side) {
      case 0:
        toggleBottom(setOn); break;
      case 1: toggleTop(setOn); break;
      case 2: toggleNorth(setOn); break;
      case 3: toggleSouth(setOn); break;
      case 4: toggleEast(setOn); break;
      case 5: toggleWest(setOn); break;
    }
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }


  public void toggleTop(boolean setOn) {
    if (topExists() && !setOn) {
      this.sides = (byte)(this.sides - 1);
    } else {
      this.sides = (byte)(this.sides + 1);
    }
  }

  public void toggleBottom(boolean setOn) {
    if (bottomExists() && !setOn) {
      this.sides = (byte)(this.sides - 2);
    } else {
      this.sides = (byte)(this.sides + 2);
    }
  }

  public void toggleNorth(boolean setOn) {
    if (northExists() && !setOn) {
      this.sides = (byte)(this.sides - 4);
    } else {
      this.sides = (byte)(this.sides + 4);
    }
  }

  public void toggleSouth(boolean setOn) {
    if (southExists() && !setOn) {
      this.sides = (byte)(this.sides - 8);
    } else {
      this.sides = (byte)(this.sides + 8);
    }
  }

  public void toggleEast(boolean setOn) {
    if (eastExists() && !setOn) {
      this.sides = (byte)(this.sides - 16);
    } else {
      this.sides = (byte)(this.sides + 16);
    }
  }

  public void toggleWest(boolean setOn) {
    if (westExists() && !setOn) {
      this.sides = (byte)(this.sides - 32);
    } else {
      this.sides = (byte)(this.sides + 32);
    }
  }

  public boolean isEmpty() {
    return (!topExists() && !bottomExists() && !northExists() && !southExists() && !eastExists() && !westExists());
  }



  public boolean canUpdate() {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.sheetStack = ItemStack.func_77949_a(nbt.func_74775_l("sheetType"));
    this.sides = nbt.func_74771_c("sides");
    this.metalID = nbt.func_74762_e("metalID");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74774_a("sides", this.sides);
    NBTTagCompound st = new NBTTagCompound();
    if (this.sheetStack != null)
      this.sheetStack.func_77955_b(st);
    nbt.func_74782_a("sheetType", (NBTBase)st);
    nbt.func_74768_a("metalID", this.metalID);
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.sides = nbt.func_74771_c("sides");
    this.metalID = nbt.func_74762_e("metalID");
  }



  public void handleDataPacket(NBTTagCompound nbt) {}



  public void createDataNBT(NBTTagCompound nbt) {}


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74774_a("sides", this.sides);
    nbt.func_74768_a("metalID", this.metalID);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEMetalSheet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */