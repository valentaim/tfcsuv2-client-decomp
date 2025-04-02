package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotLogPile;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TELogPile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class ContainerLogPile
  extends ContainerTFC
{
  private World world;
  private TELogPile logpile;
  private EntityPlayer player;

  public ContainerLogPile(InventoryPlayer playerinv, TELogPile pile, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;
    this.logpile = pile;
    this.world = world;



    pile.func_70295_k_();
    layoutContainer((IInventory)playerinv, (IInventory)pile, 0, 0);
    PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
  }






  public void func_75134_a(EntityPlayer par1EntityPlayer) {
    super.func_75134_a(par1EntityPlayer);

    if (!this.world.field_72995_K) {
      this.logpile.func_70305_f();
    }
  }





  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 4) {

        if (!func_75135_a(slotStack, 4, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 4, false)) {
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



  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }


  protected void layoutContainer(IInventory playerInventory, IInventory chestInventory, int xSize, int ySize) {
    func_75146_a((Slot)new SlotLogPile(getPlayer(), chestInventory, 0, 71, 25));
    func_75146_a((Slot)new SlotLogPile(getPlayer(), chestInventory, 1, 89, 25));
    func_75146_a((Slot)new SlotLogPile(getPlayer(), chestInventory, 2, 71, 43));
    func_75146_a((Slot)new SlotLogPile(getPlayer(), chestInventory, 3, 89, 43));
  }


  public EntityPlayer getPlayer() {
    return this.player;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerLogPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */