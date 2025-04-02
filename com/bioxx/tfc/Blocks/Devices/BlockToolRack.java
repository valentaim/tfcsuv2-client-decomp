package com.bioxx.tfc.Blocks.Devices;

import com.bioxx.tfc.Blocks.BlockTerraContainer;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TEToolRack;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;













public class BlockToolRack
  extends BlockTerraContainer
{
  protected String[] woodNames;

  public BlockToolRack() {
    super(Material.field_151575_d);
    func_149647_a(TFCTabs.TFC_DECORATION);
    this.woodNames = Global.WOOD_ALL;
  }



  public boolean func_149662_c() {
    return false;
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }



  public AxisAlignedBB func_149668_a(World world, int x, int y, int z) {
    return null;
  }



  public boolean func_149686_d() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.toolRackRenderId;
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      TileEntity te = world.func_147438_o(x, y, z);
      if (te instanceof TEToolRack) {

        TEToolRack tet = (TEToolRack)te;
        int dir = world.func_72805_g(x, y, z);
        if (dir == 0) {

          if (hitX < 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 0, 0);
          } else if (hitX > 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 1, 0);
          } else if (hitX < 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 2, 0);
          } else if (hitX > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 3, 0);
          }
        } else if (dir == 1) {

          if (hitZ < 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 0, 1);
          } else if (hitZ > 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 1, 1);
          } else if (hitZ < 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 2, 1);
          } else if (hitZ > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 3, 1);
          }
        } else if (dir == 2) {

          if (hitX < 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 0, 2);
          } else if (hitX > 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 1, 2);
          } else if (hitX < 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 2, 2);
          } else if (hitX > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 3, 2);
          }
        } else if (dir == 3) {

          if (hitZ < 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 0, 3);
          } else if (hitZ > 0.5D && hitY > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 1, 3);
          } else if (hitZ < 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 2, 3);
          } else if (hitZ > 0.5D) {
            handleArea(world, x, y, z, entityplayer, tet, 3, 3);
          }
        }  world.func_147471_g(x, y, z);

        return true;
      }
    }
    return false;
  }













  private void handleArea(World world, int x, int y, int z, EntityPlayer entityplayer, TEToolRack te, int slot, int dir) {
    boolean hasToolInHand = (entityplayer.func_71045_bC() != null && (entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemTool || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemHoe || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemProPick || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemBow || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemSword || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemAxe || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemSpade || entityplayer.func_71045_bC().func_77973_b() instanceof net.minecraft.item.ItemShears || entityplayer.func_71045_bC().func_77977_a().toString().contains("crowbar") || entityplayer.func_71045_bC().func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemSpindle));
    if (te.storage[slot] == null && hasToolInHand) {

      te.storage[slot] = entityplayer.func_71045_bC().func_77946_l();
      entityplayer.field_71071_by.field_70462_a[entityplayer.field_71071_by.field_70461_c] = null;
    }
    else if (te.storage[slot] != null) {

      te.ejectItem(slot, dir);
      te.storage[slot] = null;
    }
  }



  public boolean removedByPlayer(World world, EntityPlayer player, int x, int y, int z) {
    if (!world.field_72995_K) {


      TileEntity te = world.func_147438_o(x, y, z);
      if (te instanceof TEToolRack) {

        TEToolRack rack = (TEToolRack)te;
        func_149642_a(world, x, y, z, new ItemStack(TFCBlocks.toolRack, 1, rack.woodType));
      }
    }
    return world.func_147468_f(x, y, z);
  }





  public void func_149636_a(World world, EntityPlayer entityplayer, int x, int y, int z, int meta) {}





  public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
    ArrayList<ItemStack> ret = new ArrayList<>();

    int damageValue = func_149643_k(world, x, y, z);
    ret.add(new ItemStack((Block)this, 1, damageValue));

    return ret;
  }



  public TileEntity func_149915_a(World world, int meta) {
    return (TileEntity)new TEToolRack();
  }



  public void func_149719_a(IBlockAccess access, int x, int y, int z) {
    int dir = access.func_72805_g(x, y, z);
    if (dir == 0) {
      func_149676_a(0.0F, 0.0F, 0.85F, 1.0F, 1.0F, 1.0F);
    } else if (dir == 1) {
      func_149676_a(0.0F, 0.0F, 0.0F, 0.15F, 1.0F, 1.0F);
    } else if (dir == 2) {
      func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.15F);
    } else if (dir == 3) {
      func_149676_a(0.85F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }
  }


  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    int dir = world.func_72805_g(x, y, z);
    if (dir == 0)
      return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.85F), (x + 1.0F), (y + 1.0F), (z + 1.0F));
    if (dir == 1)
      return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.0F), (x + 0.15F), (y + 1.0F), (z + 1.0F));
    if (dir == 2)
      return AxisAlignedBB.func_72330_a((x + 0.0F), (y + 0.0F), (z + 0.0F), (x + 1.0F), (y + 1.0F), (z + 0.15F));
    if (dir == 3) {
      return AxisAlignedBB.func_72330_a((x + 0.85F), (y + 0.0F), (z + 0.0F), (x + 1.0F), (y + 1.0F), (z + 1.0F));
    }
    return AxisAlignedBB.func_72330_a(x, y, z, (x + 1), (y + 1), (z + 1));
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    int dir = world.func_72805_g(x, y, z);

    if (dir == 0) {

      if (!world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH)) {
        removedByPlayer(world, (EntityPlayer)null, x, y, z);
      }
    } else if (dir == 1) {

      if (!world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST)) {
        removedByPlayer(world, (EntityPlayer)null, x, y, z);
      }
    } else if (dir == 2) {

      if (!world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH)) {
        removedByPlayer(world, (EntityPlayer)null, x, y, z);
      }
    } else if (dir == 3) {

      if (!world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST)) {
        removedByPlayer(world, (EntityPlayer)null, x, y, z);
      }
    }
  }


  public int func_149660_a(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta) {
    if (side == 4) return 3;
    if (side == 5) return 1;
    if (side == 2) return 0;
    if (side == 3) return 2;

    return 5;
  }



  public void func_149689_a(World world, int x, int y, int z, EntityLivingBase entity, ItemStack is) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TEToolRack) {

      TEToolRack rack = (TEToolRack)te;
      rack.woodType = (byte)is.func_77960_j();
      world.func_147471_g(x, y, z);
    }
  }



  public boolean func_149707_d(World world, int x, int y, int z, int side) {
    if (func_149742_c(world, x, y, z)) {

      if (side == 5 && world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.EAST))
        return true;
      if (side == 4 && world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.WEST))
        return true;
      if (side == 2 && world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
        return true;
      if (side == 3 && world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
        return true;
    }
    return false;
  }







  @SideOnly(Side.CLIENT)
  public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    for (int i = 0; i < this.woodNames.length; i++) {
      list.add(new ItemStack(item, 1, i));
    }
  }


  public IIcon func_149673_e(IBlockAccess bAccess, int x, int y, int z, int side) {
    TEToolRack te = (TEToolRack)bAccess.func_147438_o(x, y, z);

    if (te.woodType > 15)
      return TFCBlocks.woodSupportV2.func_149691_a(side, te.woodType);
    return TFCBlocks.woodSupportV.func_149691_a(side, te.woodType);
  }



  public IIcon func_149691_a(int side, int meta) {
    if (meta > 15)
      return TFCBlocks.woodSupportV2.func_149691_a(side, meta);
    return TFCBlocks.woodSupportV.func_149691_a(side, meta);
  }



  public int func_149692_a(int dmg) {
    return dmg;
  }




  public void func_149651_a(IIconRegister registerer) {}




  @SideOnly(Side.CLIENT)
  public boolean func_149646_a(IBlockAccess bAccess, int x, int y, int z, int side) {
    return true;
  }






  public int func_149643_k(World world, int x, int y, int z) {
    TileEntity te = world.func_147438_o(x, y, z);
    if (te instanceof TEToolRack)
      return ((TEToolRack)te).woodType;
    return 0;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Devices\BlockToolRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */