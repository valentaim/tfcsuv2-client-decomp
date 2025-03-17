/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ 
/*    */ 
/*    */ public class ItemFenceGate
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemFenceGate(Block par1) {
/* 12 */     super(par1);
/* 13 */     this.metaNames = new String[16];
/* 14 */     System.arraycopy(Global.WOOD_ALL, 0, this.metaNames, 0, 16);
/*    */   }
/*    */   
/*    */   public void func_94581_a(IIconRegister registerer) {}
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemFenceGate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */