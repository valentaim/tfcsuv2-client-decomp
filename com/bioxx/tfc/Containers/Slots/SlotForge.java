package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.HeatRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;




public class SlotForge
  extends Slot
{
  public SlotForge(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }



  public boolean func_75214_a(ItemStack itemstack) {
    HeatRegistry manager = HeatRegistry.getInstance();
    return (manager.findMatchingIndex(itemstack) != null &&
      !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) &&
      !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC));
  }



  public int func_75219_a() {
    return 1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */