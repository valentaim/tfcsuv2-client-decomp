package com.bioxx.tfc.Render;

import com.bioxx.tfc.Entities.EntityFallingBlockTFC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;


public class RenderFallingBlock
  extends Render
{
  private final RenderBlocks field_147920_a = new RenderBlocks();


  public RenderFallingBlock() {
    this.field_76989_e = 0.5F;
  }







  public void doRender(EntityFallingBlockTFC entity, double x, double y, double z, float f, float f1) {
    World world = entity.getWorld();
    Block block = entity.getBlock();
    int i = MathHelper.func_76128_c(entity.field_70165_t);
    int j = MathHelper.func_76128_c(entity.field_70163_u);
    int k = MathHelper.func_76128_c(entity.field_70161_v);

    if (block != null && block != world.func_147439_a(i, j, k)) {

      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y, (float)z);
      func_110777_b((Entity)entity);
      GL11.glDisable(2896);

      this.field_147920_a.func_147775_a(block);
      this.field_147920_a.func_147749_a(block, world, i, j, k, entity.blockMeta);

      GL11.glEnable(2896);
      GL11.glPopMatrix();
    }
  }





  protected ResourceLocation getEntityTexture(EntityFallingBlockTFC entity) {
    return TextureMap.field_110575_b;
  }






  protected ResourceLocation func_110775_a(Entity entity) {
    return getEntityTexture((EntityFallingBlockTFC)entity);
  }









  public void func_76986_a(Entity entity, double x, double y, double z, float f, float f1) {
    doRender((EntityFallingBlockTFC)entity, x, y, z, f, f1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */