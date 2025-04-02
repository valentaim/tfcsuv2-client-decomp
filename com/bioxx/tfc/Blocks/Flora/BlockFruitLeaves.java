package com.bioxx.tfc.Blocks.Flora;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.FloraIndex;
import com.bioxx.tfc.Food.FloraManager;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEFruitLeaves;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.Util.Helper;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockFruitLeaves
  extends BlockTerraContainer
{
  private String[] woodNames = Global.FRUIT_META_NAMES;
  private IIcon[] icons = new IIcon[16];

  public static IIcon[] iconsFruit = new IIcon[16];
  private IIcon[] iconsOpaque = new IIcon[16];

  public static IIcon[] iconsFlowers = new IIcon[16];




  public BlockFruitLeaves(int offset) {
    super(Material.field_151584_j);
    func_149675_a(true);
  }




  public int func_149720_d(IBlockAccess bAccess, int x, int y, int z) {
    return TerraFirmaCraft.proxy.foliageColorMultiplier(bAccess, x, y, z);
  }



  public int func_149645_b() {
    return TFCBlocks.leavesFruitRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public IIcon func_149691_a(int side, int meta) {
    if (TerraFirmaCraft.proxy.getGraphicsLevel()) {
      return this.icons[meta & 0x7];
    }
    return this.iconsOpaque[meta & 0x7];
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < 9; i++) {

      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/fruit trees/" + this.woodNames[i] + " Leaves");
      this.iconsOpaque[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/fruit trees/" + this.woodNames[i] + " Leaves Opaque");


      iconsFruit[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/fruit trees/" + this.woodNames[i] + " Fruit");
      iconsFlowers[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/fruit trees/" + this.woodNames[i] + " Flowers");
    }
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
    return true;
  }



  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    lifeCycle(world, x, y, z);
  }


  private void lifeCycle(World world, int x, int y, int z) {
    if (!world.field_72995_K) {

      if (!canStay(world, x, y, z)) {

        destroyLeaves(world, x, y, z);

        return;
      }
      Random rand = new Random();
      int meta = world.func_72805_g(x, y, z);
      int m = meta - 8;

      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(getType((Block)this, m));
      FloraIndex fi2 = manager.findMatchingIndex(getType((Block)this, meta));

      float temp = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
      TEFruitLeaves te = (TEFruitLeaves)world.func_147438_o(x, y, z);
      if (te != null) {

        if (fi2 != null)
        {
          if (temp >= fi2.minTemp && temp < fi2.maxTemp) {

            if (fi2.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) && !te.hasFruit && TFC_Time.getMonthsSinceDay(te.dayHarvested) > 1)
            {
              if (meta < 8) {

                meta += 8;
                te.hasFruit = true;
                te.dayFruited = TFC_Time.getTotalDays();
              }
              world.func_72921_c(x, y, z, meta, 2);

            }

          }
          else if (meta >= 8 && rand.nextInt(10) == 0) {

            if (te.hasFruit) {

              te.hasFruit = false;
              world.func_72921_c(x, y, z, meta - 8, 2);
            }
          }
        }


        if (fi != null)
        {
          if (!fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)))
          {
            if (world.func_72805_g(x, y, z) >= 8)
            {
              if (te.hasFruit) {

                te.hasFruit = false;
                world.func_72921_c(x, y, z, meta - 8, 2);
              }
            }
          }
        }

        if (rand.nextInt(100) > 50) {
          world.func_147471_g(x, y, z);
        }
      }
    }
  }


  public static boolean canStay(World world, int x, int y, int z) {
    for (int i = 1; i >= -1; i--) {

      for (int j = 0; j >= -1; j--) {

        for (int k = 1; k >= -1; k--) {

          if (world.func_147439_a(i + x, j + y, k + z) == TFCBlocks.fruitTreeWood && world
            .func_147439_a(i + x, j + y + 1, k + z) != TFCBlocks.fruitTreeWood) {
            return true;
          }
        }
      }
    }
    return false;
  }


  public static String getType(Block block, int meta) {
    if (block == TFCBlocks.fruitTreeLeaves) {

      switch (meta) {
        case 0:
          return Global.FRUIT_META_NAMES[0];
        case 1: return Global.FRUIT_META_NAMES[1];
        case 2: return Global.FRUIT_META_NAMES[2];
        case 3: return Global.FRUIT_META_NAMES[3];
        case 4: return Global.FRUIT_META_NAMES[4];
        case 5: return Global.FRUIT_META_NAMES[5];
        case 6: return Global.FRUIT_META_NAMES[6];
        case 7: return Global.FRUIT_META_NAMES[7];
      }


    } else {
      switch (meta) {
        case 0:
          return Global.FRUIT_META_NAMES[8];
      }
    }
    return "";
  }



  public void func_149695_a(World world, int x, int y, int z, Block b) {
    super.func_149695_a(world, x, y, z, b);
    lifeCycle(world, x, y, z);
  }


  private void destroyLeaves(World world, int x, int y, int z) {
    world.func_147468_f(x, y, z);
  }





  public void func_149690_a(World world, int x, int y, int z, int meta, float dropChance, int fortune) {}




  public void func_149699_a(World world, int x, int y, int z, EntityPlayer entityplayer) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(getType((Block)this, world.func_72805_g(x, y, z) & 0x7));

      if (fi != null && (fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) || (fi.inHarvest((TFC_Time.getSeasonAdjustedMonth(z) + 11) % 12) && (meta & 0x8) == 8))) {

        TEFruitLeaves te = (TEFruitLeaves)world.func_147438_o(x, y, z);
        if (te != null && te.hasFruit) {

          te.hasFruit = false;
          te.dayHarvested = TFC_Time.getTotalDays();
          world.func_72921_c(x, y, z, meta - 8, 3);
          func_149642_a(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(), Helper.roundNumber(4.0F + world.field_73012_v.nextFloat() * 12.0F, 10.0F)));
        }
      }
    }
  }




  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      FloraManager manager = FloraManager.getInstance();
      FloraIndex fi = manager.findMatchingIndex(getType((Block)this, world.func_72805_g(x, y, z) & 0x7));

      if (fi != null && (fi.inHarvest(TFC_Time.getSeasonAdjustedMonth(z)) || (fi.inHarvest((TFC_Time.getSeasonAdjustedMonth(z) + 11) % 12) && (meta & 0x8) == 8))) {

        TEFruitLeaves te = (TEFruitLeaves)world.func_147438_o(x, y, z);
        if (te != null && te.hasFruit) {

          te.hasFruit = false;
          te.dayHarvested = TFC_Time.getTotalDays();
          world.func_72921_c(x, y, z, meta - 8, 3);
          func_149642_a(world, x, y, z, ItemFoodTFC.createTag(fi.getOutput(), Helper.roundNumber(4.0F + world.field_73012_v.nextFloat() * 12.0F, 10.0F)));
          return true;
        }
      }
    }
    return false;
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEFruitLeaves();
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockFruitLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */