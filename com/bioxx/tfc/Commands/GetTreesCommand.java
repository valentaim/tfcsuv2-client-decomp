package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.api.Enums.EnumTree;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;


public class GetTreesCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "gt";
  }





  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP var4 = func_71521_c(sender);
    int posX = (int)Math.floor(var4.field_70165_t);
    int posY = (int)Math.floor(var4.field_70163_u);
    int posZ = (int)Math.floor(var4.field_70161_v);

    int t0ID = TFC_Climate.getTreeLayer(var4.field_70170_p, posX, posY, posZ, 0);
    int t1ID = TFC_Climate.getTreeLayer(var4.field_70170_p, posX, posY, posZ, 1);
    int t2ID = TFC_Climate.getTreeLayer(var4.field_70170_p, posX, posY, posZ, 2);

    String t0 = "None";
    if (t0ID != -1)
      t0 = EnumTree.values()[t0ID].name();
    String t1 = "None";
    if (t0ID != -1)
      t1 = EnumTree.values()[t1ID].name();
    String t2 = "None";
    if (t0ID != -1) {
      t2 = EnumTree.values()[t2ID].name();
    }
    throw new PlayerNotFoundException("Tree 0: " + t0 + "   Tree 1: " + t1 + "   Tree 2: " + t2, new Object[0]);
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetTreesCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */