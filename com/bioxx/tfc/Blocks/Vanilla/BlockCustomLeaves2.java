package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;





public class BlockCustomLeaves2
  extends BlockCustomLeaves
{
  public BlockCustomLeaves2() {
    this.woodNames = new String[Global.WOOD_ALL.length - 16];
    System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
    this.icons = new net.minecraft.util.IIcon[this.woodNames.length];
    this.iconsOpaque = new net.minecraft.util.IIcon[this.woodNames.length];
  }



  public Item func_149650_a(int i, Random rand, int j) {
    return Item.func_150898_a(TFCBlocks.sapling2);
  }



  protected void dropSapling(World world, int x, int y, int z, int meta) {
    if (meta != 0)
      func_149642_a(world, x, y, z, new ItemStack(func_149650_a(0, (Random)null, 0), 1, meta));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomLeaves2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */