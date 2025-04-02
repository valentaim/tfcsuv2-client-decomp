package com.bioxx.tfc.WorldGen;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomCactus;
import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomPumpkin;
import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomReed;
import com.bioxx.tfc.WorldGen.Generators.WorldGenCustomSand;
import com.bioxx.tfc.WorldGen.Generators.WorldGenGrowCrops;
import com.bioxx.tfc.WorldGen.Generators.WorldGenWaterPlants;
import com.bioxx.tfc.api.TFCBlocks;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
























public class BiomeDecoratorTFC
  extends BiomeDecorator
{
  public int field_76803_B;
  public TFCBiome biome;
  public int field_76798_D;
  public int field_76832_z;
  public int field_76804_C;
  public int field_76806_I;
  public int field_76800_F;
  public int waterPlantsPerChunk;
  public int field_76799_E;
  public int lilyPadPerChunk;

  public BiomeDecoratorTFC(TFCBiome par1) {
    this.field_76803_B = 1;
    this.field_76798_D = 0;
    this.field_76832_z = 30;
    this.field_76800_F = 2;
    this.waterPlantsPerChunk = 30;
    this.field_76825_v = (WorldGenerator)new WorldGenCustomReed();
    this.field_76810_g = (WorldGenerator)new WorldGenCustomSand(7, (Block)Blocks.field_150354_m);
    this.biome = par1;
  }






  protected void func_150513_a(BiomeGenBase bgb) {
    func_76797_b();





    Random rand = new Random(this.field_76815_a.func_72905_C() + (((this.field_76814_c >> 7) - (this.field_76811_d >> 7)) * (this.field_76811_d >> 7)));
    int cropid = rand.nextInt(CropManager.getInstance().getTotalCrops());
    CropIndex crop = CropManager.getInstance().getCropFromId(cropid);
    WorldGenGrowCrops cropGen = new WorldGenGrowCrops(cropid);

    if (this.field_76813_b.nextInt(20) == 0 && crop != null) {

      int num = 2 + this.field_76813_b.nextInt(8);
      int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
      int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
      int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord) + 1;
      for (int count = 0; count < num; count++)
      {
        cropGen.generate(this.field_76815_a, this.field_76813_b, xCoord, zCoord, 1); }
    }
    int var2;
    for (var2 = 0; var2 < this.lilyPadPerChunk; var2++) {

      int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
      int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
      int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);

      generateLilyPads(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
    }

    for (var2 = 0; var2 < 10; var2++) {

      if (this.field_76813_b.nextInt(100) < 10) {

        int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
        int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
        int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
        if (TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, yCoord, zCoord) >= 25.0F) {
          this.field_76825_v.func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
        }
      }
    }
    if (this.field_76813_b.nextInt(300) == 0) {

      int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
      int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
      int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
      (new WorldGenCustomPumpkin()).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
    }

    for (var2 = 0; var2 < this.field_76800_F; var2++) {

      int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
      int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
      int yCoord = this.field_76815_a.func_72976_f(xCoord, zCoord);
      float temperature = TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, this.field_76815_a.func_72976_f(xCoord, zCoord), zCoord);
      float rainfall = TFC_Climate.getRainfall(this.field_76815_a, xCoord, yCoord, zCoord);
      if (temperature > 20.0F && rainfall < 125.0F) {
        (new WorldGenCustomCactus()).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
      }
    }
    for (var2 = 0; var2 < this.waterPlantsPerChunk; var2++) {

      int xCoord = this.field_76814_c + this.field_76813_b.nextInt(16) + 8;
      int zCoord = this.field_76811_d + this.field_76813_b.nextInt(16) + 8;
      int yCoord = this.field_76815_a.func_72874_g(xCoord, zCoord) - 1;
      if (TFC_Climate.getBioTemperatureHeight(this.field_76815_a, xCoord, yCoord, zCoord) >= 7.0F) {
        (new WorldGenWaterPlants(TFCBlocks.waterPlant)).func_76484_a(this.field_76815_a, this.field_76813_b, xCoord, yCoord, zCoord);
      }
    }
  }

  public boolean generateLilyPads(World world, Random random, int x, int y, int z) {
    for (int l = 0; l < 10; l++) {

      int i1 = x + random.nextInt(8) - random.nextInt(8);
      int j1 = y + random.nextInt(4) - random.nextInt(4);
      int k1 = z + random.nextInt(8) - random.nextInt(8);

      if (world.func_147437_c(i1, j1, k1) && TFCBlocks.lilyPad.func_149742_c(world, i1, j1, k1) &&
        TFC_Core.isFreshWater(world.func_147439_a(i1, j1 - 1, k1)) && !TFC_Core.isFreshWater(world.func_147439_a(i1, j1 - 2, k1)))
      {
        world.func_147465_d(i1, j1, k1, TFCBlocks.lilyPad, 0, 2);
      }
    }

    return true;
  }







  public void func_150512_a(World par1World, Random par2Random, BiomeGenBase bgb, int par3, int par4) {
    if (this.field_76815_a == null) {

      this.field_76815_a = par1World;
      this.field_76813_b = par2Random;
      this.field_76814_c = par3;
      this.field_76811_d = par4;
      func_150513_a(bgb);
      this.field_76815_a = null;
      this.field_76813_b = null;
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\BiomeDecoratorTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */