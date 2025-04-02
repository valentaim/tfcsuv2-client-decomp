package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.TileEntities.TEOre;
import com.bioxx.tfc.api.Enums.EnumOreGen;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.FMLLog;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import org.apache.logging.log4j.Logger;











public class WorldGenMinableTFCNew
  extends WorldGenerator
{
  private static final Logger logger = FMLLog.getLogger();
  private static List<List<Object>> oreList = new ArrayList<>();

  public static int mPChunkX;

  public static int mPChunkZ;

  private int xChunk;

  private int zChunk;

  public Block mPBlock;

  private final int minableBlockMeta;

  public static int mPPrevX;

  public static int mPPrevZ;

  public static Block mPPrevBlock;

  public static int mPPrevMeta;

  private static boolean genBeforeCheck;

  public static int mineCount;

  public static int mineCountM;
  private Random rand;
  private static World worldObj;
  private int oreMin;
  private int oreMax;
  private final int rarity;
  private final EnumOreGen type;
  private final int SphereXSize;
  private final int SphereYSize;
  private final int SphereZSize;
  private final int VeinWidth;
  private final int VeinBaseHeight;
  private final int VeinDownFactor;
  private final int AreaNumber;
  private final int AreaMaxDistance;
  private final int CellSize;
  private final int rnd;
  private final String oreName;
  private final Block minableBlock;
  private int numberOfBlocks;

  public WorldGenMinableTFCNew(EnumOreGen type, Block block, int j, Block layerBlock, int layerMeta, int rarity, int rnd, int SphereXSize, int SphereYSize, int SphereZSize, int VeinWidth, int VeinBaseHeight, int VeinDownFactor, int an, int amd, int cs, String name) {
    this.minableBlock = block;
    this.minableBlockMeta = j;
    this.rarity = rarity;
    this.rnd = rnd;
    this.type = type;
    this.SphereXSize = SphereXSize;
    this.SphereYSize = SphereYSize;
    this.SphereZSize = SphereZSize;
    this.VeinWidth = VeinWidth;
    this.VeinBaseHeight = VeinBaseHeight;
    this.VeinDownFactor = VeinDownFactor;
    this.AreaNumber = an;
    this.AreaMaxDistance = amd;
    this.CellSize = cs;
    this.oreName = name;
  }


  public boolean generateBeforeCheck() {
    genBeforeCheck = false;
    genBeforeCheck = oreList.contains(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) }));
    if (!genBeforeCheck) oreList.add(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) }));
    return genBeforeCheck;
  }


  private int percent(float t) {
    int rrnd = 1 + this.rand.nextInt(this.rnd - 1);
    float z = t / 100.0F * rrnd;
    return (int)z;
  }


  private int toReal(int param) {
    return this.rand.nextBoolean() ? (param - percent(param)) : (param + percent(param));
  }


  private int getCenter(int min, int max, int size) throws Exception {
    int r = max - min - size;
    if (r < 0) throw new Exception();
    return max - size / 2 - this.rand.nextInt(max - min - size);
  }


  private void createMine(World worldObj, int x, int z, int miny, int maxy) {
    int posX = x;
    int posZ = z;

    this.numberOfBlocks = 0;
    int Y = 0; try {
      int size;
      int Ysize;
      switch (this.type) {
        case Area:
          Y = getCenter(miny, maxy, this.AreaMaxDistance);
          bODgenerateArea(worldObj, this.rand, posX, Y, posZ, this.AreaMaxDistance);
          break;
        case Vein:
          size = toReal(this.VeinBaseHeight);
          Y = getCenter(miny, maxy, size);
          bODgenerateVein(worldObj, this.rand, posX, Y, posZ, size);
          break;
        case Lens:
          Ysize = toReal(this.SphereYSize);
          Y = getCenter(miny, maxy, Ysize);
          bODgenerateLens(worldObj, this.rand, posX, Y, posZ, toReal(this.SphereXSize), Ysize,
              toReal(this.SphereZSize));
          break;
      }
      if (TFCOptions.enableDebugMode) logger.warn("Generating " + this.oreName + " at x=" + posX + ", y=" + Y + ", z=" + posZ);
    } catch (Exception e) {
      logger.warn("Generating " + this.oreName + " at x=" + posX + ", y=" + Y + ", z=" + posZ);
      logger.warn("Configuration error ! ");
    }

    if (TFCOptions.enableDebugMode) logger.warn("Generated " + this.numberOfBlocks + " blocks");

  }

  public boolean generate(World world, Random random, int x, int z, int min, int max) {
    mPChunkX = x;
    mPChunkZ = z;
    this.rand = random;
    worldObj = world;
    mineCount = 0;
    oreList.clear();
    this.mPBlock = this.minableBlock;
    this.oreMin = min;
    this.oreMax = max;

    if (mPChunkX != mPPrevX || mPChunkZ != mPPrevZ || mPPrevBlock != this.mPBlock || this.minableBlockMeta != mPPrevMeta)
    {
      if (!generateBeforeCheck()) {

        mPPrevX = mPChunkX;
        mPPrevZ = mPChunkZ;
        this.xChunk = mPChunkX;
        this.zChunk = mPChunkZ;
        mPPrevBlock = this.mPBlock;
        mPPrevMeta = this.minableBlockMeta;

        if (this.rarity == 1 || (this.rarity > 0 && this.rand.nextInt(this.rarity) == 0)) createMine(worldObj, this.xChunk, this.zChunk, min, max);
      }
    }
    return true;
  }



  public boolean bODgenerateVein(World world, Random rand, int parX, int parY, int parZ, int xyz) {
    int posX = parX;
    int posY = parY + xyz / 2;
    int posZ = parZ;

    int vw = toReal(this.VeinWidth);

    boolean[] array = new boolean[xyz];
    for (int l = 0; l < xyz; l++) {
      array[l] = rand.nextBoolean();
    }
    boolean directionxz = rand.nextBoolean();
    int i = 0;

    do {
      if (directionxz) { drawPlane(world, posX++, posY - i, posZ, array, directionxz, vw); }
      else { drawPlane(world, posX, posY - i, posZ++, array, directionxz, vw); }
       i += rand.nextInt(this.VeinDownFactor);
    }
    while (i < xyz / 2);



    return true;
  }


  private void drawPlane(World world, int x, int y, int z, boolean[] array, boolean directionxz, int size) {
    for (int i = 0; i < size; i++) {
      if (directionxz)
      { drawLine(world, x, y, z + i, array, directionxz); }
      else { drawLine(world, x + i, y, z, array, directionxz); }

    }
  }

  private void drawLine(World world, int x, int y, int z, boolean[] array, boolean directionxz) {
    int random = 10;
    for (int l = 0; l < array.length; l++) {

      int r = random - l * 100 / array.length * random / 100;
      if (r == 0) r = 1;


      boolean smes = array[l];

      if (directionxz)

      {
        if (smes) x++;



         }

      else if (smes) { z++; }



      int rar = 1;
      if (l < array.length / 3) { rar = 2; }
      else if (l < array.length / 3 * 2) { rar = 0; }

      if (r == 1) { setBlock(world, x, y + array.length / 2 - l, z, rar); }
      else if (this.rand.nextInt(r) == 1) { setBlock(world, x, y + array.length / 2 - l, z, rar); }

    }
  }

  private static double lengthSq(double x, double y, double z) {
    return x * x + y * y + z * z;
  }


  public boolean bODgenerateArea(World world, Random rand, int par3, int par4, int par5, int amd) {
    amd = toReal(this.AreaMaxDistance);
    Vec3 start = Vec3.func_72443_a(par3, par4, par5);
    for (int i = 0; i < this.AreaNumber; ) {

      while (true) {

        int x = rand.nextInt(amd / 2);
        int y = rand.nextInt(amd / 2);
        int z = rand.nextInt(amd / 2);
        Vec3 to = Vec3.func_72443_a(rand.nextBoolean() ? (par3 + x) : (par3 - x), rand.nextBoolean() ? (par4 + y) : (par4 - y), rand.nextBoolean() ? (par5 + z) : (par5 - z));
        if (start.func_72438_d(to) <= (amd / 2))
        { int rx = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
          int ry = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
          int rz = 1 + this.CellSize / 3 + rand.nextInt((int)(this.CellSize * 0.66666666666D));
          bODgenerateLens(world, rand, (int)to.field_72450_a, (int)to.field_72448_b, (int)to.field_72449_c, rx, ry, rz); break; }
      }  i++;
    }  return true;
  }



  public boolean bODgenerateLens(World world, Random rand, int par3, int par4, int par5, int dx, int dy, int dz) {
    int xpos = par3;
    int ypos = par4;
    int zpos = par5;

    double radiusX = (dx / 2);
    double radiusY = (dy / 2);
    double radiusZ = (dz / 2);

    radiusX += 0.5D;
    radiusY += 0.5D;
    radiusZ += 0.5D;

    double invRadiusX = 1.0D / radiusX;
    double invRadiusY = 1.0D / radiusY;
    double invRadiusZ = 1.0D / radiusZ;

    int ceilRadiusX = (int)Math.ceil(radiusX);
    int ceilRadiusY = (int)Math.ceil(radiusY);
    int ceilRadiusZ = (int)Math.ceil(radiusZ);

    double nextXn = 0.0D; int x;
    label35: for (x = 0; x <= ceilRadiusX; x++) {
      double xn = nextXn;
      nextXn = (x + 1) * invRadiusX;
      double nextYn = 0.0D; int y;
      label34: for (y = 0; y <= ceilRadiusY; y++) {
        double yn = nextYn;
        nextYn = (y + 1) * invRadiusY;
        double nextZn = 0.0D;
        for (int z = 0; z <= ceilRadiusZ; z++) {
          double zn = nextZn;
          nextZn = (z + 1) * invRadiusZ;

          double distanceSq = lengthSq(xn, yn, zn);
          if (distanceSq > 1.0D) {
            if (z == 0) {
              if (y == 0) {
                break label35;
              }

              break label34;
            }
            break;
          }
          double px = lengthSq(nextXn, yn, zn);
          double py = lengthSq(xn, nextYn, zn);
          double pz = lengthSq(xn, yn, nextZn);

          if (px <= 0.15D && py <= 0.15D && pz <= 0.05D) { createEllipse(world, xpos, ypos, zpos, x, y, z, 2, 1); }
          else if (px <= 0.5D && py <= 0.5D && pz <= 0.43333D) { createEllipse(world, xpos, ypos, zpos, x, y, z, 8, 0); }
          else { createEllipse(world, xpos, ypos, zpos, x, y, z, 25, 2); }

        }
      }
    }


    return true;
  }


  private void createEllipse(World world, int xpos, int ypos, int zpos, int x, int y, int z, int r, int g) {
    if (r == 0) { setBlock(world, xpos + x, ypos + y, zpos + z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos + y, zpos + z, g); }
     if (r == 0) { setBlock(world, xpos - x, ypos + y, zpos + z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos + y, zpos + z, g); }
     if (r == 0) { setBlock(world, xpos + x, ypos - y, zpos + z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos - y, zpos + z, g); }
     if (r == 0) { setBlock(world, xpos + x, ypos + y, zpos - z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos + y, zpos - z, g); }
     if (r == 0) { setBlock(world, xpos - x, ypos - y, zpos + z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos - y, zpos + z, g); }
     if (r == 0) { setBlock(world, xpos + x, ypos - y, zpos - z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos + x, ypos - y, zpos - z, g); }
     if (r == 0) { setBlock(world, xpos - x, ypos + y, zpos - z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos + y, zpos - z, g); }
     if (r == 0) { setBlock(world, xpos - x, ypos - y, zpos - z, g); }
    else if (this.rand.nextInt(r) == 1) { setBlock(world, xpos - x, ypos - y, zpos - z, g); }

  }


  private boolean canPlace(Block b) {
    return (b instanceof com.bioxx.tfc.Blocks.Terrain.BlockMM || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockIgIn || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockSed || b instanceof com.bioxx.tfc.Blocks.Terrain.BlockIgEx);
  }


  private void setBlock(World world, int posX, int posY, int posZ, int g) {
    int m = world.func_72805_g(posX, posY, posZ);
    Block b = world.func_147439_a(posX, posY, posZ);






    if (canPlace(b) && posY >= this.oreMin && posY <= this.oreMax &&
      this.mPBlock != null && world.func_147465_d(posX, posY, posZ, this.mPBlock, this.minableBlockMeta, 2)) {

      TEOre te = (TEOre)world.func_147438_o(posX, posY, posZ);
      if (te != null) {

        te.baseBlockID = Block.func_149682_b(b);
        te.baseBlockMeta = m;
        te.extraData = (byte)g;
        this.numberOfBlocks++;
      }
    }
  }





  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenMinableTFCNew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */