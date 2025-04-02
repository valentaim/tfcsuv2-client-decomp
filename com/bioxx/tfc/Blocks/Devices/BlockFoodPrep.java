package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEFoodPrep;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;





public class BlockFoodPrep
  extends BlockTerraContainer
{
  public BlockFoodPrep() {
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      TEFoodPrep te = (TEFoodPrep)world.func_147438_o(x, y, z);
      te.openGui(entityplayer);
    }
    return true;
  }



  public boolean func_149662_c() {
    return false;
  }




  public boolean func_149686_d() {
    return false;
  }




  public void func_149651_a(IIconRegister iconRegisterer) {}



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
    return TFC_Textures.invisibleTexture;
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int i, int j, int k) {
    return true;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEFoodPrep();
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }



  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    return null;
  }



  public AxisAlignedBB func_149633_g(World par1World, int i, int j, int k) {
    return AxisAlignedBB.func_72330_a(i, j, k, (i + 1), j + 0.15D, (k + 1));
  }



  public void func_149695_a(World world, int i, int j, int k, Block block) {
    if (!world.field_72995_K)
    {
      if (!world.isSideSolid(i, j - 1, k, ForgeDirection.UP)) {

        ((TEFoodPrep)world.func_147438_o(i, j, k)).ejectContents();
        world.func_147468_f(i, j, k);
        return;
      }
    }
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    eject(world, i, j, k);
  }



  public void func_149723_a(World par1World, int par2, int par3, int par4, Explosion ex) {
    eject(par1World, par2, par3, par4);
  }



  public void func_149664_b(World par1World, int par2, int par3, int par4, int par5) {
    eject(par1World, par2, par3, par4);
  }



  public void func_149749_a(World par1World, int par2, int par3, int par4, Block par5, int par6) {
    eject(par1World, par2, par3, par4);
  }




  public void eject(World par1World, int par2, int par3, int par4) {
    if (par1World.func_147438_o(par2, par3, par4) instanceof TEFoodPrep) {

      TEFoodPrep te = (TEFoodPrep)par1World.func_147438_o(par2, par3, par4);
      te.ejectContents();
      par1World.func_147475_p(par2, par3, par4);
    }
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return null;
  }






  @SideOnly(Side.CLIENT)
  public String func_149702_O() {
    return "terrafirmacraft:devices/foodprep";
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return (world.func_147439_a(x, y, z) == this);
  }




  public int func_149645_b() {
    return TerraFirmaCraft.renderfoodprep;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockFoodPrep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */