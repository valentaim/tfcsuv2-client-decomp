package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TESpawnMeter;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockSpawnMeter
  extends BlockTerraContainer
{
  private IIcon iconTop;
  private IIcon[] icons = new IIcon[9];


  public BlockSpawnMeter() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149715_a(1.0F);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    return Math.min(meta, 8);
  }



  public boolean func_149662_c() {
    return true;
  }



  public int func_149692_a(int j) {
    return j;
  }



  public IIcon func_149691_a(int i, int j) {
    if (i < 2)
      return this.iconTop;
    return this.icons[j];
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.iconTop = iconRegisterer.func_94245_a("terrafirmacraft:devices/MeterTop");
    for (int i = 0; i < 9; i++) {
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Meter" + i);
    }
  }


  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TESpawnMeter();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockSpawnMeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */