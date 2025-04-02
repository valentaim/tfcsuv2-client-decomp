package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class ItemToolRack
  extends ItemTerraBlock
{
  public ItemToolRack(Block par1) {
    super(par1);
    this.metaNames = Global.WOOD_ALL;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.LIGHT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */