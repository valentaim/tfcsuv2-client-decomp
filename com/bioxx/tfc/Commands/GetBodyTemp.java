/*    */ package com.bioxx.tfc.Commands;
/*    */ 
/*    */ import java.util.List;
/*    */ import net.minecraft.command.CommandBase;
/*    */ import net.minecraft.command.ICommandSender;
/*    */ import net.minecraft.command.PlayerNotFoundException;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ 
/*    */ public class GetBodyTemp
/*    */   extends CommandBase
/*    */ {
/*    */   public String func_71517_b() {
/* 15 */     return "bodytemp";
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_82362_a() {
/* 21 */     return 0;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public List func_71516_a(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
/* 28 */     return func_71530_a(par2ArrayOfStr, MinecraftServer.func_71276_C().func_71213_z());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_71515_b(ICommandSender sender, String[] params) {
/* 34 */     EntityPlayerMP var4 = func_71521_c(sender);
/* 35 */     float temp = var4.getEntityData().func_74764_b("bodyTemp") ? var4.getEntityData().func_74760_g("bodyTemp") : -1.0F;
/* 36 */     throw new PlayerNotFoundException("Body Temperature: " + temp, new Object[0]);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_71519_b(ICommandSender sender) {
/* 43 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String func_71518_a(ICommandSender icommandsender) {
/* 49 */     return "";
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\GetBodyTemp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */