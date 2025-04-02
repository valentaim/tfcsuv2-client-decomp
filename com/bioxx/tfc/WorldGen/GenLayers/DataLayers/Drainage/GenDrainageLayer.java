package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Drainage;

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


public abstract class GenDrainageLayer
  extends GenLayerTFC
{
  public static final int MIN = DataLayer.DRAINAGE_NONE.layerID;
  public static final int MAX = DataLayer.DRAINAGE_VERY_GOOD.layerID;
  public static GenLayerTFC initialize(long seed, WorldType worldType) {
    GenLayerZoomTFC genLayerZoomTFC;
    GenLayerTFC continent = genContinent(0L);
    drawImage(512, continent, "Drainage 0");
    continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
    GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Drainage 1");
    for (int zoomLevel = 0; zoomLevel < 2; zoomLevel++) {
      GenLayerDrainageMix genLayerDrainageMix;
      if (zoomLevel == 0) {

        genLayerDrainageMix = new GenLayerDrainageMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
        drawImage(512, genLayerDrainageMix, "Drainage 2-" + zoomLevel + " Mix");
      }
      genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerDrainageMix);
      drawImage(512, (GenLayerTFC)genLayerZoomTFC, "Drainage 2-" + zoomLevel + " Smoothed");
    }

    GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);

    drawImage(512, (GenLayerTFC)finalCont, "Drainage Final");
    finalCont.func_75905_a(seed);
    return (GenLayerTFC)finalCont;
  }
  private static boolean shouldDraw;

  public static GenLayerTFC genContinent(long seed) {
    GenLayerTFC continent = new GenLayerDrainageInit(1L + seed);
    drawImage(512, continent, "Drainage Init 0");
    continent = new GenLayerAddDrainage(1L, (GenLayer)continent);
    drawImage(512, continent, "Drainage Init 0b Add Drainage");
    GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
    drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Drainage Init 1");
    GenLayerAddDrainage genLayerAddDrainage4 = new GenLayerAddDrainage(1L, (GenLayer)genLayerFuzzyZoomTFC);
    drawImage(512, genLayerAddDrainage4, "Drainage Init 2 Add Drainage");
    GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, genLayerAddDrainage4);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "Drainage Init 3 Zoom");
    GenLayerAddDrainage genLayerAddDrainage3 = new GenLayerAddDrainage(2L, (GenLayer)genLayerZoomTFC3);
    drawImage(512, genLayerAddDrainage3, "Drainage Init 4 Add Drainage");
    GenLayerDrainageMix genLayerDrainageMix = new GenLayerDrainageMix(88L, (GenLayer)genLayerAddDrainage3);
    drawImage(512, genLayerDrainageMix, "Drainage Init 4b Mix");
    GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerDrainageMix);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Drainage Init 5 Zoom");
    GenLayerAddDrainage genLayerAddDrainage2 = new GenLayerAddDrainage(3L, (GenLayer)genLayerZoomTFC2);
    drawImage(512, genLayerAddDrainage2, "Drainage Init 6 Add Drainage");
    GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerAddDrainage2);
    drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Drainage Init 7 Zoom");
    GenLayerAddDrainage genLayerAddDrainage1 = new GenLayerAddDrainage(4L, (GenLayer)genLayerZoomTFC1);
    drawImage(512, genLayerAddDrainage1, "Drainage Init 8 Add Drainage");
    return genLayerAddDrainage1;
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
      Graphics2D graDrainageics = (Graphics2D)outBitmap.getGraphics();
      graDrainageics.clearRect(0, 0, size, size);
      TerraFirmaCraft.LOG.info("Starting " + name + ".bmp");
      for (int x = 0; x < size; x++) {

        for (int z = 0; z < size; z++) {

          int value = ints[x * size + z];
          int r = (value - 120) * 42 / 2 << 16;
          int g = (value - 120) * 42 / 4 << 8;
          if (value - 120 >= 0 && value - 120 <= 5) {
            graDrainageics.setColor(Color.getColor("", r + g));
          } else {
            graDrainageics.setColor(Color.getColor("", 16777215));
          }  graDrainageics.drawRect(x, z, 1, 1);
        }
      }
      TerraFirmaCraft.LOG.info("Finished " + name + ".bmp");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public GenDrainageLayer(long par1) {
    super(par1);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Drainage\GenDrainageLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */