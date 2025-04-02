package com.bioxx.tfc.api.Util;

import java.util.Random;




















public class SimplexNoise
{
  public SimplexNoise_Octave[] octaves;
  public double[] frequencys;
  public double[] amplitudes;
  public int largestFeature;
  public double persistence;
  public long seed;

  public SimplexNoise(int largestFeature, double persistence, long seed) {
    this.largestFeature = largestFeature;
    this.persistence = persistence;
    this.seed = seed;


    int numberOfOctaves = (int)Math.ceil(Math.log10(largestFeature) / Math.log10(2.0D));

    this.octaves = new SimplexNoise_Octave[numberOfOctaves];
    this.frequencys = new double[numberOfOctaves];
    this.amplitudes = new double[numberOfOctaves];

    Random rnd = new Random(seed);

    for (int i = 0; i < numberOfOctaves; i++) {
      this.octaves[i] = new SimplexNoise_Octave(rnd.nextInt());

      this.frequencys[i] = Math.pow(2.0D, i);
      this.amplitudes[i] = Math.pow(persistence, (this.octaves.length - i));
    }
  }





  public double getNoise(int x, int y) {
    double result = 0.0D;

    for (int i = 0; i < this.octaves.length; i++)
    {


      result += this.octaves[i].noise(x / this.frequencys[i], y / this.frequencys[i]) * this.amplitudes[i];
    }


    return result;
  }



  public double getNoise(int x, int y, int z) {
    double result = 0.0D;

    for (int i = 0; i < this.octaves.length; i++) {
      double frequency = Math.pow(2.0D, i);
      double amplitude = Math.pow(this.persistence, (this.octaves.length - i));

      result += this.octaves[i].noise(x / frequency, y / frequency, z / frequency) * amplitude;
    }


    return result;
  }



  public double[] getNoiseArray(int xSize, int zSize) {
    double[] outNoise = new double[xSize * zSize];
    for (int x = 0; x < 16; x++) {

      for (int z = 0; z < 16; z++)
      {
        outNoise[x + z * 16] = getNoise(x, z);
      }
    }
    return outNoise;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Util\SimplexNoise.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */