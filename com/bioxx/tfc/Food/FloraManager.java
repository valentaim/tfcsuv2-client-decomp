package com.bioxx.tfc.Food;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.TFCItems;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;




public class FloraManager
{
  private static final FloraManager INSTANCE = new FloraManager();

  private List<FloraIndex> floraList;

  public static final FloraManager getInstance() {
    return INSTANCE;
  }


  public FloraManager() {
    this.floraList = new ArrayList<>();
  }


  public void addIndex(FloraIndex index) {
    this.floraList.add(index);
  }


  public FloraIndex findMatchingIndex(String input) {
    for (int k = 0; k < this.floraList.size(); k++) {

      FloraIndex tempIndex = this.floraList.get(k);
      if (tempIndex.type.equalsIgnoreCase(input))
      {
        return tempIndex;
      }
    }

    return null;
  }



  static {
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[0], 1, 2, 7, 8, new ItemStack(TFCItems.redApple, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[1], 1, 2, 6, 6, new ItemStack(TFCItems.banana, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[2], 11, 1, 8, 8, new ItemStack(TFCItems.orange, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[3], 2, 3, 7, 8, new ItemStack(TFCItems.greenApple, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[4], 2, 3, 5, 5, new ItemStack(TFCItems.lemon, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[5], 3, 3, 7, 7, new ItemStack(TFCItems.olive, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[6], 1, 1, 3, 3, new ItemStack(TFCItems.cherry, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[7], 1, 2, 6, 6, new ItemStack(TFCItems.peach, 1)));
    INSTANCE.addIndex(new FloraIndex(Global.FRUIT_META_NAMES[8], 2, 3, 4, 5, new ItemStack(TFCItems.plum, 1)));


    INSTANCE.addIndex((new FloraIndex("Wintergreen", 6, 7, new ItemStack(TFCItems.wintergreenBerry, 1)))
        .setHangTime(5).setTemp(-18.0F, 28.0F).setBioTemp(0.0F, 20.0F).setRain(500.0F, 4000.0F).setEVT(0.0F, 1.0F));
    INSTANCE.addIndex((new FloraIndex("Blueberry", 4, 6, new ItemStack(TFCItems.blueberry, 1)))
        .setHangTime(2).setTemp(0.0F, 32.0F).setBioTemp(5.0F, 25.0F).setRain(125.0F, 1000.0F));
    INSTANCE.addIndex((new FloraIndex("Raspberry", 4, 5, new ItemStack(TFCItems.raspberry, 1)))
        .setHangTime(2).setTemp(0.0F, 30.0F).setBioTemp(5.0F, 25.0F).setRain(250.0F, 2000.0F));
    INSTANCE.addIndex((new FloraIndex("Strawberry", 2, 3, new ItemStack(TFCItems.strawberry, 1)))
        .setHangTime(2).setTemp(0.0F, 27.0F).setBioTemp(5.0F, 25.0F).setRain(500.0F, 2000.0F));
    INSTANCE.addIndex((new FloraIndex("Blackberry", 3, 6, new ItemStack(TFCItems.blackberry, 1)))
        .setHangTime(2).setTemp(0.0F, 30.0F).setBioTemp(5.0F, 25.0F).setRain(125.0F, 4000.0F).setEVT(0.25F, 4.0F));
    INSTANCE.addIndex((new FloraIndex("Bunchberry", 4, 6, new ItemStack(TFCItems.bunchberry, 1)))
        .setHangTime(2).setTemp(0.0F, 18.0F).setBioTemp(0.0F, 20.0F).setRain(125.0F, 2000.0F));
    INSTANCE.addIndex((new FloraIndex("Cranberry", 6, 8, new ItemStack(TFCItems.cranberry, 1)))
        .setHangTime(3).setTemp(2.0F, 18.0F).setBioTemp(0.0F, 30.0F).setRain(1000.0F, 8000.0F));
    INSTANCE.addIndex((new FloraIndex("Snowberry", 5, 6, new ItemStack(TFCItems.snowberry, 1)))
        .setHangTime(3).setTemp(0.0F, 18.0F).setBioTemp(0.0F, 20.0F).setRain(250.0F, 4000.0F).setEVT(0.125F, 4.0F));
    INSTANCE.addIndex((new FloraIndex("Elderberry", 5, 6, new ItemStack(TFCItems.elderberry, 1)))
        .setHangTime(2).setTemp(0.0F, 28.0F).setBioTemp(5.0F, 25.0F).setRain(250.0F, 2000.0F));
    INSTANCE.addIndex((new FloraIndex("Gooseberry", 2, 4, new ItemStack(TFCItems.gooseberry, 1)))
        .setHangTime(2).setTemp(0.0F, 28.0F).setBioTemp(5.0F, 25.0F).setRain(250.0F, 2000.0F));
    INSTANCE.addIndex((new FloraIndex("Cloudberry", 4, 5, new ItemStack(TFCItems.cloudberry, 1)))
        .setHangTime(2).setTemp(0.0F, 18.0F).setBioTemp(0.0F, 25.0F).setRain(1000.0F, 8000.0F).setEVT(0.125F, 4.0F));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\FloraManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */