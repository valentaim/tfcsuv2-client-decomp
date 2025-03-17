/*    */ package com.bioxx.tfc.Handlers;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.Core.TFC_Time;
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.nbt.NBTTagCompound;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EnteringChunkHandler
/*    */ {
/*    */   @SubscribeEvent
/*    */   public void onEnterChunk(EntityEvent.EnteringChunk event) {
/* 16 */     if (event.entity instanceof EntityPlayer) {
/*    */       
/* 18 */       EntityPlayer player = (EntityPlayer)event.entity;
/* 19 */       if (!player.field_70170_p.field_72995_K) {
/*    */         
/* 21 */         NBTTagCompound nbt = player.getEntityData();
/* 22 */         long spawnProtectionTimer = nbt.func_74764_b("spawnProtectionTimer") ? nbt.func_74763_f("spawnProtectionTimer") : (TFC_Time.getTotalTicks() + 1000L);
/*    */ 
/*    */         
/* 25 */         if (TFC_Core.getCDM(event.entity.field_70170_p) != null && (event.newChunkX != event.oldChunkX || event.newChunkZ != event.oldChunkZ)) {
/*    */           
/* 27 */           TFC_Core.getCDM(event.entity.field_70170_p).setLastVisted(event.oldChunkX, event.oldChunkZ);
/*    */           
/* 29 */           spawnProtectionTimer = TFC_Time.getTotalTicks() + 1000L;
/* 30 */           writeProtectionToNBT(nbt, spawnProtectionTimer);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void writeProtectionToNBT(NBTTagCompound nbt, long spawnProtectionTimer) {
/* 38 */     nbt.func_74772_a("spawnProtectionTimer", spawnProtectionTimer);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\EnteringChunkHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */