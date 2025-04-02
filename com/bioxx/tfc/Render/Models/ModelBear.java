package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;









public class ModelBear
  extends ModelBase
{
  private ModelRenderer neck;
  private ModelRenderer bearHead;
  private ModelRenderer body;
  private ModelRenderer mainBody;
  private ModelRenderer leg1;
  private ModelRenderer leg2;
  private ModelRenderer leg3;
  private ModelRenderer leg4;
  private ModelRenderer tail;
  private ModelRenderer ear1;
  private ModelRenderer ear2;
  private ModelRenderer nose;

  public ModelBear() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;

    this.neck = new ModelRenderer(this, 0, 29);
    this.neck.func_78789_a(0.0F, 0.0F, 0.0F, 4, 5, 8);
    this.neck.func_78793_a(-3.0F, 12.0F, -10.0F);
    this.neck.func_78787_b(128, 64);
    this.neck.field_78809_i = true;
    setRotation(this.neck, 0.3839724F, 0.0F, 0.0F);
    this.bearHead = new ModelRenderer(this, 0, 0);
    this.bearHead.func_78789_a(-3.0F, -3.0F, -2.0F, 6, 6, 4);
    this.bearHead.func_78793_a(-1.0F, 13.5F, -10.0F);
    this.bearHead.func_78787_b(128, 64);
    this.bearHead.field_78809_i = true;
    setRotation(this.bearHead, 0.0F, 0.0F, 0.0F);
    this.body = new ModelRenderer(this, 24, 18);
    this.body.func_78789_a(-4.0F, -2.0F, -3.0F, 6, 7, 7);
    this.body.func_78793_a(0.0F, 14.0F, 4.0F);
    this.body.func_78787_b(128, 64);
    this.body.field_78809_i = true;
    setRotation(this.body, 1.308997F, 0.0F, 0.0F);
    this.mainBody = new ModelRenderer(this, 24, 0);
    this.mainBody.func_78789_a(-4.0F, -3.0F, -3.0F, 8, 10, 8);
    this.mainBody.func_78793_a(-1.0F, 14.0F, -3.0F);
    this.mainBody.func_78787_b(128, 64);
    this.mainBody.field_78809_i = true;
    setRotation(this.mainBody, 1.570796F, 0.0F, 0.0F);
    this.leg1 = new ModelRenderer(this, 0, 18);
    this.leg1.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 3);
    this.leg1.func_78793_a(-2.966667F, 16.0F, 6.0F);
    this.leg1.func_78787_b(128, 64);
    this.leg1.field_78809_i = true;
    setRotation(this.leg1, 0.0F, 0.0F, 0.0F);
    this.leg2 = new ModelRenderer(this, 0, 18);
    this.leg2.func_78789_a(-0.4666667F, 0.0F, -1.0F, 2, 8, 3);
    this.leg2.func_78793_a(0.5F, 16.0F, 6.0F);
    this.leg2.func_78787_b(128, 64);
    this.leg2.field_78809_i = true;
    setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
    this.leg3 = new ModelRenderer(this, 0, 18);
    this.leg3.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 3);
    this.leg3.func_78793_a(-3.5F, 16.0F, -4.0F);
    this.leg3.func_78787_b(128, 64);
    this.leg3.field_78809_i = true;
    setRotation(this.leg3, 0.0F, 0.0F, 0.0F);
    this.leg4 = new ModelRenderer(this, 0, 18);
    this.leg4.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 8, 3);
    this.leg4.func_78793_a(1.5F, 16.0F, -4.0F);
    this.leg4.func_78787_b(128, 64);
    this.leg4.field_78809_i = true;
    setRotation(this.leg4, 0.0F, 0.0F, 0.0F);
    this.tail = new ModelRenderer(this, 10, 18);
    this.tail.func_78789_a(-1.0F, 0.0F, -1.0F, 2, 2, 2);
    this.tail.func_78793_a(-1.0F, 13.0F, 9.0F);
    this.tail.func_78787_b(128, 64);
    this.tail.field_78809_i = true;
    setRotation(this.tail, 1.130069F, 0.0F, 0.0F);
    this.ear1 = new ModelRenderer(this, 16, 14);
    this.ear1.func_78789_a(-3.0F, -5.0F, 0.0F, 2, 2, 1);
    this.ear1.func_78793_a(-1.0F, 13.5F, -10.0F);
    this.ear1.func_78787_b(128, 64);
    this.ear1.field_78809_i = true;
    setRotation(this.ear1, 0.0F, 0.0F, 0.0F);
    this.ear2 = new ModelRenderer(this, 16, 14);
    this.ear2.func_78789_a(1.0F, -5.0F, 0.0F, 2, 2, 1);
    this.ear2.func_78793_a(-1.0F, 13.5F, -10.0F);
    this.ear2.func_78787_b(128, 64);
    this.ear2.field_78809_i = true;
    setRotation(this.ear2, 0.0F, 0.0F, 0.0F);
    this.nose = new ModelRenderer(this, 0, 10);
    this.nose.func_78789_a(-1.5F, 0.0F, -4.0F, 3, 3, 4);
    this.nose.func_78793_a(-1.0F, 13.5F, -10.0F);
    this.nose.func_78787_b(128, 64);
    this.nose.field_78809_i = true;
    setRotation(this.nose, 0.0F, 0.0F, 0.0F);
  }




  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);

    float age = 1.0F;












    if (this.field_78091_s) {

      float aa = 2.0F - 1.0F - age;
      GL11.glPushMatrix();
      float ab = (float)Math.sqrt((1.0F / aa));
      GL11.glScalef(ab, ab, ab);
      GL11.glTranslatef(0.0F, 24.0F * f5 * age / aa, 2.0F * f5 * age / ab);
      this.ear1.func_78785_a(f5);
      this.ear2.func_78785_a(f5);
      this.nose.func_78785_a(f5);
      this.bearHead.func_78785_a(f5);
      GL11.glPopMatrix();
      GL11.glPushMatrix();
      GL11.glScalef(1.0F / aa, 1.0F / aa, 1.0F / aa);
      GL11.glTranslatef(0.0F, 24.0F * f5 * age, 0.0F);
      this.body.func_78785_a(f5);
      this.mainBody.func_78785_a(f5);
      this.leg1.func_78785_a(f5);
      this.leg2.func_78785_a(f5);
      this.leg3.func_78785_a(f5);
      this.leg4.func_78785_a(f5);
      this.neck.func_78785_a(f5);
      this.tail.func_78785_a(f5);
      GL11.glPopMatrix();
    }
    else {

      this.ear1.func_78785_a(f5);
      this.ear2.func_78785_a(f5);
      this.nose.func_78785_a(f5);
      this.bearHead.func_78785_a(f5);
      this.body.func_78785_a(f5);
      this.mainBody.func_78785_a(f5);
      this.leg1.func_78785_a(f5);
      this.leg2.func_78785_a(f5);
      this.leg3.func_78785_a(f5);
      this.leg4.func_78785_a(f5);
      this.neck.func_78785_a(f5);
      this.tail.func_78785_a(f5);
    }
  }



  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }




  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.bearHead.field_78795_f = f4 / 57.295776F;
    this.bearHead.field_78796_g = f3 / 57.295776F;

    this.ear1.field_78795_f = f4 / 57.295776F;
    this.ear1.field_78796_g = f3 / 57.295776F;

    this.ear2.field_78795_f = f4 / 57.295776F;
    this.ear2.field_78796_g = f3 / 57.295776F;

    this.nose.field_78795_f = f4 / 57.295776F;
    this.nose.field_78796_g = f3 / 57.295776F;


    this.leg1.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
    this.leg2.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
    this.leg3.field_78795_f = MathHelper.func_76134_b(f * 0.6662F + 3.1415927F) * 1.4F * f1;
    this.leg4.field_78795_f = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */