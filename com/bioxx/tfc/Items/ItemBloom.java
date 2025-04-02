package com.bioxx.tfc.Items;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import com.bioxx.tfc.api.Interfaces.ISmeltable;
import com.bioxx.tfc.api.Metal;
import com.bioxx.tfc.api.TFCItems;
import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;



public class ItemBloom
  extends ItemTerra
  implements ISmeltable
{
  public ItemBloom() {
    func_77627_a(true);
    func_77637_a(TFCTabs.TFC_MATERIALS);
    setWeight(EnumWeight.HEAVY);
    setSize(EnumSize.LARGE);
  }




  public boolean canStack() {
    return false;
  }



  public void addExtraInformation(ItemStack is, EntityPlayer player, List<String> arraylist) {
    arraylist.add(TFC_Core.translate("gui.units") + ": " + is.func_77960_j());
  }



  public void func_150895_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
    list.add(new ItemStack(this, 1, 100));
    list.add(new ItemStack(this, 1, 200));
    list.add(new ItemStack(this, 1, 300));
    list.add(new ItemStack(this, 1, 400));
  }



  public Metal getMetalType(ItemStack is) {
    if (this == TFCItems.rawBloom) {
      return Global.UNKNOWN;
    }
    return Global.WROUGHTIRON;
  }



  public short getMetalReturnAmount(ItemStack is) {
    return (short)is.func_77960_j();
  }



  public boolean isSmeltable(ItemStack is) {
    return (this == TFCItems.bloom);
  }



  public ISmeltable.EnumTier getSmeltTier(ItemStack is) {
    return ISmeltable.EnumTier.TierIII;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBloom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */