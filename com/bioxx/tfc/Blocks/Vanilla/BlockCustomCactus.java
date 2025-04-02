package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;


public class BlockCustomCactus
  extends Block
  implements IPlantable
{
  @SideOnly(Side.CLIENT)
  private IIcon cactusTopIcon;
  @SideOnly(Side.CLIENT)
  private IIcon cactusSideIcon;

  public BlockCustomCactus() {
    super(Material.field_151570_A);
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_DECORATION);
  }






  public void func_149674_a(World par1World, int par2, int par3, int par4, Random par5Random) {
    if (par1World.func_147437_c(par2, par3 + 1, par4)) {
      int var6;


      for (var6 = 1; par1World.func_147439_a(par2, par3 - var6, par4) == this; var6++);




      if (var6 < 3) {

        int var7 = par1World.func_72805_g(par2, par3, par4);

        if (var7 == 15) {

          par1World.func_147465_d(par2, par3 + 1, par4, this, 0, 2);
          par1World.func_72921_c(par2, par3, par4, 0, 2);
        }
        else {

          par1World.func_72921_c(par2, par3, par4, var7 + 1, 2);
        }
      }
    }
  }







  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    float var5 = 0.0625F;
    return AxisAlignedBB.func_72330_a((par2 + var5), par3, (par4 + var5), ((par2 + 1) - var5), ((par3 + 1) - var5), ((par4 + 1) - var5));
  }






  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
    float var5 = 0.0625F;
    return AxisAlignedBB.func_72330_a((par2 + var5), par3, (par4 + var5), ((par2 + 1) - var5), (par3 + 1), ((par4 + 1) - var5));
  }






  public IIcon func_149691_a(int par1, int par2) {
    return (par1 == 1) ? this.cactusTopIcon : ((par1 == 0) ? this.cactusSideIcon : this.field_149761_L);
  }






  public boolean func_149686_d() {
    return false;
  }







  public boolean func_149662_c() {
    return false;
  }






  public int func_149645_b() {
    return 13;
  }






  public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
    return !super.func_149742_c(par1World, par2, par3, par4) ? false : func_149718_j(par1World, par2, par3, par4);
  }







  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    if (!func_149718_j(par1World, par2, par3, par4)) {

      func_149697_b(par1World, par2, par3, par4, par1World.func_72805_g(par2, par3, par4), 0);
      par1World.func_147468_f(par2, par3, par4);
    }
  }



  public boolean func_149718_j(World par1World, int par2, int par3, int par4) {
    if (par1World.func_147439_a(par2 - 1, par3, par4).func_149688_o().func_76220_a())
      return false;
    if (par1World.func_147439_a(par2 + 1, par3, par4).func_149688_o().func_76220_a())
      return false;
    if (par1World.func_147439_a(par2, par3, par4 - 1).func_149688_o().func_76220_a())
      return false;
    if (par1World.func_147439_a(par2, par3, par4 + 1).func_149688_o().func_76220_a()) {
      return false;
    }
    return canSustainPlant((IBlockAccess)par1World, par2, par3 - 1, par4, ForgeDirection.UP, this);
  }



  public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction, IPlantable plant) {
    Block block = world.func_147439_a(x, y, z);
    boolean stay = false;
    if (TFC_Core.isSand(block) || block == this || block.canSustainPlant(world, x, y, z, direction, plant))
      stay = true;
    return stay;
  }






  public void func_149670_a(World par1World, int par2, int par3, int par4, Entity par5Entity) {
    par5Entity.func_70097_a(DamageSource.field_76367_g, 25.0F);
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister par1IconRegister) {
    this.field_149761_L = par1IconRegister.func_94245_a("cactus_side");
    this.cactusTopIcon = par1IconRegister.func_94245_a("cactus_top");
    this.cactusSideIcon = par1IconRegister.func_94245_a("cactus_bottom");
  }



  public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    return EnumPlantType.Desert;
  }



  public Block getPlant(IBlockAccess world, int x, int y, int z) {
    return this;
  }



  public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
    return -1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomCactus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */