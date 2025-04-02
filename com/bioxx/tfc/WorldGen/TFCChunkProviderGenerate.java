package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Blocks.Terrain.BlockCollapsible;
import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Entities.Mobs.EntityChickenTFC;
import com.bioxx.tfc.Entities.Mobs.EntityDeer;
import com.bioxx.tfc.Entities.Mobs.EntityHorseTFC;
import com.bioxx.tfc.Entities.Mobs.EntityPheasantTFC;
import com.bioxx.tfc.Entities.Mobs.EntityWolfTFC;
import com.bioxx.tfc.WorldGen.MapGen.MapGenCavesTFC;
import com.bioxx.tfc.WorldGen.MapGen.MapGenRavineTFC;
import com.bioxx.tfc.WorldGen.MapGen.MapGenRiverRavine;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.eventhandler.Event;
import fof.tfcsu.Entity.EntityBear3D;
import fof.tfcsu.Entity.EntityBoar;
import fof.tfcsu.Entity.EntityEnhancedCow;
import fof.tfcsu.Entity.Sheep.EntitySheepMerino;
import fof.tfcsu.Entity.Sheep.EntitySheepSuffolk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;



















public class TFCChunkProviderGenerate
  extends ChunkProviderGenerate
{
  private Random rand;
  private NoiseGeneratorOctaves noiseGen1;
  private NoiseGeneratorOctaves noiseGen4;
  public NoiseGeneratorOctaves field_73212_b;
  public NoiseGeneratorOctaves field_73213_c;
  private World worldObj;
  private double[] noiseArray;
  private double[] stoneNoise = new double[256];


  private BiomeGenBase[] biomesForGeneration;


  private DataLayer[] rockLayer1;


  private DataLayer[] rockLayer2;


  private DataLayer[] rockLayer3;


  private DataLayer[] evtLayer;


  private DataLayer[] rainfallLayer;


  private DataLayer[] stabilityLayer;


  private DataLayer[] drainageLayer;

  private Block[] idsTop;

  private Block[] idsBig;

  private byte[] metaBig;

  private double[] noise1;

  private double[] noise6;

  private float[] parabolicField;

  private int[] seaLevelOffsetMap = new int[256];
  private int[] chunkHeightMap = new int[256];

  private MapGenCavesTFC caveGen = new MapGenCavesTFC();
  private MapGenRavineTFC surfaceRavineGen = new MapGenRavineTFC(125, 30);
  private MapGenRavineTFC ravineGen = new MapGenRavineTFC(20, 50);
  private MapGenRiverRavine riverRavineGen = new MapGenRiverRavine();


  public TFCChunkProviderGenerate(World par1World, long par2, boolean par4) {
    super(par1World, par2, par4);

    this.worldObj = par1World;
    this.rand = new Random(par2);
    this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);


    this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
    this.field_73214_a = new NoiseGeneratorOctaves(this.rand, 2);
    this.field_73212_b = new NoiseGeneratorOctaves(this.rand, 1);
    this.field_73213_c = new NoiseGeneratorOctaves(this.rand, 8);

    this.idsTop = new Block[32768];
    this.idsBig = new Block[65536];
    this.metaBig = new byte[65536];
  }



  public Chunk func_73154_d(int chunkX, int chunkZ) {
    this.rand.setSeed(chunkX * 341873128712L + chunkZ * 132897987541L);





    Arrays.fill((Object[])this.idsTop, (Object)null);
    Arrays.fill((Object[])this.idsBig, (Object)null);
    Arrays.fill(this.metaBig, (byte)0);

    generateTerrainHigh(chunkX, chunkZ, this.idsTop);

    this.biomesForGeneration = this.worldObj.func_72959_q().func_76933_b(this.biomesForGeneration, chunkX * 16 - 1, chunkZ * 16 - 1, 18, 18);
    if (TFC_Climate.getCacheManager(this.worldObj) != null) {

      this.rockLayer1 = TFC_Climate.getCacheManager(this.worldObj).loadRockLayerGeneratorData(this.rockLayer1, chunkX * 16, chunkZ * 16, 16, 16, 0);
      this.rockLayer2 = TFC_Climate.getCacheManager(this.worldObj).loadRockLayerGeneratorData(this.rockLayer2, chunkX * 16, chunkZ * 16, 16, 16, 1);
      this.rockLayer3 = TFC_Climate.getCacheManager(this.worldObj).loadRockLayerGeneratorData(this.rockLayer3, chunkX * 16, chunkZ * 16, 16, 16, 2);
      this.evtLayer = TFC_Climate.getCacheManager(this.worldObj).loadEVTLayerGeneratorData(this.evtLayer, chunkX * 16, chunkZ * 16, 16, 16);
      this.rainfallLayer = TFC_Climate.getCacheManager(this.worldObj).loadRainfallLayerGeneratorData(this.rainfallLayer, chunkX * 16, chunkZ * 16, 16, 16);
      this.stabilityLayer = TFC_Climate.getCacheManager(this.worldObj).loadStabilityLayerGeneratorData(this.stabilityLayer, chunkX * 16, chunkZ * 16, 16, 16);
      this.drainageLayer = TFC_Climate.getCacheManager(this.worldObj).loadDrainageLayerGeneratorData(this.drainageLayer, chunkX * 16, chunkZ * 16, 16, 16);
    }

    this.seaLevelOffsetMap = new int[256];

    replaceBlocksForBiomeHigh(chunkX, chunkZ, this.idsTop, this.rand, this.idsBig, this.metaBig);
    replaceBlocksForBiomeLow(chunkX, chunkZ, this.rand, this.idsBig, this.metaBig);

    this.caveGen.generate((IChunkProvider)this, this.worldObj, chunkX, chunkZ, this.idsBig, this.metaBig);
    this.surfaceRavineGen.generate((IChunkProvider)this, this.worldObj, chunkX, chunkZ, this.idsBig, this.metaBig);
    this.ravineGen.generate((IChunkProvider)this, this.worldObj, chunkX, chunkZ, this.idsBig, this.metaBig);
    this.riverRavineGen.generate((IChunkProvider)this, this.worldObj, chunkX, chunkZ, this.idsBig, this.metaBig);

    Chunk chunk = new Chunk(this.worldObj, this.idsBig, this.metaBig, chunkX, chunkZ);
    byte[] abyte1 = chunk.func_76605_m();

    for (int x = 0; x < 16; x++) {

      for (int z = 0; z < 16; z++)
      {
        abyte1[x * z] = (byte)(getBiome(x, z)).field_76756_M;
      }
    }
    chunk.func_76616_a(abyte1);

    ChunkData data = (new ChunkData(chunk)).createNew(this.worldObj, chunkX, chunkZ);
    data.heightmap = this.seaLevelOffsetMap;
    data.rainfallMap = this.rainfallLayer;
    TFC_Core.getCDM(this.worldObj).addData(chunk, data);

    chunk.func_76603_b();
    return chunk;
  }


  private BiomeGenBase getBiome(int x, int z) {
    return this.biomesForGeneration[z + 1 + (x + 1) * 18];
  }



  public void func_73153_a(IChunkProvider chunkProvider, int chunkX, int chunkZ) {
    BlockCollapsible.fallInstantly = true;
    int xCoord = chunkX * 16;
    int zCoord = chunkZ * 16;
    TFCBiome biome = null;

    if (this.worldObj.func_72807_a(xCoord + 16, zCoord + 16) instanceof TFCBiome)
    {
      biome = (TFCBiome)this.worldObj.func_72807_a(xCoord + 16, zCoord + 16);
    }

    this.rand.setSeed(this.worldObj.func_72905_C());
    long var7 = this.rand.nextLong() / 2L * 2L + 1L;
    long var9 = this.rand.nextLong() / 2L * 2L + 1L;
    this.rand.setSeed(chunkX * var7 + chunkZ * var9 ^ this.worldObj.func_72905_C());
    boolean var11 = false;

    MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Pre(chunkProvider, this.worldObj, this.rand, chunkX, chunkZ, var11));





    TFC_Core.getCDM(this.worldObj).setFishPop(chunkX, chunkZ, 60.0F);

    int waterRand = 4;
    if (TFC_Climate.getStability(this.worldObj, xCoord, zCoord) == 1) {
      waterRand = 6;
    }
    if (!var11 && this.rand.nextInt(waterRand) == 0) {

      int i = xCoord + this.rand.nextInt(16) + 8;
      int z = zCoord + this.rand.nextInt(16) + 8;
      int j = 144 - this.rand.nextInt(45);
    }


    if (biome != null) {

      biome.func_76728_a(this.worldObj, this.rand, xCoord, zCoord);
      SpawnerAnimalsTFC.performWorldGenSpawning(this.worldObj, biome, xCoord + 8, zCoord + 8, 16, 16, this.rand);
    }

    for (int x = 0; x < 16; x++) {

      for (int z = 0; z < 16; z++) {

        int y = this.worldObj.func_72874_g(xCoord + x, zCoord + z);

        this.worldObj.func_72884_u(x + xCoord, y - 1, z + zCoord);
        if (canSnowAt(this.worldObj, x + xCoord, y, z + zCoord)) {
          this.worldObj.func_147465_d(x + xCoord, y, z + zCoord, TFCBlocks.snow, 0, 2);
        }
      }
    }
    MinecraftForge.EVENT_BUS.post((Event)new PopulateChunkEvent.Post(chunkProvider, this.worldObj, this.rand, chunkX, chunkZ, var11));
    BlockCollapsible.fallInstantly = false;
  }


  public static List<BiomeGenBase.SpawnListEntry> getCreatureSpawnsByChunk(World world, TFCBiome biome, int x, int z) {
    List<BiomeGenBase.SpawnListEntry> spawnableCreatureList = new ArrayList<>();
    spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChickenTFC.class, 24, 0, 0));
    float temp = TFC_Climate.getBioTemperatureHeight(world, x, world.func_72825_h(x, z), z);
    float rain = TFC_Climate.getRainfall(world, x, 150, z);
    float evt = 0.0F;
    if (TFC_Climate.getCacheManager(world) != null && TFC_Climate.getCacheManager(world).getEVTLayerAt(x, z) != null)
      evt = (TFC_Climate.getCacheManager(world).getEVTLayerAt(x, z)).floatdata1;
    boolean isMountainous = (biome == TFCBiome.MOUNTAINS || biome == TFCBiome.HIGH_HILLS);

    int mountainousAreaModifier = isMountainous ? -1 : 0;
    if (isMountainous) {

      if (temp < 25.0F && temp > -10.0F)
      {
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheepSuffolk.class, 2, 2, 4));
        if (rain > 250.0F && evt < 0.75D)
        {
          spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 2, 1, 3));
          spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBear3D.class, 1, 1, 1));
        }

      }

    } else if (temp > 0.0F && rain > 100.0F && rain <= 500.0F) {

      if (temp > 20.0F)
      {

        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 1, 1, 2));
      }
      if (temp < 30.0F) {

        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityEnhancedCow.class, 2, 2, 4));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorseTFC.class, 2, 2, 3));
      }
    }

    if (temp > 0.0F && temp < 21.0F && rain > 250.0F) {

      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 2 + mountainousAreaModifier, 2 + mountainousAreaModifier, 3 + mountainousAreaModifier));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 1, 1, 2 + mountainousAreaModifier));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBear3D.class, 1, 1, 1));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDeer.class, 2 + mountainousAreaModifier, 1, 3 + mountainousAreaModifier));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 3 + mountainousAreaModifier, 1, 3));
    }


    if (temp > -20.0F && temp <= 0.0F)
    {

      if (rain > 250.0F) {

        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 1 + mountainousAreaModifier, 1, 2));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 2 + mountainousAreaModifier, 1, 2 + mountainousAreaModifier));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBear3D.class, 2 + mountainousAreaModifier, 1, 1));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDeer.class, 1 + mountainousAreaModifier, 2, 3));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 1 + mountainousAreaModifier, 1, 2));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntitySheepMerino.class, 2, 2, 4));

      }
      else if (rain > 100.0F) {

        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolfTFC.class, 1 + mountainousAreaModifier, 1, 1));
        spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityDeer.class, 1 + mountainousAreaModifier, 1, 1));
      }
    }

    if (temp >= 23.0F && temp < 44.0F && rain > 1500.0F) {

      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 2 + mountainousAreaModifier, 2 + mountainousAreaModifier, 4 + mountainousAreaModifier));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityChickenTFC.class, 3 + mountainousAreaModifier, 1, 4 + mountainousAreaModifier));
    }

    if (TFC_Climate.isSwamp(world, x, 150, z)) {

      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityBoar.class, 1, 1, 2));
      spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityPheasantTFC.class, 1 + mountainousAreaModifier, 1, 1));
    }
    return spawnableCreatureList;
  }


  public boolean canSnowAt(World world, int x, int y, int z) {
    float var5 = TFC_Climate.getHeightAdjustedTemp(world, x, y, z);
    if (var5 >= 0.0F)
    {
      return false;
    }


    if (y >= 0 && y < 256 && world.func_72972_b(EnumSkyBlock.Block, x, y, z) < 10 && TFC_Time.getTotalMonths() > 1L) {

      Block var6 = world.func_147439_a(x, y - 1, z);
      Block var7 = world.func_147439_a(x, y, z);
      if (var7.isAir((IBlockAccess)world, x, y, z) && TFCBlocks.snow.func_149742_c(world, x, y, z) && !var6.isAir((IBlockAccess)world, x, y - 1, z) && var6.func_149688_o().func_76230_c())
        return true;
    }
    return false;
  }



  public void generateTerrainHigh(int chunkX, int chunkZ, Block[] idsTop) {
    byte subDivXZ = 4;
    byte subDivY = 16;
    int seaLevel = 16;
    int xSize = subDivXZ + 1;
    byte ySize = 17;
    int zSize = subDivXZ + 1;
    short arrayYHeight = 128;
    this.biomesForGeneration = this.worldObj.func_72959_q().func_76937_a(this.biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, xSize + 5, zSize + 5);
    this.noiseArray = initializeNoiseFieldHigh(this.noiseArray, chunkX * subDivXZ, 0, chunkZ * subDivXZ, xSize, ySize, zSize);

    for (int x = 0; x < subDivXZ; x++) {

      for (int z = 0; z < subDivXZ; z++) {

        for (int y = 0; y < subDivY; y++) {

          double yLerp = 0.125D;
          double noiseDL = this.noiseArray[((x + 0) * zSize + z + 0) * ySize + y + 0];
          double noiseUL = this.noiseArray[((x + 0) * zSize + z + 1) * ySize + y + 0];
          double noiseDR = this.noiseArray[((x + 1) * zSize + z + 0) * ySize + y + 0];
          double noiseUR = this.noiseArray[((x + 1) * zSize + z + 1) * ySize + y + 0];
          double noiseDLA = (this.noiseArray[((x + 0) * zSize + z + 0) * ySize + y + 1] - noiseDL) * yLerp;
          double noiseULA = (this.noiseArray[((x + 0) * zSize + z + 1) * ySize + y + 1] - noiseUL) * yLerp;
          double noiseDRA = (this.noiseArray[((x + 1) * zSize + z + 0) * ySize + y + 1] - noiseDR) * yLerp;
          double noiseURA = (this.noiseArray[((x + 1) * zSize + z + 1) * ySize + y + 1] - noiseUR) * yLerp;

          for (int var31 = 0; var31 < 8; var31++) {

            double xLerp = 0.25D;
            double var34 = noiseDL;
            double var36 = noiseUL;
            double var38 = (noiseDR - noiseDL) * xLerp;
            double var40 = (noiseUR - noiseUL) * xLerp;

            for (int var42 = 0; var42 < 4; var42++) {

              int index = var42 + x * 4 << 11 | 0 + z * 4 << 7 | y * 8 + var31;

              index -= arrayYHeight;
              double zLerp = 0.25D;
              double var49 = (var36 - var34) * zLerp;
              double var47 = var34 - var49;

              for (int var51 = 0; var51 < 4; var51++) {

                if ((var47 += var49) > 0.0D) {
                  idsTop[index += arrayYHeight] = Blocks.field_150348_b;
                } else if (y * 8 + var31 < seaLevel) {
                  idsTop[index += arrayYHeight] = TFCBlocks.saltWaterStationary;
                } else {
                  idsTop[index += arrayYHeight] = Blocks.field_150350_a;
                }
              }  var34 += var38;
              var36 += var40;
            }
            noiseDL += noiseDLA;
            noiseUL += noiseULA;
            noiseDR += noiseDRA;
            noiseUR += noiseURA;
          }
        }
      }
    }
  }






  private double[] initializeNoiseFieldHigh(double[] outArray, int xPos, int yPos, int zPos, int xSize, int ySize, int zSize) {
    if (outArray == null) {
      outArray = new double[xSize * ySize * zSize];
    }
    if (this.parabolicField == null) {

      this.parabolicField = new float[25];
      for (int counter1 = -2; counter1 <= 2; counter1++) {

        for (int counter2 = -2; counter2 <= 2; counter2++) {

          float parabolaHeight = 10.0F / MathHelper.func_76129_c((counter1 * counter1 + counter2 * counter2) + 0.2F);
          this.parabolicField[counter1 + 2 + (counter2 + 2) * 5] = parabolaHeight;
        }
      }
    }









    double double1 = 850.0D;
    double double2 = 850.0D;



    this.noise6 = this.field_73212_b.func_76305_a(this.noise6, xPos, zPos, xSize, zSize, 200.0D, 200.0D, 0.5D);


    this.noise1 = this.noiseGen1.func_76304_a(this.noise1, xPos, yPos, zPos, xSize, ySize, zSize, double1, double2, double1);


    int index1 = 0;
    int index2 = 0;

    for (int x = 0; x < xSize; x++) {

      for (int z = 0; z < zSize; z++) {

        float variationBlended = 0.0F;
        float rootBlended = 0.0F;
        float totalBlendedHeight = 0.0F;
        byte radius = 2;
        BiomeGenBase baseBiome = this.biomesForGeneration[x + 2 + (z + 2) * (xSize + 5)];

        for (int xR = -radius; xR <= radius; xR++) {

          for (int zR = -radius; zR <= radius; zR++) {

            BiomeGenBase blendBiome = this.biomesForGeneration[x + xR + 2 + (z + zR + 2) * (xSize + 5)];
            float blendedHeight = this.parabolicField[xR + 2 + (zR + 2) * 5] / 2.0F;
            if (blendBiome.field_76748_D > baseBiome.field_76748_D) {
              blendedHeight *= 0.5F;
            }
            variationBlended += blendBiome.field_76749_E * blendedHeight;
            rootBlended += blendBiome.field_76748_D * blendedHeight;
            totalBlendedHeight += blendedHeight;
          }
        }

        variationBlended /= totalBlendedHeight;
        rootBlended /= totalBlendedHeight;
        variationBlended = variationBlended * 0.9F + 0.1F;
        rootBlended = (rootBlended * 4.0F - 1.0F) / 8.0F;

        double scaledNoise6Value = this.noise6[index2] / 8000.0D;

        if (scaledNoise6Value < 0.0D) {
          scaledNoise6Value = -scaledNoise6Value * 0.3D;
        }
        scaledNoise6Value = scaledNoise6Value * 3.0D - 2.0D;

        if (scaledNoise6Value < 0.0D) {

          scaledNoise6Value /= 2.0D;
          if (scaledNoise6Value < -1.0D)
            scaledNoise6Value = -1.0D;
          scaledNoise6Value /= 2.8D;
        }
        else {

          if (scaledNoise6Value > 1.0D)
            scaledNoise6Value = 1.0D;
          scaledNoise6Value /= 8.0D;
        }

        index2++;

        for (int y = 0; y < ySize; y++) {

          double rootBlendedCopy = rootBlended;
          rootBlendedCopy += scaledNoise6Value * 0.2D;
          rootBlendedCopy = rootBlendedCopy * ySize / 16.0D;
          double var28 = ySize / 2.0D + rootBlendedCopy * 4.0D;
          double output = 0.0D;
          double var32 = (y - var28) * 12.0D * 256.0D / 256.0D / (2.7D + variationBlended);

          if (var32 < 0.0D) {
            var32 *= 4.0D;
          }
          double var34 = this.noise1[index1] / 512.0D;




          output = var34;





          output -= var32;
          if (y > ySize - 4) {

            double var40 = ((y - ySize - 4) / 3.0F);
            output = output * (1.0D - var40) + -10.0D * var40;
          }

          outArray[index1] = output;
          index1++;
        }
      }
    }
    return outArray;
  }


  private void replaceBlocksForBiomeHigh(int chunkX, int chunkZ, Block[] idsTop, Random rand, Block[] idsBig, byte[] metaBig) {
    int seaLevel = 16;
    int worldHeight = 256;
    int indexOffset = 128;
    double var6 = 0.03125D;
    this.stoneNoise = this.noiseGen4.func_76304_a(this.stoneNoise, chunkX * 16, chunkZ * 16, 0, 16, 16, 1, var6 * 4.0D, var6 * 1.0D, var6 * 4.0D);
    boolean[] cliffMap = new boolean[256];
    for (int xCoord = 0; xCoord < 16; xCoord++) {

      for (int zCoord = 0; zCoord < 16; zCoord++) {

        int arrayIndex = xCoord + zCoord * 16;
        int arrayIndexDL = zCoord + xCoord * 16;
        int arrayIndex2 = xCoord + 1 + zCoord + 16;
        TFCBiome biome = (TFCBiome)getBiome(xCoord, zCoord);
        DataLayer rock1 = (this.rockLayer1[arrayIndexDL] == null) ? DataLayer.GRANITE : this.rockLayer1[arrayIndexDL];
        DataLayer rock2 = (this.rockLayer2[arrayIndexDL] == null) ? DataLayer.GRANITE : this.rockLayer2[arrayIndexDL];
        DataLayer rock3 = (this.rockLayer3[arrayIndexDL] == null) ? DataLayer.GRANITE : this.rockLayer3[arrayIndexDL];

        float rain = (this.rainfallLayer[arrayIndexDL] == null) ? DataLayer.RAIN_125.floatdata1 : (this.rainfallLayer[arrayIndexDL]).floatdata1;
        DataLayer drainage = (this.drainageLayer[arrayIndexDL] == null) ? DataLayer.DRAINAGE_NORMAL : this.drainageLayer[arrayIndexDL];
        int var12 = (int)(this.stoneNoise[arrayIndex2] / 3.0D + 6.0D);
        int var13 = -1;

        Block surfaceBlock = TFC_Core.getTypeForGrassWithRain(rock1.data1, rain);
        Block subSurfaceBlock = TFC_Core.getTypeForDirtFromGrass(surfaceBlock);

        float bioTemp = TFC_Climate.getBioTemperature(this.worldObj, chunkX * 16 + xCoord, chunkZ * 16 + zCoord);
        int h = 0;
        if (TFC_Core.isBeachBiome((getBiome(xCoord - 1, zCoord)).field_76756_M) || TFC_Core.isBeachBiome((getBiome(xCoord + 1, zCoord)).field_76756_M) ||
          TFC_Core.isBeachBiome((getBiome(xCoord, zCoord + 1)).field_76756_M) || TFC_Core.isBeachBiome((getBiome(xCoord, zCoord - 1)).field_76756_M))
        {
          if (!TFC_Core.isBeachBiome((getBiome(xCoord, zCoord)).field_76756_M))
            cliffMap[arrayIndex] = true;
        }
        for (int height = 127; height >= 0; height--) {

          int indexBig = arrayIndex * worldHeight + height + indexOffset;
          int index = arrayIndex * 128 + height;

          float temp = TFC_Climate.adjustHeightToTemp(height, bioTemp);
          if (TFC_Core.isBeachBiome(biome.field_76756_M) && height > seaLevel + h && idsTop[index] == Blocks.field_150348_b) {

            idsTop[index] = Blocks.field_150350_a;
            if (h == 0)
              h = (height - 16) / 4;
          }
          if (idsBig[indexBig] == null) {

            idsBig[indexBig] = idsTop[index];
            if (indexBig + 1 < idsBig.length && TFC_Core.isSoilOrGravel(idsBig[indexBig + 1]) && idsBig[indexBig] == Blocks.field_150350_a)
            {
              for (int upCount = 1; TFC_Core.isSoilOrGravel(idsBig[indexBig + upCount]); upCount++) {
                idsBig[indexBig + upCount] = Blocks.field_150350_a;
              }
            }
          }
          if (idsBig[indexBig] == Blocks.field_150348_b) {

            if (this.seaLevelOffsetMap[arrayIndex] == 0 && height - 16 >= 0) {
              this.seaLevelOffsetMap[arrayIndex] = height - 16;
            }
            if (this.chunkHeightMap[arrayIndex] == 0) {
              this.chunkHeightMap[arrayIndex] = height + indexOffset;
            }
            convertStone(indexOffset + height, arrayIndex, indexBig, idsBig, metaBig, rock1, rock2, rock3);


            if (rain < 125.0F && temp < 1.5F) {

              surfaceBlock = TFC_Core.getTypeForSand(rock1.data1);
              subSurfaceBlock = TFC_Core.getTypeForSand(rock1.data1);

            }
            else if (rain < 125.0F && biome.field_76749_E < 0.5F && temp > 20.0F) {

              surfaceBlock = TFC_Core.getTypeForSand(rock1.data1);
              subSurfaceBlock = TFC_Core.getTypeForSand(rock1.data1);
            }

            if (biome == TFCBiome.BEACH || biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN) {

              subSurfaceBlock = surfaceBlock = TFC_Core.getTypeForSand(rock1.data1);
            }
            else if (biome == TFCBiome.GRAVEL_BEACH) {

              subSurfaceBlock = surfaceBlock = TFC_Core.getTypeForGravel(rock1.data1);
            }

            if (var13 == -1) {


              int arrayIndexx = (xCoord > 0) ? (xCoord - 1 + zCoord * 16) : -1;
              int arrayIndexX = (xCoord < 15) ? (xCoord + 1 + zCoord * 16) : -1;
              int arrayIndexz = (zCoord > 0) ? (xCoord + (zCoord - 1) * 16) : -1;
              int arrayIndexZ = (zCoord < 15) ? (xCoord + (zCoord + 1) * 16) : -1;
              int var12Temp = var12;
              for (int counter = 1; counter < var12Temp / 3; counter++) {

                if (arrayIndexx >= 0 && this.seaLevelOffsetMap[arrayIndex] - 3 * counter > this.seaLevelOffsetMap[arrayIndexx]) {

                  this.seaLevelOffsetMap[arrayIndex] = this.seaLevelOffsetMap[arrayIndex] - 1;
                  var12--;
                  height--;
                  indexBig = arrayIndex * worldHeight + height + indexOffset;
                  index = arrayIndex * 128 + height;
                }
                else if (arrayIndexX >= 0 && this.seaLevelOffsetMap[arrayIndex] - 3 * counter > this.seaLevelOffsetMap[arrayIndexX]) {

                  this.seaLevelOffsetMap[arrayIndex] = this.seaLevelOffsetMap[arrayIndex] - 1;
                  var12--;
                  height--;
                  indexBig = arrayIndex * worldHeight + height + indexOffset;
                  index = arrayIndex * 128 + height;
                }
                else if (arrayIndexz >= 0 && this.seaLevelOffsetMap[arrayIndex] - 3 * counter > this.seaLevelOffsetMap[arrayIndexz]) {

                  this.seaLevelOffsetMap[arrayIndex] = this.seaLevelOffsetMap[arrayIndex] - 1;
                  var12--;
                  height--;
                  indexBig = arrayIndex * worldHeight + height + indexOffset;
                  index = arrayIndex * 128 + height;
                }
                else if (arrayIndexZ >= 0 && this.seaLevelOffsetMap[arrayIndex] - 3 * counter > this.seaLevelOffsetMap[arrayIndexZ]) {

                  this.seaLevelOffsetMap[arrayIndex] = this.seaLevelOffsetMap[arrayIndex] - 1;
                  var12--;
                  height--;
                  indexBig = arrayIndex * worldHeight + height + indexOffset;
                  index = arrayIndex * 128 + height;
                }
              }
              var13 = (int)(var12 * (1.0D - Math.max(Math.min((height - 16) / 80.0D, 1.0D), 0.0D)));


              for (int c = 1; c < 3; c++) {

                if (indexBig + c < idsBig.length && idsBig[indexBig + c] != surfaceBlock && idsBig[indexBig + c] != subSurfaceBlock && idsBig[indexBig + c] != TFCBlocks.saltWaterStationary && idsBig[indexBig + c] != TFCBlocks.freshWaterStationary && idsBig[indexBig + c] != TFCBlocks.hotWater) {






                  idsBig[indexBig + c] = Blocks.field_150350_a;

                  if (indexBig + c + 1 < idsBig.length && idsBig[indexBig + c + 1] == TFCBlocks.saltWaterStationary) {

                    idsBig[indexBig + c] = subSurfaceBlock;
                    metaBig[indexBig + c] = (byte)TFC_Core.getSoilMeta(rock1.data1);
                  }
                }
              }


              int dirtH = Math.max(8 - (height + 96 - 144) / 16, 0);

              if (var13 > 0)
              {
                if (height >= seaLevel - 1 && index + 1 < idsTop.length && idsTop[index + 1] != TFCBlocks.saltWaterStationary && dirtH > 0) {

                  idsBig[indexBig] = surfaceBlock;
                  metaBig[indexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);


                  int i = 1;
                  for (; i < dirtH && !TFC_Core.isMountainBiome(biome.field_76756_M) && biome != TFCBiome.HIGH_HILLS && biome != TFCBiome.HIGH_HILLS_EDGE && !cliffMap[arrayIndex]; i++) {

                    int offsetHeight = height - i;
                    int newIndexBig = arrayIndex * worldHeight + offsetHeight + indexOffset;
                    idsBig[newIndexBig] = subSurfaceBlock;
                    metaBig[newIndexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);

                    if (i > 1 + 5 - drainage.data1) {

                      idsBig[newIndexBig] = TFC_Core.getTypeForGravel(rock1.data1);
                      metaBig[newIndexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);
                    }
                  }
                }
              }
            }

            if ((height > seaLevel - 2 && height < seaLevel && idsTop[index + 1] == TFCBlocks.saltWaterStationary) || (height < seaLevel && idsTop[index + 1] == TFCBlocks.saltWaterStationary))
            {

              if (biome != TFCBiome.SWAMPLAND)
              {
                if (idsBig[indexBig] != TFC_Core.getTypeForSand(rock1.data1) && rand.nextInt(5) != 0)
                {
                  idsBig[indexBig] = TFC_Core.getTypeForGravel(rock1.data1);
                  metaBig[indexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);

                }

              }
              else if (idsBig[indexBig] != TFC_Core.getTypeForGravel(rock1.data1))
              {
                idsBig[indexBig] = TFC_Core.getTypeForDirt(rock1.data1);
                metaBig[indexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);
              }

            }
          }
          else if (idsTop[index] == TFCBlocks.saltWaterStationary && biome != TFCBiome.OCEAN && biome != TFCBiome.DEEP_OCEAN && biome != TFCBiome.BEACH && biome != TFCBiome.GRAVEL_BEACH) {

            idsBig[indexBig] = TFCBlocks.freshWaterStationary;
          }
        }
      }
    }
  }

















  private void replaceBlocksForBiomeLow(int par1, int par2, Random rand, Block[] idsBig, byte[] metaBig) {
    for (int xCoord = 0; xCoord < 16; xCoord++) {

      for (int zCoord = 0; zCoord < 16; zCoord++) {

        int arrayIndex = xCoord + zCoord * 16;
        int arrayIndexDL = zCoord + xCoord * 16;
        DataLayer rock1 = this.rockLayer1[arrayIndexDL];
        DataLayer rock2 = this.rockLayer2[arrayIndexDL];
        DataLayer rock3 = this.rockLayer3[arrayIndexDL];
        DataLayer stability = this.stabilityLayer[arrayIndexDL];
        TFCBiome biome = (TFCBiome)getBiome(xCoord, zCoord);

        for (int height = 127; height >= 0; height--) {


          int indexBig = arrayIndex * 256 + height;
          metaBig[indexBig] = 0;

          if (height <= 1 + this.seaLevelOffsetMap[arrayIndex] / 3 + this.rand.nextInt(3)) {

            idsBig[indexBig] = Blocks.field_150357_h;
          }
          else if (idsBig[indexBig] == null) {

            convertStone(height, arrayIndex, indexBig, idsBig, metaBig, rock1, rock2, rock3);
            if (TFC_Core.isBeachBiome(biome.field_76756_M) || TFC_Core.isOceanicBiome(biome.field_76756_M))
            {
              if (idsBig[indexBig + 1] == TFCBlocks.saltWaterStationary) {

                idsBig[indexBig] = TFC_Core.getTypeForSand(rock1.data1);
                metaBig[indexBig] = (byte)TFC_Core.getSoilMeta(rock1.data1);
                idsBig[indexBig - 1] = TFC_Core.getTypeForSand(rock1.data1);
                metaBig[indexBig - 1] = (byte)TFC_Core.getSoilMeta(rock1.data1);
              }
            }
          }

          if (height <= 6 && stability.data1 == 1 && idsBig[indexBig] == Blocks.field_150350_a) {

            idsBig[indexBig] = TFCBlocks.lava;
            metaBig[indexBig] = 0;
            if (idsBig[indexBig + 1] != TFCBlocks.lava && rand.nextBoolean()) {

              idsBig[indexBig + 1] = TFCBlocks.lava;
              metaBig[indexBig + 1] = 0;
            }
          }
        }
      }
    }
  }


  public void convertStone(int height, int indexArray, int indexBig, Block[] idsBig, byte[] metaBig, DataLayer rock1, DataLayer rock2, DataLayer rock3) {
    if (idsBig[indexBig] != null && idsBig[indexBig] != Blocks.field_150348_b)
      return;
    if (height <= TFCOptions.rockLayer3Height + this.seaLevelOffsetMap[indexArray]) {

      idsBig[indexBig] = rock3.block;
      metaBig[indexBig] = (byte)rock3.data2;
    }
    else if (height <= TFCOptions.rockLayer2Height + this.seaLevelOffsetMap[indexArray] && height > 55 + this.seaLevelOffsetMap[indexArray] && rock2 != null) {

      idsBig[indexBig] = rock2.block;
      metaBig[indexBig] = (byte)rock2.data2;
    }
    else {

      idsBig[indexBig] = rock1.block;
      metaBig[indexBig] = (byte)rock1.data2;
    }
  }



  public boolean func_73156_b() {
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCChunkProviderGenerate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */