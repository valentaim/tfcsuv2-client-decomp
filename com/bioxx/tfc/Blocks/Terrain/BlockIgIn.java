/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIgIn
/*    */   extends BlockStone
/*    */ {
/*    */   public BlockIgIn(Material material) {
/* 13 */     super(material);
/* 14 */     this.dropBlock = TFCBlocks.stoneIgInCobble;
/* 15 */     this.names = Global.STONE_IGIN;
/* 16 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/* 17 */     this.looseStart = 0;
/* 18 */     this.gemChance = 2;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgIn.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */