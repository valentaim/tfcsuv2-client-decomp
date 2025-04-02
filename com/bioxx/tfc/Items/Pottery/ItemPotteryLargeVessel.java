package com.bioxx.tfc.Items.Pottery;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;



public class ItemPotteryLargeVessel
  extends ItemPotteryBase
{
  public ItemPotteryLargeVessel() {
    this.metaNames = new String[] { "Clay Vessel [Large]", "Ceramic Vessel [Large]" };
    setWeight(EnumWeight.HEAVY);
    setSize(EnumSize.HUGE);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Pottery\ItemPotteryLargeVessel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */