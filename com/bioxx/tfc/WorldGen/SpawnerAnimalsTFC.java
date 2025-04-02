package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.TerraFirmaCraft;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.init.Blocks;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;







public final class SpawnerAnimalsTFC
{
  public static boolean canCreatureTypeSpawnAtLocation(EnumCreatureType par0EnumCreatureType, World par1World, int par2, int par3, int par4) {
    if (par0EnumCreatureType.func_75600_c() == Material.field_151586_h)
    {
      return (par1World.func_147439_a(par2, par3, par4).func_149688_o().func_76224_d() && par1World
        .func_147439_a(par2, par3 - 1, par4).func_149688_o().func_76224_d() &&
        !par1World.func_147439_a(par2, par3 + 1, par4).func_149721_r());
    }
    if (!World.func_147466_a((IBlockAccess)par1World, par2, par3 - 1, par4))
    {
      return false;
    }


    Block b = par1World.func_147439_a(par2, par3 - 1, par4);
    boolean spawnBlock = (b != null && b.canCreatureSpawn(par0EnumCreatureType, (IBlockAccess)par1World, par2, par3 - 1, par4));
    return (spawnBlock && b != Blocks.field_150357_h &&
      !par1World.func_147439_a(par2, par3, par4).func_149721_r() &&
      !par1World.func_147439_a(par2, par3, par4).func_149688_o().func_76224_d() &&
      !par1World.func_147439_a(par2, par3 + 1, par4).func_149721_r());
  }







  public static void performWorldGenSpawning(World world, TFCBiome biome, int par2, int par3, int par4, int par5, Random par6Random) {
    List<BiomeGenBase.SpawnListEntry> list = TFCChunkProviderGenerate.getCreatureSpawnsByChunk(world, biome, par2, par3);
    if (!list.isEmpty())
    {
      while (par6Random.nextFloat() < biome.func_76741_f()) {

        BiomeGenBase.SpawnListEntry spawnlistentry = (BiomeGenBase.SpawnListEntry)WeightedRandom.func_76271_a(world.field_73012_v, list);
        IEntityLivingData entitylivingdata = null;
        int i1 = spawnlistentry.field_76301_c + par6Random.nextInt(1 + spawnlistentry.field_76299_d - spawnlistentry.field_76301_c);
        int j1 = par2 + par6Random.nextInt(par4);
        int k1 = par3 + par6Random.nextInt(par5);
        int l1 = j1;
        int i2 = k1;

        for (int j2 = 0; j2 < i1; j2++) {

          boolean flag = false;
          for (int k2 = 0; !flag && k2 < 4; k2++) {

            int l2 = world.func_72825_h(j1, k1);
            if (canCreatureTypeSpawnAtLocation(EnumCreatureType.creature, world, j1, l2, k1)) {
              EntityLiving entityliving;


              try {
                entityliving = spawnlistentry.field_76300_b.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
              }
              catch (Exception exception) {

                TerraFirmaCraft.LOG.catching(exception);
              }

              if (entityliving instanceof com.bioxx.tfc.Entities.Mobs.EntityFishTFC &&
                entityliving.func_70681_au().nextInt(60) > TFC_Core.getCDM(world).getFishPop(j1 >> 4, k1 >> 4)) {
                return;
              }


              float f = j1 + 0.5F;
              float f1 = l2;
              float f2 = k1 + 0.5F;
              entityliving.func_70012_b(f, f1, f2, par6Random.nextFloat() * 360.0F, 0.0F);
              world.func_72838_d((Entity)entityliving);
              entitylivingdata = entityliving.func_110161_a(entitylivingdata);
              flag = true;
            }

            j1 += par6Random.nextInt(5) - par6Random.nextInt(5);
            k1 += par6Random.nextInt(5) - par6Random.nextInt(5);
            for (; j1 < par2 || j1 >= par2 + par4 || k1 < par3 || k1 >= par3 + par4;
              k1 = i2 + par6Random.nextInt(5) - par6Random.nextInt(5))
            {
              j1 = l1 + par6Random.nextInt(5) - par6Random.nextInt(5);
            }
          }
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\SpawnerAnimalsTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */