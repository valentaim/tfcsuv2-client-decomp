package com.bioxx.tfc.Render;

import java.util.EnumSet;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class RenderBlocksLightCache
  extends RenderBlocksFixUV
{
  private EnumSet<ForgeDirection> facesToDraw = EnumSet.allOf(ForgeDirection.class);

  private RenderFaceData[] sides = new RenderFaceData[6];


  private static class RenderPointData
  {
    private int brightness;
    private float r;
    private float g;
    private float b;

    private RenderPointData() {}
  }

  private static class RenderFaceData
  {
    private int cacheBrightnessTopLeft;
    private int cacheBrightnessBottomLeft;
    private int cacheBrightnessBottomRight;
    private int cacheBrightnessTopRight;
    private float cacheColorRedTopLeft;
    private float cacheColorRedBottomLeft;
    private float cacheColorRedBottomRight;
    private float cacheColorRedTopRight;
    private float cacheColorGreenTopLeft;
    private float cacheColorGreenBottomLeft;
    private float cacheColorGreenBottomRight;
    private float cacheColorGreenTopRight;
    private float cacheColorBlueTopLeft;
    private float cacheColorBlueBottomLeft;
    private float cacheColorBlueBottomRight;
    private float cacheColorBlueTopRight;

    public RenderFaceData(RenderBlocks rb) {
      this.cacheBrightnessTopLeft = rb.field_147864_al;
      this.cacheBrightnessBottomLeft = rb.field_147874_am;
      this.cacheBrightnessBottomRight = rb.field_147876_an;
      this.cacheBrightnessTopRight = rb.field_147870_ao;

      this.cacheColorRedTopLeft = rb.field_147872_ap;
      this.cacheColorRedBottomLeft = rb.field_147852_aq;
      this.cacheColorRedBottomRight = rb.field_147850_ar;
      this.cacheColorRedTopRight = rb.field_147848_as;

      this.cacheColorGreenTopLeft = rb.field_147846_at;
      this.cacheColorGreenBottomLeft = rb.field_147860_au;
      this.cacheColorGreenBottomRight = rb.field_147858_av;
      this.cacheColorGreenTopRight = rb.field_147856_aw;

      this.cacheColorBlueTopLeft = rb.field_147854_ax;
      this.cacheColorBlueBottomLeft = rb.field_147841_ay;
      this.cacheColorBlueBottomRight = rb.field_147839_az;
      this.cacheColorBlueTopRight = rb.field_147833_aA;
    }


    public RenderBlocksLightCache.RenderPointData getCachedValue(double leftRight, double topBottom) {
      RenderBlocksLightCache.RenderPointData o = new RenderBlocksLightCache.RenderPointData();


      double rTop = this.cacheColorRedTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorRedTopRight;
      double rBottom = this.cacheColorRedBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorRedBottomRight;
      o.r = (float)(rTop * topBottom + rBottom * (1.0D - topBottom));

      double gTop = this.cacheColorGreenTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorGreenTopRight;
      double gBottom = this.cacheColorGreenBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorGreenBottomRight;
      o.g = (float)(gTop * topBottom + gBottom * (1.0D - topBottom));

      double bTop = this.cacheColorBlueTopLeft * leftRight + (1.0D - leftRight) * this.cacheColorBlueTopRight;
      double bBottom = this.cacheColorBlueBottomLeft * leftRight + (1.0D - leftRight) * this.cacheColorBlueBottomRight;
      o.b = (float)(bTop * topBottom + bBottom * (1.0D - topBottom));


      double highTop = (this.cacheBrightnessTopLeft >> 16 & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessTopRight >> 16 & 0xFF);
      double highBottom = (this.cacheBrightnessBottomLeft >> 16 & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessBottomRight >> 16 & 0xFF);
      int high = (int)(highTop * topBottom + highBottom * (1.0D - topBottom)) & 0xFF;

      double lowTop = (this.cacheBrightnessTopLeft & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessTopRight & 0xFF);
      double lowBottom = (this.cacheBrightnessBottomLeft & 0xFF) * leftRight + (1.0D - leftRight) * (this.cacheBrightnessBottomRight & 0xFF);
      int low = (int)(lowTop * topBottom + lowBottom * (1.0D - topBottom)) & 0xFF;


      o.brightness = high << 16 | low;

      return o;
    }
  }



  public RenderBlocksLightCache(RenderBlocks old) {
    super(old);
  }





  public void disableRender() {
    this.facesToDraw = EnumSet.noneOf(ForgeDirection.class);
  }


  public void enableRender() {
    this.facesToDraw = EnumSet.allOf(ForgeDirection.class);
  }







  public void func_147761_c(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.NORTH)) {

      saveData(ForgeDirection.NORTH);

      return;
    }
    super.func_147761_c(a, b, c, d, e);
  }



  public void func_147764_f(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.EAST)) {

      saveData(ForgeDirection.EAST);

      return;
    }
    super.func_147764_f(a, b, c, d, e);
  }




  public void func_147798_e(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.WEST)) {

      saveData(ForgeDirection.WEST);

      return;
    }
    super.func_147798_e(a, b, c, d, e);
  }



  public void func_147768_a(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.DOWN)) {

      saveData(ForgeDirection.DOWN);

      return;
    }
    super.func_147768_a(a, b, c, d, e);
  }



  public void func_147806_b(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.UP)) {

      saveData(ForgeDirection.UP);

      return;
    }
    super.func_147806_b(a, b, c, d, e);
  }



  public void func_147734_d(Block a, double b, double c, double d, IIcon e) {
    if (!this.facesToDraw.contains(ForgeDirection.SOUTH)) {

      saveData(ForgeDirection.SOUTH);

      return;
    }
    super.func_147734_d(a, b, c, d, e);
  }


  private void saveData(ForgeDirection side) {
    this.sides[side.ordinal()] = new RenderFaceData(this);
  }


  public void renderCachedBlock(Block block, int i, int j, int k, IIcon myTexture) {
    this.field_147863_w = Minecraft.func_71379_u();



    RenderPointData rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147857_k, this.field_147853_m);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147855_j, this.field_147853_m);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147855_j, this.field_147851_l);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.EAST.ordinal()].getCachedValue(1.0D - this.field_147857_k, this.field_147851_l);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    func_147764_f(block, i, j, k, myTexture);


    rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147857_k, this.field_147853_m);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147857_k, this.field_147851_l);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147855_j, this.field_147851_l);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.WEST.ordinal()].getCachedValue(this.field_147855_j, this.field_147853_m);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    func_147798_e(block, i, j, k, myTexture);


    rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147857_k);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147855_j);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147855_j);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.SOUTH.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147857_k);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    func_147734_d(block, i, j, k, myTexture);


    rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147857_k, 1.0D - this.field_147859_h);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147857_k, 1.0D - this.field_147861_i);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147855_j, 1.0D - this.field_147861_i);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.NORTH.ordinal()].getCachedValue(this.field_147855_j, 1.0D - this.field_147859_h);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    func_147761_c(block, i, j, k, myTexture);


    rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147861_i, this.field_147853_m);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147861_i, this.field_147851_l);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147859_h, this.field_147851_l);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.UP.ordinal()].getCachedValue(this.field_147859_h, this.field_147853_m);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    func_147806_b(block, i, j, k, myTexture);


    rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147853_m);
    this.field_147872_ap = rpd.r;
    this.field_147846_at = rpd.g;
    this.field_147854_ax = rpd.b;
    this.field_147864_al = rpd.brightness;

    rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147859_h, this.field_147851_l);
    this.field_147852_aq = rpd.r;
    this.field_147860_au = rpd.g;
    this.field_147841_ay = rpd.b;
    this.field_147874_am = rpd.brightness;

    rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147851_l);
    this.field_147850_ar = rpd.r;
    this.field_147858_av = rpd.g;
    this.field_147839_az = rpd.b;
    this.field_147876_an = rpd.brightness;

    rpd = this.sides[ForgeDirection.DOWN.ordinal()].getCachedValue(1.0D - this.field_147861_i, this.field_147853_m);
    this.field_147848_as = rpd.r;
    this.field_147856_aw = rpd.g;
    this.field_147833_aA = rpd.b;
    this.field_147870_ao = rpd.brightness;

    func_147768_a(block, i, j, k, myTexture);


    this.field_147863_w = false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Render\RenderBlocksLightCache.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */