package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class ItemBellows
  extends ItemTerraBlock
{
  public ItemBellows(Block par1) {
    super(par1);
    func_77637_a(TFCTabs.TFC_TOOLS);
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (!world.field_72995_K) {

      int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
      if (side == 1 && world.func_147439_a(x, y, z).func_149721_r() && world.func_147439_a(x, y, z).func_149662_c() && world.func_147437_c(x, y + 1, z)) {

        world.func_147465_d(x, y + 1, z, TFCBlocks.bellows, l, 2);
        (player.field_71071_by.field_70462_a[player.field_71071_by.field_70461_c]).field_77994_a--;
        return true;
      }
    }
    return false;
  }


  public EnumSize getSize(ItemStack is) {
    return EnumSize.HUGE;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */