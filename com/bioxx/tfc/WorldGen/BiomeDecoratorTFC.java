/*     */ package com.bioxx.tfc.WorldGen;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Climate;
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.Food.CropIndex;
/*     */ import com.bioxx.tfc.Food.CropManager;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomCactus;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomPumpkin;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomReed;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomSand;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenGrowCrops;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenWaterPlants;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeDecorator;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeDecoratorTFC
/*     */   extends BiomeDecorator
/*     */ {
/*     */   public int field_76803_B;
/*     */   public TFCBiome biome;
/*     */   public int field_76798_D;
/*     */   public int field_76832_z;
/*     */   public int field_76804_C;
/*     */   public int field_76806_I;
/*     */   public int field_76800_F;
/*     */   public int waterPlantsPerChunk;
/*     */   public int field_76799_E;
/*     */   public int lilyPadPerChunk;
/*     */   
/*     */   public BiomeDecoratorTFC(TFCBiome par1) {
/*  60 */     this.field_76803_B = 1;
/*  61 */     this.field_76798_D = 0;
/*  62 */     this.field_76832_z = 30;
/*  63 */     this.field_76800_F = 2;
/*  64 */     this.waterPlantsPerChunk = 30;
/*  65 */     this.field_76825_v = (WorldGenerator)new WorldGenCustomReed();
/*  66 */     this.field_76810_g = (WorldGenerator)new WorldGenCustomSand(7, (Block)Blocks.field_150354_m);
/*  67 */     this.biome = par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void func_150513_a(BiomeGenBase bgb) {
/*  76 */     func_76797_b();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     Random rand = new Random(this.field_76815_a.func_72905_C() + (((this.field_76814_c >> 7) - (this.field_76811_d >> 7)) * (this.field_76811_d >> 7)));
/*  83 */     int cropid = rand.nextInt(CropManager.getInstance().getTotalCrops());
/*  84 */     CropIndex crop = CropManager.getInstance().getCropFromId(cropid);
/*  85 */     WorldGenGrowCrops cropGen = new WorldGenGrowCrops(cropid);
/*     */     
/*  87 */     if (this.field_76813_b.nextInt(20) == 0 && crop != null) {
/*     */       
/*  89 */       int num = 2 + this.field_76813_b.nextInt(8);
/*  90 */       int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/*  91 */       int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/*  92 */       int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord) + 1;
/*  93 */       for (int count = 0; count < num; count++)
/*     */       {
/*  95 */         cropGen.generate(this.field_76815_a, this.field_76813_b, xCoord, zCoord, 1); } 
/*     */     } 
/*     */     int var2;
/*  98 */     for (var2 = 0; var2 < this.lilyPadPerChunk; var2++) {
/*     */       
/* 100 */       int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/* 101 */       int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/* 102 */       int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
/*     */       
/* 104 */       generateLilyPads(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
/*     */     } 
/*     */     
/* 107 */     for (var2 = 0; var2 < 10; var2++) {
/*     */       
/* 109 */       if (this.field_76813_b.nextInt(100) < 10) {
/*     */         
/* 111 */         int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/* 112 */         int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/* 113 */         int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
/* 114 */         if (TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, yCoord, zCoord) >= 25.0F) {
/* 115 */           this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
/*     */         }
/*     */       } 
/*     */     } 
/* 119 */     if (this.field_76813_b.nextInt(300) == 0) {
/*     */       
/* 121 */       int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/* 122 */       int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/* 123 */       int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
/* 124 */       (new WorldGenCustomPumpkin()).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
/*     */     } 
/*     */     
/* 127 */     for (var2 = 0; var2 < this.field_76800_F; var2++) {
/*     */       
/* 129 */       int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/* 130 */       int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/* 131 */       int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
/* 132 */       float temperature = TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, this.field_76815_a.func_72976_f(xCoord, zCoord), zCoord);
/* 133 */       float rainfall = TFC_Climate.getRainfall(this.field_76815_a, xCoord, yCoord, zCoord);
/* 134 */       if (temperature > 20.0F && rainfall < 125.0F) {
/* 135 */         (new WorldGenCustomCactus()).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
/*     */       }
/*     */     } 
/* 138 */     for (var2 = 0; var2 < this.waterPlantsPerChunk; var2++) {
/*     */       
/* 140 */       int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
/* 141 */       int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
/* 142 */       int yCoord = this.field_76815_a.func_72874_g(xCoord, zCoord) - 1;
/* 143 */       if (TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, yCoord, zCoord) >= 7.0F) {
/* 144 */         (new WorldGenWaterPlants(TFCBlocks.waterPlant)).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean generateLilyPads(World world, Random random, int x, int y, int z) {
/* 150 */     for (int l = 0; l < 10; l++) {
/*     */       
/* 152 */       int i1 = x + random.nextInt(8) - random.nextInt(8);
/* 153 */       int j1 = y + random.nextInt(4) - random.nextInt(4);
/* 154 */       int k1 = z + random.nextInt(8) - random.nextInt(8);
/*     */       
/* 156 */       if (world.func_147437_c(i1, j1, k1) && TFCBlocks.lilyPad.func_149742_c(world, i1, j1, k1) && 
/* 157 */         TFC_Core.isFreshWater(world.func_147439_a(i1, j1 - 1, k1)) && !TFC_Core.isFreshWater(world.func_147439_a(i1, j1 - 2, k1)))
/*     */       {
/* 159 */         world.func_147465_d(i1, j1, k1, TFCBlocks.lilyPad, 0, 2);
/*     */       }
/*     */     } 
/*     */     
/* 163 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_150512_a(World par1World, Random par2Random, BiomeGenBase bgb, int par3, int par4) {
/* 173 */     if (this.field_76815_a == null) {
/*     */       
/* 175 */       this.field_76815_a = par1World;
/* 176 */       this.field_76813_b = par2Random;
/* 177 */       this.field_76814_c = par3;
/* 178 */       this.field_76811_d = par4;
/* 179 */       func_150513_a(bgb);
/* 180 */       this.field_76815_a = null;
/* 181 */       this.field_76813_b = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\BiomeDecoratorTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */