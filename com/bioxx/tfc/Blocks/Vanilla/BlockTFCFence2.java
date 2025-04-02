package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;



public class BlockTFCFence2
  extends BlockTFCFence
{
  public BlockTFCFence2(String str, Material mat) {
    super(str, mat);
    this.woodNames = new String[Global.WOOD_ALL.length - 16];
    System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
    this.iconsPost = new net.minecraft.util.IIcon[this.woodNames.length];
    this.iconsPostTop = new net.minecraft.util.IIcon[this.woodNames.length];
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockTFCFence2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */