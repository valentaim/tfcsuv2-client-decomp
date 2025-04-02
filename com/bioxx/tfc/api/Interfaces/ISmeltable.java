package com.bioxx.tfc.api.Interfaces;

import com.bioxx.tfc.api.Metal;
import net.minecraft.item.ItemStack;





public interface ISmeltable
{
  Metal getMetalType(ItemStack paramItemStack);

  short getMetalReturnAmount(ItemStack paramItemStack);

  boolean isSmeltable(ItemStack paramItemStack);

  EnumTier getSmeltTier(ItemStack paramItemStack);

  public enum EnumTier
  {
    TierI(1), TierII(2), TierIII(3), TierIV(4), TierV(5), TierVI(6), TierVII(7), TierVIII(8), TierIX(9), TierX(10);

    public int tier;


    EnumTier(int t) {
      this.tier = t;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Interfaces\ISmeltable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */