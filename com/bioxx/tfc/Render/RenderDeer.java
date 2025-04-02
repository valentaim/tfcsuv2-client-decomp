package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.Mobs.EntityDeer;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;


public class RenderDeer
  extends RenderLiving
{
  private static final ResourceLocation DEER_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/deer.png");
  private static final ResourceLocation FAWN_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/deer_fawn.png");




  
  public RenderDeer(ModelBase par1ModelBase, float par2) {
    super(par1ModelBase, par2);
  }


  
  public void renderDeer(EntityDeer par1EntityDeer, double par2, double par4, double par6, float par8, float par9) {
    super.func_76986_a((EntityLiving)par1EntityDeer, par2, par4, par6, par8, par9);
  }











  
  protected void func_77041_b(EntityLivingBase par1EntityLiving, float par2) {
    preRenderScale((EntityDeer)par1EntityLiving, par2);
  }


  
  protected void preRenderScale(EntityDeer par1EntityDeer, float par2) {
    GL11.glScalef(par1EntityDeer.getSizeMod() - 0.3F, par1EntityDeer.getSizeMod() - 0.3F, par1EntityDeer.getSizeMod() - 0.3F);
  }





  
  protected float func_77044_a(EntityLivingBase par1EntityLiving, float par2) {
    return 1.0F;
  }


  
  public void func_76986_a(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9) {
    renderDeer((EntityDeer)par1EntityLiving, par2, par4, par6, par8, par9);
  }








  
  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    renderDeer((EntityDeer)par1Entity, par2, par4, par6, par8, par9);
  }

  
  protected ResourceLocation getTexture(IAnimal entity) {
    if (!entity.isAdult())
    {
      return FAWN_TEXTURE;
    }

    
    return DEER_TEXTURE;
  }



  
  protected ResourceLocation func_110775_a(Entity entity) {
    return getTexture((IAnimal)entity);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderDeer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */