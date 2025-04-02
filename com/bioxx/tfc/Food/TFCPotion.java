package com.bioxx.tfc.Food;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.potion.Potion;


public class TFCPotion
  extends Potion
{
  public static Potion bleed;
  public static Potion nausea;
  private String name = "";

  
  private int statusIconIndex = -1;

  
  public TFCPotion(int par1, boolean par2, int par3) {
    super(par1, par2, par3);
  }


  
  public TFCPotion setIconIndex(int par1, int par2) {
    this.statusIconIndex = par1 + par2 * 8;
    return this;
  }


  
  public TFCPotion setPotionName(String par1Str) {
    this.name = par1Str;
    return this;
  }






  
  @SideOnly(Side.CLIENT)
  public boolean func_76400_d() {
    return (this.statusIconIndex >= 0);
  }


  
  public String func_76393_a() {
    return this.name;
  }


  
  @SideOnly(Side.CLIENT)
  public int func_76392_e() {
    return this.statusIconIndex;
  }

  
  public static void setup() {
    bleed = (new TFCPotion(20, true, 16711680)).setPotionName("effect.bleed").setIconIndex(4, 0).func_76404_a(0.25D);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Food\TFCPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */