package com.bioxx.tfc.Containers;

import com.bioxx.tfc.Containers.Slots.SlotTuyere;
import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.TileEntities.TEBlastFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerBlastFurnace
  extends ContainerTFC
{
  private TEBlastFurnace tileentity;
  private float firetemp;
  private int orecount;
  private int coalcount;
  private int updatecounter;

  public ContainerBlastFurnace(InventoryPlayer inventoryplayer, TEBlastFurnace te, World world, int x, int y, int z) {
    this.tileentity = te;
    this.firetemp = 0.0F;



    func_75146_a((Slot)new SlotTuyere((IInventory)te, 1, 153, 7));

    PlayerInventory.buildInventoryLayout(this, inventoryplayer, 8, 90, false, true);

    this.tileentity.updateGui();
  }



  public boolean func_75145_c(EntityPlayer entityplayer) {
    return true;
  }



  public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
    ItemStack origStack = null;
    Slot slot = this.field_75151_b.get(slotNum);
    Slot slotTuyere = this.field_75151_b.get(0);

    if (slot != null && slot.func_75216_d()) {

      ItemStack slotStack = slot.func_75211_c();
      origStack = slotStack.func_77946_l();


      if (slotNum < 1) {

        if (!func_75135_a(slotStack, 1, this.field_75151_b.size(), true)) {
          return null;

        }
      }
      else if (slotStack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemTuyere) {

        if (slotTuyere.func_75216_d())
          return null;
        ItemStack stack = slotStack.func_77946_l();
        stack.field_77994_a = 1;
        slotTuyere.func_75215_d(stack);
        slotStack.field_77994_a--;
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
      if (this.firetemp != this.tileentity.fireTemp)
      {
        var2.func_71112_a(this, 0, (int)this.tileentity.fireTemp);
      }
    }

    if (this.orecount != this.tileentity.oreCount || this.coalcount != this.tileentity.charcoalCount || this.updatecounter == 1000) {


      this.tileentity.func_145831_w().func_147471_g(this.tileentity.field_145851_c, this.tileentity.field_145848_d, this.tileentity.field_145849_e);
      this.updatecounter = 0;
    }
    this.orecount = this.tileentity.oreCount;
    this.coalcount = this.tileentity.charcoalCount;
    this.firetemp = this.tileentity.fireTemp;
    this.updatecounter++;
  }



  public void func_75137_b(int par1, int par2) {
    if (par1 == 0)
    {
      this.tileentity.fireTemp = par2;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */