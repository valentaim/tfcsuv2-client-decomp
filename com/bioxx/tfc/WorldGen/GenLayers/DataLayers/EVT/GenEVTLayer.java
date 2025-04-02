package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.EVT;

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


public abstract class GenEVTLayer
  extends GenLayerTFC
{
  public static final int LOW = DataLayer.EVT_0_25.layerID;
  public static final int HIGH = DataLayer.EVT_8.layerID;

  public static GenLayerTFC initialize(long seed, WorldType worldType) {
    GenLayerZoomTFC genLayerZoomTFC;
    GenLayerTFC continent = genContinent(0L);
    drawImage(512, continent, "EVT 0");
    continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "EVT 1");
    for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {
      GenLayerEVTMix genLayerEVTMix;
      if (zoomLevel == 0) {

        genLayerEVTMix = new GenLayerEVTMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
        drawImage(512, genLayerEVTMix, "EVT 2-" + zoomLevel + " Mix");
      }
      genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerEVTMix);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC, "EVT 2-" + zoomLevel + " Smoothed");
    }

    GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
    GenLayerVoronoiZoomTFC genLayerVoronoiZoomTFC = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
    drawImage(512, (GenLayerTFC)genLayerVoronoiZoomTFC, "EVT 4 Voronoi EVT");
    genLayerVoronoiZoomTFC.func_75905_a(seed);
    return (GenLayerTFC)genLayerVoronoiZoomTFC;
  }
  private static boolean shouldDraw;

  public static GenLayerTFC genContinent(long seed) {
    GenLayerTFC continent = new GenLayerEVTInit(1L + seed);
    drawImage(512, continent, "EVT Init 0");
    continent = new GenLayerAddEVT(1L, (GenLayer)continent);
    drawImage(512, continent, "EVT Init 0b Add EVT");
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "EVT Init 1");
    GenLayerAddEVT genLayerAddEVT4 = new GenLayerAddEVT(1L, (GenLayer)genLayerFuzzyZoomTFC);
    drawImage(512, genLayerAddEVT4, "EVT Init 2 Add EVT");
    GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, genLayerAddEVT4);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "EVT Init 3 Zoom");
    GenLayerAddEVT genLayerAddEVT3 = new GenLayerAddEVT(2L, (GenLayer)genLayerZoomTFC3);
    drawImage(512, genLayerAddEVT3, "EVT Init 4 Add EVT");
    GenLayerEVTMix genLayerEVTMix = new GenLayerEVTMix(88L, (GenLayer)genLayerAddEVT3);
    drawImage(512, genLayerEVTMix, "EVT Init 4b Mix");
    GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerEVTMix);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "EVT Init 5 Zoom");
    GenLayerAddEVT genLayerAddEVT2 = new GenLayerAddEVT(3L, (GenLayer)genLayerZoomTFC2);
    drawImage(512, genLayerAddEVT2, "EVT Init 6 Add EVT");
    GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerAddEVT2);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "EVT Init 7 Zoom");
    GenLayerAddEVT genLayerAddEVT1 = new GenLayerAddEVT(4L, (GenLayer)genLayerZoomTFC1);
    drawImage(512, genLayerAddEVT1, "EVT Init 8 Add EVT");
    return genLayerAddEVT1;
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
      TerraFirmaCraft.LOG.info("Starting " + name + ".bmp");
      for (int x = 0; x < size; x++) {

        for (int z = 0; z < size; z++) {

          int value = ints[x * size + z];
          if (value - 80 >= 0 && value - 80 <= 7) {
            graphics.setColor(Color.getColor("", (value - 80) * 32 << 16));
          } else {
            graphics.setColor(Color.getColor("", 16777215));
          }  graphics.drawRect(x, z, 1, 1);
        }
      }
      TerraFirmaCraft.LOG.info("Finished " + name + ".bmp");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public GenEVTLayer(long par1) {
    super(par1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\EVT\GenEVTLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */