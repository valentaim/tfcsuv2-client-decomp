package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotChest;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEChest;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.registry.GameData;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class ContainerChestTFC
  extends ContainerTFC
{
  private IInventory lowerChestInventory;
  private int numRows;

  public ContainerChestTFC(IInventory playerInv, IInventory chestInv, World world, int x, int y, int z) {
    TEChest chest = (TEChest)chestInv;
    this.lowerChestInventory = chestInv;

    if (chest.field_145991_k != null) {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145991_k, chestInv);
    }
    if (chest.field_145990_j != null) {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145990_j);
    }
    if (chest.field_145992_i != null) {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", (IInventory)chest.field_145992_i, chestInv);
    }
    if (chest.field_145988_l != null) {
      this.lowerChestInventory = (IInventory)new InventoryLargeChest("Large chest", chestInv, (IInventory)chest.field_145988_l);
    }
    this.numRows = this.lowerChestInventory.func_70302_i_() / 9;
    this.lowerChestInventory.func_70295_k_();
    int var3 = (this.numRows - 4) * 18;





    for (int var4 = 0; var4 < this.numRows; var4++) {

      for (int var5 = 0; var5 < 9; var5++)
      {
        func_75146_a((Slot)(new SlotChest(this.lowerChestInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18)).addItemException(getExceptions()));
      }
    }

    PlayerInventory.buildInventoryLayout(this, (InventoryPlayer)playerInv, 8, var3 + 109, false, true);
  }


  public static List<Item> getExceptions() {
    List<Item> exceptions = new ArrayList<>();
    for (Item ingot : TEIngotPile.getIngots())
    {
      exceptions.add(ingot);
    }
    exceptions.add(TFCItems.logs);
    exceptions.add(Item.func_150898_a(TFCBlocks.barrel));
    exceptions.add(Item.func_150898_a(TFCBlocks.vessel));

    Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
    if (fromcook != null) exceptions.add(fromcook);
    return exceptions;
  }



  public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
    return this.lowerChestInventory.func_70300_a(par1EntityPlayer);
  }






  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();
      int chestSlotCount = this.numRows * 9;


      if (slotNum < chestSlotCount) {

        if (!func_75135_a(slotStack, chestSlotCount, this.field_75151_b.size(), true)) {
          return null;

        }

      }
      else if (!func_75135_a(slotStack, 0, chestSlotCount, false)) {
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






  public void func_75134_a(EntityPlayer par1EntityPlayer) {
    super.func_75134_a(par1EntityPlayer);
    this.lowerChestInventory.func_70305_f();
  }





  public IInventory getLowerChestInventory() {
    return this.lowerChestInventory;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerChestTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */