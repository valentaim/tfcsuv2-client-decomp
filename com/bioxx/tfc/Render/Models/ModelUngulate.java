package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;



public class ModelUngulate
  extends ModelBase
{
  private ModelRenderer body1;
  private ModelRenderer neck;
  private ModelRenderer haunch;
  private ModelRenderer underSide;
  private ModelRenderer throat;
  private ModelRenderer spine;
  private ModelRenderer leftThigh;
  private ModelRenderer rightThigh;
  private ModelRenderer leftCalf;
  private ModelRenderer rightCalf;
  private ModelRenderer leftAnkle;
  private ModelRenderer rightAnkle;
  private ModelRenderer leftFoot;
  private ModelRenderer rightFoot;
  private ModelRenderer leftHoofBack;
  private ModelRenderer rightHoofBack;
  private ModelRenderer leftShoulder;
  private ModelRenderer rightShoulder;
  private ModelRenderer leftArm;
  private ModelRenderer rightArm;
  private ModelRenderer leftWrist;
  private ModelRenderer rightWrist;
  private ModelRenderer rightHoofFront;
  private ModelRenderer leftHoofFront;

  public ModelUngulate() {
    this.field_78090_t = 64;
    this.field_78089_u = 32;

    this.body1 = new ModelRenderer(this, 0, 0);
    this.body1.func_78789_a(-3.0F, -3.0F, -6.0F, 6, 8, 16);
    this.body1.func_78793_a(0.0F, 7.0F, 0.0F);
    this.body1.func_78787_b(64, 32);
    this.body1.field_78809_i = true;
    setRotation(this.body1, -0.1047198F, 0.0F, 0.0F);
    this.neck = new ModelRenderer(this, 0, 0);
    this.neck.func_78789_a(-2.0F, -1.0F, -11.0F, 4, 5, 6);
    this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
    this.neck.func_78787_b(64, 32);
    this.neck.field_78809_i = true;
    setRotation(this.neck, -0.3490659F, 0.0F, 0.0F);
    this.haunch = new ModelRenderer(this, 0, 0);
    this.haunch.func_78789_a(-3.5F, -3.0F, 0.0F, 7, 8, 9);
    this.haunch.func_78793_a(0.0F, 0.0F, 4.0F);
    this.haunch.func_78787_b(64, 32);
    this.haunch.field_78809_i = true;
    setRotation(this.haunch, 0.0872665F, 0.0F, 0.0F);
    this.underSide = new ModelRenderer(this, 0, 0);
    this.underSide.func_78789_a(-3.0F, 1.5F, 2.0F, 6, 6, 4);
    this.underSide.func_78793_a(0.0F, 0.0F, 0.0F);
    this.underSide.func_78787_b(64, 32);
    this.underSide.field_78809_i = true;
    setRotation(this.underSide, 0.5235988F, 0.0F, 0.0F);
    this.throat = new ModelRenderer(this, 0, 0);
    this.throat.func_78789_a(-1.5F, 5.0F, -10.0F, 3, 2, 8);
    this.throat.func_78793_a(0.0F, 0.0F, 0.0F);
    this.throat.func_78787_b(64, 32);
    this.throat.field_78809_i = true;
    setRotation(this.throat, -0.6108652F, 0.0F, 0.0F);
    this.spine = new ModelRenderer(this, 0, 0);
    this.spine.func_78789_a(-1.5F, -4.0F, -9.0F, 3, 1, 22);
    this.spine.func_78793_a(0.0F, 0.0F, 0.0F);
    this.spine.func_78787_b(64, 32);
    this.spine.field_78809_i = true;
    setRotation(this.spine, -0.0349066F, 0.0F, 0.0F);
    this.leftThigh = new ModelRenderer(this, 0, 0);
    this.leftThigh.func_78789_a(-1.0F, -2.0F, -4.0F, 2, 7, 4);
    this.leftThigh.func_78793_a(3.0F, 2.0F, 8.0F);
    this.leftThigh.func_78787_b(64, 32);
    this.leftThigh.field_78809_i = true;
    setRotation(this.leftThigh, -0.5235988F, 0.0F, 0.0F);
    this.rightThigh = new ModelRenderer(this, 0, 0);
    this.rightThigh.func_78789_a(-1.0F, -2.0F, -4.0F, 2, 7, 4);
    this.rightThigh.func_78793_a(-3.0F, 2.0F, 8.0F);
    this.rightThigh.func_78787_b(64, 32);
    this.rightThigh.field_78809_i = true;
    setRotation(this.rightThigh, -0.5235988F, 0.0F, 0.0F);
    this.leftCalf = new ModelRenderer(this, 0, 0);
    this.leftCalf.func_78789_a(-0.6F, 0.0F, 0.0F, 2, 4, 3);
    this.leftCalf.func_78793_a(0.0F, 5.0F, -3.0F);
    this.leftCalf.func_78787_b(64, 32);
    this.leftCalf.field_78809_i = true;
    setRotation(this.leftCalf, 1.3089969F, 0.0F, 0.0F);
    this.rightCalf = new ModelRenderer(this, 0, 0);
    this.rightCalf.func_78789_a(-1.3F, 0.0F, 0.0F, 2, 4, 3);
    this.rightCalf.func_78793_a(0.0F, 5.0F, -3.0F);
    this.rightCalf.func_78787_b(64, 32);
    this.rightCalf.field_78809_i = true;
    setRotation(this.rightCalf, 1.3089969F, 0.0F, 0.0F);
    this.leftAnkle = new ModelRenderer(this, 0, 0);
    this.leftAnkle.func_78789_a(-0.6F, 2.0F, 0.2F, 2, 5, 2);
    this.leftAnkle.func_78793_a(0.0F, 0.0F, 0.0F);
    this.leftAnkle.func_78787_b(64, 32);
    this.leftAnkle.field_78809_i = true;
    setRotation(this.leftAnkle, 0.0F, 0.0F, 0.0F);
    this.rightAnkle = new ModelRenderer(this, 0, 0);
    this.rightAnkle.func_78789_a(-1.3F, 2.0F, 0.2F, 2, 5, 2);
    this.rightAnkle.func_78793_a(0.0F, 0.0F, 0.0F);
    this.rightAnkle.func_78787_b(64, 32);
    this.rightAnkle.field_78809_i = true;
    setRotation(this.rightAnkle, 0.0F, 0.0F, 0.0F);
    this.leftFoot = new ModelRenderer(this, 0, 0);
    this.leftFoot.func_78789_a(-0.6F, 0.8F, -0.3F, 2, 6, 2);
    this.leftFoot.func_78793_a(0.0F, 5.0F, 1.5F);
    this.leftFoot.func_78787_b(64, 32);
    this.leftFoot.field_78809_i = true;
    setRotation(this.leftFoot, -0.7853982F, 0.0F, 0.0F);
    this.rightFoot = new ModelRenderer(this, 0, 0);
    this.rightFoot.func_78789_a(-1.3F, 0.8F, -0.3F, 2, 6, 2);
    this.rightFoot.func_78793_a(0.0F, 5.0F, 1.5F);
    this.rightFoot.func_78787_b(64, 32);
    this.rightFoot.field_78809_i = true;
    setRotation(this.rightFoot, -0.7853982F, 0.0F, 0.0F);
    this.leftHoofBack = new ModelRenderer(this, 0, 0);
    this.leftHoofBack.func_78789_a(-0.6F, 0.5F, -0.5F, 2, 1, 2);
    this.leftHoofBack.func_78793_a(0.0F, 6.0F, -0.5F);
    this.leftHoofBack.func_78787_b(64, 32);
    this.leftHoofBack.field_78809_i = true;
    setRotation(this.leftHoofBack, 0.0F, 0.0F, 0.0F);
    this.rightHoofBack = new ModelRenderer(this, 0, 0);
    this.rightHoofBack.func_78789_a(-1.3F, 0.5F, -0.5F, 2, 1, 2);
    this.rightHoofBack.func_78793_a(0.0F, 6.0F, -0.5F);
    this.rightHoofBack.func_78787_b(64, 32);
    this.rightHoofBack.field_78809_i = true;
    setRotation(this.rightHoofBack, 0.0F, 0.0F, 0.0F);
    this.leftShoulder = new ModelRenderer(this, 0, 0);
    this.leftShoulder.func_78789_a(-1.5F, -1.0F, -2.0F, 2, 5, 3);
    this.leftShoulder.func_78793_a(3.0F, 3.0F, -4.0F);
    this.leftShoulder.func_78787_b(64, 32);
    this.leftShoulder.field_78809_i = true;
    setRotation(this.leftShoulder, 1.1519173F, 0.0F, 0.0F);
    this.rightShoulder = new ModelRenderer(this, 0, 0);
    this.rightShoulder.func_78789_a(-0.5F, -1.0F, -2.0F, 2, 5, 3);
    this.rightShoulder.func_78793_a(-3.0F, 3.0F, -4.0F);
    this.rightShoulder.func_78787_b(64, 32);
    this.rightShoulder.field_78809_i = true;
    setRotation(this.rightShoulder, 1.1519173F, 0.0F, 0.0F);
    this.leftArm = new ModelRenderer(this, 0, 0);
    this.leftArm.func_78789_a(-1.0F, 0.8F, 0.0F, 2, 5, 2);
    this.leftArm.func_78793_a(-0.4F, 2.0F, 0.0F);
    this.leftArm.func_78787_b(64, 32);
    this.leftArm.field_78809_i = true;
    setRotation(this.leftArm, -1.0471976F, 0.0F, 0.0F);
    this.rightArm = new ModelRenderer(this, 0, 0);
    this.rightArm.func_78789_a(-1.0F, 0.8F, 0.0F, 2, 5, 2);
    this.rightArm.func_78793_a(0.4F, 2.0F, 0.0F);
    this.rightArm.func_78787_b(64, 32);
    this.rightArm.field_78809_i = true;
    setRotation(this.rightArm, -1.0471976F, 0.0F, 0.0F);
    this.leftWrist = new ModelRenderer(this, 0, 0);
    this.leftWrist.func_78789_a(-1.0F, 0.5F, 0.0F, 2, 5, 2);
    this.leftWrist.func_78793_a(0.0F, 5.0F, 0.0F);
    this.leftWrist.func_78787_b(64, 32);
    this.leftWrist.field_78809_i = true;
    setRotation(this.leftWrist, 0.0F, 0.0F, 0.0F);
    this.rightWrist = new ModelRenderer(this, 0, 0);
    this.rightWrist.func_78789_a(-1.0F, 0.5F, 0.0F, 2, 5, 2);
    this.rightWrist.func_78793_a(0.0F, 5.0F, 0.0F);
    this.rightWrist.func_78787_b(64, 32);
    this.rightWrist.field_78809_i = true;
    setRotation(this.rightWrist, 0.0F, 0.0F, 0.0F);
    this.leftHoofFront = new ModelRenderer(this, 0, 0);
    this.leftHoofFront.func_78789_a(-1.0F, 0.5F, -0.5F, 2, 1, 2);
    this.leftHoofFront.func_78793_a(0.0F, 5.0F, 0.0F);
    this.leftHoofFront.func_78787_b(64, 32);
    this.leftHoofFront.field_78809_i = true;
    setRotation(this.leftHoofFront, 0.0F, 0.0F, 0.0F);
    this.rightHoofFront = new ModelRenderer(this, 0, 0);
    this.rightHoofFront.func_78789_a(-1.0F, 0.5F, -0.5F, 2, 1, 2);
    this.rightHoofFront.func_78793_a(0.0F, 5.0F, 0.0F);
    this.rightHoofFront.func_78787_b(64, 32);
    this.rightHoofFront.field_78809_i = true;
    setRotation(this.rightHoofFront, 0.0F, 0.0F, 0.0F);

    this.body1.func_78792_a(this.neck);
    this.neck.func_78792_a(this.throat);
    this.body1.func_78792_a(this.haunch);

    this.body1.func_78792_a(this.leftShoulder);
    this.body1.func_78792_a(this.rightShoulder);
    this.leftShoulder.func_78792_a(this.leftArm);
    this.leftArm.func_78792_a(this.leftWrist);
    this.leftWrist.func_78792_a(this.leftHoofFront);
    this.rightShoulder.func_78792_a(this.rightArm);
    this.rightArm.func_78792_a(this.rightWrist);
    this.rightWrist.func_78792_a(this.rightHoofFront);
    this.body1.func_78792_a(this.underSide);
    this.haunch.func_78792_a(this.leftThigh);
    this.haunch.func_78792_a(this.rightThigh);
    this.leftThigh.func_78792_a(this.leftCalf);
    this.rightThigh.func_78792_a(this.rightCalf);
    this.leftCalf.func_78792_a(this.leftAnkle);
    this.rightCalf.func_78792_a(this.rightAnkle);
    this.leftAnkle.func_78792_a(this.leftFoot);
    this.rightAnkle.func_78792_a(this.rightFoot);
    this.leftFoot.func_78792_a(this.leftHoofBack);
    this.rightFoot.func_78792_a(this.rightHoofBack);
  }



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    this.body1.func_78785_a(f5);
  }

























  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }


  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6) {
    float frontRightLeg = par1 + 2.3561945F;
    float frontLeftLeg = par1 + 7.0685835F;


    float speedMod = Math.min(Math.abs(Math.max(Math.abs(par2), 0.001F)), 1.0F) * ((par2 != 0.0F) ? (par2 / Math.abs(par2)) : 1.0F);
    float rightArmInitAngle = -1.0471976F;
    float leftArmInitAngle = -1.0471976F;
    float leftThighInitAngle = -0.5235988F;
    float rightThighInitAngle = -0.5235988F;
    float rightCalfInitAngle = 1.3089969F;
    float leftCalfInitAngle = 1.3089969F;
    float rightFootInitAngle = -0.7853982F;

    this.rightWrist.field_78795_f = Math.max(0.0F, -MathHelper.func_76134_b(frontRightLeg * 0.6662F) * 1.8F * speedMod);

    this.leftWrist.field_78795_f = Math.max(0.0F, -MathHelper.func_76134_b(frontLeftLeg * 0.6662F) * 1.8F * speedMod);


    this.rightShoulder.field_78795_f = MathHelper.func_76126_a(frontRightLeg * 0.6662F) * 1.4F * speedMod - rightArmInitAngle;
    this.leftShoulder.field_78795_f = MathHelper.func_76126_a(frontLeftLeg * 0.6662F) * 1.4F * speedMod - leftArmInitAngle;
    if (MathHelper.func_76134_b(frontRightLeg * 0.6662F + 1.5707964F) * 1.4F * speedMod > 0.0F) {
      this.rightArm.field_78795_f = MathHelper.func_76134_b(frontRightLeg * 0.6662F + 1.5707964F) * 1.4F * speedMod + rightArmInitAngle;
      this.leftCalf.field_78795_f = MathHelper.func_76134_b(frontRightLeg * 0.6662F + 1.5707964F) * 0.7F * ((MathHelper.func_76126_a(frontLeftLeg * 0.6662F) * speedMod > 0.0F) ? 1.5F : 1.0F) * speedMod + leftCalfInitAngle;
    }
    if (MathHelper.func_76134_b(frontLeftLeg * 0.6662F + 1.5707964F) * 1.4F * speedMod > 0.0F) {
      this.leftArm.field_78795_f = MathHelper.func_76134_b(frontLeftLeg * 0.6662F + 1.5707964F) * 1.4F * speedMod + leftArmInitAngle;
      this.rightCalf.field_78795_f = MathHelper.func_76134_b(frontLeftLeg * 0.6662F + 1.5707964F) * 0.7F * ((MathHelper.func_76126_a(frontRightLeg * 0.6662F) * speedMod > 0.0F) ? 1.5F : 1.0F) * speedMod + rightCalfInitAngle;
    }

    this.rightThigh.field_78795_f = MathHelper.func_76126_a(frontLeftLeg * 0.6662F) * 0.7F * speedMod + rightThighInitAngle;
    this.rightFoot.field_78795_f = MathHelper.func_76126_a(frontLeftLeg * 0.6662F) * 0.7F * ((MathHelper.func_76126_a(frontRightLeg * 0.6662F) * speedMod > 0.0F) ? 2 : true) * speedMod + rightFootInitAngle;
    this.leftThigh.field_78795_f = MathHelper.func_76126_a(frontRightLeg * 0.6662F) * 0.7F * speedMod + leftThighInitAngle;
    this.leftFoot.field_78795_f = MathHelper.func_76126_a(frontRightLeg * 0.6662F) * 0.7F * ((MathHelper.func_76126_a(frontLeftLeg * 0.6662F) * speedMod > 0.0F) ? 2 : true) * speedMod + rightFootInitAngle;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelUngulate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */