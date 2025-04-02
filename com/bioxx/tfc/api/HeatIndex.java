package com.bioxx.tfc.api;

import java.util.Random;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;





public class HeatIndex
{
  public float specificHeat;
  public float meltTemp;
  public boolean keepNBT;
  private ItemStack output;
  private int outputMin;
  private int outputMax;
  private ItemStack morph;
  public ItemStack input;

  public HeatIndex(ItemStack in, double sh, double melt, ItemStack out) {
    this.input = in;
    this.specificHeat = (float)sh;
    this.meltTemp = (float)melt;
    this.output = out;
  }


  public HeatIndex(ItemStack in, HeatRaw raw) {
    this.input = in;
    this.specificHeat = raw.specificHeat;
    this.meltTemp = raw.meltTemp;
  }


  public HeatIndex(ItemStack in, HeatRaw raw, ItemStack out) {
    this(in, raw);
    this.output = out;
  }


  public HeatIndex setKeepNBT(boolean k) {
    this.keepNBT = k;
    return this;
  }

  public boolean hasOutput() {
    return (this.output != null);
  }


  public Item getOutputItem() {
    if (this.output != null)
      return this.output.func_77973_b();
    return null;
  }


  public int getOutputDamage() {
    if (this.output != null)
      return this.output.func_77960_j();
    return 0;
  }


  public HeatIndex setMinMax(int min, int max) {
    this.outputMin = min;
    this.outputMax = max;
    return this;
  }


  public HeatIndex setMinMax(int amt) {
    this.outputMin = amt;
    this.outputMax = amt;
    return this;
  }


  public HeatIndex setMorph(ItemStack is) {
    this.morph = is;
    return this;
  }


  public ItemStack getMorph() {
    return this.morph;
  }


  public ItemStack getOutput(Random r) {
    if (getOutputItem() == null)
      return null;
    int rand = 0;
    if (this.outputMax - this.outputMin > 0) {

      rand = this.outputMin + r.nextInt(this.outputMax - this.outputMin);
      return new ItemStack(getOutputItem(), this.output.field_77994_a, 100 - rand);
    }


    return new ItemStack(getOutputItem(), this.output.field_77994_a, this.outputMin);
  }



  public ItemStack getOutput(ItemStack in, Random r) {
    ItemStack is = getOutput(r);
    if (is != null && this.keepNBT)
    {
      if (is.func_77942_o()) {

        NBTTagCompound nbt = is.func_77978_p();
        for (Object o : in.func_77978_p().func_150296_c())
        {
          NBTBase n = (NBTBase)o;
          if (nbt.func_74764_b(n.toString()))
            nbt.func_82580_o(n.toString());
          nbt.func_150296_c().add(o);
        }

      } else {

        is.func_77982_d(in.field_77990_d);
        if (TFC_ItemHeat.hasTemp(is))
          TFC_ItemHeat.setTemp(is, TFC_ItemHeat.getTemp(is) * 0.9F);
      }
    }
    return is;
  }


  public boolean matches(ItemStack is) {
    if (is != null) {

      boolean b = is.func_77973_b().func_77614_k();
      if (is.func_77973_b() != this.input.func_77973_b())
        return false;
      if (b && this.input.func_77960_j() != 32767 && is
        .func_77960_j() != this.input.func_77960_j())
        return false;
    } else {
      return false;
    }  return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\HeatIndex.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */