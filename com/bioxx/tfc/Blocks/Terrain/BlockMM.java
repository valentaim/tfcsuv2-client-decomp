package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.material.Material;



public class BlockMM
  extends BlockStone
{
  public static boolean fallInstantly;

  public BlockMM(Material material) {
    super(material);
    this.dropBlock = TFCBlocks.stoneMMCobble;
    this.names = Global.STONE_MM;
    this.icons = new net.minecraft.util.IIcon[this.names.length];
    this.looseStart = Global.STONE_MM_START;
    this.gemChance = 3;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockMM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */