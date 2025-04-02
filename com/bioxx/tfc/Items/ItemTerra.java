package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import com.bioxx.tfc.api.Interfaces.ISize;
import com.bioxx.tfc.api.TFCItems;
import com.bioxx.tfc.api.TFC_ItemHeat;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;



public class ItemTerra
  extends Item
  implements ISize
{
  protected boolean stackable = true;
  protected EnumSize size = EnumSize.TINY;
  protected EnumWeight weight = EnumWeight.LIGHT;

  public String[] metaNames;

  public IIcon[] metaIcons;
  public String textureFolder;
  private int craftingXP = 1;



  public ItemTerra() {
    func_77637_a(TFCTabs.TFC_MISC);
    this.textureFolder = "";
    setNoRepair();
  }


  public ItemTerra setMetaNames(String[] metanames) {
    this.metaNames = (String[])metanames.clone();
    this.field_77787_bX = true;
    return this;
  }


  public ItemTerra setCraftingXP(int m) {
    this.craftingXP = m;
    return this;
  }


  public int getCraftingXP() {
    return this.craftingXP;
  }




  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    if (this.metaNames != null) {

      for (int i = 0; i < this.metaNames.length; i++)
      {
        list.add(new ItemStack(this, 1, i));
      }
    }
    else {

      list.add(new ItemStack(this, 1));
    }
  }



  public int getItemStackLimit(ItemStack is) {
    if (canStack()) {
      return ((getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier <= 64) ? ((getSize((ItemStack)null)).stackSize * (getWeight((ItemStack)null)).multiplier) : 64;
    }
    return 1;
  }


  public ItemTerra setFolder(String s) {
    this.textureFolder = s;
    return this;
  }



  public void func_94581_a(IIconRegister registerer) {
    if (this.metaNames == null) {

      if (this.field_111218_cA != null) {
        this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_111208_A());
      } else {
        this.field_77791_bV = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + func_77658_a().replace("item.", ""));
      }
    } else {

      this.metaIcons = new IIcon[this.metaNames.length];
      for (int i = 0; i < this.metaNames.length; i++)
      {
        this.metaIcons[i] = registerer.func_94245_a("terrafirmacraft:" + this.textureFolder + this.metaNames[i]);
      }


      this.field_77791_bV = this.metaIcons[0];
    }
  }



  public IIcon func_77617_a(int i) {
    if (this.metaNames != null && i < this.metaNames.length) {
      return this.metaIcons[i];
    }
    return this.field_77791_bV;
  }



  public String func_77667_c(ItemStack itemstack) {
    if (this.metaNames != null && itemstack.func_77960_j() < this.metaNames.length)
      return func_77658_a().concat("." + this.metaNames[itemstack.func_77960_j()]);
    return super.func_77667_c(itemstack);
  }



  public boolean func_77651_p() {
    return true;
  }






  public boolean onUpdate(ItemStack is, World world, int x, int y, int z) {
    return false;
  }


  public static void addSizeInformation(ItemStack object, List<String> arraylist) {
    if (((ISize)object.func_77973_b()).getSize(object) != null && ((ISize)object.func_77973_b()).getWeight(object) != null && ((ISize)object.func_77973_b()).getReach(object) != null)
      arraylist.add("⚖" + TFC_Core.translate("gui.Weight." + ((ISize)object.func_77973_b()).getWeight(object).getName()) + " ⇲" +
          TFC_Core.translate("gui.Size." + ((ISize)object.func_77973_b()).getSize(object).getName().replace(" ", "")));
    if (object.func_77973_b() instanceof IEquipable)
    {
      if (((IEquipable)object.func_77973_b()).getEquipType(object) == IEquipable.EquipType.BACK)
      {
        arraylist.add(EnumChatFormatting.LIGHT_PURPLE.toString() + TFC_Core.translate("gui.slot") + EnumChatFormatting.GRAY
            .toString() + ": " + EnumChatFormatting.WHITE
            .toString() + TFC_Core.translate("gui.slot.back"));
      }
    }
  }





  public void func_77624_a(ItemStack is, EntityPlayer player, List<String> arraylist, boolean flag) {
    addSizeInformation(is, arraylist);

    addHeatInformation(is, arraylist);

    if (is.func_77942_o()) {

      NBTTagCompound tag = is.func_77978_p();

      if (tag.func_74764_b("itemCraftingValue") || tag.func_74764_b("itemCraftingRule1")) {
        arraylist.add(TFC_Core.translate("gui.ItemWorked"));
      }
    }
    addItemInformation(is, player, arraylist);
    addExtraInformation(is, player, arraylist);
  }


  public void addItemInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    if (is.func_77973_b() instanceof ItemIngot || is
      .func_77973_b() instanceof ItemMetalSheet || is
      .func_77973_b() instanceof ItemUnfinishedArmor || is
      .func_77973_b() instanceof ItemBloom || is
      .func_77973_b() == TFCItems.wroughtIronKnifeHead)
    {
      if (TFC_ItemHeat.hasTemp(is)) {

        String s = "";
        if (HeatRegistry.getInstance().isTemperatureDanger(is).booleanValue())
        {
          s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.danger") + " | ";
        }

        if (HeatRegistry.getInstance().isTemperatureWeldable(is).booleanValue())
        {
          s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.weldable") + " | ";
        }

        if (HeatRegistry.getInstance().isTemperatureWorkable(is).booleanValue())
        {
          s = s + EnumChatFormatting.WHITE + TFC_Core.translate("gui.ingot.workable");
        }

        if (!"".equals(s)) {
          arraylist.add(s);
        }
      }
    }
  }

  public static void addHeatInformation(ItemStack is, List<String> arraylist) {
    if (is.func_77942_o())
    {
      if (TFC_ItemHeat.hasTemp(is)) {

        float temp = TFC_ItemHeat.getTemp(is);
        float meltTemp = -1.0F;
        HeatIndex hi = HeatRegistry.getInstance().findMatchingIndex(is);
        if (hi != null) {
          meltTemp = hi.meltTemp;
        }
        if (meltTemp != -1.0F)
        {
          if (is.func_77973_b() == TFCItems.stick) {
            arraylist.add(TFC_ItemHeat.getHeatColorTorch(temp, meltTemp));
          } else {
            arraylist.add(TFC_ItemHeat.getHeatColor(temp, meltTemp));
          }
        }
      }
    }
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {}



  public Multimap func_111205_h() {
    return (Multimap)HashMultimap.create();
  }



  public boolean canStack() {
    return this.stackable;
  }



  public EnumSize getSize(ItemStack is) {
    return this.size;
  }


  public EnumWeight getWeight(ItemStack is) {
    return this.weight;
  }


  public ItemTerra setSize(EnumSize e) {
    this.size = e;
    return this;
  }


  public ItemTerra setWeight(EnumWeight e) {
    this.weight = e;
    return this;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemTerra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */