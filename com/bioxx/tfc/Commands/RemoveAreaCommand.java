package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;

public class RemoveAreaCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "removearea";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP player = func_71521_c(sender);

    if (!TFCOptions.enableDebugMode) {

      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Required"));

      return;
    }
    MinecraftServer server = MinecraftServer.func_71276_C();
    WorldServer world = server.func_71218_a((player.func_130014_f_()).field_73011_w.field_76574_g);
    if (params.length == 0) {

      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Removing 16 blocks up and +/- x, z"));
      for (int x = -15; x < 16; x++) {

        for (int z = -15; z < 16; z++) {

          for (int y = 0; y < 16; y++) {

            Block id = world.func_147439_a(x + (int)player.field_70165_t, y + (int)player.field_70163_u, z + (int)player.field_70161_v);
            if (id != Blocks.field_150357_h) {
              world.func_147465_d(x + (int)player.field_70165_t, y + (int)player.field_70163_u, z + (int)player.field_70161_v, Blocks.field_150350_a, 0, 2);
            }
          }
        }
      }
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Removing Area Complete"));
    }
    else if (params.length == 3) {

      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Removing Area"));
      int radius = Integer.parseInt(params[0]);
      for (int x = -radius; x <= Integer.parseInt(params[0]); x++) {

        for (int z = -Integer.parseInt(params[2]); z <= Integer.parseInt(params[2]); z++) {

          for (int y = 0; y <= Integer.parseInt(params[1]); y++) {

            Block id = world.func_147439_a(x + (int)player.field_70165_t, y + (int)player.field_70163_u, z + (int)player.field_70161_v);
            if (id != Blocks.field_150357_h) {
              world.func_147465_d(x + (int)player.field_70165_t, y + (int)player.field_70163_u, z + (int)player.field_70161_v, Blocks.field_150350_a, 0, 2);
            }
          }
        }
      }
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Removing Area Complete"));
    }
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\RemoveAreaCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */