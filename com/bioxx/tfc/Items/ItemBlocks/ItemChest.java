package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.TileEntities.TEChest;
import com.bioxx.tfc.api.Constant.Global;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemChest
  extends ItemTerraBlock
{
  public ItemChest(Block par1) {
    super(par1);
    func_77627_a(true);
    this.metaNames = Global.WOOD_ALL;
  }




  public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
    if (!world.func_147465_d(x, y, z, this.field_150939_a, 0, 3))
    {
      return false;
    }

    if (world.func_147439_a(x, y, z) == this.field_150939_a) {

      this.field_150939_a.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
      this.field_150939_a.func_149714_e(world, x, y, z, 0);

      TEChest chest = (TEChest)world.func_147438_o(x, y, z);
      if (metadata >= Global.WOOD_ALL.length) {

        metadata /= 2;
        chest.isDoubleChest = true;
      }
      chest.type = metadata;
    }


    return true;
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < Global.WOOD_ALL.length; i++)
      list.add(new ItemStack((Item)this, 1, i));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */