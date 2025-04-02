package com.bioxx.tfc.Food;

import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCCrafting;
import com.bioxx.tfc.api.TFCItems;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;






public class ItemSalad
  extends ItemMeal
{
  public ItemSalad() {
    this.field_77787_bX = true;
    this.metaNames = new String[] { "Salad0", "Salad1", "Salad2", "Salad3" };
    this.metaIcons = new net.minecraft.util.IIcon[4];
    setFolder("food/");
  }


  
  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(createTag(new ItemStack((Item)this, 1)));
  }


  
  public static ItemStack createTag(ItemStack is) {
    ItemMeal.createTag(is);
    int[] foodGroups = { -1, -1, -1, -1 };
    Food.setFoodGroups(is, foodGroups);
    return is;
  }



  
  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    is = super.func_77654_b(is, world, player);

    
    if (is.field_77994_a == 0)
    {
      
      if (TFCCrafting.enableBowlsAlwaysBreak || world.field_73012_v.nextInt(2) == 0) {
        
        world.func_72956_a((Entity)player, "terrafirmacraft:item.ceramicbreak", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F);
      
      }
      else if (!player.field_71071_by.func_70441_a(new ItemStack(TFCItems.potteryBowl, 1, 1))) {

        
        return new ItemStack(TFCItems.potteryBowl, 1, 1);
      } 
    }
    
    return is;
  }


  
  public ItemStack onDecayed(ItemStack is, World world, int i, int j, int k) {
    return new ItemStack(TFCItems.potteryBowl, 1, 1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemSalad.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */