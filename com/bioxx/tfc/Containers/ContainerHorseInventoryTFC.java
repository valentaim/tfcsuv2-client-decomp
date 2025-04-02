package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.ContainerHorseInventorySlotArmor;
import com.bioxx.tfc.Containers.Slots.ContainerHorseInventorySlotSaddle;
import com.bioxx.tfc.Containers.Slots.SlotAnimalChest;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerHorseInventoryTFC
  extends ContainerTFC {
  private IInventory horseInv;
  private EntityHorseTFC horse;

  public ContainerHorseInventoryTFC(InventoryPlayer invPlayer, IInventory invHorse, EntityHorseTFC entityHorse) {
    this.horseInv = invHorse;
    this.horse = entityHorse;
    invHorse.func_70295_k_();
    func_75146_a((Slot)new ContainerHorseInventorySlotSaddle(this, invHorse, 0, 8, 18));
    func_75146_a((Slot)new ContainerHorseInventorySlotArmor(this, this.horseInv, 1, 8, 36, (EntityHorse)this.horse));

    if (entityHorse.func_110261_ca())
    {

      for (int j = 0; j < 3; j++) {

        for (int k = 0; k < 5; k++)
        {

          func_75146_a((Slot)(new SlotAnimalChest(invHorse, 2 + k + j * 5, 80 + k * 18, 18 + j * 18)).addItemException(ContainerChestTFC.getExceptions()));
        }
      }
    }

    PlayerInventory.buildInventoryLayout(this, invPlayer, 8, 90, false, true);
  }



  public boolean func_75145_c(EntityPlayer player) {
    return (this.horseInv.func_70300_a(player) && this.horse.func_70089_S() && this.horse.func_70032_d((Entity)player) < 8.0F);
  }






  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();
      int inventorySize = this.horseInv.func_70302_i_();

      if (slotNum < inventorySize) {

        if (!func_75135_a(slotStack, inventorySize, this.field_75151_b.size(), true))
        {
          return null;
        }
      }
      else if (func_75139_a(1).func_75214_a(slotStack) && !func_75139_a(1).func_75216_d()) {

        if (!func_75135_a(slotStack, 1, 2, false))
        {
          return null;
        }
      }
      else if (func_75139_a(0).func_75214_a(slotStack)) {

        if (!func_75135_a(slotStack, 0, 1, false))
        {
          return null;
        }
      }
      else if (inventorySize <= 2 || !func_75135_a(slotStack, 2, inventorySize, false)) {

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






  public void func_75134_a(EntityPlayer player) {
    super.func_75134_a(player);
    this.horseInv.func_70305_f();
    this.horse.updateChestSaddle();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerHorseInventoryTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */