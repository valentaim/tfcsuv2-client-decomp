/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class SlotSizeSmall
/*    */   extends Slot
/*    */ {
/* 12 */   private EnumSize size = EnumSize.SMALL;
/*    */   
/*    */   public SlotSizeSmall(IInventory iinventory, int i, int j, int k) {
/* 15 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 21 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize)
/* 22 */       return true; 
/* 23 */     if (!(itemstack.func_77973_b() instanceof ISize))
/* 24 */       return true; 
/* 25 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotSizeSmall.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */