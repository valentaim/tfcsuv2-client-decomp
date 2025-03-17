/*    */ package com.bioxx.tfc.Items;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.bioxx.tfc.api.Enums.EnumWeight;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemStick
/*    */   extends ItemTerra
/*    */ {
/*    */   public ItemStick() {
/* 14 */     func_77656_e(0);
/* 15 */     func_77627_a(true);
/* 16 */     func_77637_a(TFCTabs.TFC_MATERIALS);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_77647_b(int i) {
/* 22 */     return i;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumSize getSize(ItemStack is) {
/* 28 */     return EnumSize.TINY;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumWeight getWeight(ItemStack is) {
/* 33 */     return EnumWeight.LIGHT;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemStick.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */