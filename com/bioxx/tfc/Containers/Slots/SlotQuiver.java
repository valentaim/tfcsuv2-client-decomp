/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotQuiver
/*    */   extends Slot
/*    */ {
/*    */   public SlotQuiver(IInventory iinventory, int i, int j, int k) {
/* 14 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 20 */     return (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemJavelin || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemArrow);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotQuiver.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */