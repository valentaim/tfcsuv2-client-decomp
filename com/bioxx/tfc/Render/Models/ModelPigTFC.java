package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPigTFC extends ModelPig {
  ModelRenderer Head;
  ModelRenderer Trout;
  ModelRenderer Tusks;
  ModelRenderer Jaw;
  ModelRenderer LeftEar;
  ModelRenderer RightEar;
  ModelRenderer HeadMane;
  ModelRenderer Body;
  ModelRenderer BodyMane;
  ModelRenderer Tail;
  ModelRenderer UpperLegRight;
  ModelRenderer LowerLegRight;
  ModelRenderer UpperLegLeft;
  ModelRenderer LowerLegLeft;
  ModelRenderer UpperHindLegRight;
  ModelRenderer LowerHindLegRight;
  ModelRenderer UpperHindLegLeft;
  ModelRenderer LowerHindLegLeft;
  
  public ModelPigTFC() {
    this(1.0F);
  }
  
  public ModelPigTFC(float j) {
    this.field_78090_t = 64;
    this.field_78089_u = 64;
    this.Head = new ModelRenderer((ModelBase)this, 0, 0);
    this.Head.func_78789_a(-3.0F, 0.0F, -5.0F, 6, 6, 5);
    this.Head.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.Head, 0.2617994F, 0.0F, 0.0F);
    this.Trout = new ModelRenderer((ModelBase)this, 0, 11);
    this.Trout.func_78789_a(-1.5F, 1.5F, -9.5F, 3, 3, 5);
    this.Trout.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.Trout, 0.3490659F, 0.0F, 0.0F);
    this.Tusks = new ModelRenderer((ModelBase)this, 0, 24);
    this.Tusks.func_78789_a(-2.0F, 3.0F, -8.0F, 4, 2, 1);
    this.Tusks.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.Tusks, 0.3490659F, 0.0F, 0.0F);
    this.Jaw = new ModelRenderer((ModelBase)this, 0, 19);
    this.Jaw.func_78789_a(-1.0F, 4.9F, -8.5F, 2, 1, 4);
    this.Jaw.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.Jaw, 0.2617994F, 0.0F, 0.0F);
    this.LeftEar = new ModelRenderer((ModelBase)this, 16, 11);
    this.LeftEar.func_78789_a(1.0F, -4.0F, -2.0F, 2, 4, 2);
    this.LeftEar.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.LeftEar, 0.6981317F, 0.0F, 0.3490659F);
    this.RightEar = new ModelRenderer((ModelBase)this, 16, 17);
    this.RightEar.func_78789_a(-3.0F, -4.0F, -2.0F, 2, 4, 2);
    this.RightEar.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.RightEar, 0.6981317F, 0.0F, -0.3490659F);
    this.HeadMane = new ModelRenderer((ModelBase)this, 23, 0);
    this.HeadMane.func_78789_a(-1.0F, -2.0F, -5.0F, 2, 2, 5);
    this.HeadMane.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.HeadMane, 0.4363323F, 0.0F, 0.0F);
    this.Body = new ModelRenderer((ModelBase)this, 24, 0);
    this.Body.func_78789_a(-3.5F, 0.0F, 0.0F, 7, 8, 13);
    this.Body.func_78793_a(0.0F, 11.0F, -5.0F);
    setRotation(this.Body, -0.0872665F, 0.0F, 0.0F);
    this.BodyMane = new ModelRenderer((ModelBase)this, 0, 27);
    this.BodyMane.func_78789_a(-1.0F, -2.0F, -1.0F, 2, 2, 9);
    this.BodyMane.func_78793_a(0.0F, 11.3F, -4.0F);
    setRotation(this.BodyMane, -0.2617994F, 0.0F, 0.0F);
    this.Tail = new ModelRenderer((ModelBase)this, 60, 38);
    this.Tail.func_78789_a(-0.5F, 0.0F, 0.0F, 1, 5, 1);
    this.Tail.func_78793_a(0.0F, 13.0F, 7.5F);
    setRotation(this.Tail, 0.0872665F, 0.0F, 0.0F);
    this.UpperLegRight = new ModelRenderer((ModelBase)this, 32, 21);
    this.UpperLegRight.func_78789_a(-1.0F, -2.0F, -2.0F, 1, 5, 3);
    this.UpperLegRight.func_78793_a(-3.5F, 16.0F, -2.5F);
    setRotation(this.UpperLegRight, 0.1745329F, 0.0F, 0.0F);
    this.LowerLegRight = new ModelRenderer((ModelBase)this, 32, 29);
    this.LowerLegRight.func_78789_a(-0.5F, 2.0F, -1.0F, 2, 6, 2);
    this.LowerLegRight.func_78793_a(-3.5F, 16.0F, -2.5F);
    this.UpperLegLeft = new ModelRenderer((ModelBase)this, 24, 21);
    this.UpperLegLeft.func_78789_a(0.0F, -2.0F, -2.0F, 1, 5, 3);
    this.UpperLegLeft.func_78793_a(3.5F, 16.0F, -2.5F);
    setRotation(this.UpperLegLeft, 0.1745329F, 0.0F, 0.0F);
    this.LowerLegLeft = new ModelRenderer((ModelBase)this, 24, 29);
    this.LowerLegLeft.func_78789_a(-1.5F, 2.0F, -1.0F, 2, 6, 2);
    this.LowerLegLeft.func_78793_a(3.5F, 16.0F, -2.5F);
    this.UpperHindLegRight = new ModelRenderer((ModelBase)this, 44, 21);
    this.UpperHindLegRight.func_78789_a(-1.5F, -2.0F, -2.0F, 1, 5, 4);
    this.UpperHindLegRight.func_78793_a(-3.0F, 16.0F, 5.5F);
    setRotation(this.UpperHindLegRight, -0.2617994F, 0.0F, 0.0F);
    this.LowerHindLegRight = new ModelRenderer((ModelBase)this, 46, 30);
    this.LowerHindLegRight.func_78789_a(-1.0F, 2.0F, 0.0F, 2, 6, 2);
    this.LowerHindLegRight.func_78793_a(-3.0F, 16.0F, 5.5F);
    this.UpperHindLegLeft = new ModelRenderer((ModelBase)this, 54, 21);
    this.UpperHindLegLeft.func_78789_a(0.5F, -2.0F, -2.0F, 1, 5, 4);
    this.UpperHindLegLeft.func_78793_a(3.0F, 16.0F, 5.5F);
    setRotation(this.UpperHindLegLeft, -0.2617994F, 0.0F, 0.0F);
    this.LowerHindLegLeft = new ModelRenderer((ModelBase)this, 56, 30);
    this.LowerHindLegLeft.func_78789_a(-1.0F, 2.0F, 0.0F, 2, 6, 2);
    this.LowerHindLegLeft.func_78793_a(3.0F, 16.0F, 5.5F);
  }
  
  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    this.Head.func_78785_a(f5);
    this.Trout.func_78785_a(f5);
    this.Tusks.func_78785_a(f5);
    this.Jaw.func_78785_a(f5);
    this.LeftEar.func_78785_a(f5);
    this.RightEar.func_78785_a(f5);
    this.HeadMane.func_78785_a(f5);
    this.Body.func_78785_a(f5);
    this.BodyMane.func_78785_a(f5);
    this.Tail.func_78785_a(f5);
    this.UpperLegRight.func_78785_a(f5);
    this.LowerLegRight.func_78785_a(f5);
    this.UpperLegLeft.func_78785_a(f5);
    this.LowerLegLeft.func_78785_a(f5);
    this.UpperHindLegRight.func_78785_a(f5);
    this.LowerHindLegRight.func_78785_a(f5);
    this.UpperHindLegLeft.func_78785_a(f5);
    this.LowerHindLegLeft.func_78785_a(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    float XAngle = f4 / 57.29578F;
    float YAngle = f3 / 57.29578F;
    this.Head.field_78795_f = 0.2617994F + XAngle;
    this.Head.field_78796_g = YAngle;
    this.HeadMane.field_78795_f = 0.4363323F + XAngle;
    this.HeadMane.field_78796_g = YAngle;
    this.Trout.field_78795_f = 0.3490659F + XAngle;
    this.Trout.field_78796_g = YAngle;
    this.Jaw.field_78795_f = 0.2617994F + XAngle;
    this.Jaw.field_78796_g = YAngle;
    this.Tusks.field_78795_f = 0.3490659F + XAngle;
    this.Tusks.field_78796_g = YAngle;
    this.LeftEar.field_78795_f = 0.6981317F + XAngle;
    this.LeftEar.field_78796_g = YAngle;
    this.RightEar.field_78795_f = 0.6981317F + XAngle;
    this.RightEar.field_78796_g = YAngle;
    float LLegRotX = MathHelper.func_76134_b(f * 0.6662F) * 1.4F * f1;
    float RLegRotX = MathHelper.func_76134_b(f * 0.6662F + 3.141593F) * 1.4F * f1;
    this.UpperLegLeft.field_78795_f = LLegRotX;
    this.LowerLegLeft.field_78795_f = LLegRotX;
    this.UpperHindLegRight.field_78795_f = LLegRotX;
    this.LowerHindLegRight.field_78795_f = LLegRotX;
    this.UpperLegRight.field_78795_f = RLegRotX;
    this.LowerLegRight.field_78795_f = RLegRotX;
    this.UpperHindLegLeft.field_78795_f = RLegRotX;
    this.LowerHindLegLeft.field_78795_f = RLegRotX;
    this.Tail.field_78808_h = LLegRotX * 0.2F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelPigTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */