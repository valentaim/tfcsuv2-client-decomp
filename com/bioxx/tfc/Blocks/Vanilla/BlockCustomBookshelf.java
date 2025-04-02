package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;



public class BlockCustomBookshelf
  extends BlockTerra
{
  public BlockCustomBookshelf() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_BUILDING);
  }


  
  public IIcon func_149691_a(int par1, int par2) {
    return (par1 != 1 && par1 != 0) ? super.func_149691_a(par1, par2) : TFCBlocks.planks.func_149733_h(par1);
  }


  
  public int func_149745_a(Random par1Random) {
    return 3;
  }


  
  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return Items.field_151122_aG;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomBookshelf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */