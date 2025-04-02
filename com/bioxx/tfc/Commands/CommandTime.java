package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Time;
import com.bioxx.tfc.api.TFCOptions;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldServer;


public class CommandTime
  extends CommandBase
{
  public String func_71517_b() {
    return "time";
  }






  public int func_82362_a() {
    return 2;
  }



  public String func_71518_a(ICommandSender par1ICommandSender) {
    return "commands.time.usage";
  }



  public void func_71515_b(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    if (!TFCOptions.enableDebugMode) {
      if (par2ArrayOfStr.length > 1) {


        long currentTime = TFC_Time.getTotalTicks();

        if (par2ArrayOfStr[0].equals("set")) {
          int i;
          if (par2ArrayOfStr[1].equals("day")) {

            i = (int)(currentTime + 24000L - currentTime % 24000L);
          }
          else if (par2ArrayOfStr[1].equals("night")) {

            i = 12500 + (int)(currentTime + 24000L - currentTime % 24000L);
          }
          else {

            i = func_71528_a(par1ICommandSender, par2ArrayOfStr[1], 0);
          }
          if (i < currentTime) {

            par1ICommandSender.func_145747_a((IChatComponent)new ChatComponentText("Cannot set time to before current time."));
          }
          else {

            setTime(par1ICommandSender, i);
            func_152373_a(par1ICommandSender, (ICommand)this, "commands.time.set", new Object[] { Integer.valueOf(i) });
          }

          return;
        }
        if (par2ArrayOfStr[0].equals("add")) {

          int i = func_71528_a(par1ICommandSender, par2ArrayOfStr[1], 0);
          if (i + currentTime < currentTime) {

            par1ICommandSender.func_145747_a((IChatComponent)new ChatComponentText("Cannot set time to before current time."));
          }
          else {

            addTime(par1ICommandSender, i);
            func_152373_a(par1ICommandSender, (ICommand)this, "commands.time.added", new Object[] { Integer.valueOf(i) });
          }

          return;
        }
      }
    } else {
      (new net.minecraft.command.CommandTime()).func_71515_b(par1ICommandSender, par2ArrayOfStr);
      return;
    }
    throw new WrongUsageException("commands.time.usage", new Object[0]);
  }







  public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    return (par2ArrayOfStr.length == 1) ? func_71530_a(par2ArrayOfStr, new String[] { "set", "add" }) : ((par2ArrayOfStr.length == 2 && par2ArrayOfStr[0].equals("set")) ? func_71530_a(par2ArrayOfStr, new String[] { "day", "night" }) : null);
  }





  protected void setTime(ICommandSender par1ICommandSender, int par2) {
    for (int j = 0; j < (MinecraftServer.func_71276_C()).field_71305_c.length; j++)
    {
      (MinecraftServer.func_71276_C()).field_71305_c[j].func_72877_b(par2);
    }
  }





  protected void addTime(ICommandSender par1ICommandSender, int par2) {
    for (int j = 0; j < (MinecraftServer.func_71276_C()).field_71305_c.length; j++) {

      WorldServer worldserver = (MinecraftServer.func_71276_C()).field_71305_c[j];
      worldserver.func_72877_b(worldserver.func_72820_D() + par2);
    }
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\CommandTime.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */