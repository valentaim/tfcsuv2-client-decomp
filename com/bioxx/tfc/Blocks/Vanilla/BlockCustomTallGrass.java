package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.ColorizerFoliageTFC;
import com.bioxx.tfc.Core.ColorizerGrassTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.oredict.OreDictionary;

public class BlockCustomTallGrass
  extends BlockTallGrass
  implements IShearable
{
  private static final String[] META_NAMES = new String[] { "tallgrass", "fern", "shortgrass" };

  @SideOnly(Side.CLIENT)
  private IIcon[] icons;


  public BlockCustomTallGrass() {
    float var3 = 0.4F;
    func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, 0.8F, 0.5F + var3);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }



  public void func_149666_a(Item item, CreativeTabs tab, List<ItemStack> list) {
    for (int i = 0; i < META_NAMES.length; i++)
    {
      list.add(new ItemStack(item, 1, i));
    }
  }



  public int func_149635_D() {
    double var1 = 0.5D;
    double var3 = 1.0D;
    return ColorizerGrassTFC.getGrassColor(var1, var3);
  }



  public int func_149741_i(int par1) {
    return (par1 == 0) ? 16777215 : ColorizerFoliageTFC.getFoliageColorBasic();
  }



  public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
    return TerraFirmaCraft.proxy.grassColorMultiplier(bAccess, x, y, z);
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public int func_149679_a(int i, Random rand) {
    return 1 + rand.nextInt(i * 2 + 1);
  }



  public void func_149636_a(World world, EntityPlayer player, int i, int j, int k, int l) {
    super.func_149636_a(world, player, i, j, k, l);

    ItemStack is = player.field_71071_by.func_70448_g();
    int[] equipIDs = OreDictionary.getOreIDs(is);

    for (int id : equipIDs) {

      String name = OreDictionary.getOreName(id);
      if (name.startsWith("itemKnife")) {

        createStraw(world, player, i, j, k);
        is.func_77972_a(1, (EntityLivingBase)player);
        if (is.field_77994_a == 0)
          player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
        break;
      }
      if (name.startsWith("itemScythe")) {


        createStraw(world, player, i, j, k);

        for (int x = -1; x < 2; x++) {

          for (int z = -1; z < 2; z++) {

            if (world.func_147439_a(i + x, j, k + z) == this) {

              createStraw(world, player, i + x, j, k + z);
              is.func_77972_a(1, (EntityLivingBase)player);
              if (is.field_77994_a == 0)
                player.field_71071_by.func_70299_a(player.field_71071_by.field_70461_c, null);
              world.func_147468_f(i + x, j, k + z);
            }
          }
        }
        break;
      }
    }
  }


  private void createStraw(World world, EntityPlayer player, int i, int j, int k) {
    EntityItem ei = new EntityItem(world, (i + 0.5F), (j + 0.5F), (k + 0.5F), new ItemStack(TFCItems.straw, 1));
    world.func_72838_d((Entity)ei);
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    ItemStack item = getSeeds(world.field_73012_v);
    if (item != null)
      ret.add(item);
    return ret;
  }



  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return true;
  }



  public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    ret.add(new ItemStack((Block)this, 1, world.func_72805_g(x, y, z)));
    return ret;
  }


  protected boolean canThisPlantGrowOnThisBlock(Block block) {
    return TFC_Core.isSoil(block);
  }






  public boolean func_149718_j(World world, int x, int y, int z) {
    return ((world.func_72883_k(x, y, z) >= 8 || world
      .func_72937_j(x, y, z)) &&
      canThisPlantGrowOnThisBlock(world.func_147439_a(x, y - 1, z)));
  }


  public static ItemStack getSeeds(Random r) {
    ItemStack is = null;









































    return is;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    this.icons = new IIcon[META_NAMES.length];
    for (int i = 0; i < this.icons.length; i++)
    {
      this.icons[i] = register.func_94245_a(((i > 1) ? "terrafirmacraft:plants/" : "") + META_NAMES[i]);
    }
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta >= this.icons.length) meta = 0;
    return this.icons[meta];
  }




  public void func_149674_a(World w, int x, int y, int z, Random rand) {
    float temp = TFC_Climate.getHeightAdjustedTemp(w, x, y, z);

    if (!w.field_72995_K && w.func_72957_l(x, y, z) < 7 && temp > 12.77D) {

      if (w.field_73012_v.nextInt(Math.max((int)(160.0F / (temp - 4.0F)), 1)) < 2) {

        float vol = 0.1F + w.field_73012_v.nextFloat() * 0.2F;
        float pitch = temp / 100.0F * 2.0F + 0.5F + vol;
        w.func_72908_a(x, y, z, "terrafirmacraft:mob.cricket", vol, pitch);
      }

      if (rand.nextInt(8) == 0) {
        w.func_147464_a(x, y, z, (Block)this, 5);
      }
    }

    super.func_149674_a(w, x, y, z, rand);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */