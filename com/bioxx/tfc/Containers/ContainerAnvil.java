package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotAnvilFlux;
import com.bioxx.tfc.Containers.Slots.SlotAnvilHammer;
import com.bioxx.tfc.Containers.Slots.SlotAnvilIn;
import com.bioxx.tfc.Containers.Slots.SlotAnvilWeldOut;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEAnvil;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerAnvil
  extends ContainerTFC
{
  private TEAnvil anvil;
  private int greenIndicator;
  private int redIndicator;
  private int tier = -1;


  public ContainerAnvil(InventoryPlayer inventoryplayer, TEAnvil anvil, World world, int x, int y, int z) {
    this.anvil = anvil;

    this.redIndicator = -1000;
    this.greenIndicator = -1000;


    func_75146_a((Slot)new SlotAnvilHammer(inventoryplayer.field_70458_d, (IInventory)anvil, 0, 7, 95));

    func_75146_a((Slot)new SlotAnvilIn((IInventory)anvil, 1, 87, 46));


    func_75146_a((Slot)new SlotAnvilIn((IInventory)anvil, 2, 14, 12));
    func_75146_a((Slot)new SlotAnvilIn((IInventory)anvil, 3, 32, 12));
    func_75146_a((Slot)new SlotAnvilWeldOut((IInventory)anvil, 4, 23, 34));

    func_75146_a((Slot)new SlotAnvilIn((IInventory)anvil, 5, 105, 46));

    func_75146_a((Slot)new SlotAnvilFlux((IInventory)anvil, 6, 185, 95));















    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 24, 122, false, true);
  }




  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);
    Slot slothammer = this.field_75151_b.get(0);
    Slot[] slotinput = { this.field_75151_b.get(1), this.field_75151_b.get(2), this.field_75151_b.get(3), this.field_75151_b.get(5) };

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 7) {

        if (!func_75135_a(slotStack, 7, this.field_75151_b.size(), true)) {
          return null;
        }
      }
      else if (slotStack.func_77973_b() == TFCItems.powder && slotStack.func_77960_j() == 0) {

        if (!func_75135_a(slotStack, 6, 7, false)) {
          return null;
        }
      }
      else if (slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemHammer) {

        if (slothammer.func_75216_d())
          return null;
        ItemStack stack = slotStack.func_77946_l();
        stack.field_77994_a = 1;
        slothammer.func_75215_d(stack);
        slotStack.field_77994_a--;

      }
      else {

        int j = 0;
        while (j < slotinput.length) {

          if (slotinput[j].func_75216_d()) {
            j++;
            continue;
          }
          ItemStack stack = slotStack.func_77946_l();
          stack.field_77994_a = 1;
          slotinput[j].func_75215_d(stack);
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
    super.func_75142_b();

    for (int var1 = 0; var1 < this.field_75149_d.size(); var1++) {

      ICrafting var2 = this.field_75149_d.get(var1);
      int cv = this.anvil.getCraftingValue();
      int icv = this.anvil.getItemCraftingValueNoSet(1);
      int t = this.anvil.anvilTier;

      if (this.redIndicator != cv)
        var2.func_71112_a(this, 0, cv);
      if (this.greenIndicator != icv)
        var2.func_71112_a(this, 1, icv);
      if (this.tier != t) {
        var2.func_71112_a(this, 2, t);
      }
    }
    this.redIndicator = this.anvil.craftingValue;
    this.greenIndicator = this.anvil.itemCraftingValue;
    this.tier = this.anvil.anvilTier;
  }







  public void func_75137_b(int par1, int par2) {
    if (this.anvil != null)
    {
      if (par1 == 0) {
        this.anvil.craftingValue = par2;
      } else if (par1 == 1) {
        this.anvil.itemCraftingValue = par2;
      } else if (par1 == 2) {
        this.anvil.anvilTier = par2;
      }
    }
  }


  public void func_75134_a(EntityPlayer par1EntityPlayer) {
    super.func_75134_a(par1EntityPlayer);
    this.anvil.func_70305_f();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */