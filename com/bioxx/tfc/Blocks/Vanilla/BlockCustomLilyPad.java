package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;





public class BlockCustomLilyPad
  extends BlockLilyPad
{
  public BlockCustomLilyPad() {
    float f = 0.5F;
    float f1 = 0.015625F;
    func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }






  public int func_149645_b() {
    return 23;
  }







  public void func_149743_a(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
    if (!(par7Entity instanceof net.minecraft.entity.item.EntityBoat)) {
      super.func_149743_a(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }
  }






  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    return AxisAlignedBB.func_72330_a(par2 + this.field_149759_B, par3 + this.field_149760_C, par4 + this.field_149754_D, par2 + this.field_149755_E, par3 + this.field_149756_F, par4 + this.field_149757_G);
  }



  @SideOnly(Side.CLIENT)
  public int func_149635_D() {
    return 2129968;
  }






  @SideOnly(Side.CLIENT)
  public int func_149741_i(int par1) {
    return 2129968;
  }







  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a(func_149641_N());
  }







  @SideOnly(Side.CLIENT)
  public int func_149720_d(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return 2129968;
  }



  protected boolean func_149854_a(Block block) {
    return canThisPlantGrowOnThisBlock(block);
  }


  protected boolean canThisPlantGrowOnThisBlock(Block par1) {
    return TFC_Core.isFreshWater(par1);
  }


  public boolean canThisPlantGrowOnThisBlock(Block par1, int meta) {
    return (TFC_Core.isFreshWaterIncludeIce(par1, meta) && !TFC_Core.isWaterFlowing(par1));
  }






  public boolean func_149718_j(World world, int x, int y, int z) {
    Block id = world.func_147439_a(x, y - 1, z);
    int meta = world.func_72805_g(x, y - 1, z);
    return (y > 0 && y < 256) ? TFC_Core.isFreshWaterIncludeIce(id, meta) : false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomLilyPad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */