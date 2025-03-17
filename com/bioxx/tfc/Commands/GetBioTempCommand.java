/*    */ package com.bioxx.tfc.Commands;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Climate;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.PlayerNotFoundException;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ 
/*    */ 
/*    */ public class GetBioTempCommand
/*    */   extends CommandBase
/*    */ {
/*    */   public String func_71517_b() {
/* 14 */     return "gbt";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] params) {
/* 21 */     EntityPlayerMP var4 = func_71521_c(sender);
/*    */     
/* 23 */     float t = TFC_Climate.getBioTemperatureHeight(var4.field_70170_p, (int)var4.field_70165_t, (int)var4.field_70163_u, (int)var4.field_70161_v);
/* 24 */     throw new PlayerNotFoundException("BioTemp: " + t, new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender icommandsender) {
/* 30 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetBioTempCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */