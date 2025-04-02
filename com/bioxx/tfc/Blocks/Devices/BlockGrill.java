package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;




public class BlockGrill
  extends BlockTerraContainer
{
  public BlockGrill() {
    super(Material.field_151573_f);
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    super.func_149727_a(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
    if (entityplayer.field_71071_by.func_70448_g() != null && entityplayer.field_71071_by.func_70448_g().func_77973_b() instanceof com.bioxx.tfc.Items.ItemBlocks.ItemMetalTrapDoor) {
      return false;
    }
    TEGrill te = (TEGrill)world.func_147438_o(x, y, z);
    int meta = world.func_72805_g(x, y, z);

    if (isGrillOpen(meta) || (entityplayer.func_70093_af() && te.isEmpty())) {

      world.func_72921_c(x, y, z, meta ^ 0x4, 2);
      world.func_72889_a(entityplayer, 1003, x, y, z, 0);
      return true;
    }


    entityplayer.openGui(TerraFirmaCraft.instance, 43, world, x, y, z);
    return true;
  }




  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEGrill();
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
    return false;
  }







  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    func_149719_a((IBlockAccess)world, x, y, z);
    return super.func_149668_a(world, x, y, z);
  }







  public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
  }







  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    if (access.func_147438_o(x, y, z) != null && access.func_147438_o(x, y, z) instanceof TEGrill) {
      setBlockBoundsForBlockRender(access.func_72805_g(x, y, z), ((TEGrill)access.func_147438_o(x, y, z)).data);
    }
  }


  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return !isGrillOpen(par1IBlockAccess.func_72805_g(par2, par3, par4));
  }


  public boolean isGrillOpen(int meta) {
    return ((meta & 0x4) != 0);
  }


  public void setBlockBoundsForBlockRender(int meta, int data) {
    float f = 0.05F;
    int side = data & 0x7;
    int hinge = data >> 4;
    float fx = 0.0F, fy = 0.0F, fz = 0.0F, fx2 = 1.0F, fy2 = 1.0F, fz2 = 1.0F;

    if (isGrillOpen(meta)) {

      if (hinge == 0) {

        switch (side) {


          case 0:
          case 1:
          case 2:
          case 3:
            fx2 = f;
            break;


          case 4:
          case 5:
            fy2 = f;
            break;

          default:
            fx2 = f;
            break;
        }

      } else if (hinge == 1) {

        switch (side) {


          case 2:
          case 3:
            fy2 = f;
            break;

          default:
            fz2 = f;
            break;
        }

      } else if (hinge == 2) {

        switch (side) {


          case 4:
          case 5:
            fy = 1.0F - f;
            break;

          default:
            fx = 1.0F - f;
            break;
        }

      } else if (hinge == 3) {

        switch (side) {


          case 2:
          case 3:
            fy = 1.0F - f;
            break;


          case 4:
          case 5:
            fz = 1.0F - f;
            break;

          default:
            fz = 1.0F - f;
            break;
        }

      }
      func_149676_a(fx, fy, fz, fx2, fy2, fz2);
    } else {

      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.05F, 1.0F);
    }
  }







  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister iconRegisterer) {
    this.field_149761_L = iconRegisterer.func_94245_a("terrafirmacraft:devices/Grill Wrought Iron");
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta == 0) {

      if (side == 0 || side == 1)
      {
        return this.field_149761_L;
      }
      return TFC_Textures.sheetWroughtIron;
    }
    return this.field_149761_L;
  }



  public boolean func_149686_d() {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public int func_149645_b() {
    return TFCBlocks.grillRenderId;
  }


  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }






  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    func_149719_a((IBlockAccess)world, x, y, z);
    return super.func_149633_g(world, x, y, z);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */