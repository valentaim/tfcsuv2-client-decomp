package com.bioxx.tfc.Render.Models;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;




public class ModelPotteryBase
  extends ModelBox
{
  private Object[] rings;
  protected List<TexturedQuad> polygons;

  public ModelPotteryBase(ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale, Object[] dataArray, boolean connectTop) {
    super(renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale);











































    this.rings = new Object[dataArray.length];

    for (int i = 0; i < dataArray.length; i++) {







      float[] data = (float[])dataArray[i];
      this.rings[i] = newRing(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
    }

    this.polygons = buildSides(this.rings, renderer, textureOffsetX, textureOffsetY, originX, originY, originZ, width, height, depth, scale, connectTop);
  }




  public PositionTextureVertex[] newRing(float originX, float originY, float originZ, float offsetX, float offsetY, float offsetZ, float width) {
    PositionTextureVertex[] vert = new PositionTextureVertex[8];
    float widthX = width / (MathHelper.func_76129_c(2.0F) + 2.0F);
    vert[0] = new PositionTextureVertex(originX + offsetX - width / 2.0F - widthX, originY + offsetY, originZ + offsetZ - width / 2.0F, 0.0F, 0.0F);
    vert[1] = new PositionTextureVertex(originX + offsetX + width / 2.0F - widthX, originY + offsetY, originZ + offsetZ - width / 2.0F, 0.0F, 0.0F);
    vert[2] = new PositionTextureVertex(originX + offsetX + width / 2.0F, originY + offsetY, originZ + offsetZ - width / 2.0F - widthX, 0.0F, 0.0F);
    vert[3] = new PositionTextureVertex(originX + offsetX + width / 2.0F, originY + offsetY, originZ + offsetZ + width / 2.0F - widthX, 0.0F, 0.0F);
    vert[4] = new PositionTextureVertex(originX + offsetX + width / 2.0F - widthX, originY + offsetY, originZ + offsetZ + width / 2.0F, 0.0F, 0.0F);
    vert[5] = new PositionTextureVertex(originX + offsetX - width / 2.0F - widthX, originY + offsetY, originZ + offsetZ + width / 2.0F, 0.0F, 0.0F);
    vert[6] = new PositionTextureVertex(originX + offsetX - width / 2.0F, originY + offsetY, originZ + offsetZ + width / 2.0F - widthX, 0.0F, 0.0F);
    vert[7] = new PositionTextureVertex(originX + offsetX - width / 2.0F, originY + offsetY, originZ + offsetZ - width / 2.0F - widthX, 0.0F, 0.0F);
    return vert;
  }




  public List<TexturedQuad> buildSides(Object[] vertices, ModelRenderer renderer, int textureOffsetX, int textureOffsetY, float originX, float originY, float originZ, int width, int height, int depth, float scale, boolean connectTopFace) {
    ArrayList<TexturedQuad> aList = new ArrayList<>();
    for (int i = 0; i < vertices.length - 1; i++) {
      for (int j = 0; j < 8; j++) {
        aList.add(new TexturedQuad(new PositionTextureVertex[] { ((PositionTextureVertex[])vertices[i + 1])[j], ((PositionTextureVertex[])vertices[i + 1])[(j + 1) % 8], ((PositionTextureVertex[])vertices[i])[(j + 1) % 8], ((PositionTextureVertex[])vertices[i])[j] }, textureOffsetX + depth + width, textureOffsetY + depth, textureOffsetX + depth + width + depth, textureOffsetY + depth + height, renderer.field_78801_a, renderer.field_78799_b));
      }
    }

    PositionTextureVertex[] baseRing = (PositionTextureVertex[])vertices[0];
    aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[1], baseRing[2], baseRing[3], baseRing[0] }));
    aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[0], baseRing[3], baseRing[4], baseRing[7] }));
    aList.add(new TexturedQuad(new PositionTextureVertex[] { baseRing[5], baseRing[6], baseRing[7], baseRing[4] }));

    if (connectTopFace) {
      PositionTextureVertex[] topRing = (PositionTextureVertex[])vertices[vertices.length - 1];
      aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[1], topRing[2], topRing[3], topRing[0] }));
      aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[0], topRing[3], topRing[4], topRing[7] }));
      aList.add(new TexturedQuad(new PositionTextureVertex[] { topRing[5], topRing[6], topRing[7], topRing[4] }));
    }
    return aList;
  }







  @SideOnly(Side.CLIENT)
  public void func_78245_a(Tessellator par1Tessellator, float par2) {
    GL11.glDisable(2884);
    for (TexturedQuad quad : this.polygons) {
      quad.func_78236_a(par1Tessellator, par2);
    }
    GL11.glEnable(2884);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelPotteryBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */