package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Chunkdata.ChunkData;
import com.bioxx.tfc.Core.TFC_Core;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;


public class GetSpawnProtectionCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "gsp";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    if (sender.func_70003_b(0, sender.func_70005_c_())) {
      int x, z;
      EntityPlayerMP player = func_71521_c(sender);



      if (params.length >= 2) {

        try
        {
          x = Integer.parseInt(params[0]);
          z = Integer.parseInt(params[1]);
        }
        catch (NumberFormatException ex)
        {
          throw new WrongUsageException("invalid chunk coords: %d x %d", new Object[] { params[0], params[1] });
        }

      } else {

        x = (int)player.field_70165_t >> 4;
        z = (int)player.field_70161_v >> 4;
      }

      ChunkData d = TFC_Core.getCDM(player.field_70170_p).getData(x, z);

      if (d != null) {
        throw new PlayerNotFoundException("SP (" + x + "," + z + "): " + d.getSpawnProtectionWithUpdate(), new Object[0]);
      }
      throw new PlayerNotFoundException("Unable to find ChunkData for " + x + "," + z, new Object[0]);
    }
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetSpawnProtectionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */