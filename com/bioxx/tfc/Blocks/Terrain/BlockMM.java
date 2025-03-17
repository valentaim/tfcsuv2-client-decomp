/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockMM
/*    */   extends BlockStone
/*    */ {
/*    */   public static boolean fallInstantly;
/*    */   
/*    */   public BlockMM(Material material) {
/* 15 */     super(material);
/* 16 */     this.dropBlock = TFCBlocks.stoneMMCobble;
/* 17 */     this.names = Global.STONE_MM;
/* 18 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/* 19 */     this.looseStart = Global.STONE_MM_START;
/* 20 */     this.gemChance = 3;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockMM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */