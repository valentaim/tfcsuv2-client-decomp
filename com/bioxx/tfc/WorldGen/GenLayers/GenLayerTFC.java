package com.bioxx.tfc.WorldGen.GenLayers;

import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerAddIslandTFC;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerBiomeEdge;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerBiomeTFC;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerDeepOcean;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerIslandTFC;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerLakes;
import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerShoreTFC;
import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverInitTFC;
import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverMixTFC;
import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.WorldGen.TFCWorldType;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import net.minecraft.world.gen.layer.GenLayer;


public abstract class GenLayerTFC
  extends GenLayer
{
  protected long field_75907_b;
  protected GenLayerTFC field_75909_a;
  protected long field_75908_c;
  protected long field_75906_d;
  private static boolean shouldDraw;

  public static GenLayerTFC[] initialize(long par0, TFCWorldType par2) {
    GenLayerShoreTFC genLayerShoreTFC;
    GenLayerTFC continent = genContinent(0L, false);
    GenLayerDeepOcean genLayerDeepOcean = new GenLayerDeepOcean(4L, continent);
    drawImage(512, (GenLayerTFC)genLayerDeepOcean, "8b Continents Done Deep Ocean");
    byte var4 = 4;


    GenLayerTFC continentCopy2 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerDeepOcean, 0);
    drawImage(512, continentCopy2, "14 ContinentsZoom");
    GenLayerBiomeTFC genLayerBiomeTFC = new GenLayerBiomeTFC(200L, continentCopy2, par2);
    drawImage(512, (GenLayerTFC)genLayerBiomeTFC, "15 Biome");
    GenLayerLakes lakes = new GenLayerLakes(200L, (GenLayer)genLayerBiomeTFC);
    drawImage(512, (GenLayerTFC)genLayerBiomeTFC, "15b Lakes");
    continentCopy2 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)lakes, 2);
    drawImage(512, continentCopy2, "16 ZoomBiome");
    GenLayerBiomeEdge genLayerBiomeEdge = new GenLayerBiomeEdge(1000L, continentCopy2);
    drawImage(512, (GenLayerTFC)genLayerBiomeEdge, "17 BiomeEdge");
    for (int var7 = 0; var7 < var4; var7++) {
      GenLayerAddIslandTFC genLayerAddIslandTFC;
      GenLayerZoomTFC genLayerZoomTFC = new GenLayerZoomTFC((1000 + var7), (GenLayerTFC)genLayerBiomeEdge);
      drawImage(512, genLayerZoomTFC, "18-" + var7 + " Zoom");
      if (var7 == 0)
        genLayerAddIslandTFC = new GenLayerAddIslandTFC(3L, genLayerZoomTFC);
      if (var7 == 1) {

        genLayerShoreTFC = new GenLayerShoreTFC(1000L, (GenLayer)genLayerAddIslandTFC);
        drawImage(512, (GenLayerTFC)genLayerShoreTFC, "18z Shore");
      }
    }


    GenLayerTFC riverCont = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerDeepOcean, 2);
    drawImage(512, riverCont, "9 ContinentsZoom");
    GenLayerRiverInitTFC genLayerRiverInitTFC = new GenLayerRiverInitTFC(100L, riverCont);
    drawImage(512, (GenLayerTFC)genLayerRiverInitTFC, "10 RiverInit");
    GenLayerTFC genLayerTFC1 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerRiverInitTFC, 6);
    drawImage(512, genLayerTFC1, "11 RiverInitZoom");
    GenLayerRiverTFC genLayerRiverTFC = new GenLayerRiverTFC(1L, genLayerTFC1);
    drawImage(512, (GenLayerTFC)genLayerRiverTFC, "12 River");
    GenLayerSmoothTFC genLayerSmoothTFC = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerRiverTFC);
    drawImage(512, genLayerSmoothTFC, "13 SmoothRiver");

    GenLayerSmoothBiomeTFC smoothContinent = new GenLayerSmoothBiomeTFC(1000L, (GenLayer)genLayerShoreTFC);
    drawImage(512, smoothContinent, "Biome 19");
    GenLayerRiverMixTFC riverMix = new GenLayerRiverMixTFC(100L, smoothContinent, genLayerSmoothTFC);
    drawImage(512, (GenLayerTFC)riverMix, "Biome 20");
    GenLayerTFC finalCont = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)riverMix, 2);
    drawImage(512, finalCont, "Biome 20-zoom");
    finalCont = new GenLayerSmoothBiomeTFC(1001L, finalCont);
    drawImage(512, finalCont, "Biome 21");
    riverMix.func_75905_a(par0);
    finalCont.func_75905_a(par0);
    return new GenLayerTFC[] { (GenLayerTFC)riverMix, finalCont };
  }


  public static GenLayerTFC genContinent(long seed, boolean oceanReduction) {
    GenLayerIslandTFC genLayerIslandTFC = new GenLayerIslandTFC(1L + seed);
    drawImage(512, (GenLayerTFC)genLayerIslandTFC, "0 ContinentsStart");
    GenLayerFuzzyZoomTFC continentFuzzyZoom = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)genLayerIslandTFC);
    drawImage(512, continentFuzzyZoom, "1 ContinentsFuzzyZoom");
    GenLayerAddIslandTFC genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(1L, continentFuzzyZoom);
    drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "2 ContinentsAddIsland");
    GenLayerTFC var11 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerAddIslandTFC1);
    drawImage(512, var11, "3 ContinentsAddIslandZoom");
    genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(2L, var11);
    drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "4 ContinentsAddIsland2");
    var11 = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerAddIslandTFC1);
    drawImage(512, var11, "5 ContinentsAddIslandZoom2");
    genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(3L, var11);
    drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "6 ContinentsAddIsland3");
    var11 = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerAddIslandTFC1);
    drawImage(512, var11, "7 ContinentsAddIslandZoom3");
    GenLayerAddIslandTFC genLayerAddIslandTFC2 = new GenLayerAddIslandTFC(4L, var11);
    drawImage(512, (GenLayerTFC)genLayerAddIslandTFC2, "8 ContinentsDone");
    return (GenLayerTFC)genLayerAddIslandTFC2;
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

          if (ints[x * size + z] != -1 && TFCBiome.getBiomeGenArray()[ints[x * size + z]] != null) {

            graphics.setColor(Color.getColor("", TFCBiome.getBiome(ints[x * size + z]).getBiomeColor()));
            graphics.drawRect(x, z, 1, 1);
          }
        }
      }
      TerraFirmaCraft.LOG.info(name + ".bmp");
      ImageIO.write(outBitmap, "BMP", outFile);
    }
    catch (Exception e) {

      TerraFirmaCraft.LOG.catching(e);
    }
  }


  public GenLayerTFC(long par1) {
    super(par1);
    this.field_75906_d = par1;
    this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
    this.field_75906_d += par1;
    this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
    this.field_75906_d += par1;
    this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
    this.field_75906_d += par1;
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





  public static int validateInt(int[] array, int index) {
    return array[index];
  }


  public static void validateIntArray(int[] array, int xSize, int zSize) {
    for (int z = 0; z < zSize; z++) {

      for (int x = 0; x < xSize; x++) {

        if (TFCBiome.biomeList[array[x + z * xSize]] == null) {

          TerraFirmaCraft.LOG.error("Error Array garbage data: " + array[x + z * xSize]);
          return;
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\GenLayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */