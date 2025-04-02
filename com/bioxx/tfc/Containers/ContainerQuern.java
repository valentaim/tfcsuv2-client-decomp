package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotBlocked;
import com.bioxx.tfc.Containers.Slots.SlotQuern;
import com.bioxx.tfc.Containers.Slots.SlotQuernGrain;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEQuern;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class ContainerQuern
  extends ContainerTFC
{
  private World world;
  private TEQuern te;
  private EntityPlayer player;

  public ContainerQuern(InventoryPlayer playerinv, TEQuern pile, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;
    this.te = pile;
    this.world = world;



    pile.func_70295_k_();
    layoutContainer((IInventory)playerinv, (IInventory)pile, 0, 0);
    PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
  }






  public void func_75134_a(EntityPlayer par1EntityPlayer) {
    super.func_75134_a(par1EntityPlayer);
    if (!this.world.field_72995_K) {
      this.te.func_70305_f();
    } else {
      this.te.func_145829_t();
    }
  }


  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }


  protected void layoutContainer(IInventory playerInventory, IInventory chestInventory, int xSize, int ySize) {
    func_75146_a((Slot)new SlotQuernGrain(chestInventory, 0, 66, 47));
    func_75146_a((Slot)new SlotBlocked(chestInventory, 1, 93, 47));
    func_75146_a((Slot)new SlotQuern(chestInventory, 2, 93, 20));
  }


  public EntityPlayer getPlayer() {
    return this.player;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();

      if (slotNum < 3) {

        if (!func_75135_a(slotStack, 3, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 3, false)) {
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerQuern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */