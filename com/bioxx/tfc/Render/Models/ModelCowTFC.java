package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelCowTFC
  extends ModelQuadruped
{
  public ModelRenderer udders;
  private ModelRenderer horn1;
  private ModelRenderer horn2;
  private ModelRenderer horn1b;
  private ModelRenderer horn2b;

  public ModelCowTFC() {
    super(12, 0.0F);
    this.field_78150_a = new ModelRenderer((ModelBase)this, 0, 0);
    this.field_78150_a.func_78790_a(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
    this.field_78150_a.func_78793_a(0.0F, 4.0F, -8.0F);

    this.horn1 = new ModelRenderer((ModelBase)this, 22, 0);
    this.horn1.func_78790_a(0.0F, 0.0F, 0.0F, 1, 3, 1, 0.15F);
    this.horn1.func_78793_a(-5.5F, -2.5F, -2.0F);
    this.horn1.field_78808_h = -1.5707964F;

    this.horn1b = new ModelRenderer((ModelBase)this, 22, 0);
    this.horn1b.func_78790_a(0.0F, -2.1F, -0.5F, 1, 3, 1, 0.0F);
    this.horn1b.func_78793_a(0.0F, 0.0F, 0.0F);
    this.horn1b.field_78795_f = 1.0471976F;
    this.horn1b.field_78796_g = -0.2617994F;
    this.horn1.func_78792_a(this.horn1b);

    this.field_78150_a.func_78792_a(this.horn1);
    this.horn2 = new ModelRenderer((ModelBase)this, 22, 0);
    this.horn2.func_78790_a(0.0F, -3.0F, 0.0F, 1, 3, 1, 0.15F);
    this.horn2.func_78793_a(5.5F, -2.5F, -2.0F);
    this.horn2.field_78808_h = -1.5707964F;

    this.horn2b = new ModelRenderer((ModelBase)this, 22, 0);
    this.horn2b.func_78790_a(0.0F, -0.8F, -0.5F, 1, 3, 1, 0.0F);
    this.horn2b.func_78793_a(0.0F, 0.0F, 0.0F);
    this.horn2b.field_78795_f = -1.0471976F;
    this.horn2b.field_78796_g = -0.2617994F;
    this.horn2.func_78792_a(this.horn2b);

    this.field_78150_a.func_78792_a(this.horn2);
    this.field_78148_b = new ModelRenderer((ModelBase)this, 18, 4);
    this.field_78148_b.func_78790_a(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
    this.field_78148_b.func_78793_a(0.0F, 5.0F, 2.0F);
    this.udders = new ModelRenderer((ModelBase)this, 18, 4);
    this.udders.func_78793_a(0.0F, 5.0F, 2.0F);
    this.udders.func_78784_a(52, 0).func_78789_a(-2.0F, 2.0F, -8.0F, 4, 6, 1);

    this.field_78149_c.field_78800_c--;
    this.field_78146_d.field_78800_c++;
    this.field_78149_c.field_78798_e += 0.0F;
    this.field_78146_d.field_78798_e += 0.0F;
    this.field_78147_e.field_78800_c--;
    this.field_78144_f.field_78800_c++;
    this.field_78147_e.field_78798_e--;
    this.field_78144_f.field_78798_e--;
    this.field_78151_h += 2.0F;
  }



  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    setRotationAngles(par2, par3, par4, par5, par6, par7);

    float percent = TFC_Core.getPercentGrown((IAnimal)entity);

    float ageScale = 2.0F - percent;
    float ageHeadScale = (float)Math.pow((1.0F / ageScale), 0.66D);

    GL11.glPushMatrix();

    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(ageHeadScale, ageHeadScale, ageHeadScale);
    GL11.glTranslatef(0.0F, 0.0F, 0.1875F - 0.1875F * percent);
    if (percent < 0.5D) {

      this.horn1.field_78807_k = true;
      this.horn2.field_78807_k = true;
      if (percent < 0.75D) {

        this.horn1b.field_78807_k = true;
        this.horn2b.field_78807_k = true;
      }
    }

    if (((IAnimal)entity).getGender() == IAnimal.GenderEnum.MALE) {

      this.udders.field_78807_k = true;
    }
    else {

      this.horn1b.field_78807_k = true;
      this.horn2b.field_78807_k = true;
    }
    this.field_78150_a.func_78785_a(par7);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);

    this.field_78148_b.func_78785_a(par7);
    this.udders.func_78785_a(par7);
    this.field_78149_c.func_78785_a(par7);
    this.field_78146_d.func_78785_a(par7);
    this.field_78147_e.func_78785_a(par7);
    this.field_78144_f.func_78785_a(par7);
    GL11.glPopMatrix();
  }





  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
    this.field_78150_a.field_78795_f = par5 / 57.295776F;
    this.field_78150_a.field_78796_g = par4 / 57.295776F;
    this.field_78148_b.field_78795_f = 1.5707964F;
    this.udders.field_78795_f = 1.5707964F;
    this.field_78149_c.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    this.field_78146_d.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
    this.field_78147_e.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
    this.field_78144_f.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    this.horn1.field_78795_f = 0.0F;
    this.horn2.field_78795_f = 0.0F;
    this.horn1.field_78807_k = false;
    this.horn1b.field_78807_k = false;
    this.horn2.field_78807_k = false;
    this.horn2b.field_78807_k = false;
    this.udders.field_78807_k = false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelCowTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */