package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEPottery;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;













public class TESRPottery
  extends TESRBase
{
  public void renderAt(TEPottery te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {

      EntityItem customitem = new EntityItem(this.field_147501_a.field_147550_f);
      customitem.field_70290_d = 0.0F;
      float blockScale = 1.0F;
      float timeD = (float)(360.0D * (System.currentTimeMillis() & 0x3FFFL) / 16383.0D);
      for (int i = 0; i < 4; i++) {

        float offsetX = 0.25F;
        float offsetY = 0.1F;
        float offsetZ = 0.25F;
        if (i == 1) {

          offsetX = 0.75F;
          offsetZ = 0.25F;
        }
        else if (i == 2) {

          offsetX = 0.25F;
          offsetZ = 0.75F;
        }
        else if (i == 3) {

          offsetX = 0.75F;
          offsetZ = 0.75F;
        }

        if (te.func_70301_a(i) != null) {

          if (te.func_70301_a(i).func_77973_b() instanceof net.minecraft.item.ItemBlock) {

            blockScale = 2.0F; offsetX = 0.5F; offsetZ = 0.5F;
          }
          GL11.glPushMatrix();
          GL11.glTranslatef((float)d + offsetX, (float)d1 + offsetY, (float)d2 + offsetZ);
          if (RenderManager.field_78727_a.field_78733_k.field_74347_j)
          {
            GL11.glRotatef(timeD, 0.0F, 1.0F, 0.0F);
          }
          GL11.glScalef(blockScale, blockScale, blockScale);
          customitem.func_92058_a(te.func_70301_a(i));
          itemRenderer.func_76986_a(customitem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
          GL11.glPopMatrix();
        }
      }
    }
  }





  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderAt((TEPottery)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRPottery.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */