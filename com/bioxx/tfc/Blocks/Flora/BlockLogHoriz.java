package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;



public class BlockLogHoriz
  extends BlockLogVert
{
  protected int offset;

  public BlockLogHoriz(int off) {
    this.offset = off;
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, off, this.woodNames, 0, 8);
    System.arraycopy(Global.WOOD_ALL, off, this.woodNames, 8, 8);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    int dir = meta >> 3;
    meta = (meta & 0x7) + this.offset;

    if (dir == 0) {

      if (side == 0 || side == 1)
        return ((BlockLogNatural)TFCBlocks.logNatural).sideIcons[meta];
      if (side == 2 || side == 3) {
        return ((BlockLogNatural)TFCBlocks.logNatural).innerIcons[meta];
      }
      return ((BlockLogNatural)TFCBlocks.logNatural).rotatedSideIcons[meta];
    }


    if (side == 0 || side == 1 || side == 2 || side == 3) {
      return ((BlockLogNatural)TFCBlocks.logNatural).rotatedSideIcons[meta];
    }
    return ((BlockLogNatural)TFCBlocks.logNatural).innerIcons[meta];
  }




  public int func_149692_a(int dmg) {
    return (dmg & 0x7) + this.offset;
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < (this.woodNames.length + 1) / 2; i++) {
      list.add(new ItemStack((Block)this, 1, i));
    }
  }


  public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
    int dir = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    int metadata = world.func_72805_g(x, y, z);

    if (dir == 1 || dir == 3)
      world.func_72921_c(x, y, z, metadata + 8, 3);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogHoriz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */