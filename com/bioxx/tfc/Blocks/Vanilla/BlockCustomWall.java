package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockCustomWall
  extends BlockWall
{
  private int totalsubTypes;
  private Block block;

  public BlockCustomWall(Block blk, int t) {
    super(blk);
    this.block = blk;
    this.totalsubTypes = t;
    func_149647_a(TFCTabs.TFC_BUILDING);
  }






  public IIcon func_149691_a(int par1, int par2) {
    return this.block.func_149691_a(par1, par2);
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack is) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(i, j, k);
      TerraFirmaCraft.LOG.info("Meta=" + func_149739_a() + ":" + metadata);
    }
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (TFCOptions.enableDebugMode && world.field_72995_K) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
    }
    return false;
  }






  public int func_149645_b() {
    return TFCBlocks.wallRenderId;
  }



  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    for (int i = 0; i < this.totalsubTypes; i++) {
      par3List.add(new ItemStack(par1, 1, i));
    }
  }






  public int func_149692_a(int par1) {
    return par1;
  }



  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    boolean flag0 = func_150091_e(world, x, y, z - 1);
    boolean flag1 = func_150091_e(world, x, y, z + 1);
    boolean flag2 = func_150091_e(world, x - 1, y, z);
    boolean flag3 = func_150091_e(world, x + 1, y, z);

    boolean flag0Up = func_150091_e(world, x, y, z - 1);
    boolean flag1Up = func_150091_e(world, x, y, z + 1);
    boolean flag2Up = func_150091_e(world, x - 1, y, z);
    boolean flag3Up = func_150091_e(world, x + 1, y, z);
    float f = 0.25F;
    float f1 = 0.75F;
    float f2 = 0.25F;
    float f3 = 0.75F;
    float f4 = 1.0F;

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
        f4 = 0.8125F;
      }
      f = 0.3125F;
      f1 = 0.6875F;
    }
    else if (!flag0 && !flag1 && flag2 && flag3) {

      if (!flag2Up || !flag3Up) {
        f4 = 0.8125F;
      }
      f2 = 0.3125F;
      f3 = 0.6875F;
    }

    func_149676_a(f, 0.0F, f2, f1, f4, f3);
  }



  public boolean func_150091_e(IBlockAccess access, int i, int j, int k) {
    Block block = access.func_147439_a(i, j, k);
    if (block != this && block != Blocks.field_150396_be && block != TFCBlocks.fenceGate && block != TFCBlocks.fenceGate2 && !(block instanceof BlockCustomWall) && !(block instanceof com.bioxx.tfc.Blocks.BlockWattle)) {
      return (block != null && block.func_149688_o().func_76218_k() && block.func_149686_d()) ? ((block.func_149688_o() != Material.field_151572_C)) : false;
    }
    return true;
  }


  public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
    return true;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomWall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */