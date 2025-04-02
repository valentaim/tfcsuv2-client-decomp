package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TESmokeRack;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;












public class TESRSmokeRack
  extends TESRBase
{
  public void renderAt(TESmokeRack te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {

      EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
      customitem.field_70290_d = 0.0F;
      float blockScale = 1.0F;
      int meta = te.func_145831_w().func_72805_g(te.field_145851_c, te.field_145848_d, te.field_145849_e);
      float offsetX = 0.0F;
      float offsetZ = 0.0F;
      if ((meta & 0x1) == 0) {
        offsetZ = 0.25F;
      } else {
        offsetX = 0.25F;
      }  if (te.func_70301_a(0) != null) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F - offsetX, (float)d1 + 0.0F, (float)d2 + 0.5F - offsetZ);

        if ((meta & 0x1) == 0) {
          GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        } else {
          GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        }
        GL11.glScalef(blockScale, blockScale, blockScale);
        customitem.func_92058_a(te.func_70301_a(0));
        itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();
      }
      if (te.func_70301_a(1) != null) {

        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.5F + offsetX, (float)d1 + 0.0F, (float)d2 + 0.5F + offsetZ);

        if ((meta & 0x1) == 0) {
          GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
        } else {
          GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
        }
        GL11.glScalef(blockScale, blockScale, blockScale);
        customitem.func_92058_a(te.func_70301_a(1));
        itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
        GL11.glPopMatrix();
      }
    }
  }


  public void drawItem(TESmokeRack te, int index, double minX, double maxX, double minZ, double maxZ) {
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
    renderAt((TESmokeRack)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRSmokeRack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */