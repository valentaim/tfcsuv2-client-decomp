package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class GSPVisualCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "vgsp";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    if (!TFCOptions.enableDebugMode)
      return;
    MinecraftServer server = MinecraftServer.func_71276_C();


    EntityPlayerMP player = func_71521_c(sender);
    WorldServer world = server.func_71218_a((player.func_130014_f_()).field_73011_w.field_76574_g);

    int px = (int)player.field_70165_t >> 4;
    int pz = (int)player.field_70161_v >> 4;

    ChunkData d = TFC_Core.getCDM((World)world).getData(px, pz);

    if (params.length == 0) {

      Chunk chunk = world.func_72938_d((int)player.field_70165_t, (int)player.field_70161_v);
      for (int x = 0; x < 16; x++) {

        for (int z = 0; z < 16; z++) {
          world.func_147465_d(x + chunk.field_76635_g * 16, (int)player.field_70163_u - 1, z + chunk.field_76647_h * 16, Blocks.field_150325_L, getColor(d.getSpawnProtectionWithUpdate()), 2);
        }
      }
    } else if (params.length == 1) {

      int radius = Integer.parseInt(params[0]);
      for (int i = -radius; i <= radius; i++) {

        for (int k = -radius; k <= radius; k++) {

          Chunk chunk = world.func_72938_d((int)player.field_70165_t + i * 16, (int)player.field_70161_v + k * 16);
          for (int x = 0; x < 16; x++) {

            for (int z = 0; z < 16; z++) {
              world.func_147465_d(x + chunk.field_76635_g * 16, (int)player.field_70163_u - 1, z + chunk.field_76647_h * 16, Blocks.field_150325_L, getColor(d.getSpawnProtectionWithUpdate()), 2);
            }
          }
        }
      }
    }
  }

  private int getColor(int val) {
    return val / 270;
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GSPVisualCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */