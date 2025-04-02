package com.bioxx.tfc.Items;

import com.bioxx.tfc.Entities.EntityCustomMinecart;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import java.util.List;
import net.minecraft.block.BlockRailBase;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;



public class ItemCustomMinecart
  extends ItemTerra
{
  public int minecartType;

  public ItemCustomMinecart(int par2) {
    this.field_77777_bU = 1;
    this.minecartType = par2;
    func_77637_a(CreativeTabs.field_78029_e);
    setWeight(EnumWeight.HEAVY);
    setSize(EnumSize.HUGE);
  }



  public void func_94581_a(IIconRegister registerer) {
    this.field_77791_bV = registerer.func_94245_a("minecart_chest");
  }



  public boolean func_77648_a(ItemStack itemstack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
    if (BlockRailBase.func_150051_a(world.func_147439_a(x, y, z))) {

      if (!world.field_72995_K)
      {
        world.func_72838_d((Entity)new EntityCustomMinecart(world, (x + 0.5F), (y + 0.5F), (z + 0.5F)));
      }

      itemstack.field_77994_a--;
      return true;
    }


    return false;
  }




  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List) {
    par3List.add(new ItemStack(par1, 1, 0));
  }



  public boolean canStack() {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemCustomMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */