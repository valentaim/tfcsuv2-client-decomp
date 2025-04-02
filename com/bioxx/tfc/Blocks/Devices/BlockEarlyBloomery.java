package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.CollisionRayTraceStandard;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEBloomery;
import com.bioxx.tfc.api.Interfaces.ICustomCollision;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockEarlyBloomery
  extends BlockTerraContainer
  implements ICustomCollision
{
  private IIcon textureOn;
  private IIcon textureOff;
  public static final int[][] BLOOMERY_TO_STACK_MAP = new int[][] { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
  public static final int[][] SIDES_MAP = new int[][] { { 1, 0 }, { 0, 1 }, { 1, 0 }, { 0, 1 } };






  public BlockEarlyBloomery() {
    super(Material.field_151576_e);
    func_149647_a(TFCTabs.TFC_DEVICES);
    func_149676_a(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
  }



  public int getLightValue(IBlockAccess world, int x, int y, int z) {
    int meta = world.func_72805_g(x, y, z) & 0x4;
    if (meta == 0) {
      return 0;
    }
    return 15;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
    if (!func_149718_j(world, x, y, z)) {

      world.func_147468_f(x, y, z);
      world.func_72838_d((Entity)new EntityItem(world, x, y, z, new ItemStack((Block)this, 1)));
    }
    else if ((TEBloomery)world.func_147438_o(x, y, z) != null) {

      TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
      ItemStack is = entityplayer.func_71045_bC();

      if (is != null && (is.func_77973_b() == TFCItems.fireStarter || is.func_77973_b() == TFCItems.flintSteel)) {

        if (te.canLight()) {
          entityplayer.func_71045_bC().func_77972_a(1, (EntityLivingBase)entityplayer);
        }
      } else {

        world.func_72889_a(entityplayer, 1003, x, y, z, 0);
        if (isOpen(world.func_72805_g(x, y, z))) {
          world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) - 8, 3);
        } else {
          world.func_72921_c(x, y, z, world.func_72805_g(x, y, z) + 8, 3);
        }
      }
    }  return true;
  }



  public boolean func_149718_j(World world, int x, int y, int z) {
    if (world.func_147437_c(x, y, z)) {
      return true;
    }
    if (world.func_147438_o(x, y, z) instanceof TEBloomery) {

      boolean flipped = false;
      int dir = world.func_72805_g(x, y, z) & 0x3;
      TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);

      if (te != null) {
        flipped = te.isFlipped;
      }
      if (checkStack(world, x, y, z, dir))
      {
        if (checkVertical(world, x, y, z, flipped)) {

          if (checkHorizontal(world, x, y, z, flipped)) {
            return true;
          }
        } else if (te != null && !flipped) {

          tryFlip(world, x, y, z);
          flipped = te.isFlipped;
          if (checkVertical(world, x, y, z, flipped))
          {
            if (checkHorizontal(world, x, y, z, flipped))
              return true;
          }
        }
      }
    }
    return false;
  }


  public boolean checkStack(World world, int x, int y, int z, int dir) {
    int[] map = BLOOMERY_TO_STACK_MAP[dir];
    int centerX = x + map[0];
    int centerZ = z + map[1];
    if (isNorthStackValid(world, centerX, y, centerZ - 1) || (centerX == x && centerZ - 1 == z))
    {
      if (isSouthStackValid(world, centerX, y, centerZ + 1) || (centerX == x && centerZ + 1 == z))
      {
        if (isEastStackValid(world, centerX - 1, y, centerZ) || (centerX - 1 == x && centerZ == z))
        {
          if (isWestStackValid(world, centerX + 1, y, centerZ) || (centerX + 1 == x && centerZ == z))
          {
            return true;
          }
        }
      }
    }
    return false;
  }


  private boolean isNorthStackValid(World world, int x, int y, int z) {
    return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
      .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
      .func_147439_a(x, y, z).func_149721_r()) ||
      TFC_Core.isSouthFaceSolid(world, x, y, z));
  }


  private boolean isSouthStackValid(World world, int x, int y, int z) {
    return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
      .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
      .func_147439_a(x, y, z).func_149721_r()) ||
      TFC_Core.isNorthFaceSolid(world, x, y, z));
  }


  private boolean isEastStackValid(World world, int x, int y, int z) {
    return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
      .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
      .func_147439_a(x, y, z).func_149721_r()) ||
      TFC_Core.isWestFaceSolid(world, x, y, z));
  }


  private boolean isWestStackValid(World world, int x, int y, int z) {
    return (((world.func_147439_a(x, y, z).func_149688_o() == Material.field_151576_e || world
      .func_147439_a(x, y, z).func_149688_o() == Material.field_151573_f) && world
      .func_147439_a(x, y, z).func_149721_r()) ||
      TFC_Core.isEastFaceSolid(world, x, y, z));
  }


  private boolean checkHorizontal(World world, int x, int y, int z, boolean flip) {
    int dir = world.func_72805_g(x, y, z) & 0x3;

    if (flip) {
      dir = flipDir(dir);
    }
    int[] map = SIDES_MAP[dir];

    boolean l = false;
    boolean r = false;
    if ((world.func_147439_a(x - map[0], y, z - map[1]).func_149688_o() == Material.field_151576_e || world.func_147439_a(x - map[0], y, z - map[1]).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x - map[0], y, z - map[1]).func_149662_c()) {
      l = true;
    }
    if ((!l && world.func_147439_a(x - map[0], y, z - map[1]) == TFCBlocks.detailed) || world.func_147439_a(x - map[0], y, z - map[1]) == TFCBlocks.stoneSlabs) {

      switch (dir) {

        case 0:
          if (TFC_Core.isNorthFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]))
            l = true;
          break;
        case 1:
          if (TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1]))
            l = true;
          break;
        case 2:
          if (TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isEastFaceSolid(world, x - map[0], y, z - map[1]))
            l = true;
          break;
        case 3:
          if (TFC_Core.isWestFaceSolid(world, x - map[0], y, z - map[1]) && TFC_Core.isSouthFaceSolid(world, x - map[0], y, z - map[1])) {
            l = true;
          }
          break;
      }
      if (!TFC_Core.isBottomFaceSolid(world, x - map[0], y, z - map[1]))
        l = false;
      if (!TFC_Core.isTopFaceSolid(world, x - map[0], y, z - map[1])) {
        l = false;
      }
    }
    if ((world.func_147439_a(x + map[0], y, z + map[1]).func_149688_o() == Material.field_151576_e || world.func_147439_a(x + map[0], y, z + map[1]).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x + map[0], y, z + map[1]).func_149662_c()) {
      r = true;
    }
    if ((!r && world.func_147439_a(x + map[0], y, z + map[1]) == TFCBlocks.detailed) || world.func_147439_a(x + map[0], y, z + map[1]) == TFCBlocks.stoneSlabs)
    {
      switch (dir) {

        case 0:
          if (TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]))
            r = true;
          break;
        case 1:
          if (TFC_Core.isEastFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1]))
            r = true;
          break;
        case 2:
          if (TFC_Core.isSouthFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]))
            r = true;
          break;
        case 3:
          if (TFC_Core.isWestFaceSolid(world, x + map[0], y, z + map[1]) && TFC_Core.isNorthFaceSolid(world, x + map[0], y, z + map[1])) {
            r = true;
          }
          break;
      }
    }
    if (!TFC_Core.isBottomFaceSolid(world, x + map[0], y, z + map[1]))
      r = false;
    if (!TFC_Core.isTopFaceSolid(world, x + map[0], y, z + map[1])) {
      r = false;
    }
    return (l && r);
  }



  private boolean checkVertical(World world, int x, int y, int z, boolean flip) {
    int dir = world.func_72805_g(x, y, z) & 0x3;

    if (flip) {
      dir = flipDir(dir);
    }
    boolean b = false;
    boolean t = false;
    if ((world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151576_e || world.func_147439_a(x, y - 1, z).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x, y - 1, z).func_149662_c()) {
      b = true;
    }
    if ((!b && world.func_147439_a(x, y - 1, z) == TFCBlocks.detailed) || world.func_147439_a(x, y - 1, z) == TFCBlocks.stoneSlabs) {

      switch (dir) {

        case 0:
          if (TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isWestFaceSolid(world, x, y - 1, z))
            b = true;
          break;
        case 1:
          if (TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isSouthFaceSolid(world, x, y - 1, z))
            b = true;
          break;
        case 2:
          if (TFC_Core.isSouthFaceSolid(world, x, y - 1, z) && TFC_Core.isEastFaceSolid(world, x, y - 1, z) && TFC_Core.isWestFaceSolid(world, x, y - 1, z))
            b = true;
          break;
        case 3:
          if (TFC_Core.isWestFaceSolid(world, x, y - 1, z) && TFC_Core.isNorthFaceSolid(world, x, y - 1, z) && TFC_Core.isSouthFaceSolid(world, x, y - 1, z)) {
            b = true;
          }
          break;
      }
      if (!TFC_Core.isTopFaceSolid(world, x, y - 1, z)) {
        b = false;
      }
    }
    if ((world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151576_e || world.func_147439_a(x, y + 1, z).func_149688_o() == Material.field_151573_f) && world.func_147439_a(x, y + 1, z).func_149662_c()) {
      t = true;
    }
    if ((!t && world.func_147439_a(x, y + 1, z) == TFCBlocks.detailed) || world.func_147439_a(x, y + 1, z) == TFCBlocks.stoneSlabs) {

      switch (dir) {

        case 0:
          if (TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isWestFaceSolid(world, x, y + 1, z))
            t = true;
          break;
        case 1:
          if (TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isSouthFaceSolid(world, x, y + 1, z))
            t = true;
          break;
        case 2:
          if (TFC_Core.isSouthFaceSolid(world, x, y + 1, z) && TFC_Core.isEastFaceSolid(world, x, y + 1, z) && TFC_Core.isWestFaceSolid(world, x, y + 1, z))
            t = true;
          break;
        case 3:
          if (TFC_Core.isWestFaceSolid(world, x, y + 1, z) && TFC_Core.isNorthFaceSolid(world, x, y + 1, z) && TFC_Core.isSouthFaceSolid(world, x, y + 1, z)) {
            t = true;
          }
          break;
      }
      if (!TFC_Core.isBottomFaceSolid(world, x, y + 1, z) || !TFC_Core.isTopFaceSolid(world, x, y + 1, z)) {
        t = false;
      }
    }
    return (b && t);
  }



  public boolean func_149742_c(World world, int x, int y, int z) {
    return func_149718_j(world, x, y, z);
  }



  public IIcon func_149691_a(int i, int j) {
    int lit = ((j & 0x4) == 4) ? 1 : 0;
    if (!isOpen(j))
    {
      if (lit == 1)
        return this.textureOn;
    }
    return this.textureOff;
  }



  public void func_149651_a(IIconRegister iconRegisterer) {
    this.textureOn = iconRegisterer.func_94245_a("terrafirmacraft:devices/Bloomery On");
    this.textureOff = iconRegisterer.func_94245_a("terrafirmacraft:devices/Bloomery Off");
  }



  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
    if (!world.field_72995_K) {

      int dir = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;

      world.func_72921_c(x, y, z, dir, 2);
      if (!func_149718_j(world, x, y, z))
      {
        func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
      }
    }
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    clearStack(world, x, y, z);
    return true;
  }


  public void clearStack(World world, int x, int y, int z) {
    if (!world.field_72995_K) {

      world.func_147468_f(x, y, z);
      int meta = world.func_72805_g(x, y, z);
      int[] dir = BLOOMERY_TO_STACK_MAP[meta & 0x3];
      if (world.func_147439_a(x + dir[0], y, z + dir[1]) == TFCBlocks.molten)
        world.func_147468_f(x + dir[0], y, z + dir[1]);
      if (world.func_147439_a(x + dir[0], y + 1, z + dir[1]) == TFCBlocks.molten)
        world.func_147468_f(x + dir[0], y + 1, z + dir[1]);
      if (world.func_147439_a(x + dir[0], y + 2, z + dir[1]) == TFCBlocks.molten)
        world.func_147468_f(x + dir[0], y + 2, z + dir[1]);
      if (world.func_147439_a(x + dir[0], y + 3, z + dir[1]) == TFCBlocks.molten) {
        world.func_147468_f(x + dir[0], y + 3, z + dir[1]);
      }
    }
  }


  public void func_149695_a(World world, int x, int y, int z, Block block) {
    if (!func_149718_j(world, x, y, z))
    {
      if (!tryFlip(world, x, y, z)) {

        world.func_147468_f(x, y, z);
        func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
      }
      else if (!func_149718_j(world, x, y, z)) {

        world.func_147468_f(x, y, z);
        func_149642_a(world, x, y, z, new ItemStack((Block)this, 1));
      }
    }
  }

  public static int flipDir(int dir) {
    int out = 0;
    if (dir - 2 >= 0) {
      out = dir - 2;
    } else if (dir + 2 < 4) {
      out = dir + 2;
    }  return out;
  }


  private boolean tryFlip(World world, int x, int y, int z) {
    TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
    te.swapFlipped();
    return func_149718_j(world, x, y, z);
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEBloomery();
  }


































  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }


  public static boolean isOpen(int par0) {
    return ((par0 & 0x8) != 0);
  }



  public int func_149645_b() {
    return TFCBlocks.bloomeryRenderId;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149686_d() {
    return false;
  }




  public void addCollisionBoxesToList(World world, int x, int y, int z, List<Object[]> list) {
    int meta = world.func_72805_g(x, y, z);
    int dir = meta & 0x3;
    if (world.func_147438_o(x, y, z) instanceof TEBloomery) {

      TEBloomery te = (TEBloomery)world.func_147438_o(x, y, z);
      if (te.isFlipped)
        dir = flipDir(dir);
    }
    float f = 0.125F;

    if (!isOpen(meta)) {

      if (dir == 0) {
        list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, f) });
      } else if (dir == 1) {
        list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 1.0D) });
      } else if (dir == 2) {
        list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D) });
      } else if (dir == 3) {
        list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, f, 1.0D, 1.0D) });
      }

    }
    else if (dir == 0) {

      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, f, 1.0D, 0.5D) });
      list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.0D, 1.0D, 1.0D, 0.5D) });
    }
    else if (dir == 1) {

      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, 0.0D, 1.0D, 1.0D, f) });
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.5D, 0.0D, (1.0F - f), 1.0D, 1.0D, 1.0D) });
    }
    else if (dir == 2) {

      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.5D, f, 1.0D, 1.0D) });
      list.add(new Object[] { AxisAlignedBB.func_72330_a((1.0F - f), 0.0D, 0.5D, 1.0D, 1.0D, 1.0D) });
    }
    else if (dir == 3) {

      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, 0.0D, 0.5D, 1.0D, f) });
      list.add(new Object[] { AxisAlignedBB.func_72330_a(0.0D, 0.0D, (1.0F - f), 0.5D, 1.0D, 1.0D) });
    }
  }




  public MovingObjectPosition func_149731_a(World world, int x, int y, int z, Vec3 player, Vec3 view) {
    return CollisionRayTraceStandard.collisionRayTrace(this, world, x, y, z, player, view);
  }



  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
    return true;
  }



  protected void func_149642_a(World world, int x, int y, int z, ItemStack is) {
    if (!world.field_72995_K && world.func_82736_K().func_82766_b("doTileDrops")) {

      clearStack(world, x, y, z);
      EntityItem ei = new EntityItem(world, x + 0.5D, y + 0.5D, z + 0.5D, is);
      ei.field_70159_w = 0.0D;
      ei.field_70181_x = 0.0D;
      ei.field_70179_y = 0.0D;
      ei.field_145804_b = 10;
      world.func_72838_d((Entity)ei);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockEarlyBloomery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */