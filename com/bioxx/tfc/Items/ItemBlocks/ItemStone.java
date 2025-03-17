/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFC_Core;
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ 
/*    */ 
/*    */ public class ItemStone
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemStone(Block b) {
/* 12 */     super(b);
/* 13 */     if (TFC_Core.isStoneIgEx(b)) { this.metaNames = Global.STONE_IGEX; }
/* 14 */     else if (TFC_Core.isStoneIgIn(b)) { this.metaNames = Global.STONE_IGIN; }
/* 15 */     else if (TFC_Core.isStoneSed(b)) { this.metaNames = Global.STONE_SED; }
/* 16 */     else if (TFC_Core.isStoneMM(b)) { this.metaNames = Global.STONE_MM; }
/*    */   
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemStone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */