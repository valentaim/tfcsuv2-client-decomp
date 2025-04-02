package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.Handlers.Network.AbstractPacket;
import com.bioxx.tfc.Handlers.Network.DebugModePacket;
import com.bioxx.tfc.TerraFirmaCraft;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class DebugModeCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "debugmode";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP player = func_71521_c(sender);

    if (params.length == 0) {

      if (TFCOptions.enableDebugMode) {

        TFCOptions.enableDebugMode = false;
        TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Disabled"));
      }
      else {

        TFCOptions.enableDebugMode = true;
        TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Enabled"));
      }

      DebugModePacket debugModePacket = new DebugModePacket((EntityPlayer)player);
      TerraFirmaCraft.PACKET_PIPELINE.sendTo((AbstractPacket)debugModePacket, player);
    }
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\DebugModeCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */