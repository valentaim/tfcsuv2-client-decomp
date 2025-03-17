/*    */ package com.bioxx.tfc.Commands;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class GiveSkillCommand
/*    */   extends CommandBase
/*    */ {
/*    */   public String func_71517_b() {
/* 16 */     return "giveskill";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 23 */     return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] params) {
/* 29 */     EntityPlayerMP var4 = func_71521_c(sender);
/*    */ 
/*    */     
/* 32 */     if (params.length == 2) {
/*    */       
/* 34 */       TFC_Core.getSkillStats((EntityPlayer)var4).increaseSkill(params[0], Integer.parseInt(params[1]));
/*    */     }
/* 36 */     else if (params.length == 3) {
/*    */       
/* 38 */       EntityPlayerMP player = func_82359_c(sender, params[0]);
/* 39 */       TFC_Core.getSkillStats((EntityPlayer)player).increaseSkill(params[1], Integer.parseInt(params[2]));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender icommandsender) {
/* 45 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GiveSkillCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */