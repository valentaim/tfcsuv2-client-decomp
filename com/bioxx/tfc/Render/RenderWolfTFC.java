package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;



public class RenderWolfTFC
  extends RenderWolf
{
  public RenderWolfTFC(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
    super(par1ModelBase, par2ModelBase, par3);
    func_77042_a(par2ModelBase);
  }









  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    this.field_76989_e = 0.35F + TFC_Core.getPercentGrown((IAnimal)par1Entity) * 0.35F;
    super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
  }



  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
    float scale = ((EntityWolfTFC)par1EntityLivingBase).getSizeMod() / 2.0F + 0.5F;
    GL11.glScalef(scale, scale, scale);
  }





  protected float handleRotationFloat(EntityWolfTFC wolf, float par2) {
    return wolf.func_70920_v();
  }






  protected float func_77044_a(EntityLivingBase entity, float par2) {
    return handleRotationFloat((EntityWolfTFC)entity, par2);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderWolfTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */