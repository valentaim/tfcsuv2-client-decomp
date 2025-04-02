package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotFirepitFuel
  extends Slot
{
  public SlotFirepitFuel(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    return (itemstack.func_77973_b() == TFCItems.logs || itemstack.func_77973_b() == Item.func_150898_a(TFCBlocks.peat));
  }



  public int func_75219_a() {
    return 1;
  }



  public void func_75215_d(ItemStack par1ItemStack) {
    if (par1ItemStack != null) par1ItemStack.field_77994_a = 1;
    super.func_75215_d(par1ItemStack);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotFirepitFuel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */