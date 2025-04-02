package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TFCItems;
import fof.tfcsu.utils.ExpBonus;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class TELeatherRack
  extends NetworkTileEntity
{
  public short workedArea;
  public ItemStack leatherItem;

  public void setLeather(ItemStack is) {
    this.leatherItem = is.func_77946_l();
    this.leatherItem.field_77994_a = 1;
    is.field_77994_a--;
  }


  public void workArea(int coord) {
    this.workedArea = (short)(this.workedArea | 1 << coord);
    if (this.workedArea == -1 && this.leatherItem != null) {

      int meta = this.leatherItem.func_77960_j();
      this.leatherItem = new ItemStack(TFCItems.scrapedHide, 1, meta);
      ExpBonus.LEATHER_RACK.spawnExp(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    NBTTagCompound item = new NBTTagCompound();
    if (this.leatherItem != null) {

      this.leatherItem.func_77955_b(item);
      nbt.func_74782_a("leatherItem", (NBTBase)item);
    }
    nbt.func_74777_a("workedArea", this.workedArea);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("leatherItem"))
      this.leatherItem = ItemStack.func_77949_a((NBTTagCompound)nbt.func_74781_a("leatherItem"));
    this.workedArea = nbt.func_74765_d("workedArea");
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    if (nbt.func_74764_b("leatherItem"))
      this.leatherItem = ItemStack.func_77949_a((NBTTagCompound)nbt.func_74781_a("leatherItem"));
    this.workedArea = nbt.func_74765_d("workedArea");
  }


  public void createInitNBT(NBTTagCompound nbt) {
    NBTTagCompound item = new NBTTagCompound();
    if (this.leatherItem != null) {

      this.leatherItem.func_77955_b(item);
      nbt.func_74782_a("leatherItem", (NBTBase)item);
    }
    nbt.func_74777_a("workedArea", this.workedArea);
  }


  public void handleDataPacket(NBTTagCompound nbt) {
    this.workedArea = nbt.func_74765_d("workedArea");
    this.field_145850_b.func_147479_m(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TELeatherRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */