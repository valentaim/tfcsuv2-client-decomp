package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotChest;
import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.TileEntities.TEBarrel;
import com.bioxx.tfc.TileEntities.TEVessel;
import com.bioxx.tfc.api.Enums.EnumSize;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ContainerLargeVessel
  extends ContainerBarrel
{
  TileEntity vessel;

  public ContainerLargeVessel(InventoryPlayer inventoryplayer, TEVessel tileentitybarrel, World world, int x, int y, int z, int tab) {
    super(inventoryplayer, (TEBarrel)tileentitybarrel, world, x, y, z, tab);
    this.vessel = (TileEntity)tileentitybarrel;
  }



  protected void buildLayout() {
    if (this.guiTab == 0) {


      if (!this.barrel.getSealed()) {
        func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, 0, 80, 29)).setSize(EnumSize.MEDIUM).addItemException(ContainerBarrel.getExceptions()));
      } else {
        func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, 0, 80, 29));
      }
    } else if (this.guiTab == 1) {

      for (int i = 0; i < 3; i++) {

        for (int k = 0; k < 3; k++) {

          if (!this.barrel.getSealed()) {
            func_75146_a((Slot)(new SlotChest((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18)).setSize(EnumSize.MEDIUM).addItemException(ContainerChestTFC.getExceptions()));
          } else {
            func_75146_a((Slot)new SlotForShowOnly((IInventory)this.barrel, k + i * 3, 71 + i * 18, 17 + k * 18));
          }
        }
      }
    }
  }


  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    if (this.vessel.func_145831_w() instanceof cuchaz.ships.ShipWorld) {
      if (!player.field_70170_p.field_72995_K) FMLLog.getLogger().warn("Ship Duper found: " + player.getDisplayName());
      return null;
    }
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (!this.barrel.getSealed() && slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 1 && this.guiTab == 0) {

        if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
          return null;
        }
      }
      else if (slotNum < 9 && this.guiTab == 1) {

        if (!func_75135_a(slotStack, 9, this.field_75151_b.size(), true)) {
          return null;

        }

      }
      else if (this.guiTab == 1) {

        if (!func_75135_a(slotStack, 0, 9, false)) {
          return null;
        }
      }
      else if (this.guiTab == 0) {

        if (!func_75135_a(slotStack, 0, 1, false)) {
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */