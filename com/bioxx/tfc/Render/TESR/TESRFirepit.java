package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEFirepit;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;











public class TESRFirepit
  extends TESRBase
{
  public void renderTileEntityFirepitAt(TEFirepit par1TileEntityPit, double d, double d1, double d2, float f) {
    if (par1TileEntityPit.func_145831_w() != null)
    {
      if (par1TileEntityPit.hasCookingPot) {



        TFC_Core.bindTexture(new ResourceLocation("terrafirmacraft", "textures/blocks/clay/Ceramic.png"));
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.0F, (float)d2 + 0.0F);


        GL11.glPopMatrix();
      }
    }
  }



  public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
    renderTileEntityFirepitAt((TEFirepit)par1TileEntity, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRFirepit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */