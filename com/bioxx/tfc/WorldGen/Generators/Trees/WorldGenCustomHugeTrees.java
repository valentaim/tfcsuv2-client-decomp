package com.bioxx.tfc.WorldGen.Generators.Trees;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;






public class WorldGenCustomHugeTrees
  extends WorldGenerator
{
  private final int field_48195_a;
  private final int woodMetadata;
  private final int leavesMetadata;

  public WorldGenCustomHugeTrees(boolean par1, int par2, int par3, int par4) {
    super(par1);
    this.field_48195_a = par2;
    this.woodMetadata = par3;
    this.leavesMetadata = par4;
  }


  private void genLeaves(World par1World, int par2, int par3, int par4, int par5, Random par6Random) {
    byte var7 = 2;
    for (int var8 = par4 - var7; var8 <= par4; var8++) {

      int var9 = var8 - par4;
      int var10 = par5 + 1 - var9;
      for (int var11 = par2 - var10; var11 <= par2 + var10 + 1; var11++) {

        int var12 = var11 - par2;
        for (int var13 = par3 - var10; var13 <= par3 + var10 + 1; var13++) {

          int var14 = var13 - par3;
          if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && ((var12 <= 0 && var14 <= 0) || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random

            .nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1)) &&
            !par1World.func_147439_a(var11, var8, var13).func_149662_c())
          {
            func_150516_a(par1World, var11, var8, var13, TFCBlocks.leaves, this.leavesMetadata);
          }
        }
      }
    }
  }



  public boolean func_76484_a(World world, Random rand, int xCoord, int yCoord, int zCoord) {
    int var6 = rand.nextInt(3) + this.field_48195_a;
    boolean canGenHere = true;

    if (yCoord >= 1 && yCoord + var6 + 1 <= 256) {





      for (int blockUnder = yCoord; blockUnder <= yCoord + 1 + var6; blockUnder++) {

        byte var9 = 2;
        if (blockUnder == yCoord)
          var9 = 1;
        if (blockUnder >= yCoord + 1 + var6 - 2) {
          var9 = 2;
        }
        for (int tempX = xCoord - var9; tempX <= xCoord + var9 && canGenHere; tempX++) {

          for (int tempZ = zCoord - var9; tempZ <= zCoord + var9 && canGenHere; tempZ++) {

            if (blockUnder >= 0 && blockUnder < 256) {

              Block block = world.func_147439_a(tempX, blockUnder, tempZ);
              if (!block.isAir((IBlockAccess)world, tempX, blockUnder, tempZ) && (block != TFCBlocks.leaves || block != TFCBlocks.leaves2) && (block != TFCBlocks.grass || block != TFCBlocks.grass2) && (block != TFCBlocks.dirt || block != TFCBlocks.dirt2) && (block != TFCBlocks.logNatural || block != TFCBlocks.logNatural2) && (block != TFCBlocks.sapling || block != TFCBlocks.sapling2) &&





                !TFC_Core.isSoil(block))
              {
                canGenHere = false;
              }
            }
            else {

              canGenHere = false;
            }
          }
        }
      }

      if (!canGenHere)
      {
        return false;
      }


      if (TFC_Core.isSoil(world.func_147439_a(xCoord, yCoord - 1, zCoord)) && yCoord < 256 - var6 - 1) {

        DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 0);
        world.func_147465_d(xCoord, yCoord - 1, zCoord, TFC_Core.getTypeForDirt(rockLayer1.data2), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
        world.func_147465_d(xCoord + 1, yCoord - 1, zCoord, TFC_Core.getTypeForDirt(rockLayer1.data2), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
        world.func_147465_d(xCoord, yCoord - 1, zCoord + 1, TFC_Core.getTypeForDirt(rockLayer1.data2), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
        world.func_147465_d(xCoord + 1, yCoord - 1, zCoord + 1, TFC_Core.getTypeForDirt(rockLayer1.data2), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
        genLeaves(world, xCoord, zCoord, yCoord + var6, 2, rand);
        int var14;
        for (var14 = yCoord + var6 - 2 - rand.nextInt(4); var14 > yCoord + var6 / 2; var14 -= 2 + rand.nextInt(4)) {

          float var15 = rand.nextFloat() * 3.1415927F * 2.0F;
          int tempZ = xCoord + (int)(0.5F + MathHelper.func_76134_b(var15) * 4.0F);
          int var12 = zCoord + (int)(0.5F + MathHelper.func_76126_a(var15) * 4.0F);
          genLeaves(world, tempZ, var12, var14, 0, rand);

          for (int var13 = 0; var13 < 5; var13++) {

            tempZ = xCoord + (int)(1.5F + MathHelper.func_76134_b(var15) * var13);
            var12 = zCoord + (int)(1.5F + MathHelper.func_76126_a(var15) * var13);
            func_150516_a(world, tempZ, var14 - 3 + var13 / 2, var12, TFCBlocks.logNatural, this.woodMetadata);
          }
        }

        for (int tempX = 0; tempX < var6; tempX++) {

          Block id = world.func_147439_a(xCoord, yCoord + tempX, zCoord);
          if (id.isAir((IBlockAccess)world, xCoord, yCoord + tempX, zCoord) || id == TFCBlocks.leaves || id == TFCBlocks.leaves2) {

            func_150516_a(world, xCoord, yCoord + tempX, zCoord, TFCBlocks.logNatural, this.woodMetadata);
            if (tempX > 0) {

              if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord - 1, yCoord + tempX, zCoord))
                func_150516_a(world, xCoord - 1, yCoord + tempX, zCoord, TFCBlocks.vine, 8);
              if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord, yCoord + tempX, zCoord - 1)) {
                func_150516_a(world, xCoord, yCoord + tempX, zCoord - 1, TFCBlocks.vine, 1);
              }
            }
          }
          if (tempX < var6 - 1) {

            id = world.func_147439_a(xCoord + 1, yCoord + tempX, zCoord);
            if (id.isAir((IBlockAccess)world, xCoord, yCoord + tempX, zCoord) || id == TFCBlocks.leaves || id == TFCBlocks.leaves2) {

              func_150516_a(world, xCoord + 1, yCoord + tempX, zCoord, TFCBlocks.logNatural, this.woodMetadata);
              if (tempX > 0) {

                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord + 2, yCoord + tempX, zCoord))
                  func_150516_a(world, xCoord + 2, yCoord + tempX, zCoord, TFCBlocks.vine, 2);
                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord + 1, yCoord + tempX, zCoord - 1)) {
                  func_150516_a(world, xCoord + 1, yCoord + tempX, zCoord - 1, TFCBlocks.vine, 1);
                }
              }
            }
            id = world.func_147439_a(xCoord + 1, yCoord + tempX, zCoord + 1);
            if (id.isAir((IBlockAccess)world, xCoord, yCoord + tempX, zCoord) || id == TFCBlocks.leaves || id == TFCBlocks.leaves2) {

              func_150516_a(world, xCoord + 1, yCoord + tempX, zCoord + 1, TFCBlocks.logNatural, this.woodMetadata);
              if (tempX > 0) {

                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord + 2, yCoord + tempX, zCoord + 1))
                  func_150516_a(world, xCoord + 2, yCoord + tempX, zCoord + 1, TFCBlocks.vine, 2);
                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord + 1, yCoord + tempX, zCoord + 2)) {
                  func_150516_a(world, xCoord + 1, yCoord + tempX, zCoord + 2, TFCBlocks.vine, 4);
                }
              }
            }
            id = world.func_147439_a(xCoord, yCoord + tempX, zCoord + 1);
            if (id.isAir((IBlockAccess)world, xCoord, yCoord + tempX, zCoord) || id == TFCBlocks.leaves || id == TFCBlocks.leaves2) {

              func_150516_a(world, xCoord, yCoord + tempX, zCoord + 1, TFCBlocks.logNatural, this.woodMetadata);
              if (tempX > 0) {

                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord - 1, yCoord + tempX, zCoord + 1))
                  func_150516_a(world, xCoord - 1, yCoord + tempX, zCoord + 1, TFCBlocks.vine, 8);
                if (rand.nextInt(3) > 0 && world.func_147437_c(xCoord, yCoord + tempX, zCoord + 2))
                  func_150516_a(world, xCoord, yCoord + tempX, zCoord + 2, TFCBlocks.vine, 4);
              }
            }
          }
        }
        return true;
      }


      return false;
    }




    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\Trees\WorldGenCustomHugeTrees.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */