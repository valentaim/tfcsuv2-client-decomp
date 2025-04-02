package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotFirepitOut
  extends Slot
{
  private int slotMax;

  public SlotFirepitOut(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
    this.slotMax = 64;
  }


  public boolean func_75214_a(ItemStack itemstack) {
    if (itemstack.func_77973_b() == TFCItems.ceramicMold && itemstack.func_77960_j() == 1) {

      this.slotMax = 1;
      return true;
    }
    return false;
  }


  public void func_75215_d(ItemStack par1ItemStack) {
    if (par1ItemStack != null && par1ItemStack.func_77973_b() == TFCItems.ceramicMold && par1ItemStack.func_77960_j() == 1) {

      par1ItemStack.field_77994_a = 1;
      this.slotMax = 1;
    }
    else {

      this.slotMax = 64;
    }
    super.func_75215_d(par1ItemStack);
  }



  public int func_75219_a() {
    return this.slotMax;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotFirepitOut.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */