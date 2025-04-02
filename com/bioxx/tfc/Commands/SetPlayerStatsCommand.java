package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;


public class SetPlayerStatsCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "sps";
  }




  public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
  }


  public void func_71515_b(ICommandSender sender, String[] params) {
    double[] values = new double[3];

    if (params.length == 4 || params.length == 3) {
      for (int i = 0; i < 3; i++) {
        try {
          values[i] = Double.parseDouble(params[i + params.length - 3]);
        } catch (NumberFormatException e) {
          throw new PlayerNotFoundException("Invalid", new Object[0]);
        }
        if (values[i] < 0.0D || values[i] > 100.0D) {
          throw new PlayerNotFoundException("Invalid", new Object[0]);
        }
      }
    }
    EntityPlayerMP player = func_71521_c(sender);
    if (params.length == 4) {
      try {
        player = func_82359_c(sender, params[0]);
      } catch (PlayerNotFoundException e) {
        throw new PlayerNotFoundException("Unknown Player", new Object[0]);
      }
    }
    if (player == null) {
      throw new PlayerNotFoundException("Invalid", new Object[0]);
    }
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats((EntityPlayer)player);
    player.func_70606_j((int)(values[0] / 100.0D * player.func_110138_aP()));
    fs.setFoodLevel((int)values[1]);
    fs.waterLevel = (int)(values[2] / 100.0D * fs.getMaxWater((EntityPlayer)player));
    throw new PlayerNotFoundException(values[0] + " " + values[1] + " " + values[2], new Object[0]);
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\SetPlayerStatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */