/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.Containers.ContainerHorseInventoryTFC;
/*    */ import net.minecraft.init.Items;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ContainerHorseInventorySlotSaddle
/*    */   extends Slot
/*    */ {
/*    */   public ContainerHorseInventorySlotSaddle(ContainerHorseInventoryTFC par1ContainerHorseInventory, IInventory par2IInventory, int par3, int par4, int par5) {
/* 16 */     super(par2IInventory, par3, par4, par5);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack par1ItemStack) {
/* 26 */     return (super.func_75214_a(par1ItemStack) && par1ItemStack.func_77973_b() == Items.field_151141_av && !func_75216_d());
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\ContainerHorseInventorySlotSaddle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */