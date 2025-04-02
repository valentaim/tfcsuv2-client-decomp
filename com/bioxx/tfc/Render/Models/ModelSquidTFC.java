package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;



@SideOnly(Side.CLIENT)
public class ModelSquidTFC
  extends ModelBase
{
  private ModelRenderer squidBody;
  private ModelRenderer[] squidTentacles = new ModelRenderer[8];


  public ModelSquidTFC() {
    byte b0 = -16;
    this.squidBody = new ModelRenderer(this, 0, 0);
    this.squidBody.func_78789_a(-6.0F, -8.0F, -6.0F, 12, 16, 12);
    this.squidBody.field_78797_d += (24 + b0);

    for (int i = 0; i < this.squidTentacles.length; i++) {

      this.squidTentacles[i] = new ModelRenderer(this, 48, 0);
      double d0 = i * Math.PI * 2.0D / this.squidTentacles.length;
      float f = (float)Math.cos(d0) * 5.0F;
      float f1 = (float)Math.sin(d0) * 5.0F;
      this.squidTentacles[i].func_78789_a(-1.0F, 0.0F, -1.0F, 2, 18, 2);
      (this.squidTentacles[i]).field_78800_c = f;
      (this.squidTentacles[i]).field_78798_e = f1;
      (this.squidTentacles[i]).field_78797_d = (31 + b0);
      d0 = i * Math.PI * -2.0D / this.squidTentacles.length + 1.5707963267948966D;
      (this.squidTentacles[i]).field_78796_g = (float)d0;
    }
  }








  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    ModelRenderer[] amodelrenderer = this.squidTentacles;
    int i = amodelrenderer.length;

    for (int j = 0; j < i; j++) {

      ModelRenderer modelrenderer = amodelrenderer[j];
      modelrenderer.field_78795_f = par3;
    }
  }






  public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
    this.squidBody.func_78785_a(par7);

    for (int i = 0; i < this.squidTentacles.length; i++)
    {
      this.squidTentacles[i].func_78785_a(par7);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelSquidTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */