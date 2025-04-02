package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Stability;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerFuzzyZoomTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerSmoothTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerVoronoiZoomTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerZoomTFC;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;

public abstract class GenStabilityLayer
  extends GenLayerTFC {
  private static boolean shouldDraw;

  public static GenLayerTFC initialize(long par0, WorldType par2WorldType) {
    GenLayerZoomTFC genLayerZoomTFC;
    GenLayerTFC continent = genContinent(par0);
    drawImage(512, continent, "Stability 0");
    continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Stability 1");
    for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {

      genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), (GenLayerTFC)genLayerSmoothTFC1);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC, "Stability 2-" + zoomLevel);
    }

    GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
    drawImage(512, (GenLayerTFC)finalCont, "Stability 3");
    GenLayerVoronoiZoomTFC voronoiZoom = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
    voronoiZoom.func_75905_a(par0);
    return (GenLayerTFC)voronoiZoom;
  }


  public static GenLayerTFC genContinent(long seed) {
    GenLayerTFC continent = new GenLayerStabilityInit(1L + seed);
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);

    GenLayerZoomTFC genLayerZoomTFC = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);

    genLayerZoomTFC = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerZoomTFC);

    genLayerZoomTFC = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerZoomTFC);

    return (GenLayerTFC)genLayerZoomTFC;
  }



  public static void drawImage(int size, GenLayerTFC genlayer, String name) {
    if (!shouldDraw) {
      return;
    }
    try {
      File outFile = new File(name + ".bmp");
      if (outFile.exists())
        return;
      int[] ints = genlayer.func_75904_a(0, 0, size, size);
      BufferedImage outBitmap = new BufferedImage(size, size, 1);
      Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
      graphics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info(name + ".bmp");
      for (int x = 0; x < size; x++) {

        for (int z = 0; z < size; z++) {

          int id = (DataLayer.layers[ints[x * size + z]]).data1;
          graphics.setColor(Color.getColor("", (id * 255 << 16) + (id * 255 << 8) + id * 255));
          graphics.drawRect(x, z, 1, 1);
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public GenStabilityLayer(long par1) {
    super(par1);
  }







  public void func_75905_a(long par1) {
    this.field_75907_b = par1;
    if (this.field_75909_a != null) {
      this.field_75909_a.func_75905_a(par1);
    }
    this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
    this.field_75907_b += this.field_75906_d;
    this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
    this.field_75907_b += this.field_75906_d;
    this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
    this.field_75907_b += this.field_75906_d;
  }






  public void func_75903_a(long par1, long par3) {
    this.field_75908_c = this.field_75907_b;
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += par1;
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += par3;
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += par1;
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += par3;
  }






  protected int func_75902_a(int par1) {
    int var2 = (int)((this.field_75908_c >> 24L) % par1);
    if (var2 < 0)
      var2 += par1;
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += this.field_75907_b;
    return var2;
  }

  public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Stability\GenStabilityLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */