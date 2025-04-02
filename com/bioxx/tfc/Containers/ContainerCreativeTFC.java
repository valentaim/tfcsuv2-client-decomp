package com.bioxx.tfc.Containers;

import com.bioxx.tfc.GUI.GuiContainerCreativeTFC;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


@SideOnly(Side.CLIENT)
public class ContainerCreativeTFC
  extends Container
{
  public List<ItemStack> itemList = new ArrayList<>();


  public ContainerCreativeTFC(EntityPlayer par1EntityPlayer) {
    InventoryPlayer inventoryplayer = par1EntityPlayer.field_71071_by;

    int i;
    for (i = 0; i < 5; i++) {

      for (int j = 0; j < 9; j++)
      {
        func_75146_a(new Slot((IInventory)GuiContainerCreativeTFC.getInventory(), i * 9 + j, 9 + j * 18, 18 + i * 18));
      }
    }

    for (i = 0; i < 9; i++)
    {
      func_75146_a(new Slot((IInventory)inventoryplayer, i, 9 + i * 18, 112));
    }

    scrollTo(0.0F);
  }



  public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
    return true;
  }





  public void scrollTo(float par1) {
    int i = this.itemList.size() / 9 - 5 + 1;
    int j = (int)((par1 * i) + 0.5D);

    if (j < 0)
    {
      j = 0;
    }

    for (int k = 0; k < 5; k++) {

      for (int l = 0; l < 9; l++) {

        int i1 = l + (k + j) * 9;

        if (i1 >= 0 && i1 < this.itemList.size()) {

          GuiContainerCreativeTFC.getInventory().func_70299_a(l + k * 9, this.itemList.get(i1));
        }
        else {

          GuiContainerCreativeTFC.getInventory().func_70299_a(l + k * 9, null);
        }
      }
    }
  }





  public boolean hasMoreThan1PageOfItemsInList() {
    return (this.itemList.size() > 45);
  }




  protected void func_75133_b(int par1, int par2, boolean par3, EntityPlayer par4EntityPlayer) {}




  public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
    if (par2 >= this.field_75151_b.size() - 9 && par2 < this.field_75151_b.size()) {

      Slot slot = this.field_75151_b.get(par2);

      if (slot != null && slot.func_75216_d())
      {
        slot.func_75215_d(null);
      }
    }

    return null;
  }



  public boolean func_94530_a(ItemStack par1ItemStack, Slot par2Slot) {
    return (par2Slot.field_75221_f > 90);
  }







  public boolean func_94531_b(Slot par1Slot) {
    return (par1Slot.field_75224_c instanceof InventoryPlayer || (par1Slot.field_75221_f > 90 && par1Slot.field_75223_e <= 162));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerCreativeTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */