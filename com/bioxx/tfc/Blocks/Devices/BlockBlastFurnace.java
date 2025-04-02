package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.TileEntities.TEBlastFurnace;
import com.bioxx.tfc.TileEntities.TEMetalSheet;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockBlastFurnace
  extends BlockTerraContainer
{
  private IIcon[] textureSide;
  private IIcon textureOn;
  private IIcon textureOff;

  public BlockBlastFurnace() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_DEVICES);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z) & 0x4;
    if (meta == 0) {
      return 0;
    }
    return 15;
  }



  public boolean func_149727_a(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    ItemStack equippedItem = entityplayer.func_71045_bC();
    if (!world.field_72995_K)
    {
      if (!func_149718_j(world, i, j, k)) {

        world.func_147468_f(i, j, k);
        world.func_72838_d((Entity)new EntityItem(world, i, j, k, new ItemStack((Block)this, 1)));
      }
      else if (world.func_147438_o(i, j, k) != null) {

        TEBlastFurnace te = (TEBlastFurnace)world.func_147438_o(i, j, k);

        if (te.isValid) {

          if (equippedItem != null && (equippedItem.func_77973_b() == TFCItems.fireStarter || equippedItem.func_77973_b() == TFCItems.flintSteel) &&
            te.canLight()) {
            entityplayer.func_71045_bC().func_77972_a(1, (EntityLivingBase)entityplayer);
          }
          entityplayer.openGui(TerraFirmaCraft.instance, 26, world, i, j, k);
        }
      }
    }
    return true;
  }



  public boolean func_149718_j(World world, int i, int j, int k) {
    return checkStackAt(world, i, j + 1, k);
  }


  public boolean checkStackAt(World world, int x, int y, int z) {
    return (checkBlock(world, x + 1, y, z, x, z) && checkBlock(world, x - 1, y, z, x, z) && checkBlock(world, x, y, z + 1, x, z) &&
      checkBlock(world, x, y, z - 1, x, z) && (world.func_147437_c(x, y, z) || world.func_147439_a(x, y, z) == TFCBlocks.molten));
  }


  public boolean checkBlock(World world, int x, int y, int z, int stackX, int stackZ) {
    if (!world.func_72899_e(x, y, z) || world.func_147439_a(x, y, z) != TFCBlocks.fireBrick) {
      return false;
    }
    int count = 0;
    int xCoord = x - 1;
    int zCoord = z;
    if (world.func_72899_e(xCoord, y, zCoord) && (xCoord != stackX || zCoord != stackZ) && world
      .func_147439_a(xCoord, y, zCoord) == TFCBlocks.metalSheet && world.func_147438_o(xCoord, y, zCoord) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(xCoord, y, zCoord);
      if (!te.westExists() || !isValidMetalSheet(te))
        return false;
      count++;
    }
    xCoord = x + 1;
    zCoord = z;
    if (world.func_72899_e(xCoord, y, zCoord) && (xCoord != stackX || zCoord != stackZ) && world
      .func_147439_a(xCoord, y, zCoord) == TFCBlocks.metalSheet && world.func_147438_o(xCoord, y, zCoord) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(xCoord, y, zCoord);
      if (!te.eastExists() || !isValidMetalSheet(te))
        return false;
      count++;
    }
    xCoord = x;
    zCoord = z - 1;
    if (world.func_72899_e(xCoord, y, zCoord) && (xCoord != stackX || zCoord != stackZ) && world
      .func_147439_a(xCoord, y, zCoord) == TFCBlocks.metalSheet && world.func_147438_o(xCoord, y, zCoord) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(xCoord, y, zCoord);
      if (!te.southExists() || !isValidMetalSheet(te))
        return false;
      count++;
    }
    xCoord = x;
    zCoord = z + 1;
    if (world.func_72899_e(xCoord, y, zCoord) && (xCoord != stackX || zCoord != stackZ) && world
      .func_147439_a(xCoord, y, zCoord) == TFCBlocks.metalSheet && world.func_147438_o(xCoord, y, zCoord) instanceof TEMetalSheet) {

      TEMetalSheet te = (TEMetalSheet)world.func_147438_o(xCoord, y, zCoord);
      if (!te.northExists() || !isValidMetalSheet(te))
        return false;
      count++;
    }
    return (count >= 3);
  }


  public boolean isValidMetalSheet(TEMetalSheet te) {
    if (te != null) {

      ItemStack sheet = te.sheetStack;
      if (sheet != null && (sheet
        .func_77973_b() == TFCItems.wroughtIronSheet || sheet
        .func_77973_b() == TFCItems.steelSheet || sheet
        .func_77973_b() == TFCItems.blackSteelSheet || sheet
        .func_77973_b() == TFCItems.blueSteelSheet || sheet
        .func_77973_b() == TFCItems.redSteelSheet))
        return true;
    }
    return false;
  }



  public boolean func_149742_c(World world, int i, int j, int k) {
    return true;
  }



  public IIcon func_149691_a(int i, int j) {
    int lit = ((j & 0x4) == 4) ? 1 : 0;
    j &= 0x3;

    if (i == 0 || i == 1) {

      if (lit == 1) {
        return this.textureSide[1];
      }
      return this.textureSide[0];
    }


    if (lit == 1) {
      return this.textureOn;
    }
    return this.textureOff;
  }




  public void func_149651_a(IIconRegister iconRegisterer) {
    this.textureSide = new IIcon[2];
    this.textureSide[0] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Blast Furnace Bottom Off");
    this.textureSide[1] = iconRegisterer.func_94245_a("terrafirmacraft:devices/Blast Furnace Bottom On");
    this.textureOn = iconRegisterer.func_94245_a("terrafirmacraft:devices/Blast Furnace On");
    this.textureOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Blast Furnace Off");
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack par6ItemStack) {
    if (!world.field_72995_K) {

      int l = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      world.func_72921_c(i, j, k, l, 2);
      if (!func_149718_j(world, i, j, k)) {

        world.func_147468_f(i, j, k);
        world.func_72838_d((Entity)new EntityItem(world, i, j, k, new ItemStack((Block)this, 1)));
      }
      else {

        ((TEBlastFurnace)world.func_147438_o(i, j, k)).slowCounter = 101;
      }
    }
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int i, int j, int k) {
    if (!world.field_72995_K) {

      world.func_72805_g(i, j, k);

      if (world.func_147439_a(i, j, k) == TFCBlocks.molten)
        world.func_147468_f(i, j, k);
      if (world.func_147439_a(i, j + 1, k) == TFCBlocks.molten)
        world.func_147468_f(i, j + 1, k);
      if (world.func_147439_a(i, j + 2, k) == TFCBlocks.molten)
        world.func_147468_f(i, j + 2, k);
      if (world.func_147439_a(i, j + 3, k) == TFCBlocks.molten)
        world.func_147468_f(i, j + 3, k);
      if (world.func_147439_a(i, j + 4, k) == TFCBlocks.molten)
        world.func_147468_f(i, j + 4, k);
      world.func_147468_f(i, j, k);
    }
    return true;
  }







  public void func_149695_a(World world, int i, int j, int k, Block block) {}






  public TileEntity func_149915_a(World var1, int var2) {
    return (TileEntity)new TEBlastFurnace();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */