/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.inventory.Slot;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotChest
/*    */   extends Slot
/*    */ {
/* 20 */   private EnumSize size = EnumSize.LARGE;
/*    */   
/*    */   private List<Item> exceptions;
/*    */ 
/*    */   
/*    */   public SlotChest(IInventory iinventory, int i, int j, int k) {
/* 26 */     super(iinventory, i, j, k);
/* 27 */     this.exceptions = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 32 */     if (itemstack.func_77973_b().getClass().getName().toLowerCase().contains("horsearmor")) return true;
/*    */     
/* 34 */     if ((itemstack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || itemstack
/* 35 */       .func_77973_b() instanceof net.minecraft.item.ItemHoe) && itemstack.func_77973_b() instanceof ISize && 
/* 36 */       (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.SMALL.stackSize) {
/* 37 */       return false;
/*    */     }
/*    */     
/* 40 */     boolean except = this.exceptions.contains(itemstack.func_77973_b());
/* 41 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && !except)
/*    */     {
/* 43 */       return true;
/*    */     }
/* 45 */     if (!(itemstack.func_77973_b() instanceof ISize) && !except) {
/* 46 */       return true;
/*    */     }
/*    */     
/* 49 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotChest setSize(EnumSize s) {
/* 54 */     this.size = s;
/* 55 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotChest addItemException(List<Item> ex) {
/* 60 */     this.exceptions.addAll(ex);
/* 61 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */