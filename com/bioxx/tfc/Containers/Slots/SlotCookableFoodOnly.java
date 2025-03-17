/*    */ package com.bioxx.tfc.Containers.Slots;
/*    */ 
/*    */ import com.bioxx.tfc.api.Enums.EnumFoodGroup;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Interfaces.ICookableFood;
/*    */ import com.bioxx.tfc.api.Interfaces.ISize;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.inventory.IInventory;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class SlotCookableFoodOnly
/*    */   extends SlotSize
/*    */ {
/* 16 */   private List<EnumFoodGroup> excpetionsFG = new ArrayList<>();
/* 17 */   private List<EnumFoodGroup> inclusionsFG = new ArrayList<>();
/*    */   
/*    */   public SlotCookableFoodOnly(IInventory iinventory, int i, int j, int k) {
/* 20 */     super(iinventory, i, j, k);
/* 21 */     setSize(EnumSize.SMALL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_75214_a(ItemStack itemstack) {
/* 27 */     if (itemstack.func_77973_b() instanceof ICookableFood) {
/*    */       
/* 29 */       EnumFoodGroup efg = ((ICookableFood)itemstack.func_77973_b()).getFoodGroup();
/* 30 */       if (efg == null)
/* 31 */         return false; 
/* 32 */       boolean except = this.excpetionsFG.contains(efg);
/* 33 */       boolean include = (this.inclusionsFG.contains(efg) || this.inclusionsFG.isEmpty());
/* 34 */       if (except || !include)
/* 35 */         return false; 
/* 36 */       if (itemstack.func_77973_b() instanceof ISize && (((ISize)itemstack.func_77973_b()).getSize(itemstack)).stackSize >= this.size.stackSize)
/* 37 */         return super.func_75214_a(itemstack); 
/*    */     } 
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotCookableFoodOnly addFGException(EnumFoodGroup... ex) {
/* 44 */     for (int i = 0; i < ex.length; i++)
/* 45 */       this.excpetionsFG.add(ex[i]); 
/* 46 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public SlotCookableFoodOnly addFGInclusion(EnumFoodGroup... ex) {
/* 51 */     for (int i = 0; i < ex.length; i++)
/* 52 */       this.inclusionsFG.add(ex[i]); 
/* 53 */     return this;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Containers\Slots\SlotCookableFoodOnly.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */