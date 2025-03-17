/*     */ package com.bioxx.tfc.WorldGen.GenLayers.River;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.GenLayers.GenLayerTFC;
/*     */ import com.bioxx.tfc.WorldGen.TFCBiome;
/*     */ import net.minecraft.world.gen.layer.GenLayer;
/*     */ 
/*     */ 
/*     */ public class GenLayerRiverMixTFC
/*     */   extends GenLayerTFC
/*     */ {
/*     */   private GenLayer biomePatternGeneratorChain;
/*     */   private GenLayer riverPatternGeneratorChain;
/*     */   private int[] layerBiomes;
/*     */   private int[] layerRivers;
/*     */   private int[] layerOut;
/*     */   private int xn;
/*     */   private int xp;
/*     */   private int zn;
/*     */   private int zp;
/*     */   
/*     */   public GenLayerRiverMixTFC(long par1, GenLayer par3GenLayer, GenLayer par4GenLayer) {
/*  23 */     super(par1);
/*  24 */     this.biomePatternGeneratorChain = par3GenLayer;
/*  25 */     this.riverPatternGeneratorChain = par4GenLayer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] func_75904_a(int x, int z, int xSize, int zSize) {
/*  35 */     this.layerBiomes = this.biomePatternGeneratorChain.func_75904_a(x, z, xSize, zSize);
/*  36 */     this.layerRivers = this.riverPatternGeneratorChain.func_75904_a(x, z, xSize, zSize);
/*  37 */     this.layerOut = new int[xSize * zSize];
/*     */     
/*  39 */     for (int zElement = 0; zElement < zSize; zElement++) {
/*     */       
/*  41 */       for (int xElement = 0; xElement < xSize; xElement++) {
/*     */         
/*  43 */         int index = xElement + zElement * xSize;
/*     */         
/*  45 */         if (index < this.layerBiomes.length && index < this.layerRivers.length) {
/*  46 */           int b = this.layerBiomes[index];
/*  47 */           int r = this.layerRivers[index];
/*     */           
/*  49 */           this.xn = index - 1;
/*  50 */           this.xp = index + 1;
/*  51 */           this.zn = index - zSize;
/*  52 */           this.zp = index + zSize;
/*     */           
/*  54 */           if (TFC_Core.isOceanicBiome(b) || TFC_Core.isMountainBiome(b)) {
/*  55 */             this.layerOut[index] = b;
/*  56 */           } else if (r > 0) {
/*     */             
/*  58 */             this.layerOut[index] = r;
/*     */ 
/*     */             
/*  61 */             if (TFC_Core.isBeachBiome(b)) {
/*     */               
/*  63 */               this.layerOut[index] = TFCBiome.OCEAN.field_76756_M;
/*  64 */               if (inBounds(this.xn, this.layerOut) && this.layerOut[this.xn] == TFCBiome.RIVER.field_76756_M)
/*     */               {
/*  66 */                 this.layerOut[this.xn] = TFCBiome.OCEAN.field_76756_M;
/*     */               }
/*  68 */               if (inBounds(this.zn, this.layerOut) && this.layerOut[this.zn] == TFCBiome.RIVER.field_76756_M)
/*     */               {
/*  70 */                 this.layerOut[this.zn] = TFCBiome.OCEAN.field_76756_M;
/*     */               }
/*  72 */               if (inBounds(this.zp, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.zp]) && this.layerRivers[this.zp] == 0)
/*     */               {
/*  74 */                 this.layerOut[index] = b;
/*     */               }
/*  76 */               if (inBounds(this.zn, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.zn]) && this.layerRivers[this.zn] == 0)
/*     */               {
/*  78 */                 this.layerOut[index] = b;
/*     */               }
/*  80 */               if (inBounds(this.xn, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.xn]) && this.layerRivers[this.xn] == 0)
/*     */               {
/*  82 */                 this.layerOut[index] = b;
/*     */               }
/*  84 */               if (inBounds(this.xp, this.layerOut) && TFC_Core.isOceanicBiome(this.layerBiomes[this.xp]) && this.layerRivers[this.xp] == 0)
/*     */               {
/*  86 */                 this.layerOut[index] = b;
/*     */               }
/*     */             } 
/*     */           } else {
/*     */             
/*  91 */             this.layerOut[index] = b;
/*     */           } 
/*     */           
/*  94 */           removeRiver(index, TFCBiome.LAKE.field_76756_M);
/*  95 */           removeRiver(index, TFCBiome.MOUNTAINS_EDGE.field_76756_M);
/*     */           
/*  97 */           validateInt(this.layerOut, index);
/*     */         } 
/*     */       } 
/*     */     } 
/* 101 */     return (int[])this.layerOut.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeRiver(int index, int biomeToReplaceWith) {
/* 106 */     if (this.layerOut[index] == TFCBiome.RIVER.field_76756_M) {
/*     */       
/* 108 */       if (this.xn >= 0 && this.layerBiomes[this.xn] == biomeToReplaceWith)
/*     */       {
/* 110 */         this.layerOut[index] = biomeToReplaceWith;
/*     */       }
/* 112 */       if (this.zn >= 0 && this.layerBiomes[this.zn] == biomeToReplaceWith)
/*     */       {
/* 114 */         this.layerOut[index] = biomeToReplaceWith;
/*     */       }
/* 116 */       if (this.xp < this.layerBiomes.length && this.layerBiomes[this.xp] == biomeToReplaceWith)
/*     */       {
/* 118 */         this.layerOut[index] = biomeToReplaceWith;
/*     */       }
/* 120 */       if (this.zp < this.layerBiomes.length && this.layerBiomes[this.zp] == biomeToReplaceWith)
/*     */       {
/* 122 */         this.layerOut[index] = biomeToReplaceWith;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean inBounds(int index, int[] array) {
/* 129 */     return (index < array.length && index >= 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_75905_a(long par1) {
/* 139 */     this.biomePatternGeneratorChain.func_75905_a(par1);
/* 140 */     this.riverPatternGeneratorChain.func_75905_a(par1);
/* 141 */     super.func_75905_a(par1);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\River\GenLayerRiverMixTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */