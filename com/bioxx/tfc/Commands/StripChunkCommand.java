/*     */ package com.bioxx.tfc.Commands;
/*     */ 
/*     */ import com.bioxx.tfc.Core.TFC_Core;
/*     */ import com.bioxx.tfc.WorldGen.Generators.OreSpawnData;
/*     */ import com.bioxx.tfc.WorldGen.Generators.WorldGenOre;
/*     */ import com.bioxx.tfc.api.TFCBlocks;
/*     */ import com.bioxx.tfc.api.TFCOptions;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.command.CommandBase;
/*     */ import net.minecraft.command.ICommandSender;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.entity.player.EntityPlayerMP;
/*     */ import net.minecraft.init.Blocks;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.util.ChatComponentText;
/*     */ import net.minecraft.util.IChatComponent;
/*     */ import net.minecraft.world.WorldServer;
/*     */ import net.minecraft.world.chunk.Chunk;
/*     */ 
/*     */ public class StripChunkCommand
/*     */   extends CommandBase
/*     */ {
/*     */   public String func_71517_b() {
/*  24 */     return "stripchunk";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void func_71515_b(ICommandSender sender, String[] params) {
/*  30 */     EntityPlayerMP player = func_71521_c(sender);
/*     */     
/*  32 */     if (!TFCOptions.enableDebugMode) {
/*     */       
/*  34 */       TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Debug Mode Required"));
/*     */       
/*     */       return;
/*     */     } 
/*  38 */     MinecraftServer server = MinecraftServer.func_71276_C();
/*  39 */     WorldServer world = server.func_71218_a((player.func_130014_f_()).field_73011_w.field_76574_g);
/*  40 */     if (params.length == 0) {
/*     */       
/*  42 */       TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Stripping Chunk"));
/*  43 */       Chunk chunk = world.func_72938_d((int)player.field_70165_t, (int)player.field_70161_v);
/*  44 */       for (int x = 0; x < 16; x++) {
/*     */         
/*  46 */         for (int z = 0; z < 16; z++) {
/*     */           
/*  48 */           for (int y = 0; y < 256; y++) {
/*     */             
/*  50 */             Block id = chunk.func_150810_a(x, y, z);
/*  51 */             if (id != Blocks.field_150350_a && id != TFCBlocks.ore && id != TFCBlocks.ore2 && id != TFCBlocks.ore3 && id != Blocks.field_150357_h)
/*     */             {
/*  53 */               if (TFC_Core.isGround(id)) {
/*     */                 
/*  55 */                 world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
/*     */               }
/*     */               else {
/*     */                 
/*  59 */                 Boolean isOre = Boolean.valueOf(false);
/*  60 */                 for (OreSpawnData osd : WorldGenOre.oreList.values()) {
/*  61 */                   if (osd != null && id == osd.block) {
/*     */                     
/*  63 */                     isOre = Boolean.valueOf(true);
/*     */                     break;
/*     */                   } 
/*     */                 } 
/*  67 */                 if (!isOre.booleanValue())
/*     */                 {
/*  69 */                   world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
/*     */                 }
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  77 */       TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Stripping Chunk Complete"));
/*     */     }
/*  79 */     else if (params.length == 1) {
/*     */       
/*  81 */       TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Stripping Chunks Within a Radius of " + Integer.parseInt(params[0])));
/*  82 */       int radius = Integer.parseInt(params[0]);
/*  83 */       for (int i = -radius; i <= radius; i++) {
/*     */         
/*  85 */         for (int k = -radius; k <= radius; k++) {
/*     */           
/*  87 */           Chunk chunk = world.func_72938_d((int)player.field_70165_t + i * 16, (int)player.field_70161_v + k * 16);
/*  88 */           for (int x = 0; x < 16; x++) {
/*     */             
/*  90 */             for (int z = 0; z < 16; z++) {
/*     */               
/*  92 */               for (int y = 0; y < 256; y++) {
/*     */                 
/*  94 */                 Block id = chunk.func_150810_a(x, y, z);
/*  95 */                 if (id != Blocks.field_150350_a && id != TFCBlocks.ore && id != TFCBlocks.ore2 && id != TFCBlocks.ore3 && id != Blocks.field_150357_h)
/*     */                 {
/*  97 */                   if (TFC_Core.isGround(id)) {
/*     */                     
/*  99 */                     world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
/*     */                   }
/*     */                   else {
/*     */                     
/* 103 */                     Boolean isOre = Boolean.valueOf(false);
/* 104 */                     for (OreSpawnData osd : WorldGenOre.oreList.values()) {
/* 105 */                       if (osd != null && id == osd.block) {
/*     */                         
/* 107 */                         isOre = Boolean.valueOf(true);
/*     */                         break;
/*     */                       } 
/*     */                     } 
/* 111 */                     if (!isOre.booleanValue())
/*     */                     {
/* 113 */                       world.func_147465_d(x + chunk.field_76635_g * 16, y, z + chunk.field_76647_h * 16, Blocks.field_150350_a, 0, 2);
/*     */                     }
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 123 */       TFC_Core.sendInfoMessage((EntityPlayer)player, (IChatComponent)new ChatComponentText("Stripping Chunk Complete"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String func_71518_a(ICommandSender icommandsender) {
/* 130 */     return "";
/*     */   }
/*     */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Commands\StripChunkCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */