/*     */ package com.bioxx.tfc.Commands;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.command.PlayerNotFoundException;
/*     */ import net.minecraft.command.WrongUsageException;
/*     */ import net.minecraft.entity.passive.EntityTameable;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentTranslation;
/*     */ import net.minecraft.util.EnumChatFormatting;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandTransferTamed
/*     */   extends CommandBase
/*     */ {
/*     */   public List func_71514_a() {
/*  24 */     return Arrays.asList(new String[] { "transfer" });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71517_b() {
/*  30 */     return "transferTamed";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int func_82362_a() {
/*  39 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_71519_b(ICommandSender sender) {
/*  45 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender sender) {
/*  51 */     return "commands.transferTamed.usage";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] chars) {
/*  58 */     if (sender instanceof EntityPlayer) {
/*  59 */       EntityPlayerMP entityplayermp = null;
/*  60 */       if (chars.length > 0) {
/*  61 */         entityplayermp = func_82359_c(sender, chars[0]);
/*     */       }
/*     */       
/*  64 */       EntityTameable tamedEntity = null;
/*  65 */       List<EntityTameable> entitiesInRange = ((EntityPlayer)sender).field_70170_p.func_72872_a(EntityTameable.class, ((EntityPlayer)sender).field_70121_D.func_72314_b(3.0D, 1.0D, 3.0D));
/*     */       
/*  67 */       if (entitiesInRange.isEmpty())
/*     */       {
/*  69 */         throw new WrongUsageException("commands.transferTamed.noTamed", new Object[0]);
/*     */       }
/*  71 */       if (entitiesInRange.size() > 1) {
/*  72 */         throw new WrongUsageException("commands.transferTamed.tooMany", new Object[0]);
/*     */       }
/*  74 */       if (entitiesInRange.size() == 1) {
/*  75 */         tamedEntity = entitiesInRange.get(0);
/*  76 */         if (tamedEntity.func_70902_q() == null || !tamedEntity.func_70902_q().equals(entityplayermp)) {
/*  77 */           throw new WrongUsageException("commands.transferTamed.wrongOwner", new Object[0]);
/*     */         }
/*     */       } 
/*     */       
/*  81 */       if (entityplayermp == null) {
/*     */         
/*  83 */         if (tamedEntity != null && chars.length == 0) {
/*  84 */           tamedEntity.func_70903_f(false);
/*  85 */           tamedEntity.func_152115_b("");
/*     */         } else {
/*     */           
/*  88 */           throw new PlayerNotFoundException();
/*     */         } 
/*     */       } else {
/*  91 */         if (entityplayermp == sender)
/*     */         {
/*  93 */           throw new PlayerNotFoundException("commands.transferTamed.sameTarget", new Object[0]);
/*     */         }
/*  95 */         if (tamedEntity != null) {
/*     */           
/*  97 */           tamedEntity.func_152115_b(entityplayermp.func_110124_au().toString());
/*     */           
/*  99 */           ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation("commands.transferTamed.display.incoming", new Object[] { sender.func_145748_c_() });
/* 100 */           ChatComponentTranslation chatcomponenttranslation1 = new ChatComponentTranslation("commands.transferTamed.display.outgoing", new Object[] { entityplayermp.func_145748_c_() });
/* 101 */           chatcomponenttranslation.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.TRUE);
/* 102 */           chatcomponenttranslation1.func_150256_b().func_150238_a(EnumChatFormatting.GRAY).func_150217_b(Boolean.TRUE);
/* 103 */           TFC_Core.sendInfoMessage((EntityPlayer)entityplayermp, (IChatComponent)chatcomponenttranslation);
/* 104 */           sender.func_145747_a((IChatComponent)chatcomponenttranslation1);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 108 */       throw new WrongUsageException("commands.transferTamed.wrongSender", new Object[0]);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List func_71516_a(ICommandSender sender, String[] string) {
/* 123 */     return func_71530_a(string, MinecraftServer.func_71276_C().func_71213_z());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean func_82358_a(String[] string, int index) {
/* 132 */     return (index == 0);
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\CommandTransferTamed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */