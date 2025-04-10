/*    */ package com.bioxx.tfc.Blocks.Flora;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ public class BlockLogHoriz2
/*    */   extends BlockLogHoriz
/*    */ {
/*    */   public BlockLogHoriz2(int off) {
/* 14 */     super(off);
/* 15 */     int size = Global.WOOD_ALL.length - 16 - off;
/* 16 */     if (size < 0) size = 0; 
/* 17 */     this.woodNames = new String[size * 2];
/* 18 */     if (off < Global.WOOD_ALL.length - 16) {
/*    */       
/* 20 */       System.arraycopy(Global.WOOD_ALL, 16 + off, this.woodNames, 0, size);
/* 21 */       System.arraycopy(Global.WOOD_ALL, 16 + off, this.woodNames, size, size);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 29 */     int dir = meta >> 3;
/* 30 */     meta = (meta & 0x7) + this.offset;
/* 31 */     if (meta >= ((BlockLogNatural2)TFCBlocks.logNatural2).sideIcons.length) {
/* 32 */       meta = 0;
/*    */     }
/* 34 */     if (dir == 0) {
/*    */       
/* 36 */       if (side == 0 || side == 1)
/* 37 */         return ((BlockLogNatural2)TFCBlocks.logNatural2).sideIcons[meta]; 
/* 38 */       if (side == 2 || side == 3) {
/* 39 */         return ((BlockLogNatural2)TFCBlocks.logNatural2).innerIcons[meta];
/*    */       }
/* 41 */       return ((BlockLogNatural2)TFCBlocks.logNatural2).rotatedSideIcons[meta];
/*    */     } 
/*    */ 
/*    */     
/* 45 */     if (side == 0 || side == 1 || side == 2 || side == 3) {
/* 46 */       return ((BlockLogNatural2)TFCBlocks.logNatural2).rotatedSideIcons[meta];
/*    */     }
/* 48 */     return ((BlockLogNatural2)TFCBlocks.logNatural2).innerIcons[meta];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int dmg) {
/* 55 */     return (dmg & 0x7) + this.offset + 16;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogHoriz2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */