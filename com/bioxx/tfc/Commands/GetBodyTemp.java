package com.bioxx.tfc.Commands;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;


public class GetBodyTemp
  extends CommandBase
{
  public String func_71517_b() {
    return "bodytemp";
  }



  public int func_82362_a() {
    return 0;
  }




  public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP var4 = func_71521_c(sender);
    float temp = var4.getEntityData().func_74764_b("bodyTemp") ? var4.getEntityData().func_74760_g("bodyTemp") : -1.0F;
    throw new PlayerNotFoundException("Body Temperature: " + temp, new Object[0]);
  }




  public boolean func_71519_b(ICommandSender sender) {
    return true;
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetBodyTemp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */