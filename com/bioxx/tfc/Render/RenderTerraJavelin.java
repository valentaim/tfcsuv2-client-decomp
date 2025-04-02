package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.EntityJavelin;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;





public class RenderTerraJavelin
  extends Render
{
  public void render(EntityJavelin entity, double x, double y, double z, float par8, float par9) {
    func_110777_b((Entity)entity);
    GL11.glPushMatrix();
    GL11.glTranslatef((float)x, (float)y, (float)z);
    GL11.glRotatef(entity.field_70126_B + (entity.field_70177_z - entity.field_70126_B) * par9 - 90.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(entity.field_70127_C + (entity.field_70125_A - entity.field_70127_C) * par9, 0.0F, 0.0F, 1.0F);
    Tessellator tessellator = Tessellator.field_78398_a;
    byte var11 = 0;
    float f2 = 0.0F;
    float f3 = 0.5F;
    float f4 = (0 + var11 * 10) / 32.0F;
    float f5 = (5 + var11 * 10) / 32.0F;




    float f10 = 0.05625F;

    GL11.glEnable(32826);
    float var21 = entity.field_70249_b - par9;

    if (var21 > 0.0F) {

      float var22 = -MathHelper.func_76126_a(var21 * 3.0F) * var21;
      GL11.glRotatef(var22, 0.0F, 0.0F, 1.0F);
    }

    GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
    GL11.glScalef(f10, f10, f10);
    GL11.glTranslatef(-4.0F, 0.0F, 0.0F);

    for (int i = 0; i < 4; i++) {

      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      GL11.glNormal3f(0.0F, 0.0F, f10);
      tessellator.func_78382_b();
      tessellator.func_78374_a(-28.0D, -2.0D, 0.0D, f2, f4);
      tessellator.func_78374_a(8.0D, -2.0D, 0.0D, f3, f4);
      tessellator.func_78374_a(8.0D, 2.0D, 0.0D, f3, f5);
      tessellator.func_78374_a(-28.0D, 2.0D, 0.0D, f2, f5);
      tessellator.func_78381_a();
    }

    GL11.glDisable(32826);
    GL11.glPopMatrix();
  }









  public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
    render((EntityJavelin)par1Entity, par2, par4, par6, par8, par9);
  }



  protected ResourceLocation func_110775_a(Entity entity) {
    return new ResourceLocation("terrafirmacraft", "textures/mob/javelin.png");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderTerraJavelin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */