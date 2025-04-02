package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;



public class BlockPlanks2
  extends BlockPlanks
{
  public BlockPlanks2(Material material) {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.woodNames = new String[Global.WOOD_ALL.length - 16];
    System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
    this.icons = new net.minecraft.util.IIcon[this.woodNames.length];
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockPlanks2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */