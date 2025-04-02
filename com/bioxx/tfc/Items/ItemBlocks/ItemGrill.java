package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.TileEntities.TEGrill;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;


public class ItemGrill
  extends ItemTerraBlock
{
  public ItemGrill(Block par1) {
    super(par1);
    func_77637_a(TFCTabs.TFC_TOOLS);
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
    {
      if (side == 1 && world.func_147437_c(x, y + 1, z)) {

        int out = side;
        int hinge = 0;

        if (hitX < 0.2D) {
          hinge = 0;
        } else if (hitZ < 0.2D) {
          hinge = 1;
        } else if (hitX > 0.8D) {
          hinge = 2;
        } else if (hitZ > 0.8D) {
          hinge = 3;
        } else {
          hinge = 0;
        }
        out += hinge << 4;

        TileEntity teFire = world.func_147438_o(x, y, z);
        if (teFire != null && teFire instanceof com.bioxx.tfc.api.TileEntities.TEFireEntity && checkSides(world, x, y, z)) {

          if (world.func_147465_d(x, y + 1, z, TFCBlocks.grill, itemstack.func_77960_j(), 2))
          {
            TEGrill teGrill = (TEGrill)world.func_147438_o(x, y + 1, z);
            teGrill.data = (byte)out;
          }

        }
        else if (world.func_147437_c(x, y + 2, z) && checkSides(world, x, y + 1, z)) {

          if (world.func_147465_d(x, y + 2, z, TFCBlocks.grill, itemstack.func_77960_j(), 2)) {

            TEGrill teGrill = (TEGrill)world.func_147438_o(x, y + 2, z);
            teGrill.data = (byte)out;
          }
        } else {

          return false;
        }  (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c]).field_77994_a--;
        return true;
      }
    }
    return false;
  }


  public boolean checkSides(World world, int x, int y, int z) {
    int count = 0;
    if (world.func_147439_a(x - 1, y, z).isSideSolid((IBlockAccess)world, x - 1, y, z, ForgeDirection.WEST))
    {
      count++;
    }
    if (world.func_147439_a(x + 1, y, z).isSideSolid((IBlockAccess)world, x + 1, y, z, ForgeDirection.EAST))
    {
      count++;
    }
    if (world.func_147439_a(x, y, z - 1).isSideSolid((IBlockAccess)world, x, y, z - 1, ForgeDirection.SOUTH))
    {
      count++;
    }
    if (world.func_147439_a(x, y, z + 1).isSideSolid((IBlockAccess)world, x, y, z + 1, ForgeDirection.NORTH))
    {
      count++;
    }
    return (count >= 2);
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */