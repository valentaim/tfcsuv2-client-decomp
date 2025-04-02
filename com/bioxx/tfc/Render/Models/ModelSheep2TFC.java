package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;




public class ModelSheep2TFC
  extends ModelSheep2
{
  private ModelRenderer horn1;
  private ModelRenderer horn2;
  private ModelRenderer horn1b;
  private ModelRenderer horn2b;

  public ModelSheep2TFC() {
    this.horn1 = new ModelRenderer((ModelBase)this, 28, 2);
    this.horn1.func_78790_a(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
    this.horn1.func_78793_a(0.0F, -10.0F, 0.0F);
    this.horn1.field_78808_h = -0.5235988F;
    this.horn1.field_78795_f = -0.5235988F;
    this.horn1.field_78796_g = -1.0471976F;
    this.horn1.func_78793_a(-5.0F, -6.0F, -1.0F);

    this.horn1b = new ModelRenderer((ModelBase)this, 38, 4);
    this.horn1b.func_78790_a(0.5F, 1.0F, 0.5F, 1, 3, 1, 0.25F);
    this.horn1b.func_78793_a(0.0F, -2.0F, 4.0F);
    this.horn1b.field_78795_f = -1.0471976F;

    this.horn2 = new ModelRenderer((ModelBase)this, 28, 2);
    this.horn2.func_78790_a(0.0F, 0.0F, 0.0F, 2, 4, 2, 0.0F);
    this.horn2.func_78793_a(0.0F, -10.0F, 0.0F);
    this.horn2.field_78808_h = 0.5235988F;
    this.horn2.field_78795_f = -0.5235988F;
    this.horn2.field_78796_g = 1.0471976F;
    this.horn2.func_78793_a(4.0F, -6.5F, 0.75F);

    this.horn2b = new ModelRenderer((ModelBase)this, 38, 4);
    this.horn2b.func_78790_a(0.5F, 1.0F, 0.5F, 1, 3, 1, 0.25F);
    this.horn2b.func_78793_a(0.0F, -2.0F, 4.0F);
    this.horn2b.field_78795_f = -1.0471976F;

    this.horn1.func_78792_a(this.horn1b);
    this.horn2.func_78792_a(this.horn2b);

    this.field_78150_a.func_78792_a(this.horn1);
    this.field_78150_a.func_78792_a(this.horn2);
  }



  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    func_78087_a(par2, par3, par4, par5, par6, par7, entity);

    float percent = TFC_Core.getPercentGrown((IAnimal)entity);
    float ageScale = 2.0F - percent;
    float ageHeadScale = (float)Math.pow((1.0F / ageScale), 0.66D);






    GL11.glPushMatrix();

    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(ageHeadScale, ageHeadScale, ageHeadScale);
    GL11.glTranslatef(0.0F, (ageScale - 1.0F) * -0.125F, 0.1875F - 0.1875F * percent);


    if (percent < 0.5D) {

      this.horn1.field_78807_k = true;
      this.horn2.field_78807_k = true;
      if (percent < 0.75D) {

        this.horn1b.field_78807_k = true;
        this.horn2b.field_78807_k = true;
      }
    }

    if (((IAnimal)entity).getGender() == IAnimal.GenderEnum.FEMALE) {

      this.horn1.field_78807_k = true;
      this.horn2.field_78807_k = true;
    }
    this.field_78150_a.func_78785_a(par7);



    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);

    this.field_78148_b.func_78785_a(par7);
    this.field_78149_c.func_78785_a(par7);
    this.field_78146_d.func_78785_a(par7);
    this.field_78147_e.func_78785_a(par7);
    this.field_78144_f.func_78785_a(par7);
    this.horn1.field_78807_k = false;
    this.horn1b.field_78807_k = false;
    this.horn2.field_78807_k = false;
    this.horn2b.field_78807_k = false;
    GL11.glPopMatrix();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelSheep2TFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */