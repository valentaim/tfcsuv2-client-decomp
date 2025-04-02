package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.Items.ItemLooseRock;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.block.Block;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.entity.player.EntityPlayerMP;



public class GetRocksCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "gr";
  }




  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP var4 = func_71521_c(sender);

    DataLayer t0 = TFC_Climate.getRockLayer(var4.field_70170_p, (int)var4.field_70165_t, (int)var4.field_70163_u, (int)var4.field_70161_v, 0);
    DataLayer t1 = TFC_Climate.getRockLayer(var4.field_70170_p, (int)var4.field_70165_t, (int)var4.field_70163_u, (int)var4.field_70161_v, 1);
    DataLayer t2 = TFC_Climate.getRockLayer(var4.field_70170_p, (int)var4.field_70165_t, (int)var4.field_70163_u, (int)var4.field_70161_v, 2);

    String t0s = ((ItemLooseRock)TFCItems.looseRock).metaNames[t0.data1];
    String t1s = ((ItemLooseRock)TFCItems.looseRock).metaNames[t1.data1];
    String t2s = ((ItemLooseRock)TFCItems.looseRock).metaNames[t2.data1];

    throw new PlayerNotFoundException("Rock Layer 1: " + t0s + "   Rock Layer 2: " + t1s + "   Rock Layer 3: " + t2s, new Object[0]);
  }


  public static int getSoilMetaFromStone(Block inType, int inMeta) {
    if (inType == TFCBlocks.stoneIgIn)
      return inMeta;
    if (inType == TFCBlocks.stoneSed)
      return inMeta + 3;
    if (inType == TFCBlocks.stoneIgEx) {
      return inMeta + 11;
    }
    return inMeta + 15;
  }



  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetRocksCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */