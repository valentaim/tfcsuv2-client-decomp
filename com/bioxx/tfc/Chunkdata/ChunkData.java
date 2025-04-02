package com.bioxx.tfc.Chunkdata;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;


public class ChunkData
{
  public int chunkX;
  public int chunkZ;
  public long lastVisited;
  public long previousVisit;
  public int spawnProtection;
  public int protectionBuffer = (TFCOptions.protectionBuffer >= 0) ? (TFCOptions.protectionBuffer * -1) : -24;

  public int[] heightmap;

  public DataLayer[] rainfallMap;
  public int sluicedAmount;
  public float fishPop = -1.0F;

  public static final float FISH_POP_MAX = 60.0F;

  public int lastSpringGen;

  public int cropInfestation;
  public boolean isUnloaded;
  private final Chunk chunk;

  public ChunkData(Chunk chunk) {
    this.chunk = chunk;

    this.heightmap = new int[256];
    this.rainfallMap = new DataLayer[256];
  }


  public ChunkData(Chunk chunk, NBTTagCompound tag) {
    this.chunk = chunk;

    this.chunkX = tag.func_74762_e("chunkX");
    this.chunkZ = tag.func_74762_e("chunkZ");
    this.lastVisited = tag.func_74763_f("lastVisited");
    this.spawnProtection = tag.func_74762_e("spawnProtection");

    updateSpawnProtection();

    this.heightmap = tag.func_74759_k("heightmap");
    if (this.heightmap.length == 0)
      this.heightmap = new int[256];
    this.sluicedAmount = tag.func_74762_e("sluicedAmount");

    this.lastSpringGen = tag.func_74762_e("lastSpringGen");
    this.cropInfestation = tag.func_74762_e("cropInfestation");

    this.fishPop = Math.min(tag.func_74760_g("fishPopulation"), 60.0F);
  }


  public NBTTagCompound getTag() {
    NBTTagCompound tag = new NBTTagCompound();

    tag.func_74768_a("chunkX", this.chunkX);
    tag.func_74768_a("chunkZ", this.chunkZ);

    updateSpawnProtection();

    tag.func_74768_a("spawnProtection", this.spawnProtection);
    tag.func_74772_a("lastVisited", this.lastVisited);
    tag.func_74783_a("heightmap", this.heightmap);
    tag.func_74768_a("lastSpringGen", this.lastSpringGen);
    tag.func_74768_a("sluicedAmount", this.sluicedAmount);
    tag.func_74768_a("cropInfestation", this.cropInfestation);
    tag.func_74776_a("fishPopulation", Math.max(this.fishPop, -1.0F));
    return tag;
  }


  public ChunkData createNew(World world, int x, int z) {
    this.chunkX = x;
    this.chunkZ = z;
    this.lastVisited = 0L;
    this.spawnProtection = this.protectionBuffer;
    this.lastSpringGen = TFC_Time.getYear();
    return this;
  }


  public int getSpawnProtectionWithUpdate() {
    updateSpawnProtection();

    if (this.spawnProtection > 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths) {
      this.spawnProtection = 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths;
    }
    return this.spawnProtection;
  }


  public void setSpawnProtectionWithUpdate(int amount) {
    updateSpawnProtection();

    this.spawnProtection += amount;

    if (this.spawnProtection > 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths) {
      this.spawnProtection = 24 * TFC_Time.daysInMonth * TFCOptions.maxProtectionMonths;
    }
  }

  private void updateSpawnProtection() {
    long now = TFC_Time.getTotalTicks();

    if (this.lastVisited < now) {

      long visit = (now - this.lastVisited) / 1000L;
      this.spawnProtection = (int)(this.spawnProtection - visit);
      this.lastVisited += visit * 1000L;

      if (this.spawnProtection < this.protectionBuffer) {
        this.spawnProtection = this.protectionBuffer;
      }
      this.chunk.func_76630_e();
    }
  }


  public void infest() {
    Math.min(this.cropInfestation++, 10);
  }


  public void uninfest() {
    Math.max(this.cropInfestation--, 0);
  }







  public float getRainfall(int x, int z) {
    if (this.rainfallMap[x * z] != null) {
      return (this.rainfallMap[x * z]).floatdata1;
    }
    return 0.0F;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Chunkdata\ChunkData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */