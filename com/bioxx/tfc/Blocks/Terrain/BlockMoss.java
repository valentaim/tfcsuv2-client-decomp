package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Blocks.BlockTerra;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockMoss
  extends BlockTerra
{
  public BlockMoss() {
    super(Material.field_151582_l);
    func_149675_a(true);
    func_149647_a(null);
  }






  public void func_149683_g() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
  }






  public int func_149645_b() {
    return 20;
  }







  public boolean func_149662_c() {
    return false;
  }






  public boolean func_149686_d() {
    return false;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:plants/Moss");
  }



  public IIcon func_149691_a(int side, int meta) {
    return this.field_149761_L;
  }






  public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int l = par1IBlockAccess.func_72805_g(par2, par3, par4);
    float f = 1.0F;
    float f1 = 1.0F;
    float f2 = 1.0F;
    float f3 = 0.0F;
    float f4 = 0.0F;
    float f5 = 0.0F;
    boolean flag = (l > 0);

    if ((l & 0x2) != 0) {

      f3 = Math.max(f3, 0.0625F);
      f = 0.0F;
      f1 = 0.0F;
      f4 = 1.0F;
      f2 = 0.0F;
      f5 = 1.0F;
      flag = true;
    }

    if ((l & 0x8) != 0) {

      f = Math.min(f, 0.9375F);
      f3 = 1.0F;
      f1 = 0.0F;
      f4 = 1.0F;
      f2 = 0.0F;
      f5 = 1.0F;
      flag = true;
    }

    if ((l & 0x4) != 0) {

      f5 = Math.max(f5, 0.0625F);
      f2 = 0.0F;
      f = 0.0F;
      f3 = 1.0F;
      f1 = 0.0F;
      f4 = 1.0F;
      flag = true;
    }

    if ((l & 0x1) != 0) {

      f2 = Math.min(f2, 0.9375F);
      f5 = 1.0F;
      f = 0.0F;
      f3 = 1.0F;
      f1 = 0.0F;
      f4 = 1.0F;
      flag = true;
    }

    if (!flag && canBePlacedOn(par1IBlockAccess.func_147439_a(par2, par3 + 1, par4))) {

      f1 = Math.min(f1, 0.9375F);
      f4 = 1.0F;
      f = 0.0F;
      f3 = 1.0F;
      f2 = 0.0F;
      f5 = 1.0F;
    }

    func_149676_a(f, f1, f2, f3, f4, f5);
  }







  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    return null;
  }






  public boolean func_149707_d(World par1World, int par2, int par3, int par4, int par5) {
    switch (par5) {

      case 1:
        return canBePlacedOn(par1World.func_147439_a(par2, par3 + 1, par4));
      case 2:
        return canBePlacedOn(par1World.func_147439_a(par2, par3, par4 + 1));
      case 3:
        return canBePlacedOn(par1World.func_147439_a(par2, par3, par4 - 1));
      case 4:
        return canBePlacedOn(par1World.func_147439_a(par2 + 1, par3, par4));
      case 5:
        return canBePlacedOn(par1World.func_147439_a(par2 - 1, par3, par4));
    }
    return false;
  }






  private boolean canBePlacedOn(Block block) {
    if (block == Blocks.field_150350_a) {
      return false;
    }
    return (block.func_149686_d() && block.func_149688_o().func_76230_c());
  }





  private boolean canVineStay(World par1World, int par2, int par3, int par4) {
    int l = par1World.func_72805_g(par2, par3, par4);
    int i1 = l;

    if (l > 0)
    {
      for (int j1 = 0; j1 <= 3; j1++) {

        int k1 = 1 << j1;
        if ((l & k1) != 0 && !canBePlacedOn(par1World.func_147439_a(par2 + Direction.field_71583_a[j1], par3, par4 + Direction.field_71581_b[j1])) && (par1World.func_147439_a(par2, par3 + 1, par4) != this || (par1World.func_72805_g(par2, par3 + 1, par4) & k1) == 0)) {
          i1 &= k1 ^ 0xFFFFFFFF;
        }
      }
    }
    if (i1 == 0 && !canBePlacedOn(par1World.func_147439_a(par2, par3 + 1, par4)))
    {
      return false;
    }


    if (i1 != l) {
      par1World.func_72921_c(par2, par3, par4, i1, 2);
    }
    return true;
  }








  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    if (!par1World.field_72995_K && !canVineStay(par1World, par2, par3, par4)) {

      func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
      par1World.func_147468_f(par2, par3, par4);
    }
  }






  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    if (!world.field_72995_K && world.field_73012_v.nextInt(4) == 0) {

      byte b0 = 4;
      int l = 5;
      boolean flag = false;


      int i1;


      label85: for (i1 = x - b0; i1 <= x + b0; i1++) {

        for (int i = z - b0; i <= z + b0; i++) {

          for (int j = y - 1; j <= y + 1; j++) {

            if (world.func_147439_a(i1, j, i) == this) {

              l--;
              if (l <= 0) {

                flag = true;

                break label85;
              }
            }
          }
        }
      }
      i1 = world.func_72805_g(x, y, z);
      int j1 = world.field_73012_v.nextInt(6);
      int k1 = Direction.field_71579_d[j1];




      if (j1 == 1 && y < 255 && world.func_147437_c(x, y + 1, z)) {

        if (flag) {
          return;
        }
        int l1 = world.field_73012_v.nextInt(16) & i1;
        if (l1 > 0)
        {
          for (int i2 = 0; i2 <= 3; i2++) {

            if (!canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[i2], y + 1, z + Direction.field_71581_b[i2]))) {
              l1 &= 1 << i2 ^ 0xFFFFFFFF;
            }
          }
          if (l1 > 0) {
            world.func_147465_d(x, y + 1, z, (Block)this, l1, 2);

          }
        }

      }
      else if (j1 >= 2 && j1 <= 5 && (i1 & 1 << k1) == 0) {

        if (flag) {
          return;
        }
        Block block = world.func_147439_a(x + Direction.field_71583_a[k1], y, z + Direction.field_71581_b[k1]);
        if (!block.isAir((IBlockAccess)world, x + Direction.field_71583_a[k1], y, z + Direction.field_71581_b[k1])) {

          if (block.func_149688_o().func_76218_k() && block.func_149686_d()) {
            world.func_72921_c(x, y, z, i1 | 1 << k1, 2);
          }
        } else {

          int i2 = k1 + 1 & 0x3;
          int j2 = k1 + 3 & 0x3;

          if ((i1 & 1 << i2) != 0 && canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[k1] + Direction.field_71583_a[i2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[i2]))) {
            world.func_147465_d(x + Direction.field_71583_a[k1], y, z + Direction.field_71581_b[k1], (Block)this, 1 << i2, 2);
          } else if ((i1 & 1 << j2) != 0 && canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[k1] + Direction.field_71583_a[j2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[j2]))) {
            world.func_147465_d(x + Direction.field_71583_a[k1], y, z + Direction.field_71581_b[k1], (Block)this, 1 << j2, 2);
          } else if ((i1 & 1 << i2) != 0 && world.func_147437_c(x + Direction.field_71583_a[k1] + Direction.field_71583_a[i2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[i2]) && canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[i2], y, z + Direction.field_71581_b[i2]))) {
            world.func_147465_d(x + Direction.field_71583_a[k1] + Direction.field_71583_a[i2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[i2], (Block)this, 1 << (k1 + 2 & 0x3), 2);
          } else if ((i1 & 1 << j2) != 0 && world.func_147437_c(x + Direction.field_71583_a[k1] + Direction.field_71583_a[j2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[j2]) && canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[j2], y, z + Direction.field_71581_b[j2]))) {
            world.func_147465_d(x + Direction.field_71583_a[k1] + Direction.field_71583_a[j2], y, z + Direction.field_71581_b[k1] + Direction.field_71581_b[j2], (Block)this, 1 << (k1 + 2 & 0x3), 2);
          } else if (canBePlacedOn(world.func_147439_a(x + Direction.field_71583_a[k1], y + 1, z + Direction.field_71581_b[k1]))) {
            world.func_147465_d(x + Direction.field_71583_a[k1], y, z + Direction.field_71581_b[k1], (Block)this, 0, 2);
          }
        }
      } else if (y > 1) {

        Block block = world.func_147439_a(x, y - 1, z);

        if (block.isAir((IBlockAccess)world, x, y - 1, z)) {

          int i2 = world.field_73012_v.nextInt(16) & i1;
          if (i2 > 0) {
            world.func_147465_d(x, y - 1, z, (Block)this, i2, 2);
          }
        } else if (block == this) {

          int i2 = world.field_73012_v.nextInt(16) & i1;
          int j2 = world.func_72805_g(x, y - 1, z);
          if (j2 != (j2 | i2)) {
            world.func_72921_c(x, y - 1, z, j2 | i2, 2);
          }
        }
      }
    }
  }






  public int func_149660_a(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
    byte b0 = 0;

    switch (par5) {

      case 2:
        b0 = 1;
        break;
      case 3:
        b0 = 4;
        break;
      case 4:
        b0 = 8;
        break;
      case 5:
        b0 = 2;
        break;
    }

    return (b0 != 0) ? b0 : par9;
  }






  public Item func_149650_a(int par1, Random par2Random, int par3) {
    return null;
  }






  public int func_149745_a(Random par1Random) {
    return 0;
  }







  public void func_149636_a(World par1World, EntityPlayer par2EntityPlayer, int par3, int par4, int par5, int par6) {
    super.func_149636_a(par1World, par2EntityPlayer, par3, par4, par5, par6);
  }



  public boolean isLadder(IBlockAccess world, int x, int y, int z, EntityLivingBase entity) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockMoss.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */