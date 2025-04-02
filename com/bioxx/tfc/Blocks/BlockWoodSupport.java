package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;




public class BlockWoodSupport
  extends BlockTerra
{
  protected String[] woodNames;
  protected IIcon[] icons;

  public BlockWoodSupport(Material material) {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_BUILDING);
    this.woodNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.woodNames, 0, 16);
    this.icons = new IIcon[this.woodNames.length];
  }




  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    if (TFCBlocks.isBlockVSupport(this))
    {
      for (int i = 0; i < this.woodNames.length; i++) {
        list.add(new ItemStack(this, 1, i));
      }
    }
  }

  public static boolean hasSupportsInRange(World world, int x, int y, int z, int range) {
    return (getSupportsInRangeDir(world, x, y, z, range, false) != null);
  }


  public static boolean isSupportConnected(World world, int x, int y, int z) {
    return (getSupportsInRangeDir(world, x, y, z, 5, true) != null);
  }


  public static ForgeDirection getSupportDirection(World world, int x, int y, int z) {
    int[] r = getSupportsInRangeDir(world, x, y, z, 5, false);
    if (r != null) {

      if (r[2] > r[3])
        return ForgeDirection.NORTH;
      if (r[3] > r[2])
        return ForgeDirection.SOUTH;
      if (r[5] > r[4])
        return ForgeDirection.EAST;
      if (r[4] > r[5]) {
        return ForgeDirection.WEST;
      }
    }
    return ForgeDirection.UNKNOWN;
  }


  public static int getDistanceFromDirection(ForgeDirection dir, int[] dist) {
    switch (dir) {
      case NORTH:
        return dist[2];
      case SOUTH: return dist[3];
      case WEST: return dist[4];
      case EAST: return dist[5];
    }  return Integer.MAX_VALUE;
  }




  public static int[] getSupportsInRangeDir(World world, int x, int y, int z, int range, boolean checkConnection) {
    int n = 0; boolean foundNV = false, foundNH = true;
    int s = 0; boolean foundSV = false, foundSH = true;
    int e = 0; boolean foundEV = false, foundEH = true;
    int w = 0; boolean foundWV = false, foundWH = true;
    boolean clearNorthPath = true;
    boolean clearSouthPath = true;
    boolean clearEastPath = true;
    boolean clearWestPath = true;

    for (int i = 1; i <= range; i++) {

      if (!foundEV) {

        if (!checkConnection) {

          if (world.func_147437_c(x + i, y, z) || TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z))) {
            e++;
          } else {
            clearEastPath = false;
          }
        } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x + i, y, z)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z))) {
          foundEH = false;
        } else {
          e++;
        }  if (clearEastPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x + i, y, z)) && (e >= 0 || i == 1))
        {
          if (scanVert(world, x + i, y, z)) {
            foundEV = true;
          } else {
            e -= 50;
          }  }
      }
      if (!foundWV) {

        if (!checkConnection) {

          if (world.func_147437_c(x - i, y, z) || TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z))) {
            w++;
          } else {
            clearWestPath = false;
          }
        } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x - i, y, z)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z))) {
          foundWH = false;
        } else {
          w++;
        }  if (clearWestPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x - i, y, z)) && (w >= 0 || i == 1))
        {
          if (scanVert(world, x - i, y, z)) {
            foundWV = true;
          } else {
            w -= 50;
          }  }
      }
      if (!foundSV) {

        if (!checkConnection) {

          if (world.func_147437_c(x, y, z + i) || TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i))) {
            s++;
          } else {
            clearSouthPath = false;
          }

        } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + i)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i))) {
          foundSH = false;
        } else {
          s++;
        }  if (clearSouthPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + i)) && (s >= 0 || i == 1))
        {
          if (scanVert(world, x, y, z + i))
            foundSV = true;
        }
      }
      if (!foundNV) {

        if (!checkConnection) {

          if (world.func_147437_c(x, y, z - i) || TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i))) {
            n++;
          } else {
            clearNorthPath = false;
          }
        } else if (checkConnection && !TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - i)) && !TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i))) {
          foundNH = false;
        } else {
          n++;
        }  if (clearNorthPath && TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - i)) && (n >= 0 || i == 1))
        {
          if (scanVert(world, x, y, z - i)) {
            foundNV = true;
          }
        }
      }
    }
    if (foundEV && foundEH && foundWV && foundWH)
      return new int[] { 0, 0, 0, 0, w, e };
    if (foundSV && foundSH && foundNV && foundNH)
      return new int[] { 0, 0, n, s, 0, 0 };
    return null;
  }


  private static boolean scanVert(World world, int x, int y, int z) {
    int out = 1;
    while (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y - out, z))) {
      out++;
    }
    return (out > 2);
  }



  public boolean func_149655_b(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
    return true;
  }


  public static int isNextToSupport(World world, int x, int y, int z) {
    if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
      return 5;
    if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
      return 4;
    if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
      return 3;
    if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1)))
      return 2;
    return 0;
  }



















  public int func_149692_a(int j) {
    return j;
  }



  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();
    Block b = world.func_147439_a(x, y, z);
    if (b == TFCBlocks.woodSupportH || b == TFCBlocks.woodSupportV) {
      ret.add(new ItemStack(TFCBlocks.woodSupportV, 1, metadata));
    } else if (b == TFCBlocks.woodSupportH2 || b == TFCBlocks.woodSupportV2) {
      ret.add(new ItemStack(TFCBlocks.woodSupportV2, 1, metadata));
    }  return ret;
  }



  public IIcon func_149691_a(int side, int meta) {
    if (meta < 0)
      return this.icons[0];
    if (meta < this.icons.length)
      return this.icons[meta];
    return TFCBlocks.woodSupportH2.func_149691_a(side, meta - 16);
  }



  public void func_149651_a(IIconRegister registerer) {
    for (int i = 0; i < this.woodNames.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/WoodSheet/" + this.woodNames[i]);
    }
  }


  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return getCollisionBoundingBoxFromPoolIBlockAccess((IBlockAccess)world, x, y, z).func_72325_c(x, y, z);
  }


  private AxisAlignedBB getCollisionBoundingBoxFromPoolIBlockAccess(IBlockAccess blockAccess, int x, int y, int z) {
    Boolean isHorizontal = Boolean.valueOf(TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z)));


    double minX = 0.25D, minY = 0.0D, minZ = 0.25D;
    double maxX = 0.75D, maxY = 0.75D, maxZ = 0.75D;

    if (isHorizontal.booleanValue()) {

      minY = 0.5D;
      maxY = 1.0D;
      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x + 1, y, z)))
        maxX = 1.0D;
      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x - 1, y, z)))
        minX = 0.0D;
      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z + 1)))
        maxZ = 1.0D;
      if (TFCBlocks.isBlockVSupport(blockAccess.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(blockAccess.func_147439_a(x, y, z - 1))) {
        minZ = 0.0D;

      }
    }
    else {

      minY = 0.0D;
      maxY = 1.0D;
    }









    return AxisAlignedBB.func_72330_a(minX, minY, minZ, maxX, maxY, maxZ);
  }



  public void func_149719_a(IBlockAccess blockAccess, int x, int y, int z) {
    AxisAlignedBB aabb = getCollisionBoundingBoxFromPoolIBlockAccess(blockAccess, x, y, z);
    func_149676_a((float)aabb.field_72340_a, (float)aabb.field_72338_b, (float)aabb.field_72339_c, (float)aabb.field_72336_d, (float)aabb.field_72337_e, (float)aabb.field_72334_f);
  }



  public int func_149645_b() {
    if (TFCBlocks.isBlockVSupport(this)) {
      return TFCBlocks.woodSupportRenderIdV;
    }
    return TFCBlocks.woodSupportRenderIdH;
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    Boolean isHorizontal = Boolean.valueOf(TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z)));


    double minX = 0.25D, minY = 0.0D, minZ = 0.25D;
    double maxX = 0.75D, maxY = 0.75D, maxZ = 0.75D;


    if (isHorizontal.booleanValue()) {

      minY = 0.5D;
      maxY = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
        maxX = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
        minX = 0.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
        maxZ = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1)))
        minZ = 0.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y - 1, z))) {
        minY = 0.0D;
      }
    } else {

      minY = 0.0D;
      maxY = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x + 1, y, z)))
        maxX = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x - 1, y, z)))
        minX = 0.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z + 1)))
        maxZ = 1.0D;
      if (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || TFCBlocks.isBlockHSupport(world.func_147439_a(x, y, z - 1))) {
        minZ = 0.0D;
      }
    }
    return AxisAlignedBB.func_72330_a(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
  }



  public void func_149636_a(World world, EntityPlayer entityplayer, int i, int j, int k, int l) {
    if (this == TFCBlocks.woodSupportH) {
      func_149642_a(world, i, j, k, new ItemStack(TFCBlocks.woodSupportV, 1, l));
    } else if (this == TFCBlocks.woodSupportH2) {
      func_149642_a(world, i, j, k, new ItemStack(TFCBlocks.woodSupportV2, 1, l));
    } else {
      func_149642_a(world, i, j, k, new ItemStack(this, 1, l));
    }
  }


  public boolean func_149637_q() {
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess par1iBlockAccess, int par2, int par3, int par4, int par5) {
    return true;
  }



  public void func_149689_a(World world, int i, int j, int k, EntityLivingBase entity, ItemStack is) {
    super.func_149689_a(world, i, j, k, entity, is);
  }





  public void func_149695_a(World world, int i, int j, int k, Block l) {
    boolean isOtherHorizontal = TFCBlocks.isBlockHSupport(l);

    boolean isHorizontal = TFCBlocks.isBlockHSupport(world.func_147439_a(i, j, k));
    boolean isVertical = TFCBlocks.isBlockVSupport(world.func_147439_a(i, j, k));

    int meta = world.func_72805_g(i, j, k);

    if (isVertical && !isOtherHorizontal) {


      if (!world.func_147439_a(i, j - 1, k).func_149662_c() && !TFCBlocks.isBlockVSupport(world.func_147439_a(i, j - 1, k)))
      {
        func_149636_a(world, (EntityPlayer)null, i, j, k, meta);
        world.func_147468_f(i, j, k);
      }

    } else if (isHorizontal) {

      boolean b1 = !isSupportConnected(world, i, j, k);
      if (b1) {

        func_149636_a(world, (EntityPlayer)null, i, j, k, meta);
        world.func_147468_f(i, j, k);
      }
      else if (TFCBlocks.isBlockVSupport(world.func_147439_a(i, j - 1, k))) {

        if (this == TFCBlocks.woodSupportH) {
          world.func_147465_d(i, j, k, TFCBlocks.woodSupportV, meta, 2);
        } else if (this == TFCBlocks.woodSupportH2) {
          world.func_147465_d(i, j, k, TFCBlocks.woodSupportV2, meta, 2);
        }
      }
    }
  }


  public boolean func_149707_d(World world, int x, int y, int z, int side) {
    Block downBlock = world.func_147439_a(x, y - 1, z);


    if (!TFCBlocks.isBlockVSupport(downBlock)) {

      if (side == 0 && world.func_147437_c(x, y - 1, z))
      {
        return true;
      }
      if (side == 1 && downBlock.func_149662_c())
      {
        return true;
      }
      if (side == 2) {

        if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
          return true;
        }
      } else if (side == 3) {

        if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
          return true;
        }
      } else if (side == 4) {

        if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
          return true;
        }
      } else if (side == 5) {

        if (isNextToSupport(world, x, y, z) != 0 && hasSupportsInRange(world, x, y, z, 5)) {
          return true;
        }
      }
    } else if (TFCBlocks.isBlockVSupport(downBlock) || downBlock.func_149662_c()) {

      if (side == 1 && world.func_147437_c(x, y, z))
        return true;
      if (side == 2 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z - 1)) || world.func_147439_a(x, y, z - 1).func_149662_c()) && world.func_147437_c(x, y, z - 1))
        return true;
      if (side == 3 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x, y, z + 1)) || world.func_147439_a(x, y, z + 1).func_149662_c()) && world.func_147437_c(x, y, z + 1))
        return true;
      if (side == 4 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x - 1, y, z)) || world.func_147439_a(x - 1, y, z).func_149662_c()) && world.func_147437_c(x - 1, y, z))
        return true;
      if (side == 5 && (TFCBlocks.isBlockVSupport(world.func_147439_a(x + 1, y, z)) || world.func_147439_a(x + 1, y, z).func_149662_c()) && world.func_147437_c(x + 1, y, z)) {
        return true;
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockWoodSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */