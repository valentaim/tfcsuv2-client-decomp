/*    */ package com.bioxx.tfc.Commands;
/*    */ 
/*    */ import com.bioxx.tfc.Core.Player.FoodStatsTFC;
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.PlayerNotFoundException;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class SetPlayerStatsCommand
/*    */   extends CommandBase
/*    */ {
/*    */   public String func_71517_b() {
/* 18 */     return "sps";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 25 */     return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
/*    */   }
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] params) {
/* 30 */     double[] values = new double[3];
/*    */     
/* 32 */     if (params.length == 4 || params.length == 3) {
/* 33 */       for (int i = 0; i < 3; i++) {
/*    */         try {
/* 35 */           values[i] = Double.parseDouble(params[i + params.length - 3]);
/* 36 */         } catch (NumberFormatException e) {
/* 37 */           throw new PlayerNotFoundException("Invalid", new Object[0]);
/*    */         } 
/* 39 */         if (values[i] < 0.0D || values[i] > 100.0D) {
/* 40 */           throw new PlayerNotFoundException("Invalid", new Object[0]);
/*    */         }
/*    */       } 
/*    */     }
/* 44 */     EntityPlayerMP player = func_71521_c(sender);
/* 45 */     if (params.length == 4) {
/*    */       try {
/* 47 */         player = func_82359_c(sender, params[0]);
/* 48 */       } catch (PlayerNotFoundException e) {
/* 49 */         throw new PlayerNotFoundException("Unknown Player", new Object[0]);
/*    */       } 
/*    */     }
/* 52 */     if (player == null) {
/* 53 */       throw new PlayerNotFoundException("Invalid", new Object[0]);
/*    */     }
/* 55 */     FoodStatsTFC fs = TFC_Core.getPlayerFoodStats((EntityPlayer)player);
/* 56 */     player.func_70606_j((int)(values[0] / 100.0D * player.func_110138_aP()));
/* 57 */     fs.setFoodLevel((int)values[1]);
/* 58 */     fs.waterLevel = (int)(values[2] / 100.0D * fs.getMaxWater((EntityPlayer)player));
/* 59 */     throw new PlayerNotFoundException(values[0] + " " + values[1] + " " + values[2], new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender icommandsender) {
/* 65 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\SetPlayerStatsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */