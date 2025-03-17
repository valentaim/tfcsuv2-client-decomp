/*    */ package com.bioxx.tfc.Blocks.Terrain;
/*    */ 
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDryGrass
/*    */   extends BlockGrass
/*    */ {
/*    */   public BlockDryGrass(int texOff) {
/* 11 */     super(texOff);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149651_a(IIconRegister registerer) {
/* 17 */     super.func_149651_a(registerer);
/* 18 */     this.grassTopTexture = registerer.func_94245_a("terrafirmacraft:GrassSparseOverlay");
/* 19 */     this.iconGrassSideOverlay = registerer.func_94245_a("terrafirmacraft:GrassSideSparse");
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Terrain\BlockDryGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */