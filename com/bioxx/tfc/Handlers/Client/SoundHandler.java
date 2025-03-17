/*    */ package com.bioxx.tfc.Handlers.Client;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.SubscribeEvent;
/*    */ import net.minecraft.client.audio.ISound;
/*    */ import net.minecraft.client.audio.PositionedSoundRecord;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.client.event.sound.PlaySoundEvent17;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SoundHandler
/*    */ {
/*    */   private ISound iSound;
/*    */   
/*    */   @SubscribeEvent
/*    */   public void onSound17(PlaySoundEvent17 event) {
/* 18 */     if (event.sound != null && event.category != null && event.category.func_147155_a().equalsIgnoreCase("music"))
/*    */     {
/* 20 */       if (event.manager.func_148597_a(this.iSound)) {
/* 21 */         event.result = null;
/*    */       } else {
/*    */         
/* 24 */         this.iSound = (ISound)PositionedSoundRecord.func_147673_a(new ResourceLocation("terrafirmacraft:music.tfc"));
/* 25 */         event.result = this.iSound;
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\Client\SoundHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */