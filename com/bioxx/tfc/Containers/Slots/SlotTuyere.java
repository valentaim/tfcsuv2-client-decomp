/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotTuyere
/*    */   extends Slot
/*    */ {
/*    */   public SlotTuyere(IInventory iinventory, int i, int j, int k) {
/* 13 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 19 */     return itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemTuyere;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 25 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotTuyere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */