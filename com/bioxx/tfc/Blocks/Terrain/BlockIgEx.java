package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;



public class BlockIgEx
  extends BlockStone
{
  public BlockIgEx(Material material) {
    super(material);
    this.dropBlock = TFCBlocks.stoneIgExCobble;
    this.names = Global.STONE_IGEX;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
    this.looseStart = Global.STONE_IGEX_START;
    this.gemChance = 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgEx.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */