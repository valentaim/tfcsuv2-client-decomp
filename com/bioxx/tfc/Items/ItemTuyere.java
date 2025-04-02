package com.bioxx.tfc.Items;


public class ItemTuyere
  extends ItemTerra
{
  public int blockMeta;

  public ItemTuyere(int maxDam, int meta) {
    this.field_77787_bX = false;
    func_77656_e(maxDam);
    setFolder("tools/");
    this.blockMeta = meta;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemTuyere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */