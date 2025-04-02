package com.bioxx.tfc.api;

import com.bioxx.tfc.Core.FluidBaseTFC;
import net.minecraft.init.Blocks;
import net.minecraftforge.fluids.Fluid;


public class TFCFluids
{
  public static final Fluid SALTWATER = (Fluid)(new FluidBaseTFC("saltwater")).setBaseColor(3493173);
  public static final Fluid FRESHWATER = (Fluid)(new FluidBaseTFC("freshwater")).setBaseColor(3493173);
  public static final Fluid HOTWATER = (new FluidBaseTFC("hotwater")).setBaseColor(2052249).setTemperature(350);
  public static final Fluid LAVA = (new FluidBaseTFC("lavatfc")).setLuminosity(15).setDensity(3000).setViscosity(6000).setTemperature(1300).setUnlocalizedName(Blocks.field_150353_l.func_149739_a());
  public static final Fluid RUM = (Fluid)(new FluidBaseTFC("rum")).setBaseColor(7209251);
  public static final Fluid BEER = (Fluid)(new FluidBaseTFC("beer")).setBaseColor(12820023);
  public static final Fluid RYEWHISKEY = (Fluid)(new FluidBaseTFC("ryewhiskey")).setBaseColor(13073745);
  public static final Fluid WHISKEY = (Fluid)(new FluidBaseTFC("whiskey")).setBaseColor(5781273);
  public static final Fluid CORNWHISKEY = (Fluid)(new FluidBaseTFC("cornwhiskey")).setBaseColor(14272439);
  public static final Fluid SAKE = (Fluid)(new FluidBaseTFC("sake")).setBaseColor(12048828);
  public static final Fluid VODKA = (Fluid)(new FluidBaseTFC("vodka")).setBaseColor(14474460);
  public static final Fluid CIDER = (Fluid)(new FluidBaseTFC("cider")).setBaseColor(11578930);
  public static final Fluid TANNIN = (Fluid)(new FluidBaseTFC("tannin")).setBaseColor(6510926);
  public static final Fluid VINEGAR = (Fluid)(new FluidBaseTFC("vinegar")).setBaseColor(13091498);
  public static final Fluid BRINE = (Fluid)(new FluidBaseTFC("brine")).setBaseColor(14472137);
  public static final Fluid LIMEWATER = (Fluid)(new FluidBaseTFC("limewater")).setBaseColor(11842740);
  public static final Fluid MILK = (Fluid)(new FluidBaseTFC("milk")).setBaseColor(16777215);
  public static final Fluid MILKCURDLED = (Fluid)(new FluidBaseTFC("milkcurdled")).setBaseColor(16776168);
  public static final Fluid MILKVINEGAR = (Fluid)(new FluidBaseTFC("milkvinegar")).setBaseColor(16776168);
  public static final Fluid OLIVEOIL = (Fluid)(new FluidBaseTFC("oliveoil")).setBaseColor(6976823);

  public static final Fluid HONEY = (Fluid)(new FluidBaseTFC("honey")).setBaseColor(14470244);
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\TFCFluids.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */