/*    */ package com.bioxx.tfc.api.Events;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.Cancelable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import net.minecraftforge.event.entity.EntityEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Cancelable
/*    */ public class AnvilWeldEvent
/*    */   extends EntityEvent
/*    */ {
/*    */   public ItemStack input1;
/*    */   public ItemStack input2;
/*    */   public ItemStack result;
/*    */   public TileEntity anvilTE;
/*    */   
/*    */   public AnvilWeldEvent(EntityPlayer entityplayer, TileEntity te, ItemStack i1, ItemStack i2, ItemStack r) {
/* 35 */     super((Entity)entityplayer);
/* 36 */     this.input1 = i1;
/* 37 */     this.input2 = i2;
/* 38 */     this.result = r;
/* 39 */     this.anvilTE = te;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\AnvilWeldEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */