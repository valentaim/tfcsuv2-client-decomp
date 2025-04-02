package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Render.Models.ModelLoom;
import com.bioxx.tfc.TileEntities.TELoom;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;






public class TESRLoom
  extends TESRBase
{
  public void renderTileEntityLoomAt(TELoom te, double d, double d1, double d2, float f) {
    if (te.func_145831_w() != null) {

      int stringCount = te.getStringCount();
      int reqStringCount = te.getRequiredStringCount();

      if (te.getModel() == null)
      {
        te.setModel(new ModelLoom());
      }
      TFC_Core.bindTexture(te.getWoodResource());
      GL11.glPushMatrix();
      GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.0F, (float)d2 + 0.0F);
      if (stringCount < reqStringCount)
      {
        te.getModel().updateCloth(te.getCloth());
      }
      if (te.getIsWeaving() || (te.getModel()).stillWeaving) {

        if (TFC_Time.getTotalTicks() > (te.getModel()).tempTime) {

          (te.getModel()).tempNum += (int)(TFC_Time.getTotalTicks() - (te.getModel()).tempTime);
          (te.getModel()).tempTime = TFC_Time.getTotalTicks();
          if ((te.getModel()).tempNum >= (te.getModel()).lastClothIncrease + (te.getModel()).mod / 2) {

            (te.getModel()).clothIncrease = true;
            (te.getModel()).lastClothIncrease = ((te.getModel()).lastClothIncrease + (te.getModel()).mod / 2) % (te.getModel()).mod;

            te.finishWeaveCycle();
            (te.getModel()).stillWeaving = te.getIsWeaving();
          }
          (te.getModel()).tempNum %= (te.getModel()).mod;
        }
      } else {

        (te.getModel()).tempTime = TFC_Time.getTotalTicks();
      }

      if (te.func_70301_a(1) != null) {
        te.getModel().render(reqStringCount, reqStringCount, (te.getModel()).tempNum, false, (te.getModel()).mod, te.getStringResource(), false, false, te);
      } else {
        te.getModel().render(stringCount, reqStringCount, (te.getModel()).tempNum, (te.getModel()).clothIncrease, (te.getModel()).mod, te.getStringResource(), te.getIsWeaving(), (te.getModel()).stillWeaving, te);
      }
      (te.getModel()).clothIncrease = false;
      GL11.glPopMatrix();
    }
  }



  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderTileEntityLoomAt((TELoom)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */