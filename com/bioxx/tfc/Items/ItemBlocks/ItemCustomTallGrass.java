/*    */ package com.bioxx.tfc.Items.ItemBlocks;
/*    */ 
/*    */ import com.bioxx.tfc.Core.ColorizerFoliageTFC;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.IIcon;
/*    */ 
/*    */ 
/*    */ public class ItemCustomTallGrass
/*    */   extends ItemTerraBlock
/*    */ {
/*    */   public ItemCustomTallGrass(Block b) {
/* 16 */     super(b);
/* 17 */     this.metaNames = new String[] { "tallgrass", "fern", "shortgrass" };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public int func_82790_a(ItemStack is, int par2) {
/* 24 */     return ColorizerFoliageTFC.getFoliageColorBasic();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @SideOnly(Side.CLIENT)
/*    */   public IIcon func_77617_a(int par1) {
/* 31 */     return TFCBlocks.tallGrass.func_149691_a(0, par1);
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\ItemBlocks\ItemCustomTallGrass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */