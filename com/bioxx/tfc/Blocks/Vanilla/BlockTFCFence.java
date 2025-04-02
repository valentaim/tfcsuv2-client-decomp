package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemLead;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockTFCFence
  extends BlockFence
{
  protected String[] woodNames;
  protected IIcon[] iconsPost;
  protected IIcon[] iconsPostTop;

  public BlockTFCFence(String str, Material mat) {
    super(str, mat);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.iconsPost = new IIcon[this.woodNames.length];
    this.iconsPostTop = new IIcon[this.woodNames.length];
    func_149647_a(TFCTabs.TFC_DECORATION);
  }







  public void func_149743_a(World world, int x, int y, int z, AxisAlignedBB aaBB, List list, Entity entity) {
    boolean flag = func_149826_e((IBlockAccess)world, x, y, z - 1);
    boolean flag1 = func_149826_e((IBlockAccess)world, x, y, z + 1);
    boolean flag2 = func_149826_e((IBlockAccess)world, x - 1, y, z);
    boolean flag3 = func_149826_e((IBlockAccess)world, x + 1, y, z);
    float f = 0.375F;
    float f1 = 0.625F;
    float f2 = 0.375F;
    float f3 = 0.625F;

    if (flag) f2 = 0.0F;
    if (flag1) f3 = 1.0F;

    if (flag || flag1) {

      func_149676_a(f, 0.0F, f2, f1, 1.5F, f3);
      super.func_149743_a(world, x, y, z, aaBB, list, entity);
    }

    f2 = 0.375F;
    f3 = 0.625F;

    if (flag2) f = 0.0F;
    if (flag3) f1 = 1.0F;

    if (flag2 || flag3 || (!flag && !flag1)) {

      func_149676_a(f, 0.0F, f2, f1, 1.5F, f3);
      super.func_149743_a(world, x, y, z, aaBB, list, entity);
    }

    if (flag) f2 = 0.0F;
    if (flag1) f3 = 1.0F;

    func_149676_a(f, 0.0F, f2, f1, 1.0F, f3);
  }



  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.woodNames.length; i++) {
      list.add(new ItemStack((Block)this, 1, i));
    }
  }











  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < this.woodNames.length; i++) {

      this.iconsPost[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/" + this.woodNames[i] + " Fence");
      this.iconsPostTop[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/" + this.woodNames[i] + " Fence Top");
    }
  }






  public void func_149719_a(IBlockAccess bAccess, int x, int y, int z) {
    boolean flag = func_149826_e(bAccess, x, y, z - 1);
    boolean flag1 = func_149826_e(bAccess, x, y, z + 1);
    boolean flag2 = func_149826_e(bAccess, x - 1, y, z);
    boolean flag3 = func_149826_e(bAccess, x + 1, y, z);
    float f = 0.375F;
    float f1 = 0.625F;
    float f2 = 0.375F;
    float f3 = 0.625F;

    if (flag) f2 = 0.0F;
    if (flag1) f3 = 1.0F;
    if (flag2) f = 0.0F;
    if (flag3) f1 = 1.0F;

    func_149676_a(f, 0.0F, f2, f1, 1.0F, f3);
  }







  public boolean func_149662_c() {
    return false;
  }



  public IIcon func_149691_a(int par1, int par2) {
    if (par1 == 1) {
      return this.iconsPostTop[par2];
    }
    return this.iconsPost[par2];
  }



  public int func_149692_a(int par1) {
    return par1;
  }






  public boolean func_149686_d() {
    return false;
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }






  public int func_149645_b() {
    return TFCBlocks.fenceRenderId;
  }






  public boolean func_149826_e(IBlockAccess bAccess, int x, int y, int z) {
    Block block = bAccess.func_147439_a(x, y, z);

    if (TFCBlocks.canFenceConnectTo(block)) {
      return true;
    }
    return (block != this && block.func_149688_o().func_76218_k() && block.func_149686_d()) ? ((block.func_149688_o() != Material.field_151572_C)) : false;
  }


  public static boolean isBlockAFence(Block block) {
    return TFCBlocks.isBlockAFence(block);
  }







  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
    if (!world.field_72995_K)
    {
      return ItemLead.func_150909_a(player, world, x, y, z);
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


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockTFCFence.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */