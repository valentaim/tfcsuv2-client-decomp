package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotForge;
import com.bioxx.tfc.Containers.Slots.SlotForgeFuel;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEForge;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




public class ContainerForge
  extends ContainerTFC
{
  private TEForge forge;
  private float firetemp;

  public ContainerForge(InventoryPlayer inventoryplayer, TEForge tileentityforge, World world, int x, int y, int z) {
    this.forge = tileentityforge;





    func_75146_a((Slot)new SlotForge(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 0, 44, 8));
    func_75146_a((Slot)new SlotForge(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 1, 62, 26));
    func_75146_a((Slot)new SlotForge(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 2, 80, 44));
    func_75146_a((Slot)new SlotForge(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 3, 98, 26));
    func_75146_a((Slot)new SlotForge(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 4, 116, 8));

    func_75146_a((Slot)new SlotForgeFuel(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 5, 44, 26));
    func_75146_a((Slot)new SlotForgeFuel(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 6, 62, 44));
    func_75146_a((Slot)new SlotForgeFuel(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 7, 80, 62));
    func_75146_a((Slot)new SlotForgeFuel(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 8, 98, 44));
    func_75146_a((Slot)new SlotForgeFuel(inventoryplayer.field_70458_d, (IInventory)tileentityforge, 9, 116, 26));

    func_75146_a(new Slot((IInventory)tileentityforge, 10, 152, 8));
    func_75146_a(new Slot((IInventory)tileentityforge, 11, 152, 26));
    func_75146_a(new Slot((IInventory)tileentityforge, 12, 152, 44));
    func_75146_a(new Slot((IInventory)tileentityforge, 13, 152, 62));

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
  }



  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    Slot[] slotfuel = { this.field_75151_b.get(7), this.field_75151_b.get(6), this.field_75151_b.get(8), this.field_75151_b.get(5), this.field_75151_b.get(9) };

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 14) {

        if (!func_75135_a(slotStack, 14, this.field_75151_b.size(), true)) {
          return null;


        }

      }
      else if (slotStack.func_77973_b() == TFCItems.coal) {

        int j = 0;
        while (j < 5) {

          if (slotfuel[j].func_75216_d()) {

            j++;

            continue;
          }
          ItemStack stack = slotStack.func_77946_l();
          stack.field_77994_a = 1;
          slotfuel[j].func_75215_d(stack);
          slotStack.field_77994_a--;
          j = -1;
        }


        if (j > 0 && !func_75135_a(slotStack, 10, 14, false)) {
          return null;
        }
      }
      else if (!func_75135_a(slotStack, 0, 5, false) && !func_75135_a(slotStack, 10, 14, false)) {
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



  public void func_75142_b() {
    super.func_75142_b();

    for (int var1 = 0; var1 < this.field_75149_d.size(); var1++) {

      ICrafting var2 = this.field_75149_d.get(var1);
      if (this.firetemp != this.forge.fireTemp) {
        var2.func_71112_a(this, 0, (int)this.forge.fireTemp);
      }
    }
    this.firetemp = this.forge.fireTemp;
  }



  public void func_75137_b(int par1, int par2) {
    if (par1 == 0)
      this.forge.fireTemp = par2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */