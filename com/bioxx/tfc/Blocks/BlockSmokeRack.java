package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TESmokeRack;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;





public class BlockSmokeRack
  extends BlockTerraContainer
{
  public BlockSmokeRack() {
    super(Material.field_151594_q);
  }



  public void func_149719_a(IBlockAccess world, int x, int y, int z) {
    if ((world.func_72805_g(x, y, z) & 0x1) == 0) {

      func_149676_a(0.45F, 0.45F, 0.0F, 0.55F, 0.55F, 1.0F);
    }
    else {

      func_149676_a(0.0F, 0.45F, 0.45F, 1.0F, 0.55F, 0.55F);
    }
  }



  public boolean isReplaceable(IBlockAccess world, int x, int y, int z) {
    return true;
  }



  public boolean func_149655_b(IBlockAccess bAccess, int x, int y, int z) {
    return true;
  }



  public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
    return new ItemStack(TFCItems.woolYarn);
  }



  public boolean func_149727_a(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ) {
    boolean flag = false;
    if (!world.field_72995_K) {

      int meta = world.func_72805_g(x, y, z);
      TESmokeRack te = (TESmokeRack)world.func_147438_o(x, y, z);
      ItemStack yarn = TFC_Core.getItemInInventory(TFCItems.woolYarn, (IInventory)entityplayer.field_71071_by);
      if ((meta & 0x1) == 0 && hitZ < 0.5D) {

        if (te.func_70301_a(0) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
        {
          te.func_70299_a(0, entityplayer.field_71071_by.func_70448_g().func_77946_l());
          (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
          entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
          flag = true;
        }
        else if (te.func_70301_a(0) != null)
        {
          TFC_Core.giveItemToPlayer(te.removeStackInSlot(0), entityplayer);
          flag = true;
        }

      } else if ((meta & 0x1) == 0 && hitZ >= 0.5D) {

        if (te.func_70301_a(1) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
        {
          te.func_70299_a(1, entityplayer.field_71071_by.func_70448_g().func_77946_l());
          (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
          entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
          flag = true;
        }
        else if (te.func_70301_a(1) != null)
        {
          TFC_Core.giveItemToPlayer(te.removeStackInSlot(1), entityplayer);
          flag = true;
        }

      } else if ((meta & 0x1) == 1 && hitX < 0.5D) {

        if (te.func_70301_a(0) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g()))
        {
          te.func_70299_a(0, entityplayer.field_71071_by.func_70448_g().func_77946_l());
          (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
          entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
          flag = true;
        }
        else if (te.func_70301_a(0) != null)
        {
          TFC_Core.giveItemToPlayer(te.removeStackInSlot(0), entityplayer);
          flag = true;
        }

      } else if ((meta & 0x1) == 1 && hitX >= 0.5D) {

        if (te.func_70301_a(1) == null && yarn != null && isItemValid(entityplayer.field_71071_by.func_70448_g())) {

          te.func_70299_a(1, entityplayer.field_71071_by.func_70448_g().func_77946_l());
          (entityplayer.field_71071_by.func_70448_g()).field_77994_a--;
          entityplayer.field_71071_by.func_146026_a(TFCItems.woolYarn);
          return true;
        }
        if (te.func_70301_a(1) != null) {

          TFC_Core.giveItemToPlayer(te.removeStackInSlot(1), entityplayer);
          flag = true;
        }
      }
    }
    return flag;
  }


  private boolean isItemValid(ItemStack is) {
    if (is == null)
      return false;
    if (is.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodMeat) {

      if (!Food.isCooked(is) && Food.isBrined(is)) {
        return true;
      }
    } else if (is.func_77973_b().func_77658_a().toLowerCase().contains("cheese")) {

      if (!Food.isCooked(is)) {
        return true;
      }
    }
    return false;
  }



  public boolean func_149662_c() {
    return false;
  }



  public int func_149645_b() {
    return TFCBlocks.smokeRackRenderId;
  }



  public void func_149695_a(World world, int x, int y, int z, Block block) {
    int meta = world.func_72805_g(x, y, z);
    if (!world.field_72995_K) {

      if ((meta & 0x1) == 0) {

        if (!isValidNeighbor(world, x, y, z - 1, ForgeDirection.NORTH) || !isValidNeighbor(world, x, y, z + 1, ForgeDirection.SOUTH))
        {
          TFC_Core.destroyBlock(world, x, y, z);

        }

      }
      else if (!isValidNeighbor(world, x - 1, y, z, ForgeDirection.WEST) || !isValidNeighbor(world, x + 1, y, z, ForgeDirection.EAST)) {

        TFC_Core.destroyBlock(world, x, y, z);
      }


      if (world.func_147439_a(x, y + 1, z) instanceof com.bioxx.tfc.Blocks.Terrain.BlockCollapsible)
      {
        TFC_Core.destroyBlock(world, x, y, z);
      }
    }
  }


  private boolean isValidNeighbor(World world, int x, int y, int z, ForgeDirection dir) {
    Block b = world.func_147439_a(x, y, z);
    return (b == this || b.isSideSolid((IBlockAccess)world, x, y, z, dir.getOpposite()));
  }



  public boolean func_149686_d() {
    return false;
  }



  public AxisAlignedBB func_149668_a(World world, int i, int j, int k) {
    return null;
  }



  public AxisAlignedBB func_149633_g(World world, int x, int y, int z) {
    if ((world.func_72805_g(x, y, z) & 0x1) == 0)
    {
      return AxisAlignedBB.func_72330_a(x + 0.45D, y + 0.45D, z, x + 0.55D, y + 0.55D, (z + 1));
    }


    return AxisAlignedBB.func_72330_a(x, y + 0.45D, z + 0.45D, (x + 1), y + 0.55D, z + 0.55D);
  }




  public Item func_149650_a(int i, Random rand, int j) {
    return TFCItems.woolYarn;
  }



  @SideOnly(Side.CLIENT)
  public void func_149651_a(IIconRegister reg) {
    this.field_149761_L = reg.func_94245_a("terrafirmacraft:String");
  }



  public TileEntity createTileEntity(World world, int meta) {
    return (TileEntity)new TESmokeRack();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\BlockSmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */