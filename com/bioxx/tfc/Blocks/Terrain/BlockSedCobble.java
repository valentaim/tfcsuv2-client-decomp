/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockSedCobble
/*    */   extends BlockCobble
/*    */ {
/*    */   public BlockSedCobble(Material material) {
/* 12 */     super(material);
/* 13 */     this.names = Global.STONE_SED;
/* 14 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/* 15 */     this.looseStart = Global.STONE_SED_START;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149738_a(World world) {
/* 21 */     return 20;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockSedCobble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */