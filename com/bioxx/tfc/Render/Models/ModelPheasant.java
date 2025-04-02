package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;









public class ModelPheasant
  extends ModelBase
{
  private ModelRenderer body;
  private ModelRenderer tail;
  private ModelRenderer leftLeg;
  private ModelRenderer rightLeg;
  private ModelRenderer tailFeather;
  private ModelRenderer leftLowerLeg;
  private ModelRenderer rightLowerLeg;
  private ModelRenderer neck;
  private ModelRenderer head;
  private ModelRenderer beak;
  private ModelRenderer chest;
  private ModelRenderer leftWing;
  private ModelRenderer rightWing;
  private ModelRenderer leftFoot;
  private ModelRenderer rightFoot;

  public ModelPheasant() {
    this.field_78090_t = 64;
    this.field_78089_u = 32;

    this.body = new ModelRenderer(this, 0, 7);
    this.body.func_78789_a(-4.0F, 0.0F, -2.5F, 9, 6, 5);
    this.body.func_78793_a(0.0F, 12.0F, 0.0F);
    this.body.field_78809_i = true;
    setRotation(this.body, 0.0F, 0.0F, -0.5235988F);

    this.tail = new ModelRenderer(this, 0, 0);
    this.tail.func_78789_a(-7.0F, 2.0F, -2.0F, 6, 3, 4);
    this.tail.func_78793_a(0.0F, 12.0F, 0.0F);
    this.tail.field_78809_i = true;
    setRotation(this.tail, 0.0F, 0.0F, -0.1745329F);

    this.leftLeg = new ModelRenderer(this, 16, 24);
    this.leftLeg.func_78789_a(1.0F, 4.0F, 1.0F, 3, 4, 2);
    this.leftLeg.func_78793_a(0.0F, 12.0F, 0.0F);
    this.leftLeg.field_78809_i = true;
    setRotation(this.leftLeg, 0.0F, 0.0F, 0.3490659F);

    this.rightLeg = new ModelRenderer(this, 26, 24);
    this.rightLeg.func_78789_a(1.0F, 4.0F, -3.0F, 3, 4, 2);
    this.rightLeg.func_78793_a(0.0F, 12.0F, 0.0F);
    this.rightLeg.field_78809_i = true;
    setRotation(this.rightLeg, 0.0F, 0.0F, 0.3490659F);

    this.tailFeather = new ModelRenderer(this, 20, 0);
    this.tailFeather.func_78789_a(-14.96F, 3.0F, -1.0F, 9, 1, 2);
    this.tailFeather.func_78793_a(0.0F, 12.0F, 0.0F);
    this.tailFeather.field_78809_i = true;
    setRotation(this.tailFeather, 0.0F, 0.0F, -0.0872665F);

    this.leftLowerLeg = new ModelRenderer(this, 34, 19);
    this.leftLowerLeg.func_78789_a(-1.0F, 8.0F, 1.5F, 1, 4, 1);
    this.leftLowerLeg.func_78793_a(0.0F, 0.0F, 0.0F);
    this.leftLowerLeg.field_78809_i = true;
    setRotation(this.leftLowerLeg, 0.0F, 0.0F, 0.0F);

    this.rightLowerLeg = new ModelRenderer(this, 38, 19);
    this.rightLowerLeg.func_78789_a(-1.0F, 8.0F, -2.5F, 1, 4, 1);
    this.rightLowerLeg.func_78793_a(0.0F, 0.0F, 0.0F);
    this.rightLowerLeg.field_78809_i = true;
    setRotation(this.rightLowerLeg, 0.0F, 0.0F, 0.0F);

    this.neck = new ModelRenderer(this, 28, 13);
    this.neck.func_78790_a(4.0F, 2.0F, -1.5F, 4, 3, 3, 0.05F);
    this.neck.func_78793_a(-4.0F, 2.0F, 0.0F);
    this.neck.field_78809_i = true;
    setRotation(this.neck, 0.0F, 0.0F, -0.8726646F);

    this.head = new ModelRenderer(this, 16, 18);
    this.head.func_78790_a(2.5F, -3.0F, -1.5F, 3, 3, 3, 0.1F);
    this.head.func_78793_a(4.0F, 10.0F, 0.0F);
    this.head.field_78809_i = true;
    setRotation(this.head, 0.0F, 0.0F, 0.0F);

    this.beak = new ModelRenderer(this, 28, 19);
    this.beak.func_78789_a(5.0F, -2.0F, -0.5F, 2, 1, 1);
    this.beak.func_78793_a(4.0F, 10.0F, 0.0F);
    this.beak.field_78809_i = true;
    setRotation(this.beak, 0.0F, 0.0F, 0.0F);

    this.chest = new ModelRenderer(this, 28, 7);
    this.chest.func_78789_a(-1.0F, 4.5F, -1.5F, 4, 3, 3);
    this.chest.func_78793_a(-4.0F, 2.0F, 0.0F);
    this.chest.field_78809_i = true;
    setRotation(this.chest, 0.0F, 0.0F, -1.396263F);

    this.leftWing = new ModelRenderer(this, 0, 23);
    this.leftWing.func_78789_a(-3.0F, 1.0F, 2.5F, 7, 4, 1);
    this.leftWing.func_78793_a(0.0F, 12.0F, 0.0F);
    this.leftWing.field_78809_i = true;
    setRotation(this.leftWing, 0.0F, 0.0F, -0.5235988F);

    this.rightWing = new ModelRenderer(this, 0, 18);
    this.rightWing.func_78789_a(-3.0F, 1.0F, -3.5F, 7, 4, 1);
    this.rightWing.func_78793_a(0.0F, 12.0F, 0.0F);
    this.rightWing.field_78809_i = true;
    setRotation(this.rightWing, 0.0F, 0.0F, -0.5235988F);

    this.leftFoot = new ModelRenderer(this, 20, 3);
    this.leftFoot.func_78790_a(-2.0F, 11.9F, 0.5F, 4, 0, 3, 0.001F);
    this.leftFoot.func_78793_a(0.0F, 0.0F, 0.0F);
    this.leftFoot.field_78809_i = true;
    setRotation(this.leftFoot, 0.0F, 0.0F, 0.0F);

    this.rightFoot = new ModelRenderer(this, 20, 3);
    this.rightFoot.func_78790_a(-2.0F, 11.9F, -3.5F, 4, 0, 3, 0.001F);
    this.rightFoot.func_78793_a(0.0F, 0.0F, 0.0F);
    this.rightFoot.field_78809_i = true;
    setRotation(this.rightFoot, 0.0F, 0.0F, 0.0F);

    this.head.func_78792_a(this.neck);
    this.head.func_78792_a(this.chest);

    this.rightLeg.func_78792_a(this.rightLowerLeg);
    this.rightLowerLeg.func_78792_a(this.rightFoot);
    this.leftLeg.func_78792_a(this.leftLowerLeg);
    this.leftLowerLeg.func_78792_a(this.leftFoot);
  }



  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    setRotationAngles(par2, par3, par4, par5, par6, par7);
    float percent = Math.max(TFC_Core.getPercentGrown((IAnimal)entity), 0.0F);
    float ageScale = 2.0F - percent;


    GL11.glPushMatrix();

    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);

    this.head.func_78785_a(par7);
    this.beak.func_78785_a(par7);



    GL11.glPopMatrix();

    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
    this.body.func_78785_a(par7);
    this.rightLeg.func_78785_a(par7);
    this.leftLeg.func_78785_a(par7);


    this.rightWing.func_78785_a(par7);
    this.leftWing.func_78785_a(par7);


    this.tail.func_78785_a(par7);
    this.tailFeather.func_78785_a(par7);
    GL11.glPopMatrix();
  }


  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }


  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
    this.head.field_78796_g = par4 / 57.295776F;

    this.beak.field_78796_g = this.head.field_78796_g;


    this.neck.field_78808_h = -0.8726646F;
    this.chest.field_78808_h = -1.3962634F;
    this.body.field_78808_h = -0.5235988F;
    this.rightWing.field_78808_h = -0.5235988F;
    this.leftWing.field_78808_h = -0.5235988F;
    if (par3 != 0.0F) {
      this.rightWing.func_78793_a(4.0F, 12.0F, -2.0F);
      this.leftWing.func_78793_a(4.0F, 12.0F, 2.0F);
      this.rightWing.field_78808_h = -1.5707964F;
      this.leftWing.field_78808_h = -1.5707964F;
      this.rightWing.field_82906_o = -0.1875F;
      this.rightWing.field_82908_p = -0.1875F;
      this.rightWing.field_82907_q = -0.09375F;
      this.leftWing.field_82906_o = -0.1875F;
      this.leftWing.field_82908_p = -0.1875F;
      this.leftWing.field_82907_q = 0.09375F;
    } else {

      this.rightWing.func_78793_a(0.0F, 12.0F, 0.0F);
      this.leftWing.func_78793_a(0.0F, 12.0F, 0.0F);
      this.rightWing.field_82906_o = 0.0F;
      this.rightWing.field_82908_p = 0.0F;
      this.rightWing.field_82907_q = 0.0F;
      this.leftWing.field_82906_o = 0.0F;
      this.leftWing.field_82908_p = 0.0F;
      this.leftWing.field_82907_q = 0.0F;
    }
    this.rightWing.field_78796_g = par3;
    this.leftWing.field_78796_g = -par3;


    this.tail.field_78808_h = -0.17453292F;
    this.tailFeather.field_78808_h = -0.08726646F;
    this.rightLeg.field_78808_h = 0.34906584F + MathHelper.func_76134_b(par1 * 0.6662F) * 1.4F * par2;
    this.leftLeg.field_78808_h = 0.34906584F + MathHelper.func_76134_b(par1 * 0.6662F + 3.1415927F) * 1.4F * par2;
    this.rightLowerLeg.field_78808_h = -0.34906584F;
    this.leftLowerLeg.field_78808_h = -0.34906584F;
    this.rightFoot.field_78808_h = 0.0F;
    this.leftFoot.field_78808_h = 0.0F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelPheasant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */