package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.ItemFoodTFC;
import com.bioxx.tfc.api.Events.ItemCookEvent;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;











public class FoodCraftingHandler
{
  public static boolean preCrafted;

  @SubscribeEvent
  public void onFoodCook(ItemCookEvent event) {}

  private ItemStack getweightstack(ItemStack is, float weight) {
    ItemStack out = new ItemStack(is.func_77973_b());
    ItemFoodTFC.createTag(out, weight);
    return out;
  }


  @SubscribeEvent
  public void onFoodCrafting(PlayerEvent.ItemCraftedEvent e) {
    if (preCrafted) {

      preCrafted = false;

      return;
    }
    ItemStack craftResult = e.crafting;
    IInventory craftingInv = e.craftMatrix;

    if (craftingInv != null) {




      int num = 0;
      boolean butter = false;
      boolean salt = false;
      boolean knife = false;
      float butterweight = 0.0F;
      float saltweight = 0.0F;
      int saltpos = 0;
      int butterpos = 0; int i;
      for (i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack itemstack = craftingInv.func_70301_a(i);
        if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
          if (itemstack.func_77977_a().toLowerCase().contains("butter")) { butter = true; butterweight = Food.getWeight(itemstack); butterpos = i; }
          else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { salt = true; saltweight = Food.getWeight(itemstack); saltpos = i; }
          else if (itemstack.func_77977_a().toLowerCase().contains("knife")) { knife = true; }
           num++;
        }
      }
      if (!knife && butter && salt && num == 2) {

        if (saltweight >= butterweight / 3.0F) {
          ItemStack salt_ = craftingInv.func_70301_a(saltpos);
          Food.setWeight(salt_, saltweight - butterweight / 3.0F);
          TFC_Core.giveItemToPlayer(salt_, e.player);
          if (butterweight + butterweight / 3.0F > 160.0F) TFC_Core.giveItemToPlayer(getweightstack(craftingInv.func_70301_a(butterpos), butterweight + butterweight / 3.0F - 160.0F), e.player);

        } else {

          ItemStack butter_ = craftingInv.func_70301_a(butterpos);
          Food.setWeight(butter_, Math.min(160.0F, butterweight - saltweight * 3.0F));
          TFC_Core.giveItemToPlayer(butter_, e.player);
        }

        return;
      }
      for (i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack itemstack = craftingInv.func_70301_a(i);
        if (itemstack != null &&
          itemstack.func_77977_a().toLowerCase().contains("knife")) knife = true;
      }
      if (knife && refiningMilk(craftResult, craftingInv, true)) {
        List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
        handleItem(e.player, craftingInv, knives);

        return;
      }

      if (refiningGrain(craftResult, craftingInv)) {

        List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
        handleItem(e.player, craftingInv, knives);

        for (int j = 0; j < craftingInv.func_70302_i_(); j++) {

          ItemStack inputStack = craftingInv.func_70301_a(j);
          if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {


            float foodWeight = Food.getWeight(inputStack);
            int strawCount = 0;

            for (int k = 0; k < foodWeight; k += 4) {
              strawCount++;
            }
            TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.straw, strawCount), e.player);
          }
        }
      } else if (makingDough(craftResult, craftingInv)) {

        for (i = 0; i < craftingInv.func_70302_i_(); i++) {

          ItemStack inputStack = craftingInv.func_70301_a(i);
          if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {


            float grainWeight = Food.getWeight(inputStack);
            float grainDecay = Food.getDecay(inputStack);
            if (grainDecay >= 0.0F)
              grainWeight -= grainDecay;
            grainWeight -= Math.min(grainWeight, 80.0F);

            inputStack = ItemFoodTFC.createTag(inputStack, grainWeight, 0.0F);

            if (grainWeight > 0.0F) {

              inputStack.field_77994_a++;
              if (inputStack.field_77994_a > 2)
                inputStack.field_77994_a = 2;
            }
          }
        }
      } else if (craftResult.func_77942_o() && craftResult.func_77978_p().func_74764_b("foodWeight")) {

        craftResult = processFoodInput(e.player, craftResult, craftingInv);
      }
    }
  }










  private static ItemStack processFoodInput(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
    float finalWeight = 0.0F;
    float finalDecay = 0.0F;
    int[] fuelTasteProfile = { 0, 0, 0, 0, 0 };
    int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
    float cookedTime = 0.0F;
    int foodCount = 0;
    int itemCount = 0;
    int foodSlot = 0;

    int milkcount = 0;
    int todelcount = 0; int i;
    for (i = 0; i < craftingInv.func_70302_i_(); i++) {

      ItemStack is = craftingInv.func_70301_a(i);
      if (is != null) {

        itemCount++;
        if (is.func_77973_b() instanceof ItemFoodTFC && is.func_77942_o() && is.func_77978_p().func_74764_b("foodWeight")) {

          foodSlot = i;
          if (foodCount == 0) {

            fuelTasteProfile = Food.getFuelProfile(is);
            cookedTasteProfile = Food.getCookedProfile(is);
            cookedTime = Food.getCooked(is);
          }

          float inputWeight = Food.getWeight(is);
          float oldInputWeight = inputWeight;
          float inputDecayPercent = Food.getDecay(is) / oldInputWeight;
          float inputDecay = Food.getDecay(is);
          float weightChange = 0.0F;



          if (finalWeight < 160.0F &&
            Food.isSameSmoked(cookedTasteProfile, Food.getCookedProfile(is)) &&
            Food.isSameSmoked(fuelTasteProfile, Food.getFuelProfile(is)) && (
            (int)Food.getCooked(is) - 600) / 120 == ((int)cookedTime - 600) / 120) {

            weightChange = Math.min(160.0F - finalWeight, inputWeight);
            inputWeight -= weightChange;
            finalWeight += weightChange;
          }


          if (inputWeight != oldInputWeight) {

            if (inputWeight == 0.0F) {

              if (finalDecay < 0.0F) {

                if (inputDecay > finalDecay) {
                  finalDecay = inputDecay;
                }
              } else {
                finalDecay += inputDecay;
              }
            } else {

              float decayChange = weightChange * inputDecayPercent;
              inputDecay -= decayChange;
              if (finalDecay < 0.0F) {

                if (decayChange > finalDecay) {
                  finalDecay = decayChange;
                }
              } else {
                finalDecay += decayChange;
              }
            }  foodCount++;
          }

          milkcount++;
          if (inputWeight > 0.0F) {

            Food.setWeight(is, inputWeight);
            Food.setDecay(is, inputDecay);
            is.field_77994_a++;
            if (is.field_77994_a > 2) is.field_77994_a = 2;
            todelcount++;
          }
        }
      }
    }  if (milkcount > 1 && (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream") || craftResult
      .func_77973_b().func_77658_a().toLowerCase().contains("yogurt"))) {
      int result = milkcount - todelcount - 1;
      if (result > 0) TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.potteryBowl, result, 1), player);

    }
    if (craftResult.field_77994_a == 0) {
      craftResult.field_77994_a = 1;
    }
    if (itemCount == 1) {

      if (finalDecay > 0.0F)
      {
        for (i = 0; i < player.field_71071_by.func_70302_i_(); i++) {

          ItemStack stack = player.field_71071_by.func_70301_a(i);

          if (stack != null && stack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {


            stack.func_77972_a(1, (EntityLivingBase)player);
            if (stack.func_77960_j() >= stack.func_77958_k()) {
              player.field_71071_by.func_70299_a(i, null);
            }



            break;
          }
        }
      }
    } else {
      for (i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack itemstack = craftingInv.func_70301_a(i);
        if (itemstack != null) {

          boolean fullInv = isInvFull(player);

          if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife && fullInv)
          {
            if (!preCrafted) {


              itemstack.field_77994_a++;
              if (itemstack.field_77994_a > 2) {
                itemstack.field_77994_a = 2;
              }
            }
          }
          if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife && (!fullInv || !preCrafted))
          {
            if (finalDecay > 0.0F) {

              damageItem(player, craftingInv, i, itemstack.func_77973_b());
            }
            else if (finalDecay <= 0.0F) {

              if (finalWeight / 2.0F < 1.0F) {


                itemstack.field_77994_a++;
                if (itemstack.field_77994_a > 2) {
                  itemstack.field_77994_a = 2;
                }
              } else {

                damageItem(player, craftingInv, i, itemstack.func_77973_b());

                if (!craftResult.func_77973_b().func_77658_a().toLowerCase().contains("yogurt") &&
                  !craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream")) {


                  Food.setWeight(craftingInv.func_70301_a(foodSlot), Helper.roundNumber(finalWeight / 2.0F, 100.0F));

                  (craftingInv.func_70301_a(foodSlot)).field_77994_a++;
                  if ((craftingInv.func_70301_a(foodSlot)).field_77994_a > 2) (craftingInv.func_70301_a(foodSlot)).field_77994_a = 2;
                }
              }
            }  }
        }
      }
    }
    return craftResult;
  }


  private static boolean isButter(IInventory craftingInv) {
    boolean butter = false;
    boolean salt = false;
    boolean knife = false;
    int num = 0;
    for (int i = 0; i < craftingInv.func_70302_i_(); i++) {

      ItemStack itemstack = craftingInv.func_70301_a(i);
      if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {
        if (itemstack.func_77977_a().toLowerCase().contains("butter")) { butter = true; }
        else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { salt = true; }
        else if (itemstack.func_77977_a().toLowerCase().contains("knife")) { knife = true; }
         num++;
      }
    }
    if (!knife && butter && salt && num == 2) return true;
    return false;
  }


  private static boolean haskmilk(IInventory inv) {
    for (int i = 0; i < inv.func_70302_i_(); i++) {

      ItemStack itemstack = inv.func_70301_a(i);
      if (itemstack != null && itemstack.func_77973_b() == TFCItems.woodenBucketMilk) return true;
    }
    return false;
  }







  public static void updateOutput(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
    if (isButter(craftingInv)) {
      float weight = 0.0F;
      float saltweight = 0.0F;
      for (int j = 0; j < craftingInv.func_70302_i_() || (weight == 0.0F && saltweight == 0.0F); j++) {

        ItemStack itemstack = craftingInv.func_70301_a(j);
        if (itemstack != null && itemstack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood)
          if (itemstack.func_77977_a().toLowerCase().contains("butter")) { weight = Food.getWeight(itemstack); }
          else if (itemstack.func_77977_a().toLowerCase().contains("salt")) { saltweight = Food.getWeight(itemstack); }

      }  if (saltweight >= weight / 3.0F) {





        craftResult = ItemFoodTFC.createTag(craftResult, Math.min(160.0F, weight + weight / 3.0F), Helper.roundNumber(Food.getDecay(craftResult), 100.0F));


      }
      else {


        craftResult = ItemFoodTFC.createTag(craftResult, Math.min(160.0F, saltweight * 3.0F), Helper.roundNumber(Food.getDecay(craftResult), 100.0F));
      }
      return;
    }
    if (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("yogurt")) {

      if (haskmilk(craftingInv)) {
        ItemFoodTFC.createTag(craftResult, 40.0F); return;
      }
    } else if (craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream")) {

      if (haskmilk(craftingInv)) {
        ItemFoodTFC.createTag(craftResult, 80.0F); return;
      }
    }
    float finalWeight = 0.0F;
    float finalDecay = 0.0F;
    int sweetMod = -1;
    int sourMod = -1;
    int saltyMod = -1;
    int bitterMod = -1;
    int umamiMod = -1;
    int[] fuelTasteProfile = { 0, 0, 0, 0, 0 };
    int[] cookedTasteProfile = { 0, 0, 0, 0, 0 };
    float cookedTime = 0.0F;
    String infusion = null;
    boolean salted = true;
    boolean pickled = true;
    boolean brined = true;
    boolean dried = true;
    int driedAmt = 0;
    int foodCount = 0;
    int itemCount = 0;
    boolean hasknife = false; int i;
    for (i = 0; i < craftingInv.func_70302_i_(); i++) {

      if (craftingInv.func_70301_a(i) != null) {

        itemCount++;
        ItemStack is = craftingInv.func_70301_a(i);
        if (is.func_77973_b() instanceof ItemFoodTFC && is.func_77942_o() && is.func_77978_p().func_74764_b("foodWeight")) {

          if (foodCount == 0) {

            fuelTasteProfile = Food.getFuelProfile(is);
            cookedTasteProfile = Food.getCookedProfile(is);
            cookedTime = Food.getCooked(is);
            infusion = Food.getInfusion(is);
            driedAmt = Food.getDried(is);
          }
          if (sweetMod == -1) {
            sweetMod = Food.getSweetMod(is);
          } else if (sweetMod != Food.getSweetMod(is)) {
            sweetMod = 0;
          }
          if (sourMod == -1) {
            sourMod = Food.getSourMod(is);
          } else if (sourMod != Food.getSourMod(is)) {
            sourMod = 0;
          }
          if (saltyMod == -1) {
            saltyMod = Food.getSaltyMod(is);
          } else if (saltyMod != Food.getSaltyMod(is)) {
            saltyMod = 0;
          }
          if (bitterMod == -1) {
            bitterMod = Food.getBitterMod(is);
          } else if (bitterMod != Food.getBitterMod(is)) {
            bitterMod = 0;
          }
          if (umamiMod == -1) {
            umamiMod = Food.getSavoryMod(is);
          } else if (umamiMod != Food.getSavoryMod(is)) {
            umamiMod = 0;
          }
          float inputWeight = Food.getWeight(is);
          float oldInputWeight = inputWeight;
          float inputDecayPercent = Food.getDecay(is) / oldInputWeight;
          float inputDecay = Food.getDecay(is);
          float weightChange = 0.0F;

          salted = (salted && Food.isSalted(is));
          pickled = (pickled && Food.isPickled(is));
          brined = (brined && Food.isBrined(is));
          dried = (dried && Food.isDried(is));



          if (finalWeight < 160.0F &&
            Food.isSameSmoked(cookedTasteProfile, Food.getCookedProfile(is)) &&
            Food.isSameSmoked(fuelTasteProfile, Food.getFuelProfile(is)) && (
            (int)Food.getCooked(is) - 600) / 120 == ((int)cookedTime - 600) / 120) {

            weightChange = Math.min(160.0F - finalWeight, inputWeight);
            inputWeight -= weightChange;
            finalWeight += weightChange;
          }


          if (inputWeight != oldInputWeight) {

            if (inputWeight == 0.0F) {

              if (finalDecay < 0.0F) {

                if (inputDecay > finalDecay) {
                  finalDecay = inputDecay;
                }
              } else {
                finalDecay += inputDecay;
              }
            } else {

              float decayChange = weightChange * inputDecayPercent;
              inputDecay -= decayChange;
              if (finalDecay < 0.0F) {

                if (decayChange > finalDecay) {
                  finalDecay = decayChange;
                }
              } else {
                finalDecay += decayChange;
              }
            }  foodCount++;
          }
        }
      }
    }
    if (itemCount == 1) {

      if (finalDecay > 0.0F)
      {
        for (i = 0; i < player.field_71071_by.func_70302_i_(); i++) {

          if (player.field_71071_by.func_70301_a(i) != null)
          {
            if (player.field_71071_by.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {

              finalWeight -= finalDecay;
              finalDecay = 0.0F;


              break;
            }
          }
        }
      }
    } else {
      for (i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack inputStack = craftingInv.func_70301_a(i);
        if (inputStack != null)
        {


          if (inputStack.func_77973_b() == TFCItems.powder && inputStack.func_77960_j() == 9) {

            salted = true;
          }
          else if (inputStack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemKnife) {

            hasknife = true;
            if (finalDecay > 0.0F) {

              finalWeight -= finalDecay;
              finalDecay = 0.0F;
            }
            else if (finalDecay <= 0.0F) {

              if (!refiningGrain(craftResult, craftingInv) && finalWeight / 2.0F >= 1.0F)
              {
                if (!refiningMilk(craftResult, craftingInv, false)) finalWeight /= 2.0F;

              }
            }
          } else if (makingDough(craftResult, craftingInv) && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {

            float grainWeight = Food.getWeight(inputStack);
            float grainDecay = Food.getDecay(inputStack);
            if (grainDecay >= 0.0F)
              grainWeight -= grainDecay;
            float doughWeight = Math.min(grainWeight, 80.0F) * 2.0F;
            finalWeight = doughWeight;
            finalDecay = 0.0F;
          }
        }
      }
    }
    craftResult = ItemFoodTFC.createTag(craftResult, Helper.roundNumber(finalWeight, 100.0F), Helper.roundNumber(finalDecay, 100.0F));
    if (sweetMod != 0)
      Food.setSweetMod(craftResult, sweetMod);
    if (sourMod != 0)
      Food.setSourMod(craftResult, sourMod);
    if (saltyMod != 0)
      Food.setSaltyMod(craftResult, saltyMod);
    if (bitterMod != 0)
      Food.setBitterMod(craftResult, bitterMod);
    if (umamiMod != 0) {
      Food.setSavoryMod(craftResult, umamiMod);
    }
    if (cookedTime > 0.0F) {
      Food.setCooked(craftResult, cookedTime);
    }
    for (int fuelTaste : fuelTasteProfile) {

      if (fuelTaste > 0) {

        Food.setFuelProfile(craftResult, fuelTasteProfile);
        break;
      }
    }
    for (int cookedTaste : cookedTasteProfile) {

      if (cookedTaste > 0) {

        Food.setCookedProfile(craftResult, cookedTasteProfile);

        break;
      }
    }
    if (salted)
      Food.setSalted(craftResult, salted);
    if (pickled)
      Food.setPickled(craftResult, pickled);
    if (brined) {
      Food.setBrined(craftResult, brined);
    }



    if (dried)
    { Food.setDried(craftResult, 4); }
    else if (hasknife || itemCount == 1)
    { if (driedAmt > 0) Food.setDried(craftResult, driedAmt);  }
    else if (Food.isDried(craftResult)) { ((NBTTagCompound)craftResult.func_77978_p().func_74781_a("Processing Tag")).func_82580_o("Dried"); }







    if (infusion != null) {
      Food.setInfusion(craftResult, infusion);
    }
    if (craftResult.field_77994_a == 0) {
      craftResult.field_77994_a = 1;
    }
  }



  public static boolean refiningMilk(ItemStack craftResult, IInventory iinventory, boolean delete) {
    return ((craftResult.func_77973_b().func_77658_a().toLowerCase().contains("icecream") && gridHasItem(iinventory, "icecream", delete)) || (craftResult
      .func_77973_b().func_77658_a().toLowerCase().contains("yogurt") && gridHasItem(iinventory, "yogurt", delete)));
  }



  public static boolean refiningGrain(ItemStack craftResult, IInventory iinventory) {
    return ((craftResult.func_77973_b() == TFCItems.wheatGrain && gridHasItem(iinventory, TFCItems.wheatWhole)) || (craftResult
      .func_77973_b() == TFCItems.ryeGrain && gridHasItem(iinventory, TFCItems.ryeWhole)) || (craftResult
      .func_77973_b() == TFCItems.oatGrain && gridHasItem(iinventory, TFCItems.oatWhole)) || (craftResult
      .func_77973_b() == TFCItems.barleyGrain && gridHasItem(iinventory, TFCItems.barleyWhole)) || (craftResult
      .func_77973_b() == TFCItems.riceGrain && gridHasItem(iinventory, TFCItems.riceWhole)));
  }


  public static boolean makingDough(ItemStack craftResult, IInventory iinventory) {
    return ((craftResult.func_77973_b() == TFCItems.wheatDough || craftResult.func_77973_b() == TFCItems.ryeDough || craftResult.func_77973_b() == TFCItems.oatDough || craftResult
      .func_77973_b() == TFCItems.barleyDough || craftResult.func_77973_b() == TFCItems.cornmealDough || craftResult
      .func_77973_b() == TFCItems.riceDough) && (
      gridHasItem(iinventory, TFCItems.woodenBucketWater) || gridHasItem(iinventory, TFCItems.redSteelBucketWater)));
  }


  public static boolean isInvFull(EntityPlayer player) {
    for (int i = 0; i < player.field_71071_by.field_70462_a.length; i++) {

      if (player.field_71071_by.field_70462_a[i] == null)
        return false;
    }
    return true;
  }






  public static void preCraft(EntityPlayer player, ItemStack craftResult, IInventory craftingInv) {
    if (isButter(craftingInv))
      return;  preCrafted = true;
    if (refiningGrain(craftResult, craftingInv)) {

      List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
      handleItem(player, craftingInv, knives);
      for (int i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack inputStack = craftingInv.func_70301_a(i);
        if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {


          float foodWeight = Food.getWeight(inputStack);
          int strawCount = 0;

          for (int j = 0; j < foodWeight; j += 4) {
            strawCount++;
          }
          TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.straw, strawCount), player);
        }
      }
    } else if (makingDough(craftResult, craftingInv)) {

      for (int i = 0; i < craftingInv.func_70302_i_(); i++) {

        ItemStack inputStack = craftingInv.func_70301_a(i);
        if (inputStack != null && inputStack.func_77973_b() instanceof com.bioxx.tfc.api.Interfaces.IFood) {


          float grainWeight = Food.getWeight(inputStack);
          float grainDecay = Food.getDecay(inputStack);
          if (grainDecay >= 0.0F)
            grainWeight -= grainDecay;
          float doughWeight = Math.min(grainWeight, 80.0F);
          grainWeight -= doughWeight;

          inputStack = ItemFoodTFC.createTag(inputStack, Helper.roundNumber(grainWeight, 100.0F), 0.0F);

          if (grainWeight > 0.0F)
            inputStack.field_77994_a++;
        }
      }
    } else if (craftResult.func_77942_o() && craftResult.func_77978_p().func_74764_b("foodWeight")) {

      craftResult = processFoodInput(player, craftResult, craftingInv);
    }
  }


  public static boolean gridHasItem(IInventory iinventory, Item id) {
    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {
        if (iinventory.func_70301_a(i).func_77973_b() == id)
          return true;  }
    }
    return false;
  }


  public static boolean gridHasItem(IInventory iinventory, String id, boolean delete) {
    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {
        if (iinventory.func_70301_a(i).func_77973_b().func_77658_a().toLowerCase().contains(id)) {

          if (delete) iinventory.func_70299_a(i, null);
          return true;
        }  }
    }
    return false;
  }



  public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, Item[] items) {
    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {
        for (int j = 0; j < items.length; j++)
          damageItem(entityplayer, iinventory, i, items[j]);
      }
    }
  }

  public static void handleItem(EntityPlayer entityplayer, IInventory iinventory, List<ItemStack> items) {
    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {
        for (ItemStack is : items)
          damageItem(entityplayer, iinventory, i, is.func_77973_b());
      }
    }
  }

  public static void damageItem(EntityPlayer entityplayer, IInventory iinventory, int i, Item item) {
    if (iinventory.func_70301_a(i).func_77973_b() == item) {

      int index = i;
      ItemStack is = iinventory.func_70301_a(index).func_77946_l();
      if (is != null) {

        is.func_77972_a(1, (EntityLivingBase)entityplayer);
        if (is.func_77960_j() != 0 || entityplayer.field_71075_bZ.field_75098_d) {

          iinventory.func_70299_a(index, is);
          (iinventory.func_70301_a(index)).field_77994_a++;
          if ((iinventory.func_70301_a(index)).field_77994_a > 2)
            (iinventory.func_70301_a(index)).field_77994_a = 2;
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\FoodCraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */