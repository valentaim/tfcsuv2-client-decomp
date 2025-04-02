package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCOptions;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;



public class ServerTickHandler
{
  private long wSeed = Long.MIN_VALUE;
  public int ticks;

  @SubscribeEvent
  public void onServerWorldTick(TickEvent.WorldTickEvent event) {
    World world = event.world;
    if (event.phase == TickEvent.Phase.START) {

      if (world.field_73011_w.field_76574_g == 0 && world.func_72912_H().func_76063_b() != this.wSeed) {

        TFC_Core.setupWorld(world);
        this.wSeed = world.func_72912_H().func_76063_b();
      }
      TFC_Time.updateTime(world);




      if (MinecraftServer.func_71276_C().func_71233_x() == 0 && TFCOptions.simSpeedNoPlayers > 0) {

        this.ticks++;
        long t = world.func_72912_H().func_82573_f();
        long w = world.func_72912_H().func_76073_f();
        if (this.ticks < TFCOptions.simSpeedNoPlayers) {

          world.func_72912_H().func_82572_b(t - 1L);
          world.func_72912_H().func_76068_b(w - 1L);
        }
        else {

          this.ticks = 0;
        }
      }
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\ServerTickHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */