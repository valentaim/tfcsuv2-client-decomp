/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSapling2
/*    */   extends ItemSapling
/*    */ {
/*    */   public ItemSapling2(Block b) {
/* 12 */     super(b);
/* 13 */     this.metaNames = new String[Global.WOOD_ALL.length - 16];
/* 14 */     System.arraycopy(Global.WOOD_ALL, 16, this.metaNames, 0, Global.WOOD_ALL.length - 16);
/* 15 */     this.icons = new net.minecraft.util.IIcon[this.metaNames.length];
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemSapling2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */