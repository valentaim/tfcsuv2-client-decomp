package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotLiquidVessel;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TECrucible;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;




public class ContainerCrucible
  extends ContainerTFC
{
  private TECrucible te;
  private float firetemp;

  public ContainerCrucible(InventoryPlayer inventoryplayer, TECrucible tileentityforge, World world, int x, int y, int z) {
    this.te = tileentityforge;
    this.firetemp = 0.0F;

    func_75146_a(new Slot((IInventory)tileentityforge, 0, 152, 7)
        {

          public boolean func_75214_a(ItemStack itemstack)
          {
            if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil) return true;
            if (itemstack.func_77973_b() != TFCItems.bloom && (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel || (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.MEDIUM.stackSize))) return false;
            return (itemstack.func_77973_b() != TFCItems.rawBloom && (itemstack.func_77973_b() != TFCItems.bloom || itemstack.func_77960_j() <= 100));
          }
        });

    func_75146_a((Slot)new SlotLiquidVessel((IInventory)tileentityforge, 1, 152, 90));

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 118, false, true);

    this.te.updateGui((byte)0);
  }



  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      if (!(slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil) &&
        slotStack.func_77973_b() != TFCItems.bloom && (slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.Pottery.ItemPotterySmallVessel || (slotStack.func_77973_b() instanceof ISize && (((ISize)slotStack.func_77973_b()).getSize(slotStack)).stackSize < EnumSize.MEDIUM.stackSize))) return null;

      origStack = slotStack.func_77946_l();


      if (slotNum < 2) {

        if (!func_75135_a(slotStack, 2, this.field_75151_b.size(), true)) {
          return null;
        }
      }
      else if ((slotStack.func_77973_b() == TFCItems.ceramicMold && slotStack.func_77960_j() == 1) || slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {

        if (!func_75135_a(slotStack, 1, 2, true)) {
          return null;

        }

      }
      else if (!func_75135_a(slotStack, 0, 1, true)) {
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
      if (this.firetemp != this.te.temperature) {
        var2.func_71112_a(this, 0, this.te.temperature);
      }
    }
  }


  public void func_75137_b(int id, int value) {
    if (id == 0)
      this.te.temperature = value;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerCrucible.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */