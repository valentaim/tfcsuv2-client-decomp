/*    */ package com.bioxx.tfc.Blocks.Flora;
/*    */ 
/*    */ import com.bioxx.tfc.api.Constant.Global;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.List;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ import net.minecraft.util.MathHelper;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockLogHoriz
/*    */   extends BlockLogVert
/*    */ {
/*    */   protected int offset;
/*    */   
/*    */   public BlockLogHoriz(int off) {
/* 25 */     this.offset = off;
/* 26 */     this.woodNames = new String[16];
/* 27 */     System.arraycopy(Global.WOOD_ALL, off, this.woodNames, 0, 8);
/* 28 */     System.arraycopy(Global.WOOD_ALL, off, this.woodNames, 8, 8);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_149691_a(int side, int meta) {
/* 35 */     int dir = meta >> 3;
/* 36 */     meta = (meta & 0x7) + this.offset;
/*    */     
/* 38 */     if (dir == 0) {
/*    */       
/* 40 */       if (side == 0 || side == 1)
/* 41 */         return ((BlockLogNatural)TFCBlocks.logNatural).sideIcons[meta]; 
/* 42 */       if (side == 2 || side == 3) {
/* 43 */         return ((BlockLogNatural)TFCBlocks.logNatural).innerIcons[meta];
/*    */       }
/* 45 */       return ((BlockLogNatural)TFCBlocks.logNatural).rotatedSideIcons[meta];
/*    */     } 
/*    */ 
/*    */     
/* 49 */     if (side == 0 || side == 1 || side == 2 || side == 3) {
/* 50 */       return ((BlockLogNatural)TFCBlocks.logNatural).rotatedSideIcons[meta];
/*    */     }
/* 52 */     return ((BlockLogNatural)TFCBlocks.logNatural).innerIcons[meta];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int func_149692_a(int dmg) {
/* 59 */     return (dmg & 0x7) + this.offset;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public void func_149666_a(Item item, CreativeTabs tabs, List<ItemStack> list) {
/* 70 */     for (int i = 0; i < (this.woodNames.length + 1) / 2; i++) {
/* 71 */       list.add(new ItemStack((Block)this, 1, i));
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityliving) {
/* 77 */     int dir = MathHelper.func_76128_c((entityliving.field_70177_z * 4.0F / 360.0F) + 0.5D) & 0x3;
/* 78 */     int metadata = world.func_72805_g(x, y, z);
/*    */     
/* 80 */     if (dir == 1 || dir == 3)
/* 81 */       world.func_72921_c(x, y, z, metadata + 8, 3); 
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Blocks\Flora\BlockLogHoriz.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */