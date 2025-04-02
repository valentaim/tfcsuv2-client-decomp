package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;



public class WorldGenSoilPits
  implements IWorldGenerator
{
  private static WorldGenBerryBush cranberryGen = new WorldGenBerryBush(false, 6, 15, 1, 6, TFCBlocks.peatGrass);
  private static WorldGenBerryBush cloudberryGen = new WorldGenBerryBush(false, 10, 12, 1, 6, TFCBlocks.peatGrass);







  
  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    chunkX *= 16;
    chunkZ *= 16;
    
    int x = chunkX + random.nextInt(16) + 8;
    int z = chunkZ + random.nextInt(16) + 8;
    generateClay(world, random, x, world.func_72825_h(x, z), z);
    
    x = chunkX + random.nextInt(16) + 8;
    z = chunkZ + random.nextInt(16) + 8;
    if (generatePeat(world, random, x, world.func_72825_h(x, z), z))
    {
      if (random.nextInt(5) == 0)
      {
        if (!cloudberryGen.func_76484_a(world, random, x, world.func_72825_h(x, z) + 1, z)) {
          cranberryGen.func_76484_a(world, random, x, world.func_72825_h(x, z) + 1, z);
        }
      }
    }
  }
  
  public boolean generatePeat(World world, Random random, int xCoord, int yCoord, int zCoord) {
    int var6 = random.nextInt(16) + 8;
    byte var7 = 2;
    boolean flag = false;
    
    if (random.nextInt(50) == 0 && yCoord <= 144)
    {
      for (int x = xCoord - var6; x <= xCoord + var6; x++) {
        
        for (int z = zCoord - var6; z <= zCoord + var6; z++) {
          
          int var10 = x - xCoord;
          int var11 = z - zCoord;
          if (var10 * var10 + var11 * var11 <= var6 * var6)
          {
            for (int y = yCoord - var7; y <= yCoord + var7; y++) {
              
              Block block = world.func_147439_a(x, y, z);
              if (TFC_Climate.isSwamp(world, x, y, z))
              {
                if (TFC_Core.isDirt(block) || TFC_Core.isClay(block) || TFC_Core.isPeat(block)) {
                  
                  world.func_147465_d(x, y, z, TFCBlocks.peat, 0, 2);
                  flag = true;
                }
                else if (TFC_Core.isGrass(block)) {
                  
                  world.func_147465_d(x, y, z, TFCBlocks.peatGrass, 0, 2);
                  flag = true;
                } 
              }
            } 
          }
        } 
      } 
    }
    return flag;
  }

  
  public boolean generateClay(World world, Random rand, int i, int j, int k) {
    int radius = rand.nextInt(14) + 2;
    byte depth = (byte)(rand.nextInt(3) + 1);
    boolean flag = false;
    if (rand.nextInt(30) == 0 && j <= 150)
    {
      for (int xCoord = i - radius; xCoord <= i + radius; xCoord++) {
        
        for (int zCoord = k - radius; zCoord <= k + radius; zCoord++) {
          
          int x = xCoord - i;
          int z = zCoord - k;
          if (x * x + z * z <= radius * radius && TFC_Climate.getRainfall(world, xCoord, 144, zCoord) >= 500.0F) {
            
            flag = false;
            for (int yCoord = j - depth; yCoord <= j + depth; yCoord++) {
              
              Block block = world.func_147439_a(xCoord, yCoord, zCoord);
              if (TFC_Climate.getCacheManager(world) != null) {
                
                DataLayer rockLayer1 = TFC_Climate.getCacheManager(world).getRockLayerAt(xCoord, zCoord, 0);
                if (block == TFCBlocks.dirt || block == TFCBlocks.dirt2) {
                  
                  world.func_147465_d(xCoord, yCoord, zCoord, TFC_Core.getTypeForClay(block), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
                  flag = true;
                }
                else if (block == TFCBlocks.grass || block == TFCBlocks.grass2) {
                  
                  world.func_147465_d(xCoord, yCoord, zCoord, TFC_Core.getTypeForClayGrass(block), TFC_Core.getSoilMetaFromStone(rockLayer1.block, rockLayer1.data2), 2);
                  flag = true;
                } 
              } 
            } 
            if (flag && rand.nextInt(15) == 0) {
              
              int y = world.func_72825_h(xCoord, zCoord);
              if (world.func_147437_c(xCoord, y, zCoord) && TFC_Core.isSoil(world.func_147439_a(xCoord, y - 1, zCoord)))
                world.func_147465_d(xCoord, y, zCoord, TFCBlocks.flora, 0, 2); 
            } 
          } 
        } 
      } 
    }
    return flag;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenSoilPits.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */