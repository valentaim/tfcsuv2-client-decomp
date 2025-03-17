/*    */ package com.bioxx.tfc.Blocks.Flora;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSapling2
/*    */   extends BlockSapling
/*    */ {
/*    */   public BlockSapling2() {
/* 12 */     this.woodNames = new String[Global.WOOD_ALL.length - 16];
/* 13 */     System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
/* 14 */     this.icons = new net.minecraft.util.IIcon[this.woodNames.length];
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockSapling2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */