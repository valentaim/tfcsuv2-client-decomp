package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFCOptions;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;


public class ItemCustomBucketMilk
  extends ItemTerra
  implements IFood
{
  public ItemCustomBucketMilk() {
    func_77625_d(1);
    func_77637_a(TFCTabs.TFC_FOODS);
    setFolder("tools/");
  }



  public boolean canStack() {
    return false;
  }



  public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> list) {
    list.add(createTag(new ItemStack((Item)this, 1), 20.0F));
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    float ounces = Food.getWeight(is);
    if (ounces > 0.0F)
      arraylist.add(ounces + " oz");
    float decay = Food.getDecay(is);
    if (decay > 0.0F) {

      float perc = decay / ounces;
      String s = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.milk.fresh");
      if (perc > 50.0F)
        s = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.milk.old");
      if (perc > 70.0F) {
        s = EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.milk.sour");
      }
      arraylist.add(s);
    }
    if (TFCOptions.enableDebugMode) {
      arraylist.add(EnumChatFormatting.DARK_GRAY + "Decay: " + decay);
    }
  }

  public static ItemStack createTag(ItemStack is, float weight) {
    if (!is.func_77942_o()) {
      is.func_77982_d(new NBTTagCompound());
    }
    Food.setWeight(is, weight);
    Food.setDecay(is, 0.0F);
    Food.setDecayTimer(is, (int)TFC_Time.getTotalHours() + 1);
    return is;
  }



  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
    if (!world.field_72995_K && foodstats.needFood()) {

      world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);

      if (is.func_77942_o()) {





        float tasteFactor = foodstats.getTasteFactor(is);
        if (foodstats.nutrDairy < 0.2D) foodstats.addNutrition(((IFood)is.func_77973_b()).getFoodGroup(), 20.0F * tasteFactor);

      }
      foodstats.restoreWater(player, 16000);

      TFC_Core.setPlayerFoodStats(player, foodstats);

      is = new ItemStack(TFCItems.woodenBucketEmpty);
      is.field_77990_d = null;
    }
    return is;
  }






  public int func_77626_a(ItemStack par1ItemStack) {
    return 32;
  }






  public EnumAction func_77661_b(ItemStack par1ItemStack) {
    return EnumAction.drink;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer entity) {
    MovingObjectPosition mop = func_77621_a(world, entity, true);
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats(entity);

    if (mop == null)
    {
      if (fs.needDrink() && fs.needFood())
        entity.func_71008_a(is, func_77626_a(is));
    }
    return is;
  }



  public EnumFoodGroup getFoodGroup() {
    return EnumFoodGroup.Dairy;
  }



  public int getFoodID() {
    return -1;
  }



  public ItemStack onDecayed(ItemStack is, World world, int i, int j, int k) {
    return new ItemStack(TFCItems.woodenBucketEmpty);
  }



  public float getDecayRate(ItemStack is) {
    return 6.0F;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }



  public boolean isEdible(ItemStack is) {
    return true;
  }



  public boolean isUsable(ItemStack is) {
    return false;
  }


  public int getTasteSweet(ItemStack is) {
    return 0;
  }


  public int getTasteSour(ItemStack is) {
    return 0;
  }


  public int getTasteSalty(ItemStack is) {
    return 0;
  }


  public int getTasteBitter(ItemStack is) {
    return 0;
  }


  public int getTasteSavory(ItemStack is) {
    return 10;
  }



  public float getFoodMaxWeight(ItemStack is) {
    return 80.0F;
  }



  public boolean renderDecay() {
    return true;
  }



  public boolean renderWeight() {
    return false;
  }



  public float getFoodWeight(ItemStack is) {
    return Food.getWeight(is);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomBucketMilk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */