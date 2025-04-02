package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;


public class BlockWorkbench
  extends BlockTerra
{
  @SideOnly(Side.CLIENT)
  private IIcon topIcon;
  @SideOnly(Side.CLIENT)
  private IIcon frontIcon;

  public BlockWorkbench() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    if (par1 == 1)
      return this.topIcon;
    if (par1 == 0)
      return TFCBlocks.planks.func_149733_h(par1);
    if (par1 != 2 && par1 != 4) {
      return this.field_149761_L;
    }
    return this.frontIcon;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("crafting_table_side");
    this.topIcon = par1IconRegister.func_94245_a("crafting_table_top");
    this.frontIcon = par1IconRegister.func_94245_a("crafting_table_front");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */