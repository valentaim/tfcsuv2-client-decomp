package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockSand
  extends BlockTerra
{
  protected IIcon[] icons = new IIcon[Global.STONE_ALL.length];

  protected int textureOffset;

  public BlockSand(int texOff) {
    super(Material.field_151595_p);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.textureOffset = texOff;
  }

  public int func_149738_a(World p_149738_1_) {
    return 20;
  }






  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    Boolean addToCreative = Boolean.valueOf(true);

    if (addToCreative.booleanValue()) {
      int count;

      if (this.textureOffset == 0) { count = 16; }
      else { count = Global.STONE_ALL.length - 16; }

      for (int i = 0; i < count; i++) {
        list.add(new ItemStack(item, 1, i));
      }
    }
  }


  public int func_149692_a(int dmg) {
    return dmg;
  }






  public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
    int meta = bAccess.func_72805_g(x, y, z);
    if (meta >= this.icons.length) return this.icons[this.icons.length - 1];
    return this.icons[meta];
  }






  public IIcon func_149691_a(int side, int meta) {
    if (meta >= this.icons.length) return this.icons[this.icons.length - 1];
    return this.icons[meta];
  }



  public void func_149651_a(IIconRegister registerer) {
    int count = (this.textureOffset == 0) ? 16 : (Global.STONE_ALL.length - 16);
    this.icons = new IIcon[count];
    for (int i = 0; i < count; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:sand/Sand " + Global.STONE_ALL[i + this.textureOffset]);
    }
  }


  public void func_149726_b(World world, int x, int y, int z) {
    world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
  }

  public void func_149674_a(World world, int x, int y, int z, Random random) {
    BlockCollapsible.updateTickCollapsible(world, x, y, z, random, (Block)this, false);
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K) {

      BlockCollapsible.tryToFall(world, x, y, z, (Block)this);
      world.func_147464_a(x, y, z, (Block)this, func_149738_a(world));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */