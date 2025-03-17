/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockSedSmooth
/*    */   extends BlockSmooth
/*    */ {
/*    */   public BlockSedSmooth() {
/* 13 */     super(Material.field_151576_e);
/* 14 */     func_149647_a(TFCTabs.TFC_BUILDING);
/* 15 */     this.names = Global.STONE_SED;
/* 16 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSedSmooth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */