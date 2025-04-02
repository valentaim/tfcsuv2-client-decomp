package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.oredict.OreDictionary;





public class BlockCustomLeaves
  extends BlockLeaves
  implements IShearable
{
  protected int[][][] adjacentTreeBlocks;
  protected String[] woodNames;
  protected IIcon[] icons;
  protected IIcon[] iconsOpaque;

  public BlockCustomLeaves() {
    func_149675_a(false);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.icons = new IIcon[16];
    this.iconsOpaque = new IIcon[16];
    func_149675_a(false);
  }





  public void func_149666_a(Item item, CreativeTabs tabs, List list) {}




  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }



  public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
    return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
  }




  public void func_149743_a(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_, List<AxisAlignedBB> p_149743_6_, Entity p_149743_7_) {
    if (p_149743_7_ instanceof EntityPlayer || p_149743_7_ instanceof net.minecraft.entity.passive.EntityCow || p_149743_7_ instanceof net.minecraft.entity.passive.EntityHorse || (p_149743_7_ != null && p_149743_7_
      .getClass().getSimpleName().toLowerCase().contains("ostri")))
      return;  AxisAlignedBB axisalignedbb1 = func_149668_a(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_);

    if (axisalignedbb1 != null && p_149743_5_.func_72326_a(axisalignedbb1))
    {
      p_149743_6_.add(axisalignedbb1);
    }
  }



  public void func_149670_a(World world, int x, int y, int z, Entity entity) {
    entity.field_70159_w *= 0.1D;
    entity.field_70179_y *= 0.1D;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
    Block block = world.func_147439_a(x, y, z);


    if (side == 0 && this.field_149760_C > 0.0D)
      return true;
    if (side == 1 && this.field_149756_F < 1.0D)
      return true;
    if (side == 2 && this.field_149754_D > 0.0D)
      return true;
    if (side == 3 && this.field_149757_G < 1.0D)
      return true;
    if (side == 4 && this.field_149759_B > 0.0D)
      return true;
    if (side == 5 && this.field_149755_E < 1.0D) {
      return true;
    }
    return !block.func_149662_c();
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    func_149695_a(world, x, y, z, (Block)null);
  }




  public void beginLeavesDecay(World world, int x, int y, int z) {}




  public void func_149695_a(World world, int xOrig, int yOrig, int zOrig, Block b) {
    if (!world.field_72995_K) {

      int var6 = world.func_72805_g(xOrig, yOrig, zOrig);

      byte searchRadius = 4;
      int maxDist = searchRadius + 1;
      byte searchDistance = 11;
      int center = searchDistance / 2;
      this.adjacentTreeBlocks = (int[][][])null;
      if (this.adjacentTreeBlocks == null) {
        this.adjacentTreeBlocks = new int[searchDistance][searchDistance][searchDistance];
      }
      if (world.func_72904_c(xOrig - maxDist, yOrig - maxDist, zOrig - maxDist, xOrig + maxDist, yOrig + maxDist, zOrig + maxDist)) {

        for (int xd = -searchRadius; xd <= searchRadius; xd++) {

          int searchY = searchRadius - Math.abs(xd);
          for (int yd = -searchY; yd <= searchY; yd++) {

            int searchZ = searchY - Math.abs(yd);
            for (int zd = -searchZ; zd <= searchZ; zd++) {

              Block block = world.func_147439_a(xOrig + xd, yOrig + yd, zOrig + zd);

              if (block == TFCBlocks.logNatural || block == TFCBlocks.logNatural2) {
                this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = 0;
              } else if (block == this && var6 == world.func_72805_g(xOrig + xd, yOrig + yd, zOrig + zd)) {
                this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -2;
              } else {
                this.adjacentTreeBlocks[xd + center][yd + center][zd + center] = -1;
              }
            }
          }
        }
        for (int pass = 1; pass <= 4; pass++) {

          for (int i = -searchRadius; i <= searchRadius; i++) {

            int searchY = searchRadius - Math.abs(i);
            for (int yd = -searchY; yd <= searchY; yd++) {

              int searchZ = searchY - Math.abs(yd);
              for (int zd = -searchZ; zd <= searchZ; zd++) {

                if (this.adjacentTreeBlocks[i + center][yd + center][zd + center] == pass - 1) {

                  if (this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] == -2) {
                    this.adjacentTreeBlocks[i + center - 1][yd + center][zd + center] = pass;
                  }
                  if (this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] == -2) {
                    this.adjacentTreeBlocks[i + center + 1][yd + center][zd + center] = pass;
                  }
                  if (this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] == -2) {
                    this.adjacentTreeBlocks[i + center][yd + center - 1][zd + center] = pass;
                  }
                  if (this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] == -2) {
                    this.adjacentTreeBlocks[i + center][yd + center + 1][zd + center] = pass;
                  }
                  if (this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] == -2) {
                    this.adjacentTreeBlocks[i + center][yd + center][zd + center - 1] = pass;
                  }
                  if (this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] == -2) {
                    this.adjacentTreeBlocks[i + center][yd + center][zd + center + 1] = pass;
                  }
                }
              }
            }
          }
        }
      }
      int res = this.adjacentTreeBlocks[center][center][center];

      if (res < 0)
      {
        if (world.func_72938_d(xOrig, zOrig) != null) {
          destroyLeaves(world, xOrig, yOrig, zOrig);
        }
      }
    }
  }

  private void destroyLeaves(World world, int x, int y, int z) {
    world.func_147468_f(x, y, z);
  }


  private void removeLeaves(World world, int x, int y, int z) {
    func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
    if (world.field_73012_v.nextInt(100) < 30)
      func_149642_a(world, x, y, z, new ItemStack(TFCItems.stick, 1));
    world.func_147468_f(x, y, z);
  }



  public int func_149745_a(Random rand) {
    return (rand.nextInt(20) != 0) ? 0 : 1;
  }



  public Item func_149650_a(int i, Random rand, int j) {
    return Item.func_150898_a(TFCBlocks.sapling);
  }




  public void func_149690_a(World world, int x, int y, int z, int meta, float f, int i1) {}




  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int meta) {
    if (!world.field_72995_K) {

      ItemStack itemstack = entityplayer.field_71071_by.func_70448_g();
      int[] equipIDs = OreDictionary.getOreIDs(itemstack);
      for (int id : equipIDs) {

        String name = OreDictionary.getOreName(id);
        if (name.startsWith("itemScythe")) {

          for (int x = -1; x < 2; x++) {

            for (int z = -1; z < 2; z++) {

              for (int y = -1; y < 2; y++) {

                if (world.func_147439_a(i + x, j + y, k + z).func_149688_o() == Material.field_151584_j && entityplayer.field_71071_by
                  .func_70301_a(entityplayer.field_71071_by.field_70461_c) != null) {

                  entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
                  entityplayer.func_71020_j(0.045F);
                  if (world.field_73012_v.nextInt(100) < 11) {
                    func_149642_a(world, i + x, j + y, k + z, new ItemStack(TFCItems.stick, 1));
                  } else if (world.field_73012_v.nextInt(100) < 4 && TFCOptions.enableSaplingDrops) {
                    dropSapling(world, i + x, j + y, k + z, meta);
                  }  removeLeaves(world, i + x, j + y, k + z);
                  super.func_149636_a(world, entityplayer, i + x, j + y, k + z, meta);

                  itemstack.func_77972_a(1, (EntityLivingBase)entityplayer);
                  if (itemstack.field_77994_a == 0) {
                    entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, null);
                  }
                }
              }
            }
          }

          return;
        }
      }
      entityplayer.func_71064_a(StatList.field_75934_C[func_149682_b((Block)this)], 1);
      entityplayer.func_71020_j(0.025F);
      if (world.field_73012_v.nextInt(100) < 28) {
        func_149642_a(world, i, j, k, new ItemStack(TFCItems.stick, 1));
      } else if (world.field_73012_v.nextInt(100) < 6 && TFCOptions.enableSaplingDrops) {
        dropSapling(world, i, j, k, meta);
      }
      super.func_149636_a(world, entityplayer, i, j, k, meta);
    }
  }



  protected void dropSapling(World world, int x, int y, int z, int meta) {
    if (meta != 9 && meta != 15) {
      func_149642_a(world, x, y, z, new ItemStack(func_149650_a(0, (Random)null, 0), 1, meta));
    }
  }


  public int func_149692_a(int dmg) {
    return dmg;
  }



  public boolean func_149662_c() {
    return false;
  }



  public IIcon func_149691_a(int side, int meta) {
    if (meta > this.woodNames.length - 1)
      meta = 0;
    if (TerraFirmaCraft.proxy.getGraphicsLevel()) {
      return this.icons[meta];
    }
    return this.iconsOpaque[meta];
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < this.woodNames.length; i++) {

      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves Fancy");
      this.iconsOpaque[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/trees/" + this.woodNames[i] + " Leaves");
    }
  }



  public String[] func_150125_e() {
    return (String[])this.woodNames.clone();
  }



  public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */