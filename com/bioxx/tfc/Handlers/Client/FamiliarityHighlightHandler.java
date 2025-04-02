package com.bioxx.tfc.Handlers.Client;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import org.lwjgl.opengl.GL11;



public class FamiliarityHighlightHandler
{
  @SubscribeEvent
  public void renderLivingEvent(RenderLivingEvent.Specials.Post evt) {
    if (RenderManager.field_78727_a.field_78734_h instanceof EntityPlayer) {
      EntityLivingBase entity = evt.entity;
      EntityPlayer player = (EntityPlayer)RenderManager.field_78727_a.field_78734_h;

      double x = evt.x;
      double y = evt.y;
      double z = evt.z;
      if (entity instanceof IAnimal && entity == (Minecraft.func_71410_x()).field_147125_j) {

        IAnimal animal = (IAnimal)entity;
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        double d3 = entity.func_70068_e((Entity)RenderManager.field_78727_a.field_78734_h);
        float f2 = 8.0F;

        if (d3 < (f2 * f2))
        {

          if (player.func_70093_af()) {

            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.0F, (float)y + entity.field_70131_O + 0.75F, (float)z);
            GL11.glRotatef(-RenderManager.field_78727_a.field_78735_i, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(RenderManager.field_78727_a.field_78732_j, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(2896);
            GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            TFC_Core.bindTexture(RenderOverlayHandler.tfcicons);
            float maxFam = 100.0F;
            float percentFam = Math.min(animal.getFamiliarity() / maxFam, 1.0F);
            GL11.glScalef(0.33F, 0.33F, 0.33F);
            if (percentFam >= 0.3D) {
              drawTexturedModalRect(-8, 0, 112, 40, 16, 16);
            }
            else {

              drawTexturedModalRect(-8, 0, 92, 40, 16, 16);
            }
            GL11.glTranslatef(0.0F, 0.0F, -0.001F);
            if (percentFam == 1.0F || !animal.canFamiliarize()) {

              drawTexturedModalRect(-6, 14 - (int)(12.0F * percentFam), 114, 74 - (int)(12.0F * percentFam), 12, (int)(12.0F * percentFam));
            }
            else {

              drawTexturedModalRect(-6, 14 - (int)(12.0F * percentFam), 94, 74 - (int)(12.0F * percentFam), 12, (int)(12.0F * percentFam));
            }

            GL11.glDepthMask(true);
            GL11.glEnable(2896);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glPopMatrix();
          }
        }
      }
    }
  }


  public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
    float f = 0.00390625F;
    float f1 = 0.00390625F;
    Tessellator tessellator = Tessellator.field_78398_a;
    tessellator.func_78382_b();
    tessellator.func_78374_a((par1 + 0), (par2 + par6), 0.0D, ((par3 + 0) * f), ((par4 + par6) * f1));
    tessellator.func_78374_a((par1 + par5), (par2 + par6), 0.0D, ((par3 + par5) * f), ((par4 + par6) * f1));
    tessellator.func_78374_a((par1 + par5), (par2 + 0), 0.0D, ((par3 + par5) * f), ((par4 + 0) * f1));
    tessellator.func_78374_a((par1 + 0), (par2 + 0), 0.0D, ((par3 + 0) * f), ((par4 + 0) * f1));
    tessellator.func_78381_a();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\FamiliarityHighlightHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */