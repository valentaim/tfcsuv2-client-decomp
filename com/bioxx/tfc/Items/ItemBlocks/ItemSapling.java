package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumWeight;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;



public class ItemSapling
  extends ItemTerraBlock
{
  public ItemSapling(Block b) {
    super(b);
    this.metaNames = new String[16];
    System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
    this.icons = new IIcon[this.metaNames.length];
  }



  public IIcon func_77617_a(int index) {
    return this.icons[index];
  }



  public void func_94581_a(IIconRegister registerer) {
    for (int i = 0; i < this.metaNames.length; i++) {
      this.icons[i] = registerer.func_94245_a("terrafirmacraft:wood/trees/" + this.metaNames[i] + " Sapling");
    }
  }


  public EnumWeight getWeight(ItemStack is) {
    return EnumWeight.MEDIUM;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemSapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */