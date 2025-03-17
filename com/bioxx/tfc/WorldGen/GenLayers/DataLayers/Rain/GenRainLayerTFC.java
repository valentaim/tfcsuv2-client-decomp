/*     */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rain;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.WorldGen.DataLayer;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerFuzzyZoomTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerSmoothTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerVoronoiZoomTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerZoomTFC;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.world.WorldType;
/*     */ import net.minecraft.world.gen.layer.GenLayer;
/*     */ 
/*     */ 
/*     */ public abstract class GenRainLayerTFC
/*     */   extends GenLayerTFC
/*     */ {
/*  22 */   public static final int WET = DataLayer.RAIN_4000.layerID;
/*  23 */   public static final int DRY = DataLayer.RAIN_125.layerID;
/*     */   
/*     */   public static GenLayerTFC initialize(long seed, WorldType worldType) {
/*     */     GenLayerZoomTFC genLayerZoomTFC;
/*  27 */     GenLayerTFC continent = genContinent(0L);
/*  28 */     drawImage(512, continent, "Rain 0");
/*  29 */     continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
/*  30 */     GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
/*  31 */     drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Rain 1");
/*  32 */     for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {
/*     */       GenLayerRainMix genLayerRainMix;
/*  34 */       if ((zoomLevel & 0x1) == 1) {
/*     */         
/*  36 */         genLayerRainMix = new GenLayerRainMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
/*  37 */         drawImage(512, genLayerRainMix, "Rain 2-" + zoomLevel + " Mix");
/*     */       } 
/*  39 */       genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerRainMix);
/*  40 */       drawImage(512, (GenLayerTFC)genLayerZoomTFC, "Rain 2-" + zoomLevel + " Smoothed");
/*     */     } 
/*     */     
/*  43 */     GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
/*  44 */     GenLayerVoronoiZoomTFC voronoiZoom = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
/*  45 */     drawImage(512, (GenLayerTFC)finalCont, "Rain 4 Voronoi Rain");
/*  46 */     voronoiZoom.func_75905_a(seed);
/*  47 */     return (GenLayerTFC)voronoiZoom;
/*     */   }
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC genContinent(long seed) {
/*  52 */     GenLayerTFC continent = new GenLayerRainInit(1L + seed);
/*  53 */     drawImage(512, continent, "Rain Init 0");
/*  54 */     continent = new GenLayerAddRain(1L, (GenLayer)continent);
/*  55 */     drawImage(512, continent, "Rain Init 0b Add Rain");
/*  56 */     GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
/*  57 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Rain Init 1");
/*     */ 
/*     */     
/*  60 */     GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);
/*  61 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "Rain Init 3 Zoom");
/*     */ 
/*     */     
/*  64 */     GenLayerRainMix genLayerRainMix2 = new GenLayerRainMix(88L, (GenLayer)genLayerZoomTFC3);
/*  65 */     drawImage(512, genLayerRainMix2, "Rain Init 4b Mix");
/*  66 */     GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerRainMix2);
/*  67 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Rain Init 5 Zoom");
/*  68 */     GenLayerRainMix genLayerRainMix1 = new GenLayerRainMix(88L, (GenLayer)genLayerZoomTFC2);
/*  69 */     drawImage(512, genLayerRainMix1, "Rain Init 5b Mix");
/*     */ 
/*     */     
/*  72 */     GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerRainMix1);
/*  73 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Rain Init 7 Zoom");
/*  74 */     return (GenLayerTFC)genLayerZoomTFC1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(int size, GenLayerTFC genlayer, String name) {
/*  80 */     if (!shouldDraw) {
/*     */       return;
/*     */     }
/*     */     try {
/*  84 */       File outFile = new File(name + ".bmp");
/*  85 */       if (outFile.exists())
/*     */         return; 
/*  87 */       int[] ints = genlayer.func_75904_a(0, 0, size, size);
/*  88 */       BufferedImage outBitmap = new BufferedImage(size, size, 1);
/*  89 */       Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
/*  90 */       graphics.clearRect(0, 0, size, size);
/*  91 */       TerraFirmaCraft.LOG.info("Starting " + name + ".bmp");
/*  92 */       for (int x = 0; x < size; x++) {
/*     */         
/*  94 */         for (int z = 0; z < size; z++) {
/*     */           
/*  96 */           int value = ints[x * size + z];
/*  97 */           if (value - 100 >= 0) {
/*  98 */             graphics.setColor(Color.getColor("", (value - 100) * 32));
/*     */           } else {
/* 100 */             graphics.setColor(Color.getColor("", 16777215));
/* 101 */           }  graphics.drawRect(x, z, 1, 1);
/*     */         } 
/*     */       } 
/* 104 */       TerraFirmaCraft.LOG.info("Finished " + name + ".bmp");
/* 105 */       ImageIO.write(outBitmap, "BMP", outFile);
/*     */     }
/* 107 */     catch (Exception e) {
/*     */       
/* 109 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GenRainLayerTFC(long par1) {
/* 115 */     super(par1);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Rain\GenRainLayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */