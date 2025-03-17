/*    */ package com.bioxx.tfc.api.Events;
/*    */ 
/*    */ import cpw.mods.fml.common.eventhandler.Cancelable;
/*    */ import cpw.mods.fml.common.eventhandler.Event;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.TileEntity;
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
/*    */ public class ItemCookEvent
/*    */   extends Event
/*    */ {
/*    */   public ItemStack input1;
/*    */   public ItemStack result;
/*    */   public TileEntity te;
/*    */   
/*    */   public ItemCookEvent(ItemStack i1, ItemStack r, TileEntity t) {
/* 30 */     this.input1 = i1;
/* 31 */     this.result = r;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\api\Events\ItemCookEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */