package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TESluice;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockSluice
  extends BlockContainer
{
  public static final int[][] HEAD_FOOT_BLOCKMAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };


  public BlockSluice() {
    super(Material.field_151575_d);
    this.field_149789_z = true;
  }





  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess world, int x, int y, int z, int side) {
    return true;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    int meta = world.func_72805_g(i, j, k);






    if (world.field_72995_K) {
      return true;
    }

    if (!isBlockFootOfBed(meta) &&
      (TESluice)world.func_147438_o(i, j, k) != null) {


      TESluice tileentitysluice = (TESluice)world.func_147438_o(i, j, k);
      ItemStack is = entityplayer.func_71045_bC();
      if (is != null && is.func_77973_b() == TFCItems.goldPan && is.func_77960_j() != 0) {

        tileentitysluice.soilAmount += 7;
        tileentitysluice.soilType = (byte)is.func_77960_j();
        if (tileentitysluice.soilAmount > 50)
          tileentitysluice.soilAmount = 50;
        is.func_77964_b(0);
        entityplayer.field_71071_by.func_70299_a(entityplayer.field_71071_by.field_70461_c, is);



        return true;
      }
      entityplayer.openGui(TerraFirmaCraft.instance, 25, world, i, j, k);
    }

    return true;
  }




  public IIcon func_149691_a(int side, int meta) {
    if ((meta & 0x4) != 0 && side == 1) {
      return TFCFluids.SALTWATER.getFlowingIcon();
    }
    return TFCBlocks.woodSupportH.func_149691_a(side, 8);
  }







  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess world, int x, int y, int z) {
    if ((world.func_72805_g(x, y, z) & 0x4) == 0) {
      return 16777215;
    }
    return 3493173;
  }


  public static int getDirectionFromMetadata(int i) {
    return i & 0x3;
  }


  public static boolean isBlockFootOfBed(int i) {
    return ((i & 0x8) != 0);
  }


  public boolean getIsRecievingWater(int i) {
    return ((i & 0x4) != 0);
  }


  public String getItemNameIS(ItemStack itemstack) {
    return "Sluice";
  }



  public int func_149645_b() {
    return TFCBlocks.sluiceRenderId;
  }



  public Item func_149650_a(int i, Random random, int j) {
    if (!isBlockFootOfBed(i)) {
      return TFCItems.sluiceItem;
    }
    return null;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
    byte byte0 = 0;
    byte byte1 = 0;
    if (l == 0)
      byte1 = 1;
    if (l == 1)
      byte0 = -1;
    if (l == 2)
      byte1 = -1;
    if (l == 3)
      byte0 = 1;
    world.func_72921_c(i, j, k, l, 3);
    if (world.func_147439_a(i, j, k) == this) {
      world.func_147465_d(i + byte0, j, k + byte1, (Block)this, l + 8, 3);
    }
  }





  public void func_149719_a(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    int meta = par1IBlockAccess.func_72805_g(i, j, k);
    if (isBlockFootOfBed(meta)) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    } else {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
  }


  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    int meta = world.func_72805_g(i, j, k);
    if (isBlockFootOfBed(meta))
      return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 0.5F), (k + 1));
    return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), (j + 1), (k + 1));
  }



  public boolean func_149742_c(World world, int x, int y, int z) {
    Block block = world.func_147439_a(x, y, z);

    int dir = getDirectionFromMetadata(world.func_72805_g(x, y, z));
    int[] offset = HEAD_FOOT_BLOCKMAP[dir];


    boolean stay = (canStay(world, x, y, z, false, dir) && canStay(world, x + offset[0], y, z + offset[1], true, dir) && (block.isAir((IBlockAccess)world, x, y, z) || block.func_149688_o().func_76222_j()));

    return stay;
  }


  public boolean canPlace(World world, int i, int j, int k, int dir) {
    int[] offset = HEAD_FOOT_BLOCKMAP[dir];
    Block topBlock = world.func_147439_a(i, j, k);
    Block footBlock = world.func_147439_a(i + offset[0], j, k + offset[1]);


    boolean stay = (canStay(world, i, j, k, false, dir) && canStay(world, i + offset[0], j, k + offset[1], true, dir) && (topBlock.isAir((IBlockAccess)world, i, j, k) || topBlock.func_149688_o().func_76222_j()) && (footBlock.isAir((IBlockAccess)world, i + offset[0], j, k + offset[1]) || footBlock.func_149688_o().func_76222_j()));

    return stay;
  }


  private boolean canStay(World world, int i, int j, int k, boolean foot, int dir) {
    int l = dir;
    if (l == 0) {
      if (!foot && (
        !world.func_147439_a(i + 1, j, k).func_149721_r() ||
        !world.func_147439_a(i - 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j, k - 1).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
      if (foot && (
        !world.func_147439_a(i + 1, j, k).func_149721_r() ||
        !world.func_147439_a(i - 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
    }  if (l == 1) {
      if (!foot && (
        !world.func_147439_a(i, j, k + 1).func_149721_r() ||
        !world.func_147439_a(i, j, k - 1).func_149721_r() ||
        !world.func_147439_a(i + 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
      if (foot && (
        !world.func_147439_a(i, j, k + 1).func_149721_r() ||
        !world.func_147439_a(i, j, k - 1).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
    }  if (l == 2) {
      if (!foot && (
        !world.func_147439_a(i + 1, j, k).func_149721_r() ||
        !world.func_147439_a(i - 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j, k + 1).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
      if (foot && (
        !world.func_147439_a(i + 1, j, k).func_149721_r() ||
        !world.func_147439_a(i - 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
    }  if (l == 3) {
      if (!foot && (
        !world.func_147439_a(i, j, k + 1).func_149721_r() ||
        !world.func_147439_a(i, j, k - 1).func_149721_r() ||
        !world.func_147439_a(i - 1, j, k).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
      if (foot && (
        !world.func_147439_a(i, j, k + 1).func_149721_r() ||
        !world.func_147439_a(i, j, k - 1).func_149721_r() ||
        !world.func_147439_a(i, j - 1, k).func_149721_r() || world
        .func_147439_a(i, j + 2, k).func_149721_r()))
        return false;
    }  return true;
  }













  public void func_149695_a(World world, int i, int j, int k, Block block) {
    int i1 = world.func_72805_g(i, j, k);
    int j1 = getDirectionFromMetadata(i1);

    if (isBlockFootOfBed(i1)) {

      if (world.func_147439_a(i - HEAD_FOOT_BLOCKMAP[j1][0], j, k - HEAD_FOOT_BLOCKMAP[j1][1]) != this || !canStay(world, i, j, k, true, j1)) {
        world.func_147468_f(i, j, k);
      }
    } else if (world.func_147439_a(i + HEAD_FOOT_BLOCKMAP[j1][0], j, k + HEAD_FOOT_BLOCKMAP[j1][1]) != this || !canStay(world, i, j, k, false, j1)) {

      world.func_147468_f(i, j, k);
      if (!world.field_72995_K) {
        func_149697_b(world, i, j, k, i1, 0);
      }
    }
  }

  public boolean func_149686_d() {
    return false;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TESluice();
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return new ItemStack(TFCItems.sluiceItem);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */