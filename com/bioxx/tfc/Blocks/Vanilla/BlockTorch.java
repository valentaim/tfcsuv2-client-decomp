package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TELightEmitter;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
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
import net.minecraftforge.common.util.ForgeDirection;







public class BlockTorch
  extends BlockTerraContainer
{
  protected IIcon offIcon;

  public BlockTorch() {
    super(Material.field_151594_q);
    func_149675_a(true);
    func_149647_a(TFCTabs.TFC_DECORATION);
    func_149715_a(0.9375F);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 8)
      return 0;
    return func_149750_m();
  }



  @SideOnly(Side.CLIENT)
  public IIcon func_149691_a(int side, int meta) {
    if (meta >= 8)
      return this.offIcon;
    return this.field_149761_L;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister register) {
    super.func_149651_a(register);
    this.offIcon = register.func_94245_a("terrafirmacraft:torch_off");
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      ItemStack is = player.field_71071_by.func_70448_g();
      Item item = (is != null) ? is.func_77973_b() : null;


      if (meta < 8 && item == TFCItems.stick)
      {
        player.field_71071_by.func_146026_a(TFCItems.stick);
        TFC_Core.giveItemToPlayer(new ItemStack(TFCBlocks.torch), player);

      }
      else if (item == Item.func_150898_a(TFCBlocks.torch))
      {
        TELightEmitter te = (TELightEmitter)world.func_147438_o(x, y, z);
        te.hourPlaced = (int)TFC_Time.getTotalHours();
        if (meta >= 8)
        {
          world.func_72921_c(x, y, z, meta - 8, 3);

        }
      }
      else
      {
        world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta, 3);

      }

    }
    else if (TFCOptions.enableDebugMode) {

      int metadata = world.func_72805_g(x, y, z);
      TerraFirmaCraft.LOG.info("Meta = " + func_149739_a() + ":" + metadata);
    }

    return true;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();


    if (metadata >= 8) {
      return ret;
    }
    Item item = func_149650_a(metadata, world.field_73012_v, fortune);
    if (item != null)
    {
      ret.add(new ItemStack(item, 1, func_149692_a(metadata)));
    }
    return ret;
  }



  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TELightEmitter();
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
    return TFCBlocks.torchRenderId;
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
    return (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) || world
      .isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) || world
      .isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) || world
      .isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) ||
      canSupportTorch(world, x, y - 1, z));
  }






  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    int j1 = meta;

    if (side == 1 && canSupportTorch(world, x, y - 1, z))
    {
      j1 = 5;
    }

    if (side == 2 && world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true))
    {
      j1 = 4;
    }

    if (side == 3 && world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true))
    {
      j1 = 3;
    }

    if (side == 4 && world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true))
    {
      j1 = 2;
    }

    if (side == 5 && world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true))
    {
      j1 = 1;
    }

    return j1;
  }






  public void func_149674_a(World world, int x, int y, int z, Random rand) {
    super.func_149674_a(world, x, y, z, rand);
    int meta = world.func_72805_g(x, y, z);

    if (meta == 0)
    {
      func_149726_b(world, x, y, z);
    }
    if (!world.field_72995_K)
    {
      if (TFCOptions.torchBurnTime != 0 && world.func_147438_o(x, y, z) instanceof TELightEmitter) {


        TELightEmitter te = (TELightEmitter)world.func_147438_o(x, y, z);
        if (TFC_Time.getTotalHours() > (te.hourPlaced + TFCOptions.torchBurnTime) ||
          TFC_Core.isExposedToRain(world, x, y, z))
        {
          world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta, 3);
        }
      }
      else if (meta >= 8) {

        world.func_147465_d(x, y, z, TFCBlocks.torchOff, meta - 8, 3);
      }
    }
  }






  public void func_149726_b(World world, int x, int y, int z) {
    if (world.func_72805_g(x, y, z) == 0)
    {
      if (world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true)) {

        world.func_72921_c(x, y, z, 1, 3);
      }
      else if (world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true)) {

        world.func_72921_c(x, y, z, 2, 3);
      }
      else if (world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true)) {

        world.func_72921_c(x, y, z, 3, 3);
      }
      else if (world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true)) {

        world.func_72921_c(x, y, z, 4, 3);
      }
      else if (canSupportTorch(world, x, y - 1, z)) {

        world.func_72921_c(x, y, z, 5, 3);
      }
    }
    ((TELightEmitter)world.func_147438_o(x, y, z)).create();

    tryPlace(world, x, y, z);
  }







  public void func_149695_a(World world, int x, int y, int z, Block b) {
    checkValidity(world, x, y, z, b);
  }



  public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
    return true;
  }


  protected boolean checkValidity(World world, int x, int y, int z, Block b) {
    if (tryPlace(world, x, y, z)) {

      int l = world.func_72805_g(x, y, z);
      boolean flag = false;

      if (!world.isSideSolid(x - 1, y, z, ForgeDirection.EAST, true) && l == 1)
      {
        flag = true;
      }

      if (!world.isSideSolid(x + 1, y, z, ForgeDirection.WEST, true) && l == 2)
      {
        flag = true;
      }

      if (!world.isSideSolid(x, y, z - 1, ForgeDirection.SOUTH, true) && l == 3)
      {
        flag = true;
      }

      if (!world.isSideSolid(x, y, z + 1, ForgeDirection.NORTH, true) && l == 4)
      {
        flag = true;
      }

      if (!canSupportTorch(world, x, y - 1, z) && l == 5)
      {
        flag = true;
      }

      if (flag) {

        func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
        world.func_147468_f(x, y, z);
        return true;
      }


      return false;
    }



    return true;
  }



  protected boolean tryPlace(World world, int x, int y, int z) {
    if (!func_149742_c(world, x, y, z)) {

      if (world.func_147439_a(x, y, z) == this) {

        func_149697_b(world, x, y, z, world.func_72805_g(x, y, z), 0);
        world.func_147468_f(x, y, z);
      }

      return false;
    }


    return true;
  }








  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 startVec, Vec3 endVec) {
    int l = world.func_72805_g(x, y, z) & 0x7;
    float f = 0.15F;

    if (l == 1) {

      func_149676_a(0.0F, 0.2F, 0.5F - f, f * 2.0F, 0.8F, 0.5F + f);
    }
    else if (l == 2) {

      func_149676_a(1.0F - f * 2.0F, 0.2F, 0.5F - f, 1.0F, 0.8F, 0.5F + f);
    }
    else if (l == 3) {

      func_149676_a(0.5F - f, 0.2F, 0.0F, 0.5F + f, 0.8F, f * 2.0F);
    }
    else if (l == 4) {

      func_149676_a(0.5F - f, 0.2F, 1.0F - f * 2.0F, 0.5F + f, 0.8F, 1.0F);
    }
    else {

      f = 0.1F;
      func_149676_a(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.6F, 0.5F + f);
    }

    return super.func_149731_a(world, x, y, z, startVec, endVec);
  }






  @SideOnly(Side.CLIENT)
  public void func_149734_b(World world, int x, int y, int z, Random rand) {
    int meta = world.func_72805_g(x, y, z);
    if (meta >= 8) {
      return;
    }

    double centerX = (x + 0.5F);
    double centerY = (y + 0.7F);
    double centerZ = (z + 0.5F);
    double d3 = 0.22D;
    double d4 = 0.27D;

    if ((meta & 0x7) == 1) {

      world.func_72869_a("smoke", centerX - d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", centerX - d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
    }
    else if ((meta & 0x7) == 2) {

      world.func_72869_a("smoke", centerX + d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", centerX + d4, centerY + d3, centerZ, 0.0D, 0.0D, 0.0D);
    }
    else if ((meta & 0x7) == 3) {

      world.func_72869_a("smoke", centerX, centerY + d3, centerZ - d4, 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", centerX, centerY + d3, centerZ - d4, 0.0D, 0.0D, 0.0D);
    }
    else if ((meta & 0x7) == 4) {

      world.func_72869_a("smoke", centerX, centerY + d3, centerZ + d4, 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", centerX, centerY + d3, centerZ + d4, 0.0D, 0.0D, 0.0D);
    }
    else {

      world.func_72869_a("smoke", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
      world.func_72869_a("flame", centerX, centerY, centerZ, 0.0D, 0.0D, 0.0D);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockTorch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */