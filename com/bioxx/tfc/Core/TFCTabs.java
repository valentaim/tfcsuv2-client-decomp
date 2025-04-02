package com.bioxx.tfc.Core;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TFCTabs
  extends CreativeTabs {
  public static final CreativeTabs TFC_BUILDING = new TFCTabs("TFCBuilding");
  public static final CreativeTabs TFC_DECORATION = new TFCTabs("TFCDecoration");
  public static final CreativeTabs TFC_DEVICES = new TFCTabs("TFCDevices");
  public static final CreativeTabs TFC_POTTERY = new TFCTabs("TFCPottery");
  public static final CreativeTabs TFC_MISC = new TFCTabs("TFCMisc");
  public static final CreativeTabs TFC_FOODS = new TFCTabs("TFCFoods");
  public static final CreativeTabs TFC_TOOLS = new TFCTabs("TFCTools");
  public static final CreativeTabs TFC_WEAPONS = new TFCTabs("TFCWeapons");
  public static final CreativeTabs TFC_ARMOR = new TFCTabs("TFCArmor");
  public static final CreativeTabs TFC_MATERIALS = new TFCTabs("TFCMaterials");


  private ItemStack is;


  public TFCTabs(String par2Str) {
    super(par2Str);
  }


  public TFCTabs(String par2Str, int icon) {
    super(par2Str);
  }




  @SideOnly(Side.CLIENT)
  public Item func_78016_d() {
    return this.is.func_77973_b();
  }


  public void setTabIconItem(Item i) {
    this.is = new ItemStack(i);
  }



  public ItemStack func_151244_d() {
    return this.is;
  }


  public void setTabIconItemStack(ItemStack stack) {
    this.is = stack;
  }



  @SideOnly(Side.CLIENT)
  public String func_78024_c() {
    return TFC_Core.translate("itemGroup." + func_78013_b());
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\TFCTabs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */