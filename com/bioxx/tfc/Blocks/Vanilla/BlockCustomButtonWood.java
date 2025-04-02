package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.util.IIcon;



public class BlockCustomButtonWood
  extends BlockButtonWood
{
  public BlockCustomButtonWood() {
    func_149647_a(TFCTabs.TFC_DEVICES);
  }






  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    return TFCBlocks.planks.func_149733_h(0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomButtonWood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */