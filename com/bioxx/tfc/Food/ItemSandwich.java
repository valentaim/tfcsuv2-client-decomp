package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.api.Food;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;






public class ItemSandwich
  extends ItemMeal
{
  public ItemSandwich() {
    this.field_77787_bX = true;
    this.metaNames = new String[] { "Sandwich Wheat", "Sandwich Oat", "Sandwich Barley", "Sandwich Rye", "Sandwich Corn", "Sandwich Rice" };
    this.metaIcons = new net.minecraft.util.IIcon[6];
    setFolder("food/");
  }



  protected void addFGInformation(ItemStack is, List<String> arraylist) {
    int[] fg = Food.getFoodGroups(is);
    for (int i = 0; i < fg.length; i++) {

      if (i == 5 && fg[5] == fg[0])
        return;
      if (fg[i] != -1) {
        arraylist.add(localize(fg[i]));
      }
    }
  }


  protected float getEatAmount(FoodStatsTFC fs, float amount) {
    float eatAmount = Math.min(amount, 10.0F);
    float stomachDiff = fs.stomachLevel + eatAmount - fs.getMaxStomach(fs.player);
    if (stomachDiff > 0.0F)
      eatAmount -= stomachDiff;
    return eatAmount;
  }



  protected float getFillingBoost() {
    return 1.25F;
  }



  protected float[] getFoodWeights() {
    return new float[] { 2.0F, 3.0F, 2.0F, 2.0F, 1.0F };
  }


  public float getFoodMaxWeight(ItemStack is) {
    return 10.0F;
  }


  public boolean renderDecay() {
    return true;
  }


  public boolean renderWeight() {
    return false;
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
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemSandwich.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */