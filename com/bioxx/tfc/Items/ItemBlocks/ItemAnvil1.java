/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.Metal;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ 
/*    */ public class ItemAnvil1
/*    */   extends ItemAnvil
/*    */ {
/*    */   public ItemAnvil1(Block par1) {
/* 13 */     super(par1);
/* 14 */     this.metaNames = new String[] { "Stone", "Copper", "Bronze", "Wrought Iron", "Steel", "Black Steel", "Blue Steel", "Red Steel" };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Metal getMetalType(ItemStack is) {
/* 20 */     int meta = is.func_77960_j();
/* 21 */     switch (meta) {
/*    */       case 1:
/* 23 */         return Global.COPPER;
/* 24 */       case 2: return Global.BRONZE;
/* 25 */       case 3: return Global.WROUGHTIRON;
/* 26 */       case 4: return Global.STEEL;
/* 27 */       case 5: return Global.BLACKSTEEL;
/* 28 */       case 6: return Global.BLUESTEEL;
/* 29 */       case 7: return Global.REDSTEEL;
/* 30 */     }  return Global.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemAnvil1.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */