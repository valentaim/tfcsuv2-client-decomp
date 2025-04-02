package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;












@SideOnly(Side.CLIENT)
public class ModelHorseTFC
  extends ModelBase
{
  private ModelRenderer head;
  private ModelRenderer mouthTop;
  private ModelRenderer mouthBottom;
  private ModelRenderer horseLeftEar;
  private ModelRenderer horseRightEar;
  private ModelRenderer muleLeftEar;
  private ModelRenderer muleRightEar;
  private ModelRenderer neck;
  private ModelRenderer horseFaceRopes;
  private ModelRenderer mane;
  private ModelRenderer body;
  private ModelRenderer tailBase;
  private ModelRenderer tailMiddle;
  private ModelRenderer tailTip;
  private ModelRenderer backLeftLeg;
  private ModelRenderer backLeftShin;
  private ModelRenderer backLeftHoof;
  private ModelRenderer backRightLeg;
  private ModelRenderer backRightShin;
  private ModelRenderer backRightHoof;
  private ModelRenderer frontLeftLeg;
  private ModelRenderer frontLeftShin;
  private ModelRenderer frontLeftHoof;
  private ModelRenderer frontRightLeg;
  private ModelRenderer frontRightShin;
  private ModelRenderer frontRightHoof;
  private ModelRenderer muleLeftChest;
  private ModelRenderer muleRightChest;
  private ModelRenderer horseSaddleBottom;
  private ModelRenderer horseSaddleFront;
  private ModelRenderer horseSaddleBack;
  private ModelRenderer horseLeftSaddleRope;
  private ModelRenderer horseLeftSaddleMetal;
  private ModelRenderer horseRightSaddleRope;
  private ModelRenderer horseRightSaddleMetal;
  private ModelRenderer horseLeftFaceMetal;
  private ModelRenderer horseRightFaceMetal;
  private ModelRenderer horseLeftRein;
  private ModelRenderer horseRightRein;

  public ModelHorseTFC() {
    this.field_78090_t = 128;
    this.field_78089_u = 128;
    this.body = new ModelRenderer(this, 0, 34);
    this.body.func_78789_a(-5.0F, -8.0F, -19.0F, 10, 10, 24);
    this.body.func_78793_a(0.0F, 11.0F, 9.0F);
    this.tailBase = new ModelRenderer(this, 44, 0);
    this.tailBase.func_78789_a(-1.0F, -1.0F, 0.0F, 2, 2, 3);
    this.tailBase.func_78793_a(0.0F, 3.0F, 14.0F);
    setBoxRotation(this.tailBase, -1.134464F, 0.0F, 0.0F);
    this.tailMiddle = new ModelRenderer(this, 38, 7);
    this.tailMiddle.func_78789_a(-1.5F, -2.0F, 3.0F, 3, 4, 7);
    this.tailMiddle.func_78793_a(0.0F, 3.0F, 14.0F);
    setBoxRotation(this.tailMiddle, -1.134464F, 0.0F, 0.0F);
    this.tailTip = new ModelRenderer(this, 24, 3);
    this.tailTip.func_78789_a(-1.5F, -4.5F, 9.0F, 3, 4, 7);
    this.tailTip.func_78793_a(0.0F, 3.0F, 14.0F);
    setBoxRotation(this.tailTip, -1.40215F, 0.0F, 0.0F);
    this.backLeftLeg = new ModelRenderer(this, 78, 29);
    this.backLeftLeg.func_78789_a(-2.5F, -2.0F, -2.5F, 4, 9, 5);
    this.backLeftLeg.func_78793_a(4.0F, 9.0F, 11.0F);
    this.backLeftShin = new ModelRenderer(this, 78, 43);
    this.backLeftShin.func_78789_a(-2.0F, 0.0F, -1.5F, 3, 5, 3);
    this.backLeftShin.func_78793_a(4.0F, 16.0F, 11.0F);
    this.backLeftHoof = new ModelRenderer(this, 78, 51);
    this.backLeftHoof.func_78789_a(-2.5F, 5.1F, -2.0F, 4, 3, 4);
    this.backLeftHoof.func_78793_a(4.0F, 16.0F, 11.0F);
    this.backRightLeg = new ModelRenderer(this, 96, 29);
    this.backRightLeg.func_78789_a(-1.5F, -2.0F, -2.5F, 4, 9, 5);
    this.backRightLeg.func_78793_a(-4.0F, 9.0F, 11.0F);
    this.backRightShin = new ModelRenderer(this, 96, 43);
    this.backRightShin.func_78789_a(-1.0F, 0.0F, -1.5F, 3, 5, 3);
    this.backRightShin.func_78793_a(-4.0F, 16.0F, 11.0F);
    this.backRightHoof = new ModelRenderer(this, 96, 51);
    this.backRightHoof.func_78789_a(-1.5F, 5.1F, -2.0F, 4, 3, 4);
    this.backRightHoof.func_78793_a(-4.0F, 16.0F, 11.0F);
    this.frontLeftLeg = new ModelRenderer(this, 44, 29);
    this.frontLeftLeg.func_78789_a(-1.9F, -1.0F, -2.1F, 3, 8, 4);
    this.frontLeftLeg.func_78793_a(4.0F, 9.0F, -8.0F);
    this.frontLeftShin = new ModelRenderer(this, 44, 41);
    this.frontLeftShin.func_78789_a(-1.9F, 0.0F, -1.6F, 3, 5, 3);
    this.frontLeftShin.func_78793_a(4.0F, 16.0F, -8.0F);
    this.frontLeftHoof = new ModelRenderer(this, 44, 51);
    this.frontLeftHoof.func_78789_a(-2.4F, 5.1F, -2.1F, 4, 3, 4);
    this.frontLeftHoof.func_78793_a(4.0F, 16.0F, -8.0F);
    this.frontRightLeg = new ModelRenderer(this, 60, 29);
    this.frontRightLeg.func_78789_a(-1.1F, -1.0F, -2.1F, 3, 8, 4);
    this.frontRightLeg.func_78793_a(-4.0F, 9.0F, -8.0F);
    this.frontRightShin = new ModelRenderer(this, 60, 41);
    this.frontRightShin.func_78789_a(-1.1F, 0.0F, -1.6F, 3, 5, 3);
    this.frontRightShin.func_78793_a(-4.0F, 16.0F, -8.0F);
    this.frontRightHoof = new ModelRenderer(this, 60, 51);
    this.frontRightHoof.func_78789_a(-1.6F, 5.1F, -2.1F, 4, 3, 4);
    this.frontRightHoof.func_78793_a(-4.0F, 16.0F, -8.0F);
    this.head = new ModelRenderer(this, 0, 0);
    this.head.func_78789_a(-2.5F, -10.0F, -1.5F, 5, 5, 7);
    this.head.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.head, 0.5235988F, 0.0F, 0.0F);
    this.mouthTop = new ModelRenderer(this, 24, 18);
    this.mouthTop.func_78789_a(-2.0F, -10.0F, -7.0F, 4, 3, 6);
    this.mouthTop.func_78793_a(0.0F, 3.95F, -10.0F);
    setBoxRotation(this.mouthTop, 0.5235988F, 0.0F, 0.0F);
    this.mouthBottom = new ModelRenderer(this, 24, 27);
    this.mouthBottom.func_78789_a(-2.0F, -7.0F, -6.5F, 4, 2, 5);
    this.mouthBottom.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.mouthBottom, 0.5235988F, 0.0F, 0.0F);
    this.head.func_78792_a(this.mouthTop);
    this.head.func_78792_a(this.mouthBottom);
    this.horseLeftEar = new ModelRenderer(this, 0, 0);
    this.horseLeftEar.func_78789_a(0.45F, -12.0F, 4.0F, 2, 3, 1);
    this.horseLeftEar.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.horseLeftEar, 0.5235988F, 0.0F, 0.0F);
    this.horseRightEar = new ModelRenderer(this, 0, 0);
    this.horseRightEar.func_78789_a(-2.45F, -12.0F, 4.0F, 2, 3, 1);
    this.horseRightEar.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.horseRightEar, 0.5235988F, 0.0F, 0.0F);
    this.muleLeftEar = new ModelRenderer(this, 0, 12);
    this.muleLeftEar.func_78789_a(-2.0F, -16.0F, 4.0F, 2, 7, 1);
    this.muleLeftEar.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.muleLeftEar, 0.5235988F, 0.0F, 0.2617994F);
    this.muleRightEar = new ModelRenderer(this, 0, 12);
    this.muleRightEar.func_78789_a(0.0F, -16.0F, 4.0F, 2, 7, 1);
    this.muleRightEar.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.muleRightEar, 0.5235988F, 0.0F, -0.2617994F);
    this.neck = new ModelRenderer(this, 0, 12);
    this.neck.func_78789_a(-2.05F, -9.8F, -2.0F, 4, 14, 8);
    this.neck.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.neck, 0.5235988F, 0.0F, 0.0F);
    this.muleLeftChest = new ModelRenderer(this, 0, 34);
    this.muleLeftChest.func_78789_a(-3.0F, 0.0F, 0.0F, 8, 8, 3);
    this.muleLeftChest.func_78793_a(-7.5F, 3.0F, 10.0F);
    setBoxRotation(this.muleLeftChest, 0.0F, 1.5707964F, 0.0F);
    this.muleRightChest = new ModelRenderer(this, 0, 47);
    this.muleRightChest.func_78789_a(-3.0F, 0.0F, 0.0F, 8, 8, 3);
    this.muleRightChest.func_78793_a(4.5F, 3.0F, 10.0F);
    setBoxRotation(this.muleRightChest, 0.0F, 1.5707964F, 0.0F);
    this.horseSaddleBottom = new ModelRenderer(this, 80, 0);
    this.horseSaddleBottom.func_78789_a(-5.0F, 0.0F, -3.0F, 10, 1, 8);
    this.horseSaddleBottom.func_78793_a(0.0F, 2.0F, 2.0F);
    this.horseSaddleFront = new ModelRenderer(this, 106, 9);
    this.horseSaddleFront.func_78789_a(-1.5F, -1.0F, -3.0F, 3, 1, 2);
    this.horseSaddleFront.func_78793_a(0.0F, 2.0F, 2.0F);
    this.horseSaddleBack = new ModelRenderer(this, 80, 9);
    this.horseSaddleBack.func_78789_a(-4.0F, -1.0F, 3.0F, 8, 1, 2);
    this.horseSaddleBack.func_78793_a(0.0F, 2.0F, 2.0F);
    this.horseLeftSaddleMetal = new ModelRenderer(this, 74, 0);
    this.horseLeftSaddleMetal.func_78789_a(-0.5F, 6.0F, -1.0F, 1, 2, 2);
    this.horseLeftSaddleMetal.func_78793_a(5.0F, 3.0F, 2.0F);
    this.horseLeftSaddleRope = new ModelRenderer(this, 70, 0);
    this.horseLeftSaddleRope.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 6, 1);
    this.horseLeftSaddleRope.func_78793_a(5.0F, 3.0F, 2.0F);
    this.horseRightSaddleMetal = new ModelRenderer(this, 74, 4);
    this.horseRightSaddleMetal.func_78789_a(-0.5F, 6.0F, -1.0F, 1, 2, 2);
    this.horseRightSaddleMetal.func_78793_a(-5.0F, 3.0F, 2.0F);
    this.horseRightSaddleRope = new ModelRenderer(this, 80, 0);
    this.horseRightSaddleRope.func_78789_a(-0.5F, 0.0F, -0.5F, 1, 6, 1);
    this.horseRightSaddleRope.func_78793_a(-5.0F, 3.0F, 2.0F);
    this.horseLeftFaceMetal = new ModelRenderer(this, 74, 13);
    this.horseLeftFaceMetal.func_78789_a(1.5F, -8.0F, -4.0F, 1, 2, 2);
    this.horseLeftFaceMetal.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.horseLeftFaceMetal, 0.5235988F, 0.0F, 0.0F);
    this.horseRightFaceMetal = new ModelRenderer(this, 74, 13);
    this.horseRightFaceMetal.func_78789_a(-2.5F, -8.0F, -4.0F, 1, 2, 2);
    this.horseRightFaceMetal.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.horseRightFaceMetal, 0.5235988F, 0.0F, 0.0F);
    this.horseLeftRein = new ModelRenderer(this, 44, 10);
    this.horseLeftRein.func_78789_a(2.6F, -6.0F, -6.0F, 0, 3, 16);
    this.horseLeftRein.func_78793_a(0.0F, 4.0F, -10.0F);
    this.horseRightRein = new ModelRenderer(this, 44, 5);
    this.horseRightRein.func_78789_a(-2.6F, -6.0F, -6.0F, 0, 3, 16);
    this.horseRightRein.func_78793_a(0.0F, 4.0F, -10.0F);
    this.mane = new ModelRenderer(this, 58, 0);
    this.mane.func_78789_a(-1.0F, -11.5F, 5.0F, 2, 16, 4);
    this.mane.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.mane, 0.5235988F, 0.0F, 0.0F);
    this.horseFaceRopes = new ModelRenderer(this, 80, 12);
    this.horseFaceRopes.func_78790_a(-2.5F, -10.1F, -7.0F, 5, 5, 12, 0.2F);
    this.horseFaceRopes.func_78793_a(0.0F, 4.0F, -10.0F);
    setBoxRotation(this.horseFaceRopes, 0.5235988F, 0.0F, 0.0F);
  }






  public void func_78088_a(Entity entity, float minX, float minY, float minZ, float maxX, float maxY, float maxZ) {
    EntityHorseTFC entityhorse = (EntityHorseTFC)entity;
    int i = entityhorse.func_110265_bP();

    boolean flag = entityhorse.func_110228_bR();
    boolean flag1 = (flag && entityhorse.func_110257_ck());
    boolean flag2 = (flag && entityhorse.func_110261_ca());
    boolean flag3 = (i == 1 || i == 2);
    float f7 = entityhorse.func_110254_bY();
    boolean flag4 = (entityhorse.field_70153_n != null);

    if (flag1) {

      this.horseFaceRopes.func_78785_a(maxZ);
      this.horseSaddleBottom.func_78785_a(maxZ);
      this.horseSaddleFront.func_78785_a(maxZ);
      this.horseSaddleBack.func_78785_a(maxZ);
      this.horseLeftSaddleRope.func_78785_a(maxZ);
      this.horseLeftSaddleMetal.func_78785_a(maxZ);
      this.horseRightSaddleRope.func_78785_a(maxZ);
      this.horseRightSaddleMetal.func_78785_a(maxZ);
      this.horseLeftFaceMetal.func_78785_a(maxZ);
      this.horseRightFaceMetal.func_78785_a(maxZ);

      if (flag4) {

        this.horseLeftRein.func_78785_a(maxZ);
        this.horseRightRein.func_78785_a(maxZ);
      }
    }

    float percent = TFC_Core.getPercentGrown((IAnimal)entity);
    float ageScale = 2.0F - percent;
    float ageHeadScale = (float)Math.pow((1.0F / ageScale), 0.66D);


    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.3F - 0.3F * percent, 0.0F);
    GL11.glPushMatrix();
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale + 0.25F * (1.0F - percent), 1.0F / ageScale);
    GL11.glTranslatef(0.0F, 0.95F * (1.0F - f7) * (1.0F - percent), 0.0F);








    this.backLeftLeg.func_78785_a(maxZ);
    this.backLeftShin.func_78785_a(maxZ);
    this.backLeftHoof.func_78785_a(maxZ);
    this.backRightLeg.func_78785_a(maxZ);
    this.backRightShin.func_78785_a(maxZ);
    this.backRightHoof.func_78785_a(maxZ);
    this.frontLeftLeg.func_78785_a(maxZ);
    this.frontLeftShin.func_78785_a(maxZ);
    this.frontLeftHoof.func_78785_a(maxZ);
    this.frontRightLeg.func_78785_a(maxZ);
    this.frontRightShin.func_78785_a(maxZ);
    this.frontRightHoof.func_78785_a(maxZ);
    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
    GL11.glTranslatef(0.0F, 1.35F * (1.0F - f7) * (1.0F - percent), 0.0F);


    this.body.func_78785_a(maxZ);
    this.tailBase.func_78785_a(maxZ);
    this.tailMiddle.func_78785_a(maxZ);
    this.tailTip.func_78785_a(maxZ);







    this.neck.func_78785_a(maxZ);
    this.mane.func_78785_a(maxZ);

    GL11.glPopMatrix();

    GL11.glPushMatrix();


    GL11.glScalef(ageHeadScale, ageHeadScale, ageHeadScale);
    GL11.glTranslatef(0.0F, 0.0F, 0.1875F - 0.1875F * percent);


















    if (flag3) {

      this.muleLeftEar.func_78785_a(maxZ);
      this.muleRightEar.func_78785_a(maxZ);
    }
    else {

      this.horseLeftEar.func_78785_a(maxZ);
      this.horseRightEar.func_78785_a(maxZ);
    }

    this.head.func_78785_a(maxZ);

    if (flag2) {

      this.muleLeftChest.func_78785_a(maxZ);
      this.muleRightChest.func_78785_a(maxZ);
    }
    GL11.glPopMatrix();
    GL11.glPopMatrix();
  }





  private void setBoxRotation(ModelRenderer renderer, float xAngle, float yAngle, float zAngle) {
    renderer.field_78795_f = xAngle;
    renderer.field_78796_g = yAngle;
    renderer.field_78808_h = zAngle;
  }






  private float updateHorseRotation(float xOffset, float yOffset, float zOffset) {
    float f3;
    for (f3 = yOffset - xOffset; f3 < -180.0F; f3 += 360.0F);




    while (f3 >= 180.0F)
    {
      f3 -= 360.0F;
    }

    return xOffset + zOffset * f3;
  }







  public void func_78086_a(EntityLivingBase entity, float x, float y, float z) {
    super.func_78086_a(entity, x, y, z);
    float f3 = updateHorseRotation(entity.field_70760_ar, entity.field_70761_aq, z);
    float f4 = updateHorseRotation(entity.field_70758_at, entity.field_70759_as, z);
    float f5 = entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * z;
    float f6 = f4 - f3;
    float f7 = f5 / 57.295776F;

    if (f6 > 20.0F)
    {
      f6 = 20.0F;
    }

    if (f6 < -20.0F)
    {
      f6 = -20.0F;
    }

    if (y > 0.2F)
    {
      f7 += MathHelper.func_76134_b(x * 0.4F) * 0.15F * y;
    }

    EntityHorseTFC entityhorse = (EntityHorseTFC)entity;
    float f8 = entityhorse.func_110258_o(z);
    float f9 = entityhorse.func_110223_p(z);
    float f10 = 1.0F - f9;
    float f11 = entityhorse.func_110201_q(z);
    boolean flag = (entityhorse.field_110278_bp != 0);
    boolean flag1 = entityhorse.func_110257_ck();
    boolean flag2 = (entityhorse.field_70153_n != null);
    float f12 = entity.field_70173_aa + z;
    float f13 = MathHelper.func_76134_b(x * 0.6662F + 3.1415927F);
    float f14 = f13 * 0.8F * y;
    this.head.field_78797_d = 4.0F;
    this.head.field_78798_e = -10.0F;
    this.tailBase.field_78797_d = 3.0F;
    this.tailMiddle.field_78798_e = 14.0F;
    this.muleRightChest.field_78797_d = 3.0F;
    this.muleRightChest.field_78798_e = 10.0F;
    this.body.field_78795_f = 0.0F;
    this.head.field_78795_f = 0.5235988F + f7;
    this.head.field_78796_g = f6 / 57.295776F;
    this.head.field_78795_f = f9 * (0.2617994F + f7) + f8 * 2.18166F + (1.0F - Math.max(f9, f8)) * this.head.field_78795_f;
    this.head.field_78796_g = f9 * f6 / 57.295776F + (1.0F - Math.max(f9, f8)) * this.head.field_78796_g;
    this.head.field_78797_d = f9 * -6.0F + f8 * 11.0F + (1.0F - Math.max(f9, f8)) * this.head.field_78797_d;
    this.head.field_78798_e = f9 * -1.0F + f8 * -10.0F + (1.0F - Math.max(f9, f8)) * this.head.field_78798_e;
    this.tailBase.field_78797_d = f9 * 9.0F + f10 * this.tailBase.field_78797_d;
    this.tailMiddle.field_78798_e = f9 * 18.0F + f10 * this.tailMiddle.field_78798_e;
    this.muleRightChest.field_78797_d = f9 * 5.5F + f10 * this.muleRightChest.field_78797_d;
    this.muleRightChest.field_78798_e = f9 * 15.0F + f10 * this.muleRightChest.field_78798_e;
    this.body.field_78795_f = f9 * -0.7853982F + f10 * this.body.field_78795_f;
    this.horseLeftEar.field_78797_d = this.head.field_78797_d;
    this.horseRightEar.field_78797_d = this.head.field_78797_d;
    this.muleLeftEar.field_78797_d = this.head.field_78797_d;
    this.muleRightEar.field_78797_d = this.head.field_78797_d;
    this.neck.field_78797_d = this.head.field_78797_d;
    this.mouthTop.field_78797_d = 0.02F;
    this.mouthBottom.field_78797_d = 0.0F;
    this.mane.field_78797_d = this.head.field_78797_d;
    this.horseLeftEar.field_78798_e = this.head.field_78798_e;
    this.horseRightEar.field_78798_e = this.head.field_78798_e;
    this.muleLeftEar.field_78798_e = this.head.field_78798_e;
    this.muleRightEar.field_78798_e = this.head.field_78798_e;
    this.neck.field_78798_e = this.head.field_78798_e;
    this.mouthTop.field_78798_e = 0.02F - f11 * 1.0F;
    this.mouthBottom.field_78798_e = 0.0F + f11 * 1.0F;
    this.mane.field_78798_e = this.head.field_78798_e;
    this.horseLeftEar.field_78795_f = this.head.field_78795_f;
    this.horseRightEar.field_78795_f = this.head.field_78795_f;
    this.muleLeftEar.field_78795_f = this.head.field_78795_f;
    this.muleRightEar.field_78795_f = this.head.field_78795_f;
    this.neck.field_78795_f = this.head.field_78795_f;
    this.mouthTop.field_78795_f = 0.0F - 0.09424778F * f11;
    this.mouthBottom.field_78795_f = 0.0F + 0.15707964F * f11;
    this.mane.field_78795_f = this.head.field_78795_f;
    this.horseLeftEar.field_78796_g = this.head.field_78796_g;
    this.horseRightEar.field_78796_g = this.head.field_78796_g;
    this.muleLeftEar.field_78796_g = this.head.field_78796_g;
    this.muleRightEar.field_78796_g = this.head.field_78796_g;
    this.neck.field_78796_g = this.head.field_78796_g;
    this.mouthTop.field_78796_g = 0.0F;
    this.mouthBottom.field_78796_g = 0.0F;
    this.mane.field_78796_g = this.head.field_78796_g;
    this.muleLeftChest.field_78795_f = f14 / 5.0F;
    this.muleRightChest.field_78795_f = -f14 / 5.0F;
    float f15 = 1.5707964F;


    float f18 = 0.2617994F * f9;
    float f19 = MathHelper.func_76134_b(f12 * 0.6F + 3.1415927F);
    this.frontLeftLeg.field_78797_d = -2.0F * f9 + 9.0F * f10;
    this.frontLeftLeg.field_78798_e = -2.0F * f9 + -8.0F * f10;
    this.frontRightLeg.field_78797_d = this.frontLeftLeg.field_78797_d;
    this.frontRightLeg.field_78798_e = this.frontLeftLeg.field_78798_e;
    this.backLeftLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f18 + f10 * -f13 * 0.5F * y) * 7.0F;
    this.backLeftLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f18 + f10 * -f13 * 0.5F * y) * 7.0F;
    this.backRightLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f18 + f10 * f13 * 0.5F * y) * 7.0F;
    this.backRightLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f18 + f10 * f13 * 0.5F * y) * 7.0F;
    float f20 = (-1.0471976F + f19) * f9 + f14 * f10;
    float f21 = (-1.0471976F + -f19) * f9 + -f14 * f10;
    this.frontLeftLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f20) * 7.0F;
    this.frontLeftLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f20) * 7.0F;
    this.frontRightLeg.field_78797_d += MathHelper.func_76126_a(1.5707964F + f21) * 7.0F;
    this.frontRightLeg.field_78798_e += MathHelper.func_76134_b(4.712389F + f21) * 7.0F;
    this.backLeftLeg.field_78795_f = f18 + -f13 * 0.5F * y * f10;
    this.backLeftShin.field_78795_f = -0.08726646F * f9 + (-f13 * 0.5F * y - Math.max(0.0F, f13 * 0.5F * y)) * f10;
    this.backLeftHoof.field_78795_f = this.backLeftShin.field_78795_f;
    this.backRightLeg.field_78795_f = f18 + f13 * 0.5F * y * f10;
    this.backRightShin.field_78795_f = -0.08726646F * f9 + (f13 * 0.5F * y - Math.max(0.0F, -f13 * 0.5F * y)) * f10;
    this.backRightHoof.field_78795_f = this.backRightShin.field_78795_f;
    this.frontLeftLeg.field_78795_f = f20;
    this.frontLeftShin.field_78795_f = (this.frontLeftLeg.field_78795_f + 3.1415927F * Math.max(0.0F, 0.2F + f19 * 0.2F)) * f9 + (f14 + Math.max(0.0F, f13 * 0.5F * y)) * f10;
    this.frontLeftHoof.field_78795_f = this.frontLeftShin.field_78795_f;
    this.frontRightLeg.field_78795_f = f21;
    this.frontRightShin.field_78795_f = (this.frontRightLeg.field_78795_f + 3.1415927F * Math.max(0.0F, 0.2F - f19 * 0.2F)) * f9 + (-f14 + Math.max(0.0F, -f13 * 0.5F * y)) * f10;
    this.frontRightHoof.field_78795_f = this.frontRightShin.field_78795_f;
    this.backLeftHoof.field_78797_d = this.backLeftShin.field_78797_d;
    this.backLeftHoof.field_78798_e = this.backLeftShin.field_78798_e;
    this.backRightHoof.field_78797_d = this.backRightShin.field_78797_d;
    this.backRightHoof.field_78798_e = this.backRightShin.field_78798_e;
    this.frontLeftHoof.field_78797_d = this.frontLeftShin.field_78797_d;
    this.frontLeftHoof.field_78798_e = this.frontLeftShin.field_78798_e;
    this.frontRightHoof.field_78797_d = this.frontRightShin.field_78797_d;
    this.frontRightHoof.field_78798_e = this.frontRightShin.field_78798_e;

    if (flag1) {

      this.horseSaddleBottom.field_78797_d = f9 * 0.5F + f10 * 2.0F;
      this.horseSaddleBottom.field_78798_e = f9 * 11.0F + f10 * 2.0F;
      this.horseSaddleFront.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.horseSaddleBack.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.horseLeftSaddleRope.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.horseRightSaddleRope.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.horseLeftSaddleMetal.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.horseRightSaddleMetal.field_78797_d = this.horseSaddleBottom.field_78797_d;
      this.muleLeftChest.field_78797_d = this.muleRightChest.field_78797_d;
      this.horseSaddleFront.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.horseSaddleBack.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.horseLeftSaddleRope.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.horseRightSaddleRope.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.horseLeftSaddleMetal.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.horseRightSaddleMetal.field_78798_e = this.horseSaddleBottom.field_78798_e;
      this.muleLeftChest.field_78798_e = this.muleRightChest.field_78798_e;
      this.horseSaddleBottom.field_78795_f = this.body.field_78795_f;
      this.horseSaddleFront.field_78795_f = this.body.field_78795_f;
      this.horseSaddleBack.field_78795_f = this.body.field_78795_f;
      this.horseLeftRein.field_78797_d = this.head.field_78797_d;
      this.horseRightRein.field_78797_d = this.head.field_78797_d;
      this.horseFaceRopes.field_78797_d = this.head.field_78797_d;
      this.horseLeftFaceMetal.field_78797_d = this.head.field_78797_d;
      this.horseRightFaceMetal.field_78797_d = this.head.field_78797_d;
      this.horseLeftRein.field_78798_e = this.head.field_78798_e;
      this.horseRightRein.field_78798_e = this.head.field_78798_e;
      this.horseFaceRopes.field_78798_e = this.head.field_78798_e;
      this.horseLeftFaceMetal.field_78798_e = this.head.field_78798_e;
      this.horseRightFaceMetal.field_78798_e = this.head.field_78798_e;
      this.horseLeftRein.field_78795_f = f7;
      this.horseRightRein.field_78795_f = f7;
      this.horseFaceRopes.field_78795_f = this.head.field_78795_f;
      this.horseLeftFaceMetal.field_78795_f = this.head.field_78795_f;
      this.horseRightFaceMetal.field_78795_f = this.head.field_78795_f;
      this.horseFaceRopes.field_78796_g = this.head.field_78796_g;
      this.horseLeftFaceMetal.field_78796_g = this.head.field_78796_g;
      this.horseLeftRein.field_78796_g = this.head.field_78796_g;
      this.horseRightFaceMetal.field_78796_g = this.head.field_78796_g;
      this.horseRightRein.field_78796_g = this.head.field_78796_g;

      if (flag2) {

        this.horseLeftSaddleRope.field_78795_f = -1.0471976F;
        this.horseLeftSaddleMetal.field_78795_f = -1.0471976F;
        this.horseRightSaddleRope.field_78795_f = -1.0471976F;
        this.horseRightSaddleMetal.field_78795_f = -1.0471976F;
        this.horseLeftSaddleRope.field_78808_h = 0.0F;
        this.horseLeftSaddleMetal.field_78808_h = 0.0F;
        this.horseRightSaddleRope.field_78808_h = 0.0F;
        this.horseRightSaddleMetal.field_78808_h = 0.0F;
      }
      else {

        this.horseLeftSaddleRope.field_78795_f = f14 / 3.0F;
        this.horseLeftSaddleMetal.field_78795_f = f14 / 3.0F;
        this.horseRightSaddleRope.field_78795_f = f14 / 3.0F;
        this.horseRightSaddleMetal.field_78795_f = f14 / 3.0F;
        this.horseLeftSaddleRope.field_78808_h = f14 / 5.0F;
        this.horseLeftSaddleMetal.field_78808_h = f14 / 5.0F;
        this.horseRightSaddleRope.field_78808_h = -f14 / 5.0F;
        this.horseRightSaddleMetal.field_78808_h = -f14 / 5.0F;
      }
    }

    f15 = -1.3089F + y * 1.5F;

    if (f15 > 0.0F)
    {
      f15 = 0.0F;
    }

    if (flag) {

      this.tailBase.field_78796_g = MathHelper.func_76134_b(f12 * 0.7F);
      f15 = 0.0F;
    }
    else {

      this.tailBase.field_78796_g = 0.0F;
    }

    this.tailMiddle.field_78796_g = this.tailBase.field_78796_g;
    this.tailTip.field_78796_g = this.tailBase.field_78796_g;
    this.tailMiddle.field_78797_d = this.tailBase.field_78797_d;
    this.tailTip.field_78797_d = this.tailBase.field_78797_d;
    this.tailMiddle.field_78798_e = this.tailBase.field_78798_e;
    this.tailTip.field_78798_e = this.tailBase.field_78798_e;
    this.tailBase.field_78795_f = f15;
    this.tailMiddle.field_78795_f = f15;
    this.tailTip.field_78795_f = -0.2618F + f15;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelHorseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */