package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Items.ItemQuiver;
import com.bioxx.tfc.Render.Models.ModelQuiver;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;






public class RenderQuiver
{
  private ModelQuiver quiver = new ModelQuiver();
  private static final ResourceLocation QUIVER_TEXTURE = new ResourceLocation("terrafirmacraft", "textures/models/armor/leatherquiver_1.png");





  public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    doRender(entity, item, e);
  }


  public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    if (e != null) {
      GL11.glPushMatrix();
      e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      GL11.glTranslatef(0.0F, 0.5F, 0.05F);
      TFC_Core.bindTexture(QUIVER_TEXTURE);
      this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8);
      GL11.glPopMatrix();
    } else {
      float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
      GL11.glPushMatrix();
      (Minecraft.func_71410_x()).field_71446_o.func_110577_a(QUIVER_TEXTURE);
      if (!entity.func_70093_af()) {
        GL11.glTranslatef(0.0F, entityTranslateY + 0.0F, 0.1F);
      } else {
        GL11.glTranslatef(0.0F, entityTranslateY + 0.1F, 0.1F);
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
      }
      if (item != null) {
        if (item.func_77973_b() instanceof IEquipable) {
          ((IEquipable)item.func_77973_b()).onEquippedRender();
        }
        if (entity instanceof com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC) { this.quiver.render(entity, 16); }
        else { this.quiver.render(entity, ((ItemQuiver)item.func_77973_b()).getQuiverArrows(item) / 8); }

      }  GL11.glPopMatrix();
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */