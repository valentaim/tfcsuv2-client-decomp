package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotSluice;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TESluice;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerSluice
  extends ContainerTFC
{
  private TESluice sluice;
  private EntityPlayer player;
  private int soilamt;
  private int progress;

  public ContainerSluice(InventoryPlayer inventoryplayer, TESluice tileentitysluice, World world, int x, int y, int z) {
    this.sluice = tileentitysluice;
    this.player = inventoryplayer.field_70458_d;
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 0, 116, 16));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 1, 134, 16));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 2, 152, 16));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 3, 116, 34));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 4, 134, 34));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 5, 152, 34));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 6, 116, 52));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 7, 134, 52));
    func_75146_a((Slot)new SlotSluice(this.player, (IInventory)this.sluice, 8, 152, 52));
    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);
  }



  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 9)
      {
        if (!func_75135_a(slotStack, 9, this.field_75151_b.size(), true)) {
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




  public void func_75142_b() {
    super.func_75142_b(); int var1;
    for (var1 = 0; var1 < this.field_75151_b.size(); var1++) {

      ItemStack var2 = ((Slot)this.field_75151_b.get(var1)).func_75211_c();
      ItemStack var3 = this.field_75153_a.get(var1);

      if (!ItemStack.func_77989_b(var3, var2)) {

        var3 = (var2 == null) ? null : var2.func_77946_l();
        this.field_75153_a.set(var1, var3);

        for (int var4 = 0; var4 < this.field_75149_d.size(); var4++)
          ((ICrafting)this.field_75149_d.get(var4)).func_71111_a(this, var1, var3);
      }
    }
    for (var1 = 0; var1 < this.field_75149_d.size(); var1++) {

      ICrafting var2 = this.field_75149_d.get(var1);
      if (this.soilamt != this.sluice.soilAmount)
        var2.func_71112_a(this, 0, this.sluice.soilAmount);
      if (this.progress != this.sluice.processTimeRemaining) {
        var2.func_71112_a(this, 1, this.sluice.processTimeRemaining);
      }
    }
    this.soilamt = this.sluice.soilAmount;
    this.progress = this.sluice.processTimeRemaining;
  }



  public void func_75137_b(int par1, int par2) {
    if (par1 == 0)
      this.sluice.soilAmount = par2;
    if (par1 == 1)
      this.sluice.processTimeRemaining = par2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */