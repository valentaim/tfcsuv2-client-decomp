package com.bioxx.tfc.Render.TESR;

import com.bioxx.tfc.TileEntities.TEBellows;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;



public class TESRBellows
  extends TESRBase
{
  private static final ResourceLocation FRONT_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows Front.png");
  private static final ResourceLocation BACK_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows Back.png");
  private static final ResourceLocation SIDE1_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows82.png");
  private static final ResourceLocation SIDE2_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows83.png");
  private static final ResourceLocation SIDE3_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows84.png");
  private static final ResourceLocation SIDE4_TEXTURE = new ResourceLocation("terrafirmacraft:textures/blocks/devices/Bellows85.png");



  public void func_147500_a(TileEntity tileentity, double x, double y, double z, float f) {
    TEBellows bellows = (TEBellows)tileentity;
    if (bellows != null) {

      int meta = bellows.func_145831_w().func_72805_g(bellows.field_145851_c, bellows.field_145848_d, bellows.field_145849_e);
      float pos = bellows.blowTimer * 0.1F;
      if (pos < 0.0F) pos = 0.0F;

      Tessellator t = Tessellator.field_78398_a;
      GL11.glPushMatrix();
      GL11.glTranslatef((float)x, (float)y, (float)z);

      renderBack(t, meta, pos);
      renderBody(t, meta, pos);
      renderFront(t, meta);

      GL11.glPopMatrix();
    }
  }




  private void renderBack(Tessellator t, int meta, float pos) {
    float l = 0.125F;
    float m = 1.0F - l;

    if (meta == 0) {

      float i = pos;
      float j = 0.1F + pos;

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, i, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, j, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, j, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, 0.0D);


      t.func_78374_a(0.0D, 1.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, j, 0.0D, l);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, l);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, j, 0.0D, l);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, l);
      t.func_78374_a(0.0D, 1.0D, i, 1.0D, 0.0D);

      t.func_78374_a(1.0D, 1.0D, i, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 1.0D, j, 0.0D, l);
      t.func_78374_a(1.0D, 0.0D, j, 1.0D, l);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, i, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, j, l, 1.0D);
      t.func_78374_a(0.0D, 0.0D, j, l, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 1) {

      float i = 0.9F - pos;
      float j = 1.0F - pos;

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(i, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(j, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(j, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, 1.0D, 0.0D);


      t.func_78374_a(i, 1.0D, 0.0D, m, 0.0D);
      t.func_78374_a(i, 1.0D, 1.0D, m, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 0.0D, 0.0D, m, 0.0D);
      t.func_78374_a(i, 1.0D, 0.0D, m, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 0.0D, 1.0D, m, 0.0D);
      t.func_78374_a(i, 0.0D, 0.0D, m, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 1.0D, 1.0D, m, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, m, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, 1.0D, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 2) {

      float i = 0.9F - pos;
      float j = 1.0F - pos;

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, i, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, j, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, j, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, 0.0D);


      t.func_78374_a(0.0D, 1.0D, i, 0.0D, m);
      t.func_78374_a(0.0D, 1.0D, j, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, m);

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, j, 0.0D, l);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, l);
      t.func_78374_a(0.0D, 1.0D, i, 1.0D, 0.0D);

      t.func_78374_a(1.0D, 1.0D, i, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 1.0D, j, 0.0D, l);
      t.func_78374_a(1.0D, 0.0D, j, 1.0D, l);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, i, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, j, l, 1.0D);
      t.func_78374_a(0.0D, 0.0D, j, l, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 3) {

      float i = pos;
      float j = 0.1F + pos;

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(i, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(j, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(j, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, 1.0D, 0.0D);


      t.func_78374_a(i, 1.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 1.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, l, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, l, 0.0D);

      t.func_78374_a(i, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, l, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, l, 0.0D);

      t.func_78374_a(i, 0.0D, 1.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 0.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, l, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, l, 0.0D);

      t.func_78374_a(i, 1.0D, 1.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, l, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, l, 0.0D);
      t.func_78381_a();
    }
  }




  private void renderBody(Tessellator t, int meta, float pos) {
    float k = 0.9F;
    float l = 0.1F;

    if (meta == 0 || meta == 2) {
      float i, j;
      if (meta == 0) {

        func_147499_a(SIDE1_TEXTURE);
        i = 0.05F + pos;
        j = 0.95F;
      }
      else {

        func_147499_a(SIDE4_TEXTURE);
        i = 0.05F;
        j = 0.95F - pos;
      }
      t.func_78382_b();
      t.func_78374_a(k, k, i, 0.0D, 0.0D);
      t.func_78374_a(k, k, j, 0.0D, 1.0D);
      t.func_78374_a(k, l, j, 1.0D, 1.0D);
      t.func_78374_a(k, l, i, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(l, k, i, 0.0D, 0.0D);
      t.func_78374_a(l, k, j, 0.0D, 1.0D);
      t.func_78374_a(k, k, j, 1.0D, 1.0D);
      t.func_78374_a(k, k, i, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(l, l, i, 0.0D, 0.0D);
      t.func_78374_a(l, l, j, 0.0D, 1.0D);
      t.func_78374_a(l, k, j, 1.0D, 1.0D);
      t.func_78374_a(l, k, i, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(k, l, i, 0.0D, 0.0D);
      t.func_78374_a(k, l, j, 0.0D, 1.0D);
      t.func_78374_a(l, l, j, 1.0D, 1.0D);
      t.func_78374_a(l, l, i, 1.0D, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 1 || meta == 3) {
      float i, j;
      if (meta == 3) {

        func_147499_a(SIDE3_TEXTURE);
        i = 0.05F + pos;
        j = 0.95F;
      }
      else {

        func_147499_a(SIDE2_TEXTURE);
        i = 0.05F;
        j = 0.95F - pos;
      }
      t.func_78382_b();
      t.func_78374_a(i, l, l, 0.0D, 0.0D);
      t.func_78374_a(i, k, l, 0.0D, 1.0D);
      t.func_78374_a(j, k, l, 1.0D, 1.0D);
      t.func_78374_a(j, l, l, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(i, l, k, 0.0D, 0.0D);
      t.func_78374_a(i, l, l, 0.0D, 1.0D);
      t.func_78374_a(j, l, l, 1.0D, 1.0D);
      t.func_78374_a(j, l, k, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(i, k, k, 0.0D, 0.0D);
      t.func_78374_a(i, l, k, 0.0D, 1.0D);
      t.func_78374_a(j, l, k, 1.0D, 1.0D);
      t.func_78374_a(j, k, k, 1.0D, 0.0D);
      t.func_78381_a();

      t.func_78382_b();
      t.func_78374_a(i, k, l, 0.0D, 0.0D);
      t.func_78374_a(i, k, k, 0.0D, 1.0D);
      t.func_78374_a(j, k, k, 1.0D, 1.0D);
      t.func_78374_a(j, k, l, 1.0D, 0.0D);
      t.func_78381_a();
    }
  }


  private void renderFront(Tessellator t, int meta) {
    float i = 0.9F;
    float j = 0.1F;
    float l = 0.125F;
    float m = 1.0F - l;

    if (meta == 0) {

      func_147499_a(FRONT_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, 1.0D, 1.0D, 0.0D);
      t.func_78381_a();

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, i, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, 0.0D);


      t.func_78374_a(0.0D, 1.0D, i, 0.0D, m);
      t.func_78374_a(0.0D, 1.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, i, 1.0D, m);

      t.func_78374_a(0.0D, 0.0D, i, 0.0D, m);
      t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, i, 1.0D, m);

      t.func_78374_a(1.0D, 1.0D, i, 0.0D, m);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, i, 1.0D, m);

      t.func_78374_a(0.0D, 0.0D, i, m, 0.0D);
      t.func_78374_a(1.0D, 0.0D, i, m, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 0.0D, 1.0D, 1.0D, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 1) {

      func_147499_a(FRONT_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, 0.0D, 1.0D, 0.0D);
      t.func_78381_a();

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(j, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(j, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, 1.0D, 0.0D);


      t.func_78374_a(0.0D, 1.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, l, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, l, 0.0D);

      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 1.0D, 0.0D, l, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, l, 0.0D);

      t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 0.0D, l, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, l, 0.0D);

      t.func_78374_a(0.0D, 1.0D, 1.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(j, 0.0D, 1.0D, l, 1.0D);
      t.func_78374_a(j, 1.0D, 1.0D, l, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 2) {

      func_147499_a(FRONT_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 0.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 0.0D);
      t.func_78381_a();

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(0.0D, 0.0D, j, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, j, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, 1.0D);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, 0.0D);


      t.func_78374_a(0.0D, 1.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 1.0D, j, 0.0D, l);
      t.func_78374_a(1.0D, 1.0D, j, 1.0D, l);
      t.func_78374_a(1.0D, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(0.0D, 0.0D, j, 0.0D, l);
      t.func_78374_a(0.0D, 1.0D, j, 1.0D, l);
      t.func_78374_a(0.0D, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(1.0D, 1.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 1.0D, j, 0.0D, l);
      t.func_78374_a(1.0D, 0.0D, j, 1.0D, l);
      t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, j, l, 1.0D);
      t.func_78374_a(0.0D, 0.0D, j, l, 0.0D);
      t.func_78381_a();
    }
    else if (meta == 3) {

      func_147499_a(FRONT_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(1.0D, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(1.0D, 1.0D, 0.0D, 0.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 0.0D);
      t.func_78381_a();

      func_147499_a(BACK_TEXTURE);
      t.func_78382_b();

      t.func_78374_a(i, 0.0D, 0.0D, 0.0D, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, 0.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(i, 1.0D, 0.0D, 1.0D, 0.0D);


      t.func_78374_a(i, 1.0D, 0.0D, m, 0.0D);
      t.func_78374_a(i, 1.0D, 1.0D, m, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 0.0D, 0.0D, m, 0.0D);
      t.func_78374_a(i, 1.0D, 0.0D, m, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 0.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 0.0D, 1.0D, m, 0.0D);
      t.func_78374_a(i, 0.0D, 0.0D, m, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 0.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 0.0D);

      t.func_78374_a(i, 1.0D, 1.0D, m, 0.0D);
      t.func_78374_a(i, 0.0D, 1.0D, m, 1.0D);
      t.func_78374_a(1.0D, 0.0D, 1.0D, 1.0D, 1.0D);
      t.func_78374_a(1.0D, 1.0D, 1.0D, 1.0D, 0.0D);
      t.func_78381_a();
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\TESR\TESRBellows.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */