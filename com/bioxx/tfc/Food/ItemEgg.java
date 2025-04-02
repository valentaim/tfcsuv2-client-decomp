package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;


public class ItemEgg
  extends ItemFoodTFC
  implements IFood
{
  public ItemEgg() {
    super(EnumFoodGroup.Protein, 0, 0, 0, 0, 0, false, false);
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(createTag(new ItemStack((Item)this, 1), 2.0F));
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    arraylist.add(getFoodGroupName(getFoodGroup()));
    addFoodHeatInformation(is, arraylist);

    if (is.func_77942_o()) {

      if (is.func_77978_p().func_74764_b("Fertilized")) {
        arraylist.add(EnumChatFormatting.GOLD + TFC_Core.translate("gui.fertilized"));
      } else {
        addFoodInformation(is, player, arraylist);
      }
    } else {

      arraylist.add(TFC_Core.translate("gui.badnbt"));
    }
  }






  public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
    if (is.func_77942_o()) {

      if (is.func_77978_p().func_74764_b("Fertilized")) {

        is.field_77990_d.func_82580_o("Fertilized");
        is.field_77990_d.func_82580_o("Genes");
      }
      if (is.func_77978_p().func_74764_b("Fertilized"))
      {
        return true;
      }
    }
    return false;
  }



  public float getDecayRate(ItemStack is) {
    if (Food.isPickled(is))
      return 0.3F;
    return 0.5F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */