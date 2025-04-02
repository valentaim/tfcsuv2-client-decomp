package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;








public class ModelUrn
  extends ModelBox
{
  private final TexturedQuad[] quadList;

  public ModelUrn(ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale) {
    super(renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale);


    this.quadList = new TexturedQuad[17];
    float maxX = originX + width;
    float maxY = originY + height;
    float maxZ = originZ + depth;

    float height2 = height * 1.5F;
    originY += 0.01F;
    originX -= width;
    originZ -= depth;

    originX -= scale;
    originY -= scale;
    originZ -= scale;

    maxY = originY + height2 + height2;

    maxX += scale;
    maxY += scale;
    maxZ += scale;

    float innerXO = originX + height / 4.0F;
    float innerZO = originZ + height / 4.0F;
    float innerXM = maxX - height / 4.0F;
    float innerZM = maxZ - height / 4.0F;
    float midY1 = originY + height2;
    float midY2 = midY1 + height2 / 2.0F;
    float midY3 = maxY - height2 / 4.0F;

    PositionTextureVertex vert0 = new PositionTextureVertex(innerXO, originY, innerZO, 0.0F, 0.0F);
    PositionTextureVertex vert1 = new PositionTextureVertex(innerXM, originY, innerZO, 0.0F, 8.0F);
    PositionTextureVertex vert2 = new PositionTextureVertex(innerXO, originY, innerZM, 8.0F, 0.0F);
    PositionTextureVertex vert3 = new PositionTextureVertex(innerXM, originY, innerZM, 8.0F, 8.0F);

    PositionTextureVertex vert4 = new PositionTextureVertex(originX, midY1, originZ, 0.0F, 0.0F);
    PositionTextureVertex vert5 = new PositionTextureVertex(maxX, midY1, originZ, 0.0F, 8.0F);
    PositionTextureVertex vert6 = new PositionTextureVertex(originX, midY1, maxZ, 8.0F, 0.0F);
    PositionTextureVertex vert7 = new PositionTextureVertex(maxX, midY1, maxZ, 8.0F, 8.0F);

    PositionTextureVertex vert8 = new PositionTextureVertex(originX, midY2, originZ, 0.0F, 0.0F);
    PositionTextureVertex vert9 = new PositionTextureVertex(maxX, midY2, originZ, 0.0F, 8.0F);
    PositionTextureVertex vert10 = new PositionTextureVertex(originX, midY2, maxZ, 8.0F, 0.0F);
    PositionTextureVertex vert11 = new PositionTextureVertex(maxX, midY2, maxZ, 8.0F, 8.0F);

    PositionTextureVertex vert12 = new PositionTextureVertex(innerXO, midY3, innerZO, 0.0F, 0.0F);
    PositionTextureVertex vert13 = new PositionTextureVertex(innerXM, midY3, innerZO, 0.0F, 8.0F);
    PositionTextureVertex vert14 = new PositionTextureVertex(innerXO, midY3, innerZM, 8.0F, 0.0F);
    PositionTextureVertex vert15 = new PositionTextureVertex(innerXM, midY3, innerZM, 8.0F, 8.0F);

    PositionTextureVertex vert16 = new PositionTextureVertex(innerXO, maxY, innerZO, 0.0F, 0.0F);
    PositionTextureVertex vert17 = new PositionTextureVertex(innerXM, maxY, innerZO, 0.0F, 8.0F);
    PositionTextureVertex vert18 = new PositionTextureVertex(innerXO, maxY, innerZM, 8.0F, 0.0F);
    PositionTextureVertex vert19 = new PositionTextureVertex(innerXM, maxY, innerZM, 8.0F, 8.0F);






















    this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] { vert7, vert6, vert2, vert3 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] { vert5, vert7, vert3, vert1 }, textureOffsetX, textureOffsetY + depth, textureOffsetX + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] { vert4, vert5, vert1, vert0 }, textureOffsetX + depth, textureOffsetY, textureOffsetX + depth + width, textureOffsetY + depth, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] { vert6, vert4, vert0, vert2 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + width, textureOffsetY, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] { vert1, vert0, vert2, vert3 }, textureOffsetX + depth, textureOffsetY + depth, textureOffsetX + depth + width, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);


    this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] { vert11, vert10, vert6, vert7 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[6] = new TexturedQuad(new PositionTextureVertex[] { vert9, vert11, vert7, vert5 }, textureOffsetX, textureOffsetY + depth, textureOffsetX + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[7] = new TexturedQuad(new PositionTextureVertex[] { vert8, vert9, vert5, vert4 }, textureOffsetX + depth, textureOffsetY, textureOffsetX + depth + width, textureOffsetY + depth, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[8] = new TexturedQuad(new PositionTextureVertex[] { vert10, vert8, vert4, vert6 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + width, textureOffsetY, renderer.field_78801_a, renderer.field_78799_b);


    this.quadList[9] = new TexturedQuad(new PositionTextureVertex[] { vert15, vert14, vert10, vert11 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[10] = new TexturedQuad(new PositionTextureVertex[] { vert13, vert15, vert11, vert9 }, textureOffsetX, textureOffsetY + depth, textureOffsetX + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[11] = new TexturedQuad(new PositionTextureVertex[] { vert12, vert13, vert9, vert8 }, textureOffsetX + depth, textureOffsetY, textureOffsetX + depth + width, textureOffsetY + depth, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[12] = new TexturedQuad(new PositionTextureVertex[] { vert14, vert12, vert8, vert10 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + width, textureOffsetY, renderer.field_78801_a, renderer.field_78799_b);


    this.quadList[13] = new TexturedQuad(new PositionTextureVertex[] { vert19, vert18, vert14, vert15 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[14] = new TexturedQuad(new PositionTextureVertex[] { vert17, vert19, vert15, vert13 }, textureOffsetX, textureOffsetY + depth, textureOffsetX + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[15] = new TexturedQuad(new PositionTextureVertex[] { vert16, vert17, vert13, vert12 }, textureOffsetX + depth, textureOffsetY, textureOffsetX + depth + width, textureOffsetY + depth, renderer.field_78801_a, renderer.field_78799_b);

    this.quadList[16] = new TexturedQuad(new PositionTextureVertex[] { vert18, vert16, vert12, vert14 }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + width, textureOffsetY, renderer.field_78801_a, renderer.field_78799_b);
  }






  @SideOnly(Side.CLIENT)
  public void func_78245_a(Tessellator par1Tessellator, float par2) {
    for (int var3 = 0; var3 < this.quadList.length; var3++)
    {
      this.quadList[var3].func_78236_a(par1Tessellator, par2);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelUrn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */