package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemYarn
  extends ItemTerra
{
  protected final int[][] sidesMap = new int[][] { { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, 0 }, { 1, 0, 0 } };



  public ItemYarn() {
    this.field_77787_bX = false;
    func_77656_e(0);
    func_77637_a(TFCTabs.TFC_MATERIALS);
  }



  public boolean func_77648_a(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K && side > 1 && !world.func_147439_a(x, y, z).equals(TFCBlocks.loom)) {

      int length = 0;
      int[] map = this.sidesMap[side];
      ForgeDirection opp = ForgeDirection.VALID_DIRECTIONS[side].getOpposite(); int i;
      for (i = 1; i < 6; i++) {

        int xCoord = x + map[0] * i;
        int yCoord = y + map[1] * i;
        int zCoord = z + map[2] * i;
        Block b = world.func_147439_a(xCoord, yCoord, zCoord);

        if (b.func_149688_o().func_76222_j()) {

          length++;
        } else {
          if (!b.isSideSolid((IBlockAccess)world, xCoord, yCoord, zCoord, opp) && !(b instanceof com.bioxx.tfc.Blocks.BlockWoodSupport))
          {
            return false;
          }

          break;
        }
      }

      if (length == 5) {

        int xCoord = x + map[0] * 6;
        int yCoord = y + map[1] * 6;
        int zCoord = z + map[2] * 6;
        Block b = world.func_147439_a(xCoord, yCoord, zCoord);
        if (!b.isSideSolid((IBlockAccess)world, xCoord, yCoord, zCoord, opp) && !(b instanceof com.bioxx.tfc.Blocks.BlockWoodSupport))
        {
          return false;
        }
      }

      if (is.field_77994_a < length) {
        return false;
      }
      for (i = 1; i <= length; i++) {

        int xCoord = x + map[0] * i;
        int yCoord = y + map[1] * i;
        int zCoord = z + map[2] * i;
        int meta = (side & 0x4) >> 2;
        world.func_147465_d(xCoord, yCoord, zCoord, TFCBlocks.smokeRack, meta, 2);
        is.field_77994_a--;
      }

      return true;
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemYarn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */