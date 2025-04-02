package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Metal;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class ItemAnvil1
  extends ItemAnvil
{
  public ItemAnvil1(Block par1) {
    super(par1);
    this.metaNames = new String[] { "Stone", "Copper", "Bronze", "Wrought Iron", "Steel", "Black Steel", "Blue Steel", "Red Steel" };
  }


  
  public Metal getMetalType(ItemStack is) {
    int meta = is.func_77960_j();
    switch (meta) {
      case 1:
        return Global.COPPER;
      case 2: return Global.BRONZE;
      case 3: return Global.WROUGHTIRON;
      case 4: return Global.STEEL;
      case 5: return Global.BLACKSTEEL;
      case 6: return Global.BLUESTEEL;
      case 7: return Global.REDSTEEL;
    }  return Global.UNKNOWN;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemAnvil1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */