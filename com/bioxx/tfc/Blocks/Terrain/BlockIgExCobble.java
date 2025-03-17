/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockIgExCobble
/*    */   extends BlockCobble
/*    */ {
/*    */   public BlockIgExCobble(Material material) {
/* 13 */     super(material);
/* 14 */     this.names = Global.STONE_IGEX;
/* 15 */     this.icons = new net.minecraft.util.IIcon[this.names.length];
/* 16 */     this.looseStart = Global.STONE_IGEX_START;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149738_a(World world) {
/* 22 */     return 20;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockIgExCobble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */