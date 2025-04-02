package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Constant.Global;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;





public class BlockFungi
  extends BlockMushroom
{
  @SideOnly(Side.CLIENT)
  private IIcon[] icons;

  public BlockFungi() {
    float var3 = 0.2F;
    func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var3 * 2.0F, 0.5F + var3);
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }






  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    if (par2 >= this.icons.length) par2 = 0;
    return this.icons[par2];
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.icons = new IIcon[Global.FUNGI_META_NAMES.length];
    for (int i = 0; i < this.icons.length; i++)
    {

      this.icons[i] = register.func_94245_a(((this.icons.length > 2) ? "terrafirmacraft:plants/" : "") + Global.FUNGI_META_NAMES[i]);
    }
  }






  public int func_149692_a(int par1) {
    return par1;
  }






  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < this.icons.length; i++)
    {
      list.add(new ItemStack(item, 1, i));
    }
  }






  public boolean func_149718_j(World world, int x, int y, int z) {
    if (y >= 0 && y < 256) {

      Block var5 = world.func_147439_a(x, y - 1, z);
      return (var5 == Blocks.field_150391_bh || (world.func_72883_k(x, y, z) < 13 && canThisPlantGrowOnThisBlock(var5)));
    }


    return false;
  }



  protected boolean canThisPlantGrowOnThisBlock(Block block) {
    return (TFC_Core.isSoil(block) || TFC_Core.isFarmland(block));
  }






  public boolean func_149742_c(World world, int x, int y, int z) {
    Block var5 = world.func_147439_a(x, y, z);
    return ((world.func_147437_c(x, y, z) || var5.func_149688_o().func_76222_j()) &&
      canThisPlantGrowOnThisBlock(var5) &&
      func_149718_j(world, x, y, z));
  }






  public boolean func_149884_c(World world, int x, int y, int z, Random rnd) {
    Block block = world.func_147439_a(x, y, z);
    int meta = world.func_72805_g(x, y, z);
    WorldGenBigMushroom bigGen = null;

    world.func_147468_f(x, y, z);

    if (this == block && meta == 0) {
      bigGen = new WorldGenBigMushroom(0);
    } else if (this == block && meta == 1) {
      bigGen = new WorldGenBigMushroom(1);
    }
    if (bigGen != null && bigGen.func_76484_a(world, rnd, x, y, z))
    {
      return true;
    }


    world.func_147465_d(x, y, z, (Block)this, meta, 3);
    return false;
  }







  public void func_149674_a(World world, int x, int y, int z, Random rnd) {
    int meta = world.func_72805_g(x, y, z);

    if (rnd.nextInt(25) == 0) {

      byte var6 = 4;
      int var7 = 5;


      int i;


      for (i = x - var6; i <= x + var6; i++) {

        for (int m = z - var6; m <= z + var6; m++) {

          for (int n = y - 1; n <= y + 1; n++) {

            if (world.func_72899_e(i, n, m) && world.func_147439_a(i, n, m) == this) {

              var7--;
              if (var7 <= 0) {
                return;
              }
            }
          }
        }
      }
      i = x + rnd.nextInt(3) - 1;
      int j = y + rnd.nextInt(2) - rnd.nextInt(2);
      int k = z + rnd.nextInt(3) - 1;

      for (int var11 = 0; var11 < 4; var11++) {

        if (world.func_72899_e(i, j, k) && world.func_147437_c(i, j, k) && func_149718_j(world, i, j, k)) {

          x = i;
          y = j;
          z = k;
        }

        i = x + rnd.nextInt(3) - 1;
        j = y + rnd.nextInt(2) - rnd.nextInt(2);
        k = z + rnd.nextInt(3) - 1;
      }

      if (world.func_72899_e(i, j, k) && world.func_147437_c(i, j, k) && func_149718_j(world, i, j, k))
        world.func_147465_d(i, j, k, (Block)this, meta, 2);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockFungi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */