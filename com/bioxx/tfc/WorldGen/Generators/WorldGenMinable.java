package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TileEntities.TEOre;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;








public class WorldGenMinable
  extends WorldGenerator
{
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
  private static Random rand;
  private static World worldObj;
  private final int rarity;
  private final int veinSi;
  private final int veinAm;
  private final int height;
  private int mineHeight = 2;

  private final int diameter;

  private final int vDens;

  private final int hDens;

  private final Block genInBlock;

  private final int genInBlockMeta;

  private final boolean useMarcoVeins;

  private final int grade;
  private final Block minableBlock;
  private int numberOfBlocks;

  public WorldGenMinable(Block block, int j, Block layerBlock, int layerMeta, int rarity, int veinSize, int veinAmount, int height, int diameter, int vDensity, int hDensity, boolean vein, int oreGrade) {
    this.minableBlock = block;
    this.minableBlockMeta = j;
    this.genInBlock = layerBlock;
    this.genInBlockMeta = layerMeta;
    this.rarity = rarity;
    this.veinSi = veinSize;
    this.veinAm = veinAmount;
    this.height = height;
    this.diameter = diameter;
    this.vDens = vDensity;
    this.hDens = hDensity;
    this.useMarcoVeins = vein;
    this.grade = oreGrade;
  }


  public boolean generateBeforeCheck() {
    genBeforeCheck = false;
    genBeforeCheck = oreList.contains(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) }));
    if (!genBeforeCheck)
      oreList.add(Arrays.asList(new Object[] { this.mPBlock, Integer.valueOf(this.minableBlockMeta) }));
    return genBeforeCheck;
  }


  private void createMine(World worldObj, Random rand, int x, int z) {
    for (int loopCount = 0; loopCount < this.veinAm; loopCount++) {

      int temp1 = mPCalculateDensity(this.diameter, this.hDens);
      int temp2 = this.mineHeight + mPCalculateDensity(this.height, this.vDens);
      int temp3 = mPCalculateDensity(this.diameter, this.hDens);
      int posX = x + temp1;
      int posY = temp2;
      int posZ = z + temp3;
      if (!this.useMarcoVeins) {
        bODgenerate(worldObj, rand, posX, posY, posZ, this.veinSi);
      } else {
        bODgenerateVein(worldObj, rand, posX, posY, posZ, this.veinSi);
      }
    }
  }







  public boolean generate(World world, Random random, int x, int z, int min, int max) {
    mPChunkX = x;
    mPChunkZ = z;
    rand = random;
    worldObj = world;
    mineCount = 0;
    oreList.clear();
    this.mPBlock = this.minableBlock;

    if (mPChunkX != mPPrevX || mPChunkZ != mPPrevZ || mPPrevBlock != this.mPBlock || this.minableBlockMeta != mPPrevMeta)
    {
      if (!generateBeforeCheck()) {

        mPPrevX = mPChunkX;
        mPPrevZ = mPChunkZ;
        this.xChunk = mPChunkX;
        this.zChunk = mPChunkZ;
        mPPrevBlock = this.mPBlock;
        mPPrevMeta = this.minableBlockMeta;
        this.mineHeight = min + rand.nextInt(max - min);
        if (this.rarity == 1 || (this.rarity > 0 && rand.nextInt(this.rarity) == 0))
          createMine(worldObj, rand, this.xChunk, this.zChunk);
      }
    }
    return true;
  }


  public int mPCalculateDensity(int oreDistance, float oreDensity) {
    int loopCount = 0;
    int densityValuePassInner = 0;
    int densityValuePass = 0;
    oreDensity *= 0.01F;
    oreDensity = oreDensity * (oreDistance >> 1) + 1.0F;
    loopCount = (int)oreDensity;
    densityValuePassInner = oreDistance / loopCount;
    densityValuePassInner += (oreDistance - densityValuePassInner * loopCount) / loopCount;
    densityValuePass = 0;
    while (loopCount > 0) {

      densityValuePass += rand.nextInt(densityValuePassInner);
      loopCount--;
    }
    return densityValuePass;
  }



  public boolean bODgenerateVein(World world, Random rand, int parX, int parY, int parZ, int xyz) {
    int posX = parX;
    int posY = parY;
    int posZ = parZ;



    int posX2 = 0;
    int posY2 = 0;
    int posZ2 = 0;
    int directionX = 0;
    int directionY = 0;
    int directionZ = 0;
    int directionX2 = 0;
    int directionY2 = 0;
    int directionZ2 = 0;



    int directionChange = 0;
    int directionChange2 = 0;
    int blocksToUse = xyz;
    int blocksToUse2 = 0;

    for (int blocksMade = 0; blocksMade <= blocksToUse; ) {

      blocksToUse2 = 1 + blocksToUse / 30;
      directionChange = rand.nextInt(6);
      directionX = rand.nextInt(2);
      directionY = rand.nextInt(2);
      directionZ = rand.nextInt(2);

      for (int blocksMade1 = 0; blocksMade1 <= blocksToUse2; ) {

        if (directionX == 0 && directionChange != 1)
          posX += rand.nextInt(2);
        if (directionX == 1 && directionChange != 1)
          posX -= rand.nextInt(2);
        if (directionY == 0 && directionChange != 2)
          posY += rand.nextInt(2);
        if (directionY == 1 && directionChange != 2)
          posY -= rand.nextInt(2);
        if (directionZ == 0 && directionChange != 3)
          posZ += rand.nextInt(2);
        if (directionZ == 1 && directionChange != 3)
          posZ -= rand.nextInt(2);
        if (rand.nextInt(4) == 0) {

          posX2 += rand.nextInt(2);
          posY2 += rand.nextInt(2);
          posZ2 += rand.nextInt(2);
          posX2 -= rand.nextInt(2);
          posY2 -= rand.nextInt(2);
          posZ2 -= rand.nextInt(2);
        }
        if (rand.nextInt(3) == 0) {

          posX2 = posX;
          posY2 = posY;
          posZ2 = posZ;
          directionX2 = rand.nextInt(2);
          directionY2 = rand.nextInt(2);
          directionZ2 = rand.nextInt(2);
          directionChange2 = rand.nextInt(6);
          if (directionX2 == 0 && directionChange2 != 0)
            posX2 += rand.nextInt(2);
          if (directionY2 == 0 && directionChange2 != 1)
            posY2 += rand.nextInt(2);
          if (directionZ2 == 0 && directionChange2 != 2)
            posZ2 += rand.nextInt(2);
          if (directionX2 == 1 && directionChange2 != 0)
            posX2 -= rand.nextInt(2);
          if (directionY2 == 1 && directionChange2 != 1)
            posY2 -= rand.nextInt(2);
          if (directionZ2 == 1 && directionChange2 != 2) {
            posZ2 -= rand.nextInt(2);
          }
          for (int blocksMade2 = 0; blocksMade2 <= 1 + blocksToUse2 / 5; ) {

            if (directionX2 == 0 && directionChange2 != 0)
              posX2 += rand.nextInt(2);
            if (directionY2 == 0 && directionChange2 != 1)
              posY2 += rand.nextInt(2);
            if (directionZ2 == 0 && directionChange2 != 2)
              posZ2 += rand.nextInt(2);
            if (directionX2 == 1 && directionChange2 != 0)
              posX2 -= rand.nextInt(2);
            if (directionY2 == 1 && directionChange2 != 1)
              posY2 -= rand.nextInt(2);
            if (directionZ2 == 1 && directionChange2 != 2) {
              posZ2 -= rand.nextInt(2);
            }
            boolean bool1 = false;
            boolean bool2 = false;
            int i = posX & 0xF;
            int j = posZ & 0xF;

            ChunkData chunkData = TFC_Core.getCDM(world).getData(posX >> 4, posZ >> 4);
            int k = (chunkData != null) ? chunkData.heightmap[i + j * 16] : 0;
            posY = Math.min(255, posY + k);

            int n = world.func_72805_g(posX, posY, posZ);
            Block block = world.func_147439_a(posX, posY, posZ);
            bool1 = (block == this.genInBlock);
            bool2 = (n == this.genInBlockMeta || this.genInBlockMeta == -1);

            if (bool1 && bool2)
            {
              if (this.mPBlock != null && world.func_147465_d(posX, posY, posZ, this.mPBlock, this.minableBlockMeta, 2)) {

                TEOre te = (TEOre)world.func_147438_o(posX, posY, posZ);
                if (te != null) {

                  te.baseBlockID = Block.func_149682_b(block);
                  te.baseBlockMeta = n;
                  te.extraData = (byte)(this.grade + 8);
                }
              }
            }
            blocksMade++;
            blocksMade1++;
            blocksMade2++;
          }
        }

        int localX = posX & 0xF;
        int localZ = posZ & 0xF;
        ChunkData data = TFC_Core.getCDM(world).getData(posX >> 4, posZ >> 4);
        int hm = (data != null) ? data.heightmap[localX + localZ * 16] : 0;
        posY = Math.min(255, posY + hm);

        int m = world.func_72805_g(posX, posY, posZ);
        Block b = world.func_147439_a(posX, posY, posZ);
        boolean isCorrectRockType = (b == this.genInBlock);
        boolean isCorrectMeta = (m == this.genInBlockMeta || this.genInBlockMeta == -1);

        if (isCorrectRockType && isCorrectMeta)
        {
          if (this.mPBlock != null && world.func_147465_d(posX, posY, posZ, this.mPBlock, this.minableBlockMeta, 2)) {

            TEOre te = (TEOre)world.func_147438_o(posX, posY, posZ);
            if (te != null) {

              te.baseBlockID = Block.func_149682_b(b);
              te.baseBlockMeta = m;
              te.extraData = (byte)this.grade;
            }
          }
        }
        blocksMade++;
        blocksMade1++;
      }

      parX += rand.nextInt(3) - 1;
      parY += rand.nextInt(3) - 1;
      parZ += rand.nextInt(3) - 1;
      posX = parX;
      posY = parY;
      posZ = parZ;
    }
    return true;
  }



  public boolean bODgenerate(World world, Random rand, int par3, int par4, int par5, int xyz) {
    this.numberOfBlocks = xyz;


    float var6 = rand.nextFloat() * 3.1415927F;
    double var7 = ((par3 + 8) + MathHelper.func_76126_a(var6) * this.numberOfBlocks / 8.0F);
    double var9 = ((par3 + 8) - MathHelper.func_76126_a(var6) * this.numberOfBlocks / 8.0F);
    double var11 = ((par5 + 8) + MathHelper.func_76134_b(var6) * this.numberOfBlocks / 8.0F);
    double var13 = ((par5 + 8) - MathHelper.func_76134_b(var6) * this.numberOfBlocks / 8.0F);
    double var15 = (par4 + rand.nextInt(3) - 2);
    double var17 = (par4 + rand.nextInt(3) - 2);

    for (int var19 = 0; var19 <= this.numberOfBlocks; var19++) {

      double var20 = var7 + (var9 - var7) * var19 / this.numberOfBlocks;
      double var22 = var15 + (var17 - var15) * var19 / this.numberOfBlocks;
      double var24 = var11 + (var13 - var11) * var19 / this.numberOfBlocks;
      double var26 = rand.nextDouble() * this.numberOfBlocks / 16.0D;
      double var28 = (MathHelper.func_76126_a(var19 * 3.1415927F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
      double var30 = (MathHelper.func_76126_a(var19 * 3.1415927F / this.numberOfBlocks) + 1.0F) * var26 + 1.0D;
      int var32 = MathHelper.func_76128_c(var20 - var28 / 2.0D);
      int var33 = MathHelper.func_76128_c(var22 - var30 / 2.0D);
      int var34 = MathHelper.func_76128_c(var24 - var28 / 2.0D);
      int var35 = MathHelper.func_76128_c(var20 + var28 / 2.0D);
      int var36 = MathHelper.func_76128_c(var22 + var30 / 2.0D);
      int var37 = MathHelper.func_76128_c(var24 + var28 / 2.0D);

      for (int posX = var32; posX <= var35; posX++) {

        double var39 = (posX + 0.5D - var20) / var28 / 2.0D;
        if (var39 * var39 < 1.0D)
        {
          for (int posY = var33; posY <= var36; posY++) {

            double var42 = (posY + 0.5D - var22) / var30 / 2.0D;
            if (var39 * var39 + var42 * var42 < 1.0D)
            {
              for (int posZ = var34; posZ <= var37; posZ++) {

                double var45 = (posZ + 0.5D - var24) / var28 / 2.0D;
                int localX = posX & 0xF;
                int localZ = posZ & 0xF;
                ChunkData data = TFC_Core.getCDM(world).getData(posX >> 4, posZ >> 4);
                int hm = (data != null) ? data.heightmap[localX + localZ * 16] : 0;
                posY = Math.min(255, posY + hm);

                int m = world.func_72805_g(posX, posY, posZ);
                Block b = world.func_147439_a(posX, posY, posZ);
                boolean isCorrectRockType = (b == this.genInBlock);
                boolean isCorrectMeta = (m == this.genInBlockMeta || this.genInBlockMeta == -1);

                if (isCorrectRockType && isCorrectMeta)
                {
                  if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D)
                  {
                    if (this.mPBlock != null && world.func_147465_d(posX, posY, posZ, this.mPBlock, this.minableBlockMeta, 2)) {

                      TEOre te = (TEOre)world.func_147438_o(posX, posY, posZ);
                      if (te != null) {

                        te.baseBlockID = Block.func_149682_b(b);
                        te.baseBlockMeta = m;
                        te.extraData = (byte)this.grade;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }

    return true;
  }



  public boolean func_76484_a(World world, Random random, int i, int j, int k) {
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenMinable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */