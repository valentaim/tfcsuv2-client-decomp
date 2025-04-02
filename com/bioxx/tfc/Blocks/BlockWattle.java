package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockWattle
  extends BlockTerra
{
  public BlockWattle(Material m) {
    super(m);
    func_149647_a(TFCTabs.TFC_BUILDING);
  }


  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
    ArrayList<ItemStack> drops = new ArrayList<>();
    Random rand = new Random();
    drops.add(new ItemStack(TFCItems.stick, rand.nextInt(2) + 1));
    return drops;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(i, j, k);
      TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
    }
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
    }
    if (!world.field_72995_K)
    {
      ItemStack itemStack = player.field_71071_by.func_70448_g();
    }














    return false;
  }





  public boolean func_149686_d() {
    return false;
  }


  public boolean func_149655_b(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_) {
    return false;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:wattle");
  }






  public boolean func_149662_c() {
    return false;
  }






  public int func_149645_b() {
    return TFCBlocks.wattleRenderId;
  }



  public AxisAlignedBB func_149668_a(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_) {
    func_149719_a((IBlockAccess)p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
    this.field_149756_F = 1.5D;
    return super.func_149668_a(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
  }



  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    boolean flag0 = canConnectWallTo(world, x, y, z - 1);
    boolean flag1 = canConnectWallTo(world, x, y, z + 1);
    boolean flag2 = canConnectWallTo(world, x - 1, y, z);
    boolean flag3 = canConnectWallTo(world, x + 1, y, z);

    boolean flag0Up = canConnectWallTo(world, x, y, z - 1);
    boolean flag1Up = canConnectWallTo(world, x, y, z + 1);
    boolean flag2Up = canConnectWallTo(world, x - 1, y, z);
    boolean flag3Up = canConnectWallTo(world, x + 1, y, z);
    float f = 0.425F;
    float f1 = 0.575F;
    float f2 = 0.45F;
    float f3 = 0.575F;
    float f4 = 1.1F;

    if (flag0)
    {
      f2 = 0.0F;
    }

    if (flag1)
    {
      f3 = 1.0F;
    }

    if (flag2)
    {
      f = 0.0F;
    }

    if (flag3)
    {
      f1 = 1.0F;
    }

    if (flag0 && flag1 && !flag2 && !flag3) {

      if (!flag0Up || !flag1Up) {
        f4 = 1.1F;
      }
      f = 0.425F;
      f1 = 0.575F;
    }
    else if (!flag0 && !flag1 && flag2 && flag3) {

      if (!flag2Up || !flag3Up) {
        f4 = 1.1F;
      }
      f2 = 0.425F;
      f3 = 0.575F;
    }
    f4 = 1.0F;
    func_149676_a(f, 0.0F, f2, f1, f4, f3);
  }



  public boolean canConnectWallTo(IBlockAccess access, int i, int j, int k) {
    Block block = access.func_147439_a(i, j, k);
    if (block != this && block != Blocks.field_150396_be && block != TFCBlocks.fenceGate && block != TFCBlocks.fenceGate2 && !(block instanceof com.bioxx.tfc.Blocks.Vanilla.BlockCustomWall)) {
      return (block != null && block.func_149688_o().func_76218_k() && block.func_149686_d()) ? ((block.func_149688_o() != Material.field_151572_C)) : false;
    }
    return true;
  }






  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
    return (p_149646_5_ == 0) ? super.func_149646_a(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_) : true;
  }


  public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
    return true;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWattle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */