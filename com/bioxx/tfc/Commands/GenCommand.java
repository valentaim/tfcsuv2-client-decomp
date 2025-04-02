package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Core;
import com.bioxx.tfc.WorldGen.Generators.Trees.WorldGenCustomFruitTree;
import com.bioxx.tfc.WorldGen.Generators.WorldGenFissure;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "gen";
  }



  public void func_71515_b(ICommandSender sender, String[] params) {
    EntityPlayerMP player = func_71521_c(sender);

    if (!TFCOptions.enableDebugMode) {

      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Required"));

      return;
    }
    if (params.length == 1) {

      if (params[0].equalsIgnoreCase("fruittree")) {

        TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Fruit Tree"));
        WorldGenCustomFruitTree worldGenCustomFruitTree = new WorldGenCustomFruitTree(false, TFCBlocks.fruitTreeLeaves, 0);

        if (!worldGenCustomFruitTree.func_76484_a(sender.func_130014_f_(), (sender.func_130014_f_()).field_73012_v, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generation Failed"));
        }
      }
    } else if (params.length == 2) {

      if (params[0].equals("fissure")) {

        WorldGenFissure gen = null;
        if (params[1].equals("water")) {

          gen = new WorldGenFissure(TFCBlocks.freshWater);
          gen.checkStability = false;
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Water"));
        }
        else if (params[1].equals("hotwater")) {

          gen = new WorldGenFissure(TFCBlocks.hotWater);
          gen.checkStability = false;
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Hot Springs"));
        }
        else {

          gen = new WorldGenFissure(null);
          gen.checkStability = false;
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Fissure"));
        }
        gen.generate(sender.func_130014_f_(), (sender.func_130014_f_()).field_73012_v, (int)player.field_70165_t, (int)player.field_70163_u - 1, (int)player.field_70161_v);
      }
      else if (params[0].equalsIgnoreCase("tree")) {

        int i = getTree(params[1]);

        if (i != -1) {

          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Small " + params[1] + " Tree"));
          WorldGenerator treeGen = TFCBiome.getTreeGen(i, Boolean.valueOf(false));
          if (!treeGen.func_76484_a(sender.func_130014_f_(), (sender.func_130014_f_()).field_73012_v, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
            TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generation Failed"));
          }
        } else {
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Invalid Tree"));
        }
      }
    } else if (params.length == 3 && params[0].equalsIgnoreCase("tree") && params[2].equalsIgnoreCase("big")) {

      int i = getTree(params[1]);

      if (i != -1) {

        TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generating Big " + params[1] + " Tree"));
        WorldGenerator treeGen = TFCBiome.getTreeGen(i, Boolean.valueOf(true));
        if (!treeGen.func_76484_a(sender.func_130014_f_(), (sender.func_130014_f_()).field_73012_v, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v)) {
          TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Generation Failed"));
        }
      } else {
        TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Invalid Tree"));
      }
    }
  }


  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }


  public int getTree(String tree) {
    if ("oak".equalsIgnoreCase(tree))
      return 0;
    if ("aspen".equalsIgnoreCase(tree))
      return 1;
    if ("birch".equalsIgnoreCase(tree))
      return 2;
    if ("chestnut".equalsIgnoreCase(tree))
      return 3;
    if ("douglasfir".equalsIgnoreCase(tree))
      return 4;
    if ("hickory".equalsIgnoreCase(tree))
      return 5;
    if ("maple".equalsIgnoreCase(tree))
      return 6;
    if ("ash".equalsIgnoreCase(tree))
      return 7;
    if ("pine".equalsIgnoreCase(tree))
      return 8;
    if ("sequoia".equalsIgnoreCase(tree))
      return 9;
    if ("spruce".equalsIgnoreCase(tree))
      return 10;
    if ("sycamore".equalsIgnoreCase(tree))
      return 11;
    if ("whitecedar".equalsIgnoreCase(tree))
      return 12;
    if ("whiteelm".equalsIgnoreCase(tree))
      return 13;
    if ("willow".equalsIgnoreCase(tree))
      return 14;
    if ("kapok".equalsIgnoreCase(tree))
      return 15;
    if ("acacia".equalsIgnoreCase(tree))
      return 16;
    if ("test".equalsIgnoreCase(tree)) {
      return 17;
    }
    return -1;
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GenCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */