/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotOutputOnly
/*    */   extends Slot
/*    */ {
/*    */   public SlotOutputOnly(IInventory iinventory, int i, int j, int k) {
/* 11 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack par1ItemStack) {
/* 17 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotOutputOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */