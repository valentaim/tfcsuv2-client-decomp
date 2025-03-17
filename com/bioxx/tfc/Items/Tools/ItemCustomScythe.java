/*    */ package com.bioxx.tfc.Items.Tools;
/*    */ 
/*    */ import com.bioxx.tfc.Core.TFCTabs;
/*    */ import com.bioxx.tfc.api.Enums.EnumItemReach;
/*    */ import com.bioxx.tfc.api.TFCBlocks;
/*    */ import com.google.common.collect.Sets;
/*    */ import java.util.Set;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.entity.EntityLivingBase;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class ItemCustomScythe
/*    */   extends ItemTerraTool
/*    */ {
/* 16 */   private static final Set<Block> BLOCKS = Sets.newHashSet((Object[])new Block[] { TFCBlocks.leaves, TFCBlocks.leaves2 });
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemCustomScythe(Item.ToolMaterial e) {
/* 21 */     super((int)-(e.func_78000_c() * 0.3F), e, BLOCKS);
/* 22 */     func_77656_e(e.func_77997_a() * 3);
/*    */     
/* 24 */     this.field_77864_a = e.func_77998_b();
/* 25 */     func_77637_a(TFCTabs.TFC_TOOLS);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean func_77644_a(ItemStack par1ItemStack, EntityLivingBase par2EntityLivingBase, EntityLivingBase par3EntityLivingBase) {
/* 31 */     par1ItemStack.func_77972_a(1, par3EntityLivingBase);
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumItemReach getReach(ItemStack is) {
/* 38 */     return EnumItemReach.FAR;
/*    */   }
/*    */ }


/* Location:              D:\workdir\java\tfc\[1.7.10]TerraFirmaCraft-0.79.29.jar!\com\bioxx\tfc\Items\Tools\ItemCustomScythe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */