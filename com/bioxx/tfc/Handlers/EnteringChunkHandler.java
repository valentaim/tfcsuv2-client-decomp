package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.EntityEvent;



public class EnteringChunkHandler
{
  @SubscribeEvent
  public void onEnterChunk(EntityEvent.EnteringChunk event) {
    if (event.entity instanceof EntityPlayer) {

      EntityPlayer player = (EntityPlayer)event.entity;
      if (!player.field_70170_p.field_72995_K) {

        NBTTagCompound nbt = player.getEntityData();
        long spawnProtectionTimer = nbt.func_74764_b("spawnProtectionTimer") ? nbt.func_74763_f("spawnProtectionTimer") : (TFC_Time.getTotalTicks() + 1000L);


        if (TFC_Core.getCDM(event.entity.field_70170_p) != null && (event.newChunkX != event.oldChunkX || event.newChunkZ != event.oldChunkZ)) {

          TFC_Core.getCDM(event.entity.field_70170_p).setLastVisted(event.oldChunkX, event.oldChunkZ);

          spawnProtectionTimer = TFC_Time.getTotalTicks() + 1000L;
          writeProtectionToNBT(nbt, spawnProtectionTimer);
        }
      }
    }
  }


  public void writeProtectionToNBT(NBTTagCompound nbt, long spawnProtectionTimer) {
    nbt.func_74772_a("spawnProtectionTimer", spawnProtectionTimer);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EnteringChunkHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */