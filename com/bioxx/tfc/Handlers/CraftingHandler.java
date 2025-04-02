package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.Player.PlayerInventory;
import com.bioxx.tfc.Core.Recipes;
import com.bioxx.tfc.Core.TFC_Achievements;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.PlayerUpdatePacket;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import fof.tfcsu.Register.tfcsuItems;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatBase;
import net.minecraftforge.oredict.OreDictionary;









public class CraftingHandler
{
  @SubscribeEvent
  public void onCrafting(PlayerEvent.ItemCraftedEvent e) {
    EntityPlayer player = e.player;
    ItemStack itemstack = e.crafting;
    Item item = itemstack.func_77973_b();
    int itemDamage = itemstack.func_77960_j();
    IInventory iinventory = e.craftMatrix;


    if (iinventory != null) {


      if (item == TFCItems.stoneBrick) {

        List<ItemStack> chisels = OreDictionary.getOres("itemChisel", false);
        handleItem(player, iinventory, chisels);
      }
      else if (item == TFCItems.singlePlank || item ==
        Item.func_150898_a(TFCBlocks.woodSupportH) || item == Item.func_150898_a(TFCBlocks.woodSupportH2) || item ==
        Item.func_150898_a(TFCBlocks.woodSupportV) || item == Item.func_150898_a(TFCBlocks.woodSupportV2)) {

        List<ItemStack> axes = OreDictionary.getOres("itemAxe", false);
        List<ItemStack> saws = OreDictionary.getOres("itemSaw", false);
        handleItem(player, iinventory, axes);
        handleItem(player, iinventory, saws);
      }
      else if (item == TFCItems.wool) {

        List<ItemStack> knives = OreDictionary.getOres("itemKnife", false);
        handleItem(player, iinventory, knives);
        int size = 0;
        for (int i = 0; i < iinventory.func_70302_i_(); i++) {

          if (iinventory.func_70301_a(i) != null) {

            if (iinventory.func_70301_a(i).func_77973_b() == TFCItems.sheepSkin)
              size = iinventory.func_70301_a(i).func_77960_j();
            if (iinventory.func_70301_a(i).func_77973_b() == tfcsuItems.pbearSkin)
              size = iinventory.func_70301_a(i).func_77960_j();
          }
        }
        TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.hide, 1, size), player);
      }
      else if (item == TFCItems.woolYarn) {

        handleItem(player, iinventory, Recipes.spindle);
      }
      else if (item == TFCItems.powder && itemDamage == 0) {

        List<ItemStack> hammers = OreDictionary.getOres("itemHammer", false);
        handleItem(player, iinventory, hammers);
      }


      triggerAchievements(player, itemstack, item, itemDamage);


      if (item == Item.func_150898_a(TFCBlocks.workbench)) {

        if (!player.getEntityData().func_74764_b("craftingTable")) {
          player.field_71071_by.func_146027_a(Item.func_150898_a(TFCBlocks.workbench), -1);
        }
        if (!player.field_70170_p.field_72995_K)
        {
          if (!player.getEntityData().func_74764_b("craftingTable")) {

            player.getEntityData().func_74757_a("craftingTable", true);

            try {
              PlayerUpdatePacket playerUpdatePacket = new PlayerUpdatePacket(player, 2);
              TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)playerUpdatePacket, (EntityPlayerMP)player);
            } catch (Exception e1) {

              TerraFirmaCraft.LOG.info("--------------------------------------------------");
              TerraFirmaCraft.LOG.catching(e1);
              TerraFirmaCraft.LOG.info("--------------------------------------------------");
            }
            PlayerInventory.upgradePlayerCrafting(player);
          }
        }
      }

      if (!player.field_70170_p.field_72995_K && item instanceof com.bioxx.tfc.Items.ItemIngot)
      {
        for (int i = 0; i < iinventory.func_70302_i_(); i++) {

          ItemStack is = iinventory.func_70301_a(i);
          if (is != null)
          {
            if (is.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {

              if (player.field_70170_p.field_73012_v.nextInt(20) == 0) {
                player.field_70170_p.func_72956_a((Entity)player, "terrafirmacraft:item.ceramicbreak", 0.7F, player.field_70170_p.field_73012_v.nextFloat() * 0.2F + 0.8F); break;
              }
              TFC_Core.giveItemToPlayer(new ItemStack(TFCItems.ceramicMold, 1, 1), player);
              break;
            }
          }
        }
      }
    }
  }


  public static void preCraft(EntityPlayer player, ItemStack itemstack, IInventory iinventory) {
    triggerAchievements(player, itemstack, itemstack.func_77973_b(), itemstack.func_77960_j());
  }


  public static void triggerAchievements(EntityPlayer player, ItemStack itemstack, Item item, int itemDamage) {
    if (item instanceof com.bioxx.tfc.Items.Tools.ItemCustomPickaxe) {

      player.func_71029_a((StatBase)TFC_Achievements.achPickaxe);
    }
    else if (item instanceof com.bioxx.tfc.Items.Tools.ItemCustomSaw) {

      player.func_71029_a((StatBase)TFC_Achievements.achSaw);
    }
    else if ((item instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil1 && itemDamage == 2) || (item instanceof com.bioxx.tfc.Items.ItemBlocks.ItemAnvil2 && (itemDamage == 1 || itemDamage == 2))) {

      player.func_71029_a((StatBase)TFC_Achievements.achBronzeAge);
    }
    else if (item == Item.func_150898_a(TFCBlocks.blastFurnace)) {
      player.func_71029_a((StatBase)TFC_Achievements.achBlastFurnace);
    } else if (item == TFCItems.clayBall && itemDamage == 1) {
      player.func_71029_a((StatBase)TFC_Achievements.achFireClay);
    } else if (item == TFCItems.unknownIngot) {
      player.func_71029_a((StatBase)TFC_Achievements.achUnknown);
    }
  }




  public static void transferNBT(boolean clearContents, EntityPlayer player, ItemStack itemstack, IInventory iinventory) {
    Item item = itemstack.func_77973_b();
    int itemDamage = itemstack.func_77960_j();
    if (item instanceof com.bioxx.tfc.Items.ItemIngot) {

      float temperature = 0.0F;
      for (int j = 0; j < iinventory.func_70302_i_(); j++) {

        if (iinventory.func_70301_a(j) != null)
        {
          if (iinventory.func_70301_a(j).func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal)
          {
            temperature = TFC_ItemHeat.getTemp(iinventory.func_70301_a(j)); }
        }
      }
      TFC_ItemHeat.setTemp(itemstack, temperature);
    }
    else if (item instanceof com.bioxx.tfc.Items.ItemMeltedMetal) {

      float temperature = 0.0F;
      for (int j = 0; j < iinventory.func_70302_i_(); j++) {

        if (iinventory.func_70301_a(j) != null)
        {
          if (iinventory.func_70301_a(j).func_77973_b() instanceof com.bioxx.tfc.Items.ItemIngot)
            temperature = TFC_ItemHeat.getTemp(iinventory.func_70301_a(j));  }
      }
      TFC_ItemHeat.setTemp(itemstack, temperature);
    }
    else if (item == TFCItems.potterySmallVessel && itemDamage == 0) {

      int color = -1;
      for (int j = 0; j < iinventory.func_70302_i_(); j++) {

        if (iinventory.func_70301_a(j) != null) {


          int[] ids = OreDictionary.getOreIDs(iinventory.func_70301_a(j));
          float[] c = null;
          for (int id : ids) {

            String name = OreDictionary.getOreName(id);
            for (int k = 0; k < Global.DYE_NAMES.length; k++) {

              if (name.equals(Global.DYE_NAMES[k])) {

                c = EntitySheep.field_70898_d[k];

                break;
              }
            }
          }
          if (c != null) {

            int r = (int)(c[0] * 255.0F);
            int g = (int)(c[1] * 255.0F);
            int b = (int)(c[2] * 255.0F);
            r <<= 16;
            g <<= 8;

            color += r += g += b;
          }
        }
      }
      if (color != -1) {

        NBTTagCompound nbt = null;
        if (itemstack.func_77942_o()) {
          nbt = itemstack.func_77978_p();
        } else {
          nbt = new NBTTagCompound();
        }
        nbt.func_74768_a("color", color);
        itemstack.func_77982_d(nbt);
      }
    }

    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {



        if ((iinventory.func_70301_a(i).func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemMiscToolHead || iinventory.func_70301_a(i).func_77973_b().getClass().getSimpleName().toLowerCase().contains("horsearmorpart")) && iinventory
          .func_70301_a(i).func_77942_o() && iinventory.func_70301_a(i).func_77978_p().func_74764_b("craftingTag"))
        {
          AnvilManager.setCraftTag(itemstack, AnvilManager.getCraftTag(iinventory.func_70301_a(i)));
        }
      }
    }
  }

  public static boolean gridHasItem(IInventory iinventory, Item item) {
    for (int i = 0; i < iinventory.func_70302_i_(); i++) {

      if (iinventory.func_70301_a(i) != null)
      {
        if (iinventory.func_70301_a(i).func_77973_b() == item)
          return true;  }
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

  public static void damageItem(EntityPlayer entityplayer, IInventory iinventory, int i, Item shiftedindex) {
    if (iinventory.func_70301_a(i).func_77973_b() == shiftedindex) {

      int index = i;
      ItemStack item = iinventory.func_70301_a(index).func_77946_l();
      if (item != null) {

        item.func_77972_a(1, (EntityLivingBase)entityplayer);
        if (item.func_77960_j() != 0 || entityplayer.field_71075_bZ.field_75098_d) {

          iinventory.func_70299_a(index, item);
          (iinventory.func_70301_a(index)).field_77994_a++;
          if ((iinventory.func_70301_a(index)).field_77994_a > 2)
            (iinventory.func_70301_a(index)).field_77994_a = 2;
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\CraftingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */