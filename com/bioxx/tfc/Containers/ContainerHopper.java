package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotHopper;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class ContainerHopper
  extends ContainerTFC
{
  private final IInventory hopperInv;

  public ContainerHopper(InventoryPlayer playerInv, IInventory inv) {
    this.hopperInv = inv;
    inv.func_70295_k_();


    for (int i = 0; i < inv.func_70302_i_(); i++)
    {
      func_75146_a((Slot)new SlotHopper(inv, i, 44 + i * 18, 17));
    }

    PlayerInventory.buildInventoryLayout(this, playerInv, 8, 54, false, true);
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      if (!TFC_Core.isItemHopperValid(slot.func_75211_c())) return null;
      origStack = slotStack.func_77946_l();

      if (slotNum < 5) {

        if (!func_75135_a(slotStack, 5, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 5, false)) {
        return null;
      }

      if (slotStack.field_77994_a <= 0) {
        slot.func_75215_d(null);
      } else {
        slot.func_75218_e();
      }
      if (slotStack.field_77994_a == origStack.field_77994_a) {
        return null;
      }
      slot.func_82870_a(player, slotStack);
    }

    return origStack;
  }



  public boolean func_75145_c(EntityPlayer player) {
    return this.hopperInv.func_70300_a(player);
  }






  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.hopperInv.func_70305_f();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */