package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;



public class BlockFireBrick
  extends BlockTerra
{
  public BlockFireBrick() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_BUILDING);
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:rocks/Fire Brick");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockFireBrick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */