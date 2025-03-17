/*     */ package com.bioxx.tfc.WorldGen;
/*     */ 
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityDeer;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySlimeTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
/*     */ import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
/*     */ import com.bioxx.tfc.TerraFirmaCraft;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.LOTRWorldGenPine;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.LOTRWorldGenWillow;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenBOPTaiga2;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenBulbTree;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenCyprusTree;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenSacredOak;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenAcaciaKoaTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomBigTree;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomCedarTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomMapleShortTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomMapleTallTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomShortTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomTallTrees;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenDouglasFir;
/*     */ import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenRedwoodXL;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Random;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityCreature;
/*     */ import net.minecraft.entity.EntityLiving;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.util.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.biome.BiomeDecorator;
/*     */ import net.minecraft.world.biome.BiomeGenBase;
/*     */ import net.minecraft.world.gen.feature.WorldGenerator;
/*     */ 
/*     */ public class TFCBiome
/*     */   extends BiomeGenBase {
/*  43 */   public static float riverDepthMin = -0.5F;
/*  44 */   public static float riverDepthMax = -0.3F;
/*     */   
/*     */   public float temperatureTFC;
/*     */   
/*     */   public BiomeDecoratorTFC field_76760_I;
/*  49 */   public static TFCBiome[] biomeList = new TFCBiome[256];
/*     */ 
/*     */   
/*  52 */   public static final TFCBiome OCEAN = (new TFCBiome(0)).setBiomeName("Ocean").setMinMaxHeight(-0.9F, 1.0E-5F).setBiomeColor(255);
/*  53 */   public static final TFCBiome RIVER = (new TFCBiome(7)).setBiomeName("River").setMinMaxHeight(riverDepthMin, riverDepthMax).setBiomeColor(16777215);
/*  54 */   public static final TFCBiome HELL = (new TFCBiome(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
/*  55 */   public static final TFCBiome BEACH = (new TFCBiome(16)).setColor(16440917).setBiomeName("Beach").setMinMaxHeight(0.01F, 0.02F).setBiomeColor(16758899);
/*  56 */   public static final TFCBiome GRAVEL_BEACH = (new TFCBiome(17)).setColor(16440917).setBiomeName("Gravel Beach").setMinMaxHeight(0.01F, 0.02F).setBiomeColor(9402723);
/*  57 */   public static final TFCBiome HIGH_HILLS = (new TFCBiome(3)).setBiomeName("High Hills").setMinMaxHeight(0.8F, 1.6F).setBiomeColor(282407);
/*  58 */   public static final TFCBiome PLAINS = (new TFCBiome(1)).setBiomeName("Plains").setMinMaxHeight(0.1F, 0.16F).setBiomeColor(6938528);
/*  59 */   public static final TFCBiome SWAMPLAND = (new TFCBiome(6)).setBiomeName("Swamp").setMinMaxHeight(-0.1F, 0.1F).setBiomeColor(2046251).setLilyPads(8).setWaterPlants(45);
/*  60 */   public static final TFCBiome HIGH_HILLS_EDGE = (new TFCBiome(20)).setBiomeName("High Hills Edge").setMinMaxHeight(0.2F, 0.4F).setBiomeColor(3188583);
/*  61 */   public static final TFCBiome ROLLING_HILLS = (new TFCBiome(30)).setBiomeName("Rolling Hills").setMinMaxHeight(0.1F, 0.4F).setBiomeColor(8893492);
/*  62 */   public static final TFCBiome MOUNTAINS = (new TFCBiome(31)).setBiomeName("Mountains").setMinMaxHeight(0.8F, 1.6F).setBiomeColor(7371104);
/*  63 */   public static final TFCBiome MOUNTAINS_EDGE = (new TFCBiome(32)).setBiomeName("Mountains Edge").setMinMaxHeight(0.4F, 0.8F).setBiomeColor(11713695);
/*  64 */   public static final TFCBiome HIGH_PLAINS = (new TFCBiome(35)).setBiomeName("High Plains").setMinMaxHeight(0.4F, 0.43F).setBiomeColor(10920988);
/*  65 */   public static final TFCBiome DEEP_OCEAN = (new TFCBiome(36)).setBiomeName("Deep Ocean").setMinMaxHeight(-0.9F, 1.0E-5F).setBiomeColor(918874);
/*  66 */   public static final TFCBiome LAKE = (new TFCBiome(2)).setBiomeName("Lake").setMinMaxHeight(-0.5F, 0.001F).setBiomeColor(4886174).setLilyPads(2);
/*     */   
/*     */   protected static WorldGenAcaciaKoaTrees worldGenAcaciaKoaTrees;
/*     */   
/*     */   protected static WorldGenCustomTallTrees worldGenAshTallTrees;
/*     */   
/*     */   protected static WorldGenBulbTree worldGenAspenTallTrees;
/*     */   
/*     */   protected static WorldGenBulbTree worldGenBirchTallTrees;
/*     */   
/*     */   protected static WorldGenSacredOak worldGenChestnutTallTrees;
/*     */   protected static WorldGenDouglasFir worldGenDouglasFirTallTrees;
/*     */   protected static WorldGenSacredOak worldGenHickoryTallTrees;
/*     */   protected static WorldGenCustomMapleTallTrees worldGenMapleTallTrees;
/*     */   protected static WorldGenSacredOak worldGenOakTallTrees;
/*     */   protected static LOTRWorldGenPine worldGenPineTallTrees;
/*     */   protected static WorldGenRedwoodXL worldGenRedwoodTallTrees;
/*     */   protected static WorldGenBOPTaiga2 worldGenSpruceTallTrees;
/*     */   protected static WorldGenSacredOak worldGenSycamoreTallTrees;
/*     */   protected static WorldGenCustomCedarTrees worldGenWhiteCedarTallTrees;
/*     */   protected static WorldGenCustomTallTrees worldGenWhiteElmTallTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenAshShortTrees;
/*     */   protected static WorldGenBulbTree worldGenAspenShortTrees;
/*     */   protected static WorldGenBulbTree worldGenBirchShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenChestnutShortTrees;
/*     */   protected static WorldGenDouglasFir worldGenDouglasFirShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenHickoryShortTrees;
/*     */   protected static WorldGenCustomMapleShortTrees worldGenMapleShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenOakShortTrees;
/*     */   protected static LOTRWorldGenPine worldGenPineShortTrees;
/*     */   protected static WorldGenRedwoodXL worldGenRedwoodShortTrees;
/*     */   protected static WorldGenBOPTaiga2 worldGenSpruceShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenSycamoreShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenWhiteElmShortTrees;
/*     */   protected static LOTRWorldGenWillow worldGenWillowShortTrees;
/*     */   protected static WorldGenCustomShortTrees worldGenTestTree;
/*     */   protected static WorldGenCyprusTree worldGenCyprusTrees;
/*     */   protected static WorldGenSacredOak worldGenOakSacredTrees;
/*     */   protected int biomeColor;
/*     */   
/*     */   public TFCBiome(int par1) {
/* 107 */     super(par1);
/*     */     
/* 109 */     this.field_76752_A = (Block)Blocks.field_150349_c;
/* 110 */     this.field_76753_B = Blocks.field_150346_d;
/* 111 */     this.field_76748_D = 0.1F;
/* 112 */     this.field_76749_E = 0.3F;
/* 113 */     this.temperatureTFC = 0.5F;
/* 114 */     this.field_76751_G = 0.5F;
/* 115 */     this.field_76761_J = new ArrayList();
/* 116 */     this.field_76762_K = new ArrayList();
/* 117 */     this.field_76755_L = new ArrayList();
/*     */     
/* 119 */     worldGenAcaciaKoaTrees = new WorldGenAcaciaKoaTrees(false, 0);
/* 120 */     worldGenAshTallTrees = new WorldGenCustomTallTrees(false, 7);
/* 121 */     worldGenAspenTallTrees = new WorldGenBulbTree(1, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 5, false);
/* 122 */     worldGenBirchTallTrees = new WorldGenBulbTree(2, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 5, false);
/* 123 */     worldGenChestnutTallTrees = new WorldGenSacredOak(false, 3, 1.5D, 5, 16, 4);
/* 124 */     worldGenDouglasFirTallTrees = new WorldGenDouglasFir(false, 4, true);
/* 125 */     worldGenHickoryTallTrees = new WorldGenSacredOak(false, 5, 1.5D, 5, 16, 4);
/* 126 */     worldGenMapleTallTrees = new WorldGenCustomMapleTallTrees(false, 6);
/* 127 */     worldGenOakTallTrees = new WorldGenSacredOak(false, 0, 1.5D, 5, 16, 4);
/* 128 */     worldGenPineTallTrees = new LOTRWorldGenPine(false, 8, 20, 25);
/* 129 */     worldGenRedwoodTallTrees = new WorldGenRedwoodXL(false);
/* 130 */     worldGenSpruceTallTrees = new WorldGenBOPTaiga2(10, TFCBlocks.logNatural, TFCBlocks.leaves, false, 20, 15, 4, 4);
/* 131 */     worldGenSycamoreTallTrees = new WorldGenSacredOak(false, 11, 2.0D, 4, 16, 4);
/* 132 */     worldGenWhiteCedarTallTrees = new WorldGenCustomCedarTrees(false, 12);
/* 133 */     worldGenWhiteElmTallTrees = new WorldGenCustomTallTrees(false, 13);
/*     */     
/* 135 */     worldGenAshShortTrees = new WorldGenCustomShortTrees(false, 7);
/* 136 */     worldGenAspenShortTrees = new WorldGenBulbTree(1, TFCBlocks.logNatural, TFCBlocks.leaves, false, 10, 3, false);
/* 137 */     worldGenBirchShortTrees = new WorldGenBulbTree(2, TFCBlocks.logNatural, TFCBlocks.leaves, false, 10, 3, false);
/* 138 */     worldGenChestnutShortTrees = new WorldGenCustomShortTrees(false, 3);
/* 139 */     worldGenDouglasFirShortTrees = new WorldGenDouglasFir(false, 4, false);
/* 140 */     worldGenHickoryShortTrees = new WorldGenCustomShortTrees(false, 5);
/* 141 */     worldGenMapleShortTrees = new WorldGenCustomMapleShortTrees(false, 6);
/* 142 */     worldGenOakShortTrees = new WorldGenCustomShortTrees(false, 0);
/* 143 */     worldGenPineShortTrees = new LOTRWorldGenPine(false, 8, 15, 20);
/* 144 */     worldGenRedwoodShortTrees = new WorldGenRedwoodXL(false);
/* 145 */     worldGenSpruceShortTrees = new WorldGenBOPTaiga2(10, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 10, 4, 4);
/* 146 */     worldGenSycamoreShortTrees = new WorldGenCustomShortTrees(false, 11);
/* 147 */     worldGenWhiteElmShortTrees = new WorldGenCustomShortTrees(false, 13);
/* 148 */     worldGenWillowShortTrees = new LOTRWorldGenWillow(false, 14);
/*     */     
/* 150 */     worldGenTestTree = new WorldGenCustomShortTrees(false, 10);
/*     */     
/* 152 */     worldGenOakSacredTrees = new WorldGenSacredOak(false, 0, 1.5D, 5, 28, 4);
/* 153 */     worldGenCyprusTrees = new WorldGenCyprusTree(false, 15);
/* 154 */     worldGenCyprusTrees.setConfigOptions(TFCBlocks.logNatural, TFCBlocks.leaves, 28, 32);
/*     */ 
/*     */     
/* 157 */     this.field_76762_K.clear();
/*     */ 
/*     */ 
/*     */     
/* 161 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityChickenTFC.class, 10, 2, 4));
/*     */     
/* 163 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 2, 1, 3));
/*     */     
/* 165 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorseTFC.class, 2, 2, 3));
/*     */     
/* 167 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 16, 0, 0));
/* 168 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 2, 1, 4));
/* 169 */     this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityDeer.class, 1, 1, 4));
/*     */     
/* 171 */     this.field_76755_L.clear();
/* 172 */     switch (par1) { case 0:
/* 173 */         this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntitySquidTFC.class, 8, 1, 3)); break;
/* 174 */       case 2: this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntityFishTFC.class, 7, 1, 3));
/* 175 */         this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntityFishTFC.class, 12, 0, 0));
/*     */         break; }
/*     */ 
/*     */ 
/*     */     
/* 180 */     this.field_76761_J.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 185 */     this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySlimeTFC.class, 8, 1, 2));
/*     */ 
/*     */ 
/*     */     
/* 189 */     biomeList[par1] = this;
/* 190 */     this.field_76760_I = createBiomeDecorator();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBiomeColor() {
/* 195 */     return this.biomeColor;
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCBiome setBiomeColor(int c) {
/* 200 */     this.biomeColor = c;
/* 201 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeDecoratorTFC createBiomeDecorator() {
/* 210 */     return new BiomeDecoratorTFC(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
/* 216 */     this.field_76760_I.func_150512_a(par1World, par2Random, this, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome setMinMaxHeight(float par1, float par2) {
/* 225 */     this.field_76748_D = par1 - 2.7F;
/* 226 */     this.field_76749_E = par2 - 2.7F;
/* 227 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome setTemperatureRainfall(float par1, float par2) {
/* 233 */     this.temperatureTFC = par1;
/* 234 */     this.field_76751_G = par2;
/* 235 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome setBiomeName(String par1Str) {
/* 241 */     this.field_76791_y = par1Str;
/* 242 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCBiome setWaterMult(int par1) {
/* 247 */     this.field_76759_H = par1;
/* 248 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome setColor(int par1) {
/* 254 */     this.field_76790_z = par1;
/* 255 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TFCBiome setDisableRain() {
/* 264 */     this.field_76765_S = false;
/* 265 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   private static WorldGenerator if20big(boolean j, int num, WorldGenerator t2, WorldGenerator t3) {
/* 270 */     Random r = new Random();
/* 271 */     if (j) return (r.nextInt(20) == 0) ? (WorldGenerator)new WorldGenCustomBigTree(false, num) : t2; 
/* 272 */     return t3;
/*     */   }
/*     */   
/*     */   public static WorldGenerator getTreeGen(int i, Boolean j) {
/*     */     Random r;
/* 277 */     switch (i) {
/*     */       case 7:
/* 279 */         return if20big(j.booleanValue(), 7, (WorldGenerator)worldGenAshTallTrees, (WorldGenerator)worldGenAshShortTrees);
/* 280 */       case 1: return if20big(j.booleanValue(), 1, (WorldGenerator)worldGenAspenTallTrees, (WorldGenerator)worldGenAspenShortTrees);
/* 281 */       case 2: return if20big(j.booleanValue(), 2, (WorldGenerator)worldGenBirchTallTrees, (WorldGenerator)worldGenBirchShortTrees);
/* 282 */       case 3: return if20big(j.booleanValue(), 3, (WorldGenerator)worldGenChestnutTallTrees, (WorldGenerator)worldGenChestnutShortTrees);
/* 283 */       case 4: return j.booleanValue() ? (WorldGenerator)worldGenDouglasFirTallTrees : (WorldGenerator)worldGenDouglasFirShortTrees;
/* 284 */       case 5: return if20big(j.booleanValue(), 5, (WorldGenerator)worldGenHickoryTallTrees, (WorldGenerator)worldGenHickoryShortTrees);
/* 285 */       case 6: return if20big(j.booleanValue(), 6, (WorldGenerator)worldGenMapleTallTrees, (WorldGenerator)worldGenMapleShortTrees);
/*     */       case 0:
/* 287 */         r = new Random();
/* 288 */         return j.booleanValue() ? ((r.nextInt(20) < 2) ? (WorldGenerator)worldGenOakSacredTrees : (WorldGenerator)worldGenOakTallTrees) : (WorldGenerator)worldGenOakShortTrees;
/* 289 */       case 8: return j.booleanValue() ? (WorldGenerator)worldGenPineTallTrees : (WorldGenerator)worldGenPineShortTrees;
/* 290 */       case 9: return j.booleanValue() ? (WorldGenerator)worldGenRedwoodTallTrees : (WorldGenerator)worldGenRedwoodShortTrees;
/* 291 */       case 10: return if20big(j.booleanValue(), 10, (WorldGenerator)worldGenSpruceTallTrees, (WorldGenerator)worldGenSpruceShortTrees);
/* 292 */       case 11: return if20big(j.booleanValue(), 11, (WorldGenerator)worldGenSycamoreTallTrees, (WorldGenerator)worldGenSycamoreShortTrees);
/* 293 */       case 12: return (WorldGenerator)worldGenWhiteCedarTallTrees;
/* 294 */       case 13: return if20big(j.booleanValue(), 13, (WorldGenerator)worldGenWhiteElmTallTrees, (WorldGenerator)worldGenWhiteElmShortTrees);
/* 295 */       case 14: return (WorldGenerator)worldGenWillowShortTrees;
/* 296 */       case 15: return (WorldGenerator)worldGenCyprusTrees;
/* 297 */       case 16: return (WorldGenerator)worldGenAcaciaKoaTrees;
/* 298 */       case 17: return (WorldGenerator)worldGenTestTree;
/*     */     } 
/* 300 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TFCBiome getBiome(int id) {
/* 308 */     if (biomeList[id] == null)
/*     */     {
/* 310 */       TerraFirmaCraft.LOG.warn("Biome ID is null: " + id);
/*     */     }
/* 312 */     if (id >= 0 && id <= biomeList.length && biomeList[id] != null)
/*     */     {
/* 314 */       return biomeList[id];
/*     */     }
/*     */ 
/*     */     
/* 318 */     TerraFirmaCraft.LOG.warn("Biome ID is out of bounds: " + id + ", defaulting to 0 (Ocean)");
/* 319 */     return OCEAN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static TFCBiome getBiomeByName(String name) {
/* 325 */     for (int i = 0; i < (getBiomeGenArray()).length; i++) {
/*     */       
/* 327 */       if (getBiomeGenArray()[i] != null) {
/*     */         
/* 329 */         String n = (getBiomeGenArray()[i]).field_76791_y.toLowerCase();
/* 330 */         if (n.equalsIgnoreCase(name))
/* 331 */           return getBiomeGenArray()[i]; 
/*     */       } 
/*     */     } 
/* 334 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static TFCBiome[] getBiomeGenArray() {
/* 339 */     return (TFCBiome[])biomeList.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCBiome setLilyPads(int i) {
/* 344 */     this.field_76760_I.lilyPadPerChunk = i;
/* 345 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public TFCBiome setWaterPlants(int i) {
/* 350 */     this.field_76760_I.waterPlantsPerChunk = i;
/* 351 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getCanSpawnHere(EntityLiving e) {
/* 356 */     int i = MathHelper.func_76128_c(e.field_70165_t);
/* 357 */     int j = MathHelper.func_76128_c(e.field_70121_D.field_72338_b);
/* 358 */     int k = MathHelper.func_76128_c(e.field_70161_v);
/* 359 */     return (getCanSpawnHere1((EntityCreature)e) && ((EntityCreature)e).func_70783_a(i, j, k) >= 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean getCanSpawnHere1(EntityCreature e) {
/* 364 */     return (e.field_70170_p.func_72855_b(e.field_70121_D) && e.field_70170_p.func_72945_a((Entity)e, e.field_70121_D).isEmpty() && !e.field_70170_p.func_72953_d(e.field_70121_D));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isGrass(Block b) {
/* 369 */     return b.func_149739_a().contains("Grass");
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */