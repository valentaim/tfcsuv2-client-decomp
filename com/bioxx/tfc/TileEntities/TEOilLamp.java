package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCFluids;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.FluidStack;











public class TEOilLamp
  extends TELightEmitter
{
  private FluidStack fuel;

  public FluidStack getFuel() {
    if (this.fuel == null)
      return null;
    FluidStack f = this.fuel.copy();
    f.amount /= TFCOptions.oilLampFuelMult;
    return f;
  }






  public void updateLampFuel(Boolean burn) {
    if ((int)TFC_Time.getTotalHours() - TFCOptions.oilLampFuelMult >= this.hourPlaced) {

      int diff = burn.booleanValue() ? ((int)TFC_Time.getTotalHours() - this.hourPlaced) : 0;
      this.hourPlaced = (int)TFC_Time.getTotalHours();

      if (this.fuel != null && getFuel().getFluid() != TFCFluids.LAVA && getFuelAmount() > 0) {

        this.fuel.amount -= diff;
        if (this.fuel.amount <= 0) {
          this.fuel = null;
        }
      }
    }
  }

  public void setFuelFromStack(FluidStack fs) {
    this.fuel = fs;
    this.fuel.amount *= TFCOptions.oilLampFuelMult;
  }


  public boolean isFuelValid() {
    int meta = this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
    if (getFuel() != null && getFuel().getFluid() == TFCFluids.OLIVEOIL)
    {
      return true;
    }
    if ((meta & 0x7) == 5 && getFuel() != null && getFuel().getFluid() == TFCFluids.LAVA)
    {
      return true;
    }
    return false;
  }



  public boolean canUpdate() {
    return false;
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    if (nbt.func_74764_b("Fuel")) {
      this.fuel = FluidStack.loadFluidStackFromNBT(nbt.func_74775_l("Fuel"));
    }
  }


  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    if (this.fuel != null) {
      nbt.func_74782_a("Fuel", (NBTBase)this.fuel.writeToNBT(new NBTTagCompound()));
    }
  }

  public int getFuelAmount() {
    if (this.fuel == null)
      return 0;
    return this.fuel.amount;
  }


  public int getFuelTimeLeft() {
    int f = getFuelAmount();
    float perc = f / 250.0F;
    return (int)((TFC_Time.daysInYear * 24) * perc);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEOilLamp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */