package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotFirepit;
import com.bioxx.tfc.Containers.Slots.SlotFirepitFuel;
import com.bioxx.tfc.Containers.Slots.SlotFirepitIn;
import com.bioxx.tfc.Containers.Slots.SlotFirepitOut;
import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEFirepit;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ContainerFirepit
  extends ContainerTFC
{
  private TEFirepit firepit;
  private float firetemp;

  public ContainerFirepit(InventoryPlayer inventoryplayer, TEFirepit tileentityfirepit, World world, int x, int y, int z) {
    this.firepit = tileentityfirepit;
    this.firetemp = -1111.0F;


    func_75146_a((Slot)new SlotFirepitIn(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 1, 80, 20));

    func_75146_a((Slot)new SlotFirepitFuel(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 0, 8, 8));
    func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 3, 8, 26));
    func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 4, 8, 44));
    func_75146_a((Slot)new SlotFirepit(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 5, 8, 62));


    func_75146_a((Slot)new SlotFirepitOut(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 7, 71, 48));
    func_75146_a((Slot)new SlotFirepitOut(inventoryplayer.field_70458_d, (IInventory)tileentityfirepit, 8, 89, 48));


    func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 2, -50000, 0));
    func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 6, -50000, 0));
    func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 9, -50000, 0));
    func_75146_a((Slot)new SlotForShowOnly((IInventory)tileentityfirepit, 10, -50000, 0));

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
  }



  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);
    Slot slotinput = this.field_75151_b.get(0);
    Slot[] slotfuel = { this.field_75151_b.get(1), this.field_75151_b.get(3), this.field_75151_b.get(4), this.field_75151_b.get(5) };

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 11) {

        if (!func_75135_a(slotStack, 11, this.field_75151_b.size(), true)) {
          return null;
        }
      } else {

        HeatRegistry manager = HeatRegistry.getInstance();


        if (slotStack.func_77973_b() == TFCItems.logs || slotStack.func_77973_b() == Item.func_150898_a(TFCBlocks.peat)) {

          if (slotfuel[0].func_75216_d())
            return null;
          ItemStack stack = slotStack.func_77946_l();
          stack.field_77994_a = 1;
          slotfuel[0].func_75215_d(stack);
          slotStack.field_77994_a--;

        }
        else if (!(slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) && manager.findMatchingIndex(slotStack) != null) {

          if (slotinput.func_75216_d())
            return null;
          ItemStack stack = slotStack.func_77946_l();
          stack.field_77994_a = 1;
          slotinput.func_75215_d(stack);
          slotStack.field_77994_a--;
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




  public void func_75142_b() {
    super.func_75142_b(); int var1;
    for (var1 = 0; var1 < this.field_75151_b.size(); var1++) {

      ItemStack var2 = ((Slot)this.field_75151_b.get(var1)).func_75211_c();
      ItemStack var3 = this.field_75153_a.get(var1);

      if (!ItemStack.func_77989_b(var3, var2)) {

        var3 = (var2 == null) ? null : var2.func_77946_l();
        this.field_75153_a.set(var1, var3);

        for (int var4 = 0; var4 < this.field_75149_d.size(); var4++) {
          ((ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, var1, var3);
        }
      }
    }
    for (var1 = 0; var1 < this.field_75149_d.size(); var1++) {

      ICrafting var2 = this.field_75149_d.get(var1);
      if (this.firetemp != this.firepit.fireTemp) {
        var2.func_71112_a(this, 0, (int)this.firepit.fireTemp);
      }
    }
    this.firetemp = this.firepit.fireTemp;
  }



  public void func_75137_b(int par1, int par2) {
    if (par1 == 0)
      this.firepit.fireTemp = par2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */