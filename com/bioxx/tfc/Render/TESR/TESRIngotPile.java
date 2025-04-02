package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.Blocks.BlockIngotPile;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Render.Models.ModelIngotPile;
import com.bioxx.tfc.TileEntities.TEIngotPile;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;




public class TESRIngotPile
  extends TESRBase
{
  private final ModelIngotPile ingotModel = new ModelIngotPile();


  public void renderTileEntityIngotPileAt(TEIngotPile tep, double d, double d1, double d2, float f) {
    Block block = tep.func_145838_q();
    if (tep.func_145831_w() != null && tep.func_70301_a(0) != null && block == TFCBlocks.ingotPile) {

      int i = ((BlockIngotPile)block).getStack(tep.func_145831_w(), tep);
      TFC_Core.bindTexture(new ResourceLocation("terrafirmacraft", "textures/blocks/metal/" + tep.type + ".png"));

      GL11.glPushMatrix();
      GL11.glTranslatef((float)d + 0.0F, (float)d1 + 0.0F, (float)d2 + 0.0F);
      this.ingotModel.renderIngots(i);
      GL11.glPopMatrix();
    }
  }



  public void func_147500_a(TileEntity te, double par2, double par4, double par6, float par8) {
    renderTileEntityIngotPileAt((TEIngotPile)te, par2, par4, par6, par8);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRIngotPile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */