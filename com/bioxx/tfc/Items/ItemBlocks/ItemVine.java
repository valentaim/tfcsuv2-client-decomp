package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.ColorizerFoliageTFC;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;


public class ItemVine
  extends ItemTerraBlock
{
  public ItemVine(Block b) {
    super(b);
  }


  
  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack is, int par2) {
    return ColorizerFoliageTFC.getFoliageColorBasic();
  }


  
  @SideOnly(Side.CLIENT)
  public IIcon func_77617_a(int par1) {
    return TFCBlocks.vine.func_149691_a(0, 0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemVine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */