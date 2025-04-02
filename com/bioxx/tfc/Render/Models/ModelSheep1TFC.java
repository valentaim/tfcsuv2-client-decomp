package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Entities.IAnimal;
import net.minecraft.client.model.ModelSheep1;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;



































































































































































































public class ModelSheep1TFC
  extends ModelSheep1
{
  public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
    func_78087_a(par2, par3, par4, par5, par6, par7, entity);

    float percent = TFC_Core.getPercentGrown((IAnimal)entity);
    float ageScale = 2.0F - percent;
    float ageHeadScale = (float)Math.pow((1.0F / ageScale), 0.66D);






    GL11.glPushMatrix();

    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(ageHeadScale, ageHeadScale, ageHeadScale);
    GL11.glTranslatef(0.0F, (ageScale - 1.0F) * -0.125F, 0.1875F - 0.1875F * percent);


    this.field_78150_a.func_78785_a(par7);

    GL11.glPopMatrix();
    GL11.glPushMatrix();
    GL11.glTranslatef(0.0F, 0.75F - 0.75F * percent, 0.0F);
    GL11.glScalef(1.0F / ageScale, 1.0F / ageScale, 1.0F / ageScale);
    this.field_78148_b.func_78785_a(par7);
    this.field_78149_c.func_78785_a(par7);
    this.field_78146_d.func_78785_a(par7);
    this.field_78147_e.func_78785_a(par7);
    this.field_78144_f.func_78785_a(par7);
    GL11.glPopMatrix();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelSheep1TFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */