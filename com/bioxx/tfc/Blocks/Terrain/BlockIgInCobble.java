/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIgInCobble
/*    */   extends BlockCobble
/*    */ {
/*    */   public BlockIgInCobble(Material material) {
/* 12 */     super(material);
/* 13 */     this.names = Global.STONE_IGIN;
/* 14 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/* 15 */     this.looseStart = 0;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgInCobble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */