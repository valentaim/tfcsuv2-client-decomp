package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotOutputOnly;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TENestBox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;






public class ContainerNestBox
  extends ContainerTFC
{
  public ContainerNestBox(InventoryPlayer playerinv, TENestBox te, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;





    func_75146_a((Slot)new SlotOutputOnly((IInventory)te, 0, 71, 25));
    func_75146_a((Slot)new SlotOutputOnly((IInventory)te, 1, 89, 25));
    func_75146_a((Slot)new SlotOutputOnly((IInventory)te, 2, 71, 43));
    func_75146_a((Slot)new SlotOutputOnly((IInventory)te, 3, 89, 43));

    PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
  }



  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();

      if (slotNum < 4)
      {
        if (!func_75135_a(slotStack, 4, this.field_75151_b.size(), true)) {
          return null;
        }
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerNestBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */