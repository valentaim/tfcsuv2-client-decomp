package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;









public class TFCProvider
  extends WorldProvider
{
  private int moonPhase;
  private int moonPhaseLastCalculated;

  protected void func_76572_b() {
    TFC_Climate.worldPair.put(this.field_76579_a, new WorldCacheManager(this.field_76579_a));
    TFC_Core.addCDM(this.field_76579_a);

    super.func_76572_b();
  }



  public IChunkProvider func_76555_c() {
    return (IChunkProvider)new TFCChunkProviderGenerate(this.field_76579_a, this.field_76579_a.func_72905_C(), this.field_76579_a.func_72912_H().func_76089_r());
  }



  public boolean func_76566_a(int x, int z) {
    int y = this.field_76579_a.func_72825_h(x, z) - 1;
    if (y < 144 || y > 169) return false;
    Block b = this.field_76579_a.func_147439_a(x, y, z);
    return (TFC_Core.isSand(b) || TFC_Core.isGrass(b));
  }





  @SideOnly(Side.CLIENT)
  public int func_76559_b(long par1) {
    if (TFC_Time.getDayFromTotalHours(TFC_Time.getTotalHours()) != this.moonPhaseLastCalculated) {

      int daysPassed = (int)(par1 / 24000L);
      int dayOfMonth = daysPassed % TFC_Time.daysInMonth;
      float dayToLunarDayMultiplier = 8.0F / TFC_Time.daysInMonth;


      int lunarDay = Math.round(dayOfMonth * dayToLunarDayMultiplier);

      this.moonPhase = lunarDay % 8;

      this.moonPhaseLastCalculated = TFC_Time.getDayFromTotalHours(TFC_Time.getTotalHours());
    }

    return this.moonPhase;
  }



  public float func_76571_f() {
    return 256.0F;
  }








  private boolean isNextToShoreOrIce(int x, int y, int z) {
    if (this.field_76579_a.func_72904_c(x + 1, y, z, x + 1, y, z) && (
      this.field_76579_a.func_147439_a(x + 1, y, z) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x + 1, y, z))))
      return true;
    if (this.field_76579_a.func_72904_c(x - 1, y, z, x - 1, y, z) && (
      this.field_76579_a.func_147439_a(x - 1, y, z) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x - 1, y, z))))
      return true;
    if (this.field_76579_a.func_72904_c(x, y, z + 1, x, y, z + 1) && (
      this.field_76579_a.func_147439_a(x, y, z + 1) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x, y, z + 1))))
      return true;
    if (this.field_76579_a.func_72904_c(x, y, z - 1, x, y, z - 1) && (
      this.field_76579_a.func_147439_a(x, y, z - 1) == TFCBlocks.ice || TFC_Core.isGround(this.field_76579_a.func_147439_a(x, y, z - 1))))
      return true;
    return false;
  }



  public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
    Block id = this.field_76579_a.func_147439_a(x, y, z);
    int meta = this.field_76579_a.func_72805_g(x, y, z);
    float temp = TFC_Climate.getHeightAdjustedTemp(this.field_76579_a, x, y, z);
    BiomeGenBase biome = this.field_76579_a.func_72807_a(x, z);

    if (temp <= 0.0F && biome != TFCBiome.DEEP_OCEAN) {

      if (this.field_76579_a.func_147437_c(x, y + 1, z) && TFC_Core.isWater(id) && this.field_76579_a.field_73012_v.nextInt(4) == 0 && isNextToShoreOrIce(x, y, z))
      {
        Material mat = this.field_76579_a.func_147439_a(x, y, z).func_149688_o();
        boolean salty = TFC_Core.isSaltWaterIncludeIce(id, meta, mat);

        if (temp <= -2.0F) {
          salty = false;
        }
        if ((mat == Material.field_151586_h || mat == Material.field_151588_w) && !salty)
        {
          if (id == TFCBlocks.freshWaterStationary && meta == 0) {

            this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.ice, 1, 2);
          }
          else if (id == TFCBlocks.saltWaterStationary && meta == 0) {

            this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.ice, 0, 2);
          }
        }
        return false;

      }

    }
    else if (id == TFCBlocks.ice) {

      int chance = (int)Math.floor(Math.max(1.0F, 6.0F - temp));
      if (id == TFCBlocks.ice && this.field_76579_a.field_73012_v.nextInt(chance) == 0)
      {
        if (this.field_76579_a.func_147439_a(x, y + 1, z) == Blocks.field_150433_aE) {

          int m = this.field_76579_a.func_72805_g(x, y + 1, z);
          if (m > 0)
          {
            this.field_76579_a.func_72921_c(x, y + 1, z, m - 1, 2);
          }
          else
          {
            this.field_76579_a.func_147468_f(x, y + 1, z);
          }

        } else {

          int flag = 2;




          if ((meta & 0x1) == 0) {

            this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.saltWaterStationary, 0, flag);
          }
          else if ((meta & 0x1) == 1) {

            this.field_76579_a.func_147465_d(x, y, z, TFCBlocks.freshWaterStationary, 0, flag);
          }
        }
      }
    }

    return false;
  }



  public boolean canDoRainSnowIce(Chunk chunk) {
    return true;
  }



  public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
    if (TFC_Climate.getHeightAdjustedTemp(this.field_76579_a, x, y, z) > 0.0F)
      return false;
    Material material = this.field_76579_a.func_147439_a(x, y, z).func_149688_o();
    if (material == Material.field_151597_y) {
      return false;
    }
    return (TFCBlocks.snow.func_149742_c(this.field_76579_a, x, y, z) && material.func_76222_j());
  }










  public String func_80007_l() {
    return "DEFAULT";
  }






  public ChunkCoordinates func_76554_h() {
    return getSpawnPoint();
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\TFCProvider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */