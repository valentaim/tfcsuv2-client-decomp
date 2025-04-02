package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEGrill;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;












public class TESRGrill
  extends TESRBase
{
  public void renderAt(TEGrill te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {

      EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
      customitem.field_70290_d = 0.0F;
      float blockScale = 0.6F;
      float timeD = (float)(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);

      if (RenderManager.field_78727_a.field_78733_k.field_74347_j) {

        if (te.func_70301_a(0) != null) {

          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.25F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(0));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
        if (te.func_70301_a(1) != null) {

          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.25F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(1));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
        if (te.func_70301_a(2) != null) {

          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.5F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(2));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
        if (te.func_70301_a(3) != null) {

          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.5F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(3));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
        if (te.func_70301_a(4) != null) {

          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.25F, (float)d1 + 0.0F, (float)d2 + 0.75F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(4));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
        if (te.func_70301_a(5) != null)
        {
          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + 0.75F, (float)d1 + 0.0F, (float)d2 + 0.75F);
          GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(5));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }

      }
      else {

        GL11.glPushMatrix();
        GL11.glTranslated(d, d1 + 0.001D, d2);
        drawItem(te, 0, 0.05D, 0.35D, 0.05D, 0.35D);
        drawItem(te, 1, 0.65D, 0.95D, 0.05D, 0.35D);
        drawItem(te, 2, 0.05D, 0.35D, 0.35D, 0.65D);
        drawItem(te, 3, 0.65D, 0.95D, 0.35D, 0.65D);
        drawItem(te, 4, 0.05D, 0.35D, 0.65D, 0.95D);
        drawItem(te, 5, 0.65D, 0.95D, 0.65D, 0.95D);
        GL11.glPopMatrix();
      }
    }
  }


  public void drawItem(TEGrill te, int index, double minX, double maxX, double minZ, double maxZ) {
    if (te.storage[index] != null) {

      float minU = te.storage[index].func_77954_c().func_94209_e();
      float maxU = te.storage[index].func_77954_c().func_94212_f();
      float minV = te.storage[index].func_77954_c().func_94206_g();
      float maxV = te.storage[index].func_77954_c().func_94210_h();
      Tessellator tessellator = Tessellator.field_78398_a;
      tessellator.func_78382_b();
      tessellator.func_78375_b(0.0F, 1.0F, 0.0F);
      tessellator.func_78374_a(minX, 0.0D, maxZ, minU, maxV);
      tessellator.func_78374_a(maxX, 0.0D, maxZ, maxU, maxV);
      tessellator.func_78374_a(maxX, 0.0D, minZ, maxU, minV);
      tessellator.func_78374_a(minX, 0.0D, minZ, minU, minV);
      tessellator.func_78381_a();
    }
  }



  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderAt((TEGrill)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRGrill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */