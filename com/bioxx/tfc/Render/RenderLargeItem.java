package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Interfaces.IEquipable;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.opengl.GL11;











public class RenderLargeItem
{
  public void render(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    doRender(entity, item, e);
  }

  public void render(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    doRender(entity, item, e);
  }



  public void doRender(EntityLivingBase entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    if (e != null) {
      GL11.glPushMatrix();
      e.renderer.field_77109_a.field_78115_e.func_78794_c(0.0625F);
      GL11.glScalef(0.7F, 0.7F, 0.7F);
      GL11.glTranslatef(0.0F, 0.5F, 0.6F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
      TFC_Core.bindTexture(TextureMap.field_110575_b);
      RenderBlocks.getInstance().func_147800_a(Block.func_149634_a(item.func_77973_b()), item.func_77960_j(), 1.0F);
      GL11.glPopMatrix();
    } else {

      float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
      GL11.glPushMatrix();

      if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
      else
      { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
        GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
       GL11.glScalef(0.8F, 0.8F, 0.8F);
      GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

      if (item != null) {
        if (item.func_77973_b() instanceof IEquipable) {
          ((IEquipable)item.func_77973_b()).onEquippedRender();
        }
        else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
          ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
        }
        Block block = Block.func_149634_a(item.func_77973_b());
        TFC_Core.bindTexture(TextureMap.field_110575_b);
        RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
      }
      GL11.glPopMatrix();
    }
  }

  public void doRender(Entity entity, ItemStack item, RenderPlayerEvent.Specials.Pre e) {
    float entityTranslateY = (entity instanceof net.minecraft.entity.player.EntityPlayer) ? 0.0F : -1.5F;
    GL11.glPushMatrix();

    if (!entity.func_70093_af()) { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY + 0.0F, 0.5F); }
    else
    { GL11.glTranslatef(0.0F, 0.2F + entityTranslateY - 0.1F, 0.6F);
      GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F); }
     GL11.glScalef(0.8F, 0.8F, 0.8F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);

    if (item != null) {
      if (item.func_77973_b() instanceof IEquipable) {
        ((IEquipable)item.func_77973_b()).onEquippedRender();
      }
      else if (Block.func_149634_a(item.func_77973_b()) instanceof IEquipable) {
        ((IEquipable)Block.func_149634_a(item.func_77973_b())).onEquippedRender();
      }
      Block block = Block.func_149634_a(item.func_77973_b());
      TFC_Core.bindTexture(TextureMap.field_110575_b);
      RenderBlocks.getInstance().func_147800_a(block, item.func_77960_j(), 1.0F);
    }
    GL11.glPopMatrix();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderLargeItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */