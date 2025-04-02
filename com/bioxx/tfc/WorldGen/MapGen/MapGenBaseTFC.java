package com.bioxx.tfc.WorldGen.MapGen;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;



public class MapGenBaseTFC
{
  protected int range = 8;


  protected Random rand = new Random();


  protected World worldObj;


  public void generate(IChunkProvider par1IChunkProvider, World par2World, int par3, int par4, Block[] idsBig) {
    int var6 = this.range;
    this.worldObj = par2World;
    this.rand.setSeed(par2World.func_72905_C());
    long var7 = this.rand.nextLong();
    long var9 = this.rand.nextLong();

    for (int var11 = par3 - var6; var11 <= par3 + var6; var11++) {

      long var13 = var11 * var7;
      for (int var12 = par4 - var6; var12 <= par4 + var6; var12++) {

        long var15 = var12 * var9;
        this.rand.setSeed(var13 ^ var15 ^ par2World.func_72905_C());
        recursiveGenerate(par2World, var11, var12, par3, par4, idsBig);
      }
    }
  }

  protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, Block[] idsBig) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\MapGen\MapGenBaseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */