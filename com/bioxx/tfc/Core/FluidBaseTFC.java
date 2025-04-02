package com.bioxx.tfc.Core;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class FluidBaseTFC extends Fluid {
  private int color;

  public FluidBaseTFC(String fluidName) {
    super(fluidName);


    this.color = 16777215;
  }

  public FluidBaseTFC setBaseColor(int c) {
    this.color = c;
    return this;
  }



  public int getColor(FluidStack fs) {
    return this.color;
  }



  public int getColor() {
    return this.color;
  }



  public IIcon getStillIcon() {
    if (this.stillIcon == null)
      return TFCBlocks.hotWater.func_149691_a(0, 0);
    return this.stillIcon;
  }



  public IIcon getFlowingIcon() {
    if (this.flowingIcon == null)
      return TFCBlocks.hotWater.func_149691_a(2, 0);
    return this.flowingIcon;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Core\FluidBaseTFC.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */