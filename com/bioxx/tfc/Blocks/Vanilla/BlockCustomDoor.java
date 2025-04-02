package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.IconFlipped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;



public class BlockCustomDoor
  extends BlockTerra
{
  private int woodType;
  private String[] woodNames = new String[] { "Oak Door Lower", "Oak Door Upper", "Aspen Door Lower", "Aspen Door Upper", "Birch Door Lower", "Birch Door Upper", "Chestnut Door Lower", "Chestnut Door Upper", "Douglas Fir Door Lower", "Douglas Fir Door Upper", "Hickory Door Lower", "Hickory Door Upper", "Maple Door Lower", "Maple Door Upper", "Ash Door Lower", "Ash Door Upper", "Pine Door Lower", "Pine Door Upper", "Sequoia Door Lower", "Sequoia Door Upper", "Spruce Door Lower", "Spruce Door Upper", "Sycamore Door Lower", "Sycamore Door Upper", "White Cedar Door Lower", "White Cedar Door Upper", "White Elm Door Lower", "White Elm Door Upper", "Willow Door Lower", "Willow Door Upper", "Kapok Door Lower", "Kapok Door Upper", "Acacia Door Lower", "Acacia Door Upper" };







  private IIcon[] icons = new IIcon[Global.WOOD_ALL.length * 2];

  public BlockCustomDoor(int woodId) {
    super(Material.field_151575_d);
    func_149711_c(3.0F);

    float var3 = 0.5F;
    float var4 = 1.0F;
    func_149676_a(0.5F - var3, 0.0F, 0.5F - var3, 0.5F + var3, var4, 0.5F + var3);
    setWoodType(woodId);
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int par1, int par2) {
    return this.icons[getWoodType()];
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
    if (par5 != 1 && par5 != 0) {

      int meta = getFullMetadata(par1IBlockAccess, par2, par3, par4);
      int rotation = meta & 0x3;
      boolean flag = ((meta & 0x4) != 0);
      boolean flag1 = false;
      boolean flag2 = ((meta & 0x8) != 0);

      if (flag) {

        if (rotation == 0 && par5 == 2) {
          flag1 = !flag1;
        } else if (rotation == 1 && par5 == 5) {
          flag1 = !flag1;
        } else if (rotation == 2 && par5 == 3) {
          flag1 = !flag1;
        } else if (rotation == 3 && par5 == 4) {
          flag1 = !flag1;
        }
      } else {

        if (rotation == 0 && par5 == 5) {
          flag1 = !flag1;
        } else if (rotation == 1 && par5 == 3) {
          flag1 = !flag1;
        } else if (rotation == 2 && par5 == 4) {
          flag1 = !flag1;
        } else if (rotation == 3 && par5 == 2) {
          flag1 = !flag1;
        }
        if ((meta & 0x10) != 0) {
          flag1 = !flag1;
        }
      }
      return this.icons[getWoodType() + (flag1 ? this.woodNames.length : 0) + (flag2 ? 1 : 0)];
    }


    return this.icons[getWoodType()];
  }




  public void func_149651_a(IIconRegister registerer) {
    this.icons = new IIcon[this.woodNames.length * 2];
    for (int i = 0; i < this.woodNames.length; i++) {

      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/doors/" + this.woodNames[i]);
      this.icons[i + this.woodNames.length] = (IIcon)new IconFlipped(this.icons[i], true, false);
    }
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int var5 = getFullMetadata(par1IBlockAccess, par2, par3, par4);
    return ((var5 & 0x4) != 0);
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return 7;
  }



  @SideOnly(Side.CLIENT)
  public AxisAlignedBB func_149633_g(World par1World, int par2, int par3, int par4) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    return super.func_149633_g(par1World, par2, par3, par4);
  }



  public AxisAlignedBB func_149668_a(World par1World, int par2, int par3, int par4) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    return super.func_149668_a(par1World, par2, par3, par4);
  }



  public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    setDoorRotation(getFullMetadata(par1IBlockAccess, par2, par3, par4));
  }





  public int getDoorOrientation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x3;
  }


  public boolean isDoorOpen(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return ((getFullMetadata(par1IBlockAccess, par2, par3, par4) & 0x4) != 0);
  }


  private void setDoorRotation(int par1) {
    float var2 = 0.1875F;
    func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
    int var3 = par1 & 0x3;
    boolean var4 = ((par1 & 0x4) != 0);
    boolean var5 = ((par1 & 0x10) != 0);

    if (var3 == 0) {

      if (var4) {

        if (!var5) {
          func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
        } else {
          func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
        }
      } else {

        func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
      }

    } else if (var3 == 1) {

      if (var4) {

        if (!var5) {
          func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
          func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
        }
      } else {

        func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
      }

    } else if (var3 == 2) {

      if (var4) {

        if (!var5) {
          func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
        } else {
          func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var2);
        }
      } else {

        func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      }

    } else if (var3 == 3) {

      if (var4) {

        if (!var5) {
          func_149676_a(0.0F, 0.0F, 0.0F, var2, 1.0F, 1.0F);
        } else {
          func_149676_a(1.0F - var2, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
      } else {

        func_149676_a(0.0F, 0.0F, 1.0F - var2, 1.0F, 1.0F, 1.0F);
      }
    }
  }



  public void func_149699_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) {}


  public boolean func_149727_a(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
    if (this.field_149764_J == Material.field_151573_f)
    {
      return false;
    }


    int var10 = getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
    int var11 = var10 & 0x7;
    var11 ^= 0x4;

    if ((var10 & 0x8) == 0) {

      par1World.func_72921_c(par2, par3, par4, var11, 3);
      par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
    }
    else {

      par1World.func_72921_c(par2, par3 - 1, par4, var11, 3);
      par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
    }

    par1World.func_72889_a(par5EntityPlayer, 1003, par2, par3, par4, 0);
    return true;
  }



  public void onPoweredBlockChange(World par1World, int par2, int par3, int par4, boolean par5) {
    int var6 = getFullMetadata((IBlockAccess)par1World, par2, par3, par4);
    boolean var7 = ((var6 & 0x4) != 0);

    if (var7 != par5) {

      int var8 = var6 & 0x7;
      var8 ^= 0x4;

      if ((var6 & 0x8) == 0) {

        par1World.func_72921_c(par2, par3, par4, var8, 3);
        par1World.func_147458_c(par2, par3, par4, par2, par3, par4);
      }
      else {

        par1World.func_72921_c(par2, par3 - 1, par4, var8, 3);
        par1World.func_147458_c(par2, par3 - 1, par4, par2, par3, par4);
      }

      par1World.func_72889_a((EntityPlayer)null, 1003, par2, par3, par4, 0);
    }
  }







  public void func_149695_a(World par1World, int par2, int par3, int par4, Block par5) {
    int var6 = par1World.func_72805_g(par2, par3, par4);

    if ((var6 & 0x8) == 0) {

      boolean var7 = false;

      if (par1World.func_147439_a(par2, par3 + 1, par4) != this) {

        par1World.func_147468_f(par2, par3, par4);
        var7 = true;
      }

      if (!World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4)) {

        par1World.func_147468_f(par2, par3, par4);
        var7 = true;

        if (par1World.func_147439_a(par2, par3 + 1, par4) == this) {
          par1World.func_147468_f(par2, par3 + 1, par4);
        }
      }
      if (var7) {

        if (!par1World.field_72995_K) {
          func_149697_b(par1World, par2, par3, par4, var6, 0);
        }
      } else {

        boolean var8 = (par1World.func_72864_z(par2, par3, par4) || par1World.func_72864_z(par2, par3 + 1, par4));
        if ((var8 || par5.func_149744_f()) && par5 != this) {
          onPoweredBlockChange(par1World, par2, par3, par4, var8);
        }
      }
    } else {

      if (par1World.func_147439_a(par2, par3 - 1, par4) != this) {
        par1World.func_147468_f(par2, par3, par4);
      }
      if (par5 != this) {
        func_149695_a(par1World, par2, par3 - 1, par4, par5);
      }
    }
  }






  public MovingObjectPosition func_149731_a(World par1World, int par2, int par3, int par4, Vec3 par5Vec3, Vec3 par6Vec3) {
    func_149719_a((IBlockAccess)par1World, par2, par3, par4);
    return super.func_149731_a(par1World, par2, par3, par4, par5Vec3, par6Vec3);
  }






  public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
    return (par3 >= 255) ? false : ((World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4) && super.func_149742_c(par1World, par2, par3, par4) && super.func_149742_c(par1World, par2, par3 + 1, par4)));
  }







  public int func_149656_h() {
    return 1;
  }





  public int getFullMetadata(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    int var7, var8, var5 = par1IBlockAccess.func_72805_g(par2, par3, par4);
    boolean var6 = ((var5 & 0x8) != 0);



    if (var6) {

      var7 = par1IBlockAccess.func_72805_g(par2, par3 - 1, par4);
      var8 = var5;
    }
    else {

      var7 = var5;
      var8 = par1IBlockAccess.func_72805_g(par2, par3 + 1, par4);
    }

    boolean var9 = ((var8 & 0x1) != 0);
    return var7 & 0x7 | (var6 ? 8 : 0) | (var9 ? 16 : 0);
  }



  @SideOnly(Side.CLIENT)
  public Item func_149694_d(World par1World, int par2, int par3, int par4) {
    return (this.field_149764_J == Material.field_151573_f) ? Items.field_151139_aw : Items.field_151135_aq;
  }



  @SideOnly(Side.CLIENT)
  public boolean addDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer) {
    return true;
  }



  @SideOnly(Side.CLIENT)
  public boolean addHitEffects(World worldObj, MovingObjectPosition target, EffectRenderer effectRenderer) {
    return true;
  }






  public int func_149643_k(World world, int x, int y, int z) {
    return getWoodType() / 2;
  }






  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();


    if (this == TFCBlocks.wattleDoor) {


      if ((metadata & 0x8) == 0)
      {


        Block block = world.func_147439_a(x, y + 1, z);
        if (block != null && (block instanceof BlockCustomDoor || block == Blocks.field_150350_a))
        {


          int damageValue = func_149643_k(world, x, y, z);
          ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));

        }

      }
      else
      {
        Block block = world.func_147439_a(x, y - 1, z);
        if (block instanceof BlockCustomDoor)
        {


          int damageValue = func_149643_k(world, x, y, z);
          ret.add(new ItemStack(TFCItems.doorWattle, 1, 0));

        }

      }

    }
    else if ((metadata & 0x8) == 0) {



      Block block = world.func_147439_a(x, y + 1, z);
      if (block != null && (block instanceof BlockCustomDoor || block == Blocks.field_150350_a))
      {


        int damageValue = func_149643_k(world, x, y, z);
        ret.add(new ItemStack(Recipes.doors[damageValue], 1, 0));

      }

    }
    else {

      Block block = world.func_147439_a(x, y - 1, z);
      if (block instanceof BlockCustomDoor) {



        int damageValue = func_149643_k(world, x, y, z);
        ret.add(new ItemStack(Recipes.doors[damageValue], 1, 0));
      }
    }

    return ret;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    int damageValue = func_149643_k(world, x, y, z);

    if (this == TFCBlocks.wattleDoor)
    {
      return new ItemStack(TFCItems.doorWattle, 1, 0);
    }

    return new ItemStack(Recipes.doors[damageValue], 1, 0);
  }


  public int getWoodType() {
    return this.woodType;
  }


  private void setWoodType(int woodType) {
    this.woodType = woodType;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */