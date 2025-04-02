package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.registry.GameData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class SlotLogPile
  extends Slot
{
  public SlotLogPile(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
    super(iinventory, i, j, k);
  }




  public boolean func_75214_a(ItemStack itemstack) {
    Item fromcook = (Item)GameData.getItemRegistry().func_82594_a("cookingwithtfc:item.Log");
    if (fromcook != null) return (itemstack.func_77973_b() == TFCItems.logs || itemstack.func_77973_b() == fromcook);
    return (itemstack.func_77973_b() == TFCItems.logs);
  }



  public int func_75219_a() {
    return 4;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotLogPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */