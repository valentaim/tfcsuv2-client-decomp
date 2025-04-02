package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.api.Enums.EnumDamageType;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCustomSword
  extends ItemWeapon
{
  public ItemCustomSword(Item.ToolMaterial par2EnumToolMaterial, float damage, EnumDamageType dt) {
    super(par2EnumToolMaterial, damage);

    this.damageType = dt;
  }


  public ItemCustomSword(Item.ToolMaterial par2EnumToolMaterial, float damage) {
    super(par2EnumToolMaterial, damage);

    this.damageType = EnumDamageType.SLASHING;
  }



  public EnumItemReach getReach(ItemStack is) {
    return EnumItemReach.MEDIUM;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomSword.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */