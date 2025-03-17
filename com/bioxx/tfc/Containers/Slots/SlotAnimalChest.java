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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SlotAnimalChest
/*    */   extends Slot
/*    */ {
/* 24 */   private EnumSize size = EnumSize.LARGE;
/*    */   
/*    */   private List<Item> exceptions;
/*    */ 
/*    */   
/*    */   public SlotAnimalChest(IInventory iinventory, int i, int j, int k) {
/* 30 */     super(iinventory, i, j, k);
/* 31 */     this.exceptions = new ArrayList<>();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 36 */     if ((itemstack.func_77973_b() instanceof net.minecraft.item.ItemTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemTerraTool || itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.Tools.ItemWeapon || itemstack
/* 37 */       .func_77973_b() instanceof net.minecraft.item.ItemHoe) && itemstack.func_77973_b() instanceof ISize && 
/* 38 */       (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize < EnumSize.SMALL.stackSize) {
/* 39 */       return false;
/*    */     }
/* 41 */     if (itemstack.func_77973_b() instanceof com.bioxx.tfc.Items.ItemOre || itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemFoodTFC || itemstack.func_77973_b() instanceof com.bioxx.tfc.Food.ItemMeal) return false;
/*    */     
/* 43 */     boolean except = this.exceptions.contains(itemstack.func_77973_b());
/* 44 */     if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize && !except)
/*    */     {
/* 46 */       return true;
/*    */     }
/* 48 */     if (!(itemstack.func_77973_b() instanceof ISize) && !except) {
/* 49 */       return true;
/*    */     }
/*    */     
/* 52 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotAnimalChest setSize(EnumSize s) {
/* 57 */     this.size = s;
/* 58 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotAnimalChest addItemException(List<Item> ex) {
/* 63 */     this.exceptions.addAll(ex);
/* 64 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotAnimalChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */