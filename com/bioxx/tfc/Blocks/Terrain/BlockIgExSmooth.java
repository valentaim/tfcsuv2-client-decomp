package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;



public class BlockIgExSmooth
  extends BlockSmooth
{
  public BlockIgExSmooth() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.names = Global.STONE_IGEX;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgExSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */