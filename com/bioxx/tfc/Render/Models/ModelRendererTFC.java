package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererTFC
  extends ModelRenderer {
  public ModelBase modelBase;
  public int field_78803_o;
  public int field_78813_p;

  public ModelRendererTFC(ModelBase par1) {
    super(par1);
    this.modelBase = par1;
  }


  public ModelRendererTFC(ModelBase par1ModelBase, int par2, int par3) {
    this(par1ModelBase);
    setTextureOffset(par2, par3);
  }



  public ModelRendererTFC setTextureOffset(int par1, int par2) {
    this.field_78803_o = par1;
    this.field_78813_p = par2;
    return this;
  }







  public void func_78790_a(float par1, float par2, float par3, int par4, int par5, int par6, float par7) {
    this.field_78804_l.add(new ModelBox(this, this.field_78803_o, this.field_78813_p, par1, par2, par3, par4, par5, par6, par7));
  }






  public void addBox(ModelBox box) {
    this.field_78804_l.add(box);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelRendererTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */