/*    */ package com.bioxx.tfc.Blocks.Flora;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLogNatural2
/*    */   extends BlockLogNatural
/*    */ {
/*    */   public BlockLogNatural2() {
/* 14 */     this.woodNames = new String[Global.WOOD_ALL.length - 16];
/* 15 */     System.arraycopy(Global.WOOD_ALL, 16, this.woodNames, 0, Global.WOOD_ALL.length - 16);
/* 16 */     this.sideIcons = new IIcon[this.woodNames.length];
/* 17 */     this.innerIcons = new IIcon[this.woodNames.length];
/* 18 */     this.rotatedSideIcons = new IIcon[this.woodNames.length];
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int dmg) {
/* 24 */     dmg += 16; return dmg;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 31 */     if (meta >= this.woodNames.length)
/* 32 */       meta = 0; 
/* 33 */     if (side == 0 || side == 1)
/* 34 */       return this.innerIcons[meta]; 
/* 35 */     return this.sideIcons[meta];
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogNatural2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */