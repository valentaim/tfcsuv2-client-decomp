package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityDeer;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;









public class ModelDeer
  extends ModelBase
{
  private ModelRenderer antler24;
  private ModelRenderer antler23;
  private ModelRenderer antler22;
  private ModelRenderer antler21;
  private ModelRenderer antler14;
  private ModelRenderer antler13;
  private ModelRenderer antler12;
  private ModelRenderer antler11;
  private ModelRenderer hoof2;
  private ModelRenderer toes3;
  private ModelRenderer thigh1;
  private ModelRenderer ear2;
  private ModelRenderer ear1;
  private ModelRenderer calf2;
  private ModelRenderer tail;
  private ModelRenderer collar;
  private ModelRenderer upperLeg4;
  private ModelRenderer neck;
  private ModelRenderer rump;
  private ModelRenderer head;
  private ModelRenderer body;
  private ModelRenderer leg1;
  private ModelRenderer leg2;
  private ModelRenderer leg3;
  private ModelRenderer leg4;
  private ModelRenderer snout;
  private ModelRenderer torso;
  private ModelRenderer upperLeg3;
  private ModelRenderer calf1;
  private ModelRenderer lowerleg3;
  private ModelRenderer lowerleg4;
  private ModelRenderer thigh2;
  private ModelRenderer toes4;
  private ModelRenderer toes2;
  private ModelRenderer toes1;
  private ModelRenderer hoof1;
  private ModelRenderer hoof3;
  private ModelRenderer hoof4;
  private boolean running;

  public ModelDeer() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;

    this.antler24 = new ModelRenderer(this, 44, 0);
    this.antler24.func_78789_a(-6.8F, -15.4F, -1.8F, 1, 2, 1);
    this.antler24.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler24.func_78787_b(128, 64);
    this.antler24.field_78809_i = true;
    setRotation(this.antler24, 0.0F, 0.0F, 0.2792527F);
    this.antler23 = new ModelRenderer(this, 44, 0);
    this.antler23.func_78789_a(-2.8F, -11.0F, 8.0F, 1, 2, 1);

    this.antler23.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler23.func_78787_b(128, 64);
    this.antler23.field_78809_i = true;
    setRotation(this.antler23, 0.8726646F, -0.4363323F, 0.0F);
    this.antler22 = new ModelRenderer(this, 44, 0);
    this.antler22.func_78789_a(2.3F, -14.3F, -5.1F, 1, 3, 1);
    this.antler22.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler22.func_78787_b(128, 64);
    this.antler22.field_78809_i = true;
    setRotation(this.antler22, -0.2268928F, 0.0F, -0.3490659F);
    this.antler21 = new ModelRenderer(this, 44, 0);
    this.antler21.func_78789_a(-2.0F, -13.0F, -2.5F, 1, 3, 1);
    this.antler21.func_78793_a(0.0F, -1.0F, -8.0F);
    this.antler21.func_78787_b(128, 64);
    this.antler21.field_78809_i = true;
    setRotation(this.antler21, 0.0F, 0.0F, 0.0F);



    this.antler14 = new ModelRenderer(this, 44, 0);
    this.antler14.func_78789_a(5.8F, -15.4F, -1.8F, 1, 2, 1);
    this.antler14.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler14.func_78787_b(128, 64);
    this.antler14.field_78809_i = true;
    setRotation(this.antler14, 0.0F, 0.0F, -0.2792527F);
    this.antler13 = new ModelRenderer(this, 44, 0);
    this.antler13.func_78789_a(1.8F, -11.0F, 8.0F, 1, 2, 1);
    this.antler13.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler13.func_78787_b(128, 64);
    this.antler13.field_78809_i = true;
    setRotation(this.antler13, 0.8726646F, 0.4363323F, 0.0F);
    this.antler12 = new ModelRenderer(this, 44, 0);
    this.antler12.func_78789_a(-3.3F, -14.3F, -5.1F, 1, 3, 1);
    this.antler12.func_78793_a(0.0F, 0.0F, 0.0F);
    this.antler12.func_78787_b(128, 64);
    this.antler12.field_78809_i = true;
    setRotation(this.antler12, -0.2268928F, 0.0F, 0.3490659F);
    this.antler11 = new ModelRenderer(this, 44, 0);
    this.antler11.func_78789_a(1.0F, -13.0F, -2.5F, 1, 3, 1);
    this.antler11.func_78793_a(0.0F, -1.0F, -8.0F);
    this.antler11.func_78787_b(128, 64);
    this.antler11.field_78809_i = true;
    setRotation(this.antler11, 0.0F, 0.0F, 0.0F);




    this.toes3 = new ModelRenderer(this, 18, 22);
    this.toes3.func_78789_a(-0.5F, 0.4F, -3.0F, 2, 1, 4);
    this.toes3.func_78793_a(0.0F, 5.0F, 0.0F);
    this.toes3.func_78787_b(128, 64);
    setRotation(this.toes3, 1.134464F, 0.0F, 0.0F);
    this.thigh1 = new ModelRenderer(this, 40, 22);
    this.thigh1.func_78789_a(-1.0F, -2.3F, -2.0F, 2, 9, 5);
    this.thigh1.func_78793_a(-3.0F, 2.0F, 7.0F);
    this.thigh1.func_78787_b(128, 64);
    setRotation(this.thigh1, -0.1745329F, 0.0F, 0.1745329F);

    this.ear2 = new ModelRenderer(this, 54, 16);
    this.ear2.field_78809_i = true;
    this.ear2.func_78789_a(-9.0F, -10.0F, -2.0F, 5, 3, 0);
    this.ear2.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ear2.func_78787_b(128, 64);
    setRotation(this.ear2, 0.0F, 0.3490659F, 0.34906584F);
    this.ear1 = new ModelRenderer(this, 54, 16);
    this.ear1.func_78789_a(4.0F, -10.0F, -2.0F, 5, 3, 0);
    this.ear1.func_78793_a(0.0F, 0.0F, 0.0F);
    this.ear1.func_78787_b(128, 64);
    setRotation(this.ear1, 0.0F, -0.3490659F, -0.34906584F);

    this.calf2 = new ModelRenderer(this, 54, 7);
    this.calf2.field_78809_i = true;
    this.calf2.func_78789_a(-1.0F, -1.0F, 0.0F, 2, 6, 3);
    this.calf2.func_78793_a(0.0F, 6.0F, 0.0F);
    this.calf2.func_78787_b(128, 64);
    setRotation(this.calf2, 0.5585054F, 0.0F, 0.0F);
    this.tail = new ModelRenderer(this, 24, 52);
    this.tail.func_78789_a(-1.5F, -0.5F, 0.0F, 3, 2, 9);
    this.tail.func_78793_a(0.0F, -1.5F, 10.0F);
    this.tail.func_78787_b(128, 64);
    this.tail.field_78809_i = true;
    setRotation(this.tail, -1.308997F, 0.0F, 0.0F);
    this.collar = new ModelRenderer(this, 30, 38);
    this.collar.func_78789_a(0.0F, -2.0F, -4.0F, 4, 6, 7);
    this.collar.func_78793_a(-2.0F, -1.0F, -8.0F);
    this.collar.func_78787_b(128, 64);
    this.collar.field_78809_i = true;
    setRotation(this.collar, 1.151917F, 0.0F, 0.0F);

    this.upperLeg4 = new ModelRenderer(this, 30, 22);
    this.upperLeg4.field_78809_i = true;
    this.upperLeg4.func_78789_a(-1.25F, -1.0F, -1.5F, 2, 5, 3);
    this.upperLeg4.func_78793_a(4.0F, 5.0F, -7.0F);
    this.upperLeg4.func_78787_b(128, 64);

    setRotation(this.upperLeg4, 0.3490659F, 0.0F, 0.0349066F);
    this.neck = new ModelRenderer(this, 57, 22);
    this.neck.func_78789_a(-2.0F, -4.0F, -2.0F, 4, 5, 8);
    this.neck.func_78793_a(0.0F, -1.0F, -8.0F);
    this.neck.func_78787_b(128, 64);
    this.neck.field_78809_i = true;
    setRotation(this.neck, 1.815142F, 0.0F, 0.0F);
    this.rump = new ModelRenderer(this, 0, 47);
    this.rump.func_78789_a(-3.0F, -4.0F, 3.0F, 6, 10, 6);
    this.rump.func_78793_a(0.0F, 1.5F, 1.0F);
    this.rump.func_78787_b(128, 64);
    this.rump.field_78809_i = true;
    setRotation(this.rump, -0.0872665F, 0.0F, 0.0F);
    this.head = new ModelRenderer(this, 54, 35);
    this.head.func_78789_a(-2.5F, -11.0F, -5.0F, 5, 6, 6);
    this.head.func_78793_a(0.0F, -1.0F, -8.0F);
    this.head.func_78787_b(128, 64);
    this.head.field_78809_i = true;
    setRotation(this.head, 0.1570796F, 0.0F, 0.0F);
    this.head.func_78792_a(this.ear1);
    this.head.func_78792_a(this.ear2);
    this.body = new ModelRenderer(this, 18, 4);
    this.body.func_78789_a(-4.0F, -11.0F, -8.0F, 8, 8, 10);
    this.body.func_78793_a(0.0F, 1.0F, 2.0F);
    this.body.func_78787_b(128, 64);
    this.body.field_78809_i = true;
    setRotation(this.body, 1.43117F, 0.0F, 0.0F);
    this.leg1 = new ModelRenderer(this, 0, 16);
    this.leg1.func_78789_a(-0.0F, -1.0F, 0.0F, 2, 9, 2);
    this.leg1.func_78793_a(0.0F, 5.0F, 0.0F);
    this.leg1.func_78787_b(128, 64);
    setRotation(this.leg1, 0.0F, 0.0F, 0.0F);

    this.leg2 = new ModelRenderer(this, 0, 16);
    this.leg2.field_78809_i = true;
    this.leg2.func_78789_a(-1.0F, -1.0F, 0.0F, 2, 9, 2);
    this.leg2.func_78793_a(0.0F, 5.0F, 0.0F);
    this.leg2.func_78787_b(128, 64);

    setRotation(this.leg2, 0.0F, 0.0F, 0.0F);
    this.leg3 = new ModelRenderer(this, 8, 16);
    this.leg3.func_78789_a(-0.5F, 0.0F, 0.0F, 2, 7, 2);
    this.leg3.func_78793_a(0.0F, 3.0F, -1.0F);
    this.leg3.func_78787_b(128, 64);
    setRotation(this.leg3, 0.0F, 0.0F, 0.0F);

    this.leg4 = new ModelRenderer(this, 8, 16);
    this.leg4.field_78809_i = true;
    this.leg4.func_78789_a(-1.5F, 0.0F, 0.0F, 2, 7, 2);
    this.leg4.func_78793_a(0.0F, 3.0F, -1.0F);
    this.leg4.func_78787_b(128, 64);

    setRotation(this.leg4, -0.3490659F, 0.0F, -0.0349066F);
    this.snout = new ModelRenderer(this, 54, 0);
    this.snout.func_78789_a(-1.5F, -9.3F, -9.0F, 3, 3, 4);
    this.snout.func_78793_a(0.0F, 0.0F, 0.0F);
    this.snout.func_78787_b(128, 64);
    this.snout.field_78809_i = true;

    this.head.func_78792_a(this.snout);
    this.torso = new ModelRenderer(this, 0, 29);
    this.torso.func_78789_a(-3.5F, -3.0F, -5.0F, 7, 10, 8);
    this.torso.func_78793_a(0.0F, 1.0F, 2.0F);
    this.torso.func_78787_b(128, 64);
    this.torso.field_78809_i = true;
    setRotation(this.torso, 0.122173F, 0.0F, 0.0F);
    this.upperLeg3 = new ModelRenderer(this, 30, 22);
    this.upperLeg3.func_78789_a(-0.7F, -1.0F, -1.5F, 2, 5, 3);
    this.upperLeg3.func_78793_a(-4.0F, 5.0F, -7.0F);
    this.upperLeg3.func_78787_b(128, 64);
    setRotation(this.upperLeg3, 0.3490659F, 0.0F, -0.0349066F);
    this.calf1 = new ModelRenderer(this, 54, 7);
    this.calf1.func_78789_a(0.0F, -1.0F, 0.0F, 2, 6, 3);
    this.calf1.func_78793_a(0.0F, 7.0F, 0.0F);
    this.calf1.func_78787_b(128, 64);
    setRotation(this.calf1, 0.5585054F, 0.0F, 0.0F);
    this.lowerleg3 = new ModelRenderer(this, 30, 30);
    this.lowerleg3.func_78789_a(-0.5F, 0.0F, 0.0F, 2, 6, 2);
    this.lowerleg3.func_78793_a(0.0F, 7.0F, 0.0F);
    this.lowerleg3.func_78787_b(128, 64);
    setRotation(this.lowerleg3, 0.0F, 0.0F, 0.0F);

    this.lowerleg4 = new ModelRenderer(this, 30, 30);
    this.lowerleg4.field_78809_i = true;
    this.lowerleg4.func_78789_a(-1.5F, 0.0F, 0.0F, 2, 6, 2);
    this.lowerleg4.func_78793_a(0.0F, 7.0F, 0.0F);
    this.lowerleg4.func_78787_b(128, 64);

    setRotation(this.lowerleg4, 0.0F, 0.0F, 0.0F);

    this.thigh2 = new ModelRenderer(this, 40, 22);
    this.thigh2.field_78809_i = true;
    this.thigh2.func_78789_a(-1.0F, -2.3F, -2.0F, 2, 9, 5);
    this.thigh2.func_78793_a(3.0F, 2.0F, 7.0F);
    this.thigh2.func_78787_b(128, 64);

    setRotation(this.thigh2, -0.1745329F, 0.0F, -0.1745329F);

    this.toes4 = new ModelRenderer(this, 18, 22);
    this.toes4.field_78809_i = true;
    this.toes4.func_78789_a(-1.5F, 0.4F, -3.0F, 2, 1, 4);
    this.toes4.func_78793_a(0.0F, 5.0F, 0.0F);
    this.toes4.func_78787_b(128, 64);

    setRotation(this.toes4, 1.134464F, 0.0F, 0.0F);

    this.toes2 = new ModelRenderer(this, 18, 22);
    this.toes2.field_78809_i = true;
    this.toes2.func_78789_a(-1.0F, 0.4F, -3.0F, 2, 1, 4);
    this.toes2.func_78793_a(0.0F, 8.0F, 0.0F);
    this.toes2.func_78787_b(128, 64);

    setRotation(this.toes2, 1.134464F, 0.0F, 0.0F);
    this.toes1 = new ModelRenderer(this, 18, 22);
    this.toes1.func_78789_a(-0.0F, 0.4F, -3.0F, 2, 1, 4);
    this.toes1.func_78793_a(0.0F, 8.0F, 0.0F);
    this.toes1.func_78787_b(128, 64);
    setRotation(this.toes1, 1.134464F, 0.0F, 0.0F);
    this.hoof1 = new ModelRenderer(this, 30, 0);
    this.hoof1.func_78789_a(-0.0F, 0.0F, -6.3F, 2, 1, 2);
    this.hoof1.func_78793_a(0.0F, 5.0F, 0.5F);
    this.hoof1.func_78787_b(128, 64);
    setRotation(this.hoof1, 0.0F, 0.0F, 0.0F);
    this.hoof2 = new ModelRenderer(this, 30, 0);
    this.hoof2.field_78809_i = true;
    this.hoof2.func_78789_a(-0.5F, 0.0F, -6.3F, 2, 1, 2);
    this.hoof2.func_78793_a(0.0F, 5.0F, -0.5F);
    this.hoof2.func_78787_b(128, 64);

    setRotation(this.hoof2, 0.0F, 0.0F, 0.0F);
    this.hoof3 = new ModelRenderer(this, 30, 0);
    this.hoof3.func_78789_a(-0.5F, 0.0F, -6.3F, 2, 1, 2);
    this.hoof3.func_78793_a(0.0F, 5.0F, -0.5F);
    this.hoof3.func_78787_b(128, 64);
    setRotation(this.hoof3, 0.0F, 0.0F, 0.0F);

    this.hoof4 = new ModelRenderer(this, 30, 0);
    this.hoof4.field_78809_i = true;
    this.hoof4.func_78789_a(-1.5F, 0.0F, -6.3F, 2, 1, 2);
    this.hoof4.func_78793_a(0.0F, 5.0F, -0.5F);
    this.hoof4.func_78787_b(128, 64);

    setRotation(this.hoof4, 0.0F, 0.0F, 0.0F);

    this.upperLeg4.func_78792_a(this.leg4);
    this.leg4.func_78792_a(this.lowerleg4);
    this.lowerleg4.func_78792_a(this.toes4);
    this.toes4.func_78792_a(this.hoof4);

    this.upperLeg3.func_78792_a(this.leg3);
    this.leg3.func_78792_a(this.lowerleg3);
    this.lowerleg3.func_78792_a(this.toes3);
    this.toes3.func_78792_a(this.hoof3);

    this.thigh1.func_78792_a(this.calf1);
    this.calf1.func_78792_a(this.leg1);
    this.leg1.func_78792_a(this.toes1);
    this.toes1.func_78792_a(this.hoof1);

    this.thigh2.func_78792_a(this.calf2);
    this.calf2.func_78792_a(this.leg2);
    this.leg2.func_78792_a(this.toes2);
    this.toes2.func_78792_a(this.hoof2);

    this.antler11.func_78792_a(this.antler12);
    this.antler21.func_78792_a(this.antler22);
    this.antler11.func_78792_a(this.antler13);
    this.antler21.func_78792_a(this.antler23);
    this.antler11.func_78792_a(this.antler14);
    this.antler21.func_78792_a(this.antler24);
  }



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.running = false;
    this.running = ((EntityDeer)entity).getRunning();


    float age = 1.0F - TFC_Core.getPercentGrown((IAnimal)entity);








    float aa = 2.0F - 1.0F - age;
    GL11.glTranslatef(0.0F, -6.0F * f5 * age / (float)Math.pow(aa, 0.4D), 0.0F);
    GL11.glPushMatrix();
    float ab = (float)Math.sqrt((1.0F / aa));
    GL11.glScalef(ab, ab, ab);
    GL11.glTranslatef(0.0F, 22.0F * f5 * age / (float)Math.pow(aa, 0.4D), 2.0F * f5 * age / ab);
    if (((IAnimal)entity).getGender() == IAnimal.GenderEnum.MALE &&
      aa <= 1.75D) {
      this.antler11.field_78807_k = false;
      this.antler21.field_78807_k = false;
      if (aa <= 1.5D) {
        this.antler12.field_78807_k = false;
        this.antler22.field_78807_k = false;
        if (aa <= 1.3D) {
          this.antler13.field_78807_k = false;
          this.antler23.field_78807_k = false;
          if (aa <= 1.1D) {
            this.antler14.field_78807_k = false;
            this.antler24.field_78807_k = false;
          }
        }
      }
    }

    this.antler11.func_78785_a(f5);
    this.antler21.func_78785_a(f5);
    this.head.func_78785_a(f5);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(1.0F / aa, ab, 1.0F / aa);
    GL11.glTranslatef(0.0F, 22.0F * f5 * age / (float)Math.pow(aa, 0.4D), 0.0F);
    this.thigh1.func_78785_a(f5);
    this.upperLeg4.func_78785_a(f5);
    this.upperLeg3.func_78785_a(f5);
    this.thigh2.func_78785_a(f5);




    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(1.0F / aa, 1.0F / aa, 1.0F / aa);
    GL11.glTranslatef(0.0F, 22.0F * f5 * age, 0.0F);




    this.tail.func_78785_a(f5);
    this.collar.func_78785_a(f5);

    this.neck.func_78785_a(f5);
    this.rump.func_78785_a(f5);
    this.body.func_78785_a(f5);




    this.torso.func_78785_a(f5);











    GL11.glPopMatrix();
  }















































  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }



  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);


    f1 = Math.min(f1 * 7.5F, 0.75F);
    f *= 0.95F;

    this.antler11.field_78807_k = true;
    this.antler12.field_78807_k = true;
    this.antler13.field_78807_k = true;
    this.antler14.field_78807_k = true;
    this.antler21.field_78807_k = true;
    this.antler22.field_78807_k = true;
    this.antler23.field_78807_k = true;
    this.antler24.field_78807_k = true;
    setRotation(this.antler21, f4 / 57.295776F, f3 / 57.295776F, 0.0F);
    setRotation(this.head, f4 / 57.295776F + 0.1570796F, f3 / 57.295776F, 0.0F);
    setRotation(this.antler11, f4 / 57.295776F, f3 / 57.295776F, 0.0F);
    setRotation(this.torso, 0.122173F, 0.0F, 0.0F);
    setRotation(this.collar, f4 / 171.88733F + 1.151917F, f3 / 171.88733F, 0.0F);
    setRotation(this.neck, f4 / 85.943665F + 1.815142F, f3 / 85.943665F, 0.0F);
    setRotation(this.rump, -0.0872665F, 0.0F, 0.0F);
    setRotation(this.body, 1.43117F, 0.0F, 0.0F);
    setRotation(this.calf1, 0.5585054F, 0.0F, -0.1745329F);
    setRotation(this.calf2, 0.5585054F, 0.0F, 0.1745329F);
    setRotation(this.toes3, 1.134464F, 0.0F, 0.0F);
    setRotation(this.hoof1, -1.134464F, 0.0F, 0.0F);
    setRotation(this.hoof2, -1.134464F, 0.0F, 0.0F);
    setRotation(this.hoof3, -1.134464F, 0.0F, 0.0F);
    setRotation(this.hoof4, -1.134464F, 0.0F, 0.0F);

    setRotation(this.tail, -1.308997F, 0.0F, 0.0F);

    setRotation(this.leg1, -0.38397244F, 0.0F, 0.0F);
    setRotation(this.leg2, -0.38397244F, 0.0F, 0.0F);
    setRotation(this.leg3, -0.3490659F, 0.0F, 0.0349066F);
    setRotation(this.leg4, -0.3490659F, 0.0F, -0.0349066F);

    setRotation(this.upperLeg4, 0.3490659F, 0.0F, 0.0349066F);
    setRotation(this.upperLeg3, 0.3490659F, 0.0F, -0.0349066F);
    setRotation(this.thigh1, -0.1745329F, 0.0F, 0.1745329F);
    setRotation(this.thigh2, -0.1745329F, 0.0F, -0.1745329F);

    if (!this.running) {
      setRotation(this.upperLeg4, MathHelper.func_76134_b(f / 1.5F + 4.712389F) * 0.7F * f1 + 0.3490659F, 0.0F, 0.0349066F);
      setRotation(this.upperLeg3, MathHelper.func_76134_b(f / 1.5F + 1.5707964F) * 0.7F * f1 + 0.3490659F, 0.0F, -0.0349066F);
      setRotation(this.thigh1, MathHelper.func_76134_b(f / 1.5F + 5.4977875F) * 0.7F * f1 - 0.1745329F, 0.0F, 0.1745329F);
      setRotation(this.thigh2, MathHelper.func_76134_b(f / 1.5F + 2.3561945F) * 0.7F * f1 - 0.1745329F, 0.0F, -0.1745329F);
      if (MathHelper.func_76126_a(f / 1.5F + 1.5707964F) * 0.7F * f1 > 0.0F) {
        setRotation(this.lowerleg3, MathHelper.func_76126_a(f / 1.5F + 1.5707964F) * 1.4F * f1, 0.0F, 0.0F);
        setRotation(this.leg3, -MathHelper.func_76126_a(f / 1.5F + 1.5707964F) * 0.7F * f1 - 0.3490659F, 0.0F, 0.0349066F);
        setRotation(this.toes3, MathHelper.func_76126_a(f / 1.5F + 1.5707964F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }
      if (MathHelper.func_76126_a(f / 1.5F + 1.5707964F) * 0.7F * f1 < 0.0F) {
        setRotation(this.lowerleg4, MathHelper.func_76126_a(f / 1.5F + 4.712389F) * 1.4F * f1, 0.0F, 0.0F);
        setRotation(this.leg4, -MathHelper.func_76126_a(f / 1.5F + 4.712389F) * 0.7F * f1 - 0.3490659F, 0.0F, -0.0349066F);
        setRotation(this.toes4, MathHelper.func_76126_a(f / 1.5F + 4.712389F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }
      if (MathHelper.func_76126_a(f / 1.5F + 5.4977875F) * 0.7F * f1 > 0.0F) {
        setRotation(this.calf1, MathHelper.func_76126_a(f / 1.5F + 5.4977875F) * 1.4F * f1 + 0.5585054F, 0.0F, -0.1745329F);
        setRotation(this.leg1, -MathHelper.func_76126_a(f / 1.5F + 5.4977875F) * 1.4F * f1 - 0.38397244F, 0.0F, 0.0F);
        setRotation(this.toes1, MathHelper.func_76126_a(f / 1.5F + 5.4977875F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }
      if (MathHelper.func_76126_a(f / 1.5F + 2.3561945F) * 0.7F * f1 > 0.0F) {
        setRotation(this.calf2, MathHelper.func_76126_a(f / 1.5F + 2.3561945F) * 1.4F * f1 + 0.5585054F, 0.0F, 0.1745329F);
        setRotation(this.leg2, -MathHelper.func_76126_a(f / 1.5F + 2.3561945F) * 1.4F * f1 - 0.38397244F, 0.0F, 0.0F);
        setRotation(this.toes2, MathHelper.func_76126_a(f / 1.5F + 2.3561945F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }

    } else {

      if (MathHelper.func_76134_b(f / 1.5F + 3.926991F) > -Math.sqrt(0.5D) && MathHelper.func_76134_b(f / 1.5F + 3.926991F) < Math.sqrt(0.5D)) {
        setRotation(this.upperLeg4, MathHelper.func_76134_b(f / 1.5F + 3.926991F) * 2.8F * f1 + 0.3490659F, 0.0F, 0.0349066F);
      }
      if (MathHelper.func_76126_a(f / 1.5F + 3.926991F - 1.1780972F) > 0.0F) {
        setRotation(this.lowerleg4, MathHelper.func_76126_a(f / 1.5F + 3.926991F - 1.1780972F) * 3.5F * f1, 0.0F, 0.0F);
        setRotation(this.leg4, -MathHelper.func_76126_a(f / 1.5F + 3.926991F - 1.1780972F) * 3.5F * f1 - 0.3490659F, 0.0F, -0.0349066F);
        setRotation(this.toes4, MathHelper.func_76126_a(f / 1.5F + 3.926991F - 1.1780972F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }


      if (MathHelper.func_76134_b(f / 1.5F + 1.5707964F) > -Math.sqrt(0.5D) && MathHelper.func_76134_b(f / 1.5F + 1.5707964F) < Math.sqrt(0.5D)) {
        setRotation(this.upperLeg3, MathHelper.func_76134_b(f / 1.5F + 1.5707964F) * 2.8F * f1 + 0.3490659F, 0.0F, -0.0349066F);
      }
      if (MathHelper.func_76126_a(f / 1.5F + 1.5707964F - 1.1780972F) > 0.0F) {
        setRotation(this.lowerleg3, MathHelper.func_76126_a(f / 1.5F + 1.5707964F - 1.1780972F) * 3.5F * f1, 0.0F, 0.0F);
        setRotation(this.leg3, -MathHelper.func_76126_a(f / 1.5F + 1.5707964F - 1.1780972F) * 3.5F * f1 - 0.3490659F, 0.0F, 0.0349066F);
        setRotation(this.toes3, MathHelper.func_76126_a(f / 1.5F + 1.5707964F - 1.1780972F) * 2.1F * f1 + 1.134464F, 0.0F, 0.0F);
      }

      setRotation(this.thigh1, MathHelper.func_76134_b(f / 1.5F + 5.4977875F) * 2.8F * f1 - 0.1745329F, 0.0F, 0.1745329F);
      setRotation(this.thigh2, MathHelper.func_76134_b(f / 1.5F + 2.3561945F) * 2.8F * f1 - 0.1745329F, 0.0F, -0.1745329F);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */