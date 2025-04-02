package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Tree;

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
import net.minecraft.world.gen.layer.GenLayer;

public abstract class GenTreeLayer extends GenLayerTFC {
  private static boolean shouldDraw;

  public static GenLayerTFC initialize(long par0, DataLayer[] trees) {
    GenLayerZoomTFC genLayerZoomTFC1;
    GenLayerTFC layer = new GenLayerTreeInit(1L, trees);
    drawImage(512, layer, "Tree 0");
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)layer);
    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Tree 1");

    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Tree 2");
    GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);

    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 3");
    genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerZoomTFC2);

    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 4");
    genLayerZoomTFC2 = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerZoomTFC2);

    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 5");
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC2);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Tree 6");
    for (int zoomLevel = 0; zoomLevel < 5; zoomLevel++) {

      genLayerZoomTFC1 = new GenLayerZoomTFC((1000 + zoomLevel), (GenLayerTFC)genLayerSmoothTFC1);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Tree " + (7 + zoomLevel));
    }

    GenLayerSmoothTFC smoothedLayer = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC1);
    GenLayerVoronoiZoomTFC voronoiLayer = new GenLayerVoronoiZoomTFC(10L, (GenLayer)smoothedLayer);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Tree Final");

    voronoiLayer.func_75905_a(par0);
    return (GenLayerTFC)voronoiLayer;
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

          int id = ints[x * size + z];
          int color = (id * 8 << 16) + (id * 8 << 8) + id * 8;
          graphics.setColor(Color.getColor("", color));
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


  public GenTreeLayer(long par1) {
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
    if (var2 < 0) {
      var2 += par1;
    }
    this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
    this.field_75908_c += this.field_75907_b;
    return var2;
  }

  public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Tree\GenTreeLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */