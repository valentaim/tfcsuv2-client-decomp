package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelQuiver
  extends ModelBase
{
  private ModelRenderer quiver;
  private ModelRenderer[] arrows = new ModelRenderer[16];



  public ModelQuiver() {
    this.quiver = new ModelRenderer(this, 38, 0);
    this.quiver.func_78789_a(-2.5F, -6.0F, -1.5F, 5, 12, 3);
    this.quiver.func_78793_a(0.0F, 4.0F, 4.0F);
    this.quiver.field_78808_h = 3.926991F;
    this.quiver.func_78787_b(64, 32);

    for (int i = 0; i < this.arrows.length; i++) {

      this.arrows[i] = new ModelRenderer(this, 59, 0);
      this.arrows[i].func_78789_a(-1.0F, -8.0F, 0.0F, 2, 14, 0);
      this.arrows[i].func_78793_a(0.0F, 0.0F, 0.0F);
      this.arrows[i].func_78787_b(64, 32);
      (this.arrows[i]).field_78808_h = 3.1415927F + 0.08726646F * (i % 3) * ((i % 2 == 0) ? 1.0F : -1.0F);
      (this.arrows[i]).field_78795_f = 0.08726646F * (i % 3) * ((i % 2 == i % 3) ? 1.0F : -1.0F);
      this.quiver.func_78792_a(this.arrows[i]);
    }
  }










  protected void setRotationRadians(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }








  protected void setRotationDegrees(ModelRenderer model, float x, float y, float z) {
    setRotationRadians(model, (float)Math.toRadians(x), (float)Math.toRadians(y), (float)Math.toRadians(z));
  }
  public void render(EntityLivingBase theEntity, int numArrows) {
    int i;
    for (i = 0; i < numArrows; i++) {
      (this.arrows[i]).field_78807_k = false;
    }
    this.quiver.func_78785_a(0.0625F);
    for (i = 0; i < this.arrows.length; i++) {
      (this.arrows[i]).field_78807_k = true;
    }
  }


  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    if (entity instanceof net.minecraft.entity.player.EntityPlayer) this.quiver.func_78785_a(0.0625F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */