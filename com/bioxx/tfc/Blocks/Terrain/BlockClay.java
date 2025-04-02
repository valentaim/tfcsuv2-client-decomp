package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockClay
  extends BlockTerra
{
  protected IIcon[] dirtTexture;
  protected int textureOffset;

  public BlockClay(int texOff) {
    super(Material.field_151571_B);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.textureOffset = texOff;
  }




  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
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


  public void func_149651_a(IIconRegister registerer) {
    int count = (this.textureOffset == 0) ? 16 : (Global.STONE_ALL.length - 16);
    this.dirtTexture = new IIcon[count];
    for (int i = 0; i < count; i++) {
      this.dirtTexture[i] = registerer.func_94245_a("terrafirmacraft:clay/Clay " + Global.STONE_ALL[i + this.textureOffset]);
    }
  }





  public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
    int meta = bAccess.func_72805_g(x, y, z);
    if (meta >= this.dirtTexture.length) return this.dirtTexture[this.dirtTexture.length - 1];
    return this.dirtTexture[meta];
  }






  public IIcon func_149691_a(int side, int meta) {
    if (meta >= this.dirtTexture.length) return this.dirtTexture[this.dirtTexture.length - 1];
    return this.dirtTexture[meta];
  }






  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return TFCItems.clayBall;
  }






  public int func_149745_a(Random rand) {
    return rand.nextInt(3) + 1;
  }



  public int func_149692_a(int dmg) {
    return dmg;
  }







  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    int count = func_149745_a(world.field_73012_v);
    Item item = func_149650_a(metadata, world.field_73012_v, fortune);
    for (int i = 0; i < count; i++)
      ret.add(new ItemStack(item, 1, 0));
    return ret;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockClay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */