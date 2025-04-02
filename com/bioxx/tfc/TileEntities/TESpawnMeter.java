package com.bioxx.tfc.TileEntities;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import net.minecraft.nbt.NBTTagCompound;





public class TESpawnMeter
  extends NetworkTileEntity
{
  private long timer = TFC_Time.getTotalTicks();

  private int protection;


  public void func_145845_h() {
    if (!this.field_145850_b.field_72995_K)
    {
      if (this.timer < TFC_Time.getTotalTicks()) {

        this.timer += 1000L;
        if (TFC_Core.getCDM(this.field_145850_b) != null) {

          ChunkData cd = TFC_Core.getCDM(this.field_145850_b).getData(this.field_145851_c >> 4, this.field_145849_e >> 4);
          if (cd != null) {

            this.protection = cd.spawnProtection;
            int meta = 0;
            if (this.protection > 0)
            {

              meta = (this.protection > 384) ? 8 : (this.protection / 48);
            }

            if (meta != this.field_145850_b.func_72805_g(this.field_145851_c, this.field_145848_d, this.field_145849_e))
            {
              this.field_145850_b.func_72921_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, meta, 3);
            }
          }
        }
      }
    }
  }



  public void func_145839_a(NBTTagCompound nbt) {
    super.func_145839_a(nbt);
    this.protection = nbt.func_74762_e("protectionHours");
  }



  public void func_145841_b(NBTTagCompound nbt) {
    super.func_145841_b(nbt);
    nbt.func_74768_a("protectionHours", this.protection);
  }



  public void handleInitPacket(NBTTagCompound nbt) {
    this.protection = nbt.func_74762_e("protectionHours");
  }



  public void handleDataPacket(NBTTagCompound nbt) {
    this.protection = nbt.func_74762_e("protectionHours");
  }



  public void createDataNBT(NBTTagCompound nbt) {
    nbt.func_74768_a("protectionHours", this.protection);
  }



  public void createInitNBT(NBTTagCompound nbt) {
    nbt.func_74768_a("protectionHours", this.protection);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\TileEntities\TESpawnMeter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */