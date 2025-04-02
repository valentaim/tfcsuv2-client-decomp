package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;



public class BlockSed
  extends BlockStone
{
  public BlockSed(Material material) {
    super(material);
    this.dropBlock = TFCBlocks.stoneSedCobble;
    this.names = Global.STONE_SED;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
    this.looseStart = Global.STONE_SED_START;
    this.gemChance = 1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */