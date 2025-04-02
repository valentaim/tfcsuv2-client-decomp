package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;

@SideOnly(Side.CLIENT)
public class ModelIngotPile
  extends ModelBase {
  public ModelRendererTFC[] renderer = new ModelRendererTFC[64];



  public ModelIngotPile() {
    for (int n = 0; n < 64; n++) {
      this.renderer[n] = new ModelRendererTFC(this, 0, 0);
      int m = (n + 8) / 8;
      float x = (n % 4) * 0.25F;
      float y = (m - 1) * 0.125F;
      float z = 0.0F;

      if (n % 8 >= 4) z = 0.5F;

      (this.renderer[n]).field_78804_l.add(new ModelIngot(this.renderer[n], (this.renderer[n]).field_78803_o, (this.renderer[n]).field_78813_p));
      (this.renderer[n]).field_82908_p = y;
      if (m % 2 == 1) {
        (this.renderer[n]).field_78796_g = 1.56F;
        (this.renderer[n]).field_82906_o = x;
        (this.renderer[n]).field_82907_q = z + 0.5F;
      } else {
        (this.renderer[n]).field_82906_o = z;
        (this.renderer[n]).field_82907_q = x;
      }
    }
  }


  public void renderIngots(int i) {
    for (int n = 0; n < i; n++)
    {
      this.renderer[n].func_78785_a(0.03125F);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */