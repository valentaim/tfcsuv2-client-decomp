package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rain;

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


public abstract class GenRainLayerTFC
  extends GenLayerTFC
{
  public static final int WET = DataLayer.RAIN_4000.layerID;
  public static final int DRY = DataLayer.RAIN_125.layerID;

  public static GenLayerTFC initialize(long seed, WorldType worldType) {
    GenLayerZoomTFC genLayerZoomTFC;
    GenLayerTFC continent = genContinent(0L);
    drawImage(512, continent, "Rain 0");
    continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Rain 1");
    for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {
      GenLayerRainMix genLayerRainMix;
      if ((zoomLevel & 0x1) == 1) {

        genLayerRainMix = new GenLayerRainMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
        drawImage(512, genLayerRainMix, "Rain 2-" + zoomLevel + " Mix");
      }
      genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerRainMix);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC, "Rain 2-" + zoomLevel + " Smoothed");
    }

    GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
    GenLayerVoronoiZoomTFC voronoiZoom = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
    drawImage(512, (GenLayerTFC)finalCont, "Rain 4 Voronoi Rain");
    voronoiZoom.func_75905_a(seed);
    return (GenLayerTFC)voronoiZoom;
  }
  private static boolean shouldDraw;

  public static GenLayerTFC genContinent(long seed) {
    GenLayerTFC continent = new GenLayerRainInit(1L + seed);
    drawImage(512, continent, "Rain Init 0");
    continent = new GenLayerAddRain(1L, (GenLayer)continent);
    drawImage(512, continent, "Rain Init 0b Add Rain");
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Rain Init 1");


    GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "Rain Init 3 Zoom");


    GenLayerRainMix genLayerRainMix2 = new GenLayerRainMix(88L, (GenLayer)genLayerZoomTFC3);
    drawImage(512, genLayerRainMix2, "Rain Init 4b Mix");
    GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerRainMix2);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Rain Init 5 Zoom");
    GenLayerRainMix genLayerRainMix1 = new GenLayerRainMix(88L, (GenLayer)genLayerZoomTFC2);
    drawImage(512, genLayerRainMix1, "Rain Init 5b Mix");


    GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerRainMix1);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Rain Init 7 Zoom");
    return (GenLayerTFC)genLayerZoomTFC1;
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
          if (value - 100 >= 0) {
            graphics.setColor(Color.getColor("", (value - 100) * 32));
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


  public GenRainLayerTFC(long par1) {
    super(par1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Rain\GenRainLayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */