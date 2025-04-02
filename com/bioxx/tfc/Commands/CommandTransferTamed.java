package com.bioxx.tfc.Commands;

import com.bioxx.tfc.Core.TFC_Core;
import java.util.Arrays;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.PlayerNotFoundException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;



public class CommandTransferTamed
  extends CommandBase
{
  public List func_71514_a() {
    return Arrays.asList(new String[] { "transfer" });
  }


  
  public String func_71517_b() {
    return "transferTamed";
  }





  
  public int func_82362_a() {
    return 0;
  }


  
  public boolean func_71519_b(ICommandSender sender) {
    return true;
  }


  
  public String func_71518_a(ICommandSender sender) {
    return "commands.transferTamed.usage";
  }



  
  public void func_71515_b(ICommandSender sender, String[] chars) {
    if (sender instanceof EntityPlayer) {
      EntityPlayerMP entityplayermp = null;
      if (chars.length > 0) {
        entityplayermp = func_82359_c(sender, chars[0]);
      }
      
      EntityTameable tamedEntity = null;
      List<EntityTameable> entitiesInRange = ((EntityPlayer)sender).field_70170_p.func_72872_a(EntityTameable.class, ((EntityPlayer)sender).field_70121_D.func_72314_b(3.0D, 1.0D, 3.0D));
      
      if (entitiesInRange.isEmpty())
      {
        throw new WrongUsageException("commands.transferTamed.noTamed", new Object[0]);
      }
      if (entitiesInRange.size() > 1) {
        throw new WrongUsageException("commands.transferTamed.tooMany", new Object[0]);
      }
      if (entitiesInRange.size() == 1) {
        tamedEntity = entitiesInRange.get(0);
        if (tamedEntity.func_70902_q() == null || !tamedEntity.func_70902_q().equals(entityplayermp)) {
          throw new WrongUsageException("commands.transferTamed.wrongOwner", new Object[0]);
        }
      } 
      
      if (entityplayermp == null) {
        
        if (tamedEntity != null && chars.length == 0) {
          tamedEntity.func_70903_f(false);
          tamedEntity.func_152115_b("");
        } else {
          
          throw new PlayerNotFoundException();
        } 
      } else {
        if (entityplayermp == sender)
        {
          throw new PlayerNotFoundException("commands.transferTamed.sameTarget", new Object[0]);
        }
        if (tamedEntity != null) {
          
          tamedEntity.func_152115_b(entityplayermp.func_110124_au().toString());
          
          ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.transferTamed.display.incoming", new Object[] { sender.func_145748_c_() });
          ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.transferTamed.display.outgoing", new Object[] { entityplayermp.func_145748_c_() });
          chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.TRUE);
          chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.TRUE);
          TFC_Core.sendInfoMessage((EntityPlayer)entityplayermp, (IChatComponent)chatcomponenttranslation);
          sender.func_145747_a((IChatComponent)chatcomponenttranslation1);
        } 
      } 
    } else {
      throw new WrongUsageException("commands.transferTamed.wrongSender", new Object[0]);
    } 
  }










  
  public List func_71516_a(ICommandSender sender, String[] string) {
    return func_71530_a(string, MinecraftServer.func_71276_C().func_71213_z());
  }





  
  public boolean func_82358_a(String[] string, int index) {
    return (index == 0);
  }
}


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\CommandTransferTamed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */