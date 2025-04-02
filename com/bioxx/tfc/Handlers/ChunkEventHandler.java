package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.WorldGen.Generators.WorldGenGrowCrops;
import com.bioxx.tfc.WorldGen.Generators.WorldGenWaterPlants;
import com.bioxx.tfc.WorldGen.WorldCacheManager;
import com.bioxx.tfc.api.Crafting.AnvilManager;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;



public class ChunkEventHandler
{
  @SubscribeEvent
  public void onLoad(ChunkEvent.Load event) {
    if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null && event.getChunk() != null) {

      ChunkData cd = TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
      if (cd == null)
        return;
      BiomeGenBase biome = event.world.func_72807_a((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);
      int month = TFC_Time.getSeasonAdjustedMonth((event.getChunk()).field_76647_h << 4);
      if (TFC_Time.getYear() > cd.lastSpringGen && month > 1 && month < 6) {

        int chunkX = (event.getChunk()).field_76635_g;
        int chunkZ = (event.getChunk()).field_76647_h;
        if (TFC_Core.isWaterBiome(biome)) {

          cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
          cd.fishPop = Math.min(cd.fishPop, 60.0F);
        }
        cd.lastSpringGen = TFC_Time.getYear();

        Random rand = new Random(event.world.func_72905_C() + (((chunkX >> 3) - (chunkZ >> 3)) * (chunkZ >> 3)));


        if (TFC_Core.isWaterBiome(biome)) {
          cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
          cd.fishPop = Math.min(cd.fishPop, 60.0F);
          if (rand.nextInt(50) == 0) {
            int waterPlantsPerChunk = 10;

            for (int var2 = 0; var2 < waterPlantsPerChunk; var2++) {
              int xCoord = (chunkX << 4) + rand.nextInt(16) + 8;
              int zCoord = (chunkZ << 4) + rand.nextInt(16) + 8;
              int yCoord = event.world.func_72874_g(xCoord, zCoord) - 1;
              if (TFC_Climate.getBioTemperatureHeight(event.world, xCoord, yCoord, zCoord) >= 7.0F) {
                (new WorldGenWaterPlants(TFCBlocks.waterPlant)).func_76484_a(event.world, rand, xCoord, yCoord, zCoord);
              }
            }
          }
        }

        int cropid = rand.nextInt(CropManager.getInstance().getTotalCrops());
        CropIndex crop = CropManager.getInstance().getCropFromId(cropid);
        if (event.world.field_73012_v.nextInt(25) == 0 && crop != null)
        {
          int num = 1 + event.world.field_73012_v.nextInt(5);
          WorldGenGrowCrops cropGen = new WorldGenGrowCrops(cropid);
          int x = (chunkX << 4) + event.world.field_73012_v.nextInt(16) + 8;
          int z = (chunkZ << 4) + event.world.field_73012_v.nextInt(16) + 8;
          cropGen.generate(event.world, event.world.field_73012_v, x, z, num);
        }

      } else if (TFC_Time.getYear() > cd.lastSpringGen && month >= 6) {


        if (TFC_Core.isWaterBiome(biome)) {

          cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
          cd.fishPop = Math.min(cd.fishPop, 60.0F);
        }
        cd.lastSpringGen = TFC_Time.getYear();
      }
      else if (TFC_Time.getYear() > cd.lastSpringGen + 1) {

        if (TFC_Core.isWaterBiome(biome)) {

          cd.fishPop = (float)(cd.fishPop * Math.pow(1.2D, (cd.lastSpringGen - TFC_Time.getYear())));
          cd.fishPop = Math.min(cd.fishPop, 60.0F);
        }
        cd.lastSpringGen = TFC_Time.getYear();
      }

    } else if (TFC_Core.getCDM(event.world) != null && TFC_Climate.getCacheManager(event.world) != null) {

      Chunk chunk = event.getChunk();
      ChunkData data = (new ChunkData(chunk)).createNew(event.world, chunk.field_76635_g, chunk.field_76647_h);
      data.rainfallMap = TFC_Climate.getCacheManager(event.world).loadRainfallLayerGeneratorData(data.rainfallMap, (event.getChunk()).field_76635_g * 16, (event.getChunk()).field_76647_h * 16, 16, 16);
      TFC_Core.getCDM(event.world).addData(chunk, data);
    }
  }


  @SubscribeEvent
  public void onUnload(ChunkEvent.Unload event) {
    if (TFC_Core.getCDM(event.world) != null && TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h) != null) {
      (TFC_Core.getCDM(event.world).getData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h)).isUnloaded = true;
    }

    if (event.world.field_72995_K) TFC_Core.getCDM(event.world).removeData((event.getChunk()).field_76635_g, (event.getChunk()).field_76647_h);

  }

  @SubscribeEvent
  public void onUnloadWorld(WorldEvent.Unload event) {
    TFC_Climate.removeCacheManager(event.world);
    TFC_Core.removeCDM(event.world);
    if (event.world.field_73011_w.field_76574_g == 0) {
      AnvilManager.getInstance().clearRecipes();
    }
  }

  @SubscribeEvent
  public void onLoadWorld(WorldEvent.Load event) {
    if (event.world.field_73011_w.field_76574_g == 0 && event.world.func_82737_E() < 100L)
      createSpawn(event.world);
    if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0 && AnvilManager.getInstance().getRecipeList().size() == 0)
    {
      TFC_Core.setupWorld(event.world);
    }
    TFC_Climate.worldPair.put(event.world, new WorldCacheManager(event.world));
    TFC_Core.addCDM(event.world);
  }


  @SubscribeEvent
  public void onDataLoad(ChunkDataEvent.Load event) {
    if (!event.world.field_72995_K) {

      NBTTagCompound eventTag = event.getData();

      Chunk chunk = event.getChunk();
      if (eventTag.func_74764_b("ChunkData")) {

        NBTTagCompound spawnProtectionTag = eventTag.func_74775_l("ChunkData");
        ChunkData data = new ChunkData(chunk, spawnProtectionTag);
        if (TFC_Core.getCDM(event.world) != null) {
          TFC_Core.getCDM(event.world).addData(chunk, data);

        }
      }
      else {

        NBTTagCompound levelTag = eventTag.func_74775_l("Level");
        ChunkData data = (new ChunkData(chunk)).createNew(event.world, levelTag.func_74762_e("xPos"), levelTag.func_74762_e("zPos"));
        if (TFC_Core.getCDM(event.world) != null) {
          TFC_Core.getCDM(event.world).addData(chunk, data);
        }
      }
    }
  }

  @SubscribeEvent
  public void onDataSave(ChunkDataEvent.Save event) {
    if (!event.world.field_72995_K && TFC_Core.getCDM(event.world) != null) {

      NBTTagCompound levelTag = event.getData().func_74775_l("Level");
      int x = levelTag.func_74762_e("xPos");
      int z = levelTag.func_74762_e("zPos");

      ChunkData data = TFC_Core.getCDM(event.world).getData(x, z);

      if (data != null) {

        NBTTagCompound spawnProtectionTag = data.getTag();


        event.getData().func_74782_a("ChunkData", (NBTBase)spawnProtectionTag);
        if (data.isUnloaded) TFC_Core.getCDM(event.world).removeData(x, z);

      }
    }
  }


  private ChunkCoordinates createSpawn(World world) {
    List biomeList = world.func_72959_q().func_76932_a();
    long seed = world.func_72912_H().func_76063_b();
    Random rand = new Random(seed);

    ChunkPosition chunkCoord = null;
    int xOffset = 0;
    int xCoord = 0;

    int zCoord = 10000;
    int startingZ = 5000 + rand.nextInt(10000);

    while (chunkCoord == null) {

      chunkCoord = world.func_72959_q().func_150795_a(xOffset, -startingZ, 64, biomeList, rand);
      if (chunkCoord != null) {

        xCoord = chunkCoord.field_151329_a;
        zCoord = chunkCoord.field_151328_c;

        continue;
      }
      xOffset += 64;
    }



    int var9 = 0;
    while (!world.field_73011_w.func_76566_a(xCoord, zCoord)) {

      xCoord += rand.nextInt(16) - rand.nextInt(16);
      zCoord += rand.nextInt(16) - rand.nextInt(16);
      var9++;
      if (var9 == 1000) {
        break;
      }
    }
    WorldInfo info = world.func_72912_H();
    info.func_76081_a(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
    if (!info.func_76066_a().func_74764_b("superseed"))
      info.func_76066_a().func_74772_a("superseed", System.currentTimeMillis());
    return new ChunkCoordinates(xCoord, world.func_72825_h(xCoord, zCoord), zCoord);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\ChunkEventHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */