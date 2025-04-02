package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelStand
  extends ModelBiped
{
  public ModelStand() {
    super(0.0F);
  }


  public ModelStand(float par1) {
    super(par1, 0.0F, 64, 32);
  }


  public ModelStand(float par1, float par2, int par3, int par4) {
    super(par1, par2, par3, par4);
  }



  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
    this.field_78114_d.field_78796_g = 0.0F;
    this.field_78114_d.field_78795_f = this.field_78116_c.field_78795_f;
    this.field_78112_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.001F;
    this.field_78113_g.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 2.0F * par2 * 0.5F + 0.001F;
    this.field_78112_f.field_78808_h = 0.0F;
    this.field_78113_g.field_78808_h = 0.0F;
    this.field_78123_h.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2 + 0.001F;
    this.field_78124_i.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
    this.field_78123_h.field_78796_g = 0.0F;
    this.field_78124_i.field_78796_g = 0.0F;

    if (this.field_78119_l != 0)
    {
      this.field_78113_g.field_78795_f = this.field_78113_g.field_78795_f * 0.5F - 0.31415927F * this.field_78119_l;
    }

    if (this.field_78120_m != 0)
    {
      this.field_78112_f.field_78795_f = this.field_78112_f.field_78795_f * 0.5F - 0.31415927F * this.field_78120_m;
    }

    this.field_78112_f.field_78796_g = 0.0F;
    this.field_78113_g.field_78796_g = 0.0F;



    if (this.field_78095_p > -9990.0F) {


      this.field_78115_e.field_78796_g = 0.0F;
      this.field_78112_f.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
      this.field_78112_f.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;
      this.field_78113_g.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 5.0F;
      this.field_78113_g.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 5.0F;

      this.field_78123_h.field_78798_e = MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 1.9F;
      this.field_78123_h.field_78800_c = -MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 1.9F;
      this.field_78124_i.field_78798_e = -MathHelper.func_76126_a(this.field_78115_e.field_78796_g) * 1.9F;
      this.field_78124_i.field_78800_c = MathHelper.func_76134_b(this.field_78115_e.field_78796_g) * 1.9F;

      this.field_78123_h.field_78796_g += this.field_78115_e.field_78796_g;
      this.field_78124_i.field_78796_g += this.field_78115_e.field_78796_g;

      this.field_78112_f.field_78796_g += this.field_78115_e.field_78796_g;
      this.field_78113_g.field_78796_g += this.field_78115_e.field_78796_g;
    }


    this.field_78115_e.field_78795_f = 0.0F;
    this.field_78123_h.field_78797_d = 12.0F;
    this.field_78124_i.field_78797_d = 12.0F;
    this.field_78116_c.field_78797_d = 0.0F;
    this.field_78114_d.field_78797_d = 0.0F;

    this.field_78116_c.field_78796_g = 0.0F;
    this.field_78114_d.field_78796_g = 0.0F;
    this.field_78124_i.field_78795_f += 0.01F;
    this.field_78123_h.field_78795_f += 0.01F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */