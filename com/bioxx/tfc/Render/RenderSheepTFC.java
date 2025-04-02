package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.Mobs.EntitySheepTFC;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderSheep;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class RenderSheepTFC
  extends RenderSheep
{
  private static final ResourceLocation SHEEP_FUR_TEXTURE = new ResourceLocation("textures/entity/sheep/sheep_fur.png");
  private static final ResourceLocation SHEEP_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/mob/sheep.png");


  public RenderSheepTFC(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3) {
    super(par1ModelBase, par2ModelBase, par3);
    func_77042_a(par2ModelBase);
  }


  protected int setWoolColorAndRender(EntitySheepTFC par1EntitySheep, int par2, float par3) {
    if (par2 == 0 && !par1EntitySheep.func_70892_o()) {

      func_110776_a(SHEEP_FUR_TEXTURE);
      float var4 = 1.0F;
      int var5 = par1EntitySheep.func_70896_n();
      GL11.glColor3f(var4 * EntitySheepTFC.FLEECE_COLOR_TABLE[var5][0], var4 * EntitySheepTFC.FLEECE_COLOR_TABLE[var5][1], var4 * EntitySheepTFC.FLEECE_COLOR_TABLE[var5][2]);
      return 1;
    }


    return -1;
  }



  protected ResourceLocation getTexture(EntitySheep par1EntitySheep) {
    return SHEEP_TEXTURE;
  }



  protected void func_77041_b(EntityLivingBase par1EntityLivingBase, float par2) {
    float scale = ((EntitySheepTFC)par1EntityLivingBase).getSizeMod() / 2.0F + 0.5F;
    GL11.glScalef(scale, scale, scale);
  }



  protected ResourceLocation func_110775_a(Entity par1Entity) {
    return getTexture((EntitySheep)par1Entity);
  }



  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    this.field_76989_e = 0.35F + TFC_Core.getPercentGrown((IAnimal)par1Entity) * 0.35F;
    super.func_76986_a(par1Entity, par2, par4, par6, par8, par9);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderSheepTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */