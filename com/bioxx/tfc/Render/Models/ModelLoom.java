package com.bioxx.tfc.Render.Models;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TELoom;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;









public class ModelLoom
  extends ModelBase
{
  private ModelRenderer loomPole1;
  private ModelRenderer loomPole2;
  private TexturedQuad[] initialString;
  private TexturedQuad[] finalString;
  private TexturedQuad clothRender;
  private PositionTextureVertex vert0;
  private PositionTextureVertex vert1;
  private PositionTextureVertex vert2;
  private PositionTextureVertex vert3;
  public int cloth;
  private float pole1StringY = 14.5F;
  private float pole2StringY = 14.5F;

  public int tempNum;

  public long tempTime;

  public boolean clothIncrease;

  public int mod = 40;

  public int lastClothIncrease;
  public boolean stillWeaving;

  public ModelLoom() {
    this.loomPole2 = new ModelRenderer(this, 38, 0);
    this.loomPole2.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 1);
    this.loomPole2.func_78793_a(1.0F, 1.65F, 10.0F);

    this.loomPole1 = new ModelRenderer(this, 38, 0);
    this.loomPole1.func_78789_a(0.0F, 0.0F, 0.0F, 14, 1, 1);
    this.loomPole1.func_78793_a(1.0F, 4.85F, 10.0F);
  }









  protected void setRotationRadians(ModelRenderer model, float x, float y, float z) {
    model.field_78795_f = x;
    model.field_78796_g = y;
    model.field_78808_h = z;
  }








  protected void setRotationDegrees(ModelRenderer model, float x, float y, float z) {
    setRotationRadians(model, (float)Math.toRadians(x), (float)Math.toRadians(y), (float)Math.toRadians(z));
  }

  public void render(int numStrings, int numMaxStrings, int tick, boolean shouldClothIncrease, int tickMod, ResourceLocation stringTex, boolean isWeaving, boolean stillWeaving, TELoom te) {
    float renderOffsetPole2 = 9.0F;
    float renderOffsetPole1 = 9.0F;

    this.initialString = new TexturedQuad[numMaxStrings];
    this.finalString = new TexturedQuad[numMaxStrings];



    float stringStartZ = 11.9F;

    float stringWidth = 9.5F / numMaxStrings;
    float stringStartX = 3.25F;

    float pole2Height = 2.15F;
    float pole1Height = 5.35F;

    if (this.cloth == 0) {
      this.pole1StringY = 14.5F;
      this.pole2StringY = 14.5F;
    }

    if (shouldClothIncrease) {
      this.cloth++;
    }

    if (shouldClothIncrease && tick >= tickMod / 4 - 1 && tick < 3 * tickMod / 4 - 1) {

      this.pole1StringY = this.pole2StringY - stringWidth;
    }
    else if (shouldClothIncrease && (tick >= 3 * tickMod / 4 - 1 || tick < tickMod / 4 - 1)) {

      this.pole2StringY = this.pole1StringY - stringWidth;
    }

    if (tick >= tickMod / 2 && (isWeaving || (stillWeaving && renderOffsetPole1 != 9.0F))) {

      renderOffsetPole1 = 7.0F + (float)(2.0D * Math.cos((tick / (tickMod / 4)) * Math.PI));
    }
    else if (isWeaving || (stillWeaving && renderOffsetPole2 != 9.0F)) {

      renderOffsetPole2 = 7.0F + (float)(2.0D * Math.cos((tick / (tickMod / 4)) * Math.PI));
    }

    if (this.cloth >= numMaxStrings && shouldClothIncrease) {
      resetCloth(te);
    }

    this.loomPole2.func_78793_a(1.0F, 1.65F, renderOffsetPole2);
    this.loomPole1.func_78793_a(1.0F, 4.85F, renderOffsetPole1);

    GL11.glPushMatrix();
    GL11.glRotatef(te.rotation * -90.0F, 0.0F, 1.0F, 0.0F);
    switch (te.rotation) {
      case 1:
        GL11.glTranslatef(0.0F, 0.0F, -1.0F); break;
      case 2: GL11.glTranslatef(-1.0F, 0.0F, -1.0F); break;
      case 3: GL11.glTranslatef(-1.0F, 0.0F, 0.0F);
        break;
    }
    this.loomPole2.func_78785_a(0.0625F);
    this.loomPole1.func_78785_a(0.0625F);

    TFC_Core.bindTexture(stringTex);

    float string1Z = Math.min(renderOffsetPole1 + 4.25F, 12.0F);
    float string2Z = Math.min(renderOffsetPole2 + 4.25F, 12.0F);

    float string1Length = (float)Math.sqrt(Math.pow((stringStartZ - string1Z), 2.0D) + Math.pow((this.pole1StringY - pole1Height), 1.0D));
    float string2Length = (float)Math.sqrt(Math.pow((stringStartZ - string2Z), 2.0D) + Math.pow((this.pole2StringY - pole2Height), 1.0D));

    float string1FinalLength = (float)Math.sqrt(Math.pow((stringStartZ - string1Z), 2.0D) + Math.pow(pole1Height, 1.0D));
    float string2FinalLength = (float)Math.sqrt(Math.pow((stringStartZ - string2Z), 2.0D) + Math.pow(pole2Height, 1.0D));
    int i;
    for (i = 0; i < numStrings; i += 2) {




      this.vert0 = new PositionTextureVertex(stringStartX + i * stringWidth, this.pole2StringY, stringStartZ, 0.0F, 0.0F);




      this.vert1 = new PositionTextureVertex(stringStartX + stringWidth + i * stringWidth, this.pole2StringY, stringStartZ, 0.0F, 8.0F);




      this.vert2 = new PositionTextureVertex(stringStartX + i * stringWidth, pole2Height, string2Z, 8.0F, 0.0F);




      this.vert3 = new PositionTextureVertex(stringStartX + stringWidth + i * stringWidth, pole2Height, string2Z, 8.0F, 8.0F);





      this.initialString[i] = new TexturedQuad(new PositionTextureVertex[] { this.vert2, this.vert3, this.vert1, this.vert0 }, 0, 0, 1, (int)string2Length, 16.0F, 16.0F);



      this.vert0 = new PositionTextureVertex(stringStartX + i * stringWidth, pole2Height, string2Z, 0.0F, 0.0F);




      this.vert1 = new PositionTextureVertex(stringStartX + stringWidth + i * stringWidth, pole2Height, string2Z, 0.0F, 8.0F);




      this.vert2 = new PositionTextureVertex(stringStartX + i * stringWidth, 0.0F, stringStartZ, 8.0F, 0.0F);




      this.vert3 = new PositionTextureVertex(stringStartX + stringWidth + i * stringWidth, 0.0F, stringStartZ, 8.0F, 8.0F);





      this.finalString[i] = new TexturedQuad(new PositionTextureVertex[] { this.vert2, this.vert3, this.vert1, this.vert0 }, 0, 0, 1, (int)string2FinalLength, 16.0F, 16.0F);






      this.vert0 = new PositionTextureVertex(stringStartX + (i + 1) * stringWidth, this.pole1StringY, stringStartZ, 0.0F, 0.0F);




      this.vert1 = new PositionTextureVertex(stringStartX + stringWidth + (i + 1) * stringWidth, this.pole1StringY, stringStartZ, 0.0F, 8.0F);




      this.vert2 = new PositionTextureVertex(stringStartX + (i + 1) * stringWidth, pole1Height, string1Z, 8.0F, 0.0F);




      this.vert3 = new PositionTextureVertex(stringStartX + stringWidth + (i + 1) * stringWidth, pole1Height, string1Z, 8.0F, 8.0F);




      if (i + 1 < numStrings) {
        this.initialString[i + 1] = new TexturedQuad(new PositionTextureVertex[] { this.vert2, this.vert3, this.vert1, this.vert0 }, 0, 0, 1, (int)string1Length, 16.0F, 16.0F);


        this.vert0 = new PositionTextureVertex(stringStartX + (i + 1) * stringWidth, pole1Height, string1Z, 0.0F, 0.0F);




        this.vert1 = new PositionTextureVertex(stringStartX + stringWidth + (i + 1) * stringWidth, pole1Height, string1Z, 0.0F, 8.0F);




        this.vert2 = new PositionTextureVertex(stringStartX + (i + 1) * stringWidth, 0.0F, stringStartZ, 8.0F, 0.0F);




        this.vert3 = new PositionTextureVertex(stringStartX + stringWidth + (i + 1) * stringWidth, 0.0F, stringStartZ, 8.0F, 8.0F);





        this.finalString[i + 1] = new TexturedQuad(new PositionTextureVertex[] { this.vert2, this.vert3, this.vert1, this.vert0 }, 0, 0, 1, (int)string1FinalLength, 16.0F, 16.0F);
      }
    }

    this.vert0 = new PositionTextureVertex(stringStartX, 14.5F, stringStartZ, 0.0F, 0.0F);




    this.vert1 = new PositionTextureVertex(stringStartX + numStrings * stringWidth, 14.5F, stringStartZ, 0.0F, 8.0F);




    this.vert2 = new PositionTextureVertex(stringStartX, 14.5F - this.cloth * stringWidth, stringStartZ, 8.0F, 0.0F);




    this.vert3 = new PositionTextureVertex(stringStartX + numStrings * stringWidth, 14.5F - this.cloth * stringWidth, stringStartZ, 8.0F, 8.0F);





    this.clothRender = new TexturedQuad(new PositionTextureVertex[] { this.vert0, this.vert1, this.vert3, this.vert2 }, 0, 0, 16, this.cloth, 16.0F, 16.0F);

    GL11.glPushMatrix();
    GL11.glDisable(2884);
    for (i = 0; i < numStrings; i++) {
      this.initialString[i].func_78236_a(Tessellator.field_78398_a, 0.0625F);
      this.finalString[i].func_78236_a(Tessellator.field_78398_a, 0.0625F);
    }
    this.clothRender.func_78236_a(Tessellator.field_78398_a, 0.0625F);
    GL11.glEnable(2884);
    GL11.glPopMatrix();
    GL11.glPopMatrix();
  }

  public void updateCloth(int newCloth) {
    this.cloth = newCloth;
  }


  public void resetCloth(TELoom te) {
    te.finishCloth();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\Models\ModelLoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */