package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotBlocked;
import com.bioxx.tfc.Containers.Slots.SlotFoodOnly;
import com.bioxx.tfc.Containers.Slots.SlotSize;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class ContainerFoodPrep
  extends ContainerTFC
{
  private TEFoodPrep te;
  private EntityPlayer player;
  private int guiTab;

  public ContainerFoodPrep(InventoryPlayer playerinv, TEFoodPrep pile, World world, int x, int y, int z, int tab) {
    this.player = playerinv.field_70458_d;
    this.te = pile;




    this.guiTab = tab;
    pile.func_70295_k_();
    layoutContainer((IInventory)playerinv, (IInventory)pile, 0, 0);
    pile.lastTab = tab;
    PlayerInventory.buildInventoryLayout(this, playerinv, 8, 90, false, true);
  }






  public void func_75134_a(EntityPlayer par1EntityPlayer) {
    super.func_75134_a(par1EntityPlayer);
    this.te.func_70305_f();
  }



  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }


  protected void layoutContainer(IInventory playerInventory, IInventory chestInventory, int xSize, int ySize) {
    if (this.guiTab == 0) {

      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 0, 44, 24)).addItemInclusion(new Item[] { TFCItems.wheatBread, TFCItems.barleyBread, TFCItems.oatBread, TFCItems.ryeBread, TFCItems.riceBread, TFCItems.cornBread
            }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 1, 62, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 2, 80, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 3, 98, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 4, 116, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));


    }
    else if (this.guiTab == 1) {

      func_75146_a((Slot)new SlotBlocked(chestInventory, 0, 15, 8));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 1, 53, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 2, 71, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 3, 89, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
      func_75146_a((Slot)(new SlotFoodOnly(chestInventory, 4, 107, 24)).addItemException(new Item[] { TFCItems.woodenBucketMilk }).setSize(EnumSize.HUGE));
    }

    func_75146_a((Slot)new SlotBlocked(chestInventory, 6, 53, 46));
    func_75146_a((Slot)(new SlotSize(chestInventory, 7, 145, 8)).setSize(EnumSize.SMALL));
    func_75146_a((Slot)(new SlotSize(chestInventory, 8, 145, 26)).setSize(EnumSize.SMALL));
    func_75146_a((Slot)(new SlotSize(chestInventory, 9, 145, 44)).setSize(EnumSize.SMALL));
    func_75146_a((Slot)(new SlotSize(chestInventory, 10, 145, 62)).setSize(EnumSize.SMALL));
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


      if (slotNum < 10) {

        if (!func_75135_a(slotStack, 10, this.field_75151_b.size(), true)) {
          return null;

        }

      }
      else if (!func_75135_a(slotStack, 0, 10, false)) {
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */