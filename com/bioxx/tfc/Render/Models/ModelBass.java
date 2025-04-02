package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Time;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;









public class ModelBass
  extends ModelBase
{
  private ModelRenderer head;
  private ModelRenderer mouth;
  private ModelRenderer gils;
  private ModelRenderer neck;
  private ModelRenderer body;
  private ModelRenderer back;
  private ModelRenderer rear;
  private ModelRenderer tail;
  private ModelRenderer tailEnd;
  private ModelRenderer tailFin;
  private ModelRenderer dorsalFin;
  private ModelRenderer analFin;
  private ModelRenderer pelvicFinBox;
  private ModelRenderer pectoralFinBox;
  private long n;
  private float rotateMouth;
  private float rotateSwim;

  public ModelBass() {
    this.field_78090_t = 128;
    this.field_78089_u = 64;

    this.head = new ModelRenderer(this, 0, 0);
    this.head.func_78789_a(-2.5F, 0.0F, 0.0F, 5, 5, 4);
    this.head.func_78793_a(0.0F, 0.0F, 0.0F);
    this.head.func_78787_b(128, 64);
    this.head.field_78809_i = true;
    setRotation(this.head, 0.5235988F, 0.0F, 0.0F);
    this.mouth = new ModelRenderer(this, 24, 0);
    this.mouth.func_78789_a(-2.0F, -1.0F, -5.0F, 4, 1, 6);
    this.mouth.func_78793_a(0.0F, 4.0F, 4.0F);
    this.mouth.func_78787_b(128, 64);
    this.mouth.field_78809_i = true;
    setRotation(this.mouth, -0.2617994F, 0.0F, 0.0F);
    this.gils = new ModelRenderer(this, 64, 0);
    this.gils.func_78789_a(-3.0F, 2.0F, 2.0F, 6, 4, 5);
    this.gils.func_78793_a(0.0F, 0.0F, 0.0F);
    this.gils.func_78787_b(128, 64);
    this.gils.field_78809_i = true;
    setRotation(this.gils, 0.5235988F, 0.0F, 0.0F);
    this.neck = new ModelRenderer(this, 44, 0);
    this.neck.func_78789_a(-2.0F, -1.2F, 3.5F, 4, 2, 6);
    this.neck.func_78793_a(0.0F, 0.0F, 0.0F);
    this.neck.func_78787_b(128, 64);
    this.neck.field_78809_i = true;
    setRotation(this.neck, 0.1745329F, 0.0F, 0.0F);
    this.body = new ModelRenderer(this, 0, 17);
    this.body.func_78789_a(-2.5F, -1.5F, 5.0F, 5, 6, 10);
    this.body.func_78793_a(0.0F, 0.0F, 0.0F);
    this.body.func_78787_b(128, 64);
    this.body.field_78809_i = true;
    setRotation(this.body, 0.0F, 0.0F, 0.0F);
    this.back = new ModelRenderer(this, 30, 17);
    this.back.func_78789_a(-2.0F, -3.3F, 9.0F, 4, 3, 7);
    this.back.func_78793_a(0.0F, 0.0F, 0.0F);
    this.back.func_78787_b(128, 64);
    this.back.field_78809_i = true;
    setRotation(this.back, -0.0523599F, 0.0F, 0.0F);
    this.rear = new ModelRenderer(this, 0, 33);
    this.rear.func_78789_a(-2.0F, 5.4F, 12.9F, 4, 3, 4);
    this.rear.func_78793_a(0.0F, 0.0F, 0.0F);
    this.rear.func_78787_b(128, 64);
    this.rear.field_78809_i = true;
    setRotation(this.rear, 0.2792527F, 0.0F, 0.0F);
    this.tail = new ModelRenderer(this, 16, 33);
    this.tail.func_78789_a(-1.5F, -11.0F, 12.3F, 3, 4, 5);
    this.tail.func_78793_a(0.0F, 1.0F, 0.0F);
    this.tail.func_78787_b(128, 64);
    this.tail.field_78809_i = true;
    setRotation(this.tail, -0.5235988F, 0.0F, 0.0F);
    this.tailEnd = new ModelRenderer(this, 32, 33);
    this.tailEnd.func_78789_a(-1.0F, -0.5F, 0.0F, 2, 3, 5);
    this.tailEnd.func_78793_a(0.0F, 0.0F, 19.0F);
    this.tailEnd.func_78787_b(128, 64);
    this.tailEnd.field_78809_i = true;
    setRotation(this.tailEnd, 0.0F, 0.0F, 0.0F);
    this.tailFin = new ModelRenderer(this, 0, 51);
    this.tailFin.func_78789_a(0.0F, -2.5F, 0.0F, 0, 7, 6);
    this.tailFin.func_78793_a(0.0F, 0.0F, 4.0F);
    this.tailFin.func_78787_b(128, 64);
    this.tailFin.field_78809_i = true;
    setRotation(this.tailFin, 0.0F, 0.0F, 0.0F);
    this.tailEnd.func_78792_a(this.tailFin);
    this.dorsalFin = new ModelRenderer(this, 12, 47);
    this.dorsalFin.func_78789_a(0.0F, -8.5F, 8.0F, 0, 5, 12);
    this.dorsalFin.func_78793_a(0.0F, 0.0F, 0.0F);
    this.dorsalFin.func_78787_b(128, 64);
    this.dorsalFin.field_78809_i = true;
    setRotation(this.dorsalFin, -0.0872665F, 0.0F, 0.0F);
    this.analFin = new ModelRenderer(this, 36, 55);
    this.analFin.func_78789_a(0.0F, 10.0F, 12.0F, 0, 4, 5);
    this.analFin.func_78793_a(0.0F, 0.0F, 0.0F);
    this.analFin.func_78787_b(128, 64);
    this.analFin.field_78809_i = true;
    setRotation(this.analFin, 0.4363323F, 0.0F, 0.0F);
    this.pelvicFinBox = new ModelRenderer(this, 53, 33);
    this.pelvicFinBox.func_78789_a(-1.5F, -1.0F, 8.0F, 3, 2, 4);
    this.pelvicFinBox.func_78793_a(0.0F, 0.0F, 0.0F);
    this.pelvicFinBox.func_78787_b(128, 64);
    this.pelvicFinBox.field_78809_i = true;
    setRotation(this.pelvicFinBox, -0.5235988F, 0.0F, 0.0F);
    this.pectoralFinBox = new ModelRenderer(this, 67, 33);
    this.pectoralFinBox.func_78789_a(-3.0F, 4.4F, 6.7F, 6, 2, 4);
    this.pectoralFinBox.func_78793_a(0.0F, 0.0F, 0.0F);
    this.pectoralFinBox.func_78787_b(128, 64);
    this.pectoralFinBox.field_78809_i = true;
    setRotation(this.pectoralFinBox, 0.2617994F, 0.0F, 0.0F);
  }



  public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
    super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    func_78087_a(f, f1, f2, f3, f4, f5, entity);
    this.head.func_78785_a(f5);
    this.mouth.func_78785_a(f5);
    this.gils.func_78785_a(f5);
    this.neck.func_78785_a(f5);
    this.body.func_78785_a(f5);
    this.back.func_78785_a(f5);
    this.rear.func_78785_a(f5);
    this.tail.func_78785_a(f5);
    this.tailEnd.func_78785_a(f5);

    this.dorsalFin.func_78785_a(f5);
    this.analFin.func_78785_a(f5);
    this.pelvicFinBox.func_78785_a(f5);
    this.pectoralFinBox.func_78785_a(f5);
  }


  private void setRotation(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }



  public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
    super.func_78087_a(f, f1, f2, f3, f4, f5, entity);

    this.n = TFC_Time.getTotalTicks() % 30L;
    this.rotateMouth = (float)(this.n * (this.n - 30L)) * 0.0022F;
    this.rotateSwim = (float)(this.n * (this.n - 30L)) * -0.0044F;

    setRotation(this.mouth, -0.2617994F + -0.2617994F * this.rotateMouth, 0.0F, 0.0F);

    if (entity.func_70090_H() || entity.field_70160_al) {

      setRotation(this.tailEnd, 0.0F, -0.2617994F + 0.2617994F * this.rotateSwim * (entity.field_70160_al ? 4 : 2), 0.0F);
      setRotation(this.tailFin, 0.0F, -0.2617994F + 0.2617994F * this.rotateSwim * (entity.field_70160_al ? 4 : 2), 0.0F);
    }
    else {

      setRotation(this.tailEnd, 0.0F, 0.0F, 0.0F);
      setRotation(this.tailFin, 0.0F, 0.0F, 0.0F);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelBass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */