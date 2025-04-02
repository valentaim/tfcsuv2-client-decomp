package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;



public class SlotFirepitIn
  extends Slot
{
  public SlotFirepitIn(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public int func_75219_a() {
    return 1;
  }



  public boolean func_75214_a(ItemStack is) {
    HeatRegistry manager = HeatRegistry.getInstance();
    return (is.func_77973_b() == TFCItems.fireStarter || is
      .func_77973_b() == TFCItems.flintSteel || (manager
      .findMatchingIndex(is) != null &&
      !(is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre)));
  }



  public void func_75215_d(ItemStack is) {
    if (is != null)
      is.field_77994_a = 1;
    if (this.field_75224_c != null)
      super.func_75215_d(is);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotFirepitIn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */