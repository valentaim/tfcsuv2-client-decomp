package com.bioxx.tfc.Items.Pottery;

import com.bioxx.tfc.Core.Metal.Alloy;
import com.bioxx.tfc.Core.Metal.AlloyManager;
import com.bioxx.tfc.Core.Metal.AlloyMetal;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Food;
import com.bioxx.tfc.api.Interfaces.IBag;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.Util.Helper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;





public class ItemPotterySmallVessel
  extends ItemPotteryBase
  implements IBag
{
  @SideOnly(Side.CLIENT)
  private IIcon overlayIcon;

  public ItemPotterySmallVessel() {
    this.metaNames = new String[] { "Clay Vessel", "Ceramic Vessel", "Ceramic Vessel" };
    func_77625_d(1);
    setWeight(EnumWeight.MEDIUM);
    setSize(EnumSize.SMALL);
  }



  @SideOnly(Side.CLIENT)
  public boolean func_77623_v() {
    return true;
  }



  public IIcon getIcon(ItemStack stack, int pass) {
    if (pass == 1 && stack.func_77978_p() != null && stack.func_77978_p().func_74764_b("color")) {
      return this.overlayIcon;
    }
    return super.getIcon(stack, pass);
  }



  public void func_94581_a(IIconRegister registerer) {
    super.func_94581_a(registerer);
    this.overlayIcon = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + "Ceramic Vessel Overlay");
  }



  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(new ItemStack((Item)this, 1, 0));
    list.add(new ItemStack((Item)this, 1, 1));
  }



  public boolean canStack() {
    return false;
  }



  public void onDoneCooking(World world, ItemStack is, Alloy.EnumTier furnaceTier) {
    ItemStack[] bag = loadBagInventory(is);
    boolean canCookAlloy = true;
    for (int i = 0; bag != null && i < 4; i++) {

      if (bag[i] != null)
      {
        if (!(bag[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemOreSmall) && !(bag[i].func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre)) {
          canCookAlloy = false;
        }
      }
    }
    if (is.func_77960_j() == 2) {

      NBTTagCompound tag = is.field_77990_d;
      long totalH = TFC_Time.getTotalHours();
      tag.func_74772_a("TempTimer", totalH);
    }

    if (canCookAlloy && bag != null) {

      Metal[] types = new Metal[4];
      int[] metalAmounts = new int[4];

      if (bag[0] != null) {

        types[0] = ((ISmeltable)bag[0].func_77973_b()).getMetalType(bag[0]);
        metalAmounts[0] = ((ISmeltable)bag[0].func_77973_b()).getMetalReturnAmount(bag[0]) * (bag[0]).field_77994_a;
      }

      if (bag[1] != null) {

        types[1] = ((ISmeltable)bag[1].func_77973_b()).getMetalType(bag[1]);
        metalAmounts[1] = ((ISmeltable)bag[1].func_77973_b()).getMetalReturnAmount(bag[1]) * (bag[1]).field_77994_a;

        if (mergeMetals(types[0], types[1], metalAmounts[0], metalAmounts[1]) != metalAmounts[0]) {

          metalAmounts[0] = mergeMetals(types[0], types[1], metalAmounts[0], metalAmounts[1]);
          types[1] = null;
          metalAmounts[1] = 0;
        }
      }

      if (bag[2] != null) {

        types[2] = ((ISmeltable)bag[2].func_77973_b()).getMetalType(bag[2]);
        metalAmounts[2] = ((ISmeltable)bag[2].func_77973_b()).getMetalReturnAmount(bag[2]) * (bag[2]).field_77994_a;

        if (mergeMetals(types[0], types[2], metalAmounts[0], metalAmounts[2]) != metalAmounts[0]) {

          metalAmounts[0] = mergeMetals(types[0], types[2], metalAmounts[0], metalAmounts[2]);
          types[2] = null;
          metalAmounts[2] = 0;
        }
        if (mergeMetals(types[1], types[2], metalAmounts[1], metalAmounts[2]) != metalAmounts[1]) {

          metalAmounts[1] = mergeMetals(types[1], types[2], metalAmounts[1], metalAmounts[2]);
          types[2] = null;
          metalAmounts[2] = 0;
        }
      }
      if (bag[3] != null) {

        types[3] = ((ISmeltable)bag[3].func_77973_b()).getMetalType(bag[3]);
        metalAmounts[3] = ((ISmeltable)bag[3].func_77973_b()).getMetalReturnAmount(bag[3]) * (bag[3]).field_77994_a;

        if (mergeMetals(types[0], types[3], metalAmounts[0], metalAmounts[3]) != metalAmounts[0]) {

          metalAmounts[0] = mergeMetals(types[0], types[3], metalAmounts[0], metalAmounts[3]);
          types[3] = null;
          metalAmounts[3] = 0;
        }
        if (mergeMetals(types[1], types[3], metalAmounts[1], metalAmounts[3]) != metalAmounts[1]) {

          metalAmounts[1] = mergeMetals(types[1], types[3], metalAmounts[1], metalAmounts[3]);
          types[3] = null;
          metalAmounts[3] = 0;
        }
        if (mergeMetals(types[2], types[3], metalAmounts[2], metalAmounts[3]) != metalAmounts[2]) {

          metalAmounts[2] = mergeMetals(types[2], types[3], metalAmounts[2], metalAmounts[3]);
          types[3] = null;
          metalAmounts[3] = 0;
        }
      }

      int total = metalAmounts[0] + metalAmounts[1] + metalAmounts[2] + metalAmounts[3];










      if (total > 0) {

        float[] metalPercent = new float[4];
        metalPercent[0] = metalAmounts[0] / total * 100.0F;
        metalPercent[1] = metalAmounts[1] / total * 100.0F;
        metalPercent[2] = metalAmounts[2] / total * 100.0F;
        metalPercent[3] = metalAmounts[3] / total * 100.0F;

        List<AlloyMetal> a = new ArrayList<>();
        if (types[0] != null)
          a.add(new AlloyMetal(types[0], metalPercent[0]));
        if (types[1] != null)
          a.add(new AlloyMetal(types[1], metalPercent[1]));
        if (types[2] != null)
          a.add(new AlloyMetal(types[2], metalPercent[2]));
        if (types[3] != null) {
          a.add(new AlloyMetal(types[3], metalPercent[3]));
        }
        Metal match = AlloyManager.INSTANCE.matchesAlloy(a, furnaceTier);
        if (match != null) {

          Alloy output = new Alloy(match, total);
          NBTTagCompound tag = is.field_77990_d;
          tag.func_74778_a("MetalType", output.outputType.name);
          tag.func_74768_a("MetalAmount", (int)output.outputAmount);
          long totalH = TFC_Time.getTotalHours();
          tag.func_74772_a("TempTimer", totalH);
          is.func_77978_p().func_82580_o("Items");
          is.func_77964_b(2);
        }
      }
    }
  }


  private int mergeMetals(Metal mt0, Metal mt1, int m0, int m1) {
    if (mt0 != null && mt1 != null && m0 > 0)
    {
      if (mt0.name.equals(mt1.name))
        return m0 + m1;
    }
    return m0;
  }



  @SideOnly(Side.CLIENT)
  public int func_82790_a(ItemStack is, int pass) {
    if (pass != 1)
    {
      return 16777215;
    }


    int j = getColor(is);

    if (j < 0)
    {
      return 16777215;
    }

    if (is.func_77960_j() == 0) {






      int r = Math.min((j >> 16) + 96, 255);
      int b = Math.min((j >> 8 & 0xFF) + 96, 255);
      int g = Math.min((j & 0xFF) + 96, 255);
      return r << 16 | b << 8 | g;
    }

    return j;
  }



  public int getColor(ItemStack is) {
    if (!is.func_77942_o() || !is.func_77978_p().func_74764_b("color")) {
      return -1;
    }
    return is.func_77978_p().func_74762_e("color");
  }



  public ItemStack[] loadBagInventory(ItemStack is) {
    ItemStack[] bag = new ItemStack[4];
    if (is != null && is.func_77942_o() && is.func_77978_p().func_74764_b("Items")) {

      NBTTagList nbttaglist = is.func_77978_p().func_150295_c("Items", 10);
      for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

        NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
        byte byte0 = nbttagcompound1.func_74771_c("Slot");
        if (byte0 >= 0 && byte0 < 4) {
          bag[byte0] = ItemStack.func_77949_a(nbttagcompound1);
        }
      }
    } else {
      return null;
    }
    return bag;
  }



  public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
    ItemStack[] bag = loadBagInventory(is);
    if (bag != null) {

      TFC_Core.handleItemTicking(bag, world, x, y, z, 0.5F);
      for (ItemStack i : bag) {

        if (i != null && i.field_77994_a == 0)
          i = null;
      }
      saveContents(is, bag);
    }
    return true;
  }


  public void saveContents(ItemStack vessel, ItemStack[] bag) {
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < 4; i++) {

      if (bag[i] != null) {

        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.func_74774_a("Slot", (byte)i);
        bag[i].func_77955_b(nbttagcompound1);
        nbttaglist.func_74742_a((NBTBase)nbttagcompound1);
      }
    }
    if (vessel != null) {

      if (!vessel.func_77942_o())
        vessel.func_77982_d(new NBTTagCompound());
      vessel.func_77978_p().func_74782_a("Items", (NBTBase)nbttaglist);
    }
  }



  public ItemStack func_77659_a(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    if (!entityplayer.func_70093_af())
    {
      if (itemstack.func_77960_j() == 2) {

        NBTTagCompound nbt = itemstack.func_77978_p();
        if (nbt == null) {

          itemstack.func_77964_b(1);
          if (!world.field_72995_K)
          {

            String error = TFC_Core.translate("error.error") + " " + itemstack.func_77977_a() + " " + TFC_Core.translate("error.NBT") + " " + TFC_Core.translate("error.Contact");


            TFC_Core.sendInfoMessage(entityplayer, (IChatComponent)new ChatComponentText(error));
          }

        } else if (nbt.func_74764_b("TempTimer")) {

          long temp = nbt.func_74763_f("TempTimer");
          if (TFC_Time.getTotalHours() - temp < 11L) {
            entityplayer.openGui(TerraFirmaCraft.instance, 19, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
          }
        }
      } else if (itemstack.func_77960_j() == 1) {

        entityplayer.openGui(TerraFirmaCraft.instance, 39, entityplayer.field_70170_p, (int)entityplayer.field_70165_t, (int)entityplayer.field_70163_u, (int)entityplayer.field_70161_v);
      }
    }
    return itemstack;
  }



  public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    NBTTagCompound tag = is.field_77990_d;
    if (tag != null) {

      if (tag.func_74764_b("MetalType")) {

        String name = tag.func_74779_i("MetalType");
        name = name.replace(" ", "");
        name = TFC_Core.translate("gui.metal." + name);


        if (tag.func_74764_b("MetalAmount"))
        {

          name = name + " (" + tag.func_74762_e("MetalAmount") + " " + TFC_Core.translate("gui.units") + ")";
        }

        arraylist.add(EnumChatFormatting.DARK_GREEN + name);
      }

      if (tag.func_74764_b("TempTimer")) {

        long total = TFC_Time.getTotalHours();
        long temp = tag.func_74763_f("TempTimer");
        if (total - temp < 11L) {
          arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.ItemHeat.Liquid"));
        } else {
          arraylist.add(EnumChatFormatting.WHITE + TFC_Core.translate("gui.ItemHeat.Solidified"));
        }
      }
      if (tag.func_74764_b("Items")) {

        NBTTagList nbttaglist = tag.func_150295_c("Items", 10);
        for (int i = 0; i < nbttaglist.func_74745_c(); i++) {

          NBTTagCompound nbttagcompound1 = nbttaglist.func_150305_b(i);
          byte byte0 = nbttagcompound1.func_74771_c("Slot");
          if (byte0 >= 0 && byte0 < 4) {

            ItemStack itemstack = ItemStack.func_77949_a(nbttagcompound1);
            if (itemstack.field_77994_a > 0)
            {
              if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC) {

                float decay = Food.getDecay(itemstack);
                float weight = Helper.roundNumber(Food.getWeight(itemstack), 100.0F);

                String ds = " " + EnumChatFormatting.DARK_GRAY + Helper.roundNumber(decay / weight * 100.0F, 10.0F) + "%";
                if (decay <= 0.0F) {
                  ds = "";
                }
                arraylist.add(EnumChatFormatting.GOLD.toString() + itemstack.func_77973_b().func_77653_i(itemstack) + " " + EnumChatFormatting.WHITE + weight + "oz" + ds);
              } else {

                arraylist.add(EnumChatFormatting.GOLD.toString() + itemstack.field_77994_a + "x " + itemstack.func_77973_b().func_77653_i(itemstack));
              }
            }
          }
        }
      }
    }
  }


  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (TFC_Core.showShiftInformation()) {

      arraylist.add(TFC_Core.translate("gui.Help"));
      arraylist.add(TFC_Core.translate("gui.PotteryBase.Inst0"));

      NBTTagCompound tag = is.field_77990_d;
      if (tag != null && tag.func_74764_b("TempTimer")) {

        long total = TFC_Time.getTotalHours();
        long temp = tag.func_74763_f("TempTimer");
        if (total - temp < 11L) {
          arraylist.add(TFC_Core.translate("gui.PotteryVesselSmall.Inst0"));
        }
      } else {
        arraylist.add(TFC_Core.translate("gui.PotteryVesselSmall.Inst0"));
      }
    } else {

      arraylist.add(TFC_Core.translate("gui.ShowHelp"));
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotterySmallVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */