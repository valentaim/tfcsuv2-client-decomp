/*     */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Tree;
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
/*     */ public abstract class GenTreeLayer extends GenLayerTFC {
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC initialize(long par0, DataLayer[] trees) {
/*     */     GenLayerZoomTFC genLayerZoomTFC1;
/*  22 */     GenLayerTFC layer = new GenLayerTreeInit(1L, trees);
/*  23 */     drawImage(512, layer, "Tree 0");
/*  24 */     GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)layer);
/*  25 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Tree 1");
/*     */     
/*  27 */     drawImage(512, (GenLayerTFC)genLayerFuzzyZoomTFC, "Tree 2");
/*  28 */     GenLayerZoomTFC genLayerZoomTFC2 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);
/*     */     
/*  30 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 3");
/*  31 */     genLayerZoomTFC2 = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerZoomTFC2);
/*     */     
/*  33 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 4");
/*  34 */     genLayerZoomTFC2 = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerZoomTFC2);
/*     */     
/*  36 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC2, "Tree 5");
/*  37 */     GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC2);
/*  38 */     drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Tree 6");
/*  39 */     for (int zoomLevel = 0; zoomLevel < 5; zoomLevel++) {
/*     */       
/*  41 */       genLayerZoomTFC1 = new GenLayerZoomTFC((1000 + zoomLevel), (GenLayerTFC)genLayerSmoothTFC1);
/*  42 */       drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Tree " + (7 + zoomLevel));
/*     */     } 
/*     */     
/*  45 */     GenLayerSmoothTFC smoothedLayer = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC1);
/*  46 */     GenLayerVoronoiZoomTFC voronoiLayer = new GenLayerVoronoiZoomTFC(10L, (GenLayer)smoothedLayer);
/*  47 */     drawImage(512, (GenLayerTFC)genLayerZoomTFC1, "Tree Final");
/*     */     
/*  49 */     voronoiLayer.func_75905_a(par0);
/*  50 */     return (GenLayerTFC)voronoiLayer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(int size, GenLayerTFC genlayer, String name) {
/*  56 */     if (!shouldDraw) {
/*     */       return;
/*     */     }
/*     */     try {
/*  60 */       File outFile = new File(name + ".bmp");
/*  61 */       if (outFile.exists())
/*     */         return; 
/*  63 */       int[] ints = genlayer.func_75904_a(0, 0, size, size);
/*  64 */       BufferedImage outBitmap = new BufferedImage(size, size, 1);
/*  65 */       Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
/*  66 */       graphics.clearRect(0, 0, size, size);
/*  67 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  68 */       for (int x = 0; x < size; x++) {
/*     */         
/*  70 */         for (int z = 0; z < size; z++) {
/*     */           
/*  72 */           int id = ints[x * size + z];
/*  73 */           int color = (id * 8 << 16) + (id * 8 << 8) + id * 8;
/*  74 */           graphics.setColor(Color.getColor("", color));
/*  75 */           graphics.drawRect(x, z, 1, 1);
/*     */         } 
/*     */       } 
/*  78 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  79 */       ImageIO.write(outBitmap, "BMP", outFile);
/*     */     }
/*  81 */     catch (Exception e) {
/*     */       
/*  83 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GenTreeLayer(long par1) {
/*  89 */     super(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75905_a(long par1) {
/*  99 */     this.field_75907_b = par1;
/* 100 */     if (this.field_75909_a != null) {
/* 101 */       this.field_75909_a.func_75905_a(par1);
/*     */     }
/* 103 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 104 */     this.field_75907_b += this.field_75906_d;
/* 105 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 106 */     this.field_75907_b += this.field_75906_d;
/* 107 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 108 */     this.field_75907_b += this.field_75906_d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75903_a(long par1, long par3) {
/* 117 */     this.field_75908_c = this.field_75907_b;
/* 118 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 119 */     this.field_75908_c += par1;
/* 120 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 121 */     this.field_75908_c += par3;
/* 122 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 123 */     this.field_75908_c += par1;
/* 124 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 125 */     this.field_75908_c += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_75902_a(int par1) {
/* 134 */     int var2 = (int)((this.field_75908_c >> 24L) % par1);
/* 135 */     if (var2 < 0) {
/* 136 */       var2 += par1;
/*     */     }
/* 138 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 139 */     this.field_75908_c += this.field_75907_b;
/* 140 */     return var2;
/*     */   }
/*     */   
/*     */   public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Tree\GenTreeLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */