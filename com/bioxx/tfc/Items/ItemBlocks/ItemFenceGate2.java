package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;


public class ItemFenceGate2
  extends ItemTerraBlock
{
  public ItemFenceGate2(Block par1) {
    super(par1);
    this.metaNames = new String[Global.WOOD_ALL.length - 16];
    System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, 0, Global.WOOD_ALL.length - 16);
  }

  public void func_94581_a(IIconRegister registerer) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemFenceGate2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */