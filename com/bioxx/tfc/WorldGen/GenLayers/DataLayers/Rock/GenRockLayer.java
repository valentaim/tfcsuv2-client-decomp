/*     */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Rock;
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
/*     */ import net.minecraft.world.gen.layer.GenLayer;
/*     */ 
/*     */ public abstract class GenRockLayer
/*     */   extends GenLayerTFC {
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC initialize(long par0, DataLayer[] rocks) {
/*     */     GenLayerZoomTFC genLayerZoomTFC1;
/*  23 */     GenLayerTFC layer = new GenLayerRockInit(1L, rocks);
/*  24 */     drawImage(512, layer, "Rock 0");
/*  25 */     GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)layer);
/*  26 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Rock 1");
/*     */     
/*  28 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Rock 2");
/*  29 */     GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);
/*     */     
/*  31 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Rock 3");
/*  32 */     genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerZoomTFC2);
/*     */     
/*  34 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Rock 4");
/*  35 */     genLayerZoomTFC2 = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerZoomTFC2);
/*     */     
/*  37 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Rock 5");
/*  38 */     GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC2);
/*  39 */     drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Rock 6");
/*  40 */     for (int zoomLevel = 0; zoomLevel < 5; zoomLevel++) {
/*     */       
/*  42 */       genLayerZoomTFC1 = new GenLayerZoomTFC((1000 + zoomLevel), (GenLayerTFC)genLayerSmoothTFC1);
/*  43 */       drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Rock " + (7 + zoomLevel));
/*     */     } 
/*     */     
/*  46 */     GenLayerSmoothTFC smoothedLayer = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC1);
/*  47 */     GenLayerVoronoiZoomTFC voronoiLayer = new GenLayerVoronoiZoomTFC(10L, (GenLayer)smoothedLayer);
/*  48 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Rock Final");
/*  49 */     smoothedLayer.func_75905_a(par0);
/*  50 */     voronoiLayer.func_75905_a(par0);
/*  51 */     return (GenLayerTFC)voronoiLayer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(int size, GenLayerTFC genlayer, String name) {
/*  57 */     if (!shouldDraw) {
/*     */       return;
/*     */     }
/*     */     try {
/*  61 */       File outFile = new File(name + ".bmp");
/*  62 */       if (outFile.exists())
/*     */         return; 
/*  64 */       int[] ints = genlayer.func_75904_a(0, 0, size, size);
/*  65 */       BufferedImage outBitmap = new BufferedImage(size, size, 1);
/*  66 */       Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
/*  67 */       graphics.clearRect(0, 0, size, size);
/*  68 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  69 */       for (int x = 0; x < size; x++) {
/*     */         
/*  71 */         for (int z = 0; z < size; z++) {
/*     */           
/*  73 */           int id = ints[x * size + z];
/*  74 */           int color = (id * 8 << 16) + (id * 8 << 8) + id * 8;
/*  75 */           graphics.setColor(Color.getColor("", color));
/*  76 */           graphics.drawRect(x, z, 1, 1);
/*     */         } 
/*     */       } 
/*  79 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  80 */       ImageIO.write(outBitmap, "BMP", outFile);
/*     */     }
/*  82 */     catch (Exception e) {
/*     */       
/*  84 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GenRockLayer(long par1) {
/*  90 */     super(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75905_a(long par1) {
/* 100 */     this.field_75907_b = par1;
/* 101 */     if (this.field_75909_a != null) {
/* 102 */       this.field_75909_a.func_75905_a(par1);
/*     */     }
/* 104 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 105 */     this.field_75907_b += this.field_75906_d;
/* 106 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 107 */     this.field_75907_b += this.field_75906_d;
/* 108 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 109 */     this.field_75907_b += this.field_75906_d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75903_a(long par1, long par3) {
/* 118 */     this.field_75908_c = this.field_75907_b;
/* 119 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 120 */     this.field_75908_c += par1;
/* 121 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 122 */     this.field_75908_c += par3;
/* 123 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 124 */     this.field_75908_c += par1;
/* 125 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 126 */     this.field_75908_c += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_75902_a(int par1) {
/* 135 */     int var2 = (int)((this.field_75908_c >> 24L) % par1);
/* 136 */     if (var2 < 0) {
/* 137 */       var2 += par1;
/*     */     }
/* 139 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 140 */     this.field_75908_c += this.field_75907_b;
/* 141 */     return var2;
/*     */   }
/*     */   
/*     */   public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Rock\GenRockLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */