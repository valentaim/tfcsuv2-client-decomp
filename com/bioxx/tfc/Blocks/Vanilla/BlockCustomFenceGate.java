package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TEFenceGate;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Interfaces.IMultipleBlock;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockCustomFenceGate
  extends BlockFenceGate
  implements ITileEntityProvider, IMultipleBlock
{
  public String[] woodNames;
  public IIcon[] icons;

  public BlockCustomFenceGate() {
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.icons = new IIcon[this.woodNames.length];
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    int direction = 0;
    boolean open = false;
    TileEntity te = world.func_147438_o(x, y, z);

    if (te instanceof TEFenceGate) {

      direction = ((TEFenceGate)te).getDirection();
      open = ((TEFenceGate)te).getOpen();
    }

    if (open) {
      return null;
    }
    return (direction != 2 && direction != 0) ? AxisAlignedBB.func_72330_a((x + 0.375F), y, z, (x + 0.625F), (y + 1.5F), (z + 1)) : AxisAlignedBB.func_72330_a(x, y, (z + 0.375F), (x + 1), (y + 1.5F), (z + 0.625F));
  }



  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    for (int i = 0; i < this.woodNames.length; i++) {
      par3List.add(new ItemStack((Block)this, 1, i));
    }
  }


  public void func_149651_a(IIconRegister iconRegisterer) {
    for (int i = 0; i < this.woodNames.length; i++) {
      this.icons[i] = iconRegisterer.func_94245_a("terrafirmacraft:wood/" + this.woodNames[i] + " Plank");
    }
  }


  public int func_149645_b() {
    return TFCBlocks.fenceGateRenderId;
  }



  public IIcon func_149691_a(int par1, int par2) {
    return this.icons[par2];
  }



  public int func_149692_a(int par1) {
    return par1;
  }



  public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    return this.icons[par1IBlockAccess.func_72805_g(par2, par3, par4)];
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    if (par1IBlockAccess.func_147438_o(par3, par3, par4) instanceof TEFenceGate)
      return ((TEFenceGate)par1IBlockAccess.func_147438_o(par2, par3, par4)).getOpen();
    return false;
  }



  public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int l = ((TEFenceGate)par1IBlockAccess.func_147438_o(par2, par3, par4)).getDirection();

    if (l != 2 && l != 0) {

      func_149676_a(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
    }
    else {

      func_149676_a(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
    }
  }



  public void func_149689_a(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
    int l = (MathHelper.func_76128_c((par5EntityLivingBase.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3) % 4;
    ((TEFenceGate)par1World.func_147438_o(par2, par3, par4)).setDirection((byte)l);
  }





  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    TEFenceGate te = (TEFenceGate)par1World.func_147438_o(par2, par3, par4);

    if (te.getOpen()) {
      te.setOpen(false);
    } else {
      te.setOpen(true);
    }
    par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
    return true;
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    TEFenceGate te = (TEFenceGate)world.func_147438_o(x, y, z);
    boolean flag = world.func_72864_z(x, y, z);

    if (flag || (block.func_149688_o() != Material.field_151579_a && block.func_149744_f()))
    {
      if (flag && !te.getOpen()) {

        te.setOpen(true);
        world.func_72889_a((EntityPlayer)null, 1003, x, y, z, 0);
      }
      else if (!flag && te.getOpen()) {

        te.setOpen(false);
        world.func_72889_a((EntityPlayer)null, 1003, x, y, z, 0);
      }
    }
  }




  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEFenceGate();
  }



  public Block getBlockTypeForRender() {
    return TFCBlocks.fenceGate;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
    return true;
  }



  public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */