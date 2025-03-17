/*     */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.EVT;
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
/*     */ public abstract class GenEVTLayer
/*     */   extends GenLayerTFC
/*     */ {
/*  22 */   public static final int LOW = DataLayer.EVT_0_25.layerID;
/*  23 */   public static final int HIGH = DataLayer.EVT_8.layerID;
/*     */   
/*     */   public static GenLayerTFC initialize(long seed, WorldType worldType) {
/*     */     GenLayerZoomTFC genLayerZoomTFC;
/*  27 */     GenLayerTFC continent = genContinent(0L);
/*  28 */     drawImage(512, continent, "EVT 0");
/*  29 */     continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
/*  30 */     GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
/*  31 */     drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "EVT 1");
/*  32 */     for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {
/*     */       GenLayerEVTMix genLayerEVTMix;
/*  34 */       if (zoomLevel == 0) {
/*     */         
/*  36 */         genLayerEVTMix = new GenLayerEVTMix((1000 + zoomLevel), (GenLayer)genLayerSmoothTFC1);
/*  37 */         drawImage(512, genLayerEVTMix, "EVT 2-" + zoomLevel + " Mix");
/*     */       } 
/*  39 */       genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), genLayerEVTMix);
/*  40 */       drawImage(512, (GenLayerTFC)genLayerZoomTFC, "EVT 2-" + zoomLevel + " Smoothed");
/*     */     } 
/*     */     
/*  43 */     GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
/*  44 */     GenLayerVoronoiZoomTFC genLayerVoronoiZoomTFC = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
/*  45 */     drawImage(512, (GenLayerTFC)genLayerVoronoiZoomTFC, "EVT 4 Voronoi EVT");
/*  46 */     genLayerVoronoiZoomTFC.func_75905_a(seed);
/*  47 */     return (GenLayerTFC)genLayerVoronoiZoomTFC;
/*     */   }
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC genContinent(long seed) {
/*  52 */     GenLayerTFC continent = new GenLayerEVTInit(1L + seed);
/*  53 */     drawImage(512, continent, "EVT Init 0");
/*  54 */     continent = new GenLayerAddEVT(1L, (GenLayer)continent);
/*  55 */     drawImage(512, continent, "EVT Init 0b Add EVT");
/*  56 */     GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
/*  57 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "EVT Init 1");
/*  58 */     GenLayerAddEVT genLayerAddEVT4 = new GenLayerAddEVT(1L, (GenLayer)genLayerFuzzyZoomTFC);
/*  59 */     drawImage(512, genLayerAddEVT4, "EVT Init 2 Add EVT");
/*  60 */     GenLayerZoomTFC genLayerZoomTFC3 = new GenLayerZoomTFC(2001L, genLayerAddEVT4);
/*  61 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC3, "EVT Init 3 Zoom");
/*  62 */     GenLayerAddEVT genLayerAddEVT3 = new GenLayerAddEVT(2L, (GenLayer)genLayerZoomTFC3);
/*  63 */     drawImage(512, genLayerAddEVT3, "EVT Init 4 Add EVT");
/*  64 */     GenLayerEVTMix genLayerEVTMix = new GenLayerEVTMix(88L, (GenLayer)genLayerAddEVT3);
/*  65 */     drawImage(512, genLayerEVTMix, "EVT Init 4b Mix");
/*  66 */     GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, genLayerEVTMix);
/*  67 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "EVT Init 5 Zoom");
/*  68 */     GenLayerAddEVT genLayerAddEVT2 = new GenLayerAddEVT(3L, (GenLayer)genLayerZoomTFC2);
/*  69 */     drawImage(512, genLayerAddEVT2, "EVT Init 6 Add EVT");
/*  70 */     GenLayerZoomTFC genLayerZoomTFC1 = new GenLayerZoomTFC(2003L, genLayerAddEVT2);
/*  71 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "EVT Init 7 Zoom");
/*  72 */     GenLayerAddEVT genLayerAddEVT1 = new GenLayerAddEVT(4L, (GenLayer)genLayerZoomTFC1);
/*  73 */     drawImage(512, genLayerAddEVT1, "EVT Init 8 Add EVT");
/*  74 */     return genLayerAddEVT1;
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
/*  97 */           if (value - 80 >= 0 && value - 80 <= 7) {
/*  98 */             graphics.setColor(Color.getColor("", (value - 80) * 32 << 16));
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
/*     */   public GenEVTLayer(long par1) {
/* 115 */     super(par1);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\EVT\GenEVTLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */