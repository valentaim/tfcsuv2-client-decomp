package com.bioxx.tfc.Food;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.Render.Item.FoodItemRenderer;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.FoodRegistry;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.TFCOptions;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.bioxx.tfc.api.Util.Helper;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;






public class ItemMeal
  extends ItemTerra
  implements IFood
{
  public ItemMeal() {
    this.field_77787_bX = true;
    this.metaNames = new String[] { "Meal0", "Meal1", "Meal2", "Meal3", "Meal4", "Meal5", "Meal6", "Meal7", "Meal8", "Meal9", "Meal10" };
    this.metaIcons = new net.minecraft.util.IIcon[11];
    setFolder("food/");
    this.stackable = false;
    func_77637_a(null);
  }



  public void func_94581_a(IIconRegister registerer) {
    super.func_94581_a(registerer);
    MinecraftForgeClient.registerItemRenderer((Item)this, (IItemRenderer)new FoodItemRenderer());
  }



  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(createTag(new ItemStack((Item)this, 1)));
  }



  public static ItemStack createTag(ItemStack is) {
    if (!is.func_77942_o()) {
      is.func_77982_d(new NBTTagCompound());
    }
    Food.setWeight(is, 0.0F);
    Food.setDecay(is, 0.0F);
    Food.setDecayTimer(is, (int)TFC_Time.getTotalHours() + 1);
    return is;
  }



  public String func_77667_c(ItemStack itemstack) {
    return func_77658_a();
  }



  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    ItemTerra.addSizeInformation(is, arraylist);
    if (!TFC_Core.showShiftInformation())
    {
      arraylist.add("");
    }

    if (is.func_77942_o()) {


      ItemFoodTFC.addFoodHeatInformation(is, arraylist);
      addFoodInformation(is, player, arraylist);

      if (TFC_Core.showShiftInformation())
      {
        addFGInformation(is, arraylist);
      }
    }
    else {

      arraylist.add(TFC_Core.translate("gui.badnbt"));
    }
  }





  public void addFoodInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    float ounces = Helper.roundNumber(Food.getWeight(is), 100.0F);
    if (ounces > 0.0F)
      arraylist.add(TFC_Core.translate("gui.food.amount") + " " + ounces + " oz / " + 160.0F + " oz");
    float decay = Food.getDecay(is);
    if (decay > 0.0F)
      arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + " " + Helper.roundNumber(decay / ounces * 100.0F, 10.0F) + "%");
    if (TFCOptions.enableDebugMode) {

      arraylist.add(EnumChatFormatting.DARK_GRAY + TFC_Core.translate("gui.food.decay") + ": " + decay);
      arraylist.add(EnumChatFormatting.DARK_GRAY + "Decay Rate: " + getDecayRate(is));
    }

    if (TFC_Core.showCtrlInformation()) {
      ItemFoodTFC.addTasteInformation(is, player, arraylist);
    } else {
      arraylist.add(TFC_Core.translate("gui.showtaste"));
    }
  }

  protected void addFGInformation(ItemStack is, List<String> arraylist) {
    int[] fg = Food.getFoodGroups(is);
    for (int i = 0; i < fg.length; i++) {

      if (fg[i] != -1) {
        arraylist.add(localize(fg[i]));
      }
    }
  }

  protected String localize(int id) {
    return ItemFoodTFC.getFoodGroupColor(FoodRegistry.getInstance().getFoodGroup(id)) +
      TFC_Core.translate(FoodRegistry.getInstance().getFood(id).func_77658_a() + ".name");
  }


  protected float[] getNutritionalWeights(int[] foodGroups) {
    float[] nw = new float[foodGroups.length];
    float[] fw = getFoodWeights();
    float totalWeight = 0.0F; int i;
    for (i = 0; i < foodGroups.length; i++) {

      if (foodGroups[i] != -1)
      {
        totalWeight += fw[i];
      }
    }

    for (i = 0; i < foodGroups.length; i++)
    {
      nw[i] = fw[i] / totalWeight;
    }
    return nw;
  }


  protected float[] getFoodWeights() {
    return new float[] { 10.0F, 4.0F, 4.0F, 2.0F };
  }







  protected float getEatAmount(FoodStatsTFC fs, float amount) {
    float eatAmount = Math.min(amount, 5.0F);
    float stomachDiff = fs.stomachLevel + eatAmount - fs.getMaxStomach(fs.player);
    if (stomachDiff > 0.0F)
      eatAmount -= stomachDiff;
    return eatAmount;
  }


  protected float getFillingBoost() {
    return 1.0F;
  }



  public ItemStack func_77654_b(ItemStack is, World world, EntityPlayer player) {
    world.func_72956_a((Entity)player, "random.burp", 0.5F, world.field_73012_v.nextFloat() * 0.1F + 0.9F);

    FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);
    if (!world.field_72995_K)
    {

      if (is.func_77942_o()) {

        float weight = Food.getWeight(is);
        float decay = Math.max(Food.getDecay(is), 0.0F);
        float eatAmount = getEatAmount(foodstats, weight - decay);
        float tasteFactor = foodstats.getTasteFactor(is);

        int[] fg = Food.getFoodGroups(is);
        float[] nWeights = getNutritionalWeights(fg);
        for (int i = 0; i < fg.length; i++) {

          if (fg[i] != -1) {
            foodstats.addNutrition(FoodRegistry.getInstance().getFoodGroup(fg[i]), eatAmount * nWeights[i] * 2.5F);
          }
        }

        foodstats.stomachLevel += eatAmount * getFillingBoost();
        foodstats.setSatisfaction(foodstats.getSatisfaction() + eatAmount / 3.0F * tasteFactor, fg);


        if (FoodStatsTFC.reduceFood(is, eatAmount))
        {
          is.field_77994_a = 0;

        }
      }
      else {

        String error = TFC_Core.translate("error.error") + " " + is.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");


        TFC_Core.sendInfoMessage(player, (IChatComponent)new ChatComponentText(error));
      }
    }
    TFC_Core.setPlayerFoodStats(player, foodstats);
    return is;
  }














  public static boolean isWarm(ItemStack is) {
    return (TFC_ItemHeat.getTemp(is) > TFC_ItemHeat.isCookable(is) * 0.1D);
  }






  public int func_77626_a(ItemStack par1ItemStack) {
    return 32;
  }






  public EnumAction func_77661_b(ItemStack par1ItemStack) {
    return EnumAction.eat;
  }






  public ItemStack func_77659_a(ItemStack is, World world, EntityPlayer player) {
    FoodStatsTFC foodstats = TFC_Core.getPlayerFoodStats(player);


    if (foodstats.needFood()) {
      player.func_71008_a(is, func_77626_a(is));
    }
    return is;
  }



  public EnumSize getSize(ItemStack is) {
    return EnumSize.SMALL;
  }



  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }



  public EnumFoodGroup getFoodGroup() {
    return null;
  }



  public int getFoodID() {
    return 0;
  }



  public float getDecayRate(ItemStack is) {
    return Food.getDecayRate(is);
  }



  public ItemStack onDecayed(ItemStack is, World world, int i, int j, int k) {
    return null;
  }



  public boolean isEdible(ItemStack is) {
    return false;
  }



  public boolean isUsable(ItemStack is) {
    return false;
  }


  public int getTasteSweet(ItemStack is) {
    int base = 0;
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSweet"))
      base = is.func_77978_p().func_74762_e("tasteSweet");
    return base + Food.getSweetMod(is);
  }


  public int getTasteSour(ItemStack is) {
    int base = 0;
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSour"))
      base = is.func_77978_p().func_74762_e("tasteSour");
    return base + Food.getSourMod(is);
  }


  public int getTasteSalty(ItemStack is) {
    int base = 0;
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteSalty"))
      base = is.func_77978_p().func_74762_e("tasteSalty");
    return base + Food.getSaltyMod(is);
  }


  public int getTasteBitter(ItemStack is) {
    int base = 0;
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteBitter"))
      base = is.func_77978_p().func_74762_e("tasteBitter");
    return base + Food.getBitterMod(is);
  }


  public int getTasteSavory(ItemStack is) {
    int base = 0;
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("tasteUmami"))
      base = is.func_77978_p().func_74762_e("tasteUmami");
    return base + Food.getSavoryMod(is);
  }


  public float getFoodMaxWeight(ItemStack is) {
    return 20.0F;
  }


  public boolean renderDecay() {
    return true;
  }


  public boolean renderWeight() {
    return true;
  }



  public float getFoodWeight(ItemStack is) {
    return Food.getWeight(is);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\ItemMeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */