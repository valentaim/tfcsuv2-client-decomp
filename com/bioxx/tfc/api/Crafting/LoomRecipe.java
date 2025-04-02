package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


public class LoomRecipe
{
  public ItemStack inItemStack;
  public ItemStack outItemStack;
  public int inSize;

  public LoomRecipe(ItemStack inputItem, ItemStack outIS) {
    this.inItemStack = inputItem;
    this.outItemStack = outIS;
    this.inSize = inputItem.field_77994_a;
  }


  public Boolean matches(ItemStack item) {
    boolean iStack = (this.inItemStack != null && item != null && item.field_77994_a == this.inItemStack.field_77994_a);

    boolean itemsEqual = OreDictionary.itemMatches(this.inItemStack, item, false);

    return Boolean.valueOf((iStack && itemsEqual));
  }


  public Boolean resultMatches(ItemStack item) {
    boolean iStack = (this.outItemStack != null && item != null && item.field_77994_a == this.outItemStack.field_77994_a);

    boolean itemsEqual = OreDictionary.itemMatches(this.outItemStack, item, false);

    return Boolean.valueOf((iStack && itemsEqual));
  }


  public Boolean partiallyMatches(ItemStack item) {
    boolean iStack = (this.inItemStack != null && item != null);

    boolean itemsEqual = OreDictionary.itemMatches(this.inItemStack, item, false);

    return Boolean.valueOf((iStack && itemsEqual));
  }


  public ItemStack getInItem() {
    return this.inItemStack;
  }

  public int getReqSize() {
    return this.inSize;
  }


  public String getRecipeName() {
    String s = "";
    if (this.outItemStack != null)
      s = this.outItemStack.func_82833_r();
    return s;
  }


  public ItemStack getResult(ItemStack inIS) {
    ItemStack is = null;
    if (this.outItemStack != null) {

      is = this.outItemStack.func_77946_l();
      return is;
    }
    return is;
  }


  public ItemStack getOutItemStack() {
    return this.outItemStack;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Crafting\LoomRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */