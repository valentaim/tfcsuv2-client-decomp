package com.bioxx.tfc.Items;

import com.bioxx.tfc.Blocks.Devices.BlockSluice;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;




public class ItemSluice
  extends ItemTerra
{
  public ItemSluice() {
    setWeight(EnumWeight.HEAVY);
    setSize(EnumSize.HUGE);
  }





  public int getPlacedBlockMetadata(int i) {
    return i;
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {




      int r = MathHelper.func_76128_c((entityplayer.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      byte byte0 = 0;
      byte byte1 = 0;
      if (r == 0) {

        byte1 = 1;
      }
      else if (r == 1) {

        byte0 = -1;
      }
      else if (r == 2) {

        byte1 = -1;
      }
      else if (r == 3) {

        byte0 = 1;
      }
      if (((BlockSluice)TFCBlocks.sluice).canPlace(world, i, j + 1, k, r)) {

        world.func_147465_d(i, j + 1, k, TFCBlocks.sluice, r, 2);
        if (world.func_147439_a(i, j + 1, k) == TFCBlocks.sluice) {

          world.func_147465_d(i + byte0, j + 1, k + byte1, TFCBlocks.sluice, r + 8, 2);
          if (!entityplayer.field_71075_bZ.field_75098_d)
            entityplayer.field_71071_by.func_70298_a(entityplayer.field_71071_by.field_70461_c, 1);
        }
        return true;
      }
    }
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemSluice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */