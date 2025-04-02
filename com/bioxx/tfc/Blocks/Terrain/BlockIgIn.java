package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;



public class BlockIgIn
  extends BlockStone
{
  public BlockIgIn(Material material) {
    super(material);
    this.dropBlock = TFCBlocks.stoneIgInCobble;
    this.names = Global.STONE_IGIN;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
    this.looseStart = 0;
    this.gemChance = 2;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgIn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */