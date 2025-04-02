package com.bioxx.tfc.Items;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class ItemBackpack
  extends ItemTerra
{
  public ItemBackpack() {
    setWeight(EnumWeight.HEAVY);
    setSize(EnumSize.HUGE);
  }

  public void func_77622_d(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBackpack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */