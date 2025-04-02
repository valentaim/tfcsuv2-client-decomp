package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEBarrel;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;



public class TESRBarrel
  extends TESRBase
{
  private static final IModelCustom model = AdvancedModelLoader.loadModel(new ResourceLocation("terrafirmacraft", "models/barrel.obj"));







  public void renderAt(TEBarrel te, double x, double y, double z, float f5) {
    if (te.func_145831_w() != null) {


      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y + 0.79F, (float)z);
      GL11.glScalef(1.0F, 1.0F, 1.0F);


      model.renderAll();

      if (te.fluid != null && TESRBase.renderBlocks.field_147840_d == null) {
        int color = te.fluid.getFluid().getColor(te.fluid);
        float f = (color >> 16 & 0xFF) / 255.0F;
        float f1 = (color >> 8 & 0xFF) / 255.0F;
        float f2 = (color & 0xFF) / 255.0F;
        float h = 0.75F * te.fluid.amount / 10000.0F;
        double size = 0.7D;
        GL11.glBegin(7);

        GL11.glColor3f(f, f1, f2);
        GL11.glVertex3d(-size / 2.0D, size / 2.0D, -size / 2.0D);
        GL11.glVertex3d(-size / 2.0D, size / 2.0D, size / 2.0D);
        GL11.glVertex3d(size / 2.0D, size / 2.0D, size / 2.0D);
        GL11.glVertex3d(size / 2.0D, size / 2.0D, -size / 2.0D);
        GL11.glEnd();
      }


      GL11.glPopMatrix();
    }
  }




  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderAt((TEBarrel)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRBarrel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */