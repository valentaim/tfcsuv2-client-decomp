package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Blocks.BlockWoodSupport;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemWoodSupport extends ItemTerraBlock {
  public ItemWoodSupport(Block par1) {
    super(par1);
    this.field_77787_bX = true;
    func_77656_e(0);
    this.metaNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }


  public Block getBlock() {
    return this.field_150939_a;
  }


  public boolean isValidUnder(World world, int x, int y, int z, int side) {
    if (side == 0) {
      y--;
    } else if (side == 1) {
      y++;
    } else if (side == 2) {
      z--;
    } else if (side == 3) {
      z++;
    } else if (side == 4) {
      x--;
    } else if (side == 5) {
      x++;
    }
    Block b = world.func_147439_a(x, y - 1, z);
    return (b == TFCBlocks.woodSupportV || b == TFCBlocks.woodSupportV2 || b.isSideSolid((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP));
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (y < 250 && y > 0 && side == 1 && isValidUnder(world, x, y, z, side)) {

      if (!player.func_70093_af() && world.func_147437_c(x, y + 1, z) && world.func_147437_c(x, y + 2, z) && world.func_147437_c(x, y + 3, z) && itemstack.field_77994_a >= 3 && world
        .func_147439_a(x, y, z) != TFCBlocks.woodSupportV && world.func_147439_a(x, y, z) != TFCBlocks.woodSupportV2) {

        placeBlockAt(getBlock(), itemstack, player, world, x, y + 1, z, itemstack.func_77960_j(), 2);
        placeBlockAt(getBlock(), itemstack, player, world, x, y + 2, z, itemstack.func_77960_j(), 2);
        placeBlockAt(getBlock(), itemstack, player, world, x, y + 3, z, itemstack.func_77960_j(), 2);
        itemstack.field_77994_a -= 3;
        return true;
      }


      placeBlockAt(getBlock(), itemstack, player, world, x, y + 1, z, itemstack.func_77960_j(), 3);
      itemstack.field_77994_a--;
      return true;
    }

    if (y < 255 && y > 0 && side == 0) {

      boolean shouldGen = false;
      int dist = 0;
      for (dist = 1; dist <= 20 && !shouldGen; dist++) {

        if (!world.func_147439_a(x, y - dist, z).isReplaceable((IBlockAccess)world, x, y - dist, z)) {



          if (world.func_147439_a(x, y - dist, z).isSideSolid((IBlockAccess)world, x, y - dist, z, ForgeDirection.UP))
          {
            shouldGen = true;
          }


          break;
        }
      }


      if (itemstack.field_77994_a >= dist - 1) {
        for (int j = dist - 1; j >= 1 && shouldGen;) {

          if (world.func_147439_a(x, y - j, z).isReplaceable((IBlockAccess)world, x, y - j, z)) {

            placeBlockAt(getBlock(), itemstack, player, world, x, y - j, z, itemstack.func_77960_j(), 3);
            itemstack.field_77994_a--;
            world.func_147471_g(x, y - j, z);

            j--;
          }
        }
      }
    } else {
      Block b = TFCBlocks.woodSupportH;
      if (getBlock() == TFCBlocks.woodSupportV2) {
        b = TFCBlocks.woodSupportH2;
      }
      if (side == 0) {
        y--;
      } else if (side == 1) {
        y++;
      } else if (side == 2) {
        z--;
      } else if (side == 3) {
        z++;
      } else if (side == 4) {
        x--;
      } else if (side == 5) {
        x++;
      }
      if (y == 255 && b.func_149688_o().func_76220_a())
      {
        return false;
      }
      if (world.func_147472_a(b, x, y, z, false, side, (Entity)player, itemstack)) {

        ForgeDirection dir = BlockWoodSupport.getSupportDirection(world, x, y, z);

        int[] dist = BlockWoodSupport.getSupportsInRangeDir(world, x, y, z, 5, false);
        int total = BlockWoodSupport.getDistanceFromDirection(dir, dist);
        if (total == Integer.MAX_VALUE) {

          total = 1;
          dir = ForgeDirection.getOrientation(side);
        }
        if (itemstack.field_77994_a < total)
          return false;
        int i1 = func_77647_b(itemstack.func_77960_j());
        int count = 0;

        do {
          int j1 = b.func_149660_a(world, x + dir.offsetX * count, y, z + dir.offsetZ * count, side, hitX, hitY, hitZ, i1);
          if (!placeBlockAt(b, itemstack, player, world, x + dir.offsetX * count, y, z + dir.offsetZ * count, j1, 2))
            continue;
          world.func_72908_a((x + (dir.offsetX * count) + 0.5F), (y + 0.5F), (z + (dir.offsetZ * count) + 0.5F), b.field_149762_H.func_150496_b(), (b.field_149762_H.func_150497_c() + 1.0F) / 2.0F, b.field_149762_H.func_150494_d() * 0.8F);
          itemstack.field_77994_a--;

          ++count;
        } while (count < total);



        return true;
      }
    }
    return false;
  }


  public boolean placeBlockAt(Block b, ItemStack is, EntityPlayer player, World world, int x, int y, int z, int metadata, int flag) {
    if (!world.func_147465_d(x, y, z, b, metadata, flag))
    {
      return false;
    }

    if (world.func_147439_a(x, y, z) == b) {

      b.func_149689_a(world, x, y, z, (EntityLivingBase)player, is);
      b.func_149714_e(world, x, y, z, metadata);
    }

    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemWoodSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */