package com.bioxx.tfc.Render;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Entities.EntityBarrel;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;




public class RenderBarrelEntity
  extends Render
{
  public void func_76986_a(Entity e, double x, double y, double z, float par8, float par9) {
    EntityBarrel entity = (EntityBarrel)e;
    GL11.glPushMatrix();
    GL11.glTranslated(x + 0.5D, y, z + 0.5D);
    Block block = TFCBlocks.barrel;
    TFC_Core.bindTexture(TextureMap.field_110575_b);
    RenderBlocks.getInstance().func_147800_a(block, entity.getBarrelType(), 1.0F);

    GL11.glPopMatrix();
  }


  protected ResourceLocation func_110775_a(Entity entity) {
    return null;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderBarrelEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */