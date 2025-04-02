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
import net.minecraft.world.chunk.Chunk;

public class ClearChunkCommand
  extends CommandBase
{
  public String func_71517_b() {
    return "clearchunk";
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
      
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Clearing Chunk"));
      Chunk chunk = world.func_72938_d((int)player.field_70165_t, (int)player.field_70161_v);
      for (int x = 0; x < 16; x++) {
        
        for (int z = 0; z < 16; z++) {
          
          for (int y = 256; y > 0; ) {
            
            Block id = chunk.func_150810_a(x, y, z);
            if (!TFC_Core.isRawStone(id)) {
              
              if (id != Blocks.field_150350_a) {
                world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
              }
              
              y--;
            } 
          } 
        } 
      } 
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Clearing Chunk Complete"));
    }
    else if (params.length == 1) {
      
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Clearing Chunks Within a Radius of " + Integer.parseInt(params[0])));
      int radius = Integer.parseInt(params[0]);
      for (int i = -radius; i <= radius; i++) {
        
        for (int k = -radius; k <= radius; k++) {
          
          Chunk chunk = world.func_72938_d((int)player.field_70165_t + i * 16, (int)player.field_70161_v + k * 16);
          for (int x = 0; x < 16; x++) {
            
            for (int z = 0; z < 16; z++) {
              
              for (int y = 256; y > 0; ) {
                
                Block id = chunk.func_150810_a(x, y, z);
                if (!TFC_Core.isRawStone(id)) {
                  
                  if (id != Blocks.field_150350_a) {
                    world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
                  }
                  
                  y--;
                } 
              } 
            } 
          } 
        } 
      } 
      TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Clearing Chunks Complete"));
    } 
  }


  
  public String func_71518_a(ICommandSender icommandsender) {
    return "";
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\ClearChunkCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */