package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;





public class WorldGenCustomBigTree
  extends WorldGenerator
{
  private static final byte[] OTHER_COORD_PAIRS = new byte[] { 2, 0, 0, 1, 2, 1 };
  private final Random rand = new Random();

  private World worldObj;

  private int[] basePos = new int[] { 0, 0, 0 };

  private int heightLimit;
  private int height;
  private static final double HEIGHT_ATTENUATION = 0.618D;
  private static final double BRANCH_SLOPE = 0.381D;
  private double scaleWidth = 1.0D;
  private double leafDensity = 1.0D;









  private int heightLimitLimit = 12;




  private int leafDistanceLimit = 4;


  private int[][] leafNodes;

  private final int treeId;


  public WorldGenCustomBigTree(boolean par1, int id) {
    super(par1);
    this.treeId = id;
  }





  private int checkBlockLine(int[] startCoords, int[] endCoords) {
    byte direction;
    int[] distance = { 0, 0, 0 };
    byte axis = 0;

    byte newAxis;
    for (newAxis = 0; axis < 3; axis = (byte)(axis + 1)) {

      distance[axis] = endCoords[axis] - startCoords[axis];
      if (Math.abs(distance[axis]) > Math.abs(distance[newAxis])) {
        newAxis = axis;
      }
    }
    if (distance[newAxis] == 0)
    {
      return -1;
    }


    byte newAxisInverse = OTHER_COORD_PAIRS[newAxis];
    byte newAxisInversePerp = OTHER_COORD_PAIRS[newAxis + 3];


    if (distance[newAxis] > 0) {
      direction = 1;
    } else {
      direction = -1;
    }
    double var9 = distance[newAxisInverse] / distance[newAxis];
    double var11 = distance[newAxisInversePerp] / distance[newAxis];
    int[] coords = { 0, 0, 0 };
    int result = 0;

    int endPoint;
    for (endPoint = distance[newAxis] + direction; result != endPoint; result += direction) {

      coords[newAxis] = startCoords[newAxis] + result;
      coords[newAxisInverse] = MathHelper.func_76128_c(startCoords[newAxisInverse] + result * var9);
      coords[newAxisInversePerp] = MathHelper.func_76128_c(startCoords[newAxisInversePerp] + result * var11);
      Block block = this.worldObj.func_147439_a(coords[0], coords[1], coords[2]);
      if (!block.isAir((IBlockAccess)this.worldObj, coords[0], coords[1], coords[2]) && block != TFCBlocks.leaves && block != TFCBlocks.leaves2) {
        break;
      }
    }
    return (result == endPoint) ? -1 : Math.abs(result);
  }




  public boolean func_76484_a(World world, Random rand, int x, int y, int z) {
    this.worldObj = world;
    long seed = rand.nextLong();
    this.rand.setSeed(seed);
    this.basePos[0] = x;
    this.basePos[1] = y;
    this.basePos[2] = z;

    if (this.heightLimit == 0) {
      this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
    }
    if (!validTreeLocation())
    {
      return false;
    }


    generateLeafNodeList();
    generateLeaves();
    generateTrunk();
    generateLeafNodeBases();
    return true;
  }






  private void generateLeafNode(int x, int y, int z) {
    int yCoord = y;
    for (int range = y + this.leafDistanceLimit; yCoord < range; yCoord++) {

      float leafSizeAtHeight = leafSize(yCoord - y);
      genTreeLayer(x, yCoord, z, leafSizeAtHeight, (byte)1, TFCBlocks.leaves);
    }
  }





  private void generateLeafNodeBases() {
    int i = 0;

    for (int[] location = { this.basePos[0], this.basePos[1], this.basePos[2] }; i < this.leafNodes.length; i++) {

      int[] leafPoints = this.leafNodes[i];
      int[] newLocation = { leafPoints[0], leafPoints[1], leafPoints[2] };
      location[1] = leafPoints[3];
      int baseHeight = location[1] - this.basePos[1];
      if (leafNodeNeedsBase(baseHeight)) {
        placeBlockLine(location, newLocation, TFCBlocks.logNatural);
      }
    }
  }




  private void generateLeafNodeList() {
    this.height = (int)(this.heightLimit * 0.618D);
    if (this.height >= this.heightLimit) {
      this.height = this.heightLimit - 1;
    }
    int var1 = (int)(1.382D + Math.pow(this.leafDensity * this.heightLimit / 13.0D, 2.0D));
    if (var1 < 1) {
      var1 = 1;
    }
    int[][] var2 = new int[var1 * this.heightLimit][4];
    int trunkTop = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
    int var4 = 1;
    int treeTop = this.basePos[1] + this.height;
    int height = trunkTop - this.basePos[1];
    var2[0][0] = this.basePos[0];
    var2[0][1] = trunkTop;
    var2[0][2] = this.basePos[2];
    var2[0][3] = treeTop;
    trunkTop--;

    while (height >= 0) {

      int var7 = 0;
      float var8 = layerSize(height);

      if (var8 < 0.0F) {

        trunkTop--;
        height--;

        continue;
      }
      for (double var9 = 0.5D; var7 < var1; var7++) {

        double var11 = this.scaleWidth * var8 * (this.rand.nextFloat() + 0.328D);
        double var13 = this.rand.nextFloat() * 2.0D * Math.PI;
        int var15 = MathHelper.func_76128_c(var11 * Math.sin(var13) + this.basePos[0] + var9);
        int var16 = MathHelper.func_76128_c(var11 * Math.cos(var13) + this.basePos[2] + var9);
        int[] var17 = { var15, trunkTop, var16 };
        int[] var18 = { var15, trunkTop + this.leafDistanceLimit, var16 };

        if (checkBlockLine(var17, var18) == -1) {

          int[] var19 = { this.basePos[0], this.basePos[1], this.basePos[2] };
          double var20 = Math.sqrt(Math.pow(Math.abs(this.basePos[0] - var17[0]), 2.0D) + Math.pow(Math.abs(this.basePos[2] - var17[2]), 2.0D));
          double var22 = var20 * 0.381D;

          if (var17[1] - var22 > treeTop) {
            var19[1] = treeTop;
          } else {
            var19[1] = (int)(var17[1] - var22);
          }
          if (checkBlockLine(var19, var17) == -1) {

            var2[var4][0] = var15;
            var2[var4][1] = trunkTop;
            var2[var4][2] = var16;
            var2[var4][3] = var19[1];
            var4++;
          }
        }
      }
      trunkTop--;
      height--;
    }

    this.leafNodes = new int[var4][4];
    System.arraycopy(var2, 0, this.leafNodes, 0, var4);
  }





  private void generateLeaves() {
    for (int i = 0; i < this.leafNodes.length; i++) {

      int x = this.leafNodes[i][0];
      int y = this.leafNodes[i][1];
      int z = this.leafNodes[i][2];
      generateLeafNode(x, y, z);
    }
  }






  private void generateTrunk() {
    int x = this.basePos[0];
    int y = this.basePos[1];
    int maxY = this.basePos[1] + this.height;
    int z = this.basePos[2];
    int[] bottom = { x, y, z };
    int[] top = { x, maxY, z };
    placeBlockLine(bottom, top, TFCBlocks.logNatural);
  }















  private void genTreeLayer(int x, int y, int z, float leafSizeAtHeight, byte axis, Block b) {
    int range = (int)(leafSizeAtHeight + 0.618D);
    byte axisInverse = OTHER_COORD_PAIRS[axis];
    byte axisInversePerp = OTHER_COORD_PAIRS[axis + 3];
    int[] startCoords = { x, y, z };
    int[] coords = { 0, 0, 0 };
    int width1 = -range;
    int width2 = -range;

    for (coords[axis] = startCoords[axis]; width1 <= range; width1++) {

      coords[axisInverse] = startCoords[axisInverse] + width1;
      width2 = -range;

      while (width2 <= range) {

        double distance = Math.sqrt(Math.pow(Math.abs(width1) + 0.5D, 2.0D) + Math.pow(Math.abs(width2) + 0.5D, 2.0D));

        if (distance > leafSizeAtHeight) {

          width2++;

          continue;
        }
        coords[axisInversePerp] = startCoords[axisInversePerp] + width2;
        Block block = this.worldObj.func_147439_a(coords[0], coords[1], coords[2]);
        if (this.worldObj.func_147437_c(coords[0], coords[1], coords[2]) || block == TFCBlocks.leaves || block == TFCBlocks.leaves2)
          func_150516_a(this.worldObj, coords[0], coords[1], coords[2], b, this.treeId);
        width2++;
      }
    }
  }





  private float layerSize(int height) {
    float size;
    if (height < this.heightLimit * 0.3D)
    {
      return -1.618F;
    }


    float halfLimit = this.heightLimit / 2.0F;
    float leafBase = this.heightLimit / 2.0F - height;


    if (leafBase == 0.0F) {
      size = halfLimit;
    } else if (Math.abs(leafBase) >= halfLimit) {
      size = 0.0F;
    } else {
      size = (float)Math.sqrt(Math.pow(Math.abs(halfLimit), 2.0D) - Math.pow(Math.abs(leafBase), 2.0D));
    }
    size *= 0.5F;
    return size;
  }






  private boolean leafNodeNeedsBase(int par1) {
    return (par1 >= this.heightLimit * 0.2D);
  }


  private float leafSize(int height) {
    return (height >= 0 && height < this.leafDistanceLimit) ? ((height != 0 && height != this.leafDistanceLimit - 1) ? 3.0F : 2.0F) : -1.0F;
  }





  private void placeBlockLine(int[] bottom, int[] top, Block b) {
    int[] location = { 0, 0, 0 };
    byte axis = 0;

    byte newAxis;
    for (newAxis = 0; axis < 3; axis = (byte)(axis + 1)) {

      location[axis] = top[axis] - bottom[axis];
      if (Math.abs(location[axis]) > Math.abs(location[newAxis])) {
        newAxis = axis;
      }
    }
    if (location[newAxis] != 0) {

      byte direction, newAxisInverse = OTHER_COORD_PAIRS[newAxis];
      byte newAxisInversePerp = OTHER_COORD_PAIRS[newAxis + 3];


      if (location[newAxis] > 0) {
        direction = 1;
      } else {
        direction = -1;
      }
      double var10 = location[newAxisInverse] / location[newAxis];
      double var12 = location[newAxisInversePerp] / location[newAxis];
      int[] coords = { 0, 0, 0 };
      int loc;
      for (loc = 0; loc != location[newAxis] + direction; loc += direction) {

        coords[newAxis] = MathHelper.func_76128_c((bottom[newAxis] + loc) + 0.5D);
        coords[newAxisInverse] = MathHelper.func_76128_c(bottom[newAxisInverse] + loc * var10 + 0.5D);
        coords[newAxisInversePerp] = MathHelper.func_76128_c(bottom[newAxisInversePerp] + loc * var12 + 0.5D);
        Block block = this.worldObj.func_147439_a(coords[0], coords[1], coords[2]);
        if (this.worldObj.func_147437_c(coords[0], coords[1], coords[2]) || block == TFCBlocks.leaves || block == TFCBlocks.leaves2) {
          func_150516_a(this.worldObj, coords[0], coords[1], coords[2], b, this.treeId);
        }
      }
    }
  }





  public void func_76487_a(double heightScale, double widthScale, double densityScale) {
    this.heightLimitLimit = (int)(heightScale * 12.0D);
    if (heightScale > 0.5D)
      this.leafDistanceLimit = 5;
    this.scaleWidth = widthScale;
    this.leafDensity = densityScale;
  }






  private boolean validTreeLocation() {
    int[] coords = { this.basePos[0], this.basePos[1], this.basePos[2] };
    int[] topCoords = { this.basePos[0], this.basePos[1] + this.heightLimit - 1, this.basePos[2] };
    Block block = this.worldObj.func_147439_a(this.basePos[0], this.basePos[1] - 1, this.basePos[2]);

    if (!TFC_Core.isSoil(block))
    {
      return false;
    }


    int distance = checkBlockLine(coords, topCoords);
    if (distance == -1)
    {
      return true;
    }
    if (distance < 6)
    {
      return false;
    }


    this.heightLimit = distance;
    return true;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomBigTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */