package com.bioxx.tfc.Chunkdata;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;





public class ChunkDataManager
{
  private LongHashMap chunkmap = new LongHashMap();


  
  public ChunkDataManager(World world) {}


  
  public void removeData(int x, int z) {
    this.chunkmap.func_76159_d(ChunkCoordIntPair.func_77272_a(x, z));
  }

  
  public void addData(long key, ChunkData cd) {
    this.chunkmap.func_76163_a(key, cd);
  }

  
  public void addData(Chunk chunk, ChunkData cd) {
    this.chunkmap.func_76163_a(ChunkCoordIntPair.func_77272_a(chunk.field_76635_g, chunk.field_76647_h), cd);
  }

  
  public void addData(int x, int z, ChunkData cd) {
    this.chunkmap.func_76163_a(ChunkCoordIntPair.func_77272_a(x, z), cd);
  }

  
  public ChunkData getData(int x, int z) {
    return (ChunkData)this.chunkmap.func_76164_a(ChunkCoordIntPair.func_77272_a(x, z));
  }

  
  public ChunkData getData(long key) {
    return (ChunkData)this.chunkmap.func_76164_a(key);
  }

  
  public boolean hasData(long key) {
    return this.chunkmap.func_76161_b(key);
  }

  
  public boolean addProtection(int x, int z, int amount) {
    ChunkData d = getData(x, z);
    if (d != null) {
      
      if (d.spawnProtection < 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths)
        d.setSpawnProtectionWithUpdate(amount); 
      return true;
    } 
    return false;
  }

  
  public void setFishPop(int x, int z, float fishPop) {
    ChunkData d = getData(x, z);
    if (d != null && fishPop >= 0.0F)
    {
      d.fishPop = fishPop;
    }
  }

  
  public int getFishPop(int x, int z) {
    ChunkData d = getData(x, z);
    if (d != null)
    {
      return (int)d.fishPop;
    }
    return -1;
  }

  
  public boolean catchFish(int x, int z) {
    ChunkData d = getData(x, z);
    if (d != null)
    {
      if (d.fishPop > 0.0F) {
        d.fishPop--;
        return true;
      } 
    }
    return false;
  }

  
  public boolean setLastVisted(int x, int z) {
    ChunkData d = getData(x, z);
    if (d != null) {
      
      d.lastVisited = TFC_Time.getTotalTicks();
      return true;
    } 
    return false;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Chunkdata\ChunkDataManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */