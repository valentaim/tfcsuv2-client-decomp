/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.TFCItems;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotMetal
/*    */   extends Slot
/*    */ {
/*    */   public SlotMetal(IInventory iinventory, int i, int j, int k) {
/* 14 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 20 */     return (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemMeltedMetal || itemstack.func_77973_b() == TFCItems.ceramicMold);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotMetal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */