package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.PH;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerFuzzyZoomTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerSmoothTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
import com.bioxx.tfc.WorldGen.GenLayers.GenLayerZoomTFC;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;


public abstract class GenPHLayer
  extends GenLayerTFC
{
  public static final int MIN = DataLayer.PH_ACID_HIGH.layerID;
  public static final int MAX = DataLayer.PH_ALKALINE_HIGH.layerID;
  public static GenLayerTFC initialize(long seed, WorldType worldType) {
    GenLayerZoomTFC genLayerZoomTFC;
    GenLayerTFC continent = genContinent(0L);
    drawImage(512, continent, "PH 0");
    continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "PH 1");
    for (int zoomLevel = 0; zoomLevel < 2; zoomLevel++) {
      GenLayerPHMix genLayerPHMix;
      if (zoomLevel == 0) {

        genLayerPHMix = new GenLayerPHMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
        drawImage(512, genLayerPHMix, "PH 2-" + zoomLevel + " Mix");
      }
      genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerPHMix);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC, "PH 2-" + zoomLevel + " Smoothed");
    }

    GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);

    drawImage(512, (GenLayerTFC)finalCont, "PH Final");
    finalCont.func_75905_a(seed);
    return (GenLayerTFC)finalCont;
  }
  private static boolean shouldDraw;

  public static GenLayerTFC genContinent(long seed) {
    GenLayerTFC continent = new GenLayerPHInit(1L + seed);
    drawImage(512, continent, "PH Init 0");
    continent = new GenLayerAddPH(1L, (GenLayer)continent);
    drawImage(512, continent, "PH Init 0b Add PH");
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "PH Init 1");
    GenLayerAddPH genLayerAddPH4 = new GenLayerAddPH(1L, (GenLayer)genLayerFuzzyZoomTFC);
    drawImage(512, genLayerAddPH4, "PH Init 2 Add PH");
    GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, genLayerAddPH4);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "PH Init 3 Zoom");
    GenLayerAddPH genLayerAddPH3 = new GenLayerAddPH(2L, (GenLayer)genLayerZoomTFC3);
    drawImage(512, genLayerAddPH3, "PH Init 4 Add PH");
    GenLayerPHMix genLayerPHMix = new GenLayerPHMix(88L, (GenLayer)genLayerAddPH3);
    drawImage(512, genLayerPHMix, "PH Init 4b Mix");
    GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerPHMix);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "PH Init 5 Zoom");
    GenLayerAddPH genLayerAddPH2 = new GenLayerAddPH(3L, (GenLayer)genLayerZoomTFC2);
    drawImage(512, genLayerAddPH2, "PH Init 6 Add PH");
    GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerAddPH2);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "PH Init 7 Zoom");
    GenLayerAddPH genLayerAddPH1 = new GenLayerAddPH(4L, (GenLayer)genLayerZoomTFC1);
    drawImage(512, genLayerAddPH1, "PH Init 8 Add PH");
    return genLayerAddPH1;
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
          if (value - 130 >= 0 && value - 130 <= 4) {
            graphics.setColor(Color.getColor("", (value - 130) * 32 << 8));
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


  public GenPHLayer(long par1) {
    super(par1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\PH\GenPHLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */