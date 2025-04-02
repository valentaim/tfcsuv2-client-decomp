package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.Food.CropIndex;
import com.bioxx.tfc.Food.CropManager;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TEFarmland
  extends NetworkTileEntity
{
  public long nutrientTimer = -1L;
  public int[] nutrients = new int[] {
      getSoilMax(), getSoilMax(), getSoilMax(), 0
    };


  public boolean isInfested;

  public long timeSinceUpdate;


  public TEFarmland() {
    this.shouldSendInitData = true;
  }



  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K) {

      if (this.nutrientTimer <= 0L) {
        this.nutrientTimer = TFC_Time.getTotalHours();
      }
      if (this.nutrientTimer < TFC_Time.getTotalHours()) {

        CropIndex crop = null;
        int soilMax = getSoilMax();
        int restoreAmount = 139;

        if (this.field_145850_b.func_147439_a(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e) == TFCBlocks.crops) {

          crop = CropManager.getInstance().getCropFromId(((TECrop)this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d + 1, this.field_145849_e)).cropId);

          if (crop.cycleType != 0)
          {
            if (this.nutrients[0] < soilMax) {
              this.nutrients[0] = this.nutrients[0] + restoreAmount + crop.nutrientExtraRestore[0];
            }
          }
          if (crop.cycleType != 1)
          {
            if (this.nutrients[1] < soilMax) {
              this.nutrients[1] = this.nutrients[1] + restoreAmount + crop.nutrientExtraRestore[1];
            }
          }
          if (crop.cycleType != 2)
          {
            if (this.nutrients[2] < soilMax) {
              this.nutrients[2] = this.nutrients[2] + restoreAmount + crop.nutrientExtraRestore[2];
            }
          }
        } else {

          if (this.nutrients[0] < soilMax)
            this.nutrients[0] = this.nutrients[0] + restoreAmount;
          if (this.nutrients[1] < soilMax)
            this.nutrients[1] = this.nutrients[1] + restoreAmount;
          if (this.nutrients[2] < soilMax) {
            this.nutrients[2] = this.nutrients[2] + restoreAmount;
          }
        }
        if (this.nutrients[0] > soilMax)
          this.nutrients[0] = soilMax;
        if (this.nutrients[1] > soilMax)
          this.nutrients[1] = soilMax;
        if (this.nutrients[2] > soilMax) {
          this.nutrients[2] = soilMax;
        }
        this.nutrientTimer += 24L;

        if (this.isInfested) {

          float temp = TFC_Climate.getHeightAdjustedTempSpecificDay(this.field_145850_b, TFC_Time.getDayFromTotalHours(this.nutrientTimer), this.field_145851_c, this.field_145848_d, this.field_145849_e);
          if (temp > 10.0F && this.field_145850_b.field_73012_v.nextInt(10) == 0) {

            TileEntity te = this.field_145850_b.func_147438_o(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            if (te instanceof TEFarmland)
            {
              ((TEFarmland)te).infest();
            }
          }
          else if (temp <= 10.0F) {

            if (this.field_145850_b.field_73012_v.nextInt(5) == 0) {
              uninfest();
            }
          }
        }
      }
    }
  }



















  public void infest() {
    this.isInfested = true;
    ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);
    if (cd != null && cd.cropInfestation == 0) {
      cd.infest();
    }
  }

  public void uninfest() {
    this.isInfested = false;
    ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);
    if (cd != null && cd.cropInfestation > 0) {
      cd.uninfest();
    }
  }

  public int getSoilMax() {
    float timeMultiplier = TFC_Time.daysInYear / 360.0F;
    return (int)(25000.0F * timeMultiplier);
  }


  public void drainNutrients(int type, float multiplier) {
    float timeMultiplier = 360.0F / TFC_Time.daysInYear;
    this.nutrients[type] = (int)(this.nutrients[type] - 100.0F * multiplier * timeMultiplier);

    if (this.nutrients[type] < 0) {
      this.nutrients[type] = 0;
    }
  }

  public boolean fertilize(ItemStack is, boolean isOrganic) {
    this.nutrients[3] = getSoilMax();
    is.field_77994_a--;
    return true;
  }






  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.nutrients = nbt.func_74759_k("nutrients");
    this.nutrientTimer = nbt.func_74763_f("nutrientTimer");
    this.isInfested = nbt.func_74767_n("isInfested");
  }






  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74783_a("nutrients", this.nutrients);
    nbt.func_74772_a("nutrientTimer", this.nutrientTimer);
    nbt.func_74757_a("isInfested", this.isInfested);
  }


  public void requestNutrientData() {
    if (TFC_Time.getTotalTicks() > this.timeSinceUpdate + 1000L) {

      this.timeSinceUpdate = TFC_Time.getTotalTicks();
      this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
      broadcastPacketInRange();
    }
  }


  public void handleInitPacket(NBTTagCompound nbt) {
    this.nutrients = nbt.func_74759_k("nutrients");
    this.isInfested = nbt.func_74767_n("isInfested");
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    if (this.field_145850_b.field_72995_K) {
      this.nutrients = nbt.func_74759_k("nutrients");
    } else {
      broadcastPacketInRange();
    }
  }


  public void createDataNBT(NBTTagCompound nbt) {
    if (!this.field_145850_b.field_72995_K)
    {
      nbt.func_74783_a("nutrients", this.nutrients);
    }
  }


  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74783_a("nutrients", this.nutrients);
    nbt.func_74757_a("isInfested", this.isInfested);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TEFarmland.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */