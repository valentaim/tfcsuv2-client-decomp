package com.bioxx.tfc.api.TileEntities;

import com.bioxx.tfc.TileEntities.NetworkTileEntity;
import com.bioxx.tfc.api.HeatIndex;
import com.bioxx.tfc.api.HeatRegistry;
import com.bioxx.tfc.api.TFC_ItemHeat;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class TEFireEntity
  extends NetworkTileEntity
{
  public int airFromBellows;
  public float fireTemp;
  public int maxFireTempScale = 2000;


  public int fuelTimeLeft;

  public int fuelBurnTemp;

  public int fuelTasteProfile;

  public static final int AIRTOADD = 200;


  public void careForInventorySlot(ItemStack is) {
    if (is != null) {

      HeatRegistry manager = HeatRegistry.getInstance();
      HeatIndex index = manager.findMatchingIndex(is);

      if (index != null && index.hasOutput()) {

        float temp = TFC_ItemHeat.getTemp(is);
        if (this.fireTemp > temp) {

          temp += TFC_ItemHeat.getTempIncrease(is);
        } else {

          temp -= TFC_ItemHeat.getTempDecrease(is);
        }  TFC_ItemHeat.setTemp(is, temp);
      }
    }
  }


  public void receiveAirFromBellows() {
    if (this.airFromBellows < 600)
      this.airFromBellows += 200;
    if (this.airFromBellows > 600) {
      this.airFromBellows = 600;
    }
  }

  public void keepTempToRange() {
    if (this.fireTemp > getMaxTemp()) {
      this.fireTemp = getMaxTemp();
    } else if (this.fireTemp < 0.0F) {
      this.fireTemp = 0.0F;
    }
  }

  public int getMaxTemp() {
    return this.fuelBurnTemp + this.airFromBellows;
  }


  public int getTemperatureScaled(int s) {
    return (int)(this.fireTemp * s / this.maxFireTempScale);
  }


  protected float handleTemp() {
    if (this.fuelTimeLeft > 0) {

      this.fuelTimeLeft--;
      if (this.airFromBellows > 0) {
        this.fuelTimeLeft--;
      }
    } else if (this.fuelTimeLeft < 0) {
      this.fuelTimeLeft = 0;
    }
    if (this.fuelTimeLeft > 0) {
      return (this.fuelBurnTemp + this.airFromBellows);
    }
    return 0.0F;
  }


  public void handleAirReduction() {
    if (this.airFromBellows > 0) {
      this.airFromBellows--;
    }
  }

  public void handleTempFlux(float desiredTemp) {
    if (this.fireTemp < desiredTemp) {

      if (this.airFromBellows == 0) {
        this.fireTemp++;
      } else {
        this.fireTemp += 2.0F;
      }
    } else if (this.fireTemp > desiredTemp) {

      if (desiredTemp == 0.0F)
      {
        if (this.airFromBellows == 0) {
          this.fireTemp--;
        } else {
          this.fireTemp = (float)(this.fireTemp - 0.5D);
        }  }
    }
    keepTempToRange();
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74776_a("temperature", this.fireTemp);
    nbt.func_74768_a("fuelTime", this.fuelTimeLeft);
    nbt.func_74768_a("fuelTemp", this.fuelBurnTemp);
    nbt.func_74768_a("bellowsAir", this.airFromBellows);
    nbt.func_74768_a("fuelTasteProfile", this.fuelTasteProfile);
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.fireTemp = nbt.func_74760_g("temperature");
    this.fuelTimeLeft = nbt.func_74762_e("fuelTime");
    this.fuelBurnTemp = nbt.func_74762_e("fuelTemp");
    this.airFromBellows = nbt.func_74762_e("airBellows");
    this.fuelTasteProfile = nbt.func_74762_e("fuelTasteProfile");
  }

  public void handleInitPacket(NBTTagCompound nbt) {}

  public void handleDataPacket(NBTTagCompound nbt) {}

  public void createDataNBT(NBTTagCompound nbt) {}

  public void createInitNBT(NBTTagCompound nbt) {}
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\TileEntities\TEFireEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */