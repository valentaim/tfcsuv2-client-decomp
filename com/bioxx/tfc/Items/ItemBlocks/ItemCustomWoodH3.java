package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;


public class ItemCustomWoodH3
  extends ItemTerraBlock
{
  public ItemCustomWoodH3(Block b) {
    super(b);
    int size = Global.WOOD_ALL.length - 16;
    this.metaNames = new String[size * 2];
    System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, 0, size);
    System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, size, size);
  }



  
  public String func_77667_c(ItemStack is) {
    try {
      int meta = is.func_77960_j();
      if (meta > 15) meta -= 16; 
      if (this.metaNames != null && meta < this.metaNames.length) {
        return func_77658_a().concat("." + this.metaNames[meta]);
      }
    } catch (Exception ex) {
      
      TerraFirmaCraft.LOG.error(ex.getLocalizedMessage());
    } 
    return "Unknown";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCustomWoodH3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */