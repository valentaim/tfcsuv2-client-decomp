package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotForShowOnly;
import com.bioxx.tfc.Containers.Slots.SlotQuiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;




public class ContainerQuiver
  extends ContainerTFC
{
  public InventoryCrafting containerInv = new InventoryCrafting(this, 4, 2);



  public ContainerQuiver(InventoryPlayer playerinv, World world, int x, int y, int z) {
    this.player = playerinv.field_70458_d;




    this.bagsSlotNum = this.player.field_71071_by.field_70461_c;


    layoutContainer((IInventory)playerinv, 0, 0);

    if (!world.field_72995_K)
      loadBagInventory();
    this.doItemSaving = true;
  }


  protected void layoutContainer(IInventory playerInventory, int xSize, int ySize) {
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 0, 53, 8));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 1, 71, 8));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 2, 89, 8));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 3, 107, 8));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 4, 53, 26));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 5, 71, 26));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 6, 89, 26));
    func_75146_a((Slot)new SlotQuiver((IInventory)this.containerInv, 7, 107, 26));


    int row;

    for (row = 0; row < 9; row++) {

      if (row == this.bagsSlotNum) {
        func_75146_a((Slot)new SlotForShowOnly(playerInventory, row, 8 + row * 18, 112));
      } else {
        func_75146_a(new Slot(playerInventory, row, 8 + row * 18, 112));
      }
    }
    for (row = 0; row < 3; row++) {

      for (int col = 0; col < 9; col++) {
        func_75146_a(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 54 + row * 18));
      }
    }
  }

  public void loadBagInventory() {
    if (this.player.field_71071_by.func_70301_a(this.bagsSlotNum) != null && this.player.field_71071_by
      .func_70301_a(this.bagsSlotNum).func_77942_o()) {

      NBTTagList nbttaglist = this.player.field_71071_by.func_70301_a(this.bagsSlotNum).func_77978_p().func_150295_c("Items", 10);

      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        this.isLoading = true;
        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte byte0 = nbttagcompound1.func_74771_c("Slot");
        if (byte0 >= 0 && byte0 < 8) {
          this.containerInv.func_70299_a(byte0, ItemStack.func_77949_a(nbttagcompound1));
        }
      }
    }
  }


  public void saveContents(ItemStack is) {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.containerInv.func_70302_i_(); i++) {

      if (this.containerInv.func_70301_a(i) != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        this.containerInv.func_70301_a(i).func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    if (is != null) {

      if (!is.func_77942_o()) {
        is.func_77982_d(new NBTTagCompound());
      }
      is.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
    }
  }




  public ItemStack loadContents(int slot) {
    if (this.player.field_71071_by.func_70301_a(this.bagsSlotNum) != null && this.player.field_71071_by
      .func_70301_a(this.bagsSlotNum).func_77942_o()) {

      NBTTagList nbttaglist = this.player.field_71071_by.func_70301_a(this.bagsSlotNum).func_77978_p().func_150295_c("Items", 10);
      if (nbttaglist != null)
      {
        for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

          NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
          byte byte0 = nbttagcompound1.func_74771_c("Slot");
          if (byte0 == slot)
            return ItemStack.func_77949_a(nbttagcompound1);
        }
      }
    }
    return null;
  }



  public boolean func_75145_c(EntityPlayer var1) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();

      if (slotNum < 8) {

        if (!func_75135_a(slotStack, 8, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 8, false)) {
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */