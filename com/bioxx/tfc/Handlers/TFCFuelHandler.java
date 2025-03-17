/*    */ package com.bioxx.tfc.Handlers;
/*    */ 
/*    */ import cpw.mods.fml.common.IFuelHandler;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class TFCFuelHandler
/*    */   implements IFuelHandler
/*    */ {
/* 12 */   public static Map<Item, Integer> fuelItems = new HashMap<>();
/*    */ 
/*    */   
/*    */   public static void registerFuel(Item item, int burnTime) {
/* 16 */     if (!fuelItems.containsKey(item)) {
/* 17 */       fuelItems.put(item, Integer.valueOf(burnTime));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBurnTime(ItemStack is) {
/* 23 */     Item item = is.func_77973_b();
/* 24 */     Integer burnTime = fuelItems.get(item);
/* 25 */     if (burnTime != null) return burnTime.intValue();
/*    */     
/* 27 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Handlers\TFCFuelHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */