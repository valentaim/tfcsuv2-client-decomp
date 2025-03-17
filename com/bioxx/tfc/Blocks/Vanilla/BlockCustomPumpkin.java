/*    */ package com.bioxx.tfc.Blocks.Vanilla;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.BlockPumpkin;
/*    */ import net.minecraft.client.renderer.texture.IIconRegister;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ public class BlockCustomPumpkin
/*    */   extends BlockPumpkin
/*    */ {
/*    */   private boolean isLit;
/*    */   @SideOnly(Side.CLIENT)
/*    */   private IIcon topIcon;
/*    */   @SideOnly(Side.CLIENT)
/*    */   private IIcon faceIcon;
/*    */   
/*    */   public BlockCustomPumpkin(boolean lit) {
/* 22 */     super(lit);
/* 23 */     this.isLit = lit;
/* 24 */     func_149647_a(TFCTabs.TFC_BUILDING);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 34 */     return (side == 1) ? this.topIcon : ((side == 0) ? this.topIcon : ((meta == 2 && side == 2) ? this.faceIcon : ((meta == 3 && side == 5) ? this.faceIcon : ((meta == 0 && side == 3) ? this.faceIcon : ((meta == 1 && side == 4) ? this.faceIcon : this.field_149761_L)))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void func_149726_b(World world, int x, int y, int z) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149651_a(IIconRegister iconRegister) {
/* 53 */     this.topIcon = iconRegister.func_94245_a(func_149641_N() + "_top");
/* 54 */     this.field_149761_L = iconRegister.func_94245_a(func_149641_N() + "_side");
/* 55 */     this.faceIcon = this.isLit ? iconRegister.func_94245_a(func_149641_N() + "_face_on") : this.field_149761_L;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Vanilla\BlockCustomPumpkin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */