package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;


















public class ModelWolfTFC
  extends ModelBase
{
  public ModelRenderer wolfHeadMain;
  public ModelRenderer wolfBody;
  public ModelRenderer wolfLeg1;
  public ModelRenderer wolfLeg2;
  public ModelRenderer wolfLeg3;
  public ModelRenderer wolfLeg4;
  private ModelRenderer wolfTail;
  private ModelRenderer wolfMane;

  public ModelWolfTFC() {
    float f = 0.0F;
    float f1 = 13.5F;
    this.wolfHeadMain = new ModelRenderer(this, 0, 0);
    this.wolfHeadMain.func_78790_a(-3.0F, -3.0F, -2.0F, 6, 6, 4, f);
    this.wolfHeadMain.func_78793_a(-1.0F, f1, -7.0F);
    this.wolfBody = new ModelRenderer(this, 18, 14);
    this.wolfBody.func_78790_a(-4.0F, -2.0F, -3.0F, 6, 9, 6, f);
    this.wolfBody.func_78793_a(0.0F, 14.0F, 2.0F);
    this.wolfMane = new ModelRenderer(this, 21, 0);
    this.wolfMane.func_78790_a(-4.0F, -3.0F, -3.0F, 8, 6, 7, f);
    this.wolfMane.func_78793_a(-1.0F, 14.0F, 2.0F);
    this.wolfLeg1 = new ModelRenderer(this, 0, 18);
    this.wolfLeg1.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
    this.wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
    this.wolfLeg2 = new ModelRenderer(this, 0, 18);
    this.wolfLeg2.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
    this.wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
    this.wolfLeg3 = new ModelRenderer(this, 0, 18);
    this.wolfLeg3.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
    this.wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
    this.wolfLeg4 = new ModelRenderer(this, 0, 18);
    this.wolfLeg4.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
    this.wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
    this.wolfTail = new ModelRenderer(this, 9, 18);
    this.wolfTail.func_78790_a(-1.0F, 0.0F, -1.0F, 2, 8, 2, f);
    this.wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
    this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(-3.0F, -5.0F, 0.0F, 2, 2, 1, f);
    this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(1.0F, -5.0F, 0.0F, 2, 2, 1, f);
    this.wolfHeadMain.func_78784_a(0, 10).func_78790_a(-1.5F, 0.0F, -5.0F, 3, 3, 4, f);
  }






  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    super.func_78088_a(entity, par2, par3, par4, par5, par6, par7);
    func_78087_a(par2, par3, par4, par5, par6, par7, entity);

    float percent = TFC_Core.getPercentGrown((IAnimal)entity);
    float ageScale = 2.0F - percent;


    if (entity instanceof IAnimal) {

      GL11.glPushMatrix();
      GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
      GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
      this.wolfHeadMain.func_78791_b(par7);
      this.wolfBody.func_78785_a(par7);
      this.wolfLeg1.func_78785_a(par7);
      this.wolfLeg2.func_78785_a(par7);
      this.wolfLeg3.func_78785_a(par7);
      this.wolfLeg4.func_78785_a(par7);
      this.wolfTail.func_78791_b(par7);
      this.wolfMane.func_78785_a(par7);
      GL11.glPopMatrix();
    }
  }



  public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
    EntityWolfTFC entitywolf = (EntityWolfTFC)par1EntityLivingBase;

    if (entitywolf.func_70919_bu()) {

      this.wolfTail.field_78796_g = 0.0F;
    }
    else {

      this.wolfTail.field_78796_g = MathHelper.func_76134_b(par2 * 0.6662F) * 1.4F * par3;
    }
    if (entitywolf.func_70906_o()) {

      this.wolfMane.func_78793_a(-1.0F, 16.0F, -3.0F);
      this.wolfMane.field_78795_f = 1.2566371F;
      this.wolfMane.field_78796_g = 0.0F;
      this.wolfBody.func_78793_a(0.0F, 18.0F, 0.0F);
      this.wolfBody.field_78795_f = 0.7853982F;
      this.wolfTail.func_78793_a(-1.0F, 21.0F, 6.0F);
      this.wolfLeg1.func_78793_a(-2.5F, 22.0F, 2.0F);
      this.wolfLeg1.field_78795_f = 4.712389F;
      this.wolfLeg2.func_78793_a(0.5F, 22.0F, 2.0F);
      this.wolfLeg2.field_78795_f = 4.712389F;
      this.wolfLeg3.field_78795_f = 5.811947F;
      this.wolfLeg3.func_78793_a(-2.49F, 17.0F, -4.0F);
      this.wolfLeg4.field_78795_f = 5.811947F;
      this.wolfLeg4.func_78793_a(0.51F, 17.0F, -4.0F);
    }
    else {

      this.wolfBody.func_78793_a(0.0F, 14.0F, 2.0F);
      this.wolfBody.field_78795_f = 1.5707964F;
      this.wolfMane.func_78793_a(-1.0F, 14.0F, -3.0F);
      this.wolfMane.field_78795_f = this.wolfBody.field_78795_f;
      this.wolfTail.func_78793_a(-1.0F, 12.0F, 8.0F);
      this.wolfLeg1.func_78793_a(-2.5F, 16.0F, 7.0F);
      this.wolfLeg2.func_78793_a(0.5F, 16.0F, 7.0F);
      this.wolfLeg3.func_78793_a(-2.5F, 16.0F, -4.0F);
      this.wolfLeg4.func_78793_a(0.5F, 16.0F, -4.0F);
      this.wolfLeg1.field_78795_f = MathHelper.func_76134_b(par2 * 0.6662F) * 1.4F * par3;
      this.wolfLeg2.field_78795_f = MathHelper.func_76134_b(par2 * 0.6662F + 3.1415927F) * 1.4F * par3;
      this.wolfLeg3.field_78795_f = MathHelper.func_76134_b(par2 * 0.6662F + 3.1415927F) * 1.4F * par3;
      this.wolfLeg4.field_78795_f = MathHelper.func_76134_b(par2 * 0.6662F) * 1.4F * par3;
    }

    this.wolfHeadMain.field_78808_h = entitywolf.func_70917_k(par4) + entitywolf.func_70923_f(par4, 0.0F);
    this.wolfMane.field_78808_h = entitywolf.func_70923_f(par4, -0.08F);
    this.wolfBody.field_78808_h = entitywolf.func_70923_f(par4, -0.16F);
    this.wolfTail.field_78808_h = entitywolf.func_70923_f(par4, -0.2F);
    this.wolfTail.field_78796_g = 0.5F * (1.0F - 1.0F / (entitywolf.getHappyTicks() + 1.0F)) * MathHelper.func_76126_a((float)(Math.PI * entitywolf.getHappyTicks() / 5.0D));
  }



  public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
    super.func_78087_a(par1, par2, par3, par4, par5, par6, par7Entity);
    this.wolfHeadMain.field_78795_f = par5 / 57.295776F;
    this.wolfHeadMain.field_78796_g = par4 / 57.295776F;
    if (par7Entity instanceof EntityWolfTFC && ((EntityWolfTFC)par7Entity).getHappyTicks() > 0) {
      this.wolfTail.field_78795_f = 1.9634955F;
    } else {
      this.wolfTail.field_78795_f = par3;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelWolfTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */