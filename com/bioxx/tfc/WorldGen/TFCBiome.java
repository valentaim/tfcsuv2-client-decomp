package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
import com.bioxx.tfc.Entities.Mobs.EntityDeer;
import com.bioxx.tfc.Entities.Mobs.EntityFishTFC;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySlimeTFC;
import com.bioxx.tfc.Entities.Mobs.EntitySquidTFC;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.LOTRWorldGenPine;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.LOTRWorldGenWillow;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenBOPTaiga2;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenBulbTree;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenCyprusTree;
import com.bioxx.tfc.WorldGen.Generators.Trees.New.WorldGenSacredOak;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenAcaciaKoaTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomBigTree;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomCedarTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomMapleShortTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomMapleTallTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomShortTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomTallTrees;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenDouglasFir;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenRedwoodXL;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class TFCBiome
  extends BiomeGenBase {
  public static float riverDepthMin = -0.5F;
  public static float riverDepthMax = -0.3F;
  
  public float temperatureTFC;
  
  public BiomeDecoratorTFC field_76760_I;
  public static TFCBiome[] biomeList = new TFCBiome[256];

  
  public static final TFCBiome OCEAN = (new TFCBiome(0)).setBiomeName("Ocean").setMinMaxHeight(-0.9F, 1.0E-5F).setBiomeColor(255);
  public static final TFCBiome RIVER = (new TFCBiome(7)).setBiomeName("River").setMinMaxHeight(riverDepthMin, riverDepthMax).setBiomeColor(16777215);
  public static final TFCBiome HELL = (new TFCBiome(8)).setColor(16711680).setBiomeName("Hell").setDisableRain().setTemperatureRainfall(2.0F, 0.0F);
  public static final TFCBiome BEACH = (new TFCBiome(16)).setColor(16440917).setBiomeName("Beach").setMinMaxHeight(0.01F, 0.02F).setBiomeColor(16758899);
  public static final TFCBiome GRAVEL_BEACH = (new TFCBiome(17)).setColor(16440917).setBiomeName("Gravel Beach").setMinMaxHeight(0.01F, 0.02F).setBiomeColor(9402723);
  public static final TFCBiome HIGH_HILLS = (new TFCBiome(3)).setBiomeName("High Hills").setMinMaxHeight(0.8F, 1.6F).setBiomeColor(282407);
  public static final TFCBiome PLAINS = (new TFCBiome(1)).setBiomeName("Plains").setMinMaxHeight(0.1F, 0.16F).setBiomeColor(6938528);
  public static final TFCBiome SWAMPLAND = (new TFCBiome(6)).setBiomeName("Swamp").setMinMaxHeight(-0.1F, 0.1F).setBiomeColor(2046251).setLilyPads(8).setWaterPlants(45);
  public static final TFCBiome HIGH_HILLS_EDGE = (new TFCBiome(20)).setBiomeName("High Hills Edge").setMinMaxHeight(0.2F, 0.4F).setBiomeColor(3188583);
  public static final TFCBiome ROLLING_HILLS = (new TFCBiome(30)).setBiomeName("Rolling Hills").setMinMaxHeight(0.1F, 0.4F).setBiomeColor(8893492);
  public static final TFCBiome MOUNTAINS = (new TFCBiome(31)).setBiomeName("Mountains").setMinMaxHeight(0.8F, 1.6F).setBiomeColor(7371104);
  public static final TFCBiome MOUNTAINS_EDGE = (new TFCBiome(32)).setBiomeName("Mountains Edge").setMinMaxHeight(0.4F, 0.8F).setBiomeColor(11713695);
  public static final TFCBiome HIGH_PLAINS = (new TFCBiome(35)).setBiomeName("High Plains").setMinMaxHeight(0.4F, 0.43F).setBiomeColor(10920988);
  public static final TFCBiome DEEP_OCEAN = (new TFCBiome(36)).setBiomeName("Deep Ocean").setMinMaxHeight(-0.9F, 1.0E-5F).setBiomeColor(918874);
  public static final TFCBiome LAKE = (new TFCBiome(2)).setBiomeName("Lake").setMinMaxHeight(-0.5F, 0.001F).setBiomeColor(4886174).setLilyPads(2);
  
  protected static WorldGenAcaciaKoaTrees worldGenAcaciaKoaTrees;
  
  protected static WorldGenCustomTallTrees worldGenAshTallTrees;
  
  protected static WorldGenBulbTree worldGenAspenTallTrees;
  
  protected static WorldGenBulbTree worldGenBirchTallTrees;
  
  protected static WorldGenSacredOak worldGenChestnutTallTrees;
  protected static WorldGenDouglasFir worldGenDouglasFirTallTrees;
  protected static WorldGenSacredOak worldGenHickoryTallTrees;
  protected static WorldGenCustomMapleTallTrees worldGenMapleTallTrees;
  protected static WorldGenSacredOak worldGenOakTallTrees;
  protected static LOTRWorldGenPine worldGenPineTallTrees;
  protected static WorldGenRedwoodXL worldGenRedwoodTallTrees;
  protected static WorldGenBOPTaiga2 worldGenSpruceTallTrees;
  protected static WorldGenSacredOak worldGenSycamoreTallTrees;
  protected static WorldGenCustomCedarTrees worldGenWhiteCedarTallTrees;
  protected static WorldGenCustomTallTrees worldGenWhiteElmTallTrees;
  protected static WorldGenCustomShortTrees worldGenAshShortTrees;
  protected static WorldGenBulbTree worldGenAspenShortTrees;
  protected static WorldGenBulbTree worldGenBirchShortTrees;
  protected static WorldGenCustomShortTrees worldGenChestnutShortTrees;
  protected static WorldGenDouglasFir worldGenDouglasFirShortTrees;
  protected static WorldGenCustomShortTrees worldGenHickoryShortTrees;
  protected static WorldGenCustomMapleShortTrees worldGenMapleShortTrees;
  protected static WorldGenCustomShortTrees worldGenOakShortTrees;
  protected static LOTRWorldGenPine worldGenPineShortTrees;
  protected static WorldGenRedwoodXL worldGenRedwoodShortTrees;
  protected static WorldGenBOPTaiga2 worldGenSpruceShortTrees;
  protected static WorldGenCustomShortTrees worldGenSycamoreShortTrees;
  protected static WorldGenCustomShortTrees worldGenWhiteElmShortTrees;
  protected static LOTRWorldGenWillow worldGenWillowShortTrees;
  protected static WorldGenCustomShortTrees worldGenTestTree;
  protected static WorldGenCyprusTree worldGenCyprusTrees;
  protected static WorldGenSacredOak worldGenOakSacredTrees;
  protected int biomeColor;
  
  public TFCBiome(int par1) {
    super(par1);
    
    this.field_76752_A = (Block)Blocks.field_150349_c;
    this.field_76753_B = Blocks.field_150346_d;
    this.field_76748_D = 0.1F;
    this.field_76749_E = 0.3F;
    this.temperatureTFC = 0.5F;
    this.field_76751_G = 0.5F;
    this.field_76761_J = new ArrayList();
    this.field_76762_K = new ArrayList();
    this.field_76755_L = new ArrayList();
    
    worldGenAcaciaKoaTrees = new WorldGenAcaciaKoaTrees(false, 0);
    worldGenAshTallTrees = new WorldGenCustomTallTrees(false, 7);
    worldGenAspenTallTrees = new WorldGenBulbTree(1, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 5, false);
    worldGenBirchTallTrees = new WorldGenBulbTree(2, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 5, false);
    worldGenChestnutTallTrees = new WorldGenSacredOak(false, 3, 1.5D, 5, 16, 4);
    worldGenDouglasFirTallTrees = new WorldGenDouglasFir(false, 4, true);
    worldGenHickoryTallTrees = new WorldGenSacredOak(false, 5, 1.5D, 5, 16, 4);
    worldGenMapleTallTrees = new WorldGenCustomMapleTallTrees(false, 6);
    worldGenOakTallTrees = new WorldGenSacredOak(false, 0, 1.5D, 5, 16, 4);
    worldGenPineTallTrees = new LOTRWorldGenPine(false, 8, 20, 25);
    worldGenRedwoodTallTrees = new WorldGenRedwoodXL(false);
    worldGenSpruceTallTrees = new WorldGenBOPTaiga2(10, TFCBlocks.logNatural, TFCBlocks.leaves, false, 20, 15, 4, 4);
    worldGenSycamoreTallTrees = new WorldGenSacredOak(false, 11, 2.0D, 4, 16, 4);
    worldGenWhiteCedarTallTrees = new WorldGenCustomCedarTrees(false, 12);
    worldGenWhiteElmTallTrees = new WorldGenCustomTallTrees(false, 13);
    
    worldGenAshShortTrees = new WorldGenCustomShortTrees(false, 7);
    worldGenAspenShortTrees = new WorldGenBulbTree(1, TFCBlocks.logNatural, TFCBlocks.leaves, false, 10, 3, false);
    worldGenBirchShortTrees = new WorldGenBulbTree(2, TFCBlocks.logNatural, TFCBlocks.leaves, false, 10, 3, false);
    worldGenChestnutShortTrees = new WorldGenCustomShortTrees(false, 3);
    worldGenDouglasFirShortTrees = new WorldGenDouglasFir(false, 4, false);
    worldGenHickoryShortTrees = new WorldGenCustomShortTrees(false, 5);
    worldGenMapleShortTrees = new WorldGenCustomMapleShortTrees(false, 6);
    worldGenOakShortTrees = new WorldGenCustomShortTrees(false, 0);
    worldGenPineShortTrees = new LOTRWorldGenPine(false, 8, 15, 20);
    worldGenRedwoodShortTrees = new WorldGenRedwoodXL(false);
    worldGenSpruceShortTrees = new WorldGenBOPTaiga2(10, TFCBlocks.logNatural, TFCBlocks.leaves, false, 15, 10, 4, 4);
    worldGenSycamoreShortTrees = new WorldGenCustomShortTrees(false, 11);
    worldGenWhiteElmShortTrees = new WorldGenCustomShortTrees(false, 13);
    worldGenWillowShortTrees = new LOTRWorldGenWillow(false, 14);
    
    worldGenTestTree = new WorldGenCustomShortTrees(false, 10);
    
    worldGenOakSacredTrees = new WorldGenSacredOak(false, 0, 1.5D, 5, 28, 4);
    worldGenCyprusTrees = new WorldGenCyprusTree(false, 15);
    worldGenCyprusTrees.setConfigOptions(TFCBlocks.logNatural, TFCBlocks.leaves, 28, 32);

    
    this.field_76762_K.clear();


    
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityChickenTFC.class, 10, 2, 4));
    
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 2, 1, 3));
    
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorseTFC.class, 2, 2, 3));
    
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 16, 0, 0));
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 2, 1, 4));
    this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityDeer.class, 1, 1, 4));
    
    this.field_76755_L.clear();
    switch (par1) { case 0:
        this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntitySquidTFC.class, 8, 1, 3)); break;
      case 2: this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntityFishTFC.class, 7, 1, 3));
        this.field_76755_L.add(new BiomeGenBase.SpawnListEntry(EntityFishTFC.class, 12, 0, 0));
        break; }


    
    this.field_76761_J.clear();



    
    this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntitySlimeTFC.class, 8, 1, 2));


    
    biomeList[par1] = this;
    this.field_76760_I = createBiomeDecorator();
  }

  
  public int getBiomeColor() {
    return this.biomeColor;
  }

  
  public TFCBiome setBiomeColor(int c) {
    this.biomeColor = c;
    return this;
  }





  
  public BiomeDecoratorTFC createBiomeDecorator() {
    return new BiomeDecoratorTFC(this);
  }


  
  public void func_76728_a(World par1World, Random par2Random, int par3, int par4) {
    this.field_76760_I.func_150512_a(par1World, par2Random, this, par3, par4);
  }





  
  public TFCBiome setMinMaxHeight(float par1, float par2) {
    this.field_76748_D = par1 - 2.7F;
    this.field_76749_E = par2 - 2.7F;
    return this;
  }


  
  public TFCBiome setTemperatureRainfall(float par1, float par2) {
    this.temperatureTFC = par1;
    this.field_76751_G = par2;
    return this;
  }


  
  public TFCBiome setBiomeName(String par1Str) {
    this.field_76791_y = par1Str;
    return this;
  }

  
  public TFCBiome setWaterMult(int par1) {
    this.field_76759_H = par1;
    return this;
  }


  
  public TFCBiome setColor(int par1) {
    this.field_76790_z = par1;
    return this;
  }





  
  public TFCBiome setDisableRain() {
    this.field_76765_S = false;
    return this;
  }

  
  private static WorldGenerator if20big(boolean j, int num, WorldGenerator t2, WorldGenerator t3) {
    Random r = new Random();
    if (j) return (r.nextInt(20) == 0) ? (WorldGenerator)new WorldGenCustomBigTree(false, num) : t2; 
    return t3;
  }
  
  public static WorldGenerator getTreeGen(int i, Boolean j) {
    Random r;
    switch (i) {
      case 7:
        return if20big(j.booleanValue(), 7, (WorldGenerator)worldGenAshTallTrees, (WorldGenerator)worldGenAshShortTrees);
      case 1: return if20big(j.booleanValue(), 1, (WorldGenerator)worldGenAspenTallTrees, (WorldGenerator)worldGenAspenShortTrees);
      case 2: return if20big(j.booleanValue(), 2, (WorldGenerator)worldGenBirchTallTrees, (WorldGenerator)worldGenBirchShortTrees);
      case 3: return if20big(j.booleanValue(), 3, (WorldGenerator)worldGenChestnutTallTrees, (WorldGenerator)worldGenChestnutShortTrees);
      case 4: return j.booleanValue() ? (WorldGenerator)worldGenDouglasFirTallTrees : (WorldGenerator)worldGenDouglasFirShortTrees;
      case 5: return if20big(j.booleanValue(), 5, (WorldGenerator)worldGenHickoryTallTrees, (WorldGenerator)worldGenHickoryShortTrees);
      case 6: return if20big(j.booleanValue(), 6, (WorldGenerator)worldGenMapleTallTrees, (WorldGenerator)worldGenMapleShortTrees);
      case 0:
        r = new Random();
        return j.booleanValue() ? ((r.nextInt(20) < 2) ? (WorldGenerator)worldGenOakSacredTrees : (WorldGenerator)worldGenOakTallTrees) : (WorldGenerator)worldGenOakShortTrees;
      case 8: return j.booleanValue() ? (WorldGenerator)worldGenPineTallTrees : (WorldGenerator)worldGenPineShortTrees;
      case 9: return j.booleanValue() ? (WorldGenerator)worldGenRedwoodTallTrees : (WorldGenerator)worldGenRedwoodShortTrees;
      case 10: return if20big(j.booleanValue(), 10, (WorldGenerator)worldGenSpruceTallTrees, (WorldGenerator)worldGenSpruceShortTrees);
      case 11: return if20big(j.booleanValue(), 11, (WorldGenerator)worldGenSycamoreTallTrees, (WorldGenerator)worldGenSycamoreShortTrees);
      case 12: return (WorldGenerator)worldGenWhiteCedarTallTrees;
      case 13: return if20big(j.booleanValue(), 13, (WorldGenerator)worldGenWhiteElmTallTrees, (WorldGenerator)worldGenWhiteElmShortTrees);
      case 14: return (WorldGenerator)worldGenWillowShortTrees;
      case 15: return (WorldGenerator)worldGenCyprusTrees;
      case 16: return (WorldGenerator)worldGenAcaciaKoaTrees;
      case 17: return (WorldGenerator)worldGenTestTree;
    } 
    return null;
  }




  
  public static TFCBiome getBiome(int id) {
    if (biomeList[id] == null)
    {
      TerraFirmaCraft.LOG.warn("Biome ID is null: " + id);
    }
    if (id >= 0 && id <= biomeList.length && biomeList[id] != null)
    {
      return biomeList[id];
    }

    
    TerraFirmaCraft.LOG.warn("Biome ID is out of bounds: " + id + ", defaulting to 0 (Ocean)");
    return OCEAN;
  }


  
  public static TFCBiome getBiomeByName(String name) {
    for (int i = 0; i < (getBiomeGenArray()).length; i++) {
      
      if (getBiomeGenArray()[i] != null) {
        
        String n = (getBiomeGenArray()[i]).field_76791_y.toLowerCase();
        if (n.equalsIgnoreCase(name))
          return getBiomeGenArray()[i]; 
      } 
    } 
    return null;
  }

  
  public static TFCBiome[] getBiomeGenArray() {
    return (TFCBiome[])biomeList.clone();
  }

  
  public TFCBiome setLilyPads(int i) {
    this.field_76760_I.lilyPadPerChunk = i;
    return this;
  }

  
  public TFCBiome setWaterPlants(int i) {
    this.field_76760_I.waterPlantsPerChunk = i;
    return this;
  }

  
  public static boolean getCanSpawnHere(EntityLiving e) {
    int i = MathHelper.func_76128_c(e.field_70165_t);
    int j = MathHelper.func_76128_c(e.field_70121_D.field_72338_b);
    int k = MathHelper.func_76128_c(e.field_70161_v);
    return (getCanSpawnHere1((EntityCreature)e) && ((EntityCreature)e).func_70783_a(i, j, k) >= 0.0F);
  }

  
  public static boolean getCanSpawnHere1(EntityCreature e) {
    return (e.field_70170_p.func_72855_b(e.field_70121_D) && e.field_70170_p.func_72945_a((Entity)e, e.field_70121_D).isEmpty() && !e.field_70170_p.func_72953_d(e.field_70121_D));
  }

  
  public static boolean isGrass(Block b) {
    return b.func_149739_a().contains("Grass");
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCBiome.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */