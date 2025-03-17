/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ public class ItemCustomWoodH
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemCustomWoodH(Block b) {
/* 11 */     super(b);
/* 12 */     this.metaNames = new String[16];
/* 13 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 8);
/* 14 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 8, 8);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCustomWoodH.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */