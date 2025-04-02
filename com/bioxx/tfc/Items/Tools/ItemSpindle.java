package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Items.ItemTerra;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.item.ItemStack;



public class ItemSpindle
  extends ItemTerra
{
  public ItemSpindle() {
    func_77656_e(40);
    setFolder("tools/");
    setNoRepair();

    setSize(EnumSize.VERYSMALL);
  }



  public Multimap func_111205_h() {
    return (Multimap)HashMultimap.create();
  }


  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.SHORT;
  }



  public int func_77639_j() {
    return 1;
  }



  public boolean canStack() {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemSpindle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */