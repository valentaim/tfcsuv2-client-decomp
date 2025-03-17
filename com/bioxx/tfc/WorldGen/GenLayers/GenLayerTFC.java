/*     */ package com.bioxx.tfc.WorldGen.GenLayers;
/*     */ 
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerAddIslandTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerBiomeEdge;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerBiomeTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerDeepOcean;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerIslandTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerLakes;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.Biome.GenLayerShoreTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverInitTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverMixTFC;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.River.GenLayerRiverTFC;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import com.bioxx.tfc.WorldGen.TFCWorldType;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import javax.imageio.ImageIO;
/*     */ import net.minecraft.world.gen.layer.GenLayer;
/*     */ 
/*     */ 
/*     */ public abstract class GenLayerTFC
/*     */   extends GenLayer
/*     */ {
/*     */   protected long field_75907_b;
/*     */   protected GenLayerTFC field_75909_a;
/*     */   protected long field_75908_c;
/*     */   protected long field_75906_d;
/*     */   private static boolean shouldDraw;
/*     */   
/*     */   public static GenLayerTFC[] initialize(long par0, TFCWorldType par2) {
/*     */     GenLayerShoreTFC genLayerShoreTFC;
/*  35 */     GenLayerTFC continent = genContinent(0L, false);
/*  36 */     GenLayerDeepOcean genLayerDeepOcean = new GenLayerDeepOcean(4L, continent);
/*  37 */     drawImage(512, (GenLayerTFC)genLayerDeepOcean, "8b Continents Done Deep Ocean");
/*  38 */     byte var4 = 4;
/*     */ 
/*     */     
/*  41 */     GenLayerTFC continentCopy2 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerDeepOcean, 0);
/*  42 */     drawImage(512, continentCopy2, "14 ContinentsZoom");
/*  43 */     GenLayerBiomeTFC genLayerBiomeTFC = new GenLayerBiomeTFC(200L, continentCopy2, par2);
/*  44 */     drawImage(512, (GenLayerTFC)genLayerBiomeTFC, "15 Biome");
/*  45 */     GenLayerLakes lakes = new GenLayerLakes(200L, (GenLayer)genLayerBiomeTFC);
/*  46 */     drawImage(512, (GenLayerTFC)genLayerBiomeTFC, "15b Lakes");
/*  47 */     continentCopy2 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)lakes, 2);
/*  48 */     drawImage(512, continentCopy2, "16 ZoomBiome");
/*  49 */     GenLayerBiomeEdge genLayerBiomeEdge = new GenLayerBiomeEdge(1000L, continentCopy2);
/*  50 */     drawImage(512, (GenLayerTFC)genLayerBiomeEdge, "17 BiomeEdge");
/*  51 */     for (int var7 = 0; var7 < var4; var7++) {
/*     */       GenLayerAddIslandTFC genLayerAddIslandTFC;
/*  53 */       GenLayerZoomTFC genLayerZoomTFC = new GenLayerZoomTFC((1000 + var7), (GenLayerTFC)genLayerBiomeEdge);
/*  54 */       drawImage(512, genLayerZoomTFC, "18-" + var7 + " Zoom");
/*  55 */       if (var7 == 0)
/*  56 */         genLayerAddIslandTFC = new GenLayerAddIslandTFC(3L, genLayerZoomTFC); 
/*  57 */       if (var7 == 1) {
/*     */         
/*  59 */         genLayerShoreTFC = new GenLayerShoreTFC(1000L, (GenLayer)genLayerAddIslandTFC);
/*  60 */         drawImage(512, (GenLayerTFC)genLayerShoreTFC, "18z Shore");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  65 */     GenLayerTFC riverCont = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerDeepOcean, 2);
/*  66 */     drawImage(512, riverCont, "9 ContinentsZoom");
/*  67 */     GenLayerRiverInitTFC genLayerRiverInitTFC = new GenLayerRiverInitTFC(100L, riverCont);
/*  68 */     drawImage(512, (GenLayerTFC)genLayerRiverInitTFC, "10 RiverInit");
/*  69 */     GenLayerTFC genLayerTFC1 = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)genLayerRiverInitTFC, 6);
/*  70 */     drawImage(512, genLayerTFC1, "11 RiverInitZoom");
/*  71 */     GenLayerRiverTFC genLayerRiverTFC = new GenLayerRiverTFC(1L, genLayerTFC1);
/*  72 */     drawImage(512, (GenLayerTFC)genLayerRiverTFC, "12 River");
/*  73 */     GenLayerSmoothTFC genLayerSmoothTFC = new GenLayerSmoothTFC(1000L, (GenLayer)genLayerRiverTFC);
/*  74 */     drawImage(512, genLayerSmoothTFC, "13 SmoothRiver");
/*     */     
/*  76 */     GenLayerSmoothBiomeTFC smoothContinent = new GenLayerSmoothBiomeTFC(1000L, (GenLayer)genLayerShoreTFC);
/*  77 */     drawImage(512, smoothContinent, "Biome 19");
/*  78 */     GenLayerRiverMixTFC riverMix = new GenLayerRiverMixTFC(100L, smoothContinent, genLayerSmoothTFC);
/*  79 */     drawImage(512, (GenLayerTFC)riverMix, "Biome 20");
/*  80 */     GenLayerTFC finalCont = GenLayerZoomTFC.magnify(1000L, (GenLayerTFC)riverMix, 2);
/*  81 */     drawImage(512, finalCont, "Biome 20-zoom");
/*  82 */     finalCont = new GenLayerSmoothBiomeTFC(1001L, finalCont);
/*  83 */     drawImage(512, finalCont, "Biome 21");
/*  84 */     riverMix.func_75905_a(par0);
/*  85 */     finalCont.func_75905_a(par0);
/*  86 */     return new GenLayerTFC[] { (GenLayerTFC)riverMix, finalCont };
/*     */   }
/*     */ 
/*     */   
/*     */   public static GenLayerTFC genContinent(long seed, boolean oceanReduction) {
/*  91 */     GenLayerIslandTFC genLayerIslandTFC = new GenLayerIslandTFC(1L + seed);
/*  92 */     drawImage(512, (GenLayerTFC)genLayerIslandTFC, "0 ContinentsStart");
/*  93 */     GenLayerFuzzyZoomTFC continentFuzzyZoom = new GenLayerFuzzyZoomTFC(2000L, (GenLayer)genLayerIslandTFC);
/*  94 */     drawImage(512, continentFuzzyZoom, "1 ContinentsFuzzyZoom");
/*  95 */     GenLayerAddIslandTFC genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(1L, continentFuzzyZoom);
/*  96 */     drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "2 ContinentsAddIsland");
/*  97 */     GenLayerTFC var11 = new GenLayerZoomTFC(2001L, (GenLayerTFC)genLayerAddIslandTFC1);
/*  98 */     drawImage(512, var11, "3 ContinentsAddIslandZoom");
/*  99 */     genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(2L, var11);
/* 100 */     drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "4 ContinentsAddIsland2");
/* 101 */     var11 = new GenLayerZoomTFC(2002L, (GenLayerTFC)genLayerAddIslandTFC1);
/* 102 */     drawImage(512, var11, "5 ContinentsAddIslandZoom2");
/* 103 */     genLayerAddIslandTFC1 = new GenLayerAddIslandTFC(3L, var11);
/* 104 */     drawImage(512, (GenLayerTFC)genLayerAddIslandTFC1, "6 ContinentsAddIsland3");
/* 105 */     var11 = new GenLayerZoomTFC(2003L, (GenLayerTFC)genLayerAddIslandTFC1);
/* 106 */     drawImage(512, var11, "7 ContinentsAddIslandZoom3");
/* 107 */     GenLayerAddIslandTFC genLayerAddIslandTFC2 = new GenLayerAddIslandTFC(4L, var11);
/* 108 */     drawImage(512, (GenLayerTFC)genLayerAddIslandTFC2, "8 ContinentsDone");
/* 109 */     return (GenLayerTFC)genLayerAddIslandTFC2;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void drawImage(int size, GenLayerTFC genlayer, String name) {
/* 115 */     if (!shouldDraw) {
/*     */       return;
/*     */     }
/*     */     try {
/* 119 */       File outFile = new File(name + ".bmp");
/* 120 */       if (outFile.exists())
/*     */         return; 
/* 122 */       int[] ints = genlayer.func_75904_a(0, 0, size, size);
/* 123 */       BufferedImage outBitmap = new BufferedImage(size, size, 1);
/* 124 */       Graphics2D graphics = (Graphics2D)outBitmap.getGraphics();
/* 125 */       graphics.clearRect(0, 0, size, size);
/* 126 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/* 127 */       for (int x = 0; x < size; x++) {
/*     */         
/* 129 */         for (int z = 0; z < size; z++) {
/*     */           
/* 131 */           if (ints[x * size + z] != -1 && TFCBiome.getBiomeGenArray()[ints[x * size + z]] != null) {
/*     */             
/* 133 */             graphics.setColor(Color.getColor("", TFCBiome.getBiome(ints[x * size + z]).getBiomeColor()));
/* 134 */             graphics.drawRect(x, z, 1, 1);
/*     */           } 
/*     */         } 
/*     */       } 
/* 138 */       TerraFirmaCraft.LOG.info(name + ".bmp");
/* 139 */       ImageIO.write(outBitmap, "BMP", outFile);
/*     */     }
/* 141 */     catch (Exception e) {
/*     */       
/* 143 */       TerraFirmaCraft.LOG.catching(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GenLayerTFC(long par1) {
/* 149 */     super(par1);
/* 150 */     this.field_75906_d = par1;
/* 151 */     this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
/* 152 */     this.field_75906_d += par1;
/* 153 */     this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
/* 154 */     this.field_75906_d += par1;
/* 155 */     this.field_75906_d *= this.field_75906_d * 6364136223846793005L + 1442695040888963407L;
/* 156 */     this.field_75906_d += par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75905_a(long par1) {
/* 167 */     this.field_75907_b = par1;
/* 168 */     if (this.field_75909_a != null) {
/* 169 */       this.field_75909_a.func_75905_a(par1);
/*     */     }
/* 171 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 172 */     this.field_75907_b += this.field_75906_d;
/* 173 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 174 */     this.field_75907_b += this.field_75906_d;
/* 175 */     this.field_75907_b *= this.field_75907_b * 6364136223846793005L + 1442695040888963407L;
/* 176 */     this.field_75907_b += this.field_75906_d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75903_a(long par1, long par3) {
/* 185 */     this.field_75908_c = this.field_75907_b;
/* 186 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 187 */     this.field_75908_c += par1;
/* 188 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 189 */     this.field_75908_c += par3;
/* 190 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 191 */     this.field_75908_c += par1;
/* 192 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 193 */     this.field_75908_c += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int func_75902_a(int par1) {
/* 202 */     int var2 = (int)((this.field_75908_c >> 24L) % par1);
/* 203 */     if (var2 < 0)
/* 204 */       var2 += par1; 
/* 205 */     this.field_75908_c *= this.field_75908_c * 6364136223846793005L + 1442695040888963407L;
/* 206 */     this.field_75908_c += this.field_75907_b;
/* 207 */     return var2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int[] func_75904_a(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int validateInt(int[] array, int index) {
/* 221 */     return array[index];
/*     */   }
/*     */ 
/*     */   
/*     */   public static void validateIntArray(int[] array, int xSize, int zSize) {
/* 226 */     for (int z = 0; z < zSize; z++) {
/*     */       
/* 228 */       for (int x = 0; x < xSize; x++) {
/*     */         
/* 230 */         if (TFCBiome.biomeList[array[x + z * xSize]] == null) {
/*     */           
/* 232 */           TerraFirmaCraft.LOG.error("Error Array garbage data: " + array[x + z * xSize]);
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\GenLayerTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */