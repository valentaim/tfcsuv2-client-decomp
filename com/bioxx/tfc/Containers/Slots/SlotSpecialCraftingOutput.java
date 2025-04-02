package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.Containers.ContainerSpecialCrafting;
import com.bioxx.tfc.GUI.GuiKnapping;
import com.bioxx.tfc.TerraFirmaCraft;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotSpecialCraftingOutput
  extends Slot
{
  private final IInventory craftMatrix;
  private EntityPlayer thePlayer;
  private Container container;

  public SlotSpecialCraftingOutput(Container container, EntityPlayer entityplayer, IInventory iinventory, IInventory iinventory1, int i, int j, int k) {
    super(iinventory1, i, j, k);
    this.container = container;
    this.thePlayer = entityplayer;
    this.craftMatrix = iinventory;
  }



  public boolean func_75214_a(ItemStack itemstack) {
    return false;
  }




  public void func_82870_a(EntityPlayer player, ItemStack itemstack) {
    itemstack.func_77980_a(this.thePlayer.field_70170_p, this.thePlayer, this.field_75222_d);
    TerraFirmaCraft.proxy.takenFromCrafting(this.thePlayer, itemstack, this.craftMatrix);

    for (int i = 0; i < this.craftMatrix.func_70302_i_(); i++) {


      this.craftMatrix.func_70299_a(i, null);
      if (player.field_70170_p.field_72995_K)
      {
        ((GuiKnapping)(Minecraft.func_71410_x()).field_71462_r).resetButton(i);
      }
    }


    ((ContainerSpecialCrafting)this.container).setDecreasedStack(Boolean.valueOf(false));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotSpecialCraftingOutput.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */