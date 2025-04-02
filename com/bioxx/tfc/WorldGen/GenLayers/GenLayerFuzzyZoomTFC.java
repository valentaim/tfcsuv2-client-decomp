package com.bioxx.tfc.WorldGen.GenLayers;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerFuzzyZoomTFC
  extends GenLayerTFC
{
  public GenLayerFuzzyZoomTFC(long par1, GenLayer par3GenLayer) {
    super(par1);
    this.field_75909_a = (GenLayerTFC)par3GenLayer;
  }






  
  public int[] func_75904_a(int par1, int par2, int par3, int par4) {
    int var5 = par1 >> 1;
    int var6 = par2 >> 1;
    int var7 = (par3 >> 1) + 3;
    int var8 = (par4 >> 1) + 3;
    int[] var9 = this.field_75909_a.func_75904_a(var5, var6, var7, var8);
    int[] var10 = new int[var7 * 2 * var8 * 2];
    int var11 = var7 << 1;

    
    for (int var12 = 0; var12 < var8 - 1; var12++) {
      
      int i = var12 << 1;
      int var14 = i * var11;
      int var15 = var9[0 + (var12 + 0) * var7];
      int var16 = var9[0 + (var12 + 1) * var7];
      
      for (int var17 = 0; var17 < var7 - 1; var17++) {
        
        func_75903_a((var17 + var5 << 1), (var12 + var6 << 1));
        int var18 = var9[var17 + 1 + (var12 + 0) * var7];
        int var19 = var9[var17 + 1 + (var12 + 1) * var7];
        var10[var14] = var15;
        var10[var14++ + var11] = choose(var15, var16);
        var10[var14] = choose(var15, var18);
        var10[var14++ + var11] = choose(var15, var18, var16, var19);
        var15 = var18;
        var16 = var19;
      } 
    } 
    
    int[] var20 = new int[par3 * par4];
    
    for (int var13 = 0; var13 < par4; var13++) {
      System.arraycopy(var10, (var13 + (par2 & 0x1)) * (var7 << 1) + (par1 & 0x1), var20, var13 * par3, par3);
    }
    return var20;
  }




  
  protected int choose(int par1, int par2) {
    return (func_75902_a(2) == 0) ? par1 : par2;
  }




  
  protected int choose(int par1, int par2, int par3, int par4) {
    int var5 = func_75902_a(4);
    return (var5 == 0) ? par1 : ((var5 == 1) ? par2 : ((var5 == 2) ? par3 : par4));
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\WorldGen\GenLayers\GenLayerFuzzyZoomTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */