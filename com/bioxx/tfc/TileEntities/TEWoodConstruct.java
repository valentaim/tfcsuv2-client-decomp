package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEWoodConstruct
  extends NetworkTileEntity
{
  public byte[] woodTypes = new byte[192];
  public BitSet data;
  public static int plankDetailLevel = 8;


  public boolean[] solidCheck = new boolean[48];


  public TEWoodConstruct() {
    this.data = new BitSet(192);
  }
































  public boolean canUpdate() {
    return false;
  }


  public void ejectContents() {
    for (int i = 0; i < 192; i++) {

      if (this.data.get(i)) {

        this.data.clear(i);
        ItemStack stack = new ItemStack(TFCItems.singlePlank, 1, this.woodTypes[i]);
        EntityItem e = new EntityItem(this.field_145850_b, this.field_145851_c, this.field_145848_d, this.field_145849_e, stack);
        e.field_145804_b = 5;
        this.field_145850_b.func_72838_d((Entity)e);
      }
    }
  }


  public List<ItemStack> getDrops() {
    List<ItemStack> list = new ArrayList<>();
    for (int i = 0; i < 192; i++) {

      if (this.data.get(i)) {

        ItemStack stack = new ItemStack(TFCItems.singlePlank, 1, this.woodTypes[i]);
        list.add(stack);
      }
    }
    return list;
  }


  public static BitSet fromByteArray(byte[] bytes) {
    BitSet bits = new BitSet(192);
    for (int i = 0; i < bytes.length * 8; i++) {

      if ((bytes[bytes.length - i / 8 - 1] & 1 << i % 8) > 0)
        bits.set(i);
    }
    return bits;
  }


  public static byte[] toByteArray(BitSet bits) {
    byte[] bytes = new byte[bits.length() / 8 + 1];
    for (int i = 0; i < bits.length(); i++) {

      if (bits.get(i))
        bytes[bytes.length - i / 8 - 1] = (byte)(bytes[bytes.length - i / 8 - 1] | 1 << i % 8);
    }
    return bytes;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.woodTypes = nbt.func_74770_j("woodTypes");
    this.data = new BitSet(192);
    this.data.or(fromByteArray(nbt.func_74770_j("data")));
  }




  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74773_a("woodTypes", this.woodTypes);
    nbt.func_74773_a("data", toByteArray(this.data));
  }




  public void handleInitPacket(NBTTagCompound nbt) {
    this.woodTypes = nbt.func_74770_j("woodTypes");
    this.data = new BitSet(192);
    this.data.or(fromByteArray(nbt.func_74770_j("data")));
    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    int index = nbt.func_74762_e("index");
    byte meta = nbt.func_74771_c("meta");
    this.data.flip(index);
    this.woodTypes[index] = meta;

    this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74773_a("woodTypes", this.woodTypes);
    nbt.func_74773_a("data", toByteArray(this.data));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEWoodConstruct.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */