package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotCookableFoodOnly;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.api.TileEntities.TEFireEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;







public class ContainerGrill
  extends ContainerTFC
{
  private TEFireEntity fire;
  private float firetemp = -1111.0F;
  public ContainerGrill(InventoryPlayer inventoryplayer, TEGrill grill, World world, int x, int y, int z) {
    if (world.func_147438_o(x, y - 1, z) instanceof TEFireEntity)
    {
      this.fire = (TEFireEntity)world.func_147438_o(x, y - 1, z);
    }


    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 0, 71, 17));
    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 1, 89, 17));
    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 2, 71, 35));
    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 3, 89, 35));
    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 4, 71, 53));
    func_75146_a((Slot)new SlotCookableFoodOnly((IInventory)grill, 5, 89, 53));

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

      if (slotNum < 6) {

        if (!func_75135_a(slotStack, 6, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (!func_75135_a(slotStack, 0, 6, false)) {
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
      if (this.fire != null && this.firetemp != this.fire.fireTemp) {
        var2.func_71112_a(this, 0, (int)this.fire.fireTemp);
      }
    }
    if (this.fire != null)
    { this.firetemp = this.fire.fireTemp; }
    else { this.firetemp = 0.0F; }

  }


  public void func_75137_b(int par1, int par2) {
    if (this.fire != null && par1 == 0)
      this.fire.fireTemp = par2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */