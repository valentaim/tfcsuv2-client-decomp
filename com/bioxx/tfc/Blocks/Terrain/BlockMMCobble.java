package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;



public class BlockMMCobble
  extends BlockCobble
{
  public BlockMMCobble(Material material) {
    super(material);
    this.names = Global.STONE_MM;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
    this.looseStart = Global.STONE_MM_START;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockMMCobble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */