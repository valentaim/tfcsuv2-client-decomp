package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;


@SideOnly(Side.CLIENT)
public class ModelCrossedSquares
  extends ModelBase
{
  public ModelRendererTFC renderer;

  public ModelCrossedSquares(int texOffx, int texOffy, int texWidth, int texHeight, int sqWidth, int sqHeight, int sqDepth) {
    this.renderer = new ModelRendererTFC(this, 0, 0);
    this.renderer.field_78799_b = texHeight;
    this.renderer.field_78801_a = texWidth;
    float x = 0.0F;
    float y = 0.0F;
    float z = 0.0F;

    int squareHeight = sqWidth;
    int squareWidth = sqWidth;
    int squareDepth = sqDepth;
    this.renderer.field_78804_l.add(new ModelCrossedSquare(this.renderer, this.renderer.field_78803_o, this.renderer.field_78813_p, x - ((sqWidth - 16) / 2), y, z - ((sqDepth - 16) / 2), squareWidth, squareHeight, squareDepth, 0.0F));
  }




  public void renderSquares() {
    this.renderer.func_78785_a(0.0625F);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelCrossedSquares.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */