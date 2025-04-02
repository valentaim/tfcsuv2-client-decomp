package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;



public class BlockSedSmooth
  extends BlockSmooth
{
  public BlockSedSmooth() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.names = Global.STONE_SED;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSedSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */