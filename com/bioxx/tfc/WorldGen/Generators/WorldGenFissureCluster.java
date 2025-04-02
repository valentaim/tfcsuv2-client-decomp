package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;


public class WorldGenFissureCluster
  implements IWorldGenerator
{
  private Random rand;
  private int waterRarity = 225;

  private WorldGenFissure fissureGenWater = new WorldGenFissure(TFCBlocks.freshWater);
  private WorldGenFissure fissureGenLava = new WorldGenFissure(TFCBlocks.lava);
  private WorldGenFissure fissureGenAir = new WorldGenFissure(null);




  public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
    this.rand = random;
    chunkX *= 16;
    chunkZ *= 16;

    int startX = chunkX + random.nextInt(16) + 8;
    int startZ = chunkZ + random.nextInt(16) + 8;

    if (this.rand.nextInt(this.waterRarity) == 0) {

      int num = 3 + this.rand.nextInt(10);
      for (int i = 0; i < num; i++) {

        int x = startX - 30 + random.nextInt(60);
        int z = startZ - 30 + random.nextInt(60);
        int y = world.func_72825_h(x, z) - 1;
        if (this.rand.nextInt(10) == 0) {
          this.fissureGenAir.generate(world, this.rand, x, y, z);
        } else {
          this.fissureGenWater.generate(world, this.rand, x, y, z);
        }
      }
    } else if (this.rand.nextInt(400) == 0) {

      int num = 3 + this.rand.nextInt(10);
      for (int i = 0; i < num; i++) {

        int x = startX - 30 + random.nextInt(60);
        int z = startZ - 30 + random.nextInt(60);
        int y = world.func_72825_h(x, z) - 1;

        if (this.rand.nextInt(10) == 0) {
          this.fissureGenAir.generate(world, this.rand, x, y, z);
        } else {
          this.fissureGenLava.generate(world, this.rand, x, y, z);
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\Generators\WorldGenFissureCluster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */