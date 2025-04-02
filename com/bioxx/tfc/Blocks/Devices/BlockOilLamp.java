package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Textures;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.ItemBlocks.ItemOilLamp;
import com.bioxx.tfc.TileEntities.TEOilLamp;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;


public class BlockOilLamp
  extends BlockTerraContainer
{
  private IIcon[] icons;

  public BlockOilLamp() {
    super(Material.field_151594_q);
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_DECORATION);
    func_149715_a(1.0F);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 8)
      return 0;
    return func_149750_m();
  }




  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < 6; i++)
    {
      list.add(ItemOilLamp.getFullLamp(i));
    }
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    int m = meta & 0x7;
    if (side == 0 || side == 1) {

      if (m == 0)
        return TFC_Textures.sheetGold;
      if (m == 1)
        return TFC_Textures.sheetPlatinum;
      if (m == 2)
        return TFC_Textures.sheetRoseGold;
      if (m == 3)
        return TFC_Textures.sheetSilver;
      if (m == 4)
        return TFC_Textures.sheetSterlingSilver;
      if (m == 5)
        return TFC_Textures.sheetBlueSteel;
      return TFC_Textures.sheetGold;
    }
    return this.icons[m];
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149673_e(IBlockAccess access, int i, int j, int k, int side) {
    return func_149691_a(side, access.func_72805_g(i, j, k));
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister registerer) {
    this.icons = new IIcon[6];
    this.icons[0] = registerer.func_94245_a("terrafirmacraft:metal/GoldLamp");
    this.icons[1] = registerer.func_94245_a("terrafirmacraft:metal/PlatinumLamp");
    this.icons[2] = registerer.func_94245_a("terrafirmacraft:metal/RoseGoldLamp");
    this.icons[3] = registerer.func_94245_a("terrafirmacraft:metal/SilverLamp");
    this.icons[4] = registerer.func_94245_a("terrafirmacraft:metal/SterlingSilverLamp");
    this.icons[5] = registerer.func_94245_a("terrafirmacraft:metal/BlueSteelLamp");
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      if (!isLampLit(meta)) {

        TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
        if (te != null) {

          te.updateLampFuel(Boolean.valueOf(false));
          if (te.isFuelValid() &&
            te.getFuelTimeLeft() > 0) {
            world.func_72921_c(x, y, z, meta - 8, 3);
          }
        }
      } else {

        TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
        if (te != null)
        {
          te.updateLampFuel(Boolean.valueOf(true));
        }
        world.func_72921_c(x, y, z, meta + 8, 3);
      }
    }
    return true;
  }


  public static boolean isLampLit(int meta) {
    return ((meta & 0x8) <= 0);
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    return new ArrayList<>();
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEOilLamp();
  }







  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }







  public boolean func_149662_c() {
    return false;
  }






  public boolean func_149686_d() {
    return false;
  }






  public int func_149645_b() {
    return TFCBlocks.oilLampRenderId;
  }


  private boolean canSupportTorch(World world, int x, int y, int z) {
    if (World.func_147466_a((IBlockAccess)world, x, y, z))
    {
      return true;
    }


    Block block = world.func_147439_a(x, y, z);
    return block.canPlaceTorchOnTop(world, x, y, z);
  }







  public boolean func_149742_c(World world, int x, int y, int z) {
    return canSupportTorch(world, x, y - 1, z);
  }






  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    super.func_149674_a(world, x, y, z, rand);
    int meta = world.func_72805_g(x, y, z);
    if (meta < 8) {

      TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
      if (te != null) {

        te.updateLampFuel(Boolean.valueOf(true));
        if (te.getFuelTimeLeft() == 0) {
          world.func_72921_c(x, y, z, meta + 8, 3);
        }
      }
    }
  }


  public int func_149738_a(World world) {
    return 20;
  }






  public void func_149726_b(World world, int x, int y, int z) {
    tryPlace(world, x, y, z);
  }



  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TEOilLamp) {

      ((TEOilLamp)te).create();
      FluidStack fs = FluidStack.loadFluidStackFromNBT(is.func_77978_p());
      if (fs != null)
      {
        ((TEOilLamp)te).setFuelFromStack(fs);
      }




      ((TEOilLamp)te).hourPlaced = (int)TFC_Time.getTotalHours();
    }
  }








  public void func_149695_a(World world, int x, int y, int z, Block b) {
    if (!World.func_147466_a((IBlockAccess)world, x, y - 1, z)) {
      TFC_Core.setBlockToAirWithDrops(world, x, y, z);
    }
  }


  public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
    return true;
  }


  protected boolean tryPlace(World world, int x, int y, int z) {
    if (!func_149742_c(world, x, y, z)) {

      if (world.func_147439_a(x, y, z) == this)
      {

        world.func_147468_f(x, y, z);
      }

      return false;
    }


    return true;
  }




  public void func_149725_f(World world, int x, int y, int z, int meta) {
    if (!world.field_72995_K) {

      TEOilLamp te = (TEOilLamp)world.func_147438_o(x, y, z);
      if ((meta & 0x8) != 0)
        meta -= 8;
      if (te != null)
      {
        if (te.getFuel() != null) {

          ItemStack is = new ItemStack((Block)this, 1, meta);
          NBTTagCompound nbt = te.getFuel().writeToNBT(new NBTTagCompound());
          is.func_77982_d(nbt);
          EntityItem ei = new EntityItem(world, x, y, z, is);
          world.func_72838_d((Entity)ei);
        }
        else {

          ItemStack is = new ItemStack((Block)this, 1, meta);
          EntityItem ei = new EntityItem(world, x, y, z, is);
          world.func_72838_d((Entity)ei);
        }
      }
    }
  }







  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
    func_149676_a(0.25F, 0.0F, 0.25F, 0.75F, 0.5F, 0.75F);
    return super.func_149731_a(world, x, y, z, startVec, endVec);
  }






  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 8) {
      return;
    }

    double centerX = (x + 0.5F);
    double centerY = (y + 0.6F);
    double centerZ = (z + 0.5F);



    world.func_72869_a("smoke", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
    world.func_72869_a("flame", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */