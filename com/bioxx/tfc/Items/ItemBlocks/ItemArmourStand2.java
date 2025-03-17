/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ public class ItemArmourStand2
/*    */   extends ItemArmourStand
/*    */ {
/*    */   public ItemArmourStand2(Block par1) {
/* 11 */     super(par1);
/* 12 */     this.metaNames = new String[Global.WOOD_ALL.length - 16];
/* 13 */     System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, 0, Global.WOOD_ALL.length - 16);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected int getOffset() {
/* 19 */     return 16;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemArmourStand2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */