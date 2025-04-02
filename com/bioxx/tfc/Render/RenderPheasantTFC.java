package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderPheasantTFC
  extends RenderChicken
{
  private static final ResourceLocation FEMALE_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/PheasantF.png");
  private static final ResourceLocation MALE_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/PheasantM.png");
  private static final ResourceLocation CHICK_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/pheasant_chick.png");


  public RenderPheasantTFC(ModelBase par1ModelBase, float par2) {
    super(par1ModelBase, par2);
  }









  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    this.field_76989_e = 0.15F + TFC_Core.getPercentGrown((IAnimal)par1Entity) * 0.15F;
    super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
  }


  protected ResourceLocation getTexture(IAnimal entity) {
    float percent = TFC_Core.getPercentGrown(entity);

    if (percent < 0.65F) {
      return CHICK_TEXTURE;
    }
    if (entity.getGender() == IAnimal.GenderEnum.MALE) {
      return MALE_TEXTURE;
    }

    return FEMALE_TEXTURE;
  }




  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
    float scale = ((EntityPheasantTFC)par1EntityLivingBase).getSizeMod() / 3.0F + 0.5F;
    GL11.glScalef(scale, scale, scale);
    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
  }



  protected ResourceLocation func_110775_a(Entity entity) {
    return getTexture((IAnimal)entity);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderPheasantTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */