/*    */ package com.bioxx.tfc.Containers;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.Slots.SlotHopper;
/*    */ import com.bioxx.tfc.Core.Player.PlayerInventory;
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.entity.player.InventoryPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ContainerHopper
/*    */   extends ContainerTFC
/*    */ {
/*    */   private final IInventory hopperInv;
/*    */   
/*    */   public ContainerHopper(InventoryPlayer playerInv, IInventory inv) {
/* 19 */     this.hopperInv = inv;
/* 20 */     inv.func_70295_k_();
/*    */ 
/*    */     
/* 23 */     for (int i = 0; i < inv.func_70302_i_(); i++)
/*    */     {
/* 25 */       func_75146_a((Slot)new SlotHopper(inv, i, 44 + i * 18, 17));
/*    */     }
/*    */     
/* 28 */     PlayerInventory.buildInventoryLayout(this, playerInv, 8, 54, false, true);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack transferStackInSlotTFC(EntityPlayer player, int slotNum) {
/* 34 */     ItemStack origStack = null;
/* 35 */     Slot slot = this.field_75151_b.get(slotNum);
/*    */     
/* 37 */     if (slot != null && slot.func_75216_d()) {
/*    */       
/* 39 */       ItemStack slotStack = slot.func_75211_c();
/* 40 */       if (!TFC_Core.isItemHopperValid(slot.func_75211_c())) return null; 
/* 41 */       origStack = slotStack.func_77946_l();
/*    */       
/* 43 */       if (slotNum < 5) {
/*    */         
/* 45 */         if (!func_75135_a(slotStack, 5, this.field_75151_b.size(), true)) {
/* 46 */           return null;
/*    */         
/*    */         }
/*    */       }
/* 50 */       else if (!func_75135_a(slotStack, 0, 5, false)) {
/* 51 */         return null;
/*    */       } 
/*    */       
/* 54 */       if (slotStack.field_77994_a <= 0) {
/* 55 */         slot.func_75215_d(null);
/*    */       } else {
/* 57 */         slot.func_75218_e();
/*    */       } 
/* 59 */       if (slotStack.field_77994_a == origStack.field_77994_a) {
/* 60 */         return null;
/*    */       }
/* 62 */       slot.func_82870_a(player, slotStack);
/*    */     } 
/*    */     
/* 65 */     return origStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75145_c(EntityPlayer player) {
/* 71 */     return this.hopperInv.func_70300_a(player);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_75134_a(EntityPlayer player) {
/* 80 */     super.func_75134_a(player);
/* 81 */     this.hopperInv.func_70305_f();
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\ContainerHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */