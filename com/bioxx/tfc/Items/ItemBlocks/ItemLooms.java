package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.TileEntities.TELoom;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;


public class ItemLooms
  extends ItemTerraBlock
{
  public ItemLooms(Block par1) {
    super(par1);
    func_77656_e(0);
    func_77627_a(true);
    func_77637_a(TFCTabs.TFC_DEVICES);
    this.metaNames = Global.WOOD_ALL;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.LARGE;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.HEAVY;
  }




  public void func_77624_a(ItemStack is, EntityPlayer player, List arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
  }




  public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
    if (!world.func_147465_d(x, y, z, this.field_150939_a, metadata & 0xF, 3))
    {
      return false;
    }

    if (world.func_147439_a(x, y, z) == this.field_150939_a) {

      this.field_150939_a.func_149689_a(world, x, y, z, (EntityLivingBase)player, stack);
      this.field_150939_a.func_149714_e(world, x, y, z, 0);

      if (world.func_147438_o(x, y, z) instanceof TELoom) {

        TELoom te = (TELoom)world.func_147438_o(x, y, z);
        if (te != null) {

          te.loomType = metadata;
          world.func_147471_g(x, y, z);

          int l = MathHelper.func_76128_c((player.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
          byte byte0 = 0;
          if (l == 0)
            byte0 = 0;
          if (l == 1)
            byte0 = 1;
          if (l == 2)
            byte0 = 2;
          if (l == 3)
            byte0 = 3;
          te.rotation = byte0;
        }
      }
    }
    return true;
  }




  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    for (int i = 0; i < this.metaNames.length; i++)
      list.add(new ItemStack((Item)this, 1, i));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemLooms.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */