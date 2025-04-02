package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.TileEntities.TEIngotPile;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockIngotPile
  extends BlockTerraContainer
{
  private Random random = new Random();


  public BlockIngotPile() {
    super(Material.field_151573_f);
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (world.field_72995_K) {

      world.func_147438_o(x, y, z).func_145829_t();
      return true;
    }


    if ((TEIngotPile)world.func_147438_o(x, y, z) != null) {


      TEIngotPile tileentityingotpile = (TEIngotPile)world.func_147438_o(x, y, z);

      if (tileentityingotpile.func_70301_a(0) == null) {

        world.func_147468_f(x, y, z);
        return false;
      }

      if (!entityplayer.func_70093_af() && tileentityingotpile.func_70301_a(0) != null) {

        if ((tileentityingotpile.func_70301_a(0)).field_77994_a > 0) {
          tileentityingotpile.injectContents(0, -1);
        }
        world.func_72838_d((Entity)new EntityItem(world, tileentityingotpile.field_145851_c, (tileentityingotpile.field_145848_d + 1), tileentityingotpile.field_145849_e, new ItemStack(tileentityingotpile
                .func_70301_a(0).func_77973_b(), 1, tileentityingotpile.func_70301_a(0).func_77960_j())));
        world.func_147460_e(x, y + 1, z, (Block)this);

        if ((tileentityingotpile.func_70301_a(0)).field_77994_a < 1) {
          world.func_147468_f(x, y, z);
        }
        world.func_147471_g(x, y, z);
      }
    }

    return true;
  }



  public void combineIngotsDown(World world, int x, int y, int z) {
    TEIngotPile teip = (TEIngotPile)world.func_147438_o(x, y, z);
    TEIngotPile teipBottom = (TEIngotPile)world.func_147438_o(x, y - 1, z);

    int bottomSize = (teipBottom.func_70301_a(0)).field_77994_a;
    int topSize = (teip.func_70301_a(0)).field_77994_a;

    if (bottomSize < 64) {

      bottomSize += topSize;
      int m2 = 0;
      if (bottomSize > 64) {

        m2 = bottomSize - 64;
        bottomSize = 64;
      }
      teipBottom.storage[0] = new ItemStack(teipBottom.storage[0].func_77973_b(), bottomSize, teipBottom.storage[0].func_77960_j());

      if (m2 > 0) {

        teip.injectContents(0, m2 - topSize);
        world.func_147460_e(x, y + 1, z, (Block)this);
        world.func_147471_g(teip.field_145851_c, teip.field_145848_d, teip.field_145849_e);

      }
      else {

        teip.storage[0] = null;
        world.func_147468_f(x, y, z);
      }
    }
  }


  public void combineIngotsUp(World world, int x, int y, int z) {
    TEIngotPile teip = (TEIngotPile)world.func_147438_o(x, y + 1, z);
    TEIngotPile teipBottom = (TEIngotPile)world.func_147438_o(x, y, z);

    int bottomSize = (teipBottom.func_70301_a(0)).field_77994_a;
    int topSize = (teip.func_70301_a(0)).field_77994_a;

    if (bottomSize < 64) {

      bottomSize += topSize;
      int m2 = 0;
      if (bottomSize > 64) {

        m2 = bottomSize - 64;
        bottomSize = 64;
      }
      teipBottom.storage[0] = new ItemStack(teipBottom.storage[0].func_77973_b(), bottomSize, teipBottom.storage[0].func_77960_j());

      if (m2 > 0) {

        teip.injectContents(0, m2 - topSize);
        world.func_147460_e(x, y + 2, z, (Block)this);
        world.func_147471_g(teip.field_145851_c, teip.field_145848_d, teip.field_145849_e);
      }
      else {

        world.func_147468_f(x, y + 1, z);
      }
    }
  }







  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);

    if (te != null && te.func_70301_a(0) != null) {
      return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + (((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D, z + 1.0D);
    }

    return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.25D, z + 1.0D);
  }





  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);

    if (te.func_70301_a(0) != null) {
      return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + (((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D, z + 1.0D);
    }
    return AxisAlignedBB.func_72330_a(x, y + 0.0D, z + 0.0D, x + 1.0D, y + 0.25D, z + 1.0D);
  }





  public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
    TEIngotPile te = (TEIngotPile)bAccess.func_147438_o(x, y, z);

    if (te.func_70301_a(0) != null) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, (float)((((te.func_70301_a(0)).field_77994_a + 7) / 8) * 0.125D), 1.0F);
    } else {
      func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.25F, 0.0F);
    }
  }
























  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  public int func_149645_b() {
    return 22;
  }


  public int getStack(World world, TEIngotPile tt) {
    if (world.func_147438_o(tt.field_145851_c, tt.field_145848_d, tt.field_145849_e) instanceof TEIngotPile) {

      TEIngotPile te = (TEIngotPile)world.func_147438_o(tt.field_145851_c, tt.field_145848_d, tt.field_145849_e);

      return (te.func_70301_a(0) != null) ? (te.func_70301_a(0)).field_77994_a : 0;
    }

    return 0;
  }




  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int side) {}



  public boolean func_149662_c() {
    return false;
  }



  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
    super.func_149689_a(world, x, y, z, entityliving, is);
    int meta = world.func_72805_g(x, y, z);

    int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    byte byte0 = 0;
    if (l == 0)
      byte0 = 8;
    if (l == 1)
      byte0 = 0;
    if (l == 2)
      byte0 = 8;
    if (l == 3) {
      byte0 = 0;
    }
    byte0 = (byte)(byte0 + meta);
    world.func_72921_c(x, y, z, byte0, 2);
  }



  public void func_149749_a(World world, int x, int y, int z, Block b, int meta) {
    TEIngotPile te = (TEIngotPile)world.func_147438_o(x, y, z);
    if (te != null) {

      for (int var6 = 0; var6 < te.func_70302_i_(); var6++) {

        ItemStack var7 = te.func_70301_a(var6);

        if (var7 != null) {

          float var8 = this.random.nextFloat() * 0.8F + 0.1F;
          float var9 = this.random.nextFloat() * 0.8F + 0.1F;


          for (float var10 = this.random.nextFloat() * 0.8F + 0.1F; var7.field_77994_a > 0; world.func_72838_d((Entity)var12)) {

            int var11 = this.random.nextInt(21) + 10;

            if (var11 > var7.field_77994_a) {
              var11 = var7.field_77994_a;
            }
            var7.field_77994_a -= var11;
            EntityItem var12 = new EntityItem(world, (x + var8), (y + var9), (z + var10), new ItemStack(var7.func_77973_b(), var11, var7.func_77960_j()));
            float var13 = 0.05F;
            var12.field_70159_w = ((float)this.random.nextGaussian() * var13);
            var12.field_70181_x = ((float)this.random.nextGaussian() * var13 + 0.2F);
            var12.field_70179_y = ((float)this.random.nextGaussian() * var13);

            if (var7.func_77942_o())
              var12.func_92059_d().func_77982_d((NBTTagCompound)var7.func_77978_p().func_74737_b());
          }
        }
      }
      super.func_149749_a(world, x, y, z, b, meta);
    }
  }



  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }



  public void func_149725_f(World world, int i, int j, int k, int meta) {
    if (!world.field_72995_K) {

      TEIngotPile te = (TEIngotPile)world.func_147438_o(i, j, k);
      if (te != null && te.func_70301_a(0) != null) {

        EntityItem ei = new EntityItem(world, i, j, k, te.func_70301_a(0));
        world.func_72838_d((Entity)ei);
      }
    }
  }



  public boolean func_149686_d() {
    return false;
  }


  public static int getAnvilTypeFromMeta(int j) {
    int l = 7;
    return j & l;
  }


  public static int getDirectionFromMetadata(int i) {
    int d = i >> 3;

    if (d == 1) {
      return 1;
    }
    return 0;
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEIngotPile();
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!world.field_72995_K)
    {
      if (!world.isSideSolid(x, y - 1, z, ForgeDirection.UP) && world.func_147438_o(x, y, z) instanceof TEIngotPile) {

        TEIngotPile ingotPile = (TEIngotPile)world.func_147438_o(x, y, z);
        Item ingot = (ingotPile.storage[0] != null) ? ingotPile.storage[0].func_77973_b() : null;

        if (world.func_147439_a(x, y - 1, z) == this && world.func_147438_o(x, y - 1, z) instanceof TEIngotPile) {

          TEIngotPile lowerPile = (TEIngotPile)world.func_147438_o(x, y - 1, z);
          Item lowerIngot = (lowerPile.storage[0] != null) ? lowerPile.storage[0].func_77973_b() : null;

          if (ingot == lowerIngot) {
            combineIngotsDown(world, x, y, z);
          }
        } else if (world.func_147439_a(x, y + 1, z) == this && world.func_147438_o(x, y + 1, z) instanceof TEIngotPile) {

          TEIngotPile upperPile = (TEIngotPile)world.func_147438_o(x, y + 1, z);
          Item upperIngot = (upperPile.storage[0] != null) ? upperPile.storage[0].func_77973_b() : null;

          if (ingot == upperIngot) {
            combineIngotsUp(world, x, y, z);
          }
        } else {

          ingotPile.ejectContents();
          world.func_147468_f(x, y, z);
          return;
        }
      }
    }
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */