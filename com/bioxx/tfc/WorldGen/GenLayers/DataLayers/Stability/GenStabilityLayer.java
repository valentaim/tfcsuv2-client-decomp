/*     */ package com.bioxx.tfc.WorldGen.GenLayers.DataLayers.Stability;
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
/*     */ public abstract class GenStabilityLayer
/*     */   extends GenLayerTFC {
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC initialize(long par0, WorldType par2WorldType) {
/*     */     GenLayerZoomTFC genLayerZoomTFC;
/*  24 */     GenLayerTFC continent = genContinent(par0);
/*  25 */     drawImage(512, continent, "Stability 0");
/*  26 */     continent = GenLayerZoomTFC.magnify(1000L, continent, 2);
/*  27 */     GenLayerSmoothTFC genLayerSmoothTFC1 = new GenLayerSmoothTFC(1000L, (GenLayer)continent);
/*  28 */     drawImage(512, (GenLayerTFC)genLayerSmoothTFC1, "Stability 1");
/*  29 */     for (int zoomLevel = 0; zoomLevel < 4; zoomLevel++) {
/*     */       
/*  31 */       genLayerZoomTFC = new GenLayerZoomTFC((1000 + zoomLevel), (GenLayerTFC)genLayerSmoothTFC1);
/*  32 */       drawImage(512, (GenLayerTFC)genLayerZoomTFC, "Stability 2-" + zoomLevel);
/*     */     } 
/*     */     
/*  35 */     GenLayerSmoothTFC finalCont = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerZoomTFC);
/*  36 */     drawImage(512, (GenLayerTFC)finalCont, "Stability 3");
/*  37 */     GenLayerVoronoiZoomTFC voronoiZoom = new GenLayerVoronoiZoomTFC(10L, (GenLayer)finalCont);
/*  38 */     voronoiZoom.func_75905_a(par0);
/*  39 */     return (GenLayerTFC)voronoiZoom;
/*     */   }
/*     */ 
/*     */   
/*     */   public static GenLayerTFC genContinent(long seed) {
/*  44 */     GenLayerTFC continent = new GenLayerStabilityInit(1L + seed);
/*  45 */     GenLayerFuzzyZoomTFC genLayerFuzzyZoomTFC = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)continent);
/*     */     
/*  47 */     GenLayerZoomTFC genLayerZoomTFC = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerFuzzyZoomTFC);
/*     */     
/*  49 */     genLayerZoomTFC = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerZoomTFC);
/*     */     
/*  51 */     genLayerZoomTFC = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerZoomTFC);
/*     */     
/*  53 */     return (GenLayerTFC)genLayerZoomTFC;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(int size, GenLayerTFC genlayer, String name) {
/*  59 */     if (!shouldDraw) {
/*     */       return;
/*     */     }
/*     */     try {
/*  63 */       File outFile = new File(name + ".bmp");
/*  64 */       if (outFile.exists())
/*     */         return; 
/*  66 */       int[] ints = genlayer.func_75904_a(0, 0, size, size);
/*  67 */       BufferedImage outBitmap = new BufferedImage(size, size, 1);
/*  68 */       Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
/*  69 */       graphics.clearRect(0, 0, size, size);
/*  70 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  71 */       for (int x = 0; x < size; x++) {
/*     */         
/*  73 */         for (int z = 0; z < size; z++) {
/*     */           
/*  75 */           int id = (DataLayer.layers[ints[x * size + z]]).data1;
/*  76 */           graphics.setColor(Color.getColor("", (id * 255 << 16) + (id * 255 << 8) + id * 255));
/*  77 */           graphics.drawRect(x, z, 1, 1);
/*     */         } 
/*     */       } 
/*  80 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/*  81 */       ImageIO.write(outBitmap, "BMP", outFile);
/*     */     }
/*  83 */     catch (Exception e) {
/*     */       
/*  85 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GenStabilityLayer(long par1) {
/*  91 */     super(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75905_a(long par1) {
/* 101 */     this.field_75907_b = par1;
/* 102 */     if (this.field_75909_a != null) {
/* 103 */       this.field_75909_a.func_75905_a(par1);
/*     */     }
/* 105 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 106 */     this.field_75907_b += this.field_75906_d;
/* 107 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 108 */     this.field_75907_b += this.field_75906_d;
/* 109 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 110 */     this.field_75907_b += this.field_75906_d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75903_a(long par1, long par3) {
/* 119 */     this.field_75908_c = this.field_75907_b;
/* 120 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 121 */     this.field_75908_c += par1;
/* 122 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 123 */     this.field_75908_c += par3;
/* 124 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 125 */     this.field_75908_c += par1;
/* 126 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 127 */     this.field_75908_c += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_75902_a(int par1) {
/* 136 */     int var2 = (int)((this.field_75908_c >> 24L) % par1);
/* 137 */     if (var2 < 0)
/* 138 */       var2 += par1; 
/* 139 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 140 */     this.field_75908_c += this.field_75907_b;
/* 141 */     return var2;
/*     */   }
/*     */   
/*     */   public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\DataLayers\Stability\GenStabilityLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */