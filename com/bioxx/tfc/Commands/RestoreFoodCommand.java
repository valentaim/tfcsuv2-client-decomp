package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.Player.FoodStatsTFC;
import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class RestoreFoodCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "rf";
  }




  public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
    return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
  }


  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP player = func_71521_c(sender);
    if (player == null) {
      throw new PlayerNotFoundException("Invalid", new Object[0]);
    }
    FoodStatsTFC fs = TFC_Core.getPlayerFoodStats((EntityPlayer)player);
    player.func_70606_j(player.func_110138_aP());
    fs.setFoodLevel(24.0F);
    fs.waterLevel = fs.getMaxWater((EntityPlayer)player);
    for (EnumFoodGroup gr : EnumFoodGroup.values())
      fs.addNutrition(gr, 1.0F, false);
    TFC_Core.setPlayerFoodStats((EntityPlayer)player, fs);
    TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Done."));
  }


  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\RestoreFoodCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */