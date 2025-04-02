package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotArmorTFC;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Handlers.CraftingHandler;
import com.bioxx.tfc.Handlers.FoodCraftingHandler;
import com.bioxx.tfc.Items.ItemTFCArmor;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;





public class ContainerPlayerTFC
  extends ContainerPlayer
{
  private final EntityPlayer thePlayer;

  public ContainerPlayerTFC(InventoryPlayer playerInv, boolean par2, EntityPlayer player) {
    super(playerInv, par2, player);
    this.field_75181_e = new InventoryCrafting((Container)this, 3, 3);
    this.field_75151_b.clear();
    this.field_75153_a.clear();
    this.thePlayer = player;
    func_75146_a((Slot)new SlotCrafting(player, (IInventory)this.field_75181_e, this.field_75179_f, 0, 152, 36));

    int x;

    for (x = 0; x < 2; x++) {

      for (int y = 0; y < 2; y++) {
        func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
      }
    }
    for (x = 0; x < playerInv.field_70460_b.length; x++) {

      int index = playerInv.func_70302_i_() - 3 - x;

      func_75146_a((Slot)new SlotArmorTFC(this, (IInventory)playerInv, index, 8, 8 + x * 18, x));
    }
    PlayerInventory.buildInventoryLayout((Container)this, playerInv, 8, 90, false, true);


    if (player.getEntityData().func_74764_b("craftingTable") || !player.field_70170_p.field_72995_K) {

      x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
      x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
      x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
      x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));
      x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18, 18 + x * 18));

    }
    else {

      x = 2; int y = 0; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
      x = 2; y = 1; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
      x = 0; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
      x = 1; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
      x = 2; y = 2; func_75146_a(new Slot((IInventory)this.field_75181_e, y + x * 3, 82 + y * 18 - 50000, 18 + x * 18));
    }
    PlayerInventory.addExtraEquipables((Container)this, playerInv, 8, 90, false);
    func_75130_a((IInventory)this.field_75181_e);
  }






  public void func_75130_a(IInventory iinventory) {
    super.func_75130_a(iinventory);

    Slot craftOut = this.field_75151_b.get(0);
    if (craftOut != null && craftOut.func_75216_d()) {

      ItemStack craftResult = craftOut.func_75211_c();
      if (craftResult != null)
      {
        if (craftResult.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {
          FoodCraftingHandler.updateOutput(this.thePlayer, craftResult, (IInventory)this.field_75181_e);
        } else {
          CraftingHandler.transferNBT(false, this.thePlayer, craftResult, (IInventory)this.field_75181_e);
        }
      }
    }
  }


  public void func_75134_a(EntityPlayer player) {
    if (!player.field_70170_p.field_72995_K) {

      super.func_75134_a(player);

      for (int i = 0; i < 9; i++) {

        ItemStack itemstack = this.field_75181_e.func_70304_b(i);
        if (itemstack != null) {
          player.func_71019_a(itemstack, false);
        }
      }
      this.field_75179_f.func_70299_a(0, (ItemStack)null);
    }
  }



  public ItemStack func_82846_b(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);
    Slot equipmentSlot = this.field_75151_b.get(50);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum == 0) {

        FoodCraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);
        CraftingHandler.preCraft(player, slotStack, (IInventory)this.field_75181_e);

        if (!mergeItemStack(slotStack, 9, 45, true, true)) {
          return null;
        }
        slot.func_75220_a(slotStack, origStack);

      }
      else if ((slotNum >= 1 && slotNum < 5) || (player.getEntityData().func_74764_b("craftingTable") && slotNum >= 45 && slotNum < 50)) {

        if (!mergeItemStack(slotStack, 9, 45, true, false)) {
          return null;
        }
      }
      else if ((slotNum >= 5 && slotNum < 9) || slotNum == 50) {

        if (!mergeItemStack(slotStack, 9, 45, true, false)) {
          return null;
        }
      }
      else if (origStack.func_77973_b() instanceof ItemArmor) {

        int armorSlotNum = 5 + ((ItemArmor)origStack.func_77973_b()).field_77881_a;
        if (origStack.func_77973_b() instanceof ItemTFCArmor) {

          armorSlotNum = 5 + ((ItemTFCArmor)origStack.func_77973_b()).getUnadjustedArmorType();

          if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d())
          {
            if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
              return null;
            }
          }
        } else if (!((Slot)this.field_75151_b.get(armorSlotNum)).func_75216_d()) {

          if (!mergeItemStack(slotStack, armorSlotNum, armorSlotNum + 1, false, false)) {
            return null;
          }
        }

      } else if (!equipmentSlot.func_75216_d() && origStack.func_77973_b() instanceof IEquipable) {

        IEquipable equipment = (IEquipable)origStack.func_77973_b();
        if (equipment.getEquipType(origStack) == IEquipable.EquipType.BACK && (equipment == TFCItems.quiver || equipment.getTooHeavyToCarry(origStack)))
        {
          ItemStack backStack = slotStack.func_77946_l();
          backStack.field_77994_a = 1;
          equipmentSlot.func_75215_d(backStack);
          slotStack.field_77994_a--;
        }

      }
      else if (slotNum >= 9 && slotNum < 45 && origStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood && !(origStack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) && !isCraftingGridFull()) {

        if (!mergeItemStack(slotStack, 1, 5, false, false) && slotStack.field_77994_a == 0)
          return null;
        if (slotStack.field_77994_a > 0 && player.getEntityData().func_74764_b("craftingTable") && !func_75135_a(slotStack, 45, 50, false))
          return null;
        if (slotStack.field_77994_a > 0 && slotNum >= 9 && slotNum < 36) {

          if (!mergeItemStack(slotStack, 36, 45, false, false)) {
            return null;
          }
        } else if (slotStack.field_77994_a > 0 && slotNum >= 36 && slotNum < 45) {

          if (!mergeItemStack(slotStack, 9, 36, false, false)) {
            return null;
          }
        }

      } else if (slotNum >= 9 && slotNum < 36) {

        if (!mergeItemStack(slotStack, 36, 45, false, false)) {
          return null;
        }
      }
      else if (slotNum >= 36 && slotNum < 45) {

        if (!mergeItemStack(slotStack, 9, 36, false, false)) {
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




  public ItemStack func_75144_a(int sourceSlotID, int destSlotID, int clickType, EntityPlayer p) {
    if (sourceSlotID >= 0 && sourceSlotID < this.field_75151_b.size()) {

      Slot sourceSlot = this.field_75151_b.get(sourceSlotID);
      ItemStack slotStack = sourceSlot.func_75211_c();


      if (clickType == 2 && sourceSlotID == 0 && slotStack != null) {

        CraftingHandler.preCraft(p, slotStack, (IInventory)this.field_75181_e);

      }
      else if (clickType == 7 && sourceSlotID >= 9 && sourceSlotID < 45) {

        if (sourceSlot.func_82869_a(p))
        {
          Slot destSlot = this.field_75151_b.get(destSlotID);
          destSlot.func_75215_d(slotStack);
          sourceSlot.func_75215_d(null);
          return null;
        }

      }
      else if (clickType == 1 && sourceSlotID == 0 && isInventoryFull() && slotStack != null && slotStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
        return null;
      }
    }  return super.func_75144_a(sourceSlotID, destSlotID, clickType, p);
  }


  protected boolean isCraftingGridFull() {
    for (int i = 0; i < this.field_75181_e.func_70302_i_(); i++) {

      if (this.field_75181_e.func_70301_a(i) == null)
        return false;
    }
    return true;
  }



  protected boolean isInventoryFull() {
    for (int i = 9; i < 45; i++) {

      if (((Slot)this.field_75151_b.get(i)).func_75211_c() == null)
        return false;
    }
    return true;
  }


  public EntityPlayer getPlayer() {
    return this.thePlayer;
  }


  protected boolean mergeItemStack(ItemStack is, int slotStart, int slotFinish, boolean reverseOrder, boolean craftingOutput) {
    boolean merged = false;
    int slotIndex = slotStart;

    if (reverseOrder) {
      slotIndex = slotFinish - 1;
    }



    if (is.func_77985_e())
    {
      while (is.field_77994_a > 0 && ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart))) {

        Slot slot = this.field_75151_b.get(slotIndex);
        ItemStack slotstack = slot.func_75211_c();

        if (slotstack != null && slotstack.func_77973_b() == is.func_77973_b() && is

          .func_77960_j() == slotstack.func_77960_j() && ItemStack.func_77970_a(is, slotstack) && slotstack.field_77994_a < slot.func_75219_a()) {

          int mergedStackSize = is.field_77994_a + getSmaller(slotstack.field_77994_a, slot.func_75219_a());


          if (mergedStackSize <= is.func_77976_d() && mergedStackSize <= slot.func_75219_a()) {

            is.field_77994_a = 0;
            slotstack.field_77994_a = mergedStackSize;
            slot.func_75218_e();
            merged = true;

          }
          else if (!craftingOutput && slotstack.field_77994_a < is.func_77976_d() && slotstack.field_77994_a < slot.func_75219_a()) {


            if (slot.func_75219_a() >= is.func_77976_d()) {

              is.field_77994_a -= is.func_77976_d() - slotstack.field_77994_a;
              slotstack.field_77994_a = is.func_77976_d();
              slot.func_75218_e();
              merged = true;

            }
            else if (slot.func_75219_a() < is.func_77976_d()) {

              is.field_77994_a -= slot.func_75219_a() - slotstack.field_77994_a;
              slotstack.field_77994_a = slot.func_75219_a();
              slot.func_75218_e();
              merged = true;
            }
          }
        }

        if (reverseOrder) {
          slotIndex--; continue;
        }
        slotIndex++;
      }
    }

    if (is.field_77994_a > 0) {

      if (reverseOrder) {
        slotIndex = slotFinish - 1;
      } else {
        slotIndex = slotStart;
      }
      while ((!reverseOrder && slotIndex < slotFinish) || (reverseOrder && slotIndex >= slotStart)) {

        Slot slot = this.field_75151_b.get(slotIndex);
        ItemStack slotstack = slot.func_75211_c();
        if (slotstack == null && slot.func_75214_a(is) && slot.func_75219_a() < is.field_77994_a) {

          ItemStack copy = is.func_77946_l();
          copy.field_77994_a = slot.func_75219_a();
          is.field_77994_a -= slot.func_75219_a();
          slot.func_75215_d(copy);
          slot.func_75218_e();
          merged = true;

          break;
        }
        if (slotstack == null && slot.func_75214_a(is)) {

          slot.func_75215_d(is.func_77946_l());
          slot.func_75218_e();
          is.field_77994_a = 0;
          merged = true;

          break;
        }
        if (reverseOrder) {
          slotIndex--; continue;
        }
        slotIndex++;
      }
    }

    return merged;
  }


  protected int getSmaller(int i, int j) {
    if (i < j) {
      return i;
    }
    return j;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerPlayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */