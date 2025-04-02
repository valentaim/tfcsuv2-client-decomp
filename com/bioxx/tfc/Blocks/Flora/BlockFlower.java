package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;




public class BlockFlower
  extends BlockTerra
{
  public String[] flowerNames;
  @SideOnly(Side.CLIENT)
  protected IIcon[] icons;

  public BlockFlower() {
    super(Material.field_151585_k);
    func_149675_a(true);
    float var4 = 0.2F;
    func_149676_a(0.5F - var4, 0.0F, 0.5F - var4, 0.5F + var4, var4 * 3.0F, 0.5F + var4);
    func_149647_a(TFCTabs.TFC_DECORATION);
    this.flowerNames = new String[] { "flower_dandelion", "flower_nasturtium", "flower_meads_milkweed", "flower_tropical_milkweed", "flower_butterfly_milkweed", "flower_calendula" };
  }


  public boolean canGrowConditions(World world, int x, int y, int z, int flowerMeta) {
    float evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(x, z)).floatdata1;
    float rain = TFC_Climate.getRainfall(world, x, 144, z);
    float bioTemperature = TFC_Climate.getBioTemperatureHeight(world, x, y, z);
    if (flowerMeta == 3 && bioTemperature > 20.0F && rain > 500.0F && evt < 2.0F)
    {
      return true;
    }
    if (bioTemperature > 5.0F && rain > 250.0F)
      return true;
    return false;
  }








  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    Boolean addToCreative = Boolean.valueOf(true);

    if (addToCreative.booleanValue())
    {
      for (int i = 0; i < this.flowerNames.length; i++) {
        list.add(new ItemStack(item, 1, i));
      }
    }
  }


  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta >= this.icons.length)
      meta = 0;
    return this.icons[meta];
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.icons = new IIcon[this.flowerNames.length];

    for (int i = 0; i < this.icons.length; i++)
    {
      this.icons[i] = register.func_94245_a("terrafirmacraft:plants/" + this.flowerNames[i]);
    }
  }



  public int func_149692_a(int dmg) {
    return dmg;
  }






  public boolean func_149718_j(World world, int x, int y, int z) {
    return ((world.func_72883_k(x, y, z) >= 8 || world.func_72937_j(x, y, z)) && canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
  }



  public boolean func_149742_c(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);
    return ((world.func_147437_c(x, y, z) || block.func_149688_o().func_76222_j()) && canThisPlantGrowOnThisBlock(block));
  }


  protected boolean canThisPlantGrowOnThisBlock(Block block) {
    return (TFC_Core.isSoil(block) || TFC_Core.isFarmland(block));
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public int func_149645_b() {
    return 1;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    checkAndDropBlock(world, x, y, z);
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    checkAndDropBlock(world, x, y, z);
  }


  protected void checkAndDropBlock(World world, int x, int y, int z) {
    if (!func_149718_j(world, x, y, z)) {

      func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
      world.func_147465_d(x, y, z, func_149729_e(0), 0, 2);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */