package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.api.TFCItems;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockCharcoal
  extends BlockTerra
{
  public BlockCharcoal() {
    super(Material.field_151578_c);
  }



  public IIcon func_149691_a(int side, int meta) {
    return this.field_149761_L;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:devices/Charcoal");
  }



  public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  public void func_149681_a(World world, int x, int y, int z, int side, EntityPlayer entityplayer) {
    if (!world.field_72995_K)
    {
      if (entityplayer.field_71075_bZ.field_75098_d) {

        world.func_147468_f(x, y, z);

      }
      else {

        boolean isShovel = false;
        ItemStack equip = entityplayer.func_71045_bC();
        if (equip != null)
        {
          if (equip.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemCustomShovel) {
            isShovel = true;
          }
        }
        if (isShovel) {

          int top = 0;
          for (; world.func_147439_a(x, y + top + 1, z) == this; top++);

          func_149642_a(world, x, y, z, new ItemStack(TFCItems.coal, 1, 1));
          if (side - 1 > 0) {

            if (world.func_147439_a(x, y + 1, z) == this) {

              int m1 = world.func_72805_g(x, y + top, z);
              if (m1 - 1 > 0) {
                world.func_72921_c(x, y + top, z, m1 - 1, 2);
              } else {
                world.func_147468_f(x, y + top, z);
              }
              world.func_147465_d(x, y, z, this, 8, 2);
            }
            else {

              world.func_147465_d(x, y, z, this, side - 1, 2);
            }

            world.func_147471_g(x, y, z);
            world.func_147471_g(x, y + top, z);
          } else {

            world.func_147465_d(x, y, z, Blocks.field_150350_a, 0, 2);
          }
        } else {

          world.func_147465_d(x, y, z, this, side, 2);
        }

        if (side == 0) {
          world.func_147468_f(x, y, z);
        }
      }
    }
  }


  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    if (world.func_72805_g(x, y, z) > 0)
      return false;
    return world.func_147468_f(x, y, z);
  }


  public void combineCharcoalDown(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    int bottomMeta = world.func_72805_g(x, y - 1, z);

    if (bottomMeta < 8) {

      bottomMeta += meta;
      int m2 = 0;
      if (bottomMeta > 8) {

        m2 = bottomMeta - 8;
        bottomMeta = 8;
      }

      world.func_147465_d(x, y - 1, z, this, bottomMeta, 2);

      if (m2 > 0) {

        world.func_147465_d(x, y, z, this, m2, 2);
        world.func_147460_e(x, y + 1, z, this);
      } else {

        world.func_147468_f(x, y, z);
      }
    }
  }

  public void combineCharcoalUp(World world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y + 1, z);
    int bottomMeta = world.func_72805_g(x, y, z);

    if (bottomMeta < 8) {

      bottomMeta += meta;
      int m2 = 0;
      if (bottomMeta > 8) {

        m2 = bottomMeta - 8;
        bottomMeta = 8;
      }

      world.func_147465_d(x, y, z, this, bottomMeta, 2);

      if (m2 > 0) {

        world.func_147465_d(x, y + 1, z, this, m2, 2);
        world.func_147460_e(x, y + 2, z, this);
      } else {

        world.func_147468_f(x, y + 1, z);
      }
    }
  }


  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K)
    {
      if (world.func_147437_c(x, y - 1, z)) {

        int meta = world.func_72805_g(x, y, z);
        world.func_147465_d(x, y - 1, z, this, meta, 2);
        world.func_147468_f(x, y, z);
      }
      else {

        if (world.func_147439_a(x, y - 1, z) == this) {
          combineCharcoalDown(world, x, y, z);
        }
        if (world.func_147439_a(x, y + 1, z) == this) {
          combineCharcoalUp(world, x, y, z);
        }
      }
    }
  }






  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    int md = world.func_72805_g(x, y, z);

    if (md == 8) {
      return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
    }
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 0.125F * md), (z + 1));
  }



  public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
    int meta = bAccess.func_72805_g(x, y, z);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F * meta, 1.0F);
  }



  public boolean func_149659_a(Explosion ex) {
    return false;
  }



  public void onBlockExploded(World world, int x, int y, int z, Explosion ex) {
    if (!world.field_72995_K) {

      int amount = world.func_72805_g(x, y, z);
      if (amount > 0) {

        Random rand = new Random();

        amount = rand.nextInt(amount + 1) + amount / 2;
        func_149642_a(world, x, y, z, new ItemStack(TFCItems.coal, amount, 1));
      }
    }

    super.onBlockExploded(world, x, y, z, ex);
  }




  public void func_149723_a(World world, int x, int y, int z, Explosion ex) {}



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockCharcoal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */