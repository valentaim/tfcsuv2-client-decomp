package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.TileEntities.TEMetalTrapDoor;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMetalTrapDoor
  extends BlockTerraContainer
{
  public IIcon[] icons;
  public static String[] metalNames = new String[] { "Bismuth", "Bismuth Bronze", "Black Bronze", "Black Steel", "Blue Steel", "Brass", "Bronze", "Copper", "Gold", "Wrought Iron", "Lead", "Nickel", "Pig Iron", "Platinum", "Red Steel", "Rose Gold", "Silver", "Steel", "Sterling Silver", "Tin", "Zinc" };




  public BlockMetalTrapDoor() {
    super(Material.field_151573_f);


    func_149676_a(0.0F, 0.0F, 0.0F, 0.001F, 0.001F, 0.001F);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public int func_149645_b() {
    return TFCBlocks.metalTrapDoorRenderId;
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < metalNames.length; i++)
    {

      list.add(new ItemStack((Block)this, 1, i + (i << 5)));
    }
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEMetalTrapDoor();
  }








  public boolean func_149662_c() {
    return false;
  }







  public boolean func_149686_d() {
    return false;
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return !isTrapdoorOpen(par1IBlockAccess.func_72805_g(par2, par3, par4));
  }






  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    func_149719_a((IBlockAccess)world, x, y, z);
    return super.func_149633_g(world, x, y, z);
  }







  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    func_149719_a((IBlockAccess)world, x, y, z);
    return super.func_149668_a(world, x, y, z);
  }







  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    if (access.func_147438_o(x, y, z) != null && access.func_147438_o(x, y, z) instanceof TEMetalTrapDoor) {
      setBlockBoundsForBlockRender(access.func_72805_g(x, y, z), ((TEMetalTrapDoor)access.func_147438_o(x, y, z)).data);
    }
  }





  public void func_149683_g() {
    float f = 0.125F;
    func_149676_a(0.0F, 0.5F - f / 2.0F, 0.0F, 1.0F, 0.5F + f / 2.0F, 1.0F);
  }


  public void setBlockBoundsForBlockRender(int meta, int data) {
    float f = 0.125F;
    int side = data & 0x7;
    int hinge = data >> 4;
    float fx = 0.0F;
    float fy = 0.0F;
    float fz = 0.0F;
    float fx2 = 1.0F;
    float fy2 = 1.0F;
    float fz2 = 1.0F;

    if (isTrapdoorOpen(meta)) {

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
    } else if (side == 0) {

      fy = 1.0F - f;
    }
    else if (side == 1) {

      fy2 = f;
    }
    else if (side == 2) {

      fz = 1.0F - f;
    }
    else if (side == 3) {

      fz2 = f;
    }
    else if (side == 4) {

      fx = 1.0F - f;
    }
    else if (side == 5) {

      fx2 = f;
    }


    func_149676_a(fx, fy, fz, fx2, fy2, fz2);
  }





  public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}





  public Item func_149650_a(int metadata, Random rand, int fortune) {
    return null;
  }








  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    int i1 = par1World.func_72805_g(par2, par3, par4);
    par1World.func_72921_c(par2, par3, par4, i1 ^ 0x4, 2);
    par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
    return true;
  }



  public void func_149725_f(World world, int i, int j, int k, int meta) {
    if (!world.field_72995_K) {

      TEMetalTrapDoor te = (TEMetalTrapDoor)world.func_147438_o(i, j, k);
      if (te != null && te.sheetStack != null) {

        EntityItem ei = new EntityItem(world, i, j, k, te.sheetStack);
        world.func_72838_d((Entity)ei);
      }
    }
  }








  public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
  }


  public static boolean isTrapdoorOpen(int par0) {
    return ((par0 & 0x4) != 0);
  }



  public void func_149651_a(IIconRegister registerer) {
    this.icons = new IIcon[metalNames.length];
    for (int i = 0; i < this.icons.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:metal/" + metalNames[i] + " Trap Door");
    }
  }


  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int meta) {
    TEMetalTrapDoor te = (TEMetalTrapDoor)access.func_147438_o(i, j, k);
    if (te != null && te.sheetStack != null) {
      return this.icons[te.sheetStack.func_77960_j() & 0x1F];
    }
    return TFC_Textures.invisibleTexture;
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    return this.icons[meta & 0x1F];
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return (world.func_147439_a(x, y, z) == this);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockMetalTrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */