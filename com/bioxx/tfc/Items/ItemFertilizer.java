package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFarmland;
import fof.tfcsu.utils.ExpBonus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;









public class ItemFertilizer
  extends ItemTerra
{
  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K)
    {
      if (TFC_Core.isFarmland(world.func_147439_a(x, y, z))) {

        TEFarmland tef = (TEFarmland)world.func_147438_o(x, y, z);
        if (tef.nutrients[3] != tef.getSoilMax())
        {
          ExpBonus.USE_FERTILIZER.give(entityplayer);
          return tef.fertilize(itemstack, false);
        }

      } else if (world.func_147438_o(x, y, z) instanceof com.bioxx.tfc.TileEntities.TECrop && TFC_Core.isFarmland(world.func_147439_a(x, y - 1, z))) {

        TEFarmland tef = (TEFarmland)world.func_147438_o(x, y - 1, z);
        if (tef.nutrients[3] != tef.getSoilMax()) {

          ExpBonus.USE_FERTILIZER.give(entityplayer);
          return tef.fertilize(itemstack, false);
        }
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemFertilizer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */