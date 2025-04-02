package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;




public class ModelChickenTFC
  extends ModelBase
{
  public ModelRenderer head;
  public ModelRenderer body;
  public ModelRenderer rightLeg;
  public ModelRenderer leftLeg;
  public ModelRenderer rightWing;
  public ModelRenderer leftWing;
  public ModelRenderer bill;
  public ModelRenderer chin;
  public ModelRenderer[] tails;
  public ModelRenderer crown;

  public ModelChickenTFC() {
    byte var1 = 16;
    this.head = new ModelRenderer(this, 0, 0);
    this.head.func_78790_a(-2.0F, -6.0F, -2.0F, 4, 6, 3, 0.0F);
    this.head.func_78793_a(0.0F, (-1 + var1), -4.0F);
    this.bill = new ModelRenderer(this, 14, 0);
    this.bill.func_78790_a(-2.0F, -4.0F, -4.0F, 4, 2, 2, 0.0F);
    this.bill.func_78793_a(0.0F, (-1 + var1), -4.0F);
    this.chin = new ModelRenderer(this, 14, 4);
    this.chin.func_78790_a(-1.0F, -2.0F, -3.0F, 2, 2, 2, 0.0F);
    this.chin.func_78793_a(0.0F, (-1 + var1), -4.0F);
    this.body = new ModelRenderer(this, 0, 9);
    this.body.func_78790_a(-3.0F, -4.0F, -3.0F, 6, 8, 6, 0.0F);
    this.body.func_78793_a(0.0F, var1, 0.0F);
    this.rightLeg = new ModelRenderer(this, 26, 0);
    this.rightLeg.func_78789_a(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.rightLeg.func_78793_a(-2.0F, (3 + var1), 1.0F);
    this.leftLeg = new ModelRenderer(this, 26, 0);
    this.leftLeg.func_78789_a(-1.0F, 0.0F, -3.0F, 3, 5, 3);
    this.leftLeg.func_78793_a(1.0F, (3 + var1), 1.0F);
    this.rightWing = new ModelRenderer(this, 24, 13);
    this.rightWing.func_78789_a(0.0F, 0.0F, -3.0F, 1, 4, 6);
    this.rightWing.func_78793_a(-4.0F, (-3 + var1), 0.0F);
    this.leftWing = new ModelRenderer(this, 24, 13);
    this.leftWing.func_78789_a(-1.0F, 0.0F, -3.0F, 1, 4, 6);
    this.leftWing.func_78793_a(4.0F, (-3 + var1), 0.0F);

    this.crown = new ModelRenderer(this, 0, 23);
    this.crown.func_78789_a(0.0F, -7.0F, -3.0F, 0, 4, 5);
    this.crown.func_78793_a(0.0F, (-3 + var1), -1.0F);


    this.tails = new ModelRenderer[32];
    for (int i = 0; i < 32; i++) {
      this.tails[i] = new ModelRenderer(this, 45, 0);
      this.tails[i].func_78790_a(0.0F, 16.0F, 0.0F, 3, 21, 0, 0.0F);
      this.tails[i].func_78793_a(0.0F, 32.0F, 2.0F);
    }
  }






  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    setRotationAngles(par2, par3, par4, par5, par6, par7);
    float percent = Math.max(TFC_Core.getPercentGrown((IAnimal)entity), 0.0F);
    float ageScale = 2.0F - percent;


    GL11.glPushMatrix();

    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);

    if (percent >= 0.75D && ((IAnimal)entity).getGender() == IAnimal.GenderEnum.MALE) {
      this.crown.field_78807_k = false;
      this.body.field_78795_f = 0.7853982F;
      this.rightWing.field_78795_f = 0.7853982F;
      this.rightWing.func_78793_a(-4.0F, 13.5F, -2.5F);
      this.leftWing.field_78795_f = 0.7853982F;
      this.leftWing.func_78793_a(4.0F, 13.5F, -2.5F);
      this.head.func_78793_a(0.0F, 13.0F, -1.0F);
      this.bill.func_78793_a(0.0F, 13.0F, -1.0F);
      this.chin.func_78793_a(0.0F, 13.0F, -1.0F);
    }
    this.head.func_78785_a(par7);
    this.bill.func_78785_a(par7);
    this.chin.func_78785_a(par7);

    GL11.glPushMatrix();
    GL11.glScalef(0.75F, 0.75F, 0.75F);
    this.crown.func_78785_a(par7);
    GL11.glPopMatrix();
    GL11.glPopMatrix();

    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
    this.body.func_78785_a(par7);
    this.rightLeg.func_78785_a(par7);
    this.leftLeg.func_78785_a(par7);
    this.rightWing.func_78785_a(par7);
    this.leftWing.func_78785_a(par7);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(0.25F / percent, 0.5F / percent, 0.25F / percent);
    for (int i = 0; i < 32; i++) {
      this.tails[i].func_78785_a(par7);
    }
    GL11.glPopMatrix();
  }





  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
    this.head.field_78795_f = -(par5 / 57.295776F);
    this.head.field_78796_g = par4 / 57.295776F;
    this.crown.field_78795_f = -(par5 / 57.295776F);
    this.crown.field_78796_g = par4 / 57.295776F;
    this.bill.field_78795_f = this.head.field_78795_f;
    this.bill.field_78796_g = this.head.field_78796_g;
    this.chin.field_78795_f = this.head.field_78795_f;
    this.chin.field_78796_g = this.head.field_78796_g;
    this.body.field_78795_f = 1.5707964F;








    this.crown.field_78807_k = true;

    for (int i = 0; i < 32; i++) {
      (this.tails[i]).field_78795_f = 1.8849556F + (float)((i % 3) * Math.PI / 32.0D) * ((i % 2 != 0) ? true : -1);
      (this.tails[i]).field_78808_h = -1.5707964F + 3.1415927F * i / 31.0F;
    }
    this.rightLeg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    this.leftLeg.field_78795_f = MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
    this.rightWing.field_78808_h = par3;
    this.leftWing.field_78808_h = -par3;
    this.rightWing.field_78795_f = 0.0F;
    this.leftWing.field_78795_f = 0.0F;
    this.rightWing.func_78793_a(-4.0F, 13.0F, 0.0F);
    this.leftWing.func_78793_a(4.0F, 13.0F, 0.0F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelChickenTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */