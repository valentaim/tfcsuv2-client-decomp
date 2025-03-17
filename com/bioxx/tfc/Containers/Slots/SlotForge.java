/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.HeatRegistry;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotForge
/*    */   extends Slot
/*    */ {
/*    */   public SlotForge(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k) {
/* 16 */     super(iinventory, i, j, k);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 22 */     HeatRegistry manager = HeatRegistry.getInstance();
/* 23 */     return (manager.findMatchingIndex(itemstack) != null && 
/* 24 */       !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre) && 
/* 25 */       !(itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_75219_a() {
/* 31 */     return 1;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotForge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */