package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;




public class BlockFlora
  extends BlockTerra
{
  private IIcon[] icons;
  private String[] metaNames;

  public BlockFlora() {
    super(Material.field_151585_k);
    this.metaNames = new String[] { "Golden Rod", "Cat Tails" };
    this.icons = new IIcon[this.metaNames.length];
    func_149676_a(0.3F, 0.0F, 0.3F, 0.7F, 0.7F, 0.7F);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }



  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    return null;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return 1;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) {
    for (int i = 0; i < this.icons.length; i++) {
      this.icons[i] = par1IconRegister.func_94245_a("terrafirmacraft:plants/" + this.metaNames[i]);
    }
  }


  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    if (par2 >= this.icons.length)
      par2 = 0;
    return this.icons[par2];
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    return ((world.func_72883_k(x, y, z) >= 8 || world
      .func_72937_j(x, y, z)) &&
      canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
  }



  public void func_149695_a(World world, int i, int j, int k, Block par5) {
    super.func_149695_a(world, i, j, k, par5);
    if (!func_149718_j(world, i, j, k)) {
      world.func_147468_f(i, j, k);
    }
  }


  public boolean func_149742_c(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return ((world.func_147437_c(x, y, z) || block.func_149688_o().func_76222_j()) && canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
  }


  protected boolean canThisPlantGrowOnThisBlock(Block block) {
    return (TFC_Core.isSoil(block) || TFC_Core.isFarmland(block));
  }



  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return Item.func_150898_a((Block)this);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFlora.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */