/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Items.ItemTerra;
/*    */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*    */ import com.bioxx.tfc.api.Enums.EnumSize;
/*    */ import com.google.common.collect.HashMultimap;
/*    */ import com.google.common.collect.Multimap;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSpindle
/*    */   extends ItemTerra
/*    */ {
/*    */   public ItemSpindle() {
/* 16 */     func_77656_e(40);
/* 17 */     setFolder("tools/");
/* 18 */     setNoRepair();
/*    */     
/* 20 */     setSize(EnumSize.VERYSMALL);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Multimap func_111205_h() {
/* 26 */     return (Multimap)HashMultimap.create();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumItemReach getReach(ItemStack is) {
/* 31 */     return EnumItemReach.SHORT;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_77639_j() {
/* 37 */     return 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canStack() {
/* 43 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemSpindle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */